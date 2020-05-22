
package com.cla.sds.project;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

import com.terapico.caf.baseelement.CandidateQuery;
import com.terapico.utils.TextUtil;

import com.cla.sds.SdsBaseDAOImpl;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.AccessKey;
import com.cla.sds.DateKey;
import com.cla.sds.StatsInfo;
import com.cla.sds.StatsItem;

import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;


import com.cla.sds.userproject.UserProject;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.company.Company;
import com.cla.sds.changerequestspec.ChangeRequestSpec;

import com.cla.sds.pageflowspec.PageFlowSpecDAO;
import com.cla.sds.workflowspec.WorkFlowSpecDAO;
import com.cla.sds.pagecontentspec.PageContentSpecDAO;
import com.cla.sds.changerequestspec.ChangeRequestSpecDAO;
import com.cla.sds.company.CompanyDAO;
import com.cla.sds.userproject.UserProjectDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class ProjectJDBCTemplateDAO extends SdsBaseDAOImpl implements ProjectDAO{

	protected CompanyDAO companyDAO;
	public void setCompanyDAO(CompanyDAO companyDAO){
 	
 		if(companyDAO == null){
 			throw new IllegalStateException("Do not try to set companyDAO to null.");
 		}
	 	this.companyDAO = companyDAO;
 	}
 	public CompanyDAO getCompanyDAO(){
 		if(this.companyDAO == null){
 			throw new IllegalStateException("The companyDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.companyDAO;
 	}	

	protected UserProjectDAO userProjectDAO;
	public void setUserProjectDAO(UserProjectDAO userProjectDAO){
 	
 		if(userProjectDAO == null){
 			throw new IllegalStateException("Do not try to set userProjectDAO to null.");
 		}
	 	this.userProjectDAO = userProjectDAO;
 	}
 	public UserProjectDAO getUserProjectDAO(){
 		if(this.userProjectDAO == null){
 			throw new IllegalStateException("The userProjectDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userProjectDAO;
 	}	

	protected PageFlowSpecDAO pageFlowSpecDAO;
	public void setPageFlowSpecDAO(PageFlowSpecDAO pageFlowSpecDAO){
 	
 		if(pageFlowSpecDAO == null){
 			throw new IllegalStateException("Do not try to set pageFlowSpecDAO to null.");
 		}
	 	this.pageFlowSpecDAO = pageFlowSpecDAO;
 	}
 	public PageFlowSpecDAO getPageFlowSpecDAO(){
 		if(this.pageFlowSpecDAO == null){
 			throw new IllegalStateException("The pageFlowSpecDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.pageFlowSpecDAO;
 	}	

	protected WorkFlowSpecDAO workFlowSpecDAO;
	public void setWorkFlowSpecDAO(WorkFlowSpecDAO workFlowSpecDAO){
 	
 		if(workFlowSpecDAO == null){
 			throw new IllegalStateException("Do not try to set workFlowSpecDAO to null.");
 		}
	 	this.workFlowSpecDAO = workFlowSpecDAO;
 	}
 	public WorkFlowSpecDAO getWorkFlowSpecDAO(){
 		if(this.workFlowSpecDAO == null){
 			throw new IllegalStateException("The workFlowSpecDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.workFlowSpecDAO;
 	}	

	protected ChangeRequestSpecDAO changeRequestSpecDAO;
	public void setChangeRequestSpecDAO(ChangeRequestSpecDAO changeRequestSpecDAO){
 	
 		if(changeRequestSpecDAO == null){
 			throw new IllegalStateException("Do not try to set changeRequestSpecDAO to null.");
 		}
	 	this.changeRequestSpecDAO = changeRequestSpecDAO;
 	}
 	public ChangeRequestSpecDAO getChangeRequestSpecDAO(){
 		if(this.changeRequestSpecDAO == null){
 			throw new IllegalStateException("The changeRequestSpecDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.changeRequestSpecDAO;
 	}	

	protected PageContentSpecDAO pageContentSpecDAO;
	public void setPageContentSpecDAO(PageContentSpecDAO pageContentSpecDAO){
 	
 		if(pageContentSpecDAO == null){
 			throw new IllegalStateException("Do not try to set pageContentSpecDAO to null.");
 		}
	 	this.pageContentSpecDAO = pageContentSpecDAO;
 	}
 	public PageContentSpecDAO getPageContentSpecDAO(){
 		if(this.pageContentSpecDAO == null){
 			throw new IllegalStateException("The pageContentSpecDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.pageContentSpecDAO;
 	}	

	
	/*
	protected Project load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalProject(accessKey, options);
	}
	*/
	
	public SmartList<Project> loadAll() {
	    return this.loadAll(getProjectMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Project load(String id,Map<String,Object> options) throws Exception{
		return loadInternalProject(ProjectTable.withId(id), options);
	}
	
	
	
	public Project save(Project project,Map<String,Object> options){
		
		String methodName="save(Project project,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(project, methodName, "project");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalProject(project,options);
	}
	public Project clone(String projectId, Map<String,Object> options) throws Exception{
	
		return clone(ProjectTable.withId(projectId),options);
	}
	
	protected Project clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String projectId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Project newProject = loadInternalProject(accessKey, options);
		newProject.setVersion(0);
		
		
 		
 		if(isSaveUserProjectListEnabled(options)){
 			for(UserProject item: newProject.getUserProjectList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePageFlowSpecListEnabled(options)){
 			for(PageFlowSpec item: newProject.getPageFlowSpecList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWorkFlowSpecListEnabled(options)){
 			for(WorkFlowSpec item: newProject.getWorkFlowSpecList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveChangeRequestSpecListEnabled(options)){
 			for(ChangeRequestSpec item: newProject.getChangeRequestSpecList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePageContentSpecListEnabled(options)){
 			for(PageContentSpec item: newProject.getPageContentSpecList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalProject(newProject,options);
		
		return newProject;
	}
	
	
	
	

	protected void throwIfHasException(String projectId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ProjectVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ProjectNotFoundException(
					"The " + this.getTableName() + "(" + projectId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String projectId, int version) throws Exception{
	
		String methodName="delete(String projectId, int version)";
		assertMethodArgumentNotNull(projectId, methodName, "projectId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{projectId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(projectId,version);
		}
		
	
	}
	
	
	
	
	

	public Project disconnectFromAll(String projectId, int version) throws Exception{
	
		
		Project project = loadInternalProject(ProjectTable.withId(projectId), emptyOptions());
		project.clearFromAll();
		this.saveProject(project);
		return project;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ProjectTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "project";
	}
	@Override
	protected String getBeanName() {
		
		return "project";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ProjectTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractCompanyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ProjectTokens.COMPANY);
 	}

 	protected boolean isSaveCompanyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ProjectTokens.COMPANY);
 	}
 	

 	
 
		
	
	protected boolean isExtractUserProjectListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProjectTokens.USER_PROJECT_LIST);
 	}
 	protected boolean isAnalyzeUserProjectListEnabled(Map<String,Object> options){		 		
 		return ProjectTokens.of(options).analyzeUserProjectListEnabled();
 	}
	
	protected boolean isSaveUserProjectListEnabled(Map<String,Object> options){
		return checkOptions(options, ProjectTokens.USER_PROJECT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPageFlowSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProjectTokens.PAGE_FLOW_SPEC_LIST);
 	}
 	protected boolean isAnalyzePageFlowSpecListEnabled(Map<String,Object> options){		 		
 		return ProjectTokens.of(options).analyzePageFlowSpecListEnabled();
 	}
	
	protected boolean isSavePageFlowSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, ProjectTokens.PAGE_FLOW_SPEC_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWorkFlowSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProjectTokens.WORK_FLOW_SPEC_LIST);
 	}
 	protected boolean isAnalyzeWorkFlowSpecListEnabled(Map<String,Object> options){		 		
 		return ProjectTokens.of(options).analyzeWorkFlowSpecListEnabled();
 	}
	
	protected boolean isSaveWorkFlowSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, ProjectTokens.WORK_FLOW_SPEC_LIST);
		
 	}
 	
		
	
	protected boolean isExtractChangeRequestSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProjectTokens.CHANGE_REQUEST_SPEC_LIST);
 	}
 	protected boolean isAnalyzeChangeRequestSpecListEnabled(Map<String,Object> options){		 		
 		return ProjectTokens.of(options).analyzeChangeRequestSpecListEnabled();
 	}
	
	protected boolean isSaveChangeRequestSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, ProjectTokens.CHANGE_REQUEST_SPEC_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPageContentSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProjectTokens.PAGE_CONTENT_SPEC_LIST);
 	}
 	protected boolean isAnalyzePageContentSpecListEnabled(Map<String,Object> options){		 		
 		return ProjectTokens.of(options).analyzePageContentSpecListEnabled();
 	}
	
	protected boolean isSavePageContentSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, ProjectTokens.PAGE_CONTENT_SPEC_LIST);
		
 	}
 	
		

	

	protected ProjectMapper getProjectMapper(){
		return new ProjectMapper();
	}

	
	
	protected Project extractProject(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Project project = loadSingleObject(accessKey, getProjectMapper());
			return project;
		}catch(EmptyResultDataAccessException e){
			throw new ProjectNotFoundException("Project("+accessKey+") is not found!");
		}

	}

	
	

	protected Project loadInternalProject(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Project project = extractProject(accessKey, loadOptions);
 	
 		if(isExtractCompanyEnabled(loadOptions)){
	 		extractCompany(project, loadOptions);
 		}
 
		
		if(isExtractUserProjectListEnabled(loadOptions)){
	 		extractUserProjectList(project, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeUserProjectListEnabled(loadOptions)){
	 		analyzeUserProjectList(project, loadOptions);
 		}
 		
		
		if(isExtractPageFlowSpecListEnabled(loadOptions)){
	 		extractPageFlowSpecList(project, loadOptions);
 		}	
 		
 		
 		if(isAnalyzePageFlowSpecListEnabled(loadOptions)){
	 		analyzePageFlowSpecList(project, loadOptions);
 		}
 		
		
		if(isExtractWorkFlowSpecListEnabled(loadOptions)){
	 		extractWorkFlowSpecList(project, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeWorkFlowSpecListEnabled(loadOptions)){
	 		analyzeWorkFlowSpecList(project, loadOptions);
 		}
 		
		
		if(isExtractChangeRequestSpecListEnabled(loadOptions)){
	 		extractChangeRequestSpecList(project, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeChangeRequestSpecListEnabled(loadOptions)){
	 		analyzeChangeRequestSpecList(project, loadOptions);
 		}
 		
		
		if(isExtractPageContentSpecListEnabled(loadOptions)){
	 		extractPageContentSpecList(project, loadOptions);
 		}	
 		
 		
 		if(isAnalyzePageContentSpecListEnabled(loadOptions)){
	 		analyzePageContentSpecList(project, loadOptions);
 		}
 		
		
		return project;
		
	}

	 

 	protected Project extractCompany(Project project, Map<String,Object> options) throws Exception{

		if(project.getCompany() == null){
			return project;
		}
		String companyId = project.getCompany().getId();
		if( companyId == null){
			return project;
		}
		Company company = getCompanyDAO().load(companyId,options);
		if(company != null){
			project.setCompany(company);
		}
		
 		
 		return project;
 	}
 		
 
		
	protected void enhanceUserProjectList(SmartList<UserProject> userProjectList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Project extractUserProjectList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<UserProject> userProjectList = getUserProjectDAO().findUserProjectByProject(project.getId(),options);
		if(userProjectList != null){
			enhanceUserProjectList(userProjectList,options);
			project.setUserProjectList(userProjectList);
		}
		
		return project;
	
	}	
	
	protected Project analyzeUserProjectList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<UserProject> userProjectList = project.getUserProjectList();
		if(userProjectList != null){
			getUserProjectDAO().analyzeUserProjectByProject(userProjectList, project.getId(), options);
			
		}
		
		return project;
	
	}	
	
		
	protected void enhancePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Project extractPageFlowSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<PageFlowSpec> pageFlowSpecList = getPageFlowSpecDAO().findPageFlowSpecByProject(project.getId(),options);
		if(pageFlowSpecList != null){
			enhancePageFlowSpecList(pageFlowSpecList,options);
			project.setPageFlowSpecList(pageFlowSpecList);
		}
		
		return project;
	
	}	
	
	protected Project analyzePageFlowSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<PageFlowSpec> pageFlowSpecList = project.getPageFlowSpecList();
		if(pageFlowSpecList != null){
			getPageFlowSpecDAO().analyzePageFlowSpecByProject(pageFlowSpecList, project.getId(), options);
			
		}
		
		return project;
	
	}	
	
		
	protected void enhanceWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Project extractWorkFlowSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<WorkFlowSpec> workFlowSpecList = getWorkFlowSpecDAO().findWorkFlowSpecByProject(project.getId(),options);
		if(workFlowSpecList != null){
			enhanceWorkFlowSpecList(workFlowSpecList,options);
			project.setWorkFlowSpecList(workFlowSpecList);
		}
		
		return project;
	
	}	
	
	protected Project analyzeWorkFlowSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<WorkFlowSpec> workFlowSpecList = project.getWorkFlowSpecList();
		if(workFlowSpecList != null){
			getWorkFlowSpecDAO().analyzeWorkFlowSpecByProject(workFlowSpecList, project.getId(), options);
			
		}
		
		return project;
	
	}	
	
		
	protected void enhanceChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Project extractChangeRequestSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = getChangeRequestSpecDAO().findChangeRequestSpecByProject(project.getId(),options);
		if(changeRequestSpecList != null){
			enhanceChangeRequestSpecList(changeRequestSpecList,options);
			project.setChangeRequestSpecList(changeRequestSpecList);
		}
		
		return project;
	
	}	
	
	protected Project analyzeChangeRequestSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = project.getChangeRequestSpecList();
		if(changeRequestSpecList != null){
			getChangeRequestSpecDAO().analyzeChangeRequestSpecByProject(changeRequestSpecList, project.getId(), options);
			
		}
		
		return project;
	
	}	
	
		
	protected void enhancePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Project extractPageContentSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<PageContentSpec> pageContentSpecList = getPageContentSpecDAO().findPageContentSpecByProject(project.getId(),options);
		if(pageContentSpecList != null){
			enhancePageContentSpecList(pageContentSpecList,options);
			project.setPageContentSpecList(pageContentSpecList);
		}
		
		return project;
	
	}	
	
	protected Project analyzePageContentSpecList(Project project, Map<String,Object> options){
		
		
		if(project == null){
			return null;
		}
		if(project.getId() == null){
			return project;
		}

		
		
		SmartList<PageContentSpec> pageContentSpecList = project.getPageContentSpecList();
		if(pageContentSpecList != null){
			getPageContentSpecDAO().analyzePageContentSpecByProject(pageContentSpecList, project.getId(), options);
			
		}
		
		return project;
	
	}	
	
		
		
  	
 	public SmartList<Project> findProjectByCompany(String companyId,Map<String,Object> options){
 	
  		SmartList<Project> resultList = queryWith(ProjectTable.COLUMN_COMPANY, companyId, options, getProjectMapper());
		// analyzeProjectByCompany(resultList, companyId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Project> findProjectByCompany(String companyId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Project> resultList =  queryWithRange(ProjectTable.COLUMN_COMPANY, companyId, options, getProjectMapper(), start, count);
 		//analyzeProjectByCompany(resultList, companyId, options);
 		return resultList;
 		
 	}
 	public void analyzeProjectByCompany(SmartList<Project> resultList, String companyId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countProjectByCompany(String companyId,Map<String,Object> options){

 		return countWith(ProjectTable.COLUMN_COMPANY, companyId, options);
 	}
 	@Override
	public Map<String, Integer> countProjectByCompanyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ProjectTable.COLUMN_COMPANY, ids, options);
	}
 	
 	
		
		
		

	

	protected Project saveProject(Project  project){
		
		if(!project.isChanged()){
			return project;
		}
		
		
		String SQL=this.getSaveProjectSQL(project);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveProjectParameters(project);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		project.incVersion();
		return project;
	
	}
	public SmartList<Project> saveProjectList(SmartList<Project> projectList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitProjectList(projectList);
		
		batchProjectCreate((List<Project>)lists[CREATE_LIST_INDEX]);
		
		batchProjectUpdate((List<Project>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Project project:projectList){
			if(project.isChanged()){
				project.incVersion();
			}
			
		
		}
		
		
		return projectList;
	}

	public SmartList<Project> removeProjectList(SmartList<Project> projectList,Map<String,Object> options){
		
		
		super.removeList(projectList, options);
		
		return projectList;
		
		
	}
	
	protected List<Object[]> prepareProjectBatchCreateArgs(List<Project> projectList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Project project:projectList ){
			Object [] parameters = prepareProjectCreateParameters(project);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareProjectBatchUpdateArgs(List<Project> projectList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Project project:projectList ){
			if(!project.isChanged()){
				continue;
			}
			Object [] parameters = prepareProjectUpdateParameters(project);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchProjectCreate(List<Project> projectList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareProjectBatchCreateArgs(projectList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchProjectUpdate(List<Project> projectList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareProjectBatchUpdateArgs(projectList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitProjectList(List<Project> projectList){
		
		List<Project> projectCreateList=new ArrayList<Project>();
		List<Project> projectUpdateList=new ArrayList<Project>();
		
		for(Project project: projectList){
			if(isUpdateRequest(project)){
				projectUpdateList.add( project);
				continue;
			}
			projectCreateList.add(project);
		}
		
		return new Object[]{projectCreateList,projectUpdateList};
	}
	
	protected boolean isUpdateRequest(Project project){
 		return project.getVersion() > 0;
 	}
 	protected String getSaveProjectSQL(Project project){
 		if(isUpdateRequest(project)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveProjectParameters(Project project){
 		if(isUpdateRequest(project) ){
 			return prepareProjectUpdateParameters(project);
 		}
 		return prepareProjectCreateParameters(project);
 	}
 	protected Object[] prepareProjectUpdateParameters(Project project){
 		Object[] parameters = new Object[5];
 
 		
 		parameters[0] = project.getName();
 		 	
 		if(project.getCompany() != null){
 			parameters[1] = project.getCompany().getId();
 		}
 		
 		parameters[2] = project.nextVersion();
 		parameters[3] = project.getId();
 		parameters[4] = project.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareProjectCreateParameters(Project project){
		Object[] parameters = new Object[3];
		String newProjectId=getNextId();
		project.setId(newProjectId);
		parameters[0] =  project.getId();
 
 		
 		parameters[1] = project.getName();
 		 	
 		if(project.getCompany() != null){
 			parameters[2] = project.getCompany().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Project saveInternalProject(Project project, Map<String,Object> options){
		
		saveProject(project);
 	
 		if(isSaveCompanyEnabled(options)){
	 		saveCompany(project, options);
 		}
 
		
		if(isSaveUserProjectListEnabled(options)){
	 		saveUserProjectList(project, options);
	 		//removeUserProjectList(project, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePageFlowSpecListEnabled(options)){
	 		savePageFlowSpecList(project, options);
	 		//removePageFlowSpecList(project, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveWorkFlowSpecListEnabled(options)){
	 		saveWorkFlowSpecList(project, options);
	 		//removeWorkFlowSpecList(project, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveChangeRequestSpecListEnabled(options)){
	 		saveChangeRequestSpecList(project, options);
	 		//removeChangeRequestSpecList(project, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePageContentSpecListEnabled(options)){
	 		savePageContentSpecList(project, options);
	 		//removePageContentSpecList(project, options);
	 		//Not delete the record
	 		
 		}		
		
		return project;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Project saveCompany(Project project, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(project.getCompany() == null){
 			return project;//do nothing when it is null
 		}
 		
 		getCompanyDAO().save(project.getCompany(),options);
 		return project;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Project planToRemoveUserProjectList(Project project, String userProjectIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.PROJECT_PROPERTY, project.getId());
		key.put(UserProject.ID_PROPERTY, userProjectIds);
		
		SmartList<UserProject> externalUserProjectList = getUserProjectDAO().
				findUserProjectWithKey(key, options);
		if(externalUserProjectList == null){
			return project;
		}
		if(externalUserProjectList.isEmpty()){
			return project;
		}
		
		for(UserProject userProjectItem: externalUserProjectList){

			userProjectItem.clearFromAll();
		}
		
		
		SmartList<UserProject> userProjectList = project.getUserProjectList();		
		userProjectList.addAllToRemoveList(externalUserProjectList);
		return project;	
	
	}


	//disconnect Project with user in UserProject
	public Project planToRemoveUserProjectListWithUser(Project project, String userId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.PROJECT_PROPERTY, project.getId());
		key.put(UserProject.USER_PROPERTY, userId);
		
		SmartList<UserProject> externalUserProjectList = getUserProjectDAO().
				findUserProjectWithKey(key, options);
		if(externalUserProjectList == null){
			return project;
		}
		if(externalUserProjectList.isEmpty()){
			return project;
		}
		
		for(UserProject userProjectItem: externalUserProjectList){
			userProjectItem.clearUser();
			userProjectItem.clearProject();
			
		}
		
		
		SmartList<UserProject> userProjectList = project.getUserProjectList();		
		userProjectList.addAllToRemoveList(externalUserProjectList);
		return project;
	}
	
	public int countUserProjectListWithUser(String projectId, String userId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.PROJECT_PROPERTY, projectId);
		key.put(UserProject.USER_PROPERTY, userId);
		
		int count = getUserProjectDAO().countUserProjectWithKey(key, options);
		return count;
	}
	
	public Project planToRemovePageFlowSpecList(Project project, String pageFlowSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.PROJECT_PROPERTY, project.getId());
		key.put(PageFlowSpec.ID_PROPERTY, pageFlowSpecIds);
		
		SmartList<PageFlowSpec> externalPageFlowSpecList = getPageFlowSpecDAO().
				findPageFlowSpecWithKey(key, options);
		if(externalPageFlowSpecList == null){
			return project;
		}
		if(externalPageFlowSpecList.isEmpty()){
			return project;
		}
		
		for(PageFlowSpec pageFlowSpecItem: externalPageFlowSpecList){

			pageFlowSpecItem.clearFromAll();
		}
		
		
		SmartList<PageFlowSpec> pageFlowSpecList = project.getPageFlowSpecList();		
		pageFlowSpecList.addAllToRemoveList(externalPageFlowSpecList);
		return project;	
	
	}


	//disconnect Project with owner in PageFlowSpec
	public Project planToRemovePageFlowSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.PROJECT_PROPERTY, project.getId());
		key.put(PageFlowSpec.OWNER_PROPERTY, ownerId);
		
		SmartList<PageFlowSpec> externalPageFlowSpecList = getPageFlowSpecDAO().
				findPageFlowSpecWithKey(key, options);
		if(externalPageFlowSpecList == null){
			return project;
		}
		if(externalPageFlowSpecList.isEmpty()){
			return project;
		}
		
		for(PageFlowSpec pageFlowSpecItem: externalPageFlowSpecList){
			pageFlowSpecItem.clearOwner();
			pageFlowSpecItem.clearProject();
			
		}
		
		
		SmartList<PageFlowSpec> pageFlowSpecList = project.getPageFlowSpecList();		
		pageFlowSpecList.addAllToRemoveList(externalPageFlowSpecList);
		return project;
	}
	
	public int countPageFlowSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.PROJECT_PROPERTY, projectId);
		key.put(PageFlowSpec.OWNER_PROPERTY, ownerId);
		
		int count = getPageFlowSpecDAO().countPageFlowSpecWithKey(key, options);
		return count;
	}
	
	public Project planToRemoveWorkFlowSpecList(Project project, String workFlowSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.PROJECT_PROPERTY, project.getId());
		key.put(WorkFlowSpec.ID_PROPERTY, workFlowSpecIds);
		
		SmartList<WorkFlowSpec> externalWorkFlowSpecList = getWorkFlowSpecDAO().
				findWorkFlowSpecWithKey(key, options);
		if(externalWorkFlowSpecList == null){
			return project;
		}
		if(externalWorkFlowSpecList.isEmpty()){
			return project;
		}
		
		for(WorkFlowSpec workFlowSpecItem: externalWorkFlowSpecList){

			workFlowSpecItem.clearFromAll();
		}
		
		
		SmartList<WorkFlowSpec> workFlowSpecList = project.getWorkFlowSpecList();		
		workFlowSpecList.addAllToRemoveList(externalWorkFlowSpecList);
		return project;	
	
	}


	//disconnect Project with owner in WorkFlowSpec
	public Project planToRemoveWorkFlowSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.PROJECT_PROPERTY, project.getId());
		key.put(WorkFlowSpec.OWNER_PROPERTY, ownerId);
		
		SmartList<WorkFlowSpec> externalWorkFlowSpecList = getWorkFlowSpecDAO().
				findWorkFlowSpecWithKey(key, options);
		if(externalWorkFlowSpecList == null){
			return project;
		}
		if(externalWorkFlowSpecList.isEmpty()){
			return project;
		}
		
		for(WorkFlowSpec workFlowSpecItem: externalWorkFlowSpecList){
			workFlowSpecItem.clearOwner();
			workFlowSpecItem.clearProject();
			
		}
		
		
		SmartList<WorkFlowSpec> workFlowSpecList = project.getWorkFlowSpecList();		
		workFlowSpecList.addAllToRemoveList(externalWorkFlowSpecList);
		return project;
	}
	
	public int countWorkFlowSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.PROJECT_PROPERTY, projectId);
		key.put(WorkFlowSpec.OWNER_PROPERTY, ownerId);
		
		int count = getWorkFlowSpecDAO().countWorkFlowSpecWithKey(key, options);
		return count;
	}
	
	public Project planToRemoveChangeRequestSpecList(Project project, String changeRequestSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.PROJECT_PROPERTY, project.getId());
		key.put(ChangeRequestSpec.ID_PROPERTY, changeRequestSpecIds);
		
		SmartList<ChangeRequestSpec> externalChangeRequestSpecList = getChangeRequestSpecDAO().
				findChangeRequestSpecWithKey(key, options);
		if(externalChangeRequestSpecList == null){
			return project;
		}
		if(externalChangeRequestSpecList.isEmpty()){
			return project;
		}
		
		for(ChangeRequestSpec changeRequestSpecItem: externalChangeRequestSpecList){

			changeRequestSpecItem.clearFromAll();
		}
		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = project.getChangeRequestSpecList();		
		changeRequestSpecList.addAllToRemoveList(externalChangeRequestSpecList);
		return project;	
	
	}


	//disconnect Project with owner in ChangeRequestSpec
	public Project planToRemoveChangeRequestSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.PROJECT_PROPERTY, project.getId());
		key.put(ChangeRequestSpec.OWNER_PROPERTY, ownerId);
		
		SmartList<ChangeRequestSpec> externalChangeRequestSpecList = getChangeRequestSpecDAO().
				findChangeRequestSpecWithKey(key, options);
		if(externalChangeRequestSpecList == null){
			return project;
		}
		if(externalChangeRequestSpecList.isEmpty()){
			return project;
		}
		
		for(ChangeRequestSpec changeRequestSpecItem: externalChangeRequestSpecList){
			changeRequestSpecItem.clearOwner();
			changeRequestSpecItem.clearProject();
			
		}
		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = project.getChangeRequestSpecList();		
		changeRequestSpecList.addAllToRemoveList(externalChangeRequestSpecList);
		return project;
	}
	
	public int countChangeRequestSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.PROJECT_PROPERTY, projectId);
		key.put(ChangeRequestSpec.OWNER_PROPERTY, ownerId);
		
		int count = getChangeRequestSpecDAO().countChangeRequestSpecWithKey(key, options);
		return count;
	}
	
	public Project planToRemovePageContentSpecList(Project project, String pageContentSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.PROJECT_PROPERTY, project.getId());
		key.put(PageContentSpec.ID_PROPERTY, pageContentSpecIds);
		
		SmartList<PageContentSpec> externalPageContentSpecList = getPageContentSpecDAO().
				findPageContentSpecWithKey(key, options);
		if(externalPageContentSpecList == null){
			return project;
		}
		if(externalPageContentSpecList.isEmpty()){
			return project;
		}
		
		for(PageContentSpec pageContentSpecItem: externalPageContentSpecList){

			pageContentSpecItem.clearFromAll();
		}
		
		
		SmartList<PageContentSpec> pageContentSpecList = project.getPageContentSpecList();		
		pageContentSpecList.addAllToRemoveList(externalPageContentSpecList);
		return project;	
	
	}


	//disconnect Project with owner in PageContentSpec
	public Project planToRemovePageContentSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.PROJECT_PROPERTY, project.getId());
		key.put(PageContentSpec.OWNER_PROPERTY, ownerId);
		
		SmartList<PageContentSpec> externalPageContentSpecList = getPageContentSpecDAO().
				findPageContentSpecWithKey(key, options);
		if(externalPageContentSpecList == null){
			return project;
		}
		if(externalPageContentSpecList.isEmpty()){
			return project;
		}
		
		for(PageContentSpec pageContentSpecItem: externalPageContentSpecList){
			pageContentSpecItem.clearOwner();
			pageContentSpecItem.clearProject();
			
		}
		
		
		SmartList<PageContentSpec> pageContentSpecList = project.getPageContentSpecList();		
		pageContentSpecList.addAllToRemoveList(externalPageContentSpecList);
		return project;
	}
	
	public int countPageContentSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.PROJECT_PROPERTY, projectId);
		key.put(PageContentSpec.OWNER_PROPERTY, ownerId);
		
		int count = getPageContentSpecDAO().countPageContentSpecWithKey(key, options);
		return count;
	}
	

		
	protected Project saveUserProjectList(Project project, Map<String,Object> options){
		
		
		
		
		SmartList<UserProject> userProjectList = project.getUserProjectList();
		if(userProjectList == null){
			//null list means nothing
			return project;
		}
		SmartList<UserProject> mergedUpdateUserProjectList = new SmartList<UserProject>();
		
		
		mergedUpdateUserProjectList.addAll(userProjectList); 
		if(userProjectList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateUserProjectList.addAll(userProjectList.getToRemoveList());
			userProjectList.removeAll(userProjectList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getUserProjectDAO().saveUserProjectList(mergedUpdateUserProjectList,options);
		
		if(userProjectList.getToRemoveList() != null){
			userProjectList.removeAll(userProjectList.getToRemoveList());
		}
		
		
		return project;
	
	}
	
	protected Project removeUserProjectList(Project project, Map<String,Object> options){
	
	
		SmartList<UserProject> userProjectList = project.getUserProjectList();
		if(userProjectList == null){
			return project;
		}	
	
		SmartList<UserProject> toRemoveUserProjectList = userProjectList.getToRemoveList();
		
		if(toRemoveUserProjectList == null){
			return project;
		}
		if(toRemoveUserProjectList.isEmpty()){
			return project;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getUserProjectDAO().removeUserProjectList(toRemoveUserProjectList,options);
		
		return project;
	
	}
	
	

 	
 	
	
	
	
		
	protected Project savePageFlowSpecList(Project project, Map<String,Object> options){
		
		
		
		
		SmartList<PageFlowSpec> pageFlowSpecList = project.getPageFlowSpecList();
		if(pageFlowSpecList == null){
			//null list means nothing
			return project;
		}
		SmartList<PageFlowSpec> mergedUpdatePageFlowSpecList = new SmartList<PageFlowSpec>();
		
		
		mergedUpdatePageFlowSpecList.addAll(pageFlowSpecList); 
		if(pageFlowSpecList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePageFlowSpecList.addAll(pageFlowSpecList.getToRemoveList());
			pageFlowSpecList.removeAll(pageFlowSpecList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPageFlowSpecDAO().savePageFlowSpecList(mergedUpdatePageFlowSpecList,options);
		
		if(pageFlowSpecList.getToRemoveList() != null){
			pageFlowSpecList.removeAll(pageFlowSpecList.getToRemoveList());
		}
		
		
		return project;
	
	}
	
	protected Project removePageFlowSpecList(Project project, Map<String,Object> options){
	
	
		SmartList<PageFlowSpec> pageFlowSpecList = project.getPageFlowSpecList();
		if(pageFlowSpecList == null){
			return project;
		}	
	
		SmartList<PageFlowSpec> toRemovePageFlowSpecList = pageFlowSpecList.getToRemoveList();
		
		if(toRemovePageFlowSpecList == null){
			return project;
		}
		if(toRemovePageFlowSpecList.isEmpty()){
			return project;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPageFlowSpecDAO().removePageFlowSpecList(toRemovePageFlowSpecList,options);
		
		return project;
	
	}
	
	

 	
 	
	
	
	
		
	protected Project saveWorkFlowSpecList(Project project, Map<String,Object> options){
		
		
		
		
		SmartList<WorkFlowSpec> workFlowSpecList = project.getWorkFlowSpecList();
		if(workFlowSpecList == null){
			//null list means nothing
			return project;
		}
		SmartList<WorkFlowSpec> mergedUpdateWorkFlowSpecList = new SmartList<WorkFlowSpec>();
		
		
		mergedUpdateWorkFlowSpecList.addAll(workFlowSpecList); 
		if(workFlowSpecList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateWorkFlowSpecList.addAll(workFlowSpecList.getToRemoveList());
			workFlowSpecList.removeAll(workFlowSpecList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getWorkFlowSpecDAO().saveWorkFlowSpecList(mergedUpdateWorkFlowSpecList,options);
		
		if(workFlowSpecList.getToRemoveList() != null){
			workFlowSpecList.removeAll(workFlowSpecList.getToRemoveList());
		}
		
		
		return project;
	
	}
	
	protected Project removeWorkFlowSpecList(Project project, Map<String,Object> options){
	
	
		SmartList<WorkFlowSpec> workFlowSpecList = project.getWorkFlowSpecList();
		if(workFlowSpecList == null){
			return project;
		}	
	
		SmartList<WorkFlowSpec> toRemoveWorkFlowSpecList = workFlowSpecList.getToRemoveList();
		
		if(toRemoveWorkFlowSpecList == null){
			return project;
		}
		if(toRemoveWorkFlowSpecList.isEmpty()){
			return project;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getWorkFlowSpecDAO().removeWorkFlowSpecList(toRemoveWorkFlowSpecList,options);
		
		return project;
	
	}
	
	

 	
 	
	
	
	
		
	protected Project saveChangeRequestSpecList(Project project, Map<String,Object> options){
		
		
		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = project.getChangeRequestSpecList();
		if(changeRequestSpecList == null){
			//null list means nothing
			return project;
		}
		SmartList<ChangeRequestSpec> mergedUpdateChangeRequestSpecList = new SmartList<ChangeRequestSpec>();
		
		
		mergedUpdateChangeRequestSpecList.addAll(changeRequestSpecList); 
		if(changeRequestSpecList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateChangeRequestSpecList.addAll(changeRequestSpecList.getToRemoveList());
			changeRequestSpecList.removeAll(changeRequestSpecList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getChangeRequestSpecDAO().saveChangeRequestSpecList(mergedUpdateChangeRequestSpecList,options);
		
		if(changeRequestSpecList.getToRemoveList() != null){
			changeRequestSpecList.removeAll(changeRequestSpecList.getToRemoveList());
		}
		
		
		return project;
	
	}
	
	protected Project removeChangeRequestSpecList(Project project, Map<String,Object> options){
	
	
		SmartList<ChangeRequestSpec> changeRequestSpecList = project.getChangeRequestSpecList();
		if(changeRequestSpecList == null){
			return project;
		}	
	
		SmartList<ChangeRequestSpec> toRemoveChangeRequestSpecList = changeRequestSpecList.getToRemoveList();
		
		if(toRemoveChangeRequestSpecList == null){
			return project;
		}
		if(toRemoveChangeRequestSpecList.isEmpty()){
			return project;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getChangeRequestSpecDAO().removeChangeRequestSpecList(toRemoveChangeRequestSpecList,options);
		
		return project;
	
	}
	
	

 	
 	
	
	
	
		
	protected Project savePageContentSpecList(Project project, Map<String,Object> options){
		
		
		
		
		SmartList<PageContentSpec> pageContentSpecList = project.getPageContentSpecList();
		if(pageContentSpecList == null){
			//null list means nothing
			return project;
		}
		SmartList<PageContentSpec> mergedUpdatePageContentSpecList = new SmartList<PageContentSpec>();
		
		
		mergedUpdatePageContentSpecList.addAll(pageContentSpecList); 
		if(pageContentSpecList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePageContentSpecList.addAll(pageContentSpecList.getToRemoveList());
			pageContentSpecList.removeAll(pageContentSpecList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPageContentSpecDAO().savePageContentSpecList(mergedUpdatePageContentSpecList,options);
		
		if(pageContentSpecList.getToRemoveList() != null){
			pageContentSpecList.removeAll(pageContentSpecList.getToRemoveList());
		}
		
		
		return project;
	
	}
	
	protected Project removePageContentSpecList(Project project, Map<String,Object> options){
	
	
		SmartList<PageContentSpec> pageContentSpecList = project.getPageContentSpecList();
		if(pageContentSpecList == null){
			return project;
		}	
	
		SmartList<PageContentSpec> toRemovePageContentSpecList = pageContentSpecList.getToRemoveList();
		
		if(toRemovePageContentSpecList == null){
			return project;
		}
		if(toRemovePageContentSpecList.isEmpty()){
			return project;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPageContentSpecDAO().removePageContentSpecList(toRemovePageContentSpecList,options);
		
		return project;
	
	}
	
	

 	
 	
	
	
	
		

	public Project present(Project project,Map<String, Object> options){
	
		presentUserProjectList(project,options);
		presentPageFlowSpecList(project,options);
		presentWorkFlowSpecList(project,options);
		presentChangeRequestSpecList(project,options);
		presentPageContentSpecList(project,options);

		return project;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Project presentUserProjectList(
			Project project,
			Map<String, Object> options) {

		SmartList<UserProject> userProjectList = project.getUserProjectList();		
				SmartList<UserProject> newList= presentSubList(project.getId(),
				userProjectList,
				options,
				getUserProjectDAO()::countUserProjectByProject,
				getUserProjectDAO()::findUserProjectByProject
				);

		
		project.setUserProjectList(newList);
		

		return project;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Project presentPageFlowSpecList(
			Project project,
			Map<String, Object> options) {

		SmartList<PageFlowSpec> pageFlowSpecList = project.getPageFlowSpecList();		
				SmartList<PageFlowSpec> newList= presentSubList(project.getId(),
				pageFlowSpecList,
				options,
				getPageFlowSpecDAO()::countPageFlowSpecByProject,
				getPageFlowSpecDAO()::findPageFlowSpecByProject
				);

		
		project.setPageFlowSpecList(newList);
		

		return project;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Project presentWorkFlowSpecList(
			Project project,
			Map<String, Object> options) {

		SmartList<WorkFlowSpec> workFlowSpecList = project.getWorkFlowSpecList();		
				SmartList<WorkFlowSpec> newList= presentSubList(project.getId(),
				workFlowSpecList,
				options,
				getWorkFlowSpecDAO()::countWorkFlowSpecByProject,
				getWorkFlowSpecDAO()::findWorkFlowSpecByProject
				);

		
		project.setWorkFlowSpecList(newList);
		

		return project;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Project presentChangeRequestSpecList(
			Project project,
			Map<String, Object> options) {

		SmartList<ChangeRequestSpec> changeRequestSpecList = project.getChangeRequestSpecList();		
				SmartList<ChangeRequestSpec> newList= presentSubList(project.getId(),
				changeRequestSpecList,
				options,
				getChangeRequestSpecDAO()::countChangeRequestSpecByProject,
				getChangeRequestSpecDAO()::findChangeRequestSpecByProject
				);

		
		project.setChangeRequestSpecList(newList);
		

		return project;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Project presentPageContentSpecList(
			Project project,
			Map<String, Object> options) {

		SmartList<PageContentSpec> pageContentSpecList = project.getPageContentSpecList();		
				SmartList<PageContentSpec> newList= presentSubList(project.getId(),
				pageContentSpecList,
				options,
				getPageContentSpecDAO()::countPageContentSpecByProject,
				getPageContentSpecDAO()::findPageContentSpecByProject
				);

		
		project.setPageContentSpecList(newList);
		

		return project;
	}			
		

	
    public SmartList<Project> requestCandidateProjectForUserProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProjectTable.COLUMN_NAME, ProjectTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getProjectMapper());
    }
		
    public SmartList<Project> requestCandidateProjectForPageFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProjectTable.COLUMN_NAME, ProjectTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getProjectMapper());
    }
		
    public SmartList<Project> requestCandidateProjectForWorkFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProjectTable.COLUMN_NAME, ProjectTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getProjectMapper());
    }
		
    public SmartList<Project> requestCandidateProjectForChangeRequestSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProjectTable.COLUMN_NAME, ProjectTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getProjectMapper());
    }
		
    public SmartList<Project> requestCandidateProjectForPageContentSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProjectTable.COLUMN_NAME, ProjectTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getProjectMapper());
    }
		

	protected String getTableName(){
		return ProjectTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Project> projectList) {		
		this.enhanceListInternal(projectList, this.getProjectMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:UserProject的project的UserProjectList
	public SmartList<UserProject> loadOurUserProjectList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.PROJECT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<UserProject> loadedObjs = userContext.getDAOGroup().getUserProjectDAO().findUserProjectWithKey(key, options);
		Map<String, List<UserProject>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProject().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<UserProject> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<UserProject> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setUserProjectList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:PageFlowSpec的project的PageFlowSpecList
	public SmartList<PageFlowSpec> loadOurPageFlowSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.PROJECT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<PageFlowSpec> loadedObjs = userContext.getDAOGroup().getPageFlowSpecDAO().findPageFlowSpecWithKey(key, options);
		Map<String, List<PageFlowSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProject().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<PageFlowSpec> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<PageFlowSpec> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setPageFlowSpecList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:WorkFlowSpec的project的WorkFlowSpecList
	public SmartList<WorkFlowSpec> loadOurWorkFlowSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.PROJECT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WorkFlowSpec> loadedObjs = userContext.getDAOGroup().getWorkFlowSpecDAO().findWorkFlowSpecWithKey(key, options);
		Map<String, List<WorkFlowSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProject().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<WorkFlowSpec> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<WorkFlowSpec> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setWorkFlowSpecList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestSpec的project的ChangeRequestSpecList
	public SmartList<ChangeRequestSpec> loadOurChangeRequestSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.PROJECT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ChangeRequestSpec> loadedObjs = userContext.getDAOGroup().getChangeRequestSpecDAO().findChangeRequestSpecWithKey(key, options);
		Map<String, List<ChangeRequestSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProject().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ChangeRequestSpec> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ChangeRequestSpec> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setChangeRequestSpecList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:PageContentSpec的project的PageContentSpecList
	public SmartList<PageContentSpec> loadOurPageContentSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.PROJECT_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<PageContentSpec> loadedObjs = userContext.getDAOGroup().getPageContentSpecDAO().findPageContentSpecWithKey(key, options);
		Map<String, List<PageContentSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getProject().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<PageContentSpec> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<PageContentSpec> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setPageContentSpecList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Project> projectList = ownerEntity.collectRefsWithType(Project.INTERNAL_TYPE);
		this.enhanceList(projectList);
		
	}
	
	@Override
	public SmartList<Project> findProjectWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getProjectMapper());

	}
	@Override
	public int countProjectWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countProjectWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Project> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getProjectMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateProject executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateProject result = new CandidateProject();
		int pageNo = Math.max(1, query.getPageNo());
		result.setOwnerClass(TextUtil.toCamelCase(query.getOwnerType()));
		result.setOwnerId(query.getOwnerId());
		result.setFilterKey(query.getFilterKey());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName(TextUtil.uncapFirstChar(TextUtil.toCamelCase("displayName")));
		result.setGroupByFieldName(TextUtil.uncapFirstChar(TextUtil.toCamelCase(query.getGroupBy())));

		SmartList candidateList = queryList(sql, parmeters);
		this.alias(candidateList);
		result.setCandidates(candidateList);
		int offSet = (pageNo - 1 ) * query.getPageSize();
		if (candidateList.size() > query.getPageSize()) {
			result.setTotalPage(pageNo+1);
		}else {
			result.setTotalPage(pageNo);
		}
		return result;
	}
	
	

}


