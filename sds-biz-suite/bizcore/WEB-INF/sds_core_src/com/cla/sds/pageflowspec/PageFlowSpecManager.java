
package com.cla.sds.pageflowspec;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface PageFlowSpecManager extends BaseManager{

		

	public PageFlowSpec createPageFlowSpec(SdsUserContext userContext, String scene,String brief,String ownerId,String projectId) throws Exception;
	public PageFlowSpec updatePageFlowSpec(SdsUserContext userContext,String pageFlowSpecId, int pageFlowSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PageFlowSpec loadPageFlowSpec(SdsUserContext userContext, String pageFlowSpecId, String [] tokensExpr) throws Exception;
	public PageFlowSpec internalSavePageFlowSpec(SdsUserContext userContext, PageFlowSpec pageFlowSpec) throws Exception;
	public PageFlowSpec internalSavePageFlowSpec(SdsUserContext userContext, PageFlowSpec pageFlowSpec,Map<String,Object>option) throws Exception;

	public PageFlowSpec transferToAnotherOwner(SdsUserContext userContext, String pageFlowSpecId, String anotherOwnerId)  throws Exception;
 	public PageFlowSpec transferToAnotherProject(SdsUserContext userContext, String pageFlowSpecId, String anotherProjectId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String pageFlowSpecId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, PageFlowSpec newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByOwner(SdsUserContext userContext,String ownerId) throws Exception;
	public Object listPageByOwner(SdsUserContext userContext,String ownerId, int start, int count) throws Exception;
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception;
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception;
  

}


