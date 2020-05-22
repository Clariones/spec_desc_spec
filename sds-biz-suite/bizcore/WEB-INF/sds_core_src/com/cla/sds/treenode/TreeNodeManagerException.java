
package com.cla.sds.treenode;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class TreeNodeManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public TreeNodeManagerException(String string) {
		super(string);
	}
	public TreeNodeManagerException(Message message) {
		super(message);
	}
	public TreeNodeManagerException(List<Message> messageList) {
		super(messageList);
	}

}



















