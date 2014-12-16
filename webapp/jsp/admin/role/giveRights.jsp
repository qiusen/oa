<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	getChecked();
	return true;
}


var data = ${requestScope.data};

var treeManager = null;

//调用初始化页面方法
$(document).ready(function(){
	init();
});
//初始化方法
function init(){
    var tree = $("#tree1").ligerTree({  
    data:data, 
     idFieldName :'id',
     slide : false,
     parentIDFieldName :'pid'
     });

     treeManager = $("#tree1").ligerGetTreeManager();
     
     treeManager.expandAll();
    
}

function getChecked() {
    var notes = treeManager.getChecked();
    var text = ",";
    for (var i = 0; i < notes.length; i++) {
        if(notes[i].data.type==3){
        	text += notes[i].data.id + ",";
        }
        
    }
    
    document.getElementById("role.rights").value = text;
    //alert('选择的节点数：' + text);
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
<form name="roleForm" id="roleForm" method="post" action="roleAction!saveRights.${actionExt}" onsubmit="return checkForm();">
<input type="hidden" id="role.id" name="role.id" value="${requestScope.role.id}"/>
<input type="hidden" id="role.rights" name="role.rights" value="${requestScope.role.rights}"/>
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;width:550px;">
    <tr>
        <td align="right" class="l-table-edit-td">角色名称：</td>
        <td align="left" class="l-table-edit-td">${requestScope.role.rolename}</td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">角色状态：</td>
        <td align="left" class="l-table-edit-td"><c:if test="${requestScope.role.status==1}">有效</c:if>
        <c:if test="${requestScope.role.status==0}">无效</c:if>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">可操作模块：</td>
    	<td align="left" colspan="2">
    	<div style="width:300px; height:300px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
		<ul id="tree1"></ul>
		</div> 
    	
    	</td>
    </tr>
    <tr>
    	<td align="center" colspan="3">&nbsp;</td>
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