
package com.cla.sds.user;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.terapico.caf.baseelement.CandidateQuery;
import com.cla.sds.BaseDAO;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.MultipleAccessKey;
import com.cla.sds.SdsUserContext;

import com.cla.sds.userproject.UserProject;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.company.Company;
import com.cla.sds.changerequestspec.ChangeRequestSpec;

import com.cla.sds.pageflowspec.PageFlowSpecDAO;
import com.cla.sds.workflowspec.WorkFlowSpecDAO;
import com.cla.sds.pagecontentspec.PageContentSpecDAO;
import com.cla.sds.changerequestspec.ChangeRequestSpecDAO;
import com.cla.sds.company.CompanyDAO;
import com.cla.sds.userproject.UserProjectDAO;


public interface UserDAO extends BaseDAO{

	public SmartList<User> loadAll();
	public User load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<User> userList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public User present(User user,Map<String,Object> options) throws Exception;
	public User clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public User save(User user,Map<String,Object> options);
	public SmartList<User> saveUserList(SmartList<User> userList,Map<String,Object> options);
	public SmartList<User> removeUserList(SmartList<User> userList,Map<String,Object> options);
	public SmartList<User> findUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countUserWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countUserWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String userId, int version) throws Exception;
	public User disconnectFromAll(String userId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public UserProjectDAO getUserProjectDAO();
		
	public PageFlowSpecDAO getPageFlowSpecDAO();
		
	public WorkFlowSpecDAO getWorkFlowSpecDAO();
		
	public ChangeRequestSpecDAO getChangeRequestSpecDAO();
		
	public PageContentSpecDAO getPageContentSpecDAO();
		
	
 	public SmartList<User> requestCandidateUserForUserProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForPageFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForWorkFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForChangeRequestSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<User> requestCandidateUserForPageContentSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public User planToRemoveUserProjectList(User user, String userProjectIds[], Map<String,Object> options)throws Exception;


	//disconnect User with project in UserProject
	public User planToRemoveUserProjectListWithProject(User user, String projectId, Map<String,Object> options)throws Exception;
	public int countUserProjectListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception;
	
	public User planToRemovePageFlowSpecList(User user, String pageFlowSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect User with project in PageFlowSpec
	public User planToRemovePageFlowSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception;
	public int countPageFlowSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception;
	
	public User planToRemoveWorkFlowSpecList(User user, String workFlowSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect User with project in WorkFlowSpec
	public User planToRemoveWorkFlowSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception;
	public int countWorkFlowSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception;
	
	public User planToRemoveChangeRequestSpecList(User user, String changeRequestSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect User with project in ChangeRequestSpec
	public User planToRemoveChangeRequestSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception;
	public int countChangeRequestSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception;
	
	public User planToRemovePageContentSpecList(User user, String pageContentSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect User with project in PageContentSpec
	public User planToRemovePageContentSpecListWithProject(User user, String projectId, Map<String,Object> options)throws Exception;
	public int countPageContentSpecListWithProject(String userId, String projectId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<User> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateUser executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<User> findUserByCompany(String companyId, Map<String,Object> options);
 	public int countUserByCompany(String companyId, Map<String,Object> options);
 	public Map<String, Integer> countUserByCompanyIds(String[] ids, Map<String,Object> options);
 	public SmartList<User> findUserByCompany(String companyId, int start, int count, Map<String,Object> options);
 	public void analyzeUserByCompany(SmartList<User> resultList, String companyId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:UserProject的user的UserProjectList
	public SmartList<UserProject> loadOurUserProjectList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:PageFlowSpec的owner的PageFlowSpecList
	public SmartList<PageFlowSpec> loadOurPageFlowSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WorkFlowSpec的owner的WorkFlowSpecList
	public SmartList<WorkFlowSpec> loadOurWorkFlowSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestSpec的owner的ChangeRequestSpecList
	public SmartList<ChangeRequestSpec> loadOurChangeRequestSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:PageContentSpec的owner的PageContentSpecList
	public SmartList<PageContentSpec> loadOurPageContentSpecList(SdsUserContext userContext, List<User> us, Map<String,Object> options) throws Exception;
	
}


