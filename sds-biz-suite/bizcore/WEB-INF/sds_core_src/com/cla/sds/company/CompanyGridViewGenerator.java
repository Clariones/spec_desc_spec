
package com.cla.sds.company;
import com.cla.sds.AccessKey;

import com.cla.sds.BaseGridViewGenerator;

public class CompanyGridViewGenerator extends BaseGridViewGenerator{
	
	

	private static final long serialVersionUID = 1L;
	
	
	
	protected void throwExceptionIfListNotFount(String listName) {
		String message=String.format("List '%s' is not found for Company", listName);
		throw new IllegalArgumentException(message);
	}
	
	protected String [] getHeaderKeys(String listName) {
		
		if(Company.USER_LIST.equals(listName)){
			return new String[]{"id","name","join_time","company","title","version"};
		}
		if(Company.PROJECT_LIST.equals(listName)){
			return new String[]{"id","name","company","version"};
		}
		
		throwExceptionIfListNotFount(listName);
		return new String[]{}; // place holder, code will never go here!!!
		
		
	}
	protected String  getObjectKey(String listName) {
		if(Company.USER_LIST.equals(listName)){
			return "user";
		}
		if(Company.PROJECT_LIST.equals(listName)){
			return "project";
		}
		

		throwExceptionIfListNotFount(listName);
		return ""; // place holder, code will never go here!!!
	}

}





