package com.cla.sds.wxapp;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

import com.cla.sds.BaseEntity;
import com.cla.sds.BaseViewPage;
import com.cla.sds.CustomSdsCheckerManager;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.SdsUserContextImpl;
import com.cla.sds.SdsViewScope;
import com.cla.sds.page.PageManagerImpl;
import com.cla.sds.secuser.SecUser;
import com.cla.sds.secuser.SecUserManagerImpl;
import com.cla.sds.services.IamService;
import com.cla.sds.userapp.UserApp;

import com.cla.sds.page.Page;


import com.cla.sds.user.User;
import com.cla.sds.user.UserManagerImpl;
import com.cla.sds.SdsListOfViewScope;
  
import com.cla.sds.userproject.UserProject;
  
import com.cla.sds.pageflowspec.PageFlowSpec;
  
import com.cla.sds.workflowspec.WorkFlowSpec;
  
import com.cla.sds.changerequestspec.ChangeRequestSpec;
  
import com.cla.sds.pagecontentspec.PageContentSpec;
  



import com.terapico.caf.Password;
import com.terapico.caf.baseelement.LoginParam;
import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.uccaf.BaseUserContext;
import com.terapico.utils.ListofUtils;


public class WxappBaseService extends CustomSdsCheckerManager{

	@Override
	public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
			throws IllegalAccessException {
		SdsUserContextImpl userContext = (SdsUserContextImpl)baseUserContext;
		IamService iamService = (IamService) userContext.getBean("iamService");
		Map<String, Object> loginInfo = iamService.getCachedLoginInfo(userContext);

		SecUser secUser = iamService.tryToLoadSecUser(userContext, loginInfo);
		UserApp userApp = iamService.tryToLoadUserApp(userContext, loginInfo);
		if (userApp != null) {
			userApp.setSecUser(secUser);
		}
		if (secUser == null) {
			iamService.onCheckAccessWhenAnonymousFound(userContext, loginInfo);
		}
		afterSecUserAppLoadedWhenCheckAccess(userContext, loginInfo, secUser, userApp);
		if (!isMethodNeedLogin(userContext, methodName, parameters)) {
			return accessOK();
		}

		return super.checkAccess(baseUserContext, methodName, parameters);
	}

	protected boolean isMethodNeedLogin(SdsUserContextImpl userContext, String methodName, Object[] parameters) {
		if (methodName.startsWith("customer")) {
			return true;
		}
		return false;
	}

	protected void afterSecUserAppLoadedWhenCheckAccess(SdsUserContextImpl ctx, Map<String, Object> loginInfo,
			SecUser secUser, UserApp userApp) {
		// 默认加载userApp指定的对象到 currentUserInfo里. 这个逻辑不一定正确,项目上需要自己处理
		if (userApp == null) {
			return;
		}
		BaseEntity app = ctx.getDAOGroup().loadBasicData(userApp.getObjectType(), userApp.getObjectId());
		((CustomSdsUserContextImpl)ctx).setCurrentUserInfo(app);
	}

	public Object clientLogin(SdsUserContextImpl ctx, LoginParam param) throws Exception {

		UserManagerImpl mng = (UserManagerImpl) ctx.getManagerGroup().getUserManager();

		if (param.getLoginMethod().equals(LoginParam.MOBILE_VCODE)) {
			return mng.loginByMobile(ctx, param.getMobile(), param.getVerifyCode());
		}

		if (param.getLoginMethod().equals(LoginParam.WECHAT_APP)) {
			return mng.loginByWechatMiniProgram(ctx, param.getCode());
		}

		if (param.getLoginMethod().equals(LoginParam.WECHAT_WORK_APP)) {
			return mng.loginByWechatWorkMiniProgram(ctx, param.getCode());
		}

		if (param.getLoginMethod().equals(LoginParam.ACCOUNT_PASSWORD)) {
			return mng.loginByPassword(ctx, param.getLogin(), new Password(param.getPassword()));
		}

		throw new Exception("自动生成的登录尚不支持"+param.getLoginMethod()+"方式登录");
	}

	protected Map getPageAsMap(CustomSdsUserContextImpl ctx, String pageId) throws Exception {
		Map result = new HashMap();

		PageManagerImpl pageManager = (PageManagerImpl) pageManagerOf(ctx);
		Page pageData = (Page) pageManager.view(ctx, pageId);

		result.put("id", pageData.getId());
		result.put("pageTitle", pageData.getPageTitle());
		result.put("linkToUrl", pageData.getLinkToUrl());
		result.put("pageType", pageData.getPageType());
		result.put("slideList", pageData.getSlideList());
		result.put("actionList", pageData.getUiActionList());

		return result;
	}

	public Object viewHomePage(CustomSdsUserContextImpl ctx) throws Exception {
    













		SerializeScope vscope = SdsViewScope.getInstance().getPageDetailScope().clone();
    Map result = this.getPageAsMap(ctx, "P000001");
    List sections = new ArrayList();
		UserManagerImpl merchantManager= (UserManagerImpl)userManagerOf(ctx);
		String merchantObjId=ctx.getCurrentUserInfo().getId();
		User merchantObj=(User)merchantManager.view(ctx,merchantObjId);

  
		result.put("sectionList", sections);
		vscope.field("actionList", SerializeScope.EXCLUDE())
		      .field("sectionList", SerializeScope.EXCLUDE());

	  ctx.forceResponseXClassHeader("com.terapico.appview.HomePage");
		return BaseViewPage.serialize(result, vscope);
	}

