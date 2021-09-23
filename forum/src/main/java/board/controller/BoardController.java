package board.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import board.bean.BoardDTO;
import comment.controller.CommentService;


@Controller
@RequestMapping(value = "/board")
@SessionAttributes({"id", "pg", "seq"})
@EnableTransactionManagement
public class BoardController {

  @Autowired
  private BoardService boardService;
  @Autowired
  private CommentService commentService;
  // -- 파일이 저장되거나 업로드 되는 경로
  String realFolder = "C:/Users/seok/Desktop/warehouse";

  // -- 게시글
  @RequestMapping(value = "/boardList")
  public String boardList(Model model, HttpServletRequest request) throws Exception {

    // -- 데이터
    int pg = 0;
    int totalA = 0;
    List<BoardDTO> list = null;

    if (request.getParameter("pg") == null) {
      pg = 1;
    } else {
      pg = Integer.parseInt(request.getParameter("pg"));
    }


    // 1. 목록 가져오기
    // => 1페이지당 5개씩
    int endNum = pg * 5;
    int startNum = endNum - 4;
    
    String search = request.getParameter("search");

    // => 검색된 값이 있을 때와 없을 때
    if (search != null && search != "") {   
      list = boardService.searchList(search, startNum, endNum);
      totalA = boardService.getTotalB(search); // 검색된 총 글수
      model.addAttribute("search", search);    
    } else {
      list = boardService.boardList(startNum, endNum);
      totalA = boardService.getTotalA(); // 총 글수     
    }

    // 2. 페이징 처리
    // => 3블럭

    int totalP = (totalA + 4) / 5; // 총 페이지 수
    int startPage = (pg - 1) / 3 * 3 + 1; // 시작 페이지    
    int endPage = startPage + 2; // 끝 페이지
    if (endPage > totalP)
      endPage = totalP; // 총 페이지 보다 많을 경우

    // 3. 댓글 처리
    for (int i = 0; i < list.size(); i++) {
      list.get(i).setReply(commentService.getComment(list.get(i).getSeq()));
    }
    
    // 4. 데이터 공유 + view 처리
    model.addAttribute("pg", pg);
    model.addAttribute("list", list);
    model.addAttribute("totalP", totalP);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("size", list.size());

    return "boardList.jsp";
  }

  // -- 게시글 수정페이지에서 파일을 삭제
  @RequestMapping(value = "/boardFileDelete")
  @ResponseBody
  public String boardFileDelete(BoardDTO boardDTO, HttpSession session) throws IOException {

    String fileName = null;

    // -- seq 게시글 번호 받아 게시 번호에 맞는 행을 가져온다
    int seq = (Integer) session.getAttribute("seq");
    BoardDTO _boardDTO = boardService.boardView(seq);

    // -- 저장된 파일 이름과 경로를 가지고 파일 객체 생성
    File file = new File(realFolder, boardDTO.getFileName());

    // -- 파일이 존재하면
    if (file.exists()) {

      file.delete();
      // -- 파일 이름을 Null로 채운다.
      _boardDTO.setFileName(fileName);
      _boardDTO.setOriginal_fileName(boardDTO.getOriginal_fileName());
      boardService.boardFileModify(_boardDTO);
    }

    return "success";
  }

  // -- 임시 글 수정
  @RequestMapping(value = "/temporaryBoardModify")
  public String boardModify(BoardDTO boardDTO, HttpServletRequest request, RedirectAttributes rttr)
      throws IOException {

    // -- multipart/form-data 보내진 타입은 multipartrequest로 받아야한다
    // -- cos.jar 라이브러리를 가지고 파일을 업로드
    // -- DefaultFileRenamePolicy()는 중복된 파일명이 생기지 않도록 도와준다. 파일 이름에 숫자가 순차적으로 붙는다
    MultipartRequest multi = new MultipartRequest(request, realFolder, 5 * 1024 * 1024, "UTF-8",
        new DefaultFileRenamePolicy());

    // --boardModify.jsp에서 파일 이름을 받는다
    String original_fileName = multi.getOriginalFileName("upload1");
    String fileName = multi.getFilesystemName("upload1");
    // -- 파일을 지정된 경로에 업로드한다.
    multi.getFile("upload1");


    int seq = Integer.parseInt(multi.getParameter("seq"));
    String content = multi.getParameter("content");
    String fileName1 = multi.getParameter("fileName");
    String original_fileName1 = multi.getParameter("original_fileName");

    // -- 수정될 내용이 담길 boardDTO에 값을 넣어준다

    boardDTO.setSeq(seq);
    boardDTO.setContent(content);

    // -- 게시글에 파일이 미 등록일 경우
    if (fileName1 != null && fileName == null) {
      boardDTO.setFileName(fileName1);
      boardDTO.setOriginal_fileName(original_fileName1);
    } else { // => 이미 파일이 저장되어 있는 경우 원래 파일을 지운다.
      boardDTO.setFileName(fileName);
      boardDTO.setOriginal_fileName(original_fileName);

      File file = new File(realFolder, fileName1);

      if (file.exists()) {
        file.delete();
      }
    }
    // -- 파일 이름을 수정해준다
    boardService.boardFileModify(boardDTO);

    // -- 파일 이름이 바뀐 BoarDTO 객체를 넘겨준다 RedirectAttributes를 사용했다
    rttr.addFlashAttribute("boardDTO", boardDTO);

    return "redirect:/board/boardModify";

  }



