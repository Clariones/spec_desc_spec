
package com.cla.sds.keypairidentity;
import com.cla.sds.AccessKey;


public class KeypairIdentityTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="keypair_identity_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_PUBLIC_KEY = "public_key";
	static final String COLUMN_KEY_TYPE = "key_type";
	static final String COLUMN_SEC_USER = "sec_user";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_PUBLIC_KEY,COLUMN_KEY_TYPE,COLUMN_SEC_USER,COLUMN_CREATE_TIME,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_PUBLIC_KEY,COLUMN_KEY_TYPE,COLUMN_SEC_USER,COLUMN_CREATE_TIME};
	
	
}


