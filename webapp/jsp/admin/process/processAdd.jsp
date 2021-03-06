<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程部署</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script type='text/javascript' src='${base}/dwr/interface/catalogDwr.js'></script>
<script type='text/javascript' src='${base}/dwr/engine.js'></script>
<script language="javascript">
function checkForm(){
	var modulename = document.getElementById("module.modulename").value;
	if(modulename==null || modulename==''){
		alert("请输入模块名称！");
		return false;
	}

	var moduleurl = document.getElementById("module.moduleurl").value;
	if(moduleurl==null || moduleurl==''){
		alert("请输入模块URL！");
		return false;
	}
	return true;
}

function changeCatalog(v){
	catalogDwr.selectCatalogByMenu(v, callbackCatalog)
}
var callbackCatalog = function(catalogList){
	var s= document.getElementById("module.catalogId");
	clearSelect(s);	//清空
	if(catalogList!= null){
		//alert(s.options.length);
		if(s!=null){
			for(var i=0;i<catalogList.length;i++){
				s.options[i] = new Option(catalogList[i].catalogname, catalogList[i].id);
			}
		}
	}
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
<form name="processModelForm" id="processModelForm" method="post" action="processAction!addSave.${actionExt}" onsubmit="return checkForm();" enctype ="multipart/form-data">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
        <td align="right" class="l-table-edit-td">流程名称：</td>
        <td align="left" class="l-table-edit-td"><input name="processModel.name" type="text" id="processModel.name" ltype="text" /></td>
        <td align="left"><font color="red">*</font></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">流程文件：</td>
        <td align="left" class="l-table-edit-td"><input name="processModel.processZipFile" type="file" id="processModel.processZipFile" /></td>
        <td align="left"><font color="red">*</font></td>
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