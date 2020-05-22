
package com.cla.sds.publickeytype;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.userdomain.UserDomain;
import com.cla.sds.keypairidentity.KeypairIdentity;

import com.cla.sds.keypairidentity.KeypairIdentityDAO;
import com.cla.sds.userdomain.UserDomainDAO;


public interface PublicKeyTypeDAO extends BaseDAO{

	public SmartList<PublicKeyType> loadAll();
	public PublicKeyType load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<PublicKeyType> publicKeyTypeList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public PublicKeyType present(PublicKeyType publicKeyType,Map<String,Object> options) throws Exception;
	public PublicKeyType clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public PublicKeyType save(PublicKeyType publicKeyType,Map<String,Object> options);
	public SmartList<PublicKeyType> savePublicKeyTypeList(SmartList<PublicKeyType> publicKeyTypeList,Map<String,Object> options);
	public SmartList<PublicKeyType> removePublicKeyTypeList(SmartList<PublicKeyType> publicKeyTypeList,Map<String,Object> options);
	public SmartList<PublicKeyType> findPublicKeyTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPublicKeyTypeWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPublicKeyTypeWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String publicKeyTypeId, int version) throws Exception;
	public PublicKeyType disconnectFromAll(String publicKeyTypeId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public KeypairIdentityDAO getKeypairIdentityDAO();
		
	
 	public SmartList<PublicKeyType> requestCandidatePublicKeyTypeForKeypairIdentity(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public PublicKeyType planToRemoveKeypairIdentityList(PublicKeyType publicKeyType, String keypairIdentityIds[], Map<String,Object> options)throws Exception;


	//disconnect PublicKeyType with sec_user in KeypairIdentity
	public PublicKeyType planToRemoveKeypairIdentityListWithSecUser(PublicKeyType publicKeyType, String secUserId, Map<String,Object> options)throws Exception;
	public int countKeypairIdentityListWithSecUser(String publicKeyTypeId, String secUserId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<PublicKeyType> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidatePublicKeyType executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<PublicKeyType> findPublicKeyTypeByDomain(String userDomainId, Map<String,Object> options);
 	public int countPublicKeyTypeByDomain(String userDomainId, Map<String,Object> options);
 	public Map<String, Integer> countPublicKeyTypeByDomainIds(String[] ids, Map<String,Object> options);
 	public SmartList<PublicKeyType> findPublicKeyTypeByDomain(String userDomainId, int start, int count, Map<String,Object> options);
 	public void analyzePublicKeyTypeByDomain(SmartList<PublicKeyType> resultList, String userDomainId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:KeypairIdentity的keyType的KeypairIdentityList
	public SmartList<KeypairIdentity> loadOurKeypairIdentityList(SdsUserContext userContext, List<PublicKeyType> us, Map<String,Object> options) throws Exception;
	
}


