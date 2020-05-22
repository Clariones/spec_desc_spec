package com.cla.sds;


import com.terapico.caf.viewpage.SerializeScope;

import com.cla.sds.platform.Platform;
import com.cla.sds.company.Company;
import com.cla.sds.user.User;
import com.cla.sds.userproject.UserProject;
import com.cla.sds.changerequesttype.ChangeRequestType;
import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.project.Project;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.cla.sds.changerequestspec.ChangeRequestSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.mobileapp.MobileApp;
import com.cla.sds.page.Page;
import com.cla.sds.pagetype.PageType;
import com.cla.sds.slide.Slide;
import com.cla.sds.uiaction.UiAction;
import com.cla.sds.section.Section;
import com.cla.sds.userdomain.UserDomain;
import com.cla.sds.userwhitelist.UserWhiteList;
import com.cla.sds.secuser.SecUser;
import com.cla.sds.userapp.UserApp;
import com.cla.sds.quicklink.QuickLink;
import com.cla.sds.listaccess.ListAccess;
import com.cla.sds.loginhistory.LoginHistory;
import com.cla.sds.candidatecontainer.CandidateContainer;
import com.cla.sds.candidateelement.CandidateElement;
import com.cla.sds.wechatworkappidentity.WechatWorkappIdentity;
import com.cla.sds.wechatminiappidentity.WechatMiniappIdentity;
import com.cla.sds.keypairidentity.KeypairIdentity;
import com.cla.sds.publickeytype.PublicKeyType;
import com.cla.sds.treenode.TreeNode;


public class SdsBaseViewScope {

