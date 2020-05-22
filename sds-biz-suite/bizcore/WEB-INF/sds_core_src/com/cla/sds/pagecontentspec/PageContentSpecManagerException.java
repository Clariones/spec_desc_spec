
package com.cla.sds.pagecontentspec;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class PageContentSpecManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public PageContentSpecManagerException(String string) {
		super(string);
	}
	public PageContentSpecManagerException(Message message) {
		super(message);
	}
	public PageContentSpecManagerException(List<Message> messageList) {
		super(messageList);
	}

}


