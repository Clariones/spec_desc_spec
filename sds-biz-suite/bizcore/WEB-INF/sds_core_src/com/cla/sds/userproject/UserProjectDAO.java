
package com.cla.sds.userproject;
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


public interface UserProjectDAO extends BaseDAO{

	public SmartList<UserProject> loadAll();
	public UserProject load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<UserProject> userProjectList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public UserProject present(UserProject userProject,Map<String,Object> options) throws Exception;
	public UserProject clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public UserProject save(UserProject userProject,Map<String,Object> options);
	public SmartList<UserProject> saveUserProjectList(SmartList<UserProject> userProjectList,Map<String,Object> options);
	public SmartList<UserProject> removeUserProjectList(SmartList<UserProject> userProjectList,Map<String,Object> options);
	public SmartList<UserProject> findUserProjectWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUserProjectWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUserProjectWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String userProjectId, int version) throws Exception;
	public UserProject disconnectFromAll(String userProjectId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<UserProject> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateUserProject executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<UserProject> findUserProjectByUser(String userId, Map<String,Object> options);
 	public int countUserProjectByUser(String userId, Map<String,Object> options);
 	public Map<String, Integer> countUserProjectByUserIds(String[] ids, Map<String,Object> options);
 	public SmartList<UserProject> findUserProjectByUser(String userId, int start, int count, Map<String,Object> options);
 	public void analyzeUserProjectByUser(SmartList<UserProject> resultList, String userId, Map<String,Object> options);

 
  
 	public SmartList<UserProject> findUserProjectByProject(String projectId, Map<String,Object> options);
 	public int countUserProjectByProject(String projectId, Map<String,Object> options);
 	public Map<String, Integer> countUserProjectByProjectIds(String[] ids, Map<String,Object> options);
 	public SmartList<UserProject> findUserProjectByProject(String projectId, int start, int count, Map<String,Object> options);
 	public void analyzeUserProjectByProject(SmartList<UserProject> resultList, String projectId, Map<String,Object> options);

 
 
}


