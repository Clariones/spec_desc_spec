package com.cla.sds;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import com.cla.sds.platform.Platform;
import com.cla.sds.platform.PlatformDAO;
import com.cla.sds.platform.PlatformTokens;
import com.cla.sds.company.Company;
import com.cla.sds.company.CompanyDAO;
import com.cla.sds.company.CompanyTokens;
import com.cla.sds.user.User;
import com.cla.sds.user.UserDAO;
import com.cla.sds.user.UserTokens;
import com.cla.sds.userproject.UserProject;
import com.cla.sds.userproject.UserProjectDAO;
import com.cla.sds.userproject.UserProjectTokens;
import com.cla.sds.changerequesttype.ChangeRequestType;
import com.cla.sds.changerequesttype.ChangeRequestTypeDAO;
import com.cla.sds.changerequesttype.ChangeRequestTypeTokens;
import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.changerequest.ChangeRequestDAO;
import com.cla.sds.changerequest.ChangeRequestTokens;
import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.eventupdateprofile.EventUpdateProfileDAO;
import com.cla.sds.eventupdateprofile.EventUpdateProfileTokens;
import com.cla.sds.project.Project;
import com.cla.sds.project.ProjectDAO;
import com.cla.sds.project.ProjectTokens;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.pageflowspec.PageFlowSpecDAO;
import com.cla.sds.pageflowspec.PageFlowSpecTokens;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.cla.sds.workflowspec.WorkFlowSpecDAO;
import com.cla.sds.workflowspec.WorkFlowSpecTokens;
import com.cla.sds.changerequestspec.ChangeRequestSpec;
import com.cla.sds.changerequestspec.ChangeRequestSpecDAO;
import com.cla.sds.changerequestspec.ChangeRequestSpecTokens;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.pagecontentspec.PageContentSpecDAO;
import com.cla.sds.pagecontentspec.PageContentSpecTokens;
import com.cla.sds.mobileapp.MobileApp;
import com.cla.sds.mobileapp.MobileAppDAO;
import com.cla.sds.mobileapp.MobileAppTokens;
import com.cla.sds.page.Page;
import com.cla.sds.page.PageDAO;
import com.cla.sds.page.PageTokens;
import com.cla.sds.pagetype.PageType;
import com.cla.sds.pagetype.PageTypeDAO;
import com.cla.sds.pagetype.PageTypeTokens;
import com.cla.sds.slide.Slide;
import com.cla.sds.slide.SlideDAO;
import com.cla.sds.slide.SlideTokens;
import com.cla.sds.uiaction.UiAction;
import com.cla.sds.uiaction.UiActionDAO;
import com.cla.sds.uiaction.UiActionTokens;
import com.cla.sds.section.Section;
import com.cla.sds.section.SectionDAO;
import com.cla.sds.section.SectionTokens;
import com.cla.sds.userdomain.UserDomain;
import com.cla.sds.userdomain.UserDomainDAO;
import com.cla.sds.userdomain.UserDomainTokens;
import com.cla.sds.userwhitelist.UserWhiteList;
import com.cla.sds.userwhitelist.UserWhiteListDAO;
import com.cla.sds.userwhitelist.UserWhiteListTokens;
import com.cla.sds.secuser.SecUser;
import com.cla.sds.secuser.SecUserDAO;
import com.cla.sds.secuser.SecUserTokens;
import com.cla.sds.userapp.UserApp;
import com.cla.sds.userapp.UserAppDAO;
import com.cla.sds.userapp.UserAppTokens;
import com.cla.sds.quicklink.QuickLink;
import com.cla.sds.quicklink.QuickLinkDAO;
import com.cla.sds.quicklink.QuickLinkTokens;
import com.cla.sds.listaccess.ListAccess;
import com.cla.sds.listaccess.ListAccessDAO;
import com.cla.sds.listaccess.ListAccessTokens;
import com.cla.sds.loginhistory.LoginHistory;
import com.cla.sds.loginhistory.LoginHistoryDAO;
import com.cla.sds.loginhistory.LoginHistoryTokens;
import com.cla.sds.candidatecontainer.CandidateContainer;
import com.cla.sds.candidatecontainer.CandidateContainerDAO;
import com.cla.sds.candidatecontainer.CandidateContainerTokens;
import com.cla.sds.candidateelement.CandidateElement;
import com.cla.sds.candidateelement.CandidateElementDAO;
import com.cla.sds.candidateelement.CandidateElementTokens;
import com.cla.sds.wechatworkappidentity.WechatWorkappIdentity;
import com.cla.sds.wechatworkappidentity.WechatWorkappIdentityDAO;
import com.cla.sds.wechatworkappidentity.WechatWorkappIdentityTokens;
import com.cla.sds.wechatminiappidentity.WechatMiniappIdentity;
import com.cla.sds.wechatminiappidentity.WechatMiniappIdentityDAO;
import com.cla.sds.wechatminiappidentity.WechatMiniappIdentityTokens;
import com.cla.sds.keypairidentity.KeypairIdentity;
import com.cla.sds.keypairidentity.KeypairIdentityDAO;
import com.cla.sds.keypairidentity.KeypairIdentityTokens;
import com.cla.sds.publickeytype.PublicKeyType;
import com.cla.sds.publickeytype.PublicKeyTypeDAO;
import com.cla.sds.publickeytype.PublicKeyTypeTokens;
import com.cla.sds.treenode.TreeNode;
import com.cla.sds.treenode.TreeNodeDAO;
import com.cla.sds.treenode.TreeNodeTokens;

