
package com.cla.sds.eventupdateprofile;

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

import com.cla.sds.changerequest.ChangeRequestDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class EventUpdateProfileJDBCTemplateDAO extends SdsBaseDAOImpl implements EventUpdateProfileDAO{

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
	protected EventUpdateProfile load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalEventUpdateProfile(accessKey, options);
	}
	*/
	
	public SmartList<EventUpdateProfile> loadAll() {
	    return this.loadAll(getEventUpdateProfileMapper());
	}
	
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public EventUpdateProfile load(String id,Map<String,Object> options) throws Exception{
		return loadInternalEventUpdateProfile(EventUpdateProfileTable.withId(id), options);
	}
	
	
	
	public EventUpdateProfile save(EventUpdateProfile eventUpdateProfile,Map<String,Object> options){
		
		String methodName="save(EventUpdateProfile eventUpdateProfile,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(eventUpdateProfile, methodName, "eventUpdateProfile");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalEventUpdateProfile(eventUpdateProfile,options);
	}
	public EventUpdateProfile clone(String eventUpdateProfileId, Map<String,Object> options) throws Exception{
	
		return clone(EventUpdateProfileTable.withId(eventUpdateProfileId),options);
	}
	
	protected EventUpdateProfile clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String eventUpdateProfileId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		EventUpdateProfile newEventUpdateProfile = loadInternalEventUpdateProfile(accessKey, options);
		newEventUpdateProfile.setVersion(0);
		
		

		
		saveInternalEventUpdateProfile(newEventUpdateProfile,options);
		
		return newEventUpdateProfile;
	}
	
	
	
	

	protected void throwIfHasException(String eventUpdateProfileId,int version,int count) throws Exception{
		if (count == 1) {
			throw new EventUpdateProfileVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new EventUpdateProfileNotFoundException(
					"The " + this.getTableName() + "(" + eventUpdateProfileId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String eventUpdateProfileId, int version) throws Exception{
	
		String methodName="delete(String eventUpdateProfileId, int version)";
		assertMethodArgumentNotNull(eventUpdateProfileId, methodName, "eventUpdateProfileId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{eventUpdateProfileId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(eventUpdateProfileId,version);
		}
		
	
	}
	
	
	
	
	

	public EventUpdateProfile disconnectFromAll(String eventUpdateProfileId, int version) throws Exception{
	
		
		EventUpdateProfile eventUpdateProfile = loadInternalEventUpdateProfile(EventUpdateProfileTable.withId(eventUpdateProfileId), emptyOptions());
		eventUpdateProfile.clearFromAll();
		this.saveEventUpdateProfile(eventUpdateProfile);
		return eventUpdateProfile;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return EventUpdateProfileTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "event_update_profile";
	}
	@Override
	protected String getBeanName() {
		
		return "eventUpdateProfile";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return EventUpdateProfileTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractChangeRequestEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, EventUpdateProfileTokens.CHANGEREQUEST);
 	}

 	protected boolean isSaveChangeRequestEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, EventUpdateProfileTokens.CHANGEREQUEST);
 	}
 	

 	
 
		

	

	protected EventUpdateProfileMapper getEventUpdateProfileMapper(){
		return new EventUpdateProfileMapper();
	}

	
	
	protected EventUpdateProfile extractEventUpdateProfile(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			EventUpdateProfile eventUpdateProfile = loadSingleObject(accessKey, getEventUpdateProfileMapper());
			return eventUpdateProfile;
		}catch(EmptyResultDataAccessException e){
			throw new EventUpdateProfileNotFoundException("EventUpdateProfile("+accessKey+") is not found!");
		}

	}

	
	

	protected EventUpdateProfile loadInternalEventUpdateProfile(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		EventUpdateProfile eventUpdateProfile = extractEventUpdateProfile(accessKey, loadOptions);
 	
 		if(isExtractChangeRequestEnabled(loadOptions)){
	 		extractChangeRequest(eventUpdateProfile, loadOptions);
 		}
 
		
		return eventUpdateProfile;
		
	}

	 

 	protected EventUpdateProfile extractChangeRequest(EventUpdateProfile eventUpdateProfile, Map<String,Object> options) throws Exception{

		if(eventUpdateProfile.getChangeRequest() == null){
			return eventUpdateProfile;
		}
		String changeRequestId = eventUpdateProfile.getChangeRequest().getId();
		if( changeRequestId == null){
			return eventUpdateProfile;
		}
		ChangeRequest changeRequest = getChangeRequestDAO().load(changeRequestId,options);
		if(changeRequest != null){
			eventUpdateProfile.setChangeRequest(changeRequest);
		}
		
 		
 		return eventUpdateProfile;
 	}
 		
 
		
		
  	
 	public SmartList<EventUpdateProfile> findEventUpdateProfileByChangeRequest(String changeRequestId,Map<String,Object> options){
 	
  		SmartList<EventUpdateProfile> resultList = queryWith(EventUpdateProfileTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getEventUpdateProfileMapper());
		// analyzeEventUpdateProfileByChangeRequest(resultList, changeRequestId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<EventUpdateProfile> findEventUpdateProfileByChangeRequest(String changeRequestId, int start, int count,Map<String,Object> options){
 		
 		SmartList<EventUpdateProfile> resultList =  queryWithRange(EventUpdateProfileTable.COLUMN_CHANGE_REQUEST, changeRequestId, options, getEventUpdateProfileMapper(), start, count);
 		//analyzeEventUpdateProfileByChangeRequest(resultList, changeRequestId, options);
 		return resultList;
 		
 	}
 	public void analyzeEventUpdateProfileByChangeRequest(SmartList<EventUpdateProfile> resultList, String changeRequestId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countEventUpdateProfileByChangeRequest(String changeRequestId,Map<String,Object> options){

 		return countWith(EventUpdateProfileTable.COLUMN_CHANGE_REQUEST, changeRequestId, options);
 	}
 	@Override
	public Map<String, Integer> countEventUpdateProfileByChangeRequestIds(String[] ids, Map<String, Object> options) {
		return countWithIds(EventUpdateProfileTable.COLUMN_CHANGE_REQUEST, ids, options);
	}
 	
 	
		
		
		

	

	protected EventUpdateProfile saveEventUpdateProfile(EventUpdateProfile  eventUpdateProfile){
		
		if(!eventUpdateProfile.isChanged()){
			return eventUpdateProfile;
		}
		
		
		String SQL=this.getSaveEventUpdateProfileSQL(eventUpdateProfile);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveEventUpdateProfileParameters(eventUpdateProfile);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		eventUpdateProfile.incVersion();
		return eventUpdateProfile;
	
	}
	public SmartList<EventUpdateProfile> saveEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitEventUpdateProfileList(eventUpdateProfileList);
		
		batchEventUpdateProfileCreate((List<EventUpdateProfile>)lists[CREATE_LIST_INDEX]);
		
		batchEventUpdateProfileUpdate((List<EventUpdateProfile>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(EventUpdateProfile eventUpdateProfile:eventUpdateProfileList){
			if(eventUpdateProfile.isChanged()){
				eventUpdateProfile.incVersion();
			}
			
		
		}
		
		
		return eventUpdateProfileList;
	}

	public SmartList<EventUpdateProfile> removeEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList,Map<String,Object> options){
		
		
		super.removeList(eventUpdateProfileList, options);
		
		return eventUpdateProfileList;
		
		
	}
	
	protected List<Object[]> prepareEventUpdateProfileBatchCreateArgs(List<EventUpdateProfile> eventUpdateProfileList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(EventUpdateProfile eventUpdateProfile:eventUpdateProfileList ){
			Object [] parameters = prepareEventUpdateProfileCreateParameters(eventUpdateProfile);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareEventUpdateProfileBatchUpdateArgs(List<EventUpdateProfile> eventUpdateProfileList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(EventUpdateProfile eventUpdateProfile:eventUpdateProfileList ){
			if(!eventUpdateProfile.isChanged()){
				continue;
			}
			Object [] parameters = prepareEventUpdateProfileUpdateParameters(eventUpdateProfile);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchEventUpdateProfileCreate(List<EventUpdateProfile> eventUpdateProfileList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareEventUpdateProfileBatchCreateArgs(eventUpdateProfileList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchEventUpdateProfileUpdate(List<EventUpdateProfile> eventUpdateProfileList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareEventUpdateProfileBatchUpdateArgs(eventUpdateProfileList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitEventUpdateProfileList(List<EventUpdateProfile> eventUpdateProfileList){
		
		List<EventUpdateProfile> eventUpdateProfileCreateList=new ArrayList<EventUpdateProfile>();
		List<EventUpdateProfile> eventUpdateProfileUpdateList=new ArrayList<EventUpdateProfile>();
		
		for(EventUpdateProfile eventUpdateProfile: eventUpdateProfileList){
			if(isUpdateRequest(eventUpdateProfile)){
				eventUpdateProfileUpdateList.add( eventUpdateProfile);
				continue;
			}
			eventUpdateProfileCreateList.add(eventUpdateProfile);
		}
		
		return new Object[]{eventUpdateProfileCreateList,eventUpdateProfileUpdateList};
	}
	
	protected boolean isUpdateRequest(EventUpdateProfile eventUpdateProfile){
 		return eventUpdateProfile.getVersion() > 0;
 	}
 	protected String getSaveEventUpdateProfileSQL(EventUpdateProfile eventUpdateProfile){
 		if(isUpdateRequest(eventUpdateProfile)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveEventUpdateProfileParameters(EventUpdateProfile eventUpdateProfile){
 		if(isUpdateRequest(eventUpdateProfile) ){
 			return prepareEventUpdateProfileUpdateParameters(eventUpdateProfile);
 		}
 		return prepareEventUpdateProfileCreateParameters(eventUpdateProfile);
 	}
 	protected Object[] prepareEventUpdateProfileUpdateParameters(EventUpdateProfile eventUpdateProfile){
 		Object[] parameters = new Object[9];
 
 		
 		parameters[0] = eventUpdateProfile.getName();
 		
 		
 		parameters[1] = eventUpdateProfile.getAvantar();
 		
 		
 		parameters[2] = eventUpdateProfile.getFieldGroup();
 		
 		
 		parameters[3] = eventUpdateProfile.getEventInitiatorType();
 		
 		
 		parameters[4] = eventUpdateProfile.getEventInitiatorId();
 		 	
 		if(eventUpdateProfile.getChangeRequest() != null){
 			parameters[5] = eventUpdateProfile.getChangeRequest().getId();
 		}
 		
 		parameters[6] = eventUpdateProfile.nextVersion();
 		parameters[7] = eventUpdateProfile.getId();
 		parameters[8] = eventUpdateProfile.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareEventUpdateProfileCreateParameters(EventUpdateProfile eventUpdateProfile){
		Object[] parameters = new Object[7];
		String newEventUpdateProfileId=getNextId();
		eventUpdateProfile.setId(newEventUpdateProfileId);
		parameters[0] =  eventUpdateProfile.getId();
 
 		
 		parameters[1] = eventUpdateProfile.getName();
 		
 		
 		parameters[2] = eventUpdateProfile.getAvantar();
 		
 		
 		parameters[3] = eventUpdateProfile.getFieldGroup();
 		
 		
 		parameters[4] = eventUpdateProfile.getEventInitiatorType();
 		
 		
 		parameters[5] = eventUpdateProfile.getEventInitiatorId();
 		 	
 		if(eventUpdateProfile.getChangeRequest() != null){
 			parameters[6] = eventUpdateProfile.getChangeRequest().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected EventUpdateProfile saveInternalEventUpdateProfile(EventUpdateProfile eventUpdateProfile, Map<String,Object> options){
		
		saveEventUpdateProfile(eventUpdateProfile);
 	
 		if(isSaveChangeRequestEnabled(options)){
	 		saveChangeRequest(eventUpdateProfile, options);
 		}
 
		
		return eventUpdateProfile;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected EventUpdateProfile saveChangeRequest(EventUpdateProfile eventUpdateProfile, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(eventUpdateProfile.getChangeRequest() == null){
 			return eventUpdateProfile;//do nothing when it is null
 		}
 		
 		getChangeRequestDAO().save(eventUpdateProfile.getChangeRequest(),options);
 		return eventUpdateProfile;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public EventUpdateProfile present(EventUpdateProfile eventUpdateProfile,Map<String, Object> options){
	

		return eventUpdateProfile;
	
	}
		

	

	protected String getTableName(){
		return EventUpdateProfileTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<EventUpdateProfile> eventUpdateProfileList) {		
		this.enhanceListInternal(eventUpdateProfileList, this.getEventUpdateProfileMapper());
	}
	
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<EventUpdateProfile> eventUpdateProfileList = ownerEntity.collectRefsWithType(EventUpdateProfile.INTERNAL_TYPE);
		this.enhanceList(eventUpdateProfileList);
		
	}
	
	@Override
	public SmartList<EventUpdateProfile> findEventUpdateProfileWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getEventUpdateProfileMapper());

	}
	@Override
	public int countEventUpdateProfileWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countEventUpdateProfileWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<EventUpdateProfile> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getEventUpdateProfileMapper());
	}
	@Override
	public int count(String sql, Object... parameters) {
	    return queryInt(sql, parameters);
	}
	@Override
	public CandidateEventUpdateProfile executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception {

		CandidateEventUpdateProfile result = new CandidateEventUpdateProfile();
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


