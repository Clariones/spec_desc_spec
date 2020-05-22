
package com.cla.sds.changerequesttype;
import com.cla.sds.AccessKey;

import com.cla.sds.BaseGridViewGenerator;

public class ChangeRequestTypeGridViewGenerator extends BaseGridViewGenerator{
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	protected void throwExceptionIfListNotFount(String listName) {
		String message=String.format("List '%s' is not found for ChangeRequestType", listName);
		throw new IllegalArgumentException(message);
	}
	
	protected String [] getHeaderKeys(String listName) {
		
		if(ChangeRequestType.CHANGE_REQUEST_LIST.equals(listName)){
			return new String[]{"id","name","create_time","remote_ip","request_type","commited","platform","version"};
		}
		
		throwExceptionIfListNotFount(listName);
		return new String[]{}; // place holder, code will never go here!!!
		
		
	}
	protected String  getObjectKey(String listName) {
		if(ChangeRequestType.CHANGE_REQUEST_LIST.equals(listName)){
			return "change_request";
		}
		

		throwExceptionIfListNotFount(listName);
		return ""; // place holder, code will never go here!!!
	}

}