public class DAOGroup {

	protected PlatformDAO platformDAO;

	protected CompanyDAO companyDAO;

	protected UserDAO userDAO;

	protected UserProjectDAO userProjectDAO;

	protected ChangeRequestTypeDAO changeRequestTypeDAO;

	protected ChangeRequestDAO changeRequestDAO;

	protected EventUpdateProfileDAO eventUpdateProfileDAO;

	protected ProjectDAO projectDAO;

	protected PageFlowSpecDAO pageFlowSpecDAO;

	protected WorkFlowSpecDAO workFlowSpecDAO;

	protected ChangeRequestSpecDAO changeRequestSpecDAO;

	protected PageContentSpecDAO pageContentSpecDAO;

	protected MobileAppDAO mobileAppDAO;

	protected PageDAO pageDAO;

	protected PageTypeDAO pageTypeDAO;

	protected SlideDAO slideDAO;

	protected UiActionDAO uiActionDAO;

	protected SectionDAO sectionDAO;

	protected UserDomainDAO userDomainDAO;

	protected UserWhiteListDAO userWhiteListDAO;

	protected SecUserDAO secUserDAO;

	protected UserAppDAO userAppDAO;

	protected QuickLinkDAO quickLinkDAO;

	protected ListAccessDAO listAccessDAO;

	protected LoginHistoryDAO loginHistoryDAO;

	protected CandidateContainerDAO candidateContainerDAO;

	protected CandidateElementDAO candidateElementDAO;

	protected WechatWorkappIdentityDAO wechatWorkappIdentityDAO;

	protected WechatMiniappIdentityDAO wechatMiniappIdentityDAO;

	protected KeypairIdentityDAO keypairIdentityDAO;

	protected PublicKeyTypeDAO publicKeyTypeDAO;

	protected TreeNodeDAO treeNodeDAO;



	public PlatformDAO getPlatformDAO(){
		return this.platformDAO;
	}
	public void setPlatformDAO(PlatformDAO dao){
		this.platformDAO = dao;
	}


	public CompanyDAO getCompanyDAO(){
		return this.companyDAO;
	}
	public void setCompanyDAO(CompanyDAO dao){
		this.companyDAO = dao;
	}


	public UserDAO getUserDAO(){
		return this.userDAO;
	}
	public void setUserDAO(UserDAO dao){
		this.userDAO = dao;
	}


	public UserProjectDAO getUserProjectDAO(){
		return this.userProjectDAO;
	}
	public void setUserProjectDAO(UserProjectDAO dao){
		this.userProjectDAO = dao;
	}


