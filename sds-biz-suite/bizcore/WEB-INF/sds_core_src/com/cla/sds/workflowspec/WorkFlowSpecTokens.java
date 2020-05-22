
package com.cla.sds.workflowspec;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class WorkFlowSpecTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="workFlowSpec";
	
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
	protected WorkFlowSpecTokens(){
		//ensure not initialized outside the class
	}
	public  static  WorkFlowSpecTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		WorkFlowSpecTokens tokens = new WorkFlowSpecTokens(options);
		return tokens;
		
	}
	protected WorkFlowSpecTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public WorkFlowSpecTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static WorkFlowSpecTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected WorkFlowSpecTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static WorkFlowSpecTokens start(){
		return new WorkFlowSpecTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public WorkFlowSpecTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static WorkFlowSpecTokens allTokens(){
		
		return start()
			.withOwner()
			.withProject();
	
	}
	public static WorkFlowSpecTokens withoutListsTokens(){
		
		return start()
			.withOwner()
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
	
	public WorkFlowSpecTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String OWNER = "owner";
	public String getOwner(){
		return OWNER;
	}
	public WorkFlowSpecTokens withOwner(){		
		addSimpleOptions(OWNER);
		return this;
	}
	
	
	protected static final String PROJECT = "project";
	public String getProject(){
		return PROJECT;
	}
	public WorkFlowSpecTokens withProject(){		
		addSimpleOptions(PROJECT);
		return this;
	}
	
	
	
	public  WorkFlowSpecTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

