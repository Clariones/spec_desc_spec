
package com.cla.sds.user;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class UserTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="user";
	
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
	protected UserTokens(){
		//ensure not initialized outside the class
	}
	public  static  UserTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		UserTokens tokens = new UserTokens(options);
		return tokens;
		
	}
	protected UserTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public UserTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static UserTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected UserTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static UserTokens start(){
		return new UserTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public UserTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static UserTokens allTokens(){
		
		return start()
			.withCompany()
			.withUserProjectList()
			.withPageFlowSpecList()
			.withWorkFlowSpecList()
			.withChangeRequestSpecList()
			.withPageContentSpecList();
	
	}
	public static UserTokens withoutListsTokens(){
		
		return start()
			.withCompany();
	
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
	
	public UserTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String COMPANY = "company";
	public String getCompany(){
		return COMPANY;
	}
	public UserTokens withCompany(){		
		addSimpleOptions(COMPANY);
		return this;
	}
	
	
	protected static final String USER_PROJECT_LIST = "userProjectList";
	public String getUserProjectList(){
		return USER_PROJECT_LIST;
	}
	public UserTokens withUserProjectList(){		
		addSimpleOptions(USER_PROJECT_LIST);
		return this;
	}
	public UserTokens analyzeUserProjectList(){		
		addSimpleOptions(USER_PROJECT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeUserProjectListEnabled(){		
		
		if(checkOptions(this.options(), USER_PROJECT_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromUserProjectList(String idsSeperatedWithComma){		
		addSimpleOptions(USER_PROJECT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int userProjectListSortCounter = 0;
	public UserTokens sortUserProjectListWith(String field, String descOrAsc){		
		addSortMoreOptions(USER_PROJECT_LIST,userProjectListSortCounter++, field, descOrAsc);
		return this;
	}
	private int userProjectListSearchCounter = 0;
	public UserTokens searchUserProjectListWith(String field, String verb, String value){		
		
		withUserProjectList();
		addSearchMoreOptions(USER_PROJECT_LIST,userProjectListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfUserProjectList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(USER_PROJECT_LIST,userProjectListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfUserProjectList(int rowsPerPage){		
		addSimpleOptions(USER_PROJECT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfUserProjectList(int currentPageNumber){		
		addSimpleOptions(USER_PROJECT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfUserProjectList(String[] columns){		
		addSimpleOptions(USER_PROJECT_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfUserProjectList(String[] columns){		
		addSimpleOptions(USER_PROJECT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PAGE_FLOW_SPEC_LIST = "pageFlowSpecList";
	public String getPageFlowSpecList(){
		return PAGE_FLOW_SPEC_LIST;
	}
	public UserTokens withPageFlowSpecList(){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST);
		return this;
	}
	public UserTokens analyzePageFlowSpecList(){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+".anaylze");
		return this;
	}
	public boolean analyzePageFlowSpecListEnabled(){		
		
		if(checkOptions(this.options(), PAGE_FLOW_SPEC_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromPageFlowSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int pageFlowSpecListSortCounter = 0;
	public UserTokens sortPageFlowSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(PAGE_FLOW_SPEC_LIST,pageFlowSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int pageFlowSpecListSearchCounter = 0;
	public UserTokens searchPageFlowSpecListWith(String field, String verb, String value){		
		
		withPageFlowSpecList();
		addSearchMoreOptions(PAGE_FLOW_SPEC_LIST,pageFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfPageFlowSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(PAGE_FLOW_SPEC_LIST,pageFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfPageFlowSpecList(int rowsPerPage){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfPageFlowSpecList(int currentPageNumber){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfPageFlowSpecList(String[] columns){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfPageFlowSpecList(String[] columns){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String WORK_FLOW_SPEC_LIST = "workFlowSpecList";
	public String getWorkFlowSpecList(){
		return WORK_FLOW_SPEC_LIST;
	}
	public UserTokens withWorkFlowSpecList(){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST);
		return this;
	}
	public UserTokens analyzeWorkFlowSpecList(){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+".anaylze");
		return this;
	}
	public boolean analyzeWorkFlowSpecListEnabled(){		
		
		if(checkOptions(this.options(), WORK_FLOW_SPEC_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromWorkFlowSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int workFlowSpecListSortCounter = 0;
	public UserTokens sortWorkFlowSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(WORK_FLOW_SPEC_LIST,workFlowSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int workFlowSpecListSearchCounter = 0;
	public UserTokens searchWorkFlowSpecListWith(String field, String verb, String value){		
		
		withWorkFlowSpecList();
		addSearchMoreOptions(WORK_FLOW_SPEC_LIST,workFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfWorkFlowSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(WORK_FLOW_SPEC_LIST,workFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfWorkFlowSpecList(int rowsPerPage){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfWorkFlowSpecList(int currentPageNumber){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfWorkFlowSpecList(String[] columns){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfWorkFlowSpecList(String[] columns){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CHANGE_REQUEST_SPEC_LIST = "changeRequestSpecList";
	public String getChangeRequestSpecList(){
		return CHANGE_REQUEST_SPEC_LIST;
	}
	public UserTokens withChangeRequestSpecList(){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST);
		return this;
	}
	public UserTokens analyzeChangeRequestSpecList(){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+".anaylze");
		return this;
	}
	public boolean analyzeChangeRequestSpecListEnabled(){		
		
		if(checkOptions(this.options(), CHANGE_REQUEST_SPEC_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromChangeRequestSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestSpecListSortCounter = 0;
	public UserTokens sortChangeRequestSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_SPEC_LIST,changeRequestSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestSpecListSearchCounter = 0;
	public UserTokens searchChangeRequestSpecListWith(String field, String verb, String value){		
		
		withChangeRequestSpecList();
		addSearchMoreOptions(CHANGE_REQUEST_SPEC_LIST,changeRequestSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfChangeRequestSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(CHANGE_REQUEST_SPEC_LIST,changeRequestSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfChangeRequestSpecList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfChangeRequestSpecList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfChangeRequestSpecList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfChangeRequestSpecList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PAGE_CONTENT_SPEC_LIST = "pageContentSpecList";
	public String getPageContentSpecList(){
		return PAGE_CONTENT_SPEC_LIST;
	}
	public UserTokens withPageContentSpecList(){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST);
		return this;
	}
	public UserTokens analyzePageContentSpecList(){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+".anaylze");
		return this;
	}
	public boolean analyzePageContentSpecListEnabled(){		
		
		if(checkOptions(this.options(), PAGE_CONTENT_SPEC_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public UserTokens extractMoreFromPageContentSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int pageContentSpecListSortCounter = 0;
	public UserTokens sortPageContentSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(PAGE_CONTENT_SPEC_LIST,pageContentSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int pageContentSpecListSearchCounter = 0;
	public UserTokens searchPageContentSpecListWith(String field, String verb, String value){		
		
		withPageContentSpecList();
		addSearchMoreOptions(PAGE_CONTENT_SPEC_LIST,pageContentSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens searchAllTextOfPageContentSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(PAGE_CONTENT_SPEC_LIST,pageContentSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public UserTokens rowsPerPageOfPageContentSpecList(int rowsPerPage){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public UserTokens currentPageNumberOfPageContentSpecList(int currentPageNumber){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public UserTokens retainColumnsOfPageContentSpecList(String[] columns){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public UserTokens excludeColumnsOfPageContentSpecList(String[] columns){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  UserTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfUserProjectList(verb, value);	
		searchAllTextOfPageFlowSpecList(verb, value);	
		searchAllTextOfWorkFlowSpecList(verb, value);	
		searchAllTextOfChangeRequestSpecList(verb, value);	
		searchAllTextOfPageContentSpecList(verb, value);	
		return this;
	}
}

