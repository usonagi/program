//自定义的js操作函数

//设置分页  默认页面  每页条数 总页数  总条数
	var pageNO = 1;
	var pageSize = 8;
	var totalPage = 5;
	var totalCount = 0;

//页面加载时调用的方法
$(function(){
	//向页面显示查询到的所有活动
	//findAllActivity();
	
	//清空搜索栏中的内容  搜索活动
	$("#search_div").html("<input type='text' id='search' placeholder='搜索你想要的(发布者)' />" +
			"<i class='inverted circular search link icon' onclick='findByText()'></i>");
	
	//显示分页条信息
	//pageNO = 1;
	findByPage(pageNO);	//默认第一页
});

//显示所有用户信息
function showUsers(pageNum){

	//清空搜索栏中的内容
	$("#search_div2").html("<input type='text' id='searchUser' placeholder='搜索你想要的(手机号)' />" +
			"<i class='inverted circular search link icon' onclick='serchUsers()'></i>");
	
	pageNO = pageNum;
	
	var url = "/ppca_manage/user?method=findAllUser";

	$.post(url,{"pageNO":pageNO,"pageSize":pageSize},function(data){
		//var json_users = eval(data);  //查询所有用户记录 不分页显示
		
		//分页所需数据 以及分页查询的user记录
		var json_page = eval(data);
		var json_users = json_page.content;
		pageNO = json_page.pageNo;
		pageSize = json_page.pageSize;
		totalPage = json_page.totalPage;
		totalCount = json_page.totalCount;
		
		var html = "";
		
		//向表格中填充数据
		for (var i = 0; i < json_users.length; i++) {
			html += "<tr><td>" + (i + 1) + "</td> <td>" 
					+ json_users[i].uno + "</td><td>" 
					+ json_users[i].username + "</td><td>"
					+ json_users[i].password + "</td><td>"
					+ json_users[i].phone + "</td><td>" 
					+ json_users[i].sex + "</td><td>" 
					+ json_users[i].email+ "</td><td>" 
					+ "<a href='javascript:void(0)' onclick='findUserPhone(\"" 
					+ json_users[i].phone + "\")'>编辑</a></td></tr>"; // 第一个转义双引号表示函数引用参数 第二个表示结束
		}
		
		$("#userbody").html(html);
		
		//显示分页条提示内容
		$("#fyt_tip2").html("<span>共有<strong>"+totalCount+"</strong>条数据，当前是第<strong>"+pageNO+"</strong>页</span>");
		
		//显示分页条内容
		var page = ""; 
		
		//处理上一页 
		if(pageNO == 1){
			page += "<a class='icon item disabled'> <i class='left chevron icon'></i></a>";
		}else{
			page += "<a class='item' href='javascript:void(0)' onclick=showUsers("+(pageNO-1)+")> <i class='left chevron icon'></i></a>";
		}
		
		//当前页码与i一致，则高亮显示当前页码
		for(var i=1; i <= totalPage;i++)
		{
			if(i == pageNO){
				page += "<a class='active item' href='javascript:void(0)' onclick='showUsers("+i+")'>"+i+"</a>";
			}else{
				page += "<a class='item' href='javascript:void(0)' onclick='showUsers("+i+")'>"+i+"</a>";
			}
		}
	
		//处理下一页
		if(pageNO == totalPage){
			page += "<a class='icon item disabled'> <i class='right chevron icon'></i></a>";
		}else{
			page += "<a class='item' href='javascript:void(0)' onclick='showUsers("+(pageNO+1)+")'> <i class='right chevron icon'></i></a>";
		}
		
		$("#fyt2").append(page);
		
	},"json");
	
	$("#fyt2").html("");

}

