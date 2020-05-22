
package com.cla.sds.changerequesttype;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class ChangeRequestTypeManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestTypeManagerException(String string) {
		super(string);
	}
	public ChangeRequestTypeManagerException(Message message) {
		super(message);
	}
	public ChangeRequestTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


