
package com.cla.sds.project;
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


public interface ProjectDAO extends BaseDAO{

	public SmartList<Project> loadAll();
	public Project load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Project> projectList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Project present(Project project,Map<String,Object> options) throws Exception;
	public Project clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Project save(Project project,Map<String,Object> options);
	public SmartList<Project> saveProjectList(SmartList<Project> projectList,Map<String,Object> options);
	public SmartList<Project> removeProjectList(SmartList<Project> projectList,Map<String,Object> options);
	public SmartList<Project> findProjectWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countProjectWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countProjectWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String projectId, int version) throws Exception;
	public Project disconnectFromAll(String projectId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public UserProjectDAO getUserProjectDAO();
		
	public PageFlowSpecDAO getPageFlowSpecDAO();
		
	public WorkFlowSpecDAO getWorkFlowSpecDAO();
		
	public ChangeRequestSpecDAO getChangeRequestSpecDAO();
		
	public PageContentSpecDAO getPageContentSpecDAO();
		
	
 	public SmartList<Project> requestCandidateProjectForUserProject(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Project> requestCandidateProjectForPageFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Project> requestCandidateProjectForWorkFlowSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Project> requestCandidateProjectForChangeRequestSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Project> requestCandidateProjectForPageContentSpec(SdsUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Project planToRemoveUserProjectList(Project project, String userProjectIds[], Map<String,Object> options)throws Exception;


	//disconnect Project with user in UserProject
	public Project planToRemoveUserProjectListWithUser(Project project, String userId, Map<String,Object> options)throws Exception;
	public int countUserProjectListWithUser(String projectId, String userId, Map<String,Object> options)throws Exception;
	
	public Project planToRemovePageFlowSpecList(Project project, String pageFlowSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect Project with owner in PageFlowSpec
	public Project planToRemovePageFlowSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception;
	public int countPageFlowSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception;
	
	public Project planToRemoveWorkFlowSpecList(Project project, String workFlowSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect Project with owner in WorkFlowSpec
	public Project planToRemoveWorkFlowSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception;
	public int countWorkFlowSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception;
	
	public Project planToRemoveChangeRequestSpecList(Project project, String changeRequestSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect Project with owner in ChangeRequestSpec
	public Project planToRemoveChangeRequestSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception;
	public int countChangeRequestSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception;
	
	public Project planToRemovePageContentSpecList(Project project, String pageContentSpecIds[], Map<String,Object> options)throws Exception;


	//disconnect Project with owner in PageContentSpec
	public Project planToRemovePageContentSpecListWithOwner(Project project, String ownerId, Map<String,Object> options)throws Exception;
	public int countPageContentSpecListWithOwner(String projectId, String ownerId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Project> queryList(String sql, Object ... parmeters);
	public int count(String sql, Object ... parmeters);
	public CandidateProject executeCandidatesQuery(CandidateQuery query, String sql, Object ... parmeters) throws Exception ;
 
 	public SmartList<Project> findProjectByCompany(String companyId, Map<String,Object> options);
 	public int countProjectByCompany(String companyId, Map<String,Object> options);
 	public Map<String, Integer> countProjectByCompanyIds(String[] ids, Map<String,Object> options);
 	public SmartList<Project> findProjectByCompany(String companyId, int start, int count, Map<String,Object> options);
 	public void analyzeProjectByCompany(SmartList<Project> resultList, String companyId, Map<String,Object> options);

 
 
	// 需要一个加载引用我的对象的enhance方法:UserProject的project的UserProjectList
	public SmartList<UserProject> loadOurUserProjectList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:PageFlowSpec的project的PageFlowSpecList
	public SmartList<PageFlowSpec> loadOurPageFlowSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:WorkFlowSpec的project的WorkFlowSpecList
	public SmartList<WorkFlowSpec> loadOurWorkFlowSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:ChangeRequestSpec的project的ChangeRequestSpecList
	public SmartList<ChangeRequestSpec> loadOurChangeRequestSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:PageContentSpec的project的PageContentSpecList
	public SmartList<PageContentSpec> loadOurPageContentSpecList(SdsUserContext userContext, List<Project> us, Map<String,Object> options) throws Exception;
	
}


