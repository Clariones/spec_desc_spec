
package com.cla.sds.company;

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
import com.cla.sds.platform.Platform;

import com.cla.sds.platform.CandidatePlatform;

import com.cla.sds.company.Company;






public class CompanyManagerImpl extends CustomSdsCheckerManager implements CompanyManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(SdsUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = CompanyTokens.start().withTokenFromListName(listName).done();
		Company  company = (Company) this.loadCompany(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = company.collectRefercencesFromLists();
		companyDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, company, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new CompanyGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "Company";
	@Override
	public CompanyDAO daoOf(SdsUserContext userContext) {
		return companyDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws CompanyManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new CompanyManagerException(message);

	}



 	protected Company saveCompany(SdsUserContext userContext, Company company, String [] tokensExpr) throws Exception{	
 		//return getCompanyDAO().save(company, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveCompany(userContext, company, tokens);
 	}
 	
 	protected Company saveCompanyDetail(SdsUserContext userContext, Company company) throws Exception{	

 		
 		return saveCompany(userContext, company, allTokens());
 	}
 	
 	public Company loadCompany(SdsUserContext userContext, String companyId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).throwExceptionIfHasErrors( CompanyManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Company company = loadCompany( userContext, companyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,company, tokens);
 	}
 	
 	
 	 public Company searchCompany(SdsUserContext userContext, String companyId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).throwExceptionIfHasErrors( CompanyManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Company company = loadCompany( userContext, companyId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,company, tokens);
 	}
 	
 	

 	protected Company present(SdsUserContext userContext, Company company, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,company,tokens);
		
		
		Company  companyToPresent = companyDaoOf(userContext).present(company, tokens);
		
		List<BaseEntity> entityListToNaming = companyToPresent.collectRefercencesFromLists();
		companyDaoOf(userContext).alias(entityListToNaming);
		
		return  companyToPresent;
		
		
	}
 
 	
 	
 	public Company loadCompanyDetail(SdsUserContext userContext, String companyId) throws Exception{	
 		Company company = loadCompany( userContext, companyId, allTokens());
 		return present(userContext,company, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String companyId) throws Exception{	
 		Company company = loadCompany( userContext, companyId, viewTokens());
 		return present(userContext,company, allTokens());
		
 	}
 	protected Company saveCompany(SdsUserContext userContext, Company company, Map<String,Object>tokens) throws Exception{	
 		return companyDaoOf(userContext).save(company, tokens);
 	}
 	protected Company loadCompany(SdsUserContext userContext, String companyId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).throwExceptionIfHasErrors( CompanyManagerException.class);

 
 		return companyDaoOf(userContext).load(companyId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Company company, Map<String, Object> tokens){
		super.addActions(userContext, company, tokens);
		
		addAction(userContext, company, tokens,"@create","createCompany","createCompany/","main","primary");
		addAction(userContext, company, tokens,"@update","updateCompany","updateCompany/"+company.getId()+"/","main","primary");
		addAction(userContext, company, tokens,"@copy","cloneCompany","cloneCompany/"+company.getId()+"/","main","primary");
		
		addAction(userContext, company, tokens,"company.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+company.getId()+"/","main","primary");
		addAction(userContext, company, tokens,"company.addUser","addUser","addUser/"+company.getId()+"/","userList","primary");
		addAction(userContext, company, tokens,"company.removeUser","removeUser","removeUser/"+company.getId()+"/","userList","primary");
		addAction(userContext, company, tokens,"company.updateUser","updateUser","updateUser/"+company.getId()+"/","userList","primary");
		addAction(userContext, company, tokens,"company.copyUserFrom","copyUserFrom","copyUserFrom/"+company.getId()+"/","userList","primary");
		addAction(userContext, company, tokens,"company.addProject","addProject","addProject/"+company.getId()+"/","projectList","primary");
		addAction(userContext, company, tokens,"company.removeProject","removeProject","removeProject/"+company.getId()+"/","projectList","primary");
		addAction(userContext, company, tokens,"company.updateProject","updateProject","updateProject/"+company.getId()+"/","projectList","primary");
		addAction(userContext, company, tokens,"company.copyProjectFrom","copyProjectFrom","copyProjectFrom/"+company.getId()+"/","projectList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Company company, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Company createCompany(SdsUserContext userContext, String name,String platformId) throws Exception
	//public Company createCompany(SdsUserContext userContext,String name, String platformId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfCompany(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);


		Company company=createNewCompany();	

		company.setName(name);
		company.setCreateTime(userContext.now());
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		company.setPlatform(platform);
		
		

		company = saveCompany(userContext, company, emptyOptions());
		
		onNewInstanceCreated(userContext, company);
		return company;


	}
	protected Company createNewCompany()
	{

		return new Company();
	}

	protected void checkParamsForUpdatingCompany(SdsUserContext userContext,String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkVersionOfCompany( companyVersion);
		

		if(Company.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfCompany(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);


	}



	public Company clone(SdsUserContext userContext, String fromCompanyId) throws Exception{

		return companyDaoOf(userContext).clone(fromCompanyId, this.allTokens());
	}

	public Company internalSaveCompany(SdsUserContext userContext, Company company) throws Exception
	{
		return internalSaveCompany(userContext, company, allTokens());

	}
	public Company internalSaveCompany(SdsUserContext userContext, Company company, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingCompany(userContext, companyId, companyVersion, property, newValueExpr, tokensExpr);


		synchronized(company){
			//will be good when the company loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Company.
			if (company.isChanged()){
			
			}
			company = saveCompany(userContext, company, options);
			return company;

		}

	}

	public Company updateCompany(SdsUserContext userContext,String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCompany(userContext, companyId, companyVersion, property, newValueExpr, tokensExpr);



		Company company = loadCompany(userContext, companyId, allTokens());
		if(company.getVersion() != companyVersion){
			String message = "The target version("+company.getVersion()+") is not equals to version("+companyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(company){
			//will be good when the company loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Company.
			
			company.changeProperty(property, newValueExpr);
			company = saveCompany(userContext, company, tokens().done());
			return present(userContext,company, mergedAllTokens(tokensExpr));
			//return saveCompany(userContext, company, tokens().done());
		}

	}

	public Company updateCompanyProperty(SdsUserContext userContext,String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingCompany(userContext, companyId, companyVersion, property, newValueExpr, tokensExpr);

		Company company = loadCompany(userContext, companyId, allTokens());
		if(company.getVersion() != companyVersion){
			String message = "The target version("+company.getVersion()+") is not equals to version("+companyVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(company){
			//will be good when the company loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Company.

			company.changeProperty(property, newValueExpr);
			
			company = saveCompany(userContext, company, tokens().done());
			return present(userContext,company, mergedAllTokens(tokensExpr));
			//return saveCompany(userContext, company, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected CompanyTokens tokens(){
		return CompanyTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return CompanyTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortUserListWith("id","desc")
		.sortProjectListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return CompanyTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(SdsUserContext userContext, String companyId, String anotherPlatformId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfCompany(companyId);
 		checkerOf(userContext).checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

 	}
 	public Company transferToAnotherPlatform(SdsUserContext userContext, String companyId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, companyId,anotherPlatformId);
 
		Company company = loadCompany(userContext, companyId, allTokens());	
		synchronized(company){
			//will be good when the company loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			company.updatePlatform(platform);		
			company = saveCompany(userContext, company, emptyOptions());
			
			return present(userContext,company, allTokens());
			
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
		SmartList<Platform> candidateList = platformDaoOf(userContext).requestCandidatePlatformForCompany(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(SdsUserContext userContext, String companyId, int companyVersion) throws Exception {
		//deleteInternal(userContext, companyId, companyVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String companyId, int companyVersion) throws Exception{

		companyDaoOf(userContext).delete(companyId, companyVersion);
	}

	public Company forgetByAll(SdsUserContext userContext, String companyId, int companyVersion) throws Exception {
		return forgetByAllInternal(userContext, companyId, companyVersion);
	}
	protected Company forgetByAllInternal(SdsUserContext userContext,
			String companyId, int companyVersion) throws Exception{

		return companyDaoOf(userContext).disconnectFromAll(companyId, companyVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new CompanyManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return companyDaoOf(userContext).deleteAll();
	}








	protected void checkParamsForAddingUser(SdsUserContext userContext, String companyId, String name, Date joinTime, String title,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfCompany(companyId);

		
		checkerOf(userContext).checkNameOfUser(name);
		
		checkerOf(userContext).checkJoinTimeOfUser(joinTime);
		
		checkerOf(userContext).checkTitleOfUser(title);
	
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);


	}
	public  Company addUser(SdsUserContext userContext, String companyId, String name, Date joinTime, String title, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingUser(userContext,companyId,name, joinTime, title,tokensExpr);

		User user = createUser(userContext,name, joinTime, title);

		Company company = loadCompany(userContext, companyId, emptyOptions());
		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			company.addUser( user );
			company = saveCompany(userContext, company, tokens().withUserList().done());
			
			userContext.getManagerGroup().getUserManager().onNewInstanceCreated(userContext, user);
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserProperties(SdsUserContext userContext, String companyId,String id,String name,Date joinTime,String title,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkIdOfUser(id);

		checkerOf(userContext).checkNameOfUser( name);
		checkerOf(userContext).checkJoinTimeOfUser( joinTime);
		checkerOf(userContext).checkTitleOfUser( title);

		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company updateUserProperties(SdsUserContext userContext, String companyId, String id,String name,Date joinTime,String title, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserProperties(userContext,companyId,id,name,joinTime,title,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserListList()
				.searchUserListWith(User.ID_PROPERTY, "is", id).done();

		Company companyToUpdate = loadCompany(userContext, companyId, options);

		if(companyToUpdate.getUserList().isEmpty()){
			throw new CompanyManagerException("User is NOT FOUND with id: '"+id+"'");
		}

		User item = companyToUpdate.getUserList().first();

		item.updateName( name );
		item.updateJoinTime( joinTime );
		item.updateTitle( title );


		//checkParamsForAddingUser(userContext,companyId,name, code, used,tokensExpr);
		Company company = saveCompany(userContext, companyToUpdate, tokens().withUserList().done());
		synchronized(company){
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}
	}


	protected User createUser(SdsUserContext userContext, String name, Date joinTime, String title) throws Exception{

		User user = new User();
		
		
		user.setName(name);		
		user.setJoinTime(joinTime);		
		user.setTitle(title);
	
		
		return user;


	}

	protected User createIndexedUser(String id, int version){

		User user = new User();
		user.setId(id);
		user.setVersion(version);
		return user;

	}

	protected void checkParamsForRemovingUserList(SdsUserContext userContext, String companyId,
			String userIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfCompany(companyId);
		for(String userIdItem: userIds){
			checkerOf(userContext).checkIdOfUser(userIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company removeUserList(SdsUserContext userContext, String companyId,
			String userIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingUserList(userContext, companyId,  userIds, tokensExpr);


			Company company = loadCompany(userContext, companyId, allTokens());
			synchronized(company){
				//Will be good when the company loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				companyDaoOf(userContext).planToRemoveUserList(company, userIds, allTokens());
				company = saveCompany(userContext, company, tokens().withUserList().done());
				deleteRelationListInGraph(userContext, company.getUserList());
				return present(userContext,company, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingUser(SdsUserContext userContext, String companyId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfCompany( companyId);
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser(userVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company removeUser(SdsUserContext userContext, String companyId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingUser(userContext,companyId, userId, userVersion,tokensExpr);

		User user = createIndexedUser(userId, userVersion);
		Company company = loadCompany(userContext, companyId, allTokens());
		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			company.removeUser( user );
			company = saveCompany(userContext, company, tokens().withUserList().done());
			deleteRelationInGraph(userContext, user);
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingUser(SdsUserContext userContext, String companyId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfCompany( companyId);
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser(userVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company copyUserFrom(SdsUserContext userContext, String companyId,
		String userId, int userVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingUser(userContext,companyId, userId, userVersion,tokensExpr);

		User user = createIndexedUser(userId, userVersion);
		Company company = loadCompany(userContext, companyId, allTokens());
		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			company.copyUserFrom( user );
			company = saveCompany(userContext, company, tokens().withUserList().done());
			
			userContext.getManagerGroup().getUserManager().onNewInstanceCreated(userContext, (User)company.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingUser(SdsUserContext userContext, String companyId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser(userVersion);
		

		if(User.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfUser(parseString(newValueExpr));
		
		}
		
		if(User.JOIN_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkJoinTimeOfUser(parseDate(newValueExpr));
		
		}
		
		if(User.TITLE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTitleOfUser(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}

	public  Company updateUser(SdsUserContext userContext, String companyId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingUser(userContext, companyId, userId, userVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withUserList().searchUserListWith(User.ID_PROPERTY, "eq", userId).done();



		Company company = loadCompany(userContext, companyId, loadTokens);

		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//company.removeUser( user );
			//make changes to AcceleraterAccount.
			User userIndex = createIndexedUser(userId, userVersion);

			User user = company.findTheUser(userIndex);
			if(user == null){
				throw new CompanyManagerException(user+" is NOT FOUND" );
			}

			user.changeProperty(property, newValueExpr);
			
			company = saveCompany(userContext, company, tokens().withUserList().done());
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingProject(SdsUserContext userContext, String companyId, String name,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfCompany(companyId);

		
		checkerOf(userContext).checkNameOfProject(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);


	}
	public  Company addProject(SdsUserContext userContext, String companyId, String name, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingProject(userContext,companyId,name,tokensExpr);

		Project project = createProject(userContext,name);

		Company company = loadCompany(userContext, companyId, emptyOptions());
		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			company.addProject( project );
			company = saveCompany(userContext, company, tokens().withProjectList().done());
			
			userContext.getManagerGroup().getProjectManager().onNewInstanceCreated(userContext, project);
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingProjectProperties(SdsUserContext userContext, String companyId,String id,String name,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkIdOfProject(id);

		checkerOf(userContext).checkNameOfProject( name);

		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company updateProjectProperties(SdsUserContext userContext, String companyId, String id,String name, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingProjectProperties(userContext,companyId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withProjectListList()
				.searchProjectListWith(Project.ID_PROPERTY, "is", id).done();

		Company companyToUpdate = loadCompany(userContext, companyId, options);

		if(companyToUpdate.getProjectList().isEmpty()){
			throw new CompanyManagerException("Project is NOT FOUND with id: '"+id+"'");
		}

		Project item = companyToUpdate.getProjectList().first();

		item.updateName( name );


		//checkParamsForAddingProject(userContext,companyId,name, code, used,tokensExpr);
		Company company = saveCompany(userContext, companyToUpdate, tokens().withProjectList().done());
		synchronized(company){
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}
	}


	protected Project createProject(SdsUserContext userContext, String name) throws Exception{

		Project project = new Project();
		
		
		project.setName(name);
	
		
		return project;


	}

	protected Project createIndexedProject(String id, int version){

		Project project = new Project();
		project.setId(id);
		project.setVersion(version);
		return project;

	}

	protected void checkParamsForRemovingProjectList(SdsUserContext userContext, String companyId,
			String projectIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfCompany(companyId);
		for(String projectIdItem: projectIds){
			checkerOf(userContext).checkIdOfProject(projectIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company removeProjectList(SdsUserContext userContext, String companyId,
			String projectIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingProjectList(userContext, companyId,  projectIds, tokensExpr);


			Company company = loadCompany(userContext, companyId, allTokens());
			synchronized(company){
				//Will be good when the company loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				companyDaoOf(userContext).planToRemoveProjectList(company, projectIds, allTokens());
				company = saveCompany(userContext, company, tokens().withProjectList().done());
				deleteRelationListInGraph(userContext, company.getProjectList());
				return present(userContext,company, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingProject(SdsUserContext userContext, String companyId,
		String projectId, int projectVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfCompany( companyId);
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkVersionOfProject(projectVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company removeProject(SdsUserContext userContext, String companyId,
		String projectId, int projectVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingProject(userContext,companyId, projectId, projectVersion,tokensExpr);

		Project project = createIndexedProject(projectId, projectVersion);
		Company company = loadCompany(userContext, companyId, allTokens());
		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			company.removeProject( project );
			company = saveCompany(userContext, company, tokens().withProjectList().done());
			deleteRelationInGraph(userContext, project);
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingProject(SdsUserContext userContext, String companyId,
		String projectId, int projectVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfCompany( companyId);
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkVersionOfProject(projectVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}
	public  Company copyProjectFrom(SdsUserContext userContext, String companyId,
		String projectId, int projectVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingProject(userContext,companyId, projectId, projectVersion,tokensExpr);

		Project project = createIndexedProject(projectId, projectVersion);
		Company company = loadCompany(userContext, companyId, allTokens());
		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			company.copyProjectFrom( project );
			company = saveCompany(userContext, company, tokens().withProjectList().done());
			
			userContext.getManagerGroup().getProjectManager().onNewInstanceCreated(userContext, (Project)company.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingProject(SdsUserContext userContext, String companyId, String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfCompany(companyId);
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkVersionOfProject(projectVersion);
		

		if(Project.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfProject(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(CompanyManagerException.class);

	}

	public  Company updateProject(SdsUserContext userContext, String companyId, String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingProject(userContext, companyId, projectId, projectVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withProjectList().searchProjectListWith(Project.ID_PROPERTY, "eq", projectId).done();



		Company company = loadCompany(userContext, companyId, loadTokens);

		synchronized(company){
			//Will be good when the company loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//company.removeProject( project );
			//make changes to AcceleraterAccount.
			Project projectIndex = createIndexedProject(projectId, projectVersion);

			Project project = company.findTheProject(projectIndex);
			if(project == null){
				throw new CompanyManagerException(project+" is NOT FOUND" );
			}

			project.changeProperty(property, newValueExpr);
			
			company = saveCompany(userContext, company, tokens().withProjectList().done());
			return present(userContext,company, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(SdsUserContext userContext, Company newCreated) throws Exception{
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
		//   Company newCompany = this.createCompany(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newCompany
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Company.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<Company> list) throws Exception {
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
		SmartList<Company> list = companyDaoOf(userContext).findCompanyByPlatform(platformId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(Company.class);
		page.setContainerObject(Platform.withId(platformId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("公司列表");
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
 	public Object wxappview(SdsUserContext userContext, String companyId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getCompanyDetailScope().clone();
		Company merchantObj = (Company) this.view(userContext, companyId);
    String merchantObjId = companyId;
    String linkToUrl =	"companyManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "公司"+"详情";
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
				MapUtil.put("id", "4-platform")
				    .put("fieldName", "platform")
				    .put("label", "平台")
				    .put("type", "auto")
				    .put("linkToUrl", "platformManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("platform", merchantObj.getPlatform());

		//处理 sectionList

		//处理Section：userListSection
		Map userListSection = ListofUtils.buildSection(
		    "userListSection",
		    "用户列表",
		    null,
		    "",
		    "__no_group",
		    "userManager/listByCompany/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(userListSection);

		result.put("userListSection", ListofUtils.toShortList(merchantObj.getUserList(), "user"));
		vscope.field("userListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( User.class.getName(), null));

		//处理Section：projectListSection
		Map projectListSection = ListofUtils.buildSection(
		    "projectListSection",
		    "项目列表",
		    null,
		    "",
		    "__no_group",
		    "projectManager/listByCompany/"+merchantObjId+"/",
		    "auto"
		);
		sections.add(projectListSection);

		result.put("projectListSection", ListofUtils.toShortList(merchantObj.getProjectList(), "project"));
		vscope.field("projectListSection", SdsListOfViewScope.getInstance()
					.getListOfViewScope( Project.class.getName(), null));

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


