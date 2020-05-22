package com.cla.sds;


import com.cla.sds.platform.PlatformManager;

import com.cla.sds.company.CompanyManager;

import com.cla.sds.user.UserManager;

import com.cla.sds.userproject.UserProjectManager;

import com.cla.sds.changerequesttype.ChangeRequestTypeManager;

import com.cla.sds.changerequest.ChangeRequestManager;

import com.cla.sds.eventupdateprofile.EventUpdateProfileManager;

import com.cla.sds.project.ProjectManager;

import com.cla.sds.pageflowspec.PageFlowSpecManager;

import com.cla.sds.workflowspec.WorkFlowSpecManager;

import com.cla.sds.changerequestspec.ChangeRequestSpecManager;

import com.cla.sds.pagecontentspec.PageContentSpecManager;

import com.cla.sds.mobileapp.MobileAppManager;

import com.cla.sds.page.PageManager;

import com.cla.sds.pagetype.PageTypeManager;

import com.cla.sds.slide.SlideManager;

import com.cla.sds.uiaction.UiActionManager;

import com.cla.sds.section.SectionManager;

import com.cla.sds.userdomain.UserDomainManager;

import com.cla.sds.userwhitelist.UserWhiteListManager;

import com.cla.sds.secuser.SecUserManager;

import com.cla.sds.userapp.UserAppManager;

import com.cla.sds.quicklink.QuickLinkManager;

import com.cla.sds.listaccess.ListAccessManager;

import com.cla.sds.loginhistory.LoginHistoryManager;

import com.cla.sds.candidatecontainer.CandidateContainerManager;

import com.cla.sds.candidateelement.CandidateElementManager;

import com.cla.sds.wechatworkappidentity.WechatWorkappIdentityManager;

import com.cla.sds.wechatminiappidentity.WechatMiniappIdentityManager;

import com.cla.sds.keypairidentity.KeypairIdentityManager;

import com.cla.sds.publickeytype.PublicKeyTypeManager;

import com.cla.sds.treenode.TreeNodeManager;


public class ManagerGroup {

	protected PlatformManager platformManager;

	protected CompanyManager companyManager;

	protected UserManager userManager;

	protected UserProjectManager userProjectManager;

	protected ChangeRequestTypeManager changeRequestTypeManager;

	protected ChangeRequestManager changeRequestManager;

	protected EventUpdateProfileManager eventUpdateProfileManager;

	protected ProjectManager projectManager;

	protected PageFlowSpecManager pageFlowSpecManager;

	protected WorkFlowSpecManager workFlowSpecManager;

	protected ChangeRequestSpecManager changeRequestSpecManager;

	protected PageContentSpecManager pageContentSpecManager;

	protected MobileAppManager mobileAppManager;

	protected PageManager pageManager;

	protected PageTypeManager pageTypeManager;

	protected SlideManager slideManager;

	protected UiActionManager uiActionManager;

	protected SectionManager sectionManager;

	protected UserDomainManager userDomainManager;

	protected UserWhiteListManager userWhiteListManager;

	protected SecUserManager secUserManager;

	protected UserAppManager userAppManager;

	protected QuickLinkManager quickLinkManager;

	protected ListAccessManager listAccessManager;

	protected LoginHistoryManager loginHistoryManager;

	protected CandidateContainerManager candidateContainerManager;

	protected CandidateElementManager candidateElementManager;

	protected WechatWorkappIdentityManager wechatWorkappIdentityManager;

	protected WechatMiniappIdentityManager wechatMiniappIdentityManager;

	protected KeypairIdentityManager keypairIdentityManager;

	protected PublicKeyTypeManager publicKeyTypeManager;

	protected TreeNodeManager treeNodeManager;



	public PlatformManager getPlatformManager(){
		return this.platformManager;
	}
	public void setPlatformManager(PlatformManager manager){
		this.platformManager = manager;
	}


	public CompanyManager getCompanyManager(){
		return this.companyManager;
	}
	public void setCompanyManager(CompanyManager manager){
		this.companyManager = manager;
	}


	public UserManager getUserManager(){
		return this.userManager;
	}
	public void setUserManager(UserManager manager){
		this.userManager = manager;
	}


	public UserProjectManager getUserProjectManager(){
		return this.userProjectManager;
	}
	public void setUserProjectManager(UserProjectManager manager){
		this.userProjectManager = manager;
	}


	public ChangeRequestTypeManager getChangeRequestTypeManager(){
		return this.changeRequestTypeManager;
	}
	public void setChangeRequestTypeManager(ChangeRequestTypeManager manager){
		this.changeRequestTypeManager = manager;
	}


	public ChangeRequestManager getChangeRequestManager(){
		return this.changeRequestManager;
	}
	public void setChangeRequestManager(ChangeRequestManager manager){
		this.changeRequestManager = manager;
	}


	public EventUpdateProfileManager getEventUpdateProfileManager(){
		return this.eventUpdateProfileManager;
	}
	public void setEventUpdateProfileManager(EventUpdateProfileManager manager){
		this.eventUpdateProfileManager = manager;
	}


