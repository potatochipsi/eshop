<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看管理员</title>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
		
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<h1>用户列表</h1>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<table class="table table-striped">
						<tr>
							<td>id</td>
							<td>头像</td>
							<td>手机号码</td>
							<td>昵称</td>
							<td>真实姓名</td>
							<td>性别</td>
							<td>状态</td>
							<td>操作</td>
						</tr>
						
						<!--遍历userBeans-->
						<c:forEach items="${userBeans}" var="item" varStatus="status">
							<tr>
								<td>${item.id}</td>
								<td><img alt="头像" src="${item.pic}" width="50px" height="50px" class="img-circle"></td>
								<td><a href="userServlet?method=search&search=${item.username}">${item.username}</a></td>
								<td>${item.nickname}</td>
								<td>${item.truename}</td>
								<td>
									<c:if test="${item.sex==0}">男</c:if>
									<c:if test="${item.sex==1}">女</c:if>
								</td>
								<td>
									<c:if test="${item.status==1}">活跃</c:if>
									<c:if test="${item.status==0}">冻结</c:if>
								</td>
								<td>
									<c:if test="${item.status==1}"><a href="userServlet?method=update&status=0&id=${item.id}">冻结</a></c:if>
									<c:if test="${item.status==0}"><a href="userServlet?method=update&status=1&id=${item.id}">解冻</a></c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row-fluid">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div>
						<util:page pagingBean="${pagingBean}"/>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</body>
</html>