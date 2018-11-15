<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Book.Literature" import = "java.util.*" import="javax.servlet.*" import = "service.Literatureservice"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	
	Literatureservice literatureservice = new Literatureservice();
	ArrayList<Literature> literatureList = (ArrayList<Literature>)literatureservice.findAllList();
	String litName = (String)request.getParameter("litName");
	literatureservice.deleteLiterature(litName);
	response.sendRedirect("index.jsp");
	
%>
</body>
</html>