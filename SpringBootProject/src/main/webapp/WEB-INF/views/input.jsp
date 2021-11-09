<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var error = "${error}";
	var passworSsuccess = "${passworSsuccess}";
	var passworError = "${passworError}";
	
	window.onload = function() {

		if (error) {
			alert(error);
		}
		
		if (passworSsuccess) {
			alert(passworSsuccess);
		}
		
		if (passworError) {
			alert(passworError);
		}
		
	}
</script>
</head>
<body>

	<form action="input" method="post">
		<div align='center'>
			<div>
				<h3>登入</h3>
			</div>
			帳號 : <input type="text" id="mail" name="mail"> <br> <br>
			密碼 : <input type="text" id="password" name="password"> <br>
			<br> <input type='submit' value='登入'> <br> <br>
	</form>
	
	
	
	<div>
	<form action="index" method="post">
		<input type='submit' value='註冊頁面'>
	</form><br>
	
	
	
	<form action="search" method="post">
		<input type='submit' value='找回密碼'>
	</form>
	</div>
	
	</div>


</body>
</html>