	public ChangeRequestTypeDAO getChangeRequestTypeDAO(){
		return this.changeRequestTypeDAO;
	}
	public void setChangeRequestTypeDAO(ChangeRequestTypeDAO dao){
		this.changeRequestTypeDAO = dao;
	}


	public ChangeRequestDAO getChangeRequestDAO(){
		return this.changeRequestDAO;
	}
	public void setChangeRequestDAO(ChangeRequestDAO dao){
		this.changeRequestDAO = dao;
	}


	public EventUpdateProfileDAO getEventUpdateProfileDAO(){
		return this.eventUpdateProfileDAO;
	}
	public void setEventUpdateProfileDAO(EventUpdateProfileDAO dao){
		this.eventUpdateProfileDAO = dao;
	}


	public ProjectDAO getProjectDAO(){
		return this.projectDAO;
	}
	public void setProjectDAO(ProjectDAO dao){
		this.projectDAO = dao;
	}


	public PageFlowSpecDAO getPageFlowSpecDAO(){
		return this.pageFlowSpecDAO;
	}
	public void setPageFlowSpecDAO(PageFlowSpecDAO dao){
		this.pageFlowSpecDAO = dao;
	}


	public WorkFlowSpecDAO getWorkFlowSpecDAO(){
		return this.workFlowSpecDAO;
	}
	public void setWorkFlowSpecDAO(WorkFlowSpecDAO dao){
		this.workFlowSpecDAO = dao;
	}


	public ChangeRequestSpecDAO getChangeRequestSpecDAO(){
		return this.changeRequestSpecDAO;
	}
	public void setChangeRequestSpecDAO(ChangeRequestSpecDAO dao){
		this.changeRequestSpecDAO = dao;
	}


	public PageContentSpecDAO getPageContentSpecDAO(){
		return this.pageContentSpecDAO;
	}
	public void setPageContentSpecDAO(PageContentSpecDAO dao){
		this.pageContentSpecDAO = dao;
	}


	public MobileAppDAO getMobileAppDAO(){
		return this.mobileAppDAO;
	}
	public void setMobileAppDAO(MobileAppDAO dao){
		this.mobileAppDAO = dao;
	}


	public PageDAO getPageDAO(){
		return this.pageDAO;
	}
	public void setPageDAO(PageDAO dao){
		this.pageDAO = dao;
	}


	public PageTypeDAO getPageTypeDAO(){
		return this.pageTypeDAO;
	}
	public void setPageTypeDAO(PageTypeDAO dao){
		this.pageTypeDAO = dao;
	}


	public SlideDAO getSlideDAO(){
		return this.slideDAO;
	}
	public void setSlideDAO(SlideDAO dao){
		this.slideDAO = dao;
	}


	public UiActionDAO getUiActionDAO(){
		return this.uiActionDAO;
	}
	public void setUiActionDAO(UiActionDAO dao){
		this.uiActionDAO = dao;
	}


	public SectionDAO getSectionDAO(){
		return this.sectionDAO;
	}
	public void setSectionDAO(SectionDAO dao){
		this.sectionDAO = dao;
	}


	public UserDomainDAO getUserDomainDAO(){
		return this.userDomainDAO;
	}
	public void setUserDomainDAO(UserDomainDAO dao){
		this.userDomainDAO = dao;
	}


	public UserWhiteListDAO getUserWhiteListDAO(){
		return this.userWhiteListDAO;
	}
	public void setUserWhiteListDAO(UserWhiteListDAO dao){
		this.userWhiteListDAO = dao;
	}


	public SecUserDAO getSecUserDAO(){
		return this.secUserDAO;
	}
	public void setSecUserDAO(SecUserDAO dao){
		this.secUserDAO = dao;
	}


	public UserAppDAO getUserAppDAO(){
		return this.userAppDAO;
	}
	public void setUserAppDAO(UserAppDAO dao){
		this.userAppDAO = dao;
	}


	public QuickLinkDAO getQuickLinkDAO(){
		return this.quickLinkDAO;
	}
	public void setQuickLinkDAO(QuickLinkDAO dao){
		this.quickLinkDAO = dao;
	}