//按内容搜索用户  暂定使用手机号
function serchUsers(){
	var phone = $("#searchUser").val();

	var url = "/ppca_manage/user?method=findUserPhone";

	$.post(url, {
		"phone" : phone
	}, function(data) {
	
		var json_user = eval(data);
		var html = "";
		var i = 1 ;
		
		html += "<tr><td>" + (i) + "</td><td>"
			+ json_user.uno + "</td><td>"
			+ json_user.username + "</td><td>"
			+ json_user.password + "</td><td>"
			+ json_user.phone + "</td><td>"
			+ json_user.sex + "</td><td>"
			+ json_user.email
			+ "</td><td><a href='javascript:void(0)' onclick='findUserPhone(\"" 
			+ json_user.phone + "\")'>编辑</a></td></tr>"; // 第一个转义双引号表示函数引用参数 第二个表示结束

		$("#userbody").html(html);
		
		//显示分页条相关信息
		totalCount = i;
		totalPage = 1;
		pageNO = 1;
		
		//显示分页条提示内容
		$("#fyt_tip2").html("<span>共有<strong>"+totalCount+"</strong>条数据，当前是第<strong>"+pageNO+"</strong>页</span>");
		
		//处理分页条内容
		var page = "<a class='icon item disabled'> <i class='left chevron icon'></i></a> <a class=' active item'>1</a> " +
				"<a class='icon item disabled'> <i class='right chevron icon'></i></a>";
		
		$("#fyt2").append(page);
		
	},"json");
	
	//清空搜索栏中的内容
	$("#search_div2").html("<input type='text' id='searchUser' placeholder='搜索你想要的(手机号)' />" +
			"<i class='inverted circular search link icon' onclick='serchUsers()'></i>");
	
	//清空分页条内容
	$("#fyt2").html("");

}

//删除用户信息  使用phone查询记录
function delUser(){

	clearDelUserForm();
	
	$("#delUserModal").modal('show');
	
	//为输入框中的搜索图标绑定点击函数
	$("#searchUser2").unbind("click");
	
	$("#searchUser2").bind("click",function(){
		
		//根据输入的phone进行查询
		var phone = $("#Form_delUser input[name='phone']").val();
		
		var url= "/ppca_manage/user?method=findUserPhone";
		
		$.post(url,{ "phone" : phone },function(data){
				
				var user = eval("("+data+")");
				
				//匹配 指定表单元素下所有的input name=特定值 的元素    将查询到的user信息显示在form中
				$("#Form_delUser input[name='uno']").val(user.uno);
				$("#Form_delUser input[name='username']").val(user.username);
				//$("#Form_delUser input[name='password']").val(user.password);
				$("#Form_delUser input[name='phone']").val(user.phone);
				//$("#Form_delUser input[name='sex']").val(user.sex);
				$("#Form_delUser input[name='email']").val(user.email);
				
				
				$("#delUserButton").unbind("click");
				
				//在弹窗中点击删除按钮
				$("#delUserButton").bind("click",function(){
					
					var delUserConfirm = window.confirm("确认删除此用户吗？删除后无法恢复...");
					
					if( delUserConfirm ){
						
						//获取表单的数据
						var json_Form_delUser = $("#Form_delUser").serializeJson();
						
						//向服务器发送请求
						$.post("/ppca_manage/user?method=delUser",
								json_Form_delUser
							,function(data){
							
							//关闭弹窗
							$("#delUserModal").modal('hide');
							
							showUsers(1);
						});
					}
				});
				
			});

	});
	
}

//按用户手机号查询  并对其进行修改
function findUserPhone(phone){
	var url = "/ppca_manage/user?method=findUserPhone";
	
	$.post(url,{"phone":phone},function(data){
		var json_user = eval("("+data+")");
		
		updateUser(json_user);
		
	});
	
}

//对用户信息进行修改
function updateUser(user){
	
	//var user = findUserPhone(phone);
	
	//弹出一个窗口  显示用户信息
	$("#editUserModal").modal('show');
	
	//匹配 id=Form_editUser 元素下所有的input name=特定值 的元素    将查询到的user信息显示在form中
	$("#Form_editUser input[name='uno']").val(user.uno);
	$("#Form_editUser input[name='username']").val(user.username);
	$("#Form_editUser input[name='password']").val(user.password);
	$("#Form_editUser input[name='phone']").val(user.phone);
	$("#Form_editUser input[name='sex']").val(user.sex);
	$("#Form_editUser input[name='email']").val(user.email);
	
	//在绑定函数前，将之前可能的函数解绑，防止出现多次修改
	$("#editUserButton").unbind("click");
	
	//点击修改按钮 完成对Activity信息的修改
	$("#editUserButton").bind("click",function(){
		
		var editUserConfirm = window.confirm("确认修改此用户的信息吗？");
		if( editUserConfirm ){
			//调用serializeJson方法，得到表单数据
			var json_Form_editUser = $("#Form_editUser").serializeJson();
			
			var url_update = "/ppca_manage/user?method=UpdateUser";
			
			$.post(url_update,json_Form_editUser,function(data){
				$("#editUserModal").modal('hide');
				
				showUsers(1);
			});
		}
	});
}

