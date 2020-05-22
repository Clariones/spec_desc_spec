
package com.cla.sds.secuser;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.loginhistory.LoginHistory;
import com.cla.sds.userdomain.UserDomain;
import com.cla.sds.wechatworkappidentity.WechatWorkappIdentity;
import com.cla.sds.keypairidentity.KeypairIdentity;
import com.cla.sds.wechatminiappidentity.WechatMiniappIdentity;
import com.cla.sds.userapp.UserApp;

import com.cla.sds.keypairidentity.KeypairIdentityDAO;
import com.cla.sds.userapp.UserAppDAO;
import com.cla.sds.userdomain.UserDomainDAO;
import com.cla.sds.wechatminiappidentity.WechatMiniappIdentityDAO;
import com.cla.sds.wechatworkappidentity.WechatWorkappIdentityDAO;
import com.cla.sds.loginhistory.LoginHistoryDAO;


public interface SecUserDAO extends BaseDAO{

	public SmartList<SecUser> loadAll();
	public SecUser load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<SecUser> secUserList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public SecUser loadByLogin(String login,Map<String,Object> options) throws Exception;
	
	
	public SecUser loadByEmail(String email,Map<String,Object> options) throws Exception;
	
	
	public SecUser loadByMobile(String mobile,Map<String,Object> options) throws Exception;
	
	
	public SecUser present(SecUser secUser,Map<String,Object> options) throws Exception;
	public SecUser clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public SecUser cloneByLogin(String login,Map<String,Object> options) throws Exception;
	
	
	public SecUser cloneByEmail(String email,Map<String,Object> options) throws Exception;
	
	
	public SecUser cloneByMobile(String mobile,Map<String,Object> options) throws Exception;
	
	
	public SecUser save(SecUser secUser,Map<String,Object> options);
	public SmartList<SecUser> saveSecUserList(SmartList<SecUser> secUserList,Map<String,Object> options);
	public SmartList<SecUser> removeSecUserList(SmartList<SecUser> secUserList,Map<String,Object> options);
	public SmartList<SecUser> findSecUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countSecUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countSecUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String secUserId, int version) throws Exception;
	public SecUser disconnectFromAll(String secUserId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public UserAppDAO getUserAppDAO();
		
	public LoginHistoryDAO getLoginHistoryDAO();
		
	public WechatWorkappIdentityDAO getWechatWorkappIdentityDAO();
		
	public WechatMiniappIdentityDAO getWechatMiniappIdentityDAO();
		
	public KeypairIdentityDAO getKeypairIdentityDAO();
		
	
 	public SmartList<SecUser> requestCandidateSecUserForUserApp(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<SecUser> requestCandidateSecUserForLoginHistory(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<SecUser> requestCandidateSecUserForWechatWorkappIdentity(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<SecUser> requestCandidateSecUserForWechatMiniappIdentity(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<SecUser> requestCandidateSecUserForKeypairIdentity(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public SecUser planToRemoveUserAppList(SecUser secUser, String userAppIds[], Map<String,Object> options)throws Exception;


	//disconnect SecUser with object_id in UserApp
	public SecUser planToRemoveUserAppListWithObjectId(SecUser secUser, String objectIdId, Map<String,Object> options)throws Exception;
	public int countUserAppListWithObjectId(String secUserId, String objectIdId, Map<String,Object> options)throws Exception;
	
	public SecUser planToRemoveLoginHistoryList(SecUser secUser, String loginHistoryIds[], Map<String,Object> options)throws Exception;


	public SecUser planToRemoveWechatWorkappIdentityList(SecUser secUser, String wechatWorkappIdentityIds[], Map<String,Object> options)throws Exception;


	//disconnect SecUser with corp_id in WechatWorkappIdentity
	public SecUser planToRemoveWechatWorkappIdentityListWithCorpId(SecUser secUser, String corpIdId, Map<String,Object> options)throws Exception;
	public int countWechatWorkappIdentityListWithCorpId(String secUserId, String corpIdId, Map<String,Object> options)throws Exception;
	
	//disconnect SecUser with user_id in WechatWorkappIdentity
	public SecUser planToRemoveWechatWorkappIdentityListWithUserId(SecUser secUser, String userIdId, Map<String,Object> options)throws Exception;
	public int countWechatWorkappIdentityListWithUserId(String secUserId, String userIdId, Map<String,Object> options)throws Exception;
	
	public SecUser planToRemoveWechatMiniappIdentityList(SecUser secUser, String wechatMiniappIdentityIds[], Map<String,Object> options)throws Exception;


	//disconnect SecUser with open_id in WechatMiniappIdentity
	public SecUser planToRemoveWechatMiniappIdentityListWithOpenId(SecUser secUser, String openIdId, Map<String,Object> options)throws Exception;
	public int countWechatMiniappIdentityListWithOpenId(String secUserId, String openIdId, Map<String,Object> options)throws Exception;
	
	//disconnect SecUser with app_id in WechatMiniappIdentity
	public SecUser planToRemoveWechatMiniappIdentityListWithAppId(SecUser secUser, String appIdId, Map<String,Object> options)throws Exception;
	public int countWechatMiniappIdentityListWithAppId(String secUserId, String appIdId, Map<String,Object> options)throws Exception;
	
	public SecUser planToRemoveKeypairIdentityList(SecUser secUser, String keypairIdentityIds[], Map<String,Object> options)throws Exception;


	//disconnect SecUser with key_type in KeypairIdentity
	public SecUser planToRemoveKeypairIdentityListWithKeyType(SecUser secUser, String keyTypeId, Map<String,Object> options)throws Exception;
	public int countKeypairIdentityListWithKeyType(String secUserId, String keyTypeId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<SecUser> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateSecUser executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<SecUser> findSecUserByDomain(String userDomainId, Map<String,Object> options);
 	public int countSecUserByDomain(String userDomainId, Map<String,Object> options);
 	public Map<String, Integer> countSecUserByDomainIds(String[] ids, Map<String,Object> options);
 	public SmartList<SecUser> findSecUserByDomain(String userDomainId, int start, int count, Map<String,Object> options);
 	public void analyzeSecUserByDomain(SmartList<SecUser> resultList, String userDomainId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:UserApp的secUser的UserAppList
	public SmartList<UserApp> loadOurUserAppList(SdsUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:LoginHistory的secUser的LoginHistoryList
	public SmartList<LoginHistory> loadOurLoginHistoryList(SdsUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WechatWorkappIdentity的secUser的WechatWorkappIdentityList
	public SmartList<WechatWorkappIdentity> loadOurWechatWorkappIdentityList(SdsUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WechatMiniappIdentity的secUser的WechatMiniappIdentityList
	public SmartList<WechatMiniappIdentity> loadOurWechatMiniappIdentityList(SdsUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:KeypairIdentity的secUser的KeypairIdentityList
	public SmartList<KeypairIdentity> loadOurKeypairIdentityList(SdsUserContext userContext, List<SecUser> us, Map<String,Object> options) throws Exception;
	
}


