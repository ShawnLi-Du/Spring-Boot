<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align='center'>
		<h2>會員資料</h2>
		<table border="1">
			<tr style="background-color: #a8fefa">
				<th>帳號
				<th>密碼 <c:forEach items="${lists}" var="stud">
						<tr>
							<td><c:out value="${stud.getEmail()}" />
							<td><c:out value="${stud.getPassword()}" />
					</c:forEach>
		</table>
	<br><br>
	<form action="login" method="post">
		<input type='submit' value='登入頁面'>
	</form>
	
</div>

</body>
</html>