
package com.cla.sds.changerequest;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class ChangeRequestManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestManagerException(String string) {
		super(string);
	}
	public ChangeRequestManagerException(Message message) {
		super(message);
	}
	public ChangeRequestManagerException(List<Message> messageList) {
		super(messageList);
	}

}


