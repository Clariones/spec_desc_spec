package com.cla.sds;


import com.terapico.caf.viewpage.SerializeScope;

public class SdsViewScope extends SdsBaseViewScope{

	static {
		// 定制化本项目的序列化scope的代码放在这里
		System.out.println("**************************************************************\n定制序列化\n");
	}
	
	protected static SdsViewScope instance = null;
	public static SdsViewScope getInstance() {
		if (instance != null) {
			return instance;
		}
		synchronized (SdsViewScope.class) {
			instance = new SdsViewScope();
		}
		return instance;
	}
}

















