
package com.cla.sds.platform;

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


import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.company.Company;
import com.cla.sds.changerequesttype.ChangeRequestType;

import com.cla.sds.changerequesttype.ChangeRequestTypeDAO;
import com.cla.sds.changerequest.ChangeRequestDAO;
import com.cla.sds.company.CompanyDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class PlatformJDBCTemplateDAO extends SdsBaseDAOImpl implements PlatformDAO{

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

	protected ChangeRequestTypeDAO changeRequestTypeDAO;
	public void setChangeRequestTypeDAO(ChangeRequestTypeDAO changeRequestTypeDAO){
 	
 		if(changeRequestTypeDAO == null){
 			throw new IllegalStateException("Do not try to set changeRequestTypeDAO to null.");
 		}
	 	this.changeRequestTypeDAO = changeRequestTypeDAO;
 	}
 	public ChangeRequestTypeDAO getChangeRequestTypeDAO(){
 		if(this.changeRequestTypeDAO == null){
 			throw new IllegalStateException("The changeRequestTypeDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.changeRequestTypeDAO;
 	}	

	protected ChangeRequestDAO changeRequestDAO;
	public void setChangeRequestDAO(ChangeRequestDAO changeRequestDAO){
 	
 		if(changeRequestDAO == null){
 			throw new IllegalStateException("Do not try to set changeRequestDAO to null.");
 		}
	 	this.changeRequestDAO = changeRequestDAO;
 	}
 	public ChangeRequestDAO getChangeRequestDAO(){
 		if(this.changeRequestDAO == null){
 			throw new IllegalStateException("The changeRequestDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.changeRequestDAO;
 	}	

	
	/*
	protected Platform load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(accessKey, options);
	}
	*/
	
	public SmartList<Platform> loadAll() {
	    return this.loadAll(getPlatformMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Platform load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(PlatformTable.withId(id), options);
	}
	
	
	
	public Platform save(Platform platform,Map<String,Object> options){
		
		String methodName="save(Platform platform,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(platform, methodName, "platform");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPlatform(platform,options);
	}
	public Platform clone(String platformId, Map<String,Object> options) throws Exception{
	
		return clone(PlatformTable.withId(platformId),options);
	}
	
	protected Platform clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String platformId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Platform newPlatform = loadInternalPlatform(accessKey, options);
		newPlatform.setVersion(0);
		
		
 		
 		if(isSaveCompanyListEnabled(options)){
 			for(Company item: newPlatform.getCompanyList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveChangeRequestTypeListEnabled(options)){
 			for(ChangeRequestType item: newPlatform.getChangeRequestTypeList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveChangeRequestListEnabled(options)){
 			for(ChangeRequest item: newPlatform.getChangeRequestList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPlatform(newPlatform,options);
		
		return newPlatform;
	}
	
	
	
	

	protected void throwIfHasException(String platformId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PlatformVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PlatformNotFoundException(
					"The " + this.getTableName() + "(" + platformId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String platformId, int version) throws Exception{
	
		String methodName="delete(String platformId, int version)";
		assertMethodArgumentNotNull(platformId, methodName, "platformId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{platformId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(platformId,version);
		}
		
	
	}
	
	
	
	
	

	public Platform disconnectFromAll(String platformId, int version) throws Exception{
	
		
		Platform platform = loadInternalPlatform(PlatformTable.withId(platformId), emptyOptions());
		platform.clearFromAll();
		this.savePlatform(platform);
		return platform;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PlatformTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "platform";
	}
	@Override
	protected String getBeanName() {
		
		return "platform";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PlatformTokens.checkOptions(options, optionToCheck);
	
	}


		
	
	protected boolean isExtractCompanyListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.COMPANY_LIST);
 	}
 	protected boolean isAnalyzeCompanyListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeCompanyListEnabled();
 	}
	
	protected boolean isSaveCompanyListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.COMPANY_LIST);
		
 	}
 	
		
	
	protected boolean isExtractChangeRequestTypeListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.CHANGE_REQUEST_TYPE_LIST);
 	}
 	protected boolean isAnalyzeChangeRequestTypeListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeChangeRequestTypeListEnabled();
 	}
	
	protected boolean isSaveChangeRequestTypeListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.CHANGE_REQUEST_TYPE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractChangeRequestListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.CHANGE_REQUEST_LIST);
 	}
 	protected boolean isAnalyzeChangeRequestListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeChangeRequestListEnabled();
 	}
	
	protected boolean isSaveChangeRequestListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.CHANGE_REQUEST_LIST);
		
 	}
 	
		

	

	protected PlatformMapper getPlatformMapper(){
		return new PlatformMapper();
	}

	
	
	protected Platform extractPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Platform platform = loadSingleObject(accessKey, getPlatformMapper());
			return platform;
		}catch(EmptyResultDataAccessException e){
			throw new PlatformNotFoundException("Platform("+accessKey+") is not found!");
		}

	}

	
	

	protected Platform loadInternalPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Platform platform = extractPlatform(accessKey, loadOptions);

		
		if(isExtractCompanyListEnabled(loadOptions)){
	 		extractCompanyList(platform, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeCompanyListEnabled(loadOptions)){
	 		analyzeCompanyList(platform, loadOptions);
 		}
 		
		
		if(isExtractChangeRequestTypeListEnabled(loadOptions)){
	 		extractChangeRequestTypeList(platform, loadOptions);
 		}	
 		
 		
		
		if(isExtractChangeRequestListEnabled(loadOptions)){
	 		extractChangeRequestList(platform, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeChangeRequestListEnabled(loadOptions)){
	 		analyzeChangeRequestList(platform, loadOptions);
 		}
 		
		
		return platform;
		
	}

	
		
	protected void enhanceCompanyList(SmartList<Company> companyList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractCompanyList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Company> companyList = getCompanyDAO().findCompanyByPlatform(platform.getId(),options);
		if(companyList != null){
			enhanceCompanyList(companyList,options);
			platform.setCompanyList(companyList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeCompanyList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Company> companyList = platform.getCompanyList();
		if(companyList != null){
			getCompanyDAO().analyzeCompanyByPlatform(companyList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractChangeRequestTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequestType> changeRequestTypeList = getChangeRequestTypeDAO().findChangeRequestTypeByPlatform(platform.getId(),options);
		if(changeRequestTypeList != null){
			enhanceChangeRequestTypeList(changeRequestTypeList,options);
			platform.setChangeRequestTypeList(changeRequestTypeList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeChangeRequestTypeList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();
		if(changeRequestTypeList != null){
			getChangeRequestTypeDAO().analyzeChangeRequestTypeByPlatform(changeRequestTypeList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractChangeRequestList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequest> changeRequestList = getChangeRequestDAO().findChangeRequestByPlatform(platform.getId(),options);
		if(changeRequestList != null){
			enhanceChangeRequestList(changeRequestList,options);
			platform.setChangeRequestList(changeRequestList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeChangeRequestList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();
		if(changeRequestList != null){
			getChangeRequestDAO().analyzeChangeRequestByPlatform(changeRequestList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
		
 	
		
		
		

	

	protected Platform savePlatform(Platform  platform){
		
		if(!platform.isChanged()){
			return platform;
		}
		
		
		String SQL=this.getSavePlatformSQL(platform);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePlatformParameters(platform);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		platform.incVersion();
		return platform;
	
	}
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPlatformList(platformList);
		
		batchPlatformCreate((List<Platform>)lists[CREATE_LIST_INDEX]);
		
		batchPlatformUpdate((List<Platform>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Platform platform:platformList){
			if(platform.isChanged()){
				platform.incVersion();
			}
			
		
		}
		
		
		return platformList;
	}

	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		
		
		super.removeList(platformList, options);
		
		return platformList;
		
		
	}
	
	protected List<Object[]> preparePlatformBatchCreateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			Object [] parameters = preparePlatformCreateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePlatformBatchUpdateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			if(!platform.isChanged()){
				continue;
			}
			Object [] parameters = preparePlatformUpdateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPlatformCreate(List<Platform> platformList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePlatformBatchCreateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPlatformUpdate(List<Platform> platformList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePlatformBatchUpdateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPlatformList(List<Platform> platformList){
		
		List<Platform> platformCreateList=new ArrayList<Platform>();
		List<Platform> platformUpdateList=new ArrayList<Platform>();
		
		for(Platform platform: platformList){
			if(isUpdateRequest(platform)){
				platformUpdateList.add( platform);
				continue;
			}
			platformCreateList.add(platform);
		}
		
		return new Object[]{platformCreateList,platformUpdateList};
	}
	
	protected boolean isUpdateRequest(Platform platform){
 		return platform.getVersion() > 0;
 	}
 	protected String getSavePlatformSQL(Platform platform){
 		if(isUpdateRequest(platform)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePlatformParameters(Platform platform){
 		if(isUpdateRequest(platform) ){
 			return preparePlatformUpdateParameters(platform);
 		}
 		return preparePlatformCreateParameters(platform);
 	}
 	protected Object[] preparePlatformUpdateParameters(Platform platform){
 		Object[] parameters = new Object[6];
 
 		
 		parameters[0] = platform.getName();
 		
 		
 		parameters[1] = platform.getCreateTime();
 		
 		
 		parameters[2] = platform.getLastUpdateTime();
 				
 		parameters[3] = platform.nextVersion();
 		parameters[4] = platform.getId();
 		parameters[5] = platform.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePlatformCreateParameters(Platform platform){
		Object[] parameters = new Object[4];
		String newPlatformId=getNextId();
		platform.setId(newPlatformId);
		parameters[0] =  platform.getId();
 
 		
 		parameters[1] = platform.getName();
 		
 		
 		parameters[2] = platform.getCreateTime();
 		
 		
 		parameters[3] = platform.getLastUpdateTime();
 				
 				
 		return parameters;
 	}
 	
	protected Platform saveInternalPlatform(Platform platform, Map<String,Object> options){
		
		savePlatform(platform);

		
		if(isSaveCompanyListEnabled(options)){
	 		saveCompanyList(platform, options);
	 		//removeCompanyList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveChangeRequestTypeListEnabled(options)){
	 		saveChangeRequestTypeList(platform, options);
	 		//removeChangeRequestTypeList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveChangeRequestListEnabled(options)){
	 		saveChangeRequestList(platform, options);
	 		//removeChangeRequestList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		return platform;
		
	}
	
	
	
	//======================================================================================
	

	
	public Platform planToRemoveCompanyList(Platform platform, String companyIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Company.PLATFORM_PROPERTY, platform.getId());
		key.put(Company.ID_PROPERTY, companyIds);
		
		SmartList<Company> externalCompanyList = getCompanyDAO().
				findCompanyWithKey(key, options);
		if(externalCompanyList == null){
			return platform;
		}
		if(externalCompanyList.isEmpty()){
			return platform;
		}
		
		for(Company companyItem: externalCompanyList){

			companyItem.clearFromAll();
		}
		
		
		SmartList<Company> companyList = platform.getCompanyList();		
		companyList.addAllToRemoveList(externalCompanyList);
		return platform;	
	
	}


	public Platform planToRemoveChangeRequestTypeList(Platform platform, String changeRequestTypeIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestType.PLATFORM_PROPERTY, platform.getId());
		key.put(ChangeRequestType.ID_PROPERTY, changeRequestTypeIds);
		
		SmartList<ChangeRequestType> externalChangeRequestTypeList = getChangeRequestTypeDAO().
				findChangeRequestTypeWithKey(key, options);
		if(externalChangeRequestTypeList == null){
			return platform;
		}
		if(externalChangeRequestTypeList.isEmpty()){
			return platform;
		}
		
		for(ChangeRequestType changeRequestTypeItem: externalChangeRequestTypeList){

			changeRequestTypeItem.clearFromAll();
		}
		
		
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();		
		changeRequestTypeList.addAllToRemoveList(externalChangeRequestTypeList);
		return platform;	
	
	}


	public Platform planToRemoveChangeRequestList(Platform platform, String changeRequestIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, platform.getId());
		key.put(ChangeRequest.ID_PROPERTY, changeRequestIds);
		
		SmartList<ChangeRequest> externalChangeRequestList = getChangeRequestDAO().
				findChangeRequestWithKey(key, options);
		if(externalChangeRequestList == null){
			return platform;
		}
		if(externalChangeRequestList.isEmpty()){
			return platform;
		}
		
		for(ChangeRequest changeRequestItem: externalChangeRequestList){

			changeRequestItem.clearFromAll();
		}
		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();		
		changeRequestList.addAllToRemoveList(externalChangeRequestList);
		return platform;	
	
	}


	//disconnect Platform with request_type in ChangeRequest
	public Platform planToRemoveChangeRequestListWithRequestType(Platform platform, String requestTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, platform.getId());
		key.put(ChangeRequest.REQUEST_TYPE_PROPERTY, requestTypeId);
		
		SmartList<ChangeRequest> externalChangeRequestList = getChangeRequestDAO().
				findChangeRequestWithKey(key, options);
		if(externalChangeRequestList == null){
			return platform;
		}
		if(externalChangeRequestList.isEmpty()){
			return platform;
		}
		
		for(ChangeRequest changeRequestItem: externalChangeRequestList){
			changeRequestItem.clearRequestType();
			changeRequestItem.clearPlatform();
			
		}
		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();		
		changeRequestList.addAllToRemoveList(externalChangeRequestList);
		return platform;
	}
	
	public int countChangeRequestListWithRequestType(String platformId, String requestTypeId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, platformId);
		key.put(ChangeRequest.REQUEST_TYPE_PROPERTY, requestTypeId);
		
		int count = getChangeRequestDAO().countChangeRequestWithKey(key, options);
		return count;
	}
	

		
	protected Platform saveCompanyList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Company> companyList = platform.getCompanyList();
		if(companyList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Company> mergedUpdateCompanyList = new SmartList<Company>();
		
		
		mergedUpdateCompanyList.addAll(companyList); 
		if(companyList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateCompanyList.addAll(companyList.getToRemoveList());
			companyList.removeAll(companyList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getCompanyDAO().saveCompanyList(mergedUpdateCompanyList,options);
		
		if(companyList.getToRemoveList() != null){
			companyList.removeAll(companyList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeCompanyList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Company> companyList = platform.getCompanyList();
		if(companyList == null){
			return platform;
		}	
	
		SmartList<Company> toRemoveCompanyList = companyList.getToRemoveList();
		
		if(toRemoveCompanyList == null){
			return platform;
		}
		if(toRemoveCompanyList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getCompanyDAO().removeCompanyList(toRemoveCompanyList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveChangeRequestTypeList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();
		if(changeRequestTypeList == null){
			//null list means nothing
			return platform;
		}
		SmartList<ChangeRequestType> mergedUpdateChangeRequestTypeList = new SmartList<ChangeRequestType>();
		
		
		mergedUpdateChangeRequestTypeList.addAll(changeRequestTypeList); 
		if(changeRequestTypeList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateChangeRequestTypeList.addAll(changeRequestTypeList.getToRemoveList());
			changeRequestTypeList.removeAll(changeRequestTypeList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getChangeRequestTypeDAO().saveChangeRequestTypeList(mergedUpdateChangeRequestTypeList,options);
		
		if(changeRequestTypeList.getToRemoveList() != null){
			changeRequestTypeList.removeAll(changeRequestTypeList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeChangeRequestTypeList(Platform platform, Map<String,Object> options){
	
	
		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();
		if(changeRequestTypeList == null){
			return platform;
		}	
	
		SmartList<ChangeRequestType> toRemoveChangeRequestTypeList = changeRequestTypeList.getToRemoveList();
		
		if(toRemoveChangeRequestTypeList == null){
			return platform;
		}
		if(toRemoveChangeRequestTypeList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getChangeRequestTypeDAO().removeChangeRequestTypeList(toRemoveChangeRequestTypeList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveChangeRequestList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();
		if(changeRequestList == null){
			//null list means nothing
			return platform;
		}
		SmartList<ChangeRequest> mergedUpdateChangeRequestList = new SmartList<ChangeRequest>();
		
		
		mergedUpdateChangeRequestList.addAll(changeRequestList); 
		if(changeRequestList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateChangeRequestList.addAll(changeRequestList.getToRemoveList());
			changeRequestList.removeAll(changeRequestList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getChangeRequestDAO().saveChangeRequestList(mergedUpdateChangeRequestList,options);
		
		if(changeRequestList.getToRemoveList() != null){
			changeRequestList.removeAll(changeRequestList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeChangeRequestList(Platform platform, Map<String,Object> options){
	
	
		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();
		if(changeRequestList == null){
			return platform;
		}	
	
		SmartList<ChangeRequest> toRemoveChangeRequestList = changeRequestList.getToRemoveList();
		
		if(toRemoveChangeRequestList == null){
			return platform;
		}
		if(toRemoveChangeRequestList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getChangeRequestDAO().removeChangeRequestList(toRemoveChangeRequestList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		

	public Platform present(Platform platform,Map<String, Object> options){
	
		presentCompanyList(platform,options);
		presentChangeRequestTypeList(platform,options);
		presentChangeRequestList(platform,options);

		return platform;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentCompanyList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Company> companyList = platform.getCompanyList();		
				SmartList<Company> newList= presentSubList(platform.getId(),
				companyList,
				options,
				getCompanyDAO()::countCompanyByPlatform,
				getCompanyDAO()::findCompanyByPlatform
				);

		
		platform.setCompanyList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentChangeRequestTypeList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<ChangeRequestType> changeRequestTypeList = platform.getChangeRequestTypeList();		
				SmartList<ChangeRequestType> newList= presentSubList(platform.getId(),
				changeRequestTypeList,
				options,
				getChangeRequestTypeDAO()::countChangeRequestTypeByPlatform,
				getChangeRequestTypeDAO()::findChangeRequestTypeByPlatform
				);

		
		platform.setChangeRequestTypeList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentChangeRequestList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<ChangeRequest> changeRequestList = platform.getChangeRequestList();		
				SmartList<ChangeRequest> newList= presentSubList(platform.getId(),
				changeRequestList,
				options,
				getChangeRequestDAO()::countChangeRequestByPlatform,
				getChangeRequestDAO()::findChangeRequestByPlatform
				);

		
		platform.setChangeRequestList(newList);
		

		return platform;
	}			
		

	
    public SmartList<Platform> requestCandidatePlatformForCompany(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, null, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForChangeRequestType(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, null, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForChangeRequest(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, null, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		

	protected String getTableName(){
		return PlatformTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Platform> platformList) {		
		this.enhanceListInternal(platformList, this.getPlatformMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Company的platform的CompanyList
	public SmartList<Company> loadOurCompanyList(SdsUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Company.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Company> loadedObjs = userContext.getDAOGroup().getCompanyDAO().findCompanyWithKey(key, options);
		Map<String, List<Company>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Company> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Company> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setCompanyList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestType的platform的ChangeRequestTypeList
	public SmartList<ChangeRequestType> loadOurChangeRequestTypeList(SdsUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequestType.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ChangeRequestType> loadedObjs = userContext.getDAOGroup().getChangeRequestTypeDAO().findChangeRequestTypeWithKey(key, options);
		Map<String, List<ChangeRequestType>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ChangeRequestType> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ChangeRequestType> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setChangeRequestTypeList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequest的platform的ChangeRequestList
	public SmartList<ChangeRequest> loadOurChangeRequestList(SdsUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(ChangeRequest.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<ChangeRequest> loadedObjs = userContext.getDAOGroup().getChangeRequestDAO().findChangeRequestWithKey(key, options);
		Map<String, List<ChangeRequest>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<ChangeRequest> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<ChangeRequest> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setChangeRequestList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Platform> platformList = ownerEntity.collectRefsWithType(Platform.INTERNAL_TYPE);
		this.enhanceList(platformList);
		
	}
	
	@Override
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPlatformMapper());

	}
	@Override
	public int countPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Platform> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPlatformMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidatePlatform executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
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
	
	
    
	public Map<String, Integer> countBySql(String sql, Object[] params) {
		if (params == null || params.length == 0) {
			return new HashMap<>();
		}
		List<Map<String, Object>> result = this.getJdbcTemplateObject().queryForList(sql, params);
		if (result == null || result.isEmpty()) {
			return new HashMap<>();
		}
		Map<String, Integer> cntMap = new HashMap<>();
		for (Map<String, Object> data : result) {
			String key = (String) data.get("id");
			Number value = (Number) data.get("count");
			cntMap.put(key, value.intValue());
		}
		this.logSQLAndParameters("countBySql", sql, params, cntMap.size() + " Counts");
		return cntMap;
	}

	public Integer singleCountBySql(String sql, Object[] params) {
		Integer cnt = this.getJdbcTemplateObject().queryForObject(sql, params, Integer.class);
		logSQLAndParameters("singleCountBySql", sql, params, cnt + "");
		return cnt;
	}

	public BigDecimal summaryBySql(String sql, Object[] params) {
		BigDecimal cnt = this.getJdbcTemplateObject().queryForObject(sql, params, BigDecimal.class);
		logSQLAndParameters("summaryBySql", sql, params, cnt + "");
		return cnt == null ? BigDecimal.ZERO : cnt;
	}

	public <T> List<T> queryForList(String sql, Object[] params, Class<T> claxx) {
		List<T> result = this.getJdbcTemplateObject().queryForList(sql, params, claxx);
		logSQLAndParameters("queryForList", sql, params, result.size() + " items");
		return result;
	}

	public Map<String, Object> queryForMap(String sql, Object[] params) throws DataAccessException {
		Map<String, Object> result = null;
		try {
			result = this.getJdbcTemplateObject().queryForMap(sql, params);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// 空结果，返回null
		}
		logSQLAndParameters("queryForObject", sql, params, result == null ? "not found" : String.valueOf(result));
		return result;
	}

	public <T> T queryForObject(String sql, Object[] params, Class<T> claxx) throws DataAccessException {
		T result = null;
		try {
			result = this.getJdbcTemplateObject().queryForObject(sql, params, claxx);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// 空结果，返回null
		}
		logSQLAndParameters("queryForObject", sql, params, result == null ? "not found" : String.valueOf(result));
		return result;
	}

	public List<Map<String, Object>> queryAsMapList(String sql, Object[] params) {
		List<Map<String, Object>> result = getJdbcTemplateObject().queryForList(sql, params);
		logSQLAndParameters("queryAsMapList", sql, params, result.size() + " items");
		return result;
	}

	public synchronized int updateBySql(String sql, Object[] params) {
		int result = getJdbcTemplateObject().update(sql, params);
		logSQLAndParameters("updateBySql", sql, params, result + " items");
		return result;
	}

	public void execSqlWithRowCallback(String sql, Object[] args, RowCallbackHandler callback) {
		getJdbcTemplateObject().query(sql, args, callback);
	}

	public void executeSql(String sql) {
		logSQLAndParameters("executeSql", sql, new Object[] {}, "");
		getJdbcTemplateObject().execute(sql);
	}


}


