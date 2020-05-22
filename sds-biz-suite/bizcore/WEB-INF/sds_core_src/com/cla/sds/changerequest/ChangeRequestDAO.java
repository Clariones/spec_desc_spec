
package com.cla.sds.changerequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.platform.Platform;
import com.cla.sds.changerequesttype.ChangeRequestType;

import com.cla.sds.eventupdateprofile.EventUpdateProfileDAO;
import com.cla.sds.changerequesttype.ChangeRequestTypeDAO;
import com.cla.sds.platform.PlatformDAO;


public interface ChangeRequestDAO extends BaseDAO{

	public SmartList<ChangeRequest> loadAll();
	public ChangeRequest load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ChangeRequest> changeRequestList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ChangeRequest present(ChangeRequest changeRequest,Map<String,Object> options) throws Exception;
	public ChangeRequest clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ChangeRequest save(ChangeRequest changeRequest,Map<String,Object> options);
	public SmartList<ChangeRequest> saveChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options);
	public SmartList<ChangeRequest> removeChangeRequestList(SmartList<ChangeRequest> changeRequestList,Map<String,Object> options);
	public SmartList<ChangeRequest> findChangeRequestWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countChangeRequestWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countChangeRequestWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String changeRequestId, int version) throws Exception;
	public ChangeRequest disconnectFromAll(String changeRequestId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public EventUpdateProfileDAO getEventUpdateProfileDAO();
		
	
 	public SmartList<ChangeRequest> requestCandidateChangeRequestForEventUpdateProfile(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public ChangeRequest planToRemoveEventUpdateProfileList(ChangeRequest changeRequest, String eventUpdateProfileIds[], Map<String,Object> options)throws Exception;


	//disconnect ChangeRequest with event_initiator_id in EventUpdateProfile
	public ChangeRequest planToRemoveEventUpdateProfileListWithEventInitiatorId(ChangeRequest changeRequest, String eventInitiatorIdId, Map<String,Object> options)throws Exception;
	public int countEventUpdateProfileListWithEventInitiatorId(String changeRequestId, String eventInitiatorIdId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<ChangeRequest> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateChangeRequest executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId, Map<String,Object> options);
 	public int countChangeRequestByRequestType(String changeRequestTypeId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestByRequestTypeIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequest> findChangeRequestByRequestType(String changeRequestTypeId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestByRequestType(SmartList<ChangeRequest> resultList, String changeRequestTypeId, Map<String,Object> options);

 
  
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, Map<String,Object> options);
 	public int countChangeRequestByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequest> findChangeRequestByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestByPlatform(SmartList<ChangeRequest> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:EventUpdateProfile的changeRequest的EventUpdateProfileList
	public SmartList<EventUpdateProfile> loadOurEventUpdateProfileList(SdsUserContext userContext, List<ChangeRequest> us, Map<String,Object> options) throws Exception;
	
}


