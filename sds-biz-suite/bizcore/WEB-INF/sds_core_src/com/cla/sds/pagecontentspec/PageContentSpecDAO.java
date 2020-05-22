
package com.cla.sds.pagecontentspec;
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


public interface PageContentSpecDAO extends BaseDAO{

	public SmartList<PageContentSpec> loadAll();
	public PageContentSpec load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<PageContentSpec> pageContentSpecList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public PageContentSpec present(PageContentSpec pageContentSpec,Map<String,Object> options) throws Exception;
	public PageContentSpec clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public PageContentSpec save(PageContentSpec pageContentSpec,Map<String,Object> options);
	public SmartList<PageContentSpec> savePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList,Map<String,Object> options);
	public SmartList<PageContentSpec> removePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList,Map<String,Object> options);
	public SmartList<PageContentSpec> findPageContentSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPageContentSpecWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPageContentSpecWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String pageContentSpecId, int version) throws Exception;
	public PageContentSpec disconnectFromAll(String pageContentSpecId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<PageContentSpec> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidatePageContentSpec executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<PageContentSpec> findPageContentSpecByOwner(String userId, Map<String,Object> options);
 	public int countPageContentSpecByOwner(String userId, Map<String,Object> options);
 	public Map<String, Integer> countPageContentSpecByOwnerIds(String[] ids, Map<String,Object> options);
 	public SmartList<PageContentSpec> findPageContentSpecByOwner(String userId, int start, int count, Map<String,Object> options);
 	public void analyzePageContentSpecByOwner(SmartList<PageContentSpec> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<PageContentSpec> findPageContentSpecByProject(String projectId, Map<String,Object> options);
 	public int countPageContentSpecByProject(String projectId, Map<String,Object> options);
 	public Map<String, Integer> countPageContentSpecByProjectIds(String[] ids, Map<String,Object> options);
 	public SmartList<PageContentSpec> findPageContentSpecByProject(String projectId, int start, int count, Map<String,Object> options);
 	public void analyzePageContentSpecByProject(SmartList<PageContentSpec> resultList, String projectId, Map<String,Object> options);

 
 
}


