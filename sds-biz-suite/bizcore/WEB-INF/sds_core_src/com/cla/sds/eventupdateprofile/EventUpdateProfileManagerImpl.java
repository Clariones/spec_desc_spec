
package com.cla.sds.eventupdateprofile;

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


import com.cla.sds.changerequest.ChangeRequest;

import com.cla.sds.changerequest.CandidateChangeRequest;







public class EventUpdateProfileManagerImpl extends CustomSdsCheckerManager implements EventUpdateProfileManager, BusinessHandler{

	// Only some of ods have such function
	
	



  


	private static final String SERVICE_TYPE = "EventUpdateProfile";
	@Override
	public EventUpdateProfileDAO daoOf(SdsUserContext userContext) {
		return eventUpdateProfileDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws EventUpdateProfileManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new EventUpdateProfileManagerException(message);

	}



 	protected EventUpdateProfile saveEventUpdateProfile(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile, String [] tokensExpr) throws Exception{	
 		//return getEventUpdateProfileDAO().save(eventUpdateProfile, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveEventUpdateProfile(userContext, eventUpdateProfile, tokens);
 	}
 	
 	protected EventUpdateProfile saveEventUpdateProfileDetail(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile) throws Exception{	

 		
 		return saveEventUpdateProfile(userContext, eventUpdateProfile, allTokens());
 	}
 	
 	public EventUpdateProfile loadEventUpdateProfile(SdsUserContext userContext, String eventUpdateProfileId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).throwExceptionIfHasErrors( EventUpdateProfileManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile( userContext, eventUpdateProfileId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,eventUpdateProfile, tokens);
 	}
 	
 	
 	 public EventUpdateProfile searchEventUpdateProfile(SdsUserContext userContext, String eventUpdateProfileId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).throwExceptionIfHasErrors( EventUpdateProfileManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile( userContext, eventUpdateProfileId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,eventUpdateProfile, tokens);
 	}
 	
 	

 	protected EventUpdateProfile present(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,eventUpdateProfile,tokens);
		
		
		EventUpdateProfile  eventUpdateProfileToPresent = eventUpdateProfileDaoOf(userContext).present(eventUpdateProfile, tokens);
		
		List<BaseEntity> entityListToNaming = eventUpdateProfileToPresent.collectRefercencesFromLists();
		eventUpdateProfileDaoOf(userContext).alias(entityListToNaming);
		
