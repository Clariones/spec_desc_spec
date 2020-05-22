
package com.cla.sds.workflowspec;
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


public interface WorkFlowSpecDAO extends BaseDAO{

	public SmartList<WorkFlowSpec> loadAll();
	public WorkFlowSpec load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<WorkFlowSpec> workFlowSpecList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public WorkFlowSpec present(WorkFlowSpec workFlowSpec,Map<String,Object> options) throws Exception;
	public WorkFlowSpec clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public WorkFlowSpec save(WorkFlowSpec workFlowSpec,Map<String,Object> options);
	public SmartList<WorkFlowSpec> saveWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList,Map<String,Object> options);
	public SmartList<WorkFlowSpec> removeWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList,Map<String,Object> options);
	public SmartList<WorkFlowSpec> findWorkFlowSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countWorkFlowSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countWorkFlowSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String workFlowSpecId, int version) throws Exception;
	public WorkFlowSpec disconnectFromAll(String workFlowSpecId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<WorkFlowSpec> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateWorkFlowSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByOwner(String userId, Map<String,Object> options);
 	public int countWorkFlowSpecByOwner(String userId, Map<String,Object> options);
 	public Map<String, Integer> countWorkFlowSpecByOwnerIds(String[] ids, Map<String,Object> options);
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByOwner(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeWorkFlowSpecByOwner(SmartList<WorkFlowSpec> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByProject(String projectId, Map<String,Object> options);
 	public int countWorkFlowSpecByProject(String projectId, Map<String,Object> options);
 	public Map<String, Integer> countWorkFlowSpecByProjectIds(String[] ids, Map<String,Object> options);
 	public SmartList<WorkFlowSpec> findWorkFlowSpecByProject(String projectId, int start, int count, Map<String,Object> options);
 	public void analyzeWorkFlowSpecByProject(SmartList<WorkFlowSpec> resultList, String projectId, Map<String,Object> options);

 
 
}


