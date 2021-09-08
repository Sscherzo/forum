package member.controller;

import member.bean.MemberDTO;

public interface MemberService {
	//-- 멤버 로그인        : select
	public int loginMember(MemberDTO memberDTO);
	//-- 멤버 가입           : insert
	public int insertMember(MemberDTO memberDTO);
	//-- 아이디 중복 체크 : select
	public int idCheckMember(MemberDTO memberDTO);
}
