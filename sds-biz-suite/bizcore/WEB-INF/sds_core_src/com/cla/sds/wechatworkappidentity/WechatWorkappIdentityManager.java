
package com.cla.sds.wechatworkappidentity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface WechatWorkappIdentityManager extends BaseManager{

		

	public WechatWorkappIdentity createWechatWorkappIdentity(SdsUserContext userContext, String corpId,String userId,String secUserId,DateTime lastLoginTime) throws Exception;
	public WechatWorkappIdentity updateWechatWorkappIdentity(SdsUserContext userContext,String wechatWorkappIdentityId, int wechatWorkappIdentityVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public WechatWorkappIdentity loadWechatWorkappIdentity(SdsUserContext userContext, String wechatWorkappIdentityId, String [] tokensExpr) throws Exception;
	public WechatWorkappIdentity internalSaveWechatWorkappIdentity(SdsUserContext userContext, WechatWorkappIdentity wechatWorkappIdentity) throws Exception;
	public WechatWorkappIdentity internalSaveWechatWorkappIdentity(SdsUserContext userContext, WechatWorkappIdentity wechatWorkappIdentity,Map<String,Object>option) throws Exception;

	public WechatWorkappIdentity transferToAnotherSecUser(SdsUserContext userContext, String wechatWorkappIdentityId, String anotherSecUserId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String wechatWorkappIdentityId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, WechatWorkappIdentity newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listBySecUser(SdsUserContext userContext,String secUserId) throws Exception;
	public Object listPageBySecUser(SdsUserContext userContext,String secUserId, int start, int count) throws Exception;
  

}


