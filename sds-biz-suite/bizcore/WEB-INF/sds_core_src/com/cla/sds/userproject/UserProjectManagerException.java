
package com.cla.sds.userproject;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class UserProjectManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public UserProjectManagerException(String string) {
		super(string);
	}
	public UserProjectManagerException(Message message) {
		super(message);
	}
	public UserProjectManagerException(List<Message> messageList) {
		super(messageList);
	}

}


