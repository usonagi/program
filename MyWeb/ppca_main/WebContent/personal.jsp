<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PPCA-用户个人中心</title>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/semantic.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/center.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/semantic.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
</head>

<body>
	<!--网页头部-->
	<div class="ui clearing segment">
		<h3 class="ui left floated header">PPCA-用户中心</h3>
		<div class="ui right floated header">
			<div class="ui horizontal list">
				<div class="item">
					<i class="user icon"></i>
					<a href="javascript:;">${ user.username }</a>
					<a href="javascript:;" onclick="signOut()"><i class="power icon"></i></a>
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
					<div class="title active">
						<i class="dropdown icon"></i> <i class="user secret icon"></i>个人信息
					</div>
					<div class="content active item vertical tabular menu">
						<div class="transition visible header item" data-tab="one" onclick="showInfo()">基本信息</div>
						<div class="transition visible header item" data-tab="one2" onclick="showDetails()">详细信息</div>
					</div>
					
					<div class="title">
						<i class="dropdown icon"></i> <i class="tasks icon"></i>活动管理
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition visible header item" data-tab="two" onclick="showAct()">活动列表</div>
					</div>
					
					<div class="title">
						<i class="dropdown icon"></i> <i class="user circle icon"></i>实名认证
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition hidden header item" data-tab="three" onclick="showReal()">实名认证</div>
					</div>
					
					<div class="title">
						<i class="dropdown icon"></i> <i class="protect icon"></i>安全中心
					</div>
					<div class="content item vertical tabular menu">
						<div class="transition hidden header item" data-tab="four">修改密码</div>
						<div class="transition hidden header item" data-tab="five">换绑手机</div>
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

			<!-- 个人信息 -->
			<div class="ui active tab segment" data-tab="one">
				<div class="ui grid">
					<div class="wide  column">
						<div class="ui tab segment active" data-tab="first">
							<div class="ui breadcrumb">
								<i class="home icon"></i> <a class="section">基本信息</a>
							</div>

							<div class="items">
								<table class="ui sortable celled table">
									<thead>
										<tr>
											<th colspan="9">用户基本信息列表</th>
										</tr>
									</thead>
									<thead>
										<tr align="center">
											<th>用户名</th>
											<th>手机号码</th>
											<th>性别</th>
											<th>邮箱</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody id="userbody" align="center">

									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 用户详细信息 -->
			<div class="ui tab segment" data-tab="one2">
				<div class="ui grid">
					<div class="wide  column">
						<div class="ui tab segment active" data-tab="second">
							<div class="ui breadcrumb">
								<i class="home icon"></i> <a class="section">详细信息</a>
							</div>

							<div class="items">
								<table class="ui sortable celled table">
									<thead>
										<tr>
											<th colspan="9">用户详细信息列表</th>
										</tr>
									</thead>
									<thead>
										<tr align="center">
											<th>用户名</th>
											<th>手机号码</th>
											<th>性别</th>
											<th>邮箱</th>
											<th>真实姓名</th>
											<th>学号</th>
											<th>专业</th>
											<th>年龄</th>
											<th>操作</th>
										</tr>
									</thead>

									<tbody id="userbody2" align="center">

									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 活动管理 -->
			<div class="ui tab segment" data-tab="two">
				<div class="ui grid">
					<div class="wide  column">
						<div class="ui tab segment active">
							<div class="ui breadcrumb">
								<i class="home icon"></i> <a class="section">活动管理</a> <i
									class="right angle icon divider"></i> <a class="section">活动列表</a>
							</div>

							<!-- 功能按钮 添加活动 -->
							<div class="btns">
								<div class="settings">
									<div class="ui green fade button" tabindex="0">
										<div onclick="addActivity()">发布活动</div>
									</div>
								</div>

								<div class="search">
									<div class="ui icon input" id="search_div">
										<input type="text" id="search" placeholder="搜索与你相关的活动" /> <i
											class="inverted circular search link icon"
											onclick="findByActName()"></i>
									</div>
								</div>
							</div>
							<div style="clear: both;"></div>

							<!-- 显示所有活动信息 -->
							<div class="items">
								<table class="ui sortable celled table">
									<thead>
										<tr>

											<th colspan="9">活动列表信息</th>
										</tr>
									</thead>
									<thead>
										<tr align="center">
											<th>活动名称</th>
											<th>发布者</th>
											<th>活动类型</th>
											<th>活动状态</th>
											<th>参与人数</th>
											<th>活动简介</th>
											<th>截止时间</th>
											<th>操作</th>
										</tr>
									</thead>

									<!-- 显示所有活动信息 -->
									<tbody id="actbody" align="center">

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

			<!-- 实名认证 -->
			<div class="ui tab segment" data-tab="three">
				<div class="ui grid">
					<div class="wide  column">
						<div class="ui tab segment active">
							<div class="ui breadcrumb">
								<i class="home icon"></i> <a class="section">实名认证(伪)</a>
							</div>
							<div class="btns">
								<div class="settings">
									<div class="ui green fade button" tabindex="0">
										<div onclick="certify()">进行认证</div>
									</div>
								</div>
							</div>

							<div class="items">
								<table class="ui sortable celled table">
									<thead>
										<tr>
											<th colspan="9">用户认证信息列表</th>
										</tr>
									</thead>
									<thead>
										<tr align="center">
											<th>手机号码</th>
											<th>真实姓名</th>
											<th>学号</th>
											<th>专业</th>
											<th>年龄</th>
											<th>状态</th>
										</tr>
									</thead>

									<tbody id="certifybody" align="center">

									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 安全中心 -->
			<div class="ui tab segment" data-tab="four">
				<div class="ui grid">
					<div class="row"></div>

					<div class="one column row" align="center">
						<div class="column">
							<font size="5" face="楷体" color="green">修 改 密 码</font>
						</div>
					</div>

					<div class="four cloumn ">
						<div class="ui ordered steps">
							<div class="column">
								<div class="active step">
									<div class="content">
										<div class="title">步骤 1</div>
										<div class="description">在登录页面，点击"忘记密码"</div>
									</div>
								</div>
							</div>
							<div class="column">
								<div class="step">
									<div class="content">
										<div class="title">步骤 2</div>
										<div class="description">输入绑定的手机号，点击"下一步"</div>
									</div>
								</div>
							</div>
							<div class="column">
								<div class="step">
									<div class="content">
										<div class="title">步骤 3</div>
										<div class="description">输入新密码，点击"确认更改"</div>
									</div>
								</div>
							</div>
							<div class="column">
								<div class="completed step">
									<div class="content">
										<div class="title">步骤 4</div>
										<div class="description">您已成功修改密码，请前往登录页面进行登录...</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row"></div>
					<div class="one column row">
						<!-- <div class="column"></div> -->
						<div class="column" align="center">
							<span> 版权所有@2022湖南工学院 | 地址：湖南省衡阳市珠晖区衡花路18号 | 邮编:421002 |
								计算机科学与技术-1801<i style="color: red;"> 小蚂蚁 </i>制作维护
							</span>
						</div>

					</div>
					<div class="row"></div>
					<div class="row"></div>
				</div>
			</div>

			<div class="ui tab segment" data-tab="five">
				<div class="ui grid">
					<div class="one column row" align="center">
						<div class="column">
							<font size="5" face="楷体" color="blue">相关功能正在开发中...</font>
						</div>
					</div>
				</div>
			</div>

			<div class="ui tab segment" data-tab="six">
				<div class="ui centered grid">
					<div class="one column row">
						<font color="red" size="5">注销账号将会删除您在本系统的所有记录，请注意！</font>
					</div>

					<div class="three column row">
						<div class="ui vertical ordered steps">

							<div class="step">
								<div class="content">
									<div class="title">个人信息清空</div>
									<div class="description">您在本系统的所有个人信息都将被删除...</div>
								</div>
							</div>

							<div class="step">
								<div class="content">
									<div class="title">活动信息清空</div>
									<div class="description">您在本系统的所有活动参与记录都将被删除...</div>
								</div>
							</div>

							<div class="step">
								<div class="content">
									<div class="title">系统权限清空</div>
									<div class="description">您在本系统的操作将会受到一定的限制...</div>
								</div>
							</div>

						</div>
					</div>

					<div class="row">
						<div class="ui checkbox">
							<input type="checkbox" name="confirm" /> <label>我已阅读注意事项，并同意服务条例。</label>
						</div>
					</div>

					<div class="row">
						<div>
							<button class="ui inverted red button"
								onclick="javascript:void(alert('相关功能正在开发中...'))">注销账号</button>
						</div>
					</div>
					<div class="row"></div>
					<div class="row"></div>
				</div>
			</div>

			<!-- 帮助中心 -->
			<div class="ui tab segment" data-tab="seven">
				<div class="ui two column grid" style="margin-top: 30px;">
					<div class="column" align="center">
						<div class="ui segment">
							<img class="ui centered medium img" src="./img/湖工二维码.jpg">
						</div>
						<span><font size="4">湖南工学院微信公众号</font></span>
					</div>
					
					<div class="column" align="center">
						<div class="ui segment">
							<img class="ui centered medium img" src="./img/湖工logo.jpg">
							
						</div>
						<span><font size="4">计信学院QQ:1431745061</font></span>
					</div>
					
				</div>
			</div>
		</div>

		<!-- 弹出层   用户修改信息 -->
		<div class="ui modal" id="editUserModal">
			<i class="close icon"></i>
			<div class="header">修改用户信息</div>
			<div class="image content">
				<!-- 新增一个表单 用于修改用户 -->
				<form class="ui form" id="Form_editUser">
					<div class="inline field">
						<label>键入昵称</label> <input type="text" name="username"
							placeholder="请输入用户名">
					</div>
					<div class="inline field">
						<label>键入性别</label> <input type="text" name="sex"
							placeholder="请输入性别">
					</div>
					<div class="inline field">
						<label>键入邮箱</label> <input type="text" name="email"
							placeholder="请输入邮箱">
					</div>
					<div class="field" style="display: none" >
						<label>确认手机号</label> <input type="text" name="phone"
							placeholder="请输入手机号">
					</div>
					<input class="ui orange basic button" type="button" value="确认修改"
						id="editUserButton" />
				</form>
			</div>
		</div>
		
		<!-- 弹出层   用户实名认证 -->
		<div class="ui modal" id="realModal">
			<i class="close icon"></i>
			<div class="header">用户认证信息</div>
			<div class="image content">
				<!-- 新增一个表单 用于用户实名认证 -->
				<form class="ui form" id="Form_certify">
					<div class="field">
						<label>手机号码</label> <input type="text" name="phone"
							placeholder="请输入手机号">
					</div>
					<div class="field">
						<label>真实姓名</label> <input type="text" name="realname"
							placeholder="请输入真实姓名">
					</div>
					<div class="field">
						<label>学号</label> <input type="text" name="stuid"
							placeholder="请输入学号">
					</div>
					<div class="field">
						<label>专业</label> <input type="text" name="major"
							placeholder="请输入专业">
					</div>
					<div class="field">
						<label>真实年龄</label> <input type="text" name="age"
							placeholder="请输入真实年龄">
					</div>
					<input class="ui orange basic button" type="button" value="立即认证"
						id="certifyButton" />
				</form>
			</div>
		</div>
		
		<!-- 弹出层   添加活动 -->
		<div class="ui modal" id="addModal">
			<i class="close icon"></i>
			<div class="header">活动添加</div>
			<div class="image content">
				<!-- 新增一个表单 用于添加活动 -->
				<form class="ui form" id="Form_addActivity">
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
					<div class="field" >
						<label>活动状态</label> <input type="text" name="act_status"
							placeholder="请输入活动状态">
					</div>
					<div class="field" >
						<label>活动简介</label> <input type="text" name="introd"
							placeholder="请简单介绍一下活动">
					</div>
					<div class="field" style="display: none" >
						<label>活动发起者</label> <input type="text" name="uname"
							value="${ user.username }">
					</div>
					<div class="field" >
						<label>截止时间 年-月-日</label> <input type="text" name="deadline"
							value="请输入活动截止时间">
					</div>
					<!-- <div class="field" >
						<label>活动招募人数</label> <input type="text" name="nums"
							placeholder="请输入活动招募人数">
					</div> -->
					<input class="ui green basic button" type="button" value="发布活动" id="addButton"/>
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
						<label>活动名称</label> <input type="text" name="act_name"
							placeholder="请输入活动名称">
					</div>
					<div class="field" style="display: none" >
						<label>活动发布者</label> <input type="text" name="uname"
							placeholder="请输入活动发布者">
					</div>
					<div class="field" >
						<label>活动类型</label> <input type="text" name="act_type"
							placeholder="请输入活动类型">
					</div>
					<div class="field" >
						<label>活动状态</label> <input type="text" name="act_status"
							placeholder="请输入活动状态">
					</div>
					<!-- <div class="field" >
						<label>参与人数</label> <input type="text" name="number"
							placeholder="请输入活动参与人数">
					</div> -->
					<div class="field" >
						<label>活动简介</label> <input type="text" name="introd"
							placeholder="请输入活动简介">
					</div>
					<div class="field" >
						<label>截止时间 年-月-日</label> <input type="text" name="deadline"
							placeholder="请输入活动截止时间">
					</div>
					<div class="field disabled" >
						<label>活动编号</label> <input type="text" name="act_id"
							placeholder="请输入活动编号">
					</div>
					<input class="ui orange basic button" type="button" value="确认修改" id="editButton"/>
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