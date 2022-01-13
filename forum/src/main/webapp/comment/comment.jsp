<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/bootstrap.css">
</head>
<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<%
  String seq = request.getParameter("seq");
String id = (String) session.getAttribute("id");
%>
<body>
	<div class="container">
		<form id="commentForm" name="commentForm" method="post">
			<br>
			<br>
			<div>
				<div>
					<span><strong>Comments</strong></span> <span id="cCnt"></span>
				</div>
				<div>
					<table class="table">
						<tr>
							<td><textarea
									style="width: 450px; height: 120px; outline: none; resize: none;"
									rows="3" cols="30" id="comment_content" name="comment_content"
									placeholder="댓글을 입력하세요"></textarea> <br>
								<div>
									<a href="javascript:;" onClick="fn_comment()"
										class="btn pull-right btn-success">등록</a>
								</div></td>
						</tr>
					</table>
				</div>
			</div>
			<input type="hidden" id="seq" name="seq" value="${seq}" /> <input
				type="hidden" id="id" name="id" value="${id}" />
		</form>
	</div>
	<br>
	<hr>
	<div class="container">
		<form id="commentListForm" name="commentListForm" method="post">
			<div id="commentList"></div>
		</form>
	</div>

	<script>

/*
 * 초기 페이지 로딩시 댓글 불러오기
 */
 
$(document).ready(function(){
		
	getCommentList();

});



/*
 * 댓글 등록하기
 */
function fn_comment(){	
    
    $.ajax({
        type:'POST',
        url : "<c:url value='/comment/commentWrite'/>",
        data:$("#commentForm").serialize(),
        success : function(data){
            if(data=="success")
            {            	
                getCommentList();
                $("#comment").val("");
            }else if(data == "fail"){
            	alert("로그인후에 가능합니다.");
            }else if(data == "comment"){
            	alert("내용을 입력하세요.");
            }
        },
        error:function(request,status,error){
            
       }
        
    });
    
	
}

/*
 * 댓글 삭제하기
 */

function fn_deleteReply(rno){		 
	
    $.ajax({
        type:'POST',
        url : "<c:url value='/comment/commentDelete'/>",
        data:{rno : rno},
        success : function(data){
            if(data=="success")
            {                	
                getCommentList();
               
            }else if(data == "fail"){
            	alert("댓글 작성자만 삭제 가능합니다.");
            }else if(data == "login"){
            	alert("로그인후에 가능합니다.");
            }
        },
        error:function(request,status,error){
            
       }
        
    });
}

/*
 * 댓글 수정하기
 */

function fn_editReplyComplete(rno){	
	
	
	var replyEditContent = $('#editContent').val();	
	
	
	$.ajax({
        type:'POST',
        url : "<c:url value='/comment/commentModify'/>",
        data:{rno : rno,
        	  comment_content : replyEditContent},
        success : function(data){
            if(data=="success")
            {                	
            	
            	getCommentList();
               
            }else if(data == "fail"){
            	alert("댓글 작성자만 수정 가능합니다.");
            }else if(data == "comment_content"){
            	alert("내용을 입력하세요.");
            }else if(data == "login"){
            	alert("로그인후에 가능합니다.");
            }
        },
        error:function(request,status,error){
            
       }
        
    });
	
}

/*
 * 댓글 수정화면 보여 주기
 */

