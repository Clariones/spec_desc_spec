
package com.cla.sds.pagecontentspec;

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


public class PageContentSpecJDBCTemplateDAO extends SdsBaseDAOImpl implements PageContentSpecDAO{

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
	protected PageContentSpec load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPageContentSpec(accessKey, options);
	}
	*/
	
	public SmartList<PageContentSpec> loadAll() {
	    return this.loadAll(getPageContentSpecMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public PageContentSpec load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPageContentSpec(PageContentSpecTable.withId(id), options);
	}
	
	
	
	public PageContentSpec save(PageContentSpec pageContentSpec,Map<String,Object> options){
		
		String methodName="save(PageContentSpec pageContentSpec,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(pageContentSpec, methodName, "pageContentSpec");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPageContentSpec(pageContentSpec,options);
	}
	public PageContentSpec clone(String pageContentSpecId, Map<String,Object> options) throws Exception{
	
		return clone(PageContentSpecTable.withId(pageContentSpecId),options);
	}
	
	protected PageContentSpec clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String pageContentSpecId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		PageContentSpec newPageContentSpec = loadInternalPageContentSpec(accessKey, options);
		newPageContentSpec.setVersion(0);
		
		

		
		saveInternalPageContentSpec(newPageContentSpec,options);
		
		return newPageContentSpec;
	}
	
	
	
	

	protected void throwIfHasException(String pageContentSpecId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PageContentSpecVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PageContentSpecNotFoundException(
					"The " + this.getTableName() + "(" + pageContentSpecId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String pageContentSpecId, int version) throws Exception{
	
		String methodName="delete(String pageContentSpecId, int version)";
		assertMethodArgumentNotNull(pageContentSpecId, methodName, "pageContentSpecId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{pageContentSpecId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(pageContentSpecId,version);
		}
		
	
	}
	
	
	
	
	

	public PageContentSpec disconnectFromAll(String pageContentSpecId, int version) throws Exception{
	
		
		PageContentSpec pageContentSpec = loadInternalPageContentSpec(PageContentSpecTable.withId(pageContentSpecId), emptyOptions());
		pageContentSpec.clearFromAll();
		this.savePageContentSpec(pageContentSpec);
		return pageContentSpec;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PageContentSpecTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "page_content_spec";
	}
	@Override
	protected String getBeanName() {
		
		return "pageContentSpec";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PageContentSpecTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractOwnerEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PageContentSpecTokens.OWNER);
 	}

 	protected boolean isSaveOwnerEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PageContentSpecTokens.OWNER);
 	}
 	

 	
  

 	protected boolean isExtractProjectEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PageContentSpecTokens.PROJECT);
 	}

 	protected boolean isSaveProjectEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PageContentSpecTokens.PROJECT);
 	}
 	

 	
 
		

	

	protected PageContentSpecMapper getPageContentSpecMapper(){
		return new PageContentSpecMapper();
	}

	
	
	protected PageContentSpec extractPageContentSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			PageContentSpec pageContentSpec = loadSingleObject(accessKey, getPageContentSpecMapper());
			return pageContentSpec;
		}catch(EmptyResultDataAccessException e){
			throw new PageContentSpecNotFoundException("PageContentSpec("+accessKey+") is not found!");
		}

	}

	
	

	protected PageContentSpec loadInternalPageContentSpec(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		PageContentSpec pageContentSpec = extractPageContentSpec(accessKey, loadOptions);
 	
 		if(isExtractOwnerEnabled(loadOptions)){
	 		extractOwner(pageContentSpec, loadOptions);
 		}
  	
 		if(isExtractProjectEnabled(loadOptions)){
	 		extractProject(pageContentSpec, loadOptions);
 		}
 
		
		return pageContentSpec;
		
	}

	 

 	protected PageContentSpec extractOwner(PageContentSpec pageContentSpec, Map<String,Object> options) throws Exception{

		if(pageContentSpec.getOwner() == null){
			return pageContentSpec;
		}
		String ownerId = pageContentSpec.getOwner().getId();
		if( ownerId == null){
			return pageContentSpec;
		}
		User owner = getUserDAO().load(ownerId,options);
		if(owner != null){
			pageContentSpec.setOwner(owner);
		}
		
 		
 		return pageContentSpec;
 	}
 		
  

 	protected PageContentSpec extractProject(PageContentSpec pageContentSpec, Map<String,Object> options) throws Exception{

		if(pageContentSpec.getProject() == null){
			return pageContentSpec;
		}
		String projectId = pageContentSpec.getProject().getId();
		if( projectId == null){
			return pageContentSpec;
		}
		Project project = getProjectDAO().load(projectId,options);
		if(project != null){
			pageContentSpec.setProject(project);
		}
		
 		
 		return pageContentSpec;
 	}
 		
 
		
		
  	
 	public SmartList<PageContentSpec> findPageContentSpecByOwner(String userId,Map<String,Object> options){
 	
  		SmartList<PageContentSpec> resultList = queryWith(PageContentSpecTable.COLUMN_OWNER, userId, options, getPageContentSpecMapper());
		// analyzePageContentSpecByOwner(resultList, userId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PageContentSpec> findPageContentSpecByOwner(String userId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PageContentSpec> resultList =  queryWithRange(PageContentSpecTable.COLUMN_OWNER, userId, options, getPageContentSpecMapper(), start, count);
 		//analyzePageContentSpecByOwner(resultList, userId, options);
 		return resultList;
 		
 	}
 	public void analyzePageContentSpecByOwner(SmartList<PageContentSpec> resultList, String userId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PageContentSpec.OWNER_PROPERTY, userId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPageContentSpecByOwner(String userId,Map<String,Object> options){

 		return countWith(PageContentSpecTable.COLUMN_OWNER, userId, options);
 	}
 	@Override
	public Map<String, Integer> countPageContentSpecByOwnerIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PageContentSpecTable.COLUMN_OWNER, ids, options);
	}
 	
  	
 	public SmartList<PageContentSpec> findPageContentSpecByProject(String projectId,Map<String,Object> options){
 	
  		SmartList<PageContentSpec> resultList = queryWith(PageContentSpecTable.COLUMN_PROJECT, projectId, options, getPageContentSpecMapper());
		// analyzePageContentSpecByProject(resultList, projectId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PageContentSpec> findPageContentSpecByProject(String projectId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PageContentSpec> resultList =  queryWithRange(PageContentSpecTable.COLUMN_PROJECT, projectId, options, getPageContentSpecMapper(), start, count);
 		//analyzePageContentSpecByProject(resultList, projectId, options);
 		return resultList;
 		
 	}
 	public void analyzePageContentSpecByProject(SmartList<PageContentSpec> resultList, String projectId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PageContentSpec.PROJECT_PROPERTY, projectId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPageContentSpecByProject(String projectId,Map<String,Object> options){

 		return countWith(PageContentSpecTable.COLUMN_PROJECT, projectId, options);
 	}
 	@Override
	public Map<String, Integer> countPageContentSpecByProjectIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PageContentSpecTable.COLUMN_PROJECT, ids, options);
	}
 	
 	
		
		
		

	

	protected PageContentSpec savePageContentSpec(PageContentSpec  pageContentSpec){
		
		if(!pageContentSpec.isChanged()){
			return pageContentSpec;
		}
		
		
		String SQL=this.getSavePageContentSpecSQL(pageContentSpec);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePageContentSpecParameters(pageContentSpec);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		pageContentSpec.incVersion();
		return pageContentSpec;
	
	}
	public SmartList<PageContentSpec> savePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPageContentSpecList(pageContentSpecList);
		
		batchPageContentSpecCreate((List<PageContentSpec>)lists[CREATE_LIST_INDEX]);
		
		batchPageContentSpecUpdate((List<PageContentSpec>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(PageContentSpec pageContentSpec:pageContentSpecList){
			if(pageContentSpec.isChanged()){
				pageContentSpec.incVersion();
			}
			
		
		}
		
		
		return pageContentSpecList;
	}

	public SmartList<PageContentSpec> removePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList,Map<String,Object> options){
		
		
		super.removeList(pageContentSpecList, options);
		
		return pageContentSpecList;
		
		
	}
	
	protected List<Object[]> preparePageContentSpecBatchCreateArgs(List<PageContentSpec> pageContentSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PageContentSpec pageContentSpec:pageContentSpecList ){
			Object [] parameters = preparePageContentSpecCreateParameters(pageContentSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePageContentSpecBatchUpdateArgs(List<PageContentSpec> pageContentSpecList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PageContentSpec pageContentSpec:pageContentSpecList ){
			if(!pageContentSpec.isChanged()){
				continue;
			}
			Object [] parameters = preparePageContentSpecUpdateParameters(pageContentSpec);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPageContentSpecCreate(List<PageContentSpec> pageContentSpecList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePageContentSpecBatchCreateArgs(pageContentSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPageContentSpecUpdate(List<PageContentSpec> pageContentSpecList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePageContentSpecBatchUpdateArgs(pageContentSpecList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPageContentSpecList(List<PageContentSpec> pageContentSpecList){
		
		List<PageContentSpec> pageContentSpecCreateList=new ArrayList<PageContentSpec>();
		List<PageContentSpec> pageContentSpecUpdateList=new ArrayList<PageContentSpec>();
		
		for(PageContentSpec pageContentSpec: pageContentSpecList){
			if(isUpdateRequest(pageContentSpec)){
				pageContentSpecUpdateList.add( pageContentSpec);
				continue;
			}
			pageContentSpecCreateList.add(pageContentSpec);
		}
		
		return new Object[]{pageContentSpecCreateList,pageContentSpecUpdateList};
	}
	
	protected boolean isUpdateRequest(PageContentSpec pageContentSpec){
 		return pageContentSpec.getVersion() > 0;
 	}
 	protected String getSavePageContentSpecSQL(PageContentSpec pageContentSpec){
 		if(isUpdateRequest(pageContentSpec)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePageContentSpecParameters(PageContentSpec pageContentSpec){
 		if(isUpdateRequest(pageContentSpec) ){
 			return preparePageContentSpecUpdateParameters(pageContentSpec);
 		}
 		return preparePageContentSpecCreateParameters(pageContentSpec);
 	}
 	protected Object[] preparePageContentSpecUpdateParameters(PageContentSpec pageContentSpec){
 		Object[] parameters = new Object[7];
 
 		
 		parameters[0] = pageContentSpec.getScene();
 		
 		
 		parameters[1] = pageContentSpec.getBrief();
 		 	
 		if(pageContentSpec.getOwner() != null){
 			parameters[2] = pageContentSpec.getOwner().getId();
 		}
  	
 		if(pageContentSpec.getProject() != null){
 			parameters[3] = pageContentSpec.getProject().getId();
 		}
 		
 		parameters[4] = pageContentSpec.nextVersion();
 		parameters[5] = pageContentSpec.getId();
 		parameters[6] = pageContentSpec.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePageContentSpecCreateParameters(PageContentSpec pageContentSpec){
		Object[] parameters = new Object[5];
		String newPageContentSpecId=getNextId();
		pageContentSpec.setId(newPageContentSpecId);
		parameters[0] =  pageContentSpec.getId();
 
 		
 		parameters[1] = pageContentSpec.getScene();
 		
 		
 		parameters[2] = pageContentSpec.getBrief();
 		 	
 		if(pageContentSpec.getOwner() != null){
 			parameters[3] = pageContentSpec.getOwner().getId();
 		
 		}
 		 	
 		if(pageContentSpec.getProject() != null){
 			parameters[4] = pageContentSpec.getProject().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected PageContentSpec saveInternalPageContentSpec(PageContentSpec pageContentSpec, Map<String,Object> options){
		
		savePageContentSpec(pageContentSpec);
 	
 		if(isSaveOwnerEnabled(options)){
	 		saveOwner(pageContentSpec, options);
 		}
  	
 		if(isSaveProjectEnabled(options)){
	 		saveProject(pageContentSpec, options);
 		}
 
		
		return pageContentSpec;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected PageContentSpec saveOwner(PageContentSpec pageContentSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(pageContentSpec.getOwner() == null){
 			return pageContentSpec;//do nothing when it is null
 		}
 		
 		getUserDAO().save(pageContentSpec.getOwner(),options);
 		return pageContentSpec;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected PageContentSpec saveProject(PageContentSpec pageContentSpec, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(pageContentSpec.getProject() == null){
 			return pageContentSpec;//do nothing when it is null
 		}
 		
 		getProjectDAO().save(pageContentSpec.getProject(),options);
 		return pageContentSpec;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public PageContentSpec present(PageContentSpec pageContentSpec,Map<String, Object> options){
	

		return pageContentSpec;
	
	}
		

	

	protected String getTableName(){
		return PageContentSpecTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<PageContentSpec> pageContentSpecList) {		
		this.enhanceListInternal(pageContentSpecList, this.getPageContentSpecMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<PageContentSpec> pageContentSpecList = ownerEntity.collectRefsWithType(PageContentSpec.INTERNAL_TYPE);
		this.enhanceList(pageContentSpecList);
		
	}
	
	@Override
	public SmartList<PageContentSpec> findPageContentSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPageContentSpecMapper());

	}
	@Override
	public int countPageContentSpecWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPageContentSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<PageContentSpec> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPageContentSpecMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidatePageContentSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidatePageContentSpec result = new CandidatePageContentSpec();
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


