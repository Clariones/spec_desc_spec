
package com.cla.sds.userdomain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface UserDomainManager extends BaseManager{

		

	public UserDomain createUserDomain(SdsUserContext userContext, String name) throws Exception;
	public UserDomain updateUserDomain(SdsUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UserDomain loadUserDomain(SdsUserContext userContext, String userDomainId, String [] tokensExpr) throws Exception;
	public UserDomain internalSaveUserDomain(SdsUserContext userContext, UserDomain userDomain) throws Exception;
	public UserDomain internalSaveUserDomain(SdsUserContext userContext, UserDomain userDomain,Map<String,Object>option) throws Exception;



	public void delete(SdsUserContext userContext, String userDomainId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, UserDomain newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  UserWhiteListManager getUserWhiteListManager(SdsUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions ,String [] tokensExpr)  throws Exception;

	public  UserDomain addUserWhiteList(SdsUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions , String [] tokensExpr)  throws Exception;
	public  UserDomain removeUserWhiteList(SdsUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion,String [] tokensExpr)  throws Exception;
	public  UserDomain updateUserWhiteList(SdsUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  SecUserManager getSecUserManager(SdsUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime ,String [] tokensExpr)  throws Exception;

	public  UserDomain addSecUser(SdsUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime , String [] tokensExpr)  throws Exception;
	public  UserDomain removeSecUser(SdsUserContext userContext, String userDomainId, String secUserId, int secUserVersion,String [] tokensExpr)  throws Exception;
	public  UserDomain updateSecUser(SdsUserContext userContext, String userDomainId, String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PublicKeyTypeManager getPublicKeyTypeManager(SdsUserContext userContext, String userDomainId, String name, String code ,String [] tokensExpr)  throws Exception;

	public  UserDomain addPublicKeyType(SdsUserContext userContext, String userDomainId, String name, String code , String [] tokensExpr)  throws Exception;
	public  UserDomain removePublicKeyType(SdsUserContext userContext, String userDomainId, String publicKeyTypeId, int publicKeyTypeVersion,String [] tokensExpr)  throws Exception;
	public  UserDomain updatePublicKeyType(SdsUserContext userContext, String userDomainId, String publicKeyTypeId, int publicKeyTypeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


