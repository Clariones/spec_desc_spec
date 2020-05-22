package com.cla.sds.pcclient;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.FootprintProducer;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsBaseConstants;
import com.cla.sds.SdsBaseUtils;
import com.cla.sds.SmartList;
import com.cla.sds.secuser.SecUser;
import com.cla.sds.userapp.UserApp;
import com.cla.sds.userapp.UserAppTokens;
import com.cla.sds.SdsException;

import com.cla.sds.client.ClientService;
import com.cla.sds.pcclientpageview.*;
import com.terapico.utils.DateTimeUtil;
import com.terapico.utils.JWTUtil;
import com.terapico.utils.MapUtil;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.RandomUtil;
import com.terapico.utils.TextUtil;

import com.terapico.uccaf.BaseUserContext;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.caf.appview.ChangeRequestPostData;
import com.terapico.caf.appview.ChangeRequestProcessResult;
import com.terapico.caf.viewcomponent.GenericFormPage;
import com.cla.sds.ChangeRequestHelper;
import com.cla.sds.CR;
import com.cla.sds.ChangeRequestHelper;
/**
 * 此类负责：声明所有PcClientViewService中所使用的方法和常量。 单独列出的目的是便于维护。
 * @author clariones
 *
 */
public abstract class BasePcClientViewService extends ClientService implements FootprintProducer{
	public static final int $PRC_RESULT_OBJECT_WAS_SET = -1;
	public static final int PRC_BY_DEFAULT = 0;
	protected boolean returnRightNow(int resultCode) {
		return $PRC_RESULT_OBJECT_WAS_SET == resultCode;
	}
	protected void getCurrentUserInfo(CustomSdsUserContextImpl ctx) {
		// 从redis的数据中获得当前用户. 默认已经在checkAccess/loginXXX中完成, 如果有特别处理,可以在此完成
	}
	protected void ensureCurrentUserInfo(CustomSdsUserContextImpl ctx) throws Exception{
		getCurrentUserInfo(ctx);
		if (ctx.getCurrentUserInfo() == null){
			throw new Exception("请先登录");
		}
	}
	protected void commonLog(CustomSdsUserContextImpl ctx, String eventCode, String title, String key1, String key2,
			String key3, Object detailInfo) {
		// by default, only print log
		try {
			System.out.println("[  PcClientViewBizService]: " + DebugUtil.dumpAsJson(MapUtil.putIf("eventCode", eventCode)
					.putIf("title", title).putIf("key1", key1).putIf("key2", key2).putIf("key3", key3)
					.putIf("detailInfo", detailInfo).into_map()	, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters) {
		super.onAccess(baseUserContext, methodName, parameters);
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) baseUserContext;
		ctx.saveAccessInfo(getBeanName(), methodName, parameters);
	}
	
	protected boolean hasFormResubmitFlag(CustomSdsUserContextImpl  ctx) {
		Object flag = ctx.getFromContextLocalStorage(SdsBaseConstants.KEY_RE_SUBMIT);
		if (flag == null) {
			return false;
		}
		if (flag instanceof Boolean) {
			return ((Boolean) flag).booleanValue();
		}
		return false;
	}
	protected Map<String, Object> makeToast(String content, int duration, String type) {
		HashMap<String, Object> toast = new HashMap<String, Object>();
		toast.put("text", content);
		toast.put("duration", duration * 1000);
		toast.put("icon", type);
		toast.put("position", "center");
		return toast;
	}
	protected static String makeUrl(String methodName, Object ... params) {
		return makeUrlF(methodName, true, params);
	}
	protected static String makeUrlF(String methodName, boolean encode, Object ... params) {
		StringBuilder sb = new StringBuilder("clientService").append("/").append(methodName).append("/");
		if (params != null) {
			for(Object param : params) {
				if (param == null || ((param instanceof String) && TextUtil.isBlank((String) param))) {
					sb.append('+').append('/');
					continue;
				}
				if (param instanceof Date) {
					sb.append(DateTimeUtil.formatDate((Date)param, "yyyy-MM-dd'T'HH:mm:ss")).append('/');
					continue;
				}
				boolean isVariable = false;
				if (param instanceof String && ((String) param).length() > 0) {
					isVariable = ((String) param).charAt(0) == ':';
				}
				if (encode && !isVariable) {
					try {
						sb.append(URLEncoder.encode(String.valueOf(param), "utf-8"));
					} catch (UnsupportedEncodingException e) {
						sb.append(URLEncoder.encode(String.valueOf(param)));
					}
				} else {
					sb.append(String.valueOf(param));
				}
				sb.append('/');
			}
		}
		return sb.toString();
	}
	
	public Object sendVerifyCode(CustomSdsUserContextImpl ctx, String mobile) throws Exception {
		mobile = TextUtil.formatChinaMobile(mobile);
		if (mobile == null) {
			throw new Exception("您输入的" + mobile + "不是有效的手机号");
		}
		String verifyCode = RandomUtil.randomNum(6);
		cacheVerifyCode(ctx, mobile, verifyCode);
		ctx.sendMessage(mobile, getSmsSign(ctx), getSmsVCodeTemplate(ctx), MapUtil.with("code", verifyCode));
		if (ctx.isProductEnvironment()) {
			ctx.setToast(makeToast("验证码已经发送到手机" + TextUtil.shrink(mobile, 3, 3, "***") + ",请注意查收", 5, "info"));
		} else {
			ctx.setToast(makeToast("验证码" + verifyCode + "已经发送到手机" + TextUtil.shrink(mobile, 3, 3, "***") + ",请注意查收", 5,
					"info"));
		}
		return assemblerToastPage(ctx, "sendVerifyCode");
		
	}
	protected Object assemblerToastPage(CustomSdsUserContextImpl ctx, String methodName) throws Exception {
		return ctx.getToast();
	}
    
    protected String getSmsVCodeTemplate(CustomSdsUserContextImpl ctx) {
		return TextUtil.getExtVariable("SMS_VERIFY_CODE", "");
	}
	protected String getSmsSign(CustomSdsUserContextImpl ctx) {
		return TextUtil.getExtVariable("SMS_SIGN", "");
	}
	// 处理请求：服务器心跳检查 (heart beat)
	public static String makeHeartBeatUrl(CustomSdsUserContextImpl ctx){
		return makeUrl("customerHeartBeat");
	}
	// 处理请求：登录页面 (login)
	public static String makeLoginUrl(CustomSdsUserContextImpl ctx, String login , String password){
		return makeUrl("login", login , password);
	}
	// 处理请求：退出登录 (logout)
	public static String makeLogoutUrl(CustomSdsUserContextImpl ctx){
		return makeUrl("logout");
	}
	// 处理请求：这个程序员很懒,什么也没留下 (view dashboard)
	public static String makeViewDashboardUrl(CustomSdsUserContextImpl ctx){
		return makeUrl("customerViewDashboard");
	}
	// 处理请求：打开某个场景的page flow视图 (view page flow)
	public static String makeViewPageFlowUrl(CustomSdsUserContextImpl ctx, String pageFlowSpecId){
		return makeUrl("customerViewPageFlow", pageFlowSpecId);
	}
	// 处理请求：打开某个场景的work flow视图 (view work flow)
	public static String makeViewWorkFlowUrl(CustomSdsUserContextImpl ctx, String workFlowSpecId){
		return makeUrl("customerViewWorkFlow", workFlowSpecId);
	}
	// 处理请求：打开某个change request视图 (view change request)
	public static String makeViewChangeRequestUrl(CustomSdsUserContextImpl ctx, String changeRequestSpecId){
		return makeUrl("customerViewChangeRequest", changeRequestSpecId);
	}
	// 处理请求：打开某个页面的内容视图 (view page content)
	public static String makeViewPageContentUrl(CustomSdsUserContextImpl ctx, String pageContentSpecId){
		return makeUrl("customerViewPageContent", pageContentSpecId);
	}

	/** 处理请求：服务器心跳检查. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestCustomerHeartBeat(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：登录页面. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestLogin(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：退出登录. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestLogout(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestCustomerViewDashboard(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：打开某个场景的page flow视图. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestCustomerViewPageFlow(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：打开某个场景的work flow视图. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestCustomerViewWorkFlow(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：打开某个change request视图. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestCustomerViewChangeRequest(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}
	/** 处理请求：打开某个页面的内容视图. 返回值：PRC_BY_DEFAULT: ;  */
	protected int processRequestCustomerViewPageContent(CustomSdsUserContextImpl ctx) throws Exception { return PRC_BY_DEFAULT;}

	protected UserDashboardPage assemblerUserDashboardPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		UserDashboardPage page = new UserDashboardPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected PageContentWorkshopPage assemblerPageContentWorkshopPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		PageContentWorkshopPage page = new PageContentWorkshopPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected PageFlowWorkshopPage assemblerPageFlowWorkshopPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		PageFlowWorkshopPage page = new PageFlowWorkshopPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected PcClientLoginPage assemblerPcClientLoginPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		PcClientLoginPage page = new PcClientLoginPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected WorkFlowWorkshopPage assemblerWorkFlowWorkshopPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		WorkFlowWorkshopPage page = new WorkFlowWorkshopPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected ChangeRequestWorkshopPage assemblerChangeRequestWorkshopPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		ChangeRequestWorkshopPage page = new ChangeRequestWorkshopPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected SimpleToastPage assemblerSimpleToastPage(CustomSdsUserContextImpl ctx, String requestName)throws Exception {
		SimpleToastPage page = new SimpleToastPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
}
