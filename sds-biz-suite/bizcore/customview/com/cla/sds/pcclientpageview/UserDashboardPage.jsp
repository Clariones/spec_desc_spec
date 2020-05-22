<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ include file="common/header_start.jsp"%>

<title>我的项目</title>

<%@ include file="common/header_end.jsp"%>
${result.user.name}

<ul class="project_panel">
<c:forEach items="${result.user.userProjectList}" var="project">
    <li>
        ${project.project.name}
        <ul class="page_flow_panel"> 页面流
            <c:forEach items="${project.project.pageFlowSpecList}" var="pageFlow">
            <li>
                <div>
                    ${pageFlow.scene}
                    <c:forEach items="${pageFlow.actionList}" var="action">
                        <a href="${action.linkToUrl}">${action.title}</a>
                    </c:forEach>
                </div>
            </li>
            </c:forEach>
        </ul>
        <ul class="work_flow_panel"> 工作流
            <c:forEach items="${project.project.workFlowSpecList}" var="workFlow">
            <li>
                <div>
                    ${workFlow.scene}
                    <c:forEach items="${workFlow.actionList}" var="action">
                        <a href="${action.linkToUrl}">${action.title}</a>
                    </c:forEach>
                </div>
            </li>
            </c:forEach>
        </ul>
        <ul class="change_request_panel"> 表单
            <c:forEach items="${project.project.changeRequestSpecList}" var="changeRequest">
            <li>
                <div>
                    ${changeRequest.scene}
                     <c:forEach items="${changeRequest.actionList}" var="action">
                        <a href="${action.linkToUrl}">${action.title}</a>
                     </c:forEach>
                </div>
            </li>
            </c:forEach>
        </ul>
        <ul class="change_request_panel"> 页面
             <c:forEach items="${project.project.pageContentSpecList}" var="pageContent">
             <li>
                 <div>
                     ${pageContent.scene}
                     <c:forEach items="${pageContent.actionList}" var="action">
                         <a href="${action.linkToUrl}">${action.title}</a>
                      </c:forEach>
                 </div>
             </li>
             </c:forEach>
         </ul>
    </li>
</c:forEach>
</ul>
<%@ include file="common/footer_start.jsp"%>
<script>
<!-- 我要在文件末尾加一些东西 -->
</script>
<%@ include file="common/footer_end.jsp"%>






