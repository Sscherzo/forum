package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  private BoardDAO boardDAO;

  // -- 게시글 데이터 저장
  @Override
  public int boardWrite(BoardDTO boardDTO) {
    return boardDAO.boardWrite(boardDTO);
  }

  // -- 게시글 데이터 삭제
  @Override
  public int boardDelete(int seq) {
    return boardDAO.boardDelete(seq);
  }

  // -- 게시글 조회수 증가
  @Override
  public int updateHit(int seq) {
    return boardDAO.updateHit(seq);
  }

  // -- 게시글 목록 보기
  @Override
  public List<BoardDTO> boardList(int startNum, int endNum) {
    return boardDAO.boardList(startNum, endNum);
  }

  // -- 검색한 게시글 목록 보기
  @Override
  public List<BoardDTO> searchList(String search, int startNum, int endNum) {
    // TODO Auto-generated method stub
    return boardDAO.searchList(search, startNum, endNum);
  }

  // -- 상세 보기
  @Override
  public BoardDTO boardView(int seq) {
    return boardDAO.boardView(seq);
  }

  // -- 총 게시글 수 구하기
  @Override
  public int getTotalA() {
    return boardDAO.getTotalA();
  }

  // -- 검색된 게시글 수 구하기 : select
  @Override
  public int getTotalB(String search) {
    // TODO Auto-generated method stub
    return boardDAO.getTotalB(search);
  }

  // -- 게시글 데이터 수정
  @Override
  public int boardModify(BoardDTO boardDTO) {
    // TODO Auto-generated method stub
    return boardDAO.boardModify(boardDTO);
  }

  // -- 게시글 파일 데이터 수정
  @Override
  public int boardFileModify(BoardDTO boardDTO) {
    // TODO Auto-generated method stub
    return boardDAO.boardFileModify(boardDTO);
  }

}
