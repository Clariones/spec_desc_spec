
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty pageFlowSpec}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A PageFlowSpec" style="color: green">${userContext.localeMap['page_flow_spec']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['page_flow_spec.id']}</span> ${pageFlowSpec.id}</li>
<li><span>${userContext.localeMap['page_flow_spec.scene']}</span> ${pageFlowSpec.scene}</li>
<li><span>${userContext.localeMap['page_flow_spec.brief']}</span> ${pageFlowSpec.brief}</li>

	
	</ul>
</div>



</c:if>


