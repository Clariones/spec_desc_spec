
package com.cla.sds.changerequest;

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


import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.platform.Platform;
import com.cla.sds.changerequesttype.ChangeRequestType;

import com.cla.sds.platform.CandidatePlatform;
import com.cla.sds.changerequesttype.CandidateChangeRequestType;

import com.cla.sds.changerequest.ChangeRequest;






public class ChangeRequestManagerImpl extends CustomSdsCheckerManager implements ChangeRequestManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(SdsUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = ChangeRequestTokens.start().withTokenFromListName(listName).done();
		ChangeRequest  changeRequest = (ChangeRequest) this.loadChangeRequest(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = changeRequest.collectRefercencesFromLists();
		changeRequestDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, changeRequest, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new ChangeRequestGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "ChangeRequest";
	@Override
	public ChangeRequestDAO daoOf(SdsUserContext userContext) {
		return changeRequestDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ChangeRequestManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ChangeRequestManagerException(message);

	}



 	protected ChangeRequest saveChangeRequest(SdsUserContext userContext, ChangeRequest changeRequest, String [] tokensExpr) throws Exception{	
 		//return getChangeRequestDAO().save(changeRequest, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveChangeRequest(userContext, changeRequest, tokens);
 	}
 	
 	protected ChangeRequest saveChangeRequestDetail(SdsUserContext userContext, ChangeRequest changeRequest) throws Exception{	

 		
 		return saveChangeRequest(userContext, changeRequest, allTokens());
 	}
 	
 	public ChangeRequest loadChangeRequest(SdsUserContext userContext, String changeRequestId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequest, tokens);
 	}
 	
 	
 	 public ChangeRequest searchChangeRequest(SdsUserContext userContext, String changeRequestId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequest, tokens);
 	}
 	
 	

 	protected ChangeRequest present(SdsUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,changeRequest,tokens);
		
		
		ChangeRequest  changeRequestToPresent = changeRequestDaoOf(userContext).present(changeRequest, tokens);
		
		List<BaseEntity> entityListToNaming = changeRequestToPresent.collectRefercencesFromLists();
		changeRequestDaoOf(userContext).alias(entityListToNaming);
		
		return  changeRequestToPresent;
		
		
	}
 
 	
 	
 	public ChangeRequest loadChangeRequestDetail(SdsUserContext userContext, String changeRequestId) throws Exception{	
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, allTokens());
 		return present(userContext,changeRequest, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String changeRequestId) throws Exception{	
 		ChangeRequest changeRequest = loadChangeRequest( userContext, changeRequestId, viewTokens());
 		return present(userContext,changeRequest, allTokens());
		
 	}
 	protected ChangeRequest saveChangeRequest(SdsUserContext userContext, ChangeRequest changeRequest, Map<String,Object>tokens) throws Exception{	
 		return changeRequestDaoOf(userContext).save(changeRequest, tokens);
 	}
 	protected ChangeRequest loadChangeRequest(SdsUserContext userContext, String changeRequestId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestManagerException.class);

 
 		return changeRequestDaoOf(userContext).load(changeRequestId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens){
		super.addActions(userContext, changeRequest, tokens);
		
		addAction(userContext, changeRequest, tokens,"@create","createChangeRequest","createChangeRequest/","main","primary");
		addAction(userContext, changeRequest, tokens,"@update","updateChangeRequest","updateChangeRequest/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"@copy","cloneChangeRequest","cloneChangeRequest/"+changeRequest.getId()+"/","main","primary");
		
		addAction(userContext, changeRequest, tokens,"change_request.transfer_to_request_type","transferToAnotherRequestType","transferToAnotherRequestType/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"change_request.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+changeRequest.getId()+"/","main","primary");
		addAction(userContext, changeRequest, tokens,"change_request.addEventUpdateProfile","addEventUpdateProfile","addEventUpdateProfile/"+changeRequest.getId()+"/","eventUpdateProfileList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.removeEventUpdateProfile","removeEventUpdateProfile","removeEventUpdateProfile/"+changeRequest.getId()+"/","eventUpdateProfileList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.updateEventUpdateProfile","updateEventUpdateProfile","updateEventUpdateProfile/"+changeRequest.getId()+"/","eventUpdateProfileList","primary");
		addAction(userContext, changeRequest, tokens,"change_request.copyEventUpdateProfileFrom","copyEventUpdateProfileFrom","copyEventUpdateProfileFrom/"+changeRequest.getId()+"/","eventUpdateProfileList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, ChangeRequest changeRequest, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ChangeRequest createChangeRequest(SdsUserContext userContext, String name,String requestTypeId,boolean commited,String platformId) throws Exception
	//public ChangeRequest createChangeRequest(SdsUserContext userContext,String name, String requestTypeId, boolean commited, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfChangeRequest(name);
		checkerOf(userContext).checkCommitedOfChangeRequest(commited);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


		ChangeRequest changeRequest=createNewChangeRequest();	

		changeRequest.setName(name);
		changeRequest.setCreateTime(userContext.now());
		changeRequest.setRemoteIp(userContext.getRemoteIP());
			
		ChangeRequestType requestType = loadChangeRequestType(userContext, requestTypeId,emptyOptions());
		changeRequest.setRequestType(requestType);
		
		
		changeRequest.setCommited(commited);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		changeRequest.setPlatform(platform);
		
		

		changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
		
		onNewInstanceCreated(userContext, changeRequest);
		return changeRequest;


	}
	protected ChangeRequest createNewChangeRequest()
	{

		return new ChangeRequest();
	}

	protected void checkParamsForUpdatingChangeRequest(SdsUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest( changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfChangeRequest(parseString(newValueExpr));
		
			
		}		

		
		if(ChangeRequest.COMMITED_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCommitedOfChangeRequest(parseBoolean(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}



	public ChangeRequest clone(SdsUserContext userContext, String fromChangeRequestId) throws Exception{

		return changeRequestDaoOf(userContext).clone(fromChangeRequestId, this.allTokens());
	}

	public ChangeRequest internalSaveChangeRequest(SdsUserContext userContext, ChangeRequest changeRequest) throws Exception
	{
		return internalSaveChangeRequest(userContext, changeRequest, allTokens());

	}
	public ChangeRequest internalSaveChangeRequest(SdsUserContext userContext, ChangeRequest changeRequest, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);


		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			if (changeRequest.isChanged()){
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			}
			changeRequest = saveChangeRequest(userContext, changeRequest, options);
			return changeRequest;

		}

	}

	public ChangeRequest updateChangeRequest(SdsUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		if(changeRequest.getVersion() != changeRequestVersion){
			String message = "The target version("+changeRequest.getVersion()+") is not equals to version("+changeRequestVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			changeRequest.changeProperty(property, newValueExpr);
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			//return saveChangeRequest(userContext, changeRequest, tokens().done());
		}

	}

	public ChangeRequest updateChangeRequestProperty(SdsUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequest(userContext, changeRequestId, changeRequestVersion, property, newValueExpr, tokensExpr);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		if(changeRequest.getVersion() != changeRequestVersion){
			String message = "The target version("+changeRequest.getVersion()+") is not equals to version("+changeRequestVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequest.

			changeRequest.changeProperty(property, newValueExpr);
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			//return saveChangeRequest(userContext, changeRequest, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ChangeRequestTokens tokens(){
		return ChangeRequestTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ChangeRequestTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortEventUpdateProfileListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ChangeRequestTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherRequestType(SdsUserContext userContext, String changeRequestId, String anotherRequestTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
 		checkerOf(userContext).checkIdOfChangeRequestType(anotherRequestTypeId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

 	}
 	public ChangeRequest transferToAnotherRequestType(SdsUserContext userContext, String changeRequestId, String anotherRequestTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherRequestType(userContext, changeRequestId,anotherRequestTypeId);
 
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());	
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequestType requestType = loadChangeRequestType(userContext, anotherRequestTypeId, emptyOptions());		
			changeRequest.updateRequestType(requestType);		
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
			
			return present(userContext,changeRequest, allTokens());
			
		}

 	}

	

	protected void checkParamsForTransferingAnotherRequestTypeWithCode(SdsUserContext userContext, String changeRequestId, String anotherCode) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
 		checkerOf(userContext).checkCodeOfChangeRequestType( anotherCode);
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

 	}

 	public ChangeRequest transferToAnotherRequestTypeWithCode(SdsUserContext userContext, String changeRequestId, String anotherCode) throws Exception
 	{
 		checkParamsForTransferingAnotherRequestTypeWithCode(userContext, changeRequestId,anotherCode);
 		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			ChangeRequestType requestType = loadChangeRequestTypeWithCode(userContext, anotherCode, emptyOptions());
			changeRequest.updateRequestType(requestType);
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());

			return present(userContext,changeRequest, allTokens());

		}
 	}

	 


	public CandidateChangeRequestType requestCandidateRequestType(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateChangeRequestType result = new CandidateChangeRequestType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<ChangeRequestType> candidateList = changeRequestTypeDaoOf(userContext).requestCandidateChangeRequestTypeForChangeRequest(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherPlatform(SdsUserContext userContext, String changeRequestId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

 	}
 	public ChangeRequest transferToAnotherPlatform(SdsUserContext userContext, String changeRequestId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, changeRequestId,anotherPlatformId);
 
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());	
		synchronized(changeRequest){
			//will be good when the changeRequest loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			changeRequest.updatePlatform(platform);		
			changeRequest = saveChangeRequest(userContext, changeRequest, emptyOptions());
			
			return present(userContext,changeRequest, allTokens());
			
		}

 	}

	


	public CandidatePlatform requestCandidatePlatform(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForChangeRequest(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected ChangeRequestType loadChangeRequestType(SdsUserContext userContext, String newRequestTypeId, Map<String,Object> options) throws Exception
 	{

 		return changeRequestTypeDaoOf(userContext).load(newRequestTypeId, options);
 	}
 	
 	protected ChangeRequestType loadChangeRequestTypeWithCode(SdsUserContext userContext, String newCode, Map<String,Object> options) throws Exception
 	{

 		return changeRequestTypeDaoOf(userContext).loadByCode(newCode, options);
 	}

 	


	

 	protected Platform loadPlatform(SdsUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String changeRequestId, int changeRequestVersion) throws Exception {
		//deleteInternal(userContext, changeRequestId, changeRequestVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String changeRequestId, int changeRequestVersion) throws Exception{

		changeRequestDaoOf(userContext).delete(changeRequestId, changeRequestVersion);
	}

	public ChangeRequest forgetByAll(SdsUserContext userContext, String changeRequestId, int changeRequestVersion) throws Exception {
		return forgetByAllInternal(userContext, changeRequestId, changeRequestVersion);
	}
	protected ChangeRequest forgetByAllInternal(SdsUserContext userContext,
			String changeRequestId, int changeRequestVersion) throws Exception{

		return changeRequestDaoOf(userContext).disconnectFromAll(changeRequestId, changeRequestVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ChangeRequestManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return changeRequestDaoOf(userContext).deleteAll();
	}


	//disconnect ChangeRequest with event_initiator_id in EventUpdateProfile
	protected ChangeRequest breakWithEventUpdateProfileByEventInitiatorId(SdsUserContext userContext, String changeRequestId, String eventInitiatorIdId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());

			synchronized(changeRequest){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestDaoOf(userContext).planToRemoveEventUpdateProfileListWithEventInitiatorId(changeRequest, eventInitiatorIdId, this.emptyOptions());

				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withEventUpdateProfileList().done());
				return changeRequest;
			}
	}






	protected void checkParamsForAddingEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String name, String avantar, String fieldGroup, String eventInitiatorType, String eventInitiatorId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);

		
		checkerOf(userContext).checkNameOfEventUpdateProfile(name);
		
		checkerOf(userContext).checkAvantarOfEventUpdateProfile(avantar);
		
		checkerOf(userContext).checkFieldGroupOfEventUpdateProfile(fieldGroup);
		
		checkerOf(userContext).checkEventInitiatorTypeOfEventUpdateProfile(eventInitiatorType);
		
		checkerOf(userContext).checkEventInitiatorIdOfEventUpdateProfile(eventInitiatorId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);


	}
	public  ChangeRequest addEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String name, String avantar, String fieldGroup, String eventInitiatorType, String eventInitiatorId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingEventUpdateProfile(userContext,changeRequestId,name, avantar, fieldGroup, eventInitiatorType, eventInitiatorId,tokensExpr);

		EventUpdateProfile eventUpdateProfile = createEventUpdateProfile(userContext,name, avantar, fieldGroup, eventInitiatorType, eventInitiatorId);

		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, emptyOptions());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.addEventUpdateProfile( eventUpdateProfile );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withEventUpdateProfileList().done());
			
			userContext.getManagerGroup().getEventUpdateProfileManager().onNewInstanceCreated(userContext, eventUpdateProfile);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingEventUpdateProfileProperties(SdsUserContext userContext, String changeRequestId,String id,String name,String avantar,String fieldGroup,String eventInitiatorType,String eventInitiatorId,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfEventUpdateProfile(id);

		checkerOf(userContext).checkNameOfEventUpdateProfile( name);
		checkerOf(userContext).checkAvantarOfEventUpdateProfile( avantar);
		checkerOf(userContext).checkFieldGroupOfEventUpdateProfile( fieldGroup);
		checkerOf(userContext).checkEventInitiatorTypeOfEventUpdateProfile( eventInitiatorType);
		checkerOf(userContext).checkEventInitiatorIdOfEventUpdateProfile( eventInitiatorId);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest updateEventUpdateProfileProperties(SdsUserContext userContext, String changeRequestId, String id,String name,String avantar,String fieldGroup,String eventInitiatorType,String eventInitiatorId, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEventUpdateProfileProperties(userContext,changeRequestId,id,name,avantar,fieldGroup,eventInitiatorType,eventInitiatorId,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withEventUpdateProfileListList()
				.searchEventUpdateProfileListWith(EventUpdateProfile.ID_PROPERTY, "is", id).done();

		ChangeRequest changeRequestToUpdate = loadChangeRequest(userContext, changeRequestId, options);

		if(changeRequestToUpdate.getEventUpdateProfileList().isEmpty()){
			throw new ChangeRequestManagerException("EventUpdateProfile is NOT FOUND with id: '"+id+"'");
		}

		EventUpdateProfile item = changeRequestToUpdate.getEventUpdateProfileList().first();

		item.updateName( name );
		item.updateAvantar( avantar );
		item.updateFieldGroup( fieldGroup );
		item.updateEventInitiatorType( eventInitiatorType );
		item.updateEventInitiatorId( eventInitiatorId );


		//checkParamsForAddingEventUpdateProfile(userContext,changeRequestId,name, code, used,tokensExpr);
		ChangeRequest changeRequest = saveChangeRequest(userContext, changeRequestToUpdate, tokens().withEventUpdateProfileList().done());
		synchronized(changeRequest){
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}
	}


	protected EventUpdateProfile createEventUpdateProfile(SdsUserContext userContext, String name, String avantar, String fieldGroup, String eventInitiatorType, String eventInitiatorId) throws Exception{

		EventUpdateProfile eventUpdateProfile = new EventUpdateProfile();
		
		
		eventUpdateProfile.setName(name);		
		eventUpdateProfile.setAvantar(avantar);		
		eventUpdateProfile.setFieldGroup(fieldGroup);		
		eventUpdateProfile.setEventInitiatorType(eventInitiatorType);		
		eventUpdateProfile.setEventInitiatorId(eventInitiatorId);
	
		
		return eventUpdateProfile;


	}

	protected EventUpdateProfile createIndexedEventUpdateProfile(String id, int version){

		EventUpdateProfile eventUpdateProfile = new EventUpdateProfile();
		eventUpdateProfile.setId(id);
		eventUpdateProfile.setVersion(version);
		return eventUpdateProfile;

	}

	protected void checkParamsForRemovingEventUpdateProfileList(SdsUserContext userContext, String changeRequestId,
			String eventUpdateProfileIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		for(String eventUpdateProfileIdItem: eventUpdateProfileIds){
			checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeEventUpdateProfileList(SdsUserContext userContext, String changeRequestId,
			String eventUpdateProfileIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingEventUpdateProfileList(userContext, changeRequestId,  eventUpdateProfileIds, tokensExpr);


			ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
			synchronized(changeRequest){
				//Will be good when the changeRequest loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestDaoOf(userContext).planToRemoveEventUpdateProfileList(changeRequest, eventUpdateProfileIds, allTokens());
				changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withEventUpdateProfileList().done());
				deleteRelationListInGraph(userContext, changeRequest.getEventUpdateProfileList());
				return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingEventUpdateProfile(SdsUserContext userContext, String changeRequestId,
		String eventUpdateProfileId, int eventUpdateProfileVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).checkVersionOfEventUpdateProfile(eventUpdateProfileVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest removeEventUpdateProfile(SdsUserContext userContext, String changeRequestId,
		String eventUpdateProfileId, int eventUpdateProfileVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingEventUpdateProfile(userContext,changeRequestId, eventUpdateProfileId, eventUpdateProfileVersion,tokensExpr);

		EventUpdateProfile eventUpdateProfile = createIndexedEventUpdateProfile(eventUpdateProfileId, eventUpdateProfileVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequest.removeEventUpdateProfile( eventUpdateProfile );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withEventUpdateProfileList().done());
			deleteRelationInGraph(userContext, eventUpdateProfile);
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingEventUpdateProfile(SdsUserContext userContext, String changeRequestId,
		String eventUpdateProfileId, int eventUpdateProfileVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequest( changeRequestId);
		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).checkVersionOfEventUpdateProfile(eventUpdateProfileVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}
	public  ChangeRequest copyEventUpdateProfileFrom(SdsUserContext userContext, String changeRequestId,
		String eventUpdateProfileId, int eventUpdateProfileVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingEventUpdateProfile(userContext,changeRequestId, eventUpdateProfileId, eventUpdateProfileVersion,tokensExpr);

		EventUpdateProfile eventUpdateProfile = createIndexedEventUpdateProfile(eventUpdateProfileId, eventUpdateProfileVersion);
		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, allTokens());
		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			changeRequest.copyEventUpdateProfileFrom( eventUpdateProfile );
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withEventUpdateProfileList().done());
			
			userContext.getManagerGroup().getEventUpdateProfileManager().onNewInstanceCreated(userContext, (EventUpdateProfile)changeRequest.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkIdOfEventUpdateProfile(eventUpdateProfileId);
		checkerOf(userContext).checkVersionOfEventUpdateProfile(eventUpdateProfileVersion);
		

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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestManagerException.class);

	}

	public  ChangeRequest updateEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingEventUpdateProfile(userContext, changeRequestId, eventUpdateProfileId, eventUpdateProfileVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withEventUpdateProfileList().searchEventUpdateProfileListWith(EventUpdateProfile.ID_PROPERTY, "eq", eventUpdateProfileId).done();



		ChangeRequest changeRequest = loadChangeRequest(userContext, changeRequestId, loadTokens);

		synchronized(changeRequest){
			//Will be good when the changeRequest loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequest.removeEventUpdateProfile( eventUpdateProfile );
			//make changes to AcceleraterAccount.
			EventUpdateProfile eventUpdateProfileIndex = createIndexedEventUpdateProfile(eventUpdateProfileId, eventUpdateProfileVersion);

			EventUpdateProfile eventUpdateProfile = changeRequest.findTheEventUpdateProfile(eventUpdateProfileIndex);
			if(eventUpdateProfile == null){
				throw new ChangeRequestManagerException(eventUpdateProfile+" is NOT FOUND" );
			}

			eventUpdateProfile.changeProperty(property, newValueExpr);
			
			changeRequest = saveChangeRequest(userContext, changeRequest, tokens().withEventUpdateProfileList().done());
			return present(userContext,changeRequest, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(SdsUserContext userContext, ChangeRequest newCreated) throws Exception{
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
		//   ChangeRequest newChangeRequest = this.createChangeRequest(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newChangeRequest
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ChangeRequest.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<ChangeRequest> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<ChangeRequestType> requestTypeList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, ChangeRequestType.class);
		userContext.getDAOGroup().enhanceList(requestTypeList, ChangeRequestType.class);
		List<Platform> platformList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, Platform.class);
		userContext.getDAOGroup().enhanceList(platformList, Platform.class);


    }
	
	public Object listByRequestType(SdsUserContext userContext,String requestTypeId) throws Exception {
		return listPageByRequestType(userContext, requestTypeId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByRequestType(SdsUserContext userContext,String requestTypeId, int start, int count) throws Exception {
		SmartList<ChangeRequest> list = changeRequestDaoOf(userContext).findChangeRequestByRequestType(requestTypeId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(ChangeRequest.class);
		page.setContainerObject(ChangeRequestType.withId(requestTypeId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("变更请求列表");
		page.setRequestName("listByRequestType");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByRequestType/%s/",  getBeanName(), requestTypeId)));

		page.assemblerContent(userContext, "listByRequestType");
		return page.doRender(userContext);
	}
  
	public Object listByPlatform(SdsUserContext userContext,String platformId) throws Exception {
		return listPageByPlatform(userContext, platformId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByPlatform(SdsUserContext userContext,String platformId, int start, int count) throws Exception {
		SmartList<ChangeRequest> list = changeRequestDaoOf(userContext).findChangeRequestByPlatform(platformId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(ChangeRequest.class);
		page.setContainerObject(Platform.withId(platformId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("变更请求列表");
		page.setRequestName("listByPlatform");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByPlatform/%s/",  getBeanName(), platformId)));

		page.assemblerContent(userContext, "listByPlatform");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(SdsUserContext userContext, String changeRequestId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getChangeRequestDetailScope().clone();
		ChangeRequest merchantObj = (ChangeRequest) this.view(userContext, changeRequestId);
    String merchantObjId = changeRequestId;
    String linkToUrl =	"changeRequestManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "变更请求"+"详情";
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
				MapUtil.put("id", "3-createTime")
				    .put("fieldName", "createTime")
				    .put("label", "创建时间")
				    .put("type", "datetime")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("createTime", merchantObj.getCreateTime());

		propList.add(
				MapUtil.put("id", "4-remoteIp")
				    .put("fieldName", "remoteIp")
				    .put("label", "访问IP")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("remoteIp", merchantObj.getRemoteIp());

		propList.add(
				MapUtil.put("id", "5-requestType")
				    .put("fieldName", "requestType")
				    .put("label", "请求类型")
				    .put("type", "status")
				    .put("linkToUrl", "")
				    .put("displayMode", "{\"brief\":\"display_order\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("requestType", merchantObj.getRequestType());

		propList.add(
				MapUtil.put("id", "6-commited")
				    .put("fieldName", "commited")
				    .put("label", "通过对")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("commited", merchantObj.getCommited());

		propList.add(
				MapUtil.put("id", "7-platform")
				    .put("fieldName", "platform")
				    .put("label", "平台")
				    .put("type", "auto")
				    .put("linkToUrl", "platformManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("platform", merchantObj.getPlatform());

		//处理 sectionList

		//处理Section：eventUpdateProfileListSection
		Map eventUpdateProfileListSection = ListofUtils.buildSection(
		    "eventUpdateProfileListSection",
		    "事件更新配置文件列表",
		    null,
		    "",
		    "__no_group",
		    "eventUpdateProfileManager/listByChangeRequest/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(eventUpdateProfileListSection);

		result.put("eventUpdateProfileListSection", ListofUtils.toShortList(merchantObj.getEventUpdateProfileList(), "eventUpdateProfile"));
		vscope.field("eventUpdateProfileListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( EventUpdateProfile.class.getName(), null));

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


