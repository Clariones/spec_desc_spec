
package com.cla.sds.keypairidentity;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class KeypairIdentityManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public KeypairIdentityManagerException(String string) {
		super(string);
	}
	public KeypairIdentityManagerException(Message message) {
		super(message);
	}
	public KeypairIdentityManagerException(List<Message> messageList) {
		super(messageList);
	}

}


