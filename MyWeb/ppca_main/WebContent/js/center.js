//设置分页  默认页面  每页条数 总页数  总条数
	var pageNO = 1;
	var pageSize = 8;
	var totalPage = 5;
	var totalCount = 0;

	
$(function(){
	
	showInfo(1);
});

//更改用户信息
function updateUser(user){
	
	//clearEditForm();
	
	//弹出一个窗口  显示用户信息
	$("#editUserModal").modal('show');
	
	//匹配 id=Form_editUser 元素下所有的input name=特定值 的元素    将查询到的user信息显示在form中
	$("#Form_editUser input[name='username']").val(user.username);
	$("#Form_editUser input[name='sex']").val(user.sex);
	$("#Form_editUser input[name='email']").val(user.email);
	$("#Form_editUser input[name='phone']").val(user.phone);
	
	//在绑定函数前，将之前可能的函数解绑，防止出现多次修改
	$("#editUserButton").unbind("click");
	
	//点击修改按钮 完成对user信息的修改
	$("#editUserButton").bind("click",function(){
		
		//校验信息是否符合要求
		var UnameNull = notNull($("#Form_editUser input[name='username']"));
		if( UnameNull == false){
			//tip($("#phone"),"手机号不能为空","red");
			alert("用户名不能为空!");
			return ;
		}
		
		//^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$
		var EmailFormat = regex($("#Form_editUser input[name='email']"),"^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
		if( EmailFormat == false ){
			//tip($("#phone"),"手机号码不符合规范!","red");
			alert("邮箱不合格");
			return ;
		}
		
		var editConfirm = window.confirm("确认修改吗？");
		
		if( editConfirm ){
			//调用serializeJson方法，得到表单数据
			var json_Form_editUser = $("#Form_editUser").serializeJson();
			
			var url_update = "/ppca_main/user?method=updateUser";
			
			$.post(url_update,json_Form_editUser,function(data){
				$("#editUserModal").modal('hide');
				
				showInfo();
				
				//向用户显示提示信息
				var tipMsg = eval("("+data+")");
				if(tipMsg.status == true){
					alert(tipMsg.errorJson);
				}else if(tipMsg == false){
					alert(tipMsg.errorJson);
				}
			});
		}
	});

}

//修改用户详细信息
function updateUserDetail(user){
	
	var url = "/ppca_main/user?method=getUserDetail";
	var phone = user.phone;
	
	$.post(url,{ "phone":phone },function(data){
		var json_user = eval("("+data+")");
		var user = json_user.content;
		
		if(json_user.status == true){
			var newTab = "<div class='field'><label>键入真实姓名</label> <input type='text' name='realname' placeholder='请输入您的真实姓名'></div>"+
			"<div class='field'><label>键入学号</label> <input type='text' name='stuid' placeholder='请输入您的学号'></div>"+
			"<div class='field'><label>键入专业</label> <input type='text' name='major' placeholder='请输入您的专业'></div>"+
			"<div class='field'><label>键入年龄</label> <input type='text' name='age' placeholder='请输入您的年龄'></div>"
			
			//在表单中的修改按钮前添加内容
			$("#editUserButton").before(newTab);
			
			//clearEditForm2();
			
			//弹出一个窗口  显示用户信息
			$("#editUserModal").modal('show');
			
			//匹配 id=Form_editUser 元素下所有的input name=特定值 的元素    将查询到的user信息显示在form中
			$("#Form_editUser input[name='username']").val(user.username);
			$("#Form_editUser input[name='sex']").val(user.sex);
			$("#Form_editUser input[name='email']").val(user.email);
			$("#Form_editUser input[name='phone']").val(user.phone);
			$("#Form_editUser input[name='realname']").val(user.realname);
			$("#Form_editUser input[name='stuid']").val(user.stuid);
			$("#Form_editUser input[name='major']").val(user.major);
			$("#Form_editUser input[name='age']").val(user.age);
			
			//在绑定函数前，将之前可能的函数解绑，防止出现多次修改
			$("#editUserButton").unbind("click");
			
			//点击修改按钮 完成对user信息的修改
			$("#editUserButton").bind("click",function(){
				
				//对表单内容校验
				var EmailFormat = regex($("#Form_editUser input[name='email']"),"^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
				if( EmailFormat == false ){
					//tip($("#phone"),"手机号码不符合规范!","red");
					alert("邮箱不合格");
					return ;
				}
				
				var StuidNull = notNull($("#Form_editUser input[name='stuid']"));
				if( StuidNull == false){
					alert("学号不能为空");
					return ;
				}
				
				//学号格式 1+[1-9]+[0-9]中任意四个 共6个数字
				var StuidFormat = regex($("#Form_editUser input[name='stuid']"),"^(1+[1-9][0-9]{4})$");
				if( !StuidFormat ){
					alert("学号不合格");
					return ;
				}
				
				var editConfirm = window.confirm("确认修改吗？");
				
				if( editConfirm ){
					//调用serializeJson方法，得到表单数据
					var json_Form_editUser = $("#Form_editUser").serializeJson();
					
					var url_update = "/ppca_main/user?method=updateUserDetail";
					
					$.post(url_update,json_Form_editUser,function(data){
						$("#editUserModal").modal('hide');
						
						showDetails();
						
						//向用户显示提示信息
						var tipMsg = eval("("+data+")");
						if(tipMsg.status == true){
							alert(tipMsg.errorJson);
						}else if(tipMsg == false){
							alert(tipMsg.errorJson);
						}
					});
				}
			});
			
		}else if(json_user.status == false){
			alert(json_user.errorJson);
		}
		
	});
	clearEditForm();
}

//根据手机号进行查询
function findUserPhone(phone){

	var url = "/ppca_main/user?method=findUser";
	var user ;
	$.post(url,{ "phone":phone },function(data){
		var json_user = eval("("+data+")");
		user = json_user.content;
		
		if(json_user.status == true){
			updateUser(user);
		}else if(json_user.status == false){
			alert(json_user.errorJson);
		}
		
	});
}


//显示用户详细信息
function showDetails(){
	
	var phone = "13333333313"; 
	var url = "/ppca_main/user?method=getUserDetail";
	$.post(url,{ "phone":phone },function(data){
		var json_user = eval("("+data+")");
		var Detail = json_user.content;
		
		if (json_user.status == true) {
			var userDetail = "<tr><td>"+Detail.username+"</td><td>"+Detail.phone+"</td><td>"
				+Detail.sex+"</td><td>"+Detail.email+"</td><td>"+Detail.realname+"</td><td>"
				+Detail.stuid+"</td><td>"+Detail.major+"</td><td>"+Detail.age+"</td><td>" +
					"<a href='javascript:void(0)' onclick='updateUserDetail(\"" + Detail.phone+ "\")'>修改</a></td></tr>";
			
			$("#userbody2").html(userDetail);

		} else if (json_user.status == false) {
			//alert(json_user.errorJson);
			//location.href = "/ppca_main/login.html";
			alert(json_user.errorJson+"快去实名认证，完善信息!");
		}
		
	});
}

//显示用户基本信息
function showInfo(){
	
	//向服务器发送请求，获取当前用户信息
	var url = "/ppca_main/user?method=findUser";
	$.post(url,function(data){
		var json_user = eval("("+data+")");
		
		if(json_user.status == true){
			/*$("#userData input[name='username']").val(user.username);
			$("#userData input[name='sex']").val(user.sex);
			$("#userData input[name='phone']").val(user.phone);
			$("#userData input[name='email']").val(user.email);*/
			
			var user = json_user.content;
			
			var userData = "<tr><td>"+user.username+"</td><td>"+user.phone+"</td><td>"
			+user.sex+"</td><td>"+user.email+"</td><td><a href='javascript:void(0)' " +
			"onclick='findUserPhone(\""+user.phone+"\")'>修改</a></td></tr>";
			
			$("#userbody").html(userData);
			
		}else if(json_user.status == false){
			alert(json_user.errorJson);
			location.href = "/ppca_main/login.jsp";
		}
		
	});
	
}

//分页显示活动详情
function showAct(){
	
	//清空搜索栏中的内容
	$("#search_div").html("<input type='text' id='search' placeholder='搜索与你相关的(活动名)' />" +
			"<i class='inverted circular search link icon' onclick='findByActName()'></i>");
	
	//showData(1);
	
	var url = "/ppca_main/activity?method=findActivity";
	
	$.post(url,function(data){
		var json_act = eval("("+data+")");
		
		var length = json_act.content.length;
		var actData = "";
		
		//设置分页参数
		pageSize = 8;
		totalCount = length;
		totalPage = Math.ceil(totalCount / pageSize);	//向上取整
		
		//遍历活动数据
		if(json_act.status == true){
			for(var i = 0; i < length; i++){
				var activities = json_act.content[i];
				
				actData += "<tr><td>"+activities.act_name+"</td><td>"+activities.uname+"</td><td>"
					+activities.act_type+"</td><td>"+activities.act_status+"</td><td>"+activities.number+"</td><td>"
					+activities.introd+"</td><td>"+showDate(activities.deadline)+"</td><td><a href='javascript:void(0)'" +
						" onclick='findByActId(\""+activities.act_id+"\")'>修改</a>/<a href='javascript:void(0)'" +
								" onclick='delActivity(\""+activities.act_id+"\")'>撤销</a></td></tr>";
			}
			$("#actbody").html(actData);
			
			//改进方法  将得到的json数据做参数传递给函数
			showData(1);
			
			//showFYT();
			
		}else if(json_act.status == false){
			alert(json_act.errorJson)
		}
	});
	$("#fyt").html("");
}

//分页显示活动记录的函数
function showData(pageNum){
	
	var url = "/ppca_main/activity?method=findActivity";
	$.post(url,function(data){
		var json = eval("("+data+")");
		
		if(json.status ==true ){
			var acts = json.content;
			var actData = "";
			
			var size = (pageNum-1)*pageSize+pageSize;
			if(size > totalCount){
				size = totalCount;
			}
			var i = (pageNum-1)*pageSize;
			
			for(i ; i < size; i++){
				var activities = acts[i];
				actData += "<tr><td>"+activities.act_name+"</td><td>"+activities.uname+"</td><td>"
					+activities.act_type+"</td><td>"+activities.act_status+"</td><td>"+activities.number+"</td><td>"
					+activities.introd+"</td><td>"+showDate(activities.deadline)+"</td><td><a href='javascript:void(0)'" +
						" onclick='findByActId(\""+activities.act_id+"\")'>修改</a>/<a href='javascript:void(0)'" +
								" onclick='delActivity(\""+activities.act_id+"\")'>撤销</a></td></tr>";
			}
			$("#actbody").html(actData);
		}
	});
	
	pageNO = pageNum;
	showFYT();
}

//显示分页信息的函数
function showFYT(){
	$("#fyt").html("");
	
	//显示分页条
	var tip = "<span>共有<strong>"+totalCount+"</strong>条数据，当前是第<strong>"+pageNO+"</strong>页</span>";
	$("#fyt_tip").html(tip);
	
	//显示分页条内容
	var page = ""; 
	
	//处理上一页 
	if(pageNO == 1){
		page += "<a class='icon item disabled'> <i class='left chevron icon'></i></a>";
	}else{
		page += "<a class='item' href='javascript:void(0)' onclick='showData("+(pageNO-1)+")'> <i class='left chevron icon'></i></a>";
	}
	
	//当前页码与i一致，则高亮显示当前页码
	for(var i=1; i <= totalPage;i++)
	{
		if(i == pageNO){
			page += "<a class='active item' href='javascript:void(0)' onclick='showData("+i+")'>"+i+"</a>";
		}else{
			page += "<a class='item' href='javascript:void(0)' onclick='showData("+i+")'>"+i+"</a>";
		}
	}

	//处理下一页
	if(pageNO == totalPage || totalPage == 0){
		page += "<a class='icon item disabled'> <i class='right chevron icon'></i></a>";
	}else{
		page += "<a class='item' href='javascript:void(0)' onclick='showData("+(pageNO+1)+")'> <i class='right chevron icon'></i></a>";
	}
	
	$("#fyt").append(page);
}

//发布活动
function addActivity(){
	
	//在显示弹出之前，先将弹窗中的数据清空
	clearActAddForm();
	
	//显示添加活动的弹窗
	$('#addModal').modal('show')
	//$('.ui.modal').modal('show');
	
	$("#addButton").unbind("click");
	
	//在弹窗中点击添加按钮
	$("#addButton").bind("click",function(){
		
		//表单校验
		var ActIdNull = notNull($("#Form_addActivity input[name='act_id']"));
		if( !ActIdNull){
			alert("活动编号不能为空!");
			return ;
		}
		//以字母开头 5-8个长度
		var ActIdFormat = regex($("#Form_addActivity input[name='act_id']"),"^[a-zA-Z][a-zA-Z0-9_]{4,8}$");
		if( !ActIdFormat ){
			alert("活动编号不合格!");
			return;
		}
		
		var ActNameNull = notNull($("#Form_addActivity input[name='act_name']"));
		if( !ActNameNull ){
			alert("活动名不能为空!");
			return ;
		}
		
		var DateNull = notNull($("#Form_addActivity input[name='deadline']"));
		if( !DateNull){
			alert("截止日期不能为空!");
			return ;
		}
		var regDate = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-" +
				"(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"
		var DateFormat = regex($("#Form_addActivity input[name='deadline']"),regDate);
		if( !DateFormat ){
			alert("日期格式错误!");
			return ;
		}

		var addConfirm = window.confirm("确认发布此活动吗？");
		
		if( addConfirm ){
			
			//获取表单的数据
			var json_Form_addActivity = $("#Form_addActivity").serializeJson();
			//向服务器发送请求
			$.post("/ppca_main/activity?method=addActivity",json_Form_addActivity,function(data){
				//关闭弹窗
				$("#addModal").modal('hide');
	
				showAct();
				
				//向用户显示提示信息
				var tipMsg = eval("("+data+")");
				if(tipMsg.status == true){
					alert(tipMsg.errorJson);
				}else if(tipMsg == false){
					alert(tipMsg.errorJson);
				}
			});
		}
	});
}

//撤销活动
function delActivity(actId){

	var delConfirm = window.confirm("确认撤销此活动吗？");
	//显示即将删除的活动信息
	
	if( delConfirm ){
		$.post("/ppca_main/activity?method=delActivity",{ "actId":actId },function(data){
			$("#delModal").modal('hide');
			
			showAct();
			
			//向用户显示提示信息
			var tipMsg = eval("("+data+")");
			if(tipMsg.status == true){
				alert(tipMsg.errorJson);
			}else if(tipMsg == false){
				alert(tipMsg.errorJson);
			}
		});
	}
}

//根据活动名查询活动信息
function findByActName(){
	var actName = $("#search").val();
	
	var url = "/ppca_main/activity?method=findByActName";
	
	$.post(url,{"act_name":actName},function(data){
		var json_act = eval("("+data+")");
		var actData = "";
		var length = json_act.content.length;
		
		//遍历活动数据
		if(json_act.status == true){
			for(var i = 0; i < length; i++){
				var activities = json_act.content[i];
				
				actData += "<tr><td>"+activities.act_name+"</td><td>"+activities.uname+"</td><td>"
					+activities.act_type+"</td><td>"+activities.act_status+"</td><td>"+activities.number+"</td><td>"
					+activities.introd+"</td><td>"+showDate(activities.deadline)+"</td><td><a href='javascript:void(0)'" +
						" onclick='findByActId(\""+activities.act_id+"\")'>修改</a>/<a href='javascript:void(0)'" +
								" onclick='delActivity(\""+activities.act_id+"\")'>撤销</a></td></tr>";
			}
			$("#actbody").html(actData);
			
			//显示分页条  暂不分页
			var tip = "<span>共有<strong>"+length+"</strong>条数据，当前是第<strong>"+1+"</strong>页</span>";
			$("#fyt_tip").html(tip);
			
			//处理分页条内容
			var page = "<a class='icon item disabled'> <i class='left chevron icon'></i></a> <a class=' active item'>1</a> " +
					"<a class='icon item disabled'> <i class='right chevron icon'></i></a>";
			
			$("#fyt").append(page);
			
		}else if(json_act.status == false){
			alert(json_act.errorJson)
		}
		
	});
	
	//清空搜索栏中的内容
	$("#search_div").html("<input type='text' id='search' placeholder='搜索与你相关的(活动名)' />" +
			"<i class='inverted circular search link icon' onclick='findByActName()'></i>");
	//清空分页条内容
	$("#fyt").html("");
}

//根据活动编号查询活动  并进行修改
function findByActId(actId){
	
	var url = "/ppca_main/activity?method=findByActId";
	$.post(url,{ "act_id":actId },function(data){
		
		//弹出一个窗口  显示信息
		$("#editModal").modal('show');
		
		var json_activity = eval("("+data+")");
		var actData = json_activity.content;
		
		//匹配 id=Form_editActivity 元素下所有的input name=特定值 的元素    将查询到的Activity信息显示在form中
		$("#Form_editActivity input[name='introd']").val(actData.introd);
		$("#Form_editActivity input[name='act_name']").val(actData.act_name);
		$("#Form_editActivity input[name='act_type']").val(actData.act_type);
		$("#Form_editActivity input[name='uname']").val(actData.uname);
		$("#Form_editActivity input[name='act_status']").val(actData.act_status);
		$("#Form_editActivity input[name='deadline']").val(showDate(actData.deadline));
		$("#Form_editActivity input[name='act_id']").val(actData.act_id);
		
		//在绑定函数前，将之前可能的函数解绑，防止出现多次修改(连续修改两个不同Activity,出现相同结果)
		$("#editButton").unbind("click");
		
		//点击修改按钮 完成对Activity信息的修改
		$("#editButton").bind("click",function(){
			
			//校验表单信息
			var regDate = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-" +
					"(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"
			var DateFormat = regex($("#Form_editActivity input[name='deadline']"),regDate);
			if( !DateFormat ){
				alert("日期格式错误!");
				return ;
			}
			
			
			var editConfirm = window.confirm("确认修改此活动吗？");
			
			if( editConfirm ){
				//调用serializeJson方法，得到表单数据
				var json_Form_editActivity = $("#Form_editActivity").serializeJson();
				
				var url_update = "/ppca_main/activity?method=updateAct";
				
				$.post(url_update,json_Form_editActivity,function(data){
					$("#editModal").modal('hide');
					
					showAct();
					
					//向用户显示提示信息
					var tipMsg = eval("("+data+")");
					if(tipMsg.status == true){
						alert(tipMsg.errorJson);
					}else if(tipMsg == false){
						alert(tipMsg.errorJson);
					}
				});
			}
		});
	});
}

//显示用户认证信息
function showReal(){
	
	var url = "/ppca_main/user?method=getRealUser";
	$.post(url,function(data){
		var json_user = eval("("+data+")");
		
		if(json_user.status == true){
			var realUser = json_user.content;
			
			var userData = "<tr><td>"+realUser.phone+"</td><td>"+realUser.realname+"</td><td>"
					+realUser.stuid+"</td><td>"+realUser.major+"</td><td>"+realUser.age+"</td><td " +
					"class='error'><i class='attention icon'></i>已认证</td></tr>";
			$("#certifybody").html(userData);
		}else if(json_user.status == false){
			alert(json_user.errorJson);
		}
	});
}

//用户实名认证
function certify(){
	
	clearCertifyForm();
	
	//弹出一个窗口  提示用户输入信息
	$("#realModal").modal('show');
	
	//在绑定函数前，将之前可能的函数解绑
	$("#certifyButton").unbind("click");
	
	//点击按钮 完成对信息的认证
	$("#certifyButton").bind("click",function(){
		
		//调用serializeJson方法，得到表单数据
		var json_Form_realUser = $("#Form_certify").serializeJson();
		
		var url_certify = "/ppca_main/user?method=certify";
		
		//进行校验  input[name='username']
		var PhoneNull = notNull($("#Form_certify input[name='phone']"));
		if( PhoneNull == false){
			//tip($("#phone"),"手机号不能为空","red");
			alert("手机号不为空");
			return ;
		}
		var PhoneFormat = regex($("#Form_certify input[name='phone']"),"^(1+[34578][0-9]{9})$");
		if( PhoneFormat == false ){
			//tip($("#phone"),"手机号码不符合规范!","red");
			alert("手机号不合格");
			return ;
		}
		var RealNameNull = notNull($("#Form_certify input[name='realname']"));
		if( RealNameNull == false){
			alert("真实姓名不能为空");
			return ;
		}
		var StuidNull = notNull($("#Form_certify input[name='stuid']"));
		if( StuidNull == false){
			alert("学号不能为空");
			return ;
		}
		//学号格式 1+[1-9]+[0-9]中任意四个 共6个数字
		var StuidFormat = regex($("#Form_certify input[name='stuid']"),"^(1+[1-9][0-9]{4})$");
		if( !StuidFormat ){
			alert("学号不合格");
			return ;
		}
		var MajorNull = notNull($("#Form_certify input[name='major']"));
		if( MajorNull == false){
			alert("专业不能为空");
			return ;
		}
		var AgeFormat = $("#Form_certify input[name='age']").val();
		if( AgeFormat > 70 || AgeFormat < 15 ){
			alert("抱歉!您的年龄暂不支持...");
			return ;
		}
		
		$.post(url_certify,json_Form_realUser,function(data){
			$("#realModal").modal('hide');
			
			showReal();
			
			var json_certify = eval("("+data+")");
			if(json_certify.status == true){
				alert("认证成功");
			}else if(json_certify.status == false){
				alert("认证失败");
			}
		});
	});
	
}

//清空记录
function clearCertifyForm(){
	
	$("#Form_certify div:nth-child(1)").html("<label>手机号码</label> <input type='text' name='phone' placeholder='请输入手机号'>");
	$("#Form_certify div:nth-child(2)").html("<label>真实姓名</label> <input type='text' name='realname' placeholder='请输入真实姓名'>");
	$("#Form_certify div:nth-child(3)").html("<label>学号</label> <input type='text' name='stuid' placeholder='请输入学号'>");
	$("#Form_certify div:nth-child(4)").html("<label>专业</label> <input type='text' name='major' placeholder='请输入专业'>");
	$("#Form_certify div:nth-child(5)").html("<label>真实年龄</label> <input type='text' name='age' placeholder='请输入真实年龄'>");
}

function clearEditForm(){
	
	/*$("#Form_editUser div:nth-child(1)").html("<label>键入新昵称</label> <input type='text' name='username' placeholder=请输入新用户名'>");
	$("#Form_editUser div:nth-child(2)").html("<label>键入性别</label> <input type='text' name='sex' placeholder=请输入性别'>");
	$("#Form_editUser div:nth-child(3)").html("<label>键入邮箱</label> <input type='text' name='email' placeholder=请输入新邮箱'>");
	$("#Form_editUser div:nth-child(4)").html("<label>键入手机号</label> <input type='text' name='phone' placeholder=请输入手机号'>");*/
	
	var form = "<div class='field'><label>键入用户名</label> <input type='text' name='username' placeholder='请输入新用户名'></div>"+
			"<div class='field'><label>键入性别</label> <input type='text' name='sex' placeholder='请输入性别'></div>"+
			"<div class='field'><label>键入邮箱</label> <input type='text' name='email' placeholder='请输入新邮箱'></div>"+
			"<div class='field' style='display: none'><label>确认手机号</label> <input type='text' name='phone' placeholder='请输入手机号'></div>"+
			"<input class='ui orange basic button' type='button' value='确认修改' id='editUserButton' />";
	
	$("#Form_editUser").html(form);
}

function clearEditForm2(){
	
	$("#Form_editUser div:nth-child(1)").html("<label>键入新昵称</label> <input type='text' name='username' placeholder=请输入新用户名'>");
	$("#Form_editUser div:nth-child(2)").html("<label>键入性别</label> <input type='text' name='sex' placeholder=请输入性别'>");
	$("#Form_editUser div:nth-child(3)").html("<label>键入邮箱</label> <input type='text' name='email' placeholder=请输入新邮箱'>");
	$("#Form_editUser div:nth-child(4)").html("<label>键入手机号</label> <input type='text' name='phone' placeholder=请输入手机号'>");
	$("#Form_editUser div:nth-child(5)").html("<label>更正真实姓名</label> <input type='text' name='realname' placeholder=请输入手机号'>");
	$("#Form_editUser div:nth-child(6)").html("<label>键入学号</label> <input type='text' name='stuid' placeholder=请输入手机号'>");
	$("#Form_editUser div:nth-child(7)").html("<label>键入专业</label> <input type='text' name='major' placeholder=请输入手机号'>");
	$("#Form_editUser div:nth-child(8)").html("<label>键入年龄</label> <input type='text' name='age' placeholder=请输入手机号'>");
	
}

function clearActAddForm(){
	$("#Form_addActivity div:nth-child(1)").html("<div class='field' ><label>请输入活动编号</label> <input type='text'" +
			" name='act_id' placeholder='字母开始后接数字(5-8位)'></div>");
	$("#Form_addActivity div:nth-child(2)").html("<div class='field' ><label>请输入活动名称</label> <input type='text'" +
			" name='act_name' placeholder='请输入活动名称'></div>");
	$("#Form_addActivity div:nth-child(3)").html("<div class='field' ><label>请输入活动类型</label> <input type='text'" +
			" name='act_type' placeholder='请输入活动类型'></div>");
	$("#Form_addActivity div:nth-child(4)").html("<div class='field' ><label>请输入活动状态</label> <input type='text'" +
			" name='act_status' placeholder='请输入活动状态'></div>");
	$("#Form_addActivity div:nth-child(5)").html("<div class='field' ><label>请输入活动简介</label> <input type='text'" +
			" name='introd' placeholder='请输入活动简介'></div>");
	/*$("#Form_addActivity div:nth-child(6)").html("<div class='field' ><label>活动发起者</label> <input type='text'" +
			" name='uname' placeholder='请输入活动发起者'></div>");*/
	$("#Form_addActivity div:nth-child(7)").html("<div class='field' ><label>截止时间 年-月-日</label> <input type='text'" +
			" name='deadline' placeholder='日期格式 2022-02-02'></div>");
}

//退出系统  右上角电源键
function signOut() {
	// 关闭浏览器窗口
	var shut = window.confirm("确认退出登录吗？");
	if (shut) {
		$.post("/ppca_main/user?method=shutDown", function() {
			
			//回到系统主界面
			location.href = "/ppca_main/index.jsp";
			
		});
	}
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

//日期格式转换   1900-1-1:-2209017600000
function showDate(ymd){
	if("-2209017600000" == ymd){ //未设置日期
		return "/";
	}
	
	date = new Date(ymd);
	var year = date.getFullYear();	//2022
	var month = date.getMonth()+1;	//0-11
	var day = date.getDate();		//1-31
	if(month < 10){
		month = "0" + month;
	}
	if(day < 10){
		day = "0" + day;
	}
	return year+"-"+month+"-"+day;	//year-month-day
}

//页面校验的方法
function notNull(obj) {	//判断对象obj 的val 长度是否为0
	var value = $.trim(obj.val());
	if (value.length == 0) {
		return false;
	}
	return true;
}

function regex(obj,reg) { //判断obj是否符合lambda表达式reg
	//alert(obj.val());
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
				"<span><font color=" + color + ">" + msg
						+ "</font></span>");
	} else {
		tips.html("<font color=" + color + ">" + msg + "</font>");
	}
}