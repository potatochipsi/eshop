<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>删除订单</title>
		<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
		
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
				<h1>用户订单删除</h1>
				<div class="col-md-6">
					<form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath}/admin/userOrderServlet?method=del" method="post">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="订单号" name="search">
						</div>
						<button type="submit" class="btn btn-primery" onclick="return del()">提交</button>
					</form>
				</div>
			</div>
			<div class="row-fluid">
				<div class="col-md-12">
					<c:if test="${param.status.equals('1')}">
						<div class="alert alert-success" role="alert">删除成功</div>
					</c:if>
				</div>
			</div>
		</div>
	</body>
</html>