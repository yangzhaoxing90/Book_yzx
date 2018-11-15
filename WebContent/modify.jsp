<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Book.Literature" import = "java.util.*" import="javax.servlet.*" import = "service.Literatureservice"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<%
	Literatureservice literatureservice = new Literatureservice();
	ArrayList<Literature> literatureList = (ArrayList<Literature>)literatureservice.findAllList();
	String litName = (String)request.getParameter("litName");
	double litPrice = Double.parseDouble(request.getParameter("litPrice"));
	int litNumber = Integer.parseInt(request.getParameter("litNumber"));
	String litImgpath = request.getParameter("litImgpath");
	
	application.setAttribute("litImgpath", litImgpath);
	literatureservice.deleteLiterature(litName);
%>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
</div>
<div id="login">
	<h2>修改书本</h2>
	<form method="post" action="/Book_yzx/addservlet" enctype="multipart/form-data">
		<dl >
			<dt style="line-height: 25px;">书本名称：</dt>
			<dd><input class="input-text" type="text" name="bookName" value = "<%=litName%>"/></dd>
			<dt style="line-height: 25px;">书本价格：</dt>
			<dd><input class="input-text" type="text" name="bookPrice" value= "<%=litPrice%>" /></dd>
			<dt style="line-height: 25px;">书本数量：</dt>
			<dd><input class="input-text" type="text" name="bookCount" value= "<%=litNumber%>"/></dd>
			<dt style="line-height: 25px;">书本图片：</dt>
			<dd><input type="file" name="fileName"/></dd>
			<dt style="line-height: 25px;"></dt>
			<dd><input style="border: 1; "  type="submit" name="submit" value="确定" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有</div>
</body>
</html>
