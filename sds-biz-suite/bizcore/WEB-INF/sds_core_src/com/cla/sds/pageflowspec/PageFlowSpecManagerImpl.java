
package com.cla.sds.pageflowspec;

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


import com.cla.sds.user.User;
import com.cla.sds.project.Project;

import com.cla.sds.user.CandidateUser;
import com.cla.sds.project.CandidateProject;







public class PageFlowSpecManagerImpl extends CustomSdsCheckerManager implements PageFlowSpecManager, BusinessHandler{

	// Only some of ods have such function
	
	



  


	private static final String SERVICE_TYPE = "PageFlowSpec";
	@Override
	public PageFlowSpecDAO daoOf(SdsUserContext userContext) {
		return pageFlowSpecDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws PageFlowSpecManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new PageFlowSpecManagerException(message);

	}



 	protected PageFlowSpec savePageFlowSpec(SdsUserContext userContext, PageFlowSpec pageFlowSpec, String [] tokensExpr) throws Exception{	
 		//return getPageFlowSpecDAO().save(pageFlowSpec, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePageFlowSpec(userContext, pageFlowSpec, tokens);
 	}
 	
 	protected PageFlowSpec savePageFlowSpecDetail(SdsUserContext userContext, PageFlowSpec pageFlowSpec) throws Exception{	

 		
 		return savePageFlowSpec(userContext, pageFlowSpec, allTokens());
 	}
 	
 	public PageFlowSpec loadPageFlowSpec(SdsUserContext userContext, String pageFlowSpecId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).throwExceptionIfHasErrors( PageFlowSpecManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		PageFlowSpec pageFlowSpec = loadPageFlowSpec( userContext, pageFlowSpecId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,pageFlowSpec, tokens);
 	}
 	
 	
 	 public PageFlowSpec searchPageFlowSpec(SdsUserContext userContext, String pageFlowSpecId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).throwExceptionIfHasErrors( PageFlowSpecManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		PageFlowSpec pageFlowSpec = loadPageFlowSpec( userContext, pageFlowSpecId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,pageFlowSpec, tokens);
 	}
 	
 	

 	protected PageFlowSpec present(SdsUserContext userContext, PageFlowSpec pageFlowSpec, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,pageFlowSpec,tokens);
		
		
		PageFlowSpec  pageFlowSpecToPresent = pageFlowSpecDaoOf(userContext).present(pageFlowSpec, tokens);
		
		List<BaseEntity> entityListToNaming = pageFlowSpecToPresent.collectRefercencesFromLists();
		pageFlowSpecDaoOf(userContext).alias(entityListToNaming);
		
