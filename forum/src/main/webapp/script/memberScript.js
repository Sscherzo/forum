
function checkLogin() {
	
	var frm = document.loginForm;
	
	if(!frm.id.value) {
		alert("아이디를 입력하세요");
		frm.id.focus();
		
	} else if(!frm.pw.value) {
		alert("패스워드를 입력하세요");
		frm.pw.focus();
		
	}else{
		frm.submit();
	}
}


	function checkSignUp() {
	
	var frm = document.signUpForm;
	
	if(!frm.id.value) {
		alert("아이디를 입력하세요");
		frm.id.focus();
	} else if(!frm.pw.value) {
		alert("패스워드를 입력하세요");
		frm.pw.focus();
	} else {
		frm.submit();
	}
}


// frm.action='두번째로 보낼 경로';


