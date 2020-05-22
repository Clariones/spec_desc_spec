
package com.cla.sds.userproject;
import com.cla.sds.AccessKey;


public class UserProjectTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="user_project_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_USER = "user";
	static final String COLUMN_PROJECT = "project";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_LAST_UPDATE_TIME = "last_update_time";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_USER,COLUMN_PROJECT,COLUMN_CREATE_TIME,COLUMN_LAST_UPDATE_TIME,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_USER,COLUMN_PROJECT,COLUMN_CREATE_TIME,COLUMN_LAST_UPDATE_TIME};
	
	
}


