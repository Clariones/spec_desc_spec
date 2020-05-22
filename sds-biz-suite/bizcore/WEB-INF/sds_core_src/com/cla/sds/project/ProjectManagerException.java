
package com.cla.sds.project;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class ProjectManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public ProjectManagerException(String string) {
		super(string);
	}
	public ProjectManagerException(Message message) {
		super(message);
	}
	public ProjectManagerException(List<Message> messageList) {
		super(messageList);
	}

}


