
package com.cla.sds.userproject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface UserProjectManager extends BaseManager{

		

	public UserProject createUserProject(SdsUserContext userContext, String userId,String projectId) throws Exception;
	public UserProject updateUserProject(SdsUserContext userContext,String userProjectId, int userProjectVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UserProject loadUserProject(SdsUserContext userContext, String userProjectId, String [] tokensExpr) throws Exception;
	public UserProject internalSaveUserProject(SdsUserContext userContext, UserProject userProject) throws Exception;
	public UserProject internalSaveUserProject(SdsUserContext userContext, UserProject userProject,Map<String,Object>option) throws Exception;

	public UserProject transferToAnotherUser(SdsUserContext userContext, String userProjectId, String anotherUserId)  throws Exception;
 	public UserProject transferToAnotherProject(SdsUserContext userContext, String userProjectId, String anotherProjectId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String userProjectId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, UserProject newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByUser(SdsUserContext userContext,String userId) throws Exception;
	public Object listPageByUser(SdsUserContext userContext,String userId, int start, int count) throws Exception;
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception;
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception;
  

}


