
package com.cla.sds.changerequest;

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
import com.cla.sds.eventupdateprofile.EventUpdateProfile;
import com.cla.sds.platform.Platform;
import com.cla.sds.changerequesttype.ChangeRequestType;

@JsonSerialize(using = ChangeRequestSerializer.class)
public class ChangeRequest extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String REMOTE_IP_PROPERTY             = "remoteIp"          ;
	public static final String REQUEST_TYPE_PROPERTY          = "requestType"       ;
	public static final String COMMITED_PROPERTY              = "commited"          ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String EVENT_UPDATE_PROFILE_LIST                = "eventUpdateProfileList";

	public static final String INTERNAL_TYPE="ChangeRequest";
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
	protected		String              	mRemoteIp           ;
	protected		ChangeRequestType   	mRequestType        ;
	protected		boolean             	mCommited           ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<EventUpdateProfile>	mEventUpdateProfileList;

	
		
	public 	ChangeRequest(){
		// lazy load for all the properties
	}
	public 	static ChangeRequest withId(String id){
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(id);
		changeRequest.setVersion(Integer.MAX_VALUE);
		return changeRequest;
	}
	public 	static ChangeRequest refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setRequestType( null );
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
		if(REMOTE_IP_PROPERTY.equals(property)){
			changeRemoteIpProperty(newValueExpr);
		}
		if(COMMITED_PROPERTY.equals(property)){
			changeCommitedProperty(newValueExpr);
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
			
			
			
	protected void changeRemoteIpProperty(String newValueExpr){
	
		String oldValue = getRemoteIp();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateRemoteIp(newValue);
		this.onChangeProperty(REMOTE_IP_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeCommitedProperty(String newValueExpr){
	
		boolean oldValue = getCommited();
		boolean newValue = parseBoolean(newValueExpr);
		if(equalsBoolean(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCommited(newValue);
		this.onChangeProperty(COMMITED_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(REMOTE_IP_PROPERTY.equals(property)){
			return getRemoteIp();
		}
		if(REQUEST_TYPE_PROPERTY.equals(property)){
			return getRequestType();
		}
		if(COMMITED_PROPERTY.equals(property)){
			return getCommited();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(EVENT_UPDATE_PROFILE_LIST.equals(property)){
			List<BaseEntity> list = getEventUpdateProfileList().stream().map(item->item).collect(Collectors.toList());
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
	public ChangeRequest updateId(String id){
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
	public ChangeRequest updateName(String name){
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
	public ChangeRequest updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setRemoteIp(String remoteIp){
		this.mRemoteIp = trimString(remoteIp);;
	}
	public String getRemoteIp(){
		return this.mRemoteIp;
	}
	public ChangeRequest updateRemoteIp(String remoteIp){
		this.mRemoteIp = trimString(remoteIp);;
		this.changed = true;
		return this;
	}
	public void mergeRemoteIp(String remoteIp){
		if(remoteIp != null) { setRemoteIp(remoteIp);}
	}
	
	
	public void setRequestType(ChangeRequestType requestType){
		this.mRequestType = requestType;;
	}
	public ChangeRequestType getRequestType(){
		return this.mRequestType;
	}
	public ChangeRequest updateRequestType(ChangeRequestType requestType){
		this.mRequestType = requestType;;
		this.changed = true;
		return this;
	}
	public void mergeRequestType(ChangeRequestType requestType){
		if(requestType != null) { setRequestType(requestType);}
	}
	
	
	public void clearRequestType(){
		setRequestType ( null );
		this.changed = true;
	}
	
	public void setCommited(boolean commited){
		this.mCommited = commited;;
	}
	public boolean getCommited(){
		return this.mCommited;
	}
	public ChangeRequest updateCommited(boolean commited){
		this.mCommited = commited;;
		this.changed = true;
		return this;
	}
	public void mergeCommited(boolean commited){
		setCommited(commited);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public ChangeRequest updatePlatform(Platform platform){
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
	public ChangeRequest updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<EventUpdateProfile> getEventUpdateProfileList(){
		if(this.mEventUpdateProfileList == null){
			this.mEventUpdateProfileList = new SmartList<EventUpdateProfile>();
			this.mEventUpdateProfileList.setListInternalName (EVENT_UPDATE_PROFILE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mEventUpdateProfileList;	
	}
	public  void setEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList){
		for( EventUpdateProfile eventUpdateProfile:eventUpdateProfileList){
			eventUpdateProfile.setChangeRequest(this);
		}

		this.mEventUpdateProfileList = eventUpdateProfileList;
		this.mEventUpdateProfileList.setListInternalName (EVENT_UPDATE_PROFILE_LIST );
		
	}
	
	public  void addEventUpdateProfile(EventUpdateProfile eventUpdateProfile){
		eventUpdateProfile.setChangeRequest(this);
		getEventUpdateProfileList().add(eventUpdateProfile);
	}
	public  void addEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList){
		for( EventUpdateProfile eventUpdateProfile:eventUpdateProfileList){
			eventUpdateProfile.setChangeRequest(this);
		}
		getEventUpdateProfileList().addAll(eventUpdateProfileList);
	}
	public  void mergeEventUpdateProfileList(SmartList<EventUpdateProfile> eventUpdateProfileList){
		if(eventUpdateProfileList==null){
			return;
		}
		if(eventUpdateProfileList.isEmpty()){
			return;
		}
		addEventUpdateProfileList( eventUpdateProfileList );
		
	}
	public  EventUpdateProfile removeEventUpdateProfile(EventUpdateProfile eventUpdateProfileIndex){
		
		int index = getEventUpdateProfileList().indexOf(eventUpdateProfileIndex);
        if(index < 0){
        	String message = "EventUpdateProfile("+eventUpdateProfileIndex.getId()+") with version='"+eventUpdateProfileIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        EventUpdateProfile eventUpdateProfile = getEventUpdateProfileList().get(index);        
        // eventUpdateProfile.clearChangeRequest(); //disconnect with ChangeRequest
        eventUpdateProfile.clearFromAll(); //disconnect with ChangeRequest
		
		boolean result = getEventUpdateProfileList().planToRemove(eventUpdateProfile);
        if(!result){
        	String message = "EventUpdateProfile("+eventUpdateProfileIndex.getId()+") with version='"+eventUpdateProfileIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return eventUpdateProfile;
        
	
	}
	//断舍离
	public  void breakWithEventUpdateProfile(EventUpdateProfile eventUpdateProfile){
		
		if(eventUpdateProfile == null){
			return;
		}
		eventUpdateProfile.setChangeRequest(null);
		//getEventUpdateProfileList().remove();
	
	}
	
	public  boolean hasEventUpdateProfile(EventUpdateProfile eventUpdateProfile){
	
		return getEventUpdateProfileList().contains(eventUpdateProfile);
  
	}
	
	public void copyEventUpdateProfileFrom(EventUpdateProfile eventUpdateProfile) {

		EventUpdateProfile eventUpdateProfileInList = findTheEventUpdateProfile(eventUpdateProfile);
		EventUpdateProfile newEventUpdateProfile = new EventUpdateProfile();
		eventUpdateProfileInList.copyTo(newEventUpdateProfile);
		newEventUpdateProfile.setVersion(0);//will trigger copy
		getEventUpdateProfileList().add(newEventUpdateProfile);
		addItemToFlexiableObject(COPIED_CHILD, newEventUpdateProfile);
	}
	
	public  EventUpdateProfile findTheEventUpdateProfile(EventUpdateProfile eventUpdateProfile){
		
		int index =  getEventUpdateProfileList().indexOf(eventUpdateProfile);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "EventUpdateProfile("+eventUpdateProfile.getId()+") with version='"+eventUpdateProfile.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getEventUpdateProfileList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpEventUpdateProfileList(){
		getEventUpdateProfileList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getRequestType(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getEventUpdateProfileList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getEventUpdateProfileList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, REMOTE_IP_PROPERTY, getRemoteIp());
		appendKeyValuePair(result, REQUEST_TYPE_PROPERTY, getRequestType());
		appendKeyValuePair(result, COMMITED_PROPERTY, getCommited());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, EVENT_UPDATE_PROFILE_LIST, getEventUpdateProfileList());
		if(!getEventUpdateProfileList().isEmpty()){
			appendKeyValuePair(result, "eventUpdateProfileCount", getEventUpdateProfileList().getTotalCount());
			appendKeyValuePair(result, "eventUpdateProfileCurrentPageNumber", getEventUpdateProfileList().getCurrentPageNumber());
		}

		if (this.valueByKey("valuesOfGroupBy") != null) {
			appendKeyValuePair(result, "valuesOfGroupBy", this.valueByKey("valuesOfGroupBy"));
		}
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
		
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCreateTime(getCreateTime());
			dest.setRemoteIp(getRemoteIp());
			dest.setRequestType(getRequestType());
			dest.setCommited(getCommited());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setEventUpdateProfileList(getEventUpdateProfileList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
			
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeRemoteIp(getRemoteIp());
			dest.mergeRequestType(getRequestType());
			dest.mergeCommited(getCommited());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeEventUpdateProfileList(getEventUpdateProfileList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
			
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeRemoteIp(getRemoteIp());
			dest.mergeCommited(getCommited());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCreateTime(), getRemoteIp(), getRequestType(), getCommited(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ChangeRequest{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tremoteIp='"+getRemoteIp()+"';");
		if(getRequestType() != null ){
 			stringBuilder.append("\trequestType='ChangeRequestType("+getRequestType().getId()+")';");
 		}
		stringBuilder.append("\tcommited='"+getCommited()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

