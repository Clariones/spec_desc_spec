
package com.cla.sds.wechatworkappidentity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.secuser.SecUser;

import com.cla.sds.secuser.SecUserDAO;


public interface WechatWorkappIdentityDAO extends BaseDAO{

	public SmartList<WechatWorkappIdentity> loadAll();
	public WechatWorkappIdentity load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<WechatWorkappIdentity> wechatWorkappIdentityList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public WechatWorkappIdentity present(WechatWorkappIdentity wechatWorkappIdentity,Map<String,Object> options) throws Exception;
	public WechatWorkappIdentity clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public WechatWorkappIdentity save(WechatWorkappIdentity wechatWorkappIdentity,Map<String,Object> options);
	public SmartList<WechatWorkappIdentity> saveWechatWorkappIdentityList(SmartList<WechatWorkappIdentity> wechatWorkappIdentityList,Map<String,Object> options);
	public SmartList<WechatWorkappIdentity> removeWechatWorkappIdentityList(SmartList<WechatWorkappIdentity> wechatWorkappIdentityList,Map<String,Object> options);
	public SmartList<WechatWorkappIdentity> findWechatWorkappIdentityWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countWechatWorkappIdentityWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countWechatWorkappIdentityWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String wechatWorkappIdentityId, int version) throws Exception;
	public WechatWorkappIdentity disconnectFromAll(String wechatWorkappIdentityId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<WechatWorkappIdentity> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateWechatWorkappIdentity executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<WechatWorkappIdentity> findWechatWorkappIdentityBySecUser(String secUserId, Map<String,Object> options);
 	public int countWechatWorkappIdentityBySecUser(String secUserId, Map<String,Object> options);
 	public Map<String, Integer> countWechatWorkappIdentityBySecUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<WechatWorkappIdentity> findWechatWorkappIdentityBySecUser(String secUserId, int start, int count, Map<String,Object> options);
 	public void analyzeWechatWorkappIdentityBySecUser(SmartList<WechatWorkappIdentity> resultList, String secUserId, Map<String,Object> options);

 
 
}


