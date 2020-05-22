package com.cla.sds;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
public class SdsObjectChecker extends SdsChecker{

	Set<BaseEntity> checkedObjectSet;
	
	protected void markAsChecked(BaseEntity baseEntity) {
		if(checkedObjectSet==null) {
			checkedObjectSet =  new HashSet<BaseEntity>();
		}
		checkedObjectSet.add(baseEntity);
		
		
	}
	
	protected boolean isChecked(BaseEntity baseEntity) {
		if(checkedObjectSet==null) {
			return false;
			
		}
		return checkedObjectSet.contains(baseEntity);
	}
	@FunctionalInterface
	public interface CheckerParameterFunction<P1> {
		SdsChecker apply(P1 valueToCheck);
	}
	@FunctionalInterface
	public interface AssignParameterFunction {
		SdsObjectChecker apply(BaseEntity targetEntity);
	}
	
	protected boolean isReferenceObject(BaseEntity target) {
		
		if(target.getId()==null) {
			return false;
		}
		if(target.getId().isEmpty()) {
			return false;
		}
		if(target.getVersion() > 0) {
			return false;
		}
		
		return true;
		
	}
	protected boolean isObjectForCreate(BaseEntity target) {
		if(target.getVersion() > 0) {
			return false;
		}
		if(target.getId()==null) {
			return true;
		}
		if(!target.getId().isEmpty()) {
			return false;
		}
		
		
		return true;
		
	}
	protected void setEntityProperty(BaseEntity targetEntity, String property, Object value) {
		if(!targetEntity.isChanged()) {
			return;
		}
		try {
			targetEntity.setPropertyOf(property, value);
		} catch (Exception e) {
			throw new IllegalArgumentException(concat("set property <",property,"> with value ",value.toString()," of ",targetEntity.toString()," failed"));
		}
		
	}
	
	public <T> SdsObjectChecker commonObjectPropertyAssign(BaseEntity target, String propertyName, AssignParameterFunction assigmentFunction) {
		assigmentFunction.apply(target);
		return this;
	}
	public <T> SdsObjectChecker commonObjectPropertyCheck(BaseEntity target, String propertyName, CheckerParameterFunction<T> checkerFunction) {
		
		
		if(!target.isChanged()) {
			return this;
		}
		
		if(isReferenceObject(target)&&!propertyName.equals("id")) {
			//this is an object reference, so all other properties except id check will be ignored
			//id will be checked in this case
			return this; //with an Id, but version is 0 regard as refencer
		}
		if(isObjectForCreate(target)&&propertyName.equals("id")) {
			// ignore check id for new object to create
			return this;
		}
		pushPosition(propertyName);
		T valueToCheck=(T)target.propertyOf(propertyName);
		checkerFunction.apply(valueToCheck);
		popPosition();
		
		return this;
	}
	public  SdsChecker commonObjectElementCheck(BaseEntity target, String propertyName, CheckerParameterFunction<BaseEntity> checkerFunction) {
		
		pushPosition(propertyName);
		checkerFunction.apply(target);
		popPosition();
		return this;
	}
	protected String wrapArrayIndex(int andIncrement) {
		return "["+andIncrement+"]";
	}
	protected String concat(String ...args) {
		
		return Arrays.asList(args).stream().collect(Collectors.joining(""));
		
	}
	// use like commonObjectPropertyCheck(changeRequestAsBaseEntity,"name",this::checkNameOfChangeRequest);

	public SdsObjectChecker checkAndFixPlatform(BaseEntity platformAsBaseEntity){

		if( isChecked(platformAsBaseEntity) ){
			return this;
		}
		markAsChecked(platformAsBaseEntity);
		commonObjectPropertyCheck(platformAsBaseEntity,"id",this::checkIdOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"name",this::checkNameOfPlatform);
		commonObjectPropertyAssign(platformAsBaseEntity,"createTime",this::assignCreateTimeOfPlatform);
		commonObjectPropertyAssign(platformAsBaseEntity,"lastUpdateTime",this::assignLastUpdateTimeOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"version",this::checkVersionOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"companyList",this::checkCompanyListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"changeRequestTypeList",this::checkChangeRequestTypeListOfPlatform);
		commonObjectPropertyCheck(platformAsBaseEntity,"changeRequestList",this::checkChangeRequestListOfPlatform);
		return this;

	}

	public SdsObjectChecker checkAndFixCompany(BaseEntity companyAsBaseEntity){

		if( isChecked(companyAsBaseEntity) ){
			return this;
		}
		markAsChecked(companyAsBaseEntity);
		commonObjectPropertyCheck(companyAsBaseEntity,"id",this::checkIdOfCompany);
		commonObjectPropertyCheck(companyAsBaseEntity,"name",this::checkNameOfCompany);
		commonObjectPropertyAssign(companyAsBaseEntity,"createTime",this::assignCreateTimeOfCompany);
		commonObjectPropertyCheck(companyAsBaseEntity,"platform",this::checkPlatformOfCompany);
		commonObjectPropertyCheck(companyAsBaseEntity,"version",this::checkVersionOfCompany);
		commonObjectPropertyCheck(companyAsBaseEntity,"userList",this::checkUserListOfCompany);
		commonObjectPropertyCheck(companyAsBaseEntity,"projectList",this::checkProjectListOfCompany);
		return this;

	}

	public SdsObjectChecker checkAndFixUser(BaseEntity userAsBaseEntity){

		if( isChecked(userAsBaseEntity) ){
			return this;
		}
		markAsChecked(userAsBaseEntity);
		commonObjectPropertyCheck(userAsBaseEntity,"id",this::checkIdOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"name",this::checkNameOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"joinTime",this::checkJoinTimeOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"company",this::checkCompanyOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"title",this::checkTitleOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"version",this::checkVersionOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"userProjectList",this::checkUserProjectListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"pageFlowSpecList",this::checkPageFlowSpecListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"workFlowSpecList",this::checkWorkFlowSpecListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"changeRequestSpecList",this::checkChangeRequestSpecListOfUser);
		commonObjectPropertyCheck(userAsBaseEntity,"pageContentSpecList",this::checkPageContentSpecListOfUser);
		return this;

	}

	public SdsObjectChecker checkAndFixUserProject(BaseEntity userProjectAsBaseEntity){

		if( isChecked(userProjectAsBaseEntity) ){
			return this;
		}
		markAsChecked(userProjectAsBaseEntity);
		commonObjectPropertyCheck(userProjectAsBaseEntity,"id",this::checkIdOfUserProject);
		commonObjectPropertyCheck(userProjectAsBaseEntity,"user",this::checkUserOfUserProject);
		commonObjectPropertyCheck(userProjectAsBaseEntity,"project",this::checkProjectOfUserProject);
		commonObjectPropertyAssign(userProjectAsBaseEntity,"createTime",this::assignCreateTimeOfUserProject);
		commonObjectPropertyAssign(userProjectAsBaseEntity,"lastUpdateTime",this::assignLastUpdateTimeOfUserProject);
		commonObjectPropertyCheck(userProjectAsBaseEntity,"version",this::checkVersionOfUserProject);
		return this;

	}

