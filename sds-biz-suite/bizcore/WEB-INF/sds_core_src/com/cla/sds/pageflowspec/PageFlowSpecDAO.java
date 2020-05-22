
package com.cla.sds.pageflowspec;
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


public interface PageFlowSpecDAO extends BaseDAO{

	public SmartList<PageFlowSpec> loadAll();
	public PageFlowSpec load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<PageFlowSpec> pageFlowSpecList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public PageFlowSpec present(PageFlowSpec pageFlowSpec,Map<String,Object> options) throws Exception;
	public PageFlowSpec clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public PageFlowSpec save(PageFlowSpec pageFlowSpec,Map<String,Object> options);
	public SmartList<PageFlowSpec> savePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList,Map<String,Object> options);
	public SmartList<PageFlowSpec> removePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList,Map<String,Object> options);
	public SmartList<PageFlowSpec> findPageFlowSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPageFlowSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPageFlowSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String pageFlowSpecId, int version) throws Exception;
	public PageFlowSpec disconnectFromAll(String pageFlowSpecId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<PageFlowSpec> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidatePageFlowSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<PageFlowSpec> findPageFlowSpecByOwner(String userId, Map<String,Object> options);
 	public int countPageFlowSpecByOwner(String userId, Map<String,Object> options);
 	public Map<String, Integer> countPageFlowSpecByOwnerIds(String[] ids, Map<String,Object> options);
 	public SmartList<PageFlowSpec> findPageFlowSpecByOwner(String userId, int start, int count, Map<String,Object> options);
 	public void analyzePageFlowSpecByOwner(SmartList<PageFlowSpec> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<PageFlowSpec> findPageFlowSpecByProject(String projectId, Map<String,Object> options);
 	public int countPageFlowSpecByProject(String projectId, Map<String,Object> options);
 	public Map<String, Integer> countPageFlowSpecByProjectIds(String[] ids, Map<String,Object> options);
 	public SmartList<PageFlowSpec> findPageFlowSpecByProject(String projectId, int start, int count, Map<String,Object> options);
 	public void analyzePageFlowSpecByProject(SmartList<PageFlowSpec> resultList, String projectId, Map<String,Object> options);

 
 
}


