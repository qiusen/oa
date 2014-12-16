<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	var rolename = document.getElementById("role.rolename").value;
	if(rolename==null || rolename==''){
		alert("请输入角色名称！");
		return false;
	}
	return true;
}
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
</head>
<body>
<form name="roleForm" id="roleForm" method="post" action="roleAction!editSave.${actionExt}" onsubmit="return checkForm();">
<input type="hidden" id="role.id" name="role.id" value="${requestScope.role.id}"/>
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
        <td align="right" class="l-table-edit-td">角色名称：</td>
        <td align="left" class="l-table-edit-td"><input name="role.rolename" type="text" id="role.rolename" ltype="text" value="${requestScope.role.rolename}"/></td>
        <td align="left"><font color="red">*</font></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">角色状态：</td>
        <td align="left" class="l-table-edit-td">
        <select name="role.status" id="role.status" >
        <option value="1" <c:if test="${requestScope.role.status==1}">selected="true"</c:if>>有效</option>
        <option value="0" <c:if test="${requestScope.role.status==0}">selected="true"</c:if>>无效</option>
        </select>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<input type="submit" value="保存" id="Button1" class="l-button l-button-submit" /> 
		<input type="button" value="取消" class="l-button l-button-reset" onclick="javascript: history.back(-1);"/>
    	</td>
    </tr>
</table>
</form>
</body>
</html>