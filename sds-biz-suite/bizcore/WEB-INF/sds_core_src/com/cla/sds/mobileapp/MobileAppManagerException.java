
package com.cla.sds.mobileapp;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class MobileAppManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public MobileAppManagerException(String string) {
		super(string);
	}
	public MobileAppManagerException(Message message) {
		super(message);
	}
	public MobileAppManagerException(List<Message> messageList) {
		super(messageList);
	}

}


