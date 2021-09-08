<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<title>글보기</title>
<style type="text/css">
#subjectB:link {color: black; text-decoration: none;}
#subjectB:visited {color: black; text-decoration: none;}
#subjectB:hover {color: green; text-decoration: underline;}
#subjectB:active {color: black; text-decoration: none;}
</style>


<script type="text/javascript">

	 
	 function isDownFile(fileName){		 
		 
		 location.href = "fileDownload.jsp?fileName="+fileName;
		 
	 }
	
</script>


</head>
<body>

	<h2>게시글</h2>

	<table border="1">
		<tr>
			<td colspan="4"><font size="5">${boardDTO.subject}</font></td>
		</tr>
		<tr align="center">
			<td width="150">글번호 : ${boardDTO.seq}</td>
			<td width="150">작성자 : ${boardDTO.id}</td>
			<td width="150">조회수 : ${boardDTO.hit}</td>			
			<td width="150">
				
				첨부파일 :
				
				<a id="subjectB" href="javascript:;" onclick="isDownFile('${boardDTO.fileName}')">
							
					<c:if test="${boardDTO.original_fileName ne null}">
						 ${boardDTO.original_fileName}     
					</c:if>	
				
				</a></td>
						
		</tr>
		<tr>
			<td colspan="4" height="200" valign="top">
				<pre>${boardDTO.content}</pre>
			</td>
		</tr>
	</table>
	
	
		<input type="button" value="목록" onclick="location.href='boardList?pg=${pg}'">
		<c:if test="${id == boardDTO.id}">
			<input type="button" value="글삭제" 
				onclick="location.href='boardDelete?seq=${seq}'">
			<input type="button" value="글수정" 
				onclick="location.href='boardModifyView?seq=${seq}'">
		</c:if>
	




 <br>
 <br>
 <hr>
 
 <%@ include file="../comment/comment.jsp" %>
 
 <br>
 
</body>
</html>

















