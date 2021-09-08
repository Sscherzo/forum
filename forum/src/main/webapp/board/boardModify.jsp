<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	textarea:focus {
	  outline: none;
	  height: auto;
	}
	
#subjectB:link {color: black; text-decoration: none;}
#subjectB:visited {color: black; text-decoration: none;}
#subjectB:hover {color: green; text-decoration: underline;}
#subjectB:active {color: black; text-decoration: none;}
</style>
<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">		
		function deletefile(fileName){			
			
			  $.ajax({
			        type:'POST',
			        url : "<c:url value='/board/boardFileDelete'/>",
			        data:{fileName : fileName},
			        success : function(data){
			        	var html = "";
			        	
			        	if(data=="success")
			            {                	
			            	alert("성공");
			            	html += "첨부파일 :";			            	
			            	$("#attachments").empty();
			            	$("#attachments").html(html);
			            	$("#fileName").val('');
			            	$("#original_fileName").val('');
			               
			            }else if(data == "fail"){
			            	alert("실패");
			            }
			        },
			        error:function(request,status,error){
			            
			       }
			        
			    });
			  
			 
		}
</script>


<title>글보기</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/board/temporaryBoardModify" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td colspan="4"><font size="5">${boardDTO.subject}</font></td>
		</tr>
		<tr align="center">
			<td width="150">글번호 : ${boardDTO.seq}</td>
			<td width="150">작성자 : ${boardDTO.id}</td>
			<td width="150">조회수 : ${boardDTO.hit}</td>
			<td width="150" id="attachments">
				
				첨부파일 :	
							
					<c:if test="${boardDTO.original_fileName ne null}">
						 ${boardDTO.original_fileName}     
					</c:if>	
				
			</td>
						
		</tr>
		<tr>
			<td colspan="4" height="200" valign="top">
				<textarea name="content" rows="12" style="width:98%; border: 0; resize: none;">${boardDTO.content}</textarea>				
			</td>
		</tr>
	</table>
	<input type="hidden" name="seq" value="${boardDTO.seq}">
	<input type="hidden" id="fileName" name="fileName" value="${boardDTO.fileName}">
	<input type="hidden" id="original_fileName" name="original_fileName" value="${boardDTO.original_fileName}">
	<input type="button" value="목록" onclick="location.href='boardList?pg=${pg}'">
	<input type="submit" value="수정완료">
	<input type="button" value="파일 삭제" onclick="deletefile('${boardDTO.fileName}')">
	<input type="file" name="upload1">
	
	
	
</form>
	
</body>
</html>

