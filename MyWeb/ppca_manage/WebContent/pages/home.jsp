<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>PPCA后台管理系统</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/semantic.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />

<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/semantic.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/home.js"></script>

<body>
	<!--网页头部-->
	<div class="ui clearing segment">
		<h3 class="ui left floated header">PPCA后台管理系统</h3>
		<div class="ui right floated header">
			<div class="ui horizontal list">
				<div class="item">
					<!-- <i class="user icon"></i> -->
					<a href="javascript:;">${ existAdmin.aname }</a>
					<a href="javascript:;" onclick="shutDown()"><i class="power icon"></i></a>
				</div>
			</div>
		</div>
	</div>

	<!--网页主体-->
	<div class="ui grid">
		<!--左半部分-->
		<div class="two wide column">
			<div class="ui inverted segment">
				<div class="ui inverted accordion">
					<div class="title">
						<i class="dropdown icon"></i> <i class="user icon"></i>用户管理
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition hidden header item" data-tab="one" onclick="showUsers('1')">用户管理</div>
					</div>
					
					<div class="title active">
						<i class="dropdown icon"></i> <i class="tasks icon"></i>活动管理
					</div>
					<div class="content active item vertical tabular menu">
						<div class="transition visible header item" data-tab="two">活动列表</div>
					</div>
					
					<div class="title">
						<i class="dropdown icon"></i> <i class="keyboard outline icon"></i>其他管理
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition hidden header item" data-tab="three">网站管理</div>
					</div>
					
					<div class="title">
						<i class="dropdown icon"></i> <i class="protect icon"></i>安全中心
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition hidden header item" data-tab="four">修改密码</div>
						<div class="transition hidden header item" data-tab="five">修改昵称</div>
						<div class="transition hidden header item" data-tab="six">账号注销</div>
					</div>
					
					<div class="title">
						<i class="dropdown icon"></i> <i class="question circle icon"></i>帮助中心
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition hidden header item" data-tab="seven">联系我们</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 右半部分 表格 -->
		<div class="fourteen wide white column">
			
			<!-- 用户管理  -->
			<div class="ui tab segment" data-tab="one" >
				<div class="ui grid">
					<div class="wide  column">
						<div class="ui tab segment active" >
							<div class="ui breadcrumb">
								<i class="home icon"></i> <a class="section">用户管理</a> <i
									class="right angle icon divider"></i> <a class="section">用户列表</a>
							</div>

							<!-- 功能按钮  -->
							<div class="btns">
								<div class="settings">
									<div class="ui red fade button" tabindex="0">
										<div onclick="delUser()">删除用户</div>
									</div>
								</div>
								<div class="search">
									<div class="ui icon input" id="search_div2">
										<input type="text" id="searchUser" placeholder="搜索你想要的" /> <i
											class="inverted circular search link icon" onclick="serchUsers()"></i>
									</div>
								</div>
							</div>
							<div style="clear: both;"></div>

							<!-- 显示所有用户信息 不包括密码 -->
							<div class="items">
								<table class="ui sortable celled table">
									<thead>
										<tr>
											<th><i class="write square icon"></i></th>
											<th colspan="9">用户信息列表</th>
										</tr>
									</thead>
									<thead>
										<tr>
											<th>序号</th>
											<th>用户编号</th>
											<th>用户名</th>
											<th>用户密码</th>
											<th>手机号码</th>
											<th>性别</th>
											<th>邮箱</th>
											<th>操作</th>
										</tr>
									</thead>

									<!-- 显示所有信息 -->
									<tbody id="userbody" >

									</tbody>

									<!-- 分页条 分页显示数据 -->
									<tfoot>
										<tr>
											<th colspan="2" id="fyt_tip2">
												<span>共有<strong>10</strong>条数据，当前是第<strong>1</strong>页</span>
											</th>

											<th colspan="9">
												<div class="ui right floated pagination menu" id="fyt2">
													<!-- <a class="icon item"> <i class="left chevron icon"></i></a> 
													<a class=" active item">1</a> 
													<a class="item">2</a> 
													<a class="item">3</a> 
													<a class="item">4</a> 
													<a class="icon item"> <i class="right chevron icon"></i></a> -->
													<!-- <a class=" active item">1</a> -->
												</div>
											</th>
										</tr>
									</tfoot>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 活动管理 -->
			<div class="ui active tab segment" data-tab="two">
				<div class="ui grid">
					<div class="wide  column">
						<div class="ui tab segment active" data-tab="first">
							<div class="ui breadcrumb">
								<i class="home icon"></i> <a class="section">活动管理</a> <i
									class="right angle icon divider"></i> <a class="section">活动列表</a>
							</div>

							<!-- 功能按钮 添加活动 -->
							<div class="btns">
								<div class="settings">
									<div class="ui green fade button" tabindex="0">
										<div class="visible content" onclick="addActivity()">新增活动</div>
									</div>
								</div>
								<div class="settings">
									<div class="ui red fade button" tabindex="0">
										<div onclick="delActivity()" >删除活动</div>
									</div>
								</div>
								<div class="search">
									<div class="ui icon input" id="search_div">
										<input type="text" id="search" placeholder="搜索你想要的" />
										<i class="inverted circular search link icon" onclick="findByText()"></i>
									</div>
								</div>
							</div>
							<div style="clear: both;"></div>

							<!-- 显示所有活动信息 -->
							<div class="items">
								<table class="ui sortable celled table">
									<thead>
										<tr>
											<th><i class="write square icon"></i></th>
											<th colspan="9">活动列表信息</th>
										</tr>
									</thead>
									<thead>
										<tr>
											<th>序号</th>
											<th>活动编号</th>
											<th>活动名称</th>
											<th>活动类型</th>
											<th>发布者</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>

									<!-- 显示所有活动信息 -->
									<tbody id="actbody">

									</tbody>

									<!-- 分页条 分页显示数据 -->
									<tfoot>
										<tr>
											<th colspan="2" id="fyt_tip">
												<!-- <span>共有<strong>10</strong>条数据，当前是第<strong>1</strong>页</span> -->
											</th>

											<th colspan="9">
												<div class="ui right floated pagination menu" id="fyt">
													<!-- <a class="icon item"> <i class="left chevron icon"></i></a> 
													<a class=" active item">1</a> 
													<a class="item">2</a> 
													<a class="item">3</a> 
													<a class="item">4</a> 
													<a class="icon item"> <i class="right chevron icon"></i></a> -->
												</div>
											</th>
										</tr>
									</tfoot>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 其他管理 -->
			<div class="ui tab segment" data-tab="three">
				<div class="ui huge header" align="center">更多精彩内容正在开发中...</div>
			</div>
			
			<!-- 安全中心 -->
			<!-- <div class="ui tab segment" data-tab="four">修改密码</div>
			<div class="ui tab segment" data-tab="five">修改昵称</div> -->
			<!-- <div class="ui tab segment" data-tab="six">账号注销</div> -->
			
			<!-- 帮助中心 -->
			<div class="ui tab segment" data-tab="seven">
			<div class="ui two column grid" style="margin-top: 30px;">
					<div class="column" align="center">
						<div class="ui segment">
							<img class="ui centered medium img" src="../img/湖工二维码.jpg">
						</div>
						<span><font size="4">湖南工学院微信公众号</font></span>
					</div>
					
					<div class="column" align="center">
						<div class="ui segment">
							<img class="ui centered medium img" src="../img/湖工logo.jpg">
							
						</div>
						<span><font size="4">计信学院QQ:1431745061</font></span>
					</div>
					
				</div>
			</div>
		</div>

		<!-- 弹出层   添加活动 -->
		<div class="ui modal" id="addModal">
			<i class="close icon"></i>
			<div class="header">活动添加</div>
			<div class="image content">
				<!-- 新增一个表单 用于添加活动 -->
				<form class="ui form" id="Form_addActivity">
					<div class="field" id="input_id" >
						<label>活动编号</label> <input type="text" name="act_id" 
							placeholder="请输入活动编号">
					</div>
					<div class="field" id="input_name" >
						<label>活动名称</label> <input type="text" name="act_name"
							placeholder="请输入活动名称">
					</div>
					<div class="field" id="input_type" >
						<label>活动类型</label> <input type="text" name="act_type"
							placeholder="请输入活动类型">
					</div>
					<div class="field" id="uname" >
						<label>发布者</label> <input type="text" name="uname"
							placeholder="请输入活动发布者">
					</div>
					<div class="field" id="input_status" >
						<label>活动状态</label> <input type="text" name="act_status"
							placeholder="请输入活动状态">
					</div>
					<input class="ui green basic button" type="button" value="添加" id="addButton"/>
				</form>
			</div>
		</div>

		<!-- 弹出层   修改活动信息 -->
		<div class="ui modal" id="editModal">
			<i class="close icon"></i>
			<div class="header">活动修改</div>
			<div class="image content">
				<!-- 新增一个表单 用于修改活动 -->
				<form class="ui form" id="Form_editActivity">
					<div class="field" >
						<label>活动编号</label> <input type="text" name="act_id" 
							placeholder="请输入活动编号">
					</div>
					<div class="field" >
						<label>活动名称</label> <input type="text" name="act_name"
							placeholder="请输入活动名称">
					</div>
					<div class="field" >
						<label>活动类型</label> <input type="text" name="act_type"
							placeholder="请输入活动类型">
					</div>
					<div class="field" id="input_type" >
						<label>活动发布者</label> <input type="text" name="uname"
							placeholder="请输入活动发布者">
					</div>
					<div class="field" >
						<label>活动状态</label> <input type="text" name="act_status"
							placeholder="请输入活动状态">
					</div>
					<input class="ui orange basic button" type="button" value="确认修改" id="editButton"/>
				</form>
			</div>
		</div>
		
		<!-- 弹出层  删除活动 -->
		<div class="ui modal" id="delModal">
			<i class="close icon"></i>
			<div class="header">删除活动</div>
			<div class="image content">
				<!-- 新增一个表单 用于删除活动 -->
				<form class="ui form" id="Form_delActivity">
					<div class="field">
						<label>活动编号</label>
						<span class="ui icon input">
							<input type="text" name="act_id" placeholder="请输入活动编号"> <i
								class="circular search link icon" id="search2"></i>
						</span>
					</div>
					<div class="field disabled">
						<label>活动名称</label> <input type="text" name="act_name"
							placeholder="请确认活动名称">
					</div>
					<div class="field disabled">
						<label>活动类型</label> <input type="text" name="act_type"
							placeholder="请确认活动类型">
					</div>
					<div class="field disabled">
						<label>活动发布者</label> <input type="text" name="uname"
							placeholder="请确认活动发布者">
					</div>
					<div class="field disabled">
						<label>活动状态</label> <input type="text" name="act_status"
							placeholder="请确认活动状态">
					</div>
					<input class="ui red button" type="button" value="确认删除" id="delButton" />
				</form>
			</div>
		</div>

		<!-- 弹出层   修改用户 -->
		<div class="ui modal" id="editUserModal">
			<i class="close icon"></i>
			<div class="header">修改用户信息</div>
			<div class="image content">
				<!-- 新增一个表单 用于修改用户 -->
				<form class="ui form" id="Form_editUser">
					<div class="field">
						<label>用户编号</label> <input type="text" name="uno"
							placeholder="请确认用户编号">
					</div>
					<div class="field">
						<label>用户名</label> <input type="text" name="username"
							placeholder="请确认用户名">
					</div>
					<div class="field">
						<label>用户密码</label> <input type="text" name="password"
							placeholder="请确认密码">
					</div>
					<div class="field">
						<label>手机号码</label> <input type="text" name="phone"
							placeholder="请确认手机号码">
					</div>
					<div class="field">
						<label>性别</label> <input type="text" name="sex"
							placeholder="请确认性别">
					</div>
					<div class="field">
						<label>邮箱</label> <input type="text" name="email"
							placeholder="请确认邮箱">
					</div>
					<input class="ui orange basic button" type="button" value="确认修改"
						id="editUserButton" />
				</form>
			</div>
		</div>
	
		<!-- 弹出层   删除用户 -->
		<div class="ui modal" id="delUserModal">
			<i class="close icon"></i>
			<div class="header">删除用户</div>
			<div class="image content">
				<!-- 新增一个表单 用于删除用户 -->
				<form class="ui form" id="Form_delUser">
					<div class="field">
						<label>手机号码</label> <span class="ui icon input"> <input
							type="text" name="phone" placeholder="请输入手机号码"> <i
							class="circular search link icon" id="searchUser2"></i>
						</span>
					</div>
					<div class="field">
						<label>用户编号</label> <input type="text" name="uno"
							placeholder="请确认用户编号">
					</div>
					<div class="field">
						<label>用户名</label> <input type="text" name="username"
							placeholder="请确认用户名">
					</div>
					<div class="field">
						<label>邮箱</label> <input type="text" name="email"
							placeholder="请确认邮箱">
					</div>
					<input class="ui red basic button" type="button" value="确认删除"
						id="delUserButton" />
				</form>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('.ui.dropdown').dropdown({
			allowAdditions : true
		});
		$(".tabular.menu .item").tab();
		$('.ui.accordion').accordion({
			selector : {
				trigger : '.title'
			}
		});
	})
</script>

</html>