
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty eventUpdateProfile}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A EventUpdateProfile">${userContext.localeMap['event_update_profile']} ${referName}</b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./eventUpdateProfileManager/view/${eventUpdateProfile.id}/"> ${eventUpdateProfile.id}</a></li>
<li><span>${userContext.localeMap['event_update_profile.name']}</span> ${eventUpdateProfile.name}</li>
<li><span>${userContext.localeMap['event_update_profile.avantar']}</span> ${eventUpdateProfile.avantar}</li>
<li><span>${userContext.localeMap['event_update_profile.field_group']}</span> ${eventUpdateProfile.fieldGroup}</li>
<li><span>${userContext.localeMap['event_update_profile.event_initiator_type']}</span> ${eventUpdateProfile.eventInitiatorType}</li>
<li><span>${userContext.localeMap['event_update_profile.event_initiator_id']}</span> ${eventUpdateProfile.eventInitiatorId}</li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




