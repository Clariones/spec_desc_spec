
package com.cla.sds.workflowspec;

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


public class WorkFlowSpecJDBCTemplateDAO extends SdsBaseDAOImpl implements WorkFlowSpecDAO{

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
	protected WorkFlowSpec load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalWorkFlowSpec(accessKey, options);
	}
	*/
	
	public SmartList<WorkFlowSpec> loadAll() {
	    return this.loadAll(getWorkFlowSpecMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public WorkFlowSpec load(String id,Map<String,Object> options) throws Exception{
		return loadInternalWorkFlowSpec(WorkFlowSpecTable.withId(id), options);
	}
	
	
	
	public WorkFlowSpec save(WorkFlowSpec workFlowSpec,Map<String,Object> options){
		
		String methodName="save(WorkFlowSpec workFlowSpec,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(workFlowSpec, methodName, "workFlowSpec");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalWorkFlowSpec(workFlowSpec,options);
	}
	public WorkFlowSpec clone(String workFlowSpecId, Map<String,Object> options) throws Exception{
	
		return clone(WorkFlowSpecTable.withId(workFlowSpecId),options);
	}
	
	protected WorkFlowSpec clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String workFlowSpecId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		WorkFlowSpec newWorkFlowSpec = loadInternalWorkFlowSpec(accessKey, options);
		newWorkFlowSpec.setVersion(0);
		
		

		
		saveInternalWorkFlowSpec(newWorkFlowSpec,options);
		
		return newWorkFlowSpec;
	}
	
	
	
	

	protected void throwIfHasException(String workFlowSpecId,int version,int count) throws Exception{
		if (count == 1) {
			throw new WorkFlowSpecVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new WorkFlowSpecNotFoundException(
					"The " + this.getTableName() + "(" + workFlowSpecId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String workFlowSpecId, int version) throws Exception{
	
		String methodName="delete(String workFlowSpecId, int version)";
		assertMethodArgumentNotNull(workFlowSpecId, methodName, "workFlowSpecId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{workFlowSpecId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(workFlowSpecId,version);
		}
		
	
	}
	
	
	
	
	

	public WorkFlowSpec disconnectFromAll(String workFlowSpecId, int version) throws Exception{
	
		
		WorkFlowSpec workFlowSpec = loadInternalWorkFlowSpec(WorkFlowSpecTable.withId(workFlowSpecId), emptyOptions());
		workFlowSpec.clearFromAll();
		this.saveWorkFlowSpec(workFlowSpec);
		return workFlowSpec;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return WorkFlowSpecTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "work_flow_spec";
	}
	@Override
	protected String getBeanName() {
		
		return "workFlowSpec";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return WorkFlowSpecTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractOwnerEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, WorkFlowSpecTokens.OWNER);
 	}

 	protected boolean isSaveOwnerEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, WorkFlowSpecTokens.OWNER);
 	}
 	

 	
  

 	protected boolean isExtractProjectEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, WorkFlowSpecTokens.PROJECT);
 	}

 	protected boolean isSaveProjectEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, WorkFlowSpecTokens.PROJECT);
 	}
 	

 	
 
		

	

	protected WorkFlowSpecMapper getWorkFlowSpecMapper(){
		return new WorkFlowSpecMapper();
	}

	
	
	protected WorkFlowSpec extractWorkFlowSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			WorkFlowSpec workFlowSpec = loadSingleObject(accessKey, getWorkFlowSpecMapper());
			return workFlowSpec;
		}catch(EmptyResultDataAccessException e){
			throw new WorkFlowSpecNotFoundException("WorkFlowSpec("+accessKey+") is not found!");
		}

	}

	
	

	protected WorkFlowSpec loadInternalWorkFlowSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		WorkFlowSpec workFlowSpec = extractWorkFlowSpec(accessKey, loadOptions);
 	
 		if(isExtractOwnerEnabled(loadOptions)){
	 		extractOwner(workFlowSpec, loadOptions);
 		}
  	
 		if(isExtractProjectEnabled(loadOptions)){
	 		extractProject(workFlowSpec, loadOptions);
 		}
 
		
		return workFlowSpec;
		
	}

	 

 	protected WorkFlowSpec extractOwner(WorkFlowSpec workFlowSpec, Map<String,Object> options) throws Exception{

		if(workFlowSpec.getOwner() == null){
			return workFlowSpec;
		}
		String ownerId = workFlowSpec.getOwner().getId();
		if( ownerId == null){
			return workFlowSpec;
		}
		User owner = getUserDAO().load(ownerId,options);
		if(owner != null){
			workFlowSpec.setOwner(owner);
		}
		
 		
 		return workFlowSpec;
 	}
 		
  

 	protected WorkFlowSpec extractProject(WorkFlowSpec workFlowSpec, Map<String,Object> options) throws Exception{

		if(workFlowSpec.getProject() == null){
			return workFlowSpec;
		}
		String projectId = workFlowSpec.getProject().getId();
		if( projectId == null){
			return workFlowSpec;
		}
		Project project = getProjectDAO().load(projectId,options);
		if(project != null){
			workFlowSpec.setProject(project);
		}
		
 		
 		return workFlowSpec;
 	}
 		
 
		
		
  	
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByOwner(String userId,Map<String,Object> options){
 	
  		SmartList<WorkFlowSpec> resultList = queryWith(WorkFlowSpecTable.COLUMN_OWNER, userId, options, getWorkFlowSpecMapper());
		// analyzeWorkFlowSpecByOwner(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByOwner(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<WorkFlowSpec> resultList =  queryWithRange(WorkFlowSpecTable.COLUMN_OWNER, userId, options, getWorkFlowSpecMapper(), start, count);
 		//analyzeWorkFlowSpecByOwner(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzeWorkFlowSpecByOwner(SmartList<WorkFlowSpec> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(WorkFlowSpec.OWNER_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countWorkFlowSpecByOwner(String userId,Map<String,Object> options){

 		return countWith(WorkFlowSpecTable.COLUMN_OWNER, userId, options);
 	}
 	@Override
	public Map<String, Integer> countWorkFlowSpecByOwnerIds(String[] ids, Map<String, Object> options) {
		return countWithIds(WorkFlowSpecTable.COLUMN_OWNER, ids, options);
	}
 	
  	
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByProject(String projectId,Map<String,Object> options){
 	
  		SmartList<WorkFlowSpec> resultList = queryWith(WorkFlowSpecTable.COLUMN_PROJECT, projectId, options, getWorkFlowSpecMapper());
		// analyzeWorkFlowSpecByProject(resultList, projectId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByProject(String projectId, int start, int count,Map<String,Object> options){
 		
 		SmartList<WorkFlowSpec> resultList =  queryWithRange(WorkFlowSpecTable.COLUMN_PROJECT, projectId, options, getWorkFlowSpecMapper(), start, count);
 		//analyzeWorkFlowSpecByProject(resultList, projectId, options);
 		return resultList;
 		
 	}
 	public void analyzeWorkFlowSpecByProject(SmartList<WorkFlowSpec> resultList, String projectId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(WorkFlowSpec.PROJECT_PROPERTY, projectId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countWorkFlowSpecByProject(String projectId,Map<String,Object> options){

 		return countWith(WorkFlowSpecTable.COLUMN_PROJECT, projectId, options);
 	}
 	@Override
	public Map<String, Integer> countWorkFlowSpecByProjectIds(String[] ids, Map<String, Object> options) {
		return countWithIds(WorkFlowSpecTable.COLUMN_PROJECT, ids, options);
	}
 	
 	
		
		
		

	

	protected WorkFlowSpec saveWorkFlowSpec(WorkFlowSpec  workFlowSpec){
		
		if(!workFlowSpec.isChanged()){
			return workFlowSpec;
		}
		
		
		String SQL=this.getSaveWorkFlowSpecSQL(workFlowSpec);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveWorkFlowSpecParameters(workFlowSpec);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		workFlowSpec.incVersion();
		return workFlowSpec;
	
	}
	public SmartList<WorkFlowSpec> saveWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitWorkFlowSpecList(workFlowSpecList);
		
		batchWorkFlowSpecCreate((List<WorkFlowSpec>)lists[CREATE_LIST_INDEX]);
		
		batchWorkFlowSpecUpdate((List<WorkFlowSpec>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(WorkFlowSpec workFlowSpec:workFlowSpecList){
			if(workFlowSpec.isChanged()){
				workFlowSpec.incVersion();
			}
			
		
		}
		
		
		return workFlowSpecList;
	}

	public SmartList<WorkFlowSpec> removeWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList,Map<String,Object> options){
		
		
		super.removeList(workFlowSpecList, options);
		
		return workFlowSpecList;
		
		
	}
	
	protected List<Object[]> prepareWorkFlowSpecBatchCreateArgs(List<WorkFlowSpec> workFlowSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(WorkFlowSpec workFlowSpec:workFlowSpecList ){
			Object [] parameters = prepareWorkFlowSpecCreateParameters(workFlowSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareWorkFlowSpecBatchUpdateArgs(List<WorkFlowSpec> workFlowSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(WorkFlowSpec workFlowSpec:workFlowSpecList ){
			if(!workFlowSpec.isChanged()){
				continue;
			}
			Object [] parameters = prepareWorkFlowSpecUpdateParameters(workFlowSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchWorkFlowSpecCreate(List<WorkFlowSpec> workFlowSpecList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareWorkFlowSpecBatchCreateArgs(workFlowSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchWorkFlowSpecUpdate(List<WorkFlowSpec> workFlowSpecList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareWorkFlowSpecBatchUpdateArgs(workFlowSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitWorkFlowSpecList(List<WorkFlowSpec> workFlowSpecList){
		
		List<WorkFlowSpec> workFlowSpecCreateList=new ArrayList<WorkFlowSpec>();
		List<WorkFlowSpec> workFlowSpecUpdateList=new ArrayList<WorkFlowSpec>();
		
		for(WorkFlowSpec workFlowSpec: workFlowSpecList){
			if(isUpdateRequest(workFlowSpec)){
				workFlowSpecUpdateList.add( workFlowSpec);
				continue;
			}
			workFlowSpecCreateList.add(workFlowSpec);
		}
		
		return new Object[]{workFlowSpecCreateList,workFlowSpecUpdateList};
	}
	
	protected boolean isUpdateRequest(WorkFlowSpec workFlowSpec){
 		return workFlowSpec.getVersion() > 0;
 	}
 	protected String getSaveWorkFlowSpecSQL(WorkFlowSpec workFlowSpec){
 		if(isUpdateRequest(workFlowSpec)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveWorkFlowSpecParameters(WorkFlowSpec workFlowSpec){
 		if(isUpdateRequest(workFlowSpec) ){
 			return prepareWorkFlowSpecUpdateParameters(workFlowSpec);
 		}
 		return prepareWorkFlowSpecCreateParameters(workFlowSpec);
 	}
 	protected Object[] prepareWorkFlowSpecUpdateParameters(WorkFlowSpec workFlowSpec){
 		Object[] parameters = new Object[7];
 
 		
 		parameters[0] = workFlowSpec.getScene();
 		
 		
 		parameters[1] = workFlowSpec.getBrief();
 		 	
 		if(workFlowSpec.getOwner() != null){
 			parameters[2] = workFlowSpec.getOwner().getId();
 		}
  	
 		if(workFlowSpec.getProject() != null){
 			parameters[3] = workFlowSpec.getProject().getId();
 		}
 		
 		parameters[4] = workFlowSpec.nextVersion();
 		parameters[5] = workFlowSpec.getId();
 		parameters[6] = workFlowSpec.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareWorkFlowSpecCreateParameters(WorkFlowSpec workFlowSpec){
		Object[] parameters = new Object[5];
		String newWorkFlowSpecId=getNextId();
		workFlowSpec.setId(newWorkFlowSpecId);
		parameters[0] =  workFlowSpec.getId();
 
 		
 		parameters[1] = workFlowSpec.getScene();
 		
 		
 		parameters[2] = workFlowSpec.getBrief();
 		 	
 		if(workFlowSpec.getOwner() != null){
 			parameters[3] = workFlowSpec.getOwner().getId();
 		
 		}
 		 	
 		if(workFlowSpec.getProject() != null){
 			parameters[4] = workFlowSpec.getProject().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected WorkFlowSpec saveInternalWorkFlowSpec(WorkFlowSpec workFlowSpec, Map<String,Object> options){
		
		saveWorkFlowSpec(workFlowSpec);
 	
 		if(isSaveOwnerEnabled(options)){
	 		saveOwner(workFlowSpec, options);
 		}
  	
 		if(isSaveProjectEnabled(options)){
	 		saveProject(workFlowSpec, options);
 		}
 
		
		return workFlowSpec;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected WorkFlowSpec saveOwner(WorkFlowSpec workFlowSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(workFlowSpec.getOwner() == null){
 			return workFlowSpec;//do nothing when it is null
 		}
 		
 		getUserDAO().save(workFlowSpec.getOwner(),options);
 		return workFlowSpec;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected WorkFlowSpec saveProject(WorkFlowSpec workFlowSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(workFlowSpec.getProject() == null){
 			return workFlowSpec;//do nothing when it is null
 		}
 		
 		getProjectDAO().save(workFlowSpec.getProject(),options);
 		return workFlowSpec;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public WorkFlowSpec present(WorkFlowSpec workFlowSpec,Map<String, Object> options){
	

		return workFlowSpec;
	
	}
		

	

	protected String getTableName(){
		return WorkFlowSpecTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<WorkFlowSpec> workFlowSpecList) {		
		this.enhanceListInternal(workFlowSpecList, this.getWorkFlowSpecMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<WorkFlowSpec> workFlowSpecList = ownerEntity.collectRefsWithType(WorkFlowSpec.INTERNAL_TYPE);
		this.enhanceList(workFlowSpecList);
		
	}
	
	@Override
	public SmartList<WorkFlowSpec> findWorkFlowSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getWorkFlowSpecMapper());

	}
	@Override
	public int countWorkFlowSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countWorkFlowSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<WorkFlowSpec> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getWorkFlowSpecMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateWorkFlowSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateWorkFlowSpec result = new CandidateWorkFlowSpec();
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


