
package com.cla.sds.company;
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
import com.cla.sds.platform.Platform;

import com.cla.sds.user.UserDAO;
import com.cla.sds.platform.PlatformDAO;
import com.cla.sds.project.ProjectDAO;


public interface CompanyDAO extends BaseDAO{

	public SmartList<Company> loadAll();
	public Company load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Company> companyList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Company present(Company company,Map<String,Object> options) throws Exception;
	public Company clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Company save(Company company,Map<String,Object> options);
	public SmartList<Company> saveCompanyList(SmartList<Company> companyList,Map<String,Object> options);
	public SmartList<Company> removeCompanyList(SmartList<Company> companyList,Map<String,Object> options);
	public SmartList<Company> findCompanyWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countCompanyWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countCompanyWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String companyId, int version) throws Exception;
	public Company disconnectFromAll(String companyId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public UserDAO getUserDAO();
		
	public ProjectDAO getProjectDAO();
		
	
 	public SmartList<Company> requestCandidateCompanyForUser(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Company> requestCandidateCompanyForProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Company planToRemoveUserList(Company company, String userIds[], Map<String,Object> options)throws Exception;


	public Company planToRemoveProjectList(Company company, String projectIds[], Map<String,Object> options)throws Exception;


	
	public SmartList<Company> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateCompany executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<Company> findCompanyByPlatform(String platformId, Map<String,Object> options);
 	public int countCompanyByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countCompanyByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Company> findCompanyByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeCompanyByPlatform(SmartList<Company> resultList, String platformId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:User的company的UserList
	public SmartList<User> loadOurUserList(SdsUserContext userContext, List<Company> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:Project的company的ProjectList
	public SmartList<Project> loadOurProjectList(SdsUserContext userContext, List<Company> us, Map<String,Object> options) throws Exception;
	
}


