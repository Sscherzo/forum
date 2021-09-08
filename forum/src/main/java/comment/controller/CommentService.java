package comment.controller;

import java.util.List;

import board.bean.BoardDTO;
import comment.bean.CommentDTO;

public interface CommentService {
	//-- 댓글 출력 : select
	public List<CommentDTO> commentList(int bno) throws Exception;
	//-- 댓글 쓰기 : insert
	public int commentWrite (CommentDTO commentDTO) throws Exception;		
	//-- 댓글 수 구하기 : select
	public int getComment(int bno) throws Exception;
	//-- 댓글 삭제 : delete
	public int commentDelete(int rno);
	//-- 댓글 삭제 : delete(게시글 삭제 시)
	public int commentBnoDelete(int bno);	
	//-- 상세보기 : select	
	public CommentDTO commentView(int rno);	
	//-- 댓글 수정 : update(Content만 수정)
	public int commentModify(CommentDTO commentDTO);	
	//-- 댓글 rno 최댓값 : select
	public int maxRno();	
	//-- 댓글 order 최댓값 : select
	public int maxOrder(int comment_order);	
	//-- 데이터 수정 : 댓글 update(group,depth,order 수정)
	public int commentUpdate(CommentDTO commentDTO);	
	//-- 데이터 수정 : 댓글 order 증가
	public int commentPlusOrder(CommentDTO commentDTO);
}
