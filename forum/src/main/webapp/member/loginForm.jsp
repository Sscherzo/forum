<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src="../script/memberScript.js?ver=1"></script>
<script type="text/javascript">	

	var result = ${msg};

	if (result == false) {
		alert("아이디나 비밀번호가 일치하지 않습니다.");
		result = true;
	}
</script>
</head>
<body>
	<h2>로그인</h2>
	<form action="<%=request.getContextPath()%>/member/login"
		name="loginForm" method="post">
		<table border="1">
			<tr>
				<th width="70">아이디</th>
				<td><input type="text" name="id" size="30"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw" size="30"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="로그인"
					onclick="checkLogin()" /> <input type="button" value="회원가입"
					onclick="location='signUpForm'" /></td>
			</tr>
		</table>
	</form>

</body>
</html>







