package com.cla.sds;

import java.util.HashMap;
import java.util.Map;
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


public class BaseSdsListOfViewScope {

	/** Platform的简单属性序列化列表 */
	protected SerializeScope getPlatformSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Platform.ID_PROPERTY)
			.field(Platform.NAME_PROPERTY)
			.field(Platform.CREATE_TIME_PROPERTY)
			.field(Platform.LAST_UPDATE_TIME_PROPERTY)
			.field(Platform.VERSION_PROPERTY)
		;
	}

	/** Company的简单属性序列化列表 */
	protected SerializeScope getCompanySummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Company.ID_PROPERTY)
			.field(Company.NAME_PROPERTY)
			.field(Company.CREATE_TIME_PROPERTY)
			.field(Company.VERSION_PROPERTY)
		;
	}

	/** User的简单属性序列化列表 */
	protected SerializeScope getUserSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(User.ID_PROPERTY)
			.field(User.NAME_PROPERTY)
			.field(User.JOIN_TIME_PROPERTY)
			.field(User.TITLE_PROPERTY)
			.field(User.VERSION_PROPERTY)
		;
	}

	/** UserProject的简单属性序列化列表 */
	protected SerializeScope getUserProjectSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserProject.ID_PROPERTY)
			.field(UserProject.CREATE_TIME_PROPERTY)
			.field(UserProject.LAST_UPDATE_TIME_PROPERTY)
			.field(UserProject.VERSION_PROPERTY)
		;
	}

	/** ChangeRequestType的简单属性序列化列表 */
	protected SerializeScope getChangeRequestTypeSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ChangeRequestType.ID_PROPERTY)
			.field(ChangeRequestType.NAME_PROPERTY)
			.field(ChangeRequestType.CODE_PROPERTY)
			.field(ChangeRequestType.ICON_PROPERTY)
			.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY)
			.field(ChangeRequestType.BIND_TYPES_PROPERTY)
			.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
			.field(ChangeRequestType.VERSION_PROPERTY)
		;
	}

	/** ChangeRequest的简单属性序列化列表 */
	protected SerializeScope getChangeRequestSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ChangeRequest.ID_PROPERTY)
			.field(ChangeRequest.NAME_PROPERTY)
			.field(ChangeRequest.CREATE_TIME_PROPERTY)
			.field(ChangeRequest.REMOTE_IP_PROPERTY)
			.field(ChangeRequest.COMMITED_PROPERTY)
			.field(ChangeRequest.VERSION_PROPERTY)
		;
	}

	/** EventUpdateProfile的简单属性序列化列表 */
	protected SerializeScope getEventUpdateProfileSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(EventUpdateProfile.ID_PROPERTY)
			.field(EventUpdateProfile.NAME_PROPERTY)
			.field(EventUpdateProfile.AVANTAR_PROPERTY)
			.field(EventUpdateProfile.FIELD_GROUP_PROPERTY)
			.field(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY)
			.field(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY)
			.field(EventUpdateProfile.VERSION_PROPERTY)
		;
	}

	/** Project的简单属性序列化列表 */
	protected SerializeScope getProjectSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Project.ID_PROPERTY)
			.field(Project.NAME_PROPERTY)
			.field(Project.VERSION_PROPERTY)
		;
	}

	/** PageFlowSpec的简单属性序列化列表 */
	protected SerializeScope getPageFlowSpecSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PageFlowSpec.ID_PROPERTY)
			.field(PageFlowSpec.SCENE_PROPERTY)
			.field(PageFlowSpec.BRIEF_PROPERTY)
			.field(PageFlowSpec.VERSION_PROPERTY)
		;
	}

	/** WorkFlowSpec的简单属性序列化列表 */
	protected SerializeScope getWorkFlowSpecSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(WorkFlowSpec.ID_PROPERTY)
			.field(WorkFlowSpec.SCENE_PROPERTY)
			.field(WorkFlowSpec.BRIEF_PROPERTY)
			.field(WorkFlowSpec.VERSION_PROPERTY)
		;
	}

	/** ChangeRequestSpec的简单属性序列化列表 */
	protected SerializeScope getChangeRequestSpecSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ChangeRequestSpec.ID_PROPERTY)
			.field(ChangeRequestSpec.SCENE_PROPERTY)
			.field(ChangeRequestSpec.BRIEF_PROPERTY)
			.field(ChangeRequestSpec.VERSION_PROPERTY)
		;
	}

	/** PageContentSpec的简单属性序列化列表 */
	protected SerializeScope getPageContentSpecSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PageContentSpec.ID_PROPERTY)
			.field(PageContentSpec.SCENE_PROPERTY)
			.field(PageContentSpec.BRIEF_PROPERTY)
			.field(PageContentSpec.VERSION_PROPERTY)
		;
	}

	/** MobileApp的简单属性序列化列表 */
	protected SerializeScope getMobileAppSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(MobileApp.ID_PROPERTY)
			.field(MobileApp.NAME_PROPERTY)
			.field(MobileApp.VERSION_PROPERTY)
		;
	}

	/** Page的简单属性序列化列表 */
	protected SerializeScope getPageSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Page.ID_PROPERTY)
			.field(Page.PAGE_TITLE_PROPERTY)
			.field(Page.LINK_TO_URL_PROPERTY)
			.field(Page.DISPLAY_ORDER_PROPERTY)
			.field(Page.VERSION_PROPERTY)
		;
	}

	/** PageType的简单属性序列化列表 */
	protected SerializeScope getPageTypeSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PageType.ID_PROPERTY)
			.field(PageType.NAME_PROPERTY)
			.field(PageType.CODE_PROPERTY)
			.field(PageType.FOOTER_TAB_PROPERTY)
			.field(PageType.VERSION_PROPERTY)
		;
	}

	/** Slide的简单属性序列化列表 */
	protected SerializeScope getSlideSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Slide.ID_PROPERTY)
			.field(Slide.NAME_PROPERTY)
			.field(Slide.DISPLAY_ORDER_PROPERTY)
			.field(Slide.IMAGE_URL_PROPERTY)
			.field(Slide.VIDEO_URL_PROPERTY)
			.field(Slide.LINK_TO_URL_PROPERTY)
			.field(Slide.VERSION_PROPERTY)
		;
	}

	/** UiAction的简单属性序列化列表 */
	protected SerializeScope getUiActionSummaryScope() {
		return SerializeScope.INCLUDE()
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
			.field(UiAction.VERSION_PROPERTY)
		;
	}

	/** Section的简单属性序列化列表 */
	protected SerializeScope getSectionSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Section.ID_PROPERTY)
			.field(Section.TITLE_PROPERTY)
			.field(Section.BRIEF_PROPERTY)
			.field(Section.ICON_PROPERTY)
			.field(Section.DISPLAY_ORDER_PROPERTY)
			.field(Section.VIEW_GROUP_PROPERTY)
			.field(Section.LINK_TO_URL_PROPERTY)
			.field(Section.VERSION_PROPERTY)
		;
	}

	/** UserDomain的简单属性序列化列表 */
	protected SerializeScope getUserDomainSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserDomain.ID_PROPERTY)
			.field(UserDomain.NAME_PROPERTY)
			.field(UserDomain.VERSION_PROPERTY)
		;
	}

	/** UserWhiteList的简单属性序列化列表 */
	protected SerializeScope getUserWhiteListSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserWhiteList.ID_PROPERTY)
			.field(UserWhiteList.USER_IDENTITY_PROPERTY)
			.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
			.field(UserWhiteList.VERSION_PROPERTY)
		;
	}

	/** SecUser的简单属性序列化列表 */
	protected SerializeScope getSecUserSummaryScope() {
		return SerializeScope.INCLUDE()
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
			.field(SecUser.VERSION_PROPERTY)
		;
	}

	/** UserApp的简单属性序列化列表 */
	protected SerializeScope getUserAppSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserApp.ID_PROPERTY)
			.field(UserApp.TITLE_PROPERTY)
			.field(UserApp.APP_ICON_PROPERTY)
			.field(UserApp.FULL_ACCESS_PROPERTY)
			.field(UserApp.PERMISSION_PROPERTY)
			.field(UserApp.OBJECT_TYPE_PROPERTY)
			.field(UserApp.OBJECT_ID_PROPERTY)
			.field(UserApp.LOCATION_PROPERTY)
			.field(UserApp.VERSION_PROPERTY)
		;
	}

	/** QuickLink的简单属性序列化列表 */
	protected SerializeScope getQuickLinkSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(QuickLink.ID_PROPERTY)
			.field(QuickLink.NAME_PROPERTY)
			.field(QuickLink.ICON_PROPERTY)
			.field(QuickLink.IMAGE_PATH_PROPERTY)
			.field(QuickLink.LINK_TARGET_PROPERTY)
			.field(QuickLink.CREATE_TIME_PROPERTY)
			.field(QuickLink.VERSION_PROPERTY)
		;
	}

	/** ListAccess的简单属性序列化列表 */
	protected SerializeScope getListAccessSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ListAccess.ID_PROPERTY)
			.field(ListAccess.NAME_PROPERTY)
			.field(ListAccess.INTERNAL_NAME_PROPERTY)
			.field(ListAccess.READ_PERMISSION_PROPERTY)
			.field(ListAccess.CREATE_PERMISSION_PROPERTY)
			.field(ListAccess.DELETE_PERMISSION_PROPERTY)
			.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
			.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
			.field(ListAccess.VERSION_PROPERTY)
		;
	}

	/** LoginHistory的简单属性序列化列表 */
	protected SerializeScope getLoginHistorySummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(LoginHistory.ID_PROPERTY)
			.field(LoginHistory.LOGIN_TIME_PROPERTY)
			.field(LoginHistory.FROM_IP_PROPERTY)
			.field(LoginHistory.DESCRIPTION_PROPERTY)
			.field(LoginHistory.VERSION_PROPERTY)
		;
	}

	/** CandidateContainer的简单属性序列化列表 */
	protected SerializeScope getCandidateContainerSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(CandidateContainer.ID_PROPERTY)
			.field(CandidateContainer.NAME_PROPERTY)
			.field(CandidateContainer.VERSION_PROPERTY)
		;
	}

	/** CandidateElement的简单属性序列化列表 */
	protected SerializeScope getCandidateElementSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(CandidateElement.ID_PROPERTY)
			.field(CandidateElement.NAME_PROPERTY)
			.field(CandidateElement.TYPE_PROPERTY)
			.field(CandidateElement.IMAGE_PROPERTY)
			.field(CandidateElement.VERSION_PROPERTY)
		;
	}

	/** WechatWorkappIdentity的简单属性序列化列表 */
	protected SerializeScope getWechatWorkappIdentitySummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(WechatWorkappIdentity.ID_PROPERTY)
			.field(WechatWorkappIdentity.CORP_ID_PROPERTY)
			.field(WechatWorkappIdentity.USER_ID_PROPERTY)
			.field(WechatWorkappIdentity.CREATE_TIME_PROPERTY)
			.field(WechatWorkappIdentity.LAST_LOGIN_TIME_PROPERTY)
			.field(WechatWorkappIdentity.VERSION_PROPERTY)
		;
	}

	/** WechatMiniappIdentity的简单属性序列化列表 */
	protected SerializeScope getWechatMiniappIdentitySummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(WechatMiniappIdentity.ID_PROPERTY)
			.field(WechatMiniappIdentity.OPEN_ID_PROPERTY)
			.field(WechatMiniappIdentity.APP_ID_PROPERTY)
			.field(WechatMiniappIdentity.CREATE_TIME_PROPERTY)
			.field(WechatMiniappIdentity.LAST_LOGIN_TIME_PROPERTY)
			.field(WechatMiniappIdentity.VERSION_PROPERTY)
		;
	}

	/** KeypairIdentity的简单属性序列化列表 */
	protected SerializeScope getKeypairIdentitySummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(KeypairIdentity.ID_PROPERTY)
			.field(KeypairIdentity.PUBLIC_KEY_PROPERTY)
			.field(KeypairIdentity.CREATE_TIME_PROPERTY)
			.field(KeypairIdentity.VERSION_PROPERTY)
		;
	}

	/** PublicKeyType的简单属性序列化列表 */
	protected SerializeScope getPublicKeyTypeSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PublicKeyType.ID_PROPERTY)
			.field(PublicKeyType.NAME_PROPERTY)
			.field(PublicKeyType.CODE_PROPERTY)
			.field(PublicKeyType.VERSION_PROPERTY)
		;
	}

	/** TreeNode的简单属性序列化列表 */
	protected SerializeScope getTreeNodeSummaryScope() {
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(TreeNode.ID_PROPERTY)
			.field(TreeNode.NODE_ID_PROPERTY)
			.field(TreeNode.NODE_TYPE_PROPERTY)
			.field(TreeNode.LEFT_VALUE_PROPERTY)
			.field(TreeNode.RIGHT_VALUE_PROPERTY)
			.field(TreeNode.VERSION_PROPERTY)
		;
	}

	/** Platform的ListOf时需要序列化的属性列表 */
	protected SerializeScope getPlatformListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='规规格管理平台';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Platform.ID_PROPERTY)
			.field(Platform.NAME_PROPERTY).as("title")
			.field(Platform.CREATE_TIME_PROPERTY)
			.field(Platform.LAST_UPDATE_TIME_PROPERTY)
			.field(Platform.VERSION_PROPERTY)
		;
	}

	/** Company的ListOf时需要序列化的属性列表 */
	protected SerializeScope getCompanyListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='XX公司';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Company.ID_PROPERTY)
			.field(Company.NAME_PROPERTY).as("title")
			.field(Company.CREATE_TIME_PROPERTY)
			.field(Company.PLATFORM_PROPERTY, getPlatformSummaryScope())
			.field(Company.VERSION_PROPERTY)
		;
	}

	/** User的ListOf时需要序列化的属性列表 */
	protected SerializeScope getUserListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='X先生';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='title';
		//	type='string';
		//	value='BA|Leader|Coder';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(User.ID_PROPERTY)
			.field(User.NAME_PROPERTY).as("title")
			.field(User.JOIN_TIME_PROPERTY)
			.field(User.COMPANY_PROPERTY, getCompanySummaryScope())
			.field(User.TITLE_PROPERTY).as("brief").with_prefix("标题: ")
			.field(User.VERSION_PROPERTY)
		;
	}

	/** UserProject的ListOf时需要序列化的属性列表 */
	protected SerializeScope getUserProjectListOfViewScope() {
		// DisplayMode{name='auto', titleField=null, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserProject.ID_PROPERTY)
			.field(UserProject.USER_PROPERTY, getUserSummaryScope())
			.field(UserProject.PROJECT_PROPERTY, getProjectSummaryScope())
			.field(UserProject.CREATE_TIME_PROPERTY)
			.field(UserProject.LAST_UPDATE_TIME_PROPERTY)
			.field(UserProject.VERSION_PROPERTY)
		;
	}

	/** ChangeRequestType的ListOf时需要序列化的属性列表 */
	protected SerializeScope getChangeRequestTypeListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='更新个人信息';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='display_order';
		//	type='int';
		//	value='1|2|3|4|5|6';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ChangeRequestType.ID_PROPERTY)
			.field(ChangeRequestType.NAME_PROPERTY).as("title")
			.field(ChangeRequestType.CODE_PROPERTY)
			.field(ChangeRequestType.ICON_PROPERTY)
			.field(ChangeRequestType.DISPLAY_ORDER_PROPERTY).as("brief").with_prefix("顺序: ")
			.field(ChangeRequestType.BIND_TYPES_PROPERTY)
			.field(ChangeRequestType.STEP_CONFIGURATION_PROPERTY)
			.field(ChangeRequestType.PLATFORM_PROPERTY, getPlatformSummaryScope())
			.field(ChangeRequestType.VERSION_PROPERTY)
		;
	}

	/** ChangeRequest的ListOf时需要序列化的属性列表 */
	protected SerializeScope getChangeRequestListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='注册|开始考试|答题';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='request_type';
		//	type='change_request_type';
		//	value='$(object)';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ChangeRequest.ID_PROPERTY)
			.field(ChangeRequest.NAME_PROPERTY).as("title")
			.field(ChangeRequest.CREATE_TIME_PROPERTY)
			.field(ChangeRequest.REMOTE_IP_PROPERTY)
			.field(ChangeRequest.REQUEST_TYPE_PROPERTY, SerializeScope.INCLUDE()
				.field(ChangeRequestType.NAME_PROPERTY).as("brief").with_prefix("请求类型: ")
					).move_up()
			.field(ChangeRequest.COMMITED_PROPERTY)
			.field(ChangeRequest.PLATFORM_PROPERTY, getPlatformSummaryScope())
			.field(ChangeRequest.VERSION_PROPERTY)
		;
	}

	/** EventUpdateProfile的ListOf时需要序列化的属性列表 */
	protected SerializeScope getEventUpdateProfileListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='name|用户名';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='field_group';
		//	type='string';
		//	value='cr_type/step_name/event_name/event_index';
		//	required='true';
		//}
		//, imageUrlField=fieldesc{
		//	name='avantar';
		//	type='string_image';
		//	value='avantar.jpg';
		//	required='true';
		//}
		//, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(EventUpdateProfile.ID_PROPERTY)
			.field(EventUpdateProfile.NAME_PROPERTY).as("title")
			.field(EventUpdateProfile.AVANTAR_PROPERTY).as("imageUrl")
			.field(EventUpdateProfile.FIELD_GROUP_PROPERTY).as("brief").with_prefix("字段组: ")
			.field(EventUpdateProfile.EVENT_INITIATOR_TYPE_PROPERTY)
			.field(EventUpdateProfile.EVENT_INITIATOR_ID_PROPERTY)
			.field(EventUpdateProfile.CHANGE_REQUEST_PROPERTY, getChangeRequestSummaryScope())
			.field(EventUpdateProfile.VERSION_PROPERTY)
		;
	}

	/** Project的ListOf时需要序列化的属性列表 */
	protected SerializeScope getProjectListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='X项目';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Project.ID_PROPERTY)
			.field(Project.NAME_PROPERTY).as("title")
			.field(Project.COMPANY_PROPERTY, getCompanySummaryScope())
			.field(Project.VERSION_PROPERTY)
		;
	}

	/** PageFlowSpec的ListOf时需要序列化的属性列表 */
	protected SerializeScope getPageFlowSpecListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='scene';
		//	type='string';
		//	value='登录';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='brief';
		//	type='string_longtext';
		//	value='text()';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PageFlowSpec.ID_PROPERTY)
			.field(PageFlowSpec.SCENE_PROPERTY).as("title")
			.field(PageFlowSpec.BRIEF_PROPERTY).as("brief").with_prefix("短暂的: ")
			.field(PageFlowSpec.OWNER_PROPERTY, getUserSummaryScope())
			.field(PageFlowSpec.PROJECT_PROPERTY, getProjectSummaryScope())
			.field(PageFlowSpec.VERSION_PROPERTY)
		;
	}

	/** WorkFlowSpec的ListOf时需要序列化的属性列表 */
	protected SerializeScope getWorkFlowSpecListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='scene';
		//	type='string';
		//	value='登录';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='brief';
		//	type='string_longtext';
		//	value='text()';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(WorkFlowSpec.ID_PROPERTY)
			.field(WorkFlowSpec.SCENE_PROPERTY).as("title")
			.field(WorkFlowSpec.BRIEF_PROPERTY).as("brief").with_prefix("短暂的: ")
			.field(WorkFlowSpec.OWNER_PROPERTY, getUserSummaryScope())
			.field(WorkFlowSpec.PROJECT_PROPERTY, getProjectSummaryScope())
			.field(WorkFlowSpec.VERSION_PROPERTY)
		;
	}

	/** ChangeRequestSpec的ListOf时需要序列化的属性列表 */
	protected SerializeScope getChangeRequestSpecListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='scene';
		//	type='string';
		//	value='登录';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='brief';
		//	type='string_longtext';
		//	value='text()';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ChangeRequestSpec.ID_PROPERTY)
			.field(ChangeRequestSpec.SCENE_PROPERTY).as("title")
			.field(ChangeRequestSpec.BRIEF_PROPERTY).as("brief").with_prefix("短暂的: ")
			.field(ChangeRequestSpec.OWNER_PROPERTY, getUserSummaryScope())
			.field(ChangeRequestSpec.PROJECT_PROPERTY, getProjectSummaryScope())
			.field(ChangeRequestSpec.VERSION_PROPERTY)
		;
	}

	/** PageContentSpec的ListOf时需要序列化的属性列表 */
	protected SerializeScope getPageContentSpecListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='scene';
		//	type='string';
		//	value='登录';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='brief';
		//	type='string_longtext';
		//	value='text()';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PageContentSpec.ID_PROPERTY)
			.field(PageContentSpec.SCENE_PROPERTY).as("title")
			.field(PageContentSpec.BRIEF_PROPERTY).as("brief").with_prefix("短暂的: ")
			.field(PageContentSpec.OWNER_PROPERTY, getUserSummaryScope())
			.field(PageContentSpec.PROJECT_PROPERTY, getProjectSummaryScope())
			.field(PageContentSpec.VERSION_PROPERTY)
		;
	}

	/** MobileApp的ListOf时需要序列化的属性列表 */
	protected SerializeScope getMobileAppListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='移动端配置';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(MobileApp.ID_PROPERTY)
			.field(MobileApp.NAME_PROPERTY).as("title")
			.field(MobileApp.VERSION_PROPERTY)
		;
	}

	/** Page的ListOf时需要序列化的属性列表 */
	protected SerializeScope getPageListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='page_title';
		//	type='string';
		//	value='首页';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='display_order';
		//	type='int';
		//	value='1|2|3';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Page.ID_PROPERTY)
			.field(Page.PAGE_TITLE_PROPERTY).as("title")
			.field(Page.LINK_TO_URL_PROPERTY)
			.field(Page.PAGE_TYPE_PROPERTY, getPageTypeSummaryScope())
			.field(Page.DISPLAY_ORDER_PROPERTY).as("brief").with_prefix("顺序: ")
			.field(Page.MOBILE_APP_PROPERTY, getMobileAppSummaryScope())
			.field(Page.VERSION_PROPERTY)
		;
	}

	/** PageType的ListOf时需要序列化的属性列表 */
	protected SerializeScope getPageTypeListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='首页|我的|Generic Page|Listof Page|功能大厅|普通';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='code';
		//	type='string';
		//	value='home|me|generic-page|listof-page|service-center|simple';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PageType.ID_PROPERTY)
			.field(PageType.NAME_PROPERTY).as("title")
			.field(PageType.CODE_PROPERTY).as("brief").with_prefix("编码: ")
			.field(PageType.MOBILE_APP_PROPERTY, getMobileAppSummaryScope())
			.field(PageType.FOOTER_TAB_PROPERTY)
			.field(PageType.VERSION_PROPERTY)
		;
	}

	/** Slide的ListOf时需要序列化的属性列表 */
	protected SerializeScope getSlideListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='首页Focus的内容';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='display_order';
		//	type='int';
		//	value='1|2|3';
		//	required='true';
		//}
		//, imageUrlField=fieldesc{
		//	name='image_url';
		//	type='string_image';
		//	value='https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_1.jpg|https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_2.jpg|https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_3.jpg|https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_4.jpg';
		//	required='true';
		//}
		//, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Slide.ID_PROPERTY)
			.field(Slide.NAME_PROPERTY).as("title")
			.field(Slide.DISPLAY_ORDER_PROPERTY).as("brief").with_prefix("顺序: ")
			.field(Slide.IMAGE_URL_PROPERTY).as("imageUrl")
			.field(Slide.VIDEO_URL_PROPERTY)
			.field(Slide.LINK_TO_URL_PROPERTY)
			.field(Slide.PAGE_PROPERTY, getPageSummaryScope())
			.field(Slide.VERSION_PROPERTY)
		;
	}

	/** UiAction的ListOf时需要序列化的属性列表 */
	protected SerializeScope getUiActionListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='title';
		//	type='string';
		//	value='提交|分享|查看|更多';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='brief';
		//	type='string';
		//	value='Submit|Share|View|View More';
		//	required='true';
		//}
		//, imageUrlField=fieldesc{
		//	name='image_url';
		//	type='string_image';
		//	value='https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_1.jpg|https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_2.jpg|https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_3.jpg|https://xubai-test.oss-cn-beijing.aliyuncs.com/app/test/slide_4.jpg';
		//	required='true';
		//}
		//, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UiAction.ID_PROPERTY)
			.field(UiAction.CODE_PROPERTY)
			.field(UiAction.ICON_PROPERTY)
			.field(UiAction.TITLE_PROPERTY).as("title")
			.field(UiAction.DISPLAY_ORDER_PROPERTY)
			.field(UiAction.BRIEF_PROPERTY).as("brief").with_prefix("短暂的: ")
			.field(UiAction.IMAGE_URL_PROPERTY).as("imageUrl")
			.field(UiAction.LINK_TO_URL_PROPERTY)
			.field(UiAction.EXTRA_DATA_PROPERTY)
			.field(UiAction.PAGE_PROPERTY, getPageSummaryScope())
			.field(UiAction.VERSION_PROPERTY)
		;
	}

	/** Section的ListOf时需要序列化的属性列表 */
	protected SerializeScope getSectionListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='title';
		//	type='string';
		//	value='文章|作品';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='brief';
		//	type='string';
		//	value='Article|Artwork';
		//	required='true';
		//}
		//, imageUrlField=fieldesc{
		//	name='icon';
		//	type='string_image';
		//	value='icon.jpg';
		//	required='true';
		//}
		//, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(Section.ID_PROPERTY)
			.field(Section.TITLE_PROPERTY).as("title")
			.field(Section.BRIEF_PROPERTY).as("brief").with_prefix("短暂的: ")
			.field(Section.ICON_PROPERTY).as("imageUrl")
			.field(Section.DISPLAY_ORDER_PROPERTY)
			.field(Section.VIEW_GROUP_PROPERTY)
			.field(Section.LINK_TO_URL_PROPERTY)
			.field(Section.PAGE_PROPERTY, getPageSummaryScope())
			.field(Section.VERSION_PROPERTY)
		;
	}

	/** UserDomain的ListOf时需要序列化的属性列表 */
	protected SerializeScope getUserDomainListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='用户区域';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserDomain.ID_PROPERTY)
			.field(UserDomain.NAME_PROPERTY).as("title")
			.field(UserDomain.VERSION_PROPERTY)
		;
	}

	/** UserWhiteList的ListOf时需要序列化的属性列表 */
	protected SerializeScope getUserWhiteListListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='user_identity';
		//	type='string';
		//	value='clariones|13808188512';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='user_special_functions';
		//	type='string';
		//	value='tester;ios-spokesperson';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserWhiteList.ID_PROPERTY)
			.field(UserWhiteList.USER_IDENTITY_PROPERTY).as("title")
			.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY).as("brief").with_prefix("用户特殊功能: ")
			.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
			.field(UserWhiteList.VERSION_PROPERTY)
		;
	}

	/** SecUser的ListOf时需要序列化的属性列表 */
	protected SerializeScope getSecUserListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='login';
		//	type='string';
		//	value='login';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='verification_code';
		//	type='int';
		//	value='0|9999999';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(SecUser.ID_PROPERTY)
			.field(SecUser.LOGIN_PROPERTY).as("title")
			.field(SecUser.MOBILE_PROPERTY)
			.field(SecUser.EMAIL_PROPERTY)
			.field(SecUser.PWD_PROPERTY)
			.field(SecUser.WEIXIN_OPENID_PROPERTY)
			.field(SecUser.WEIXIN_APPID_PROPERTY)
			.field(SecUser.ACCESS_TOKEN_PROPERTY)
			.field(SecUser.VERIFICATION_CODE_PROPERTY).as("brief").with_prefix("验证码: ")
			.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
			.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
			.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
			.field(SecUser.VERSION_PROPERTY)
		;
	}

	/** UserApp的ListOf时需要序列化的属性列表 */
	protected SerializeScope getUserAppListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='title';
		//	type='string';
		//	value='审车平台|账户管理|接车公司|审车公司|维修公司|顾客';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='app_icon';
		//	type='string';
		//	value='users|bank|wechat|bar-chart|user|users';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(UserApp.ID_PROPERTY)
			.field(UserApp.TITLE_PROPERTY).as("title")
			.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
			.field(UserApp.APP_ICON_PROPERTY).as("brief").with_prefix("应用程序图标: ")
			.field(UserApp.FULL_ACCESS_PROPERTY)
			.field(UserApp.PERMISSION_PROPERTY)
			.field(UserApp.OBJECT_TYPE_PROPERTY)
			.field(UserApp.OBJECT_ID_PROPERTY)
			.field(UserApp.LOCATION_PROPERTY)
			.field(UserApp.VERSION_PROPERTY)
		;
	}

	/** QuickLink的ListOf时需要序列化的属性列表 */
	protected SerializeScope getQuickLinkListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='列表';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='icon';
		//	type='string';
		//	value='facebook|google';
		//	required='true';
		//}
		//, imageUrlField=fieldesc{
		//	name='image_path';
		//	type='string_image';
		//	value='y-200-200-red.jpg';
		//	required='true';
		//}
		//, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(QuickLink.ID_PROPERTY)
			.field(QuickLink.NAME_PROPERTY).as("title")
			.field(QuickLink.ICON_PROPERTY).as("brief").with_prefix("图标: ")
			.field(QuickLink.IMAGE_PATH_PROPERTY).as("imageUrl")
			.field(QuickLink.LINK_TARGET_PROPERTY)
			.field(QuickLink.CREATE_TIME_PROPERTY)
			.field(QuickLink.APP_PROPERTY, getUserAppSummaryScope())
			.field(QuickLink.VERSION_PROPERTY)
		;
	}

	/** ListAccess的ListOf时需要序列化的属性列表 */
	protected SerializeScope getListAccessListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='列表';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='internal_name';
		//	type='string';
		//	value='levelOneCategoryList';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(ListAccess.ID_PROPERTY)
			.field(ListAccess.NAME_PROPERTY).as("title")
			.field(ListAccess.INTERNAL_NAME_PROPERTY).as("brief").with_prefix("内部名称: ")
			.field(ListAccess.READ_PERMISSION_PROPERTY)
			.field(ListAccess.CREATE_PERMISSION_PROPERTY)
			.field(ListAccess.DELETE_PERMISSION_PROPERTY)
			.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
			.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
			.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
			.field(ListAccess.VERSION_PROPERTY)
		;
	}

	/** LoginHistory的ListOf时需要序列化的属性列表 */
	protected SerializeScope getLoginHistoryListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='from_ip';
		//	type='string';
		//	value='192.168.1.1|192.168.1.2';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='description';
		//	type='string';
		//	value='登陆成功';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(LoginHistory.ID_PROPERTY)
			.field(LoginHistory.LOGIN_TIME_PROPERTY)
			.field(LoginHistory.FROM_IP_PROPERTY).as("title")
			.field(LoginHistory.DESCRIPTION_PROPERTY).as("brief").with_prefix("描述: ")
			.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
			.field(LoginHistory.VERSION_PROPERTY)
		;
	}

	/** CandidateContainer的ListOf时需要序列化的属性列表 */
	protected SerializeScope getCandidateContainerListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='我只是一个容器';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(CandidateContainer.ID_PROPERTY)
			.field(CandidateContainer.NAME_PROPERTY).as("title")
			.field(CandidateContainer.VERSION_PROPERTY)
		;
	}

	/** CandidateElement的ListOf时需要序列化的属性列表 */
	protected SerializeScope getCandidateElementListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='搜索到的匹配字段';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='type';
		//	type='string';
		//	value='类型描述';
		//	required='true';
		//}
		//, imageUrlField=fieldesc{
		//	name='image';
		//	type='string_image';
		//	value='1.jpg';
		//	required='true';
		//}
		//, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(CandidateElement.ID_PROPERTY)
			.field(CandidateElement.NAME_PROPERTY).as("title")
			.field(CandidateElement.TYPE_PROPERTY).as("brief").with_prefix("类型: ")
			.field(CandidateElement.IMAGE_PROPERTY).as("imageUrl")
			.field(CandidateElement.CONTAINER_PROPERTY, getCandidateContainerSummaryScope())
			.field(CandidateElement.VERSION_PROPERTY)
		;
	}

	/** WechatWorkappIdentity的ListOf时需要序列化的属性列表 */
	protected SerializeScope getWechatWorkappIdentityListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='corp_id';
		//	type='string';
		//	value='corporation123';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='user_id';
		//	type='string';
		//	value='user123';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(WechatWorkappIdentity.ID_PROPERTY)
			.field(WechatWorkappIdentity.CORP_ID_PROPERTY).as("title")
			.field(WechatWorkappIdentity.USER_ID_PROPERTY).as("brief").with_prefix("用户Id: ")
			.field(WechatWorkappIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
			.field(WechatWorkappIdentity.CREATE_TIME_PROPERTY)
			.field(WechatWorkappIdentity.LAST_LOGIN_TIME_PROPERTY)
			.field(WechatWorkappIdentity.VERSION_PROPERTY)
		;
	}

	/** WechatMiniappIdentity的ListOf时需要序列化的属性列表 */
	protected SerializeScope getWechatMiniappIdentityListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='open_id';
		//	type='string';
		//	value='wechat_open_id_1234567890';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='app_id';
		//	type='string';
		//	value='wechat_miniapp_id_1234567890';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(WechatMiniappIdentity.ID_PROPERTY)
			.field(WechatMiniappIdentity.OPEN_ID_PROPERTY).as("title")
			.field(WechatMiniappIdentity.APP_ID_PROPERTY).as("brief").with_prefix("应用程序Id: ")
			.field(WechatMiniappIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
			.field(WechatMiniappIdentity.CREATE_TIME_PROPERTY)
			.field(WechatMiniappIdentity.LAST_LOGIN_TIME_PROPERTY)
			.field(WechatMiniappIdentity.VERSION_PROPERTY)
		;
	}

	/** KeypairIdentity的ListOf时需要序列化的属性列表 */
	protected SerializeScope getKeypairIdentityListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='public_key';
		//	type='string_longtext';
		//	value='text()';
		//	required='true';
		//}
		//, briefField=null, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(KeypairIdentity.ID_PROPERTY)
			.field(KeypairIdentity.PUBLIC_KEY_PROPERTY).as("title")
			.field(KeypairIdentity.KEY_TYPE_PROPERTY, getPublicKeyTypeSummaryScope())
			.field(KeypairIdentity.SEC_USER_PROPERTY, getSecUserSummaryScope())
			.field(KeypairIdentity.CREATE_TIME_PROPERTY)
			.field(KeypairIdentity.VERSION_PROPERTY)
		;
	}

	/** PublicKeyType的ListOf时需要序列化的属性列表 */
	protected SerializeScope getPublicKeyTypeListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='name';
		//	type='string';
		//	value='rsa|ecc';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='code';
		//	type='string';
		//	value='rsa|ecc';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(PublicKeyType.ID_PROPERTY)
			.field(PublicKeyType.NAME_PROPERTY).as("title")
			.field(PublicKeyType.CODE_PROPERTY).as("brief").with_prefix("编码: ")
			.field(PublicKeyType.DOMAIN_PROPERTY, getUserDomainSummaryScope())
			.field(PublicKeyType.VERSION_PROPERTY)
		;
	}

	/** TreeNode的ListOf时需要序列化的属性列表 */
	protected SerializeScope getTreeNodeListOfViewScope() {
		// DisplayMode{name='auto', titleField=fieldesc{
		//	name='node_id';
		//	type='string';
		//	value='node000001';
		//	required='true';
		//}
		//, briefField=fieldesc{
		//	name='node_type';
		//	type='string';
		//	value='nodetype';
		//	required='true';
		//}
		//, imageUrlField=null, imageListField=null, propList=[]}
		return SerializeScope.INCLUDE()
			.field(SdsBaseConstants.X_LINK_TO_URL)
			.field(TreeNode.ID_PROPERTY)
			.field(TreeNode.NODE_ID_PROPERTY).as("title")
			.field(TreeNode.NODE_TYPE_PROPERTY).as("brief").with_prefix("节点类型: ")
			.field(TreeNode.LEFT_VALUE_PROPERTY)
			.field(TreeNode.RIGHT_VALUE_PROPERTY)
			.field(TreeNode.VERSION_PROPERTY)
		;
	}

	
	public SerializeScope getListOfViewScope(String listClassName, String ownerClassName) {
		return scopes.get(listClassName);
	}
	
	
	protected Map<String, SerializeScope> scopes;
	protected void initAllViewScope() {
		scopes = new HashMap<>();
		scopes.put(Platform.class.getName(),getPlatformListOfViewScope());
		scopes.put(Company.class.getName(),getCompanyListOfViewScope());
		scopes.put(User.class.getName(),getUserListOfViewScope());
		scopes.put(UserProject.class.getName(),getUserProjectListOfViewScope());
		scopes.put(ChangeRequestType.class.getName(),getChangeRequestTypeListOfViewScope());
		scopes.put(ChangeRequest.class.getName(),getChangeRequestListOfViewScope());
		scopes.put(EventUpdateProfile.class.getName(),getEventUpdateProfileListOfViewScope());
		scopes.put(Project.class.getName(),getProjectListOfViewScope());
		scopes.put(PageFlowSpec.class.getName(),getPageFlowSpecListOfViewScope());
		scopes.put(WorkFlowSpec.class.getName(),getWorkFlowSpecListOfViewScope());
		scopes.put(ChangeRequestSpec.class.getName(),getChangeRequestSpecListOfViewScope());
		scopes.put(PageContentSpec.class.getName(),getPageContentSpecListOfViewScope());
		scopes.put(MobileApp.class.getName(),getMobileAppListOfViewScope());
		scopes.put(Page.class.getName(),getPageListOfViewScope());
		scopes.put(PageType.class.getName(),getPageTypeListOfViewScope());
		scopes.put(Slide.class.getName(),getSlideListOfViewScope());
		scopes.put(UiAction.class.getName(),getUiActionListOfViewScope());
		scopes.put(Section.class.getName(),getSectionListOfViewScope());
		scopes.put(UserDomain.class.getName(),getUserDomainListOfViewScope());
		scopes.put(UserWhiteList.class.getName(),getUserWhiteListListOfViewScope());
		scopes.put(SecUser.class.getName(),getSecUserListOfViewScope());
		scopes.put(UserApp.class.getName(),getUserAppListOfViewScope());
		scopes.put(QuickLink.class.getName(),getQuickLinkListOfViewScope());
		scopes.put(ListAccess.class.getName(),getListAccessListOfViewScope());
		scopes.put(LoginHistory.class.getName(),getLoginHistoryListOfViewScope());
		scopes.put(CandidateContainer.class.getName(),getCandidateContainerListOfViewScope());
		scopes.put(CandidateElement.class.getName(),getCandidateElementListOfViewScope());
		scopes.put(WechatWorkappIdentity.class.getName(),getWechatWorkappIdentityListOfViewScope());
		scopes.put(WechatMiniappIdentity.class.getName(),getWechatMiniappIdentityListOfViewScope());
		scopes.put(KeypairIdentity.class.getName(),getKeypairIdentityListOfViewScope());
		scopes.put(PublicKeyType.class.getName(),getPublicKeyTypeListOfViewScope());
		scopes.put(TreeNode.class.getName(),getTreeNodeListOfViewScope());
	}

}
















