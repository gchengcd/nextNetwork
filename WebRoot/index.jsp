<%@page import="cn.nnw.dao.impl.UserDaoImpl"%>
<%@page import="cn.nnw.domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username=null;
String password=null;
Cookie myCookie[]=request.getCookies();//创建一个Cookie对象数组 
System.out.println(">>>>>>>>>>>>>>----");
for(int i=0;myCookie!=null&&i<myCookie.length;i++){
	 //设立一个循环，来访问Cookie对象数组的每一个元素 
	Cookie newCookie= myCookie[i]; 
	
	if(newCookie.getName().equals("username")){
		username = newCookie.getValue();
		System.out.println(">>>>>>>>>>>>>>"+username);
	}
	if(newCookie.getName().equals("password")){
		password = newCookie.getValue();
		System.out.println(">>>>"+password);
	}
}
if(username!=null&&password!=null){
	cn.nnw.dao.UserDao dao = new UserDaoImpl();
	User use = dao.find(username,password);
	if(use!=null){
		request.getSession().setAttribute("user", use);
		//让用户登陆成功后，跳转首页
		/* 
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return; 
		*/
	}
}
		
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>未来互联网首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />

<!-- Le styles -->
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/inettuts.css " />
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/inettuts.js.css " />
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/next-network.css " />
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/bootstrap-responsive.css " />
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon"
	href=" <%=basePath%>ico/favicon.ico " />
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href=" <%=basePath%>ico/apple-touch-icon-144-precomposed.png " />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href=" <%=basePath%>ico/apple-touch-icon-114-precomposed.png " />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href=" <%=basePath%>ico/apple-touch-icon-72-precomposed.png " />
<link rel="apple-touch-icon-precomposed"
	href=" <%=basePath%>ico/apple-touch-icon-57-precomposed.png " />

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
	background: white;
}

#logo {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

/* add by me */
#columns #column1 {
	background-image: none;
}

#columns #column3 {
	background-image: none;
}

#columns .widget .widget-head {
	height: 35px;
}
.uneditable-input {
  height: 30px;
}
</style>
</head>

<body>

	<div class="container">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<a class="brand" href=" <%=basePath%>index.jsp" title="记忆犹新"
					style="padding-left: 28px;">Next Network</a>
				<ul id="navigation" class="nav">

					<li class="active"><a href=" <%=basePath%>category/memory">首页</a></li>

					<li><a href=" <%=basePath%>/category/lover">测试1</a></li>

					<li><a href=" <%=basePath%>/category/travel">测试2</a></li>

					<li><a href=" <%=basePath%>/category/flavor">测试3</a></li>

					<li><a href=" <%=basePath%>/category/timeline">测试4</a></li>

				</ul>

				<ul class="nav nav-pills pull-right" style="padding-right: 30px;">
				<c:if test="${user!=null}">
					 <li>
						<div class="btn-group">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-user icon-black"></i> &nbsp; ${user.useremail} <span
								class="caret"></span> </a>
							<ul class="dropdown-menu">
								<li><a href="#">个人资料</a></li>
								<li><a href="<%=basePath%>servlet/LogoutServlet">退出</a></li>
							</ul>
						</div></li> 
					 <li>
						<a class="pull-right accordion-toggle" href="#"
						data-toggle="collapse" data-target="#app-list"> <i
							class="icon-plus icon-white"></i> 添加应用 </a></li> 
					</c:if>
					<c:if test="${user==null}">
					<li>
					 <li><a href="#loginModal" class="dropdown-toggle"
						data-toggle="modal"> <i class="icon-user icon-white"></i>
							&nbsp; <span style="color:white">登录/注册</span> 
