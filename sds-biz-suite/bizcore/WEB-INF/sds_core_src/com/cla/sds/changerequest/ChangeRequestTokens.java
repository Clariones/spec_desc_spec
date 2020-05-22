
package com.cla.sds.changerequest;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class ChangeRequestTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="changeRequest";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected ChangeRequestTokens(){
		//ensure not initialized outside the class
	}
	public  static  ChangeRequestTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ChangeRequestTokens tokens = new ChangeRequestTokens(options);
		return tokens;
		
	}
	protected ChangeRequestTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ChangeRequestTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ChangeRequestTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ChangeRequestTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static ChangeRequestTokens start(){
		return new ChangeRequestTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public ChangeRequestTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static ChangeRequestTokens allTokens(){
		
		return start()
			.withRequestType()
			.withPlatform()
			.withEventUpdateProfileList();
	
	}
	public static ChangeRequestTokens withoutListsTokens(){
		
		return start()
			.withRequestType()
			.withPlatform();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}
	
	public ChangeRequestTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String REQUESTTYPE = "requestType";
	public String getRequestType(){
		return REQUESTTYPE;
	}
	public ChangeRequestTokens withRequestType(){		
		addSimpleOptions(REQUESTTYPE);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ChangeRequestTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String EVENT_UPDATE_PROFILE_LIST = "eventUpdateProfileList";
	public String getEventUpdateProfileList(){
		return EVENT_UPDATE_PROFILE_LIST;
	}
	public ChangeRequestTokens withEventUpdateProfileList(){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST);
		return this;
	}
	public ChangeRequestTokens analyzeEventUpdateProfileList(){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeEventUpdateProfileListEnabled(){		
		
		if(checkOptions(this.options(), EVENT_UPDATE_PROFILE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ChangeRequestTokens extractMoreFromEventUpdateProfileList(String idsSeperatedWithComma){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int eventUpdateProfileListSortCounter = 0;
	public ChangeRequestTokens sortEventUpdateProfileListWith(String field, String descOrAsc){		
		addSortMoreOptions(EVENT_UPDATE_PROFILE_LIST,eventUpdateProfileListSortCounter++, field, descOrAsc);
		return this;
	}
	private int eventUpdateProfileListSearchCounter = 0;
	public ChangeRequestTokens searchEventUpdateProfileListWith(String field, String verb, String value){		
		
		withEventUpdateProfileList();
		addSearchMoreOptions(EVENT_UPDATE_PROFILE_LIST,eventUpdateProfileListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens searchAllTextOfEventUpdateProfileList(String verb, String value){	
		String field = "id|name|fieldGroup|eventInitiatorType|eventInitiatorId";
		addSearchMoreOptions(EVENT_UPDATE_PROFILE_LIST,eventUpdateProfileListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ChangeRequestTokens rowsPerPageOfEventUpdateProfileList(int rowsPerPage){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ChangeRequestTokens currentPageNumberOfEventUpdateProfileList(int currentPageNumber){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ChangeRequestTokens retainColumnsOfEventUpdateProfileList(String[] columns){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST+"RetainColumns",columns);
		return this;
	}
	public ChangeRequestTokens excludeColumnsOfEventUpdateProfileList(String[] columns){		
		addSimpleOptions(EVENT_UPDATE_PROFILE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ChangeRequestTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfEventUpdateProfileList(verb, value);	
		return this;
	}
}

