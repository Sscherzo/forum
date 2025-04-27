# Forum
 * 마지막 수정 날짜 2022.12.23
 
# Demonstration Video
[![Video Label](http://img.youtube.com/vi/1EnWdFjtGHg/0.jpg)](https://youtu.be/4jZ8NcwX8eo)
🠑 위의 비디오를 클릭해주세요.
# Contents
1.[Motivation](#motivation)  
2.[Code Style](#code-style)   
3.[Tech stack](#tech-stack)  
4.[Version](#version)  
5.[Features of the project](#features-of-the-project)  
6.[Simple Diagram](#simple-diagram)   
7.[DB TABLE](#db-table)  
8.[Feature Introduction](#feature-introduction)     


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
   - Ajax

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

![게시판](https://user-images.githubusercontent.com/90139096/209348409-cda168c1-de17-49e4-bffb-783c59e67582.PNG)

게시판에서 실행되는 동작들은 각각의 Controller를 거쳐 Mybatis를 
통해 DB와 연결되어 SQL을 사용하여 값을 가져오고 값을 받은 Controller는 용도에 맞게 데이터를 가공해 그 데이터를 View를 통해 보여주게 됩니다. 

[:arrow_up: 목차로](#contents)

# DB TABLE

## *Board Table*  
![1](https://user-images.githubusercontent.com/90139096/137687501-403391aa-7393-450b-b110-0804c9346af6.PNG)

게시판 번호에 일련번호 붙이기  
- #### create sequence seq_board nocache nocycle;
- #### seq_board.nextval


## *Board_Comment*
![2](https://user-images.githubusercontent.com/90139096/137688354-9a1d9b4d-6b4b-42c4-a572-8809bff4cac8.PNG)  
댓글 번호와 댓글 그룹에 일련번호 붙이기  
- #### create sequence comment_group start with 1 increment by 1 nocache nocycle;
- ####  create sequence board_comment_seq start with 1 increment by 1 nocache nocycle;
       
## *MEMBER*

![3](https://user-images.githubusercontent.com/90139096/137688518-acac5e92-1b61-4242-b49b-a7bbc0dbdb27.PNG)

[:arrow_up: 목차로](#contents)

# Feature Introduction
   - [회원 가입](#회원-가입)
   - [로그인](#로그인)
   - [글쓰기](#글쓰기)
   - [글 삭제와 글 수정](#글-삭제와-글-수정)
   - [목록 및 페이징](#목록-및-페이징)
   - [검색](#검색)
   - [댓글](#댓글)

   [:arrow_up: 목차로](#contents)

# 회원 가입
 
![1](https://user-images.githubusercontent.com/90139096/134343007-43f7cb89-3b82-401a-821c-ce673085fbf2.PNG)

회원가입 기능은 아이디와 비밀번호만 입력하고 등록되게 만들었습니다.
아이디나 비밀번호를 입력하지 않을시 `alert` 이용해 경고합니다.

패스워드는 SHA256을 통해 암호화 하고 Rainbow Table 이용을 막기 위해
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

```java
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

```

[:arrow_up_small: 기능 소개](#feature-introduction)

# 목록 및 페이징

![게시판2](https://user-images.githubusercontent.com/90139096/209349793-958f66b3-1839-4042-8d2b-87e22476ccf5.PNG)


**목록**은 get방식으로 받는 pg(페이지)를 통해 결정됩니다.목록은 한페이지에 5개의 글을 보여주는 방식이며 *Rownum*을 사용했습니다.

```java
    int endNum = pg * 5;
    int startNum = endNum - 4;
```
위의 변수를 사용해 where 절에 사용하므로써 5개의 글을 보여줄 수 있습니다.  페이지 링크를 누르면 pg(페이지)값이 바뀌고 페이지에 맞는 목록을 출력합니다.

```
to_char(logtime, 'YYYY.MM.DD')
```
작성일은 to_char 함수를 사용함으로써 Date값(작성일)을 원하는 형식에 맞게 바꾸었습니다.

**페이징**은 우선 총 페이지 수를 구해야합니다.
```
총 페이지수 = (총 글 갯수+(보여줄 글 갯수 - 1))/보여줄 글 갯수
```
그리고 시작 페이지와 끝 페이지를 설정합니다.

```
시작 페이지 = (페이지 - 1)/보여줄 하단 페이지 링크 수 *  보여줄 하단 페이지 링크 수 +1

끝 페이지 = 시작 페이지 + (보여줄 하단 페이지 링크 수-1)

끝 페이지는 총 페이지보다 많아지면 안 되므로 총 페이지보다 많으면 끝 페이지에 총 페이지 값을 넣어줍니다.
```     
  
EX) 게시판 하단의 링크를 3개만 보여주고 싶다면
```java
    int totalP = (totalA + 4) / 5; // 총 페이지 수
    int startPage = (pg - 1) / 3 * 3 + 1; // 시작 페이지    
    int endPage = startPage + 2; // 끝 페이지
    if (endPage > totalP) // 총 페이지 보다 많을 경우
      endPage = totalP; 
```
  
View에서는 시작페이지가 보여줄 하단 페이지 링크수 보다 크면 [**이전**]을 출력하고 [**이전**] 링크를 누르면 (시작페이지 - 1) 값을 페이지에 돌려줍니다.  
  
끝 페이지가 총 페이지보다 작을 경우 [**다음**]을 출력하고  [**다음**] 링크를 누르면
(끝 페이지 + 1) 값을 페이지에 돌려줍니다.

[:arrow_up_small: 기능 소개](#feature-introduction)

# 검색

![검색1](https://user-images.githubusercontent.com/90139096/209350552-cbc1bae9-1f88-4875-8030-636675b7f0f6.PNG)

검색은 제목과 내용을 한번에 검색합니다.그리고 검색된 갯수에 맞게
페이징 처리를 해줍니다.  

```java
// => 검색된 값이 있을 때와 없을 때 (search(검색값))
    if (search != null && search != "") {   
      list = boardService.searchList(search, startNum, endNum);
      totalA = boardService.getTotalB(search); // 검색된 총 글수
      model.addAttribute("search", search);    
    } else {
      list = boardService.boardList(startNum, endNum);
      totalA = boardService.getTotalA(); // 총 글수     
    }

```
  
검색된 결과가 없다면 다음과 같이 나옵니다.

![결과없음](https://user-images.githubusercontent.com/90139096/209352064-db5ab49f-b109-483f-93d0-05f9b32127d7.PNG)


[:arrow_up_small: 기능 소개](#feature-introduction)

# 댓글

![캡처](https://user-images.githubusercontent.com/90139096/134527005-d0baa0ad-538c-40ed-802f-aeced65a9b46.PNG)

댓글은 페이지 이동없이 하기 위해서 *Ajax*를 사용했습니다.  
```js

$.ajax({
        type:'POST',  // 전송 방식
        url : "<c:url value='/comment/commentList'/>", // 전송페이지
        dataType : "json", //요청한 데이터 형식
        data:$("#commentForm").serialize(), // 전송할 데이터,쿼리 스트링 형식의 데이터 변환
        contentType: "application/x-www-form-urlencoded;charset=UTF-8", //Content-type 설정
        success : function(data){ // 전송에 성공했을 때

```
*Ajax*를 컨트롤러가 사용하기 위해서 `@RestController`를 사용하였습니다.
댓글은 삽입,삭제,수정이 가능하며 대댓글의 경우 깊이에 따른 들여쓰기를 해주었습니다. 댓글이 입력되면 Comments 옆에 댓글 수가 표시되며 글 제목에서도 코멘트 수를 확인 할 수 있습니다.

![검색2](https://user-images.githubusercontent.com/90139096/209351596-03b0630b-5a72-4828-b466-a54709df3465.PNG)

![댓글](https://user-images.githubusercontent.com/90139096/209351673-ffe106fd-f51d-4236-bd7b-a88b11fa4572.PNG)

[:arrow_up_small: 기능 소개](#feature-introduction)
