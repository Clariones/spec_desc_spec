
package com.cla.sds;
import java.util.Map;

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

public class BeanFactoryImpl{


	public Platform createPlatform(Map<String,Object> options){
		return new Platform();
	}


	public Company createCompany(Map<String,Object> options){
		return new Company();
	}


	public User createUser(Map<String,Object> options){
		return new User();
	}


	public UserProject createUserProject(Map<String,Object> options){
		return new UserProject();
	}


	public ChangeRequestType createChangeRequestType(Map<String,Object> options){
		return new ChangeRequestType();
	}


	public ChangeRequest createChangeRequest(Map<String,Object> options){
		return new ChangeRequest();
	}


	public EventUpdateProfile createEventUpdateProfile(Map<String,Object> options){
		return new EventUpdateProfile();
	}


	public Project createProject(Map<String,Object> options){
		return new Project();
	}


	public PageFlowSpec createPageFlowSpec(Map<String,Object> options){
		return new PageFlowSpec();
	}


	public WorkFlowSpec createWorkFlowSpec(Map<String,Object> options){
		return new WorkFlowSpec();
	}


	public ChangeRequestSpec createChangeRequestSpec(Map<String,Object> options){
		return new ChangeRequestSpec();
	}


	public PageContentSpec createPageContentSpec(Map<String,Object> options){
		return new PageContentSpec();
	}


	public MobileApp createMobileApp(Map<String,Object> options){
		return new MobileApp();
	}


	public Page createPage(Map<String,Object> options){
		return new Page();
	}


	public PageType createPageType(Map<String,Object> options){
		return new PageType();
	}


	public Slide createSlide(Map<String,Object> options){
		return new Slide();
	}


	public UiAction createUiAction(Map<String,Object> options){
		return new UiAction();
	}


	public Section createSection(Map<String,Object> options){
		return new Section();
	}


	public UserDomain createUserDomain(Map<String,Object> options){
		return new UserDomain();
	}


	public UserWhiteList createUserWhiteList(Map<String,Object> options){
		return new UserWhiteList();
	}


	public SecUser createSecUser(Map<String,Object> options){
		return new SecUser();
	}


	public UserApp createUserApp(Map<String,Object> options){
		return new UserApp();
	}


	public QuickLink createQuickLink(Map<String,Object> options){
		return new QuickLink();
	}


	public ListAccess createListAccess(Map<String,Object> options){
		return new ListAccess();
	}


	public LoginHistory createLoginHistory(Map<String,Object> options){
		return new LoginHistory();
	}


	public CandidateContainer createCandidateContainer(Map<String,Object> options){
		return new CandidateContainer();
	}


	public CandidateElement createCandidateElement(Map<String,Object> options){
		return new CandidateElement();
	}


	public WechatWorkappIdentity createWechatWorkappIdentity(Map<String,Object> options){
		return new WechatWorkappIdentity();
	}


	public WechatMiniappIdentity createWechatMiniappIdentity(Map<String,Object> options){
		return new WechatMiniappIdentity();
	}


	public KeypairIdentity createKeypairIdentity(Map<String,Object> options){
		return new KeypairIdentity();
	}


	public PublicKeyType createPublicKeyType(Map<String,Object> options){
		return new PublicKeyType();
	}


	public TreeNode createTreeNode(Map<String,Object> options){
		return new TreeNode();
	}





}

















