package comment.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.bean.BoardDTO;
import comment.bean.CommentDTO;

@Repository
public class CommentDAO {

  @Autowired // --rootcontext에서 설정한 mybatis 사용
  private SqlSessionTemplate sqlSession;

  // -- 댓글 쓰기 : insert
  public int commentWrite(CommentDTO commentDTO) {
    return sqlSession.insert("mybatis.commentMapper.commentWrite", commentDTO);
  }

  // -- 댓글 출력 : select
  public List<CommentDTO> commentList(int bno) {
    return sqlSession.selectList("mybatis.commentMapper.commentList", bno);
  }

  // -- 댓글 수 구하기 : select
  public int getComment(int bno) {
    return sqlSession.selectOne("mybatis.commentMapper.getComment", bno);
  }

  // -- 댓글 삭제 : delete
  public int commentDelete(int rno) {
    return sqlSession.delete("mybatis.commentMapper.commentDelete", rno);
  }

  // -- 댓글 삭제 : delete(게시글 삭제 시)
  public int commentBnoDelete(int bno) {
    return sqlSession.delete("mybatis.commentMapper.commentBnoDelete", bno);
  }

  // -- 상세보기 : select
  public CommentDTO commentView(int rno) {
    return sqlSession.selectOne("mybatis.commentMapper.commentView", rno);
  }

  // -- 댓글 수정 : update(Content만 수정)
  public int commentModify(CommentDTO commentDTO) {
    return sqlSession.update("mybatis.commentMapper.commentModify", commentDTO);
  }

  // -- 댓글 rno 최댓값 : select
  public int maxRno() {
    return sqlSession.selectOne("mybatis.commentMapper.maxRno");
  }

  // -- 댓글 order 최댓값 : select
  public int maxOrder(int comment_order) {
    return sqlSession.selectOne("mybatis.commentMapper.maxOrder", comment_order);
  }

  // -- 데이터 수정 : 댓글 update(group,depth,order 수정)
  public int commentUpdate(CommentDTO commentDTO) {
    return sqlSession.update("mybatis.commentMapper.commentUpdate", commentDTO);
  }

  // -- 데이터 수정 : 댓글 order 증가
  public int commentPlusOrder(CommentDTO commentDTO) {
    return sqlSession.update("mybatis.commentMapper.commentPlusOrder", commentDTO);
  }



}
