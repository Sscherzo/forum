package comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import comment.bean.CommentDTO;
import comment.dao.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentDAO commentDAO;

  // -- 댓글 출력 : select
  @Override
  public List<CommentDTO> commentList(int bno) throws Exception {
    // TODO Auto-generated method stub
    return commentDAO.commentList(bno);
  }

  // -- 댓글 쓰기 : insert
  @Override
  public int commentWrite(CommentDTO commentDTO) throws Exception {
    // TODO Auto-generated method stub
    return commentDAO.commentWrite(commentDTO);
  }

  // -- 댓글 수 구하기 : select
  @Override
  public int getComment(int bno) throws Exception {
    // TODO Auto-generated method stub
    return commentDAO.getComment(bno);
  }

  // -- 댓글 삭제 : delete
  @Override
  public int commentDelete(int rno) {
    // TODO Auto-generated method stub
    return commentDAO.commentDelete(rno);
  }

  // -- 댓글 삭제 : delete(게시글 삭제 시)
  @Override
  public int commentBnoDelete(int bno) {
    // TODO Auto-generated method stub
    return commentDAO.commentBnoDelete(bno);
  }

  // -- 상세보기 : select
  @Override
  public CommentDTO commentView(int rno) {
    // TODO Auto-generated method stub
    return commentDAO.commentView(rno);
  }

  // -- 댓글 수정 : update(Content만 수정)
  @Override
  public int commentModify(CommentDTO commentDTO) {
    // TODO Auto-generated method stub
    return commentDAO.commentModify(commentDTO);
  }

  // -- 댓글 rno 최댓값 : select
  @Override
  public int maxRno() {
    // TODO Auto-generated method stub
    return commentDAO.maxRno();
  }

  // -- 데이터 수정 : 댓글 update(group,depth,order 수정)
  @Override
  public int commentUpdate(CommentDTO commentDTO) {
    // TODO Auto-generated method stub
    return commentDAO.commentUpdate(commentDTO);
  }

  // -- 데이터 수정 : 댓글 order 증가
  @Override
  public int commentPlusOrder(CommentDTO commentDTO) {
    // TODO Auto-generated method stub
    return commentDAO.commentPlusOrder(commentDTO);
  }

  // -- 댓글 order 최댓값 : select
  @Override
  public int maxOrder(int comment_order) {
    // TODO Auto-generated method stub
    return commentDAO.maxOrder(comment_order);
  }

}
