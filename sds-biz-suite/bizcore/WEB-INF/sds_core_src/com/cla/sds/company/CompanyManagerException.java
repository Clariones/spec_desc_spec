
package com.cla.sds.company;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class CompanyManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public CompanyManagerException(String string) {
		super(string);
	}
	public CompanyManagerException(Message message) {
		super(message);
	}
	public CompanyManagerException(List<Message> messageList) {
		super(messageList);
	}

}