  // -- 글 수정
  @Transactional
  @RequestMapping(value = "/boardModify")
  public String boardModify(Model model, @Valid BoardDTO boardDTO, BindingResult result,
      HttpServletRequest request, HttpServletResponse response) throws IOException {

    // -- temporaryBoardModify에서 넘긴 객체 받기
    Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

    if (flashMap != null)
      boardDTO = (BoardDTO) flashMap.get("boardDTO");

    // -- multipart/form-data로 바로 받을 수 없다
    // -- BoardDTO에서 사용한 @NotBlank를 검사해서 위반한다면 다시 수정 화면으로 돌아간다
    if (result.hasErrors()) {

      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>alert('내용을 입력하십시오.'); </script>");
      out.flush();

      return "boardModifyView";
    }

    // -- 게시판 번호로 행을 가져와서 바뀐 내용을 update하고 상세보기 페이지로 넘어간다.
    BoardDTO _boardDTO = boardService.boardView(boardDTO.getSeq());
    _boardDTO.setContent(boardDTO.getContent());
    boardService.boardModify(_boardDTO);

    model.addAttribute("seq", boardDTO.getSeq());
    model.addAttribute("boardDTO", _boardDTO);

    return "boardViewUpdate";
  }

  // -- 글을 수정했을 때 보여주는 상세페이지
  @RequestMapping(value = "/boardViewUpdate")
  public String boardViewUpdate(Model model, HttpSession session) {

    int seq = (Integer) session.getAttribute("seq");    
    BoardDTO boardDTO = boardService.boardView(seq);    
    model.addAttribute("seq", seq);
    model.addAttribute("boardDTO", boardDTO);

    return "boardView.jsp";
  }

  // -- 게시글의 제목을 클릭 했을 때 보여주는 상세 페이지 조회수가 늘어난다
  @Transactional
  @RequestMapping(value = "/boardView")
  public String boardView(Model model, @RequestParam("seq") int seq) {


    boardService.updateHit(seq); // 조회수 증가
    BoardDTO boardDTO = boardService.boardView(seq);
   
    model.addAttribute("seq", seq);
    model.addAttribute("boardDTO", boardDTO);

    return "boardView.jsp";
  }

  // -- 게시글 작성
  // -- 파일과 작성한 내용을 담아 성공하면 게시글로 이동한다
  @RequestMapping(value = "/boardWrite")
  public String boardWrite(Model model, BoardDTO boardDTO, @ModelAttribute("id") String id,
      HttpServletResponse response, HttpServletRequest request) throws IOException {

    MultipartRequest multi = new MultipartRequest(request, realFolder, 5 * 1024 * 1024, "UTF-8",
        new DefaultFileRenamePolicy());

    String original_fileName = multi.getOriginalFileName("upload1");
    String fileName1 = multi.getFilesystemName("upload1");
    multi.getFile("upload1");

    String subject = multi.getParameter("subject");
    String content = multi.getParameter("content");

    boardDTO.setId(id);
    boardDTO.setContent(content);
    boardDTO.setSubject(subject);
    boardDTO.setFileName(fileName1);
    boardDTO.setOriginal_fileName(original_fileName);
    int result = boardService.boardWrite(boardDTO);

    if (result == 1) {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>alert('글이 성공적으로 저장되었습니다.'); </script>");
      out.flush();
      return "boardList";
    }

    return "boardWriteForm";
  }

  // -- 게시글 삭제
  @Transactional
  @RequestMapping(value = "/boardDelete")
  public String boardDelete(Model model, HttpServletResponse response, @RequestParam("seq") int seq)
      throws IOException {

    // -- 파일이 존재하면 삭제
    String fileName = boardService.boardView(seq).getFileName();
    File file = new File(realFolder, fileName);
    if (file.exists()) {
      file.delete();
    }

    // -- 댓글 및 게시글 삭제
    commentService.commentBnoDelete(seq);
    boardService.boardDelete(seq);

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>alert('게시글이 삭제되었습니다.'); </script>");
    out.flush();

    return "boardList";
  }

  // -- 로그 아웃
  @RequestMapping(value = "/boardLogOut")
  public String boardLogOut(SessionStatus sessionStatus, HttpServletResponse response)
      throws IOException {
    sessionStatus.setComplete();

    return "boardListRedirect.jsp";
  }

  // -- 글 작성 확면
  @RequestMapping(value = "/boardWriteForm")
  public String boardWriteForm(Model model, BoardDTO boardDTO) {

    model.addAttribute("id", boardDTO.getId());
    return "boardWriteForm.jsp";
  }

  // -- 글 수정 페이지
  @RequestMapping(value = "/boardModifyView")
  public String boardModifyView(Model model, HttpSession session) {

    int seq = (Integer) session.getAttribute("seq");
    BoardDTO boardDTO = boardService.boardView(seq);
    model.addAttribute("boardDTO", boardDTO);

    return "boardModify.jsp";
  }

  // -- 다운로드 페이지로 이동
  @RequestMapping(value = "/boardDownload")
  public String boardDownload(BoardDTO boardDTO, HttpServletResponse response) throws IOException {

    return "fileDownload.jsp";
  }


}


