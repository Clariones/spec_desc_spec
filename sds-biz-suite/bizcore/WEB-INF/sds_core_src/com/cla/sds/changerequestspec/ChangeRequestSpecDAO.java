
package com.cla.sds.changerequestspec;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.user.User;
import com.cla.sds.project.Project;

import com.cla.sds.user.UserDAO;
import com.cla.sds.project.ProjectDAO;


public interface ChangeRequestSpecDAO extends BaseDAO{

	public SmartList<ChangeRequestSpec> loadAll();
	public ChangeRequestSpec load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<ChangeRequestSpec> changeRequestSpecList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public ChangeRequestSpec present(ChangeRequestSpec changeRequestSpec,Map<String,Object> options) throws Exception;
	public ChangeRequestSpec clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public ChangeRequestSpec save(ChangeRequestSpec changeRequestSpec,Map<String,Object> options);
	public SmartList<ChangeRequestSpec> saveChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList,Map<String,Object> options);
	public SmartList<ChangeRequestSpec> removeChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList,Map<String,Object> options);
	public SmartList<ChangeRequestSpec> findChangeRequestSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countChangeRequestSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countChangeRequestSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String changeRequestSpecId, int version) throws Exception;
	public ChangeRequestSpec disconnectFromAll(String changeRequestSpecId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<ChangeRequestSpec> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateChangeRequestSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByOwner(String userId, Map<String,Object> options);
 	public int countChangeRequestSpecByOwner(String userId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestSpecByOwnerIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByOwner(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestSpecByOwner(SmartList<ChangeRequestSpec> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByProject(String projectId, Map<String,Object> options);
 	public int countChangeRequestSpecByProject(String projectId, Map<String,Object> options);
 	public Map<String, Integer> countChangeRequestSpecByProjectIds(String[] ids, Map<String,Object> options);
 	public SmartList<ChangeRequestSpec> findChangeRequestSpecByProject(String projectId, int start, int count, Map<String,Object> options);
 	public void analyzeChangeRequestSpecByProject(SmartList<ChangeRequestSpec> resultList, String projectId, Map<String,Object> options);

 
 
}