//清空表单Form_editUser中的数据
function clearDelUserForm() {

	$("#Form_delUser div:nth-child(1)").html("<label>手机号码</label> <span class='ui icon input'> " +
			"<input type='text' name='phone' placeholder='请输入手机号码'> <i class='circular search link icon' id='searchUser2'></i></span>");
	$("#Form_delUser div:nth-child(2)").html("<div class='field disabled'><label>用户编号</label> <input type='text' name='uno' placeholder='请确认用户编号'></div>");
	$("#Form_delUser div:nth-child(3)").html("<div class='field disabled'><label>用户名</label> <input type='text' name='username' placeholder='请确认用户名'></div>");
	$("#Form_delUser div:nth-child(4)").html("<div class='field disabled'><label>邮箱</label> <input type='text' name='email' placeholder='请确认用户邮箱'></div>");
	
}

//搜索活动信息  暂定使用发布者进行查询 
//按活动发布者搜索
function findByText(){
	
	var SearchText = $("#search").val();
	
	pageNO = 1;
	pageSize = 5;
	
	showData(1,SearchText);
	
	//清空搜索栏中的内容
	$("#search_div").html("<input type='text' id='search' placeholder='搜索你想要的(发布者)' />" +
			"<i class='inverted circular search link icon' onclick='findByText()'></i>");

}

//显示按发布者名搜索后的结果
function showData(pageNum,uname){

	//更新当前页的页号
	pageNO = pageNum;
	
	var url = "/ppca_manage/activity?method=findByText";

	$.post(url,{"UNAME":uname},function(data){
		var json_activities = eval("("+data+")");
		
		//获得分页 记录总数、总页数
		totalCount = json_activities.length;
		totalPage = Math.ceil(totalCount / pageSize);
		
		//计算每页显示的内容
		var actData = "";
		var i = (pageNum-1)*pageSize;
		var size = i + pageSize;
		if(size > totalCount){
			size = totalCount;
		}
		
		//显示查询到的记录
		for ( i; i < size; i++) {
			actData += "<tr><td>" + (i + 1) + "</td><td>"
					+ json_activities[i].act_id + "</td><td>"
					+ json_activities[i].act_name + "</td><td>"
					+ json_activities[i].act_type + "</td><td>"
					+ json_activities[i].uname + "</td><td>"
					+ json_activities[i].act_status
					+ "</td><td><a href='javascript:void(0)'"
					+ " onclick='findById(\"" + json_activities[i].act_id
					+ "\")'>编辑</a></td></tr>"; // 第一个转义双引号表示函数引用参数 第二个表示结束
		}

		$("#actbody").html(actData);
		
		showFYT(uname);
	});
	
}

// 显示分页信息的函数
function showFYT(uname) {
	$("#fyt").html("");

	// 显示分页条
	var tip = "<span>共有<strong>" + totalCount + "</strong>条数据，当前是第<strong>"
			+ pageNO + "</strong>页</span>";
	$("#fyt_tip").html(tip);

	// 显示分页条内容
	var page = "";

	// 处理上一页
	if (pageNO == 1) {
		page += "<a class='icon item disabled'> <i class='left chevron icon'></i></a>";
	} else {
		page += "<a class='item' href='javascript:void(0)' onclick=\"showData('"+(pageNO-1)+"','"+uname+"')\"> <i class='left chevron icon'></i></a>";
	}

	// 当前页码与i一致，则高亮显示当前页码
	for (var i = 1; i <= totalPage; i++) {
		if (i == pageNO) {
			page += "<a class='active item' href='javascript:void(0)' onclick=\"showData('"+i+"','"+uname+"')\">" + i + "</a>";
		} else {
			page += "<a class='item' href='javascript:void(0)' onclick=\"showData('"+i+"','"+uname+"')\">" + i + "</a>";
		}
	}

	// 处理下一页
	if (pageNO == totalPage) {
		page += "<a class='icon item disabled'> <i class='right chevron icon'></i></a>";
	} else {
		page += "<a class='item' href='javascript:void(0)' onclick=\"showData('"+(pageNO-(-1))+"','"+uname+"')\"> <i class='right chevron icon'></i></a>";
	}

	$("#fyt").append(page);
}


