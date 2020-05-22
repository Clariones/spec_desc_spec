
package com.cla.sds.changerequest;
import com.cla.sds.AccessKey;

import com.cla.sds.BaseGridViewGenerator;

public class ChangeRequestGridViewGenerator extends BaseGridViewGenerator{
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	protected void throwExceptionIfListNotFount(String listName) {
		String message=String.format("List '%s' is not found for ChangeRequest", listName);
		throw new IllegalArgumentException(message);
	}
	
	protected String [] getHeaderKeys(String listName) {
		
		if(ChangeRequest.EVENT_UPDATE_PROFILE_LIST.equals(listName)){
			return new String[]{"id","name","avantar","field_group","event_initiator_type","event_initiator_id","change_request","version"};
		}
		
		throwExceptionIfListNotFount(listName);
		return new String[]{}; // place holder, code will never go here!!!
		
		
	}
	protected String  getObjectKey(String listName) {
		if(ChangeRequest.EVENT_UPDATE_PROFILE_LIST.equals(listName)){
			return "event_update_profile";
		}
		

		throwExceptionIfListNotFount(listName);
		return ""; // place holder, code will never go here!!!
	}

}





