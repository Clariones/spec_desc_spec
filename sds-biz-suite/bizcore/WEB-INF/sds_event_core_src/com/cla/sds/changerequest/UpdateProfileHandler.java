
/*
	本类暂时没有很复杂的代码，这个类用于保留以后智能化推断代码
*/

package  com.cla.sds.changerequest;


import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.SdsUserContext;

public class UpdateProfileHandler extends ChangeRequestBaseHandler{
	@Override	
	protected void checkIfComplyWithSpec(SdsUserContext userContext, ChangeRequest request){
		super.checkIfComplyWithSpec(userContext,request);
	}
}

