<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>查看管理员</title>
		<script type="text/javascript" src="../static/js/jquery-1.12.1.js" ></script>
		<script type="text/javascript" src="../static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script>
		<link rel="stylesheet" href="../static/bootstrap-3.3.5-dist/css/bootstrap.css" />
		
		<script type="text/javascript">
			function del() {
				if (confirm("真的要删除吗？")){
					return true;
				}else{
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<h1>管理员列表</h1>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<table class="table table-striped">
						<tr>
							<td>id</td>
							<td>username</td>
							<td>password</td>
							<td>salt</td>
							<td align="center" colspan="2">操作</td>
						</tr>
						<!-- forEach遍历出adminBeans -->
						<c:forEach items="${adminBeans}" var="item" varStatus="status">
							<tr>
								<td>${item.id}</td>
								<td><a>${item.username}</a></td>
								<td>${item.password}</td>
								<td>${item.salt}</td>
								<td><a href="../admin/adminServlet?method=toUpdate&id=${item.id}">修改</a></td>
								<td><a href="../admin/adminServlet?method=delete&id=${item.id}" onclick="return del()">删除</a></td>
							</tr>
						</c:forEach>
					</table>
					
					<c:if test="${param.status.equals('2')}">
						<div class="alert alert-success" role="alert">修改成功</div>
					</c:if>
					<c:if test="${param.status.equals('3')}">
						<div class="alert alert-success" role="alert">删除成功</div>
					</c:if>
					<c:if test="${param.status.equals('1')}">
						<div class="alert alert-info" role="alert">没有权限操作超级管理员</div>
					</c:if>
				</div>
				<div class=" col-md-1"></div>
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