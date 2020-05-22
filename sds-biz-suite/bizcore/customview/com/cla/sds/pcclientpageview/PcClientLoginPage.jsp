<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ include file="common/header_start.jsp"%>

<title>登录</title>
<style type="text/css">
* {
 text-shadow: transparent 0px 0px 0px, rgba(0,0,0,0.68) 0px 0px 0px !important;
}
</style>
<style id="style-1-cropbar-clipper">
/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
    top: 18px !important;
    left: 50% !important;
    margin-left: -100px !important;
    width: 200px !important;
    border: 2px rgba(255,255,255,.38) solid !important;
    border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
    margin-left: 0px !important;
}
</style>
<%@ include file="common/header_end.jsp"%>


    <div class="container">
      <c:if test="${not empty result.errorMessageList}" >
    	 <ul>
    	<c:forEach var="item" items="${result.errorMessageList}">
  		<div class="alert alert-danger">
   			${item.sourcePosition} ${item.body}
		</div>
    	</c:forEach>
    	</ul>

    </c:if>

      <form class="form-signin" method="post" action="./clientService/login/login/password/">
        <h2 class="form-signin-heading sysname">请输入用户名和密码</h2>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input type="text" id="inputEmail" name="login" class="form-control" placeholder="用户名/手机号/邮箱" required="" autofocus="">
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required="">
        <input type="hidden" name="" value="abcv0N0qcok4FeDLxU1rv" required="">

        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>

    </div> <!-- /container 	private String usernameParmeter;
	private String passwordParmeter;-->

<%@ include file="common/footer_start.jsp"%>
<script>

		//alert($(location).attr('href'));
		var oldLink=location.href;
		var index=oldLink.indexOf("?__logout__");
		if(index>0){
			var newLink=oldLink.substring(0,index);
			history.pushState({}, '', newLink);
			console.log(newLink);
		}


</script>
<%@ include file="common/footer_end.jsp"%>






