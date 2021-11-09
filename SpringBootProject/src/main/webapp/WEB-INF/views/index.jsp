<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var mailValue = '';
	var passwordFunction = '';
	var myreg = /^[^\[\]\(\)\\<>:;,@.]+[^\[\]\(\)\\<>:;,@]*@[a-z0-9A-Z]+(([.]?[a-z0-9A-Z]+)*[-]*)*[.]([a-z0-9A-Z]+[-]*)+$/g;
	var email = '';
	var password = '';

		mailFunction = function() {
			mailValue = document.getElementById("mail").value; //帳號

			$.ajax({
				type : 'post',
				url : 'register',
				data : {
					"email" : mailValue,
				},
				dataType : 'json',
				success : function(data) {
				//data : 0=可新增, 1=重複
				console.log("data = " + data);
					if (mailValue == '') { //如果空白
						alert("請輸入帳號和密碼!!");
						document.getElementById('dataID').value = '';
						error();
					} else if (myreg.test(mailValue) == false) { //email 驗證
						alert('提示\n請輸入有效的E_mail！');
						document.getElementById('dataID').value = '';
						error();
					} else if (data == '0') { // 可以新增
						email = 'ok';
						document.getElementById('dataID').value = 'OK !';
					} else { // 重複
						document.getElementById('dataID').value = '帳號重複 !';
						error();
					}
				}
			});
		}//mailFunction

		passwordFunction = function() {
			passwordValue = document.getElementById("password").value; //密碼
			mailValue = document.getElementById("mail").value; //帳號

			console.log("passwordValue = " + passwordValue);
			console.log("mailValue = " + mailValue);

			if (passwordValue == '') {
				alert("請輸入密碼!!");
				error();
			} else if (!email) {
				alert("請輸入正確帳號!!");
				error();

			} else if (email == 'ok' && passwordValue != '') {
				console.log("email 1= " + email);
				console.log("password 1= " + password);
				enter();
			}

		}//passwordFunction

		// 按鈕
		function enter() {
			let data = document.getElementById("data");
			data.innerHTML = "<input type='submit' value='註冊'>";
		}
		// 按鈕消失
		function error() {
			let data = document.getElementById("data");
			data.innerHTML = "";
		}

</script>
</head>
<body>
	<div align='center'>

		<form action="registerS" method="post">

			<div>
				<h3>註冊</h3>
			</div>
			<div>
				<div>
					帳號 : <input type="text" id="mail" name="mail"
						onfocusout="mailFunction()">
				</div>
				<div>
					<input type="text" id="dataID" name="dataID" readonly
						unselectable="on" style="border: none; text-align: center"
						disabled="disabled">
				</div>
			</div>
			<br> 
			<div>
				密碼 : <input type="text" id="password" name="password"
					onfocusout="passwordFunction()"> <br> <br>

				<div id='data' align='center'></div>
		</form>
		<form action="login" method="post">
		<br>
			<input type='submit' value='登入頁面'>
		</form>


	</div>
</body>
</html>