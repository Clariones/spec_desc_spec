
package com.cla.sds.mobileapp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface MobileAppManager extends BaseManager{

		

	public MobileApp createMobileApp(SdsUserContext userContext, String name) throws Exception;
	public MobileApp updateMobileApp(SdsUserContext userContext,String mobileAppId, int mobileAppVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public MobileApp loadMobileApp(SdsUserContext userContext, String mobileAppId, String [] tokensExpr) throws Exception;
	public MobileApp internalSaveMobileApp(SdsUserContext userContext, MobileApp mobileApp) throws Exception;
	public MobileApp internalSaveMobileApp(SdsUserContext userContext, MobileApp mobileApp,Map<String,Object>option) throws Exception;



	public void delete(SdsUserContext userContext, String mobileAppId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, MobileApp newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  PageManager getPageManager(SdsUserContext userContext, String mobileAppId, String pageTitle, String linkToUrl, String pageTypeId, int displayOrder ,String [] tokensExpr)  throws Exception;

	public  MobileApp addPage(SdsUserContext userContext, String mobileAppId, String pageTitle, String linkToUrl, String pageTypeId, int displayOrder , String [] tokensExpr)  throws Exception;
	public  MobileApp removePage(SdsUserContext userContext, String mobileAppId, String pageId, int pageVersion,String [] tokensExpr)  throws Exception;
	public  MobileApp updatePage(SdsUserContext userContext, String mobileAppId, String pageId, int pageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PageTypeManager getPageTypeManager(SdsUserContext userContext, String mobileAppId, String name, String code, boolean footerTab ,String [] tokensExpr)  throws Exception;

	public  MobileApp addPageType(SdsUserContext userContext, String mobileAppId, String name, String code, boolean footerTab , String [] tokensExpr)  throws Exception;
	public  MobileApp removePageType(SdsUserContext userContext, String mobileAppId, String pageTypeId, int pageTypeVersion,String [] tokensExpr)  throws Exception;
	public  MobileApp updatePageType(SdsUserContext userContext, String mobileAppId, String pageTypeId, int pageTypeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


