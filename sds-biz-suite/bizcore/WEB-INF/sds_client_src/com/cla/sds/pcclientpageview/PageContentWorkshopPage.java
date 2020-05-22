package com.cla.sds.pcclientpageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.cla.sds.pcclientpageview.CustomBaseViewPage;
import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.SdsViewScope;

public class PageContentWorkshopPage extends CustomBaseViewPage{
	private static final long serialVersionUID = 1L;
	private static SdsViewScope ViewScope = SdsViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions", SerializeScope.EXCLUDE())
			.field("actionList")
			;
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "页面:xxx";
		}
		return pageTitle;
	}
	@Override
	public void assemblerContent(SdsUserContext userContext, String requestName)throws Exception {
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl)userContext;
		// TODO: 需要实现
		setPageTitle("尚未实现");
	}
}
