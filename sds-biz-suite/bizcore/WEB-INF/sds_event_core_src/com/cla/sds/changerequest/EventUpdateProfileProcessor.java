package  com.cla.sds.changerequest;

import java.util.Arrays;
import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.SdsUserContext;

public class EventUpdateProfileProcessor extends ChangeRequestChainProcessor{
	
	
	protected void processInternal(SdsUserContext userContext, ChangeRequest request, String beanName){
		request.getEventUpdateProfileList().forEach(event->{
			
			handleSingleEvent(userContext,request,event);
		});
	}
	protected void handleSingleEvent(SdsUserContext userContext, ChangeRequest request, EventUpdateProfile event ){
		
		userContext.log("EventUpdateProfileProcessor\t"+ event +" from processor");
		
		/*
		try {
				Account a1 = accountManagerOf(userContext)
						.loadAccount(userContext, event.getAccount().getId(), new String[] {});
				a1.updateName(event.getName());
				accountManagerOf(userContext).internalSaveAccount(userContext, a1);
		} catch (Exception e) {
				
				e.printStackTrace();
		}*/
	}
	
}


