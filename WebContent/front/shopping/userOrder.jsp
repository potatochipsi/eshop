<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://localhost:8080/3-28/util" prefix="util"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>订单详情</title>
		<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
	</head>
	<body>
		<div class="container-fluid">
			<h1>我的订单</h1>
			<div class="row-fluid">
				<div class="col-md-10 col-md-offset-1">
					<div>
						<a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=myOrder">全部订单</a>
						&nbsp;
						<a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=myOrder&search=0">未付款</a>
						&nbsp;
						<a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=myOrder&search=1">已付款</a>
						&nbsp;
						<a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=myOrder&search=2">未收货</a>
						&nbsp;
						<a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=myOrder&search=3">已收货</a>
					</div>
					<c:forEach items="${orderBeans}" var="orderBean">
						<table class="table">
							<tr>
								<td>订单详情</td>
								<td>收货人</td>
								<td>原价</td>
								<td>现价</td>
								<td>订单状态</td>
							</tr>
							<tr>
								<td>${orderBean.create_date}&nbsp;${orderBean.code}</td>
							</tr>
							<tr>
								<td>
									<c:forEach items="${orderBean.orderProductBeans}" var="item">
										<table class="table">
											<tr>
												<td><img alt="图片" src="${item.productBean.pic}" width="70px" height="70px"></td>
												<td>${item.productBean.name}</td>
												<td>￥${item.price}</td>
												<td>X${item.number}</td>
												<td><a href="shoppingServlet?method=toComm&id=${item.productBean.id}">评价</a></td>
											</tr>
										</table>
									</c:forEach>
								</td>
								<td>${orderBean.addressBean.name}</td>
								<td>￥${orderBean.original_price}</td>
								<td>￥${orderBean.price}</td>
								<td>
									<c:if test="${orderBean.status == 0}"><a href="pay.jsp?id=${orderBean.id}">未支付</a></c:if>
									<c:if test="${orderBean.status == 1}"><a href="shoppingServlet?method=update&status=2&id=${orderBean.id}">已支付,提醒发货</a></c:if>
									<c:if test="${orderBean.status == 2}"><a href="shoppingServlet?method=update&status=3&id=${orderBean.id}">未收货</a></c:if>
									<c:if test="${orderBean.status == 3}">已收货</c:if>
								</td>
							</tr>
						</table>
					</c:forEach>
				</div>
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
		${param.status == 1?"<div class='alert alert-success' role='alert'>支付成功</div>":""}
		${param.status == 2?"<div class='alert alert-success' role='alert'>已成功发货</div>":""}
		${param.status == 3?"<div class='alert alert-success' role='alert'>收货成功</div>":""}
	</body>
</html>