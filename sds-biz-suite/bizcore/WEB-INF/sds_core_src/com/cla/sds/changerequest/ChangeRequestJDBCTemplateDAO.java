
package com.cla.sds.changerequest;

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


import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.platform.Platform;
import com.cla.sds.changerequesttype.ChangeRequestType;

import com.cla.sds.eventupdateprofile.EventUpdateProfileDAO;
import com.cla.sds.changerequesttype.ChangeRequestTypeDAO;
import com.cla.sds.platform.PlatformDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class ChangeRequestJDBCTemplateDAO extends SdsBaseDAOImpl implements ChangeRequestDAO{

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

	protected EventUpdateProfileDAO eventUpdateProfileDAO;
	public void setEventUpdateProfileDAO(EventUpdateProfileDAO eventUpdateProfileDAO){
 	
 		if(eventUpdateProfileDAO == null){
 			throw new IllegalStateException("Do not try to set eventUpdateProfileDAO to null.");
 		}
	 	this.eventUpdateProfileDAO = eventUpdateProfileDAO;
 	}
 	public EventUpdateProfileDAO getEventUpdateProfileDAO(){
 		if(this.eventUpdateProfileDAO == null){
 			throw new IllegalStateException("The eventUpdateProfileDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.eventUpdateProfileDAO;
 	}	

	
	/*
	protected ChangeRequest load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequest(accessKey, options);
	}
	*/
	
	public SmartList<ChangeRequest> loadAll() {
	    return this.loadAll(getChangeRequestMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public ChangeRequest load(String id,Map<String,Object> options) throws Exception{
		return loadInternalChangeRequest(ChangeRequestTable.withId(id), options);
	}
	
	
	
	public ChangeRequest save(ChangeRequest changeRequest,Map<String,Object> options){
		
		String methodName="save(ChangeRequest changeRequest,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(changeRequest, methodName, "changeRequest");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalChangeRequest(changeRequest,options);
	}
	public ChangeRequest clone(String changeRequestId, Map<String,Object> options) throws Exception{
	
		return clone(ChangeRequestTable.withId(changeRequestId),options);
	}
	
	protected ChangeRequest clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String changeRequestId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		ChangeRequest newChangeRequest = loadInternalChangeRequest(accessKey, options);
		newChangeRequest.setVersion(0);
		
		
 		
 		if(isSaveEventUpdateProfileListEnabled(options)){
 			for(EventUpdateProfile item: newChangeRequest.getEventUpdateProfileList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalChangeRequest(newChangeRequest,options);
		
		return newChangeRequest;
	}
	
	
	
	

	protected void throwIfHasException(String changeRequestId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ChangeRequestVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ChangeRequestNotFoundException(
					"The " + this.getTableName() + "(" + changeRequestId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String changeRequestId, int version) throws Exception{
	
		String methodName="delete(String changeRequestId, int version)";
		assertMethodArgumentNotNull(changeRequestId, methodName, "changeRequestId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{changeRequestId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(changeRequestId,version);
		}
		
	
	}
	
	
	
	
	

	public ChangeRequest disconnectFromAll(String changeRequestId, int version) throws Exception{
	
		
		ChangeRequest changeRequest = loadInternalChangeRequest(ChangeRequestTable.withId(changeRequestId), emptyOptions());
		changeRequest.clearFromAll();
		this.saveChangeRequest(changeRequest);
		return changeRequest;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ChangeRequestTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "change_request";
	}
	@Override
	protected String getBeanName() {
		
		return "changeRequest";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ChangeRequestTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractRequestTypeEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestTokens.REQUESTTYPE);
 	}

 	protected boolean isSaveRequestTypeEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestTokens.REQUESTTYPE);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ChangeRequestTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ChangeRequestTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractEventUpdateProfileListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ChangeRequestTokens.EVENT_UPDATE_PROFILE_LIST);
 	}
 	protected boolean isAnalyzeEventUpdateProfileListEnabled(Map<String,Object> options){		 		
 		return ChangeRequestTokens.of(options).analyzeEventUpdateProfileListEnabled();
 	}
	
	protected boolean isSaveEventUpdateProfileListEnabled(Map<String,Object> options){
		return checkOptions(options, ChangeRequestTokens.EVENT_UPDATE_PROFILE_LIST);
		
 	}
 	
		

	

	protected ChangeRequestMapper getChangeRequestMapper(){
		return new ChangeRequestMapper();
	}

	
	
	protected ChangeRequest extractChangeRequest(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			ChangeRequest changeRequest = loadSingleObject(accessKey, getChangeRequestMapper());
			return changeRequest;
		}catch(EmptyResultDataAccessException e){
			throw new ChangeRequestNotFoundException("ChangeRequest("+accessKey+") is not found!");
		}

	}

	
	

	protected ChangeRequest loadInternalChangeRequest(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		ChangeRequest changeRequest = extractChangeRequest(accessKey, loadOptions);
 	
 		if(isExtractRequestTypeEnabled(loadOptions)){
	 		extractRequestType(changeRequest, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(changeRequest, loadOptions);
 		}
 
		
		if(isExtractEventUpdateProfileListEnabled(loadOptions)){
	 		extractEventUpdateProfileList(changeRequest, loadOptions);
 		}	
 		
 		
 		if(isAnalyzeEventUpdateProfileListEnabled(loadOptions)){
	 		analyzeEventUpdateProfileList(changeRequest, loadOptions);
 		}
 		
		
		return changeRequest;
		
	}

	 

 	protected ChangeRequest extractRequestType(ChangeRequest changeRequest, Map<String,Object> options) throws Exception{

		if(changeRequest.getRequestType() == null){
			return changeRequest;
		}
		String requestTypeId = changeRequest.getRequestType().getId();
		if( requestTypeId == null){
			return changeRequest;
		}
		ChangeRequestType requestType = getChangeRequestTypeDAO().load(requestTypeId,options);
		if(requestType != null){
			changeRequest.setRequestType(requestType);
		}
		
 		
 		return changeRequest;
 	}
 		
  

 	protected ChangeRequest extractPlatform(ChangeRequest changeRequest, Map<String,Object> options) throws Exception{

		if(changeRequest.getPlatform() == null){
			return changeRequest;
		}
		String platformId = changeRequest.getPlatform().getId();
		if( platformId == null){
			return changeRequest;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			changeRequest.setPlatform(platform);
		}
		
 		
 		return changeRequest;
 	}
 		
 
		
	protected void enhanceEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected ChangeRequest extractEventUpdateProfileList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<EventUpdateProfile> eventUpdateProfileList = getEventUpdateProfileDAO().findEventUpdateProfileByChangeRequest(changeRequest.getId(),options);
		if(eventUpdateProfileList != null){
			enhanceEventUpdateProfileList(eventUpdateProfileList,options);
			changeRequest.setEventUpdateProfileList(eventUpdateProfileList);
		}
		
		return changeRequest;
	
	}	
	
	protected ChangeRequest analyzeEventUpdateProfileList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		if(changeRequest == null){
			return null;
		}
		if(changeRequest.getId() == null){
			return changeRequest;
		}

		
		
		SmartList<EventUpdateProfile> eventUpdateProfileList = changeRequest.getEventUpdateProfileList();
		if(eventUpdateProfileList != null){
			getEventUpdateProfileDAO().analyzeEventUpdateProfileByChangeRequest(eventUpdateProfileList, changeRequest.getId(), options);
			
		}
		
		return changeRequest;
	
	}	
	
		
		
  	
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId,Map<String,Object> options){
 	
  		SmartList<ChangeRequest> resultList = queryWith(ChangeRequestTable.COLUMN_REQUEST_TYPE, changeRequestTypeId, options, getChangeRequestMapper());
		// analyzeChangeRequestByRequestType(resultList, changeRequestTypeId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequest> resultList =  queryWithRange(ChangeRequestTable.COLUMN_REQUEST_TYPE, changeRequestTypeId, options, getChangeRequestMapper(), start, count);
 		//analyzeChangeRequestByRequestType(resultList, changeRequestTypeId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestByRequestType(SmartList<ChangeRequest> resultList, String changeRequestTypeId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequest.REQUEST_TYPE_PROPERTY, changeRequestTypeId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//ChangeRequest.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("变更请求");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(ChangeRequest.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ChangeRequest.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestByRequestType(String changeRequestTypeId,Map<String,Object> options){

 		return countWith(ChangeRequestTable.COLUMN_REQUEST_TYPE, changeRequestTypeId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestByRequestTypeIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestTable.COLUMN_REQUEST_TYPE, ids, options);
	}
 	
  	
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<ChangeRequest> resultList = queryWith(ChangeRequestTable.COLUMN_PLATFORM, platformId, options, getChangeRequestMapper());
		// analyzeChangeRequestByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<ChangeRequest> resultList =  queryWithRange(ChangeRequestTable.COLUMN_PLATFORM, platformId, options, getChangeRequestMapper(), start, count);
 		//analyzeChangeRequestByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeChangeRequestByPlatform(SmartList<ChangeRequest> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(ChangeRequest.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem createTimeStatsItem = new StatsItem();
		//ChangeRequest.CREATE_TIME_PROPERTY
		createTimeStatsItem.setDisplayName("变更请求");
		createTimeStatsItem.setInternalName(formatKeyForDateLine(ChangeRequest.CREATE_TIME_PROPERTY));
		createTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(ChangeRequest.CREATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(createTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countChangeRequestByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ChangeRequestTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countChangeRequestByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ChangeRequestTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected ChangeRequest saveChangeRequest(ChangeRequest  changeRequest){
		
		if(!changeRequest.isChanged()){
			return changeRequest;
		}
		
		
		String SQL=this.getSaveChangeRequestSQL(changeRequest);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveChangeRequestParameters(changeRequest);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		changeRequest.incVersion();
		return changeRequest;
	
	}
	public SmartList<ChangeRequest> saveChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitChangeRequestList(changeRequestList);
		
		batchChangeRequestCreate((List<ChangeRequest>)lists[CREATE_LIST_INDEX]);
		
		batchChangeRequestUpdate((List<ChangeRequest>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(ChangeRequest changeRequest:changeRequestList){
			if(changeRequest.isChanged()){
				changeRequest.incVersion();
			}
			
		
		}
		
		
		return changeRequestList;
	}

	public SmartList<ChangeRequest> removeChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options){
		
		
		super.removeList(changeRequestList, options);
		
		return changeRequestList;
		
		
	}
	
	protected List<Object[]> prepareChangeRequestBatchCreateArgs(List<ChangeRequest> changeRequestList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequest changeRequest:changeRequestList ){
			Object [] parameters = prepareChangeRequestCreateParameters(changeRequest);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareChangeRequestBatchUpdateArgs(List<ChangeRequest> changeRequestList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(ChangeRequest changeRequest:changeRequestList ){
			if(!changeRequest.isChanged()){
				continue;
			}
			Object [] parameters = prepareChangeRequestUpdateParameters(changeRequest);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchChangeRequestCreate(List<ChangeRequest> changeRequestList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareChangeRequestBatchCreateArgs(changeRequestList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchChangeRequestUpdate(List<ChangeRequest> changeRequestList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareChangeRequestBatchUpdateArgs(changeRequestList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitChangeRequestList(List<ChangeRequest> changeRequestList){
		
		List<ChangeRequest> changeRequestCreateList=new ArrayList<ChangeRequest>();
		List<ChangeRequest> changeRequestUpdateList=new ArrayList<ChangeRequest>();
		
		for(ChangeRequest changeRequest: changeRequestList){
			if(isUpdateRequest(changeRequest)){
				changeRequestUpdateList.add( changeRequest);
				continue;
			}
			changeRequestCreateList.add(changeRequest);
		}
		
		return new Object[]{changeRequestCreateList,changeRequestUpdateList};
	}
	
	protected boolean isUpdateRequest(ChangeRequest changeRequest){
 		return changeRequest.getVersion() > 0;
 	}
 	protected String getSaveChangeRequestSQL(ChangeRequest changeRequest){
 		if(isUpdateRequest(changeRequest)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveChangeRequestParameters(ChangeRequest changeRequest){
 		if(isUpdateRequest(changeRequest) ){
 			return prepareChangeRequestUpdateParameters(changeRequest);
 		}
 		return prepareChangeRequestCreateParameters(changeRequest);
 	}
 	protected Object[] prepareChangeRequestUpdateParameters(ChangeRequest changeRequest){
 		Object[] parameters = new Object[9];
 
 		
 		parameters[0] = changeRequest.getName();
 		
 		
 		parameters[1] = changeRequest.getCreateTime();
 		
 		
 		parameters[2] = changeRequest.getRemoteIp();
 		 	
 		if(changeRequest.getRequestType() != null){
 			parameters[3] = changeRequest.getRequestType().getId();
 		}
 
 		
 		parameters[4] = changeRequest.getCommited();
 		 	
 		if(changeRequest.getPlatform() != null){
 			parameters[5] = changeRequest.getPlatform().getId();
 		}
 		
 		parameters[6] = changeRequest.nextVersion();
 		parameters[7] = changeRequest.getId();
 		parameters[8] = changeRequest.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareChangeRequestCreateParameters(ChangeRequest changeRequest){
		Object[] parameters = new Object[7];
		String newChangeRequestId=getNextId();
		changeRequest.setId(newChangeRequestId);
		parameters[0] =  changeRequest.getId();
 
 		
 		parameters[1] = changeRequest.getName();
 		
 		
 		parameters[2] = changeRequest.getCreateTime();
 		
 		
 		parameters[3] = changeRequest.getRemoteIp();
 		 	
 		if(changeRequest.getRequestType() != null){
 			parameters[4] = changeRequest.getRequestType().getId();
 		
 		}
 		
 		
 		parameters[5] = changeRequest.getCommited();
 		 	
 		if(changeRequest.getPlatform() != null){
 			parameters[6] = changeRequest.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected ChangeRequest saveInternalChangeRequest(ChangeRequest changeRequest, Map<String,Object> options){
		
		saveChangeRequest(changeRequest);
 	
 		if(isSaveRequestTypeEnabled(options)){
	 		saveRequestType(changeRequest, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(changeRequest, options);
 		}
 
		
		if(isSaveEventUpdateProfileListEnabled(options)){
	 		saveEventUpdateProfileList(changeRequest, options);
	 		//removeEventUpdateProfileList(changeRequest, options);
	 		//Not delete the record
	 		
 		}		
		
		return changeRequest;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected ChangeRequest saveRequestType(ChangeRequest changeRequest, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequest.getRequestType() == null){
 			return changeRequest;//do nothing when it is null
 		}
 		
 		getChangeRequestTypeDAO().save(changeRequest.getRequestType(),options);
 		return changeRequest;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected ChangeRequest savePlatform(ChangeRequest changeRequest, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(changeRequest.getPlatform() == null){
 			return changeRequest;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(changeRequest.getPlatform(),options);
 		return changeRequest;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public ChangeRequest planToRemoveEventUpdateProfileList(ChangeRequest changeRequest, String eventUpdateProfileIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(EventUpdateProfile.ID_PROPERTY, eventUpdateProfileIds);
		
		SmartList<EventUpdateProfile> externalEventUpdateProfileList = getEventUpdateProfileDAO().
				findEventUpdateProfileWithKey(key, options);
		if(externalEventUpdateProfileList == null){
			return changeRequest;
		}
		if(externalEventUpdateProfileList.isEmpty()){
			return changeRequest;
		}
		
		for(EventUpdateProfile eventUpdateProfileItem: externalEventUpdateProfileList){

			eventUpdateProfileItem.clearFromAll();
		}
		
		
		SmartList<EventUpdateProfile> eventUpdateProfileList = changeRequest.getEventUpdateProfileList();		
		eventUpdateProfileList.addAllToRemoveList(externalEventUpdateProfileList);
		return changeRequest;	
	
	}


	//disconnect ChangeRequest with event_initiator_id in EventUpdateProfile
	public ChangeRequest planToRemoveEventUpdateProfileListWithEventInitiatorId(ChangeRequest changeRequest, String eventInitiatorIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, changeRequest.getId());
		key.put(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY, eventInitiatorIdId);
		
		SmartList<EventUpdateProfile> externalEventUpdateProfileList = getEventUpdateProfileDAO().
				findEventUpdateProfileWithKey(key, options);
		if(externalEventUpdateProfileList == null){
			return changeRequest;
		}
		if(externalEventUpdateProfileList.isEmpty()){
			return changeRequest;
		}
		
		for(EventUpdateProfile eventUpdateProfileItem: externalEventUpdateProfileList){
			eventUpdateProfileItem.clearEventInitiatorId();
			eventUpdateProfileItem.clearChangeRequest();
			
		}
		
		
		SmartList<EventUpdateProfile> eventUpdateProfileList = changeRequest.getEventUpdateProfileList();		
		eventUpdateProfileList.addAllToRemoveList(externalEventUpdateProfileList);
		return changeRequest;
	}
	
	public int countEventUpdateProfileListWithEventInitiatorId(String changeRequestId, String eventInitiatorIdId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, changeRequestId);
		key.put(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY, eventInitiatorIdId);
		
		int count = getEventUpdateProfileDAO().countEventUpdateProfileWithKey(key, options);
		return count;
	}
	

		
	protected ChangeRequest saveEventUpdateProfileList(ChangeRequest changeRequest, Map<String,Object> options){
		
		
		
		
		SmartList<EventUpdateProfile> eventUpdateProfileList = changeRequest.getEventUpdateProfileList();
		if(eventUpdateProfileList == null){
			//null list means nothing
			return changeRequest;
		}
		SmartList<EventUpdateProfile> mergedUpdateEventUpdateProfileList = new SmartList<EventUpdateProfile>();
		
		
		mergedUpdateEventUpdateProfileList.addAll(eventUpdateProfileList); 
		if(eventUpdateProfileList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateEventUpdateProfileList.addAll(eventUpdateProfileList.getToRemoveList());
			eventUpdateProfileList.removeAll(eventUpdateProfileList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getEventUpdateProfileDAO().saveEventUpdateProfileList(mergedUpdateEventUpdateProfileList,options);
		
		if(eventUpdateProfileList.getToRemoveList() != null){
			eventUpdateProfileList.removeAll(eventUpdateProfileList.getToRemoveList());
		}
		
		
		return changeRequest;
	
	}
	
	protected ChangeRequest removeEventUpdateProfileList(ChangeRequest changeRequest, Map<String,Object> options){
	
	
		SmartList<EventUpdateProfile> eventUpdateProfileList = changeRequest.getEventUpdateProfileList();
		if(eventUpdateProfileList == null){
			return changeRequest;
		}	
	
		SmartList<EventUpdateProfile> toRemoveEventUpdateProfileList = eventUpdateProfileList.getToRemoveList();
		
		if(toRemoveEventUpdateProfileList == null){
			return changeRequest;
		}
		if(toRemoveEventUpdateProfileList.isEmpty()){
			return changeRequest;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getEventUpdateProfileDAO().removeEventUpdateProfileList(toRemoveEventUpdateProfileList,options);
		
		return changeRequest;
	
	}
	
	

 	
 	
	
	
	
		

	public ChangeRequest present(ChangeRequest changeRequest,Map<String, Object> options){
	
		presentEventUpdateProfileList(changeRequest,options);

		return changeRequest;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected ChangeRequest presentEventUpdateProfileList(
			ChangeRequest changeRequest,
			Map<String, Object> options) {

		SmartList<EventUpdateProfile> eventUpdateProfileList = changeRequest.getEventUpdateProfileList();		
				SmartList<EventUpdateProfile> newList= presentSubList(changeRequest.getId(),
				eventUpdateProfileList,
				options,
				getEventUpdateProfileDAO()::countEventUpdateProfileByChangeRequest,
				getEventUpdateProfileDAO()::findEventUpdateProfileByChangeRequest
				);

		
		changeRequest.setEventUpdateProfileList(newList);
		

		return changeRequest;
	}			
		

	
    public SmartList<ChangeRequest> requestCandidateChangeRequestForEventUpdateProfile(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ChangeRequestTable.COLUMN_NAME, ChangeRequestTable.COLUMN_REQUEST_TYPE, filterKey, pageNo, pageSize, getChangeRequestMapper());
    }
		

	protected String getTableName(){
		return ChangeRequestTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<ChangeRequest> changeRequestList) {		
		this.enhanceListInternal(changeRequestList, this.getChangeRequestMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:EventUpdateProfile的changeRequest的EventUpdateProfileList
	public SmartList<EventUpdateProfile> loadOurEventUpdateProfileList(SdsUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<EventUpdateProfile> loadedObjs = userContext.getDAOGroup().getEventUpdateProfileDAO().findEventUpdateProfileWithKey(key, options);
		Map<String, List<EventUpdateProfile>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getChangeRequest().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<EventUpdateProfile> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<EventUpdateProfile> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setEventUpdateProfileList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<ChangeRequest> changeRequestList = ownerEntity.collectRefsWithType(ChangeRequest.INTERNAL_TYPE);
		this.enhanceList(changeRequestList);
		
	}
	
	@Override
	public SmartList<ChangeRequest> findChangeRequestWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getChangeRequestMapper());

	}
	@Override
	public int countChangeRequestWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countChangeRequestWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<ChangeRequest> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getChangeRequestMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateChangeRequest executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateChangeRequest result = new CandidateChangeRequest();
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


