<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付页面</title>
		<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	</head>
	<body>
		<br>
		<form action="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=update&status=1" method="post">
			<input type="hidden" name="id" id="id" value="<%=request.getParameter("id")%>">
			<button class="btn btn-default" type="submit">支付</button>
		</form>
	</body>
</html>