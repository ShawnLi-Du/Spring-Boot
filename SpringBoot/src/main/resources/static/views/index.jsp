<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
span {
	display: -moz-inline-box;
	display: inline-block;
	width: 200px;
}

.ss {
	margin: 0 auto;
	width: 90%; /*畫面顯示90%*/
}
</style>
<script>
	var email = ''; //mail格式正確時, 帶入 "ok"

	mailFunction = function() {
		var myreg = /^[^\[\]\(\)\\<>:;,@.]+[^\[\]\(\)\\<>:;,@]*@[a-z0-9A-Z]+(([.]?[a-z0-9A-Z]+)*[-]*)*[.]([a-z0-9A-Z]+[-]*)+$/g;
		var el = document.getElementById("dataID");
		var mailValue = document.getElementById("mail").value; //帳號

		if (mailValue == '') { //如果空白
			el.innerHTML = '請輸入帳號!!';
			email = '';
			error();
		} else if (myreg.test(mailValue) == false) { //email 格式驗證
			el.innerHTML = '提示\n請輸入有效的E_mail！';
			email = '';
			error();

		} else {

			$.ajax({
				type : 'post',
				url : 'register',
				data : {
					"email" : mailValue
				},
				dataType : 'json',
				success : function(data) {
					//data : 0=可新增, 1=重複
					var data = data.result;
					if (data == '0') {
						el.innerHTML = 'OK!';
						email = 'ok';
					} else {
						el.innerHTML = '帳號重複!';
						email = '';
						error();
					}
				}
			}); //ajax
		} //if
	}//mailFunction

	passwordFunction = function() {
		var pw = document.getElementById("dataIDup");
		var passwordValue = document.getElementById("password").value; //密碼
		var mailValue = document.getElementById("mail").value; //帳號

		console.log("email = " + email);

		if (!passwordValue) {
			pw.innerHTML = '請輸入密碼!!';
			error();
		} else if (email == '') {
			pw.innerHTML = '請輸入正確帳號!';
			email = '';
			error();
		} else if (email == 'ok' && mailValue) {
			pw.innerHTML = 'OK!';
			enter();
		}
	}//passwordFunction

	// 按鈕-顯示
	function enter() {
		let data = document.getElementById("data");
		data.innerHTML = "<input type='submit' value='註冊'>";
	}
	// 按鈕-消失
	function error() {
		let data = document.getElementById("data");
		data.innerHTML = "";
	}


// 	--------------------------------------------------

//                            fileFunction = function() { 
//                                 console.log("測試1");
//                                 readURL(this); // this代表<input id="imgInp">
//                             };

//                             function readURL(input) {
//                             	console.log("input = " + input);
//                             	console.log("input.files = " + input.files);
//                             	console.log("input.files[0] = " + input.files[0]);
//                                 if (input.files && input.files[0]) { //如果有選檔案, 會被放在input.files裡,且是一個Array
//                                     var reader = new FileReader(); //讀檔
//                                     reader.onload = function(e) { //拿到檔案後驅動onload事件
//                                         imgHead.src = e.target.result;
//                                     }
//                                     reader.readAsDataURL(input.files[0]);
//                                 }
//                             }
                            
</script>
</head>
<body>

	<div align='center'>
		<form action="registerS" method="post">


			<h3>註冊</h3>

			<div>
				<img width='80' height='80' id='imgHead'>
			</div><br>
			
			<div>
				<input name='file' type='file' id='file' onchange="fileFunction()" />
			</div><br>
			
			<div>
				<s:textfield name="mail" id="mail" label="帳號 " onfocusout="mailFunction()" />
			</div>

			<div>
				<span id="dataID"></span>
			</div><br>

			<div>
				<s:textfield name="password" id="password" label="密碼 " onfocusout="passwordFunction()" />
			</div>
			
			<div>
				<span id="dataIDup"></span>
			</div><br> <br>
			
			<div id='data'></div>
	
		</form>



		<form action="input" method="post">
			<br> <input type='submit' value='登入頁面'>
		</form>
	</div>


</body>
</html>