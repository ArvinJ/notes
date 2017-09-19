<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/login/css/style_log.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/login/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/login/css/userpanel.css">
</head>

<body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_logo">
		<!-- img-log  -->
		 <div id="message" style="color:red">${error}<span id="showname" style="display:inline;"></span></div>
		</div>
		<div class="login_boder">
			<div class="login_padding" id="login_model">
			<form action="${pageContext.request.contextPath }/user/login" method="post">
				<h2>USERNAME</h2>
				<label> <input type="text" id="username" name = "username" class="txt_input txt_input2" value="Your name"></label>
				<h2>PASSWORD</h2>
				<label> <input type="password" name="password" id="userpwd" class="txt_input" value="******"></label>
				<p class="forgot">
					<a id="iforget" href="javascript:void(0);">Forgot your
						password?</a>
				</p>
				<div class="rem_sub">
					<div class="rem_sub_l">
						<input type="checkbox" name="checkbox" id="save_me"> <label
							for="checkbox">Remember me</label>
					</div>
					<label> <input type="submit" class="sub_button"
						name="button" id="button" value="SIGN-IN" style="opacity: 0.7;"></label>
				</div>
				
			</form>	
			</div>

			<div id="forget_model" class="login_padding" style="display: none">
				<br>

					<h1>Forgot password</h1> <br>
						<div class="forget_model_h2">(Please enter your registered
							email below and the system will automatically reset users’
							password and send it to user’s registered email address.)</div> <label>
							<input type="text" id="usrmail" class="txt_input txt_input2">
					</label>


						<div class="rem_sub">
							<div class="rem_sub_l"></div>
							<label> <input type="submit" class="sub_buttons"
								name="button" id="Retrievenow" value="Retrieve now"
								style="opacity: 0.7;"> <input type="submit"
									class="sub_button" name="button" id="denglou" value="Return"
									style="opacity: 0.7;"></label>
						</div>
			</div>
		</div>
	</div>
	<br> <br>
</body>
</html>