
package com.cla.sds.page;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface PageManager extends BaseManager{

		

	public Page createPage(SdsUserContext userContext, String pageTitle,String linkToUrl,String pageTypeId,int displayOrder,String mobileAppId) throws Exception;
	public Page updatePage(SdsUserContext userContext,String pageId, int pageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Page loadPage(SdsUserContext userContext, String pageId, String [] tokensExpr) throws Exception;
	public Page internalSavePage(SdsUserContext userContext, Page page) throws Exception;
	public Page internalSavePage(SdsUserContext userContext, Page page,Map<String,Object>option) throws Exception;

	public Page transferToAnotherPageType(SdsUserContext userContext, String pageId, String anotherPageTypeId)  throws Exception;
 	public Page transferToAnotherMobileApp(SdsUserContext userContext, String pageId, String anotherMobileAppId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String pageId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, Page newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  SlideManager getSlideManager(SdsUserContext userContext, String pageId, String name, int displayOrder, String imageUrl, String videoUrl, String linkToUrl ,String [] tokensExpr)  throws Exception;

	public  Page addSlide(SdsUserContext userContext, String pageId, String name, int displayOrder, String imageUrl, String videoUrl, String linkToUrl , String [] tokensExpr)  throws Exception;
	public  Page removeSlide(SdsUserContext userContext, String pageId, String slideId, int slideVersion,String [] tokensExpr)  throws Exception;
	public  Page updateSlide(SdsUserContext userContext, String pageId, String slideId, int slideVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  UiActionManager getUiActionManager(SdsUserContext userContext, String pageId, String code, String icon, String title, int displayOrder, String brief, String imageUrl, String linkToUrl, String extraData ,String [] tokensExpr)  throws Exception;

	public  Page addUiAction(SdsUserContext userContext, String pageId, String code, String icon, String title, int displayOrder, String brief, String imageUrl, String linkToUrl, String extraData , String [] tokensExpr)  throws Exception;
	public  Page removeUiAction(SdsUserContext userContext, String pageId, String uiActionId, int uiActionVersion,String [] tokensExpr)  throws Exception;
	public  Page updateUiAction(SdsUserContext userContext, String pageId, String uiActionId, int uiActionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  SectionManager getSectionManager(SdsUserContext userContext, String pageId, String title, String brief, String icon, int displayOrder, String viewGroup, String linkToUrl ,String [] tokensExpr)  throws Exception;

	public  Page addSection(SdsUserContext userContext, String pageId, String title, String brief, String icon, int displayOrder, String viewGroup, String linkToUrl , String [] tokensExpr)  throws Exception;
	public  Page removeSection(SdsUserContext userContext, String pageId, String sectionId, int sectionVersion,String [] tokensExpr)  throws Exception;
	public  Page updateSection(SdsUserContext userContext, String pageId, String sectionId, int sectionVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByPageType(SdsUserContext userContext,String pageTypeId) throws Exception;
	public Object listPageByPageType(SdsUserContext userContext,String pageTypeId, int start, int count) throws Exception;
  
	public Object listByMobileApp(SdsUserContext userContext,String mobileAppId) throws Exception;
	public Object listPageByMobileApp(SdsUserContext userContext,String mobileAppId, int start, int count) throws Exception;
  

}


