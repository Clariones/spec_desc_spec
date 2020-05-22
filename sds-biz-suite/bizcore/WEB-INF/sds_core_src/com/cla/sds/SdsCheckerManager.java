package com.cla.sds;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.terapico.caf.baseelement.CandidateQuery;
import com.terapico.uccaf.BaseUserContext;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class SdsCheckerManager extends BaseManagerImpl {
	public SmartList<BaseEntity> requestCandidateValuesForSearch(SdsUserContext ctx, String ownerMemberName,
			String ownerId, String resultMemberName, String resutlClassName, String targetClassName, String filterKey, int pageNo) {
		return ((BaseDAO)daoOf(ctx)).requestCandidateValuesForSearch(ownerMemberName, ownerId, resultMemberName,
				resutlClassName, targetClassName, filterKey, pageNo);
	}
	
	protected Object daoOf(SdsUserContext ctx) {
		throw new UnsupportedOperationException("You must implement it in your specific Manager implementation");
	}
	
	
	public Object queryCandidates(SdsUserContext userContext, CandidateQuery query) throws Exception {
		return new CandidatesUtil().queryCandidates(userContext, query);
	}
	
	public Object queryCandidatesForAssign(SdsUserContext userContext, CandidateQuery query) throws Exception {
		return new CandidatesUtil().queryCandidatesForAssign(userContext, query);
	}

	public Object queryCandidatesForSearch(SdsUserContext userContext, CandidateQuery query) throws Exception {
		return new CandidatesUtil().queryCandidatesForSearch(userContext, query);
	}
	
	protected SdsObjectChecker checkerOf(SdsUserContext ctx) {
		return ctx.getChecker();
	}
	private static class AsyncManagerJob extends Thread {
		protected Object me;
		protected Object proxy;
		protected Method method;
		protected Object[] args;
		protected MethodProxy methodProxy;

		public AsyncManagerJob(Object me, Object proxy, Method method, Object[] args, MethodProxy methodProxy) {
			super();
			this.me = me;
			this.proxy = proxy;
			this.method = method;
			this.args = args;
			this.methodProxy = methodProxy;
		}

		@Override
		public void run() {
			try {
				method.setAccessible(true);
				method.invoke(me, args);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final Map<String, Object> EO = new HashMap<>();
	protected Object asyncProxy = null;
	protected Object getAsyncProxy() {
		if (asyncProxy != null) {
			return asyncProxy;
		}
		
		Object me = this;
		MethodInterceptor proxy = new MethodInterceptor() {

			@Override
			public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				new AsyncManagerJob(me, proxyObj, method, args, methodProxy).start();
				return null;
			}
		};
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(me.getClass());
		enhancer.setCallback(proxy);
		return asyncProxy = enhancer.create();
	}
	
	protected void cacheVerifyCode(SdsUserContext ctx, String mobile, String verifyCode) {
		String cacheKey = String.format("vcode:%s:%s", mobile, "login");
		ctx.putToCache(cacheKey, verifyCode, SdsBaseConstants.DEFAULT_CACHE_TIME_FOR_VCODE);
	}

	protected String getVerifyCodeFromCache(SdsUserContext ctx, String mobile) {
		String cacheKey = String.format("vcode:%s:%s", mobile, "login");
		return (String) ctx.getCachedObject(cacheKey, String.class);
	}
	protected void checkVerifyCode(SdsUserContext ctx, String inputVerifyCode, String mobile) throws Exception {
		String cachedVerifyCode = getVerifyCodeFromCache(ctx, mobile);
		if (cachedVerifyCode == null) {
			throw new Exception("请先获取验证码");
		}
		if (!cachedVerifyCode.equals(inputVerifyCode)) {
			throw new Exception("验证码不正确");
		}
	}
	

	
	
	
	@Override
	protected<T extends BaseEntity> void addActions(BaseUserContext baseUserContext, T entity,
			Map<String, Object> tokens){
		
		entity.addAction(createAction("@view","view","view/"+entity.getId()+"/","main","primary"));
		this.requestTypeActions(baseUserContext, entity);
	}
	
	protected void convertToActions(BaseEntity targetObject,com.cla.sds.changerequesttype.ChangeRequestType changeRequestType) {
		
		
		String []bindTypes = changeRequestType.getBindTypes().split(",");
		
		boolean matchType = Arrays.stream(bindTypes).anyMatch(type->targetObject.getInternalType().equals(type.trim()));
		if(!matchType) {
			return ;
		}
		
		
		Action action = new Action();
		
		action.asChangeRequestGroup();
		action.setActionName(changeRequestType.getName());
		action.setActionKey(changeRequestType.getCode());
		action.setActionIcon(changeRequestType.getIcon());
		
		String actionPath = String.format("/%s/%s/%s/%s/%s",
				targetObject.getInternalType(),
				targetObject.getId(),
				changeRequestType.getInternalType(),
				changeRequestType.getCode(),
				changeRequestType.getName()
				);
		
		action.setActionPath(actionPath);
		targetObject.addAction(action);
		
		
		
		
	}
	protected void requestTypeActions(BaseUserContext userContext, BaseEntity targetObject) {
		
		changeRequestTypeDaoOf((SdsUserContext)userContext)
			.loadAll()
			.forEach(crType->convertToActions(targetObject,crType));
	}


	public com.cla.sds.platform.PlatformManager platformManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getPlatformManager();
	}
	public com.cla.sds.platform.PlatformDAO platformDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getPlatformDAO();
	}
	public com.cla.sds.company.CompanyManager companyManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getCompanyManager();
	}
	public com.cla.sds.company.CompanyDAO companyDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getCompanyDAO();
	}
	public com.cla.sds.user.UserManager userManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getUserManager();
	}
	public com.cla.sds.user.UserDAO userDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getUserDAO();
	}
	public com.cla.sds.userproject.UserProjectManager userProjectManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getUserProjectManager();
	}
	public com.cla.sds.userproject.UserProjectDAO userProjectDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getUserProjectDAO();
	}
	public com.cla.sds.changerequesttype.ChangeRequestTypeManager changeRequestTypeManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getChangeRequestTypeManager();
	}
	public com.cla.sds.changerequesttype.ChangeRequestTypeDAO changeRequestTypeDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getChangeRequestTypeDAO();
	}
	public com.cla.sds.changerequest.ChangeRequestManager changeRequestManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getChangeRequestManager();
	}
	public com.cla.sds.changerequest.ChangeRequestDAO changeRequestDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getChangeRequestDAO();
	}
	public com.cla.sds.eventupdateprofile.EventUpdateProfileManager eventUpdateProfileManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getEventUpdateProfileManager();
	}
	public com.cla.sds.eventupdateprofile.EventUpdateProfileDAO eventUpdateProfileDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getEventUpdateProfileDAO();
	}
	public com.cla.sds.project.ProjectManager projectManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getProjectManager();
	}
	public com.cla.sds.project.ProjectDAO projectDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getProjectDAO();
	}
	public com.cla.sds.pageflowspec.PageFlowSpecManager pageFlowSpecManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getPageFlowSpecManager();
	}
	public com.cla.sds.pageflowspec.PageFlowSpecDAO pageFlowSpecDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getPageFlowSpecDAO();
	}
	public com.cla.sds.workflowspec.WorkFlowSpecManager workFlowSpecManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getWorkFlowSpecManager();
	}
	public com.cla.sds.workflowspec.WorkFlowSpecDAO workFlowSpecDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getWorkFlowSpecDAO();
	}
	public com.cla.sds.changerequestspec.ChangeRequestSpecManager changeRequestSpecManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getChangeRequestSpecManager();
	}
	public com.cla.sds.changerequestspec.ChangeRequestSpecDAO changeRequestSpecDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getChangeRequestSpecDAO();
	}
	public com.cla.sds.pagecontentspec.PageContentSpecManager pageContentSpecManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getPageContentSpecManager();
	}
	public com.cla.sds.pagecontentspec.PageContentSpecDAO pageContentSpecDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getPageContentSpecDAO();
	}
	public com.cla.sds.mobileapp.MobileAppManager mobileAppManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getMobileAppManager();
	}
	public com.cla.sds.mobileapp.MobileAppDAO mobileAppDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getMobileAppDAO();
	}
	public com.cla.sds.page.PageManager pageManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getPageManager();
	}
	public com.cla.sds.page.PageDAO pageDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getPageDAO();
	}
	public com.cla.sds.pagetype.PageTypeManager pageTypeManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getPageTypeManager();
	}
	public com.cla.sds.pagetype.PageTypeDAO pageTypeDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getPageTypeDAO();
	}
	public com.cla.sds.slide.SlideManager slideManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getSlideManager();
	}
	public com.cla.sds.slide.SlideDAO slideDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getSlideDAO();
	}
	public com.cla.sds.uiaction.UiActionManager uiActionManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getUiActionManager();
	}
	public com.cla.sds.uiaction.UiActionDAO uiActionDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getUiActionDAO();
	}
	public com.cla.sds.section.SectionManager sectionManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getSectionManager();
	}
	public com.cla.sds.section.SectionDAO sectionDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getSectionDAO();
	}
	public com.cla.sds.userdomain.UserDomainManager userDomainManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getUserDomainManager();
	}
	public com.cla.sds.userdomain.UserDomainDAO userDomainDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getUserDomainDAO();
	}
	public com.cla.sds.userwhitelist.UserWhiteListManager userWhiteListManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getUserWhiteListManager();
	}
	public com.cla.sds.userwhitelist.UserWhiteListDAO userWhiteListDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getUserWhiteListDAO();
	}
	public com.cla.sds.secuser.SecUserManager secUserManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getSecUserManager();
	}
	public com.cla.sds.secuser.SecUserDAO secUserDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getSecUserDAO();
	}
	public com.cla.sds.userapp.UserAppManager userAppManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getUserAppManager();
	}
	public com.cla.sds.userapp.UserAppDAO userAppDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getUserAppDAO();
	}
	public com.cla.sds.quicklink.QuickLinkManager quickLinkManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getQuickLinkManager();
	}
	public com.cla.sds.quicklink.QuickLinkDAO quickLinkDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getQuickLinkDAO();
	}
	public com.cla.sds.listaccess.ListAccessManager listAccessManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getListAccessManager();
	}
	public com.cla.sds.listaccess.ListAccessDAO listAccessDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getListAccessDAO();
	}
	public com.cla.sds.loginhistory.LoginHistoryManager loginHistoryManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getLoginHistoryManager();
	}
	public com.cla.sds.loginhistory.LoginHistoryDAO loginHistoryDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getLoginHistoryDAO();
	}
	public com.cla.sds.candidatecontainer.CandidateContainerManager candidateContainerManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getCandidateContainerManager();
	}
	public com.cla.sds.candidatecontainer.CandidateContainerDAO candidateContainerDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getCandidateContainerDAO();
	}
	public com.cla.sds.candidateelement.CandidateElementManager candidateElementManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getCandidateElementManager();
	}
	public com.cla.sds.candidateelement.CandidateElementDAO candidateElementDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getCandidateElementDAO();
	}
	public com.cla.sds.wechatworkappidentity.WechatWorkappIdentityManager wechatWorkappIdentityManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getWechatWorkappIdentityManager();
	}
	public com.cla.sds.wechatworkappidentity.WechatWorkappIdentityDAO wechatWorkappIdentityDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getWechatWorkappIdentityDAO();
	}
	public com.cla.sds.wechatminiappidentity.WechatMiniappIdentityManager wechatMiniappIdentityManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getWechatMiniappIdentityManager();
	}
	public com.cla.sds.wechatminiappidentity.WechatMiniappIdentityDAO wechatMiniappIdentityDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getWechatMiniappIdentityDAO();
	}
	public com.cla.sds.keypairidentity.KeypairIdentityManager keypairIdentityManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getKeypairIdentityManager();
	}
	public com.cla.sds.keypairidentity.KeypairIdentityDAO keypairIdentityDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getKeypairIdentityDAO();
	}
	public com.cla.sds.publickeytype.PublicKeyTypeManager publicKeyTypeManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getPublicKeyTypeManager();
	}
	public com.cla.sds.publickeytype.PublicKeyTypeDAO publicKeyTypeDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getPublicKeyTypeDAO();
	}
	public com.cla.sds.treenode.TreeNodeManager treeNodeManagerOf(SdsUserContext userContext){
		return userContext.getManagerGroup().getTreeNodeManager();
	}
	public com.cla.sds.treenode.TreeNodeDAO treeNodeDaoOf(SdsUserContext userContext){
		return userContext.getDAOGroup().getTreeNodeDAO();
	}
	
	
	
	

	protected void checkGender(String gender, int i, int j,String targetFieldName, List<Message> messageList) {
		
		
	}
	
	//for stub only
	protected void checkDateNow(Date likeTime, int i, Object now,
			String targetFieldName, SdsException exception) {
		
		
	}


	protected Object now() {

		return null;
	}
	
	protected boolean isValidIdentifier(String value){
		return hasVisualChar(value);
		
	}
	
	protected boolean hasVisualChar(String value){
		if(value==null){
			return false;
		}
		if(value.isEmpty()){
			return false;
		}
		if(value.trim().isEmpty()){
			return false;
		}
		return true;
		
	}
	protected void checkBigDecimalRange(BigDecimal projectArea, double i, double j, String projectAreaOfProject,
			List<Message> messageList) {
		
	}
    
}


















