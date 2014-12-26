<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>请假单 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='leaveBillAction.${actionExt}';    
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
<form name="leaveBillForm" id="leaveBillForm" method="post" action="leaveBillAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="leaveBill.id" name="leaveBill.id" value="${requestScope.leaveBill.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">申请人EMAIL：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.email" type="text" id="leaveBill.email" ltype="text" value="${requestScope.leaveBill.email}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.reason" type="text" id="leaveBill.reason" ltype="text" value="${requestScope.leaveBill.reason}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">类型：1、病假；2、事假；3、年假；4、调休；5、婚假；6、产检假；7、陪产假；8、丧假：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.type" type="text" id="leaveBill.type" ltype="text" value="${requestScope.leaveBill.type}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">内容：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.description" type="text" id="leaveBill.description" ltype="text" value="${requestScope.leaveBill.description}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">状态：0、未开始；1、审批中；2、已完成：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.status" type="text" id="leaveBill.status" ltype="text" value="${requestScope.leaveBill.status}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">开始时间：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.begintime" type="text" id="leaveBill.begintime" ltype="text" value="${requestScope.leaveBill.begintime}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">结束时间：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.endtime" type="text" id="leaveBill.endtime" ltype="text" value="${requestScope.leaveBill.endtime}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">创建人：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.createuser" type="text" id="leaveBill.createuser" ltype="text" value="${requestScope.leaveBill.createuser}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">创建时间：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.createtime" type="text" id="leaveBill.createtime" ltype="text" value="${requestScope.leaveBill.createtime}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">修改人：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.updateuser" type="text" id="leaveBill.updateuser" ltype="text" value="${requestScope.leaveBill.updateuser}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">修改时间：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.updatetime" type="text" id="leaveBill.updatetime" ltype="text" value="${requestScope.leaveBill.updatetime}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<input type="submit" value="保存" id="Button1" class="l-button l-button-submit" /> 
	<input type="button" value="取消" class="l-button l-button-reset" onclick="javascript:cancleClick();"/>
    	</td>
    </tr>
</table>
</form>
</body>
</html>