package  com.cla.sds.changerequest;

import java.util.Arrays;
import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.SdsUserContext;

public class EventUpdateProfileCustomProcessor extends EventUpdateProfileProcessor{
	
	
	
	protected void handleSingleEvent(SdsUserContext userContext, ChangeRequest request, EventUpdateProfile event ){
		
		userContext.log("EventUpdateProfileCustomProcessor\t"+ event +" from processor");
		
		
	}
	
}





















