
package com.cla.sds.eventupdateprofile;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.changerequest.ChangeRequest;

import com.cla.sds.changerequest.ChangeRequestDAO;


public interface EventUpdateProfileDAO extends BaseDAO{

	public SmartList<EventUpdateProfile> loadAll();
	public EventUpdateProfile load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<EventUpdateProfile> eventUpdateProfileList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public EventUpdateProfile present(EventUpdateProfile eventUpdateProfile,Map<String,Object> options) throws Exception;
	public EventUpdateProfile clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public EventUpdateProfile save(EventUpdateProfile eventUpdateProfile,Map<String,Object> options);
	public SmartList<EventUpdateProfile> saveEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList,Map<String,Object> options);
	public SmartList<EventUpdateProfile> removeEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList,Map<String,Object> options);
	public SmartList<EventUpdateProfile> findEventUpdateProfileWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countEventUpdateProfileWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countEventUpdateProfileWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String eventUpdateProfileId, int version) throws Exception;
	public EventUpdateProfile disconnectFromAll(String eventUpdateProfileId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<EventUpdateProfile> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateEventUpdateProfile executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<EventUpdateProfile> findEventUpdateProfileByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public int countEventUpdateProfileByChangeRequest(String changeRequestId, Map<String,Object> options);
 	public Map<String, Integer> countEventUpdateProfileByChangeRequestIds(String[] ids, Map<String,Object> options);
 	public SmartList<EventUpdateProfile> findEventUpdateProfileByChangeRequest(String changeRequestId, int start, int count, Map<String,Object> options);
 	public void analyzeEventUpdateProfileByChangeRequest(SmartList<EventUpdateProfile> resultList, String changeRequestId, Map<String,Object> options);

 
 
}


