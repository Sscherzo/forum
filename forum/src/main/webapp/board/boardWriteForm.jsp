<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script type="text/javascript" src="../script/boardScript.js"></script>
<script type="text/javascript">
	if(${id == null}) {
		alert("로그인 후 작성할 수 있습니다.");
		location.href = "/forum/member/loginForm";
		//history.back();
	}else{
		location.href = "boardWriteForm";
	}
</script>
</head>
<body>
<h2>글쓰기</h2>
	<form action="<%=request.getContextPath()%>/board/boardWrite"
		method="post" name="boardWriteForm" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th width="50">제목</th>
				<td><input type="text" name="subject" size="43"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="15" cols="45" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="작성"
							onclick="checkBoardWrite()"> 
				<input type="reset"	value="다시 작성"> 
				<input type="file" name="upload1">
				</td>
			</tr>
		</table>
	</form>
	<a href="boardList">게시판으로</a>

</body>
</html>


