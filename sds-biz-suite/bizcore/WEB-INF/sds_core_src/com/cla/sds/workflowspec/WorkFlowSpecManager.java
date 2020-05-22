
package com.cla.sds.workflowspec;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface WorkFlowSpecManager extends BaseManager{

		

	public WorkFlowSpec createWorkFlowSpec(SdsUserContext userContext, String scene,String brief,String ownerId,String projectId) throws Exception;
	public WorkFlowSpec updateWorkFlowSpec(SdsUserContext userContext,String workFlowSpecId, int workFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public WorkFlowSpec loadWorkFlowSpec(SdsUserContext userContext, String workFlowSpecId, String [] tokensExpr) throws Exception;
	public WorkFlowSpec internalSaveWorkFlowSpec(SdsUserContext userContext, WorkFlowSpec workFlowSpec) throws Exception;
	public WorkFlowSpec internalSaveWorkFlowSpec(SdsUserContext userContext, WorkFlowSpec workFlowSpec,Map<String,Object>option) throws Exception;

	public WorkFlowSpec transferToAnotherOwner(SdsUserContext userContext, String workFlowSpecId, String anotherOwnerId)  throws Exception;
 	public WorkFlowSpec transferToAnotherProject(SdsUserContext userContext, String workFlowSpecId, String anotherProjectId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String workFlowSpecId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, WorkFlowSpec newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByOwner(SdsUserContext userContext,String ownerId) throws Exception;
	public Object listPageByOwner(SdsUserContext userContext,String ownerId, int start, int count) throws Exception;
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception;
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception;
  

}


