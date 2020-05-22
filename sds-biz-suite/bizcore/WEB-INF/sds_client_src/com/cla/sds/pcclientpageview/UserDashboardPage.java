package com.cla.sds.pcclientpageview;

import com.cla.sds.MiscUtils;
import com.cla.sds.changerequestspec.ChangeRequestSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.pcclient.PcClientViewService;
import com.cla.sds.user.User;
import com.cla.sds.userproject.UserProject;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.terapico.caf.viewpage.SerializeScope;
import com.cla.sds.pcclientpageview.CustomBaseViewPage;
import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsUserContextImpl;
import com.cla.sds.SdsViewScope;

public class UserDashboardPage extends CustomBaseViewPage{
	private static final long serialVersionUID = 1L;
	private static SdsViewScope ViewScope = SdsViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions", SerializeScope.EXCLUDE())
			.field("actionList")
			.field("user", SerializeScope.EXCLUDE())
			;
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "我的";
		}
		return pageTitle;
	}
	@Override
	public void assemblerContent(SdsUserContext userContext, String requestName)throws Exception {
		CustomSdsUserContextImpl ctx = (CustomSdsUserContextImpl)userContext;

		User user = Q.findUserWhichByIdForDashboard(ctx, ctx.getCurrentUserInfo().getId());
		for (UserProject userProject : user.getUserProjectList()) {
			for (PageFlowSpec spec : userProject.getProject().getPageFlowSpecList()) {
				MiscUtils.addAction(ctx, spec, "编辑", "edit", PcClientViewService.makeViewPageFlowUrl(ctx, spec.getId()));
			}
			for (WorkFlowSpec spec : userProject.getProject().getWorkFlowSpecList()) {
				MiscUtils.addAction(ctx, spec, "编辑", "edit", PcClientViewService.makeViewWorkFlowUrl(ctx, spec.getId()));
			}
			for (ChangeRequestSpec spec : userProject.getProject().getChangeRequestSpecList()) {
				MiscUtils.addAction(ctx, spec, "编辑", "edit", PcClientViewService.makeViewChangeRequestUrl(ctx, spec.getId()));
			}
			for (PageContentSpec spec : userProject.getProject().getPageContentSpecList()) {
				MiscUtils.addAction(ctx, spec, "编辑", "edit", PcClientViewService.makeViewPageContentUrl(ctx, spec.getId()));
			}
		}
		set("user", user);
	}
}
