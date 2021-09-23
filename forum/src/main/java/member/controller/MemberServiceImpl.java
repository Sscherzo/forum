package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import member.bean.MemberDTO;
import member.dao.MemberDAO;



@Component
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberDAO dao;

  // -- 멤버 로그인
  public int loginMember(MemberDTO memberDTO) {
    // TODO Auto-generated method stub
    return dao.loginMember(memberDTO);
  }

  // -- 멤버 가입
  @Override
  public int insertMember(MemberDTO memberDTO) {
    // TODO Auto-generated method stub
    return dao.insertMember(memberDTO);
  }

  // -- 아이디 중복 체크
  @Override
  public int idCheckMember(MemberDTO memberDTO) {
    // TODO Auto-generated method stub
    return dao.idCheckMember(memberDTO);
  }
  // -- 암호화에 필요한 salt 가져오기
  @Override
  public String bringSalt(String id) {
    // TODO Auto-generated method stub
    return dao.bringSalt(id);
  }


}
