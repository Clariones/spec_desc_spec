
package com.cla.sds.candidatecontainer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.SdsUserContext;
import com.cla.sds.BaseEntity;
import com.cla.sds.BaseManager;
import com.cla.sds.SmartList;

public interface CandidateContainerManager extends BaseManager{

		

	public CandidateContainer createCandidateContainer(SdsUserContext userContext, String name) throws Exception;
	public CandidateContainer updateCandidateContainer(SdsUserContext userContext,String candidateContainerId, int candidateContainerVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public CandidateContainer loadCandidateContainer(SdsUserContext userContext, String candidateContainerId, String [] tokensExpr) throws Exception;
	public CandidateContainer internalSaveCandidateContainer(SdsUserContext userContext, CandidateContainer candidateContainer) throws Exception;
	public CandidateContainer internalSaveCandidateContainer(SdsUserContext userContext, CandidateContainer candidateContainer,Map<String,Object>option) throws Exception;



	public void delete(SdsUserContext userContext, String candidateContainerId, int version) throws Exception;
	public int deleteAll(SdsUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(SdsUserContext userContext, CandidateContainer newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/


	//public  CandidateElementManager getCandidateElementManager(SdsUserContext userContext, String candidateContainerId, String name, String type, String image ,String [] tokensExpr)  throws Exception;

	public  CandidateContainer addCandidateElement(SdsUserContext userContext, String candidateContainerId, String name, String type, String image , String [] tokensExpr)  throws Exception;
	public  CandidateContainer removeCandidateElement(SdsUserContext userContext, String candidateContainerId, String candidateElementId, int candidateElementVersion,String [] tokensExpr)  throws Exception;
	public  CandidateContainer updateCandidateElement(SdsUserContext userContext, String candidateContainerId, String candidateElementId, int candidateElementVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