//处理分页条页面跳转的函数

//分页显示所有活动信息
function findByPage(pageNum){
	
	pageNO = pageNum;
	
	var url = "/ppca_manage/activity?method=findByPage";
	
	$.post(url,{"pageNO":pageNO,"pageSize":pageSize},function(data){
		
		//遍历json数据，将分页所需数据与服务器响应数据同步
		var json_page = eval(data);
		var json_activities = json_page.content;	//得到分页后的Activity数据
		pageNO = json_page.pageNo;
		pageSize = json_page.pageSize;
		totalPage = json_page.totalPage;
		totalCount = json_page.totalCount;
		
		//将得到的分页后的Activity填充到表格中
		var html = "";
		for (var i = 0; i < json_activities.length; i++) {
			html += "<tr><td>" + (i + 1) + "</td><td>"
				+ json_activities[i].act_id + "</td><td>"
				+ json_activities[i].act_name + "</td><td>"
				+ json_activities[i].act_type + "</td><td>"
				+ json_activities[i].uname + "</td><td>"
				+ json_activities[i].act_status
				+ "</td><td><a href='javascript:void(0)'"
				+ " onclick='findById(\""
				+ json_activities[i].act_id
				+ "\")'>编辑</a></td></tr>"; //第一个转义双引号表示函数引用参数 第二个表示结束
		}
		$("#actbody").html(html);
		
		
		$("#fyt_tip").html("<span>共有<strong>"+totalCount+"</strong>条数据，当前是第<strong>"+pageNO+"</strong>页</span>");
		
		/*<a class="icon item"> <i class="left chevron icon"></i></a> 
		<a class=" active item">1</a> 
		<a class="item">2</a> 
		<a class="item">3</a> 
		<a class="item">4</a> 
		<a class="icon item"> <i class="right chevron icon"></i></a>*/
		//显示分页条内容
		var page = ""; 
		
		//处理上一页 
		if(pageNO == 1){
			page += "<a class='icon item disabled'> <i class='left chevron icon'></i></a>";
		}else{
			page += "<a class='item' href='javascript:void(0)' onclick='findByPage("+(pageNO-1)+")'> <i class='left chevron icon'></i></a>";
		}
		
		//当前页码与i一致，则高亮显示当前页码
		for(var i=1; i <= totalPage;i++)
		{
			if(i == pageNO){
				page += "<a class='active item' href='javascript:void(0)' onclick='findByPage("+i+")'>"+i+"</a>";
			}else{
				page += "<a class='item' href='javascript:void(0)' onclick='findByPage("+i+")'>"+i+"</a>";
			}
		}
	
		//处理下一页
		if(pageNO == totalPage){
			page += "<a class='icon item disabled'> <i class='right chevron icon'></i></a>";
		}else{
			page += "<a class='item' href='javascript:void(0)' onclick='findByPage("+(pageNO+1)+")'> <i class='right chevron icon'></i></a>";
		}
		
		//page += "<a class='icon item'> <i class='right chevron icon'></i></a>";
		
		$("#fyt").append(page);
		
	},"json");
	$("#fyt").html("");
}


//添加活动
function addActivity(){
	//在显示弹出之前，先将弹窗中的数据清空
	clearAddForm();
	
	//显示添加活动的弹窗
	$('#addModal').modal('show')
	//$('.ui.modal').modal('show');
	
	$("#addButton").unbind("click");
	
	//在弹窗中点击添加按钮
	$("#addButton").bind("click",function(){

		//获取表单的数据
		var json_Form_addActivity = $("#Form_addActivity").serializeJson();
		//向服务器发送请求
		$.post("/ppca_manage/activity?method=addActivity",json_Form_addActivity,function(data){
			//关闭弹窗
			//$('.ui.modal').modal('hide');
			$("#addModal").modal('hide');
			
			//findAllActivity();
			findByPage(pageNO);
		});
	});
}


