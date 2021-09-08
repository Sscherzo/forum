package board.controller;

import java.util.List;
import board.bean.BoardDTO;

public interface BoardService {
	//-- 게시글 데이터 저장 : insert
	public int boardWrite(BoardDTO boardDTO);
	//-- 게시글 데이터 수정 : update
	public int boardModify(BoardDTO boardDTO);
	//-- 게시글 파일 데이터 수정 : update
	public int boardFileModify(BoardDTO boardDTO);
	//-- 게시글 데이터 삭제 : delete
	public int boardDelete(int seq);
	//-- 게시글 조회수 증가 : update
	public int updateHit(int seq);		
	//-- 게시글 목록 보기     : select
	public List<BoardDTO> boardList(int startNum, int endNum);
	//-- 검색한 게시글 목록 보기     : select
	public List<BoardDTO> searchList(String search,int startNum, int endNum);
	//-- 상세 보기     : select	
	public BoardDTO boardView(int seq);
	//-- 총 게시글 수 구하기 : select
	public int getTotalA();	
	//-- 검색된 게시글 수 구하기 : select
	public int getTotalB(String search);	
	
}







