<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Bootstrap, from Twitter</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/inettuts.css" />
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/inettuts.js.css" />

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
<link rel="stylesheet" type="text/css"
	href=" <%=basePath%>css/bootstrap-responsive.css" />

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon"
	href=" <%=basePath%>ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href=" <%=basePath%>ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href=" <%=basePath%>ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href=" <%=basePath%>ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href=" <%=basePath%>ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

	<div class="container">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<a class="brand" href="/" title="记忆犹新" style="padding-left: 28px;">
					Next Network</a>
				<ul id="navigation" class="nav">

					<li class="active"><a href=" <%=basePath%>/category/memory">首页</a>
					</li>

					<li><a href=" <%=basePath%>/category/lover">测试1</a>
					</li>

					<li><a href=" <%=basePath%>/category/travel">测试2</a>
					</li>

					<li><a href=" <%=basePath%>/category/flavor">测试3</a>
					</li>

					<li><a href=" <%=basePath%>/category/timeline">测试4</a>
					</li>

				</ul>


				<ul class="nav nav-pills pull-right" style="padding-right: 30px;">
					<li><a href="#loginModal" class="dropdown-toggle"
						data-toggle="modal"> <i class="icon-user icon-white"></i>&nbsp;
							<span style="color:white">登录/注册</span> <!--
                            <a class="pull-right" href="/auth/register">注册</a>
                            <a class="pull-right" href="/auth/signin">登录</a>
                            --> </a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="loginModal" class="modal hide fade login-modal">
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
							微博登录&nbsp;<span class="caret"></span>
						</button>
						<ul id="share" class="dropdown-menu">
							<li class="rec-sina"><a href="/user/sina/oauth"
								style="font-size: 12px;"> 新浪微博 </a></li>
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
							<label class="control-label login">邮箱<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-envelope"></i>
									</span> <input class="span3" size="16" name="LoginForm_email"
										id="LoginForm_email" type="text"> <span><div
											class="errorMessage" id="LoginForm_email_em_"
											style="display:none"></div>
									</span>
								</div>

							</div>
						</div>

						<div class="control-group">
							<label class="control-label login">密码<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-asterisk"></i>
									</span> <input class="span3" size="16" name="LoginForm_password"
										id="LoginForm_password" type="password"> <span><div
											class="errorMessage" id="LoginForm_password_em_"
											style="display:none"></div>
									</span>
								</div>
							</div>
						</div>

						<label class="checkbox login-checkbox"> <input
							name="LoginForm_checkbox" class="login-checkbox" type="checkbox">
							下次自动登录（一周） </label>


						<div style="text-align: center">
							<button type="submit" class="btn btn-info span2">登录</button>
							<a href=" <%=basePath%>login.jsp" class="btn span2">注册</a>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="span5 offset1 well" style="height: 360px">
				<h3>快速注册账号</h3>
				<hr style="size: 2px">
				<form class="form-horizontal" id="register-form" action="<%=basePath%>servlet/RegisterServlet"
					method="post">
					<fieldset>
						<div class="control-group">
							<label class="control-label login">邮箱<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-envelope"></i>
									</span> <input size="16" name="RegisterForm_email"
										id="RegisterForm_email" type="text">
									<div class="errorMessage" id="RegisterForm_email_em_"
										style="display:none"></div>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label login">昵称<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i>
									</span> <input placeholder="(4-12位，中英文或数字)" size="16"
										name="RegisterForm_nickname" id="RegisterForm_nickname"
										type="text" maxlength="12">
									<div class="errorMessage" id="RegisterForm_nickname_em_"
										style="display:none"></div>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label login">密码<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-asterisk"></i>
									</span> <input placeholder="(4-16位)" size="16"
										name="RegisterForm_password" id="RegisterForm_password"
										type="password" maxlength="16">
									<div class="errorMessage" id="RegisterForm_password_em_"
										style="display:none"></div>
								</div>

							</div>
						</div>
						<div class="control-group">
							<label class="control-label login">再次输入密码<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-asterisk"></i>
									</span> <input size="16" name="RegisterForm_password_confirmation"
										id="RegisterForm_password_confirmation" type="password"
										maxlength="16">
									<div class="errorMessage"
										id="RegisterForm_password_confirmation_em_"
										style="display:none"></div>
								</div>
							</div>
						</div>
						<div class="form-actions login-btn">
							<button type="submit" class="btn btn-info span2">立即注册</button>
						</div>
					</fieldset>
				</form>
			</div>


			<div class="span5 well" style="height: 360px">
				<h3>使用微博账号登录</h3>
				<p></p>
				<div style="text-align: center">
					<div class="btn-group"
						style="display:inline-block; margin-left: 3px">
						<button class="btn btn-large btn-danger dropdown-toggle"
							data-toggle="dropdown" style="padding: 6px 14px">
							微博登录&nbsp;<span class="caret"></span>
						</button>
						<ul id="share" class="dropdown-menu">
							<li class="rec-sina"><a href="/user/sina/oauth"
								style="font-size: 12px;"> 新浪微博 </a></li>
							<!--                <li class="rec-tx">-->
							<!--                    <a href="-->
							<!--" style="font-size: 12px;">-->
							<!--                        腾讯微博-->
							<!--                    </a>-->
							<!--                </li>-->
						</ul>
					</div>
				</div>
				<hr style="size: 2px">
				<form class="form-horizontal" id="login-form" action="<%=basePath%>servlet/LoginServlet"
					method="post">
					<fieldset>
						<h3>使用本站账号登录</h3>
						<!--  <h4>
                {{error}}
            </h4> -->

						<div class="control-group">
							<label class="control-label login">邮箱<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-envelope"></i>
									</span> <input class="span3" size="16" name="LoginForm_email"
										id="LoginForm_email" type="text"> <span><div
											class="errorMessage" id="LoginForm_email_em_"
											style="display:none"></div>
									</span>
								</div>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label login">密码<font
								style="color: red">*</font>
							</label>
							<div class="login-controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-asterisk"></i>
									</span> <input class="span3" size="16" name="LoginForm_password"
										id="LoginForm_password" type="password"> <span><div
											class="errorMessage" id="LoginForm_password_em_"
											style="display:none"></div>
									</span>
								</div>

							</div>
						</div>

						<div class="control-group">
							<label class="control-label login"></label> <label
								class="checkbox login-checkbox"> <input
								name="LoginForm_checkbox" class="login-checkbox" type="checkbox">
								下次自动登录（一周） </label>
						</div>

						<div class="form-actions login-btn">
							<button type="submit" class="btn btn-info span2">登录</button>
						</div>
					</fieldset>
				</form>
			</div>

		</div>
	</div>
	<div id="errorwarning" title="${error}"></div>


	<script type="text/javascript"
		src=" <%=basePath%>js/jquery-1.7.2.js"></script>
	<script type="text/javascript"
		src=" <%=basePath%>js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src=" <%=basePath%>js/jquery-ui-personalized-1.6rc2.min.js"></script>
	<script type="text/javascript"
		src=" <%=basePath%>js/inettuts.js"></script>
	<script type="text/javascript">
    var errorwarning = $("#errorwarning").attr("title");
    if(errorwarning!=""&&errorwarning!=null){
        alert(errorwarning);
    }
    </script>
</body>
</html>
