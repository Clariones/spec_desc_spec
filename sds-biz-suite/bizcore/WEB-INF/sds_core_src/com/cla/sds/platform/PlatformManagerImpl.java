
package com.cla.sds.platform;

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
import com.cla.sds.company.Company;
import com.cla.sds.changerequesttype.ChangeRequestType;


import com.cla.sds.platform.Platform;
import com.cla.sds.changerequesttype.ChangeRequestType;






public class PlatformManagerImpl extends CustomSdsCheckerManager implements PlatformManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(SdsUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = PlatformTokens.start().withTokenFromListName(listName).done();
		Platform  platform = (Platform) this.loadPlatform(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = platform.collectRefercencesFromLists();
		platformDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, platform, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new PlatformGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "Platform";
	@Override
	public PlatformDAO daoOf(SdsUserContext userContext) {
		return platformDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws PlatformManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new PlatformManagerException(message);

	}



 	protected Platform savePlatform(SdsUserContext userContext, Platform platform, String [] tokensExpr) throws Exception{	
 		//return getPlatformDAO().save(platform, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePlatform(userContext, platform, tokens);
 	}
 	
 	protected Platform savePlatformDetail(SdsUserContext userContext, Platform platform) throws Exception{	

 		
 		return savePlatform(userContext, platform, allTokens());
 	}
 	
 	public Platform loadPlatform(SdsUserContext userContext, String platformId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).throwExceptionIfHasErrors( PlatformManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	
 	 public Platform searchPlatform(SdsUserContext userContext, String platformId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).throwExceptionIfHasErrors( PlatformManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	

 	protected Platform present(SdsUserContext userContext, Platform platform, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,platform,tokens);
		
		
		Platform  platformToPresent = platformDaoOf(userContext).present(platform, tokens);
		
		List<BaseEntity> entityListToNaming = platformToPresent.collectRefercencesFromLists();
		platformDaoOf(userContext).alias(entityListToNaming);
		
		return  platformToPresent;
		
		
	}
 
 	
 	
 	public Platform loadPlatformDetail(SdsUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, allTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, viewTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	protected Platform savePlatform(SdsUserContext userContext, Platform platform, Map<String,Object>tokens) throws Exception{	
 		return platformDaoOf(userContext).save(platform, tokens);
 	}
 	protected Platform loadPlatform(SdsUserContext userContext, String platformId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).throwExceptionIfHasErrors( PlatformManagerException.class);

 
 		return platformDaoOf(userContext).load(platformId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Platform platform, Map<String, Object> tokens){
		super.addActions(userContext, platform, tokens);
		
		addAction(userContext, platform, tokens,"@create","createPlatform","createPlatform/","main","primary");
		addAction(userContext, platform, tokens,"@update","updatePlatform","updatePlatform/"+platform.getId()+"/","main","primary");
		addAction(userContext, platform, tokens,"@copy","clonePlatform","clonePlatform/"+platform.getId()+"/","main","primary");
		
		addAction(userContext, platform, tokens,"platform.addCompany","addCompany","addCompany/"+platform.getId()+"/","companyList","primary");
		addAction(userContext, platform, tokens,"platform.removeCompany","removeCompany","removeCompany/"+platform.getId()+"/","companyList","primary");
		addAction(userContext, platform, tokens,"platform.updateCompany","updateCompany","updateCompany/"+platform.getId()+"/","companyList","primary");
		addAction(userContext, platform, tokens,"platform.copyCompanyFrom","copyCompanyFrom","copyCompanyFrom/"+platform.getId()+"/","companyList","primary");
		addAction(userContext, platform, tokens,"platform.addChangeRequestType","addChangeRequestType","addChangeRequestType/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.removeChangeRequestType","removeChangeRequestType","removeChangeRequestType/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.updateChangeRequestType","updateChangeRequestType","updateChangeRequestType/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.copyChangeRequestTypeFrom","copyChangeRequestTypeFrom","copyChangeRequestTypeFrom/"+platform.getId()+"/","changeRequestTypeList","primary");
		addAction(userContext, platform, tokens,"platform.addChangeRequest","addChangeRequest","addChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.removeChangeRequest","removeChangeRequest","removeChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.updateChangeRequest","updateChangeRequest","updateChangeRequest/"+platform.getId()+"/","changeRequestList","primary");
		addAction(userContext, platform, tokens,"platform.copyChangeRequestFrom","copyChangeRequestFrom","copyChangeRequestFrom/"+platform.getId()+"/","changeRequestList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Platform platform, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Platform createPlatform(SdsUserContext userContext, String name) throws Exception
	//public Platform createPlatform(SdsUserContext userContext,String name) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfPlatform(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


		Platform platform=createNewPlatform();	

		platform.setName(name);
		platform.setCreateTime(userContext.now());
		platform.setLastUpdateTime(userContext.now());

		platform = savePlatform(userContext, platform, emptyOptions());
		
		onNewInstanceCreated(userContext, platform);
		return platform;


	}
	protected Platform createNewPlatform()
	{

		return new Platform();
	}

	protected void checkParamsForUpdatingPlatform(SdsUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkVersionOfPlatform( platformVersion);
		

		if(Platform.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfPlatform(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}



	public Platform clone(SdsUserContext userContext, String fromPlatformId) throws Exception{

		return platformDaoOf(userContext).clone(fromPlatformId, this.allTokens());
	}

	public Platform internalSavePlatform(SdsUserContext userContext, Platform platform) throws Exception
	{
		return internalSavePlatform(userContext, platform, allTokens());

	}
	public Platform internalSavePlatform(SdsUserContext userContext, Platform platform, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);


		synchronized(platform){
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			if (platform.isChanged()){
			platform.updateLastUpdateTime(userContext.now());
			}
			platform = savePlatform(userContext, platform, options);
			return platform;

		}

	}

	public Platform updatePlatform(SdsUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);



		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			platform.updateLastUpdateTime(userContext.now());
			platform.changeProperty(property, newValueExpr);
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}

	public Platform updatePlatformProperty(SdsUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);

		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.

			platform.changeProperty(property, newValueExpr);
			platform.updateLastUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected PlatformTokens tokens(){
		return PlatformTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PlatformTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortCompanyListWith("id","desc")
		.sortChangeRequestTypeListWith("id","desc")
		.sortChangeRequestListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PlatformTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String platformId, int platformVersion) throws Exception {
		//deleteInternal(userContext, platformId, platformVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String platformId, int platformVersion) throws Exception{

		platformDaoOf(userContext).delete(platformId, platformVersion);
	}

	public Platform forgetByAll(SdsUserContext userContext, String platformId, int platformVersion) throws Exception {
		return forgetByAllInternal(userContext, platformId, platformVersion);
	}
	protected Platform forgetByAllInternal(SdsUserContext userContext,
			String platformId, int platformVersion) throws Exception{

		return platformDaoOf(userContext).disconnectFromAll(platformId, platformVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PlatformManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return platformDaoOf(userContext).deleteAll();
	}


	//disconnect Platform with request_type in ChangeRequest
	protected Platform breakWithChangeRequestByRequestType(SdsUserContext userContext, String platformId, String requestTypeId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				platformDaoOf(userContext).planToRemoveChangeRequestListWithRequestType(platform, requestTypeId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
				return platform;
			}
	}






	protected void checkParamsForAddingCompany(SdsUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfCompany(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addCompany(SdsUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingCompany(userContext,platformId,name,tokensExpr);

		Company company = createCompany(userContext,name);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addCompany( company );
			platform = savePlatform(userContext, platform, tokens().withCompanyList().done());
			
			userContext.getManagerGroup().getCompanyManager().onNewInstanceCreated(userContext, company);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingCompanyProperties(SdsUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfCompany(id);

		checkerOf(userContext).checkNameOfCompany( name);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateCompanyProperties(SdsUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCompanyProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withCompanyListList()
				.searchCompanyListWith(Company.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getCompanyList().isEmpty()){
			throw new PlatformManagerException("Company is NOT FOUND with id: '"+id+"'");
		}

		Company item = platformToUpdate.getCompanyList().first();

		item.updateName( name );


		//checkParamsForAddingCompany(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withCompanyList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected Company createCompany(SdsUserContext userContext, String name) throws Exception{

		Company company = new Company();
		
		
		company.setName(name);		
		company.setCreateTime(userContext.now());
	
		
		return company;


	}

	protected Company createIndexedCompany(String id, int version){

		Company company = new Company();
		company.setId(id);
		company.setVersion(version);
		return company;

	}

	protected void checkParamsForRemovingCompanyList(SdsUserContext userContext, String platformId,
			String companyIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String companyIdItem: companyIds){
			checkerOf(userContext).checkIdOfCompany(companyIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeCompanyList(SdsUserContext userContext, String platformId,
			String companyIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingCompanyList(userContext, platformId,  companyIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveCompanyList(platform, companyIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withCompanyList().done());
				deleteRelationListInGraph(userContext, platform.getCompanyList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingCompany(SdsUserContext userContext, String platformId,
		String companyId, int companyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkVersionOfCompany(companyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeCompany(SdsUserContext userContext, String platformId,
		String companyId, int companyVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingCompany(userContext,platformId, companyId, companyVersion,tokensExpr);

		Company company = createIndexedCompany(companyId, companyVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeCompany( company );
			platform = savePlatform(userContext, platform, tokens().withCompanyList().done());
			deleteRelationInGraph(userContext, company);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingCompany(SdsUserContext userContext, String platformId,
		String companyId, int companyVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkVersionOfCompany(companyVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyCompanyFrom(SdsUserContext userContext, String platformId,
		String companyId, int companyVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingCompany(userContext,platformId, companyId, companyVersion,tokensExpr);

		Company company = createIndexedCompany(companyId, companyVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyCompanyFrom( company );
			platform = savePlatform(userContext, platform, tokens().withCompanyList().done());
			
			userContext.getManagerGroup().getCompanyManager().onNewInstanceCreated(userContext, (Company)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingCompany(SdsUserContext userContext, String platformId, String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkVersionOfCompany(companyVersion);
		

		if(Company.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfCompany(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateCompany(SdsUserContext userContext, String platformId, String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingCompany(userContext, platformId, companyId, companyVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withCompanyList().searchCompanyListWith(Company.ID_PROPERTY, "eq", companyId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeCompany( company );
			//make changes to AcceleraterAccount.
			Company companyIndex = createIndexedCompany(companyId, companyVersion);

			Company company = platform.findTheCompany(companyIndex);
			if(company == null){
				throw new PlatformManagerException(company+" is NOT FOUND" );
			}

			company.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withCompanyList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingChangeRequestType(SdsUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfChangeRequestType(name);
		
		checkerOf(userContext).checkCodeOfChangeRequestType(code);
		
		checkerOf(userContext).checkIconOfChangeRequestType(icon);
		
		checkerOf(userContext).checkDisplayOrderOfChangeRequestType(displayOrder);
		
		checkerOf(userContext).checkBindTypesOfChangeRequestType(bindTypes);
		
		checkerOf(userContext).checkStepConfigurationOfChangeRequestType(stepConfiguration);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addChangeRequestType(SdsUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequestType(userContext,platformId,name, code, icon, displayOrder, bindTypes, stepConfiguration,tokensExpr);

		ChangeRequestType changeRequestType = createChangeRequestType(userContext,name, code, icon, displayOrder, bindTypes, stepConfiguration);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addChangeRequestType( changeRequestType );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			
			userContext.getManagerGroup().getChangeRequestTypeManager().onNewInstanceCreated(userContext, changeRequestType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestTypeProperties(SdsUserContext userContext, String platformId,String id,String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(id);

		checkerOf(userContext).checkNameOfChangeRequestType( name);
		checkerOf(userContext).checkCodeOfChangeRequestType( code);
		checkerOf(userContext).checkIconOfChangeRequestType( icon);
		checkerOf(userContext).checkDisplayOrderOfChangeRequestType( displayOrder);
		checkerOf(userContext).checkBindTypesOfChangeRequestType( bindTypes);
		checkerOf(userContext).checkStepConfigurationOfChangeRequestType( stepConfiguration);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateChangeRequestTypeProperties(SdsUserContext userContext, String platformId, String id,String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestTypeProperties(userContext,platformId,id,name,code,icon,displayOrder,bindTypes,stepConfiguration,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestTypeListList()
				.searchChangeRequestTypeListWith(ChangeRequestType.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getChangeRequestTypeList().isEmpty()){
			throw new PlatformManagerException("ChangeRequestType is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequestType item = platformToUpdate.getChangeRequestTypeList().first();

		item.updateName( name );
		item.updateCode( code );
		item.updateIcon( icon );
		item.updateDisplayOrder( displayOrder );
		item.updateBindTypes( bindTypes );
		item.updateStepConfiguration( stepConfiguration );


		//checkParamsForAddingChangeRequestType(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withChangeRequestTypeList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequestType createChangeRequestType(SdsUserContext userContext, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration) throws Exception{

		ChangeRequestType changeRequestType = new ChangeRequestType();
		
		
		changeRequestType.setName(name);		
		changeRequestType.setCode(code);		
		changeRequestType.setIcon(icon);		
		changeRequestType.setDisplayOrder(displayOrder);		
		changeRequestType.setBindTypes(bindTypes);		
		changeRequestType.setStepConfiguration(stepConfiguration);
	
		
		return changeRequestType;


	}

	protected ChangeRequestType createIndexedChangeRequestType(String id, int version){

		ChangeRequestType changeRequestType = new ChangeRequestType();
		changeRequestType.setId(id);
		changeRequestType.setVersion(version);
		return changeRequestType;

	}

	protected void checkParamsForRemovingChangeRequestTypeList(SdsUserContext userContext, String platformId,
			String changeRequestTypeIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String changeRequestTypeIdItem: changeRequestTypeIds){
			checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequestTypeList(SdsUserContext userContext, String platformId,
			String changeRequestTypeIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestTypeList(userContext, platformId,  changeRequestTypeIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveChangeRequestTypeList(platform, changeRequestTypeIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
				deleteRelationListInGraph(userContext, platform.getChangeRequestTypeList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequestType(SdsUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType(changeRequestTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequestType(SdsUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequestType(userContext,platformId, changeRequestTypeId, changeRequestTypeVersion,tokensExpr);

		ChangeRequestType changeRequestType = createIndexedChangeRequestType(changeRequestTypeId, changeRequestTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeChangeRequestType( changeRequestType );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			deleteRelationInGraph(userContext, changeRequestType);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequestType(SdsUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType(changeRequestTypeVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyChangeRequestTypeFrom(SdsUserContext userContext, String platformId,
		String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequestType(userContext,platformId, changeRequestTypeId, changeRequestTypeVersion,tokensExpr);

		ChangeRequestType changeRequestType = createIndexedChangeRequestType(changeRequestTypeId, changeRequestTypeVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			platform.copyChangeRequestTypeFrom( changeRequestType );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			
			userContext.getManagerGroup().getChangeRequestTypeManager().onNewInstanceCreated(userContext, (ChangeRequestType)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequestType(SdsUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequestType(changeRequestTypeId);
		checkerOf(userContext).checkVersionOfChangeRequestType(changeRequestTypeVersion);
		

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
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateChangeRequestType(SdsUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequestType(userContext, platformId, changeRequestTypeId, changeRequestTypeVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestTypeList().searchChangeRequestTypeListWith(ChangeRequestType.ID_PROPERTY, "eq", changeRequestTypeId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeChangeRequestType( changeRequestType );
			//make changes to AcceleraterAccount.
			ChangeRequestType changeRequestTypeIndex = createIndexedChangeRequestType(changeRequestTypeId, changeRequestTypeVersion);

			ChangeRequestType changeRequestType = platform.findTheChangeRequestType(changeRequestTypeIndex);
			if(changeRequestType == null){
				throw new PlatformManagerException(changeRequestType+" is NOT FOUND" );
			}

			changeRequestType.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withChangeRequestTypeList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingChangeRequest(SdsUserContext userContext, String platformId, String name, String requestTypeId, boolean commited,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfPlatform(platformId);

		
		checkerOf(userContext).checkNameOfChangeRequest(name);
		
		checkerOf(userContext).checkRequestTypeIdOfChangeRequest(requestTypeId);
		
		checkerOf(userContext).checkCommitedOfChangeRequest(commited);
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);


	}
	public  Platform addChangeRequest(SdsUserContext userContext, String platformId, String name, String requestTypeId, boolean commited, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequest(userContext,platformId,name, requestTypeId, commited,tokensExpr);

		ChangeRequest changeRequest = createChangeRequest(userContext,name, requestTypeId, commited);

		Platform platform = loadPlatform(userContext, platformId, emptyOptions());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addChangeRequest( changeRequest );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, changeRequest);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestProperties(SdsUserContext userContext, String platformId,String id,String name,boolean commited,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequest(id);

		checkerOf(userContext).checkNameOfChangeRequest( name);
		checkerOf(userContext).checkCommitedOfChangeRequest( commited);

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform updateChangeRequestProperties(SdsUserContext userContext, String platformId, String id,String name,boolean commited, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestProperties(userContext,platformId,id,name,commited,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestListList()
				.searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "is", id).done();

		Platform platformToUpdate = loadPlatform(userContext, platformId, options);

		if(platformToUpdate.getChangeRequestList().isEmpty()){
			throw new PlatformManagerException("ChangeRequest is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequest item = platformToUpdate.getChangeRequestList().first();

		item.updateName( name );
		item.updateCommited( commited );


		//checkParamsForAddingChangeRequest(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withChangeRequestList().done());
		synchronized(platform){
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequest createChangeRequest(SdsUserContext userContext, String name, String requestTypeId, boolean commited) throws Exception{

		ChangeRequest changeRequest = new ChangeRequest();
		
		
		changeRequest.setName(name);		
		changeRequest.setCreateTime(userContext.now());		
		changeRequest.setRemoteIp(userContext.getRemoteIP());		
		ChangeRequestType  requestType = new ChangeRequestType();
		requestType.setId(requestTypeId);		
		changeRequest.setRequestType(requestType);		
		changeRequest.setCommited(commited);
	
		
		return changeRequest;


	}

	protected ChangeRequest createIndexedChangeRequest(String id, int version){

		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(id);
		changeRequest.setVersion(version);
		return changeRequest;

	}

	protected void checkParamsForRemovingChangeRequestList(SdsUserContext userContext, String platformId,
			String changeRequestIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfPlatform(platformId);
		for(String changeRequestIdItem: changeRequestIds){
			checkerOf(userContext).checkIdOfChangeRequest(changeRequestIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequestList(SdsUserContext userContext, String platformId,
			String changeRequestIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestList(userContext, platformId,  changeRequestIds, tokensExpr);


			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				platformDaoOf(userContext).planToRemoveChangeRequestList(platform, changeRequestIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
				deleteRelationListInGraph(userContext, platform.getChangeRequestList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequest(SdsUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform removeChangeRequest(SdsUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequest(userContext,platformId, changeRequestId, changeRequestVersion,tokensExpr);

		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeChangeRequest( changeRequest );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			deleteRelationInGraph(userContext, changeRequest);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequest(SdsUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfPlatform( platformId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}
	public  Platform copyChangeRequestFrom(SdsUserContext userContext, String platformId,
		String changeRequestId, int changeRequestVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequest(userContext,platformId, changeRequestId, changeRequestVersion,tokensExpr);

		ChangeRequest changeRequest = createIndexedChangeRequest(changeRequestId, changeRequestVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			changeRequest.updateRemoteIp(userContext.getRemoteIP());

			platform.copyChangeRequestFrom( changeRequest );
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			
			userContext.getManagerGroup().getChangeRequestManager().onNewInstanceCreated(userContext, (ChangeRequest)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequest(SdsUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfPlatform(platformId);
		checkerOf(userContext).checkIdOfChangeRequest(changeRequestId);
		checkerOf(userContext).checkVersionOfChangeRequest(changeRequestVersion);
		

		if(ChangeRequest.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfChangeRequest(parseString(newValueExpr));
		
		}
		
		if(ChangeRequest.COMMITED_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkCommitedOfChangeRequest(parseBoolean(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(PlatformManagerException.class);

	}

	public  Platform updateChangeRequest(SdsUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequest(userContext, platformId, changeRequestId, changeRequestVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestList().searchChangeRequestListWith(ChangeRequest.ID_PROPERTY, "eq", changeRequestId).done();



		Platform platform = loadPlatform(userContext, platformId, loadTokens);

		synchronized(platform){
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeChangeRequest( changeRequest );
			//make changes to AcceleraterAccount.
			ChangeRequest changeRequestIndex = createIndexedChangeRequest(changeRequestId, changeRequestVersion);

			ChangeRequest changeRequest = platform.findTheChangeRequest(changeRequestIndex);
			if(changeRequest == null){
				throw new PlatformManagerException(changeRequest+" is NOT FOUND" );
			}

			changeRequest.changeProperty(property, newValueExpr);
			changeRequest.updateRemoteIp(userContext.getRemoteIP());
			platform = savePlatform(userContext, platform, tokens().withChangeRequestList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(SdsUserContext userContext, Platform newCreated) throws Exception{
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
		//   Platform newPlatform = this.createPlatform(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newPlatform
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Platform.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<Platform> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}


    }
	
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(SdsUserContext userContext, String platformId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getPlatformDetailScope().clone();
		Platform merchantObj = (Platform) this.view(userContext, platformId);
    String merchantObjId = platformId;
    String linkToUrl =	"platformManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "平台"+"详情";
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
				MapUtil.put("id", "4-lastUpdateTime")
				    .put("fieldName", "lastUpdateTime")
				    .put("label", "最后更新时间")
				    .put("type", "datetime")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("lastUpdateTime", merchantObj.getLastUpdateTime());

		//处理 sectionList

		//处理Section：companyListSection
		Map companyListSection = ListofUtils.buildSection(
		    "companyListSection",
		    "公司列表",
		    null,
		    "",
		    "__no_group",
		    "companyManager/listByPlatform/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(companyListSection);

		result.put("companyListSection", ListofUtils.toShortList(merchantObj.getCompanyList(), "company"));
		vscope.field("companyListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( Company.class.getName(), null));

		//处理Section：changeRequestListSection
		Map changeRequestListSection = ListofUtils.buildSection(
		    "changeRequestListSection",
		    "变更请求列表",
		    null,
		    "",
		    "__no_group",
		    "changeRequestManager/listByPlatform/"+merchantObjId+"/",
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


