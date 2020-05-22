
package com.cla.sds.pagetype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface PageTypeManager extends BaseManager{

		


	public PageType loadPageTypeWithCode(SdsUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public PageType createPageType(SdsUserContext userContext, String name,String code,String mobileAppId,boolean footerTab) throws Exception;
	public PageType updatePageType(SdsUserContext userContext,String pageTypeId, int pageTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PageType loadPageType(SdsUserContext userContext, String pageTypeId, String [] tokensExpr) throws Exception;
	public PageType internalSavePageType(SdsUserContext userContext, PageType pageType) throws Exception;
	public PageType internalSavePageType(SdsUserContext userContext, PageType pageType,Map<String,Object>option) throws Exception;

	public PageType transferToAnotherMobileApp(SdsUserContext userContext, String pageTypeId, String anotherMobileAppId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String pageTypeId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, PageType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  PageManager getPageManager(SdsUserContext userContext, String pageTypeId, String pageTitle, String linkToUrl, int displayOrder, String mobileAppId ,String [] tokensExpr)  throws Exception;

	public  PageType addPage(SdsUserContext userContext, String pageTypeId, String pageTitle, String linkToUrl, int displayOrder, String mobileAppId , String [] tokensExpr)  throws Exception;
	public  PageType removePage(SdsUserContext userContext, String pageTypeId, String pageId, int pageVersion,String [] tokensExpr)  throws Exception;
	public  PageType updatePage(SdsUserContext userContext, String pageTypeId, String pageId, int pageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByMobileApp(SdsUserContext userContext,String mobileAppId) throws Exception;
	public Object listPageByMobileApp(SdsUserContext userContext,String mobileAppId, int start, int count) throws Exception;
  

}


