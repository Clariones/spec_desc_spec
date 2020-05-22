package com.cla.sds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.terapico.caf.form.ImageInfo;
import com.terapico.utils.DebugUtil;

public class BaseSdsFormProcessor extends BaseFormProcessor{
	protected SdsUserContext userContext;
	
	public SdsUserContext getUserContext() {
		return userContext;
	}
	public void setUserContext(SdsUserContext userContext) {
		this.userContext = userContext;
	}
	protected void addMessageToException(SdsException e, String msg) {
		Message message = new Message();
		message.setBody(msg);
		e.addErrorMessage(message);
	}
	public Map<String, Object> mapToUiForm(SdsUserContext userContext) {
		return null; 
	}
}









































