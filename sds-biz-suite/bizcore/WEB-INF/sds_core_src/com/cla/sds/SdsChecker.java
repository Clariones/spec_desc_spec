
package com.cla.sds;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
public class SdsChecker extends BaseChecker{

	
	public SdsChecker() {
		this.messageList = new ArrayList<Message>();
	}
	

	public static final String  ID_OF_PLATFORM ="platform.id";
	public SdsChecker checkIdOfPlatform(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PLATFORM ="platform.name";
	public SdsChecker checkNameOfPlatform(String name)
	{		
	 	checkStringLengthRange(name,2, 28,NAME_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PLATFORM ="platform.version";
	public SdsChecker checkVersionOfPlatform(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PLATFORM ); 		
		
		return this;
	}	

	public static final String  ID_OF_COMPANY ="company.id";
	public SdsChecker checkIdOfCompany(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_COMPANY ); 		
		
		return this;
	}	

	public static final String  NAME_OF_COMPANY ="company.name";
	public SdsChecker checkNameOfCompany(String name)
	{		
	 	checkStringLengthRange(name,2, 16,NAME_OF_COMPANY ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_COMPANY ="company.platform";
	public SdsChecker checkPlatformIdOfCompany(String platformId)
	{		
	 	checkIdOfCompany(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_COMPANY ="company.version";
	public SdsChecker checkVersionOfCompany(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_COMPANY ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER ="user.id";
	public SdsChecker checkIdOfUser(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER ); 		
		
		return this;
	}	

	public static final String  NAME_OF_USER ="user.name";
	public SdsChecker checkNameOfUser(String name)
	{		
	 	checkStringLengthRange(name,1, 12,NAME_OF_USER ); 		
		
		return this;
	}	

	public static final String  JOIN_TIME_OF_USER ="user.join_time";
	public SdsChecker checkJoinTimeOfUser(Date joinTime)
	{		
	 	checkDateRange(joinTime,parseDate("1900-01-01"), parseDate("2199-01-01"),JOIN_TIME_OF_USER ); 		
		
		return this;
	}	

	public static final String  COMPANY_OF_USER ="user.company";
	public SdsChecker checkCompanyIdOfUser(String companyId)
	{		
	 	checkIdOfUser(companyId ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_USER ="user.title";
	public SdsChecker checkTitleOfUser(String title)
	{		
	 	checkStringLengthRange(title,1, 24,TITLE_OF_USER ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER ="user.version";
	public SdsChecker checkVersionOfUser(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_PROJECT ="user_project.id";
	public SdsChecker checkIdOfUserProject(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_PROJECT ); 		
		
		return this;
	}	

	public static final String  USER_OF_USER_PROJECT ="user_project.user";
	public SdsChecker checkUserIdOfUserProject(String userId)
	{		
	 	checkIdOfUserProject(userId ); 		
		
		return this;
	}	

	public static final String  PROJECT_OF_USER_PROJECT ="user_project.project";
	public SdsChecker checkProjectIdOfUserProject(String projectId)
	{		
	 	checkIdOfUserProject(projectId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_PROJECT ="user_project.version";
	public SdsChecker checkVersionOfUserProject(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_PROJECT ); 		
		
		return this;
	}	

	public static final String  ID_OF_CHANGE_REQUEST_TYPE ="change_request_type.id";
	public SdsChecker checkIdOfChangeRequestType(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CHANGE_REQUEST_TYPE ="change_request_type.name";
	public SdsChecker checkNameOfChangeRequestType(String name)
	{		
	 	checkStringLengthRange(name,1, 100,NAME_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  CODE_OF_CHANGE_REQUEST_TYPE ="change_request_type.code";
	public SdsChecker checkCodeOfChangeRequestType(String code)
	{		
	 	checkStringLengthRange(code,1, 100,CODE_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  ICON_OF_CHANGE_REQUEST_TYPE ="change_request_type.icon";
	public SdsChecker checkIconOfChangeRequestType(String icon)
	{		
	 	checkStringLengthRange(icon,1, 100,ICON_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  DISPLAY_ORDER_OF_CHANGE_REQUEST_TYPE ="change_request_type.display_order";
	public SdsChecker checkDisplayOrderOfChangeRequestType(int displayOrder)
	{		
	 	checkIntegerRange(displayOrder,0, 6,DISPLAY_ORDER_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  BIND_TYPES_OF_CHANGE_REQUEST_TYPE ="change_request_type.bind_types";
	public SdsChecker checkBindTypesOfChangeRequestType(String bindTypes)
	{		
	 	checkLongtext(bindTypes,0, 1048576,BIND_TYPES_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  STEP_CONFIGURATION_OF_CHANGE_REQUEST_TYPE ="change_request_type.step_configuration";
	public SdsChecker checkStepConfigurationOfChangeRequestType(String stepConfiguration)
	{		
	 	checkLongtext(stepConfiguration,0, 1048576,STEP_CONFIGURATION_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_CHANGE_REQUEST_TYPE ="change_request_type.platform";
	public SdsChecker checkPlatformIdOfChangeRequestType(String platformId)
	{		
	 	checkIdOfChangeRequestType(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CHANGE_REQUEST_TYPE ="change_request_type.version";
	public SdsChecker checkVersionOfChangeRequestType(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CHANGE_REQUEST_TYPE ); 		
		
		return this;
	}	

	public static final String  ID_OF_CHANGE_REQUEST ="change_request.id";
	public SdsChecker checkIdOfChangeRequest(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CHANGE_REQUEST ="change_request.name";
	public SdsChecker checkNameOfChangeRequest(String name)
	{		
	 	checkStringLengthRange(name,1, 50,NAME_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  REQUEST_TYPE_OF_CHANGE_REQUEST ="change_request.request_type";
	public SdsChecker checkRequestTypeIdOfChangeRequest(String requestTypeId)
	{		
	 	checkIdOfChangeRequest(requestTypeId ); 		
		
		return this;
	}	

	public static final String  COMMITED_OF_CHANGE_REQUEST ="change_request.commited";
	public SdsChecker checkCommitedOfChangeRequest(boolean commited)
	{		
	 	checkBooleanRange(commited,0, false,COMMITED_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  PLATFORM_OF_CHANGE_REQUEST ="change_request.platform";
	public SdsChecker checkPlatformIdOfChangeRequest(String platformId)
	{		
	 	checkIdOfChangeRequest(platformId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CHANGE_REQUEST ="change_request.version";
	public SdsChecker checkVersionOfChangeRequest(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CHANGE_REQUEST ); 		
		
		return this;
	}	

	public static final String  ID_OF_EVENT_UPDATE_PROFILE ="event_update_profile.id";
	public SdsChecker checkIdOfEventUpdateProfile(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_EVENT_UPDATE_PROFILE ="event_update_profile.name";
	public SdsChecker checkNameOfEventUpdateProfile(String name)
	{		
	 	checkStringLengthRange(name,1, 30,NAME_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  AVANTAR_OF_EVENT_UPDATE_PROFILE ="event_update_profile.avantar";
	public SdsChecker checkAvantarOfEventUpdateProfile(String avantar)
	{		
	 	checkImage(avantar,0, 1,AVANTAR_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  FIELD_GROUP_OF_EVENT_UPDATE_PROFILE ="event_update_profile.field_group";
	public SdsChecker checkFieldGroupOfEventUpdateProfile(String fieldGroup)
	{		
	 	checkStringLengthRange(fieldGroup,5, 100,FIELD_GROUP_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  EVENT_INITIATOR_TYPE_OF_EVENT_UPDATE_PROFILE ="event_update_profile.event_initiator_type";
	public SdsChecker checkEventInitiatorTypeOfEventUpdateProfile(String eventInitiatorType)
	{		
	 	checkStringLengthRange(eventInitiatorType,1, 64,EVENT_INITIATOR_TYPE_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  EVENT_INITIATOR_ID_OF_EVENT_UPDATE_PROFILE ="event_update_profile.event_initiator_id";
	public SdsChecker checkEventInitiatorIdOfEventUpdateProfile(String eventInitiatorId)
	{		
	 	checkStringLengthRange(eventInitiatorId,1, 64,EVENT_INITIATOR_ID_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  CHANGE_REQUEST_OF_EVENT_UPDATE_PROFILE ="event_update_profile.change_request";
	public SdsChecker checkChangeRequestIdOfEventUpdateProfile(String changeRequestId)
	{		
	 	checkIdOfEventUpdateProfile(changeRequestId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_EVENT_UPDATE_PROFILE ="event_update_profile.version";
	public SdsChecker checkVersionOfEventUpdateProfile(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_EVENT_UPDATE_PROFILE ); 		
		
		return this;
	}	

	public static final String  ID_OF_PROJECT ="project.id";
	public SdsChecker checkIdOfProject(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PROJECT ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PROJECT ="project.name";
	public SdsChecker checkNameOfProject(String name)
	{		
	 	checkStringLengthRange(name,1, 12,NAME_OF_PROJECT ); 		
		
		return this;
	}	

	public static final String  COMPANY_OF_PROJECT ="project.company";
	public SdsChecker checkCompanyIdOfProject(String companyId)
	{		
	 	checkIdOfProject(companyId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PROJECT ="project.version";
	public SdsChecker checkVersionOfProject(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PROJECT ); 		
		
		return this;
	}	

	public static final String  ID_OF_PAGE_FLOW_SPEC ="page_flow_spec.id";
	public SdsChecker checkIdOfPageFlowSpec(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PAGE_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  SCENE_OF_PAGE_FLOW_SPEC ="page_flow_spec.scene";
	public SdsChecker checkSceneOfPageFlowSpec(String scene)
	{		
	 	checkStringLengthRange(scene,1, 8,SCENE_OF_PAGE_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  BRIEF_OF_PAGE_FLOW_SPEC ="page_flow_spec.brief";
	public SdsChecker checkBriefOfPageFlowSpec(String brief)
	{		
	 	checkLongtext(brief,0, 1048576,BRIEF_OF_PAGE_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  OWNER_OF_PAGE_FLOW_SPEC ="page_flow_spec.owner";
	public SdsChecker checkOwnerIdOfPageFlowSpec(String ownerId)
	{		
	 	checkIdOfPageFlowSpec(ownerId ); 		
		
		return this;
	}	

	public static final String  PROJECT_OF_PAGE_FLOW_SPEC ="page_flow_spec.project";
	public SdsChecker checkProjectIdOfPageFlowSpec(String projectId)
	{		
	 	checkIdOfPageFlowSpec(projectId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PAGE_FLOW_SPEC ="page_flow_spec.version";
	public SdsChecker checkVersionOfPageFlowSpec(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PAGE_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  ID_OF_WORK_FLOW_SPEC ="work_flow_spec.id";
	public SdsChecker checkIdOfWorkFlowSpec(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_WORK_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  SCENE_OF_WORK_FLOW_SPEC ="work_flow_spec.scene";
	public SdsChecker checkSceneOfWorkFlowSpec(String scene)
	{		
	 	checkStringLengthRange(scene,1, 8,SCENE_OF_WORK_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  BRIEF_OF_WORK_FLOW_SPEC ="work_flow_spec.brief";
	public SdsChecker checkBriefOfWorkFlowSpec(String brief)
	{		
	 	checkLongtext(brief,0, 1048576,BRIEF_OF_WORK_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  OWNER_OF_WORK_FLOW_SPEC ="work_flow_spec.owner";
	public SdsChecker checkOwnerIdOfWorkFlowSpec(String ownerId)
	{		
	 	checkIdOfWorkFlowSpec(ownerId ); 		
		
		return this;
	}	

	public static final String  PROJECT_OF_WORK_FLOW_SPEC ="work_flow_spec.project";
	public SdsChecker checkProjectIdOfWorkFlowSpec(String projectId)
	{		
	 	checkIdOfWorkFlowSpec(projectId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_WORK_FLOW_SPEC ="work_flow_spec.version";
	public SdsChecker checkVersionOfWorkFlowSpec(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_WORK_FLOW_SPEC ); 		
		
		return this;
	}	

	public static final String  ID_OF_CHANGE_REQUEST_SPEC ="change_request_spec.id";
	public SdsChecker checkIdOfChangeRequestSpec(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CHANGE_REQUEST_SPEC ); 		
		
		return this;
	}	

	public static final String  SCENE_OF_CHANGE_REQUEST_SPEC ="change_request_spec.scene";
	public SdsChecker checkSceneOfChangeRequestSpec(String scene)
	{		
	 	checkStringLengthRange(scene,1, 8,SCENE_OF_CHANGE_REQUEST_SPEC ); 		
		
		return this;
	}	

	public static final String  BRIEF_OF_CHANGE_REQUEST_SPEC ="change_request_spec.brief";
	public SdsChecker checkBriefOfChangeRequestSpec(String brief)
	{		
	 	checkLongtext(brief,0, 1048576,BRIEF_OF_CHANGE_REQUEST_SPEC ); 		
		
		return this;
	}	

	public static final String  OWNER_OF_CHANGE_REQUEST_SPEC ="change_request_spec.owner";
	public SdsChecker checkOwnerIdOfChangeRequestSpec(String ownerId)
	{		
	 	checkIdOfChangeRequestSpec(ownerId ); 		
		
		return this;
	}	

	public static final String  PROJECT_OF_CHANGE_REQUEST_SPEC ="change_request_spec.project";
	public SdsChecker checkProjectIdOfChangeRequestSpec(String projectId)
	{		
	 	checkIdOfChangeRequestSpec(projectId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CHANGE_REQUEST_SPEC ="change_request_spec.version";
	public SdsChecker checkVersionOfChangeRequestSpec(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CHANGE_REQUEST_SPEC ); 		
		
		return this;
	}	

	public static final String  ID_OF_PAGE_CONTENT_SPEC ="page_content_spec.id";
	public SdsChecker checkIdOfPageContentSpec(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PAGE_CONTENT_SPEC ); 		
		
		return this;
	}	

	public static final String  SCENE_OF_PAGE_CONTENT_SPEC ="page_content_spec.scene";
	public SdsChecker checkSceneOfPageContentSpec(String scene)
	{		
	 	checkStringLengthRange(scene,1, 8,SCENE_OF_PAGE_CONTENT_SPEC ); 		
		
		return this;
	}	

	public static final String  BRIEF_OF_PAGE_CONTENT_SPEC ="page_content_spec.brief";
	public SdsChecker checkBriefOfPageContentSpec(String brief)
	{		
	 	checkLongtext(brief,0, 1048576,BRIEF_OF_PAGE_CONTENT_SPEC ); 		
		
		return this;
	}	

	public static final String  OWNER_OF_PAGE_CONTENT_SPEC ="page_content_spec.owner";
	public SdsChecker checkOwnerIdOfPageContentSpec(String ownerId)
	{		
	 	checkIdOfPageContentSpec(ownerId ); 		
		
		return this;
	}	

	public static final String  PROJECT_OF_PAGE_CONTENT_SPEC ="page_content_spec.project";
	public SdsChecker checkProjectIdOfPageContentSpec(String projectId)
	{		
	 	checkIdOfPageContentSpec(projectId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PAGE_CONTENT_SPEC ="page_content_spec.version";
	public SdsChecker checkVersionOfPageContentSpec(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PAGE_CONTENT_SPEC ); 		
		
		return this;
	}	

	public static final String  ID_OF_MOBILE_APP ="mobile_app.id";
	public SdsChecker checkIdOfMobileApp(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_MOBILE_APP ); 		
		
		return this;
	}	

	public static final String  NAME_OF_MOBILE_APP ="mobile_app.name";
	public SdsChecker checkNameOfMobileApp(String name)
	{		
	 	checkStringLengthRange(name,2, 20,NAME_OF_MOBILE_APP ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_MOBILE_APP ="mobile_app.version";
	public SdsChecker checkVersionOfMobileApp(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_MOBILE_APP ); 		
		
		return this;
	}	

	public static final String  ID_OF_PAGE ="page.id";
	public SdsChecker checkIdOfPage(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PAGE ); 		
		
		return this;
	}	

	public static final String  PAGE_TITLE_OF_PAGE ="page.page_title";
	public SdsChecker checkPageTitleOfPage(String pageTitle)
	{		
	 	checkStringLengthRange(pageTitle,1, 10,PAGE_TITLE_OF_PAGE ); 		
		
		return this;
	}	

	public static final String  LINK_TO_URL_OF_PAGE ="page.link_to_url";
	public SdsChecker checkLinkToUrlOfPage(String linkToUrl)
	{		
	 	checkStringLengthRange(linkToUrl,0, 512,LINK_TO_URL_OF_PAGE ); 		
		
		return this;
	}	

	public static final String  PAGE_TYPE_OF_PAGE ="page.page_type";
	public SdsChecker checkPageTypeIdOfPage(String pageTypeId)
	{		
	 	checkIdOfPage(pageTypeId ); 		
		
		return this;
	}	

	public static final String  DISPLAY_ORDER_OF_PAGE ="page.display_order";
	public SdsChecker checkDisplayOrderOfPage(int displayOrder)
	{		
	 	checkIntegerRange(displayOrder,0, 10000,DISPLAY_ORDER_OF_PAGE ); 		
		
		return this;
	}	

	public static final String  MOBILE_APP_OF_PAGE ="page.mobile_app";
	public SdsChecker checkMobileAppIdOfPage(String mobileAppId)
	{		
	 	checkIdOfPage(mobileAppId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PAGE ="page.version";
	public SdsChecker checkVersionOfPage(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PAGE ); 		
		
		return this;
	}	

	public static final String  ID_OF_PAGE_TYPE ="page_type.id";
	public SdsChecker checkIdOfPageType(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PAGE_TYPE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PAGE_TYPE ="page_type.name";
	public SdsChecker checkNameOfPageType(String name)
	{		
	 	checkStringLengthRange(name,1, 40,NAME_OF_PAGE_TYPE ); 		
		
		return this;
	}	

	public static final String  CODE_OF_PAGE_TYPE ="page_type.code";
	public SdsChecker checkCodeOfPageType(String code)
	{		
	 	checkStringLengthRange(code,1, 40,CODE_OF_PAGE_TYPE ); 		
		
		return this;
	}	

	public static final String  MOBILE_APP_OF_PAGE_TYPE ="page_type.mobile_app";
	public SdsChecker checkMobileAppIdOfPageType(String mobileAppId)
	{		
	 	checkIdOfPageType(mobileAppId ); 		
		
		return this;
	}	

	public static final String  FOOTER_TAB_OF_PAGE_TYPE ="page_type.footer_tab";
	public SdsChecker checkFooterTabOfPageType(boolean footerTab)
	{		
	 	checkBooleanRange(footerTab,0, true|false,FOOTER_TAB_OF_PAGE_TYPE ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PAGE_TYPE ="page_type.version";
	public SdsChecker checkVersionOfPageType(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PAGE_TYPE ); 		
		
		return this;
	}	

	public static final String  ID_OF_SLIDE ="slide.id";
	public SdsChecker checkIdOfSlide(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_SLIDE ="slide.name";
	public SdsChecker checkNameOfSlide(String name)
	{		
	 	checkStringLengthRange(name,0, 40,NAME_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  DISPLAY_ORDER_OF_SLIDE ="slide.display_order";
	public SdsChecker checkDisplayOrderOfSlide(int displayOrder)
	{		
	 	checkIntegerRange(displayOrder,0, 10000,DISPLAY_ORDER_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  IMAGE_URL_OF_SLIDE ="slide.image_url";
	public SdsChecker checkImageUrlOfSlide(String imageUrl)
	{		
	 	checkImage(imageUrl,0, 512,IMAGE_URL_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  VIDEO_URL_OF_SLIDE ="slide.video_url";
	public SdsChecker checkVideoUrlOfSlide(String videoUrl)
	{		
	 	checkImage(videoUrl,0, 512,VIDEO_URL_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  LINK_TO_URL_OF_SLIDE ="slide.link_to_url";
	public SdsChecker checkLinkToUrlOfSlide(String linkToUrl)
	{
		if(linkToUrl == null) {
			return this;
		}
		
	 	checkStringLengthRange(linkToUrl,0, 512,LINK_TO_URL_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  PAGE_OF_SLIDE ="slide.page";
	public SdsChecker checkPageIdOfSlide(String pageId)
	{		
	 	checkIdOfSlide(pageId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SLIDE ="slide.version";
	public SdsChecker checkVersionOfSlide(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SLIDE ); 		
		
		return this;
	}	

	public static final String  ID_OF_UI_ACTION ="ui_action.id";
	public SdsChecker checkIdOfUiAction(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  CODE_OF_UI_ACTION ="ui_action.code";
	public SdsChecker checkCodeOfUiAction(String code)
	{		
	 	checkStringLengthRange(code,1, 40,CODE_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  ICON_OF_UI_ACTION ="ui_action.icon";
	public SdsChecker checkIconOfUiAction(String icon)
	{		
	 	checkStringLengthRange(icon,1, 512,ICON_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_UI_ACTION ="ui_action.title";
	public SdsChecker checkTitleOfUiAction(String title)
	{		
	 	checkStringLengthRange(title,1, 40,TITLE_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  DISPLAY_ORDER_OF_UI_ACTION ="ui_action.display_order";
	public SdsChecker checkDisplayOrderOfUiAction(int displayOrder)
	{		
	 	checkIntegerRange(displayOrder,0, 10000,DISPLAY_ORDER_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  BRIEF_OF_UI_ACTION ="ui_action.brief";
	public SdsChecker checkBriefOfUiAction(String brief)
	{		
	 	checkStringLengthRange(brief,0, 200,BRIEF_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  IMAGE_URL_OF_UI_ACTION ="ui_action.image_url";
	public SdsChecker checkImageUrlOfUiAction(String imageUrl)
	{		
	 	checkImage(imageUrl,0, 512,IMAGE_URL_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  LINK_TO_URL_OF_UI_ACTION ="ui_action.link_to_url";
	public SdsChecker checkLinkToUrlOfUiAction(String linkToUrl)
	{		
	 	checkStringLengthRange(linkToUrl,0, 512,LINK_TO_URL_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  EXTRA_DATA_OF_UI_ACTION ="ui_action.extra_data";
	public SdsChecker checkExtraDataOfUiAction(String extraData)
	{		
	 	checkLongtext(extraData,0, 1048576,EXTRA_DATA_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  PAGE_OF_UI_ACTION ="ui_action.page";
	public SdsChecker checkPageIdOfUiAction(String pageId)
	{		
	 	checkIdOfUiAction(pageId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_UI_ACTION ="ui_action.version";
	public SdsChecker checkVersionOfUiAction(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_UI_ACTION ); 		
		
		return this;
	}	

	public static final String  ID_OF_SECTION ="section.id";
	public SdsChecker checkIdOfSection(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_SECTION ="section.title";
	public SdsChecker checkTitleOfSection(String title)
	{		
	 	checkStringLengthRange(title,1, 40,TITLE_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  BRIEF_OF_SECTION ="section.brief";
	public SdsChecker checkBriefOfSection(String brief)
	{		
	 	checkStringLengthRange(brief,0, 200,BRIEF_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  ICON_OF_SECTION ="section.icon";
	public SdsChecker checkIconOfSection(String icon)
	{		
	 	checkImage(icon,0, 512,ICON_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  DISPLAY_ORDER_OF_SECTION ="section.display_order";
	public SdsChecker checkDisplayOrderOfSection(int displayOrder)
	{		
	 	checkIntegerRange(displayOrder,0, 10000,DISPLAY_ORDER_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  VIEW_GROUP_OF_SECTION ="section.view_group";
	public SdsChecker checkViewGroupOfSection(String viewGroup)
	{		
	 	checkStringLengthRange(viewGroup,0, 40,VIEW_GROUP_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  LINK_TO_URL_OF_SECTION ="section.link_to_url";
	public SdsChecker checkLinkToUrlOfSection(String linkToUrl)
	{		
	 	checkStringLengthRange(linkToUrl,0, 512,LINK_TO_URL_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  PAGE_OF_SECTION ="section.page";
	public SdsChecker checkPageIdOfSection(String pageId)
	{		
	 	checkIdOfSection(pageId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SECTION ="section.version";
	public SdsChecker checkVersionOfSection(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SECTION ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_DOMAIN ="user_domain.id";
	public SdsChecker checkIdOfUserDomain(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  NAME_OF_USER_DOMAIN ="user_domain.name";
	public SdsChecker checkNameOfUserDomain(String name)
	{		
	 	checkStringLengthRange(name,2, 16,NAME_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_DOMAIN ="user_domain.version";
	public SdsChecker checkVersionOfUserDomain(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_DOMAIN ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_WHITE_LIST ="user_white_list.id";
	public SdsChecker checkIdOfUserWhiteList(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  USER_IDENTITY_OF_USER_WHITE_LIST ="user_white_list.user_identity";
	public SdsChecker checkUserIdentityOfUserWhiteList(String userIdentity)
	{		
	 	checkStringLengthRange(userIdentity,1, 40,USER_IDENTITY_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  USER_SPECIAL_FUNCTIONS_OF_USER_WHITE_LIST ="user_white_list.user_special_functions";
	public SdsChecker checkUserSpecialFunctionsOfUserWhiteList(String userSpecialFunctions)
	{		
	 	checkStringLengthRange(userSpecialFunctions,1, 200,USER_SPECIAL_FUNCTIONS_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_USER_WHITE_LIST ="user_white_list.domain";
	public SdsChecker checkDomainIdOfUserWhiteList(String domainId)
	{		
	 	checkIdOfUserWhiteList(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_WHITE_LIST ="user_white_list.version";
	public SdsChecker checkVersionOfUserWhiteList(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_WHITE_LIST ); 		
		
		return this;
	}	

	public static final String  ID_OF_SEC_USER ="sec_user.id";
	public SdsChecker checkIdOfSecUser(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  LOGIN_OF_SEC_USER ="sec_user.login";
	public SdsChecker checkLoginOfSecUser(String login)
	{		
	 	checkStringLengthRange(login,0, 256,LOGIN_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  MOBILE_OF_SEC_USER ="sec_user.mobile";
	public SdsChecker checkMobileOfSecUser(String mobile)
	{		
	 	checkChinaMobilePhone(mobile,0, 11,MOBILE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  EMAIL_OF_SEC_USER ="sec_user.email";
	public SdsChecker checkEmailOfSecUser(String email)
	{		
	 	checkEmail(email,0, 256,EMAIL_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  PWD_OF_SEC_USER ="sec_user.pwd";
	public SdsChecker checkPwdOfSecUser(String pwd)
	{		
	 	checkPassword(pwd,3, 28,PWD_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  WEIXIN_OPENID_OF_SEC_USER ="sec_user.weixin_openid";
	public SdsChecker checkWeixinOpenidOfSecUser(String weixinOpenid)
	{		
	 	checkStringLengthRange(weixinOpenid,0, 128,WEIXIN_OPENID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  WEIXIN_APPID_OF_SEC_USER ="sec_user.weixin_appid";
	public SdsChecker checkWeixinAppidOfSecUser(String weixinAppid)
	{		
	 	checkStringLengthRange(weixinAppid,0, 128,WEIXIN_APPID_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  ACCESS_TOKEN_OF_SEC_USER ="sec_user.access_token";
	public SdsChecker checkAccessTokenOfSecUser(String accessToken)
	{		
	 	checkStringLengthRange(accessToken,0, 128,ACCESS_TOKEN_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  VERIFICATION_CODE_OF_SEC_USER ="sec_user.verification_code";
	public SdsChecker checkVerificationCodeOfSecUser(int verificationCode)
	{		
	 	checkIntegerRange(verificationCode,0, 9999999,VERIFICATION_CODE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  VERIFICATION_CODE_EXPIRE_OF_SEC_USER ="sec_user.verification_code_expire";
	public SdsChecker checkVerificationCodeExpireOfSecUser(DateTime verificationCodeExpire)
	{		
	 	checkDateTime(verificationCodeExpire,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T09:09:09"),VERIFICATION_CODE_EXPIRE_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_SEC_USER ="sec_user.last_login_time";
	public SdsChecker checkLastLoginTimeOfSecUser(DateTime lastLoginTime)
	{
		if(lastLoginTime == null) {
			return this;
		}
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2099-12-31T09:09:09"),LAST_LOGIN_TIME_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_SEC_USER ="sec_user.domain";
	public SdsChecker checkDomainIdOfSecUser(String domainId)
	{		
	 	checkIdOfSecUser(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_SEC_USER ="sec_user.version";
	public SdsChecker checkVersionOfSecUser(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_SEC_USER ); 		
		
		return this;
	}	

	public static final String  ID_OF_USER_APP ="user_app.id";
	public SdsChecker checkIdOfUserApp(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  TITLE_OF_USER_APP ="user_app.title";
	public SdsChecker checkTitleOfUserApp(String title)
	{		
	 	checkStringLengthRange(title,1, 300,TITLE_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_USER_APP ="user_app.sec_user";
	public SdsChecker checkSecUserIdOfUserApp(String secUserId)
	{		
	 	checkIdOfUserApp(secUserId ); 		
		
		return this;
	}	

	public static final String  APP_ICON_OF_USER_APP ="user_app.app_icon";
	public SdsChecker checkAppIconOfUserApp(String appIcon)
	{		
	 	checkStringLengthRange(appIcon,2, 36,APP_ICON_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  FULL_ACCESS_OF_USER_APP ="user_app.full_access";
	public SdsChecker checkFullAccessOfUserApp(boolean fullAccess)
	{		
	 	checkBooleanRange(fullAccess,0, true,FULL_ACCESS_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  PERMISSION_OF_USER_APP ="user_app.permission";
	public SdsChecker checkPermissionOfUserApp(String permission)
	{		
	 	checkStringLengthRange(permission,2, 16,PERMISSION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  OBJECT_TYPE_OF_USER_APP ="user_app.object_type";
	public SdsChecker checkObjectTypeOfUserApp(String objectType)
	{		
	 	checkStringLengthRange(objectType,1, 100,OBJECT_TYPE_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  OBJECT_ID_OF_USER_APP ="user_app.object_id";
	public SdsChecker checkObjectIdOfUserApp(String objectId)
	{		
	 	checkStringLengthRange(objectId,4, 40,OBJECT_ID_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  LOCATION_OF_USER_APP ="user_app.location";
	public SdsChecker checkLocationOfUserApp(String location)
	{		
	 	checkStringLengthRange(location,4, 48,LOCATION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_USER_APP ="user_app.version";
	public SdsChecker checkVersionOfUserApp(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_USER_APP ); 		
		
		return this;
	}	

	public static final String  ID_OF_QUICK_LINK ="quick_link.id";
	public SdsChecker checkIdOfQuickLink(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  NAME_OF_QUICK_LINK ="quick_link.name";
	public SdsChecker checkNameOfQuickLink(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  ICON_OF_QUICK_LINK ="quick_link.icon";
	public SdsChecker checkIconOfQuickLink(String icon)
	{		
	 	checkStringLengthRange(icon,1, 200,ICON_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  IMAGE_PATH_OF_QUICK_LINK ="quick_link.image_path";
	public SdsChecker checkImagePathOfQuickLink(String imagePath)
	{		
	 	checkImage(imagePath,0, 512,IMAGE_PATH_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  LINK_TARGET_OF_QUICK_LINK ="quick_link.link_target";
	public SdsChecker checkLinkTargetOfQuickLink(String linkTarget)
	{		
	 	checkStringLengthRange(linkTarget,1, 200,LINK_TARGET_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  APP_OF_QUICK_LINK ="quick_link.app";
	public SdsChecker checkAppIdOfQuickLink(String appId)
	{		
	 	checkIdOfQuickLink(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_QUICK_LINK ="quick_link.version";
	public SdsChecker checkVersionOfQuickLink(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_QUICK_LINK ); 		
		
		return this;
	}	

	public static final String  ID_OF_LIST_ACCESS ="list_access.id";
	public SdsChecker checkIdOfListAccess(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  NAME_OF_LIST_ACCESS ="list_access.name";
	public SdsChecker checkNameOfListAccess(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  INTERNAL_NAME_OF_LIST_ACCESS ="list_access.internal_name";
	public SdsChecker checkInternalNameOfListAccess(String internalName)
	{		
	 	checkStringLengthRange(internalName,1, 200,INTERNAL_NAME_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  READ_PERMISSION_OF_LIST_ACCESS ="list_access.read_permission";
	public SdsChecker checkReadPermissionOfListAccess(boolean readPermission)
	{		
	 	checkBooleanRange(readPermission,0, true,READ_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  CREATE_PERMISSION_OF_LIST_ACCESS ="list_access.create_permission";
	public SdsChecker checkCreatePermissionOfListAccess(boolean createPermission)
	{		
	 	checkBooleanRange(createPermission,0, true,CREATE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  DELETE_PERMISSION_OF_LIST_ACCESS ="list_access.delete_permission";
	public SdsChecker checkDeletePermissionOfListAccess(boolean deletePermission)
	{		
	 	checkBooleanRange(deletePermission,0, true,DELETE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  UPDATE_PERMISSION_OF_LIST_ACCESS ="list_access.update_permission";
	public SdsChecker checkUpdatePermissionOfListAccess(boolean updatePermission)
	{		
	 	checkBooleanRange(updatePermission,0, true,UPDATE_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  EXECUTION_PERMISSION_OF_LIST_ACCESS ="list_access.execution_permission";
	public SdsChecker checkExecutionPermissionOfListAccess(boolean executionPermission)
	{		
	 	checkBooleanRange(executionPermission,0, true,EXECUTION_PERMISSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  APP_OF_LIST_ACCESS ="list_access.app";
	public SdsChecker checkAppIdOfListAccess(String appId)
	{		
	 	checkIdOfListAccess(appId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LIST_ACCESS ="list_access.version";
	public SdsChecker checkVersionOfListAccess(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LIST_ACCESS ); 		
		
		return this;
	}	

	public static final String  ID_OF_LOGIN_HISTORY ="login_history.id";
	public SdsChecker checkIdOfLoginHistory(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  FROM_IP_OF_LOGIN_HISTORY ="login_history.from_ip";
	public SdsChecker checkFromIpOfLoginHistory(String fromIp)
	{		
	 	checkStringLengthRange(fromIp,5, 44,FROM_IP_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  DESCRIPTION_OF_LOGIN_HISTORY ="login_history.description";
	public SdsChecker checkDescriptionOfLoginHistory(String description)
	{		
	 	checkStringLengthRange(description,2, 16,DESCRIPTION_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_LOGIN_HISTORY ="login_history.sec_user";
	public SdsChecker checkSecUserIdOfLoginHistory(String secUserId)
	{		
	 	checkIdOfLoginHistory(secUserId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_LOGIN_HISTORY ="login_history.version";
	public SdsChecker checkVersionOfLoginHistory(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_LOGIN_HISTORY ); 		
		
		return this;
	}	

	public static final String  ID_OF_CANDIDATE_CONTAINER ="candidate_container.id";
	public SdsChecker checkIdOfCandidateContainer(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CANDIDATE_CONTAINER ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CANDIDATE_CONTAINER ="candidate_container.name";
	public SdsChecker checkNameOfCandidateContainer(String name)
	{		
	 	checkStringLengthRange(name,2, 28,NAME_OF_CANDIDATE_CONTAINER ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CANDIDATE_CONTAINER ="candidate_container.version";
	public SdsChecker checkVersionOfCandidateContainer(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CANDIDATE_CONTAINER ); 		
		
		return this;
	}	

	public static final String  ID_OF_CANDIDATE_ELEMENT ="candidate_element.id";
	public SdsChecker checkIdOfCandidateElement(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  NAME_OF_CANDIDATE_ELEMENT ="candidate_element.name";
	public SdsChecker checkNameOfCandidateElement(String name)
	{		
	 	checkStringLengthRange(name,1, 200,NAME_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  TYPE_OF_CANDIDATE_ELEMENT ="candidate_element.type";
	public SdsChecker checkTypeOfCandidateElement(String type)
	{		
	 	checkStringLengthRange(type,1, 200,TYPE_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  IMAGE_OF_CANDIDATE_ELEMENT ="candidate_element.image";
	public SdsChecker checkImageOfCandidateElement(String image)
	{		
	 	checkImage(image,0, 512,IMAGE_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  CONTAINER_OF_CANDIDATE_ELEMENT ="candidate_element.container";
	public SdsChecker checkContainerIdOfCandidateElement(String containerId)
	{		
	 	checkIdOfCandidateElement(containerId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_CANDIDATE_ELEMENT ="candidate_element.version";
	public SdsChecker checkVersionOfCandidateElement(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_CANDIDATE_ELEMENT ); 		
		
		return this;
	}	

	public static final String  ID_OF_WECHAT_WORKAPP_IDENTITY ="wechat_workapp_identity.id";
	public SdsChecker checkIdOfWechatWorkappIdentity(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_WECHAT_WORKAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  CORP_ID_OF_WECHAT_WORKAPP_IDENTITY ="wechat_workapp_identity.corp_id";
	public SdsChecker checkCorpIdOfWechatWorkappIdentity(String corpId)
	{		
	 	checkStringLengthRange(corpId,0, 100,CORP_ID_OF_WECHAT_WORKAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  USER_ID_OF_WECHAT_WORKAPP_IDENTITY ="wechat_workapp_identity.user_id";
	public SdsChecker checkUserIdOfWechatWorkappIdentity(String userId)
	{		
	 	checkStringLengthRange(userId,1, 100,USER_ID_OF_WECHAT_WORKAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_WECHAT_WORKAPP_IDENTITY ="wechat_workapp_identity.sec_user";
	public SdsChecker checkSecUserIdOfWechatWorkappIdentity(String secUserId)
	{		
	 	checkIdOfWechatWorkappIdentity(secUserId ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_WECHAT_WORKAPP_IDENTITY ="wechat_workapp_identity.last_login_time";
	public SdsChecker checkLastLoginTimeOfWechatWorkappIdentity(DateTime lastLoginTime)
	{
		if(lastLoginTime == null) {
			return this;
		}
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2100-01-01T00:00:00"),LAST_LOGIN_TIME_OF_WECHAT_WORKAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_WECHAT_WORKAPP_IDENTITY ="wechat_workapp_identity.version";
	public SdsChecker checkVersionOfWechatWorkappIdentity(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_WECHAT_WORKAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  ID_OF_WECHAT_MINIAPP_IDENTITY ="wechat_miniapp_identity.id";
	public SdsChecker checkIdOfWechatMiniappIdentity(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_WECHAT_MINIAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  OPEN_ID_OF_WECHAT_MINIAPP_IDENTITY ="wechat_miniapp_identity.open_id";
	public SdsChecker checkOpenIdOfWechatMiniappIdentity(String openId)
	{		
	 	checkStringLengthRange(openId,5, 128,OPEN_ID_OF_WECHAT_MINIAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  APP_ID_OF_WECHAT_MINIAPP_IDENTITY ="wechat_miniapp_identity.app_id";
	public SdsChecker checkAppIdOfWechatMiniappIdentity(String appId)
	{		
	 	checkStringLengthRange(appId,5, 128,APP_ID_OF_WECHAT_MINIAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_WECHAT_MINIAPP_IDENTITY ="wechat_miniapp_identity.sec_user";
	public SdsChecker checkSecUserIdOfWechatMiniappIdentity(String secUserId)
	{		
	 	checkIdOfWechatMiniappIdentity(secUserId ); 		
		
		return this;
	}	

	public static final String  LAST_LOGIN_TIME_OF_WECHAT_MINIAPP_IDENTITY ="wechat_miniapp_identity.last_login_time";
	public SdsChecker checkLastLoginTimeOfWechatMiniappIdentity(DateTime lastLoginTime)
	{
		if(lastLoginTime == null) {
			return this;
		}
		
	 	checkDateTime(lastLoginTime,parseTimestamp("1900-01-01T00:00:00"), parseTimestamp("2100-01-01T00:00:00"),LAST_LOGIN_TIME_OF_WECHAT_MINIAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_WECHAT_MINIAPP_IDENTITY ="wechat_miniapp_identity.version";
	public SdsChecker checkVersionOfWechatMiniappIdentity(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_WECHAT_MINIAPP_IDENTITY ); 		
		
		return this;
	}	

	public static final String  ID_OF_KEYPAIR_IDENTITY ="keypair_identity.id";
	public SdsChecker checkIdOfKeypairIdentity(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_KEYPAIR_IDENTITY ); 		
		
		return this;
	}	

	public static final String  PUBLIC_KEY_OF_KEYPAIR_IDENTITY ="keypair_identity.public_key";
	public SdsChecker checkPublicKeyOfKeypairIdentity(String publicKey)
	{		
	 	checkLongtext(publicKey,0, 1048576,PUBLIC_KEY_OF_KEYPAIR_IDENTITY ); 		
		
		return this;
	}	

	public static final String  KEY_TYPE_OF_KEYPAIR_IDENTITY ="keypair_identity.key_type";
	public SdsChecker checkKeyTypeIdOfKeypairIdentity(String keyTypeId)
	{		
	 	checkIdOfKeypairIdentity(keyTypeId ); 		
		
		return this;
	}	

	public static final String  SEC_USER_OF_KEYPAIR_IDENTITY ="keypair_identity.sec_user";
	public SdsChecker checkSecUserIdOfKeypairIdentity(String secUserId)
	{		
	 	checkIdOfKeypairIdentity(secUserId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_KEYPAIR_IDENTITY ="keypair_identity.version";
	public SdsChecker checkVersionOfKeypairIdentity(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_KEYPAIR_IDENTITY ); 		
		
		return this;
	}	

	public static final String  ID_OF_PUBLIC_KEY_TYPE ="public_key_type.id";
	public SdsChecker checkIdOfPublicKeyType(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_PUBLIC_KEY_TYPE ); 		
		
		return this;
	}	

	public static final String  NAME_OF_PUBLIC_KEY_TYPE ="public_key_type.name";
	public SdsChecker checkNameOfPublicKeyType(String name)
	{		
	 	checkStringLengthRange(name,1, 12,NAME_OF_PUBLIC_KEY_TYPE ); 		
		
		return this;
	}	

	public static final String  CODE_OF_PUBLIC_KEY_TYPE ="public_key_type.code";
	public SdsChecker checkCodeOfPublicKeyType(String code)
	{		
	 	checkStringLengthRange(code,1, 12,CODE_OF_PUBLIC_KEY_TYPE ); 		
		
		return this;
	}	

	public static final String  DOMAIN_OF_PUBLIC_KEY_TYPE ="public_key_type.domain";
	public SdsChecker checkDomainIdOfPublicKeyType(String domainId)
	{		
	 	checkIdOfPublicKeyType(domainId ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_PUBLIC_KEY_TYPE ="public_key_type.version";
	public SdsChecker checkVersionOfPublicKeyType(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_PUBLIC_KEY_TYPE ); 		
		
		return this;
	}	

	public static final String  ID_OF_TREE_NODE ="tree_node.id";
	public SdsChecker checkIdOfTreeNode(String id)
	{		
	 	checkStringLengthRange(id,2, 64,ID_OF_TREE_NODE ); 		
		
		return this;
	}	

	public static final String  NODE_ID_OF_TREE_NODE ="tree_node.node_id";
	public SdsChecker checkNodeIdOfTreeNode(String nodeId)
	{		
	 	checkStringLengthRange(nodeId,3, 40,NODE_ID_OF_TREE_NODE ); 		
		
		return this;
	}	

	public static final String  NODE_TYPE_OF_TREE_NODE ="tree_node.node_type";
	public SdsChecker checkNodeTypeOfTreeNode(String nodeType)
	{		
	 	checkStringLengthRange(nodeType,3, 32,NODE_TYPE_OF_TREE_NODE ); 		
		
		return this;
	}	

	public static final String  LEFT_VALUE_OF_TREE_NODE ="tree_node.left_value";
	public SdsChecker checkLeftValueOfTreeNode(int leftValue)
	{		
	 	checkIntegerRange(leftValue,1, 10000000,LEFT_VALUE_OF_TREE_NODE ); 		
		
		return this;
	}	

	public static final String  RIGHT_VALUE_OF_TREE_NODE ="tree_node.right_value";
	public SdsChecker checkRightValueOfTreeNode(int rightValue)
	{		
	 	checkIntegerRange(rightValue,2, 10000000,RIGHT_VALUE_OF_TREE_NODE ); 		
		
		return this;
	}	

	public static final String  VERSION_OF_TREE_NODE ="tree_node.version";
	public SdsChecker checkVersionOfTreeNode(int version)
	{		
	 	checkIntegerRange(version,0, Integer.MAX_VALUE,VERSION_OF_TREE_NODE ); 		
		
		return this;
	}	
}



















