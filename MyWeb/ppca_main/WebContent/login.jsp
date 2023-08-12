<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PPCA用户登录页面</title>
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
background-image: url(./img/南门全景.jpg);
background-repeat: no-repeat;
background-size:100%,100%;
}

.login-header {
margin-top: 3%;
margin-bottom: 3%;
/* background-image: url(./img/共青团背景.jpg); */
}

#check_div {
	margin-top: 2%;
	margin-bottom: 3%;
	/* width: 48%; */
	height: 30px;
}

#checkImg {
	height: 30px;
}

.checkCodeDiv {
	float: right;
	margin-left: 2.5%;
}
</style>

</head>
<body>

	<div class="ui login-header">
		<div class="ui center aligned header" style="background-color: rgb(20,180,200);">
			<h1 class="ui header white">
				<i class="settings icon"></i>PPCA-用户登录
			</h1>
		</div>
	</div>

	<!-- 将整个页面分成3个column 通过占用中间column 达到居中效果 -->
	<div class="ui three column stackable grid login-div" style="margin-top: 120px;">
		<div class="column"></div>
		<div class="column">
			<form id="loginForm" class="ui fluid form segment"
				style="background-color: rgb(20,180,200);"
				method="post">

				<div class="inline field" id="nameDiv">
					<label class=""></label>
					<div class="ui left icon fluid input">
						<input type="text" name="username" id="username"
							value="${ cookie.remember.value }" placeholder="请输入用户名/手机号" /> <i
							class="user icon"></i>
					</div>
					<span></span>
				</div>

				<div class="inline field" id="pwdDiv">
					<label class=""></label>
					<div class="ui left icon fluid input">
						<input type="password" name="password" id="password"
							placeholder="请输入密码" /> <i class="lock icon"></i>
					</div>
					<span></span>
				</div>

				<div class="field">
					<label></label>
					<div class="ui left icon input" id="check_div">
						<input type="text" name="checkCode" placeholder="请输入验证码">
						<div class="checkCodeDiv">
							<img id="checkImg" src="/ppca_main/CheckImgServlet"
								class="ui right floated image" onclick="changeImage()" />
						</div>
					</div>
				</div>

				<div class="inline filed">
					<input type="button" value="  登    录   " class="ui blue submit button" onclick="login()">
					<a href="/ppca_main/register.jsp"><input type="button" value="  注    册  " class="ui right floated red button"></a>
				</div>

				<div class="filed" style="margin-top: 3%;">
					<div class="ui checkbox">
						<input type="checkbox" name="emember" value="true"> <label>记住我</label>
					</div>
					<span style="margin-left: 20%;"><a>忘记密码?</a></span>
				</div>
			</form>
		</div>
		<div class="column"></div>
	</div>
</body>
<script type="text/javascript">

	/* 切换验证码图片 */
	function changeImage() {
		/* document.getElementById("checkImage").src = "/ppca_main/CheckImgServlet?time="
				+ new Date().getTime(); */
		$("#checkImg").attr("src","/ppca_main/CheckImgServlet?time="+new Date().getTime());
	}
</script>
</html>