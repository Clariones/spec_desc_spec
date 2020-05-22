
package com.cla.sds.eventupdateprofile;
import com.cla.sds.AccessKey;


public class EventUpdateProfileTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="event_update_profile_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_AVANTAR = "avantar";
	static final String COLUMN_FIELD_GROUP = "field_group";
	static final String COLUMN_EVENT_INITIATOR_TYPE = "event_initiator_type";
	static final String COLUMN_EVENT_INITIATOR_ID = "event_initiator_id";
	static final String COLUMN_CHANGE_REQUEST = "change_request";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_AVANTAR,COLUMN_FIELD_GROUP,COLUMN_EVENT_INITIATOR_TYPE,COLUMN_EVENT_INITIATOR_ID,COLUMN_CHANGE_REQUEST,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_AVANTAR,COLUMN_FIELD_GROUP,COLUMN_EVENT_INITIATOR_TYPE,COLUMN_EVENT_INITIATOR_ID,COLUMN_CHANGE_REQUEST};
	
	
}