	public Object updatePhoneNumber (CustomSdsUserContextImpl ctx, String encryptedData, String iv)throws Exception {
		UserManagerImpl merchantManager= (UserManagerImpl)userManagerOf(ctx);
		String merchantObjId=ctx.getCurrentUserInfo().getId();
		User merchantObj=(User)merchantManager.view(ctx,merchantObjId);

		return this.viewMePage(ctx);
	}

	public Object updateProfileInfo(CustomSdsUserContextImpl ctx, String name, String avatar)throws Exception {

		return this.viewMePage(ctx);
	}

	public Object viewMePage(CustomSdsUserContextImpl ctx)throws Exception {
    













		SerializeScope vscope = SdsViewScope.getInstance().getPageDetailScope().clone();
    Map result = this.getPageAsMap(ctx, "P000002");
    List sections = new ArrayList();
		UserManagerImpl merchantManager= (UserManagerImpl)userManagerOf(ctx);
		String merchantObjId=ctx.getCurrentUserInfo().getId();
		User merchantObj=(User)merchantManager.view(ctx,merchantObjId);

  
		result.put("sectionList", sections);
		vscope.field("actionList", SerializeScope.EXCLUDE())
		      .field("sectionList", SerializeScope.EXCLUDE());



		ctx.forceResponseXClassHeader("com.terapico.appview.MePage");
		return BaseViewPage.serialize(result, vscope);
	}

	public Object viewServiceCenterPage(CustomSdsUserContextImpl ctx) throws Exception {
    













		SerializeScope vscope = SdsViewScope.getInstance().getPageDetailScope().clone();
    Map result = this.getPageAsMap(ctx, "P000003");
    List sections = new ArrayList();
		UserManagerImpl merchantManager= (UserManagerImpl)userManagerOf(ctx);
		String merchantObjId=ctx.getCurrentUserInfo().getId();
		User merchantObj=(User)merchantManager.view(ctx,merchantObjId);

  
		//处理section：userProjectListSection
		Map userProjectListSection = ListofUtils.buildSection(
		    "userProjectListSection",
		    "用户项目列表",
		    null,
		    "",
		    "__no_group",
		    "userProjectManager/listByUser/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(userProjectListSection);

		result.put("userProjectListSection", ListofUtils.toShortList(merchantObj.getUserProjectList(), "userProject"));
		vscope.field("userProjectListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( UserProject.class.getName(), null));
  
		//处理section：pageFlowSpecListSection
		Map pageFlowSpecListSection = ListofUtils.buildSection(
		    "pageFlowSpecListSection",
		    "页面流程规格列表",
		    null,
		    "",
		    "__no_group",
		    "pageFlowSpecManager/listByOwner/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(pageFlowSpecListSection);

		result.put("pageFlowSpecListSection", ListofUtils.toShortList(merchantObj.getPageFlowSpecList(), "pageFlowSpec"));
		vscope.field("pageFlowSpecListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( PageFlowSpec.class.getName(), null));
  
		//处理section：workFlowSpecListSection
		Map workFlowSpecListSection = ListofUtils.buildSection(
		    "workFlowSpecListSection",
		    "工作流程规格表",
		    null,
		    "",
		    "__no_group",
		    "workFlowSpecManager/listByOwner/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(workFlowSpecListSection);

		result.put("workFlowSpecListSection", ListofUtils.toShortList(merchantObj.getWorkFlowSpecList(), "workFlowSpec"));
		vscope.field("workFlowSpecListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( WorkFlowSpec.class.getName(), null));
  
		//处理section：changeRequestSpecListSection
		Map changeRequestSpecListSection = ListofUtils.buildSection(
		    "changeRequestSpecListSection",
		    "变更请求规格表",
		    null,
		    "",
		    "__no_group",
		    "changeRequestSpecManager/listByOwner/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(changeRequestSpecListSection);

		result.put("changeRequestSpecListSection", ListofUtils.toShortList(merchantObj.getChangeRequestSpecList(), "changeRequestSpec"));
		vscope.field("changeRequestSpecListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( ChangeRequestSpec.class.getName(), null));
  
		//处理section：pageContentSpecListSection
		Map pageContentSpecListSection = ListofUtils.buildSection(
		    "pageContentSpecListSection",
		    "页面内容规范列表",
		    null,
		    "",
		    "__no_group",
		    "pageContentSpecManager/listByOwner/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(pageContentSpecListSection);

		result.put("pageContentSpecListSection", ListofUtils.toShortList(merchantObj.getPageContentSpecList(), "pageContentSpec"));
		vscope.field("pageContentSpecListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( PageContentSpec.class.getName(), null));
  
		result.put("sectionList", sections);
		vscope.field("actionList", SerializeScope.EXCLUDE())
		      .field("sectionList", SerializeScope.EXCLUDE());

		ctx.forceResponseXClassHeader("com.terapico.appview.ServiceCenterPage");
		return BaseViewPage.serialize(result, vscope);
	}
}
















