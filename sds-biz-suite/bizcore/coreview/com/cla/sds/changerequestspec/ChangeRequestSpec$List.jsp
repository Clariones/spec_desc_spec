<%@ page import='java.util.*,com.cla.sds.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty changeRequestSpecList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['change_request_spec']}! 
		 <a href="./${ownerBeanName}Manager/addChangeRequestSpec/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty changeRequestSpecList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("changeRequestSpecList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['change_request_spec']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addChangeRequestSpec/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'changeRequestSpecList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${changeRequestSpecListName};${changeRequestSpecListName}CurrentPage=${page.pageNumber};${changeRequestSpecListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='changeRequestSpecListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['change_request_spec.id']}</th>
</c:if>
<c:if test="${param.referName ne 'scene'}">
	<th>${userContext.localeMap['change_request_spec.scene']}</th>
</c:if>
<c:if test="${param.referName ne 'brief'}">
	<th>${userContext.localeMap['change_request_spec.brief']}</th>
</c:if>
<c:if test="${param.referName ne 'owner'}">
	<th>${userContext.localeMap['change_request_spec.owner']}</th>
</c:if>
<c:if test="${param.referName ne 'project'}">
	<th>${userContext.localeMap['change_request_spec.project']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${changeRequestSpecList}">
				<tr currentVersion='${item.version}' id="changeRequestSpec-${item.id}" ><td><a class="link-action-removed" href="./changeRequestSpecManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'scene'}">	<td contenteditable='true' class='edit-value'  propertyToChange='scene' storedCellValue='${item.scene}' prefix='${ownerBeanName}Manager/updateChangeRequestSpec/${result.id}/${item.id}/'>${item.scene}</td>
</c:if><c:if test="${param.referName ne 'brief'}">	<td contenteditable='true' class='edit-value'  propertyToChange='brief' storedCellValue='${item.brief}' prefix='${ownerBeanName}Manager/updateChangeRequestSpec/${result.id}/${item.id}/'><a title='${item.brief}'>${fn:substring(item.brief,0,10)}...</a></td>
</c:if><c:if test="${param.referName ne 'owner'}">
	<td class="select_candidate_td"
			data-candidate-method="./changeRequestSpecManager/requestCandidateOwner/${ownerBeanName}/${item.id}/"
			data-switch-method="./changeRequestSpecManager/transferToAnotherOwner/${item.id}/"
			data-link-template="./userManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.owner}">
			<a href='./userManager/view/${item.owner.id}/'>${item.owner.displayName}</a>
			</c:if>
			<c:if test="${empty  item.owner}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'project'}">
	<td class="select_candidate_td"
			data-candidate-method="./changeRequestSpecManager/requestCandidateProject/${ownerBeanName}/${item.id}/"
			data-switch-method="./changeRequestSpecManager/transferToAnotherProject/${item.id}/"
			data-link-template="./projectManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.project}">
			<a href='./projectManager/view/${item.project.id}/'>${item.project.displayName}</a>
			</c:if>
			<c:if test="${empty  item.project}">
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

				<a href='#${ownerBeanName}Manager/removeChangeRequestSpec/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyChangeRequestSpecFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


