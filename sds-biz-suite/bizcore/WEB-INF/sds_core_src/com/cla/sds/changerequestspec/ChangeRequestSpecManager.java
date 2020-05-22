
package com.cla.sds.changerequestspec;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface ChangeRequestSpecManager extends BaseManager{

		

	public ChangeRequestSpec createChangeRequestSpec(SdsUserContext userContext, String scene,String brief,String ownerId,String projectId) throws Exception;
	public ChangeRequestSpec updateChangeRequestSpec(SdsUserContext userContext,String changeRequestSpecId, int changeRequestSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ChangeRequestSpec loadChangeRequestSpec(SdsUserContext userContext, String changeRequestSpecId, String [] tokensExpr) throws Exception;
	public ChangeRequestSpec internalSaveChangeRequestSpec(SdsUserContext userContext, ChangeRequestSpec changeRequestSpec) throws Exception;
	public ChangeRequestSpec internalSaveChangeRequestSpec(SdsUserContext userContext, ChangeRequestSpec changeRequestSpec,Map<String,Object>option) throws Exception;

	public ChangeRequestSpec transferToAnotherOwner(SdsUserContext userContext, String changeRequestSpecId, String anotherOwnerId)  throws Exception;
 	public ChangeRequestSpec transferToAnotherProject(SdsUserContext userContext, String changeRequestSpecId, String anotherProjectId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String changeRequestSpecId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, ChangeRequestSpec newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByOwner(SdsUserContext userContext,String ownerId) throws Exception;
	public Object listPageByOwner(SdsUserContext userContext,String ownerId, int start, int count) throws Exception;
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception;
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception;
  

}