//通过活动编号ACT_ID删除活动
function delActivity(){
	
	clearDelForm();
	
	//显示删除活动的弹窗
	$('#delModal').modal('show');
	
	//为输入框中的搜索图标绑定点击函数
	$("#search2").unbind("click");
	
	$("#search2").bind("click",function(){
		//根据输入的ACT_ID进行查询
		var act_id = $("#Form_delActivity input[name='act_id']").val();
		
		var url_find = "/ppca_manage/activity?method=findById";
		
		$.post(url_find,{
			"act_id" : act_id
			},function(data){
				
				var json_activity = eval("("+data+")");
				
				//匹配 指定表单元素下所有的input name=特定值 的元素    将查询到的Activity信息显示在form中
				$("#Form_delActivity input[name='act_id']").val(json_activity.act_id);
				$("#Form_delActivity input[name='act_name']").val(json_activity.act_name);
				$("#Form_delActivity input[name='act_type']").val(json_activity.act_type);
				$("#Form_delActivity input[name='uname']").val(json_activity.uname);
				$("#Form_delActivity input[name='act_status']").val(json_activity.act_status);
				
				
				$("#delButton").unbind("click");
				
				//在弹窗中点击删除按钮
				$("#delButton").bind("click",function(){
					
					var delActConfirm = window.confirm("确认删除此活动吗？删除后不可恢复...");
					if( delActConfirm ){
						//获取表单的数据
						var json_Form_delActivity = $("#Form_delActivity").serializeJson();
						//向服务器发送请求
						$.post("/ppca_manage/activity?method=delActivity",
							json_Form_delActivity
							,function(data){
							//关闭弹窗
							$("#delModal").modal('hide');
							
							findByPage(1); //pageNO
						});
					}
				});
				
			});
	});
}



//清空表单 Form_addActivity 中的数据
function clearAddForm(){
	//contains(text)匹配包含给定文本的元素
	//获取name=username的value值
	//nth-child(index),参数index从1开始
	//"checked",true设置属性
	$("#Form_addActivity div:nth-child(1)").html("<label>活动编号</label> <input type='text' name='act_id' placeholder='请输入活动编号'>");
	$("#Form_addActivity div:nth-child(2)").html("<label>活动名称</label> <input type='text' name='act_name' placeholder='请输入活动名称'>");
	$("#Form_addActivity div:nth-child(3)").html("<label>活动类型</label> <input type='text' name='act_type' placeholder='请输入活动类型'>");
	$("#Form_addActivity div:nth-child(4)").html("<label>活动发布者</label> <input type='text' name='uname' placeholder='请输入活动发布者'>");
	$("#Form_addActivity div:nth-child(5)").html("<label>活动状态</label> <input type='text' name='act_status' placeholder='请输入活动状态'>");
	
	//通过给每个input标签id属性进行元素匹配
	//$("#input_id").html("<label>活动编号</label> <input type='text' name='act_Id' placeholder='请输入活动编号'>");
	//$("#input_name").html("<label>活动名称</label> <input type='text' name='act_name' placeholder='请输入活动名称'>");
	//$("#input_type").html("<label>活动类型</label> <input type='text' name='act_type' placeholder='请输入活动类型'>");
	//$("#input_status").html("<label>活动状态</label> <input type='text' name='act_status' placeholder='请输入活动状态'>");
}

//清空表单Form_editActivity中的数据
function clearEditForm(){
	$("#Form_editActivity div:nth-child(1)").html("<label>活动编号</label> <input type='text' name='act_id' placeholder='请输入活动编号'>");
	$("#Form_editActivity div:nth-child(2)").html("<label>活动名称</label> <input type='text' name='act_name' placeholder='请输入活动名称'>");
	$("#Form_editActivity div:nth-child(3)").html("<label>活动类型</label> <input type='text' name='act_type' placeholder='请输入活动类型'>");
	$("#Form_editActivity div:nth-child(4)").html("<label>活动发布者</label> <input type='text' name='uname' placeholder='请输入活动发布者'>");
	$("#Form_editActivity div:nth-child(5)").html("<label>活动状态</label> <input type='text' name='act_status' placeholder='请输入活动状态'>");
}

