
package com.cla.sds.userproject;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class UserProjectTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="userProject";
	
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
	protected UserProjectTokens(){
		//ensure not initialized outside the class
	}
	public  static  UserProjectTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		UserProjectTokens tokens = new UserProjectTokens(options);
		return tokens;
		
	}
	protected UserProjectTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public UserProjectTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static UserProjectTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected UserProjectTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static UserProjectTokens start(){
		return new UserProjectTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public UserProjectTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static UserProjectTokens allTokens(){
		
		return start()
			.withUser()
			.withProject();
	
	}
	public static UserProjectTokens withoutListsTokens(){
		
		return start()
			.withUser()
			.withProject();
	
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
	
	public UserProjectTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String USER = "user";
	public String getUser(){
		return USER;
	}
	public UserProjectTokens withUser(){		
		addSimpleOptions(USER);
		return this;
	}
	
	
	protected static final String PROJECT = "project";
	public String getProject(){
		return PROJECT;
	}
	public UserProjectTokens withProject(){		
		addSimpleOptions(PROJECT);
		return this;
	}
	
	
	
	public  UserProjectTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

