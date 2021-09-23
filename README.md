# forum
 * 마지막 수정 날짜 2021.09.22

# Contents
1.[Motivation](#motivation)  
2.[Code Style](#code-style)   
3.[Tech stack](#tech-stack)  
4.[Version](#version)  
5.[Features of the project](#features-of-the-project)  
6.[Simple Diagram](#simple-diagram)   
7.[Feature Introduction](#feature-introduction)     


# Motivation

Java와 Spring을 공부한 성과를 프로젝트에 담고 싶었고  
MVC 패턴을 가장 잘 나타낼 수 있는 것이 게시판이라고 생각했습니다.

[:arrow_up: 목차로](#contents)


# Code Style
코드 스타일은 Goggle Style을 사용하였습니다.
[참조](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) 

[:arrow_up: 목차로](#contents)

# Tech stack  
   - Java
   - JavaScript
   - Html 5
   - Css
   - OracleDB
   - jQuery
   - JSTL
   - Jsp
   - Spring
   - MyBatis

[:arrow_up: 목차로](#contents)

# Version  
   - Spring-tool-suite-3.9.13
   - Spring-framework-5.1.6.RELEASE
   - jdk 1.6
   - jquery-3.4.1.min.js
   - Apache-tomcat-8.5.57
   - Mybatis 3.5.5
   - Oracle Database 11g Express Edition
   - Oracle SQL Developer 19.2.1.247  

 [:arrow_up: 목차로](#contents)

 # Features of the Project

1. MVC 패턴 사용하기

    → Model2 방식을 사용함으로써 View와 Controller를 분리,            코드의 유지 보수가 용이하게 되었습니다.

2. Ajax를 통한 댓글 기능

    → Ajax를 사용함으로써 페이지를 이동하지 않고 댓글이 생성되게 하였습니다.

3. MyBatis 사용

    →  복잡한 JDBC 코드를 간소화 하고 SQL문을 소스코드로 부터 분리시켜 개발에 부담을 덜었습니다.
    
 [:arrow_up: 목차로](#contents)
 
 # Simple Diagram

![1](https://user-images.githubusercontent.com/90139096/134316589-41f6d2b6-6130-449a-b2b8-c2fcd0fb99e1.PNG)

JSP를 직접 실행하면 Controller에 연결이 안되므로 BoardListStart를
만들어서 실행시켜 간접적으로 BoardList(게시판)가 실행되게 만들었습니다.

*게시판 실행화면*
![1](https://user-images.githubusercontent.com/90139096/134329341-f109f0e0-7926-4b99-ac6e-b5134116e362.PNG)

게시판에서 실행되는 동작들은 각각의 Controller를 거쳐 Mybatis를 
통해 DB와 연결되어 SQL을 사용하여 값을 가져오고 값을 받은 Controller는 용도에 맞게 데이터를 가공해 그 데이터를 View를 통해 보여주게 됩니다. 

[:arrow_up: 목차로](#contents)

# Feature Introduction
   - [회원 가입](#회원-가입)
   - [로그인](#로그인)
   - [글쓰기](#글쓰기)
   - [글 삭제와 글 수정](#글-삭제와-글-수정)

   [:arrow_up: 목차로](#contents)

# 회원 가입
 
![1](https://user-images.githubusercontent.com/90139096/134343007-43f7cb89-3b82-401a-821c-ce673085fbf2.PNG)

회원가입 기능은 아이디와 비밀번호만 입력하고 등록되게 만들었습니다.
아이디나 비밀번호를 입력하지 않을시 `alert` 이용해 경고합니다.

패스워드는 SHA256을 통해 암호화 하고 RainbowTabl 이용을 막기 위해
Salt를 사용했습니다.

```java
   MessageDigest md = MessageDigest.getInstance("SHA-256");
   SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
```

Salt를 사용하면 랜덤으로 문자가 결정되기 때문에 랜덤한 문자열과 사용자가 입력한 패스워드를 해싱하면
같은 패스워드를 입력해도 다른 문자열이 저장되고 DB를 보아도 패스워드를 알 수 없습니다.

[:arrow_up_small: 기능 소개](#feature-introduction)

# 로그인

![캡처](https://user-images.githubusercontent.com/90139096/134345885-808a433a-6c15-42ad-a1fc-a2b82cc255dc.PNG)

회원 가입한 아이디와 비밀번호로 로그인이 가능합니다.  
로그인할 때는 회원 가입 때 사용됬던 Salt값을 가져와 해싱된 값과 비교합니다.

![캡처](https://user-images.githubusercontent.com/90139096/134346568-1f724ac0-7c94-4c2d-aef0-97adcc13eb40.PNG)

로그인을 하게되면 로그인 링크가 로그 아웃으로 변합니다.  
'로그 아웃'을 누름으로써 로그아웃됩니다.   
로그인 기능은 Session을 사용하여 만들었습니다.

```java
   @SessionAttributes를 이용해서 로그인 유지
   sessionStatus.setComplete(); 이용해서 세션을 제거
```

[:arrow_up_small: 기능 소개](#feature-introduction)

# 글쓰기

![캡처](https://user-images.githubusercontent.com/90139096/134353023-2fb31edc-b5ce-43ba-9084-6a590c32454d.PNG)

로그인을 한 뒤 글쓰기가 가능합니다. 제목과 내용을 입력하지 않을 시
`alert`를 통해 경고하며 파일 선택을 눌러  
 원하는 파일을  업로드 할
수 있습니다.  

파일 업로드는 `MultipartRequest`를 사용했습니다. `MultipartRequest`를 사용하기 위해서는 *cos.jar*가 필요합니다.

```
MultipartRequest multi = new MultipartRequest(request,저장 경로,파일 크기,인코딩,new DefaultFileRenamePolicy());  
DefaultFileRenamePolicy()를 사용하면 파일 이름뒤에 1을 붙여  
파일 이름의 중복을 피할 수 있습니다.
```
[:arrow_up_small: 기능 소개](#feature-introduction)

 # 글 삭제와 글 수정

![캡처](https://user-images.githubusercontent.com/90139096/134474403-f93a6f0a-bf4f-4b5a-967d-fb5e25f8892e.PNG)

글 삭제와 글 수정은 로그인된 상태에서 자신이 쓴 글만 삭제 가능합니다.  글 수정은 첨부파일 변경과 글 내용을 변경할 수 있습니다.
파일 삭제 기능은 *Ajax*를 사용해 페이지 이동 없이 수정 가능하게 만들었습니다.  
  
수정 완료 버튼을 누르면 컨트롤러를 두번 거치게 됩니다. 첫번 째는 파일 처리를 합니다. 파일이 이미 존재하면 삭제하고 업로드를 합니다. 그리고 받은 내용과 파일 이름을 객체에 담아 두 번째로 넘겨주는데 이 때 `RedirectAttributes`를 사용합니다.  한번에 처리가 가능하지만 2번 처리를 해준 이유는 *Bean Validation*을 통해,글 내용에 `@NotBlank`
를 사용해 검증하고 싶었기 때문입니다. 오류가 생기면 `BindingResult`를 통해 에러 메세지를 보여줍니다.

[:arrow_up_small: 기능 소개](#feature-introduction)
