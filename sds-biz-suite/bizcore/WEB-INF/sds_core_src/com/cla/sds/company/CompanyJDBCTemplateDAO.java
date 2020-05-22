
package com.cla.sds.company;

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
import com.cla.sds.platform.Platform;

import com.cla.sds.user.UserDAO;
import com.cla.sds.platform.PlatformDAO;
import com.cla.sds.project.ProjectDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class CompanyJDBCTemplateDAO extends SdsBaseDAOImpl implements CompanyDAO{

	protected PlatformDAO platformDAO;
	public void setPlatformDAO(PlatformDAO platformDAO){
 	
 		if(platformDAO == null){
 			throw new IllegalStateException("Do not try to set platformDAO to null.");
 		}
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
 		if(this.platformDAO == null){
 			throw new IllegalStateException("The platformDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.platformDAO;
 	}	

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
	protected Company load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalCompany(accessKey, options);
	}
	*/
	
	public SmartList<Company> loadAll() {
	    return this.loadAll(getCompanyMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Company load(String id,Map<String,Object> options) throws Exception{
		return loadInternalCompany(CompanyTable.withId(id), options);
	}
	
	
	
	public Company save(Company company,Map<String,Object> options){
		
		String methodName="save(Company company,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(company, methodName, "company");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalCompany(company,options);
	}
	public Company clone(String companyId, Map<String,Object> options) throws Exception{
	
		return clone(CompanyTable.withId(companyId),options);
	}
	
	protected Company clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String companyId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Company newCompany = loadInternalCompany(accessKey, options);
		newCompany.setVersion(0);
		
		
 		
 		if(isSaveUserListEnabled(options)){
 			for(User item: newCompany.getUserList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveProjectListEnabled(options)){
 			for(Project item: newCompany.getProjectList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalCompany(newCompany,options);
		
		return newCompany;
	}
	
	
	
	

	protected void throwIfHasException(String companyId,int version,int count) throws Exception{
		if (count == 1) {
			throw new CompanyVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new CompanyNotFoundException(
					"The " + this.getTableName() + "(" + companyId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String companyId, int version) throws Exception{
	
		String methodName="delete(String companyId, int version)";
		assertMethodArgumentNotNull(companyId, methodName, "companyId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{companyId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(companyId,version);
		}
		
	
	}
	
	
	
	
	

	public Company disconnectFromAll(String companyId, int version) throws Exception{
	
		
		Company company = loadInternalCompany(CompanyTable.withId(companyId), emptyOptions());
		company.clearFromAll();
		this.saveCompany(company);
		return company;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return CompanyTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "company";
	}
	@Override
	protected String getBeanName() {
		
		return "company";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return CompanyTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, CompanyTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, CompanyTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractUserListEnabled(Map<String,Object> options){		
 		return checkOptions(options,CompanyTokens.USER_LIST);
 	}
 	protected boolean isAnalyzeUserListEnabled(Map<String,Object> options){		 		
 		return CompanyTokens.of(options).analyzeUserListEnabled();
 	}
	
	protected boolean isSaveUserListEnabled(Map<String,Object> options){
		return checkOptions(options, CompanyTokens.USER_LIST);
		
 	}
 	
		
	
	protected boolean isExtractProjectListEnabled(Map<String,Object> options){		
 		return checkOptions(options,CompanyTokens.PROJECT_LIST);
 	}
 	protected boolean isAnalyzeProjectListEnabled(Map<String,Object> options){		 		
 		return CompanyTokens.of(options).analyzeProjectListEnabled();
 	}
	
	protected boolean isSaveProjectListEnabled(Map<String,Object> options){
		return checkOptions(options, CompanyTokens.PROJECT_LIST);
		
 	}
 	
		

	

	protected CompanyMapper getCompanyMapper(){
		return new CompanyMapper();
	}

	
	
	protected Company extractCompany(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Company company = loadSingleObject(accessKey, getCompanyMapper());
			return company;
		}catch(EmptyResultDataAccessException e){
			throw new CompanyNotFoundException("Company("+accessKey+") is not found!");
		}

	}

	
	

	protected Company loadInternalCompany(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Company company = extractCompany(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(company, loadOptions);
 		}
 
		
		if(isExtractUserListEnabled(loadOptions)){
	 		extractUserList(company, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeUserListEnabled(loadOptions)){
	 		analyzeUserList(company, loadOptions);
 		}
 		
		
		if(isExtractProjectListEnabled(loadOptions)){
	 		extractProjectList(company, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeProjectListEnabled(loadOptions)){
	 		analyzeProjectList(company, loadOptions);
 		}
 		
		
		return company;
		
	}

	 

 	protected Company extractPlatform(Company company, Map<String,Object> options) throws Exception{

		if(company.getPlatform() == null){
			return company;
		}
		String platformId = company.getPlatform().getId();
		if( platformId == null){
			return company;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			company.setPlatform(platform);
		}
		
 		
 		return company;
 	}
 		
 
		
	protected void enhanceUserList(SmartList<User> userList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Company extractUserList(Company company, Map<String,Object> options){
		
		
		if(company == null){
			return null;
		}
		if(company.getId() == null){
			return company;
		}

		
		
		SmartList<User> userList = getUserDAO().findUserByCompany(company.getId(),options);
		if(userList != null){
			enhanceUserList(userList,options);
			company.setUserList(userList);
		}
		
		return company;
	
	}	
	
	protected Company analyzeUserList(Company company, Map<String,Object> options){
		
		
		if(company == null){
			return null;
		}
		if(company.getId() == null){
			return company;
		}

		
		
		SmartList<User> userList = company.getUserList();
		if(userList != null){
			getUserDAO().analyzeUserByCompany(userList, company.getId(), options);
			
		}
		
		return company;
	
	}	
	
		
	protected void enhanceProjectList(SmartList<Project> projectList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Company extractProjectList(Company company, Map<String,Object> options){
		
		
		if(company == null){
			return null;
		}
		if(company.getId() == null){
			return company;
		}

		
		
		SmartList<Project> projectList = getProjectDAO().findProjectByCompany(company.getId(),options);
		if(projectList != null){
			enhanceProjectList(projectList,options);
			company.setProjectList(projectList);
		}
		
		return company;
	
	}	
	
	protected Company analyzeProjectList(Company company, Map<String,Object> options){
		
		
		if(company == null){
			return null;
		}
		if(company.getId() == null){
			return company;
		}

		
		
		SmartList<Project> projectList = company.getProjectList();
		if(projectList != null){
			getProjectDAO().analyzeProjectByCompany(projectList, company.getId(), options);
			
		}
		
		return company;
	
	}	
	
		
		
  	
 	public SmartList<Company> findCompanyByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Company> resultList = queryWith(CompanyTable.COLUMN_PLATFORM, platformId, options, getCompanyMapper());
		// analyzeCompanyByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Company> findCompanyByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Company> resultList =  queryWithRange(CompanyTable.COLUMN_PLATFORM, platformId, options, getCompanyMapper(), start, count);
 		//analyzeCompanyByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeCompanyByPlatform(SmartList<Company> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Company.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//Company.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("公司");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(Company.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Company.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countCompanyByPlatform(String platformId,Map<String,Object> options){

 		return countWith(CompanyTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countCompanyByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(CompanyTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected Company saveCompany(Company  company){
		
		if(!company.isChanged()){
			return company;
		}
		
		
		String SQL=this.getSaveCompanySQL(company);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveCompanyParameters(company);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		company.incVersion();
		return company;
	
	}
	public SmartList<Company> saveCompanyList(SmartList<Company> companyList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitCompanyList(companyList);
		
		batchCompanyCreate((List<Company>)lists[CREATE_LIST_INDEX]);
		
		batchCompanyUpdate((List<Company>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Company company:companyList){
			if(company.isChanged()){
				company.incVersion();
			}
			
		
		}
		
		
		return companyList;
	}

	public SmartList<Company> removeCompanyList(SmartList<Company> companyList,Map<String,Object> options){
		
		
		super.removeList(companyList, options);
		
		return companyList;
		
		
	}
	
	protected List<Object[]> prepareCompanyBatchCreateArgs(List<Company> companyList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Company company:companyList ){
			Object [] parameters = prepareCompanyCreateParameters(company);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareCompanyBatchUpdateArgs(List<Company> companyList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Company company:companyList ){
			if(!company.isChanged()){
				continue;
			}
			Object [] parameters = prepareCompanyUpdateParameters(company);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchCompanyCreate(List<Company> companyList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareCompanyBatchCreateArgs(companyList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchCompanyUpdate(List<Company> companyList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareCompanyBatchUpdateArgs(companyList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitCompanyList(List<Company> companyList){
		
		List<Company> companyCreateList=new ArrayList<Company>();
		List<Company> companyUpdateList=new ArrayList<Company>();
		
		for(Company company: companyList){
			if(isUpdateRequest(company)){
				companyUpdateList.add( company);
				continue;
			}
			companyCreateList.add(company);
		}
		
		return new Object[]{companyCreateList,companyUpdateList};
	}
	
	protected boolean isUpdateRequest(Company company){
 		return company.getVersion() > 0;
 	}
 	protected String getSaveCompanySQL(Company company){
 		if(isUpdateRequest(company)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveCompanyParameters(Company company){
 		if(isUpdateRequest(company) ){
 			return prepareCompanyUpdateParameters(company);
 		}
 		return prepareCompanyCreateParameters(company);
 	}
 	protected Object[] prepareCompanyUpdateParameters(Company company){
 		Object[] parameters = new Object[6];
 
 		
 		parameters[0] = company.getName();
 		
 		
 		parameters[1] = company.getCreateTime();
 		 	
 		if(company.getPlatform() != null){
 			parameters[2] = company.getPlatform().getId();
 		}
 		
 		parameters[3] = company.nextVersion();
 		parameters[4] = company.getId();
 		parameters[5] = company.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareCompanyCreateParameters(Company company){
		Object[] parameters = new Object[4];
		String newCompanyId=getNextId();
		company.setId(newCompanyId);
		parameters[0] =  company.getId();
 
 		
 		parameters[1] = company.getName();
 		
 		
 		parameters[2] = company.getCreateTime();
 		 	
 		if(company.getPlatform() != null){
 			parameters[3] = company.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Company saveInternalCompany(Company company, Map<String,Object> options){
		
		saveCompany(company);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(company, options);
 		}
 
		
		if(isSaveUserListEnabled(options)){
	 		saveUserList(company, options);
	 		//removeUserList(company, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveProjectListEnabled(options)){
	 		saveProjectList(company, options);
	 		//removeProjectList(company, options);
	 		//Not delete the record
	 		
 		}		
		
		return company;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Company savePlatform(Company company, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(company.getPlatform() == null){
 			return company;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(company.getPlatform(),options);
 		return company;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Company planToRemoveUserList(Company company, String userIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.COMPANY_PROPERTY, company.getId());
		key.put(User.ID_PROPERTY, userIds);
		
		SmartList<User> externalUserList = getUserDAO().
				findUserWithKey(key, options);
		if(externalUserList == null){
			return company;
		}
		if(externalUserList.isEmpty()){
			return company;
		}
		
		for(User userItem: externalUserList){

			userItem.clearFromAll();
		}
		
		
		SmartList<User> userList = company.getUserList();		
		userList.addAllToRemoveList(externalUserList);
		return company;	
	
	}


	public Company planToRemoveProjectList(Company company, String projectIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Project.COMPANY_PROPERTY, company.getId());
		key.put(Project.ID_PROPERTY, projectIds);
		
		SmartList<Project> externalProjectList = getProjectDAO().
				findProjectWithKey(key, options);
		if(externalProjectList == null){
			return company;
		}
		if(externalProjectList.isEmpty()){
			return company;
		}
		
		for(Project projectItem: externalProjectList){

			projectItem.clearFromAll();
		}
		
		
		SmartList<Project> projectList = company.getProjectList();		
		projectList.addAllToRemoveList(externalProjectList);
		return company;	
	
	}



		
	protected Company saveUserList(Company company, Map<String,Object> options){
		
		
		
		
		SmartList<User> userList = company.getUserList();
		if(userList == null){
			//null list means nothing
			return company;
		}
		SmartList<User> mergedUpdateUserList = new SmartList<User>();
		
		
		mergedUpdateUserList.addAll(userList); 
		if(userList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateUserList.addAll(userList.getToRemoveList());
			userList.removeAll(userList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getUserDAO().saveUserList(mergedUpdateUserList,options);
		
		if(userList.getToRemoveList() != null){
			userList.removeAll(userList.getToRemoveList());
		}
		
		
		return company;
	
	}
	
	protected Company removeUserList(Company company, Map<String,Object> options){
	
	
		SmartList<User> userList = company.getUserList();
		if(userList == null){
			return company;
		}	
	
		SmartList<User> toRemoveUserList = userList.getToRemoveList();
		
		if(toRemoveUserList == null){
			return company;
		}
		if(toRemoveUserList.isEmpty()){
			return company;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getUserDAO().removeUserList(toRemoveUserList,options);
		
		return company;
	
	}
	
	

 	
 	
	
	
	
		
	protected Company saveProjectList(Company company, Map<String,Object> options){
		
		
		
		
		SmartList<Project> projectList = company.getProjectList();
		if(projectList == null){
			//null list means nothing
			return company;
		}
		SmartList<Project> mergedUpdateProjectList = new SmartList<Project>();
		
		
		mergedUpdateProjectList.addAll(projectList); 
		if(projectList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateProjectList.addAll(projectList.getToRemoveList());
			projectList.removeAll(projectList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getProjectDAO().saveProjectList(mergedUpdateProjectList,options);
		
		if(projectList.getToRemoveList() != null){
			projectList.removeAll(projectList.getToRemoveList());
		}
		
		
		return company;
	
	}
	
	protected Company removeProjectList(Company company, Map<String,Object> options){
	
	
		SmartList<Project> projectList = company.getProjectList();
		if(projectList == null){
			return company;
		}	
	
		SmartList<Project> toRemoveProjectList = projectList.getToRemoveList();
		
		if(toRemoveProjectList == null){
			return company;
		}
		if(toRemoveProjectList.isEmpty()){
			return company;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getProjectDAO().removeProjectList(toRemoveProjectList,options);
		
		return company;
	
	}
	
	

 	
 	
	
	
	
		

	public Company present(Company company,Map<String, Object> options){
	
		presentUserList(company,options);
		presentProjectList(company,options);

		return company;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Company presentUserList(
			Company company,
			Map<String, Object> options) {

		SmartList<User> userList = company.getUserList();		
				SmartList<User> newList= presentSubList(company.getId(),
				userList,
				options,
				getUserDAO()::countUserByCompany,
				getUserDAO()::findUserByCompany
				);

		
		company.setUserList(newList);
		

		return company;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Company presentProjectList(
			Company company,
			Map<String, Object> options) {

		SmartList<Project> projectList = company.getProjectList();		
				SmartList<Project> newList= presentSubList(company.getId(),
				projectList,
				options,
				getProjectDAO()::countProjectByCompany,
				getProjectDAO()::findProjectByCompany
				);

		
		company.setProjectList(newList);
		

		return company;
	}			
		

	
    public SmartList<Company> requestCandidateCompanyForUser(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(CompanyTable.COLUMN_NAME, CompanyTable.COLUMN_PLATFORM, filterKey, pageNo, pageSize, getCompanyMapper());
    }
		
    public SmartList<Company> requestCandidateCompanyForProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(CompanyTable.COLUMN_NAME, CompanyTable.COLUMN_PLATFORM, filterKey, pageNo, pageSize, getCompanyMapper());
    }
		

	protected String getTableName(){
		return CompanyTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Company> companyList) {		
		this.enhanceListInternal(companyList, this.getCompanyMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:User的company的UserList
	public SmartList<User> loadOurUserList(SdsUserContext userContext, List<Company> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(User.COMPANY_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<User> loadedObjs = userContext.getDAOGroup().getUserDAO().findUserWithKey(key, options);
		Map<String, List<User>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCompany().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<User> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<User> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setUserList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:Project的company的ProjectList
	public SmartList<Project> loadOurProjectList(SdsUserContext userContext, List<Company> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Project.COMPANY_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Project> loadedObjs = userContext.getDAOGroup().getProjectDAO().findProjectWithKey(key, options);
		Map<String, List<Project>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getCompany().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Project> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Project> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setProjectList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Company> companyList = ownerEntity.collectRefsWithType(Company.INTERNAL_TYPE);
		this.enhanceList(companyList);
		
	}
	
	@Override
	public SmartList<Company> findCompanyWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getCompanyMapper());

	}
	@Override
	public int countCompanyWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countCompanyWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Company> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getCompanyMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateCompany executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateCompany result = new CandidateCompany();
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


