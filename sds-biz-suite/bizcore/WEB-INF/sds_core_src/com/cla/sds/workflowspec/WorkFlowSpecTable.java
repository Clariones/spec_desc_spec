
package com.cla.sds.workflowspec;
import com.cla.sds.AccessKey;


public class WorkFlowSpecTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="work_flow_spec_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_SCENE = "scene";
	static final String COLUMN_BRIEF = "brief";
	static final String COLUMN_OWNER = "owner";
	static final String COLUMN_PROJECT = "project";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_SCENE,COLUMN_BRIEF,COLUMN_OWNER,COLUMN_PROJECT,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_SCENE,COLUMN_BRIEF,COLUMN_OWNER,COLUMN_PROJECT};
	
	
}


