
package com.cla.sds.eventupdateprofile;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface EventUpdateProfileManager extends BaseManager{

		

	public EventUpdateProfile createEventUpdateProfile(SdsUserContext userContext, String name,String avantar,String fieldGroup,String eventInitiatorType,String eventInitiatorId,String changeRequestId) throws Exception;
	public EventUpdateProfile updateEventUpdateProfile(SdsUserContext userContext,String eventUpdateProfileId, int eventUpdateProfileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public EventUpdateProfile loadEventUpdateProfile(SdsUserContext userContext, String eventUpdateProfileId, String [] tokensExpr) throws Exception;
	public EventUpdateProfile internalSaveEventUpdateProfile(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile) throws Exception;
	public EventUpdateProfile internalSaveEventUpdateProfile(SdsUserContext userContext, EventUpdateProfile eventUpdateProfile,Map<String,Object>option) throws Exception;

	public EventUpdateProfile transferToAnotherChangeRequest(SdsUserContext userContext, String eventUpdateProfileId, String anotherChangeRequestId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String eventUpdateProfileId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, EventUpdateProfile newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByChangeRequest(SdsUserContext userContext,String changeRequestId) throws Exception;
	public Object listPageByChangeRequest(SdsUserContext userContext,String changeRequestId, int start, int count) throws Exception;
  

}


