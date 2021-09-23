<%@page import="javax.sound.midi.SysexMessage"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	  String fileName = request.getParameter("fileName");
	//파일이 저장된 실제 폴더 위치 구하기       	
	String realPath = "C:/Users/seok/Desktop/warehouse";
	
	//request.getServletContext().getRealPath("/storage");
	
	//file 객체에 다운받을 파일의 절대경로 설정
	File file = new File(realPath, fileName);
	
	
	//파일의 이름 설정
	String set_fileName = new String(URLEncoder.encode(fileName, "UTF-8")).replace("//+", " ");
	response.setHeader("Content-Disposition", "attachment;fileName=" + set_fileName);
	
	//파일의 크기 설정
	response.setHeader("Content-Length", String.valueOf(file.length()));
	
	//파일출력
	//기존 jsp의 내장객체 out의 buffer를 비우고 , 출력해야, 예외 발생이 안생김
	out.clear();
	out = pageContext.pushBody();
	
	FileInputStream fis = new FileInputStream(file);
	BufferedInputStream bis = new BufferedInputStream(fis);
	byte[] b = new byte[(int) file.length()];
	bis.read(b, 0, b.length);
	bis.close();
	fis.close();
	
	ServletOutputStream sos = response.getOutputStream();
	BufferedOutputStream bos = new BufferedOutputStream(sos);
	bos.write(b);
	bos.close();
%>

