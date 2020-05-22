
package com.cla.sds.changerequestspec;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class ChangeRequestSpecManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public ChangeRequestSpecManagerException(String string) {
		super(string);
	}
	public ChangeRequestSpecManagerException(Message message) {
		super(message);
	}
	public ChangeRequestSpecManagerException(List<Message> messageList) {
		super(messageList);
	}

}


