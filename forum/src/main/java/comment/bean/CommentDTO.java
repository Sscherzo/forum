package comment.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentDTO {
	
	private int rno; 					//--댓글 번호
	private int bno;					//--게시물 번호
	private String comment_writer;  	//--댓글 내용
	private String comment_content; 	//--댓글 작성자
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	//Json 사용시 Date는 JsonFormat을 사용
	private String comment_regdate;  		 //--댓글 작성 날짜	
	private int comment_group;  		//-- 그룹
	private int comment_depth;  		//-- 깊이
	private int comment_order;  		//-- 댓글 정렬
	
	
	
	public int getComment_group() {
		return comment_group;
	}
	public void setComment_group(int comment_group) {
		this.comment_group = comment_group;
	}
	public int getComment_depth() {
		return comment_depth;
	}
	public void setComment_depth(int comment_depth) {
		this.comment_depth = comment_depth;
	}
	public int getComment_order() {
		return comment_order;
	}
	public void setComment_order(int comment_order) {
		this.comment_order = comment_order;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_regdate() {
		return comment_regdate;
	}
	public void setComment_regdate(String comment_regdate) {
		this.comment_regdate = comment_regdate;
	}
	

}
