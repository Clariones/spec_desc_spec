
package com.cla.sds.userapp;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class UserAppManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public UserAppManagerException(String string) {
		super(string);
	}
	public UserAppManagerException(Message message) {
		super(message);
	}
	public UserAppManagerException(List<Message> messageList) {
		super(messageList);
	}

}


