
package com.cla.sds.changerequesttype;

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
import com.cla.sds.platform.Platform;

import com.cla.sds.platform.CandidatePlatform;

import com.cla.sds.platform.Platform;
import com.cla.sds.changerequesttype.ChangeRequestType;






public class ChangeRequestTypeManagerImpl extends CustomSdsCheckerManager implements ChangeRequestTypeManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(SdsUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = ChangeRequestTypeTokens.start().withTokenFromListName(listName).done();
		ChangeRequestType  changeRequestType = (ChangeRequestType) this.loadChangeRequestType(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = changeRequestType.collectRefercencesFromLists();
		changeRequestTypeDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, changeRequestType, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new ChangeRequestTypeGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "ChangeRequestType";
	@Override
	public ChangeRequestTypeDAO daoOf(SdsUserContext userContext) {
		return changeRequestTypeDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ChangeRequestTypeManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ChangeRequestTypeManagerException(message);

	}



 	protected ChangeRequestType saveChangeRequestType(SdsUserContext userContext, ChangeRequestType changeRequestType, String [] tokensExpr) throws Exception{	
 		//return getChangeRequestTypeDAO().save(changeRequestType, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveChangeRequestType(userContext, changeRequestType, tokens);
 	}
 	
 	protected ChangeRequestType saveChangeRequestTypeDetail(SdsUserContext userContext, ChangeRequestType changeRequestType) throws Exception{	

 		
 		return saveChangeRequestType(userContext, changeRequestType, allTokens());
 	}
 	
 	public ChangeRequestType loadChangeRequestType(SdsUserContext userContext, String changeRequestTypeId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestTypeManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		ChangeRequestType changeRequestType = loadChangeRequestType( userContext, changeRequestTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequestType, tokens);
 	}
 	
 	
 	 public ChangeRequestType searchChangeRequestType(SdsUserContext userContext, String changeRequestTypeId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestTypeManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		ChangeRequestType changeRequestType = loadChangeRequestType( userContext, changeRequestTypeId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,changeRequestType, tokens);
 	}
 	
 	

 	protected ChangeRequestType present(SdsUserContext userContext, ChangeRequestType changeRequestType, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,changeRequestType,tokens);
		
		
		ChangeRequestType  changeRequestTypeToPresent = changeRequestTypeDaoOf(userContext).present(changeRequestType, tokens);
		
		List<BaseEntity> entityListToNaming = changeRequestTypeToPresent.collectRefercencesFromLists();
		changeRequestTypeDaoOf(userContext).alias(entityListToNaming);
		
		return  changeRequestTypeToPresent;
		
		
	}
 
 	
 	
 	public ChangeRequestType loadChangeRequestTypeDetail(SdsUserContext userContext, String changeRequestTypeId) throws Exception{	
 		ChangeRequestType changeRequestType = loadChangeRequestType( userContext, changeRequestTypeId, allTokens());
 		return present(userContext,changeRequestType, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String changeRequestTypeId) throws Exception{	
 		ChangeRequestType changeRequestType = loadChangeRequestType( userContext, changeRequestTypeId, viewTokens());
 		return present(userContext,changeRequestType, allTokens());
		
 	}
 	protected ChangeRequestType saveChangeRequestType(SdsUserContext userContext, ChangeRequestType changeRequestType, Map<String,Object>tokens) throws Exception{	
 		return changeRequestTypeDaoOf(userContext).save(changeRequestType, tokens);
 	}
 	protected ChangeRequestType loadChangeRequestType(SdsUserContext userContext, String changeRequestTypeId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).throwExceptionIfHasErrors( ChangeRequestTypeManagerException.class);

 
 		return changeRequestTypeDaoOf(userContext).load(changeRequestTypeId, tokens);
 	}

	
	

	public ChangeRequestType loadChangeRequestTypeWithCode(SdsUserContext userContext, String code, Map<String,Object>tokens) throws Exception{	
 		return changeRequestTypeDaoOf(userContext).loadByCode(code, tokens);
 	}

	 


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, ChangeRequestType changeRequestType, Map<String, Object> tokens){
		super.addActions(userContext, changeRequestType, tokens);
		
		addAction(userContext, changeRequestType, tokens,"@create","createChangeRequestType","createChangeRequestType/","main","primary");
		addAction(userContext, changeRequestType, tokens,"@update","updateChangeRequestType","updateChangeRequestType/"+changeRequestType.getId()+"/","main","primary");
		addAction(userContext, changeRequestType, tokens,"@copy","cloneChangeRequestType","cloneChangeRequestType/"+changeRequestType.getId()+"/","main","primary");
		
		addAction(userContext, changeRequestType, tokens,"change_request_type.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+changeRequestType.getId()+"/","main","primary");
		addAction(userContext, changeRequestType, tokens,"change_request_type.addChangeRequest","addChangeRequest","addChangeRequest/"+changeRequestType.getId()+"/","changeRequestList","primary");
		addAction(userContext, changeRequestType, tokens,"change_request_type.removeChangeRequest","removeChangeRequest","removeChangeRequest/"+changeRequestType.getId()+"/","changeRequestList","primary");
		addAction(userContext, changeRequestType, tokens,"change_request_type.updateChangeRequest","updateChangeRequest","updateChangeRequest/"+changeRequestType.getId()+"/","changeRequestList","primary");
		addAction(userContext, changeRequestType, tokens,"change_request_type.copyChangeRequestFrom","copyChangeRequestFrom","copyChangeRequestFrom/"+changeRequestType.getId()+"/","changeRequestList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, ChangeRequestType changeRequestType, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public ChangeRequestType createChangeRequestType(SdsUserContext userContext, String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration,String platformId) throws Exception
	//public ChangeRequestType createChangeRequestType(SdsUserContext userContext,String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfChangeRequestType(name);
		checkerOf(userContext).checkCodeOfChangeRequestType(code);
		checkerOf(userContext).checkIconOfChangeRequestType(icon);
		checkerOf(userContext).checkDisplayOrderOfChangeRequestType(displayOrder);
		checkerOf(userContext).checkBindTypesOfChangeRequestType(bindTypes);
		checkerOf(userContext).checkStepConfigurationOfChangeRequestType(stepConfiguration);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);


		ChangeRequestType changeRequestType=createNewChangeRequestType();	

		changeRequestType.setName(name);
		changeRequestType.setCode(code);
		changeRequestType.setIcon(icon);
		changeRequestType.setDisplayOrder(displayOrder);
		changeRequestType.setBindTypes(bindTypes);
		changeRequestType.setStepConfiguration(stepConfiguration);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		changeRequestType.setPlatform(platform);
		
		

		changeRequestType = saveChangeRequestType(userContext, changeRequestType, emptyOptions());
		
		onNewInstanceCreated(userContext, changeRequestType);
		return changeRequestType;


	}
	protected ChangeRequestType createNewChangeRequestType()
	{

		return new ChangeRequestType();
	}

	protected void checkParamsForUpdatingChangeRequestType(SdsUserContext userContext,String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType( changeRequestTypeVersion);
		

		if(ChangeRequestType.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfChangeRequestType(parseString(newValueExpr));
		
			
		}
		if(ChangeRequestType.CODE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCodeOfChangeRequestType(parseString(newValueExpr));
		
			
		}
		if(ChangeRequestType.ICON_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkIconOfChangeRequestType(parseString(newValueExpr));
		
			
		}
		if(ChangeRequestType.DISPLAY_ORDER_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDisplayOrderOfChangeRequestType(parseInt(newValueExpr));
		
			
		}
		if(ChangeRequestType.BIND_TYPES_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBindTypesOfChangeRequestType(parseString(newValueExpr));
		
			
		}
		if(ChangeRequestType.STEP_CONFIGURATION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkStepConfigurationOfChangeRequestType(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);


	}



	public ChangeRequestType clone(SdsUserContext userContext, String fromChangeRequestTypeId) throws Exception{

		return changeRequestTypeDaoOf(userContext).clone(fromChangeRequestTypeId, this.allTokens());
	}

	public ChangeRequestType internalSaveChangeRequestType(SdsUserContext userContext, ChangeRequestType changeRequestType) throws Exception
	{
		return internalSaveChangeRequestType(userContext, changeRequestType, allTokens());

	}
	public ChangeRequestType internalSaveChangeRequestType(SdsUserContext userContext, ChangeRequestType changeRequestType, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingChangeRequestType(userContext, changeRequestTypeId, changeRequestTypeVersion, property, newValueExpr, tokensExpr);


		synchronized(changeRequestType){
			//will be good when the changeRequestType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequestType.
			if (changeRequestType.isChanged()){
			
			}
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, options);
			return changeRequestType;

		}

	}

	public ChangeRequestType updateChangeRequestType(SdsUserContext userContext,String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestType(userContext, changeRequestTypeId, changeRequestTypeVersion, property, newValueExpr, tokensExpr);



		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());
		if(changeRequestType.getVersion() != changeRequestTypeVersion){
			String message = "The target version("+changeRequestType.getVersion()+") is not equals to version("+changeRequestTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequestType){
			//will be good when the changeRequestType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequestType.
			
			changeRequestType.changeProperty(property, newValueExpr);
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().done());
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
			//return saveChangeRequestType(userContext, changeRequestType, tokens().done());
		}

	}

	public ChangeRequestType updateChangeRequestTypeProperty(SdsUserContext userContext,String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestType(userContext, changeRequestTypeId, changeRequestTypeVersion, property, newValueExpr, tokensExpr);

		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());
		if(changeRequestType.getVersion() != changeRequestTypeVersion){
			String message = "The target version("+changeRequestType.getVersion()+") is not equals to version("+changeRequestTypeVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(changeRequestType){
			//will be good when the changeRequestType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to ChangeRequestType.

			changeRequestType.changeProperty(property, newValueExpr);
			
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().done());
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
			//return saveChangeRequestType(userContext, changeRequestType, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ChangeRequestTypeTokens tokens(){
		return ChangeRequestTypeTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ChangeRequestTypeTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortChangeRequestListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ChangeRequestTypeTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(SdsUserContext userContext, String changeRequestTypeId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);

 	}
 	public ChangeRequestType transferToAnotherPlatform(SdsUserContext userContext, String changeRequestTypeId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, changeRequestTypeId,anotherPlatformId);
 
		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());	
		synchronized(changeRequestType){
			//will be good when the changeRequestType loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			changeRequestType.updatePlatform(platform);		
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, emptyOptions());
			
			return present(userContext,changeRequestType, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForChangeRequestType(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Platform loadPlatform(SdsUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{

 		return platformDaoOf(userContext).load(newPlatformId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String changeRequestTypeId, int changeRequestTypeVersion) throws Exception {
		//deleteInternal(userContext, changeRequestTypeId, changeRequestTypeVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String changeRequestTypeId, int changeRequestTypeVersion) throws Exception{

		changeRequestTypeDaoOf(userContext).delete(changeRequestTypeId, changeRequestTypeVersion);
	}

	public ChangeRequestType forgetByAll(SdsUserContext userContext, String changeRequestTypeId, int changeRequestTypeVersion) throws Exception {
		return forgetByAllInternal(userContext, changeRequestTypeId, changeRequestTypeVersion);
	}
	protected ChangeRequestType forgetByAllInternal(SdsUserContext userContext,
			String changeRequestTypeId, int changeRequestTypeVersion) throws Exception{

		return changeRequestTypeDaoOf(userContext).disconnectFromAll(changeRequestTypeId, changeRequestTypeVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ChangeRequestTypeManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return changeRequestTypeDaoOf(userContext).deleteAll();
	}


	//disconnect ChangeRequestType with platform in ChangeRequest
	protected ChangeRequestType breakWithChangeRequestByPlatform(SdsUserContext userContext, String changeRequestTypeId, String platformId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());

			synchronized(changeRequestType){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				changeRequestTypeDaoOf(userContext).planToRemoveChangeRequestListWithPlatform(changeRequestType, platformId, this.emptyOptions());

				changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().withChangeRequestList().done());
				return changeRequestType;
			}
	}






	protected void checkParamsForAddingChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String name, boolean commited, String platformId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);

		
		checkerOf(userContext).checkNameOfChangeRequest(name);
		
		checkerOf(userContext).checkCommitedOfChangeRequest(commited);
		
		checkerOf(userContext).checkPlatformIdOfChangeRequest(platformId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);


	}
	public  ChangeRequestType addChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String name, boolean commited, String platformId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequest(userContext,changeRequestTypeId,name, commited, platformId,tokensExpr);

		ChangeRequest changeRequest = createChangeRequest(userContext,name, commited, platformId);

		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, emptyOptions());
		synchronized(changeRequestType){
			//Will be good when the changeRequestType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequestType.addChangeRequest( changeRequest );
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, changeRequest);
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestProperties(SdsUserContext userContext, String changeRequestTypeId,String id,String name,boolean commited,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkIdOfChangeRequest(id);

		checkerOf(userContext).checkNameOfChangeRequest( name);
		checkerOf(userContext).checkCommitedOfChangeRequest( commited);

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);

	}
	public  ChangeRequestType updateChangeRequestProperties(SdsUserContext userContext, String changeRequestTypeId, String id,String name,boolean commited, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestProperties(userContext,changeRequestTypeId,id,name,commited,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestListList()
				.searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "is", id).done();

		ChangeRequestType changeRequestTypeToUpdate = loadChangeRequestType(userContext, changeRequestTypeId, options);

		if(changeRequestTypeToUpdate.getChangeRequestList().isEmpty()){
			throw new ChangeRequestTypeManagerException("ChangeRequest is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequest item = changeRequestTypeToUpdate.getChangeRequestList().first();

		item.updateName( name );
		item.updateCommited( commited );


		//checkParamsForAddingChangeRequest(userContext,changeRequestTypeId,name, code, used,tokensExpr);
		ChangeRequestType changeRequestType = saveChangeRequestType(userContext, changeRequestTypeToUpdate, tokens().withChangeRequestList().done());
		synchronized(changeRequestType){
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequest createChangeRequest(SdsUserContext userContext, String name, boolean commited, String platformId) throws Exception{

		ChangeRequest changeRequest = new ChangeRequest();
		
		
		changeRequest.setName(name);		
		changeRequest.setCreateTime(userContext.now());		
		changeRequest.setRemoteIp(userContext.getRemoteIP());		
		changeRequest.setCommited(commited);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		changeRequest.setPlatform(platform);
	
		
		return changeRequest;


	}

	protected ChangeRequest createIndexedChangeRequest(String id, int version){

		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(id);
		changeRequest.setVersion(version);
		return changeRequest;

	}

	protected void checkParamsForRemovingChangeRequestList(SdsUserContext userContext, String changeRequestTypeId,
			String changeRequestIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		for(String changeRequestIdItem: changeRequestIds){
			checkerOf(userContext).checkIdOfChangeRequest(changeRequestIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);

	}
	public  ChangeRequestType removeChangeRequestList(SdsUserContext userContext, String changeRequestTypeId,
			String changeRequestIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestList(userContext, changeRequestTypeId,  changeRequestIds, tokensExpr);


			ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());
			synchronized(changeRequestType){
				//Will be good when the changeRequestType loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				changeRequestTypeDaoOf(userContext).planToRemoveChangeRequestList(changeRequestType, changeRequestIds, allTokens());
				changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().withChangeRequestList().done());
				deleteRelationListInGraph(userContext, changeRequestType.getChangeRequestList());
				return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequest(SdsUserContext userContext, String changeRequestTypeId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequestType( changeRequestTypeId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);

	}
	public  ChangeRequestType removeChangeRequest(SdsUserContext userContext, String changeRequestTypeId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequest(userContext,changeRequestTypeId, changeRequestId, changeRequestVersion,tokensExpr);

		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());
		synchronized(changeRequestType){
			//Will be good when the changeRequestType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			changeRequestType.removeChangeRequest( changeRequest );
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().withChangeRequestList().done());
			deleteRelationInGraph(userContext, changeRequest);
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequest(SdsUserContext userContext, String changeRequestTypeId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfChangeRequestType( changeRequestTypeId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);

	}
	public  ChangeRequestType copyChangeRequestFrom(SdsUserContext userContext, String changeRequestTypeId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequest(userContext,changeRequestTypeId, changeRequestId, changeRequestVersion,tokensExpr);

		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, allTokens());
		synchronized(changeRequestType){
			//Will be good when the changeRequestType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			changeRequest.updateRemoteIp(userContext.getRemoteIP());

			changeRequestType.copyChangeRequestFrom( changeRequest );
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, (ChangeRequest)changeRequestType.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfChangeRequest(parseString(newValueExpr));
		
		}
		
		if(ChangeRequest.COMMITED_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCommitedOfChangeRequest(parseBoolean(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ChangeRequestTypeManagerException.class);

	}

	public  ChangeRequestType updateChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequest(userContext, changeRequestTypeId, changeRequestId, changeRequestVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestList().searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "eq", changeRequestId).done();



		ChangeRequestType changeRequestType = loadChangeRequestType(userContext, changeRequestTypeId, loadTokens);

		synchronized(changeRequestType){
			//Will be good when the changeRequestType loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//changeRequestType.removeChangeRequest( changeRequest );
			//make changes to AcceleraterAccount.
			ChangeRequest changeRequestIndex = createIndexedChangeRequest(changeRequestId, changeRequestVersion);

			ChangeRequest changeRequest = changeRequestType.findTheChangeRequest(changeRequestIndex);
			if(changeRequest == null){
				throw new ChangeRequestTypeManagerException(changeRequest+" is NOT FOUND" );
			}

			changeRequest.changeProperty(property, newValueExpr);
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			changeRequestType = saveChangeRequestType(userContext, changeRequestType, tokens().withChangeRequestList().done());
			return present(userContext,changeRequestType, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(SdsUserContext userContext, ChangeRequestType newCreated) throws Exception{
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
		//   ChangeRequestType newChangeRequestType = this.createChangeRequestType(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newChangeRequestType
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, ChangeRequestType.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<ChangeRequestType> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<Platform> platformList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, Platform.class);
		userContext.getDAOGroup().enhanceList(platformList, Platform.class);


    }
	
	public Object listByPlatform(SdsUserContext userContext,String platformId) throws Exception {
		return listPageByPlatform(userContext, platformId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByPlatform(SdsUserContext userContext,String platformId, int start, int count) throws Exception {
		SmartList<ChangeRequestType> list = changeRequestTypeDaoOf(userContext).findChangeRequestTypeByPlatform(platformId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(ChangeRequestType.class);
		page.setContainerObject(Platform.withId(platformId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("变更请求类型列表");
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
 	public Object wxappview(SdsUserContext userContext, String changeRequestTypeId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getChangeRequestTypeDetailScope().clone();
		ChangeRequestType merchantObj = (ChangeRequestType) this.view(userContext, changeRequestTypeId);
    String merchantObjId = changeRequestTypeId;
    String linkToUrl =	"changeRequestTypeManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "变更请求类型"+"详情";
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
				MapUtil.put("id", "3-code")
				    .put("fieldName", "code")
				    .put("label", "编码")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("code", merchantObj.getCode());

		propList.add(
				MapUtil.put("id", "4-icon")
				    .put("fieldName", "icon")
				    .put("label", "图标")
				    .put("type", "text")
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
				MapUtil.put("id", "6-bindTypes")
				    .put("fieldName", "bindTypes")
				    .put("label", "绑定类型")
				    .put("type", "longtext")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("bindTypes", merchantObj.getBindTypes());

		propList.add(
				MapUtil.put("id", "7-stepConfiguration")
				    .put("fieldName", "stepConfiguration")
				    .put("label", "分步配置")
				    .put("type", "longtext")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("stepConfiguration", merchantObj.getStepConfiguration());

		propList.add(
				MapUtil.put("id", "8-platform")
				    .put("fieldName", "platform")
				    .put("label", "平台")
				    .put("type", "auto")
				    .put("linkToUrl", "platformManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("platform", merchantObj.getPlatform());

		//处理 sectionList

		//处理Section：changeRequestListSection
		Map changeRequestListSection = ListofUtils.buildSection(
		    "changeRequestListSection",
		    "变更请求列表",
		    null,
		    "",
		    "__no_group",
		    "changeRequestManager/listByRequestType/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(changeRequestListSection);

		result.put("changeRequestListSection", ListofUtils.toShortList(merchantObj.getChangeRequestList(), "changeRequest"));
		vscope.field("changeRequestListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( ChangeRequest.class.getName(), null));

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


