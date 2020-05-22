
package com.cla.sds.platform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface PlatformManager extends BaseManager{

		

	public Platform createPlatform(SdsUserContext userContext, String name) throws Exception;
	public Platform updatePlatform(SdsUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Platform loadPlatform(SdsUserContext userContext, String platformId, String [] tokensExpr) throws Exception;
	public Platform internalSavePlatform(SdsUserContext userContext, Platform platform) throws Exception;
	public Platform internalSavePlatform(SdsUserContext userContext, Platform platform,Map<String,Object>option) throws Exception;



	public void delete(SdsUserContext userContext, String platformId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, Platform newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  CompanyManager getCompanyManager(SdsUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;

	public  Platform addCompany(SdsUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removeCompany(SdsUserContext userContext, String platformId, String companyId, int companyVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateCompany(SdsUserContext userContext, String platformId, String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ChangeRequestTypeManager getChangeRequestTypeManager(SdsUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration ,String [] tokensExpr)  throws Exception;

	public  Platform addChangeRequestType(SdsUserContext userContext, String platformId, String name, String code, String icon, int displayOrder, String bindTypes, String stepConfiguration , String [] tokensExpr)  throws Exception;
	public  Platform removeChangeRequestType(SdsUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateChangeRequestType(SdsUserContext userContext, String platformId, String changeRequestTypeId, int changeRequestTypeVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ChangeRequestManager getChangeRequestManager(SdsUserContext userContext, String platformId, String name, String requestTypeId, boolean commited ,String [] tokensExpr)  throws Exception;

	public  Platform addChangeRequest(SdsUserContext userContext, String platformId, String name, String requestTypeId, boolean commited , String [] tokensExpr)  throws Exception;
	public  Platform removeChangeRequest(SdsUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateChangeRequest(SdsUserContext userContext, String platformId, String changeRequestId, int changeRequestVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


