<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Book.Literature" import = "service.Literatureservice" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li class="current"><a href="index.jsp">User首页</a></li>
				<li><a href="addBook.jsp">添加书本</a></li>
				<li><a href="/Book_yzx/logout">注销</a></li>
				<li><c:choose>
				<c:when test="${user!=null }">
				<h2>登录的用户为：${user.userName }</h2>
				</c:when>
				<c:otherwise>
				<a href="login.jsp">登录</a>
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<form method="post" name="search" action="/Book_yzx/searchservlet">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="content" class="wrap">
	<div class="list bookList">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
					<th class="view">操作</th>
				</tr>
				<%
					Literatureservice literatureservice = new Literatureservice();
					ArrayList<Literature> literatureList = (ArrayList<Literature>)literatureservice.findAllList();
					application.setAttribute("literatureList", literatureList);
				%>
				<c:forEach items= "${literatureList }" var="literature" varStatus="state">
				<c:choose>
				<c:when test="${state.index % 2 == 0 }">
				<tr>
					<td><input type="checkbox" name="bookId" value="${state.index }" /></td>
					<td class="title" id ="td1">${literature.getName() }</td>
					<td id="td2">￥${literature.getPrice() }</td>
					<td id="td3">${literature.getNumber() }</td>
					<td class="thumb"><img src="${literature.getImgpath() }" /></td>
					<td class="thumb">
					<input type="button" value="修改" onClick = "window.location.href='modify.jsp?litName=${literature.getName()}\
&litPrice=${literature.getPrice()}&litNumber=${literature.getNumber()}&litImgpath=${literature.getImgpath() }'">
					|<input type="button" value="删除" onClick = "window.location.href='remove.jsp?litName=${literature.getName()}'">
					</td>
				</tr>
				</c:when>
				<c:otherwise>
				<tr class="odd">
					<td><input type="checkbox" name="bookId" value="${state.index }" /></td>
					<td class="title">${literature.getName() }</td>
					<td>￥${literature.getPrice() }</td>
					<td>${literature.getNumber() }</td>
					<td class="thumb"><img src="${literature.getImgpath()}" /></td>
					<td class="thumb"><input type="button" value="修改" onClick = "window.location.href='modify.jsp?litName=${literature.getName()}\
&litPrice=${literature.getPrice()}&litNumber=${literature.getNumber()}&litImgpath=${literature.getImgpath() }'">
					|<input type="button" value="删除" onClick = "window.location.href='remove.jsp?litName=${literature.getName()}'"></td>	
				</tr>
				</c:otherwise>
				</c:choose>
				</c:forEach>			
			</table>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>
</body>
</html>
