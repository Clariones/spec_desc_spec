
package com.cla.sds.wechatminiappidentity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface WechatMiniappIdentityManager extends BaseManager{

		

	public WechatMiniappIdentity createWechatMiniappIdentity(SdsUserContext userContext, String openId,String appId,String secUserId,DateTime lastLoginTime) throws Exception;
	public WechatMiniappIdentity updateWechatMiniappIdentity(SdsUserContext userContext,String wechatMiniappIdentityId, int wechatMiniappIdentityVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public WechatMiniappIdentity loadWechatMiniappIdentity(SdsUserContext userContext, String wechatMiniappIdentityId, String [] tokensExpr) throws Exception;
	public WechatMiniappIdentity internalSaveWechatMiniappIdentity(SdsUserContext userContext, WechatMiniappIdentity wechatMiniappIdentity) throws Exception;
	public WechatMiniappIdentity internalSaveWechatMiniappIdentity(SdsUserContext userContext, WechatMiniappIdentity wechatMiniappIdentity,Map<String,Object>option) throws Exception;

	public WechatMiniappIdentity transferToAnotherSecUser(SdsUserContext userContext, String wechatMiniappIdentityId, String anotherSecUserId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String wechatMiniappIdentityId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, WechatMiniappIdentity newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listBySecUser(SdsUserContext userContext,String secUserId) throws Exception;
	public Object listPageBySecUser(SdsUserContext userContext,String secUserId, int start, int count) throws Exception;
  

}


