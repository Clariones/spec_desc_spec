package com.cla.sds;

public class CustomSdsUserContextImpl extends SdsBizUserContextImpl{
	protected BaseEntity currentUserInfo;
	protected String password;
	protected String login;
	protected String pageFlowSpecId;
	protected String workFlowSpecId;
	protected String pageContentSpecId;
	protected String changeRequestSpecId;

	public String getPageFlowSpecId() {
		return pageFlowSpecId;
	}

	public void setPageFlowSpecId(String pageFlowSpecId) {
		this.pageFlowSpecId = pageFlowSpecId;
	}

	public String getWorkFlowSpecId() {
		return workFlowSpecId;
	}

	public void setWorkFlowSpecId(String workFlowSpecId) {
		this.workFlowSpecId = workFlowSpecId;
	}

	public String getPageContentSpecId() {
		return pageContentSpecId;
	}

	public void setPageContentSpecId(String pageContentSpecId) {
		this.pageContentSpecId = pageContentSpecId;
	}

	public String getChangeRequestSpecId() {
		return changeRequestSpecId;
	}

	public void setChangeRequestSpecId(String changeRequestSpecId) {
		this.changeRequestSpecId = changeRequestSpecId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public BaseEntity getCurrentUserInfo() {
		return currentUserInfo;
	}

	public void setCurrentUserInfo(BaseEntity currentUserInfo) {
		this.currentUserInfo = currentUserInfo;
	}

}

