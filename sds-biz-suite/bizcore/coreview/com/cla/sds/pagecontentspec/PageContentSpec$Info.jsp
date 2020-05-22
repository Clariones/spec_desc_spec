
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty pageContentSpec}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A PageContentSpec">${userContext.localeMap['page_content_spec']} ${referName}</b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./pageContentSpecManager/view/${pageContentSpec.id}/"> ${pageContentSpec.id}</a></li>
<li><span>${userContext.localeMap['page_content_spec.scene']}</span> ${pageContentSpec.scene}</li>
<li><span>${userContext.localeMap['page_content_spec.brief']}</span> ${pageContentSpec.brief}</li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




