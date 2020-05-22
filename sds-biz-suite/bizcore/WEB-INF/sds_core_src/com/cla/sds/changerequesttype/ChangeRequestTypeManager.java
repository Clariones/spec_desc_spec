
package com.cla.sds.changerequesttype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface ChangeRequestTypeManager extends BaseManager{

		


	public ChangeRequestType loadChangeRequestTypeWithCode(SdsUserContext userContext, String code, Map<String,Object>tokens) throws Exception;

	 

	public ChangeRequestType createChangeRequestType(SdsUserContext userContext, String name,String code,String icon,int displayOrder,String bindTypes,String stepConfiguration,String platformId) throws Exception;
	public ChangeRequestType updateChangeRequestType(SdsUserContext userContext,String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ChangeRequestType loadChangeRequestType(SdsUserContext userContext, String changeRequestTypeId, String [] tokensExpr) throws Exception;
	public ChangeRequestType internalSaveChangeRequestType(SdsUserContext userContext, ChangeRequestType changeRequestType) throws Exception;
	public ChangeRequestType internalSaveChangeRequestType(SdsUserContext userContext, ChangeRequestType changeRequestType,Map<String,Object>option) throws Exception;

	public ChangeRequestType transferToAnotherPlatform(SdsUserContext userContext, String changeRequestTypeId, String anotherPlatformId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String changeRequestTypeId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, ChangeRequestType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  ChangeRequestManager getChangeRequestManager(SdsUserContext userContext, String changeRequestTypeId, String name, boolean commited, String platformId ,String [] tokensExpr)  throws Exception;

	public  ChangeRequestType addChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String name, boolean commited, String platformId , String [] tokensExpr)  throws Exception;
	public  ChangeRequestType removeChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String changeRequestId, int changeRequestVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequestType updateChangeRequest(SdsUserContext userContext, String changeRequestTypeId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByPlatform(SdsUserContext userContext,String platformId) throws Exception;
	public Object listPageByPlatform(SdsUserContext userContext,String platformId, int start, int count) throws Exception;
  

}


