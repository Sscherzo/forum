package comment.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comment.bean.CommentDTO;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

  @Autowired
  CommentService commentService;

  // -- 댓글에 답글을 달 때
  @RequestMapping(value = "/commentReply")
  public String commentReply(CommentDTO commentDTO, HttpSession session) throws Exception {
    // -- 로그인을 하지 않으면 답변하지 못함
    if ((String) session.getAttribute("id") == null)
      return "fail";

    // -- 댓글에 부여된 번호로 코멘트 데이터 하나를 가져온다
    // -- 답글이 생성될 때 게시글 번호를 넘겨주지않고 댓글 번호를 넘겨주는 방식이다
    CommentDTO commentRNO = commentService.commentView(commentDTO.getRno());

    // -- 댓글 rno(번호)를 받은 다음 게시글(bno)번호를 설정해준뒤 댓글하나를 생성한다
    CommentDTO nCommentDTO = new CommentDTO();
    nCommentDTO.setBno(commentRNO.getBno());
    nCommentDTO.setComment_content(commentDTO.getComment_content());
    nCommentDTO.setComment_writer((String) session.getAttribute("id"));
    commentService.commentWrite(nCommentDTO);

    // -- 생성된 가장 최근 댓글을 찾아서(nCommentDTO) 댓글 값을 설정해준다
    // -- 처음 댓글과 같은 그룹 설정 order(순서)와 depth(깊이 표현) 설정한뒤 답글보다 큰 order를 +1해준다.
    CommentDTO _commentRNO = commentService.commentView(commentService.maxRno());
    // _commentRNO.setComment_order(commentService.maxOrder(commentRNO.getComment_group())+1);
    _commentRNO.setComment_order(commentRNO.getComment_order() + 1);
    _commentRNO.setComment_group(commentRNO.getComment_group());
    _commentRNO.setComment_depth(commentRNO.getComment_depth() + 1);
    commentService.commentPlusOrder(commentRNO);
    commentService.commentUpdate(_commentRNO);

    return "success";
  }


  // -- 댓글을 수정할 때
  @RequestMapping(value = "/commentModify")
  public String commentModify(CommentDTO commentDTO, HttpSession session) throws Exception {

    // -- 로그인을 하지 않으면 수정 불가
    if ((String) session.getAttribute("id") == null)
      return "login";

    // -- 댓글 값을 가져와서 작성자 아이디와 현재 로그인한 아이디를 비교한다
    CommentDTO _commentDTO = commentService.commentView(commentDTO.getRno());
    _commentDTO.setComment_content(commentDTO.getComment_content());
    String CommentdId = _commentDTO.getComment_writer();
    String connectId = (String) session.getAttribute("id");

    // -- 댓글과 현재 로그인한 아이디가 다르면 실패,수정된 내용이 없으면 내용을 입력
    if (connectId.equals(CommentdId) == false)
      return "fail";
    if (commentDTO.getComment_content() == null || commentDTO.getComment_content() == "")
      return "comment_content";

    commentService.commentModify(_commentDTO);

    return "success";
  }

  // -- 댓글 삭제
  @RequestMapping(value = "/commentDelete")
  public String commentDelete(Model model, CommentDTO commentDTO, HttpSession session)
      throws Exception {

    // -- 로그인을 하지 않으면 삭제 불가
    if ((String) session.getAttribute("id") == null)
      return "login";

    String connectId = (String) session.getAttribute("id");
    CommentDTO _commentDTO = commentService.commentView(commentDTO.getRno());
    String CommentdId = _commentDTO.getComment_writer();

    // -- 댓글과 현재 로그인한 아이디가 다르면 실패
    if (connectId.equals(CommentdId) == false)
      return "fail";

    commentService.commentDelete(commentDTO.getRno());

    return "success";
  }

  // -- 댓글 쓰기
  @RequestMapping(value = "/commentWrite")
  public String commentWrite(Model model, CommentDTO commentDTO, HttpServletRequest request)
      throws Exception {

    int seq = Integer.parseInt(request.getParameter("seq"));
    String id = request.getParameter("id");

    // -- 아이디가 있어야 작성 가능 , 내용을 작성해야함
    if (id == null || id == "")
      return "fail";
    if (commentDTO.getComment_content() == null || commentDTO.getComment_content() == "")
      return "comment";   
    
    commentDTO.setBno(seq);
    commentDTO.setComment_writer(id);

    commentService.commentWrite(commentDTO);

    return "success";
  }

  // -- 게시글에 달린 댓글
  @RequestMapping(value = "/commentList", produces = "application/text; charset=UTF-8")
  public ResponseEntity<Object> commentList(Model model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    HttpHeaders responseHeaders = new HttpHeaders();

    int seq = Integer.parseInt(request.getParameter("seq"));

    // -- 게시글 번호에 맞는 댓글들을 가져온다
    List<CommentDTO> commentList = commentService.commentList(seq);

    JSONArray json = new JSONArray(commentList);

    return new ResponseEntity<Object>(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
}
