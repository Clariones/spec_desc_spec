
package com.cla.sds.userproject;

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







public class UserProjectManagerImpl extends CustomSdsCheckerManager implements UserProjectManager, BusinessHandler{

	// Only some of ods have such function
	
	



  


	private static final String SERVICE_TYPE = "UserProject";
	@Override
	public UserProjectDAO daoOf(SdsUserContext userContext) {
		return userProjectDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws UserProjectManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new UserProjectManagerException(message);

	}



 	protected UserProject saveUserProject(SdsUserContext userContext, UserProject userProject, String [] tokensExpr) throws Exception{	
 		//return getUserProjectDAO().save(userProject, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveUserProject(userContext, userProject, tokens);
 	}
 	
 	protected UserProject saveUserProjectDetail(SdsUserContext userContext, UserProject userProject) throws Exception{	

 		
 		return saveUserProject(userContext, userProject, allTokens());
 	}
 	
 	public UserProject loadUserProject(SdsUserContext userContext, String userProjectId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserProjectManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		UserProject userProject = loadUserProject( userContext, userProjectId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userProject, tokens);
 	}
 	
 	
 	 public UserProject searchUserProject(SdsUserContext userContext, String userProjectId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserProjectManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		UserProject userProject = loadUserProject( userContext, userProjectId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,userProject, tokens);
 	}
 	
 	

 	protected UserProject present(SdsUserContext userContext, UserProject userProject, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,userProject,tokens);
		
		
		UserProject  userProjectToPresent = userProjectDaoOf(userContext).present(userProject, tokens);
		
		List<BaseEntity> entityListToNaming = userProjectToPresent.collectRefercencesFromLists();
		userProjectDaoOf(userContext).alias(entityListToNaming);
		
