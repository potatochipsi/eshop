<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>个人中心</title>
		<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
		<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
		<link href="${pageContext.request.contextPath}/static/typeList/typeList.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/typeList/typeList.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-spinner.min.js"></script>
	</head>
	<frameset frameborder="no" rows="82px, *">
	    <frame src="../common/usertop.jsp"/>
		<frameset  cols="285px, *">
			<frame src="../common/userleft.jsp"/>
			<frame name="mainAction"/>
		</frameset>
	</frameset>
</html>