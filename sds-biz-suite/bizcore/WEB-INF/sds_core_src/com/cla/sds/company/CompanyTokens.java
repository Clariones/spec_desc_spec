
package com.cla.sds.company;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class CompanyTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="company";
	
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
	protected CompanyTokens(){
		//ensure not initialized outside the class
	}
	public  static  CompanyTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		CompanyTokens tokens = new CompanyTokens(options);
		return tokens;
		
	}
	protected CompanyTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public CompanyTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static CompanyTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected CompanyTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static CompanyTokens start(){
		return new CompanyTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public CompanyTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static CompanyTokens allTokens(){
		
		return start()
			.withPlatform()
			.withUserList()
			.withProjectList();
	
	}
	public static CompanyTokens withoutListsTokens(){
		
		return start()
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
	
	public CompanyTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public CompanyTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String USER_LIST = "userList";
	public String getUserList(){
		return USER_LIST;
	}
	public CompanyTokens withUserList(){		
		addSimpleOptions(USER_LIST);
		return this;
	}
	public CompanyTokens analyzeUserList(){		
		addSimpleOptions(USER_LIST+".anaylze");
		return this;
	}
	public boolean analyzeUserListEnabled(){		
		
		if(checkOptions(this.options(), USER_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public CompanyTokens extractMoreFromUserList(String idsSeperatedWithComma){		
		addSimpleOptions(USER_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int userListSortCounter = 0;
	public CompanyTokens sortUserListWith(String field, String descOrAsc){		
		addSortMoreOptions(USER_LIST,userListSortCounter++, field, descOrAsc);
		return this;
	}
	private int userListSearchCounter = 0;
	public CompanyTokens searchUserListWith(String field, String verb, String value){		
		
		withUserList();
		addSearchMoreOptions(USER_LIST,userListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public CompanyTokens searchAllTextOfUserList(String verb, String value){	
		String field = "id|name|title";
		addSearchMoreOptions(USER_LIST,userListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public CompanyTokens rowsPerPageOfUserList(int rowsPerPage){		
		addSimpleOptions(USER_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public CompanyTokens currentPageNumberOfUserList(int currentPageNumber){		
		addSimpleOptions(USER_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public CompanyTokens retainColumnsOfUserList(String[] columns){		
		addSimpleOptions(USER_LIST+"RetainColumns",columns);
		return this;
	}
	public CompanyTokens excludeColumnsOfUserList(String[] columns){		
		addSimpleOptions(USER_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PROJECT_LIST = "projectList";
	public String getProjectList(){
		return PROJECT_LIST;
	}
	public CompanyTokens withProjectList(){		
		addSimpleOptions(PROJECT_LIST);
		return this;
	}
	public CompanyTokens analyzeProjectList(){		
		addSimpleOptions(PROJECT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeProjectListEnabled(){		
		
		if(checkOptions(this.options(), PROJECT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public CompanyTokens extractMoreFromProjectList(String idsSeperatedWithComma){		
		addSimpleOptions(PROJECT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int projectListSortCounter = 0;
	public CompanyTokens sortProjectListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROJECT_LIST,projectListSortCounter++, field, descOrAsc);
		return this;
	}
	private int projectListSearchCounter = 0;
	public CompanyTokens searchProjectListWith(String field, String verb, String value){		
		
		withProjectList();
		addSearchMoreOptions(PROJECT_LIST,projectListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public CompanyTokens searchAllTextOfProjectList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(PROJECT_LIST,projectListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public CompanyTokens rowsPerPageOfProjectList(int rowsPerPage){		
		addSimpleOptions(PROJECT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public CompanyTokens currentPageNumberOfProjectList(int currentPageNumber){		
		addSimpleOptions(PROJECT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public CompanyTokens retainColumnsOfProjectList(String[] columns){		
		addSimpleOptions(PROJECT_LIST+"RetainColumns",columns);
		return this;
	}
	public CompanyTokens excludeColumnsOfProjectList(String[] columns){		
		addSimpleOptions(PROJECT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  CompanyTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfUserList(verb, value);	
		searchAllTextOfProjectList(verb, value);	
		return this;
	}
}