	public SdsObjectChecker checkAndFixChangeRequestType(BaseEntity changeRequestTypeAsBaseEntity){

		if( isChecked(changeRequestTypeAsBaseEntity) ){
			return this;
		}
		markAsChecked(changeRequestTypeAsBaseEntity);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"id",this::checkIdOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"name",this::checkNameOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"code",this::checkCodeOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"icon",this::checkIconOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"displayOrder",this::checkDisplayOrderOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"bindTypes",this::checkBindTypesOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"stepConfiguration",this::checkStepConfigurationOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"platform",this::checkPlatformOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"version",this::checkVersionOfChangeRequestType);
		commonObjectPropertyCheck(changeRequestTypeAsBaseEntity,"changeRequestList",this::checkChangeRequestListOfChangeRequestType);
		return this;

	}

	public SdsObjectChecker checkAndFixChangeRequest(BaseEntity changeRequestAsBaseEntity){

		if( isChecked(changeRequestAsBaseEntity) ){
			return this;
		}
		markAsChecked(changeRequestAsBaseEntity);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"id",this::checkIdOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"name",this::checkNameOfChangeRequest);
		commonObjectPropertyAssign(changeRequestAsBaseEntity,"createTime",this::assignCreateTimeOfChangeRequest);
		commonObjectPropertyAssign(changeRequestAsBaseEntity,"remoteIp",this::assignRemoteIpOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"requestType",this::checkRequestTypeOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"commited",this::checkCommitedOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"platform",this::checkPlatformOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"version",this::checkVersionOfChangeRequest);
		commonObjectPropertyCheck(changeRequestAsBaseEntity,"eventUpdateProfileList",this::checkEventUpdateProfileListOfChangeRequest);
		return this;

	}

	public SdsObjectChecker checkAndFixEventUpdateProfile(BaseEntity eventUpdateProfileAsBaseEntity){

		if( isChecked(eventUpdateProfileAsBaseEntity) ){
			return this;
		}
		markAsChecked(eventUpdateProfileAsBaseEntity);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"id",this::checkIdOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"name",this::checkNameOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"avantar",this::checkAvantarOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"fieldGroup",this::checkFieldGroupOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"eventInitiatorType",this::checkEventInitiatorTypeOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"eventInitiatorId",this::checkEventInitiatorIdOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"changeRequest",this::checkChangeRequestOfEventUpdateProfile);
		commonObjectPropertyCheck(eventUpdateProfileAsBaseEntity,"version",this::checkVersionOfEventUpdateProfile);
		return this;

	}

	public SdsObjectChecker checkAndFixProject(BaseEntity projectAsBaseEntity){

		if( isChecked(projectAsBaseEntity) ){
			return this;
		}
		markAsChecked(projectAsBaseEntity);
		commonObjectPropertyCheck(projectAsBaseEntity,"id",this::checkIdOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"name",this::checkNameOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"company",this::checkCompanyOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"version",this::checkVersionOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"userProjectList",this::checkUserProjectListOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"pageFlowSpecList",this::checkPageFlowSpecListOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"workFlowSpecList",this::checkWorkFlowSpecListOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"changeRequestSpecList",this::checkChangeRequestSpecListOfProject);
		commonObjectPropertyCheck(projectAsBaseEntity,"pageContentSpecList",this::checkPageContentSpecListOfProject);
		return this;

	}

	public SdsObjectChecker checkAndFixPageFlowSpec(BaseEntity pageFlowSpecAsBaseEntity){

		if( isChecked(pageFlowSpecAsBaseEntity) ){
			return this;
		}
		markAsChecked(pageFlowSpecAsBaseEntity);
		commonObjectPropertyCheck(pageFlowSpecAsBaseEntity,"id",this::checkIdOfPageFlowSpec);
		commonObjectPropertyCheck(pageFlowSpecAsBaseEntity,"scene",this::checkSceneOfPageFlowSpec);
		commonObjectPropertyCheck(pageFlowSpecAsBaseEntity,"brief",this::checkBriefOfPageFlowSpec);
		commonObjectPropertyCheck(pageFlowSpecAsBaseEntity,"owner",this::checkOwnerOfPageFlowSpec);
		commonObjectPropertyCheck(pageFlowSpecAsBaseEntity,"project",this::checkProjectOfPageFlowSpec);
		commonObjectPropertyCheck(pageFlowSpecAsBaseEntity,"version",this::checkVersionOfPageFlowSpec);
		return this;

	}

	public SdsObjectChecker checkAndFixWorkFlowSpec(BaseEntity workFlowSpecAsBaseEntity){

		if( isChecked(workFlowSpecAsBaseEntity) ){
			return this;
		}
		markAsChecked(workFlowSpecAsBaseEntity);
		commonObjectPropertyCheck(workFlowSpecAsBaseEntity,"id",this::checkIdOfWorkFlowSpec);
		commonObjectPropertyCheck(workFlowSpecAsBaseEntity,"scene",this::checkSceneOfWorkFlowSpec);
		commonObjectPropertyCheck(workFlowSpecAsBaseEntity,"brief",this::checkBriefOfWorkFlowSpec);
		commonObjectPropertyCheck(workFlowSpecAsBaseEntity,"owner",this::checkOwnerOfWorkFlowSpec);
		commonObjectPropertyCheck(workFlowSpecAsBaseEntity,"project",this::checkProjectOfWorkFlowSpec);
		commonObjectPropertyCheck(workFlowSpecAsBaseEntity,"version",this::checkVersionOfWorkFlowSpec);
		return this;

	}

	public SdsObjectChecker checkAndFixChangeRequestSpec(BaseEntity changeRequestSpecAsBaseEntity){

		if( isChecked(changeRequestSpecAsBaseEntity) ){
			return this;
		}
		markAsChecked(changeRequestSpecAsBaseEntity);
		commonObjectPropertyCheck(changeRequestSpecAsBaseEntity,"id",this::checkIdOfChangeRequestSpec);
		commonObjectPropertyCheck(changeRequestSpecAsBaseEntity,"scene",this::checkSceneOfChangeRequestSpec);
		commonObjectPropertyCheck(changeRequestSpecAsBaseEntity,"brief",this::checkBriefOfChangeRequestSpec);
		commonObjectPropertyCheck(changeRequestSpecAsBaseEntity,"owner",this::checkOwnerOfChangeRequestSpec);
		commonObjectPropertyCheck(changeRequestSpecAsBaseEntity,"project",this::checkProjectOfChangeRequestSpec);
		commonObjectPropertyCheck(changeRequestSpecAsBaseEntity,"version",this::checkVersionOfChangeRequestSpec);
		return this;

	}

	public SdsObjectChecker checkAndFixPageContentSpec(BaseEntity pageContentSpecAsBaseEntity){

		if( isChecked(pageContentSpecAsBaseEntity) ){
			return this;
		}
		markAsChecked(pageContentSpecAsBaseEntity);
		commonObjectPropertyCheck(pageContentSpecAsBaseEntity,"id",this::checkIdOfPageContentSpec);
		commonObjectPropertyCheck(pageContentSpecAsBaseEntity,"scene",this::checkSceneOfPageContentSpec);
		commonObjectPropertyCheck(pageContentSpecAsBaseEntity,"brief",this::checkBriefOfPageContentSpec);
		commonObjectPropertyCheck(pageContentSpecAsBaseEntity,"owner",this::checkOwnerOfPageContentSpec);
		commonObjectPropertyCheck(pageContentSpecAsBaseEntity,"project",this::checkProjectOfPageContentSpec);
		commonObjectPropertyCheck(pageContentSpecAsBaseEntity,"version",this::checkVersionOfPageContentSpec);
		return this;

	}

	public SdsObjectChecker checkAndFixMobileApp(BaseEntity mobileAppAsBaseEntity){

		if( isChecked(mobileAppAsBaseEntity) ){
			return this;
		}
		markAsChecked(mobileAppAsBaseEntity);
		commonObjectPropertyCheck(mobileAppAsBaseEntity,"id",this::checkIdOfMobileApp);
		commonObjectPropertyCheck(mobileAppAsBaseEntity,"name",this::checkNameOfMobileApp);
		commonObjectPropertyCheck(mobileAppAsBaseEntity,"version",this::checkVersionOfMobileApp);
		commonObjectPropertyCheck(mobileAppAsBaseEntity,"pageList",this::checkPageListOfMobileApp);
		commonObjectPropertyCheck(mobileAppAsBaseEntity,"pageTypeList",this::checkPageTypeListOfMobileApp);
		return this;

	}

	public SdsObjectChecker checkAndFixPage(BaseEntity pageAsBaseEntity){

		if( isChecked(pageAsBaseEntity) ){
			return this;
		}
		markAsChecked(pageAsBaseEntity);
		commonObjectPropertyCheck(pageAsBaseEntity,"id",this::checkIdOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"pageTitle",this::checkPageTitleOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"linkToUrl",this::checkLinkToUrlOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"pageType",this::checkPageTypeOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"displayOrder",this::checkDisplayOrderOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"mobileApp",this::checkMobileAppOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"version",this::checkVersionOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"slideList",this::checkSlideListOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"uiActionList",this::checkUiActionListOfPage);
		commonObjectPropertyCheck(pageAsBaseEntity,"sectionList",this::checkSectionListOfPage);
		return this;

	}

	public SdsObjectChecker checkAndFixPageType(BaseEntity pageTypeAsBaseEntity){

		if( isChecked(pageTypeAsBaseEntity) ){
			return this;
		}
		markAsChecked(pageTypeAsBaseEntity);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"id",this::checkIdOfPageType);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"name",this::checkNameOfPageType);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"code",this::checkCodeOfPageType);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"mobileApp",this::checkMobileAppOfPageType);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"footerTab",this::checkFooterTabOfPageType);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"version",this::checkVersionOfPageType);
		commonObjectPropertyCheck(pageTypeAsBaseEntity,"pageList",this::checkPageListOfPageType);
		return this;

	}

	public SdsObjectChecker checkAndFixSlide(BaseEntity slideAsBaseEntity){

		if( isChecked(slideAsBaseEntity) ){
			return this;
		}
		markAsChecked(slideAsBaseEntity);
		commonObjectPropertyCheck(slideAsBaseEntity,"id",this::checkIdOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"name",this::checkNameOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"displayOrder",this::checkDisplayOrderOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"imageUrl",this::checkImageUrlOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"videoUrl",this::checkVideoUrlOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"linkToUrl",this::checkLinkToUrlOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"page",this::checkPageOfSlide);
		commonObjectPropertyCheck(slideAsBaseEntity,"version",this::checkVersionOfSlide);
		return this;

	}

	public SdsObjectChecker checkAndFixUiAction(BaseEntity uiActionAsBaseEntity){

		if( isChecked(uiActionAsBaseEntity) ){
			return this;
		}
		markAsChecked(uiActionAsBaseEntity);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"id",this::checkIdOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"code",this::checkCodeOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"icon",this::checkIconOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"title",this::checkTitleOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"displayOrder",this::checkDisplayOrderOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"brief",this::checkBriefOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"imageUrl",this::checkImageUrlOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"linkToUrl",this::checkLinkToUrlOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"extraData",this::checkExtraDataOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"page",this::checkPageOfUiAction);
		commonObjectPropertyCheck(uiActionAsBaseEntity,"version",this::checkVersionOfUiAction);
		return this;

	}

	public SdsObjectChecker checkAndFixSection(BaseEntity sectionAsBaseEntity){

		if( isChecked(sectionAsBaseEntity) ){
			return this;
		}
		markAsChecked(sectionAsBaseEntity);
		commonObjectPropertyCheck(sectionAsBaseEntity,"id",this::checkIdOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"title",this::checkTitleOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"brief",this::checkBriefOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"icon",this::checkIconOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"displayOrder",this::checkDisplayOrderOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"viewGroup",this::checkViewGroupOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"linkToUrl",this::checkLinkToUrlOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"page",this::checkPageOfSection);
		commonObjectPropertyCheck(sectionAsBaseEntity,"version",this::checkVersionOfSection);
		return this;

	}

	public SdsObjectChecker checkAndFixUserDomain(BaseEntity userDomainAsBaseEntity){

		if( isChecked(userDomainAsBaseEntity) ){
			return this;
		}
		markAsChecked(userDomainAsBaseEntity);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"id",this::checkIdOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"name",this::checkNameOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"version",this::checkVersionOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"userWhiteListList",this::checkUserWhiteListListOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"secUserList",this::checkSecUserListOfUserDomain);
		commonObjectPropertyCheck(userDomainAsBaseEntity,"publicKeyTypeList",this::checkPublicKeyTypeListOfUserDomain);
		return this;

	}

	public SdsObjectChecker checkAndFixUserWhiteList(BaseEntity userWhiteListAsBaseEntity){

		if( isChecked(userWhiteListAsBaseEntity) ){
			return this;
		}
		markAsChecked(userWhiteListAsBaseEntity);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"id",this::checkIdOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"userIdentity",this::checkUserIdentityOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"userSpecialFunctions",this::checkUserSpecialFunctionsOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"domain",this::checkDomainOfUserWhiteList);
		commonObjectPropertyCheck(userWhiteListAsBaseEntity,"version",this::checkVersionOfUserWhiteList);
		return this;

	}

	public SdsObjectChecker checkAndFixSecUser(BaseEntity secUserAsBaseEntity){

		if( isChecked(secUserAsBaseEntity) ){
			return this;
		}
		markAsChecked(secUserAsBaseEntity);
		commonObjectPropertyCheck(secUserAsBaseEntity,"id",this::checkIdOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"login",this::checkLoginOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"mobile",this::checkMobileOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"email",this::checkEmailOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"pwd",this::checkPwdOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"weixinOpenid",this::checkWeixinOpenidOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"weixinAppid",this::checkWeixinAppidOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"accessToken",this::checkAccessTokenOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"verificationCode",this::checkVerificationCodeOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"verificationCodeExpire",this::checkVerificationCodeExpireOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"domain",this::checkDomainOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"version",this::checkVersionOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"userAppList",this::checkUserAppListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"loginHistoryList",this::checkLoginHistoryListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"wechatWorkappIdentityList",this::checkWechatWorkappIdentityListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"wechatMiniappIdentityList",this::checkWechatMiniappIdentityListOfSecUser);
		commonObjectPropertyCheck(secUserAsBaseEntity,"keypairIdentityList",this::checkKeypairIdentityListOfSecUser);
		return this;

	}

	public SdsObjectChecker checkAndFixUserApp(BaseEntity userAppAsBaseEntity){

		if( isChecked(userAppAsBaseEntity) ){
			return this;
		}
		markAsChecked(userAppAsBaseEntity);
		commonObjectPropertyCheck(userAppAsBaseEntity,"id",this::checkIdOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"title",this::checkTitleOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"secUser",this::checkSecUserOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"appIcon",this::checkAppIconOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"fullAccess",this::checkFullAccessOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"permission",this::checkPermissionOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectType",this::checkObjectTypeOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"objectId",this::checkObjectIdOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"location",this::checkLocationOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"version",this::checkVersionOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"quickLinkList",this::checkQuickLinkListOfUserApp);
		commonObjectPropertyCheck(userAppAsBaseEntity,"listAccessList",this::checkListAccessListOfUserApp);
		return this;

	}

	public SdsObjectChecker checkAndFixQuickLink(BaseEntity quickLinkAsBaseEntity){

		if( isChecked(quickLinkAsBaseEntity) ){
			return this;
		}
		markAsChecked(quickLinkAsBaseEntity);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"id",this::checkIdOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"name",this::checkNameOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"icon",this::checkIconOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"imagePath",this::checkImagePathOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"linkTarget",this::checkLinkTargetOfQuickLink);
		commonObjectPropertyAssign(quickLinkAsBaseEntity,"createTime",this::assignCreateTimeOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"app",this::checkAppOfQuickLink);
		commonObjectPropertyCheck(quickLinkAsBaseEntity,"version",this::checkVersionOfQuickLink);
		return this;

	}

	public SdsObjectChecker checkAndFixListAccess(BaseEntity listAccessAsBaseEntity){

		if( isChecked(listAccessAsBaseEntity) ){
			return this;
		}
		markAsChecked(listAccessAsBaseEntity);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"id",this::checkIdOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"name",this::checkNameOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"internalName",this::checkInternalNameOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"readPermission",this::checkReadPermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"createPermission",this::checkCreatePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"deletePermission",this::checkDeletePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"updatePermission",this::checkUpdatePermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"executionPermission",this::checkExecutionPermissionOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"app",this::checkAppOfListAccess);
		commonObjectPropertyCheck(listAccessAsBaseEntity,"version",this::checkVersionOfListAccess);
		return this;

	}

	public SdsObjectChecker checkAndFixLoginHistory(BaseEntity loginHistoryAsBaseEntity){

		if( isChecked(loginHistoryAsBaseEntity) ){
			return this;
		}
		markAsChecked(loginHistoryAsBaseEntity);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"id",this::checkIdOfLoginHistory);
		commonObjectPropertyAssign(loginHistoryAsBaseEntity,"loginTime",this::assignLoginTimeOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"fromIp",this::checkFromIpOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"description",this::checkDescriptionOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"secUser",this::checkSecUserOfLoginHistory);
		commonObjectPropertyCheck(loginHistoryAsBaseEntity,"version",this::checkVersionOfLoginHistory);
		return this;

	}

	public SdsObjectChecker checkAndFixCandidateContainer(BaseEntity candidateContainerAsBaseEntity){

		if( isChecked(candidateContainerAsBaseEntity) ){
			return this;
		}
		markAsChecked(candidateContainerAsBaseEntity);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"id",this::checkIdOfCandidateContainer);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"name",this::checkNameOfCandidateContainer);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"version",this::checkVersionOfCandidateContainer);
		commonObjectPropertyCheck(candidateContainerAsBaseEntity,"candidateElementList",this::checkCandidateElementListOfCandidateContainer);
		return this;

	}

	public SdsObjectChecker checkAndFixCandidateElement(BaseEntity candidateElementAsBaseEntity){

		if( isChecked(candidateElementAsBaseEntity) ){
			return this;
		}
		markAsChecked(candidateElementAsBaseEntity);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"id",this::checkIdOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"name",this::checkNameOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"type",this::checkTypeOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"image",this::checkImageOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"container",this::checkContainerOfCandidateElement);
		commonObjectPropertyCheck(candidateElementAsBaseEntity,"version",this::checkVersionOfCandidateElement);
		return this;

	}

	public SdsObjectChecker checkAndFixWechatWorkappIdentity(BaseEntity wechatWorkappIdentityAsBaseEntity){

		if( isChecked(wechatWorkappIdentityAsBaseEntity) ){
			return this;
		}
		markAsChecked(wechatWorkappIdentityAsBaseEntity);
		commonObjectPropertyCheck(wechatWorkappIdentityAsBaseEntity,"id",this::checkIdOfWechatWorkappIdentity);
		commonObjectPropertyCheck(wechatWorkappIdentityAsBaseEntity,"corpId",this::checkCorpIdOfWechatWorkappIdentity);
		commonObjectPropertyCheck(wechatWorkappIdentityAsBaseEntity,"userId",this::checkUserIdOfWechatWorkappIdentity);
		commonObjectPropertyCheck(wechatWorkappIdentityAsBaseEntity,"secUser",this::checkSecUserOfWechatWorkappIdentity);
		commonObjectPropertyAssign(wechatWorkappIdentityAsBaseEntity,"createTime",this::assignCreateTimeOfWechatWorkappIdentity);
		commonObjectPropertyCheck(wechatWorkappIdentityAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfWechatWorkappIdentity);
		commonObjectPropertyCheck(wechatWorkappIdentityAsBaseEntity,"version",this::checkVersionOfWechatWorkappIdentity);
		return this;

	}

	public SdsObjectChecker checkAndFixWechatMiniappIdentity(BaseEntity wechatMiniappIdentityAsBaseEntity){

		if( isChecked(wechatMiniappIdentityAsBaseEntity) ){
			return this;
		}
		markAsChecked(wechatMiniappIdentityAsBaseEntity);
		commonObjectPropertyCheck(wechatMiniappIdentityAsBaseEntity,"id",this::checkIdOfWechatMiniappIdentity);
		commonObjectPropertyCheck(wechatMiniappIdentityAsBaseEntity,"openId",this::checkOpenIdOfWechatMiniappIdentity);
		commonObjectPropertyCheck(wechatMiniappIdentityAsBaseEntity,"appId",this::checkAppIdOfWechatMiniappIdentity);
		commonObjectPropertyCheck(wechatMiniappIdentityAsBaseEntity,"secUser",this::checkSecUserOfWechatMiniappIdentity);
		commonObjectPropertyAssign(wechatMiniappIdentityAsBaseEntity,"createTime",this::assignCreateTimeOfWechatMiniappIdentity);
		commonObjectPropertyCheck(wechatMiniappIdentityAsBaseEntity,"lastLoginTime",this::checkLastLoginTimeOfWechatMiniappIdentity);
		commonObjectPropertyCheck(wechatMiniappIdentityAsBaseEntity,"version",this::checkVersionOfWechatMiniappIdentity);
		return this;

	}

	public SdsObjectChecker checkAndFixKeypairIdentity(BaseEntity keypairIdentityAsBaseEntity){

		if( isChecked(keypairIdentityAsBaseEntity) ){
			return this;
		}
		markAsChecked(keypairIdentityAsBaseEntity);
		commonObjectPropertyCheck(keypairIdentityAsBaseEntity,"id",this::checkIdOfKeypairIdentity);
		commonObjectPropertyCheck(keypairIdentityAsBaseEntity,"publicKey",this::checkPublicKeyOfKeypairIdentity);
		commonObjectPropertyCheck(keypairIdentityAsBaseEntity,"keyType",this::checkKeyTypeOfKeypairIdentity);
		commonObjectPropertyCheck(keypairIdentityAsBaseEntity,"secUser",this::checkSecUserOfKeypairIdentity);
		commonObjectPropertyAssign(keypairIdentityAsBaseEntity,"createTime",this::assignCreateTimeOfKeypairIdentity);
		commonObjectPropertyCheck(keypairIdentityAsBaseEntity,"version",this::checkVersionOfKeypairIdentity);
		return this;

	}

	public SdsObjectChecker checkAndFixPublicKeyType(BaseEntity publicKeyTypeAsBaseEntity){

		if( isChecked(publicKeyTypeAsBaseEntity) ){
			return this;
		}
		markAsChecked(publicKeyTypeAsBaseEntity);
		commonObjectPropertyCheck(publicKeyTypeAsBaseEntity,"id",this::checkIdOfPublicKeyType);
		commonObjectPropertyCheck(publicKeyTypeAsBaseEntity,"name",this::checkNameOfPublicKeyType);
		commonObjectPropertyCheck(publicKeyTypeAsBaseEntity,"code",this::checkCodeOfPublicKeyType);
		commonObjectPropertyCheck(publicKeyTypeAsBaseEntity,"domain",this::checkDomainOfPublicKeyType);
		commonObjectPropertyCheck(publicKeyTypeAsBaseEntity,"version",this::checkVersionOfPublicKeyType);
		commonObjectPropertyCheck(publicKeyTypeAsBaseEntity,"keypairIdentityList",this::checkKeypairIdentityListOfPublicKeyType);
		return this;

	}

	public SdsObjectChecker checkAndFixTreeNode(BaseEntity treeNodeAsBaseEntity){

		if( isChecked(treeNodeAsBaseEntity) ){
			return this;
		}
		markAsChecked(treeNodeAsBaseEntity);
		commonObjectPropertyCheck(treeNodeAsBaseEntity,"id",this::checkIdOfTreeNode);
		commonObjectPropertyCheck(treeNodeAsBaseEntity,"nodeId",this::checkNodeIdOfTreeNode);
		commonObjectPropertyCheck(treeNodeAsBaseEntity,"nodeType",this::checkNodeTypeOfTreeNode);
		commonObjectPropertyCheck(treeNodeAsBaseEntity,"leftValue",this::checkLeftValueOfTreeNode);
		commonObjectPropertyCheck(treeNodeAsBaseEntity,"rightValue",this::checkRightValueOfTreeNode);
		commonObjectPropertyCheck(treeNodeAsBaseEntity,"version",this::checkVersionOfTreeNode);
		return this;

	}


	public SdsObjectChecker checkCompanyListOfPlatform(List<BaseEntity> companyList){
		AtomicInteger index = new AtomicInteger();
		companyList.stream().forEach(company->
			commonObjectElementCheck(company,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixCompany));
		return this;
	}

	public SdsObjectChecker checkChangeRequestTypeListOfPlatform(List<BaseEntity> changeRequestTypeList){
		AtomicInteger index = new AtomicInteger();
		changeRequestTypeList.stream().forEach(changeRequestType->
			commonObjectElementCheck(changeRequestType,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequestType));
		return this;
	}

	public SdsObjectChecker checkChangeRequestListOfPlatform(List<BaseEntity> changeRequestList){
		AtomicInteger index = new AtomicInteger();
		changeRequestList.stream().forEach(changeRequest->
			commonObjectElementCheck(changeRequest,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequest));
		return this;
	}

	public SdsObjectChecker checkUserListOfCompany(List<BaseEntity> userList){
		AtomicInteger index = new AtomicInteger();
		userList.stream().forEach(user->
			commonObjectElementCheck(user,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUser));
		return this;
	}

	public SdsObjectChecker checkProjectListOfCompany(List<BaseEntity> projectList){
		AtomicInteger index = new AtomicInteger();
		projectList.stream().forEach(project->
			commonObjectElementCheck(project,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixProject));
		return this;
	}

	public static final String PLATFORM_OF_COMPANY = "company.platform";


	public SdsObjectChecker checkPlatformOfCompany(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_COMPANY);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkUserProjectListOfUser(List<BaseEntity> userProjectList){
		AtomicInteger index = new AtomicInteger();
		userProjectList.stream().forEach(userProject->
			commonObjectElementCheck(userProject,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUserProject));
		return this;
	}

	public SdsObjectChecker checkPageFlowSpecListOfUser(List<BaseEntity> pageFlowSpecList){
		AtomicInteger index = new AtomicInteger();
		pageFlowSpecList.stream().forEach(pageFlowSpec->
			commonObjectElementCheck(pageFlowSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPageFlowSpec));
		return this;
	}

	public SdsObjectChecker checkWorkFlowSpecListOfUser(List<BaseEntity> workFlowSpecList){
		AtomicInteger index = new AtomicInteger();
		workFlowSpecList.stream().forEach(workFlowSpec->
			commonObjectElementCheck(workFlowSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWorkFlowSpec));
		return this;
	}

	public SdsObjectChecker checkChangeRequestSpecListOfUser(List<BaseEntity> changeRequestSpecList){
		AtomicInteger index = new AtomicInteger();
		changeRequestSpecList.stream().forEach(changeRequestSpec->
			commonObjectElementCheck(changeRequestSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequestSpec));
		return this;
	}

	public SdsObjectChecker checkPageContentSpecListOfUser(List<BaseEntity> pageContentSpecList){
		AtomicInteger index = new AtomicInteger();
		pageContentSpecList.stream().forEach(pageContentSpec->
			commonObjectElementCheck(pageContentSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPageContentSpec));
		return this;
	}

	public static final String COMPANY_OF_USER = "user.company";


	public SdsObjectChecker checkCompanyOfUser(BaseEntity companyAsBaseEntity){

		if(companyAsBaseEntity == null){
			checkBaseEntityReference(companyAsBaseEntity,true,COMPANY_OF_USER);
			return this;
		}
		checkAndFixCompany(companyAsBaseEntity);
		return this;
	}


	public static final String USER_OF_USER_PROJECT = "user_project.user";


	public SdsObjectChecker checkUserOfUserProject(BaseEntity userAsBaseEntity){

		if(userAsBaseEntity == null){
			checkBaseEntityReference(userAsBaseEntity,true,USER_OF_USER_PROJECT);
			return this;
		}
		checkAndFixUser(userAsBaseEntity);
		return this;
	}


	public static final String PROJECT_OF_USER_PROJECT = "user_project.project";


	public SdsObjectChecker checkProjectOfUserProject(BaseEntity projectAsBaseEntity){

		if(projectAsBaseEntity == null){
			checkBaseEntityReference(projectAsBaseEntity,true,PROJECT_OF_USER_PROJECT);
			return this;
		}
		checkAndFixProject(projectAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkChangeRequestListOfChangeRequestType(List<BaseEntity> changeRequestList){
		AtomicInteger index = new AtomicInteger();
		changeRequestList.stream().forEach(changeRequest->
			commonObjectElementCheck(changeRequest,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequest));
		return this;
	}

	public static final String PLATFORM_OF_CHANGE_REQUEST_TYPE = "change_request_type.platform";


	public SdsObjectChecker checkPlatformOfChangeRequestType(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CHANGE_REQUEST_TYPE);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkEventUpdateProfileListOfChangeRequest(List<BaseEntity> eventUpdateProfileList){
		AtomicInteger index = new AtomicInteger();
		eventUpdateProfileList.stream().forEach(eventUpdateProfile->
			commonObjectElementCheck(eventUpdateProfile,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixEventUpdateProfile));
		return this;
	}

	public static final String REQUEST_TYPE_OF_CHANGE_REQUEST = "change_request.request_type";


	public SdsObjectChecker checkRequestTypeOfChangeRequest(BaseEntity requestTypeAsBaseEntity){

		if(requestTypeAsBaseEntity == null){
			checkBaseEntityReference(requestTypeAsBaseEntity,true,REQUEST_TYPE_OF_CHANGE_REQUEST);
			return this;
		}
		checkAndFixChangeRequestType(requestTypeAsBaseEntity);
		return this;
	}


	public static final String PLATFORM_OF_CHANGE_REQUEST = "change_request.platform";


	public SdsObjectChecker checkPlatformOfChangeRequest(BaseEntity platformAsBaseEntity){

		if(platformAsBaseEntity == null){
			checkBaseEntityReference(platformAsBaseEntity,true,PLATFORM_OF_CHANGE_REQUEST);
			return this;
		}
		checkAndFixPlatform(platformAsBaseEntity);
		return this;
	}


	public static final String CHANGE_REQUEST_OF_EVENT_UPDATE_PROFILE = "event_update_profile.change_request";


	public SdsObjectChecker checkChangeRequestOfEventUpdateProfile(BaseEntity changeRequestAsBaseEntity){

		if(changeRequestAsBaseEntity == null){
			checkBaseEntityReference(changeRequestAsBaseEntity,true,CHANGE_REQUEST_OF_EVENT_UPDATE_PROFILE);
			return this;
		}
		checkAndFixChangeRequest(changeRequestAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkUserProjectListOfProject(List<BaseEntity> userProjectList){
		AtomicInteger index = new AtomicInteger();
		userProjectList.stream().forEach(userProject->
			commonObjectElementCheck(userProject,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUserProject));
		return this;
	}

	public SdsObjectChecker checkPageFlowSpecListOfProject(List<BaseEntity> pageFlowSpecList){
		AtomicInteger index = new AtomicInteger();
		pageFlowSpecList.stream().forEach(pageFlowSpec->
			commonObjectElementCheck(pageFlowSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPageFlowSpec));
		return this;
	}

	public SdsObjectChecker checkWorkFlowSpecListOfProject(List<BaseEntity> workFlowSpecList){
		AtomicInteger index = new AtomicInteger();
		workFlowSpecList.stream().forEach(workFlowSpec->
			commonObjectElementCheck(workFlowSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWorkFlowSpec));
		return this;
	}

	public SdsObjectChecker checkChangeRequestSpecListOfProject(List<BaseEntity> changeRequestSpecList){
		AtomicInteger index = new AtomicInteger();
		changeRequestSpecList.stream().forEach(changeRequestSpec->
			commonObjectElementCheck(changeRequestSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixChangeRequestSpec));
		return this;
	}

	public SdsObjectChecker checkPageContentSpecListOfProject(List<BaseEntity> pageContentSpecList){
		AtomicInteger index = new AtomicInteger();
		pageContentSpecList.stream().forEach(pageContentSpec->
			commonObjectElementCheck(pageContentSpec,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPageContentSpec));
		return this;
	}

	public static final String COMPANY_OF_PROJECT = "project.company";


	public SdsObjectChecker checkCompanyOfProject(BaseEntity companyAsBaseEntity){

		if(companyAsBaseEntity == null){
			checkBaseEntityReference(companyAsBaseEntity,true,COMPANY_OF_PROJECT);
			return this;
		}
		checkAndFixCompany(companyAsBaseEntity);
		return this;
	}


	public static final String OWNER_OF_PAGE_FLOW_SPEC = "page_flow_spec.owner";


	public SdsObjectChecker checkOwnerOfPageFlowSpec(BaseEntity ownerAsBaseEntity){

		if(ownerAsBaseEntity == null){
			checkBaseEntityReference(ownerAsBaseEntity,true,OWNER_OF_PAGE_FLOW_SPEC);
			return this;
		}
		checkAndFixUser(ownerAsBaseEntity);
		return this;
	}


	public static final String PROJECT_OF_PAGE_FLOW_SPEC = "page_flow_spec.project";


	public SdsObjectChecker checkProjectOfPageFlowSpec(BaseEntity projectAsBaseEntity){

		if(projectAsBaseEntity == null){
			checkBaseEntityReference(projectAsBaseEntity,true,PROJECT_OF_PAGE_FLOW_SPEC);
			return this;
		}
		checkAndFixProject(projectAsBaseEntity);
		return this;
	}


	public static final String OWNER_OF_WORK_FLOW_SPEC = "work_flow_spec.owner";


	public SdsObjectChecker checkOwnerOfWorkFlowSpec(BaseEntity ownerAsBaseEntity){

		if(ownerAsBaseEntity == null){
			checkBaseEntityReference(ownerAsBaseEntity,true,OWNER_OF_WORK_FLOW_SPEC);
			return this;
		}
		checkAndFixUser(ownerAsBaseEntity);
		return this;
	}


	public static final String PROJECT_OF_WORK_FLOW_SPEC = "work_flow_spec.project";


	public SdsObjectChecker checkProjectOfWorkFlowSpec(BaseEntity projectAsBaseEntity){

		if(projectAsBaseEntity == null){
			checkBaseEntityReference(projectAsBaseEntity,true,PROJECT_OF_WORK_FLOW_SPEC);
			return this;
		}
		checkAndFixProject(projectAsBaseEntity);
		return this;
	}


	public static final String OWNER_OF_CHANGE_REQUEST_SPEC = "change_request_spec.owner";


	public SdsObjectChecker checkOwnerOfChangeRequestSpec(BaseEntity ownerAsBaseEntity){

		if(ownerAsBaseEntity == null){
			checkBaseEntityReference(ownerAsBaseEntity,true,OWNER_OF_CHANGE_REQUEST_SPEC);
			return this;
		}
		checkAndFixUser(ownerAsBaseEntity);
		return this;
	}


	public static final String PROJECT_OF_CHANGE_REQUEST_SPEC = "change_request_spec.project";


	public SdsObjectChecker checkProjectOfChangeRequestSpec(BaseEntity projectAsBaseEntity){

		if(projectAsBaseEntity == null){
			checkBaseEntityReference(projectAsBaseEntity,true,PROJECT_OF_CHANGE_REQUEST_SPEC);
			return this;
		}
		checkAndFixProject(projectAsBaseEntity);
		return this;
	}


	public static final String OWNER_OF_PAGE_CONTENT_SPEC = "page_content_spec.owner";


	public SdsObjectChecker checkOwnerOfPageContentSpec(BaseEntity ownerAsBaseEntity){

		if(ownerAsBaseEntity == null){
			checkBaseEntityReference(ownerAsBaseEntity,true,OWNER_OF_PAGE_CONTENT_SPEC);
			return this;
		}
		checkAndFixUser(ownerAsBaseEntity);
		return this;
	}


	public static final String PROJECT_OF_PAGE_CONTENT_SPEC = "page_content_spec.project";


	public SdsObjectChecker checkProjectOfPageContentSpec(BaseEntity projectAsBaseEntity){

		if(projectAsBaseEntity == null){
			checkBaseEntityReference(projectAsBaseEntity,true,PROJECT_OF_PAGE_CONTENT_SPEC);
			return this;
		}
		checkAndFixProject(projectAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkPageListOfMobileApp(List<BaseEntity> pageList){
		AtomicInteger index = new AtomicInteger();
		pageList.stream().forEach(page->
			commonObjectElementCheck(page,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPage));
		return this;
	}

	public SdsObjectChecker checkPageTypeListOfMobileApp(List<BaseEntity> pageTypeList){
		AtomicInteger index = new AtomicInteger();
		pageTypeList.stream().forEach(pageType->
			commonObjectElementCheck(pageType,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPageType));
		return this;
	}

	public SdsObjectChecker checkSlideListOfPage(List<BaseEntity> slideList){
		AtomicInteger index = new AtomicInteger();
		slideList.stream().forEach(slide->
			commonObjectElementCheck(slide,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixSlide));
		return this;
	}

	public SdsObjectChecker checkUiActionListOfPage(List<BaseEntity> uiActionList){
		AtomicInteger index = new AtomicInteger();
		uiActionList.stream().forEach(uiAction->
			commonObjectElementCheck(uiAction,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUiAction));
		return this;
	}

	public SdsObjectChecker checkSectionListOfPage(List<BaseEntity> sectionList){
		AtomicInteger index = new AtomicInteger();
		sectionList.stream().forEach(section->
			commonObjectElementCheck(section,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixSection));
		return this;
	}

	public static final String PAGE_TYPE_OF_PAGE = "page.page_type";


	public SdsObjectChecker checkPageTypeOfPage(BaseEntity pageTypeAsBaseEntity){

		if(pageTypeAsBaseEntity == null){
			checkBaseEntityReference(pageTypeAsBaseEntity,true,PAGE_TYPE_OF_PAGE);
			return this;
		}
		checkAndFixPageType(pageTypeAsBaseEntity);
		return this;
	}


	public static final String MOBILE_APP_OF_PAGE = "page.mobile_app";


	public SdsObjectChecker checkMobileAppOfPage(BaseEntity mobileAppAsBaseEntity){

		if(mobileAppAsBaseEntity == null){
			checkBaseEntityReference(mobileAppAsBaseEntity,true,MOBILE_APP_OF_PAGE);
			return this;
		}
		checkAndFixMobileApp(mobileAppAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkPageListOfPageType(List<BaseEntity> pageList){
		AtomicInteger index = new AtomicInteger();
		pageList.stream().forEach(page->
			commonObjectElementCheck(page,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPage));
		return this;
	}

	public static final String MOBILE_APP_OF_PAGE_TYPE = "page_type.mobile_app";


	public SdsObjectChecker checkMobileAppOfPageType(BaseEntity mobileAppAsBaseEntity){

		if(mobileAppAsBaseEntity == null){
			checkBaseEntityReference(mobileAppAsBaseEntity,true,MOBILE_APP_OF_PAGE_TYPE);
			return this;
		}
		checkAndFixMobileApp(mobileAppAsBaseEntity);
		return this;
	}


	public static final String PAGE_OF_SLIDE = "slide.page";


	public SdsObjectChecker checkPageOfSlide(BaseEntity pageAsBaseEntity){

		if(pageAsBaseEntity == null){
			checkBaseEntityReference(pageAsBaseEntity,true,PAGE_OF_SLIDE);
			return this;
		}
		checkAndFixPage(pageAsBaseEntity);
		return this;
	}


	public static final String PAGE_OF_UI_ACTION = "ui_action.page";


	public SdsObjectChecker checkPageOfUiAction(BaseEntity pageAsBaseEntity){

		if(pageAsBaseEntity == null){
			checkBaseEntityReference(pageAsBaseEntity,true,PAGE_OF_UI_ACTION);
			return this;
		}
		checkAndFixPage(pageAsBaseEntity);
		return this;
	}


	public static final String PAGE_OF_SECTION = "section.page";


	public SdsObjectChecker checkPageOfSection(BaseEntity pageAsBaseEntity){

		if(pageAsBaseEntity == null){
			checkBaseEntityReference(pageAsBaseEntity,true,PAGE_OF_SECTION);
			return this;
		}
		checkAndFixPage(pageAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkUserWhiteListListOfUserDomain(List<BaseEntity> userWhiteListList){
		AtomicInteger index = new AtomicInteger();
		userWhiteListList.stream().forEach(userWhiteList->
			commonObjectElementCheck(userWhiteList,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUserWhiteList));
		return this;
	}

	public SdsObjectChecker checkSecUserListOfUserDomain(List<BaseEntity> secUserList){
		AtomicInteger index = new AtomicInteger();
		secUserList.stream().forEach(secUser->
			commonObjectElementCheck(secUser,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixSecUser));
		return this;
	}

	public SdsObjectChecker checkPublicKeyTypeListOfUserDomain(List<BaseEntity> publicKeyTypeList){
		AtomicInteger index = new AtomicInteger();
		publicKeyTypeList.stream().forEach(publicKeyType->
			commonObjectElementCheck(publicKeyType,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixPublicKeyType));
		return this;
	}

	public static final String DOMAIN_OF_USER_WHITE_LIST = "user_white_list.domain";


	public SdsObjectChecker checkDomainOfUserWhiteList(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_USER_WHITE_LIST);
			return this;
		}
		checkAndFixUserDomain(domainAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkUserAppListOfSecUser(List<BaseEntity> userAppList){
		AtomicInteger index = new AtomicInteger();
		userAppList.stream().forEach(userApp->
			commonObjectElementCheck(userApp,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixUserApp));
		return this;
	}

	public SdsObjectChecker checkLoginHistoryListOfSecUser(List<BaseEntity> loginHistoryList){
		AtomicInteger index = new AtomicInteger();
		loginHistoryList.stream().forEach(loginHistory->
			commonObjectElementCheck(loginHistory,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixLoginHistory));
		return this;
	}

	public SdsObjectChecker checkWechatWorkappIdentityListOfSecUser(List<BaseEntity> wechatWorkappIdentityList){
		AtomicInteger index = new AtomicInteger();
		wechatWorkappIdentityList.stream().forEach(wechatWorkappIdentity->
			commonObjectElementCheck(wechatWorkappIdentity,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWechatWorkappIdentity));
		return this;
	}

	public SdsObjectChecker checkWechatMiniappIdentityListOfSecUser(List<BaseEntity> wechatMiniappIdentityList){
		AtomicInteger index = new AtomicInteger();
		wechatMiniappIdentityList.stream().forEach(wechatMiniappIdentity->
			commonObjectElementCheck(wechatMiniappIdentity,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixWechatMiniappIdentity));
		return this;
	}

	public SdsObjectChecker checkKeypairIdentityListOfSecUser(List<BaseEntity> keypairIdentityList){
		AtomicInteger index = new AtomicInteger();
		keypairIdentityList.stream().forEach(keypairIdentity->
			commonObjectElementCheck(keypairIdentity,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixKeypairIdentity));
		return this;
	}

	public static final String DOMAIN_OF_SEC_USER = "sec_user.domain";


	public SdsObjectChecker checkDomainOfSecUser(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_SEC_USER);
			return this;
		}
		checkAndFixUserDomain(domainAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkQuickLinkListOfUserApp(List<BaseEntity> quickLinkList){
		AtomicInteger index = new AtomicInteger();
		quickLinkList.stream().forEach(quickLink->
			commonObjectElementCheck(quickLink,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixQuickLink));
		return this;
	}

	public SdsObjectChecker checkListAccessListOfUserApp(List<BaseEntity> listAccessList){
		AtomicInteger index = new AtomicInteger();
		listAccessList.stream().forEach(listAccess->
			commonObjectElementCheck(listAccess,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixListAccess));
		return this;
	}

	public static final String SEC_USER_OF_USER_APP = "user_app.sec_user";


	public SdsObjectChecker checkSecUserOfUserApp(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_USER_APP);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public static final String APP_OF_QUICK_LINK = "quick_link.app";


	public SdsObjectChecker checkAppOfQuickLink(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_QUICK_LINK);
			return this;
		}
		checkAndFixUserApp(appAsBaseEntity);
		return this;
	}


	public static final String APP_OF_LIST_ACCESS = "list_access.app";


	public SdsObjectChecker checkAppOfListAccess(BaseEntity appAsBaseEntity){

		if(appAsBaseEntity == null){
			checkBaseEntityReference(appAsBaseEntity,true,APP_OF_LIST_ACCESS);
			return this;
		}
		checkAndFixUserApp(appAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_LOGIN_HISTORY = "login_history.sec_user";


	public SdsObjectChecker checkSecUserOfLoginHistory(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_LOGIN_HISTORY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkCandidateElementListOfCandidateContainer(List<BaseEntity> candidateElementList){
		AtomicInteger index = new AtomicInteger();
		candidateElementList.stream().forEach(candidateElement->
			commonObjectElementCheck(candidateElement,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixCandidateElement));
		return this;
	}

	public static final String CONTAINER_OF_CANDIDATE_ELEMENT = "candidate_element.container";


	public SdsObjectChecker checkContainerOfCandidateElement(BaseEntity containerAsBaseEntity){

		if(containerAsBaseEntity == null){
			checkBaseEntityReference(containerAsBaseEntity,true,CONTAINER_OF_CANDIDATE_ELEMENT);
			return this;
		}
		checkAndFixCandidateContainer(containerAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_WECHAT_WORKAPP_IDENTITY = "wechat_workapp_identity.sec_user";


	public SdsObjectChecker checkSecUserOfWechatWorkappIdentity(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_WECHAT_WORKAPP_IDENTITY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_WECHAT_MINIAPP_IDENTITY = "wechat_miniapp_identity.sec_user";


	public SdsObjectChecker checkSecUserOfWechatMiniappIdentity(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_WECHAT_MINIAPP_IDENTITY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public static final String KEY_TYPE_OF_KEYPAIR_IDENTITY = "keypair_identity.key_type";


	public SdsObjectChecker checkKeyTypeOfKeypairIdentity(BaseEntity keyTypeAsBaseEntity){

		if(keyTypeAsBaseEntity == null){
			checkBaseEntityReference(keyTypeAsBaseEntity,true,KEY_TYPE_OF_KEYPAIR_IDENTITY);
			return this;
		}
		checkAndFixPublicKeyType(keyTypeAsBaseEntity);
		return this;
	}


	public static final String SEC_USER_OF_KEYPAIR_IDENTITY = "keypair_identity.sec_user";


	public SdsObjectChecker checkSecUserOfKeypairIdentity(BaseEntity secUserAsBaseEntity){

		if(secUserAsBaseEntity == null){
			checkBaseEntityReference(secUserAsBaseEntity,true,SEC_USER_OF_KEYPAIR_IDENTITY);
			return this;
		}
		checkAndFixSecUser(secUserAsBaseEntity);
		return this;
	}


	public SdsObjectChecker checkKeypairIdentityListOfPublicKeyType(List<BaseEntity> keypairIdentityList){
		AtomicInteger index = new AtomicInteger();
		keypairIdentityList.stream().forEach(keypairIdentity->
			commonObjectElementCheck(keypairIdentity,wrapArrayIndex(index.getAndIncrement()),this::checkAndFixKeypairIdentity));
		return this;
	}

	public static final String DOMAIN_OF_PUBLIC_KEY_TYPE = "public_key_type.domain";


	public SdsObjectChecker checkDomainOfPublicKeyType(BaseEntity domainAsBaseEntity){

		if(domainAsBaseEntity == null){
			checkBaseEntityReference(domainAsBaseEntity,true,DOMAIN_OF_PUBLIC_KEY_TYPE);
			return this;
		}
		checkAndFixUserDomain(domainAsBaseEntity);
		return this;
	}

	public SdsObjectChecker assignCreateTimeOfPlatform(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignLastUpdateTimeOfPlatform(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"lastUpdateTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfCompany(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfUserProject(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignLastUpdateTimeOfUserProject(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"lastUpdateTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfChangeRequest(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignRemoteIpOfChangeRequest(BaseEntity targetEntity){
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"remoteIp",userContext.getRemoteIP());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfQuickLink(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignLoginTimeOfLoginHistory(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"loginTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfWechatWorkappIdentity(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfWechatMiniappIdentity(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}
	public SdsObjectChecker assignCreateTimeOfKeypairIdentity(BaseEntity targetEntity){
		if(!isObjectForCreate(targetEntity)){
			return this;
		}
		if(userContext==null){
			return this;
		}
		setEntityProperty(targetEntity,"createTime",userContext.now());
		return this;
	}

}

