
package com.cla.sds.userproject;

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


import com.cla.sds.user.User;
import com.cla.sds.project.Project;

import com.cla.sds.user.UserDAO;
import com.cla.sds.project.ProjectDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class UserProjectJDBCTemplateDAO extends SdsBaseDAOImpl implements UserProjectDAO{

	protected UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO){
 	
 		if(userDAO == null){
 			throw new IllegalStateException("Do not try to set userDAO to null.");
 		}
	 	this.userDAO = userDAO;
 	}
 	public UserDAO getUserDAO(){
 		if(this.userDAO == null){
 			throw new IllegalStateException("The userDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.userDAO;
 	}	

	protected ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO){
 	
 		if(projectDAO == null){
 			throw new IllegalStateException("Do not try to set projectDAO to null.");
 		}
	 	this.projectDAO = projectDAO;
 	}
 	public ProjectDAO getProjectDAO(){
 		if(this.projectDAO == null){
 			throw new IllegalStateException("The projectDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.projectDAO;
 	}	

	
	/*
	protected UserProject load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalUserProject(accessKey, options);
	}
	*/
	
	public SmartList<UserProject> loadAll() {
	    return this.loadAll(getUserProjectMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public UserProject load(String id,Map<String,Object> options) throws Exception{
		return loadInternalUserProject(UserProjectTable.withId(id), options);
	}
	
	
	
	public UserProject save(UserProject userProject,Map<String,Object> options){
		
		String methodName="save(UserProject userProject,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(userProject, methodName, "userProject");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalUserProject(userProject,options);
	}
	public UserProject clone(String userProjectId, Map<String,Object> options) throws Exception{
	
		return clone(UserProjectTable.withId(userProjectId),options);
	}
	
	protected UserProject clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String userProjectId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		UserProject newUserProject = loadInternalUserProject(accessKey, options);
		newUserProject.setVersion(0);
		
		

		
		saveInternalUserProject(newUserProject,options);
		
		return newUserProject;
	}
	
	
	
	

	protected void throwIfHasException(String userProjectId,int version,int count) throws Exception{
		if (count == 1) {
			throw new UserProjectVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new UserProjectNotFoundException(
					"The " + this.getTableName() + "(" + userProjectId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String userProjectId, int version) throws Exception{
	
		String methodName="delete(String userProjectId, int version)";
		assertMethodArgumentNotNull(userProjectId, methodName, "userProjectId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{userProjectId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(userProjectId,version);
		}
		
	
	}
	
	
	
	
	

	public UserProject disconnectFromAll(String userProjectId, int version) throws Exception{
	
		
		UserProject userProject = loadInternalUserProject(UserProjectTable.withId(userProjectId), emptyOptions());
		userProject.clearFromAll();
		this.saveUserProject(userProject);
		return userProject;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return UserProjectTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "user_project";
	}
	@Override
	protected String getBeanName() {
		
		return "userProject";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return UserProjectTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractUserEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, UserProjectTokens.USER);
 	}

 	protected boolean isSaveUserEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, UserProjectTokens.USER);
 	}
 	

 	
  

 	protected boolean isExtractProjectEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, UserProjectTokens.PROJECT);
 	}

 	protected boolean isSaveProjectEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, UserProjectTokens.PROJECT);
 	}
 	

 	
 
		

	

	protected UserProjectMapper getUserProjectMapper(){
		return new UserProjectMapper();
	}

	
	
	protected UserProject extractUserProject(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			UserProject userProject = loadSingleObject(accessKey, getUserProjectMapper());
			return userProject;
		}catch(EmptyResultDataAccessException e){
			throw new UserProjectNotFoundException("UserProject("+accessKey+") is not found!");
		}

	}

	
	

	protected UserProject loadInternalUserProject(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		UserProject userProject = extractUserProject(accessKey, loadOptions);
 	
 		if(isExtractUserEnabled(loadOptions)){
	 		extractUser(userProject, loadOptions);
 		}
  	
 		if(isExtractProjectEnabled(loadOptions)){
	 		extractProject(userProject, loadOptions);
 		}
 
		
		return userProject;
		
	}

	 

 	protected UserProject extractUser(UserProject userProject, Map<String,Object> options) throws Exception{

		if(userProject.getUser() == null){
			return userProject;
		}
		String userId = userProject.getUser().getId();
		if( userId == null){
			return userProject;
		}
		User user = getUserDAO().load(userId,options);
		if(user != null){
			userProject.setUser(user);
		}
		
 		
 		return userProject;
 	}
 		
  

 	protected UserProject extractProject(UserProject userProject, Map<String,Object> options) throws Exception{

		if(userProject.getProject() == null){
			return userProject;
		}
		String projectId = userProject.getProject().getId();
		if( projectId == null){
			return userProject;
		}
		Project project = getProjectDAO().load(projectId,options);
		if(project != null){
			userProject.setProject(project);
		}
		
 		
 		return userProject;
 	}
 		
 
		
		
  	
 	public SmartList<UserProject> findUserProjectByUser(String userId,Map<String,Object> options){
 	
  		SmartList<UserProject> resultList = queryWith(UserProjectTable.COLUMN_USER, userId, options, getUserProjectMapper());
		// analyzeUserProjectByUser(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<UserProject> findUserProjectByUser(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<UserProject> resultList =  queryWithRange(UserProjectTable.COLUMN_USER, userId, options, getUserProjectMapper(), start, count);
 		//analyzeUserProjectByUser(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzeUserProjectByUser(SmartList<UserProject> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(UserProject.USER_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//UserProject.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("用户项目");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(UserProject.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(UserProject.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countUserProjectByUser(String userId,Map<String,Object> options){

 		return countWith(UserProjectTable.COLUMN_USER, userId, options);
 	}
 	@Override
	public Map<String, Integer> countUserProjectByUserIds(String[] ids, Map<String, Object> options) {
		return countWithIds(UserProjectTable.COLUMN_USER, ids, options);
	}
 	
  	
 	public SmartList<UserProject> findUserProjectByProject(String projectId,Map<String,Object> options){
 	
  		SmartList<UserProject> resultList = queryWith(UserProjectTable.COLUMN_PROJECT, projectId, options, getUserProjectMapper());
		// analyzeUserProjectByProject(resultList, projectId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<UserProject> findUserProjectByProject(String projectId, int start, int count,Map<String,Object> options){
 		
 		SmartList<UserProject> resultList =  queryWithRange(UserProjectTable.COLUMN_PROJECT, projectId, options, getUserProjectMapper(), start, count);
 		//analyzeUserProjectByProject(resultList, projectId, options);
 		return resultList;
 		
 	}
 	public void analyzeUserProjectByProject(SmartList<UserProject> resultList, String projectId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(UserProject.PROJECT_PROPERTY, projectId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//UserProject.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("用户项目");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(UserProject.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(UserProject.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countUserProjectByProject(String projectId,Map<String,Object> options){

 		return countWith(UserProjectTable.COLUMN_PROJECT, projectId, options);
 	}
 	@Override
	public Map<String, Integer> countUserProjectByProjectIds(String[] ids, Map<String, Object> options) {
		return countWithIds(UserProjectTable.COLUMN_PROJECT, ids, options);
	}
 	
 	
		
		
		

	

	protected UserProject saveUserProject(UserProject  userProject){
		
		if(!userProject.isChanged()){
			return userProject;
		}
		
		
		String SQL=this.getSaveUserProjectSQL(userProject);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveUserProjectParameters(userProject);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		userProject.incVersion();
		return userProject;
	
	}
	public SmartList<UserProject> saveUserProjectList(SmartList<UserProject> userProjectList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitUserProjectList(userProjectList);
		
		batchUserProjectCreate((List<UserProject>)lists[CREATE_LIST_INDEX]);
		
		batchUserProjectUpdate((List<UserProject>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(UserProject userProject:userProjectList){
			if(userProject.isChanged()){
				userProject.incVersion();
			}
			
		
		}
		
		
		return userProjectList;
	}

	public SmartList<UserProject> removeUserProjectList(SmartList<UserProject> userProjectList,Map<String,Object> options){
		
		
		super.removeList(userProjectList, options);
		
		return userProjectList;
		
		
	}
	
	protected List<Object[]> prepareUserProjectBatchCreateArgs(List<UserProject> userProjectList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(UserProject userProject:userProjectList ){
			Object [] parameters = prepareUserProjectCreateParameters(userProject);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareUserProjectBatchUpdateArgs(List<UserProject> userProjectList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(UserProject userProject:userProjectList ){
			if(!userProject.isChanged()){
				continue;
			}
			Object [] parameters = prepareUserProjectUpdateParameters(userProject);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchUserProjectCreate(List<UserProject> userProjectList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareUserProjectBatchCreateArgs(userProjectList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchUserProjectUpdate(List<UserProject> userProjectList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareUserProjectBatchUpdateArgs(userProjectList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitUserProjectList(List<UserProject> userProjectList){
		
		List<UserProject> userProjectCreateList=new ArrayList<UserProject>();
		List<UserProject> userProjectUpdateList=new ArrayList<UserProject>();
		
		for(UserProject userProject: userProjectList){
			if(isUpdateRequest(userProject)){
				userProjectUpdateList.add( userProject);
				continue;
			}
			userProjectCreateList.add(userProject);
		}
		
		return new Object[]{userProjectCreateList,userProjectUpdateList};
	}
	
	protected boolean isUpdateRequest(UserProject userProject){
 		return userProject.getVersion() > 0;
 	}
 	protected String getSaveUserProjectSQL(UserProject userProject){
 		if(isUpdateRequest(userProject)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveUserProjectParameters(UserProject userProject){
 		if(isUpdateRequest(userProject) ){
 			return prepareUserProjectUpdateParameters(userProject);
 		}
 		return prepareUserProjectCreateParameters(userProject);
 	}
 	protected Object[] prepareUserProjectUpdateParameters(UserProject userProject){
 		Object[] parameters = new Object[7];
  	
 		if(userProject.getUser() != null){
 			parameters[0] = userProject.getUser().getId();
 		}
  	
 		if(userProject.getProject() != null){
 			parameters[1] = userProject.getProject().getId();
 		}
 
 		
 		parameters[2] = userProject.getCreateTime();
 		
 		
 		parameters[3] = userProject.getLastUpdateTime();
 				
 		parameters[4] = userProject.nextVersion();
 		parameters[5] = userProject.getId();
 		parameters[6] = userProject.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareUserProjectCreateParameters(UserProject userProject){
		Object[] parameters = new Object[5];
		String newUserProjectId=getNextId();
		userProject.setId(newUserProjectId);
		parameters[0] =  userProject.getId();
  	
 		if(userProject.getUser() != null){
 			parameters[1] = userProject.getUser().getId();
 		
 		}
 		 	
 		if(userProject.getProject() != null){
 			parameters[2] = userProject.getProject().getId();
 		
 		}
 		
 		
 		parameters[3] = userProject.getCreateTime();
 		
 		
 		parameters[4] = userProject.getLastUpdateTime();
 				
 				
 		return parameters;
 	}
 	
	protected UserProject saveInternalUserProject(UserProject userProject, Map<String,Object> options){
		
		saveUserProject(userProject);
 	
 		if(isSaveUserEnabled(options)){
	 		saveUser(userProject, options);
 		}
  	
 		if(isSaveProjectEnabled(options)){
	 		saveProject(userProject, options);
 		}
 
		
		return userProject;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected UserProject saveUser(UserProject userProject, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(userProject.getUser() == null){
 			return userProject;//do nothing when it is null
 		}
 		
 		getUserDAO().save(userProject.getUser(),options);
 		return userProject;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected UserProject saveProject(UserProject userProject, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(userProject.getProject() == null){
 			return userProject;//do nothing when it is null
 		}
 		
 		getProjectDAO().save(userProject.getProject(),options);
 		return userProject;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public UserProject present(UserProject userProject,Map<String, Object> options){
	

		return userProject;
	
	}
		

	

	protected String getTableName(){
		return UserProjectTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<UserProject> userProjectList) {		
		this.enhanceListInternal(userProjectList, this.getUserProjectMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<UserProject> userProjectList = ownerEntity.collectRefsWithType(UserProject.INTERNAL_TYPE);
		this.enhanceList(userProjectList);
		
	}
	
	@Override
	public SmartList<UserProject> findUserProjectWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getUserProjectMapper());

	}
	@Override
	public int countUserProjectWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countUserProjectWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<UserProject> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getUserProjectMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateUserProject executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateUserProject result = new CandidateUserProject();
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


