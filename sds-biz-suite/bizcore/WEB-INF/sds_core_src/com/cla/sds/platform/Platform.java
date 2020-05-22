
package com.cla.sds.platform;

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
import com.cla.sds.company.Company;
import com.cla.sds.changerequesttype.ChangeRequestType;

@JsonSerialize(using = PlatformSerializer.class)
public class Platform extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String COMPANY_LIST                             = "companyList"       ;
	public static final String CHANGE_REQUEST_TYPE_LIST                 = "changeRequestTypeList";
	public static final String CHANGE_REQUEST_LIST                      = "changeRequestList" ;

	public static final String INTERNAL_TYPE="Platform";
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
	protected		DateTime            	mLastUpdateTime     ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Company>  	mCompanyList        ;
	protected		SmartList<ChangeRequestType>	mChangeRequestTypeList;
	protected		SmartList<ChangeRequest>	mChangeRequestList  ;

	
		
	public 	Platform(){
		// lazy load for all the properties
	}
	public 	static Platform withId(String id){
		Platform platform = new Platform();
		platform.setId(id);
		platform.setVersion(Integer.MAX_VALUE);
		return platform;
	}
	public 	static Platform refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){

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
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			changeLastUpdateTimeProperty(newValueExpr);
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
			
			
			
	protected void changeLastUpdateTimeProperty(String newValueExpr){
	
		DateTime oldValue = getLastUpdateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLastUpdateTime(newValue);
		this.onChangeProperty(LAST_UPDATE_TIME_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			return getLastUpdateTime();
		}
		if(COMPANY_LIST.equals(property)){
			List<BaseEntity> list = getCompanyList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CHANGE_REQUEST_TYPE_LIST.equals(property)){
			List<BaseEntity> list = getChangeRequestTypeList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CHANGE_REQUEST_LIST.equals(property)){
			List<BaseEntity> list = getChangeRequestList().stream().map(item->item).collect(Collectors.toList());
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
	public Platform updateId(String id){
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
	public Platform updateName(String name){
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
	public Platform updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
	}
	public DateTime getLastUpdateTime(){
		return this.mLastUpdateTime;
	}
	public Platform updateLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
		this.changed = true;
		return this;
	}
	public void mergeLastUpdateTime(DateTime lastUpdateTime){
		setLastUpdateTime(lastUpdateTime);
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Platform updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Company> getCompanyList(){
		if(this.mCompanyList == null){
			this.mCompanyList = new SmartList<Company>();
			this.mCompanyList.setListInternalName (COMPANY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mCompanyList;	
	}
	public  void setCompanyList(SmartList<Company> companyList){
		for( Company company:companyList){
			company.setPlatform(this);
		}

		this.mCompanyList = companyList;
		this.mCompanyList.setListInternalName (COMPANY_LIST );
		
	}
	
	public  void addCompany(Company company){
		company.setPlatform(this);
		getCompanyList().add(company);
	}
	public  void addCompanyList(SmartList<Company> companyList){
		for( Company company:companyList){
			company.setPlatform(this);
		}
		getCompanyList().addAll(companyList);
	}
	public  void mergeCompanyList(SmartList<Company> companyList){
		if(companyList==null){
			return;
		}
		if(companyList.isEmpty()){
			return;
		}
		addCompanyList( companyList );
		
	}
	public  Company removeCompany(Company companyIndex){
		
		int index = getCompanyList().indexOf(companyIndex);
        if(index < 0){
        	String message = "Company("+companyIndex.getId()+") with version='"+companyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Company company = getCompanyList().get(index);        
        // company.clearPlatform(); //disconnect with Platform
        company.clearFromAll(); //disconnect with Platform
		
		boolean result = getCompanyList().planToRemove(company);
        if(!result){
        	String message = "Company("+companyIndex.getId()+") with version='"+companyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return company;
        
	
	}
	//断舍离
	public  void breakWithCompany(Company company){
		
		if(company == null){
			return;
		}
		company.setPlatform(null);
		//getCompanyList().remove();
	
	}
	
	public  boolean hasCompany(Company company){
	
		return getCompanyList().contains(company);
  
	}
	
	public void copyCompanyFrom(Company company) {

		Company companyInList = findTheCompany(company);
		Company newCompany = new Company();
		companyInList.copyTo(newCompany);
		newCompany.setVersion(0);//will trigger copy
		getCompanyList().add(newCompany);
		addItemToFlexiableObject(COPIED_CHILD, newCompany);
	}
	
	public  Company findTheCompany(Company company){
		
		int index =  getCompanyList().indexOf(company);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Company("+company.getId()+") with version='"+company.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getCompanyList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpCompanyList(){
		getCompanyList().clear();
	}
	
	
	


	public  SmartList<ChangeRequestType> getChangeRequestTypeList(){
		if(this.mChangeRequestTypeList == null){
			this.mChangeRequestTypeList = new SmartList<ChangeRequestType>();
			this.mChangeRequestTypeList.setListInternalName (CHANGE_REQUEST_TYPE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mChangeRequestTypeList;	
	}
	public  void setChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList){
		for( ChangeRequestType changeRequestType:changeRequestTypeList){
			changeRequestType.setPlatform(this);
		}

		this.mChangeRequestTypeList = changeRequestTypeList;
		this.mChangeRequestTypeList.setListInternalName (CHANGE_REQUEST_TYPE_LIST );
		
	}
	
	public  void addChangeRequestType(ChangeRequestType changeRequestType){
		changeRequestType.setPlatform(this);
		getChangeRequestTypeList().add(changeRequestType);
	}
	public  void addChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList){
		for( ChangeRequestType changeRequestType:changeRequestTypeList){
			changeRequestType.setPlatform(this);
		}
		getChangeRequestTypeList().addAll(changeRequestTypeList);
	}
	public  void mergeChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList){
		if(changeRequestTypeList==null){
			return;
		}
		if(changeRequestTypeList.isEmpty()){
			return;
		}
		addChangeRequestTypeList( changeRequestTypeList );
		
	}
	public  ChangeRequestType removeChangeRequestType(ChangeRequestType changeRequestTypeIndex){
		
		int index = getChangeRequestTypeList().indexOf(changeRequestTypeIndex);
        if(index < 0){
        	String message = "ChangeRequestType("+changeRequestTypeIndex.getId()+") with version='"+changeRequestTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ChangeRequestType changeRequestType = getChangeRequestTypeList().get(index);        
        // changeRequestType.clearPlatform(); //disconnect with Platform
        changeRequestType.clearFromAll(); //disconnect with Platform
		
		boolean result = getChangeRequestTypeList().planToRemove(changeRequestType);
        if(!result){
        	String message = "ChangeRequestType("+changeRequestTypeIndex.getId()+") with version='"+changeRequestTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return changeRequestType;
        
	
	}
	//断舍离
	public  void breakWithChangeRequestType(ChangeRequestType changeRequestType){
		
		if(changeRequestType == null){
			return;
		}
		changeRequestType.setPlatform(null);
		//getChangeRequestTypeList().remove();
	
	}
	
	public  boolean hasChangeRequestType(ChangeRequestType changeRequestType){
	
		return getChangeRequestTypeList().contains(changeRequestType);
  
	}
	
	public void copyChangeRequestTypeFrom(ChangeRequestType changeRequestType) {

		ChangeRequestType changeRequestTypeInList = findTheChangeRequestType(changeRequestType);
		ChangeRequestType newChangeRequestType = new ChangeRequestType();
		changeRequestTypeInList.copyTo(newChangeRequestType);
		newChangeRequestType.setVersion(0);//will trigger copy
		getChangeRequestTypeList().add(newChangeRequestType);
		addItemToFlexiableObject(COPIED_CHILD, newChangeRequestType);
	}
	
	public  ChangeRequestType findTheChangeRequestType(ChangeRequestType changeRequestType){
		
		int index =  getChangeRequestTypeList().indexOf(changeRequestType);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ChangeRequestType("+changeRequestType.getId()+") with version='"+changeRequestType.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getChangeRequestTypeList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpChangeRequestTypeList(){
		getChangeRequestTypeList().clear();
	}
	
	
	


	public  SmartList<ChangeRequest> getChangeRequestList(){
		if(this.mChangeRequestList == null){
			this.mChangeRequestList = new SmartList<ChangeRequest>();
			this.mChangeRequestList.setListInternalName (CHANGE_REQUEST_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mChangeRequestList;	
	}
	public  void setChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		for( ChangeRequest changeRequest:changeRequestList){
			changeRequest.setPlatform(this);
		}

		this.mChangeRequestList = changeRequestList;
		this.mChangeRequestList.setListInternalName (CHANGE_REQUEST_LIST );
		
	}
	
	public  void addChangeRequest(ChangeRequest changeRequest){
		changeRequest.setPlatform(this);
		getChangeRequestList().add(changeRequest);
	}
	public  void addChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		for( ChangeRequest changeRequest:changeRequestList){
			changeRequest.setPlatform(this);
		}
		getChangeRequestList().addAll(changeRequestList);
	}
	public  void mergeChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		if(changeRequestList==null){
			return;
		}
		if(changeRequestList.isEmpty()){
			return;
		}
		addChangeRequestList( changeRequestList );
		
	}
	public  ChangeRequest removeChangeRequest(ChangeRequest changeRequestIndex){
		
		int index = getChangeRequestList().indexOf(changeRequestIndex);
        if(index < 0){
        	String message = "ChangeRequest("+changeRequestIndex.getId()+") with version='"+changeRequestIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ChangeRequest changeRequest = getChangeRequestList().get(index);        
        // changeRequest.clearPlatform(); //disconnect with Platform
        changeRequest.clearFromAll(); //disconnect with Platform
		
		boolean result = getChangeRequestList().planToRemove(changeRequest);
        if(!result){
        	String message = "ChangeRequest("+changeRequestIndex.getId()+") with version='"+changeRequestIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return changeRequest;
        
	
	}
	//断舍离
	public  void breakWithChangeRequest(ChangeRequest changeRequest){
		
		if(changeRequest == null){
			return;
		}
		changeRequest.setPlatform(null);
		//getChangeRequestList().remove();
	
	}
	
	public  boolean hasChangeRequest(ChangeRequest changeRequest){
	
		return getChangeRequestList().contains(changeRequest);
  
	}
	
	public void copyChangeRequestFrom(ChangeRequest changeRequest) {

		ChangeRequest changeRequestInList = findTheChangeRequest(changeRequest);
		ChangeRequest newChangeRequest = new ChangeRequest();
		changeRequestInList.copyTo(newChangeRequest);
		newChangeRequest.setVersion(0);//will trigger copy
		getChangeRequestList().add(newChangeRequest);
		addItemToFlexiableObject(COPIED_CHILD, newChangeRequest);
	}
	
	public  ChangeRequest findTheChangeRequest(ChangeRequest changeRequest){
		
		int index =  getChangeRequestList().indexOf(changeRequest);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ChangeRequest("+changeRequest.getId()+") with version='"+changeRequest.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getChangeRequestList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpChangeRequestList(){
		getChangeRequestList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){


		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getCompanyList(), internalType);
		collectFromList(this, entityList, getChangeRequestTypeList(), internalType);
		collectFromList(this, entityList, getChangeRequestList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getCompanyList());
		listOfList.add( getChangeRequestTypeList());
		listOfList.add( getChangeRequestList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, COMPANY_LIST, getCompanyList());
		if(!getCompanyList().isEmpty()){
			appendKeyValuePair(result, "companyCount", getCompanyList().getTotalCount());
			appendKeyValuePair(result, "companyCurrentPageNumber", getCompanyList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CHANGE_REQUEST_TYPE_LIST, getChangeRequestTypeList());
		if(!getChangeRequestTypeList().isEmpty()){
			appendKeyValuePair(result, "changeRequestTypeCount", getChangeRequestTypeList().getTotalCount());
			appendKeyValuePair(result, "changeRequestTypeCurrentPageNumber", getChangeRequestTypeList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CHANGE_REQUEST_LIST, getChangeRequestList());
		if(!getChangeRequestList().isEmpty()){
			appendKeyValuePair(result, "changeRequestCount", getChangeRequestList().getTotalCount());
			appendKeyValuePair(result, "changeRequestCurrentPageNumber", getChangeRequestList().getCurrentPageNumber());
		}

		if (this.valueByKey("valuesOfGroupBy") != null) {
			appendKeyValuePair(result, "valuesOfGroupBy", this.valueByKey("valuesOfGroupBy"));
		}
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
		
			Platform dest =(Platform)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCreateTime(getCreateTime());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setVersion(getVersion());
			dest.setCompanyList(getCompanyList());
			dest.setChangeRequestTypeList(getChangeRequestTypeList());
			dest.setChangeRequestList(getChangeRequestList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergeVersion(getVersion());
			dest.mergeCompanyList(getCompanyList());
			dest.mergeChangeRequestTypeList(getChangeRequestTypeList());
			dest.mergeChangeRequestList(getChangeRequestList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCreateTime(), getLastUpdateTime(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Platform{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tlastUpdateTime='"+getLastUpdateTime()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

