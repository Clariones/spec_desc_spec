
package com.cla.sds.changerequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface ChangeRequestManager extends BaseManager{

		

	public ChangeRequest createChangeRequest(SdsUserContext userContext, String name,String requestTypeId,boolean commited,String platformId) throws Exception;
	public ChangeRequest updateChangeRequest(SdsUserContext userContext,String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ChangeRequest loadChangeRequest(SdsUserContext userContext, String changeRequestId, String [] tokensExpr) throws Exception;
	public ChangeRequest internalSaveChangeRequest(SdsUserContext userContext, ChangeRequest changeRequest) throws Exception;
	public ChangeRequest internalSaveChangeRequest(SdsUserContext userContext, ChangeRequest changeRequest,Map<String,Object>option) throws Exception;

	public ChangeRequest transferToAnotherRequestType(SdsUserContext userContext, String changeRequestId, String anotherRequestTypeId)  throws Exception;
 	public ChangeRequest transferToAnotherPlatform(SdsUserContext userContext, String changeRequestId, String anotherPlatformId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String changeRequestId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, ChangeRequest newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  EventUpdateProfileManager getEventUpdateProfileManager(SdsUserContext userContext, String changeRequestId, String name, String avantar, String fieldGroup, String eventInitiatorType, String eventInitiatorId ,String [] tokensExpr)  throws Exception;

	public  ChangeRequest addEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String name, String avantar, String fieldGroup, String eventInitiatorType, String eventInitiatorId , String [] tokensExpr)  throws Exception;
	public  ChangeRequest removeEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String eventUpdateProfileId, int eventUpdateProfileVersion,String [] tokensExpr)  throws Exception;
	public  ChangeRequest updateEventUpdateProfile(SdsUserContext userContext, String changeRequestId, String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByRequestType(SdsUserContext userContext,String requestTypeId) throws Exception;
	public Object listPageByRequestType(SdsUserContext userContext,String requestTypeId, int start, int count) throws Exception;
  
	public Object listByPlatform(SdsUserContext userContext,String platformId) throws Exception;
	public Object listPageByPlatform(SdsUserContext userContext,String platformId, int start, int count) throws Exception;
  

}


