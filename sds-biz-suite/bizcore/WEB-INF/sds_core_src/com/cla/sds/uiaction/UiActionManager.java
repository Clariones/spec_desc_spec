
package com.cla.sds.uiaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface UiActionManager extends BaseManager{

		

	public UiAction createUiAction(SdsUserContext userContext, String code,String icon,String title,int displayOrder,String brief,String imageUrl,String linkToUrl,String extraData,String pageId) throws Exception;
	public UiAction updateUiAction(SdsUserContext userContext,String uiActionId, int uiActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UiAction loadUiAction(SdsUserContext userContext, String uiActionId, String [] tokensExpr) throws Exception;
	public UiAction internalSaveUiAction(SdsUserContext userContext, UiAction uiAction) throws Exception;
	public UiAction internalSaveUiAction(SdsUserContext userContext, UiAction uiAction,Map<String,Object>option) throws Exception;

	public UiAction transferToAnotherPage(SdsUserContext userContext, String uiActionId, String anotherPageId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String uiActionId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, UiAction newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByPage(SdsUserContext userContext,String pageId) throws Exception;
	public Object listPageByPage(SdsUserContext userContext,String pageId, int start, int count) throws Exception;
  

}