		return  eventUpdateProfileToPresent;
		
		
	}
 
 	
 	
 	public EventUpdateProfile loadEventUpdateProfileDetail(SdsUserContext userContext, String eventUpdateProfileId) throws Exception{	
 		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile( userContext, eventUpdateProfileId, allTokens());
 		return present(userContext,eventUpdateProfile, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String eventUpdateProfileId) throws Exception{	
 		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile( userContext, eventUpdateProfileId, viewTokens());
 		return present(userContext,eventUpdateProfile, allTokens());
		
 	}
 	protected EventUpdateProfile saveEventUpdateProfile(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile, Map<String,Object>tokens) throws Exception{	
 		return eventUpdateProfileDaoOf(userContext).save(eventUpdateProfile, tokens);
 	}
 	protected EventUpdateProfile loadEventUpdateProfile(SdsUserContext userContext, String eventUpdateProfileId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).throwExceptionIfHasErrors( EventUpdateProfileManagerException.class);

 
 		return eventUpdateProfileDaoOf(userContext).load(eventUpdateProfileId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile, Map<String, Object> tokens){
		super.addActions(userContext, eventUpdateProfile, tokens);
		
		addAction(userContext, eventUpdateProfile, tokens,"@create","createEventUpdateProfile","createEventUpdateProfile/","main","primary");
		addAction(userContext, eventUpdateProfile, tokens,"@update","updateEventUpdateProfile","updateEventUpdateProfile/"+eventUpdateProfile.getId()+"/","main","primary");
		addAction(userContext, eventUpdateProfile, tokens,"@copy","cloneEventUpdateProfile","cloneEventUpdateProfile/"+eventUpdateProfile.getId()+"/","main","primary");
		
		addAction(userContext, eventUpdateProfile, tokens,"event_update_profile.transfer_to_change_request","transferToAnotherChangeRequest","transferToAnotherChangeRequest/"+eventUpdateProfile.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public EventUpdateProfile createEventUpdateProfile(SdsUserContext userContext, String name,String avantar,String fieldGroup,String eventInitiatorType,String eventInitiatorId,String changeRequestId) throws Exception
	//public EventUpdateProfile createEventUpdateProfile(SdsUserContext userContext,String name, String avantar, String fieldGroup, String eventInitiatorType, String eventInitiatorId, String changeRequestId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfEventUpdateProfile(name);
		checkerOf(userContext).checkAvantarOfEventUpdateProfile(avantar);
		checkerOf(userContext).checkFieldGroupOfEventUpdateProfile(fieldGroup);
		checkerOf(userContext).checkEventInitiatorTypeOfEventUpdateProfile(eventInitiatorType);
		checkerOf(userContext).checkEventInitiatorIdOfEventUpdateProfile(eventInitiatorId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(EventUpdateProfileManagerException.class);


		EventUpdateProfile eventUpdateProfile=createNewEventUpdateProfile();	

		eventUpdateProfile.setName(name);
		eventUpdateProfile.setAvantar(avantar);
		eventUpdateProfile.setFieldGroup(fieldGroup);
		eventUpdateProfile.setEventInitiatorType(eventInitiatorType);
		eventUpdateProfile.setEventInitiatorId(eventInitiatorId);
			
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId,emptyOptions());
		eventUpdateProfile.setChangeRequest(changeRequest);
		
		

		eventUpdateProfile = saveEventUpdateProfile(userContext, eventUpdateProfile, emptyOptions());
		
		onNewInstanceCreated(userContext, eventUpdateProfile);
		return eventUpdateProfile;


	}
	protected EventUpdateProfile createNewEventUpdateProfile()
	{

		return new EventUpdateProfile();
	}

	protected void checkParamsForUpdatingEventUpdateProfile(SdsUserContext userContext,String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).checkVersionOfEventUpdateProfile( eventUpdateProfileVersion);
		

		if(EventUpdateProfile.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfEventUpdateProfile(parseString(newValueExpr));
		
			
		}
		if(EventUpdateProfile.AVANTAR_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkAvantarOfEventUpdateProfile(parseString(newValueExpr));
		
			
		}
		if(EventUpdateProfile.FIELD_GROUP_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkFieldGroupOfEventUpdateProfile(parseString(newValueExpr));
		
			
		}
		if(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkEventInitiatorTypeOfEventUpdateProfile(parseString(newValueExpr));
		
			
		}
		if(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkEventInitiatorIdOfEventUpdateProfile(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(EventUpdateProfileManagerException.class);


	}



	public EventUpdateProfile clone(SdsUserContext userContext, String fromEventUpdateProfileId) throws Exception{

		return eventUpdateProfileDaoOf(userContext).clone(fromEventUpdateProfileId, this.allTokens());
	}

	public EventUpdateProfile internalSaveEventUpdateProfile(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile) throws Exception
	{
		return internalSaveEventUpdateProfile(userContext, eventUpdateProfile, allTokens());

	}
	public EventUpdateProfile internalSaveEventUpdateProfile(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingEventUpdateProfile(userContext, eventUpdateProfileId, eventUpdateProfileVersion, property, newValueExpr, tokensExpr);


		synchronized(eventUpdateProfile){
			//will be good when the eventUpdateProfile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to EventUpdateProfile.
			if (eventUpdateProfile.isChanged()){
			
			}
			eventUpdateProfile = saveEventUpdateProfile(userContext, eventUpdateProfile, options);
			return eventUpdateProfile;

		}

	}

	public EventUpdateProfile updateEventUpdateProfile(SdsUserContext userContext,String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEventUpdateProfile(userContext, eventUpdateProfileId, eventUpdateProfileVersion, property, newValueExpr, tokensExpr);



		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile(userContext, eventUpdateProfileId, allTokens());
		if(eventUpdateProfile.getVersion() != eventUpdateProfileVersion){
			String message = "The target version("+eventUpdateProfile.getVersion()+") is not equals to version("+eventUpdateProfileVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(eventUpdateProfile){
			//will be good when the eventUpdateProfile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to EventUpdateProfile.
			
			eventUpdateProfile.changeProperty(property, newValueExpr);
			eventUpdateProfile = saveEventUpdateProfile(userContext, eventUpdateProfile, tokens().done());
			return present(userContext,eventUpdateProfile, mergedAllTokens(tokensExpr));
			//return saveEventUpdateProfile(userContext, eventUpdateProfile, tokens().done());
		}

	}

	public EventUpdateProfile updateEventUpdateProfileProperty(SdsUserContext userContext,String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEventUpdateProfile(userContext, eventUpdateProfileId, eventUpdateProfileVersion, property, newValueExpr, tokensExpr);

		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile(userContext, eventUpdateProfileId, allTokens());
		if(eventUpdateProfile.getVersion() != eventUpdateProfileVersion){
			String message = "The target version("+eventUpdateProfile.getVersion()+") is not equals to version("+eventUpdateProfileVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(eventUpdateProfile){
			//will be good when the eventUpdateProfile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to EventUpdateProfile.

			eventUpdateProfile.changeProperty(property, newValueExpr);
			
			eventUpdateProfile = saveEventUpdateProfile(userContext, eventUpdateProfile, tokens().done());
			return present(userContext,eventUpdateProfile, mergedAllTokens(tokensExpr));
			//return saveEventUpdateProfile(userContext, eventUpdateProfile, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected EventUpdateProfileTokens tokens(){
		return EventUpdateProfileTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return EventUpdateProfileTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return EventUpdateProfileTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherChangeRequest(SdsUserContext userContext, String eventUpdateProfileId, String anotherChangeRequestId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
 		checkerOf(userContext).checkIdOfChangeRequest(anotherChangeRequestId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(EventUpdateProfileManagerException.class);

 	}
 	public EventUpdateProfile transferToAnotherChangeRequest(SdsUserContext userContext, String eventUpdateProfileId, String anotherChangeRequestId) throws Exception
 	{
 		checkParamsForTransferingAnotherChangeRequest(userContext, eventUpdateProfileId,anotherChangeRequestId);
 
		EventUpdateProfile eventUpdateProfile = loadEventUpdateProfile(userContext, eventUpdateProfileId, allTokens());	
		synchronized(eventUpdateProfile){
			//will be good when the eventUpdateProfile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequest changeRequest = loadChangeRequest(userContext, anotherChangeRequestId, emptyOptions());		
			eventUpdateProfile.updateChangeRequest(changeRequest);		
			eventUpdateProfile = saveEventUpdateProfile(userContext, eventUpdateProfile, emptyOptions());
			
			return present(userContext,eventUpdateProfile, allTokens());
			
		}

 	}

	


	public CandidateChangeRequest requestCandidateChangeRequest(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequest result = new CandidateChangeRequest();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequest> candidateList = changeRequestDaoOf(userContext).requestCandidateChangeRequestForEventUpdateProfile(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ChangeRequest loadChangeRequest(SdsUserContext userContext, String newChangeRequestId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestDaoOf(userContext).load(newChangeRequestId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String eventUpdateProfileId, int eventUpdateProfileVersion) throws Exception {
		//deleteInternal(userContext, eventUpdateProfileId, eventUpdateProfileVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String eventUpdateProfileId, int eventUpdateProfileVersion) throws Exception{

		eventUpdateProfileDaoOf(userContext).delete(eventUpdateProfileId, eventUpdateProfileVersion);
	}

	public EventUpdateProfile forgetByAll(SdsUserContext userContext, String eventUpdateProfileId, int eventUpdateProfileVersion) throws Exception {
		return forgetByAllInternal(userContext, eventUpdateProfileId, eventUpdateProfileVersion);
	}
	protected EventUpdateProfile forgetByAllInternal(SdsUserContext userContext,
			String eventUpdateProfileId, int eventUpdateProfileVersion) throws Exception{

		return eventUpdateProfileDaoOf(userContext).disconnectFromAll(eventUpdateProfileId, eventUpdateProfileVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new EventUpdateProfileManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return eventUpdateProfileDaoOf(userContext).deleteAll();
	}








	public void onNewInstanceCreated(SdsUserContext userContext, EventUpdateProfile newCreated) throws Exception{
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
		//   EventUpdateProfile newEventUpdateProfile = this.createEventUpdateProfile(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newEventUpdateProfile
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, EventUpdateProfile.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<EventUpdateProfile> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<ChangeRequest> changeRequestList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, ChangeRequest.class);
		userContext.getDAOGroup().enhanceList(changeRequestList, ChangeRequest.class);


    }
	
	public Object listByChangeRequest(SdsUserContext userContext,String changeRequestId) throws Exception {
		return listPageByChangeRequest(userContext, changeRequestId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByChangeRequest(SdsUserContext userContext,String changeRequestId, int start, int count) throws Exception {
		SmartList<EventUpdateProfile> list = eventUpdateProfileDaoOf(userContext).findEventUpdateProfileByChangeRequest(changeRequestId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(EventUpdateProfile.class);
		page.setContainerObject(ChangeRequest.withId(changeRequestId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("事件更新概要列表");
		page.setRequestName("listByChangeRequest");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByChangeRequest/%s/",  getBeanName(), changeRequestId)));

		page.assemblerContent(userContext, "listByChangeRequest");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(SdsUserContext userContext, String eventUpdateProfileId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getEventUpdateProfileDetailScope().clone();
		EventUpdateProfile merchantObj = (EventUpdateProfile) this.view(userContext, eventUpdateProfileId);
    String merchantObjId = eventUpdateProfileId;
    String linkToUrl =	"eventUpdateProfileManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "事件更新概要"+"详情";
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
				MapUtil.put("id", "2-name")
				    .put("fieldName", "name")
				    .put("label", "名称")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("name", merchantObj.getName());

		propList.add(
				MapUtil.put("id", "3-avantar")
				    .put("fieldName", "avantar")
				    .put("label", "Avantar")
				    .put("type", "image")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("avantar", merchantObj.getAvantar());

		propList.add(
				MapUtil.put("id", "4-fieldGroup")
				    .put("fieldName", "fieldGroup")
				    .put("label", "字段组")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("fieldGroup", merchantObj.getFieldGroup());

		propList.add(
				MapUtil.put("id", "5-eventInitiatorType")
				    .put("fieldName", "eventInitiatorType")
				    .put("label", "事件引发剂类型")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("eventInitiatorType", merchantObj.getEventInitiatorType());

		propList.add(
				MapUtil.put("id", "6-eventInitiatorId")
				    .put("fieldName", "eventInitiatorId")
				    .put("label", "活动发起者Id")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("eventInitiatorId", merchantObj.getEventInitiatorId());

		propList.add(
				MapUtil.put("id", "7-changeRequest")
				    .put("fieldName", "changeRequest")
				    .put("label", "变更请求")
				    .put("type", "auto")
				    .put("linkToUrl", "changeRequestManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"request_type\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("changeRequest", merchantObj.getChangeRequest());

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


