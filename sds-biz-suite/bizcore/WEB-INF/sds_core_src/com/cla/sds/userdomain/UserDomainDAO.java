
package com.cla.sds.userdomain;
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
import com.cla.sds.userwhitelist.UserWhiteList;
import com.cla.sds.publickeytype.PublicKeyType;

import com.cla.sds.secuser.SecUserDAO;
import com.cla.sds.userwhitelist.UserWhiteListDAO;
import com.cla.sds.publickeytype.PublicKeyTypeDAO;


public interface UserDomainDAO extends BaseDAO{

	public SmartList<UserDomain> loadAll();
	public UserDomain load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<UserDomain> userDomainList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public UserDomain present(UserDomain userDomain,Map<String,Object> options) throws Exception;
	public UserDomain clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public UserDomain save(UserDomain userDomain,Map<String,Object> options);
	public SmartList<UserDomain> saveUserDomainList(SmartList<UserDomain> userDomainList,Map<String,Object> options);
	public SmartList<UserDomain> removeUserDomainList(SmartList<UserDomain> userDomainList,Map<String,Object> options);
	public SmartList<UserDomain> findUserDomainWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUserDomainWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUserDomainWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String userDomainId, int version) throws Exception;
	public UserDomain disconnectFromAll(String userDomainId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public UserWhiteListDAO getUserWhiteListDAO();
		
	public SecUserDAO getSecUserDAO();
		
	public PublicKeyTypeDAO getPublicKeyTypeDAO();
		
	
 	public SmartList<UserDomain> requestCandidateUserDomainForUserWhiteList(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<UserDomain> requestCandidateUserDomainForSecUser(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<UserDomain> requestCandidateUserDomainForPublicKeyType(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public UserDomain planToRemoveUserWhiteListList(UserDomain userDomain, String userWhiteListIds[], Map<String,Object> options)throws Exception;


	public UserDomain planToRemoveSecUserList(UserDomain userDomain, String secUserIds[], Map<String,Object> options)throws Exception;


	public UserDomain planToRemovePublicKeyTypeList(UserDomain userDomain, String publicKeyTypeIds[], Map<String,Object> options)throws Exception;


	
	public SmartList<UserDomain> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateUserDomain executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;

	// 需要一个加载引用我的对象的enhance方法:UserWhiteList的domain的UserWhiteListList
	public SmartList<UserWhiteList> loadOurUserWhiteListList(SdsUserContext userContext, List<UserDomain> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:SecUser的domain的SecUserList
	public SmartList<SecUser> loadOurSecUserList(SdsUserContext userContext, List<UserDomain> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:PublicKeyType的domain的PublicKeyTypeList
	public SmartList<PublicKeyType> loadOurPublicKeyTypeList(SdsUserContext userContext, List<UserDomain> us, Map<String,Object> options) throws Exception;
	
}