		return  userProjectToPresent;
		
		
	}
 
 	
 	
 	public UserProject loadUserProjectDetail(SdsUserContext userContext, String userProjectId) throws Exception{	
 		UserProject userProject = loadUserProject( userContext, userProjectId, allTokens());
 		return present(userContext,userProject, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String userProjectId) throws Exception{	
 		UserProject userProject = loadUserProject( userContext, userProjectId, viewTokens());
 		return present(userContext,userProject, allTokens());
		
 	}
 	protected UserProject saveUserProject(SdsUserContext userContext, UserProject userProject, Map<String,Object>tokens) throws Exception{	
 		return userProjectDaoOf(userContext).save(userProject, tokens);
 	}
 	protected UserProject loadUserProject(SdsUserContext userContext, String userProjectId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserProjectManagerException.class);

 
 		return userProjectDaoOf(userContext).load(userProjectId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, UserProject userProject, Map<String, Object> tokens){
		super.addActions(userContext, userProject, tokens);
		
		addAction(userContext, userProject, tokens,"@create","createUserProject","createUserProject/","main","primary");
		addAction(userContext, userProject, tokens,"@update","updateUserProject","updateUserProject/"+userProject.getId()+"/","main","primary");
		addAction(userContext, userProject, tokens,"@copy","cloneUserProject","cloneUserProject/"+userProject.getId()+"/","main","primary");
		
		addAction(userContext, userProject, tokens,"user_project.transfer_to_user","transferToAnotherUser","transferToAnotherUser/"+userProject.getId()+"/","main","primary");
		addAction(userContext, userProject, tokens,"user_project.transfer_to_project","transferToAnotherProject","transferToAnotherProject/"+userProject.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, UserProject userProject, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public UserProject createUserProject(SdsUserContext userContext, String userId,String projectId) throws Exception
	//public UserProject createUserProject(SdsUserContext userContext,String userId, String projectId) throws Exception
	{

		

		

	
		checkerOf(userContext).throwExceptionIfHasErrors(UserProjectManagerException.class);


		UserProject userProject=createNewUserProject();	

			
		User user = loadUser(userContext, userId,emptyOptions());
		userProject.setUser(user);
		
		
			
		Project project = loadProject(userContext, projectId,emptyOptions());
		userProject.setProject(project);
		
		
		userProject.setCreateTime(userContext.now());
		userProject.setLastUpdateTime(userContext.now());

		userProject = saveUserProject(userContext, userProject, emptyOptions());
		
		onNewInstanceCreated(userContext, userProject);
		return userProject;


	}
	protected UserProject createNewUserProject()
	{

		return new UserProject();
	}

	protected void checkParamsForUpdatingUserProject(SdsUserContext userContext,String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject( userProjectVersion);
		
		

				

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserProjectManagerException.class);


	}



	public UserProject clone(SdsUserContext userContext, String fromUserProjectId) throws Exception{

		return userProjectDaoOf(userContext).clone(fromUserProjectId, this.allTokens());
	}

	public UserProject internalSaveUserProject(SdsUserContext userContext, UserProject userProject) throws Exception
	{
		return internalSaveUserProject(userContext, userProject, allTokens());

	}
	public UserProject internalSaveUserProject(SdsUserContext userContext, UserProject userProject, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingUserProject(userContext, userProjectId, userProjectVersion, property, newValueExpr, tokensExpr);


		synchronized(userProject){
			//will be good when the userProject loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserProject.
			if (userProject.isChanged()){
			userProject.updateLastUpdateTime(userContext.now());
			}
			userProject = saveUserProject(userContext, userProject, options);
			return userProject;

		}

	}

	public UserProject updateUserProject(SdsUserContext userContext,String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserProject(userContext, userProjectId, userProjectVersion, property, newValueExpr, tokensExpr);



		UserProject userProject = loadUserProject(userContext, userProjectId, allTokens());
		if(userProject.getVersion() != userProjectVersion){
			String message = "The target version("+userProject.getVersion()+") is not equals to version("+userProjectVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userProject){
			//will be good when the userProject loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserProject.
			userProject.updateLastUpdateTime(userContext.now());
			userProject.changeProperty(property, newValueExpr);
			userProject = saveUserProject(userContext, userProject, tokens().done());
			return present(userContext,userProject, mergedAllTokens(tokensExpr));
			//return saveUserProject(userContext, userProject, tokens().done());
		}

	}

	public UserProject updateUserProjectProperty(SdsUserContext userContext,String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserProject(userContext, userProjectId, userProjectVersion, property, newValueExpr, tokensExpr);

		UserProject userProject = loadUserProject(userContext, userProjectId, allTokens());
		if(userProject.getVersion() != userProjectVersion){
			String message = "The target version("+userProject.getVersion()+") is not equals to version("+userProjectVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(userProject){
			//will be good when the userProject loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to UserProject.

			userProject.changeProperty(property, newValueExpr);
			userProject.updateLastUpdateTime(userContext.now());
			userProject = saveUserProject(userContext, userProject, tokens().done());
			return present(userContext,userProject, mergedAllTokens(tokensExpr));
			//return saveUserProject(userContext, userProject, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected UserProjectTokens tokens(){
		return UserProjectTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return UserProjectTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return UserProjectTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherUser(SdsUserContext userContext, String userProjectId, String anotherUserId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfUserProject(userProjectId);
 		checkerOf(userContext).checkIdOfUser(anotherUserId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(UserProjectManagerException.class);

 	}
 	public UserProject transferToAnotherUser(SdsUserContext userContext, String userProjectId, String anotherUserId) throws Exception
 	{
 		checkParamsForTransferingAnotherUser(userContext, userProjectId,anotherUserId);
 
		UserProject userProject = loadUserProject(userContext, userProjectId, allTokens());	
		synchronized(userProject){
			//will be good when the userProject loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			User user = loadUser(userContext, anotherUserId, emptyOptions());		
			userProject.updateUser(user);		
			userProject = saveUserProject(userContext, userProject, emptyOptions());
			
			return present(userContext,userProject, allTokens());
			
		}

 	}

	


	public CandidateUser requestCandidateUser(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

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
		SmartList<User> candidateList = userDaoOf(userContext).requestCandidateUserForUserProject(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherProject(SdsUserContext userContext, String userProjectId, String anotherProjectId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfUserProject(userProjectId);
 		checkerOf(userContext).checkIdOfProject(anotherProjectId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(UserProjectManagerException.class);

 	}
 	public UserProject transferToAnotherProject(SdsUserContext userContext, String userProjectId, String anotherProjectId) throws Exception
 	{
 		checkParamsForTransferingAnotherProject(userContext, userProjectId,anotherProjectId);
 
		UserProject userProject = loadUserProject(userContext, userProjectId, allTokens());	
		synchronized(userProject){
			//will be good when the userProject loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Project project = loadProject(userContext, anotherProjectId, emptyOptions());		
			userProject.updateProject(project);		
			userProject = saveUserProject(userContext, userProject, emptyOptions());
			
			return present(userContext,userProject, allTokens());
			
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
		SmartList<Project> candidateList = projectDaoOf(userContext).requestCandidateProjectForUserProject(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected User loadUser(SdsUserContext userContext, String newUserId, Map<String,Object> options) throws Exception
 	{

 		return userDaoOf(userContext).load(newUserId, options);
 	}
 	


	

 	protected Project loadProject(SdsUserContext userContext, String newProjectId, Map<String,Object> options) throws Exception
 	{

 		return projectDaoOf(userContext).load(newProjectId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String userProjectId, int userProjectVersion) throws Exception {
		//deleteInternal(userContext, userProjectId, userProjectVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String userProjectId, int userProjectVersion) throws Exception{

		userProjectDaoOf(userContext).delete(userProjectId, userProjectVersion);
	}

	public UserProject forgetByAll(SdsUserContext userContext, String userProjectId, int userProjectVersion) throws Exception {
		return forgetByAllInternal(userContext, userProjectId, userProjectVersion);
	}
	protected UserProject forgetByAllInternal(SdsUserContext userContext,
			String userProjectId, int userProjectVersion) throws Exception{

		return userProjectDaoOf(userContext).disconnectFromAll(userProjectId, userProjectVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new UserProjectManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return userProjectDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(SdsUserContext userContext, UserProject newCreated) throws Exception{
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
		//   UserProject newUserProject = this.createUserProject(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newUserProject
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, UserProject.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<UserProject> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<User> userList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, User.class);
		userContext.getDAOGroup().enhanceList(userList, User.class);
		List<Project> projectList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, Project.class);
		userContext.getDAOGroup().enhanceList(projectList, Project.class);


    }
	
	public Object listByUser(SdsUserContext userContext,String userId) throws Exception {
		return listPageByUser(userContext, userId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByUser(SdsUserContext userContext,String userId, int start, int count) throws Exception {
		SmartList<UserProject> list = userProjectDaoOf(userContext).findUserProjectByUser(userId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(UserProject.class);
		page.setContainerObject(User.withId(userId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("用户项目列表");
		page.setRequestName("listByUser");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByUser/%s/",  getBeanName(), userId)));

		page.assemblerContent(userContext, "listByUser");
		return page.doRender(userContext);
	}
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception {
		return listPageByProject(userContext, projectId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception {
		SmartList<UserProject> list = userProjectDaoOf(userContext).findUserProjectByProject(projectId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(UserProject.class);
		page.setContainerObject(Project.withId(projectId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("用户项目列表");
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
 	public Object wxappview(SdsUserContext userContext, String userProjectId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getUserProjectDetailScope().clone();
		UserProject merchantObj = (UserProject) this.view(userContext, userProjectId);
    String merchantObjId = userProjectId;
    String linkToUrl =	"userProjectManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "用户项目"+"详情";
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
				MapUtil.put("id", "2-user")
				    .put("fieldName", "user")
				    .put("label", "用户")
				    .put("type", "auto")
				    .put("linkToUrl", "userManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"title\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("user", merchantObj.getUser());

		propList.add(
				MapUtil.put("id", "3-project")
				    .put("fieldName", "project")
				    .put("label", "项目")
				    .put("type", "auto")
				    .put("linkToUrl", "projectManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("project", merchantObj.getProject());

		propList.add(
				MapUtil.put("id", "4-createTime")
				    .put("fieldName", "createTime")
				    .put("label", "创建时间")
				    .put("type", "datetime")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("createTime", merchantObj.getCreateTime());

		propList.add(
				MapUtil.put("id", "5-lastUpdateTime")
				    .put("fieldName", "lastUpdateTime")
				    .put("label", "最后更新时间")
				    .put("type", "datetime")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("lastUpdateTime", merchantObj.getLastUpdateTime());

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


