
package com.cla.sds.secuser;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface SecUserManager extends BaseManager{

		


	public SecUser loadSecUserWithLogin(SdsUserContext userContext, String login, Map<String,Object>tokens) throws Exception;

	 


	public SecUser loadSecUserWithEmail(SdsUserContext userContext, String email, Map<String,Object>tokens) throws Exception;

	 


	public SecUser loadSecUserWithMobile(SdsUserContext userContext, String mobile, Map<String,Object>tokens) throws Exception;

	 

	public SecUser createSecUser(SdsUserContext userContext, String login,String mobile,String email,String pwd,String weixinOpenid,String weixinAppid,String accessToken,int verificationCode,DateTime verificationCodeExpire,DateTime lastLoginTime,String domainId) throws Exception;
	public SecUser updateSecUser(SdsUserContext userContext,String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public SecUser loadSecUser(SdsUserContext userContext, String secUserId, String [] tokensExpr) throws Exception;
	public SecUser internalSaveSecUser(SdsUserContext userContext, SecUser secUser) throws Exception;
	public SecUser internalSaveSecUser(SdsUserContext userContext, SecUser secUser,Map<String,Object>option) throws Exception;

	public SecUser transferToAnotherDomain(SdsUserContext userContext, String secUserId, String anotherDomainId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String secUserId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, SecUser newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  UserAppManager getUserAppManager(SdsUserContext userContext, String secUserId, String title, String appIcon, boolean fullAccess, String permission, String objectType, String objectId, String location ,String [] tokensExpr)  throws Exception;

	public  SecUser addUserApp(SdsUserContext userContext, String secUserId, String title, String appIcon, boolean fullAccess, String permission, String objectType, String objectId, String location , String [] tokensExpr)  throws Exception;
	public  SecUser removeUserApp(SdsUserContext userContext, String secUserId, String userAppId, int userAppVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateUserApp(SdsUserContext userContext, String secUserId, String userAppId, int userAppVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  LoginHistoryManager getLoginHistoryManager(SdsUserContext userContext, String secUserId, String fromIp, String description ,String [] tokensExpr)  throws Exception;

	public  SecUser addLoginHistory(SdsUserContext userContext, String secUserId, String fromIp, String description , String [] tokensExpr)  throws Exception;
	public  SecUser removeLoginHistory(SdsUserContext userContext, String secUserId, String loginHistoryId, int loginHistoryVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateLoginHistory(SdsUserContext userContext, String secUserId, String loginHistoryId, int loginHistoryVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WechatWorkappIdentityManager getWechatWorkappIdentityManager(SdsUserContext userContext, String secUserId, String corpId, String userId, DateTime lastLoginTime ,String [] tokensExpr)  throws Exception;

	public  SecUser addWechatWorkappIdentity(SdsUserContext userContext, String secUserId, String corpId, String userId, DateTime lastLoginTime , String [] tokensExpr)  throws Exception;
	public  SecUser removeWechatWorkappIdentity(SdsUserContext userContext, String secUserId, String wechatWorkappIdentityId, int wechatWorkappIdentityVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateWechatWorkappIdentity(SdsUserContext userContext, String secUserId, String wechatWorkappIdentityId, int wechatWorkappIdentityVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  WechatMiniappIdentityManager getWechatMiniappIdentityManager(SdsUserContext userContext, String secUserId, String openId, String appId, DateTime lastLoginTime ,String [] tokensExpr)  throws Exception;

	public  SecUser addWechatMiniappIdentity(SdsUserContext userContext, String secUserId, String openId, String appId, DateTime lastLoginTime , String [] tokensExpr)  throws Exception;
	public  SecUser removeWechatMiniappIdentity(SdsUserContext userContext, String secUserId, String wechatMiniappIdentityId, int wechatMiniappIdentityVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateWechatMiniappIdentity(SdsUserContext userContext, String secUserId, String wechatMiniappIdentityId, int wechatMiniappIdentityVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  KeypairIdentityManager getKeypairIdentityManager(SdsUserContext userContext, String secUserId, String publicKey, String keyTypeId ,String [] tokensExpr)  throws Exception;

	public  SecUser addKeypairIdentity(SdsUserContext userContext, String secUserId, String publicKey, String keyTypeId , String [] tokensExpr)  throws Exception;
	public  SecUser removeKeypairIdentity(SdsUserContext userContext, String secUserId, String keypairIdentityId, int keypairIdentityVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateKeypairIdentity(SdsUserContext userContext, String secUserId, String keypairIdentityId, int keypairIdentityVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByDomain(SdsUserContext userContext,String domainId) throws Exception;
	public Object listPageByDomain(SdsUserContext userContext,String domainId, int start, int count) throws Exception;
  

}


