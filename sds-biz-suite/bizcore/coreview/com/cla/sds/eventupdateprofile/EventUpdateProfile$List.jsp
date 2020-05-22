<%@ page import='java.util.*,com.cla.sds.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty eventUpdateProfileList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['event_update_profile']}! 
		 <a href="./${ownerBeanName}Manager/addEventUpdateProfile/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty eventUpdateProfileList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("eventUpdateProfileList"); 
 	int totalCount = list.getTotalCount();
 	List pages = list.getPages();
 	pageContext.setAttribute("rowsPerPage",list.getRowsPerPage()); 
 	
 	pageContext.setAttribute("pages",pages); 
 	pageContext.setAttribute("totalCount",totalCount); 
 	//pageContext.setAttribute("accessible",list.isAccessible()); 
 	//the reason using scriptlet here is the el express is currently not able resolv common property from a subclass of list
%>
    
 	   
    <div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		<i class='fa fa-table'></i> ${userContext.localeMap['event_update_profile']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addEventUpdateProfile/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'eventUpdateProfileList' eq action.actionGroup}">
		<a class="btn btn-${action.actionLevel} btn-sm" href="${action.managerBeanName}/${action.actionPath}">${userContext.localeMap[action.localeKey]}</a>
		</c:if>
	</c:forEach>
	</div><!--end of div class="btn-group" -->
	
		 
		 
		 
		 </div>
 </div>
    
    
<div class="table-responsive">


<c:set var="currentPageNumber" value="1"/>	



<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var="page" items="${pages}"> 
<c:set var="classType" value=""/>
<c:if test='${page.selected}' >
<c:set var="classType" value="active"/>
<c:set var="currentPageNumber" value="${page.pageNumber}"/>
</c:if>


	<li class="page-item ${classType}">
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${eventUpdateProfileListName};${eventUpdateProfileListName}CurrentPage=${page.pageNumber};${eventUpdateProfileListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='eventUpdateProfileListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['event_update_profile.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['event_update_profile.name']}</th>
</c:if>
<c:if test="${param.referName ne 'avantar'}">
	<th>${userContext.localeMap['event_update_profile.avantar']}</th>
</c:if>
<c:if test="${param.referName ne 'fieldGroup'}">
	<th>${userContext.localeMap['event_update_profile.field_group']}</th>
</c:if>
<c:if test="${param.referName ne 'eventInitiatorType'}">
	<th>${userContext.localeMap['event_update_profile.event_initiator_type']}</th>
</c:if>
<c:if test="${param.referName ne 'eventInitiatorId'}">
	<th>${userContext.localeMap['event_update_profile.event_initiator_id']}</th>
</c:if>
<c:if test="${param.referName ne 'changeRequest'}">
	<th>${userContext.localeMap['event_update_profile.change_request']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${eventUpdateProfileList}">
				<tr currentVersion='${item.version}' id="eventUpdateProfile-${item.id}" ><td><a class="link-action-removed" href="./eventUpdateProfileManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateEventUpdateProfile/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'avantar'}">	<td contenteditable='true' class='edit-value'  propertyToChange='avantar' storedCellValue='${item.avantar}' prefix='${ownerBeanName}Manager/updateEventUpdateProfile/${result.id}/${item.id}/'>${item.avantar}</td>
</c:if><c:if test="${param.referName ne 'fieldGroup'}">	<td contenteditable='true' class='edit-value'  propertyToChange='fieldGroup' storedCellValue='${item.fieldGroup}' prefix='${ownerBeanName}Manager/updateEventUpdateProfile/${result.id}/${item.id}/'>${item.fieldGroup}</td>
</c:if><c:if test="${param.referName ne 'eventInitiatorType'}">	<td contenteditable='true' class='edit-value'  propertyToChange='eventInitiatorType' storedCellValue='${item.eventInitiatorType}' prefix='${ownerBeanName}Manager/updateEventUpdateProfile/${result.id}/${item.id}/'>${item.eventInitiatorType}</td>
</c:if><c:if test="${param.referName ne 'eventInitiatorId'}">	<td contenteditable='true' class='edit-value'  propertyToChange='eventInitiatorId' storedCellValue='${item.eventInitiatorId}' prefix='${ownerBeanName}Manager/updateEventUpdateProfile/${result.id}/${item.id}/'>${item.eventInitiatorId}</td>
</c:if><c:if test="${param.referName ne 'changeRequest'}">
	<td class="select_candidate_td"
			data-candidate-method="./eventUpdateProfileManager/requestCandidateChangeRequest/${ownerBeanName}/${item.id}/"
			data-switch-method="./eventUpdateProfileManager/transferToAnotherChangeRequest/${item.id}/"
			data-link-template="./changeRequestManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.changeRequest}">
			<a href='./changeRequestManager/view/${item.changeRequest.id}/'>${item.changeRequest.displayName}</a>
			</c:if>
			<c:if test="${empty  item.changeRequest}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>

				<td>

				<a href='#${ownerBeanName}Manager/removeEventUpdateProfile/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyEventUpdateProfileFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


