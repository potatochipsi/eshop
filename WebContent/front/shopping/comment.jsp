<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>评论页面</title>
		<script type="text/javascript" src="../../static/js/jquery-1.12.1.js" ></script>
		<link rel="stylesheet" href="../../static/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../../static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" />
		<script type="text/javascript" src="../../static/bootstrap-3.3.5-dist/js/bootstrap.min.js" ></script>
	</head>
	<body>
		<div class="container">
			<%
				request.setCharacterEncoding("utf-8");
			%>
			<h1 class="text-center text-danger">添加商品</h1>
			<form action="shoppingServlet?method=comment" method="post">
				<table class="table">
		        	<tr>
		            	<td>商品名称</td>
		            	<td>${productBean.name}</td>
		            	<td>价格：</td>
		            	<td>${productBean.price}</td>
		        	</tr>
		        </table>
				<div class="form-group col-md-12">
					<label class="col-sm-2 control-label" for="name">星级：</label>
					<input type="radio" name="score" value="1" checked>一星
					<input type="radio" name="score" value="2">二星
					<input type="radio" name="score" value="3">三星
					<input type="radio" name="score" value="4">四星
					<input type="radio" name="score" value="5">五星
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">评价：</label>
					<textarea rows="10" cols="50" name="content"></textarea>
				</div>
				<input type="hidden" name="id" value="${productBean.id}">
				<div class="form-group col-md-12">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" id="subt" class="btn btn-primary btn-sm">提交</button>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>