//清空表单 Form_delActivity中的数据
function clearDelForm(){
	$("#Form_delActivity div:nth-child(1)").html("<label>活动编号</label> <span class='ui icon input'> <input type='text' name='act_id' placeholder=" +
			"'请输入活动编号'> <i class='circular search link icon' id='search2'></i></span>");
	
	$("#Form_delActivity div:nth-child(2)").html("<label>活动名称</label> <input type='text' name='act_name' placeholder='请确认活动名称'>");
	$("#Form_delActivity div:nth-child(3)").html("<label>活动类型</label> <input type='text' name='act_type' placeholder='请确认活动类型'>");
	$("#Form_delActivity div:nth-child(4)").html("<label>活动发布者</label> <input type='text' name='uname' placeholder='请确认活动发布者'>");
	$("#Form_delActivity div:nth-child(5)").html("<label>活动状态</label> <input type='text' name='act_status' placeholder='请确认活动状态'>");
}

//查询所有活动信息
//查询所有的活动信息
function findAllActivity(){
	var url = "/ppca_manage/activity?method=findAllActivity";

	$.post(url,function(data){
		var json_activities = eval(data);
		
		var html = "";
		
		//向表格中填充数据
		for(var i=0; i < json_activities.length; i++){
			html += "<tr><td>"+(i+1)+"</td><td>"+json_activities[i].act_id+"</td><td>"+
					json_activities[i].act_name+"</td><td>"+
					json_activities[i].act_type+"</td><td>"+
					json_activities[i].uname+"</td><td>"+
					json_activities[i].act_status+"</td><td><a href='javascript:void(0)'" +
					" onclick='findById(\""+json_activities[i].act_id+"\")'>编辑</a></td></tr>"; //第一个转义双引号表示函数引用参数 第二个表示结束
		}
		
		$("#actbody").html(html);
	},"json");
}



//根据活动id查询活动  并进行修改操作
function findById(act_id){
	var url_find = "/ppca_manage/activity?method=findById";

	$.post(url_find,{
		"act_id" : act_id
		},function(data){
			//先清空弹窗中之前的数据
			clearEditForm();
			
			//弹出一个窗口  显示信息
			$("#editModal").modal('show');
			
			var json_activity = eval("("+data+")");
			
			//匹配 id=Form_editActivity 元素下所有的input name=特定值 的元素    将查询到的Activity信息显示在form中
			$("#Form_editActivity input[name='act_id']").val(json_activity.act_id);
			$("#Form_editActivity input[name='act_name']").val(json_activity.act_name);
			$("#Form_editActivity input[name='act_type']").val(json_activity.act_type);
			$("#Form_editActivity input[name='uname']").val(json_activity.uname);
			$("#Form_editActivity input[name='act_status']").val(json_activity.act_status);
			
			//在绑定函数前，将之前可能的函数解绑，防止出现多次修改(连续修改两个不同Activity,出现相同结果)
			$("#editButton").unbind("click");
			
			//点击修改按钮 完成对Activity信息的修改
			$("#editButton").bind("click",function(){
				
				var editActConfirm = window.confirm("确认修改此活动吗？");
				if(editActConfirm){
					//调用serializeJson方法，得到表单数据
					var json_Form_editActivity = $("#Form_editActivity").serializeJson();
					
					var url_update = "/ppca_manage/activity?method=Update";
					
					$.post(url_update,json_Form_editActivity,function(data){
						$("#editModal").modal('hide');
						
						//findAllActivity();
						findByPage(1);
					});
				}
			});
			
		});
}


//退出系统  右上角电源键
function shutDown() {
	// 关闭浏览器窗口
	var shut = window.confirm("确认关闭窗口吗？");
	if (shut) {
		// 向admin servlet发送请求
		$.post("/ppca_manage/admin?method=shutDown", function() {

			// 解决浏览器关闭的兼容问题
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
					window.opener = null;
					window.close();
				} else {
					window.open('', '_top');
					window.top.close();
				}
			} else if (navigator.userAgent.indexOf("Firefox") > 0) {
				window.location.href = 'about:blank ';
			} else {
				window.opener = null;
				window.open('', '_self', '');
				window.close();
			}
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

