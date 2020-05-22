
package com.cla.sds.uiaction;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class UiActionManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public UiActionManagerException(String string) {
		super(string);
	}
	public UiActionManagerException(Message message) {
		super(message);
	}
	public UiActionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


