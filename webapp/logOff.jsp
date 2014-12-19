<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title><%=Property.SYSTEM_NAME %></title>
<%@ include file="/jsp/common/meta.jsp"%>
<link rel="stylesheet" href="${base }/css/custom.css"/>
<script>
$(function (){
	$.ligerDialog.success('成功退出');
	window.setTimeout(redirectlogin,3000); 
});
function redirectlogin(){
	window.location="<%=Property.MANAGERCENTER_URL%>";
}
</script>
</head>
<body>
</body>
</html>