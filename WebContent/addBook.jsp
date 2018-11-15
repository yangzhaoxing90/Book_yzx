<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
</div>
<div id="login">
	<h2>添加书本</h2>
	<form method="post" action="/Book_yzx/AddServlet" enctype="multipart/form-data">
		<dl >
			<dt style="line-height: 25px;">书本名称：</dt>
			<dd><input class="input-text" type="text" name="bookName" /></dd>
			<dt style="line-height: 25px;">书本价格：</dt>
			<dd><input class="input-text" type="text" name="bookPrice" /></dd>
			<dt style="line-height: 25px;">书本数量：</dt>
			<dd><input class="input-text" type="text" name="bookCount" /></dd>
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
