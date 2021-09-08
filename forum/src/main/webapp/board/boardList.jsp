<%@page import="board.bean.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록보기</title>
<style type="text/css">
#paging {color: black; text-decoration: none;}
#currentPaging {color: gray; text-decoration: underline;}

#subjectA:link {color: black; text-decoration: none;}
#subjectA:visited {color: black; text-decoration: none;}
#subjectA:hover {color: green; text-decoration: underline;}

</style>

<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function isLogin(seq) {
				
		location.href = "boardView?seq="+seq + "&pg=" + ${requestScope.pg};
	}
	
	function search() {
				
		var search = $('#search').val();	
		
		location.href = "boardList?pg="+${requestScope.pg} + "&search=" + search;
	}
</script>

</head>
<body>
<h2>게시판</h2>
<c:if test="${size > 0}">

	<table border="1">
		<tr bgcolor="ffff00">
			<th width="70">글번호</th>
			<th width="200">제목</th>
			<th width="100">작성자</th>
			<th width="100">작성일</th>
			<th width="70">조회수</th>
		</tr>
	<c:forEach var="boardDTO" items="${list}">
		<tr align="center">
			<td>${boardDTO.seq}</td>
			<td><a id="subjectA" href="javascript:;" onclick="isLogin(${boardDTO.seq})">
				${boardDTO.subject}				
				<c:if test="${boardDTO.reply ne 0}">
					[${boardDTO.reply}]     
				</c:if>	</a></td>			
			<td>${boardDTO.id}</td>
			<td>${boardDTO.logtime}</td>
			<td>${boardDTO.hit}</td>
		</tr>
	</c:forEach>
	
	
		<tr>
			<td colspan="5" align="center">
		<c:if test="${startPage > 3}">
				[<a href="boardList?pg=${startPage-1}&search=${search}">이전</a>]	
		</c:if>	
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
			<c:if test="${pg==i}">
				[<a id="paging" href="boardList?pg=${i}&search=${search}">${i}</a>]
			</c:if>
			
			<c:if test="${pg!=i}">
				[<a id="currentPaging" href="boardList?pg=${i}&search=${search}">${i}</a>]
			</c:if>
		</c:forEach>		
		
		<c:if test="${endPage < totalP}">
				[<a href="boardList?pg=${endPage+1}&search=${search}">다음</a>]
		</c:if>			
			</td>
		</tr>
	</table>
	<br>
	</c:if>
	
	<c:if test="${size == 0}">
			<h4>게시글이 존재하지 않거나 만족하는 결과를 찾을 수 없습니다.</h4>
	</c:if>
	
	<a href="boardWriteForm">새글 쓰기</a>
	<c:if test="${id == null}">
	<a href="<c:url value='/member/loginForm'/>">로그인</a>
	</c:if>
	<c:if test="${id != null}">
		<a href="boardLogOut">로그 아웃</a>
	</c:if>
	
	<c:forEach var="i" begin="1" end="21" step="1">
			&nbsp;
	</c:forEach>
	
	<input type="text" style="width: 150px" id="search">
	<input type="button" value="검색" onclick="search()">
	
		
</body>
</html>