<!--                             <a class="pull-right" href="/auth/register">注册</a>
                        <a class="pull-right" href="/auth/signin">登录</a> -->
                        </a></li>
					<li><a href="#loginModal" class="dropdown-toggle"
						data-toggle="modal"> <i class="icon-plus icon-white"></i>
							添加应用 
                    </a></li> 
                    </c:if>
				</ul>
			</div>
		</div>
	</div>

	<!-- 下拉应用列表框 -->
	<div id="app-list" class="collapse"
		style="margin-top: -19px; padding-top: 13px; background-image: url(img/intro-threejs-header-pattern.png);">
		<table>
			<tr>
				<td style="background-color:#87cefa;">
					<div style="margin:10px;margin-top: -80px;">
						<ul class="nav nav-list" id="myTab"
							style="max-width: 60px; padding: 8px 0">
							<li class="active"><a data-toggle="tab" href="#yule"
								style="color:#000000">生活娱乐</a>
							</li>
							<li class=""><a data-toggle="tab" href="#xinxi"
								style="color:#000000">新闻信息</a>
							</li>
							<li class=""><a data-toggle="tab" href="#"
								style="color:#000000">待加入</a>
							</li>
						</ul>
					</div></td>
				<td>
					<div class="tab-content" id="myTabContent">
						<div id="yule" class="tab-pane fade active in">
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									日历 <a class="pull-right app-module" id="moduleid-1" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/calendar.jpg">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									天气 <a class="pull-right app-module" id="moduleid-2" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/weather.png">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									音乐 <a class="pull-right app-module" id="moduleid-3" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;" src=" <%=basePath%>img/music.png">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									地图 <a class="pull-right app-module" id="moduleid-7" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/shishiredian.png">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									视频 <a class="pull-right app-module" id="moduleid-6" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/shishiredian.png">
							</div>
							<!-- <div>个性推荐
                                    <a class="pull-right app-module" id="moduleid-5" href="#" title="添加"> <i class="icon-plus"></i>
                                </a>
                                </div>
                                <hr style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
                                <img class="img-rounded app-image" alt="100x100" style="width: 180px; height: 80px;" src=" <%=basePath%>img/gexingtuijian.png"></div> -->
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>title</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								well5
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>title</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								well5
							</div>
						</div>
						<div id="xinxi" class="tab-pane fade">
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									实时热点 <a class="pull-right app-module" id="moduleid-4" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/calendar.jpg">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									个性推荐 <a class="pull-right app-module" id="moduleid-5" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/calendar.jpg">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									最常访问 <a class="pull-right app-module" id="moduleid-8" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/calendar.jpg">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									推荐新闻 <a class="pull-right app-module" id="moduleid-9" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/calendar.jpg">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>
									维基百科 <a class="pull-right app-module" id="moduleid-10" href="#"
										title="添加"> <i class="icon-plus"></i> </a>
								</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								<img class="img-rounded app-image" alt="100x100"
									style="width: 180px; height: 80px;"
									src=" <%=basePath%>img/calendar.jpg">
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>title</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								well5
							</div>
							<div class="well well-large span2"
								style="width: 120px; margin-left:10px">
								<div>title</div>
								<hr
									style="margin: 1px 0; color : #E6E6FA; border-bottom : 1px solid lavender;">
								well5
							</div>
						</div>
					</div></td>
			</tr>
		</table>
	</div>

	<div class="row">
		<img id="logo" style="width:250px; height: 150px; margin: auto;"
			src=" <%=basePath%>img/new.jpg" alt="" />
	</div>

	<div class="row">
		<div class="span6 offset4">
			<form action="">
				<div class="input-append">
					<input class="span5" id="appendedInputButtons" type="text"
						placeholder="搜索一下" required>
					<!-- autofocus -->
					<button class="btn" type="button">Search</button>
				</div>
			</form>
		</div>
	</div>

	<div id="columns">

		<ul id="column1" class="column">
			<%-- <% if login_status == False%> --%>
			<!-- <input type="hidden" id="orderlist1" /> -->

			<!-- <li class="widget color-green" id="intro" >
            <div class="widget-head">
                <h3>我的导航</h3>
            </div>
            <div class="widget-content" >
                <ul>
                    <li>
                        <a href="http://www.baidu.com" style="color:black">www.baidu.com</a>
                    </li>
                    <li>
                        <a href="http://www.baidu.com" style="color:black">www.sina.com.cn</a>
                    </li>
                </ul>
            </div>
        </li> -->
        <c:if test="${user==null}">
			<li class="widget color-orange" id="appid-8">
				<div class="widget-head">
					<h3>最常访问</h3>
				</div>
				<div class="widget-content">
					<ul>
						<li><a href="http://www.baidu.com" style="color:black">www.hoopchina.com</a>
						</li>
						<li><a href="http://www.baidu.com" style="color:black">forum.byr.edu.cn</a>
						</li>
					</ul>
				</div></li>
			<!-- <li class="widget color-white" id="appid-4">
            <div class="widget-head">
                <h3>实时热点</h3>
            </div>
            <div class="widget-content">
                <ul>
                    <li>
                         <a href="http://www.baidu.com" style="color:black">www.hoopchina.com</a>
                    </li>
                </ul>
               
                
            </div>
        </li> -->
			<li id="appid-5" class="widget color-white">
				<div class="widget-head">
					<h3>个性推荐</h3>
				</div>
				<div class="widget-content">
					<ul>
						<li><a href="http://www.baidu.com" style="color:black">www.hoopchina.com</a>
						</li>
					</ul>

				</div></li>
			</c:if>
				<%--  <% endif %> --%>
		</ul>

		<ul id="column2" class="column">
		<li class="widget color-yellow" id="appid-12">
	            <div class="widget-head">
	                <h3>音频</h3>
	            </div>
		            <div class="widget-content">
					<audio controls="controls">
					  <source src="video/song.mp3" type="audio/mpeg">
					      Your browser does not support the audio tag.
				    </audio>
		            </div>
		    </li> 
			<!--<li class="widget color-white ui-sortable" id="appid-6">
			<div class="widget-head" style="cursor: move;"><a href="#" class="collapse">COLLAPSE</a>
			<h3>热点视频</h3>
			<a href="#" class="remove">CLOSE</a><a href="#" class="edit">EDIT</a></div>
			<div class="edit-box" style="display:none;"><ul><li class="item">
			<label>Change the title?</label><input value="热点视频"></li></ul><li class="item">
			<label>Available colors:</label><ul class="colors"><li class="color-yellow"></li><li class="color-red">
			</li><li class="color-blue"></li><li class="color-white"></li>
			<li class="color-orange"></li><li class="color-green"></li></ul></li></div>
			<div class="widget-content"><a href="http://127.0.0.1:8080/nextNetwork/servlet/DownLoadServlet" shape="rect" style="float: right;color:black;cursor:pointer;">
			<i class="icon-download-alt"></i>http下载</a><h4>机器人舞</h4>
			<video width="414" height="310" controls="controls">
			<source src="video/chaoniu1.mp4" type="video/mp4">Your browser does not support the video tag.</video>
			</div></li>-->
			<%-- <% if login_status == False%> --%>
			<!--      <input type="hidden" id="orderlist2" /> -->
		<!-- 	<li class="widget color-yellow" id="appid-12">
            <div class="widget-head">
                <h3>推荐音乐</h3>
            </div>
	            <div class="widget-content">
				<audio controls="controls">
				  <source src="ccnx://bupt/video/song.ogg" type="audio/ogg">
				  <source src="ccnx://bupt/video/song.mp3" type="audio/mpeg">
				      Your browser does not support the audio tag.
			    </audio>
	            </div>
            </li> 
			
			 <li class="widget color-yellow" id="appid-6">
            <div class="widget-head">
                <h3>推荐视频</h3>
            </div>
            <div class="widget-content">
                       <i class="icon-download-alt"></i>http下载</a><h4>机器人舞</h4>
			<video width="414" height="310" controls="controls">
			<source src="ccnx:/bupt/video/chaoniu1.mp4" type="video/mp4">Your browser does not support the video tag.</video>
            </div>
        </li>  -->
			<!-- <li class="widget color-yellow">    
    <div class="widget-head">
        <h3>视频</h3>
    </div>
    <div class="widget-content">
        <p>视频:日本右翼抵达钓附近海域 10艘海保船护航</p>
        <embed src=" <%=basePath%>http://you.video.sina.com.cn/api/sinawebApi/outplay.php/P060S3A4B27K+l1lHz2stssM5aINt8vji2m3vFatJBEZDFjhZoPdK51SjyvJRpYWnm1NRpo3ffYn1gJOMfQJomp0MmYSnRSGN7cMObXR5KGZOwEd8XFHrnimAN1ioaFYgSpOExLVx7QC4gOTpDHUcWCy7gU1CoWDU0N+wlQ2rMdGrBCU3Ope5hLT27zKFcYpr3VcZSaKrKcuxA.swf" quality="high" allowfullscreen="true" flashvars="playMovie=true&auto=1" pluginspage="http://get.adobe.com/cn/flashplayer/" style="visibility: visible;" allowscriptaccess="never"
                        width="393" height="300" 
                        type="application/x-shockwave-flash"></embed>
    <!--  http://you.video.sina.com.cn/api/sinawebApi/outplay.php/P060S3A4B27K+l1lHz2stssM5aINt8vji2m3vFatJBEZDFjhZoPdK51SjyvJRpYWnm1NRpo3ffYn1gJOMfQJomp0MmYSnRSGN7cMObXR5KGZOwEd8XFHrnimAN1ioaFYgSpOExLVx7QC4gOTpDHUcWCy7gU1CoWDU0N+wlQ2rMdGrBCU3Ope5hLT27zKFcYpr3VcZSaKrKcuxA.swf
                        http://video.sina.com.cn/p/news/c/v/2013-04-23/102662337491.html 
                        <!-- http://v.youku.com/v_show/id_XNTQ2NzEyMDI0.html
                       <!--  http://v.youku.com/v_show/id_XNDQ1MjIyODg4.html -->
			<!-- </div> </li>
    -->
			<!-- <li class="widget color-yellow" id="appid-7">
            <div class="widget-head">
                <h3>地图</h3>
            </div>
            <div class="widget-content">
                 <iframe scrolling="no" style="width: 100%; height: 360px;" frameborder="0" class="gwt-Frame" id="remote_iframe_9" name="remote_iframe_9" src=" <%=basePath%>http://www-ig-opensocial.googleusercontent.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.gstatic.com%2Fig%2Fmodules%2Fmapsearch%2Flocalsearch_v2.xml&amp;container=ig&amp;view=home&amp;lang=zh-cn&amp;country=US&amp;sanitize=0&amp;v=35542839c232e0b0&amp;parent=http://www.google.com&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;is_signedin=1&amp;synd=ig&amp;mid=9#st=c%3Dig%26e%3DAPu7icpd7cn0k4ucChZMDXBWX14o/GwKRI66L3IJIm0PtA2FjfnyZvrXgLYoQr3ZK4DbxJuwabZ39JuIkVm0FTk/Y9CuNeHTZ1sn9oLofali7u%252BI9fVUn4uwahrmxYc8%252BeXtnV9kRZoD&amp;gadgetId=115506503466011460772&amp;gadgetOwner=118187998234078167265&amp;gadgetViewer=118187998234078167265&amp;rpctoken=-1020536692&amp;ifpctok=-1020536692&amp;up_traffic=&amp;up_trafficMode=false&amp;up_transitionQuery=&amp;up_mapType=roadmap&amp;up_locationCacheLat=37.788081&amp;up_idleZoom=11&amp;up_location=%E6%97%A7%E9%87%91%E5%B1%B1&amp;up_rawquery=&amp;up_kml=false&amp;up_largeMapMode=true&amp;up_locationCacheLng=-122.409668&amp;up_selectedtext=&amp;up_locationCacheString="> </iframe>
            </div>
        </li> -->
			<%-- <% endif %> --%>
		</ul>

		<ul id="column3" class="column">
			<%-- <% if login_status == False%> --%>
			<!--  <input type="hidden" id="orderlist3" /> -->
			<!-- <li class="widget color-white" id="appid-4">
            <div class="widget-head">
                <h3>实时热点</h3>
            </div>
            <div class="widget-content">
                <ul>
                    <li>
                         <a href="http://www.baidu.com" style="color:black">www.hoopchina.com</a>
                    </li>
                </ul>
               
                
            </div>
        </li> -->
			<!-- <li id="appid-5" class="widget color-white" >
            <div class="widget-head">
                <h3>个性推荐</h3>
            </div>
            <div class="widget-content">
               <ul>
                    <li>
                         <a href="http://www.baidu.com" style="color:black">www.hoopchina.com</a>
                    </li>
                </ul>
                
            </div>
        </li> -->

			<!--  <li id="appid-3" class="widget color-yellow" >
            <div class="widget-head">
                <h3>百度音乐</h3>
            </div>
            <div class="widget-content">
                <iframe width="420" scrolling="no" height="210" frameborder="0" src="http://play.baidu.com/player/hao123/?r=1366853429566#top/dayhot" data-url="http://play.baidu.com/player/hao123/" id="funnynew-iframe"></iframe>
            </div>
        </li> -->
        <c:if test="${user==null}">
			<li class="widget color-white" id="appid-9">
				<div class="widget-head">
					<h3>推荐新闻</h3>
				</div>
				<div class="widget-content">
					<iframe scrolling="no" frameborder="0" class="gwt-Frame"
						id="remote_iframe_7" name="remote_iframe_7"
						src="http://www-ig-opensocial.googleusercontent.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.gstatic.com%2Fig%2Fmodules%2Ftabnews%2Ftabnews_v2.xml&amp;container=ig&amp;view=home&amp;lang=zh-cn&amp;country=US&amp;sanitize=0&amp;v=dadcd56d7947b0c3&amp;parent=http://www.google.com&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;is_signedin=1&amp;synd=ig&amp;mid=7#st=c%3Dig%26e%3DAPu7icqgoiRyfiv3LNxBal1g1xLRrkfHbIIqHSakgNnPe7WQ%252Bx7bTvCpDcYtgd/ZY5Tzf0sn/QTGkqfDUzWkxmBQzRrruG3fboIDsDVD91JYfT3qzbAVgXT7DSumqfBDUN8QjUZYn2Oh&amp;gadgetId=111502982863977288083&amp;gadgetOwner=118187998234078167265&amp;gadgetViewer=118187998234078167265&amp;rpctoken=2145383376&amp;ifpctok=2145383376&amp;up_items=5&amp;up_ned=&amp;up_queryList=&amp;up_font_size=13pt&amp;up_selectedTab=0&amp;up_tabs=h,b,t,s,e&amp;up_last_url=http://ajax.googleapis.com/ajax/services/search/news%3Fv%3D1.0%26hide%3Drelated%26key%3Dinternal-ig-tabnews%26ned%3Dcn%26topic%3Dh%26rsz%3Dlarge&amp;up_onebox=&amp;up_show_image=0"
						style="width: 100%;height: 234px;"></iframe>

				</div></li>
			<li class="widget color-white" id="appid-10">
				<div class="widget-head">
					<h3>维基百科</h3>
				</div>
				<div class="widget-content">
					<iframe scrolling="no" frameborder="0" class="gwt-Frame"
						id="remote_iframe_13" name="remote_iframe_13"
						src="http://www-ig-opensocial.googleusercontent.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.gstatic.com%2Fig%2Fmodules%2Fwikipedia%2Fwikipedia_v2.xml&amp;container=ig&amp;view=home&amp;lang=zh-cn&amp;country=US&amp;sanitize=0&amp;v=d5881d8791fc194c&amp;parent=http://www.google.com&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;is_signedin=1&amp;synd=ig&amp;mid=13#st=c%3Dig%26e%3DAPu7icqc6NhL/5CM2CGjUnYl0k39rkBTx19eieqDnMfIEb9xx/3yd3BgV%252B8VKfn78K3EQ/tmivLdxB7gC5x9wXVB//0/q2qhWW6q97AxdvaSKGoVKA9XcO/IrSO8q/xAB0yJMyDTPkbn&amp;gadgetId=116039311165796053871&amp;gadgetOwner=118187998234078167265&amp;gadgetViewer=118187998234078167265&amp;rpctoken=-891628792&amp;ifpctok=-891628792&amp;up_isChecked=true"
						style="width: 100%;height: 266px;"></iframe>
				</div></li>
			</c:if>
				
				<%--  <% endif %> --%>
		</ul>


	</div>
	<!-- <li id="appid-1" class="widget color-white">
            <div class="widget-head">
                <h3>日历</h3>
            </div>

            <div class="widget-content">
                <div style="margin-left: 9px">
                    <a href="#calendarModal"  data-toggle="modal">
                            <img class="img-rounded app-image" alt="100x100" style=" width: 430px; height: 150px;" src=" <%=basePath%>img/calendarhref.png">
                    </a>
                </div>
                
            </div>
        </li>
 -->
	<div id="calendarModal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>日历</h3>
		</div>
		<div style=" margin-left: 9px">
			<iframe width="100%" scrolling="no" frameborder="0"
				name="BAPPIframeCanvas114629136775647655430"
				src="<%=basePath %>wnl.html" allowtransparency="true"
				style="background-color: transparent; height: 440px;"
				id="BAPPIframeCanvas114629136775647655430"></iframe>
		</div>
		<!--?keyword=%25E6%2597%25A5%25E5%258E%2586&amp;canvas_pos=platform&amp;bd_user=302920995&amp;bd_sig=25da4000bb7c243063cfce48e2d3afa0&amp;canvas_pos=platform -->


	</div>

	<div id="loginModal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>请先登录</h3>
		</div>
		<div class="modal-body">
			<h4>使用微博账号登录</h4>
			<p>更好的收藏和分享，并发现好友的推荐！</p>
			<div style="text-align: center">
				<div class="btn-group"
					style="display:inline-block; margin-left: 3px">
					<button class="btn btn-large btn-danger dropdown-toggle"
						data-toggle="dropdown" style="padding: 6px 14px">
						微博登录&nbsp; <span class="caret"></span>
					</button>
					<ul id="share" class="dropdown-menu">
						<li class="rec-sina"><a href=" <%=basePath%>user/sina/oauth"
							style="font-size: 12px;">新浪微博</a></li>
						<!--                    <li class="rec-tx">
                    -->
						<!--                        <a href="-->
						<!--" style="font-size: 12px;">
                    -->
						<!--                            腾讯微博-->
						<!--                        </a>
                    -->
						<!--                    </li>-->
					</ul>
				</div>
			</div>

			<hr style="size: 2px">

			<form class="form-horizontal" id="login-form" action="<%=basePath%>servlet/LoginServlet"
				method="post">
				<fieldset>
					<h4>使用本站账号登录</h4>
					<p></p>

					<div class="control-group">
						<label class="control-label login"> 邮箱 <font
							style="color: red">*</font> </label>
						<div class="login-controls">
							<div class="input-prepend">
								<span class="add-on"> <i class="icon-envelope"></i> </span> <input
									class="span3" size="16" name="LoginForm_email"
									id="LoginForm_email" type="text"> <span>
									<div class="errorMessage" id="LoginForm_email_em_"
										style="display:none"></div> </span>
							</div>

						</div>
					</div>

					<div class="control-group">
						<label class="control-label login"> 密码 <font
							style="color: red">*</font> </label>
						<div class="login-controls">
							<div class="input-prepend">
								<span class="add-on"> <i class="icon-asterisk"></i> </span> <input
									class="span3" size="16" name="LoginForm_password"
									id="LoginForm_password" type="password"> <span>
									<div class="errorMessage" id="LoginForm_password_em_"
										style="display:none"></div> </span>
							</div>
						</div>
					</div>

					<label class="checkbox login-checkbox"> <input
						name="LoginForm_checkbox" class="login-checkbox" type="checkbox">下次自动登录(一周）</label>

					<div style="text-align: center">
						<button type="submit" class="btn btn-info span2">登录</button>
						<a href=" <%=basePath%>login.jsp" class="btn span2">注册</a>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<!-- <input name=txt><input type=button value=setday onclick="setday(this,document.all.txt)"> -->
	<!-- <input onfocus="setday(this,'hehe')" type="text"> -->

	<div id="username" title="${user.useremail}"></div>

	<script type="text/javascript"
		src=" <%=basePath%>js/jquery-1.7.2.js "></script>
	<script type="text/javascript"
		src=" <%=basePath%>js/bootstrap.min.js "></script>
	<script type="text/javascript"
		src=" <%=basePath%>js/jquery-ui-1.10.3.custom.js "></script>
	<!-- <script type="text/javascript" srcsrc=" <%=basePath%> <%=basePath%>/jquery-ui-personalized-1.6rc2.min.js "></script> -->
	<script type="text/javascript"
		src=" <%=basePath%>js/inettuts.js "></script>
	<script type="text/javascript"
		src=" <%=basePath%>js/index.js "></script>
		<%-- <script type="text/javascript"
		src=" <%=basePath%>js/hotchange.js "></script> --%>
	<!-- <script type="text/javascript" src=" <%=basePath%> js/calendar.js "></script> -->
</body>
</html>