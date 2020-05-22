
package com.cla.sds.platform;
import com.cla.sds.CommonTokens;
import java.util.Map;
public class PlatformTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="platform";
	
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
	protected PlatformTokens(){
		//ensure not initialized outside the class
	}
	public  static  PlatformTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PlatformTokens tokens = new PlatformTokens(options);
		return tokens;
		
	}
	protected PlatformTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PlatformTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PlatformTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PlatformTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	
	
	public static PlatformTokens start(){
		return new PlatformTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	public PlatformTokens withTokenFromListName(String listName){		
		addSimpleOptions(listName);
		return this;
	}
	
	protected static PlatformTokens allTokens(){
		
		return start()
			.withCompanyList()
			.withChangeRequestTypeList()
			.withChangeRequestList();
	
	}
	public static PlatformTokens withoutListsTokens(){
		
		return start();
	
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
	
	public PlatformTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String COMPANY_LIST = "companyList";
	public String getCompanyList(){
		return COMPANY_LIST;
	}
	public PlatformTokens withCompanyList(){		
		addSimpleOptions(COMPANY_LIST);
		return this;
	}
	public PlatformTokens analyzeCompanyList(){		
		addSimpleOptions(COMPANY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeCompanyListEnabled(){		
		
		if(checkOptions(this.options(), COMPANY_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromCompanyList(String idsSeperatedWithComma){		
		addSimpleOptions(COMPANY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int companyListSortCounter = 0;
	public PlatformTokens sortCompanyListWith(String field, String descOrAsc){		
		addSortMoreOptions(COMPANY_LIST,companyListSortCounter++, field, descOrAsc);
		return this;
	}
	private int companyListSearchCounter = 0;
	public PlatformTokens searchCompanyListWith(String field, String verb, String value){		
		
		withCompanyList();
		addSearchMoreOptions(COMPANY_LIST,companyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfCompanyList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(COMPANY_LIST,companyListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfCompanyList(int rowsPerPage){		
		addSimpleOptions(COMPANY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfCompanyList(int currentPageNumber){		
		addSimpleOptions(COMPANY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfCompanyList(String[] columns){		
		addSimpleOptions(COMPANY_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfCompanyList(String[] columns){		
		addSimpleOptions(COMPANY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CHANGE_REQUEST_TYPE_LIST = "changeRequestTypeList";
	public String getChangeRequestTypeList(){
		return CHANGE_REQUEST_TYPE_LIST;
	}
	public PlatformTokens withChangeRequestTypeList(){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST);
		return this;
	}
	public PlatformTokens analyzeChangeRequestTypeList(){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeChangeRequestTypeListEnabled(){		
		
		if(checkOptions(this.options(), CHANGE_REQUEST_TYPE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromChangeRequestTypeList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestTypeListSortCounter = 0;
	public PlatformTokens sortChangeRequestTypeListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_TYPE_LIST,changeRequestTypeListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestTypeListSearchCounter = 0;
	public PlatformTokens searchChangeRequestTypeListWith(String field, String verb, String value){		
		
		withChangeRequestTypeList();
		addSearchMoreOptions(CHANGE_REQUEST_TYPE_LIST,changeRequestTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfChangeRequestTypeList(String verb, String value){	
		String field = "id|name|code|icon|bindTypes|stepConfiguration";
		addSearchMoreOptions(CHANGE_REQUEST_TYPE_LIST,changeRequestTypeListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfChangeRequestTypeList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfChangeRequestTypeList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfChangeRequestTypeList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfChangeRequestTypeList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_TYPE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String CHANGE_REQUEST_LIST = "changeRequestList";
	public String getChangeRequestList(){
		return CHANGE_REQUEST_LIST;
	}
	public PlatformTokens withChangeRequestList(){		
		addSimpleOptions(CHANGE_REQUEST_LIST);
		return this;
	}
	public PlatformTokens analyzeChangeRequestList(){		
		addSimpleOptions(CHANGE_REQUEST_LIST+".anaylze");
		return this;
	}
	public boolean analyzeChangeRequestListEnabled(){		
		
		if(checkOptions(this.options(), CHANGE_REQUEST_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromChangeRequestList(String idsSeperatedWithComma){		
		addSimpleOptions(CHANGE_REQUEST_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int changeRequestListSortCounter = 0;
	public PlatformTokens sortChangeRequestListWith(String field, String descOrAsc){		
		addSortMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSortCounter++, field, descOrAsc);
		return this;
	}
	private int changeRequestListSearchCounter = 0;
	public PlatformTokens searchChangeRequestListWith(String field, String verb, String value){		
		
		withChangeRequestList();
		addSearchMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens searchAllTextOfChangeRequestList(String verb, String value){	
		String field = "id|name|remoteIp";
		addSearchMoreOptions(CHANGE_REQUEST_LIST,changeRequestListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfChangeRequestList(int rowsPerPage){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfChangeRequestList(int currentPageNumber){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfChangeRequestList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfChangeRequestList(String[] columns){		
		addSimpleOptions(CHANGE_REQUEST_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PlatformTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfCompanyList(verb, value);	
		searchAllTextOfChangeRequestTypeList(verb, value);	
		searchAllTextOfChangeRequestList(verb, value);	
		return this;
	}
}

