package member.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.bean.MemberDTO;
import member.dao.MemberDAO;


@Controller
@RequestMapping(value = "/member") // 중복되는 경로
@SessionAttributes({"id","pg"})    // 세션값
public class LoginController implements WebMvcConfigurer{	
	
	@Autowired
	private MemberService memberService;
	
    //-- 멤버 로그인
	@RequestMapping(value = "/login")
	public String login(Model model,MemberDTO memberDTO){
		
		//-- 회원이 등록 되어있는지 Member Table에서 확인
		int result = memberService.loginMember(memberDTO);
		
		//-- 회원 등록이 되어있다면
		if(result == 1) {
			
			//-- 게시판에 Id와 패스워드 전달
			model.addAttribute("id",memberDTO.getId());
			model.addAttribute("pw",memberDTO.getPw());				
		
			return "redirect:/board/boardList";		
		}
		
		//-- 로그인 페이지에 msg false를 전달 msg값에 따른 alert 생성
		model.addAttribute("msg",false);
		
		return "loginForm.jsp";
	}
		
	//-- 회원 등록
	@RequestMapping(value = "/signUp")
	public String signUp(Model model,HttpServletResponse response,MemberDTO memberDTO) throws IOException {
		
		//-- 아이디 체크		
		int loginCheckResult = memberService.idCheckMember(memberDTO);
		
		//-- 이미 등록된 아이인 경우
		if(loginCheckResult == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 등록된 아이디입니다.'); </script>");
			out.flush();	
			
			//-- 회원가입 페이지로
			return "signUpForm.jsp";
		}
		
		//-- 회원 가입 성공시
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원 등록 되었습니다..'); </script>");
		out.flush();	
		
		//-- Member Table에 회원 데이터 기록
		int result = memberService.insertMember(memberDTO);
		
		//-- 데이터 공유 + view 처리
		model.addAttribute("result",result);
		
		//-- 로그인 페이지
		return "loginForm.jsp";
			
	}	
	
	///-- 단순 페이지 전환	
	
	@RequestMapping(value = "/loginForm")
	public String loginForm(){
		return "loginForm.jsp";
	}
	
	@RequestMapping(value = "/signUpForm")
	public String signUp(){
			return "signUpForm.jsp";
	}	

}