		return  pageFlowSpecToPresent;
		
		
	}
 
 	
 	
 	public PageFlowSpec loadPageFlowSpecDetail(SdsUserContext userContext, String pageFlowSpecId) throws Exception{	
 		PageFlowSpec pageFlowSpec = loadPageFlowSpec( userContext, pageFlowSpecId, allTokens());
 		return present(userContext,pageFlowSpec, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String pageFlowSpecId) throws Exception{	
 		PageFlowSpec pageFlowSpec = loadPageFlowSpec( userContext, pageFlowSpecId, viewTokens());
 		return present(userContext,pageFlowSpec, allTokens());
		
 	}
 	protected PageFlowSpec savePageFlowSpec(SdsUserContext userContext, PageFlowSpec pageFlowSpec, Map<String,Object>tokens) throws Exception{	
 		return pageFlowSpecDaoOf(userContext).save(pageFlowSpec, tokens);
 	}
 	protected PageFlowSpec loadPageFlowSpec(SdsUserContext userContext, String pageFlowSpecId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).throwExceptionIfHasErrors( PageFlowSpecManagerException.class);

 
 		return pageFlowSpecDaoOf(userContext).load(pageFlowSpecId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, PageFlowSpec pageFlowSpec, Map<String, Object> tokens){
		super.addActions(userContext, pageFlowSpec, tokens);
		
		addAction(userContext, pageFlowSpec, tokens,"@create","createPageFlowSpec","createPageFlowSpec/","main","primary");
		addAction(userContext, pageFlowSpec, tokens,"@update","updatePageFlowSpec","updatePageFlowSpec/"+pageFlowSpec.getId()+"/","main","primary");
		addAction(userContext, pageFlowSpec, tokens,"@copy","clonePageFlowSpec","clonePageFlowSpec/"+pageFlowSpec.getId()+"/","main","primary");
		
		addAction(userContext, pageFlowSpec, tokens,"page_flow_spec.transfer_to_owner","transferToAnotherOwner","transferToAnotherOwner/"+pageFlowSpec.getId()+"/","main","primary");
		addAction(userContext, pageFlowSpec, tokens,"page_flow_spec.transfer_to_project","transferToAnotherProject","transferToAnotherProject/"+pageFlowSpec.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, PageFlowSpec pageFlowSpec, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public PageFlowSpec createPageFlowSpec(SdsUserContext userContext, String scene,String brief,String ownerId,String projectId) throws Exception
	//public PageFlowSpec createPageFlowSpec(SdsUserContext userContext,String scene, String brief, String ownerId, String projectId) throws Exception
	{

		

		

		checkerOf(userContext).checkSceneOfPageFlowSpec(scene);
		checkerOf(userContext).checkBriefOfPageFlowSpec(brief);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PageFlowSpecManagerException.class);


		PageFlowSpec pageFlowSpec=createNewPageFlowSpec();	

		pageFlowSpec.setScene(scene);
		pageFlowSpec.setBrief(brief);
			
		User owner = loadUser(userContext, ownerId,emptyOptions());
		pageFlowSpec.setOwner(owner);
		
		
			
		Project project = loadProject(userContext, projectId,emptyOptions());
		pageFlowSpec.setProject(project);
		
		

		pageFlowSpec = savePageFlowSpec(userContext, pageFlowSpec, emptyOptions());
		
		onNewInstanceCreated(userContext, pageFlowSpec);
		return pageFlowSpec;


	}
	protected PageFlowSpec createNewPageFlowSpec()
	{

		return new PageFlowSpec();
	}

	protected void checkParamsForUpdatingPageFlowSpec(SdsUserContext userContext,String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec( pageFlowSpecVersion);
		

		if(PageFlowSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfPageFlowSpec(parseString(newValueExpr));
		
			
		}
		if(PageFlowSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfPageFlowSpec(parseString(newValueExpr));
		
			
		}		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PageFlowSpecManagerException.class);


	}



	public PageFlowSpec clone(SdsUserContext userContext, String fromPageFlowSpecId) throws Exception{

		return pageFlowSpecDaoOf(userContext).clone(fromPageFlowSpecId, this.allTokens());
	}

	public PageFlowSpec internalSavePageFlowSpec(SdsUserContext userContext, PageFlowSpec pageFlowSpec) throws Exception
	{
		return internalSavePageFlowSpec(userContext, pageFlowSpec, allTokens());

	}
	public PageFlowSpec internalSavePageFlowSpec(SdsUserContext userContext, PageFlowSpec pageFlowSpec, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingPageFlowSpec(userContext, pageFlowSpecId, pageFlowSpecVersion, property, newValueExpr, tokensExpr);


		synchronized(pageFlowSpec){
			//will be good when the pageFlowSpec loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PageFlowSpec.
			if (pageFlowSpec.isChanged()){
			
			}
			pageFlowSpec = savePageFlowSpec(userContext, pageFlowSpec, options);
			return pageFlowSpec;

		}

	}

	public PageFlowSpec updatePageFlowSpec(SdsUserContext userContext,String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPageFlowSpec(userContext, pageFlowSpecId, pageFlowSpecVersion, property, newValueExpr, tokensExpr);



		PageFlowSpec pageFlowSpec = loadPageFlowSpec(userContext, pageFlowSpecId, allTokens());
		if(pageFlowSpec.getVersion() != pageFlowSpecVersion){
			String message = "The target version("+pageFlowSpec.getVersion()+") is not equals to version("+pageFlowSpecVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(pageFlowSpec){
			//will be good when the pageFlowSpec loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PageFlowSpec.
			
			pageFlowSpec.changeProperty(property, newValueExpr);
			pageFlowSpec = savePageFlowSpec(userContext, pageFlowSpec, tokens().done());
			return present(userContext,pageFlowSpec, mergedAllTokens(tokensExpr));
			//return savePageFlowSpec(userContext, pageFlowSpec, tokens().done());
		}

	}

	public PageFlowSpec updatePageFlowSpecProperty(SdsUserContext userContext,String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPageFlowSpec(userContext, pageFlowSpecId, pageFlowSpecVersion, property, newValueExpr, tokensExpr);

		PageFlowSpec pageFlowSpec = loadPageFlowSpec(userContext, pageFlowSpecId, allTokens());
		if(pageFlowSpec.getVersion() != pageFlowSpecVersion){
			String message = "The target version("+pageFlowSpec.getVersion()+") is not equals to version("+pageFlowSpecVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(pageFlowSpec){
			//will be good when the pageFlowSpec loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PageFlowSpec.

			pageFlowSpec.changeProperty(property, newValueExpr);
			
			pageFlowSpec = savePageFlowSpec(userContext, pageFlowSpec, tokens().done());
			return present(userContext,pageFlowSpec, mergedAllTokens(tokensExpr));
			//return savePageFlowSpec(userContext, pageFlowSpec, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected PageFlowSpecTokens tokens(){
		return PageFlowSpecTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PageFlowSpecTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PageFlowSpecTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherOwner(SdsUserContext userContext, String pageFlowSpecId, String anotherOwnerId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
 		checkerOf(userContext).checkIdOfUser(anotherOwnerId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(PageFlowSpecManagerException.class);

 	}
 	public PageFlowSpec transferToAnotherOwner(SdsUserContext userContext, String pageFlowSpecId, String anotherOwnerId) throws Exception
 	{
 		checkParamsForTransferingAnotherOwner(userContext, pageFlowSpecId,anotherOwnerId);
 
		PageFlowSpec pageFlowSpec = loadPageFlowSpec(userContext, pageFlowSpecId, allTokens());	
		synchronized(pageFlowSpec){
			//will be good when the pageFlowSpec loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			User owner = loadUser(userContext, anotherOwnerId, emptyOptions());		
			pageFlowSpec.updateOwner(owner);		
			pageFlowSpec = savePageFlowSpec(userContext, pageFlowSpec, emptyOptions());
			
			return present(userContext,pageFlowSpec, allTokens());
			
		}

 	}

	


	public CandidateUser requestCandidateOwner(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateUser result = new CandidateUser();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<User> candidateList = userDaoOf(userContext).requestCandidateUserForPageFlowSpec(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherProject(SdsUserContext userContext, String pageFlowSpecId, String anotherProjectId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
 		checkerOf(userContext).checkIdOfProject(anotherProjectId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(PageFlowSpecManagerException.class);

 	}
 	public PageFlowSpec transferToAnotherProject(SdsUserContext userContext, String pageFlowSpecId, String anotherProjectId) throws Exception
 	{
 		checkParamsForTransferingAnotherProject(userContext, pageFlowSpecId,anotherProjectId);
 
		PageFlowSpec pageFlowSpec = loadPageFlowSpec(userContext, pageFlowSpecId, allTokens());	
		synchronized(pageFlowSpec){
			//will be good when the pageFlowSpec loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Project project = loadProject(userContext, anotherProjectId, emptyOptions());		
			pageFlowSpec.updateProject(project);		
			pageFlowSpec = savePageFlowSpec(userContext, pageFlowSpec, emptyOptions());
			
			return present(userContext,pageFlowSpec, allTokens());
			
		}

 	}

	


	public CandidateProject requestCandidateProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateProject result = new CandidateProject();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Project> candidateList = projectDaoOf(userContext).requestCandidateProjectForPageFlowSpec(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Project loadProject(SdsUserContext userContext, String newProjectId, Map<String,Object> options) throws Exception
 	{

 		return projectDaoOf(userContext).load(newProjectId, options);
 	}
 	


	

 	protected User loadUser(SdsUserContext userContext, String newOwnerId, Map<String,Object> options) throws Exception
 	{

 		return userDaoOf(userContext).load(newOwnerId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String pageFlowSpecId, int pageFlowSpecVersion) throws Exception {
		//deleteInternal(userContext, pageFlowSpecId, pageFlowSpecVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String pageFlowSpecId, int pageFlowSpecVersion) throws Exception{

		pageFlowSpecDaoOf(userContext).delete(pageFlowSpecId, pageFlowSpecVersion);
	}

	public PageFlowSpec forgetByAll(SdsUserContext userContext, String pageFlowSpecId, int pageFlowSpecVersion) throws Exception {
		return forgetByAllInternal(userContext, pageFlowSpecId, pageFlowSpecVersion);
	}
	protected PageFlowSpec forgetByAllInternal(SdsUserContext userContext,
			String pageFlowSpecId, int pageFlowSpecVersion) throws Exception{

		return pageFlowSpecDaoOf(userContext).disconnectFromAll(pageFlowSpecId, pageFlowSpecVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PageFlowSpecManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return pageFlowSpecDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(SdsUserContext userContext, PageFlowSpec newCreated) throws Exception{
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
		//   PageFlowSpec newPageFlowSpec = this.createPageFlowSpec(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newPageFlowSpec
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, PageFlowSpec.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<PageFlowSpec> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<User> ownerList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, User.class);
		userContext.getDAOGroup().enhanceList(ownerList, User.class);
		List<Project> projectList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, Project.class);
		userContext.getDAOGroup().enhanceList(projectList, Project.class);


    }
	
	public Object listByOwner(SdsUserContext userContext,String ownerId) throws Exception {
		return listPageByOwner(userContext, ownerId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByOwner(SdsUserContext userContext,String ownerId, int start, int count) throws Exception {
		SmartList<PageFlowSpec> list = pageFlowSpecDaoOf(userContext).findPageFlowSpecByOwner(ownerId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(PageFlowSpec.class);
		page.setContainerObject(User.withId(ownerId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("页面流程规范列表");
		page.setRequestName("listByOwner");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByOwner/%s/",  getBeanName(), ownerId)));

		page.assemblerContent(userContext, "listByOwner");
		return page.doRender(userContext);
	}
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception {
		return listPageByProject(userContext, projectId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception {
		SmartList<PageFlowSpec> list = pageFlowSpecDaoOf(userContext).findPageFlowSpecByProject(projectId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(PageFlowSpec.class);
		page.setContainerObject(Project.withId(projectId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("页面流程规范列表");
		page.setRequestName("listByProject");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByProject/%s/",  getBeanName(), projectId)));

		page.assemblerContent(userContext, "listByProject");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(SdsUserContext userContext, String pageFlowSpecId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getPageFlowSpecDetailScope().clone();
		PageFlowSpec merchantObj = (PageFlowSpec) this.view(userContext, pageFlowSpecId);
    String merchantObjId = pageFlowSpecId;
    String linkToUrl =	"pageFlowSpecManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "页面流程规范"+"详情";
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
				MapUtil.put("id", "2-scene")
				    .put("fieldName", "scene")
				    .put("label", "场景")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("scene", merchantObj.getScene());

		propList.add(
				MapUtil.put("id", "3-brief")
				    .put("fieldName", "brief")
				    .put("label", "短暂的")
				    .put("type", "longtext")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("brief", merchantObj.getBrief());

		propList.add(
				MapUtil.put("id", "4-owner")
				    .put("fieldName", "owner")
				    .put("label", "所有人")
				    .put("type", "auto")
				    .put("linkToUrl", "userManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"title\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("owner", merchantObj.getOwner());

		propList.add(
				MapUtil.put("id", "5-project")
				    .put("fieldName", "project")
				    .put("label", "项目")
				    .put("type", "auto")
				    .put("linkToUrl", "projectManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("project", merchantObj.getProject());

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


