
package com.cla.sds.workflowspec;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class WorkFlowSpecManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public WorkFlowSpecManagerException(String string) {
		super(string);
	}
	public WorkFlowSpecManagerException(Message message) {
		super(message);
	}
	public WorkFlowSpecManagerException(List<Message> messageList) {
		super(messageList);
	}

}


