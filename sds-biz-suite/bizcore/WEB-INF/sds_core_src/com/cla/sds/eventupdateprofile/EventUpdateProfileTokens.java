
package com.cla.sds.eventupdateprofile;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class EventUpdateProfileTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="eventUpdateProfile";
	
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
	protected EventUpdateProfileTokens(){
		//ensure not initialized outside the class
	}
	public  static  EventUpdateProfileTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		EventUpdateProfileTokens tokens = new EventUpdateProfileTokens(options);
		return tokens;
		
	}
	protected EventUpdateProfileTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public EventUpdateProfileTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static EventUpdateProfileTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected EventUpdateProfileTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static EventUpdateProfileTokens start(){
		return new EventUpdateProfileTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public EventUpdateProfileTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static EventUpdateProfileTokens allTokens(){
		
		return start()
			.withChangeRequest();
	
	}
	public static EventUpdateProfileTokens withoutListsTokens(){
		
		return start()
			.withChangeRequest();
	
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
	
	public EventUpdateProfileTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String CHANGEREQUEST = "changeRequest";
	public String getChangeRequest(){
		return CHANGEREQUEST;
	}
	public EventUpdateProfileTokens withChangeRequest(){		
		addSimpleOptions(CHANGEREQUEST);
		return this;
	}
	
	
	
	public  EventUpdateProfileTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

