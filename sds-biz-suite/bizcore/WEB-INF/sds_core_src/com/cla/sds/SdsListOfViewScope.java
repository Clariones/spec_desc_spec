package com.cla.sds;

public class SdsListOfViewScope extends BaseSdsListOfViewScope {

	protected static SdsListOfViewScope instance = new SdsListOfViewScope();
	static {
		instance.initAllViewScope();
	}
	public static SdsListOfViewScope getInstance() {
		return instance;
	}
}















