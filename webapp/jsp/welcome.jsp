<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
		<center><h1 style="color: #0068b7;">欢迎使用<%=Property.SYSTEM_NAME %></h1></center>
		<c:if test="${sessionScope.rightsMap==null }">
		<center><h3 style="color: #ff6800;">您还没有被赋予权限，请联系<%=Property.SYSTEM_NAME %>管理员</h3></center>
		</c:if>
		<ul>
		<li>1、基础框架</li>
		<li>2、模块灵活配置</li>
		<li>3、权限</li>
		<li>4、操作日志</li>
		<li>5、公用组件</li>
		<li>6、文章;频道->栏目->类别</li>
		<li>7、产品</li>
		<li>8、工作流？</li>
		<li>文章处增加作者字段AUTH	2013-12-30</li>
		<li>自定义页面增加页面发布路径CUSTOM_PATH	2014-1-14</li>
		<li>系统管理增加地域	2014-3-1</li>
		<li>9、商城？</li>
		</ul>
</body>
</html>