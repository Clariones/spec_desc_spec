
package com.cla.sds.pageflowspec;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class PageFlowSpecTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="pageFlowSpec";
	
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
	protected PageFlowSpecTokens(){
		//ensure not initialized outside the class
	}
	public  static  PageFlowSpecTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PageFlowSpecTokens tokens = new PageFlowSpecTokens(options);
		return tokens;
		
	}
	protected PageFlowSpecTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PageFlowSpecTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PageFlowSpecTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PageFlowSpecTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static PageFlowSpecTokens start(){
		return new PageFlowSpecTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public PageFlowSpecTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static PageFlowSpecTokens allTokens(){
		
		return start()
			.withOwner()
			.withProject();
	
	}
	public static PageFlowSpecTokens withoutListsTokens(){
		
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
	
	public PageFlowSpecTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String OWNER = "owner";
	public String getOwner(){
		return OWNER;
	}
	public PageFlowSpecTokens withOwner(){		
		addSimpleOptions(OWNER);
		return this;
	}
	
	
	protected static final String PROJECT = "project";
	public String getProject(){
		return PROJECT;
	}
	public PageFlowSpecTokens withProject(){		
		addSimpleOptions(PROJECT);
		return this;
	}
	
	
	
	public  PageFlowSpecTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