	public ListAccessDAO getListAccessDAO(){
		return this.listAccessDAO;
	}
	public void setListAccessDAO(ListAccessDAO dao){
		this.listAccessDAO = dao;
	}


	public LoginHistoryDAO getLoginHistoryDAO(){
		return this.loginHistoryDAO;
	}
	public void setLoginHistoryDAO(LoginHistoryDAO dao){
		this.loginHistoryDAO = dao;
	}


	public CandidateContainerDAO getCandidateContainerDAO(){
		return this.candidateContainerDAO;
	}
	public void setCandidateContainerDAO(CandidateContainerDAO dao){
		this.candidateContainerDAO = dao;
	}


	public CandidateElementDAO getCandidateElementDAO(){
		return this.candidateElementDAO;
	}
	public void setCandidateElementDAO(CandidateElementDAO dao){
		this.candidateElementDAO = dao;
	}


	public WechatWorkappIdentityDAO getWechatWorkappIdentityDAO(){
		return this.wechatWorkappIdentityDAO;
	}
	public void setWechatWorkappIdentityDAO(WechatWorkappIdentityDAO dao){
		this.wechatWorkappIdentityDAO = dao;
	}


	public WechatMiniappIdentityDAO getWechatMiniappIdentityDAO(){
		return this.wechatMiniappIdentityDAO;
	}
	public void setWechatMiniappIdentityDAO(WechatMiniappIdentityDAO dao){
		this.wechatMiniappIdentityDAO = dao;
	}


	public KeypairIdentityDAO getKeypairIdentityDAO(){
		return this.keypairIdentityDAO;
	}
	public void setKeypairIdentityDAO(KeypairIdentityDAO dao){
		this.keypairIdentityDAO = dao;
	}


	public PublicKeyTypeDAO getPublicKeyTypeDAO(){
		return this.publicKeyTypeDAO;
	}
	public void setPublicKeyTypeDAO(PublicKeyTypeDAO dao){
		this.publicKeyTypeDAO = dao;
	}


	public TreeNodeDAO getTreeNodeDAO(){
		return this.treeNodeDAO;
	}
	public void setTreeNodeDAO(TreeNodeDAO dao){
		this.treeNodeDAO = dao;
	}


