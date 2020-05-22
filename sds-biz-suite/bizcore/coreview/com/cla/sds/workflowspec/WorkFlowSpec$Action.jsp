
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty workFlowSpec}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A WorkFlowSpec" style="color: green">${userContext.localeMap['work_flow_spec']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['work_flow_spec.id']}</span> ${workFlowSpec.id}</li>
<li><span>${userContext.localeMap['work_flow_spec.scene']}</span> ${workFlowSpec.scene}</li>
<li><span>${userContext.localeMap['work_flow_spec.brief']}</span> ${workFlowSpec.brief}</li>

	
	</ul>
</div>



</c:if>


