<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../script/memberScript.js?ver=1"></script>
</head>
<body>
		<h2>회원 가입</h2>
		
	<form action="<%= request.getContextPath() %>/member/signUp" method="post" name="signUpForm">
		<table border="1">
			<tr align="center">
				<td>아이디</td>
				<td ><input type="text" name="id"></td>
			</tr>
			<tr align="center">
				<td >비밀번호</td>
				<td ><input type="password" name="pw"></td>
			</tr>
			
			<tr align="center">
				<td colspan="2">
					<input type="button" value="등록" onclick="checkSignUp()">
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>