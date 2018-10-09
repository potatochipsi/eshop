<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>商品展示</title>
		<script type="text/javascript" src="../../static/js/jquery-1.12.1.js" ></script>
		<link rel="stylesheet" href="../../static/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../../static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" />
		<script type="text/javascript" src="../../static/bootstrap-3.3.5-dist/js/bootstrap.min.js" ></script>
		
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
		<div class="container">
			<h1>商品查询</h1>
			<table class="table table-striped">
				<c:forEach items="${productBeans}" var="item" varStatus="status">
					<c:if test="${status.index == 0}">
						<tr>
							<td>id</td>
							<td>name</td>
							<td>操作</td>
							<td>操作</td>
						</tr>
					</c:if>
					<tr>
						<td>${item.id}</td>
						<td><a href="productServlet?method=listDetails&id=${item.id}">${item.name}</a></td>
						<td><a href="productServlet?method=update&id=${item.id}">修改</a></td>
						<td><a href="productServlet?method=delete&id=${item.id}" onclick="return del()">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="form-group col-md-12">
				<c:if test="${param.status.equals('1')}">
					<div class="alert alert-success" role="alert">删除商品成功</div>
				</c:if>
				<c:if test="${param.status.equals('2')}">
					<div class="alert alert-success" role="alert">添加商品成功</div>
				</c:if>
				<c:if test="${param.status.equals('3')}">
					<div class="alert alert-success" role="alert">修改商品成功</div>
				</c:if>
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