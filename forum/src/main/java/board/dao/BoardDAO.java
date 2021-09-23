package board.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import board.bean.BoardDTO;

@Repository
public class BoardDAO {

  @Autowired
  private SqlSessionTemplate sqlSession;

  // -- 게시글 데이터 저장 : insert
  public int boardWrite(BoardDTO boardDTO) {
    return sqlSession.insert("mybatis.boardMapper.boardWrite", boardDTO);
  }

  // -- 게시글 데이터 삭제 : delete
  public int boardDelete(int seq) {
    return sqlSession.delete("mybatis.boardMapper.boardDelete", seq);
  }

  // -- 게시글 조회수 증가 : update
  public int updateHit(int seq) {
    return sqlSession.update("mybatis.boardMapper.updateHit", seq);
  }

  // -- 게시글 목록 보기 : select
  public List<BoardDTO> boardList(int startNum, int endNum) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("startNum", startNum);
    map.put("endNum", endNum);
    return sqlSession.selectList("mybatis.boardMapper.boardList", map);
  }

  // -- 검색한 게시글 목록 보기 : select
  public List<BoardDTO> searchList(String search, int startNum, int endNum) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("search", search);
    map.put("startNum", startNum);
    map.put("endNum", endNum);
    return sqlSession.selectList("mybatis.boardMapper.searchList", map);
  }

  // -- 상세보기 : select
  public BoardDTO boardView(int seq) {
    return sqlSession.selectOne("mybatis.boardMapper.boardView", seq);
  }

  // -- 게시글 데이터 수정 : update
  public int boardModify(BoardDTO boardDTO) {
    // TODO Auto-generated method stub
    return sqlSession.update("mybatis.boardMapper.boardModify", boardDTO);
  }

  // -- 게시글 파일 데이터 수정 : update
  public int boardFileModify(BoardDTO boardDTO) {
    return sqlSession.update("mybatis.boardMapper.boardFileModify", boardDTO);
  }

  // -- 총 게시글 수 구하기 : select
  public int getTotalA() {
    return sqlSession.selectOne("mybatis.boardMapper.getTotalA");
  }

  // -- 검색된 게시글 수 구하기 : select
  public int getTotalB(String search) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("search", search);
    return sqlSession.selectOne("mybatis.boardMapper.getTotalB", map);
  }


}


