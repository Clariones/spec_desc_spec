
package com.cla.sds.eventupdateprofile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.cla.sds.BaseRowMapper;
import com.cla.sds.changerequest.ChangeRequest;

public class EventUpdateProfileMapper extends BaseRowMapper<EventUpdateProfile>{
	
	protected EventUpdateProfile internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		EventUpdateProfile eventUpdateProfile = getEventUpdateProfile();		
		 		
 		setId(eventUpdateProfile, rs, rowNumber); 		
 		setName(eventUpdateProfile, rs, rowNumber); 		
 		setAvantar(eventUpdateProfile, rs, rowNumber); 		
 		setFieldGroup(eventUpdateProfile, rs, rowNumber); 		
 		setEventInitiatorType(eventUpdateProfile, rs, rowNumber); 		
 		setEventInitiatorId(eventUpdateProfile, rs, rowNumber); 		
 		setChangeRequest(eventUpdateProfile, rs, rowNumber); 		
 		setVersion(eventUpdateProfile, rs, rowNumber);

		return eventUpdateProfile;
	}
	
	protected EventUpdateProfile getEventUpdateProfile(){
		return new EventUpdateProfile();
	}		
		
	protected void setId(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(EventUpdateProfileTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setId(id);
	}
		
	protected void setName(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(EventUpdateProfileTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setName(name);
	}
		
	protected void setAvantar(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String avantar = rs.getString(EventUpdateProfileTable.COLUMN_AVANTAR);
		
		if(avantar == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setAvantar(avantar);
	}
		
	protected void setFieldGroup(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String fieldGroup = rs.getString(EventUpdateProfileTable.COLUMN_FIELD_GROUP);
		
		if(fieldGroup == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setFieldGroup(fieldGroup);
	}
		
	protected void setEventInitiatorType(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String eventInitiatorType = rs.getString(EventUpdateProfileTable.COLUMN_EVENT_INITIATOR_TYPE);
		
		if(eventInitiatorType == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setEventInitiatorType(eventInitiatorType);
	}
		
	protected void setEventInitiatorId(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String eventInitiatorId = rs.getString(EventUpdateProfileTable.COLUMN_EVENT_INITIATOR_ID);
		
		if(eventInitiatorId == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setEventInitiatorId(eventInitiatorId);
	}
		 		
 	protected void setChangeRequest(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
 		String changeRequestId = rs.getString(EventUpdateProfileTable.COLUMN_CHANGE_REQUEST);
 		if( changeRequestId == null){
 			return;
 		}
 		if( changeRequestId.isEmpty()){
 			return;
 		}
 		ChangeRequest changeRequest = eventUpdateProfile.getChangeRequest();
 		if( changeRequest != null ){
 			//if the root object 'eventUpdateProfile' already have the property, just set the id for it;
 			changeRequest.setId(changeRequestId);
 			
 			return;
 		}
 		eventUpdateProfile.setChangeRequest(createEmptyChangeRequest(changeRequestId));
 	}
 	
	protected void setVersion(EventUpdateProfile eventUpdateProfile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(EventUpdateProfileTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		eventUpdateProfile.setVersion(version);
	}
		
		

 	protected ChangeRequest  createEmptyChangeRequest(String changeRequestId){
 		ChangeRequest changeRequest = new ChangeRequest();
 		changeRequest.setId(changeRequestId);
 		changeRequest.setVersion(Integer.MAX_VALUE);
 		return changeRequest;
 	}
 	
}


