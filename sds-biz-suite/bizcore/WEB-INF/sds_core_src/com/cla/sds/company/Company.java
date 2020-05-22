
package com.cla.sds.company;

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
import com.cla.sds.user.User;
import com.cla.sds.project.Project;
import com.cla.sds.platform.Platform;

@JsonSerialize(using = CompanySerializer.class)
public class Company extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String USER_LIST                                = "userList"          ;
	public static final String PROJECT_LIST                             = "projectList"       ;

	public static final String INTERNAL_TYPE="Company";
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
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<User>     	mUserList           ;
	protected		SmartList<Project>  	mProjectList        ;

	
		
	public 	Company(){
		// lazy load for all the properties
	}
	public 	static Company withId(String id){
		Company company = new Company();
		company.setId(id);
		company.setVersion(Integer.MAX_VALUE);
		return company;
	}
	public 	static Company refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
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
			
			
			
	protected void changeCreateTimeProperty(String newValueExpr){
	
		DateTime oldValue = getCreateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCreateTime(newValue);
		this.onChangeProperty(CREATE_TIME_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(USER_LIST.equals(property)){
			List<BaseEntity> list = getUserList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(PROJECT_LIST.equals(property)){
			List<BaseEntity> list = getProjectList().stream().map(item->item).collect(Collectors.toList());
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
	public Company updateId(String id){
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
	public Company updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public Company updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Company updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Company updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<User> getUserList(){
		if(this.mUserList == null){
			this.mUserList = new SmartList<User>();
			this.mUserList.setListInternalName (USER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mUserList;	
	}
	public  void setUserList(SmartList<User> userList){
		for( User user:userList){
			user.setCompany(this);
		}

		this.mUserList = userList;
		this.mUserList.setListInternalName (USER_LIST );
		
	}
	
	public  void addUser(User user){
		user.setCompany(this);
		getUserList().add(user);
	}
	public  void addUserList(SmartList<User> userList){
		for( User user:userList){
			user.setCompany(this);
		}
		getUserList().addAll(userList);
	}
	public  void mergeUserList(SmartList<User> userList){
		if(userList==null){
			return;
		}
		if(userList.isEmpty()){
			return;
		}
		addUserList( userList );
		
	}
	public  User removeUser(User userIndex){
		
		int index = getUserList().indexOf(userIndex);
        if(index < 0){
        	String message = "User("+userIndex.getId()+") with version='"+userIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        User user = getUserList().get(index);        
        // user.clearCompany(); //disconnect with Company
        user.clearFromAll(); //disconnect with Company
		
		boolean result = getUserList().planToRemove(user);
        if(!result){
        	String message = "User("+userIndex.getId()+") with version='"+userIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return user;
        
	
	}
	//断舍离
	public  void breakWithUser(User user){
		
		if(user == null){
			return;
		}
		user.setCompany(null);
		//getUserList().remove();
	
	}
	
	public  boolean hasUser(User user){
	
		return getUserList().contains(user);
  
	}
	
	public void copyUserFrom(User user) {

		User userInList = findTheUser(user);
		User newUser = new User();
		userInList.copyTo(newUser);
		newUser.setVersion(0);//will trigger copy
		getUserList().add(newUser);
		addItemToFlexiableObject(COPIED_CHILD, newUser);
	}
	
	public  User findTheUser(User user){
		
		int index =  getUserList().indexOf(user);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "User("+user.getId()+") with version='"+user.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getUserList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpUserList(){
		getUserList().clear();
	}
	
	
	


	public  SmartList<Project> getProjectList(){
		if(this.mProjectList == null){
			this.mProjectList = new SmartList<Project>();
			this.mProjectList.setListInternalName (PROJECT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mProjectList;	
	}
	public  void setProjectList(SmartList<Project> projectList){
		for( Project project:projectList){
			project.setCompany(this);
		}

		this.mProjectList = projectList;
		this.mProjectList.setListInternalName (PROJECT_LIST );
		
	}
	
	public  void addProject(Project project){
		project.setCompany(this);
		getProjectList().add(project);
	}
	public  void addProjectList(SmartList<Project> projectList){
		for( Project project:projectList){
			project.setCompany(this);
		}
		getProjectList().addAll(projectList);
	}
	public  void mergeProjectList(SmartList<Project> projectList){
		if(projectList==null){
			return;
		}
		if(projectList.isEmpty()){
			return;
		}
		addProjectList( projectList );
		
	}
	public  Project removeProject(Project projectIndex){
		
		int index = getProjectList().indexOf(projectIndex);
        if(index < 0){
        	String message = "Project("+projectIndex.getId()+") with version='"+projectIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Project project = getProjectList().get(index);        
        // project.clearCompany(); //disconnect with Company
        project.clearFromAll(); //disconnect with Company
		
		boolean result = getProjectList().planToRemove(project);
        if(!result){
        	String message = "Project("+projectIndex.getId()+") with version='"+projectIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return project;
        
	
	}
	//断舍离
	public  void breakWithProject(Project project){
		
		if(project == null){
			return;
		}
		project.setCompany(null);
		//getProjectList().remove();
	
	}
	
	public  boolean hasProject(Project project){
	
		return getProjectList().contains(project);
  
	}
	
	public void copyProjectFrom(Project project) {

		Project projectInList = findTheProject(project);
		Project newProject = new Project();
		projectInList.copyTo(newProject);
		newProject.setVersion(0);//will trigger copy
		getProjectList().add(newProject);
		addItemToFlexiableObject(COPIED_CHILD, newProject);
	}
	
	public  Project findTheProject(Project project){
		
		int index =  getProjectList().indexOf(project);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Project("+project.getId()+") with version='"+project.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getProjectList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpProjectList(){
		getProjectList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getUserList(), internalType);
		collectFromList(this, entityList, getProjectList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getUserList());
		listOfList.add( getProjectList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, USER_LIST, getUserList());
		if(!getUserList().isEmpty()){
			appendKeyValuePair(result, "userCount", getUserList().getTotalCount());
			appendKeyValuePair(result, "userCurrentPageNumber", getUserList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PROJECT_LIST, getProjectList());
		if(!getProjectList().isEmpty()){
			appendKeyValuePair(result, "projectCount", getProjectList().getTotalCount());
			appendKeyValuePair(result, "projectCurrentPageNumber", getProjectList().getCurrentPageNumber());
		}

		if (this.valueByKey("valuesOfGroupBy") != null) {
			appendKeyValuePair(result, "valuesOfGroupBy", this.valueByKey("valuesOfGroupBy"));
		}
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Company){
		
		
			Company dest =(Company)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setUserList(getUserList());
			dest.setProjectList(getProjectList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Company){
		
			
			Company dest =(Company)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeUserList(getUserList());
			dest.mergeProjectList(getProjectList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Company){
		
			
			Company dest =(Company)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCreateTime(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Company{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