	private interface BasicLoader{
	    BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception;
	    void enhanceList(DAOGroup daoGoup, List list) throws Exception;
	    List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> list) throws Exception;
	    BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception;
	    BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception;
	    SmartList<? extends BaseEntity> queryList(DAOGroup daoGoup, String sql, Object... parmeters) throws Exception;
	}
	private static Map<String, BasicLoader> internalLoaderMap;
	static {
		internalLoaderMap = new HashMap<String, BasicLoader>();

		internalLoaderMap.put("Platform", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPlatformDAO().load(id, PlatformTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPlatformDAO().enhanceList((List<Platform>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPlatformDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPlatformDAO().present((Platform)data, tokens);
			}
			@Override
			public SmartList<Platform> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getPlatformDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)Platform.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("Company", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getCompanyDAO().load(id, CompanyTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getCompanyDAO().enhanceList((List<Company>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCompanyDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCompanyDAO().present((Company)data, tokens);
			}
			@Override
			public SmartList<Company> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getCompanyDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)Company.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("User", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserDAO().load(id, UserTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserDAO().enhanceList((List<User>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDAO().present((User)data, tokens);
			}
			@Override
			public SmartList<User> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getUserDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)User.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("UserProject", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserProjectDAO().load(id, UserProjectTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserProjectDAO().enhanceList((List<UserProject>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserProjectDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserProjectDAO().present((UserProject)data, tokens);
			}
			@Override
			public SmartList<UserProject> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getUserProjectDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)UserProject.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("ChangeRequestType", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().load(id, ChangeRequestTypeTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getChangeRequestTypeDAO().enhanceList((List<ChangeRequestType>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().present((ChangeRequestType)data, tokens);
			}
			@Override
			public SmartList<ChangeRequestType> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getChangeRequestTypeDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)ChangeRequestType.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("ChangeRequest", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getChangeRequestDAO().load(id, ChangeRequestTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getChangeRequestDAO().enhanceList((List<ChangeRequest>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestDAO().present((ChangeRequest)data, tokens);
			}
			@Override
			public SmartList<ChangeRequest> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getChangeRequestDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)ChangeRequest.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("EventUpdateProfile", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getEventUpdateProfileDAO().load(id, EventUpdateProfileTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getEventUpdateProfileDAO().enhanceList((List<EventUpdateProfile>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getEventUpdateProfileDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getEventUpdateProfileDAO().present((EventUpdateProfile)data, tokens);
			}
			@Override
			public SmartList<EventUpdateProfile> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getEventUpdateProfileDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)EventUpdateProfile.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("Project", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getProjectDAO().load(id, ProjectTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getProjectDAO().enhanceList((List<Project>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getProjectDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getProjectDAO().present((Project)data, tokens);
			}
			@Override
			public SmartList<Project> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getProjectDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)Project.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("PageFlowSpec", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPageFlowSpecDAO().load(id, PageFlowSpecTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPageFlowSpecDAO().enhanceList((List<PageFlowSpec>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageFlowSpecDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageFlowSpecDAO().present((PageFlowSpec)data, tokens);
			}
			@Override
			public SmartList<PageFlowSpec> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getPageFlowSpecDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)PageFlowSpec.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("WorkFlowSpec", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getWorkFlowSpecDAO().load(id, WorkFlowSpecTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getWorkFlowSpecDAO().enhanceList((List<WorkFlowSpec>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWorkFlowSpecDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWorkFlowSpecDAO().present((WorkFlowSpec)data, tokens);
			}
			@Override
			public SmartList<WorkFlowSpec> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getWorkFlowSpecDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)WorkFlowSpec.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("ChangeRequestSpec", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getChangeRequestSpecDAO().load(id, ChangeRequestSpecTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getChangeRequestSpecDAO().enhanceList((List<ChangeRequestSpec>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestSpecDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getChangeRequestSpecDAO().present((ChangeRequestSpec)data, tokens);
			}
			@Override
			public SmartList<ChangeRequestSpec> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getChangeRequestSpecDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)ChangeRequestSpec.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("PageContentSpec", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPageContentSpecDAO().load(id, PageContentSpecTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPageContentSpecDAO().enhanceList((List<PageContentSpec>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageContentSpecDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageContentSpecDAO().present((PageContentSpec)data, tokens);
			}
			@Override
			public SmartList<PageContentSpec> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getPageContentSpecDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)PageContentSpec.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("MobileApp", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getMobileAppDAO().load(id, MobileAppTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getMobileAppDAO().enhanceList((List<MobileApp>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getMobileAppDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getMobileAppDAO().present((MobileApp)data, tokens);
			}
			@Override
			public SmartList<MobileApp> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getMobileAppDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)MobileApp.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("Page", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPageDAO().load(id, PageTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPageDAO().enhanceList((List<Page>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageDAO().present((Page)data, tokens);
			}
			@Override
			public SmartList<Page> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getPageDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)Page.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("PageType", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPageTypeDAO().load(id, PageTypeTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPageTypeDAO().enhanceList((List<PageType>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageTypeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPageTypeDAO().present((PageType)data, tokens);
			}
			@Override
			public SmartList<PageType> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getPageTypeDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)PageType.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("Slide", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSlideDAO().load(id, SlideTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getSlideDAO().enhanceList((List<Slide>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSlideDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSlideDAO().present((Slide)data, tokens);
			}
			@Override
			public SmartList<Slide> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getSlideDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)Slide.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("UiAction", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUiActionDAO().load(id, UiActionTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUiActionDAO().enhanceList((List<UiAction>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUiActionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUiActionDAO().present((UiAction)data, tokens);
			}
			@Override
			public SmartList<UiAction> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getUiActionDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)UiAction.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("Section", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSectionDAO().load(id, SectionTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getSectionDAO().enhanceList((List<Section>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSectionDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSectionDAO().present((Section)data, tokens);
			}
			@Override
			public SmartList<Section> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getSectionDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)Section.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("UserDomain", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserDomainDAO().load(id, UserDomainTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserDomainDAO().enhanceList((List<UserDomain>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDomainDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserDomainDAO().present((UserDomain)data, tokens);
			}
			@Override
			public SmartList<UserDomain> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getUserDomainDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)UserDomain.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("UserWhiteList", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserWhiteListDAO().load(id, UserWhiteListTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserWhiteListDAO().enhanceList((List<UserWhiteList>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserWhiteListDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserWhiteListDAO().present((UserWhiteList)data, tokens);
			}
			@Override
			public SmartList<UserWhiteList> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getUserWhiteListDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)UserWhiteList.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("SecUser", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getSecUserDAO().load(id, SecUserTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getSecUserDAO().enhanceList((List<SecUser>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getSecUserDAO().present((SecUser)data, tokens);
			}
			@Override
			public SmartList<SecUser> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getSecUserDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)SecUser.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("UserApp", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getUserAppDAO().load(id, UserAppTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getUserAppDAO().enhanceList((List<UserApp>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserAppDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getUserAppDAO().present((UserApp)data, tokens);
			}
			@Override
			public SmartList<UserApp> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getUserAppDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)UserApp.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("QuickLink", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getQuickLinkDAO().load(id, QuickLinkTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getQuickLinkDAO().enhanceList((List<QuickLink>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuickLinkDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getQuickLinkDAO().present((QuickLink)data, tokens);
			}
			@Override
			public SmartList<QuickLink> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getQuickLinkDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)QuickLink.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("ListAccess", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getListAccessDAO().load(id, ListAccessTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getListAccessDAO().enhanceList((List<ListAccess>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getListAccessDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getListAccessDAO().present((ListAccess)data, tokens);
			}
			@Override
			public SmartList<ListAccess> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getListAccessDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)ListAccess.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("LoginHistory", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getLoginHistoryDAO().load(id, LoginHistoryTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getLoginHistoryDAO().enhanceList((List<LoginHistory>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLoginHistoryDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getLoginHistoryDAO().present((LoginHistory)data, tokens);
			}
			@Override
			public SmartList<LoginHistory> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getLoginHistoryDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)LoginHistory.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("CandidateContainer", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getCandidateContainerDAO().load(id, CandidateContainerTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getCandidateContainerDAO().enhanceList((List<CandidateContainer>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateContainerDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateContainerDAO().present((CandidateContainer)data, tokens);
			}
			@Override
			public SmartList<CandidateContainer> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getCandidateContainerDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)CandidateContainer.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("CandidateElement", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getCandidateElementDAO().load(id, CandidateElementTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getCandidateElementDAO().enhanceList((List<CandidateElement>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateElementDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getCandidateElementDAO().present((CandidateElement)data, tokens);
			}
			@Override
			public SmartList<CandidateElement> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getCandidateElementDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)CandidateElement.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("WechatWorkappIdentity", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getWechatWorkappIdentityDAO().load(id, WechatWorkappIdentityTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getWechatWorkappIdentityDAO().enhanceList((List<WechatWorkappIdentity>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatWorkappIdentityDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatWorkappIdentityDAO().present((WechatWorkappIdentity)data, tokens);
			}
			@Override
			public SmartList<WechatWorkappIdentity> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getWechatWorkappIdentityDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)WechatWorkappIdentity.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("WechatMiniappIdentity", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getWechatMiniappIdentityDAO().load(id, WechatMiniappIdentityTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getWechatMiniappIdentityDAO().enhanceList((List<WechatMiniappIdentity>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatMiniappIdentityDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getWechatMiniappIdentityDAO().present((WechatMiniappIdentity)data, tokens);
			}
			@Override
			public SmartList<WechatMiniappIdentity> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getWechatMiniappIdentityDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)WechatMiniappIdentity.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("KeypairIdentity", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getKeypairIdentityDAO().load(id, KeypairIdentityTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getKeypairIdentityDAO().enhanceList((List<KeypairIdentity>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getKeypairIdentityDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getKeypairIdentityDAO().present((KeypairIdentity)data, tokens);
			}
			@Override
			public SmartList<KeypairIdentity> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getKeypairIdentityDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)KeypairIdentity.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("PublicKeyType", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getPublicKeyTypeDAO().load(id, PublicKeyTypeTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getPublicKeyTypeDAO().enhanceList((List<PublicKeyType>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPublicKeyTypeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getPublicKeyTypeDAO().present((PublicKeyType)data, tokens);
			}
			@Override
			public SmartList<PublicKeyType> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getPublicKeyTypeDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)PublicKeyType.withId(id)).collect(Collectors.toList());
			}
		});

		internalLoaderMap.put("TreeNode", new BasicLoader() {
			@Override
			public BaseEntity loadBasicData(DAOGroup daoGoup, String id) throws Exception {
				return daoGoup.getTreeNodeDAO().load(id, TreeNodeTokens.withoutLists());
			}
			@Override
			public void enhanceList(DAOGroup daoGoup, List list) throws Exception {
				daoGoup.getTreeNodeDAO().enhanceList((List<TreeNode>)list);
			}
			@Override
			public BaseEntity loadBasicDataWithToken(DAOGroup daoGoup, String id, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTreeNodeDAO().load(id, tokens);
			}
			@Override
			public BaseEntity present(DAOGroup daoGoup, BaseEntity data, Map<String, Object> tokens) throws Exception {
				return daoGoup.getTreeNodeDAO().present((TreeNode)data, tokens);
			}
			@Override
			public SmartList<TreeNode> queryList(DAOGroup daoGoup, String sql, Object ... parmeters) throws Exception {
				return daoGoup.getTreeNodeDAO().queryList(sql, parmeters);
			}
      @Override
			public List<BaseEntity> wrapperList(DAOGroup daoGoup, List<String> ids) throws Exception{
				return ids.stream().map(id-> (BaseEntity)TreeNode.withId(id)).collect(Collectors.toList());
			}
		});

	}
	public BaseEntity loadBasicData(String type, String id){
	    BasicLoader loader = internalLoaderMap.get(type);
	    if (loader == null) {
	    	return null;
	    }
	    try{
	    	return loader.loadBasicData(this, id);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	public List<BaseEntity> wrapperList(String type, List<String> ids){
  	    BasicLoader loader = internalLoaderMap.get(type);
  	    if (loader == null) {
  	    	return null;
  	    }
  	    try{
  	    	return loader.wrapperList(this, ids);
  	    }catch(Exception e) {
  	    	e.printStackTrace();
  	    	return null;
  	    }
  	}

	public BaseEntity loadBasicDataWithTokens(String type, String id, Map<String, Object> tokens){
	    BasicLoader loader = internalLoaderMap.get(type);
	    if (loader == null) {
	    	return null;
	    }
	    try{
	    	return loader.loadBasicDataWithToken(this, id, tokens);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public BaseEntity present(BaseEntity data, Map<String, Object> tokens){
	    BasicLoader loader = internalLoaderMap.get(data.getInternalType());
	    if (loader == null || data == null) {
	    	return null;
	    }
	    try{
	    	return loader.present(this, data, tokens);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	public <T> void enhanceList(List list, Class<T> clazz) throws Exception{
	    BasicLoader loader = internalLoaderMap.get(clazz.getSimpleName());
	    if (loader == null) {
	    	return ;
	    }

	    loader.enhanceList(this, list);
	}

	public void enhanceList(List list, String type) throws Exception{
  	    BasicLoader loader = internalLoaderMap.get(type);
  	    if (loader == null) {
  	    	return ;
  	    }

  	    loader.enhanceList(this, list);
  	}
  	
  	public SmartList<? extends BaseEntity> queryList(String type, String sql, Object ... parameters) throws Exception{
  	    BasicLoader loader = internalLoaderMap.get(type);
  	    if (loader == null) {
  	    	return new SmartList();
  	    }

  	    return loader.queryList(this, sql, parameters);
  	}
}

