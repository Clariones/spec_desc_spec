package com.cla.sds.iamservice;

import com.cla.sds.SdsUserContext;

public interface IdentificationHandler {

	/** 完成认证 */
	LoginResult authenticate(SdsUserContext userContext, LoginContext loginContext);
	/** 绑定用户 */
	void bindWithSecUser(SdsUserContext userContext, LoginContext loginContext) throws Exception;
}
















