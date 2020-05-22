
package com.cla.sds.pageflowspec;

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


public class PageFlowSpecJDBCTemplateDAO extends SdsBaseDAOImpl implements PageFlowSpecDAO{

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
	protected PageFlowSpec load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPageFlowSpec(accessKey, options);
	}
	*/
	
	public SmartList<PageFlowSpec> loadAll() {
	    return this.loadAll(getPageFlowSpecMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public PageFlowSpec load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPageFlowSpec(PageFlowSpecTable.withId(id), options);
	}
	
	
	
	public PageFlowSpec save(PageFlowSpec pageFlowSpec,Map<String,Object> options){
		
		String methodName="save(PageFlowSpec pageFlowSpec,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(pageFlowSpec, methodName, "pageFlowSpec");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPageFlowSpec(pageFlowSpec,options);
	}
	public PageFlowSpec clone(String pageFlowSpecId, Map<String,Object> options) throws Exception{
	
		return clone(PageFlowSpecTable.withId(pageFlowSpecId),options);
	}
	
	protected PageFlowSpec clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String pageFlowSpecId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		PageFlowSpec newPageFlowSpec = loadInternalPageFlowSpec(accessKey, options);
		newPageFlowSpec.setVersion(0);
		
		

		
		saveInternalPageFlowSpec(newPageFlowSpec,options);
		
		return newPageFlowSpec;
	}
	
	
	
	

	protected void throwIfHasException(String pageFlowSpecId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PageFlowSpecVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PageFlowSpecNotFoundException(
					"The " + this.getTableName() + "(" + pageFlowSpecId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String pageFlowSpecId, int version) throws Exception{
	
		String methodName="delete(String pageFlowSpecId, int version)";
		assertMethodArgumentNotNull(pageFlowSpecId, methodName, "pageFlowSpecId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{pageFlowSpecId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(pageFlowSpecId,version);
		}
		
	
	}
	
	
	
	
	

	public PageFlowSpec disconnectFromAll(String pageFlowSpecId, int version) throws Exception{
	
		
		PageFlowSpec pageFlowSpec = loadInternalPageFlowSpec(PageFlowSpecTable.withId(pageFlowSpecId), emptyOptions());
		pageFlowSpec.clearFromAll();
		this.savePageFlowSpec(pageFlowSpec);
		return pageFlowSpec;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PageFlowSpecTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "page_flow_spec";
	}
	@Override
	protected String getBeanName() {
		
		return "pageFlowSpec";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PageFlowSpecTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractOwnerEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PageFlowSpecTokens.OWNER);
 	}

 	protected boolean isSaveOwnerEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PageFlowSpecTokens.OWNER);
 	}
 	

 	
  

 	protected boolean isExtractProjectEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PageFlowSpecTokens.PROJECT);
 	}

 	protected boolean isSaveProjectEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PageFlowSpecTokens.PROJECT);
 	}
 	

 	
 
		

	

	protected PageFlowSpecMapper getPageFlowSpecMapper(){
		return new PageFlowSpecMapper();
	}

	
	
	protected PageFlowSpec extractPageFlowSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			PageFlowSpec pageFlowSpec = loadSingleObject(accessKey, getPageFlowSpecMapper());
			return pageFlowSpec;
		}catch(EmptyResultDataAccessException e){
			throw new PageFlowSpecNotFoundException("PageFlowSpec("+accessKey+") is not found!");
		}

	}

	
	

	protected PageFlowSpec loadInternalPageFlowSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		PageFlowSpec pageFlowSpec = extractPageFlowSpec(accessKey, loadOptions);
 	
 		if(isExtractOwnerEnabled(loadOptions)){
	 		extractOwner(pageFlowSpec, loadOptions);
 		}
  	
 		if(isExtractProjectEnabled(loadOptions)){
	 		extractProject(pageFlowSpec, loadOptions);
 		}
 
		
		return pageFlowSpec;
		
	}

	 

 	protected PageFlowSpec extractOwner(PageFlowSpec pageFlowSpec, Map<String,Object> options) throws Exception{

		if(pageFlowSpec.getOwner() == null){
			return pageFlowSpec;
		}
		String ownerId = pageFlowSpec.getOwner().getId();
		if( ownerId == null){
			return pageFlowSpec;
		}
		User owner = getUserDAO().load(ownerId,options);
		if(owner != null){
			pageFlowSpec.setOwner(owner);
		}
		
 		
 		return pageFlowSpec;
 	}
 		
  

 	protected PageFlowSpec extractProject(PageFlowSpec pageFlowSpec, Map<String,Object> options) throws Exception{

		if(pageFlowSpec.getProject() == null){
			return pageFlowSpec;
		}
		String projectId = pageFlowSpec.getProject().getId();
		if( projectId == null){
			return pageFlowSpec;
		}
		Project project = getProjectDAO().load(projectId,options);
		if(project != null){
			pageFlowSpec.setProject(project);
		}
		
 		
 		return pageFlowSpec;
 	}
 		
 
		
		
  	
 	public SmartList<PageFlowSpec> findPageFlowSpecByOwner(String userId,Map<String,Object> options){
 	
  		SmartList<PageFlowSpec> resultList = queryWith(PageFlowSpecTable.COLUMN_OWNER, userId, options, getPageFlowSpecMapper());
		// analyzePageFlowSpecByOwner(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PageFlowSpec> findPageFlowSpecByOwner(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PageFlowSpec> resultList =  queryWithRange(PageFlowSpecTable.COLUMN_OWNER, userId, options, getPageFlowSpecMapper(), start, count);
 		//analyzePageFlowSpecByOwner(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzePageFlowSpecByOwner(SmartList<PageFlowSpec> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PageFlowSpec.OWNER_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPageFlowSpecByOwner(String userId,Map<String,Object> options){

 		return countWith(PageFlowSpecTable.COLUMN_OWNER, userId, options);
 	}
 	@Override
	public Map<String, Integer> countPageFlowSpecByOwnerIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PageFlowSpecTable.COLUMN_OWNER, ids, options);
	}
 	
  	
 	public SmartList<PageFlowSpec> findPageFlowSpecByProject(String projectId,Map<String,Object> options){
 	
  		SmartList<PageFlowSpec> resultList = queryWith(PageFlowSpecTable.COLUMN_PROJECT, projectId, options, getPageFlowSpecMapper());
		// analyzePageFlowSpecByProject(resultList, projectId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PageFlowSpec> findPageFlowSpecByProject(String projectId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PageFlowSpec> resultList =  queryWithRange(PageFlowSpecTable.COLUMN_PROJECT, projectId, options, getPageFlowSpecMapper(), start, count);
 		//analyzePageFlowSpecByProject(resultList, projectId, options);
 		return resultList;
 		
 	}
 	public void analyzePageFlowSpecByProject(SmartList<PageFlowSpec> resultList, String projectId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PageFlowSpec.PROJECT_PROPERTY, projectId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPageFlowSpecByProject(String projectId,Map<String,Object> options){

 		return countWith(PageFlowSpecTable.COLUMN_PROJECT, projectId, options);
 	}
 	@Override
	public Map<String, Integer> countPageFlowSpecByProjectIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PageFlowSpecTable.COLUMN_PROJECT, ids, options);
	}
 	
 	
		
		
		

	

	protected PageFlowSpec savePageFlowSpec(PageFlowSpec  pageFlowSpec){
		
		if(!pageFlowSpec.isChanged()){
			return pageFlowSpec;
		}
		
		
		String SQL=this.getSavePageFlowSpecSQL(pageFlowSpec);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePageFlowSpecParameters(pageFlowSpec);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		pageFlowSpec.incVersion();
		return pageFlowSpec;
	
	}
	public SmartList<PageFlowSpec> savePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPageFlowSpecList(pageFlowSpecList);
		
		batchPageFlowSpecCreate((List<PageFlowSpec>)lists[CREATE_LIST_INDEX]);
		
		batchPageFlowSpecUpdate((List<PageFlowSpec>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(PageFlowSpec pageFlowSpec:pageFlowSpecList){
			if(pageFlowSpec.isChanged()){
				pageFlowSpec.incVersion();
			}
			
		
		}
		
		
		return pageFlowSpecList;
	}

	public SmartList<PageFlowSpec> removePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList,Map<String,Object> options){
		
		
		super.removeList(pageFlowSpecList, options);
		
		return pageFlowSpecList;
		
		
	}
	
	protected List<Object[]> preparePageFlowSpecBatchCreateArgs(List<PageFlowSpec> pageFlowSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PageFlowSpec pageFlowSpec:pageFlowSpecList ){
			Object [] parameters = preparePageFlowSpecCreateParameters(pageFlowSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePageFlowSpecBatchUpdateArgs(List<PageFlowSpec> pageFlowSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PageFlowSpec pageFlowSpec:pageFlowSpecList ){
			if(!pageFlowSpec.isChanged()){
				continue;
			}
			Object [] parameters = preparePageFlowSpecUpdateParameters(pageFlowSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPageFlowSpecCreate(List<PageFlowSpec> pageFlowSpecList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePageFlowSpecBatchCreateArgs(pageFlowSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPageFlowSpecUpdate(List<PageFlowSpec> pageFlowSpecList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePageFlowSpecBatchUpdateArgs(pageFlowSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPageFlowSpecList(List<PageFlowSpec> pageFlowSpecList){
		
		List<PageFlowSpec> pageFlowSpecCreateList=new ArrayList<PageFlowSpec>();
		List<PageFlowSpec> pageFlowSpecUpdateList=new ArrayList<PageFlowSpec>();
		
		for(PageFlowSpec pageFlowSpec: pageFlowSpecList){
			if(isUpdateRequest(pageFlowSpec)){
				pageFlowSpecUpdateList.add( pageFlowSpec);
				continue;
			}
			pageFlowSpecCreateList.add(pageFlowSpec);
		}
		
		return new Object[]{pageFlowSpecCreateList,pageFlowSpecUpdateList};
	}
	
	protected boolean isUpdateRequest(PageFlowSpec pageFlowSpec){
 		return pageFlowSpec.getVersion() > 0;
 	}
 	protected String getSavePageFlowSpecSQL(PageFlowSpec pageFlowSpec){
 		if(isUpdateRequest(pageFlowSpec)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePageFlowSpecParameters(PageFlowSpec pageFlowSpec){
 		if(isUpdateRequest(pageFlowSpec) ){
 			return preparePageFlowSpecUpdateParameters(pageFlowSpec);
 		}
 		return preparePageFlowSpecCreateParameters(pageFlowSpec);
 	}
 	protected Object[] preparePageFlowSpecUpdateParameters(PageFlowSpec pageFlowSpec){
 		Object[] parameters = new Object[7];
 
 		
 		parameters[0] = pageFlowSpec.getScene();
 		
 		
 		parameters[1] = pageFlowSpec.getBrief();
 		 	
 		if(pageFlowSpec.getOwner() != null){
 			parameters[2] = pageFlowSpec.getOwner().getId();
 		}
  	
 		if(pageFlowSpec.getProject() != null){
 			parameters[3] = pageFlowSpec.getProject().getId();
 		}
 		
 		parameters[4] = pageFlowSpec.nextVersion();
 		parameters[5] = pageFlowSpec.getId();
 		parameters[6] = pageFlowSpec.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePageFlowSpecCreateParameters(PageFlowSpec pageFlowSpec){
		Object[] parameters = new Object[5];
		String newPageFlowSpecId=getNextId();
		pageFlowSpec.setId(newPageFlowSpecId);
		parameters[0] =  pageFlowSpec.getId();
 
 		
 		parameters[1] = pageFlowSpec.getScene();
 		
 		
 		parameters[2] = pageFlowSpec.getBrief();
 		 	
 		if(pageFlowSpec.getOwner() != null){
 			parameters[3] = pageFlowSpec.getOwner().getId();
 		
 		}
 		 	
 		if(pageFlowSpec.getProject() != null){
 			parameters[4] = pageFlowSpec.getProject().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected PageFlowSpec saveInternalPageFlowSpec(PageFlowSpec pageFlowSpec, Map<String,Object> options){
		
		savePageFlowSpec(pageFlowSpec);
 	
 		if(isSaveOwnerEnabled(options)){
	 		saveOwner(pageFlowSpec, options);
 		}
  	
 		if(isSaveProjectEnabled(options)){
	 		saveProject(pageFlowSpec, options);
 		}
 
		
		return pageFlowSpec;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected PageFlowSpec saveOwner(PageFlowSpec pageFlowSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(pageFlowSpec.getOwner() == null){
 			return pageFlowSpec;//do nothing when it is null
 		}
 		
 		getUserDAO().save(pageFlowSpec.getOwner(),options);
 		return pageFlowSpec;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected PageFlowSpec saveProject(PageFlowSpec pageFlowSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(pageFlowSpec.getProject() == null){
 			return pageFlowSpec;//do nothing when it is null
 		}
 		
 		getProjectDAO().save(pageFlowSpec.getProject(),options);
 		return pageFlowSpec;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public PageFlowSpec present(PageFlowSpec pageFlowSpec,Map<String, Object> options){
	

		return pageFlowSpec;
	
	}
		

	

	protected String getTableName(){
		return PageFlowSpecTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<PageFlowSpec> pageFlowSpecList) {		
		this.enhanceListInternal(pageFlowSpecList, this.getPageFlowSpecMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<PageFlowSpec> pageFlowSpecList = ownerEntity.collectRefsWithType(PageFlowSpec.INTERNAL_TYPE);
		this.enhanceList(pageFlowSpecList);
		
	}
	
	@Override
	public SmartList<PageFlowSpec> findPageFlowSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPageFlowSpecMapper());

	}
	@Override
	public int countPageFlowSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPageFlowSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<PageFlowSpec> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPageFlowSpecMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidatePageFlowSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidatePageFlowSpec result = new CandidatePageFlowSpec();
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


