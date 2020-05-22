
package com.cla.sds.user;

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


public class UserJDBCTemplateDAO extends SdsBaseDAOImpl implements UserDAO{

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
	protected User load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalUser(accessKey, options);
	}
	*/
	
	public SmartList<User> loadAll() {
	    return this.loadAll(getUserMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public User load(String id,Map<String,Object> options) throws Exception{
		return loadInternalUser(UserTable.withId(id), options);
	}
	
	
	
	public User save(User user,Map<String,Object> options){
		
		String methodName="save(User user,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(user, methodName, "user");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalUser(user,options);
	}
	public User clone(String userId, Map<String,Object> options) throws Exception{
	
		return clone(UserTable.withId(userId),options);
	}
	
	protected User clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String userId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		User newUser = loadInternalUser(accessKey, options);
		newUser.setVersion(0);
		
		
 		
 		if(isSaveUserProjectListEnabled(options)){
 			for(UserProject item: newUser.getUserProjectList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePageFlowSpecListEnabled(options)){
 			for(PageFlowSpec item: newUser.getPageFlowSpecList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveWorkFlowSpecListEnabled(options)){
 			for(WorkFlowSpec item: newUser.getWorkFlowSpecList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveChangeRequestSpecListEnabled(options)){
 			for(ChangeRequestSpec item: newUser.getChangeRequestSpecList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePageContentSpecListEnabled(options)){
 			for(PageContentSpec item: newUser.getPageContentSpecList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalUser(newUser,options);
		
		return newUser;
	}
	
	
	
	

	protected void throwIfHasException(String userId,int version,int count) throws Exception{
		if (count == 1) {
			throw new UserVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new UserNotFoundException(
					"The " + this.getTableName() + "(" + userId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String userId, int version) throws Exception{
	
		String methodName="delete(String userId, int version)";
		assertMethodArgumentNotNull(userId, methodName, "userId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{userId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(userId,version);
		}
		
	
	}
	
	
	
	
	

	public User disconnectFromAll(String userId, int version) throws Exception{
	
		
		User user = loadInternalUser(UserTable.withId(userId), emptyOptions());
		user.clearFromAll();
		this.saveUser(user);
		return user;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return UserTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "user";
	}
	@Override
	protected String getBeanName() {
		
		return "user";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return UserTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractCompanyEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, UserTokens.COMPANY);
 	}

 	protected boolean isSaveCompanyEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, UserTokens.COMPANY);
 	}
 	

 	
 
		
	
	protected boolean isExtractUserProjectListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.USER_PROJECT_LIST);
 	}
 	protected boolean isAnalyzeUserProjectListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeUserProjectListEnabled();
 	}
	
	protected boolean isSaveUserProjectListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.USER_PROJECT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPageFlowSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.PAGE_FLOW_SPEC_LIST);
 	}
 	protected boolean isAnalyzePageFlowSpecListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzePageFlowSpecListEnabled();
 	}
	
	protected boolean isSavePageFlowSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.PAGE_FLOW_SPEC_LIST);
		
 	}
 	
		
	
	protected boolean isExtractWorkFlowSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.WORK_FLOW_SPEC_LIST);
 	}
 	protected boolean isAnalyzeWorkFlowSpecListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeWorkFlowSpecListEnabled();
 	}
	
	protected boolean isSaveWorkFlowSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.WORK_FLOW_SPEC_LIST);
		
 	}
 	
		
	
	protected boolean isExtractChangeRequestSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.CHANGE_REQUEST_SPEC_LIST);
 	}
 	protected boolean isAnalyzeChangeRequestSpecListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzeChangeRequestSpecListEnabled();
 	}
	
	protected boolean isSaveChangeRequestSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.CHANGE_REQUEST_SPEC_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPageContentSpecListEnabled(Map<String,Object> options){		
 		return checkOptions(options,UserTokens.PAGE_CONTENT_SPEC_LIST);
 	}
 	protected boolean isAnalyzePageContentSpecListEnabled(Map<String,Object> options){		 		
 		return UserTokens.of(options).analyzePageContentSpecListEnabled();
 	}
	
	protected boolean isSavePageContentSpecListEnabled(Map<String,Object> options){
		return checkOptions(options, UserTokens.PAGE_CONTENT_SPEC_LIST);
		
 	}
 	
		

	

	protected UserMapper getUserMapper(){
		return new UserMapper();
	}

	
	
	protected User extractUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			User user = loadSingleObject(accessKey, getUserMapper());
			return user;
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User("+accessKey+") is not found!");
		}

	}

	
	

	protected User loadInternalUser(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		User user = extractUser(accessKey, loadOptions);
 	
 		if(isExtractCompanyEnabled(loadOptions)){
	 		extractCompany(user, loadOptions);
 		}
 
		
		if(isExtractUserProjectListEnabled(loadOptions)){
	 		extractUserProjectList(user, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeUserProjectListEnabled(loadOptions)){
	 		analyzeUserProjectList(user, loadOptions);
 		}
 		
		
		if(isExtractPageFlowSpecListEnabled(loadOptions)){
	 		extractPageFlowSpecList(user, loadOptions);
 		}	
 		
 		
 		if(isAnalyzePageFlowSpecListEnabled(loadOptions)){
	 		analyzePageFlowSpecList(user, loadOptions);
 		}
 		
		
		if(isExtractWorkFlowSpecListEnabled(loadOptions)){
	 		extractWorkFlowSpecList(user, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeWorkFlowSpecListEnabled(loadOptions)){
	 		analyzeWorkFlowSpecList(user, loadOptions);
 		}
 		
		
		if(isExtractChangeRequestSpecListEnabled(loadOptions)){
	 		extractChangeRequestSpecList(user, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeChangeRequestSpecListEnabled(loadOptions)){
	 		analyzeChangeRequestSpecList(user, loadOptions);
 		}
 		
		
		if(isExtractPageContentSpecListEnabled(loadOptions)){
	 		extractPageContentSpecList(user, loadOptions);
 		}	
 		
 		
 		if(isAnalyzePageContentSpecListEnabled(loadOptions)){
	 		analyzePageContentSpecList(user, loadOptions);
 		}
 		
		
		return user;
		
	}

	 

 	protected User extractCompany(User user, Map<String,Object> options) throws Exception{

		if(user.getCompany() == null){
			return user;
		}
		String companyId = user.getCompany().getId();
		if( companyId == null){
			return user;
		}
		Company company = getCompanyDAO().load(companyId,options);
		if(company != null){
			user.setCompany(company);
		}
		
 		
 		return user;
 	}
 		
 
		
	protected void enhanceUserProjectList(SmartList<UserProject> userProjectList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractUserProjectList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<UserProject> userProjectList = getUserProjectDAO().findUserProjectByUser(user.getId(),options);
		if(userProjectList != null){
			enhanceUserProjectList(userProjectList,options);
			user.setUserProjectList(userProjectList);
		}
		
		return user;
	
	}	
	
	protected User analyzeUserProjectList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<UserProject> userProjectList = user.getUserProjectList();
		if(userProjectList != null){
			getUserProjectDAO().analyzeUserProjectByUser(userProjectList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhancePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractPageFlowSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<PageFlowSpec> pageFlowSpecList = getPageFlowSpecDAO().findPageFlowSpecByOwner(user.getId(),options);
		if(pageFlowSpecList != null){
			enhancePageFlowSpecList(pageFlowSpecList,options);
			user.setPageFlowSpecList(pageFlowSpecList);
		}
		
		return user;
	
	}	
	
	protected User analyzePageFlowSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<PageFlowSpec> pageFlowSpecList = user.getPageFlowSpecList();
		if(pageFlowSpecList != null){
			getPageFlowSpecDAO().analyzePageFlowSpecByOwner(pageFlowSpecList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhanceWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractWorkFlowSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<WorkFlowSpec> workFlowSpecList = getWorkFlowSpecDAO().findWorkFlowSpecByOwner(user.getId(),options);
		if(workFlowSpecList != null){
			enhanceWorkFlowSpecList(workFlowSpecList,options);
			user.setWorkFlowSpecList(workFlowSpecList);
		}
		
		return user;
	
	}	
	
	protected User analyzeWorkFlowSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<WorkFlowSpec> workFlowSpecList = user.getWorkFlowSpecList();
		if(workFlowSpecList != null){
			getWorkFlowSpecDAO().analyzeWorkFlowSpecByOwner(workFlowSpecList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhanceChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractChangeRequestSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = getChangeRequestSpecDAO().findChangeRequestSpecByOwner(user.getId(),options);
		if(changeRequestSpecList != null){
			enhanceChangeRequestSpecList(changeRequestSpecList,options);
			user.setChangeRequestSpecList(changeRequestSpecList);
		}
		
		return user;
	
	}	
	
	protected User analyzeChangeRequestSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = user.getChangeRequestSpecList();
		if(changeRequestSpecList != null){
			getChangeRequestSpecDAO().analyzeChangeRequestSpecByOwner(changeRequestSpecList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
	protected void enhancePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected User extractPageContentSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<PageContentSpec> pageContentSpecList = getPageContentSpecDAO().findPageContentSpecByOwner(user.getId(),options);
		if(pageContentSpecList != null){
			enhancePageContentSpecList(pageContentSpecList,options);
			user.setPageContentSpecList(pageContentSpecList);
		}
		
		return user;
	
	}	
	
	protected User analyzePageContentSpecList(User user, Map<String,Object> options){
		
		
		if(user == null){
			return null;
		}
		if(user.getId() == null){
			return user;
		}

		
		
		SmartList<PageContentSpec> pageContentSpecList = user.getPageContentSpecList();
		if(pageContentSpecList != null){
			getPageContentSpecDAO().analyzePageContentSpecByOwner(pageContentSpecList, user.getId(), options);
			
		}
		
		return user;
	
	}	
	
		
		
  	
 	public SmartList<User> findUserByCompany(String companyId,Map<String,Object> options){
 	
  		SmartList<User> resultList = queryWith(UserTable.COLUMN_COMPANY, companyId, options, getUserMapper());
		// analyzeUserByCompany(resultList, companyId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<User> findUserByCompany(String companyId, int start, int count,Map<String,Object> options){
 		
 		SmartList<User> resultList =  queryWithRange(UserTable.COLUMN_COMPANY, companyId, options, getUserMapper(), start, count);
 		//analyzeUserByCompany(resultList, companyId, options);
 		return resultList;
 		
 	}
 	public void analyzeUserByCompany(SmartList<User> resultList, String companyId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countUserByCompany(String companyId,Map<String,Object> options){

 		return countWith(UserTable.COLUMN_COMPANY, companyId, options);
 	}
 	@Override
	public Map<String, Integer> countUserByCompanyIds(String[] ids, Map<String, Object> options) {
		return countWithIds(UserTable.COLUMN_COMPANY, ids, options);
	}
 	
 	
		
		
		

	

	protected User saveUser(User  user){
		
		if(!user.isChanged()){
			return user;
		}
		
		
		String SQL=this.getSaveUserSQL(user);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveUserParameters(user);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		user.incVersion();
		return user;
	
	}
	public SmartList<User> saveUserList(SmartList<User> userList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitUserList(userList);
		
		batchUserCreate((List<User>)lists[CREATE_LIST_INDEX]);
		
		batchUserUpdate((List<User>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(User user:userList){
			if(user.isChanged()){
				user.incVersion();
			}
			
		
		}
		
		
		return userList;
	}

	public SmartList<User> removeUserList(SmartList<User> userList,Map<String,Object> options){
		
		
		super.removeList(userList, options);
		
		return userList;
		
		
	}
	
	protected List<Object[]> prepareUserBatchCreateArgs(List<User> userList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(User user:userList ){
			Object [] parameters = prepareUserCreateParameters(user);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareUserBatchUpdateArgs(List<User> userList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(User user:userList ){
			if(!user.isChanged()){
				continue;
			}
			Object [] parameters = prepareUserUpdateParameters(user);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchUserCreate(List<User> userList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareUserBatchCreateArgs(userList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchUserUpdate(List<User> userList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareUserBatchUpdateArgs(userList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitUserList(List<User> userList){
		
		List<User> userCreateList=new ArrayList<User>();
		List<User> userUpdateList=new ArrayList<User>();
		
		for(User user: userList){
			if(isUpdateRequest(user)){
				userUpdateList.add( user);
				continue;
			}
			userCreateList.add(user);
		}
		
		return new Object[]{userCreateList,userUpdateList};
	}
	
	protected boolean isUpdateRequest(User user){
 		return user.getVersion() > 0;
 	}
 	protected String getSaveUserSQL(User user){
 		if(isUpdateRequest(user)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveUserParameters(User user){
 		if(isUpdateRequest(user) ){
 			return prepareUserUpdateParameters(user);
 		}
 		return prepareUserCreateParameters(user);
 	}
 	protected Object[] prepareUserUpdateParameters(User user){
 		Object[] parameters = new Object[7];
 
 		
 		parameters[0] = user.getName();
 		
 		
 		parameters[1] = user.getJoinTime();
 		 	
 		if(user.getCompany() != null){
 			parameters[2] = user.getCompany().getId();
 		}
 
 		
 		parameters[3] = user.getTitle();
 				
 		parameters[4] = user.nextVersion();
 		parameters[5] = user.getId();
 		parameters[6] = user.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareUserCreateParameters(User user){
		Object[] parameters = new Object[5];
		String newUserId=getNextId();
		user.setId(newUserId);
		parameters[0] =  user.getId();
 
 		
 		parameters[1] = user.getName();
 		
 		
 		parameters[2] = user.getJoinTime();
 		 	
 		if(user.getCompany() != null){
 			parameters[3] = user.getCompany().getId();
 		
 		}
 		
 		
 		parameters[4] = user.getTitle();
 				
 				
 		return parameters;
 	}
 	
	protected User saveInternalUser(User user, Map<String,Object> options){
		
		saveUser(user);
 	
 		if(isSaveCompanyEnabled(options)){
	 		saveCompany(user, options);
 		}
 
		
		if(isSaveUserProjectListEnabled(options)){
	 		saveUserProjectList(user, options);
	 		//removeUserProjectList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePageFlowSpecListEnabled(options)){
	 		savePageFlowSpecList(user, options);
	 		//removePageFlowSpecList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveWorkFlowSpecListEnabled(options)){
	 		saveWorkFlowSpecList(user, options);
	 		//removeWorkFlowSpecList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveChangeRequestSpecListEnabled(options)){
	 		saveChangeRequestSpecList(user, options);
	 		//removeChangeRequestSpecList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePageContentSpecListEnabled(options)){
	 		savePageContentSpecList(user, options);
	 		//removePageContentSpecList(user, options);
	 		//Not delete the record
	 		
 		}		
		
		return user;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected User saveCompany(User user, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(user.getCompany() == null){
 			return user;//do nothing when it is null
 		}
 		
 		getCompanyDAO().save(user.getCompany(),options);
 		return user;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public User planToRemoveUserProjectList(User user, String userProjectIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.USER_PROPERTY, user.getId());
		key.put(UserProject.ID_PROPERTY, userProjectIds);
		
		SmartList<UserProject> externalUserProjectList = getUserProjectDAO().
				findUserProjectWithKey(key, options);
		if(externalUserProjectList == null){
			return user;
		}
		if(externalUserProjectList.isEmpty()){
			return user;
		}
		
		for(UserProject userProjectItem: externalUserProjectList){

			userProjectItem.clearFromAll();
		}
		
		
		SmartList<UserProject> userProjectList = user.getUserProjectList();		
		userProjectList.addAllToRemoveList(externalUserProjectList);
		return user;	
	
	}


	//disconnect User with project in UserProject
	public User planToRemoveUserProjectListWithProject(User user, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.USER_PROPERTY, user.getId());
		key.put(UserProject.PROJECT_PROPERTY, projectId);
		
		SmartList<UserProject> externalUserProjectList = getUserProjectDAO().
				findUserProjectWithKey(key, options);
		if(externalUserProjectList == null){
			return user;
		}
		if(externalUserProjectList.isEmpty()){
			return user;
		}
		
		for(UserProject userProjectItem: externalUserProjectList){
			userProjectItem.clearProject();
			userProjectItem.clearUser();
			
		}
		
		
		SmartList<UserProject> userProjectList = user.getUserProjectList();		
		userProjectList.addAllToRemoveList(externalUserProjectList);
		return user;
	}
	
	public int countUserProjectListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.USER_PROPERTY, userId);
		key.put(UserProject.PROJECT_PROPERTY, projectId);
		
		int count = getUserProjectDAO().countUserProjectWithKey(key, options);
		return count;
	}
	
	public User planToRemovePageFlowSpecList(User user, String pageFlowSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.OWNER_PROPERTY, user.getId());
		key.put(PageFlowSpec.ID_PROPERTY, pageFlowSpecIds);
		
		SmartList<PageFlowSpec> externalPageFlowSpecList = getPageFlowSpecDAO().
				findPageFlowSpecWithKey(key, options);
		if(externalPageFlowSpecList == null){
			return user;
		}
		if(externalPageFlowSpecList.isEmpty()){
			return user;
		}
		
		for(PageFlowSpec pageFlowSpecItem: externalPageFlowSpecList){

			pageFlowSpecItem.clearFromAll();
		}
		
		
		SmartList<PageFlowSpec> pageFlowSpecList = user.getPageFlowSpecList();		
		pageFlowSpecList.addAllToRemoveList(externalPageFlowSpecList);
		return user;	
	
	}


	//disconnect User with project in PageFlowSpec
	public User planToRemovePageFlowSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.OWNER_PROPERTY, user.getId());
		key.put(PageFlowSpec.PROJECT_PROPERTY, projectId);
		
		SmartList<PageFlowSpec> externalPageFlowSpecList = getPageFlowSpecDAO().
				findPageFlowSpecWithKey(key, options);
		if(externalPageFlowSpecList == null){
			return user;
		}
		if(externalPageFlowSpecList.isEmpty()){
			return user;
		}
		
		for(PageFlowSpec pageFlowSpecItem: externalPageFlowSpecList){
			pageFlowSpecItem.clearProject();
			pageFlowSpecItem.clearOwner();
			
		}
		
		
		SmartList<PageFlowSpec> pageFlowSpecList = user.getPageFlowSpecList();		
		pageFlowSpecList.addAllToRemoveList(externalPageFlowSpecList);
		return user;
	}
	
	public int countPageFlowSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.OWNER_PROPERTY, userId);
		key.put(PageFlowSpec.PROJECT_PROPERTY, projectId);
		
		int count = getPageFlowSpecDAO().countPageFlowSpecWithKey(key, options);
		return count;
	}
	
	public User planToRemoveWorkFlowSpecList(User user, String workFlowSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.OWNER_PROPERTY, user.getId());
		key.put(WorkFlowSpec.ID_PROPERTY, workFlowSpecIds);
		
		SmartList<WorkFlowSpec> externalWorkFlowSpecList = getWorkFlowSpecDAO().
				findWorkFlowSpecWithKey(key, options);
		if(externalWorkFlowSpecList == null){
			return user;
		}
		if(externalWorkFlowSpecList.isEmpty()){
			return user;
		}
		
		for(WorkFlowSpec workFlowSpecItem: externalWorkFlowSpecList){

			workFlowSpecItem.clearFromAll();
		}
		
		
		SmartList<WorkFlowSpec> workFlowSpecList = user.getWorkFlowSpecList();		
		workFlowSpecList.addAllToRemoveList(externalWorkFlowSpecList);
		return user;	
	
	}


	//disconnect User with project in WorkFlowSpec
	public User planToRemoveWorkFlowSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.OWNER_PROPERTY, user.getId());
		key.put(WorkFlowSpec.PROJECT_PROPERTY, projectId);
		
		SmartList<WorkFlowSpec> externalWorkFlowSpecList = getWorkFlowSpecDAO().
				findWorkFlowSpecWithKey(key, options);
		if(externalWorkFlowSpecList == null){
			return user;
		}
		if(externalWorkFlowSpecList.isEmpty()){
			return user;
		}
		
		for(WorkFlowSpec workFlowSpecItem: externalWorkFlowSpecList){
			workFlowSpecItem.clearProject();
			workFlowSpecItem.clearOwner();
			
		}
		
		
		SmartList<WorkFlowSpec> workFlowSpecList = user.getWorkFlowSpecList();		
		workFlowSpecList.addAllToRemoveList(externalWorkFlowSpecList);
		return user;
	}
	
	public int countWorkFlowSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.OWNER_PROPERTY, userId);
		key.put(WorkFlowSpec.PROJECT_PROPERTY, projectId);
		
		int count = getWorkFlowSpecDAO().countWorkFlowSpecWithKey(key, options);
		return count;
	}
	
	public User planToRemoveChangeRequestSpecList(User user, String changeRequestSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.OWNER_PROPERTY, user.getId());
		key.put(ChangeRequestSpec.ID_PROPERTY, changeRequestSpecIds);
		
		SmartList<ChangeRequestSpec> externalChangeRequestSpecList = getChangeRequestSpecDAO().
				findChangeRequestSpecWithKey(key, options);
		if(externalChangeRequestSpecList == null){
			return user;
		}
		if(externalChangeRequestSpecList.isEmpty()){
			return user;
		}
		
		for(ChangeRequestSpec changeRequestSpecItem: externalChangeRequestSpecList){

			changeRequestSpecItem.clearFromAll();
		}
		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = user.getChangeRequestSpecList();		
		changeRequestSpecList.addAllToRemoveList(externalChangeRequestSpecList);
		return user;	
	
	}


	//disconnect User with project in ChangeRequestSpec
	public User planToRemoveChangeRequestSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.OWNER_PROPERTY, user.getId());
		key.put(ChangeRequestSpec.PROJECT_PROPERTY, projectId);
		
		SmartList<ChangeRequestSpec> externalChangeRequestSpecList = getChangeRequestSpecDAO().
				findChangeRequestSpecWithKey(key, options);
		if(externalChangeRequestSpecList == null){
			return user;
		}
		if(externalChangeRequestSpecList.isEmpty()){
			return user;
		}
		
		for(ChangeRequestSpec changeRequestSpecItem: externalChangeRequestSpecList){
			changeRequestSpecItem.clearProject();
			changeRequestSpecItem.clearOwner();
			
		}
		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = user.getChangeRequestSpecList();		
		changeRequestSpecList.addAllToRemoveList(externalChangeRequestSpecList);
		return user;
	}
	
	public int countChangeRequestSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.OWNER_PROPERTY, userId);
		key.put(ChangeRequestSpec.PROJECT_PROPERTY, projectId);
		
		int count = getChangeRequestSpecDAO().countChangeRequestSpecWithKey(key, options);
		return count;
	}
	
	public User planToRemovePageContentSpecList(User user, String pageContentSpecIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.OWNER_PROPERTY, user.getId());
		key.put(PageContentSpec.ID_PROPERTY, pageContentSpecIds);
		
		SmartList<PageContentSpec> externalPageContentSpecList = getPageContentSpecDAO().
				findPageContentSpecWithKey(key, options);
		if(externalPageContentSpecList == null){
			return user;
		}
		if(externalPageContentSpecList.isEmpty()){
			return user;
		}
		
		for(PageContentSpec pageContentSpecItem: externalPageContentSpecList){

			pageContentSpecItem.clearFromAll();
		}
		
		
		SmartList<PageContentSpec> pageContentSpecList = user.getPageContentSpecList();		
		pageContentSpecList.addAllToRemoveList(externalPageContentSpecList);
		return user;	
	
	}


	//disconnect User with project in PageContentSpec
	public User planToRemovePageContentSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.OWNER_PROPERTY, user.getId());
		key.put(PageContentSpec.PROJECT_PROPERTY, projectId);
		
		SmartList<PageContentSpec> externalPageContentSpecList = getPageContentSpecDAO().
				findPageContentSpecWithKey(key, options);
		if(externalPageContentSpecList == null){
			return user;
		}
		if(externalPageContentSpecList.isEmpty()){
			return user;
		}
		
		for(PageContentSpec pageContentSpecItem: externalPageContentSpecList){
			pageContentSpecItem.clearProject();
			pageContentSpecItem.clearOwner();
			
		}
		
		
		SmartList<PageContentSpec> pageContentSpecList = user.getPageContentSpecList();		
		pageContentSpecList.addAllToRemoveList(externalPageContentSpecList);
		return user;
	}
	
	public int countPageContentSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.OWNER_PROPERTY, userId);
		key.put(PageContentSpec.PROJECT_PROPERTY, projectId);
		
		int count = getPageContentSpecDAO().countPageContentSpecWithKey(key, options);
		return count;
	}
	

		
	protected User saveUserProjectList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<UserProject> userProjectList = user.getUserProjectList();
		if(userProjectList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removeUserProjectList(User user, Map<String,Object> options){
	
	
		SmartList<UserProject> userProjectList = user.getUserProjectList();
		if(userProjectList == null){
			return user;
		}	
	
		SmartList<UserProject> toRemoveUserProjectList = userProjectList.getToRemoveList();
		
		if(toRemoveUserProjectList == null){
			return user;
		}
		if(toRemoveUserProjectList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getUserProjectDAO().removeUserProjectList(toRemoveUserProjectList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User savePageFlowSpecList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<PageFlowSpec> pageFlowSpecList = user.getPageFlowSpecList();
		if(pageFlowSpecList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removePageFlowSpecList(User user, Map<String,Object> options){
	
	
		SmartList<PageFlowSpec> pageFlowSpecList = user.getPageFlowSpecList();
		if(pageFlowSpecList == null){
			return user;
		}	
	
		SmartList<PageFlowSpec> toRemovePageFlowSpecList = pageFlowSpecList.getToRemoveList();
		
		if(toRemovePageFlowSpecList == null){
			return user;
		}
		if(toRemovePageFlowSpecList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPageFlowSpecDAO().removePageFlowSpecList(toRemovePageFlowSpecList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User saveWorkFlowSpecList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<WorkFlowSpec> workFlowSpecList = user.getWorkFlowSpecList();
		if(workFlowSpecList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removeWorkFlowSpecList(User user, Map<String,Object> options){
	
	
		SmartList<WorkFlowSpec> workFlowSpecList = user.getWorkFlowSpecList();
		if(workFlowSpecList == null){
			return user;
		}	
	
		SmartList<WorkFlowSpec> toRemoveWorkFlowSpecList = workFlowSpecList.getToRemoveList();
		
		if(toRemoveWorkFlowSpecList == null){
			return user;
		}
		if(toRemoveWorkFlowSpecList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getWorkFlowSpecDAO().removeWorkFlowSpecList(toRemoveWorkFlowSpecList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User saveChangeRequestSpecList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<ChangeRequestSpec> changeRequestSpecList = user.getChangeRequestSpecList();
		if(changeRequestSpecList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removeChangeRequestSpecList(User user, Map<String,Object> options){
	
	
		SmartList<ChangeRequestSpec> changeRequestSpecList = user.getChangeRequestSpecList();
		if(changeRequestSpecList == null){
			return user;
		}	
	
		SmartList<ChangeRequestSpec> toRemoveChangeRequestSpecList = changeRequestSpecList.getToRemoveList();
		
		if(toRemoveChangeRequestSpecList == null){
			return user;
		}
		if(toRemoveChangeRequestSpecList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getChangeRequestSpecDAO().removeChangeRequestSpecList(toRemoveChangeRequestSpecList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		
	protected User savePageContentSpecList(User user, Map<String,Object> options){
		
		
		
		
		SmartList<PageContentSpec> pageContentSpecList = user.getPageContentSpecList();
		if(pageContentSpecList == null){
			//null list means nothing
			return user;
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
		
		
		return user;
	
	}
	
	protected User removePageContentSpecList(User user, Map<String,Object> options){
	
	
		SmartList<PageContentSpec> pageContentSpecList = user.getPageContentSpecList();
		if(pageContentSpecList == null){
			return user;
		}	
	
		SmartList<PageContentSpec> toRemovePageContentSpecList = pageContentSpecList.getToRemoveList();
		
		if(toRemovePageContentSpecList == null){
			return user;
		}
		if(toRemovePageContentSpecList.isEmpty()){
			return user;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPageContentSpecDAO().removePageContentSpecList(toRemovePageContentSpecList,options);
		
		return user;
	
	}
	
	

 	
 	
	
	
	
		

	public User present(User user,Map<String, Object> options){
	
		presentUserProjectList(user,options);
		presentPageFlowSpecList(user,options);
		presentWorkFlowSpecList(user,options);
		presentChangeRequestSpecList(user,options);
		presentPageContentSpecList(user,options);

		return user;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected User presentUserProjectList(
			User user,
			Map<String, Object> options) {

		SmartList<UserProject> userProjectList = user.getUserProjectList();		
				SmartList<UserProject> newList= presentSubList(user.getId(),
				userProjectList,
				options,
				getUserProjectDAO()::countUserProjectByUser,
				getUserProjectDAO()::findUserProjectByUser
				);

		
		user.setUserProjectList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentPageFlowSpecList(
			User user,
			Map<String, Object> options) {

		SmartList<PageFlowSpec> pageFlowSpecList = user.getPageFlowSpecList();		
				SmartList<PageFlowSpec> newList= presentSubList(user.getId(),
				pageFlowSpecList,
				options,
				getPageFlowSpecDAO()::countPageFlowSpecByOwner,
				getPageFlowSpecDAO()::findPageFlowSpecByOwner
				);

		
		user.setPageFlowSpecList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentWorkFlowSpecList(
			User user,
			Map<String, Object> options) {

		SmartList<WorkFlowSpec> workFlowSpecList = user.getWorkFlowSpecList();		
				SmartList<WorkFlowSpec> newList= presentSubList(user.getId(),
				workFlowSpecList,
				options,
				getWorkFlowSpecDAO()::countWorkFlowSpecByOwner,
				getWorkFlowSpecDAO()::findWorkFlowSpecByOwner
				);

		
		user.setWorkFlowSpecList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentChangeRequestSpecList(
			User user,
			Map<String, Object> options) {

		SmartList<ChangeRequestSpec> changeRequestSpecList = user.getChangeRequestSpecList();		
				SmartList<ChangeRequestSpec> newList= presentSubList(user.getId(),
				changeRequestSpecList,
				options,
				getChangeRequestSpecDAO()::countChangeRequestSpecByOwner,
				getChangeRequestSpecDAO()::findChangeRequestSpecByOwner
				);

		
		user.setChangeRequestSpecList(newList);
		

		return user;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected User presentPageContentSpecList(
			User user,
			Map<String, Object> options) {

		SmartList<PageContentSpec> pageContentSpecList = user.getPageContentSpecList();		
				SmartList<PageContentSpec> newList= presentSubList(user.getId(),
				pageContentSpecList,
				options,
				getPageContentSpecDAO()::countPageContentSpecByOwner,
				getPageContentSpecDAO()::findPageContentSpecByOwner
				);

		
		user.setPageContentSpecList(newList);
		

		return user;
	}			
		

	
    public SmartList<User> requestCandidateUserForUserProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, UserTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForPageFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, UserTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForWorkFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, UserTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForChangeRequestSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, UserTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getUserMapper());
    }
		
    public SmartList<User> requestCandidateUserForPageContentSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(UserTable.COLUMN_NAME, UserTable.COLUMN_COMPANY, filterKey, pageNo, pageSize, getUserMapper());
    }
		

	protected String getTableName(){
		return UserTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<User> userList) {		
		this.enhanceListInternal(userList, this.getUserMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:UserProject的user的UserProjectList
	public SmartList<UserProject> loadOurUserProjectList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(UserProject.USER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<UserProject> loadedObjs = userContext.getDAOGroup().getUserProjectDAO().findUserProjectWithKey(key, options);
		Map<String, List<UserProject>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getUser().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:PageFlowSpec的owner的PageFlowSpecList
	public SmartList<PageFlowSpec> loadOurPageFlowSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageFlowSpec.OWNER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<PageFlowSpec> loadedObjs = userContext.getDAOGroup().getPageFlowSpecDAO().findPageFlowSpecWithKey(key, options);
		Map<String, List<PageFlowSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getOwner().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:WorkFlowSpec的owner的WorkFlowSpecList
	public SmartList<WorkFlowSpec> loadOurWorkFlowSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(WorkFlowSpec.OWNER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<WorkFlowSpec> loadedObjs = userContext.getDAOGroup().getWorkFlowSpecDAO().findWorkFlowSpecWithKey(key, options);
		Map<String, List<WorkFlowSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getOwner().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestSpec的owner的ChangeRequestSpecList
	public SmartList<ChangeRequestSpec> loadOurChangeRequestSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestSpec.OWNER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ChangeRequestSpec> loadedObjs = userContext.getDAOGroup().getChangeRequestSpecDAO().findChangeRequestSpecWithKey(key, options);
		Map<String, List<ChangeRequestSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getOwner().getId()));
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
	
	// 需要一个加载引用我的对象的enhance方法:PageContentSpec的owner的PageContentSpecList
	public SmartList<PageContentSpec> loadOurPageContentSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PageContentSpec.OWNER_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<PageContentSpec> loadedObjs = userContext.getDAOGroup().getPageContentSpecDAO().findPageContentSpecWithKey(key, options);
		Map<String, List<PageContentSpec>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getOwner().getId()));
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
		List<User> userList = ownerEntity.collectRefsWithType(User.INTERNAL_TYPE);
		this.enhanceList(userList);
		
	}
	
	@Override
	public SmartList<User> findUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getUserMapper());

	}
	@Override
	public int countUserWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<User> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getUserMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateUser executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateUser result = new CandidateUser();
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


