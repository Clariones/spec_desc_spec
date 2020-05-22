
package com.cla.sds.loginhistory;
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


public interface LoginHistoryDAO extends BaseDAO{

	public SmartList<LoginHistory> loadAll();
	public LoginHistory load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<LoginHistory> loginHistoryList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public LoginHistory present(LoginHistory loginHistory,Map<String,Object> options) throws Exception;
	public LoginHistory clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public LoginHistory save(LoginHistory loginHistory,Map<String,Object> options);
	public SmartList<LoginHistory> saveLoginHistoryList(SmartList<LoginHistory> loginHistoryList,Map<String,Object> options);
	public SmartList<LoginHistory> removeLoginHistoryList(SmartList<LoginHistory> loginHistoryList,Map<String,Object> options);
	public SmartList<LoginHistory> findLoginHistoryWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countLoginHistoryWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countLoginHistoryWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String loginHistoryId, int version) throws Exception;
	public LoginHistory disconnectFromAll(String loginHistoryId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<LoginHistory> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateLoginHistory executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<LoginHistory> findLoginHistoryBySecUser(String secUserId, Map<String,Object> options);
 	public int countLoginHistoryBySecUser(String secUserId, Map<String,Object> options);
 	public Map<String, Integer> countLoginHistoryBySecUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<LoginHistory> findLoginHistoryBySecUser(String secUserId, int start, int count, Map<String,Object> options);
 	public void analyzeLoginHistoryBySecUser(SmartList<LoginHistory> resultList, String secUserId, Map<String,Object> options);

 
 
}


