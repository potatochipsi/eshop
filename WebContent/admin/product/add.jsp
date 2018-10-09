<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>商品添加</title>
		<script type="text/javascript" src="../../static/js/jquery-1.12.1.js" ></script>
		<link rel="stylesheet" href="../../static/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../../static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" />
		<script type="text/javascript" src="../../static/bootstrap-3.3.5-dist/js/bootstrap.min.js" ></script>
		<link rel="stylesheet" href="../../static/Ueditor/themes/default/css/ueditor.css" />
		<script type="text/javascript" src="../../static/Ueditor/ueditor.config.js" ></script>
		<script type="text/javascript" src="../../static/Ueditor/ueditor.all.min.js" ></script>
		<script type="text/javascript" src="../../static/Ueditor/ueditor.all.js" ></script>
		<script type="text/javascript" src="../../static/Ueditor/lang/zh-cn/zh-cn.js" ></script>
		
		<script type="text/javascript">
			function showType(obj) {
				$(obj).parent().nextAll().remove();
				if (obj == null) {
					id = 0;
				} else {
					id = obj.value;
				}
				
				//ajax 请求
				$.get("type/productTypeServlet",{
					method : "getType",
					id : id
				},
				function(data) {
					if (data != null && data.length > 0) {
						var content = "<div class='col-sm-2'><select name='productTypeId' class='form-control' onchange='showType(this)' id='type0'><option value='-1'>-- 请选择父类 --</option>";
						for (var type in data) {
							content += "<option value='"+data[type].id+"'>" + data[type].name + "</option>";
						}
						content += "</select></div>";
						$("#types").append(content);
					}
				}, "json");
			};
			
			function showOption(PropertyId) {
				var content = "";
				$.get("option/productOptionServlet",{
					method : "showOption",
						id : PropertyId
				}, function (data) {
					if (data != null && data.length > 0) {
						content += "<select name='option' class='form-control'><option value='-1'>-- 请选择选项 --</option>";
						for ( var option in data) {
							content += "<option value='"+data[option].id+"'>" + data[option].name + "</option>";
						}
						content += "</select>";
					}
				}, "json");
				return content;
			};
			
			function showProperty() {
				$("#propertys").empty();
				var list = document.getElementsByName("productTypeId");
				var typeId = 0;
				for(var tab in list) {
					if(list[tab].value >= 0) {
						typeId = Math.max(typeId, list[tab].value);
					}
				}
				
				$.ajaxSetup({
					async : false
				});
				$.get ("property/productPropertyServlet",{
					method : "getProperty",
					id : typeId
				},
				function(data) {
					if (data != null && data.length > 0) {
						var content = "";
						var content2 = "";
						for ( var property in data) {
							content += "<label class='control-label'>" + data[property].name + "</label>";
							content2 = showOption(data[property].id);
							content += content2;
						}
						content += "";
						$("#propertys").append(content);
					}
				}, "json");
			};
		</script>
		
		<script type="text/javascript">
			$().ready(
				showType(null)
			);
		</script>
	</head>
	<body>
		<div class="container">
			<h1 class="text-center text-danger">添加商品</h1>
			<form enctype="multipart/form-data" id="myForm" role="form" action="productServlet?method=add" method="post">
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">商品名称：</label>
					<div class="col-md-10">
						<input type="text" class="form-control" name="name" id="name" value="${productBean.name}"/>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">商品图片：</label>
					<div class="col-md-10">
						<input type="file" class="form-control" name="upPic"/>
					</div>
				</div>
				<div class="form-group col-md-12" id="types">
					<label class="col-sm-2 control-label" for="name">分类：</label>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">商品原价：</label>
					<div class="col-md-4">
						<input type="text" class="form-control" name="originalPrice" value="${productBean.originalPrice}"/>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">商品现价：</label>
					<div class="col-md-4">
						<input type="text" class="form-control" name="price" value="${productBean.price}"/>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">商品总量：</label>
					<div class="col-md-10">
						<input type="text" class="form-control" name="number" value="${productBean.number}"/>
					</div>
				</div>
				<div class="form-group col-md-12">
					<div class="col-md-offset-2 col-md-10">
						<button type="button" id="but" class="btn btn-primary btn-sm" onclick="showProperty()">添加属性</button>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">属性：</label>
					<div class="form-group col-sm-10" id="propertys"></div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" for="name">商品介绍：</label>
					<div class="col-md-10" style="width: 800px; margin: 20px auto 40px;">
						<textarea name="intro" id="myEditor">${productBean.intro}</textarea>
					</div>
				</div>
				<input type="hidden" name="id" value="${productBean.id}"/>
				<div class="form-group col-md-12">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" id="subt" class="btn btn-primary btn-sm">提交</button>
					</div>
				</div>
			</form>
			<script type="text/javascript">
				var editor = new UE.ui.Editor();
				editor.render("myEditor");
			</script>
		</div>
	</body>
</html>