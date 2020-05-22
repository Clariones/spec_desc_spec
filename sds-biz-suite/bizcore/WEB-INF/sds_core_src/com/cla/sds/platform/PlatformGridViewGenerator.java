
package com.cla.sds.platform;
import com.cla.sds.AccessKey;

import com.cla.sds.BaseGridViewGenerator;

public class PlatformGridViewGenerator extends BaseGridViewGenerator{
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	protected void throwExceptionIfListNotFount(String listName) {
		String message=String.format("List '%s' is not found for Platform", listName);
		throw new IllegalArgumentException(message);
	}
	
	protected String [] getHeaderKeys(String listName) {
		
		if(Platform.COMPANY_LIST.equals(listName)){
			return new String[]{"id","name","create_time","platform","version"};
		}
		if(Platform.CHANGE_REQUEST_TYPE_LIST.equals(listName)){
			return new String[]{"id","name","code","icon","display_order","bind_types","step_configuration","platform","version"};
		}
		if(Platform.CHANGE_REQUEST_LIST.equals(listName)){
			return new String[]{"id","name","create_time","remote_ip","request_type","commited","platform","version"};
		}
		
		throwExceptionIfListNotFount(listName);
		return new String[]{}; // place holder, code will never go here!!!
		
		
	}
	protected String  getObjectKey(String listName) {
		if(Platform.COMPANY_LIST.equals(listName)){
			return "company";
		}
		if(Platform.CHANGE_REQUEST_TYPE_LIST.equals(listName)){
			return "change_request_type";
		}
		if(Platform.CHANGE_REQUEST_LIST.equals(listName)){
			return "change_request";
		}
		

		throwExceptionIfListNotFount(listName);
		return ""; // place holder, code will never go here!!!
	}

}





