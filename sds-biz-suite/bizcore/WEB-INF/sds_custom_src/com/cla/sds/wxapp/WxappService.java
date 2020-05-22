package com.cla.sds.wxapp;

import com.terapico.caf.baseelement.LoginParam;

import com.cla.sds.SdsUserContextImpl;

public class WxappService extends WxappBaseService {

	// 自定代码只能写在这里,不要写到 WxappBaseService 中
	public Object clientLogin(SdsUserContextImpl ctx, LoginParam param) throws Exception {
		Object result=super.clientLogin(ctx,param);
		ctx.forceResponseXClassHeader("com.terapico.appview.HomePage");
		return result;
	}

	protected boolean isMethodNeedLogin(SdsUserContextImpl ctx, String methodName,
			Object[] parameters) {
		return methodName.startsWith("update") || (methodName.startsWith("view") && methodName
				.endsWith("Page"));
	}

}


















