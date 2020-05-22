
package com.cla.sds.wechatworkappidentity;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class WechatWorkappIdentityManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public WechatWorkappIdentityManagerException(String string) {
		super(string);
	}
	public WechatWorkappIdentityManagerException(Message message) {
		super(message);
	}
	public WechatWorkappIdentityManagerException(List<Message> messageList) {
		super(messageList);
	}

}


