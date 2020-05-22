
package com.cla.sds.wechatminiappidentity;
//import com.cla.sds.EntityNotFoundException;
import com.cla.sds.SdsException;
import com.cla.sds.Message;
import java.util.List;

public class WechatMiniappIdentityManagerException extends SdsException {
	private static final long serialVersionUID = 1L;
	public WechatMiniappIdentityManagerException(String string) {
		super(string);
	}
	public WechatMiniappIdentityManagerException(Message message) {
		super(message);
	}
	public WechatMiniappIdentityManagerException(List<Message> messageList) {
		super(messageList);
	}

}


