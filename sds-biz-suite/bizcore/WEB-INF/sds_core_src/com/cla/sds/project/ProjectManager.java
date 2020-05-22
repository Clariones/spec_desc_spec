
package com.cla.sds.project;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface ProjectManager extends BaseManager{

		

	public Project createProject(SdsUserContext userContext, String name,String companyId) throws Exception;
	public Project updateProject(SdsUserContext userContext,String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Project loadProject(SdsUserContext userContext, String projectId, String [] tokensExpr) throws Exception;
	public Project internalSaveProject(SdsUserContext userContext, Project project) throws Exception;
	public Project internalSaveProject(SdsUserContext userContext, Project project,Map<String,Object>option) throws Exception;

	public Project transferToAnotherCompany(SdsUserContext userContext, String projectId, String anotherCompanyId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String projectId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, Project newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  UserProjectManager getUserProjectManager(SdsUserContext userContext, String projectId, String userId ,String [] tokensExpr)  throws Exception;

	public  Project addUserProject(SdsUserContext userContext, String projectId, String userId , String [] tokensExpr)  throws Exception;
	public  Project removeUserProject(SdsUserContext userContext, String projectId, String userProjectId, int userProjectVersion,String [] tokensExpr)  throws Exception;
	public  Project updateUserProject(SdsUserContext userContext, String projectId, String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PageFlowSpecManager getPageFlowSpecManager(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId ,String [] tokensExpr)  throws Exception;

	public  Project addPageFlowSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId , String [] tokensExpr)  throws Exception;
	public  Project removePageFlowSpec(SdsUserContext userContext, String projectId, String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr)  throws Exception;
	public  Project updatePageFlowSpec(SdsUserContext userContext, String projectId, String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WorkFlowSpecManager getWorkFlowSpecManager(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId ,String [] tokensExpr)  throws Exception;

	public  Project addWorkFlowSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId , String [] tokensExpr)  throws Exception;
	public  Project removeWorkFlowSpec(SdsUserContext userContext, String projectId, String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr)  throws Exception;
	public  Project updateWorkFlowSpec(SdsUserContext userContext, String projectId, String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ChangeRequestSpecManager getChangeRequestSpecManager(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId ,String [] tokensExpr)  throws Exception;

	public  Project addChangeRequestSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId , String [] tokensExpr)  throws Exception;
	public  Project removeChangeRequestSpec(SdsUserContext userContext, String projectId, String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr)  throws Exception;
	public  Project updateChangeRequestSpec(SdsUserContext userContext, String projectId, String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PageContentSpecManager getPageContentSpecManager(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId ,String [] tokensExpr)  throws Exception;

	public  Project addPageContentSpec(SdsUserContext userContext, String projectId, String scene, String brief, String ownerId , String [] tokensExpr)  throws Exception;
	public  Project removePageContentSpec(SdsUserContext userContext, String projectId, String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr)  throws Exception;
	public  Project updatePageContentSpec(SdsUserContext userContext, String projectId, String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByCompany(SdsUserContext userContext,String companyId) throws Exception;
	public Object listPageByCompany(SdsUserContext userContext,String companyId, int start, int count) throws Exception;
  

}


