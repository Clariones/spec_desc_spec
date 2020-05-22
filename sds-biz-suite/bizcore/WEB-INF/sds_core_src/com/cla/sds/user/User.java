
package com.cla.sds.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.cla.sds.BaseEntity;
import com.cla.sds.SmartList;
import com.cla.sds.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cla.sds.userproject.UserProject;
import com.cla.sds.workflowspec.WorkFlowSpec;
import com.cla.sds.pageflowspec.PageFlowSpec;
import com.cla.sds.pagecontentspec.PageContentSpec;
import com.cla.sds.company.Company;
import com.cla.sds.changerequestspec.ChangeRequestSpec;

@JsonSerialize(using = UserSerializer.class)
public class User extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String JOIN_TIME_PROPERTY             = "joinTime"          ;
	public static final String COMPANY_PROPERTY               = "company"           ;
	public static final String TITLE_PROPERTY                 = "title"             ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String USER_PROJECT_LIST                        = "userProjectList"   ;
	public static final String PAGE_FLOW_SPEC_LIST                      = "pageFlowSpecList"  ;
	public static final String WORK_FLOW_SPEC_LIST                      = "workFlowSpecList"  ;
	public static final String CHANGE_REQUEST_SPEC_LIST                 = "changeRequestSpecList";
	public static final String PAGE_CONTENT_SPEC_LIST                   = "pageContentSpecList";

	public static final String INTERNAL_TYPE="User";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		Date                	mJoinTime           ;
	protected		Company             	mCompany            ;
	protected		String              	mTitle              ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<UserProject>	mUserProjectList    ;
	protected		SmartList<PageFlowSpec>	mPageFlowSpecList   ;
	protected		SmartList<WorkFlowSpec>	mWorkFlowSpecList   ;
	protected		SmartList<ChangeRequestSpec>	mChangeRequestSpecList;
	protected		SmartList<PageContentSpec>	mPageContentSpecList;

	
		
	public 	User(){
		// lazy load for all the properties
	}
	public 	static User withId(String id){
		User user = new User();
		user.setId(id);
		user.setVersion(Integer.MAX_VALUE);
		return user;
	}
	public 	static User refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setCompany( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(JOIN_TIME_PROPERTY.equals(property)){
			changeJoinTimeProperty(newValueExpr);
		}
		if(TITLE_PROPERTY.equals(property)){
			changeTitleProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
	
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeJoinTimeProperty(String newValueExpr){
	
		Date oldValue = getJoinTime();
		Date newValue = parseDate(newValueExpr);
		if(equalsDate(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateJoinTime(newValue);
		this.onChangeProperty(JOIN_TIME_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeTitleProperty(String newValueExpr){
	
		String oldValue = getTitle();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateTitle(newValue);
		this.onChangeProperty(TITLE_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(JOIN_TIME_PROPERTY.equals(property)){
			return getJoinTime();
		}
		if(COMPANY_PROPERTY.equals(property)){
			return getCompany();
		}
		if(TITLE_PROPERTY.equals(property)){
			return getTitle();
		}
		if(USER_PROJECT_LIST.equals(property)){
			List<BaseEntity> list = getUserProjectList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(PAGE_FLOW_SPEC_LIST.equals(property)){
			List<BaseEntity> list = getPageFlowSpecList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(WORK_FLOW_SPEC_LIST.equals(property)){
			List<BaseEntity> list = getWorkFlowSpecList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CHANGE_REQUEST_SPEC_LIST.equals(property)){
			List<BaseEntity> list = getChangeRequestSpecList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(PAGE_CONTENT_SPEC_LIST.equals(property)){
			List<BaseEntity> list = getPageContentSpecList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}

    		//other property not include here
		return super.propertyOf(property);
	}
    
    


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public User updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public User updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setJoinTime(Date joinTime){
		this.mJoinTime = joinTime;;
	}
	public Date getJoinTime(){
		return this.mJoinTime;
	}
	public User updateJoinTime(Date joinTime){
		this.mJoinTime = joinTime;;
		this.changed = true;
		return this;
	}
	public void mergeJoinTime(Date joinTime){
		setJoinTime(joinTime);
	}
	
	
	public void setCompany(Company company){
		this.mCompany = company;;
	}
	public Company getCompany(){
		return this.mCompany;
	}
	public User updateCompany(Company company){
		this.mCompany = company;;
		this.changed = true;
		return this;
	}
	public void mergeCompany(Company company){
		if(company != null) { setCompany(company);}
	}
	
	
	public void clearCompany(){
		setCompany ( null );
		this.changed = true;
	}
	
	public void setTitle(String title){
		this.mTitle = trimString(title);;
	}
	public String getTitle(){
		return this.mTitle;
	}
	public User updateTitle(String title){
		this.mTitle = trimString(title);;
		this.changed = true;
		return this;
	}
	public void mergeTitle(String title){
		if(title != null) { setTitle(title);}
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public User updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<UserProject> getUserProjectList(){
		if(this.mUserProjectList == null){
			this.mUserProjectList = new SmartList<UserProject>();
			this.mUserProjectList.setListInternalName (USER_PROJECT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mUserProjectList;	
	}
	public  void setUserProjectList(SmartList<UserProject> userProjectList){
		for( UserProject userProject:userProjectList){
			userProject.setUser(this);
		}

		this.mUserProjectList = userProjectList;
		this.mUserProjectList.setListInternalName (USER_PROJECT_LIST );
		
	}
	
	public  void addUserProject(UserProject userProject){
		userProject.setUser(this);
		getUserProjectList().add(userProject);
	}
	public  void addUserProjectList(SmartList<UserProject> userProjectList){
		for( UserProject userProject:userProjectList){
			userProject.setUser(this);
		}
		getUserProjectList().addAll(userProjectList);
	}
	public  void mergeUserProjectList(SmartList<UserProject> userProjectList){
		if(userProjectList==null){
			return;
		}
		if(userProjectList.isEmpty()){
			return;
		}
		addUserProjectList( userProjectList );
		
	}
	public  UserProject removeUserProject(UserProject userProjectIndex){
		
		int index = getUserProjectList().indexOf(userProjectIndex);
        if(index < 0){
        	String message = "UserProject("+userProjectIndex.getId()+") with version='"+userProjectIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        UserProject userProject = getUserProjectList().get(index);        
        // userProject.clearUser(); //disconnect with User
        userProject.clearFromAll(); //disconnect with User
		
		boolean result = getUserProjectList().planToRemove(userProject);
        if(!result){
        	String message = "UserProject("+userProjectIndex.getId()+") with version='"+userProjectIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return userProject;
        
	
	}
	//断舍离
	public  void breakWithUserProject(UserProject userProject){
		
		if(userProject == null){
			return;
		}
		userProject.setUser(null);
		//getUserProjectList().remove();
	
	}
	
	public  boolean hasUserProject(UserProject userProject){
	
		return getUserProjectList().contains(userProject);
  
	}
	
	public void copyUserProjectFrom(UserProject userProject) {

		UserProject userProjectInList = findTheUserProject(userProject);
		UserProject newUserProject = new UserProject();
		userProjectInList.copyTo(newUserProject);
		newUserProject.setVersion(0);//will trigger copy
		getUserProjectList().add(newUserProject);
		addItemToFlexiableObject(COPIED_CHILD, newUserProject);
	}
	
	public  UserProject findTheUserProject(UserProject userProject){
		
		int index =  getUserProjectList().indexOf(userProject);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "UserProject("+userProject.getId()+") with version='"+userProject.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getUserProjectList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpUserProjectList(){
		getUserProjectList().clear();
	}
	
	
	


	public  SmartList<PageFlowSpec> getPageFlowSpecList(){
		if(this.mPageFlowSpecList == null){
			this.mPageFlowSpecList = new SmartList<PageFlowSpec>();
			this.mPageFlowSpecList.setListInternalName (PAGE_FLOW_SPEC_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mPageFlowSpecList;	
	}
	public  void setPageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList){
		for( PageFlowSpec pageFlowSpec:pageFlowSpecList){
			pageFlowSpec.setOwner(this);
		}

		this.mPageFlowSpecList = pageFlowSpecList;
		this.mPageFlowSpecList.setListInternalName (PAGE_FLOW_SPEC_LIST );
		
	}
	
	public  void addPageFlowSpec(PageFlowSpec pageFlowSpec){
		pageFlowSpec.setOwner(this);
		getPageFlowSpecList().add(pageFlowSpec);
	}
	public  void addPageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList){
		for( PageFlowSpec pageFlowSpec:pageFlowSpecList){
			pageFlowSpec.setOwner(this);
		}
		getPageFlowSpecList().addAll(pageFlowSpecList);
	}
	public  void mergePageFlowSpecList(SmartList<PageFlowSpec> pageFlowSpecList){
		if(pageFlowSpecList==null){
			return;
		}
		if(pageFlowSpecList.isEmpty()){
			return;
		}
		addPageFlowSpecList( pageFlowSpecList );
		
	}
	public  PageFlowSpec removePageFlowSpec(PageFlowSpec pageFlowSpecIndex){
		
		int index = getPageFlowSpecList().indexOf(pageFlowSpecIndex);
        if(index < 0){
        	String message = "PageFlowSpec("+pageFlowSpecIndex.getId()+") with version='"+pageFlowSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        PageFlowSpec pageFlowSpec = getPageFlowSpecList().get(index);        
        // pageFlowSpec.clearOwner(); //disconnect with Owner
        pageFlowSpec.clearFromAll(); //disconnect with Owner
		
		boolean result = getPageFlowSpecList().planToRemove(pageFlowSpec);
        if(!result){
        	String message = "PageFlowSpec("+pageFlowSpecIndex.getId()+") with version='"+pageFlowSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return pageFlowSpec;
        
	
	}
	//断舍离
	public  void breakWithPageFlowSpec(PageFlowSpec pageFlowSpec){
		
		if(pageFlowSpec == null){
			return;
		}
		pageFlowSpec.setOwner(null);
		//getPageFlowSpecList().remove();
	
	}
	
	public  boolean hasPageFlowSpec(PageFlowSpec pageFlowSpec){
	
		return getPageFlowSpecList().contains(pageFlowSpec);
  
	}
	
	public void copyPageFlowSpecFrom(PageFlowSpec pageFlowSpec) {

		PageFlowSpec pageFlowSpecInList = findThePageFlowSpec(pageFlowSpec);
		PageFlowSpec newPageFlowSpec = new PageFlowSpec();
		pageFlowSpecInList.copyTo(newPageFlowSpec);
		newPageFlowSpec.setVersion(0);//will trigger copy
		getPageFlowSpecList().add(newPageFlowSpec);
		addItemToFlexiableObject(COPIED_CHILD, newPageFlowSpec);
	}
	
	public  PageFlowSpec findThePageFlowSpec(PageFlowSpec pageFlowSpec){
		
		int index =  getPageFlowSpecList().indexOf(pageFlowSpec);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "PageFlowSpec("+pageFlowSpec.getId()+") with version='"+pageFlowSpec.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPageFlowSpecList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPageFlowSpecList(){
		getPageFlowSpecList().clear();
	}
	
	
	


	public  SmartList<WorkFlowSpec> getWorkFlowSpecList(){
		if(this.mWorkFlowSpecList == null){
			this.mWorkFlowSpecList = new SmartList<WorkFlowSpec>();
			this.mWorkFlowSpecList.setListInternalName (WORK_FLOW_SPEC_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mWorkFlowSpecList;	
	}
	public  void setWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList){
		for( WorkFlowSpec workFlowSpec:workFlowSpecList){
			workFlowSpec.setOwner(this);
		}

		this.mWorkFlowSpecList = workFlowSpecList;
		this.mWorkFlowSpecList.setListInternalName (WORK_FLOW_SPEC_LIST );
		
	}
	
	public  void addWorkFlowSpec(WorkFlowSpec workFlowSpec){
		workFlowSpec.setOwner(this);
		getWorkFlowSpecList().add(workFlowSpec);
	}
	public  void addWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList){
		for( WorkFlowSpec workFlowSpec:workFlowSpecList){
			workFlowSpec.setOwner(this);
		}
		getWorkFlowSpecList().addAll(workFlowSpecList);
	}
	public  void mergeWorkFlowSpecList(SmartList<WorkFlowSpec> workFlowSpecList){
		if(workFlowSpecList==null){
			return;
		}
		if(workFlowSpecList.isEmpty()){
			return;
		}
		addWorkFlowSpecList( workFlowSpecList );
		
	}
	public  WorkFlowSpec removeWorkFlowSpec(WorkFlowSpec workFlowSpecIndex){
		
		int index = getWorkFlowSpecList().indexOf(workFlowSpecIndex);
        if(index < 0){
        	String message = "WorkFlowSpec("+workFlowSpecIndex.getId()+") with version='"+workFlowSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        WorkFlowSpec workFlowSpec = getWorkFlowSpecList().get(index);        
        // workFlowSpec.clearOwner(); //disconnect with Owner
        workFlowSpec.clearFromAll(); //disconnect with Owner
		
		boolean result = getWorkFlowSpecList().planToRemove(workFlowSpec);
        if(!result){
        	String message = "WorkFlowSpec("+workFlowSpecIndex.getId()+") with version='"+workFlowSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return workFlowSpec;
        
	
	}
	//断舍离
	public  void breakWithWorkFlowSpec(WorkFlowSpec workFlowSpec){
		
		if(workFlowSpec == null){
			return;
		}
		workFlowSpec.setOwner(null);
		//getWorkFlowSpecList().remove();
	
	}
	
	public  boolean hasWorkFlowSpec(WorkFlowSpec workFlowSpec){
	
		return getWorkFlowSpecList().contains(workFlowSpec);
  
	}
	
	public void copyWorkFlowSpecFrom(WorkFlowSpec workFlowSpec) {

		WorkFlowSpec workFlowSpecInList = findTheWorkFlowSpec(workFlowSpec);
		WorkFlowSpec newWorkFlowSpec = new WorkFlowSpec();
		workFlowSpecInList.copyTo(newWorkFlowSpec);
		newWorkFlowSpec.setVersion(0);//will trigger copy
		getWorkFlowSpecList().add(newWorkFlowSpec);
		addItemToFlexiableObject(COPIED_CHILD, newWorkFlowSpec);
	}
	
	public  WorkFlowSpec findTheWorkFlowSpec(WorkFlowSpec workFlowSpec){
		
		int index =  getWorkFlowSpecList().indexOf(workFlowSpec);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "WorkFlowSpec("+workFlowSpec.getId()+") with version='"+workFlowSpec.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getWorkFlowSpecList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpWorkFlowSpecList(){
		getWorkFlowSpecList().clear();
	}
	
	
	


	public  SmartList<ChangeRequestSpec> getChangeRequestSpecList(){
		if(this.mChangeRequestSpecList == null){
			this.mChangeRequestSpecList = new SmartList<ChangeRequestSpec>();
			this.mChangeRequestSpecList.setListInternalName (CHANGE_REQUEST_SPEC_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mChangeRequestSpecList;	
	}
	public  void setChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList){
		for( ChangeRequestSpec changeRequestSpec:changeRequestSpecList){
			changeRequestSpec.setOwner(this);
		}

		this.mChangeRequestSpecList = changeRequestSpecList;
		this.mChangeRequestSpecList.setListInternalName (CHANGE_REQUEST_SPEC_LIST );
		
	}
	
	public  void addChangeRequestSpec(ChangeRequestSpec changeRequestSpec){
		changeRequestSpec.setOwner(this);
		getChangeRequestSpecList().add(changeRequestSpec);
	}
	public  void addChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList){
		for( ChangeRequestSpec changeRequestSpec:changeRequestSpecList){
			changeRequestSpec.setOwner(this);
		}
		getChangeRequestSpecList().addAll(changeRequestSpecList);
	}
	public  void mergeChangeRequestSpecList(SmartList<ChangeRequestSpec> changeRequestSpecList){
		if(changeRequestSpecList==null){
			return;
		}
		if(changeRequestSpecList.isEmpty()){
			return;
		}
		addChangeRequestSpecList( changeRequestSpecList );
		
	}
	public  ChangeRequestSpec removeChangeRequestSpec(ChangeRequestSpec changeRequestSpecIndex){
		
		int index = getChangeRequestSpecList().indexOf(changeRequestSpecIndex);
        if(index < 0){
        	String message = "ChangeRequestSpec("+changeRequestSpecIndex.getId()+") with version='"+changeRequestSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ChangeRequestSpec changeRequestSpec = getChangeRequestSpecList().get(index);        
        // changeRequestSpec.clearOwner(); //disconnect with Owner
        changeRequestSpec.clearFromAll(); //disconnect with Owner
		
		boolean result = getChangeRequestSpecList().planToRemove(changeRequestSpec);
        if(!result){
        	String message = "ChangeRequestSpec("+changeRequestSpecIndex.getId()+") with version='"+changeRequestSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return changeRequestSpec;
        
	
	}
	//断舍离
	public  void breakWithChangeRequestSpec(ChangeRequestSpec changeRequestSpec){
		
		if(changeRequestSpec == null){
			return;
		}
		changeRequestSpec.setOwner(null);
		//getChangeRequestSpecList().remove();
	
	}
	
	public  boolean hasChangeRequestSpec(ChangeRequestSpec changeRequestSpec){
	
		return getChangeRequestSpecList().contains(changeRequestSpec);
  
	}
	
	public void copyChangeRequestSpecFrom(ChangeRequestSpec changeRequestSpec) {

		ChangeRequestSpec changeRequestSpecInList = findTheChangeRequestSpec(changeRequestSpec);
		ChangeRequestSpec newChangeRequestSpec = new ChangeRequestSpec();
		changeRequestSpecInList.copyTo(newChangeRequestSpec);
		newChangeRequestSpec.setVersion(0);//will trigger copy
		getChangeRequestSpecList().add(newChangeRequestSpec);
		addItemToFlexiableObject(COPIED_CHILD, newChangeRequestSpec);
	}
	
	public  ChangeRequestSpec findTheChangeRequestSpec(ChangeRequestSpec changeRequestSpec){
		
		int index =  getChangeRequestSpecList().indexOf(changeRequestSpec);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ChangeRequestSpec("+changeRequestSpec.getId()+") with version='"+changeRequestSpec.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getChangeRequestSpecList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpChangeRequestSpecList(){
		getChangeRequestSpecList().clear();
	}
	
	
	


	public  SmartList<PageContentSpec> getPageContentSpecList(){
		if(this.mPageContentSpecList == null){
			this.mPageContentSpecList = new SmartList<PageContentSpec>();
			this.mPageContentSpecList.setListInternalName (PAGE_CONTENT_SPEC_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mPageContentSpecList;	
	}
	public  void setPageContentSpecList(SmartList<PageContentSpec> pageContentSpecList){
		for( PageContentSpec pageContentSpec:pageContentSpecList){
			pageContentSpec.setOwner(this);
		}

		this.mPageContentSpecList = pageContentSpecList;
		this.mPageContentSpecList.setListInternalName (PAGE_CONTENT_SPEC_LIST );
		
	}
	
	public  void addPageContentSpec(PageContentSpec pageContentSpec){
		pageContentSpec.setOwner(this);
		getPageContentSpecList().add(pageContentSpec);
	}
	public  void addPageContentSpecList(SmartList<PageContentSpec> pageContentSpecList){
		for( PageContentSpec pageContentSpec:pageContentSpecList){
			pageContentSpec.setOwner(this);
		}
		getPageContentSpecList().addAll(pageContentSpecList);
	}
	public  void mergePageContentSpecList(SmartList<PageContentSpec> pageContentSpecList){
		if(pageContentSpecList==null){
			return;
		}
		if(pageContentSpecList.isEmpty()){
			return;
		}
		addPageContentSpecList( pageContentSpecList );
		
	}
	public  PageContentSpec removePageContentSpec(PageContentSpec pageContentSpecIndex){
		
		int index = getPageContentSpecList().indexOf(pageContentSpecIndex);
        if(index < 0){
        	String message = "PageContentSpec("+pageContentSpecIndex.getId()+") with version='"+pageContentSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        PageContentSpec pageContentSpec = getPageContentSpecList().get(index);        
        // pageContentSpec.clearOwner(); //disconnect with Owner
        pageContentSpec.clearFromAll(); //disconnect with Owner
		
		boolean result = getPageContentSpecList().planToRemove(pageContentSpec);
        if(!result){
        	String message = "PageContentSpec("+pageContentSpecIndex.getId()+") with version='"+pageContentSpecIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return pageContentSpec;
        
	
	}
	//断舍离
	public  void breakWithPageContentSpec(PageContentSpec pageContentSpec){
		
		if(pageContentSpec == null){
			return;
		}
		pageContentSpec.setOwner(null);
		//getPageContentSpecList().remove();
	
	}
	
	public  boolean hasPageContentSpec(PageContentSpec pageContentSpec){
	
		return getPageContentSpecList().contains(pageContentSpec);
  
	}
	
	public void copyPageContentSpecFrom(PageContentSpec pageContentSpec) {

		PageContentSpec pageContentSpecInList = findThePageContentSpec(pageContentSpec);
		PageContentSpec newPageContentSpec = new PageContentSpec();
		pageContentSpecInList.copyTo(newPageContentSpec);
		newPageContentSpec.setVersion(0);//will trigger copy
		getPageContentSpecList().add(newPageContentSpec);
		addItemToFlexiableObject(COPIED_CHILD, newPageContentSpec);
	}
	
	public  PageContentSpec findThePageContentSpec(PageContentSpec pageContentSpec){
		
		int index =  getPageContentSpecList().indexOf(pageContentSpec);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "PageContentSpec("+pageContentSpec.getId()+") with version='"+pageContentSpec.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPageContentSpecList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPageContentSpecList(){
		getPageContentSpecList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getCompany(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getUserProjectList(), internalType);
		collectFromList(this, entityList, getPageFlowSpecList(), internalType);
		collectFromList(this, entityList, getWorkFlowSpecList(), internalType);
		collectFromList(this, entityList, getChangeRequestSpecList(), internalType);
		collectFromList(this, entityList, getPageContentSpecList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getUserProjectList());
		listOfList.add( getPageFlowSpecList());
		listOfList.add( getWorkFlowSpecList());
		listOfList.add( getChangeRequestSpecList());
		listOfList.add( getPageContentSpecList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, JOIN_TIME_PROPERTY, getJoinTime());
		appendKeyValuePair(result, COMPANY_PROPERTY, getCompany());
		appendKeyValuePair(result, TITLE_PROPERTY, getTitle());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, USER_PROJECT_LIST, getUserProjectList());
		if(!getUserProjectList().isEmpty()){
			appendKeyValuePair(result, "userProjectCount", getUserProjectList().getTotalCount());
			appendKeyValuePair(result, "userProjectCurrentPageNumber", getUserProjectList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PAGE_FLOW_SPEC_LIST, getPageFlowSpecList());
		if(!getPageFlowSpecList().isEmpty()){
			appendKeyValuePair(result, "pageFlowSpecCount", getPageFlowSpecList().getTotalCount());
			appendKeyValuePair(result, "pageFlowSpecCurrentPageNumber", getPageFlowSpecList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, WORK_FLOW_SPEC_LIST, getWorkFlowSpecList());
		if(!getWorkFlowSpecList().isEmpty()){
			appendKeyValuePair(result, "workFlowSpecCount", getWorkFlowSpecList().getTotalCount());
			appendKeyValuePair(result, "workFlowSpecCurrentPageNumber", getWorkFlowSpecList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CHANGE_REQUEST_SPEC_LIST, getChangeRequestSpecList());
		if(!getChangeRequestSpecList().isEmpty()){
			appendKeyValuePair(result, "changeRequestSpecCount", getChangeRequestSpecList().getTotalCount());
			appendKeyValuePair(result, "changeRequestSpecCurrentPageNumber", getChangeRequestSpecList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PAGE_CONTENT_SPEC_LIST, getPageContentSpecList());
		if(!getPageContentSpecList().isEmpty()){
			appendKeyValuePair(result, "pageContentSpecCount", getPageContentSpecList().getTotalCount());
			appendKeyValuePair(result, "pageContentSpecCurrentPageNumber", getPageContentSpecList().getCurrentPageNumber());
		}

		if (this.valueByKey("valuesOfGroupBy") != null) {
			appendKeyValuePair(result, "valuesOfGroupBy", this.valueByKey("valuesOfGroupBy"));
		}
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof User){
		
		
			User dest =(User)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setJoinTime(getJoinTime());
			dest.setCompany(getCompany());
			dest.setTitle(getTitle());
			dest.setVersion(getVersion());
			dest.setUserProjectList(getUserProjectList());
			dest.setPageFlowSpecList(getPageFlowSpecList());
			dest.setWorkFlowSpecList(getWorkFlowSpecList());
			dest.setChangeRequestSpecList(getChangeRequestSpecList());
			dest.setPageContentSpecList(getPageContentSpecList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof User){
		
			
			User dest =(User)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeJoinTime(getJoinTime());
			dest.mergeCompany(getCompany());
			dest.mergeTitle(getTitle());
			dest.mergeVersion(getVersion());
			dest.mergeUserProjectList(getUserProjectList());
			dest.mergePageFlowSpecList(getPageFlowSpecList());
			dest.mergeWorkFlowSpecList(getWorkFlowSpecList());
			dest.mergeChangeRequestSpecList(getChangeRequestSpecList());
			dest.mergePageContentSpecList(getPageContentSpecList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof User){
		
			
			User dest =(User)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeJoinTime(getJoinTime());
			dest.mergeTitle(getTitle());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getJoinTime(), getCompany(), getTitle(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("User{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tjoinTime='"+getJoinTime()+"';");
		if(getCompany() != null ){
 			stringBuilder.append("\tcompany='Company("+getCompany().getId()+")';");
 		}
		stringBuilder.append("\ttitle='"+getTitle()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

