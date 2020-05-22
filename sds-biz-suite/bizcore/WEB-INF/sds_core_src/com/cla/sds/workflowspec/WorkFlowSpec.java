
package com.cla.sds.workflowspec;

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

@JsonSerialize(using = WorkFlowSpecSerializer.class)
public class WorkFlowSpec extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String SCENE_PROPERTY                 = "scene"             ;
	public static final String BRIEF_PROPERTY                 = "brief"             ;
	public static final String OWNER_PROPERTY                 = "owner"             ;
	public static final String PROJECT_PROPERTY               = "project"           ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="WorkFlowSpec";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getScene();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mScene              ;
	protected		String              	mBrief              ;
	protected		User                	mOwner              ;
	protected		Project             	mProject            ;
	protected		int                 	mVersion            ;
	
	

	
		
	public 	WorkFlowSpec(){
		// lazy load for all the properties
	}
	public 	static WorkFlowSpec withId(String id){
		WorkFlowSpec workFlowSpec = new WorkFlowSpec();
		workFlowSpec.setId(id);
		workFlowSpec.setVersion(Integer.MAX_VALUE);
		return workFlowSpec;
	}
	public 	static WorkFlowSpec refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setOwner( null );
		setProject( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(SCENE_PROPERTY.equals(property)){
			changeSceneProperty(newValueExpr);
		}
		if(BRIEF_PROPERTY.equals(property)){
			changeBriefProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeSceneProperty(String newValueExpr){
	
		String oldValue = getScene();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateScene(newValue);
		this.onChangeProperty(SCENE_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeBriefProperty(String newValueExpr){
	
		String oldValue = getBrief();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateBrief(newValue);
		this.onChangeProperty(BRIEF_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(SCENE_PROPERTY.equals(property)){
			return getScene();
		}
		if(BRIEF_PROPERTY.equals(property)){
			return getBrief();
		}
		if(OWNER_PROPERTY.equals(property)){
			return getOwner();
		}
		if(PROJECT_PROPERTY.equals(property)){
			return getProject();
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
	public WorkFlowSpec updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setScene(String scene){
		this.mScene = trimString(scene);;
	}
	public String getScene(){
		return this.mScene;
	}
	public WorkFlowSpec updateScene(String scene){
		this.mScene = trimString(scene);;
		this.changed = true;
		return this;
	}
	public void mergeScene(String scene){
		if(scene != null) { setScene(scene);}
	}
	
	
	public void setBrief(String brief){
		this.mBrief = brief;;
	}
	public String getBrief(){
		return this.mBrief;
	}
	public WorkFlowSpec updateBrief(String brief){
		this.mBrief = brief;;
		this.changed = true;
		return this;
	}
	public void mergeBrief(String brief){
		if(brief != null) { setBrief(brief);}
	}
	
	
	public void setOwner(User owner){
		this.mOwner = owner;;
	}
	public User getOwner(){
		return this.mOwner;
	}
	public WorkFlowSpec updateOwner(User owner){
		this.mOwner = owner;;
		this.changed = true;
		return this;
	}
	public void mergeOwner(User owner){
		if(owner != null) { setOwner(owner);}
	}
	
	
	public void clearOwner(){
		setOwner ( null );
		this.changed = true;
	}
	
	public void setProject(Project project){
		this.mProject = project;;
	}
	public Project getProject(){
		return this.mProject;
	}
	public WorkFlowSpec updateProject(Project project){
		this.mProject = project;;
		this.changed = true;
		return this;
	}
	public void mergeProject(Project project){
		if(project != null) { setProject(project);}
	}
	
	
	public void clearProject(){
		setProject ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public WorkFlowSpec updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getOwner(), internalType);
		addToEntityList(this, entityList, getProject(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, SCENE_PROPERTY, getScene());
		appendKeyValuePair(result, BRIEF_PROPERTY, getBrief());
		appendKeyValuePair(result, OWNER_PROPERTY, getOwner());
		appendKeyValuePair(result, PROJECT_PROPERTY, getProject());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		if (this.valueByKey("valuesOfGroupBy") != null) {
			appendKeyValuePair(result, "valuesOfGroupBy", this.valueByKey("valuesOfGroupBy"));
		}
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof WorkFlowSpec){
		
		
			WorkFlowSpec dest =(WorkFlowSpec)baseDest;
		
			dest.setId(getId());
			dest.setScene(getScene());
			dest.setBrief(getBrief());
			dest.setOwner(getOwner());
			dest.setProject(getProject());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof WorkFlowSpec){
		
			
			WorkFlowSpec dest =(WorkFlowSpec)baseDest;
		
			dest.mergeId(getId());
			dest.mergeScene(getScene());
			dest.mergeBrief(getBrief());
			dest.mergeOwner(getOwner());
			dest.mergeProject(getProject());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof WorkFlowSpec){
		
			
			WorkFlowSpec dest =(WorkFlowSpec)baseDest;
		
			dest.mergeId(getId());
			dest.mergeScene(getScene());
			dest.mergeBrief(getBrief());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getScene(), getBrief(), getOwner(), getProject(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("WorkFlowSpec{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tscene='"+getScene()+"';");
		stringBuilder.append("\tbrief='"+getBrief()+"';");
		if(getOwner() != null ){
 			stringBuilder.append("\towner='User("+getOwner().getId()+")';");
 		}
		if(getProject() != null ){
 			stringBuilder.append("\tproject='Project("+getProject().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

