package member.bean;

public class MemberDTO {
	
	private String id;			//-- 회원 아이디
	private String pw; 			//-- 회원 비밀번호
	
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
