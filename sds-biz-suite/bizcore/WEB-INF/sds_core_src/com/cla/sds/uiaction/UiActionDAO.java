
package com.cla.sds.uiaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.page.Page;

import com.cla.sds.page.PageDAO;


public interface UiActionDAO extends BaseDAO{

	public SmartList<UiAction> loadAll();
	public UiAction load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<UiAction> uiActionList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public UiAction present(UiAction uiAction,Map<String,Object> options) throws Exception;
	public UiAction clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public UiAction save(UiAction uiAction,Map<String,Object> options);
	public SmartList<UiAction> saveUiActionList(SmartList<UiAction> uiActionList,Map<String,Object> options);
	public SmartList<UiAction> removeUiActionList(SmartList<UiAction> uiActionList,Map<String,Object> options);
	public SmartList<UiAction> findUiActionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUiActionWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUiActionWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String uiActionId, int version) throws Exception;
	public UiAction disconnectFromAll(String uiActionId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<UiAction> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateUiAction executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<UiAction> findUiActionByPage(String pageId, Map<String,Object> options);
 	public int countUiActionByPage(String pageId, Map<String,Object> options);
 	public Map<String, Integer> countUiActionByPageIds(String[] ids, Map<String,Object> options);
 	public SmartList<UiAction> findUiActionByPage(String pageId, int start, int count, Map<String,Object> options);
 	public void analyzeUiActionByPage(SmartList<UiAction> resultList, String pageId, Map<String,Object> options);

 
 
}


