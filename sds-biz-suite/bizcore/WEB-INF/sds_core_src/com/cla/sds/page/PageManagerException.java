
package com.cla.sds.page;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class PageManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public PageManagerException(String string) {
		super(string);
	}
	public PageManagerException(Message message) {
		super(message);
	}
	public PageManagerException(List<Message> messageList) {
		super(messageList);
	}

}


