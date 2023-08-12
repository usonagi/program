//用户注册
function register(){
	
	//非空校验   用户名、密码、确认密码、手机号 
	var NameNull = notNull($("#username"));
	if( NameNull == false){
		tip($("#username"),"用户名不能为空","red");
		return ;
	}
	var PwdNull = notNull($("#password"));
	if( PwdNull == false){
		tip($("#password"),"密码不能为空","red");
		return ;
	}
	var RpwdNull = notNull($("#repassword"));
	if( RpwdNull == false){
		tip($("#repassword"),"确认密码不能为空","red");
		return ;
	}
	var PhoneNull = notNull($("#phone"));
	if( PhoneNull == false){
		tip($("#phone"),"手机号不能为空","red");
		return ;
	}
	
	//格式校验  正则表达式可以上网搜索
	var NameFormat = regex($("#username"),"^[0-9]{1}[0-9a-zA-Z]{2,9}$");  //"^[a-zA-Z]{1}[a-zA-Z0-9]{2,9}$");	
	if( !NameFormat ){
		//tip($("#username"),"用户名必须是数字开头且不能少于3为不能多余10位!","red");
		tip2($("#username"),"用户名必须是数字开头且不能少于3位不能多余10位!");
		return ;
	}
	if($("#password").val() != $("#repassword").val()){
		tip2($("#repassword"),"确认密码与密码不一致!");
		return ;
	}
	var PhoneFormat = regex($("#phone"),"^(1+[34578][0-9]{9})$");
	if( !PhoneFormat ){
		tip2($("#phone"),"手机号码不符合规范!");
		return ;
	}
	
	// 得到表单信息 并将其发送到服务器端
	var json_reg_form = $("#registerForm").serializeJson();
	
	$.post("/ppca_main/user?method=register", json_reg_form,function(data){
		
		var json_reg = eval("("+data+")");
		
		if(json_reg.status == false){
			alert(json_reg.errorJson);
		}else if(json_reg.status == true){
			alert(json_reg.errorJson);
			//跳转到登录页面 进行登录
			location.href = "/ppca_main/login.jsp";
		}

	});

}

//用户登录
function login(){
	//获取表单的username password checkCode
	var json_login_form = $("#loginForm").serializeJson();
	
	//进行校验
	var NameNull = notNull($("#username"));
	if( NameNull == false){
		tip($("#username"),"用户名不能为空","red");
		//alert("用户名不能为空");
		return ;
	}
	var PwdNull = notNull($("#password"));
	if( PwdNull == false){
		tip($("#password"),"密码不能为空","red");
		//alert("密码不能为空");
		return ;
	}
	
	//向服务器发送请求
	$.post("/ppca_main/user?method=login", json_login_form,function(data){
		
		var json_login = eval("(" + data + ")");
		if(json_login.status == false){
			//在页面显示错误信息
			//alert(json_login.errorJson);
			
			var parent = ($("#check_div")).parent();
			var tips = parent.find("label");
			//<font color="red" face="楷体" size="4">${ErrorMsg}</font>
			tips.html("<font color='red' face='楷体' size='3'>" + json_login.errorJson + "</font>");
			
		}else if(json_login.status == true){
			//跳转到主页面
			alert(json_login.errorJson);
			location.href = "/ppca_main/personal.jsp";
		}
	});
}

//切换验证码图片
function changeImage(){
	$("#checkImg").attr("src","/ppca_main/CheckImgServlet?time="+new Date().getTime());
}

//定义一个jQuery函数，完成表单转换成json  jquery/demo/插件
$.fn.extend({
	serializeJson:function(){
		//定义一个JavaScript对象
		var json = {};
		//1、通过jQuery提供的serializeArray方法得到不合要求的json数据
		var String = this.serializeArray();
		//console.info(msg);	//在浏览器的控制台输出
		
		$(String).each(function(){
			//若是出现一个key对应一个value
			//json[this.name] = this.value;
			
			//一个key对应多个value
			if(json[this.name]){	//json中存在this.name对应的key
				//是否一个key对应多个value 是，则装入到数组  否，新建key
				if(!json[this.name].push){	//push是数组可以调用的方法
					json[this.name] = [json[this.name]];	//建立数组 this.name[];
				}
				json[this.name].push(this.value || '');	//装入到数组
				
			}else{
				json[this.name] = this.value || '';
			}
		});
		return json;
	}
});


//页面校验的方法
function notNull(obj) {	//判断对象obj 的val 长度是否为0
	var value = $.trim(obj.val());
	if (value.length == 0) {
		return false;
	}
	return true;
}

function regex(obj,reg) { //判断obj是否符合lambda表达式reg
	var regMatch = new RegExp(reg);
	if (regMatch == null || regMatch == "null") {
		return true;
	}
	var result = $.trim(obj.val());
	if (!regMatch.test(result)) {
		return false;
	}
	return true;
}

function tip(obj, msg, color) {	//显示提示信息
	var parent = obj.parent();
	var tips = parent.find("span");
	if (tips.length == 0) {
		obj.parent().append(
				"<a class='ui red tag label'><font size='3'>"+msg+"</font></a>"
						);
	} else {
		tips.html("<font color=" + color + ">" + msg + "</font>");
	}
}

function tip2(obj, msg){
	alert("dd");
	var parent = obj.parent().parent();
	var tips = parent.find("label");
	tips.append("<span style='margin-left: 5%;'><font color='red'>" + msg + "</font><span>");
}