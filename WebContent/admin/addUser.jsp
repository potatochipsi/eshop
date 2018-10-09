<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>添加管理员</title>
		<meta http-equiv="Content-Type"content="text/html; charset=UTF-8">
		<meta name="viewport"content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet"type="text/css" href="../static/bootstrap-3.3.5-dist/css/bootstrap.css"/>
	</head>
	<body>
		<div class="row-fluid"style="margin-top: 200px;">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form role="form" class="form-horizontal" action="../admin/adminServlet?method=addUser&updateId=${updateBean.id}" method="post" id="checkForm">
					<div class="form-group">
						<label class="col-md-3 control-label"for="username">用户名</label>
						<div class="col-md-9">
							<input class="form-control" name="username" type="text" id="username" placeholder="Username" value="${updateBean.username}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="inputPassword">密码</label>
						<div class="col-md-9">
							<input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password" value="${updateBean.password}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="password2">确认密码</label>
						<div class="col-md-9">
							<input type="password" name="password2" class="form-control" id="password2" placeholder="conformPassword">
						</div>
					</div>
					<div class="form-group"></div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-primary btn-block">提交</button>
						</div>
					</div>
					
					<c:if test="${param.status.equals('1')}">
						<div class="alert alert-success"role="alert">添加成功</div>
					</c:if>
					<c:if test="${param.status.equals('2')}">
						<div class="alert alert-danger"role="alert">该用户已存在</div>
					</c:if>
					<c:if test="${param.status.equals('3')}">
						<div class="alert alert-success"role="alert">成功</div>
					</c:if>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
		<script type="text/javascript" src="../static/js/jquery-1.12.1.js" ></script>
		<script type="text/javascript" src="../static/bootstrap-3.3.5-dist/js/bootstrap.js" ></script>
		<script type="text/javascript" src="../static/js/jquery.validate.js" ></script>
		<script type="text/javascript" src="../static/js/myValidate.js" ></script>
	</body>
</html>