
package com.cla.sds.company;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface CompanyManager extends BaseManager{

		

	public Company createCompany(SdsUserContext userContext, String name,String platformId) throws Exception;
	public Company updateCompany(SdsUserContext userContext,String companyId, int companyVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Company loadCompany(SdsUserContext userContext, String companyId, String [] tokensExpr) throws Exception;
	public Company internalSaveCompany(SdsUserContext userContext, Company company) throws Exception;
	public Company internalSaveCompany(SdsUserContext userContext, Company company,Map<String,Object>option) throws Exception;

	public Company transferToAnotherPlatform(SdsUserContext userContext, String companyId, String anotherPlatformId)  throws Exception;
 

	public void delete(SdsUserContext userContext, String companyId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, Company newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  UserManager getUserManager(SdsUserContext userContext, String companyId, String name, Date joinTime, String title ,String [] tokensExpr)  throws Exception;

	public  Company addUser(SdsUserContext userContext, String companyId, String name, Date joinTime, String title , String [] tokensExpr)  throws Exception;
	public  Company removeUser(SdsUserContext userContext, String companyId, String userId, int userVersion,String [] tokensExpr)  throws Exception;
	public  Company updateUser(SdsUserContext userContext, String companyId, String userId, int userVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  ProjectManager getProjectManager(SdsUserContext userContext, String companyId, String name ,String [] tokensExpr)  throws Exception;

	public  Company addProject(SdsUserContext userContext, String companyId, String name , String [] tokensExpr)  throws Exception;
	public  Company removeProject(SdsUserContext userContext, String companyId, String projectId, int projectVersion,String [] tokensExpr)  throws Exception;
	public  Company updateProject(SdsUserContext userContext, String companyId, String projectId, int projectVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/


	public Object listByPlatform(SdsUserContext userContext,String platformId) throws Exception;
	public Object listPageByPlatform(SdsUserContext userContext,String platformId, int start, int count) throws Exception;
  

}


