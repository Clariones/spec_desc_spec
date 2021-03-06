
package com.cla.sds.wechatminiappidentity;
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


public interface WechatMiniappIdentityDAO extends BaseDAO{

	public SmartList<WechatMiniappIdentity> loadAll();
	public WechatMiniappIdentity load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<WechatMiniappIdentity> wechatMiniappIdentityList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public WechatMiniappIdentity present(WechatMiniappIdentity wechatMiniappIdentity,Map<String,Object> options) throws Exception;
	public WechatMiniappIdentity clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public WechatMiniappIdentity save(WechatMiniappIdentity wechatMiniappIdentity,Map<String,Object> options);
	public SmartList<WechatMiniappIdentity> saveWechatMiniappIdentityList(SmartList<WechatMiniappIdentity> wechatMiniappIdentityList,Map<String,Object> options);
	public SmartList<WechatMiniappIdentity> removeWechatMiniappIdentityList(SmartList<WechatMiniappIdentity> wechatMiniappIdentityList,Map<String,Object> options);
	public SmartList<WechatMiniappIdentity> findWechatMiniappIdentityWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countWechatMiniappIdentityWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countWechatMiniappIdentityWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String wechatMiniappIdentityId, int version) throws Exception;
	public WechatMiniappIdentity disconnectFromAll(String wechatMiniappIdentityId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<WechatMiniappIdentity> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateWechatMiniappIdentity executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<WechatMiniappIdentity> findWechatMiniappIdentityBySecUser(String secUserId, Map<String,Object> options);
 	public int countWechatMiniappIdentityBySecUser(String secUserId, Map<String,Object> options);
 	public Map<String, Integer> countWechatMiniappIdentityBySecUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<WechatMiniappIdentity> findWechatMiniappIdentityBySecUser(String secUserId, int start, int count, Map<String,Object> options);
 	public void analyzeWechatMiniappIdentityBySecUser(SmartList<WechatMiniappIdentity> resultList, String secUserId, Map<String,Object> options);

 
 
}


