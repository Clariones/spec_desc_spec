
package  com.cla.sds;

import com.terapico.caf.baseelement.CandidateQuery;

public interface BaseManager{
	<D> D daoOf(SdsUserContext userContext);
	
	Object queryCandidates(SdsUserContext userContext, CandidateQuery query) throws Exception;
	
	Object queryCandidatesForAssign(SdsUserContext userContext, CandidateQuery query) throws Exception;

	Object queryCandidatesForSearch(SdsUserContext userContext, CandidateQuery query) throws Exception;
}
















