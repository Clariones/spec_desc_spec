
package com.cla.sds.userwhitelist;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface UserWhiteListManager extends BaseManager{

		

	public UserWhiteList createUserWhiteList(SdsUserContext userContext, String userIdentity,String userSpecialFunctions,String domainId) throws Exception;
	public UserWhiteList updateUserWhiteList(SdsUserContext userContext,String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UserWhiteList loadUserWhiteList(SdsUserContext userContext, String userWhiteListId, String [] tokensExpr) throws Exception;
	public UserWhiteList internalSaveUserWhiteList(SdsUserContext userContext, UserWhiteList userWhiteList) throws Exception;
	public UserWhiteList internalSaveUserWhiteList(SdsUserContext userContext, UserWhiteList userWhiteList,Map<String,Object>option) throws Exception;

	public UserWhiteList transferToAnotherDomain(SdsUserContext userContext, String userWhiteListId, String anotherDomainId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String userWhiteListId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, UserWhiteList newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/



	public Object listByDomain(SdsUserContext userContext,String domainId) throws Exception;
	public Object listPageByDomain(SdsUserContext userContext,String domainId, int start, int count) throws Exception;
  

}