function fn_editReply(rno){
		
	
	 $.ajax({
	        type:'POST',
	        url : "<c:url value='/comment/commentList'/>",
	        dataType : "json",
	        data:$("#commentForm").serialize(),
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	        success : function(data){
	            
	            var html = "";
	            var cCnt = data.length;
	            
	            if(data.length > 0){
	                
	                for(i=0; i<data.length; i++){
	                                    	
	                   
	                    
	                    if(data[i].rno != rno){
	                    	html += "<div><table class='table'><tr><td width=\"220\">"+data[i].comment_writer+'<a href="javascript:void(0)" onclick="fn_reReply(' +data[i].rno+ ' )">댓글</a></td>';
	                    	html += "<td width=\"400\" align=\"left\">"+data[i].comment_content + "</td>";
	                    	html += "<td align=\"center\">"+data[i].comment_regdate + "</td>";
		                    html += "<td align=\"center\">"
		                    html += '<a href="javascript:void(0)" onclick="fn_editReply(' +data[i].rno+ ' )" style="padding-right:5px">수정</a>';
	                   		html += '<a href="javascript:void(0)" onclick="fn_deleteReply(' +data[i].rno+ ')" >삭제</a>';
	                    }else{
	                    	html += "<div><table class='table'><tr><td width=\"220\">"+data[i].comment_writer+"</td>";
	                    	html += "<td width=\"400\" align=\"left\" >"+"<textarea name=\"editContent\" id=\"editContent\" rows=\"10\" style=\"resize: none;\">"+data[i].comment_content +"</textarea>"+"</td>";
	                    	html += "<td align=\"center\">"+data[i].comment_regdate + "</td>";
		                    html += "<td align=\"center\">"
		                    html += '<a href="javascript:void(0)" onclick="fn_editReplyComplete(' +data[i].rno+ ' )" style="padding-right:5px">수정완료</a>';
	                   		html += '<a href="javascript:void(0)" onclick="getCommentList()" >취소</a>';
	                    }	                      
	                   
	                    
	                    html +=	"</td>"
	                    html += "</tr>"
	                    html += "</table></div>";
	                    html += "</div>";
	                    html += "<hr>";	                    
	                    
	                }
	                
	            } 
	            
	            $("#cCnt").html(cCnt);
	            $("#commentList").html(html);
	            
	        },
	        error:function(request,status,error){
	            
	       }
	        
	    });
}



 
/*
 *  댓글 불러오기
 */
function getCommentList(){	
    
    $.ajax({
        type:'POST',
        url : "<c:url value='/comment/commentList'/>",
        dataType : "json",
        data:$("#commentForm").serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(data){
            
            var html = "";
            var cCnt = data.length;
            
            if(data.length > 0){
                
                for(i=0; i<data.length; i++){                	
                	                	          	
                    html += "<div><table class='table'><tr><td width=\"220\">"   
                    
                    if(data[i].comment_depth>0){
                    	for(j=0;j<data[i].comment_depth;j++){
                    		html += "&nbsp;&nbsp;";
                    		if(j == data[i].comment_depth-1)
                    			html +="ㄴ";
                    	}
                    }
                    
                	html += data[i].comment_writer
                	html +='<a href="javascript:void(0)" onclick="fn_reReply(' +data[i].rno+ ')">댓글</a></td>';
                    html += "<td width=\"400\" align=\"left\">"+data[i].comment_content + "</td>";
                    html += "<td align=\"center\">"+data[i].comment_regdate + "</td>";
                    html += "<td align=\"center\">"
                  	html += '<a href="javascript:void(0)" onclick="fn_editReply(' +data[i].rno+ ' )" style="padding-right:5px">수정</a>';
                    html += '<a href="javascript:void(0)" onclick="fn_deleteReply(' +data[i].rno+ ')" >삭제</a>';
                    html +=	"</td>"
                    html += "</tr>"
                    html += "</table></div>";
                    html += "</div>";
                    html += "<hr>";                    
                    
                }
                
            } else {
                
                html += "<div>";
                html += "<div><table class='table'><h4><strong>등록된 댓글이 없습니다.</strong></h4>";
                html += "</table></div>";
                html += "</div>";
                
            }
            
            $("#cCnt").html(cCnt);
            $("#commentList").html(html);
            
        },
        error:function(request,status,error){            
       }
        
    });
}

/*
 *   '댓글'을 눌렀을 때 대댓글을 적을수 있는 textarea가 나오고 depth를 '->'로 표현
 */

