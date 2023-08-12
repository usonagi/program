<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PPCA平台主页面</title>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/semantic.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/semantic.css" />
<style type="text/css">


#copyRight{
text-align: center;
margin: auto;
top:0;
bottom: 0;
left:0;
right:0;
}
</style>
</head>
<body>
	<!-- 网页头部 -->
	<div class="ui two column grid" style="background-color: green;">
		<div class="column">
			<img alt="" src="./img/hnit_logo.png" style="background-color:green;">
		</div>
		<div class="column" style="margin-top: 40px;">
				<span><font size="8" face="楷体">勤学 务实 圆融 卓越</font></span>
		</div>

	</div>

	<!-- 菜单项  指向、反转、适应、充满-->
	<div class="ui pointing inverted stackable fluid nine item menu">

		<div class="header item" data-tab="zero">首页</div>
		<a href="" class="active item" data-tab="first">趣味活动</a>
		<a href="" class="item" data-tab="second">社团活动</a>
		<a href="" class="item" data-tab="third">体育竞技</a>
		<a href="" class="item" data-tab="four">公益志愿</a>
		<span class="item">更多精彩</span> 
		
		<div class=" right item" data-tab="search">
			<div class="ui icon input" id="searchDiv">
				<input type="text" placeholder="搜索活动..." id="search"> <i
					class="inverted circular search link icon"
					onclick="searchAct()"></i>
			</div>
		</div>
		<div class="right item">
			<!-- <button class="ui primary button" >登录/注册</button> -->
			<a href="/ppca_main/login.jsp">登 录/</a>
			<a href="/ppca_main/register.jsp">注 册</a>
		</div>
	</div>
	
	<!-- 网页主体  菜单内容 -->
	<div class="ui bottom attached tab segment" data-tab="zero">
		<div class="ui divided items">
				<div class="item">
					<div class="ui large image">
						<img src="./img/南门全景.jpg">
					</div>
					<div class="middle aligned content">
						<a class="header">湖工简介</a>
						<div class="meta">
							<span>湖南工学院</span>
							<span>皇家工学院</span>
						</div>
						<div class="description">
							<p>湖南工学院坐落于湖南省第二大城市、湖湘文化发源地之一、湘南地区中心城市——衡阳市，是2007年经教育部批准由湖南建材高等专科学校和湖南
							大学衡阳分校合并升格的省属公办普通本科院校，2010年3月湖南工业科技职工大学整体并入，是全国实施“卓越工程师教育培养计划”最年轻的本科院校,
							是湖南省硕士学位授予立项建设单位。<br>学校立足衡阳，面向湖南，辐射全国，重点面向工业企业，为区域经济建设和社会发展服务，是一所以工为主，
							工、经、管、文、理、艺等多学科协调发展，具有较强的科技服务能力，培养基础实、技术精、能力强、素质高，具有创新精神和社会责任感的应用型专门
							人才的本科院校。<br>奋进中的湖南工学院，将始终秉承“勤学、务实、圆融、卓越”的校训精神，坚持立德树人根本任务，贯彻新发展理念，坚持高质量发展,
							走内涵发展、创新发展、特色发展之路，努力建设特色鲜明的区域性高水平应用型大学。</p>
						</div>
						<div class="extra">
							<i class="like icon"></i>喜欢 <i class="star icon" style="margin-left: 20px;"></i>收藏
						</div>
					</div>
				</div>
				<div class="item">
					<div class="ui large image">
						<img src="./img/图书馆前坪.jpg">
					</div>
					<div class="middle aligned content">
						<a class="header">湖工历史</a>
						<div class="meta">
							<span>湖南工学院</span>
							<span>历史沿革</span>
						</div>
						<div class="description">
							<p>湖南工学院由原湖南建材高等专科学校和原湖南大学衡阳分校合并成立的一所公办全日制普通本科学校。
							原湖南建材高等专科学校的前身是成立于 1978 年 10 月的衡阳基础大学，由衡阳市主管，属县团级单位；<br> 
							1985 年 3 月，更名为湖南建材工业专科学校，由湖南省建材工业局与湖南省教育厅共同管理，行政关系隶属于湖南省建材工业局；<br> 
							1992 年 5 月，升格为副厅级事业单位； 1993 年 6 月，更名为湖南建材高等专科学校。
							原湖南大学衡阳分校创办于 1975 年 10 月，受湖南大学和衡阳地区双重领导，属县团级单位；<br>
							1979 年与湖南大学总部完全脱钩，为独立的事业法人单位，改由湖南省高教局和衡阳地区共同管理；<br> 
							1994 年 8 月，由湖南省二轻工业集团总公司接管； 1997 年 12 月，升格为副厅级事业单位。<br>
							2003 年 12 月，湖南建材高等专科学校与湖南大学衡阳分校合并，筹建湖南工学院。<br>
							2007 年 3 月，教育部下文批准成立湖南工学院。<br>
							2009 年 12 月，湖南工业科技职工大学整体划转湖南工学院。</p>
						</div>
						<div class="extra">
							<i class="like icon"></i>喜欢 <i class="star icon" style="margin-left: 20px;"></i>收藏
						</div>
					</div>
				</div>
		</div>
	</div>
	<div class="ui bottom attached tab segment active" data-tab="first">
		<div class="ui divided items">
				<div class="item">
					<div class="image">
						<img src="./img/英语竞赛.jpg">
					</div>
					<div class="middle aligned content">
						<a class="header">英语知识竞赛</a>
						<div class="meta">
							<span>知识竞赛</span>
							<span>英语</span> <span>英语协会</span>
						</div>
						<div class="description">
							<p>11月29日，湖工校英协承办的“英语知识竟赛”在经过半个多月的努力，最终在经管楼601落下帷幕。
							莅临本次决赛的评委有第十一届英语知识竞赛冠军熊丹慧，外教老师Cynthia、Jon，还有兄弟协会声乐队。<br>
							本次英语知识竞赛共分为初赛、复赛、决赛三个阶段，每一个阶段分别考察了同学们不同的能力，让大家感受到英语别有一番风味。
							初赛于11月16日上午9点在机械楼4408如期举行。以笔试为主，对同学们英语基础知识的掌握进行考察。经过90分钟的比拼，
							共有25名选手脱颖而出进入复试。</p>
						</div>
						<div class="extra">
							<i class="like icon"></i>喜欢 <i class="star icon" style="margin-left: 20px;"></i>收藏
						</div>
					</div>
				</div>
				<div class="item">
					<div class="image">
						<img src="./img/街舞.jpg">
					</div>
					<div class="middle aligned content">
						<a class="header">"舞出我人生"</a>
						<div class="meta">
							<span>街舞</span> <span>比赛</span>
						</div>
						<div class="description">
							<p>1986街舞社在B区食堂3楼举行了第四届“舞出我人生”街舞比赛，为更好的提高同学们的艺术素养，接触更多积极向上的街舞元索。
							此次比赛设置的奖项分制有一二三等奖。<br>比赛的前奏是在食堂三楼各分组选手表演的齐舞，帅气的动作以及高超的技巧可谓是吸眼无数。
							随后转换场地进行1v1Battle。比赛之中，观众见识到各个舞种的独特魅力，性感而不失硬气的爵士，帅气高难度的breaking，
							动感、张弛有度的popping等等。这次比赛让大家看到了每位舞者对舞蹈的热衷以及坚持，每个队伍的团结，和他们所拥有的团魂，
							台下的观众全都投人其中，对舞蹈表演赞不绝口。</p>
						</div>
						<div class="extra">
							<i class="like icon"></i>喜欢 <i class="star icon" style="margin-left: 20px;"></i>收藏
						</div>
					</div>
				</div>
		</div>
	</div>
	<div class="ui bottom attached tab segment" data-tab="second">
		<div class="ui divided items">
			<div class="item">
				<div class="image">
					<img src="./img/辩论.jpg">
				</div>
				<div class="middle aligned content">
					<a class="header">计信学院辩论队</a>
					<div class="meta">
						<span>辩论</span> <span>社团招新</span>
					</div>
					<div class="description">
						<p>
							计科学院首届正式招新的辩论队即将开始面向21级萌新进行选拔。<br>
							一言之辩，重于九鼎之宝，三寸之舌，强于百万之师，古有王继忠与曹利用前往辽国达成澶渊之盟，实现双赢，
							为北宋换来百年和平，这是辩论的魅力，这是言语的威力，欢迎各位萌新加入计科学院院辩论队...
						</p>
					</div>
					<div class="extra">
						<i class="like icon"></i>喜欢 <i class="star icon" style="margin-left: 20px;"></i>收藏
					</div>
				</div>
			</div>
			<div class="item">
				<div class="image">
					<img src="./img/微电影大赛.jpg">
				</div>
				<div class="middle aligned content">
					<a class="header">计算机科技文化节子活动</a>
					<div class="meta">
						<span>微电影</span> <span>比赛</span>
						<span>社团活动</span>
					</div>
					<div class="description">
						<p>
							在机械楼4404教室成功举办了第十五届计算机科技文化节子活动——微生活微电影大赛的决赛。
							活动初始，放映所有参赛小组的微生活微电影作品，结束后由各参赛小组代表对其作品进行简短的介绍，
							并由评委进行了点评。在嘉宾和参赛选手与工作人员的合影留念中，微生活微电影决赛也就此拉下帷幕！
						</p>
					</div>
					<div class="extra">
						<i class="like icon"></i>喜欢 <i class="star icon" style="margin-left: 20px;"></i>收藏
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="ui bottom attached tab segment" data-tab="third">
		<div class="ui divided items">
			<div class="item">
				<div class="image">
					<img src="./img/男子篮球.jpg">
				</div>
				<div class="middle aligned content">
					<a class="header">"湖工杯"男子篮球赛</a>
					<div class="meta">
						<span>竞技</span> <span>篮球比赛</span> <span>学校组织</span>
					</div>
					<div class="description">
						<p>湖工杯”男篮比赛计算机学院VS化环学院在体育馆展开了一场激烈的角逐，这场比赛可谓是精彩绝伦不愧是卫冕之战。<br>
						数不清有多少个夜晚的拼搏 计算机男篮一路高歌猛进晋级到这次湖工杯的总决赛实属不易。面对仅输过一场的化环学院这一劲敌，
						拥有着100%胜率的计算机学院也不能轻敌大意最后一场比赛，我们也是不出意外的完美拿下，这就是100%胜率冠军的含金量！</p>
					</div>
					<div class="extra">
						<i class="like icon"></i>喜欢 <i class="star icon"
							style="margin-left: 20px;"></i>收藏
					</div>
				</div>
			</div>
			<div class="item">
				<div class="image">
					<img src="./img/女子篮球.jpg">
				</div>
				<div class="middle aligned content">
					<a class="header">"湖工杯"女子篮球赛</a>
					<div class="meta">
						<span>竞技</span> <span>篮球比赛</span> <span>学校组织</span>
					</div>
					<div class="description">
						<p>
						"湖工杯"女篮比赛计算机学院VS安工学院于体育馆迎来了终极对决，面对同样强的对手，她们没有畏惧，她们酣畅淋漓，她们勇往直前。<br>
							计算机女篮的球员们气贯长虹，在被压制的状况下，突破极限，点燃了似火的激情，逢投必进，精彩我秀，
							可艺篮球，魅力无限三分外，手气足落，完美弧线，得分容易球球俱进，分分必得，不畏强暴，所向披靡。
						</p>
					</div>
					<div class="extra">
						<i class="like icon"></i>喜欢 <i class="star icon"
							style="margin-left: 20px;"></i>收藏
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="ui bottom attached tab segment" data-tab="four">
		<div class="ui divided items">
			<div class="item">
				<div class="image">
					<img src="./img/青年志愿.jpg">
				</div>
				<div class="middle aligned content">
					<a class="header">五四青年节志愿活动</a>
					<div class="meta">
						<span>志愿活动</span> <span>计信青协</span> <span>校园</span>
					</div>
					<div class="description">
						<p>为弘扬雷锋精神，构建和谐友好、洁净美丽的校园环境，营造良好的学习氛围，3月19日，
							计算机科学与工程学院团委勤工部开展校园志愿清扫活动。勤工部同事事先撰写好策划书，招募好
							志愿者并对其进行分组，同时确定各组的相关负责人并合理规划好负责清扫区域。在各位志愿者的积
							极参与下，从南操到北操，他们认真仔细地清扫了各种卫生死角，团结协作，使得此次志愿活动圆满完成。
							志愿者们身上无不体现着雷锋精神，他们用行动展现了新时代雷锋精神的新风貌</p>
					</div>
					<div class="extra">
						<i class="like icon"></i>喜欢 <i class="star icon"
							style="margin-left: 20px;"></i>收藏
					</div>
				</div>
			</div>
			<div class="item">
				<div class="image">
					<img src="./img/支教.jpg">
				</div>
				<div class="middle aligned content">
					<a class="header">乡村支教</a>
					<div class="meta">
						<span>支教</span> <span>志愿活动</span> <span>计信青协</span>
					</div>
					<div class="description">
						<p>
							有句俗话叫“赠人玫瑰，手有余香”，去做支教志愿者是一件快乐又有意义的事情，你在帮助他人的同时，你也在帮助了你自己。<br>
							在哪里我能看见雅嫩的学生在教室认真的学习，在操场上自由的奔跑与打闹；我能听到可爱又有礼貌的学生亲切的向我问好；
							我能享受到上课时与学生们一起沉浸在快乐的学习氛围中；我能被感动到上课时一个个学生积极的踊跃回答我的问题，一下课就围着我转的一幕。
						</p>
					</div>
					<div class="extra">
						<i class="like icon"></i>喜欢 <i class="star icon"
							style="margin-left: 20px;"></i>收藏
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="ui tab hidden segment" data-tab="search" id="searchTab">
		<div class="ui grid">
			<table class="ui sortable celled table">
				<thead align="center">
					<tr>
						<th colspan="9"><font size="4">查询结果</font></th>
					</tr>
				</thead>
				<tbody>
					<tr align="center">
						<th>活动名称</th>
						<th>发布者</th>
						<th>活动类型</th>
						<th>活动状态</th>
						<th>参与人数</th>
						<th>活动简介</th>
						<th>截止时间</th>
					</tr>
				</tbody>

				<tbody id="actbody" align="center">

				</tbody>

				<tfoot>
					<tr>
						<th colspan="2" id="fyt_tip">
							
						</th>

						<th colspan="9">
							<div class="ui right floated pagination menu" id="fyt">
								
							</div>
						</th>
					</tr>
				</tfoot>

			</table>
		</div>
	</div>


	<!-- 网页底部 -->
	<div class="ui three column grid">
		<div class="column"></div>
		<div class="column">
			<div class="ui horizontal list">
				<div class="item">
					<a href="">联系我们</a>
				</div>
				<div class="item">
					<a href="">问题反馈</a>
				</div>
				<div class="item">
					<a href="">帮助信息</a>
				</div>
			</div>
		</div>
		<div class="column"></div>
	</div>
	<br>
	<div id="copyRight">
		<p>版权所有@2022湖南工学院 | 地址：湖南省衡阳市珠晖区衡花路18号 | 邮编:421002 |
			计算机科学与技术-1801<i style="color: red;"> 小蚂蚁 </i>制作维护</p>
	</div>

</body>
<script type="text/javascript">

	//动态标签页
	$('.menu .item').tab();
	
	//点击搜索
	function searchAct(){
		
		var actName = $("#search").val();
		$.post("/ppca_main/activity?method=visitFindAct",{
			"act_name":actName },function(data){
				
			var json_act = eval("("+data+")");
			var actData = "";
			var length = json_act.content.length;
			
			//遍历活动数据
			if(json_act.status == true){
				for(var i = 0; i < length; i++){
					var activities = json_act.content[i];
					
					actData += "<tr><td>"+activities.act_name+"</td><td>"+activities.uname+"</td><td>"
						+activities.act_type+"</td><td>"+activities.act_status+"</td><td>"+activities.number+"</td><td>"
						+activities.introd+"</td><td>"+showDate(activities.deadline)+"</td></tr>";
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
		$("#searchDiv").html("<input type='text' placeholder='搜索活动(活动名)...' id='search'> "+
		" <i class='inverted circular search link icon' onclick='searchAct()''></i>");
	}
	
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
</script>
</html>