	public ProjectManager getProjectManager(){
		return this.projectManager;
	}
	public void setProjectManager(ProjectManager manager){
		this.projectManager = manager;
	}


	public PageFlowSpecManager getPageFlowSpecManager(){
		return this.pageFlowSpecManager;
	}
	public void setPageFlowSpecManager(PageFlowSpecManager manager){
		this.pageFlowSpecManager = manager;
	}


	public WorkFlowSpecManager getWorkFlowSpecManager(){
		return this.workFlowSpecManager;
	}
	public void setWorkFlowSpecManager(WorkFlowSpecManager manager){
		this.workFlowSpecManager = manager;
	}


	public ChangeRequestSpecManager getChangeRequestSpecManager(){
		return this.changeRequestSpecManager;
	}
	public void setChangeRequestSpecManager(ChangeRequestSpecManager manager){
		this.changeRequestSpecManager = manager;
	}


	public PageContentSpecManager getPageContentSpecManager(){
		return this.pageContentSpecManager;
	}
	public void setPageContentSpecManager(PageContentSpecManager manager){
		this.pageContentSpecManager = manager;
	}


	public MobileAppManager getMobileAppManager(){
		return this.mobileAppManager;
	}
	public void setMobileAppManager(MobileAppManager manager){
		this.mobileAppManager = manager;
	}


	public PageManager getPageManager(){
		return this.pageManager;
	}
	public void setPageManager(PageManager manager){
		this.pageManager = manager;
	}


	public PageTypeManager getPageTypeManager(){
		return this.pageTypeManager;
	}
	public void setPageTypeManager(PageTypeManager manager){
		this.pageTypeManager = manager;
	}


	public SlideManager getSlideManager(){
		return this.slideManager;
	}
	public void setSlideManager(SlideManager manager){
		this.slideManager = manager;
	}


	public UiActionManager getUiActionManager(){
		return this.uiActionManager;
	}
	public void setUiActionManager(UiActionManager manager){
		this.uiActionManager = manager;
	}


	public SectionManager getSectionManager(){
		return this.sectionManager;
	}
	public void setSectionManager(SectionManager manager){
		this.sectionManager = manager;
	}


	public UserDomainManager getUserDomainManager(){
		return this.userDomainManager;
	}
	public void setUserDomainManager(UserDomainManager manager){
		this.userDomainManager = manager;
	}


	public UserWhiteListManager getUserWhiteListManager(){
		return this.userWhiteListManager;
	}
	public void setUserWhiteListManager(UserWhiteListManager manager){
		this.userWhiteListManager = manager;
	}


	public SecUserManager getSecUserManager(){
		return this.secUserManager;
	}
	public void setSecUserManager(SecUserManager manager){
		this.secUserManager = manager;
	}


	public UserAppManager getUserAppManager(){
		return this.userAppManager;
	}
	public void setUserAppManager(UserAppManager manager){
		this.userAppManager = manager;
	}


	public QuickLinkManager getQuickLinkManager(){
		return this.quickLinkManager;
	}
	public void setQuickLinkManager(QuickLinkManager manager){
		this.quickLinkManager = manager;
	}


	public ListAccessManager getListAccessManager(){
		return this.listAccessManager;
	}
	public void setListAccessManager(ListAccessManager manager){
		this.listAccessManager = manager;
	}


	public LoginHistoryManager getLoginHistoryManager(){
		return this.loginHistoryManager;
	}
	public void setLoginHistoryManager(LoginHistoryManager manager){
		this.loginHistoryManager = manager;
	}


	public CandidateContainerManager getCandidateContainerManager(){
		return this.candidateContainerManager;
	}
	public void setCandidateContainerManager(CandidateContainerManager manager){
		this.candidateContainerManager = manager;
	}


	public CandidateElementManager getCandidateElementManager(){
		return this.candidateElementManager;
	}
	public void setCandidateElementManager(CandidateElementManager manager){
		this.candidateElementManager = manager;
	}


	public WechatWorkappIdentityManager getWechatWorkappIdentityManager(){
		return this.wechatWorkappIdentityManager;
	}
	public void setWechatWorkappIdentityManager(WechatWorkappIdentityManager manager){
		this.wechatWorkappIdentityManager = manager;
	}


	public WechatMiniappIdentityManager getWechatMiniappIdentityManager(){
		return this.wechatMiniappIdentityManager;
	}
	public void setWechatMiniappIdentityManager(WechatMiniappIdentityManager manager){
		this.wechatMiniappIdentityManager = manager;
	}


	public KeypairIdentityManager getKeypairIdentityManager(){
		return this.keypairIdentityManager;
	}
	public void setKeypairIdentityManager(KeypairIdentityManager manager){
		this.keypairIdentityManager = manager;
	}


	public PublicKeyTypeManager getPublicKeyTypeManager(){
		return this.publicKeyTypeManager;
	}
	public void setPublicKeyTypeManager(PublicKeyTypeManager manager){
		this.publicKeyTypeManager = manager;
	}


	public TreeNodeManager getTreeNodeManager(){
		return this.treeNodeManager;
	}
	public void setTreeNodeManager(TreeNodeManager manager){
		this.treeNodeManager = manager;
	}


}
















