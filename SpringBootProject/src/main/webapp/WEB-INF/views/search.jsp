<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align='center'>
		<form action="into" method="post">
			<div>
				<h3>重設密碼</h3>
			</div>
			帳號 : <input type="text" id="mail" name="mail"> <br> <br>
			<br> <input type='submit' value='忘記密碼'>
			 <br> <br>
		</form>




		<div>
			<form action="index" method="post">
				<input type='submit' value='註冊頁面'>
			</form>
			<br>
			<form action="login" method="post">
				<input type='submit' value='登入頁面'>
			</form>
		</div>
	</div>


</body>
</html>