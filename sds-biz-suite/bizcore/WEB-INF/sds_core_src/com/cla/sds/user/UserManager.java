
package com.cla.sds.user;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface UserManager extends BaseManager{

		

	public User createUser(SdsUserContext userContext, String name,Date joinTime,String companyId,String title) throws Exception;
	public User updateUser(SdsUserContext userContext,String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public User loadUser(SdsUserContext userContext, String userId, String [] tokensExpr) throws Exception;
	public User internalSaveUser(SdsUserContext userContext, User user) throws Exception;
	public User internalSaveUser(SdsUserContext userContext, User user,Map<String,Object>option) throws Exception;

	public User transferToAnotherCompany(SdsUserContext userContext, String userId, String anotherCompanyId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String userId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, User newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  UserProjectManager getUserProjectManager(SdsUserContext userContext, String userId, String projectId ,String [] tokensExpr)  throws Exception;

	public  User addUserProject(SdsUserContext userContext, String userId, String projectId , String [] tokensExpr)  throws Exception;
	public  User removeUserProject(SdsUserContext userContext, String userId, String userProjectId, int userProjectVersion,String [] tokensExpr)  throws Exception;
	public  User updateUserProject(SdsUserContext userContext, String userId, String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PageFlowSpecManager getPageFlowSpecManager(SdsUserContext userContext, String userId, String scene, String brief, String projectId ,String [] tokensExpr)  throws Exception;

	public  User addPageFlowSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId , String [] tokensExpr)  throws Exception;
	public  User removePageFlowSpec(SdsUserContext userContext, String userId, String pageFlowSpecId, int pageFlowSpecVersion,String [] tokensExpr)  throws Exception;
	public  User updatePageFlowSpec(SdsUserContext userContext, String userId, String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WorkFlowSpecManager getWorkFlowSpecManager(SdsUserContext userContext, String userId, String scene, String brief, String projectId ,String [] tokensExpr)  throws Exception;

	public  User addWorkFlowSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId , String [] tokensExpr)  throws Exception;
	public  User removeWorkFlowSpec(SdsUserContext userContext, String userId, String workFlowSpecId, int workFlowSpecVersion,String [] tokensExpr)  throws Exception;
	public  User updateWorkFlowSpec(SdsUserContext userContext, String userId, String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ChangeRequestSpecManager getChangeRequestSpecManager(SdsUserContext userContext, String userId, String scene, String brief, String projectId ,String [] tokensExpr)  throws Exception;

	public  User addChangeRequestSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId , String [] tokensExpr)  throws Exception;
	public  User removeChangeRequestSpec(SdsUserContext userContext, String userId, String changeRequestSpecId, int changeRequestSpecVersion,String [] tokensExpr)  throws Exception;
	public  User updateChangeRequestSpec(SdsUserContext userContext, String userId, String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PageContentSpecManager getPageContentSpecManager(SdsUserContext userContext, String userId, String scene, String brief, String projectId ,String [] tokensExpr)  throws Exception;

	public  User addPageContentSpec(SdsUserContext userContext, String userId, String scene, String brief, String projectId , String [] tokensExpr)  throws Exception;
	public  User removePageContentSpec(SdsUserContext userContext, String userId, String pageContentSpecId, int pageContentSpecVersion,String [] tokensExpr)  throws Exception;
	public  User updatePageContentSpec(SdsUserContext userContext, String userId, String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByCompany(SdsUserContext userContext,String companyId) throws Exception;
	public Object listPageByCompany(SdsUserContext userContext,String companyId, int start, int count) throws Exception;
  

}


