
package com.cla.sds.section;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.Password;
import com.terapico.utils.MapUtil;
import com.terapico.utils.ListofUtils;
import com.terapico.utils.TextUtil;
import com.terapico.caf.BlobObject;
import com.terapico.caf.viewpage.SerializeScope;

import com.cla.sds.*;
import com.cla.sds.tree.*;
import com.cla.sds.treenode.*;
import com.cla.sds.SdsUserContextImpl;
import com.cla.sds.iamservice.*;
import com.cla.sds.services.IamService;
import com.cla.sds.secuser.SecUser;
import com.cla.sds.userapp.UserApp;
import com.cla.sds.BaseViewPage;
import com.terapico.uccaf.BaseUserContext;


import com.cla.sds.page.Page;

import com.cla.sds.page.CandidatePage;







public class SectionManagerImpl extends CustomSdsCheckerManager implements SectionManager, BusinessHandler{

	// Only some of ods have such function
	
	



  


	private static final String SERVICE_TYPE = "Section";
	@Override
	public SectionDAO daoOf(SdsUserContext userContext) {
		return sectionDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws SectionManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new SectionManagerException(message);

	}



 	protected Section saveSection(SdsUserContext userContext, Section section, String [] tokensExpr) throws Exception{	
 		//return getSectionDAO().save(section, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSection(userContext, section, tokens);
 	}
 	
 	protected Section saveSectionDetail(SdsUserContext userContext, Section section) throws Exception{	

 		
 		return saveSection(userContext, section, allTokens());
 	}
 	
