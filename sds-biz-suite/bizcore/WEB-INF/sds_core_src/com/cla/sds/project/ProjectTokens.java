
package com.cla.sds.project;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class ProjectTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="project";
	
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
	protected ProjectTokens(){
		//ensure not initialized outside the class
	}
	public  static  ProjectTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ProjectTokens tokens = new ProjectTokens(options);
		return tokens;
		
	}
	protected ProjectTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ProjectTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ProjectTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ProjectTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static ProjectTokens start(){
		return new ProjectTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public ProjectTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static ProjectTokens allTokens(){
		
		return start()
			.withCompany()
			.withUserProjectList()
			.withPageFlowSpecList()
			.withWorkFlowSpecList()
			.withChangeRequestSpecList()
			.withPageContentSpecList();
	
	}
	public static ProjectTokens withoutListsTokens(){
		
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
	
	public ProjectTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String COMPANY = "company";
	public String getCompany(){
		return COMPANY;
	}
	public ProjectTokens withCompany(){		
		addSimpleOptions(COMPANY);
		return this;
	}
	
	
	protected static final String USER_PROJECT_LIST = "userProjectList";
	public String getUserProjectList(){
		return USER_PROJECT_LIST;
	}
	public ProjectTokens withUserProjectList(){		
		addSimpleOptions(USER_PROJECT_LIST);
		return this;
	}
	public ProjectTokens analyzeUserProjectList(){		
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
	public ProjectTokens extractMoreFromUserProjectList(String idsSeperatedWithComma){		
		addSimpleOptions(USER_PROJECT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int userProjectListSortCounter = 0;
	public ProjectTokens sortUserProjectListWith(String field, String descOrAsc){		
		addSortMoreOptions(USER_PROJECT_LIST,userProjectListSortCounter++, field, descOrAsc);
		return this;
	}
	private int userProjectListSearchCounter = 0;
	public ProjectTokens searchUserProjectListWith(String field, String verb, String value){		
		
		withUserProjectList();
		addSearchMoreOptions(USER_PROJECT_LIST,userProjectListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens searchAllTextOfUserProjectList(String verb, String value){	
		String field = "id";
		addSearchMoreOptions(USER_PROJECT_LIST,userProjectListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens rowsPerPageOfUserProjectList(int rowsPerPage){		
		addSimpleOptions(USER_PROJECT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProjectTokens currentPageNumberOfUserProjectList(int currentPageNumber){		
		addSimpleOptions(USER_PROJECT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProjectTokens retainColumnsOfUserProjectList(String[] columns){		
		addSimpleOptions(USER_PROJECT_LIST+"RetainColumns",columns);
		return this;
	}
	public ProjectTokens excludeColumnsOfUserProjectList(String[] columns){		
		addSimpleOptions(USER_PROJECT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PAGE_FLOW_SPEC_LIST = "pageFlowSpecList";
	public String getPageFlowSpecList(){
		return PAGE_FLOW_SPEC_LIST;
	}
	public ProjectTokens withPageFlowSpecList(){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST);
		return this;
	}
	public ProjectTokens analyzePageFlowSpecList(){		
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
	public ProjectTokens extractMoreFromPageFlowSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int pageFlowSpecListSortCounter = 0;
	public ProjectTokens sortPageFlowSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(PAGE_FLOW_SPEC_LIST,pageFlowSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int pageFlowSpecListSearchCounter = 0;
	public ProjectTokens searchPageFlowSpecListWith(String field, String verb, String value){		
		
		withPageFlowSpecList();
		addSearchMoreOptions(PAGE_FLOW_SPEC_LIST,pageFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens searchAllTextOfPageFlowSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(PAGE_FLOW_SPEC_LIST,pageFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens rowsPerPageOfPageFlowSpecList(int rowsPerPage){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProjectTokens currentPageNumberOfPageFlowSpecList(int currentPageNumber){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProjectTokens retainColumnsOfPageFlowSpecList(String[] columns){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public ProjectTokens excludeColumnsOfPageFlowSpecList(String[] columns){		
		addSimpleOptions(PAGE_FLOW_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String WORK_FLOW_SPEC_LIST = "workFlowSpecList";
	public String getWorkFlowSpecList(){
		return WORK_FLOW_SPEC_LIST;
	}
	public ProjectTokens withWorkFlowSpecList(){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST);
		return this;
	}
	public ProjectTokens analyzeWorkFlowSpecList(){		
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
	public ProjectTokens extractMoreFromWorkFlowSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int workFlowSpecListSortCounter = 0;
	public ProjectTokens sortWorkFlowSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(WORK_FLOW_SPEC_LIST,workFlowSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int workFlowSpecListSearchCounter = 0;
	public ProjectTokens searchWorkFlowSpecListWith(String field, String verb, String value){		
		
		withWorkFlowSpecList();
		addSearchMoreOptions(WORK_FLOW_SPEC_LIST,workFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens searchAllTextOfWorkFlowSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(WORK_FLOW_SPEC_LIST,workFlowSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens rowsPerPageOfWorkFlowSpecList(int rowsPerPage){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProjectTokens currentPageNumberOfWorkFlowSpecList(int currentPageNumber){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProjectTokens retainColumnsOfWorkFlowSpecList(String[] columns){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public ProjectTokens excludeColumnsOfWorkFlowSpecList(String[] columns){		
		addSimpleOptions(WORK_FLOW_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CHANGE_REQUEST_SPEC_LIST = "changeRequestSpecList";
	public String getChangeRequestSpecList(){
		return CHANGE_REQUEST_SPEC_LIST;
	}
	public ProjectTokens withChangeRequestSpecList(){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST);
		return this;
	}
	public ProjectTokens analyzeChangeRequestSpecList(){		
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
	public ProjectTokens extractMoreFromChangeRequestSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestSpecListSortCounter = 0;
	public ProjectTokens sortChangeRequestSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_SPEC_LIST,changeRequestSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestSpecListSearchCounter = 0;
	public ProjectTokens searchChangeRequestSpecListWith(String field, String verb, String value){		
		
		withChangeRequestSpecList();
		addSearchMoreOptions(CHANGE_REQUEST_SPEC_LIST,changeRequestSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens searchAllTextOfChangeRequestSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(CHANGE_REQUEST_SPEC_LIST,changeRequestSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens rowsPerPageOfChangeRequestSpecList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProjectTokens currentPageNumberOfChangeRequestSpecList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProjectTokens retainColumnsOfChangeRequestSpecList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public ProjectTokens excludeColumnsOfChangeRequestSpecList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PAGE_CONTENT_SPEC_LIST = "pageContentSpecList";
	public String getPageContentSpecList(){
		return PAGE_CONTENT_SPEC_LIST;
	}
	public ProjectTokens withPageContentSpecList(){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST);
		return this;
	}
	public ProjectTokens analyzePageContentSpecList(){		
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
	public ProjectTokens extractMoreFromPageContentSpecList(String idsSeperatedWithComma){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int pageContentSpecListSortCounter = 0;
	public ProjectTokens sortPageContentSpecListWith(String field, String descOrAsc){		
		addSortMoreOptions(PAGE_CONTENT_SPEC_LIST,pageContentSpecListSortCounter++, field, descOrAsc);
		return this;
	}
	private int pageContentSpecListSearchCounter = 0;
	public ProjectTokens searchPageContentSpecListWith(String field, String verb, String value){		
		
		withPageContentSpecList();
		addSearchMoreOptions(PAGE_CONTENT_SPEC_LIST,pageContentSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens searchAllTextOfPageContentSpecList(String verb, String value){	
		String field = "id|scene|brief";
		addSearchMoreOptions(PAGE_CONTENT_SPEC_LIST,pageContentSpecListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProjectTokens rowsPerPageOfPageContentSpecList(int rowsPerPage){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProjectTokens currentPageNumberOfPageContentSpecList(int currentPageNumber){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProjectTokens retainColumnsOfPageContentSpecList(String[] columns){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"RetainColumns",columns);
		return this;
	}
	public ProjectTokens excludeColumnsOfPageContentSpecList(String[] columns){		
		addSimpleOptions(PAGE_CONTENT_SPEC_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ProjectTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfUserProjectList(verb, value);	
		searchAllTextOfPageFlowSpecList(verb, value);	
		searchAllTextOfWorkFlowSpecList(verb, value);	
		searchAllTextOfChangeRequestSpecList(verb, value);	
		searchAllTextOfPageContentSpecList(verb, value);	
		return this;
	}
}

