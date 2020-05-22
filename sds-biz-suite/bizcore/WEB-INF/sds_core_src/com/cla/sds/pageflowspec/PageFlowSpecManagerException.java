
package com.cla.sds.pageflowspec;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class PageFlowSpecManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public PageFlowSpecManagerException(String string) {
		super(string);
	}
	public PageFlowSpecManagerException(Message message) {
		super(message);
	}
	public PageFlowSpecManagerException(List<Message> messageList) {
		super(messageList);
	}

}


