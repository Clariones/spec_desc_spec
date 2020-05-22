
package com.cla.sds.publickeytype;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface PublicKeyTypeManager extends BaseManager{

		

	public PublicKeyType createPublicKeyType(SdsUserContext userContext, String name,String code,String domainId) throws Exception;
	public PublicKeyType updatePublicKeyType(SdsUserContext userContext,String publicKeyTypeId, int publicKeyTypeVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PublicKeyType loadPublicKeyType(SdsUserContext userContext, String publicKeyTypeId, String [] tokensExpr) throws Exception;
	public PublicKeyType internalSavePublicKeyType(SdsUserContext userContext, PublicKeyType publicKeyType) throws Exception;
	public PublicKeyType internalSavePublicKeyType(SdsUserContext userContext, PublicKeyType publicKeyType,Map<String,Object>option) throws Exception;

	public PublicKeyType transferToAnotherDomain(SdsUserContext userContext, String publicKeyTypeId, String anotherDomainId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String publicKeyTypeId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, PublicKeyType newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  KeypairIdentityManager getKeypairIdentityManager(SdsUserContext userContext, String publicKeyTypeId, String publicKey, String secUserId ,String [] tokensExpr)  throws Exception;

	public  PublicKeyType addKeypairIdentity(SdsUserContext userContext, String publicKeyTypeId, String publicKey, String secUserId , String [] tokensExpr)  throws Exception;
	public  PublicKeyType removeKeypairIdentity(SdsUserContext userContext, String publicKeyTypeId, String keypairIdentityId, int keypairIdentityVersion,String [] tokensExpr)  throws Exception;
	public  PublicKeyType updateKeypairIdentity(SdsUserContext userContext, String publicKeyTypeId, String keypairIdentityId, int keypairIdentityVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByDomain(SdsUserContext userContext,String domainId) throws Exception;
	public Object listPageByDomain(SdsUserContext userContext,String domainId, int start, int count) throws Exception;
  

}


