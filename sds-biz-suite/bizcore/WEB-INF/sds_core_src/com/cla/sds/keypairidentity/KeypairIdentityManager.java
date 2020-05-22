
package com.cla.sds.keypairidentity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface KeypairIdentityManager extends BaseManager{

		

	public KeypairIdentity createKeypairIdentity(SdsUserContext userContext, String publicKey,String keyTypeId,String secUserId) throws Exception;
	public KeypairIdentity updateKeypairIdentity(SdsUserContext userContext,String keypairIdentityId, int keypairIdentityVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public KeypairIdentity loadKeypairIdentity(SdsUserContext userContext, String keypairIdentityId, String [] tokensExpr) throws Exception;
	public KeypairIdentity internalSaveKeypairIdentity(SdsUserContext userContext, KeypairIdentity keypairIdentity) throws Exception;
	public KeypairIdentity internalSaveKeypairIdentity(SdsUserContext userContext, KeypairIdentity keypairIdentity,Map<String,Object>option) throws Exception;

	public KeypairIdentity transferToAnotherKeyType(SdsUserContext userContext, String keypairIdentityId, String anotherKeyTypeId)  throws Exception;
 	public KeypairIdentity transferToAnotherSecUser(SdsUserContext userContext, String keypairIdentityId, String anotherSecUserId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String keypairIdentityId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, KeypairIdentity newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByKeyType(SdsUserContext userContext,String keyTypeId) throws Exception;
	public Object listPageByKeyType(SdsUserContext userContext,String keyTypeId, int start, int count) throws Exception;
  
	public Object listBySecUser(SdsUserContext userContext,String secUserId) throws Exception;
	public Object listPageBySecUser(SdsUserContext userContext,String secUserId, int start, int count) throws Exception;
  

}


