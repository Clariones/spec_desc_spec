
package com.cla.sds.quicklink;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface QuickLinkManager extends BaseManager{

		

	public QuickLink createQuickLink(SdsUserContext userContext, String name,String icon,String imagePath,String linkTarget,String appId) throws Exception;
	public QuickLink updateQuickLink(SdsUserContext userContext,String quickLinkId, int quickLinkVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public QuickLink loadQuickLink(SdsUserContext userContext, String quickLinkId, String [] tokensExpr) throws Exception;
	public QuickLink internalSaveQuickLink(SdsUserContext userContext, QuickLink quickLink) throws Exception;
	public QuickLink internalSaveQuickLink(SdsUserContext userContext, QuickLink quickLink,Map<String,Object>option) throws Exception;

	public QuickLink transferToAnotherApp(SdsUserContext userContext, String quickLinkId, String anotherAppId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String quickLinkId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, QuickLink newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByApp(SdsUserContext userContext,String appId) throws Exception;
	public Object listPageByApp(SdsUserContext userContext,String appId, int start, int count) throws Exception;
  

}


