
package com.cla.sds.section;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class SectionManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public SectionManagerException(String string) {
		super(string);
	}
	public SectionManagerException(Message message) {
		super(message);
	}
	public SectionManagerException(List<Message> messageList) {
		super(messageList);
	}

}


