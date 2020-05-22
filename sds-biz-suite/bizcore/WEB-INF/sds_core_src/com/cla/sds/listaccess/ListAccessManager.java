
package com.cla.sds.listaccess;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface ListAccessManager extends BaseManager{

		

	public ListAccess createListAccess(SdsUserContext userContext, String name,String internalName,boolean readPermission,boolean createPermission,boolean deletePermission,boolean updatePermission,boolean executionPermission,String appId) throws Exception;
	public ListAccess updateListAccess(SdsUserContext userContext,String listAccessId, int listAccessVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ListAccess loadListAccess(SdsUserContext userContext, String listAccessId, String [] tokensExpr) throws Exception;
	public ListAccess internalSaveListAccess(SdsUserContext userContext, ListAccess listAccess) throws Exception;
	public ListAccess internalSaveListAccess(SdsUserContext userContext, ListAccess listAccess,Map<String,Object>option) throws Exception;

	public ListAccess transferToAnotherApp(SdsUserContext userContext, String listAccessId, String anotherAppId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String listAccessId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, ListAccess newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByApp(SdsUserContext userContext,String appId) throws Exception;
	public Object listPageByApp(SdsUserContext userContext,String appId, int start, int count) throws Exception;
  

}


