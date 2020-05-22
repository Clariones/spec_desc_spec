
package com.cla.sds.platform;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class PlatformManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public PlatformManagerException(String string) {
		super(string);
	}
	public PlatformManagerException(Message message) {
		super(message);
	}
	public PlatformManagerException(List<Message> messageList) {
		super(messageList);
	}

}


