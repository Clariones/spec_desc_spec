
package com.cla.sds.eventupdateprofile;

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
import com.cla.sds.changerequest.ChangeRequest;

@JsonSerialize(using = EventUpdateProfileSerializer.class)
public class EventUpdateProfile extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String AVANTAR_PROPERTY               = "avantar"           ;
	public static final String FIELD_GROUP_PROPERTY           = "fieldGroup"        ;
	public static final String EVENT_INITIATOR_TYPE_PROPERTY  = "eventInitiatorType";
	public static final String EVENT_INITIATOR_ID_PROPERTY    = "eventInitiatorId"  ;
	public static final String CHANGE_REQUEST_PROPERTY        = "changeRequest"     ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="EventUpdateProfile";
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
	protected		String              	mAvantar            ;
	protected		String              	mFieldGroup         ;
	protected		String              	mEventInitiatorType ;
	protected		String              	mEventInitiatorId   ;
	protected		ChangeRequest       	mChangeRequest      ;
	protected		int                 	mVersion            ;
	
	

	
		
	public 	EventUpdateProfile(){
		// lazy load for all the properties
	}
	public 	static EventUpdateProfile withId(String id){
		EventUpdateProfile eventUpdateProfile = new EventUpdateProfile();
		eventUpdateProfile.setId(id);
		eventUpdateProfile.setVersion(Integer.MAX_VALUE);
		return eventUpdateProfile;
	}
	public 	static EventUpdateProfile refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setChangeRequest( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(AVANTAR_PROPERTY.equals(property)){
			changeAvantarProperty(newValueExpr);
		}
		if(FIELD_GROUP_PROPERTY.equals(property)){
			changeFieldGroupProperty(newValueExpr);
		}
		if(EVENT_INITIATOR_TYPE_PROPERTY.equals(property)){
			changeEventInitiatorTypeProperty(newValueExpr);
		}
		if(EVENT_INITIATOR_ID_PROPERTY.equals(property)){
			changeEventInitiatorIdProperty(newValueExpr);
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
			
			
			
	protected void changeAvantarProperty(String newValueExpr){
	
		String oldValue = getAvantar();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateAvantar(newValue);
		this.onChangeProperty(AVANTAR_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeFieldGroupProperty(String newValueExpr){
	
		String oldValue = getFieldGroup();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateFieldGroup(newValue);
		this.onChangeProperty(FIELD_GROUP_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeEventInitiatorTypeProperty(String newValueExpr){
	
		String oldValue = getEventInitiatorType();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateEventInitiatorType(newValue);
		this.onChangeProperty(EVENT_INITIATOR_TYPE_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeEventInitiatorIdProperty(String newValueExpr){
	
		String oldValue = getEventInitiatorId();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateEventInitiatorId(newValue);
		this.onChangeProperty(EVENT_INITIATOR_ID_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(AVANTAR_PROPERTY.equals(property)){
			return getAvantar();
		}
		if(FIELD_GROUP_PROPERTY.equals(property)){
			return getFieldGroup();
		}
		if(EVENT_INITIATOR_TYPE_PROPERTY.equals(property)){
			return getEventInitiatorType();
		}
		if(EVENT_INITIATOR_ID_PROPERTY.equals(property)){
			return getEventInitiatorId();
		}
		if(CHANGE_REQUEST_PROPERTY.equals(property)){
			return getChangeRequest();
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
	public EventUpdateProfile updateId(String id){
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
	public EventUpdateProfile updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setAvantar(String avantar){
		this.mAvantar = trimString(encodeUrl(avantar));;
	}
	public String getAvantar(){
		return this.mAvantar;
	}
	public EventUpdateProfile updateAvantar(String avantar){
		this.mAvantar = trimString(encodeUrl(avantar));;
		this.changed = true;
		return this;
	}
	public void mergeAvantar(String avantar){
		if(avantar != null) { setAvantar(avantar);}
	}
	
	
	public void setFieldGroup(String fieldGroup){
		this.mFieldGroup = trimString(fieldGroup);;
	}
	public String getFieldGroup(){
		return this.mFieldGroup;
	}
	public EventUpdateProfile updateFieldGroup(String fieldGroup){
		this.mFieldGroup = trimString(fieldGroup);;
		this.changed = true;
		return this;
	}
	public void mergeFieldGroup(String fieldGroup){
		if(fieldGroup != null) { setFieldGroup(fieldGroup);}
	}
	
	
	public void setEventInitiatorType(String eventInitiatorType){
		this.mEventInitiatorType = trimString(eventInitiatorType);;
	}
	public String getEventInitiatorType(){
		return this.mEventInitiatorType;
	}
	public EventUpdateProfile updateEventInitiatorType(String eventInitiatorType){
		this.mEventInitiatorType = trimString(eventInitiatorType);;
		this.changed = true;
		return this;
	}
	public void mergeEventInitiatorType(String eventInitiatorType){
		if(eventInitiatorType != null) { setEventInitiatorType(eventInitiatorType);}
	}
	
	
	public void setEventInitiatorId(String eventInitiatorId){
		this.mEventInitiatorId = trimString(eventInitiatorId);;
	}
	public String getEventInitiatorId(){
		return this.mEventInitiatorId;
	}
	public EventUpdateProfile updateEventInitiatorId(String eventInitiatorId){
		this.mEventInitiatorId = trimString(eventInitiatorId);;
		this.changed = true;
		return this;
	}
	public void mergeEventInitiatorId(String eventInitiatorId){
		if(eventInitiatorId != null) { setEventInitiatorId(eventInitiatorId);}
	}
	
	
	public void clearEventInitiatorId(){
		setEventInitiatorId ( null );
		this.changed = true;
	}
	
	public void setChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
	}
	public ChangeRequest getChangeRequest(){
		return this.mChangeRequest;
	}
	public EventUpdateProfile updateChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
		this.changed = true;
		return this;
	}
	public void mergeChangeRequest(ChangeRequest changeRequest){
		if(changeRequest != null) { setChangeRequest(changeRequest);}
	}
	
	
	public void clearChangeRequest(){
		setChangeRequest ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public EventUpdateProfile updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getChangeRequest(), internalType);

		
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
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, AVANTAR_PROPERTY, getAvantar());
		appendKeyValuePair(result, FIELD_GROUP_PROPERTY, getFieldGroup());
		appendKeyValuePair(result, EVENT_INITIATOR_TYPE_PROPERTY, getEventInitiatorType());
		appendKeyValuePair(result, EVENT_INITIATOR_ID_PROPERTY, getEventInitiatorId());
		appendKeyValuePair(result, CHANGE_REQUEST_PROPERTY, getChangeRequest());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		if (this.valueByKey("valuesOfGroupBy") != null) {
			appendKeyValuePair(result, "valuesOfGroupBy", this.valueByKey("valuesOfGroupBy"));
		}
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof EventUpdateProfile){
		
		
			EventUpdateProfile dest =(EventUpdateProfile)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setAvantar(getAvantar());
			dest.setFieldGroup(getFieldGroup());
			dest.setEventInitiatorType(getEventInitiatorType());
			dest.setEventInitiatorId(getEventInitiatorId());
			dest.setChangeRequest(getChangeRequest());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof EventUpdateProfile){
		
			
			EventUpdateProfile dest =(EventUpdateProfile)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAvantar(getAvantar());
			dest.mergeFieldGroup(getFieldGroup());
			dest.mergeEventInitiatorType(getEventInitiatorType());
			dest.mergeEventInitiatorId(getEventInitiatorId());
			dest.mergeChangeRequest(getChangeRequest());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof EventUpdateProfile){
		
			
			EventUpdateProfile dest =(EventUpdateProfile)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAvantar(getAvantar());
			dest.mergeFieldGroup(getFieldGroup());
			dest.mergeEventInitiatorType(getEventInitiatorType());
			dest.mergeEventInitiatorId(getEventInitiatorId());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getAvantar(), getFieldGroup(), getEventInitiatorType(), getEventInitiatorId(), getChangeRequest(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("EventUpdateProfile{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tavantar='"+getAvantar()+"';");
		stringBuilder.append("\tfieldGroup='"+getFieldGroup()+"';");
		stringBuilder.append("\teventInitiatorType='"+getEventInitiatorType()+"';");
		stringBuilder.append("\teventInitiatorId='"+getEventInitiatorId()+"';");
		if(getChangeRequest() != null ){
 			stringBuilder.append("\tchangeRequest='ChangeRequest("+getChangeRequest().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

