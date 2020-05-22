
package com.cla.sds.project;

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






public class ProjectManagerImpl extends CustomSdsCheckerManager implements ProjectManager, BusinessHandler{

	// Only some of ods have such function
	
	// To test 
	public BlobObject exportExcelFromList(SdsUserContext userContext, String id, String listName) throws Exception {
		
		Map<String,Object> tokens = ProjectTokens.start().withTokenFromListName(listName).done();
		Project  project = (Project) this.loadProject(userContext, id, tokens);
		//to enrich reference object to let it show display name
		List<BaseEntity> entityListToNaming = project.collectRefercencesFromLists();
		projectDaoOf(userContext).alias(entityListToNaming);
		
		return exportListToExcel(userContext, project, listName);
		
	}
	@Override
	public BaseGridViewGenerator gridViewGenerator() {
		return new ProjectGridViewGenerator();
	}
	
	



  


	private static final String SERVICE_TYPE = "Project";
	@Override
	public ProjectDAO daoOf(SdsUserContext userContext) {
		return projectDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}


	protected void throwExceptionWithMessage(String value) throws ProjectManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new ProjectManagerException(message);

	}



 	protected Project saveProject(SdsUserContext userContext, Project project, String [] tokensExpr) throws Exception{	
 		//return getProjectDAO().save(project, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveProject(userContext, project, tokens);
 	}
 	
 	protected Project saveProjectDetail(SdsUserContext userContext, Project project) throws Exception{	

 		
 		return saveProject(userContext, project, allTokens());
 	}
 	
 	public Project loadProject(SdsUserContext userContext, String projectId, String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).throwExceptionIfHasErrors( ProjectManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Project project = loadProject( userContext, projectId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,project, tokens);
 	}
 	
 	
 	 public Project searchProject(SdsUserContext userContext, String projectId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).throwExceptionIfHasErrors( ProjectManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Project project = loadProject( userContext, projectId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,project, tokens);
 	}
 	
 	

 	protected Project present(SdsUserContext userContext, Project project, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,project,tokens);
		
		
		Project  projectToPresent = projectDaoOf(userContext).present(project, tokens);
		
		List<BaseEntity> entityListToNaming = projectToPresent.collectRefercencesFromLists();
		projectDaoOf(userContext).alias(entityListToNaming);
		
		return  projectToPresent;
		
		
	}
 
 	
 	
 	public Project loadProjectDetail(SdsUserContext userContext, String projectId) throws Exception{	
 		Project project = loadProject( userContext, projectId, allTokens());
 		return present(userContext,project, allTokens());
		
 	}
 	
 	public Object view(SdsUserContext userContext, String projectId) throws Exception{	
 		Project project = loadProject( userContext, projectId, viewTokens());
 		return present(userContext,project, allTokens());
		
 	}
 	protected Project saveProject(SdsUserContext userContext, Project project, Map<String,Object>tokens) throws Exception{	
 		return projectDaoOf(userContext).save(project, tokens);
 	}
 	protected Project loadProject(SdsUserContext userContext, String projectId, Map<String,Object>tokens) throws Exception{	
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).throwExceptionIfHasErrors( ProjectManagerException.class);

 
 		return projectDaoOf(userContext).load(projectId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Project project, Map<String, Object> tokens){
		super.addActions(userContext, project, tokens);
		
		addAction(userContext, project, tokens,"@create","createProject","createProject/","main","primary");
		addAction(userContext, project, tokens,"@update","updateProject","updateProject/"+project.getId()+"/","main","primary");
		addAction(userContext, project, tokens,"@copy","cloneProject","cloneProject/"+project.getId()+"/","main","primary");
		
		addAction(userContext, project, tokens,"project.transfer_to_company","transferToAnotherCompany","transferToAnotherCompany/"+project.getId()+"/","main","primary");
		addAction(userContext, project, tokens,"project.addUserProject","addUserProject","addUserProject/"+project.getId()+"/","userProjectList","primary");
		addAction(userContext, project, tokens,"project.removeUserProject","removeUserProject","removeUserProject/"+project.getId()+"/","userProjectList","primary");
		addAction(userContext, project, tokens,"project.updateUserProject","updateUserProject","updateUserProject/"+project.getId()+"/","userProjectList","primary");
		addAction(userContext, project, tokens,"project.copyUserProjectFrom","copyUserProjectFrom","copyUserProjectFrom/"+project.getId()+"/","userProjectList","primary");
		addAction(userContext, project, tokens,"project.addPageFlowSpec","addPageFlowSpec","addPageFlowSpec/"+project.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.removePageFlowSpec","removePageFlowSpec","removePageFlowSpec/"+project.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.updatePageFlowSpec","updatePageFlowSpec","updatePageFlowSpec/"+project.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.copyPageFlowSpecFrom","copyPageFlowSpecFrom","copyPageFlowSpecFrom/"+project.getId()+"/","pageFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.addWorkFlowSpec","addWorkFlowSpec","addWorkFlowSpec/"+project.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.removeWorkFlowSpec","removeWorkFlowSpec","removeWorkFlowSpec/"+project.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.updateWorkFlowSpec","updateWorkFlowSpec","updateWorkFlowSpec/"+project.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.copyWorkFlowSpecFrom","copyWorkFlowSpecFrom","copyWorkFlowSpecFrom/"+project.getId()+"/","workFlowSpecList","primary");
		addAction(userContext, project, tokens,"project.addChangeRequestSpec","addChangeRequestSpec","addChangeRequestSpec/"+project.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, project, tokens,"project.removeChangeRequestSpec","removeChangeRequestSpec","removeChangeRequestSpec/"+project.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, project, tokens,"project.updateChangeRequestSpec","updateChangeRequestSpec","updateChangeRequestSpec/"+project.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, project, tokens,"project.copyChangeRequestSpecFrom","copyChangeRequestSpecFrom","copyChangeRequestSpecFrom/"+project.getId()+"/","changeRequestSpecList","primary");
		addAction(userContext, project, tokens,"project.addPageContentSpec","addPageContentSpec","addPageContentSpec/"+project.getId()+"/","pageContentSpecList","primary");
		addAction(userContext, project, tokens,"project.removePageContentSpec","removePageContentSpec","removePageContentSpec/"+project.getId()+"/","pageContentSpecList","primary");
		addAction(userContext, project, tokens,"project.updatePageContentSpec","updatePageContentSpec","updatePageContentSpec/"+project.getId()+"/","pageContentSpecList","primary");
		addAction(userContext, project, tokens,"project.copyPageContentSpecFrom","copyPageContentSpecFrom","copyPageContentSpecFrom/"+project.getId()+"/","pageContentSpecList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(SdsUserContext userContext, Project project, Map<String, Object> tokens){
	
 	
 	
 
 	
 	

	public Project createProject(SdsUserContext userContext, String name,String companyId) throws Exception
	//public Project createProject(SdsUserContext userContext,String name, String companyId) throws Exception
	{

		

		

		checkerOf(userContext).checkNameOfProject(name);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


		Project project=createNewProject();	

		project.setName(name);
			
		Company company = loadCompany(userContext, companyId,emptyOptions());
		project.setCompany(company);
		
		

		project = saveProject(userContext, project, emptyOptions());
		
		onNewInstanceCreated(userContext, project);
		return project;


	}
	protected Project createNewProject()
	{

		return new Project();
	}

	protected void checkParamsForUpdatingProject(SdsUserContext userContext,String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkVersionOfProject( projectVersion);
		

		if(Project.NAME_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkNameOfProject(parseString(newValueExpr));
		
			
		}		

		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


	}



	public Project clone(SdsUserContext userContext, String fromProjectId) throws Exception{

		return projectDaoOf(userContext).clone(fromProjectId, this.allTokens());
	}

	public Project internalSaveProject(SdsUserContext userContext, Project project) throws Exception
	{
		return internalSaveProject(userContext, project, allTokens());

	}
	public Project internalSaveProject(SdsUserContext userContext, Project project, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingProject(userContext, projectId, projectVersion, property, newValueExpr, tokensExpr);


		synchronized(project){
			//will be good when the project loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Project.
			if (project.isChanged()){
			
			}
			project = saveProject(userContext, project, options);
			return project;

		}

	}

	public Project updateProject(SdsUserContext userContext,String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingProject(userContext, projectId, projectVersion, property, newValueExpr, tokensExpr);



		Project project = loadProject(userContext, projectId, allTokens());
		if(project.getVersion() != projectVersion){
			String message = "The target version("+project.getVersion()+") is not equals to version("+projectVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(project){
			//will be good when the project loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Project.
			
			project.changeProperty(property, newValueExpr);
			project = saveProject(userContext, project, tokens().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
			//return saveProject(userContext, project, tokens().done());
		}

	}

	public Project updateProjectProperty(SdsUserContext userContext,String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingProject(userContext, projectId, projectVersion, property, newValueExpr, tokensExpr);

		Project project = loadProject(userContext, projectId, allTokens());
		if(project.getVersion() != projectVersion){
			String message = "The target version("+project.getVersion()+") is not equals to version("+projectVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(project){
			//will be good when the project loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Project.

			project.changeProperty(property, newValueExpr);
			
			project = saveProject(userContext, project, tokens().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
			//return saveProject(userContext, project, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected ProjectTokens tokens(){
		return ProjectTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ProjectTokens.all();
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
		return ProjectTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherCompany(SdsUserContext userContext, String projectId, String anotherCompanyId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfProject(projectId);
 		checkerOf(userContext).checkIdOfCompany(anotherCompanyId);//check for optional reference
 		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

 	}
 	public Project transferToAnotherCompany(SdsUserContext userContext, String projectId, String anotherCompanyId) throws Exception
 	{
 		checkParamsForTransferingAnotherCompany(userContext, projectId,anotherCompanyId);
 
		Project project = loadProject(userContext, projectId, allTokens());	
		synchronized(project){
			//will be good when the project loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Company company = loadCompany(userContext, anotherCompanyId, emptyOptions());		
			project.updateCompany(company);		
			project = saveProject(userContext, project, emptyOptions());
			
			return present(userContext,project, allTokens());
			
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
		SmartList<Company> candidateList = companyDaoOf(userContext).requestCandidateCompanyForProject(userContext,ownerClass, id, filterKey, pageNo, pageSize);
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

	public void delete(SdsUserContext userContext, String projectId, int projectVersion) throws Exception {
		//deleteInternal(userContext, projectId, projectVersion);
	}
	protected void deleteInternal(SdsUserContext userContext,
			String projectId, int projectVersion) throws Exception{

		projectDaoOf(userContext).delete(projectId, projectVersion);
	}

	public Project forgetByAll(SdsUserContext userContext, String projectId, int projectVersion) throws Exception {
		return forgetByAllInternal(userContext, projectId, projectVersion);
	}
	protected Project forgetByAllInternal(SdsUserContext userContext,
			String projectId, int projectVersion) throws Exception{

		return projectDaoOf(userContext).disconnectFromAll(projectId, projectVersion);
	}




	public int deleteAll(SdsUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ProjectManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(SdsUserContext userContext) throws Exception{
		return projectDaoOf(userContext).deleteAll();
	}


	//disconnect Project with user in UserProject
	protected Project breakWithUserProjectByUser(SdsUserContext userContext, String projectId, String userId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Project project = loadProject(userContext, projectId, allTokens());

			synchronized(project){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				projectDaoOf(userContext).planToRemoveUserProjectListWithUser(project, userId, this.emptyOptions());

				project = saveProject(userContext, project, tokens().withUserProjectList().done());
				return project;
			}
	}
	//disconnect Project with owner in PageFlowSpec
	protected Project breakWithPageFlowSpecByOwner(SdsUserContext userContext, String projectId, String ownerId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Project project = loadProject(userContext, projectId, allTokens());

			synchronized(project){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				projectDaoOf(userContext).planToRemovePageFlowSpecListWithOwner(project, ownerId, this.emptyOptions());

				project = saveProject(userContext, project, tokens().withPageFlowSpecList().done());
				return project;
			}
	}
	//disconnect Project with owner in WorkFlowSpec
	protected Project breakWithWorkFlowSpecByOwner(SdsUserContext userContext, String projectId, String ownerId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Project project = loadProject(userContext, projectId, allTokens());

			synchronized(project){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				projectDaoOf(userContext).planToRemoveWorkFlowSpecListWithOwner(project, ownerId, this.emptyOptions());

				project = saveProject(userContext, project, tokens().withWorkFlowSpecList().done());
				return project;
			}
	}
	//disconnect Project with owner in ChangeRequestSpec
	protected Project breakWithChangeRequestSpecByOwner(SdsUserContext userContext, String projectId, String ownerId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Project project = loadProject(userContext, projectId, allTokens());

			synchronized(project){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				projectDaoOf(userContext).planToRemoveChangeRequestSpecListWithOwner(project, ownerId, this.emptyOptions());

				project = saveProject(userContext, project, tokens().withChangeRequestSpecList().done());
				return project;
			}
	}
	//disconnect Project with owner in PageContentSpec
	protected Project breakWithPageContentSpecByOwner(SdsUserContext userContext, String projectId, String ownerId,  String [] tokensExpr)
		 throws Exception{

			//TODO add check code here

			Project project = loadProject(userContext, projectId, allTokens());

			synchronized(project){
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation

				projectDaoOf(userContext).planToRemovePageContentSpecListWithOwner(project, ownerId, this.emptyOptions());

				project = saveProject(userContext, project, tokens().withPageContentSpecList().done());
				return project;
			}
	}






	protected void checkParamsForAddingUserProject(SdsUserContext userContext, String projectId, String userId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProject(projectId);

		
		checkerOf(userContext).checkUserIdOfUserProject(userId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


	}
	public  Project addUserProject(SdsUserContext userContext, String projectId, String userId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingUserProject(userContext,projectId,userId,tokensExpr);

		UserProject userProject = createUserProject(userContext,userId);

		Project project = loadProject(userContext, projectId, emptyOptions());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.addUserProject( userProject );
			project = saveProject(userContext, project, tokens().withUserProjectList().done());
			
			userContext.getManagerGroup().getUserProjectManager().onNewInstanceCreated(userContext, userProject);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingUserProjectProperties(SdsUserContext userContext, String projectId,String id,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfUserProject(id);


		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project updateUserProjectProperties(SdsUserContext userContext, String projectId, String id, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingUserProjectProperties(userContext,projectId,id,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withUserProjectListList()
				.searchUserProjectListWith(UserProject.ID_PROPERTY, "is", id).done();

		Project projectToUpdate = loadProject(userContext, projectId, options);

		if(projectToUpdate.getUserProjectList().isEmpty()){
			throw new ProjectManagerException("UserProject is NOT FOUND with id: '"+id+"'");
		}

		UserProject item = projectToUpdate.getUserProjectList().first();



		//checkParamsForAddingUserProject(userContext,projectId,name, code, used,tokensExpr);
		Project project = saveProject(userContext, projectToUpdate, tokens().withUserProjectList().done());
		synchronized(project){
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}


	protected UserProject createUserProject(SdsUserContext userContext, String userId) throws Exception{

		UserProject userProject = new UserProject();
		
		
		User  user = new User();
		user.setId(userId);		
		userProject.setUser(user);		
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

	protected void checkParamsForRemovingUserProjectList(SdsUserContext userContext, String projectId,
			String userProjectIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		for(String userProjectIdItem: userProjectIds){
			checkerOf(userContext).checkIdOfUserProject(userProjectIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removeUserProjectList(SdsUserContext userContext, String projectId,
			String userProjectIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingUserProjectList(userContext, projectId,  userProjectIds, tokensExpr);


			Project project = loadProject(userContext, projectId, allTokens());
			synchronized(project){
				//Will be good when the project loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				projectDaoOf(userContext).planToRemoveUserProjectList(project, userProjectIds, allTokens());
				project = saveProject(userContext, project, tokens().withUserProjectList().done());
				deleteRelationListInGraph(userContext, project.getUserProjectList());
				return present(userContext,project, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingUserProject(SdsUserContext userContext, String projectId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject(userProjectVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removeUserProject(SdsUserContext userContext, String projectId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingUserProject(userContext,projectId, userProjectId, userProjectVersion,tokensExpr);

		UserProject userProject = createIndexedUserProject(userProjectId, userProjectVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.removeUserProject( userProject );
			project = saveProject(userContext, project, tokens().withUserProjectList().done());
			deleteRelationInGraph(userContext, userProject);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingUserProject(SdsUserContext userContext, String projectId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject(userProjectVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project copyUserProjectFrom(SdsUserContext userContext, String projectId,
		String userProjectId, int userProjectVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingUserProject(userContext,projectId, userProjectId, userProjectVersion,tokensExpr);

		UserProject userProject = createIndexedUserProject(userProjectId, userProjectVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			userProject.updateLastUpdateTime(userContext.now());

			project.copyUserProjectFrom( userProject );
			project = saveProject(userContext, project, tokens().withUserProjectList().done());
			
			userContext.getManagerGroup().getUserProjectManager().onNewInstanceCreated(userContext, (UserProject)project.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingUserProject(SdsUserContext userContext, String projectId, String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfUserProject(userProjectId);
		checkerOf(userContext).checkVersionOfUserProject(userProjectVersion);
		

	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}

	public  Project updateUserProject(SdsUserContext userContext, String projectId, String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingUserProject(userContext, projectId, userProjectId, userProjectVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withUserProjectList().searchUserProjectListWith(UserProject.ID_PROPERTY, "eq", userProjectId).done();



		Project project = loadProject(userContext, projectId, loadTokens);

		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//project.removeUserProject( userProject );
			//make changes to AcceleraterAccount.
			UserProject userProjectIndex = createIndexedUserProject(userProjectId, userProjectVersion);

			UserProject userProject = project.findTheUserProject(userProjectIndex);
			if(userProject == null){
				throw new ProjectManagerException(userProject+" is NOT FOUND" );
			}

			userProject.changeProperty(property, newValueExpr);
			userProject.updateLastUpdateTime(userContext.now());
			project = saveProject(userContext, project, tokens().withUserProjectList().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingPageFlowSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProject(projectId);

		
		checkerOf(userContext).checkSceneOfPageFlowSpec(scene);
		
		checkerOf(userContext).checkBriefOfPageFlowSpec(brief);
		
		checkerOf(userContext).checkOwnerIdOfPageFlowSpec(ownerId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


	}
	public  Project addPageFlowSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingPageFlowSpec(userContext,projectId,scene, brief, ownerId,tokensExpr);

		PageFlowSpec pageFlowSpec = createPageFlowSpec(userContext,scene, brief, ownerId);

		Project project = loadProject(userContext, projectId, emptyOptions());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.addPageFlowSpec( pageFlowSpec );
			project = saveProject(userContext, project, tokens().withPageFlowSpecList().done());
			
			userContext.getManagerGroup().getPageFlowSpecManager().onNewInstanceCreated(userContext, pageFlowSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPageFlowSpecProperties(SdsUserContext userContext, String projectId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfPageFlowSpec(id);

		checkerOf(userContext).checkSceneOfPageFlowSpec( scene);
		checkerOf(userContext).checkBriefOfPageFlowSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project updatePageFlowSpecProperties(SdsUserContext userContext, String projectId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPageFlowSpecProperties(userContext,projectId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPageFlowSpecListList()
				.searchPageFlowSpecListWith(PageFlowSpec.ID_PROPERTY, "is", id).done();

		Project projectToUpdate = loadProject(userContext, projectId, options);

		if(projectToUpdate.getPageFlowSpecList().isEmpty()){
			throw new ProjectManagerException("PageFlowSpec is NOT FOUND with id: '"+id+"'");
		}

		PageFlowSpec item = projectToUpdate.getPageFlowSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingPageFlowSpec(userContext,projectId,name, code, used,tokensExpr);
		Project project = saveProject(userContext, projectToUpdate, tokens().withPageFlowSpecList().done());
		synchronized(project){
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}


	protected PageFlowSpec createPageFlowSpec(SdsUserContext userContext, String scene, String brief, String ownerId) throws Exception{

		PageFlowSpec pageFlowSpec = new PageFlowSpec();
		
		
		pageFlowSpec.setScene(scene);		
		pageFlowSpec.setBrief(brief);		
		User  owner = new User();
		owner.setId(ownerId);		
		pageFlowSpec.setOwner(owner);
	
		
		return pageFlowSpec;


	}

	protected PageFlowSpec createIndexedPageFlowSpec(String id, int version){

		PageFlowSpec pageFlowSpec = new PageFlowSpec();
		pageFlowSpec.setId(id);
		pageFlowSpec.setVersion(version);
		return pageFlowSpec;

	}

	protected void checkParamsForRemovingPageFlowSpecList(SdsUserContext userContext, String projectId,
			String pageFlowSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		for(String pageFlowSpecIdItem: pageFlowSpecIds){
			checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removePageFlowSpecList(SdsUserContext userContext, String projectId,
			String pageFlowSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingPageFlowSpecList(userContext, projectId,  pageFlowSpecIds, tokensExpr);


			Project project = loadProject(userContext, projectId, allTokens());
			synchronized(project){
				//Will be good when the project loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				projectDaoOf(userContext).planToRemovePageFlowSpecList(project, pageFlowSpecIds, allTokens());
				project = saveProject(userContext, project, tokens().withPageFlowSpecList().done());
				deleteRelationListInGraph(userContext, project.getPageFlowSpecList());
				return present(userContext,project, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingPageFlowSpec(SdsUserContext userContext, String projectId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec(pageFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removePageFlowSpec(SdsUserContext userContext, String projectId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingPageFlowSpec(userContext,projectId, pageFlowSpecId, pageFlowSpecVersion,tokensExpr);

		PageFlowSpec pageFlowSpec = createIndexedPageFlowSpec(pageFlowSpecId, pageFlowSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.removePageFlowSpec( pageFlowSpec );
			project = saveProject(userContext, project, tokens().withPageFlowSpecList().done());
			deleteRelationInGraph(userContext, pageFlowSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingPageFlowSpec(SdsUserContext userContext, String projectId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec(pageFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project copyPageFlowSpecFrom(SdsUserContext userContext, String projectId,
		String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingPageFlowSpec(userContext,projectId, pageFlowSpecId, pageFlowSpecVersion,tokensExpr);

		PageFlowSpec pageFlowSpec = createIndexedPageFlowSpec(pageFlowSpecId, pageFlowSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			project.copyPageFlowSpecFrom( pageFlowSpec );
			project = saveProject(userContext, project, tokens().withPageFlowSpecList().done());
			
			userContext.getManagerGroup().getPageFlowSpecManager().onNewInstanceCreated(userContext, (PageFlowSpec)project.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingPageFlowSpec(SdsUserContext userContext, String projectId, String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfPageFlowSpec(pageFlowSpecId);
		checkerOf(userContext).checkVersionOfPageFlowSpec(pageFlowSpecVersion);
		

		if(PageFlowSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfPageFlowSpec(parseString(newValueExpr));
		
		}
		
		if(PageFlowSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfPageFlowSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}

	public  Project updatePageFlowSpec(SdsUserContext userContext, String projectId, String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingPageFlowSpec(userContext, projectId, pageFlowSpecId, pageFlowSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withPageFlowSpecList().searchPageFlowSpecListWith(PageFlowSpec.ID_PROPERTY, "eq", pageFlowSpecId).done();



		Project project = loadProject(userContext, projectId, loadTokens);

		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//project.removePageFlowSpec( pageFlowSpec );
			//make changes to AcceleraterAccount.
			PageFlowSpec pageFlowSpecIndex = createIndexedPageFlowSpec(pageFlowSpecId, pageFlowSpecVersion);

			PageFlowSpec pageFlowSpec = project.findThePageFlowSpec(pageFlowSpecIndex);
			if(pageFlowSpec == null){
				throw new ProjectManagerException(pageFlowSpec+" is NOT FOUND" );
			}

			pageFlowSpec.changeProperty(property, newValueExpr);
			
			project = saveProject(userContext, project, tokens().withPageFlowSpecList().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingWorkFlowSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProject(projectId);

		
		checkerOf(userContext).checkSceneOfWorkFlowSpec(scene);
		
		checkerOf(userContext).checkBriefOfWorkFlowSpec(brief);
		
		checkerOf(userContext).checkOwnerIdOfWorkFlowSpec(ownerId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


	}
	public  Project addWorkFlowSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingWorkFlowSpec(userContext,projectId,scene, brief, ownerId,tokensExpr);

		WorkFlowSpec workFlowSpec = createWorkFlowSpec(userContext,scene, brief, ownerId);

		Project project = loadProject(userContext, projectId, emptyOptions());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.addWorkFlowSpec( workFlowSpec );
			project = saveProject(userContext, project, tokens().withWorkFlowSpecList().done());
			
			userContext.getManagerGroup().getWorkFlowSpecManager().onNewInstanceCreated(userContext, workFlowSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingWorkFlowSpecProperties(SdsUserContext userContext, String projectId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(id);

		checkerOf(userContext).checkSceneOfWorkFlowSpec( scene);
		checkerOf(userContext).checkBriefOfWorkFlowSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project updateWorkFlowSpecProperties(SdsUserContext userContext, String projectId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingWorkFlowSpecProperties(userContext,projectId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withWorkFlowSpecListList()
				.searchWorkFlowSpecListWith(WorkFlowSpec.ID_PROPERTY, "is", id).done();

		Project projectToUpdate = loadProject(userContext, projectId, options);

		if(projectToUpdate.getWorkFlowSpecList().isEmpty()){
			throw new ProjectManagerException("WorkFlowSpec is NOT FOUND with id: '"+id+"'");
		}

		WorkFlowSpec item = projectToUpdate.getWorkFlowSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingWorkFlowSpec(userContext,projectId,name, code, used,tokensExpr);
		Project project = saveProject(userContext, projectToUpdate, tokens().withWorkFlowSpecList().done());
		synchronized(project){
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}


	protected WorkFlowSpec createWorkFlowSpec(SdsUserContext userContext, String scene, String brief, String ownerId) throws Exception{

		WorkFlowSpec workFlowSpec = new WorkFlowSpec();
		
		
		workFlowSpec.setScene(scene);		
		workFlowSpec.setBrief(brief);		
		User  owner = new User();
		owner.setId(ownerId);		
		workFlowSpec.setOwner(owner);
	
		
		return workFlowSpec;


	}

	protected WorkFlowSpec createIndexedWorkFlowSpec(String id, int version){

		WorkFlowSpec workFlowSpec = new WorkFlowSpec();
		workFlowSpec.setId(id);
		workFlowSpec.setVersion(version);
		return workFlowSpec;

	}

	protected void checkParamsForRemovingWorkFlowSpecList(SdsUserContext userContext, String projectId,
			String workFlowSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		for(String workFlowSpecIdItem: workFlowSpecIds){
			checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removeWorkFlowSpecList(SdsUserContext userContext, String projectId,
			String workFlowSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingWorkFlowSpecList(userContext, projectId,  workFlowSpecIds, tokensExpr);


			Project project = loadProject(userContext, projectId, allTokens());
			synchronized(project){
				//Will be good when the project loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				projectDaoOf(userContext).planToRemoveWorkFlowSpecList(project, workFlowSpecIds, allTokens());
				project = saveProject(userContext, project, tokens().withWorkFlowSpecList().done());
				deleteRelationListInGraph(userContext, project.getWorkFlowSpecList());
				return present(userContext,project, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingWorkFlowSpec(SdsUserContext userContext, String projectId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecId);
		checkerOf(userContext).checkVersionOfWorkFlowSpec(workFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removeWorkFlowSpec(SdsUserContext userContext, String projectId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingWorkFlowSpec(userContext,projectId, workFlowSpecId, workFlowSpecVersion,tokensExpr);

		WorkFlowSpec workFlowSpec = createIndexedWorkFlowSpec(workFlowSpecId, workFlowSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.removeWorkFlowSpec( workFlowSpec );
			project = saveProject(userContext, project, tokens().withWorkFlowSpecList().done());
			deleteRelationInGraph(userContext, workFlowSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingWorkFlowSpec(SdsUserContext userContext, String projectId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecId);
		checkerOf(userContext).checkVersionOfWorkFlowSpec(workFlowSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project copyWorkFlowSpecFrom(SdsUserContext userContext, String projectId,
		String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingWorkFlowSpec(userContext,projectId, workFlowSpecId, workFlowSpecVersion,tokensExpr);

		WorkFlowSpec workFlowSpec = createIndexedWorkFlowSpec(workFlowSpecId, workFlowSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			project.copyWorkFlowSpecFrom( workFlowSpec );
			project = saveProject(userContext, project, tokens().withWorkFlowSpecList().done());
			
			userContext.getManagerGroup().getWorkFlowSpecManager().onNewInstanceCreated(userContext, (WorkFlowSpec)project.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingWorkFlowSpec(SdsUserContext userContext, String projectId, String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfWorkFlowSpec(workFlowSpecId);
		checkerOf(userContext).checkVersionOfWorkFlowSpec(workFlowSpecVersion);
		

		if(WorkFlowSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfWorkFlowSpec(parseString(newValueExpr));
		
		}
		
		if(WorkFlowSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfWorkFlowSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}

	public  Project updateWorkFlowSpec(SdsUserContext userContext, String projectId, String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingWorkFlowSpec(userContext, projectId, workFlowSpecId, workFlowSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withWorkFlowSpecList().searchWorkFlowSpecListWith(WorkFlowSpec.ID_PROPERTY, "eq", workFlowSpecId).done();



		Project project = loadProject(userContext, projectId, loadTokens);

		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//project.removeWorkFlowSpec( workFlowSpec );
			//make changes to AcceleraterAccount.
			WorkFlowSpec workFlowSpecIndex = createIndexedWorkFlowSpec(workFlowSpecId, workFlowSpecVersion);

			WorkFlowSpec workFlowSpec = project.findTheWorkFlowSpec(workFlowSpecIndex);
			if(workFlowSpec == null){
				throw new ProjectManagerException(workFlowSpec+" is NOT FOUND" );
			}

			workFlowSpec.changeProperty(property, newValueExpr);
			
			project = saveProject(userContext, project, tokens().withWorkFlowSpecList().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingChangeRequestSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProject(projectId);

		
		checkerOf(userContext).checkSceneOfChangeRequestSpec(scene);
		
		checkerOf(userContext).checkBriefOfChangeRequestSpec(brief);
		
		checkerOf(userContext).checkOwnerIdOfChangeRequestSpec(ownerId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


	}
	public  Project addChangeRequestSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingChangeRequestSpec(userContext,projectId,scene, brief, ownerId,tokensExpr);

		ChangeRequestSpec changeRequestSpec = createChangeRequestSpec(userContext,scene, brief, ownerId);

		Project project = loadProject(userContext, projectId, emptyOptions());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.addChangeRequestSpec( changeRequestSpec );
			project = saveProject(userContext, project, tokens().withChangeRequestSpecList().done());
			
			userContext.getManagerGroup().getChangeRequestSpecManager().onNewInstanceCreated(userContext, changeRequestSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingChangeRequestSpecProperties(SdsUserContext userContext, String projectId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(id);

		checkerOf(userContext).checkSceneOfChangeRequestSpec( scene);
		checkerOf(userContext).checkBriefOfChangeRequestSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project updateChangeRequestSpecProperties(SdsUserContext userContext, String projectId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingChangeRequestSpecProperties(userContext,projectId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withChangeRequestSpecListList()
				.searchChangeRequestSpecListWith(ChangeRequestSpec.ID_PROPERTY, "is", id).done();

		Project projectToUpdate = loadProject(userContext, projectId, options);

		if(projectToUpdate.getChangeRequestSpecList().isEmpty()){
			throw new ProjectManagerException("ChangeRequestSpec is NOT FOUND with id: '"+id+"'");
		}

		ChangeRequestSpec item = projectToUpdate.getChangeRequestSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingChangeRequestSpec(userContext,projectId,name, code, used,tokensExpr);
		Project project = saveProject(userContext, projectToUpdate, tokens().withChangeRequestSpecList().done());
		synchronized(project){
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}


	protected ChangeRequestSpec createChangeRequestSpec(SdsUserContext userContext, String scene, String brief, String ownerId) throws Exception{

		ChangeRequestSpec changeRequestSpec = new ChangeRequestSpec();
		
		
		changeRequestSpec.setScene(scene);		
		changeRequestSpec.setBrief(brief);		
		User  owner = new User();
		owner.setId(ownerId);		
		changeRequestSpec.setOwner(owner);
	
		
		return changeRequestSpec;


	}

	protected ChangeRequestSpec createIndexedChangeRequestSpec(String id, int version){

		ChangeRequestSpec changeRequestSpec = new ChangeRequestSpec();
		changeRequestSpec.setId(id);
		changeRequestSpec.setVersion(version);
		return changeRequestSpec;

	}

	protected void checkParamsForRemovingChangeRequestSpecList(SdsUserContext userContext, String projectId,
			String changeRequestSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		for(String changeRequestSpecIdItem: changeRequestSpecIds){
			checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removeChangeRequestSpecList(SdsUserContext userContext, String projectId,
			String changeRequestSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingChangeRequestSpecList(userContext, projectId,  changeRequestSpecIds, tokensExpr);


			Project project = loadProject(userContext, projectId, allTokens());
			synchronized(project){
				//Will be good when the project loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				projectDaoOf(userContext).planToRemoveChangeRequestSpecList(project, changeRequestSpecIds, allTokens());
				project = saveProject(userContext, project, tokens().withChangeRequestSpecList().done());
				deleteRelationListInGraph(userContext, project.getChangeRequestSpecList());
				return present(userContext,project, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingChangeRequestSpec(SdsUserContext userContext, String projectId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecId);
		checkerOf(userContext).checkVersionOfChangeRequestSpec(changeRequestSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removeChangeRequestSpec(SdsUserContext userContext, String projectId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingChangeRequestSpec(userContext,projectId, changeRequestSpecId, changeRequestSpecVersion,tokensExpr);

		ChangeRequestSpec changeRequestSpec = createIndexedChangeRequestSpec(changeRequestSpecId, changeRequestSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.removeChangeRequestSpec( changeRequestSpec );
			project = saveProject(userContext, project, tokens().withChangeRequestSpecList().done());
			deleteRelationInGraph(userContext, changeRequestSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingChangeRequestSpec(SdsUserContext userContext, String projectId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecId);
		checkerOf(userContext).checkVersionOfChangeRequestSpec(changeRequestSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project copyChangeRequestSpecFrom(SdsUserContext userContext, String projectId,
		String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingChangeRequestSpec(userContext,projectId, changeRequestSpecId, changeRequestSpecVersion,tokensExpr);

		ChangeRequestSpec changeRequestSpec = createIndexedChangeRequestSpec(changeRequestSpecId, changeRequestSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			project.copyChangeRequestSpecFrom( changeRequestSpec );
			project = saveProject(userContext, project, tokens().withChangeRequestSpecList().done());
			
			userContext.getManagerGroup().getChangeRequestSpecManager().onNewInstanceCreated(userContext, (ChangeRequestSpec)project.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingChangeRequestSpec(SdsUserContext userContext, String projectId, String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfChangeRequestSpec(changeRequestSpecId);
		checkerOf(userContext).checkVersionOfChangeRequestSpec(changeRequestSpecVersion);
		

		if(ChangeRequestSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfChangeRequestSpec(parseString(newValueExpr));
		
		}
		
		if(ChangeRequestSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfChangeRequestSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}

	public  Project updateChangeRequestSpec(SdsUserContext userContext, String projectId, String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingChangeRequestSpec(userContext, projectId, changeRequestSpecId, changeRequestSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withChangeRequestSpecList().searchChangeRequestSpecListWith(ChangeRequestSpec.ID_PROPERTY, "eq", changeRequestSpecId).done();



		Project project = loadProject(userContext, projectId, loadTokens);

		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//project.removeChangeRequestSpec( changeRequestSpec );
			//make changes to AcceleraterAccount.
			ChangeRequestSpec changeRequestSpecIndex = createIndexedChangeRequestSpec(changeRequestSpecId, changeRequestSpecVersion);

			ChangeRequestSpec changeRequestSpec = project.findTheChangeRequestSpec(changeRequestSpecIndex);
			if(changeRequestSpec == null){
				throw new ProjectManagerException(changeRequestSpec+" is NOT FOUND" );
			}

			changeRequestSpec.changeProperty(property, newValueExpr);
			
			project = saveProject(userContext, project, tokens().withChangeRequestSpecList().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	protected void checkParamsForAddingPageContentSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId,String [] tokensExpr) throws Exception{

				checkerOf(userContext).checkIdOfProject(projectId);

		
		checkerOf(userContext).checkSceneOfPageContentSpec(scene);
		
		checkerOf(userContext).checkBriefOfPageContentSpec(brief);
		
		checkerOf(userContext).checkOwnerIdOfPageContentSpec(ownerId);
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);


	}
	public  Project addPageContentSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId, String [] tokensExpr) throws Exception
	{

		checkParamsForAddingPageContentSpec(userContext,projectId,scene, brief, ownerId,tokensExpr);

		PageContentSpec pageContentSpec = createPageContentSpec(userContext,scene, brief, ownerId);

		Project project = loadProject(userContext, projectId, emptyOptions());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.addPageContentSpec( pageContentSpec );
			project = saveProject(userContext, project, tokens().withPageContentSpecList().done());
			
			userContext.getManagerGroup().getPageContentSpecManager().onNewInstanceCreated(userContext, pageContentSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPageContentSpecProperties(SdsUserContext userContext, String projectId,String id,String scene,String brief,String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfPageContentSpec(id);

		checkerOf(userContext).checkSceneOfPageContentSpec( scene);
		checkerOf(userContext).checkBriefOfPageContentSpec( brief);

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project updatePageContentSpecProperties(SdsUserContext userContext, String projectId, String id,String scene,String brief, String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingPageContentSpecProperties(userContext,projectId,id,scene,brief,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPageContentSpecListList()
				.searchPageContentSpecListWith(PageContentSpec.ID_PROPERTY, "is", id).done();

		Project projectToUpdate = loadProject(userContext, projectId, options);

		if(projectToUpdate.getPageContentSpecList().isEmpty()){
			throw new ProjectManagerException("PageContentSpec is NOT FOUND with id: '"+id+"'");
		}

		PageContentSpec item = projectToUpdate.getPageContentSpecList().first();

		item.updateScene( scene );
		item.updateBrief( brief );


		//checkParamsForAddingPageContentSpec(userContext,projectId,name, code, used,tokensExpr);
		Project project = saveProject(userContext, projectToUpdate, tokens().withPageContentSpecList().done());
		synchronized(project){
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}
	}


	protected PageContentSpec createPageContentSpec(SdsUserContext userContext, String scene, String brief, String ownerId) throws Exception{

		PageContentSpec pageContentSpec = new PageContentSpec();
		
		
		pageContentSpec.setScene(scene);		
		pageContentSpec.setBrief(brief);		
		User  owner = new User();
		owner.setId(ownerId);		
		pageContentSpec.setOwner(owner);
	
		
		return pageContentSpec;


	}

	protected PageContentSpec createIndexedPageContentSpec(String id, int version){

		PageContentSpec pageContentSpec = new PageContentSpec();
		pageContentSpec.setId(id);
		pageContentSpec.setVersion(version);
		return pageContentSpec;

	}

	protected void checkParamsForRemovingPageContentSpecList(SdsUserContext userContext, String projectId,
			String pageContentSpecIds[],String [] tokensExpr) throws Exception {

		checkerOf(userContext).checkIdOfProject(projectId);
		for(String pageContentSpecIdItem: pageContentSpecIds){
			checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecIdItem);
		}

		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removePageContentSpecList(SdsUserContext userContext, String projectId,
			String pageContentSpecIds[],String [] tokensExpr) throws Exception{

			checkParamsForRemovingPageContentSpecList(userContext, projectId,  pageContentSpecIds, tokensExpr);


			Project project = loadProject(userContext, projectId, allTokens());
			synchronized(project){
				//Will be good when the project loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				projectDaoOf(userContext).planToRemovePageContentSpecList(project, pageContentSpecIds, allTokens());
				project = saveProject(userContext, project, tokens().withPageContentSpecList().done());
				deleteRelationListInGraph(userContext, project.getPageContentSpecList());
				return present(userContext,project, mergedAllTokens(tokensExpr));
			}
	}

	protected void checkParamsForRemovingPageContentSpec(SdsUserContext userContext, String projectId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecId);
		checkerOf(userContext).checkVersionOfPageContentSpec(pageContentSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project removePageContentSpec(SdsUserContext userContext, String projectId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForRemovingPageContentSpec(userContext,projectId, pageContentSpecId, pageContentSpecVersion,tokensExpr);

		PageContentSpec pageContentSpec = createIndexedPageContentSpec(pageContentSpecId, pageContentSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			project.removePageContentSpec( pageContentSpec );
			project = saveProject(userContext, project, tokens().withPageContentSpecList().done());
			deleteRelationInGraph(userContext, pageContentSpec);
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}


	}
	protected void checkParamsForCopyingPageContentSpec(SdsUserContext userContext, String projectId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{
		
		checkerOf(userContext).checkIdOfProject( projectId);
		checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecId);
		checkerOf(userContext).checkVersionOfPageContentSpec(pageContentSpecVersion);
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}
	public  Project copyPageContentSpecFrom(SdsUserContext userContext, String projectId,
		String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr) throws Exception{

		checkParamsForCopyingPageContentSpec(userContext,projectId, pageContentSpecId, pageContentSpecVersion,tokensExpr);

		PageContentSpec pageContentSpec = createIndexedPageContentSpec(pageContentSpecId, pageContentSpecVersion);
		Project project = loadProject(userContext, projectId, allTokens());
		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation

			

			project.copyPageContentSpecFrom( pageContentSpec );
			project = saveProject(userContext, project, tokens().withPageContentSpecList().done());
			
			userContext.getManagerGroup().getPageContentSpecManager().onNewInstanceCreated(userContext, (PageContentSpec)project.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}

	protected void checkParamsForUpdatingPageContentSpec(SdsUserContext userContext, String projectId, String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		checkerOf(userContext).checkIdOfProject(projectId);
		checkerOf(userContext).checkIdOfPageContentSpec(pageContentSpecId);
		checkerOf(userContext).checkVersionOfPageContentSpec(pageContentSpecVersion);
		

		if(PageContentSpec.SCENE_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkSceneOfPageContentSpec(parseString(newValueExpr));
		
		}
		
		if(PageContentSpec.BRIEF_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkBriefOfPageContentSpec(parseString(newValueExpr));
		
		}
		
	
		checkerOf(userContext).throwExceptionIfHasErrors(ProjectManagerException.class);

	}

	public  Project updatePageContentSpec(SdsUserContext userContext, String projectId, String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{

		checkParamsForUpdatingPageContentSpec(userContext, projectId, pageContentSpecId, pageContentSpecVersion, property, newValueExpr,  tokensExpr);

		Map<String,Object> loadTokens = this.tokens().withPageContentSpecList().searchPageContentSpecListWith(PageContentSpec.ID_PROPERTY, "eq", pageContentSpecId).done();



		Project project = loadProject(userContext, projectId, loadTokens);

		synchronized(project){
			//Will be good when the project loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//project.removePageContentSpec( pageContentSpec );
			//make changes to AcceleraterAccount.
			PageContentSpec pageContentSpecIndex = createIndexedPageContentSpec(pageContentSpecId, pageContentSpecVersion);

			PageContentSpec pageContentSpec = project.findThePageContentSpec(pageContentSpecIndex);
			if(pageContentSpec == null){
				throw new ProjectManagerException(pageContentSpec+" is NOT FOUND" );
			}

			pageContentSpec.changeProperty(property, newValueExpr);
			
			project = saveProject(userContext, project, tokens().withPageContentSpecList().done());
			return present(userContext,project, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/




	public void onNewInstanceCreated(SdsUserContext userContext, Project newCreated) throws Exception{
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
		//   Project newProject = this.createProject(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newProject
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
		key.put(UserApp.OBJECT_TYPE_PROPERTY, Project.INTERNAL_TYPE);
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
    protected void enhanceForListOfView(SdsUserContext userContext,SmartList<Project> list) throws Exception {
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
		SmartList<Project> list = projectDaoOf(userContext).findProjectByCompany(companyId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		SdsCommonListOfViewPage page = new SdsCommonListOfViewPage();
		page.setClassOfList(Project.class);
		page.setContainerObject(Company.withId(companyId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("项目列表");
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
 	public Object wxappview(SdsUserContext userContext, String projectId) throws Exception{
	  SerializeScope vscope = SdsViewScope.getInstance().getProjectDetailScope().clone();
		Project merchantObj = (Project) this.view(userContext, projectId);
    String merchantObjId = projectId;
    String linkToUrl =	"projectManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "项目"+"详情";
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
				MapUtil.put("id", "3-company")
				    .put("fieldName", "company")
				    .put("label", "公司")
				    .put("type", "auto")
				    .put("linkToUrl", "companyManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"name\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("company", merchantObj.getCompany());

		//处理 sectionList

		//处理Section：userProjectListSection
		Map userProjectListSection = ListofUtils.buildSection(
		    "userProjectListSection",
		    "用户项目列表",
		    null,
		    "",
		    "__no_group",
		    "userProjectManager/listByProject/"+merchantObjId+"/",
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
		    "pageFlowSpecManager/listByProject/"+merchantObjId+"/",
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
		    "workFlowSpecManager/listByProject/"+merchantObjId+"/",
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
		    "changeRequestSpecManager/listByProject/"+merchantObjId+"/",
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
		    "pageContentSpecManager/listByProject/"+merchantObjId+"/",
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