 	public Section loadSection(SdsUserContext userContext, String sectionId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSection(sectionId);
		checkerOf(userContext).throwExceptionIfHasErrors( SectionManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Section section = loadSection( userContext, sectionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,section, tokens);
 	}
 	
 	
 	 public Section searchSection(SdsUserContext userContext, String sectionId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfSection(sectionId);
		checkerOf(userContext).throwExceptionIfHasErrors( SectionManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Section section = loadSection( userContext, sectionId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,section, tokens);
 	}
 	
 	

 	protected Section present(SdsUserContext userContext, Section section, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,section,tokens);
		
		
		Section  sectionToPresent = sectionDaoOf(userContext).present(section, tokens);
		
		List<BaseEntity> entityListToNaming = sectionToPresent.collectRefercencesFromLists();
		sectionDaoOf(userContext).alias(entityListToNaming);
		
		return  sectionToPresent;
		
		
	}
 
 	
 	
 	public Section loadSectionDetail(SdsUserContext userContext, String sectionId) throws Exception{	
 		Section section = loadSection( userContext, sectionId, allTokens());
 		return present(userContext,section, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String sectionId) throws Exception{	
 		Section section = loadSection( userContext, sectionId, viewTokens());
 		return present(userContext,section, allTokens());
		
 	}
 	protected Section saveSection(SdsUserContext userContext, Section section, Map<String,Object>tokens) throws Exception{	
 		return sectionDaoOf(userContext).save(section, tokens);
 	}
 	protected Section loadSection(SdsUserContext userContext, String sectionId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfSection(sectionId);
		checkerOf(userContext).throwExceptionIfHasErrors( SectionManagerException.class);

 
 		return sectionDaoOf(userContext).load(sectionId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Section section, Map<String, Object> tokens){
		super.addActions(userContext, section, tokens);
		
		addAction(userContext, section, tokens,"@create","createSection","createSection/","main","primary");
		addAction(userContext, section, tokens,"@update","updateSection","updateSection/"+section.getId()+"/","main","primary");
		addAction(userContext, section, tokens,"@copy","cloneSection","cloneSection/"+section.getId()+"/","main","primary");
		
		addAction(userContext, section, tokens,"section.transfer_to_page","transferToAnotherPage","transferToAnotherPage/"+section.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Section section, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Section createSection(SdsUserContext userContext, String title,String brief,String icon,int displayOrder,String viewGroup,String linkToUrl,String pageId) throws Exception
	//public Section createSection(SdsUserContext userContext,String title, String brief, String icon, int displayOrder, String viewGroup, String linkToUrl, String pageId) throws Exception
	{

		

		

		checkerOf(userContext).checkTitleOfSection(title);
		checkerOf(userContext).checkBriefOfSection(brief);
		checkerOf(userContext).checkIconOfSection(icon);
		checkerOf(userContext).checkDisplayOrderOfSection(displayOrder);
		checkerOf(userContext).checkViewGroupOfSection(viewGroup);
		checkerOf(userContext).checkLinkToUrlOfSection(linkToUrl);
	
		checkerOf(userContext).throwExceptionIfHasErrors(SectionManagerException.class);


		Section section=createNewSection();	

		section.setTitle(title);
		section.setBrief(brief);
		section.setIcon(icon);
		section.setDisplayOrder(displayOrder);
		section.setViewGroup(viewGroup);
		section.setLinkToUrl(linkToUrl);
			
		Page page = loadPage(userContext, pageId,emptyOptions());
		section.setPage(page);
		
		

		section = saveSection(userContext, section, emptyOptions());
		
		onNewInstanceCreated(userContext, section);
		return section;


	}
	protected Section createNewSection()
	{

		return new Section();
	}

	protected void checkParamsForUpdatingSection(SdsUserContext userContext,String sectionId, int sectionVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfSection(sectionId);
		checkerOf(userContext).checkVersionOfSection( sectionVersion);
		

		if(Section.TITLE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTitleOfSection(parseString(newValueExpr));
		
			
		}
		if(Section.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfSection(parseString(newValueExpr));
		
			
		}
		if(Section.ICON_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkIconOfSection(parseString(newValueExpr));
		
			
		}
		if(Section.DISPLAY_ORDER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDisplayOrderOfSection(parseInt(newValueExpr));
		
			
		}
		if(Section.VIEW_GROUP_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkViewGroupOfSection(parseString(newValueExpr));
		
			
		}
		if(Section.LINK_TO_URL_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkLinkToUrlOfSection(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(SectionManagerException.class);


	}



	public Section clone(SdsUserContext userContext, String fromSectionId) throws Exception{

		return sectionDaoOf(userContext).clone(fromSectionId, this.allTokens());
	}

	public Section internalSaveSection(SdsUserContext userContext, Section section) throws Exception
	{
		return internalSaveSection(userContext, section, allTokens());

	}
	public Section internalSaveSection(SdsUserContext userContext, Section section, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingSection(userContext, sectionId, sectionVersion, property, newValueExpr, tokensExpr);


		synchronized(section){
			//will be good when the section loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Section.
			if (section.isChanged()){
			
			}
			section = saveSection(userContext, section, options);
			return section;

		}

	}

	public Section updateSection(SdsUserContext userContext,String sectionId, int sectionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSection(userContext, sectionId, sectionVersion, property, newValueExpr, tokensExpr);



		Section section = loadSection(userContext, sectionId, allTokens());
		if(section.getVersion() != sectionVersion){
			String message = "The target version("+section.getVersion()+") is not equals to version("+sectionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(section){
			//will be good when the section loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Section.
			
			section.changeProperty(property, newValueExpr);
			section = saveSection(userContext, section, tokens().done());
			return present(userContext,section, mergedAllTokens(tokensExpr));
			//return saveSection(userContext, section, tokens().done());
		}

	}

	public Section updateSectionProperty(SdsUserContext userContext,String sectionId, int sectionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingSection(userContext, sectionId, sectionVersion, property, newValueExpr, tokensExpr);

		Section section = loadSection(userContext, sectionId, allTokens());
		if(section.getVersion() != sectionVersion){
			String message = "The target version("+section.getVersion()+") is not equals to version("+sectionVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(section){
			//will be good when the section loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Section.

			section.changeProperty(property, newValueExpr);
			
			section = saveSection(userContext, section, tokens().done());
			return present(userContext,section, mergedAllTokens(tokensExpr));
			//return saveSection(userContext, section, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected SectionTokens tokens(){
		return SectionTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SectionTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SectionTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPage(SdsUserContext userContext, String sectionId, String anotherPageId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfSection(sectionId);
 		checkerOf(userContext).checkIdOfPage(anotherPageId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(SectionManagerException.class);

 	}
 	public Section transferToAnotherPage(SdsUserContext userContext, String sectionId, String anotherPageId) throws Exception
 	{
 		checkParamsForTransferingAnotherPage(userContext, sectionId,anotherPageId);
 
		Section section = loadSection(userContext, sectionId, allTokens());	
		synchronized(section){
			//will be good when the section loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Page page = loadPage(userContext, anotherPageId, emptyOptions());		
			section.updatePage(page);		
			section = saveSection(userContext, section, emptyOptions());
			
			return present(userContext,section, allTokens());
			
		}

 	}

	


	public CandidatePage requestCandidatePage(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePage result = new CandidatePage();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("pageTitle");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Page> candidateList = pageDaoOf(userContext).requestCandidatePageForSection(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Page loadPage(SdsUserContext userContext, String newPageId, Map<String,Object> options) throws Exception
 	{

 		return pageDaoOf(userContext).load(newPageId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String sectionId, int sectionVersion) throws Exception {
		//deleteInternal(userContext, sectionId, sectionVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String sectionId, int sectionVersion) throws Exception{

		sectionDaoOf(userContext).delete(sectionId, sectionVersion);
	}

	public Section forgetByAll(SdsUserContext userContext, String sectionId, int sectionVersion) throws Exception {
		return forgetByAllInternal(userContext, sectionId, sectionVersion);
	}
	protected Section forgetByAllInternal(SdsUserContext userContext,
			String sectionId, int sectionVersion) throws Exception{

		return sectionDaoOf(userContext).disconnectFromAll(sectionId, sectionVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SectionManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return sectionDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(SdsUserContext userContext, Section newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

	// -----------------------------------//  登录部分处理 \\-----------------------------------
	// 手机号+短信验证码 登录
	public Object loginByMobile(SdsUserContextImpl userContext, String mobile, String verifyCode) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(SdsBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByMobile");
		LoginData loginData = new LoginData();
		loginData.setMobile(mobile);
		loginData.setVerifyCode(verifyCode);

		LoginContext loginContext = LoginContext.of(LoginMethod.MOBILE, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 账号+密码登录
	public Object loginByPassword(SdsUserContextImpl userContext, String loginId, Password password) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(SdsBaseUtils.getRequestAppType(userContext), this.getBeanName(), "loginByPassword");
		LoginData loginData = new LoginData();
		loginData.setLoginId(loginId);
		loginData.setPassword(password.getClearTextPassword());

		LoginContext loginContext = LoginContext.of(LoginMethod.PASSWORD, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 微信小程序登录
	public Object loginByWechatMiniProgram(SdsUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(SdsBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 企业微信小程序登录
	public Object loginByWechatWorkMiniProgram(SdsUserContextImpl userContext, String code) throws Exception {
		LoginChannel loginChannel = LoginChannel.of(SdsBaseUtils.getRequestAppType(userContext), this.getBeanName(),
				"loginByWechatWorkMiniProgram");
		LoginData loginData = new LoginData();
		loginData.setCode(code);

		LoginContext loginContext = LoginContext.of(LoginMethod.WECHAT_WORK_MINIPROGRAM, loginChannel, loginData);
		return processLoginRequest(userContext, loginContext);
	}
	// 调用登录处理
	protected Object processLoginRequest(SdsUserContextImpl userContext, LoginContext loginContext) throws Exception {
		IamService iamService = (IamService) userContext.getBean("iamService");
		LoginResult loginResult = iamService.doLogin(userContext, loginContext, this);
		// 根据登录结果
		if (!loginResult.isAuthenticated()) {
			throw new Exception(loginResult.getMessage());
		}
		if (loginResult.isSuccess()) {
			return onLoginSuccess(userContext, loginResult);
		}
		if (loginResult.isNewUser()) {
			throw new Exception("请联系你的上级,先为你创建账号,然后再来登录.");
		}
		return new LoginForm();
	}

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

	// 判断哪些接口需要登录后才能执行. 默认除了loginBy开头的,其他都要登录
	protected boolean isMethodNeedLogin(SdsUserContextImpl userContext, String methodName, Object[] parameters) {
		if (methodName.startsWith("loginBy")) {
			return false;
		}
		if (methodName.startsWith("logout")) {
			return false;
		}
		return true;
	}

	// 在checkAccess中加载了secUser和userApp后会调用此方法,用于定制化的用户数据加载. 默认什么也不做
	protected void afterSecUserAppLoadedWhenCheckAccess(SdsUserContextImpl userContext, Map<String, Object> loginInfo,
			SecUser secUser, UserApp userApp) throws IllegalAccessException{
	}



	protected Object onLoginSuccess(SdsUserContext userContext, LoginResult loginResult) throws Exception {
		// by default, return the view of this object
		UserApp userApp = loginResult.getLoginContext().getLoginTarget().getUserApp();
		return this.view(userContext, userApp.getObjectId());
	}

	public void onAuthenticationFailed(SdsUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, failed is failed, nothing can do
	}
	// when user authenticated success, but no sec_user related, this maybe a new user login from 3-rd party service.
	public void onAuthenticateNewUserLogged(SdsUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// Generally speaking, when authenticated user logined, we will create a new account for him/her.
		// you need do it like :
		// First, you should create new data such as:
		//   Section newSection = this.createSection(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newSection
		//   UserApp uerApp = userAppManagerOf(userContext).createUserApp(userContext, secUser.getId(), ...
		// Also, set it into loginContext:
		//   loginContext.getLoginTarget().setUserApp(userApp);
		// Since many of detailed info were depending business requirement, So,
		throw new Exception("请重载函数onAuthenticateNewUserLogged()以处理新用户登录");
	}
	public void onAuthenticateUserLogged(SdsUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, find the correct user-app
		SecUser secUser = loginResult.getLoginContext().getLoginTarget().getSecUser();
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Section.INTERNAL_TYPE);
		SmartList<UserApp> userApps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, EO);
		if (userApps == null || userApps.isEmpty()) {
			throw new Exception("您的账号未关联销售人员,请联系客服处理账号异常.");
		}
		UserApp userApp = userApps.first();
		userApp.setSecUser(secUser);
		loginResult.getLoginContext().getLoginTarget().setUserApp(userApp);
		BaseEntity app = userContext.getDAOGroup().loadBasicData(userApp.getObjectType(), userApp.getObjectId());
		((SdsBizUserContextImpl)userContext).setCurrentUserInfo(app);
	}
	// -----------------------------------\\  登录部分处理 //-----------------------------------


	// -----------------------------------// list-of-view 处理 \\-----------------------------------
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<Section> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<Page> pageList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, Page.class);
		userContext.getDAOGroup().enhanceList(pageList, Page.class);


    }
	
	public Object listByPage(SdsUserContext userContext,String pageId) throws Exception {
		return listPageByPage(userContext, pageId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByPage(SdsUserContext userContext,String pageId, int start, int count) throws Exception {
		SmartList<Section> list = sectionDaoOf(userContext).findSectionByPage(pageId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(Section.class);
		page.setContainerObject(Page.withId(pageId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("板块列表");
		page.setRequestName("listByPage");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByPage/%s/",  getBeanName(), pageId)));

		page.assemblerContent(userContext, "listByPage");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(SdsUserContext userContext, String sectionId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getSectionDetailScope().clone();
		Section merchantObj = (Section) this.view(userContext, sectionId);
    String merchantObjId = sectionId;
    String linkToUrl =	"sectionManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "板块"+"详情";
		Map result = new HashMap();
		List propList = new ArrayList();
		List sections = new ArrayList();
 
		propList.add(
				MapUtil.put("id", "1-id")
				    .put("fieldName", "id")
				    .put("label", "ID")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("id", merchantObj.getId());

		propList.add(
				MapUtil.put("id", "2-title")
				    .put("fieldName", "title")
				    .put("label", "标题")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("title", merchantObj.getTitle());

		propList.add(
				MapUtil.put("id", "3-brief")
				    .put("fieldName", "brief")
				    .put("label", "短暂的")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("brief", merchantObj.getBrief());

		propList.add(
				MapUtil.put("id", "4-icon")
				    .put("fieldName", "icon")
				    .put("label", "图标")
				    .put("type", "image")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("icon", merchantObj.getIcon());

		propList.add(
				MapUtil.put("id", "5-displayOrder")
				    .put("fieldName", "displayOrder")
				    .put("label", "顺序")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("displayOrder", merchantObj.getDisplayOrder());

		propList.add(
				MapUtil.put("id", "6-viewGroup")
				    .put("fieldName", "viewGroup")
				    .put("label", "视图组")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("viewGroup", merchantObj.getViewGroup());

		propList.add(
				MapUtil.put("id", "7-linkToUrl")
				    .put("fieldName", "linkToUrl")
				    .put("label", "链接网址")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("linkToUrl", merchantObj.getLinkToUrl());

		propList.add(
				MapUtil.put("id", "8-page")
				    .put("fieldName", "page")
				    .put("label", "页面")
				    .put("type", "auto")
				    .put("linkToUrl", "pageManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"display_order\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"page_title\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("page", merchantObj.getPage());

		//处理 sectionList

		result.put("propList", propList);
		result.put("sectionList", sections);
		result.put("pageTitle", pageTitle);
		result.put("linkToUrl", linkToUrl);

		vscope.field("propList", SerializeScope.EXCLUDE())
				.field("sectionList", SerializeScope.EXCLUDE())
				.field("pageTitle", SerializeScope.EXCLUDE())
				.field("linkToUrl", SerializeScope.EXCLUDE());
		userContext.forceResponseXClassHeader("com.terapico.appview.DetailPage");
		return BaseViewPage.serialize(result, vscope);
	}

}


