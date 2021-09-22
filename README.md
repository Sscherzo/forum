# forum
 * 마지막 수정 날짜 2021.09.22

# Contents
1.[Motivation](#motivation)  
2.[Code Style](#code-style)   
3.[Tech stack](#tech-stack)  
4.[Version](#version)  
5.[Features of the project](#features-of-the-project)  
6.[Simple Diagram](#simple-diagram)   
3.[동기](#동기)    
4.[동기](#동기)

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
 
