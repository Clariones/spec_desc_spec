
package com.cla.sds.eventupdateprofile;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class EventUpdateProfileManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public EventUpdateProfileManagerException(String string) {
		super(string);
	}
	public EventUpdateProfileManagerException(Message message) {
		super(message);
	}
	public EventUpdateProfileManagerException(List<Message> messageList) {
		super(messageList);
	}

}


