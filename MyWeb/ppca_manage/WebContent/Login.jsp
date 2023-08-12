<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PPCA管理员登录页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/semantic.css" />
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/semantic.js"></script>
<style type="text/css">
body{
background-image: url(./img/bk.jpg);
background-repeat: no-repeat;
background-size:100%,100%;
}
.login-header {
margin:0;
height: 60px;
background-color: rgb(200,160,150);
}
#login {
	margin-top: 100px;
	background-color: rgb(200,160,150);
}
/* #check_div {
	margin-top: 2%;
	margin-bottom: 3%;
	width: 48%;
	height: 30px;
} */
.checkCodeDiv {
	/* float: right;
	margin-left: 2.5%;
	margin-top: 2%; */
	width: 40%;
}
#checkImage{
	height: 35px;
}
#loginBtn {
	text-align: center;
}
</style>

</head>
<body>
	
	<div class="ui login-header">
		<div class="ui center aligned header">
			<h1>
				<i class="settings icon"></i>PPCA系统管理-管理员登录
			</h1>
		</div>
	</div>

	<!-- 将整个页面分成3个column 通过占用中间column 达到居中效果 -->
	<div class="ui three column stackable grid login-div">
		<div class="column"></div>
		<div class="column">
			<form id="login" class="ui fluid form segment" 
				action="${ pageContext.request.contextPath }/admin?method=login"
				method="post" >

				<div class="field" id="anoDiv">
					<label></label>
					<div class="ui left icon input">
						<input type="text" name="ANO" value="${ cookie.remember.value }"
							placeholder="请输入用户名" onblur="showAnoMsg()"/><i class="user icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>

				<div class="field" id="pwdDiv">
					<label></label>
					<div class="ui left icon input">
						<input type="password" name="APWD" placeholder="请输入密码" onblur="showPwdMsg()" /> <i
							class="lock icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>

				<div class="field">
					<!-- 提示错误信息 -->
					<label><font color="red" face="楷体" size="4">${ErrorMsg}</font></label>
					<div class="ui left icon input" id="check_div">
						<input type="text" name="checkcode" placeholder="请输入验证码">
						<div class="checkCodeDiv">
							<img id="checkImage" src="/ppca_manage/CheckImgServlet"
								onclick="changeImage()" class="ui right floated image" />
						</div>
					</div>
				</div>

				<div class="ui fluid icon input">
					<input type="submit" value="登录" class="ui blue submit button" id="loginBtn">
				</div>
			</form>
		</div>
		<div class="column"></div>
	</div>
</body>

<script type="text/javascript">
	/* 切换验证码图片 */
	function changeImage() {
		document.getElementById("checkImage").src = "/ppca_manage/CheckImgServlet?time="
				+ new Date().getTime();
	}
	
	var Msg = "";
	
	function showAnoMsg(){
		/* <div class="ui pointing below red basic label">${ PwdErrorMsg }<div> */
		var UnameNull = notNull($("#login input[name='ANO']"));
		if( !UnameNull ){
			Msg = "用户名不能为空!";
			
			var len = $("#anoDiv").find("label");
			if(len == 0){
				$("#anoDiv").prepend("<span class='ui pointing below red basic label' id='anoMsg'>"+Msg+"</span>");
			}else{
				len.html("<span class='ui pointing below red basic label' id='anoMsg'>"+Msg+"</span>");
			}
			//return false;
		}
	}
	
	function showPwdMsg(){
		
		var PwdNull = notNull($("#login input[name='APWD']"));
		if( !PwdNull ){
			Msg = "密码不能为空!";
			var len = $("#pwdDiv").find("label");
			
			if(len == 0){
				$("#pwdDiv").prepend("<label><div class='ui pointing below red basic label' id='pwdMsg'>"+Msg+"</div></label>");
			}else{
				len.html("<div class='ui pointing below red basic label' id='pwdMsg'>"+Msg+"</div>");
			}
			//return false;
		}
	}

	function notNull(obj) {	//判断对象obj 的val 长度是否为0
		var value = $.trim(obj.val());
		if (value.length == 0) {
			return false;
		}
		return true;
	}
</script>
</html>