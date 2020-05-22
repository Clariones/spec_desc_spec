package com.cla.sds.pcclient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.appview.ChangeRequestPostData;
import com.terapico.caf.appview.ChangeRequestProcessResult;
import com.terapico.caf.viewcomponent.GenericFormPage;

import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.BaseViewPage;
import com.cla.sds.CR;
import com.cla.sds.ChangeRequestHelper;




/**
 * 此类负责：所有的页面入口。 以及页面的组装
 * @author clariones
 *
 */
public abstract class PcClientViewService extends BasePcClientViewService{
	// 服务器心跳检查(heart beat)
	public Object customerHeartBeat(SdsUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("customerHeartBeat", false);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "customerHeartBeat", "服务器心跳检查", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestCustomerHeartBeat(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerSimpleToastPage(ctx, "customerHeartBeat");
		return page.doRender(ctx);
	}
	
	// 登录页面(login)
	public Object login(SdsUserContext userContext, String login , String password) throws Exception {
		String accessUrl = makeUrlF("login", false, login , password);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		getCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setLogin(login);
		ctx.setPassword(password);
		commonLog(ctx, "login", "登录页面", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, login , password), null);
		int resultCode = processRequestLogin(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerUserDashboardPage(ctx, "login");
		return page.doRender(ctx);
	}
	
	// 退出登录(logout)
	public Object logout(SdsUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("logout", false);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		getCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "logout", "退出登录", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestLogout(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerPcClientLoginPage(ctx, "logout");
		return page.doRender(ctx);
	}
	
	// 这个程序员很懒,什么也没留下(view dashboard)
	public Object customerViewDashboard(SdsUserContext userContext) throws Exception {
		String accessUrl = makeUrlF("customerViewDashboard", false);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		commonLog(ctx, "customerViewDashboard", "这个程序员很懒,什么也没留下", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false), null);
		int resultCode = processRequestCustomerViewDashboard(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerUserDashboardPage(ctx, "customerViewDashboard");
		return page.doRender(ctx);
	}
	
	// 打开某个场景的page flow视图(view page flow)
	public Object customerViewPageFlow(SdsUserContext userContext, String pageFlowSpecId) throws Exception {
		String accessUrl = makeUrlF("customerViewPageFlow", false, pageFlowSpecId);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setPageFlowSpecId(pageFlowSpecId);
		commonLog(ctx, "customerViewPageFlow", "打开某个场景的page flow视图", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, pageFlowSpecId), null);
		int resultCode = processRequestCustomerViewPageFlow(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerPageFlowWorkshopPage(ctx, "customerViewPageFlow");
		return page.doRender(ctx);
	}
	
	// 打开某个场景的work flow视图(view work flow)
	public Object customerViewWorkFlow(SdsUserContext userContext, String workFlowSpecId) throws Exception {
		String accessUrl = makeUrlF("customerViewWorkFlow", false, workFlowSpecId);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setWorkFlowSpecId(workFlowSpecId);
		commonLog(ctx, "customerViewWorkFlow", "打开某个场景的work flow视图", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, workFlowSpecId), null);
		int resultCode = processRequestCustomerViewWorkFlow(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerWorkFlowWorkshopPage(ctx, "customerViewWorkFlow");
		return page.doRender(ctx);
	}
	
	// 打开某个change request视图(view change request)
	public Object customerViewChangeRequest(SdsUserContext userContext, String changeRequestSpecId) throws Exception {
		String accessUrl = makeUrlF("customerViewChangeRequest", false, changeRequestSpecId);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setChangeRequestSpecId(changeRequestSpecId);
		commonLog(ctx, "customerViewChangeRequest", "打开某个change request视图", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, changeRequestSpecId), null);
		int resultCode = processRequestCustomerViewChangeRequest(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerChangeRequestWorkshopPage(ctx, "customerViewChangeRequest");
		return page.doRender(ctx);
	}
	
	// 打开某个页面的内容视图(view page content)
	public Object customerViewPageContent(SdsUserContext userContext, String pageContentSpecId) throws Exception {
		String accessUrl = makeUrlF("customerViewPageContent", false, pageContentSpecId);
		
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl) userContext;
		ctx.setAccessUrl(accessUrl);
		ensureCurrentUserInfo(ctx);
		ctx.addFootprint(this);
		ctx.setPageContentSpecId(pageContentSpecId);
		commonLog(ctx, "customerViewPageContent", "打开某个页面的内容视图", ctx.getRemoteIP(), ctx.tokenId(), makeUrlF("", false, pageContentSpecId), null);
		int resultCode = processRequestCustomerViewPageContent(ctx);
		if (returnRightNow(resultCode)){
			return ctx.getResultObject();
		}
		BaseViewPage page = assemblerPageContentWorkshopPage(ctx, "customerViewPageContent");
		return page.doRender(ctx);
	}
	
}

