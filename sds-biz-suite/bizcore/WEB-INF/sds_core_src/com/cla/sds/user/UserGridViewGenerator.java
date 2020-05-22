
package com.cla.sds.user;
import com.cla.sds.AccessKey;

import com.cla.sds.BaseGridViewGenerator;

public class UserGridViewGenerator extends BaseGridViewGenerator{
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	protected void throwExceptionIfListNotFount(String listName) {
		String message=String.format("List '%s' is not found for User", listName);
		throw new IllegalArgumentException(message);
	}
	
	protected String [] getHeaderKeys(String listName) {
		
		if(User.USER_PROJECT_LIST.equals(listName)){
			return new String[]{"id","user","project","create_time","last_update_time","version"};
		}
		if(User.PAGE_FLOW_SPEC_LIST.equals(listName)){
			return new String[]{"id","scene","brief","owner","project","version"};
		}
		if(User.WORK_FLOW_SPEC_LIST.equals(listName)){
			return new String[]{"id","scene","brief","owner","project","version"};
		}
		if(User.CHANGE_REQUEST_SPEC_LIST.equals(listName)){
			return new String[]{"id","scene","brief","owner","project","version"};
		}
		if(User.PAGE_CONTENT_SPEC_LIST.equals(listName)){
			return new String[]{"id","scene","brief","owner","project","version"};
		}
		
		throwExceptionIfListNotFount(listName);
		return new String[]{}; // place holder, code will never go here!!!
		
		
	}
	protected String  getObjectKey(String listName) {
		if(User.USER_PROJECT_LIST.equals(listName)){
			return "user_project";
		}
		if(User.PAGE_FLOW_SPEC_LIST.equals(listName)){
			return "page_flow_spec";
		}
		if(User.WORK_FLOW_SPEC_LIST.equals(listName)){
			return "work_flow_spec";
		}
		if(User.CHANGE_REQUEST_SPEC_LIST.equals(listName)){
			return "change_request_spec";
		}
		if(User.PAGE_CONTENT_SPEC_LIST.equals(listName)){
			return "page_content_spec";
		}
		

		throwExceptionIfListNotFount(listName);
		return ""; // place holder, code will never go here!!!
	}

}





