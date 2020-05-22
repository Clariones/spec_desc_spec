
package com.cla.sds.pagetype;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class PageTypeManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public PageTypeManagerException(String string) {
		super(string);
	}
	public PageTypeManagerException(Message message) {
		super(message);
	}
	public PageTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


