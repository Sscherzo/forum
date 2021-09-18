package member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import member.bean.MemberDTO;


@Component
public class MemberDAO {
	
	@Autowired	//rootcontext에서 설정한  mybatis 사용
	private SqlSessionTemplate sqlSession;
	
	//멤버 로그인
	public int loginMember(MemberDTO memberDTO) {			
		return sqlSession.selectOne("mybatis.memberMapper.loginMember",memberDTO);
	}
	//멤버 가입
	public int insertMember(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mybatis.memberMapper.insertMember",memberDTO);
	}
	//아이디 중복 체크
	public int idCheckMember(MemberDTO memberDTO) {
	  	return sqlSession.selectOne("mybatis.memberMapper.idCheckMember",memberDTO);
	}	
	//salt 가져오기
	public String bringSalt(String id) {
		return sqlSession.selectOne("mybatis.memberMapper.bringSalt",id);
	}
	
	
	
}