function fn_reReply(rno){
	
	
	var sessionId = '<%=id%>';

			$.ajax({
						type : 'POST',
						url : "<c:url value='/comment/commentList'/>",
						dataType : "json",
						data : $("#commentForm").serialize(),
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						success : function(data) {

							var html = "";
							var cCnt = data.length;

							if (data.length > 0) {

								for (i = 0; i < data.length; i++) {

									if (data[i].rno != rno) {
										html += "<div><table class='table'><tr><td width=\"220\">"

										if (data[i].comment_depth > 0) {
											for (j = 0; j < data[i].comment_depth; j++) {
												html += "&nbsp;&nbsp;";
												if (j == data[i].comment_depth - 1)
													html += "ㄴ";
											}
										}

										html += data[i].comment_writer
										html += '<a href="javascript:void(0)" onclick="fn_reReply('
												+ data[i].rno
												+ ')">댓글</a></td>';
										html += "<td width=\"400\" align=\"left\">"
												+ data[i].comment_content
												+ "</td>";
										html += "<td align=\"center\">"
												+ data[i].comment_regdate
												+ "</td>";
										html += "<td align=\"center\">"
										html += '<a href="javascript:void(0)" onclick="fn_editReply('
												+ data[i].rno
												+ ' )" style="padding-right:5px">수정</a>';
										html += '<a href="javascript:void(0)" onclick="fn_deleteReply('
												+ data[i].rno + ')" >삭제</a>';
										html += "</td>"
										html += "</tr>"
										html += "</table></div>";
										html += "</div>";
										html += "<hr>";
									} else {

										html += "<div><table class='table'><tr><td width=\"220\">"
												+ data[i].comment_writer
												+ '<a href="javascript:void(0)" onclick="fn_reReply('
												+ data[i].rno
												+ ')">댓글</a></td>';
										html += "<td width=\"400\" align=\"left\">"
												+ data[i].comment_content
												+ "</td>";
										html += "<td align=\"center\">"
												+ data[i].comment_regdate
												+ "</td>";
										html += "<td align=\"center\">"
										html += '<a href="javascript:void(0)" onclick="fn_editReply('
												+ data[i].rno
												+ ' )" style="padding-right:5px">수정</a>';
										html += '<a href="javascript:void(0)" onclick="fn_deleteReply('
												+ data[i].rno + ')" >삭제</a>';
										html += "</td>"
										html += "</tr>"
										html += "</table></div>";
										html += "</div>";
										html += "<hr>";
										html += "<div><table class='table'><tr><td width=\"220\">"

										for (x = 0; x < data[i].comment_depth + 1; x++) {
											html += "->";
										}

										html += data[i].comment_writer;
										html += "</td>";
										html += "<td width=\"400\" align=\"left\" >"
												+ "<textarea name=\"editContent\" id=\"editContent\" rows=\"10\" style=\"resize: none;\"></textarea>"
												+ "</td>";
										html += "<td align=\"center\">"
												+ data[i].comment_regdate
												+ "</td>";
										html += "<td align=\"center\">"
										html += '<a href="javascript:void(0)" onclick="fn_ReplyComplete('
												+ data[i].rno
												+ ' )" style="padding-right:5px">답변완료</a>';
										html += '<a href="javascript:void(0)" onclick="getCommentList()" >취소</a>';
										html += "</td>"
										html += "</tr>"
										html += "</table></div>";
										html += "</div>";
										html += "<hr>";

									}

								}

							}

							$("#cCnt").html(cCnt);
							$("#commentList").html(html);

						},
						error : function(request, status, error) {

						}

					});

		}

		/*
		 *  '답변완료'를 눌렀을 때
		 */

		function fn_ReplyComplete(rno) {

			var replyEditContent = $('#editContent').val();

			$.ajax({
				type : 'POST',
				url : "<c:url value='/comment/commentReply'/>",
				data : {
					rno : rno,
					comment_content : replyEditContent
				},
				success : function(data) {
					if (data == "success") {
						getCommentList();

					} else if (data == "fail") {
						alert("로그인후에 가능합니다.");
					}
				},
				error : function(request, status, error) {

				}

			});
		}
	</script>

</body>
</html>
