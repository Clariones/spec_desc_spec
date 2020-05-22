
package com.cla.sds.publickeytype;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class PublicKeyTypeManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public PublicKeyTypeManagerException(String string) {
		super(string);
	}
	public PublicKeyTypeManagerException(Message message) {
		super(message);
	}
	public PublicKeyTypeManagerException(List<Message> messageList) {
		super(messageList);
	}

}


