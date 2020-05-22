
package com.cla.sds.pagecontentspec;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface PageContentSpecManager extends BaseManager{

		

	public PageContentSpec createPageContentSpec(SdsUserContext userContext, String scene,String brief,String ownerId,String projectId) throws Exception;
	public PageContentSpec updatePageContentSpec(SdsUserContext userContext,String pageContentSpecId, int pageContentSpecVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PageContentSpec loadPageContentSpec(SdsUserContext userContext, String pageContentSpecId, String [] tokensExpr) throws Exception;
	public PageContentSpec internalSavePageContentSpec(SdsUserContext userContext, PageContentSpec pageContentSpec) throws Exception;
	public PageContentSpec internalSavePageContentSpec(SdsUserContext userContext, PageContentSpec pageContentSpec,Map<String,Object>option) throws Exception;

	public PageContentSpec transferToAnotherOwner(SdsUserContext userContext, String pageContentSpecId, String anotherOwnerId)  throws Exception;
 	public PageContentSpec transferToAnotherProject(SdsUserContext userContext, String pageContentSpecId, String anotherProjectId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String pageContentSpecId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, PageContentSpec newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByOwner(SdsUserContext userContext,String ownerId) throws Exception;
	public Object listPageByOwner(SdsUserContext userContext,String ownerId, int start, int count) throws Exception;
  
	public Object listByProject(SdsUserContext userContext,String projectId) throws Exception;
	public Object listPageByProject(SdsUserContext userContext,String projectId, int start, int count) throws Exception;
  

}


