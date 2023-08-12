<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PPCA-用户注册</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/semantic.css" />
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/semantic.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/user-jsp.js"></script>
<style type="text/css">
body{
background-image: url(./img/图书馆前坪.jpg);
background-repeat: no-repeat;
background-size:100%,100%;
}

.register-header {
margin-top: 3%;
margin-bottom: 3%;
background-color: rgb(20,180,20);
}
</style>
</head>
<body>
	<div class="ui register-header">
		<div class="ui center aligned header">
			<h1 class="ui header white">
				<i class="settings icon"></i>PPCA-用户注册
			</h1>
		</div>
	</div>

	<!-- 将整个页面分成3个column 通过占用中间column 达到居中效果 -->
	<div class="ui three column stackable grid login-div">
		<div class="column"></div>
		<div class="column">
			<form id="registerForm" class="ui fluid form segment"
				method="post" style="background-color: rgb(20,180,20)">

				<div class="required field">
					<label class="">用户名</label>
					<div class="ui left icon input">
						<input type="text" name="username" id="username"
							value="${ cookie.remember.value }" placeholder="请输入用户名/手机号" /> <i
							class="user icon"></i>
					</div>
				</div>

				<div class="required field">
					<label class="">密码</label>
					<div class="ui left icon input">
						<input type="password" name="password" id="password"
							placeholder="请输入密码" /> <i class="lock icon"></i>
					</div>
				</div>
				
				<div class="required field">
					<label class="">确认密码</label>
					<div class="ui left icon input">
						<input type="password" name="repassword" id="repassword"
							placeholder="请确认密码" /> <i class="lock icon"></i>
					</div>
				</div>
				
				<div class="required field" >
					<label class="">手机号码</label>
					<div class="ui left icon input">
						<input type="text" name="phone" id="phone"
							placeholder="请输入手机号" /> <i class="phone square icon"></i>
					</div>
				</div>

				<div class="required inline field">
					<div class="ui checkbox">
						<input type="checkbox" tabindex="0" > <label>我同意上述条款以及条件。</label>
					</div>
				</div>

				<div class="inline filed">
					<a href="/ppca_main/login.jsp"><input type="button" value="  登    录   " class="ui blue submit button"></a>
					<input type="button" value="  注    册  " class="ui right floated red button" onclick="register()">
				</div>
			</form>
		</div>
		<div class="column"></div>
	</div>
	
</body>
</html>