	protected static SerializeScope PlatformBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.CREATE_TIME_PROPERTY)
		.field(Platform.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于Platform的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformSummaryScope() {
		return PlatformBaseSummaryScope;
	}

	protected static SerializeScope CompanyBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Company.ID_PROPERTY)
		.field(Company.NAME_PROPERTY)
		.field(Company.CREATE_TIME_PROPERTY)
		;
	/** 用于Company的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCompanySummaryScope() {
		return CompanyBaseSummaryScope;
	}

	protected static SerializeScope UserBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(User.ID_PROPERTY)
		.field(User.NAME_PROPERTY)
		.field(User.JOIN_TIME_PROPERTY)
		.field(User.TITLE_PROPERTY)
		;
	/** 用于User的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserSummaryScope() {
		return UserBaseSummaryScope;
	}

	protected static SerializeScope UserProjectBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserProject.ID_PROPERTY)
		.field(UserProject.CREATE_TIME_PROPERTY)
		.field(UserProject.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于UserProject的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserProjectSummaryScope() {
		return UserProjectBaseSummaryScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		;
	/** 用于ChangeRequestType的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeSummaryScope() {
		return ChangeRequestTypeBaseSummaryScope;
	}

	protected static SerializeScope ChangeRequestBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		.field(ChangeRequest.COMMITED_PROPERTY)
		;
	/** 用于ChangeRequest的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSummaryScope() {
		return ChangeRequestBaseSummaryScope;
	}

	protected static SerializeScope EventUpdateProfileBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(EventUpdateProfile.ID_PROPERTY)
		.field(EventUpdateProfile.NAME_PROPERTY)
		.field(EventUpdateProfile.AVANTAR_PROPERTY)
		.field(EventUpdateProfile.FIELD_GROUP_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY)
		;
	/** 用于EventUpdateProfile的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getEventUpdateProfileSummaryScope() {
		return EventUpdateProfileBaseSummaryScope;
	}

	protected static SerializeScope ProjectBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Project.ID_PROPERTY)
		.field(Project.NAME_PROPERTY)
		;
	/** 用于Project的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getProjectSummaryScope() {
		return ProjectBaseSummaryScope;
	}

	protected static SerializeScope PageFlowSpecBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageFlowSpec.ID_PROPERTY)
		.field(PageFlowSpec.SCENE_PROPERTY)
		.field(PageFlowSpec.BRIEF_PROPERTY)
		;
	/** 用于PageFlowSpec的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageFlowSpecSummaryScope() {
		return PageFlowSpecBaseSummaryScope;
	}

	protected static SerializeScope WorkFlowSpecBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WorkFlowSpec.ID_PROPERTY)
		.field(WorkFlowSpec.SCENE_PROPERTY)
		.field(WorkFlowSpec.BRIEF_PROPERTY)
		;
	/** 用于WorkFlowSpec的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWorkFlowSpecSummaryScope() {
		return WorkFlowSpecBaseSummaryScope;
	}

	protected static SerializeScope ChangeRequestSpecBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestSpec.ID_PROPERTY)
		.field(ChangeRequestSpec.SCENE_PROPERTY)
		.field(ChangeRequestSpec.BRIEF_PROPERTY)
		;
	/** 用于ChangeRequestSpec的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSpecSummaryScope() {
		return ChangeRequestSpecBaseSummaryScope;
	}

	protected static SerializeScope PageContentSpecBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageContentSpec.ID_PROPERTY)
		.field(PageContentSpec.SCENE_PROPERTY)
		.field(PageContentSpec.BRIEF_PROPERTY)
		;
	/** 用于PageContentSpec的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageContentSpecSummaryScope() {
		return PageContentSpecBaseSummaryScope;
	}

	protected static SerializeScope MobileAppBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(MobileApp.ID_PROPERTY)
		.field(MobileApp.NAME_PROPERTY)
		;
	/** 用于MobileApp的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getMobileAppSummaryScope() {
		return MobileAppBaseSummaryScope;
	}

	protected static SerializeScope PageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Page.ID_PROPERTY)
		.field(Page.PAGE_TITLE_PROPERTY)
		.field(Page.LINK_TO_URL_PROPERTY)
		.field(Page.DISPLAY_ORDER_PROPERTY)
		;
	/** 用于Page的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageSummaryScope() {
		return PageBaseSummaryScope;
	}

	protected static SerializeScope PageTypeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageType.ID_PROPERTY)
		.field(PageType.NAME_PROPERTY)
		.field(PageType.CODE_PROPERTY)
		.field(PageType.FOOTER_TAB_PROPERTY)
		;
	/** 用于PageType的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageTypeSummaryScope() {
		return PageTypeBaseSummaryScope;
	}

	protected static SerializeScope SlideBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Slide.ID_PROPERTY)
		.field(Slide.NAME_PROPERTY)
		.field(Slide.DISPLAY_ORDER_PROPERTY)
		.field(Slide.IMAGE_URL_PROPERTY)
		.field(Slide.VIDEO_URL_PROPERTY)
		.field(Slide.LINK_TO_URL_PROPERTY)
		;
	/** 用于Slide的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSlideSummaryScope() {
		return SlideBaseSummaryScope;
	}

	protected static SerializeScope UiActionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UiAction.ID_PROPERTY)
		.field(UiAction.CODE_PROPERTY)
		.field(UiAction.ICON_PROPERTY)
		.field(UiAction.TITLE_PROPERTY)
		.field(UiAction.DISPLAY_ORDER_PROPERTY)
		.field(UiAction.BRIEF_PROPERTY)
		.field(UiAction.IMAGE_URL_PROPERTY)
		.field(UiAction.LINK_TO_URL_PROPERTY)
		.field(UiAction.EXTRA_DATA_PROPERTY)
		;
	/** 用于UiAction的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUiActionSummaryScope() {
		return UiActionBaseSummaryScope;
	}

	protected static SerializeScope SectionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Section.ID_PROPERTY)
		.field(Section.TITLE_PROPERTY)
		.field(Section.BRIEF_PROPERTY)
		.field(Section.ICON_PROPERTY)
		.field(Section.DISPLAY_ORDER_PROPERTY)
		.field(Section.VIEW_GROUP_PROPERTY)
		.field(Section.LINK_TO_URL_PROPERTY)
		;
	/** 用于Section的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSectionSummaryScope() {
		return SectionBaseSummaryScope;
	}

	protected static SerializeScope UserDomainBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSummaryScope() {
		return UserDomainBaseSummaryScope;
	}

	protected static SerializeScope UserWhiteListBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSummaryScope() {
		return UserWhiteListBaseSummaryScope;
	}

	protected static SerializeScope SecUserBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于SecUser的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserSummaryScope() {
		return SecUserBaseSummaryScope;
	}

	protected static SerializeScope UserAppBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppSummaryScope() {
		return UserAppBaseSummaryScope;
	}

	protected static SerializeScope QuickLinkBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		;
	/** 用于QuickLink的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkSummaryScope() {
		return QuickLinkBaseSummaryScope;
	}

	protected static SerializeScope ListAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessSummaryScope() {
		return ListAccessBaseSummaryScope;
	}

	protected static SerializeScope LoginHistoryBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySummaryScope() {
		return LoginHistoryBaseSummaryScope;
	}

	protected static SerializeScope CandidateContainerBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		;
	/** 用于CandidateContainer的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerSummaryScope() {
		return CandidateContainerBaseSummaryScope;
	}

	protected static SerializeScope CandidateElementBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		;
	/** 用于CandidateElement的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementSummaryScope() {
		return CandidateElementBaseSummaryScope;
	}

	protected static SerializeScope WechatWorkappIdentityBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentity.ID_PROPERTY)
		.field(WechatWorkappIdentity.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentity.USER_ID_PROPERTY)
		.field(WechatWorkappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentity的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentitySummaryScope() {
		return WechatWorkappIdentityBaseSummaryScope;
	}

	protected static SerializeScope WechatMiniappIdentityBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentity.ID_PROPERTY)
		.field(WechatMiniappIdentity.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentity.APP_ID_PROPERTY)
		.field(WechatMiniappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentity的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentitySummaryScope() {
		return WechatMiniappIdentityBaseSummaryScope;
	}

	protected static SerializeScope KeypairIdentityBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(KeypairIdentity.ID_PROPERTY)
		.field(KeypairIdentity.PUBLIC_KEY_PROPERTY)
		.field(KeypairIdentity.CREATE_TIME_PROPERTY)
		;
	/** 用于KeypairIdentity的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getKeypairIdentitySummaryScope() {
		return KeypairIdentityBaseSummaryScope;
	}

	protected static SerializeScope PublicKeyTypeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PublicKeyType.ID_PROPERTY)
		.field(PublicKeyType.NAME_PROPERTY)
		.field(PublicKeyType.CODE_PROPERTY)
		;
	/** 用于PublicKeyType的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPublicKeyTypeSummaryScope() {
		return PublicKeyTypeBaseSummaryScope;
	}

	protected static SerializeScope TreeNodeBaseSummaryScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(TreeNode.ID_PROPERTY)
		.field(TreeNode.NODE_ID_PROPERTY)
		.field(TreeNode.NODE_TYPE_PROPERTY)
		.field(TreeNode.LEFT_VALUE_PROPERTY)
		.field(TreeNode.RIGHT_VALUE_PROPERTY)
		;
	/** 用于TreeNode的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getTreeNodeSummaryScope() {
		return TreeNodeBaseSummaryScope;
	}

	protected static SerializeScope PlatformBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.CREATE_TIME_PROPERTY)
		.field(Platform.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于Platform的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformSecondaryListItemScope() {
		return PlatformBaseSecondaryListItemScope;
	}

	protected static SerializeScope CompanyBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Company.ID_PROPERTY)
		.field(Company.NAME_PROPERTY)
		.field(Company.CREATE_TIME_PROPERTY)
		;
	/** 用于Company的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCompanySecondaryListItemScope() {
		return CompanyBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(User.ID_PROPERTY)
		.field(User.NAME_PROPERTY)
		.field(User.JOIN_TIME_PROPERTY)
		.field(User.TITLE_PROPERTY)
		;
	/** 用于User的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserSecondaryListItemScope() {
		return UserBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserProjectBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserProject.ID_PROPERTY)
		.field(UserProject.CREATE_TIME_PROPERTY)
		.field(UserProject.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于UserProject的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserProjectSecondaryListItemScope() {
		return UserProjectBaseSecondaryListItemScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		;
	/** 用于ChangeRequestType的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeSecondaryListItemScope() {
		return ChangeRequestTypeBaseSecondaryListItemScope;
	}

	protected static SerializeScope ChangeRequestBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		.field(ChangeRequest.COMMITED_PROPERTY)
		;
	/** 用于ChangeRequest的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSecondaryListItemScope() {
		return ChangeRequestBaseSecondaryListItemScope;
	}

	protected static SerializeScope EventUpdateProfileBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(EventUpdateProfile.ID_PROPERTY)
		.field(EventUpdateProfile.NAME_PROPERTY)
		.field(EventUpdateProfile.AVANTAR_PROPERTY)
		.field(EventUpdateProfile.FIELD_GROUP_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY)
		;
	/** 用于EventUpdateProfile的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getEventUpdateProfileSecondaryListItemScope() {
		return EventUpdateProfileBaseSecondaryListItemScope;
	}

	protected static SerializeScope ProjectBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Project.ID_PROPERTY)
		.field(Project.NAME_PROPERTY)
		;
	/** 用于Project的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getProjectSecondaryListItemScope() {
		return ProjectBaseSecondaryListItemScope;
	}

	protected static SerializeScope PageFlowSpecBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageFlowSpec.ID_PROPERTY)
		.field(PageFlowSpec.SCENE_PROPERTY)
		.field(PageFlowSpec.BRIEF_PROPERTY)
		;
	/** 用于PageFlowSpec的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageFlowSpecSecondaryListItemScope() {
		return PageFlowSpecBaseSecondaryListItemScope;
	}

	protected static SerializeScope WorkFlowSpecBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WorkFlowSpec.ID_PROPERTY)
		.field(WorkFlowSpec.SCENE_PROPERTY)
		.field(WorkFlowSpec.BRIEF_PROPERTY)
		;
	/** 用于WorkFlowSpec的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWorkFlowSpecSecondaryListItemScope() {
		return WorkFlowSpecBaseSecondaryListItemScope;
	}

	protected static SerializeScope ChangeRequestSpecBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestSpec.ID_PROPERTY)
		.field(ChangeRequestSpec.SCENE_PROPERTY)
		.field(ChangeRequestSpec.BRIEF_PROPERTY)
		;
	/** 用于ChangeRequestSpec的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSpecSecondaryListItemScope() {
		return ChangeRequestSpecBaseSecondaryListItemScope;
	}

	protected static SerializeScope PageContentSpecBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageContentSpec.ID_PROPERTY)
		.field(PageContentSpec.SCENE_PROPERTY)
		.field(PageContentSpec.BRIEF_PROPERTY)
		;
	/** 用于PageContentSpec的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageContentSpecSecondaryListItemScope() {
		return PageContentSpecBaseSecondaryListItemScope;
	}

	protected static SerializeScope MobileAppBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(MobileApp.ID_PROPERTY)
		.field(MobileApp.NAME_PROPERTY)
		;
	/** 用于MobileApp的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getMobileAppSecondaryListItemScope() {
		return MobileAppBaseSecondaryListItemScope;
	}

	protected static SerializeScope PageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Page.ID_PROPERTY)
		.field(Page.PAGE_TITLE_PROPERTY)
		.field(Page.LINK_TO_URL_PROPERTY)
		.field(Page.DISPLAY_ORDER_PROPERTY)
		;
	/** 用于Page的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageSecondaryListItemScope() {
		return PageBaseSecondaryListItemScope;
	}

	protected static SerializeScope PageTypeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageType.ID_PROPERTY)
		.field(PageType.NAME_PROPERTY)
		.field(PageType.CODE_PROPERTY)
		.field(PageType.FOOTER_TAB_PROPERTY)
		;
	/** 用于PageType的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageTypeSecondaryListItemScope() {
		return PageTypeBaseSecondaryListItemScope;
	}

	protected static SerializeScope SlideBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Slide.ID_PROPERTY)
		.field(Slide.NAME_PROPERTY)
		.field(Slide.DISPLAY_ORDER_PROPERTY)
		.field(Slide.IMAGE_URL_PROPERTY)
		.field(Slide.VIDEO_URL_PROPERTY)
		.field(Slide.LINK_TO_URL_PROPERTY)
		;
	/** 用于Slide的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSlideSecondaryListItemScope() {
		return SlideBaseSecondaryListItemScope;
	}

	protected static SerializeScope UiActionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UiAction.ID_PROPERTY)
		.field(UiAction.CODE_PROPERTY)
		.field(UiAction.ICON_PROPERTY)
		.field(UiAction.TITLE_PROPERTY)
		.field(UiAction.DISPLAY_ORDER_PROPERTY)
		.field(UiAction.BRIEF_PROPERTY)
		.field(UiAction.IMAGE_URL_PROPERTY)
		.field(UiAction.LINK_TO_URL_PROPERTY)
		.field(UiAction.EXTRA_DATA_PROPERTY)
		;
	/** 用于UiAction的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUiActionSecondaryListItemScope() {
		return UiActionBaseSecondaryListItemScope;
	}

	protected static SerializeScope SectionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Section.ID_PROPERTY)
		.field(Section.TITLE_PROPERTY)
		.field(Section.BRIEF_PROPERTY)
		.field(Section.ICON_PROPERTY)
		.field(Section.DISPLAY_ORDER_PROPERTY)
		.field(Section.VIEW_GROUP_PROPERTY)
		.field(Section.LINK_TO_URL_PROPERTY)
		;
	/** 用于Section的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSectionSecondaryListItemScope() {
		return SectionBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserDomainBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSecondaryListItemScope() {
		return UserDomainBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSecondaryListItemScope() {
		return UserWhiteListBaseSecondaryListItemScope;
	}

	protected static SerializeScope SecUserBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于SecUser的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserSecondaryListItemScope() {
		return SecUserBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserAppBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppSecondaryListItemScope() {
		return UserAppBaseSecondaryListItemScope;
	}

	protected static SerializeScope QuickLinkBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		;
	/** 用于QuickLink的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkSecondaryListItemScope() {
		return QuickLinkBaseSecondaryListItemScope;
	}

	protected static SerializeScope ListAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessSecondaryListItemScope() {
		return ListAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySecondaryListItemScope() {
		return LoginHistoryBaseSecondaryListItemScope;
	}

	protected static SerializeScope CandidateContainerBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		;
	/** 用于CandidateContainer的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerSecondaryListItemScope() {
		return CandidateContainerBaseSecondaryListItemScope;
	}

	protected static SerializeScope CandidateElementBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		;
	/** 用于CandidateElement的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementSecondaryListItemScope() {
		return CandidateElementBaseSecondaryListItemScope;
	}

	protected static SerializeScope WechatWorkappIdentityBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentity.ID_PROPERTY)
		.field(WechatWorkappIdentity.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentity.USER_ID_PROPERTY)
		.field(WechatWorkappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentity的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentitySecondaryListItemScope() {
		return WechatWorkappIdentityBaseSecondaryListItemScope;
	}

	protected static SerializeScope WechatMiniappIdentityBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentity.ID_PROPERTY)
		.field(WechatMiniappIdentity.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentity.APP_ID_PROPERTY)
		.field(WechatMiniappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentity的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentitySecondaryListItemScope() {
		return WechatMiniappIdentityBaseSecondaryListItemScope;
	}

	protected static SerializeScope KeypairIdentityBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(KeypairIdentity.ID_PROPERTY)
		.field(KeypairIdentity.PUBLIC_KEY_PROPERTY)
		.field(KeypairIdentity.CREATE_TIME_PROPERTY)
		;
	/** 用于KeypairIdentity的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getKeypairIdentitySecondaryListItemScope() {
		return KeypairIdentityBaseSecondaryListItemScope;
	}

	protected static SerializeScope PublicKeyTypeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PublicKeyType.ID_PROPERTY)
		.field(PublicKeyType.NAME_PROPERTY)
		.field(PublicKeyType.CODE_PROPERTY)
		;
	/** 用于PublicKeyType的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPublicKeyTypeSecondaryListItemScope() {
		return PublicKeyTypeBaseSecondaryListItemScope;
	}

	protected static SerializeScope TreeNodeBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(TreeNode.ID_PROPERTY)
		.field(TreeNode.NODE_ID_PROPERTY)
		.field(TreeNode.NODE_TYPE_PROPERTY)
		.field(TreeNode.LEFT_VALUE_PROPERTY)
		.field(TreeNode.RIGHT_VALUE_PROPERTY)
		;
	/** 用于TreeNode的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getTreeNodeSecondaryListItemScope() {
		return TreeNodeBaseSecondaryListItemScope;
	}

	protected static SerializeScope PlatformBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.CREATE_TIME_PROPERTY)
		.field(Platform.LAST_UPDATE_TIME_PROPERTY)
		.field(Platform.COMPANY_LIST, getCompanySecondaryListItemScope())
		.field(Platform.CHANGE_REQUEST_TYPE_LIST, getChangeRequestTypeSecondaryListItemScope())
		.field(Platform.CHANGE_REQUEST_LIST, getChangeRequestSecondaryListItemScope())
		;
	/** 用于Platform对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformListItemScope() {
		return PlatformBaseListItemScope;
	}

	protected static SerializeScope CompanyBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Company.ID_PROPERTY)
		.field(Company.NAME_PROPERTY)
		.field(Company.CREATE_TIME_PROPERTY)
		.field(Company.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Company.USER_LIST, getUserSecondaryListItemScope())
		.field(Company.PROJECT_LIST, getProjectSecondaryListItemScope())
		;
	/** 用于Company对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCompanyListItemScope() {
		return CompanyBaseListItemScope;
	}

	protected static SerializeScope UserBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(User.ID_PROPERTY)
		.field(User.NAME_PROPERTY)
		.field(User.JOIN_TIME_PROPERTY)
		.field(User.COMPANY_PROPERTY, getCompanySummaryScope())
		.field(User.TITLE_PROPERTY)
		.field(User.USER_PROJECT_LIST, getUserProjectSecondaryListItemScope())
		.field(User.PAGE_FLOW_SPEC_LIST, getPageFlowSpecSecondaryListItemScope())
		.field(User.WORK_FLOW_SPEC_LIST, getWorkFlowSpecSecondaryListItemScope())
		.field(User.CHANGE_REQUEST_SPEC_LIST, getChangeRequestSpecSecondaryListItemScope())
		.field(User.PAGE_CONTENT_SPEC_LIST, getPageContentSpecSecondaryListItemScope())
		;
	/** 用于User对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserListItemScope() {
		return UserBaseListItemScope;
	}

	protected static SerializeScope UserProjectBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserProject.ID_PROPERTY)
		.field(UserProject.USER_PROPERTY, getUserSummaryScope())
		.field(UserProject.PROJECT_PROPERTY, getProjectSummaryScope())
		.field(UserProject.CREATE_TIME_PROPERTY)
		.field(UserProject.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于UserProject对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserProjectListItemScope() {
		return UserProjectBaseListItemScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		.field(ChangeRequestType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequestType.CHANGE_REQUEST_LIST, getChangeRequestSecondaryListItemScope())
		;
	/** 用于ChangeRequestType对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeListItemScope() {
		return ChangeRequestTypeBaseListItemScope;
	}

	protected static SerializeScope ChangeRequestBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		.field(ChangeRequest.REQUEST_TYPE_PROPERTY, getChangeRequestTypeSummaryScope())
		.field(ChangeRequest.COMMITED_PROPERTY)
		.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequest.EVENT_UPDATE_PROFILE_LIST, getEventUpdateProfileSecondaryListItemScope())
		;
	/** 用于ChangeRequest对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestListItemScope() {
		return ChangeRequestBaseListItemScope;
	}

	protected static SerializeScope EventUpdateProfileBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(EventUpdateProfile.ID_PROPERTY)
		.field(EventUpdateProfile.NAME_PROPERTY)
		.field(EventUpdateProfile.AVANTAR_PROPERTY)
		.field(EventUpdateProfile.FIELD_GROUP_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY)
		.field(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		;
	/** 用于EventUpdateProfile对象的列表时需要序列化的属性列表 */
	public static SerializeScope getEventUpdateProfileListItemScope() {
		return EventUpdateProfileBaseListItemScope;
	}

	protected static SerializeScope ProjectBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Project.ID_PROPERTY)
		.field(Project.NAME_PROPERTY)
		.field(Project.COMPANY_PROPERTY, getCompanySummaryScope())
		.field(Project.USER_PROJECT_LIST, getUserProjectSecondaryListItemScope())
		.field(Project.PAGE_FLOW_SPEC_LIST, getPageFlowSpecSecondaryListItemScope())
		.field(Project.WORK_FLOW_SPEC_LIST, getWorkFlowSpecSecondaryListItemScope())
		.field(Project.CHANGE_REQUEST_SPEC_LIST, getChangeRequestSpecSecondaryListItemScope())
		.field(Project.PAGE_CONTENT_SPEC_LIST, getPageContentSpecSecondaryListItemScope())
		;
	/** 用于Project对象的列表时需要序列化的属性列表 */
	public static SerializeScope getProjectListItemScope() {
		return ProjectBaseListItemScope;
	}

	protected static SerializeScope PageFlowSpecBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageFlowSpec.ID_PROPERTY)
		.field(PageFlowSpec.SCENE_PROPERTY)
		.field(PageFlowSpec.BRIEF_PROPERTY)
		.field(PageFlowSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(PageFlowSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于PageFlowSpec对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageFlowSpecListItemScope() {
		return PageFlowSpecBaseListItemScope;
	}

	protected static SerializeScope WorkFlowSpecBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WorkFlowSpec.ID_PROPERTY)
		.field(WorkFlowSpec.SCENE_PROPERTY)
		.field(WorkFlowSpec.BRIEF_PROPERTY)
		.field(WorkFlowSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(WorkFlowSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于WorkFlowSpec对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWorkFlowSpecListItemScope() {
		return WorkFlowSpecBaseListItemScope;
	}

	protected static SerializeScope ChangeRequestSpecBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestSpec.ID_PROPERTY)
		.field(ChangeRequestSpec.SCENE_PROPERTY)
		.field(ChangeRequestSpec.BRIEF_PROPERTY)
		.field(ChangeRequestSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(ChangeRequestSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于ChangeRequestSpec对象的列表时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSpecListItemScope() {
		return ChangeRequestSpecBaseListItemScope;
	}

	protected static SerializeScope PageContentSpecBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageContentSpec.ID_PROPERTY)
		.field(PageContentSpec.SCENE_PROPERTY)
		.field(PageContentSpec.BRIEF_PROPERTY)
		.field(PageContentSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(PageContentSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于PageContentSpec对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageContentSpecListItemScope() {
		return PageContentSpecBaseListItemScope;
	}

	protected static SerializeScope MobileAppBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(MobileApp.ID_PROPERTY)
		.field(MobileApp.NAME_PROPERTY)
		.field(MobileApp.PAGE_LIST, getPageSecondaryListItemScope())
		.field(MobileApp.PAGE_TYPE_LIST, getPageTypeSecondaryListItemScope())
		;
	/** 用于MobileApp对象的列表时需要序列化的属性列表 */
	public static SerializeScope getMobileAppListItemScope() {
		return MobileAppBaseListItemScope;
	}

	protected static SerializeScope PageBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Page.ID_PROPERTY)
		.field(Page.PAGE_TITLE_PROPERTY)
		.field(Page.LINK_TO_URL_PROPERTY)
		.field(Page.PAGE_TYPE_PROPERTY, getPageTypeSummaryScope())
		.field(Page.DISPLAY_ORDER_PROPERTY)
		.field(Page.MOBILE_APP_PROPERTY, getMobileAppSummaryScope())
		.field(Page.SLIDE_LIST, getSlideSecondaryListItemScope())
		.field(Page.UI_ACTION_LIST, getUiActionSecondaryListItemScope())
		.field(Page.SECTION_LIST, getSectionSecondaryListItemScope())
		;
	/** 用于Page对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageListItemScope() {
		return PageBaseListItemScope;
	}

	protected static SerializeScope PageTypeBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageType.ID_PROPERTY)
		.field(PageType.NAME_PROPERTY)
		.field(PageType.CODE_PROPERTY)
		.field(PageType.MOBILE_APP_PROPERTY, getMobileAppSummaryScope())
		.field(PageType.FOOTER_TAB_PROPERTY)
		.field(PageType.PAGE_LIST, getPageSecondaryListItemScope())
		;
	/** 用于PageType对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPageTypeListItemScope() {
		return PageTypeBaseListItemScope;
	}

	protected static SerializeScope SlideBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Slide.ID_PROPERTY)
		.field(Slide.NAME_PROPERTY)
		.field(Slide.DISPLAY_ORDER_PROPERTY)
		.field(Slide.IMAGE_URL_PROPERTY)
		.field(Slide.VIDEO_URL_PROPERTY)
		.field(Slide.LINK_TO_URL_PROPERTY)
		.field(Slide.PAGE_PROPERTY, getPageSummaryScope())
		;
	/** 用于Slide对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSlideListItemScope() {
		return SlideBaseListItemScope;
	}

	protected static SerializeScope UiActionBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UiAction.ID_PROPERTY)
		.field(UiAction.CODE_PROPERTY)
		.field(UiAction.ICON_PROPERTY)
		.field(UiAction.TITLE_PROPERTY)
		.field(UiAction.DISPLAY_ORDER_PROPERTY)
		.field(UiAction.BRIEF_PROPERTY)
		.field(UiAction.IMAGE_URL_PROPERTY)
		.field(UiAction.LINK_TO_URL_PROPERTY)
		.field(UiAction.EXTRA_DATA_PROPERTY)
		.field(UiAction.PAGE_PROPERTY, getPageSummaryScope())
		;
	/** 用于UiAction对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUiActionListItemScope() {
		return UiActionBaseListItemScope;
	}

	protected static SerializeScope SectionBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Section.ID_PROPERTY)
		.field(Section.TITLE_PROPERTY)
		.field(Section.BRIEF_PROPERTY)
		.field(Section.ICON_PROPERTY)
		.field(Section.DISPLAY_ORDER_PROPERTY)
		.field(Section.VIEW_GROUP_PROPERTY)
		.field(Section.LINK_TO_URL_PROPERTY)
		.field(Section.PAGE_PROPERTY, getPageSummaryScope())
		;
	/** 用于Section对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSectionListItemScope() {
		return SectionBaseListItemScope;
	}

	protected static SerializeScope UserDomainBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListSecondaryListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserSecondaryListItemScope())
		.field(UserDomain.PUBLIC_KEY_TYPE_LIST, getPublicKeyTypeSecondaryListItemScope())
		;
	/** 用于UserDomain对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainListItemScope() {
		return UserDomainBaseListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListListItemScope() {
		return UserWhiteListBaseListItemScope;
	}

	protected static SerializeScope SecUserBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.USER_APP_LIST, getUserAppSecondaryListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistorySecondaryListItemScope())
		.field(SecUser.WECHAT_WORKAPP_IDENTITY_LIST, getWechatWorkappIdentitySecondaryListItemScope())
		.field(SecUser.WECHAT_MINIAPP_IDENTITY_LIST, getWechatMiniappIdentitySecondaryListItemScope())
		.field(SecUser.KEYPAIR_IDENTITY_LIST, getKeypairIdentitySecondaryListItemScope())
		;
	/** 用于SecUser对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserListItemScope() {
		return SecUserBaseListItemScope;
	}

	protected static SerializeScope UserAppBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.QUICK_LINK_LIST, getQuickLinkSecondaryListItemScope())
		.field(UserApp.LIST_ACCESS_LIST, getListAccessSecondaryListItemScope())
		;
	/** 用于UserApp对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppListItemScope() {
		return UserAppBaseListItemScope;
	}

	protected static SerializeScope QuickLinkBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		.field(QuickLink.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于QuickLink对象的列表时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkListItemScope() {
		return QuickLinkBaseListItemScope;
	}

	protected static SerializeScope ListAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessListItemScope() {
		return ListAccessBaseListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryListItemScope() {
		return LoginHistoryBaseListItemScope;
	}

	protected static SerializeScope CandidateContainerBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		.field(CandidateContainer.CANDIDATE_ELEMENT_LIST, getCandidateElementSecondaryListItemScope())
		;
	/** 用于CandidateContainer对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerListItemScope() {
		return CandidateContainerBaseListItemScope;
	}

	protected static SerializeScope CandidateElementBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		.field(CandidateElement.CONTAINER_PROPERTY, getCandidateContainerSummaryScope())
		;
	/** 用于CandidateElement对象的列表时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementListItemScope() {
		return CandidateElementBaseListItemScope;
	}

	protected static SerializeScope WechatWorkappIdentityBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentity.ID_PROPERTY)
		.field(WechatWorkappIdentity.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentity.USER_ID_PROPERTY)
		.field(WechatWorkappIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatWorkappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentity对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentityListItemScope() {
		return WechatWorkappIdentityBaseListItemScope;
	}

	protected static SerializeScope WechatMiniappIdentityBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentity.ID_PROPERTY)
		.field(WechatMiniappIdentity.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentity.APP_ID_PROPERTY)
		.field(WechatMiniappIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatMiniappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentity对象的列表时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentityListItemScope() {
		return WechatMiniappIdentityBaseListItemScope;
	}

	protected static SerializeScope KeypairIdentityBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(KeypairIdentity.ID_PROPERTY)
		.field(KeypairIdentity.PUBLIC_KEY_PROPERTY)
		.field(KeypairIdentity.KEY_TYPE_PROPERTY, getPublicKeyTypeSummaryScope())
		.field(KeypairIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(KeypairIdentity.CREATE_TIME_PROPERTY)
		;
	/** 用于KeypairIdentity对象的列表时需要序列化的属性列表 */
	public static SerializeScope getKeypairIdentityListItemScope() {
		return KeypairIdentityBaseListItemScope;
	}

	protected static SerializeScope PublicKeyTypeBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PublicKeyType.ID_PROPERTY)
		.field(PublicKeyType.NAME_PROPERTY)
		.field(PublicKeyType.CODE_PROPERTY)
		.field(PublicKeyType.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(PublicKeyType.KEYPAIR_IDENTITY_LIST, getKeypairIdentitySecondaryListItemScope())
		;
	/** 用于PublicKeyType对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPublicKeyTypeListItemScope() {
		return PublicKeyTypeBaseListItemScope;
	}

	protected static SerializeScope TreeNodeBaseListItemScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(TreeNode.ID_PROPERTY)
		.field(TreeNode.NODE_ID_PROPERTY)
		.field(TreeNode.NODE_TYPE_PROPERTY)
		.field(TreeNode.LEFT_VALUE_PROPERTY)
		.field(TreeNode.RIGHT_VALUE_PROPERTY)
		;
	/** 用于TreeNode对象的列表时需要序列化的属性列表 */
	public static SerializeScope getTreeNodeListItemScope() {
		return TreeNodeBaseListItemScope;
	}

	protected static SerializeScope PlatformBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.CREATE_TIME_PROPERTY)
		.field(Platform.LAST_UPDATE_TIME_PROPERTY)
		.field(Platform.COMPANY_LIST, getCompanyListItemScope())
		.field(Platform.CHANGE_REQUEST_TYPE_LIST, getChangeRequestTypeListItemScope())
		.field(Platform.CHANGE_REQUEST_LIST, getChangeRequestListItemScope())
		;
	/** 用于Platform对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformDetailScope() {
		return PlatformBaseDetailScope;
	}

	protected static SerializeScope CompanyBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Company.ID_PROPERTY)
		.field(Company.NAME_PROPERTY)
		.field(Company.CREATE_TIME_PROPERTY)
		.field(Company.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Company.USER_LIST, getUserListItemScope())
		.field(Company.PROJECT_LIST, getProjectListItemScope())
		;
	/** 用于Company对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCompanyDetailScope() {
		return CompanyBaseDetailScope;
	}

	protected static SerializeScope UserBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(User.ID_PROPERTY)
		.field(User.NAME_PROPERTY)
		.field(User.JOIN_TIME_PROPERTY)
		.field(User.COMPANY_PROPERTY, getCompanySummaryScope())
		.field(User.TITLE_PROPERTY)
		.field(User.USER_PROJECT_LIST, getUserProjectListItemScope())
		.field(User.PAGE_FLOW_SPEC_LIST, getPageFlowSpecListItemScope())
		.field(User.WORK_FLOW_SPEC_LIST, getWorkFlowSpecListItemScope())
		.field(User.CHANGE_REQUEST_SPEC_LIST, getChangeRequestSpecListItemScope())
		.field(User.PAGE_CONTENT_SPEC_LIST, getPageContentSpecListItemScope())
		;
	/** 用于User对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDetailScope() {
		return UserBaseDetailScope;
	}

	protected static SerializeScope UserProjectBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserProject.ID_PROPERTY)
		.field(UserProject.USER_PROPERTY, getUserSummaryScope())
		.field(UserProject.PROJECT_PROPERTY, getProjectSummaryScope())
		.field(UserProject.CREATE_TIME_PROPERTY)
		.field(UserProject.LAST_UPDATE_TIME_PROPERTY)
		;
	/** 用于UserProject对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserProjectDetailScope() {
		return UserProjectBaseDetailScope;
	}

	protected static SerializeScope ChangeRequestTypeBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestType.ID_PROPERTY)
		.field(ChangeRequestType.NAME_PROPERTY)
		.field(ChangeRequestType.CODE_PROPERTY)
		.field(ChangeRequestType.ICON_PROPERTY)
		.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
		.field(ChangeRequestType.BIND_TYPES_PROPERTY)
		.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
		.field(ChangeRequestType.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequestType.CHANGE_REQUEST_LIST, getChangeRequestListItemScope())
		;
	/** 用于ChangeRequestType对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestTypeDetailScope() {
		return ChangeRequestTypeBaseDetailScope;
	}

	protected static SerializeScope ChangeRequestBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequest.ID_PROPERTY)
		.field(ChangeRequest.NAME_PROPERTY)
		.field(ChangeRequest.CREATE_TIME_PROPERTY)
		.field(ChangeRequest.REMOTE_IP_PROPERTY)
		.field(ChangeRequest.REQUEST_TYPE_PROPERTY, getChangeRequestTypeSummaryScope())
		.field(ChangeRequest.COMMITED_PROPERTY)
		.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(ChangeRequest.EVENT_UPDATE_PROFILE_LIST, getEventUpdateProfileListItemScope())
		;
	/** 用于ChangeRequest对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestDetailScope() {
		return ChangeRequestBaseDetailScope;
	}

	protected static SerializeScope EventUpdateProfileBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(EventUpdateProfile.ID_PROPERTY)
		.field(EventUpdateProfile.NAME_PROPERTY)
		.field(EventUpdateProfile.AVANTAR_PROPERTY)
		.field(EventUpdateProfile.FIELD_GROUP_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY)
		.field(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY)
		.field(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
		;
	/** 用于EventUpdateProfile对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getEventUpdateProfileDetailScope() {
		return EventUpdateProfileBaseDetailScope;
	}

	protected static SerializeScope ProjectBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Project.ID_PROPERTY)
		.field(Project.NAME_PROPERTY)
		.field(Project.COMPANY_PROPERTY, getCompanySummaryScope())
		.field(Project.USER_PROJECT_LIST, getUserProjectListItemScope())
		.field(Project.PAGE_FLOW_SPEC_LIST, getPageFlowSpecListItemScope())
		.field(Project.WORK_FLOW_SPEC_LIST, getWorkFlowSpecListItemScope())
		.field(Project.CHANGE_REQUEST_SPEC_LIST, getChangeRequestSpecListItemScope())
		.field(Project.PAGE_CONTENT_SPEC_LIST, getPageContentSpecListItemScope())
		;
	/** 用于Project对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getProjectDetailScope() {
		return ProjectBaseDetailScope;
	}

	protected static SerializeScope PageFlowSpecBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageFlowSpec.ID_PROPERTY)
		.field(PageFlowSpec.SCENE_PROPERTY)
		.field(PageFlowSpec.BRIEF_PROPERTY)
		.field(PageFlowSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(PageFlowSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于PageFlowSpec对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageFlowSpecDetailScope() {
		return PageFlowSpecBaseDetailScope;
	}

	protected static SerializeScope WorkFlowSpecBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WorkFlowSpec.ID_PROPERTY)
		.field(WorkFlowSpec.SCENE_PROPERTY)
		.field(WorkFlowSpec.BRIEF_PROPERTY)
		.field(WorkFlowSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(WorkFlowSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于WorkFlowSpec对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWorkFlowSpecDetailScope() {
		return WorkFlowSpecBaseDetailScope;
	}

	protected static SerializeScope ChangeRequestSpecBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ChangeRequestSpec.ID_PROPERTY)
		.field(ChangeRequestSpec.SCENE_PROPERTY)
		.field(ChangeRequestSpec.BRIEF_PROPERTY)
		.field(ChangeRequestSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(ChangeRequestSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于ChangeRequestSpec对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getChangeRequestSpecDetailScope() {
		return ChangeRequestSpecBaseDetailScope;
	}

	protected static SerializeScope PageContentSpecBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageContentSpec.ID_PROPERTY)
		.field(PageContentSpec.SCENE_PROPERTY)
		.field(PageContentSpec.BRIEF_PROPERTY)
		.field(PageContentSpec.OWNER_PROPERTY, getUserSummaryScope())
		.field(PageContentSpec.PROJECT_PROPERTY, getProjectSummaryScope())
		;
	/** 用于PageContentSpec对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageContentSpecDetailScope() {
		return PageContentSpecBaseDetailScope;
	}

	protected static SerializeScope MobileAppBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(MobileApp.ID_PROPERTY)
		.field(MobileApp.NAME_PROPERTY)
		.field(MobileApp.PAGE_LIST, getPageListItemScope())
		.field(MobileApp.PAGE_TYPE_LIST, getPageTypeListItemScope())
		;
	/** 用于MobileApp对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getMobileAppDetailScope() {
		return MobileAppBaseDetailScope;
	}

	protected static SerializeScope PageBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Page.ID_PROPERTY)
		.field(Page.PAGE_TITLE_PROPERTY)
		.field(Page.LINK_TO_URL_PROPERTY)
		.field(Page.PAGE_TYPE_PROPERTY, getPageTypeSummaryScope())
		.field(Page.DISPLAY_ORDER_PROPERTY)
		.field(Page.MOBILE_APP_PROPERTY, getMobileAppSummaryScope())
		.field(Page.SLIDE_LIST, getSlideListItemScope())
		.field(Page.UI_ACTION_LIST, getUiActionListItemScope())
		.field(Page.SECTION_LIST, getSectionListItemScope())
		;
	/** 用于Page对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageDetailScope() {
		return PageBaseDetailScope;
	}

	protected static SerializeScope PageTypeBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PageType.ID_PROPERTY)
		.field(PageType.NAME_PROPERTY)
		.field(PageType.CODE_PROPERTY)
		.field(PageType.MOBILE_APP_PROPERTY, getMobileAppSummaryScope())
		.field(PageType.FOOTER_TAB_PROPERTY)
		.field(PageType.PAGE_LIST, getPageListItemScope())
		;
	/** 用于PageType对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPageTypeDetailScope() {
		return PageTypeBaseDetailScope;
	}

	protected static SerializeScope SlideBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Slide.ID_PROPERTY)
		.field(Slide.NAME_PROPERTY)
		.field(Slide.DISPLAY_ORDER_PROPERTY)
		.field(Slide.IMAGE_URL_PROPERTY)
		.field(Slide.VIDEO_URL_PROPERTY)
		.field(Slide.LINK_TO_URL_PROPERTY)
		.field(Slide.PAGE_PROPERTY, getPageSummaryScope())
		;
	/** 用于Slide对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSlideDetailScope() {
		return SlideBaseDetailScope;
	}

	protected static SerializeScope UiActionBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UiAction.ID_PROPERTY)
		.field(UiAction.CODE_PROPERTY)
		.field(UiAction.ICON_PROPERTY)
		.field(UiAction.TITLE_PROPERTY)
		.field(UiAction.DISPLAY_ORDER_PROPERTY)
		.field(UiAction.BRIEF_PROPERTY)
		.field(UiAction.IMAGE_URL_PROPERTY)
		.field(UiAction.LINK_TO_URL_PROPERTY)
		.field(UiAction.EXTRA_DATA_PROPERTY)
		.field(UiAction.PAGE_PROPERTY, getPageSummaryScope())
		;
	/** 用于UiAction对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUiActionDetailScope() {
		return UiActionBaseDetailScope;
	}

	protected static SerializeScope SectionBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(Section.ID_PROPERTY)
		.field(Section.TITLE_PROPERTY)
		.field(Section.BRIEF_PROPERTY)
		.field(Section.ICON_PROPERTY)
		.field(Section.DISPLAY_ORDER_PROPERTY)
		.field(Section.VIEW_GROUP_PROPERTY)
		.field(Section.LINK_TO_URL_PROPERTY)
		.field(Section.PAGE_PROPERTY, getPageSummaryScope())
		;
	/** 用于Section对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSectionDetailScope() {
		return SectionBaseDetailScope;
	}

	protected static SerializeScope UserDomainBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserListItemScope())
		.field(UserDomain.PUBLIC_KEY_TYPE_LIST, getPublicKeyTypeListItemScope())
		;
	/** 用于UserDomain对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainDetailScope() {
		return UserDomainBaseDetailScope;
	}

	protected static SerializeScope UserWhiteListBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListDetailScope() {
		return UserWhiteListBaseDetailScope;
	}

	protected static SerializeScope SecUserBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.USER_APP_LIST, getUserAppListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistoryListItemScope())
		.field(SecUser.WECHAT_WORKAPP_IDENTITY_LIST, getWechatWorkappIdentityListItemScope())
		.field(SecUser.WECHAT_MINIAPP_IDENTITY_LIST, getWechatMiniappIdentityListItemScope())
		.field(SecUser.KEYPAIR_IDENTITY_LIST, getKeypairIdentityListItemScope())
		;
	/** 用于SecUser对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserDetailScope() {
		return SecUserBaseDetailScope;
	}

	protected static SerializeScope UserAppBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.QUICK_LINK_LIST, getQuickLinkListItemScope())
		.field(UserApp.LIST_ACCESS_LIST, getListAccessListItemScope())
		;
	/** 用于UserApp对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppDetailScope() {
		return UserAppBaseDetailScope;
	}

	protected static SerializeScope QuickLinkBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(QuickLink.ID_PROPERTY)
		.field(QuickLink.NAME_PROPERTY)
		.field(QuickLink.ICON_PROPERTY)
		.field(QuickLink.IMAGE_PATH_PROPERTY)
		.field(QuickLink.LINK_TARGET_PROPERTY)
		.field(QuickLink.CREATE_TIME_PROPERTY)
		.field(QuickLink.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于QuickLink对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getQuickLinkDetailScope() {
		return QuickLinkBaseDetailScope;
	}

	protected static SerializeScope ListAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessDetailScope() {
		return ListAccessBaseDetailScope;
	}

	protected static SerializeScope LoginHistoryBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryDetailScope() {
		return LoginHistoryBaseDetailScope;
	}

	protected static SerializeScope CandidateContainerBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateContainer.ID_PROPERTY)
		.field(CandidateContainer.NAME_PROPERTY)
		.field(CandidateContainer.CANDIDATE_ELEMENT_LIST, getCandidateElementListItemScope())
		;
	/** 用于CandidateContainer对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateContainerDetailScope() {
		return CandidateContainerBaseDetailScope;
	}

	protected static SerializeScope CandidateElementBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(CandidateElement.ID_PROPERTY)
		.field(CandidateElement.NAME_PROPERTY)
		.field(CandidateElement.TYPE_PROPERTY)
		.field(CandidateElement.IMAGE_PROPERTY)
		.field(CandidateElement.CONTAINER_PROPERTY, getCandidateContainerSummaryScope())
		;
	/** 用于CandidateElement对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getCandidateElementDetailScope() {
		return CandidateElementBaseDetailScope;
	}

	protected static SerializeScope WechatWorkappIdentityBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatWorkappIdentity.ID_PROPERTY)
		.field(WechatWorkappIdentity.CORP_ID_PROPERTY)
		.field(WechatWorkappIdentity.USER_ID_PROPERTY)
		.field(WechatWorkappIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatWorkappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatWorkappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatWorkappIdentity对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatWorkappIdentityDetailScope() {
		return WechatWorkappIdentityBaseDetailScope;
	}

	protected static SerializeScope WechatMiniappIdentityBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(WechatMiniappIdentity.ID_PROPERTY)
		.field(WechatMiniappIdentity.OPEN_ID_PROPERTY)
		.field(WechatMiniappIdentity.APP_ID_PROPERTY)
		.field(WechatMiniappIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(WechatMiniappIdentity.CREATE_TIME_PROPERTY)
		.field(WechatMiniappIdentity.LAST_LOGIN_TIME_PROPERTY)
		;
	/** 用于WechatMiniappIdentity对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getWechatMiniappIdentityDetailScope() {
		return WechatMiniappIdentityBaseDetailScope;
	}

	protected static SerializeScope KeypairIdentityBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(KeypairIdentity.ID_PROPERTY)
		.field(KeypairIdentity.PUBLIC_KEY_PROPERTY)
		.field(KeypairIdentity.KEY_TYPE_PROPERTY, getPublicKeyTypeSummaryScope())
		.field(KeypairIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(KeypairIdentity.CREATE_TIME_PROPERTY)
		;
	/** 用于KeypairIdentity对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getKeypairIdentityDetailScope() {
		return KeypairIdentityBaseDetailScope;
	}

	protected static SerializeScope PublicKeyTypeBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(PublicKeyType.ID_PROPERTY)
		.field(PublicKeyType.NAME_PROPERTY)
		.field(PublicKeyType.CODE_PROPERTY)
		.field(PublicKeyType.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(PublicKeyType.KEYPAIR_IDENTITY_LIST, getKeypairIdentityListItemScope())
		;
	/** 用于PublicKeyType对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPublicKeyTypeDetailScope() {
		return PublicKeyTypeBaseDetailScope;
	}

	protected static SerializeScope TreeNodeBaseDetailScope = SerializeScope.INCLUDE()
		.field(SdsBaseConstants.X_LINK_TO_URL)
		.field(TreeNode.ID_PROPERTY)
		.field(TreeNode.NODE_ID_PROPERTY)
		.field(TreeNode.NODE_TYPE_PROPERTY)
		.field(TreeNode.LEFT_VALUE_PROPERTY)
		.field(TreeNode.RIGHT_VALUE_PROPERTY)
		;
	/** 用于TreeNode对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getTreeNodeDetailScope() {
		return TreeNodeBaseDetailScope;
	}

	

}
















