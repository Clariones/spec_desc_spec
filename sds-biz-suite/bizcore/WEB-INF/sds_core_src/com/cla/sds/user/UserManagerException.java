
package com.cla.sds.user;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class UserManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public UserManagerException(String string) {
		super(string);
	}
	public UserManagerException(Message message) {
		super(message);
	}
	public UserManagerException(List<Message> messageList) {
		super(messageList);
	}

}


