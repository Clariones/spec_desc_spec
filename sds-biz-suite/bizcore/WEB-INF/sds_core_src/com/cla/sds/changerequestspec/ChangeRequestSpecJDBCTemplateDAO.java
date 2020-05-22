
package com.cla.sds.changerequestspec;

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


public class ChangeRequestSpecJDBCTemplateDAO extends SdsBaseDAOImpl implements ChangeRequestSpecDAO{

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
	protected ChangeRequestSpec load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequestSpec(accessKey, options);
	}
	*/
	
	public SmartList<ChangeRequestSpec> loadAll() {
	    return this.loadAll(getChangeRequestSpecMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ChangeRequestSpec load(String id,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequestSpec(ChangeRequestSpecTable.withId(id), options);
	}
	
	
	
	public ChangeRequestSpec save(ChangeRequestSpec changeRequestSpec,Map<String,Object> options){
		
		String methodName="save(ChangeRequestSpec changeRequestSpec,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(changeRequestSpec, methodName, "changeRequestSpec");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalChangeRequestSpec(changeRequestSpec,options);
	}
	public ChangeRequestSpec clone(String changeRequestSpecId, Map<String,Object> options) throws Exception{
	
		return clone(ChangeRequestSpecTable.withId(changeRequestSpecId),options);
	}
	
	protected ChangeRequestSpec clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String changeRequestSpecId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ChangeRequestSpec newChangeRequestSpec = loadInternalChangeRequestSpec(accessKey, options);
		newChangeRequestSpec.setVersion(0);
		
		

		
		saveInternalChangeRequestSpec(newChangeRequestSpec,options);
		
		return newChangeRequestSpec;
	}
	
	
	
	

	protected void throwIfHasException(String changeRequestSpecId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ChangeRequestSpecVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ChangeRequestSpecNotFoundException(
					"The " + this.getTableName() + "(" + changeRequestSpecId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String changeRequestSpecId, int version) throws Exception{
	
		String methodName="delete(String changeRequestSpecId, int version)";
		assertMethodArgumentNotNull(changeRequestSpecId, methodName, "changeRequestSpecId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{changeRequestSpecId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(changeRequestSpecId,version);
		}
		
	
	}
	
	
	
	
	

	public ChangeRequestSpec disconnectFromAll(String changeRequestSpecId, int version) throws Exception{
	
		
		ChangeRequestSpec changeRequestSpec = loadInternalChangeRequestSpec(ChangeRequestSpecTable.withId(changeRequestSpecId), emptyOptions());
		changeRequestSpec.clearFromAll();
		this.saveChangeRequestSpec(changeRequestSpec);
		return changeRequestSpec;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ChangeRequestSpecTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "change_request_spec";
	}
	@Override
	protected String getBeanName() {
		
		return "changeRequestSpec";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ChangeRequestSpecTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractOwnerEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestSpecTokens.OWNER);
 	}

 	protected boolean isSaveOwnerEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestSpecTokens.OWNER);
 	}
 	

 	
  

 	protected boolean isExtractProjectEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestSpecTokens.PROJECT);
 	}

 	protected boolean isSaveProjectEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestSpecTokens.PROJECT);
 	}
 	

 	
 
		

	

	protected ChangeRequestSpecMapper getChangeRequestSpecMapper(){
		return new ChangeRequestSpecMapper();
	}

	
	
	protected ChangeRequestSpec extractChangeRequestSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ChangeRequestSpec changeRequestSpec = loadSingleObject(accessKey, getChangeRequestSpecMapper());
			return changeRequestSpec;
		}catch(EmptyResultDataAccessException e){
			throw new ChangeRequestSpecNotFoundException("ChangeRequestSpec("+accessKey+") is not found!");
		}

	}

	
	

	protected ChangeRequestSpec loadInternalChangeRequestSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ChangeRequestSpec changeRequestSpec = extractChangeRequestSpec(accessKey, loadOptions);
 	
 		if(isExtractOwnerEnabled(loadOptions)){
	 		extractOwner(changeRequestSpec, loadOptions);
 		}
  	
 		if(isExtractProjectEnabled(loadOptions)){
	 		extractProject(changeRequestSpec, loadOptions);
 		}
 
		
		return changeRequestSpec;
		
	}

	 

 	protected ChangeRequestSpec extractOwner(ChangeRequestSpec changeRequestSpec, Map<String,Object> options) throws Exception{

		if(changeRequestSpec.getOwner() == null){
			return changeRequestSpec;
		}
		String ownerId = changeRequestSpec.getOwner().getId();
		if( ownerId == null){
			return changeRequestSpec;
		}
		User owner = getUserDAO().load(ownerId,options);
		if(owner != null){
			changeRequestSpec.setOwner(owner);
		}
		
 		
 		return changeRequestSpec;
 	}
 		
  

 	protected ChangeRequestSpec extractProject(ChangeRequestSpec changeRequestSpec, Map<String,Object> options) throws Exception{

		if(changeRequestSpec.getProject() == null){
			return changeRequestSpec;
		}
		String projectId = changeRequestSpec.getProject().getId();
		if( projectId == null){
			return changeRequestSpec;
		}
		Project project = getProjectDAO().load(projectId,options);
		if(project != null){
			changeRequestSpec.setProject(project);
		}
		
 		
 		return changeRequestSpec;
 	}
 		
 
		
		
  	
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByOwner(String userId,Map<String,Object> options){
 	
  		SmartList<ChangeRequestSpec> resultList = queryWith(ChangeRequestSpecTable.COLUMN_OWNER, userId, options, getChangeRequestSpecMapper());
		// analyzeChangeRequestSpecByOwner(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByOwner(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequestSpec> resultList =  queryWithRange(ChangeRequestSpecTable.COLUMN_OWNER, userId, options, getChangeRequestSpecMapper(), start, count);
 		//analyzeChangeRequestSpecByOwner(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestSpecByOwner(SmartList<ChangeRequestSpec> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequestSpec.OWNER_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestSpecByOwner(String userId,Map<String,Object> options){

 		return countWith(ChangeRequestSpecTable.COLUMN_OWNER, userId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestSpecByOwnerIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestSpecTable.COLUMN_OWNER, ids, options);
	}
 	
  	
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByProject(String projectId,Map<String,Object> options){
 	
  		SmartList<ChangeRequestSpec> resultList = queryWith(ChangeRequestSpecTable.COLUMN_PROJECT, projectId, options, getChangeRequestSpecMapper());
		// analyzeChangeRequestSpecByProject(resultList, projectId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByProject(String projectId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequestSpec> resultList =  queryWithRange(ChangeRequestSpecTable.COLUMN_PROJECT, projectId, options, getChangeRequestSpecMapper(), start, count);
 		//analyzeChangeRequestSpecByProject(resultList, projectId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestSpecByProject(SmartList<ChangeRequestSpec> resultList, String projectId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequestSpec.PROJECT_PROPERTY, projectId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestSpecByProject(String projectId,Map<String,Object> options){

 		return countWith(ChangeRequestSpecTable.COLUMN_PROJECT, projectId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestSpecByProjectIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestSpecTable.COLUMN_PROJECT, ids, options);
	}
 	
 	
		
		
		

	

	protected ChangeRequestSpec saveChangeRequestSpec(ChangeRequestSpec  changeRequestSpec){
		
		if(!changeRequestSpec.isChanged()){
			return changeRequestSpec;
		}
		
		
		String SQL=this.getSaveChangeRequestSpecSQL(changeRequestSpec);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveChangeRequestSpecParameters(changeRequestSpec);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		changeRequestSpec.incVersion();
		return changeRequestSpec;
	
	}
	public SmartList<ChangeRequestSpec> saveChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitChangeRequestSpecList(changeRequestSpecList);
		
		batchChangeRequestSpecCreate((List<ChangeRequestSpec>)lists[CREATE_LIST_INDEX]);
		
		batchChangeRequestSpecUpdate((List<ChangeRequestSpec>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ChangeRequestSpec changeRequestSpec:changeRequestSpecList){
			if(changeRequestSpec.isChanged()){
				changeRequestSpec.incVersion();
			}
			
		
		}
		
		
		return changeRequestSpecList;
	}

	public SmartList<ChangeRequestSpec> removeChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList,Map<String,Object> options){
		
		
		super.removeList(changeRequestSpecList, options);
		
		return changeRequestSpecList;
		
		
	}
	
	protected List<Object[]> prepareChangeRequestSpecBatchCreateArgs(List<ChangeRequestSpec> changeRequestSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequestSpec changeRequestSpec:changeRequestSpecList ){
			Object [] parameters = prepareChangeRequestSpecCreateParameters(changeRequestSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareChangeRequestSpecBatchUpdateArgs(List<ChangeRequestSpec> changeRequestSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequestSpec changeRequestSpec:changeRequestSpecList ){
			if(!changeRequestSpec.isChanged()){
				continue;
			}
			Object [] parameters = prepareChangeRequestSpecUpdateParameters(changeRequestSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchChangeRequestSpecCreate(List<ChangeRequestSpec> changeRequestSpecList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareChangeRequestSpecBatchCreateArgs(changeRequestSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchChangeRequestSpecUpdate(List<ChangeRequestSpec> changeRequestSpecList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareChangeRequestSpecBatchUpdateArgs(changeRequestSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitChangeRequestSpecList(List<ChangeRequestSpec> changeRequestSpecList){
		
		List<ChangeRequestSpec> changeRequestSpecCreateList=new ArrayList<ChangeRequestSpec>();
		List<ChangeRequestSpec> changeRequestSpecUpdateList=new ArrayList<ChangeRequestSpec>();
		
		for(ChangeRequestSpec changeRequestSpec: changeRequestSpecList){
			if(isUpdateRequest(changeRequestSpec)){
				changeRequestSpecUpdateList.add( changeRequestSpec);
				continue;
			}
			changeRequestSpecCreateList.add(changeRequestSpec);
		}
		
		return new Object[]{changeRequestSpecCreateList,changeRequestSpecUpdateList};
	}
	
	protected boolean isUpdateRequest(ChangeRequestSpec changeRequestSpec){
 		return changeRequestSpec.getVersion() > 0;
 	}
 	protected String getSaveChangeRequestSpecSQL(ChangeRequestSpec changeRequestSpec){
 		if(isUpdateRequest(changeRequestSpec)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveChangeRequestSpecParameters(ChangeRequestSpec changeRequestSpec){
 		if(isUpdateRequest(changeRequestSpec) ){
 			return prepareChangeRequestSpecUpdateParameters(changeRequestSpec);
 		}
 		return prepareChangeRequestSpecCreateParameters(changeRequestSpec);
 	}
 	protected Object[] prepareChangeRequestSpecUpdateParameters(ChangeRequestSpec changeRequestSpec){
 		Object[] parameters = new Object[7];
 
 		
 		parameters[0] = changeRequestSpec.getScene();
 		
 		
 		parameters[1] = changeRequestSpec.getBrief();
 		 	
 		if(changeRequestSpec.getOwner() != null){
 			parameters[2] = changeRequestSpec.getOwner().getId();
 		}
  	
 		if(changeRequestSpec.getProject() != null){
 			parameters[3] = changeRequestSpec.getProject().getId();
 		}
 		
 		parameters[4] = changeRequestSpec.nextVersion();
 		parameters[5] = changeRequestSpec.getId();
 		parameters[6] = changeRequestSpec.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareChangeRequestSpecCreateParameters(ChangeRequestSpec changeRequestSpec){
		Object[] parameters = new Object[5];
		String newChangeRequestSpecId=getNextId();
		changeRequestSpec.setId(newChangeRequestSpecId);
		parameters[0] =  changeRequestSpec.getId();
 
 		
 		parameters[1] = changeRequestSpec.getScene();
 		
 		
 		parameters[2] = changeRequestSpec.getBrief();
 		 	
 		if(changeRequestSpec.getOwner() != null){
 			parameters[3] = changeRequestSpec.getOwner().getId();
 		
 		}
 		 	
 		if(changeRequestSpec.getProject() != null){
 			parameters[4] = changeRequestSpec.getProject().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ChangeRequestSpec saveInternalChangeRequestSpec(ChangeRequestSpec changeRequestSpec, Map<String,Object> options){
		
		saveChangeRequestSpec(changeRequestSpec);
 	
 		if(isSaveOwnerEnabled(options)){
	 		saveOwner(changeRequestSpec, options);
 		}
  	
 		if(isSaveProjectEnabled(options)){
	 		saveProject(changeRequestSpec, options);
 		}
 
		
		return changeRequestSpec;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ChangeRequestSpec saveOwner(ChangeRequestSpec changeRequestSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequestSpec.getOwner() == null){
 			return changeRequestSpec;//do nothing when it is null
 		}
 		
 		getUserDAO().save(changeRequestSpec.getOwner(),options);
 		return changeRequestSpec;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ChangeRequestSpec saveProject(ChangeRequestSpec changeRequestSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequestSpec.getProject() == null){
 			return changeRequestSpec;//do nothing when it is null
 		}
 		
 		getProjectDAO().save(changeRequestSpec.getProject(),options);
 		return changeRequestSpec;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public ChangeRequestSpec present(ChangeRequestSpec changeRequestSpec,Map<String, Object> options){
	

		return changeRequestSpec;
	
	}
		

	

	protected String getTableName(){
		return ChangeRequestSpecTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ChangeRequestSpec> changeRequestSpecList) {		
		this.enhanceListInternal(changeRequestSpecList, this.getChangeRequestSpecMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<ChangeRequestSpec> changeRequestSpecList = ownerEntity.collectRefsWithType(ChangeRequestSpec.INTERNAL_TYPE);
		this.enhanceList(changeRequestSpecList);
		
	}
	
	@Override
	public SmartList<ChangeRequestSpec> findChangeRequestSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getChangeRequestSpecMapper());

	}
	@Override
	public int countChangeRequestSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countChangeRequestSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ChangeRequestSpec> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getChangeRequestSpecMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateChangeRequestSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateChangeRequestSpec result = new CandidateChangeRequestSpec();
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


