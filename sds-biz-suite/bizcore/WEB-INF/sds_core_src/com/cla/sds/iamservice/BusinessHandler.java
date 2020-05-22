package com.cla.sds.iamservice;

import com.cla.sds.SdsUserContext;

public interface BusinessHandler {

	void onAuthenticationFailed(SdsUserContext userContext, LoginContext loginContext, LoginResult loginResult,
			IdentificationHandler idHandler, BusinessHandler bizHandler) throws Exception;

	void onAuthenticateNewUserLogged(SdsUserContext userContext, LoginContext loginContext, LoginResult loginResult,
			IdentificationHandler idHandler, BusinessHandler bizHandler) throws Exception;

	void onAuthenticateUserLogged(SdsUserContext userContext, LoginContext loginContext, LoginResult loginResult,
			IdentificationHandler idHandler, BusinessHandler bizHandler) throws Exception;

  default Object onLogout(SdsUserContext userContext, BusinessHandler bizHandler) throws Exception {
		return null;
	}
}
















