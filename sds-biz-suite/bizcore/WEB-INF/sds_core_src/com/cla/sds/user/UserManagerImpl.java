
package com.cla.sds.user;

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


import com.cla.sds.userproject.UserProject;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.company.Company;
import com.cla.sds.changerequestspec.ChangeRequestSpec;

import com.cla.sds.company.CandidateCompany;

import com.cla.sds.user.User;
import com.cla.sds.project.Project;






public class UserManagerImpl extends CustomSdsCheckerManager implements UserManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(SdsUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = UserTokens.start().withTokenFromListName(listName).done();
		User  user = (User) this.loadUser(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = user.collectRefercencesFromLists();
		userDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, user, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new UserGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "User";
	@Override
	public UserDAO daoOf(SdsUserContext userContext) {
		return userDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws UserManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new UserManagerException(message);

	}



 	protected User saveUser(SdsUserContext userContext, User user, String [] tokensExpr) throws Exception{	
 		//return getUserDAO().save(user, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveUser(userContext, user, tokens);
 	}
 	
 	protected User saveUserDetail(SdsUserContext userContext, User user) throws Exception{	

 		
 		return saveUser(userContext, user, allTokens());
 	}
 	
 	public User loadUser(SdsUserContext userContext, String userId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		User user = loadUser( userContext, userId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,user, tokens);
 	}
 	
 	
 	 public User searchUser(SdsUserContext userContext, String userId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		User user = loadUser( userContext, userId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,user, tokens);
 	}
 	
 	

 	protected User present(SdsUserContext userContext, User user, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,user,tokens);
		
		
		User  userToPresent = userDaoOf(userContext).present(user, tokens);
		
		List<BaseEntity> entityListToNaming = userToPresent.collectRefercencesFromLists();
		userDaoOf(userContext).alias(entityListToNaming);
		
		return  userToPresent;
		
		
	}
 
 	
 	
 	public User loadUserDetail(SdsUserContext userContext, String userId) throws Exception{	
 		User user = loadUser( userContext, userId, allTokens());
 		return present(userContext,user, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String userId) throws Exception{	
 		User user = loadUser( userContext, userId, viewTokens());
 		return present(userContext,user, allTokens());
		
 	}
 	protected User saveUser(SdsUserContext userContext, User user, Map<String,Object>tokens) throws Exception{	
 		return userDaoOf(userContext).save(user, tokens);
 	}
 	protected User loadUser(SdsUserContext userContext, String userId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).throwExceptionIfHasErrors( UserManagerException.class);

 
 		return userDaoOf(userContext).load(userId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, User user, Map<String, Object> tokens){
		super.addActions(userContext, user, tokens);
		
		addAction(userContext, user, tokens,"@create","createUser","createUser/","main","primary");
		addAction(userContext, user, tokens,"@update","updateUser","updateUser/"+user.getId()+"/","main","primary");
		addAction(userContext, user, tokens,"@copy","cloneUser","cloneUser/"+user.getId()+"/","main","primary");
		
		addAction(userContext, user, tokens,"user.transfer_to_company","transferToAnotherCompany","transferToAnotherCompany/"+user.getId()+"/","main","primary");
		addAction(userContext, user, tokens,"user.addUserProject","addUserProject","addUserProject/"+user.getId()+"/","userProjectList","primary");
		addAction(userContext, user, tokens,"user.removeUserProject","removeUserProject","removeUserProject/"+user.getId()+"/","userProjectList","primary");
		addAction(userContext, user, tokens,"user.updateUserProject","updateUserProject","updateUserProject/"+user.getId()+"/","userProjectList","primary");
		addAction(userContext, user, tokens,"user.copyUserProjectFrom","copyUserProjectFrom","copyUserProjectFrom/"+user.getId()+"/","userProjectList","primary");
		addAction(userContext, user, tokens,"user.addPageFlowSpec","addPageFlowSpec","addPageFlowSpec/"+user.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.removePageFlowSpec","removePageFlowSpec","removePageFlowSpec/"+user.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.updatePageFlowSpec","updatePageFlowSpec","updatePageFlowSpec/"+user.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.copyPageFlowSpecFrom","copyPageFlowSpecFrom","copyPageFlowSpecFrom/"+user.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.addWorkFlowSpec","addWorkFlowSpec","addWorkFlowSpec/"+user.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.removeWorkFlowSpec","removeWorkFlowSpec","removeWorkFlowSpec/"+user.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.updateWorkFlowSpec","updateWorkFlowSpec","updateWorkFlowSpec/"+user.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.copyWorkFlowSpecFrom","copyWorkFlowSpecFrom","copyWorkFlowSpecFrom/"+user.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, user, tokens,"user.addChangeRequestSpec","addChangeRequestSpec","addChangeRequestSpec/"+user.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, user, tokens,"user.removeChangeRequestSpec","removeChangeRequestSpec","removeChangeRequestSpec/"+user.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, user, tokens,"user.updateChangeRequestSpec","updateChangeRequestSpec","updateChangeRequestSpec/"+user.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, user, tokens,"user.copyChangeRequestSpecFrom","copyChangeRequestSpecFrom","copyChangeRequestSpecFrom/"+user.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, user, tokens,"user.addPageContentSpec","addPageContentSpec","addPageContentSpec/"+user.getId()+"/","pageContentSpecList","primary");
		addAction(userContext, user, tokens,"user.removePageContentSpec","removePageContentSpec","removePageContentSpec/"+user.getId()+"/","pageContentSpecList","primary");
		addAction(userContext, user, tokens,"user.updatePageContentSpec","updatePageContentSpec","updatePageContentSpec/"+user.getId()+"/","pageContentSpecList","primary");
		addAction(userContext, user, tokens,"user.copyPageContentSpecFrom","copyPageContentSpecFrom","copyPageContentSpecFrom/"+user.getId()+"/","pageContentSpecList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, User user, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public User createUser(SdsUserContext userContext, String name,Date joinTime,String companyId,String title) throws Exception
	//public User createUser(SdsUserContext userContext,String name, Date joinTime, String companyId, String title) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfUser(name);
		checkerOf(userContext).checkJoinTimeOfUser(joinTime);
		checkerOf(userContext).checkTitleOfUser(title);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


		User user=createNewUser();	

		user.setName(name);
		user.setJoinTime(joinTime);
			
		Company company = loadCompany(userContext, companyId,emptyOptions());
		user.setCompany(company);
		
		
		user.setTitle(title);

		user = saveUser(userContext, user, emptyOptions());
		
		onNewInstanceCreated(userContext, user);
		return user;


	}
	protected User createNewUser()
	{

		return new User();
	}

	protected void checkParamsForUpdatingUser(SdsUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkVersionOfUser( userVersion);
		

		if(User.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfUser(parseString(newValueExpr));
		
			
		}
		if(User.JOIN_TIME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkJoinTimeOfUser(parseDate(newValueExpr));
		
			
		}		

		
		if(User.TITLE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkTitleOfUser(parseString(newValueExpr));
		
			
		}
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}



	public User clone(SdsUserContext userContext, String fromUserId) throws Exception{

		return userDaoOf(userContext).clone(fromUserId, this.allTokens());
	}

	public User internalSaveUser(SdsUserContext userContext, User user) throws Exception
	{
		return internalSaveUser(userContext, user, allTokens());

	}
	public User internalSaveUser(SdsUserContext userContext, User user, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingUser(userContext, userId, userVersion, property, newValueExpr, tokensExpr);


		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to User.
			if (user.isChanged()){
			
			}
			user = saveUser(userContext, user, options);
			return user;

		}

	}

	public User updateUser(SdsUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUser(userContext, userId, userVersion, property, newValueExpr, tokensExpr);



		User user = loadUser(userContext, userId, allTokens());
		if(user.getVersion() != userVersion){
			String message = "The target version("+user.getVersion()+") is not equals to version("+userVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to User.
			
			user.changeProperty(property, newValueExpr);
			user = saveUser(userContext, user, tokens().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
			//return saveUser(userContext, user, tokens().done());
		}

	}

	public User updateUserProperty(SdsUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUser(userContext, userId, userVersion, property, newValueExpr, tokensExpr);

		User user = loadUser(userContext, userId, allTokens());
		if(user.getVersion() != userVersion){
			String message = "The target version("+user.getVersion()+") is not equals to version("+userVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to User.

			user.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
			//return saveUser(userContext, user, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected UserTokens tokens(){
		return UserTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return UserTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortUserProjectListWith("id","desc")
		.sortPageFlowSpecListWith("id","desc")
		.sortWorkFlowSpecListWith("id","desc")
		.sortChangeRequestSpecListWith("id","desc")
		.sortPageContentSpecListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return UserTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherCompany(SdsUserContext userContext, String userId, String anotherCompanyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfUser(userId);
 		checkerOf(userContext).checkIdOfCompany(anotherCompanyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

 	}
 	public User transferToAnotherCompany(SdsUserContext userContext, String userId, String anotherCompanyId) throws Exception
 	{
 		checkParamsForTransferingAnotherCompany(userContext, userId,anotherCompanyId);
 
		User user = loadUser(userContext, userId, allTokens());	
		synchronized(user){
			//will be good when the user loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Company company = loadCompany(userContext, anotherCompanyId, emptyOptions());		
			user.updateCompany(company);		
			user = saveUser(userContext, user, emptyOptions());
			
			return present(userContext,user, allTokens());
			
		}

 	}

	


	public CandidateCompany requestCandidateCompany(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateCompany result = new CandidateCompany();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Company> candidateList = companyDaoOf(userContext).requestCandidateCompanyForUser(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Company loadCompany(SdsUserContext userContext, String newCompanyId, Map<String,Object> options) throws Exception
 	{

 		return companyDaoOf(userContext).load(newCompanyId, options);
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(SdsUserContext userContext, String userId, int userVersion) throws Exception {
		//deleteInternal(userContext, userId, userVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String userId, int userVersion) throws Exception{

		userDaoOf(userContext).delete(userId, userVersion);
	}

	public User forgetByAll(SdsUserContext userContext, String userId, int userVersion) throws Exception {
		return forgetByAllInternal(userContext, userId, userVersion);
	}
	protected User forgetByAllInternal(SdsUserContext userContext,
			String userId, int userVersion) throws Exception{

		return userDaoOf(userContext).disconnectFromAll(userId, userVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new UserManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return userDaoOf(userContext).deleteAll();
	}


	//disconnect User with project in UserProject
	protected User breakWithUserProjectByProject(SdsUserContext userContext, String userId, String projectId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveUserProjectListWithProject(user, projectId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withUserProjectList().done());
				return user;
			}
	}
	//disconnect User with project in PageFlowSpec
	protected User breakWithPageFlowSpecByProject(SdsUserContext userContext, String userId, String projectId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemovePageFlowSpecListWithProject(user, projectId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withPageFlowSpecList().done());
				return user;
			}
	}
	//disconnect User with project in WorkFlowSpec
	protected User breakWithWorkFlowSpecByProject(SdsUserContext userContext, String userId, String projectId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveWorkFlowSpecListWithProject(user, projectId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withWorkFlowSpecList().done());
				return user;
			}
	}
	//disconnect User with project in ChangeRequestSpec
	protected User breakWithChangeRequestSpecByProject(SdsUserContext userContext, String userId, String projectId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemoveChangeRequestSpecListWithProject(user, projectId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withChangeRequestSpecList().done());
				return user;
			}
	}
	//disconnect User with project in PageContentSpec
	protected User breakWithPageContentSpecByProject(SdsUserContext userContext, String userId, String projectId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			User user = loadUser(userContext, userId, allTokens());

			synchronized(user){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				userDaoOf(userContext).planToRemovePageContentSpecListWithProject(user, projectId, this.emptyOptions());

				user = saveUser(userContext, user, tokens().withPageContentSpecList().done());
				return user;
			}
	}






	protected void checkParamsForAddingUserProject(SdsUserContext userContext, String userId, String projectId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkProjectIdOfUserProject(projectId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addUserProject(SdsUserContext userContext, String userId, String projectId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingUserProject(userContext,userId,projectId,tokensExpr);

		UserProject userProject = createUserProject(userContext,projectId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addUserProject( userProject );
			user = saveUser(userContext, user, tokens().withUserProjectList().done());
			
			userContext.getManagerGroup().getUserProjectManager().onNewInstanceCreated(userContext, userProject);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserProjectProperties(SdsUserContext userContext, String userId,String id,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfUserProject(id);


		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateUserProjectProperties(SdsUserContext userContext, String userId, String id, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserProjectProperties(userContext,userId,id,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserProjectListList()
				.searchUserProjectListWith(UserProject.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getUserProjectList().isEmpty()){
			throw new UserManagerException("UserProject is NOT FOUND with id: '"+id+"'");
		}

		UserProject item = userToUpdate.getUserProjectList().first();



		//checkParamsForAddingUserProject(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withUserProjectList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected UserProject createUserProject(SdsUserContext userContext, String projectId) throws Exception{

		UserProject userProject = new UserProject();
		
		
		Project  project = new Project();
		project.setId(projectId);		
		userProject.setProject(project);		
		userProject.setCreateTime(userContext.now());		
		userProject.setLastUpdateTime(userContext.now());
	
		
		return userProject;


	}

	protected UserProject createIndexedUserProject(String id, int version){

		UserProject userProject = new UserProject();
		userProject.setId(id);
		userProject.setVersion(version);
		return userProject;

	}

	protected void checkParamsForRemovingUserProjectList(SdsUserContext userContext, String userId,
			String userProjectIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String userProjectIdItem: userProjectIds){
			checkerOf(userContext).checkIdOfUserProject(userProjectIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeUserProjectList(SdsUserContext userContext, String userId,
			String userProjectIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingUserProjectList(userContext, userId,  userProjectIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveUserProjectList(user, userProjectIds, allTokens());
				user = saveUser(userContext, user, tokens().withUserProjectList().done());
				deleteRelationListInGraph(userContext, user.getUserProjectList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingUserProject(SdsUserContext userContext, String userId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject(userProjectVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeUserProject(SdsUserContext userContext, String userId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingUserProject(userContext,userId, userProjectId, userProjectVersion,tokensExpr);

		UserProject userProject = createIndexedUserProject(userProjectId, userProjectVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeUserProject( userProject );
			user = saveUser(userContext, user, tokens().withUserProjectList().done());
			deleteRelationInGraph(userContext, userProject);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingUserProject(SdsUserContext userContext, String userId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject(userProjectVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyUserProjectFrom(SdsUserContext userContext, String userId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingUserProject(userContext,userId, userProjectId, userProjectVersion,tokensExpr);

		UserProject userProject = createIndexedUserProject(userProjectId, userProjectVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			userProject.updateLastUpdateTime(userContext.now());

			user.copyUserProjectFrom( userProject );
			user = saveUser(userContext, user, tokens().withUserProjectList().done());
			
			userContext.getManagerGroup().getUserProjectManager().onNewInstanceCreated(userContext, (UserProject)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingUserProject(SdsUserContext userContext, String userId, String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject(userProjectVersion);
		

	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateUserProject(SdsUserContext userContext, String userId, String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingUserProject(userContext, userId, userProjectId, userProjectVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withUserProjectList().searchUserProjectListWith(UserProject.ID_PROPERTY, "eq", userProjectId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeUserProject( userProject );
			//make changes to AcceleraterAccount.
			UserProject userProjectIndex = createIndexedUserProject(userProjectId, userProjectVersion);

			UserProject userProject = user.findTheUserProject(userProjectIndex);
			if(userProject == null){
				throw new UserManagerException(userProject+" is NOT FOUND" );
			}

			userProject.changeProperty(property, newValueExpr);
			userProject.updateLastUpdateTime(userContext.now());
			user = saveUser(userContext, user, tokens().withUserProjectList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingPageFlowSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkSceneOfPageFlowSpec(scene);
		
		checkerOf(userContext).checkBriefOfPageFlowSpec(brief);
		
		checkerOf(userContext).checkProjectIdOfPageFlowSpec(projectId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addPageFlowSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingPageFlowSpec(userContext,userId,scene, brief, projectId,tokensExpr);

		PageFlowSpec pageFlowSpec = createPageFlowSpec(userContext,scene, brief, projectId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addPageFlowSpec( pageFlowSpec );
			user = saveUser(userContext, user, tokens().withPageFlowSpecList().done());
			
			userContext.getManagerGroup().getPageFlowSpecManager().onNewInstanceCreated(userContext, pageFlowSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPageFlowSpecProperties(SdsUserContext userContext, String userId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfPageFlowSpec(id);

		checkerOf(userContext).checkSceneOfPageFlowSpec( scene);
		checkerOf(userContext).checkBriefOfPageFlowSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updatePageFlowSpecProperties(SdsUserContext userContext, String userId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPageFlowSpecProperties(userContext,userId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPageFlowSpecListList()
				.searchPageFlowSpecListWith(PageFlowSpec.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getPageFlowSpecList().isEmpty()){
			throw new UserManagerException("PageFlowSpec is NOT FOUND with id: '"+id+"'");
		}

		PageFlowSpec item = userToUpdate.getPageFlowSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingPageFlowSpec(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withPageFlowSpecList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected PageFlowSpec createPageFlowSpec(SdsUserContext userContext, String scene, String brief, String projectId) throws Exception{

		PageFlowSpec pageFlowSpec = new PageFlowSpec();
		
		
		pageFlowSpec.setScene(scene);		
		pageFlowSpec.setBrief(brief);		
		Project  project = new Project();
		project.setId(projectId);		
		pageFlowSpec.setProject(project);
	
		
		return pageFlowSpec;


	}

	protected PageFlowSpec createIndexedPageFlowSpec(String id, int version){

		PageFlowSpec pageFlowSpec = new PageFlowSpec();
		pageFlowSpec.setId(id);
		pageFlowSpec.setVersion(version);
		return pageFlowSpec;

	}

	protected void checkParamsForRemovingPageFlowSpecList(SdsUserContext userContext, String userId,
			String pageFlowSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String pageFlowSpecIdItem: pageFlowSpecIds){
			checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removePageFlowSpecList(SdsUserContext userContext, String userId,
			String pageFlowSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingPageFlowSpecList(userContext, userId,  pageFlowSpecIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemovePageFlowSpecList(user, pageFlowSpecIds, allTokens());
				user = saveUser(userContext, user, tokens().withPageFlowSpecList().done());
				deleteRelationListInGraph(userContext, user.getPageFlowSpecList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingPageFlowSpec(SdsUserContext userContext, String userId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec(pageFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removePageFlowSpec(SdsUserContext userContext, String userId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingPageFlowSpec(userContext,userId, pageFlowSpecId, pageFlowSpecVersion,tokensExpr);

		PageFlowSpec pageFlowSpec = createIndexedPageFlowSpec(pageFlowSpecId, pageFlowSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removePageFlowSpec( pageFlowSpec );
			user = saveUser(userContext, user, tokens().withPageFlowSpecList().done());
			deleteRelationInGraph(userContext, pageFlowSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingPageFlowSpec(SdsUserContext userContext, String userId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec(pageFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyPageFlowSpecFrom(SdsUserContext userContext, String userId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingPageFlowSpec(userContext,userId, pageFlowSpecId, pageFlowSpecVersion,tokensExpr);

		PageFlowSpec pageFlowSpec = createIndexedPageFlowSpec(pageFlowSpecId, pageFlowSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyPageFlowSpecFrom( pageFlowSpec );
			user = saveUser(userContext, user, tokens().withPageFlowSpecList().done());
			
			userContext.getManagerGroup().getPageFlowSpecManager().onNewInstanceCreated(userContext, (PageFlowSpec)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingPageFlowSpec(SdsUserContext userContext, String userId, String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec(pageFlowSpecVersion);
		

		if(PageFlowSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfPageFlowSpec(parseString(newValueExpr));
		
		}
		
		if(PageFlowSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfPageFlowSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updatePageFlowSpec(SdsUserContext userContext, String userId, String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingPageFlowSpec(userContext, userId, pageFlowSpecId, pageFlowSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withPageFlowSpecList().searchPageFlowSpecListWith(PageFlowSpec.ID_PROPERTY, "eq", pageFlowSpecId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removePageFlowSpec( pageFlowSpec );
			//make changes to AcceleraterAccount.
			PageFlowSpec pageFlowSpecIndex = createIndexedPageFlowSpec(pageFlowSpecId, pageFlowSpecVersion);

			PageFlowSpec pageFlowSpec = user.findThePageFlowSpec(pageFlowSpecIndex);
			if(pageFlowSpec == null){
				throw new UserManagerException(pageFlowSpec+" is NOT FOUND" );
			}

			pageFlowSpec.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withPageFlowSpecList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingWorkFlowSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkSceneOfWorkFlowSpec(scene);
		
		checkerOf(userContext).checkBriefOfWorkFlowSpec(brief);
		
		checkerOf(userContext).checkProjectIdOfWorkFlowSpec(projectId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addWorkFlowSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingWorkFlowSpec(userContext,userId,scene, brief, projectId,tokensExpr);

		WorkFlowSpec workFlowSpec = createWorkFlowSpec(userContext,scene, brief, projectId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addWorkFlowSpec( workFlowSpec );
			user = saveUser(userContext, user, tokens().withWorkFlowSpecList().done());
			
			userContext.getManagerGroup().getWorkFlowSpecManager().onNewInstanceCreated(userContext, workFlowSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingWorkFlowSpecProperties(SdsUserContext userContext, String userId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(id);

		checkerOf(userContext).checkSceneOfWorkFlowSpec( scene);
		checkerOf(userContext).checkBriefOfWorkFlowSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateWorkFlowSpecProperties(SdsUserContext userContext, String userId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWorkFlowSpecProperties(userContext,userId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withWorkFlowSpecListList()
				.searchWorkFlowSpecListWith(WorkFlowSpec.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getWorkFlowSpecList().isEmpty()){
			throw new UserManagerException("WorkFlowSpec is NOT FOUND with id: '"+id+"'");
		}

		WorkFlowSpec item = userToUpdate.getWorkFlowSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingWorkFlowSpec(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withWorkFlowSpecList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected WorkFlowSpec createWorkFlowSpec(SdsUserContext userContext, String scene, String brief, String projectId) throws Exception{

		WorkFlowSpec workFlowSpec = new WorkFlowSpec();
		
		
		workFlowSpec.setScene(scene);		
		workFlowSpec.setBrief(brief);		
		Project  project = new Project();
		project.setId(projectId);		
		workFlowSpec.setProject(project);
	
		
		return workFlowSpec;


	}

	protected WorkFlowSpec createIndexedWorkFlowSpec(String id, int version){

		WorkFlowSpec workFlowSpec = new WorkFlowSpec();
		workFlowSpec.setId(id);
		workFlowSpec.setVersion(version);
		return workFlowSpec;

	}

	protected void checkParamsForRemovingWorkFlowSpecList(SdsUserContext userContext, String userId,
			String workFlowSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String workFlowSpecIdItem: workFlowSpecIds){
			checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeWorkFlowSpecList(SdsUserContext userContext, String userId,
			String workFlowSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingWorkFlowSpecList(userContext, userId,  workFlowSpecIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveWorkFlowSpecList(user, workFlowSpecIds, allTokens());
				user = saveUser(userContext, user, tokens().withWorkFlowSpecList().done());
				deleteRelationListInGraph(userContext, user.getWorkFlowSpecList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingWorkFlowSpec(SdsUserContext userContext, String userId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecId);
		checkerOf(userContext).checkVersionOfWorkFlowSpec(workFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeWorkFlowSpec(SdsUserContext userContext, String userId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingWorkFlowSpec(userContext,userId, workFlowSpecId, workFlowSpecVersion,tokensExpr);

		WorkFlowSpec workFlowSpec = createIndexedWorkFlowSpec(workFlowSpecId, workFlowSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeWorkFlowSpec( workFlowSpec );
			user = saveUser(userContext, user, tokens().withWorkFlowSpecList().done());
			deleteRelationInGraph(userContext, workFlowSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingWorkFlowSpec(SdsUserContext userContext, String userId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecId);
		checkerOf(userContext).checkVersionOfWorkFlowSpec(workFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyWorkFlowSpecFrom(SdsUserContext userContext, String userId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingWorkFlowSpec(userContext,userId, workFlowSpecId, workFlowSpecVersion,tokensExpr);

		WorkFlowSpec workFlowSpec = createIndexedWorkFlowSpec(workFlowSpecId, workFlowSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyWorkFlowSpecFrom( workFlowSpec );
			user = saveUser(userContext, user, tokens().withWorkFlowSpecList().done());
			
			userContext.getManagerGroup().getWorkFlowSpecManager().onNewInstanceCreated(userContext, (WorkFlowSpec)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingWorkFlowSpec(SdsUserContext userContext, String userId, String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecId);
		checkerOf(userContext).checkVersionOfWorkFlowSpec(workFlowSpecVersion);
		

		if(WorkFlowSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfWorkFlowSpec(parseString(newValueExpr));
		
		}
		
		if(WorkFlowSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfWorkFlowSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateWorkFlowSpec(SdsUserContext userContext, String userId, String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingWorkFlowSpec(userContext, userId, workFlowSpecId, workFlowSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withWorkFlowSpecList().searchWorkFlowSpecListWith(WorkFlowSpec.ID_PROPERTY, "eq", workFlowSpecId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeWorkFlowSpec( workFlowSpec );
			//make changes to AcceleraterAccount.
			WorkFlowSpec workFlowSpecIndex = createIndexedWorkFlowSpec(workFlowSpecId, workFlowSpecVersion);

			WorkFlowSpec workFlowSpec = user.findTheWorkFlowSpec(workFlowSpecIndex);
			if(workFlowSpec == null){
				throw new UserManagerException(workFlowSpec+" is NOT FOUND" );
			}

			workFlowSpec.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withWorkFlowSpecList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingChangeRequestSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkSceneOfChangeRequestSpec(scene);
		
		checkerOf(userContext).checkBriefOfChangeRequestSpec(brief);
		
		checkerOf(userContext).checkProjectIdOfChangeRequestSpec(projectId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addChangeRequestSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequestSpec(userContext,userId,scene, brief, projectId,tokensExpr);

		ChangeRequestSpec changeRequestSpec = createChangeRequestSpec(userContext,scene, brief, projectId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addChangeRequestSpec( changeRequestSpec );
			user = saveUser(userContext, user, tokens().withChangeRequestSpecList().done());
			
			userContext.getManagerGroup().getChangeRequestSpecManager().onNewInstanceCreated(userContext, changeRequestSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestSpecProperties(SdsUserContext userContext, String userId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(id);

		checkerOf(userContext).checkSceneOfChangeRequestSpec( scene);
		checkerOf(userContext).checkBriefOfChangeRequestSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updateChangeRequestSpecProperties(SdsUserContext userContext, String userId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestSpecProperties(userContext,userId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestSpecListList()
				.searchChangeRequestSpecListWith(ChangeRequestSpec.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getChangeRequestSpecList().isEmpty()){
			throw new UserManagerException("ChangeRequestSpec is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequestSpec item = userToUpdate.getChangeRequestSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingChangeRequestSpec(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withChangeRequestSpecList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequestSpec createChangeRequestSpec(SdsUserContext userContext, String scene, String brief, String projectId) throws Exception{

		ChangeRequestSpec changeRequestSpec = new ChangeRequestSpec();
		
		
		changeRequestSpec.setScene(scene);		
		changeRequestSpec.setBrief(brief);		
		Project  project = new Project();
		project.setId(projectId);		
		changeRequestSpec.setProject(project);
	
		
		return changeRequestSpec;


	}

	protected ChangeRequestSpec createIndexedChangeRequestSpec(String id, int version){

		ChangeRequestSpec changeRequestSpec = new ChangeRequestSpec();
		changeRequestSpec.setId(id);
		changeRequestSpec.setVersion(version);
		return changeRequestSpec;

	}

	protected void checkParamsForRemovingChangeRequestSpecList(SdsUserContext userContext, String userId,
			String changeRequestSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String changeRequestSpecIdItem: changeRequestSpecIds){
			checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeChangeRequestSpecList(SdsUserContext userContext, String userId,
			String changeRequestSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestSpecList(userContext, userId,  changeRequestSpecIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemoveChangeRequestSpecList(user, changeRequestSpecIds, allTokens());
				user = saveUser(userContext, user, tokens().withChangeRequestSpecList().done());
				deleteRelationListInGraph(userContext, user.getChangeRequestSpecList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequestSpec(SdsUserContext userContext, String userId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecId);
		checkerOf(userContext).checkVersionOfChangeRequestSpec(changeRequestSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removeChangeRequestSpec(SdsUserContext userContext, String userId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequestSpec(userContext,userId, changeRequestSpecId, changeRequestSpecVersion,tokensExpr);

		ChangeRequestSpec changeRequestSpec = createIndexedChangeRequestSpec(changeRequestSpecId, changeRequestSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removeChangeRequestSpec( changeRequestSpec );
			user = saveUser(userContext, user, tokens().withChangeRequestSpecList().done());
			deleteRelationInGraph(userContext, changeRequestSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequestSpec(SdsUserContext userContext, String userId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecId);
		checkerOf(userContext).checkVersionOfChangeRequestSpec(changeRequestSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyChangeRequestSpecFrom(SdsUserContext userContext, String userId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequestSpec(userContext,userId, changeRequestSpecId, changeRequestSpecVersion,tokensExpr);

		ChangeRequestSpec changeRequestSpec = createIndexedChangeRequestSpec(changeRequestSpecId, changeRequestSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyChangeRequestSpecFrom( changeRequestSpec );
			user = saveUser(userContext, user, tokens().withChangeRequestSpecList().done());
			
			userContext.getManagerGroup().getChangeRequestSpecManager().onNewInstanceCreated(userContext, (ChangeRequestSpec)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequestSpec(SdsUserContext userContext, String userId, String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecId);
		checkerOf(userContext).checkVersionOfChangeRequestSpec(changeRequestSpecVersion);
		

		if(ChangeRequestSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfChangeRequestSpec(parseString(newValueExpr));
		
		}
		
		if(ChangeRequestSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfChangeRequestSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updateChangeRequestSpec(SdsUserContext userContext, String userId, String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequestSpec(userContext, userId, changeRequestSpecId, changeRequestSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestSpecList().searchChangeRequestSpecListWith(ChangeRequestSpec.ID_PROPERTY, "eq", changeRequestSpecId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removeChangeRequestSpec( changeRequestSpec );
			//make changes to AcceleraterAccount.
			ChangeRequestSpec changeRequestSpecIndex = createIndexedChangeRequestSpec(changeRequestSpecId, changeRequestSpecVersion);

			ChangeRequestSpec changeRequestSpec = user.findTheChangeRequestSpec(changeRequestSpecIndex);
			if(changeRequestSpec == null){
				throw new UserManagerException(changeRequestSpec+" is NOT FOUND" );
			}

			changeRequestSpec.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withChangeRequestSpecList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingPageContentSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfUser(userId);

		
		checkerOf(userContext).checkSceneOfPageContentSpec(scene);
		
		checkerOf(userContext).checkBriefOfPageContentSpec(brief);
		
		checkerOf(userContext).checkProjectIdOfPageContentSpec(projectId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);


	}
	public  User addPageContentSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingPageContentSpec(userContext,userId,scene, brief, projectId,tokensExpr);

		PageContentSpec pageContentSpec = createPageContentSpec(userContext,scene, brief, projectId);

		User user = loadUser(userContext, userId, emptyOptions());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.addPageContentSpec( pageContentSpec );
			user = saveUser(userContext, user, tokens().withPageContentSpecList().done());
			
			userContext.getManagerGroup().getPageContentSpecManager().onNewInstanceCreated(userContext, pageContentSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPageContentSpecProperties(SdsUserContext userContext, String userId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfPageContentSpec(id);

		checkerOf(userContext).checkSceneOfPageContentSpec( scene);
		checkerOf(userContext).checkBriefOfPageContentSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User updatePageContentSpecProperties(SdsUserContext userContext, String userId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPageContentSpecProperties(userContext,userId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPageContentSpecListList()
				.searchPageContentSpecListWith(PageContentSpec.ID_PROPERTY, "is", id).done();

		User userToUpdate = loadUser(userContext, userId, options);

		if(userToUpdate.getPageContentSpecList().isEmpty()){
			throw new UserManagerException("PageContentSpec is NOT FOUND with id: '"+id+"'");
		}

		PageContentSpec item = userToUpdate.getPageContentSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingPageContentSpec(userContext,userId,name, code, used,tokensExpr);
		User user = saveUser(userContext, userToUpdate, tokens().withPageContentSpecList().done());
		synchronized(user){
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}
	}


	protected PageContentSpec createPageContentSpec(SdsUserContext userContext, String scene, String brief, String projectId) throws Exception{

		PageContentSpec pageContentSpec = new PageContentSpec();
		
		
		pageContentSpec.setScene(scene);		
		pageContentSpec.setBrief(brief);		
		Project  project = new Project();
		project.setId(projectId);		
		pageContentSpec.setProject(project);
	
		
		return pageContentSpec;


	}

	protected PageContentSpec createIndexedPageContentSpec(String id, int version){

		PageContentSpec pageContentSpec = new PageContentSpec();
		pageContentSpec.setId(id);
		pageContentSpec.setVersion(version);
		return pageContentSpec;

	}

	protected void checkParamsForRemovingPageContentSpecList(SdsUserContext userContext, String userId,
			String pageContentSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfUser(userId);
		for(String pageContentSpecIdItem: pageContentSpecIds){
			checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removePageContentSpecList(SdsUserContext userContext, String userId,
			String pageContentSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingPageContentSpecList(userContext, userId,  pageContentSpecIds, tokensExpr);


			User user = loadUser(userContext, userId, allTokens());
			synchronized(user){
				//Will be good when the user loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userDaoOf(userContext).planToRemovePageContentSpecList(user, pageContentSpecIds, allTokens());
				user = saveUser(userContext, user, tokens().withPageContentSpecList().done());
				deleteRelationListInGraph(userContext, user.getPageContentSpecList());
				return present(userContext,user, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingPageContentSpec(SdsUserContext userContext, String userId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecId);
		checkerOf(userContext).checkVersionOfPageContentSpec(pageContentSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User removePageContentSpec(SdsUserContext userContext, String userId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingPageContentSpec(userContext,userId, pageContentSpecId, pageContentSpecVersion,tokensExpr);

		PageContentSpec pageContentSpec = createIndexedPageContentSpec(pageContentSpecId, pageContentSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			user.removePageContentSpec( pageContentSpec );
			user = saveUser(userContext, user, tokens().withPageContentSpecList().done());
			deleteRelationInGraph(userContext, pageContentSpec);
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingPageContentSpec(SdsUserContext userContext, String userId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfUser( userId);
		checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecId);
		checkerOf(userContext).checkVersionOfPageContentSpec(pageContentSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}
	public  User copyPageContentSpecFrom(SdsUserContext userContext, String userId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingPageContentSpec(userContext,userId, pageContentSpecId, pageContentSpecVersion,tokensExpr);

		PageContentSpec pageContentSpec = createIndexedPageContentSpec(pageContentSpecId, pageContentSpecVersion);
		User user = loadUser(userContext, userId, allTokens());
		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			user.copyPageContentSpecFrom( pageContentSpec );
			user = saveUser(userContext, user, tokens().withPageContentSpecList().done());
			
			userContext.getManagerGroup().getPageContentSpecManager().onNewInstanceCreated(userContext, (PageContentSpec)user.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingPageContentSpec(SdsUserContext userContext, String userId, String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfUser(userId);
		checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecId);
		checkerOf(userContext).checkVersionOfPageContentSpec(pageContentSpecVersion);
		

		if(PageContentSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfPageContentSpec(parseString(newValueExpr));
		
		}
		
		if(PageContentSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfPageContentSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(UserManagerException.class);

	}

	public  User updatePageContentSpec(SdsUserContext userContext, String userId, String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingPageContentSpec(userContext, userId, pageContentSpecId, pageContentSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withPageContentSpecList().searchPageContentSpecListWith(PageContentSpec.ID_PROPERTY, "eq", pageContentSpecId).done();



		User user = loadUser(userContext, userId, loadTokens);

		synchronized(user){
			//Will be good when the user loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//user.removePageContentSpec( pageContentSpec );
			//make changes to AcceleraterAccount.
			PageContentSpec pageContentSpecIndex = createIndexedPageContentSpec(pageContentSpecId, pageContentSpecVersion);

			PageContentSpec pageContentSpec = user.findThePageContentSpec(pageContentSpecIndex);
			if(pageContentSpec == null){
				throw new UserManagerException(pageContentSpec+" is NOT FOUND" );
			}

			pageContentSpec.changeProperty(property, newValueExpr);
			
			user = saveUser(userContext, user, tokens().withPageContentSpecList().done());
			return present(userContext,user, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(SdsUserContext userContext, User newCreated) throws Exception{
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
		//   User newUser = this.createUser(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newUser
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, User.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<User> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<Company> companyList = SdsBaseUtils.collectReferencedObjectWithType(userContext, list, Company.class);
		userContext.getDAOGroup().enhanceList(companyList, Company.class);


    }
	
	public Object listByCompany(SdsUserContext userContext,String companyId) throws Exception {
		return listPageByCompany(userContext, companyId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByCompany(SdsUserContext userContext,String companyId, int start, int count) throws Exception {
		SmartList<User> list = userDaoOf(userContext).findUserByCompany(companyId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(User.class);
		page.setContainerObject(Company.withId(companyId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("用户列表");
		page.setRequestName("listByCompany");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByCompany/%s/",  getBeanName(), companyId)));

		page.assemblerContent(userContext, "listByCompany");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(SdsUserContext userContext, String userId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getUserDetailScope().clone();
		User merchantObj = (User) this.view(userContext, userId);
    String merchantObjId = userId;
    String linkToUrl =	"userManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "用户"+"详情";
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
				MapUtil.put("id", "3-joinTime")
				    .put("fieldName", "joinTime")
				    .put("label", "加入时间")
				    .put("type", "date")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("joinTime", merchantObj.getJoinTime());

		propList.add(
				MapUtil.put("id", "4-company")
				    .put("fieldName", "company")
				    .put("label", "公司")
				    .put("type", "auto")
				    .put("linkToUrl", "companyManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("company", merchantObj.getCompany());

		propList.add(
				MapUtil.put("id", "5-title")
				    .put("fieldName", "title")
				    .put("label", "标题")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("title", merchantObj.getTitle());

		//处理 sectionList

		//处理Section：userProjectListSection
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

		//处理Section：pageFlowSpecListSection
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

		//处理Section：workFlowSpecListSection
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

		//处理Section：changeRequestSpecListSection
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

		//处理Section：pageContentSpecListSection
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


