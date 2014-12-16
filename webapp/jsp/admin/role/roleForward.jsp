<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色 列表</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
<c:if test="${requestScope.errCode=='hasManager'}">
alert("该角色下有管理员，请先删除管理员！");
window.location="${base}/admin/role/roleAction.${actionExt}";
</c:if>
</script>
</head>
<body style="padding:6px; overflow:hidden;">
</body>
</html>