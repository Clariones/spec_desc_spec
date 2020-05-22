
package com.cla.sds.loginhistory;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface LoginHistoryManager extends BaseManager{

		

	public LoginHistory createLoginHistory(SdsUserContext userContext, String fromIp,String description,String secUserId) throws Exception;
	public LoginHistory updateLoginHistory(SdsUserContext userContext,String loginHistoryId, int loginHistoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public LoginHistory loadLoginHistory(SdsUserContext userContext, String loginHistoryId, String [] tokensExpr) throws Exception;
	public LoginHistory internalSaveLoginHistory(SdsUserContext userContext, LoginHistory loginHistory) throws Exception;
	public LoginHistory internalSaveLoginHistory(SdsUserContext userContext, LoginHistory loginHistory,Map<String,Object>option) throws Exception;

	public LoginHistory transferToAnotherSecUser(SdsUserContext userContext, String loginHistoryId, String anotherSecUserId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String loginHistoryId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, LoginHistory newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listBySecUser(SdsUserContext userContext,String secUserId) throws Exception;
	public Object listPageBySecUser(SdsUserContext userContext,String secUserId, int start, int count) throws Exception;
  

}


