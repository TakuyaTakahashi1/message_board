<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- このファイルの内容が大枠となり、 ${param.content} と書かれた部分に各ページのビューの内容が入ります。 --%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メッセージボード</title>
</head>
<body>
	<div id="wrapper">
		<h1>メッセージボードアプリケーション</h1>
	</div>
	<div id="content">
		${param.content}
	</div>
	<div id="footer">
		by Taro Kirameki
	</div>
</body>
</html>