<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程 列表</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
var dresultListData = {${requestScope.deploymentJson}} ;
var resultListData = {${requestScope.json}} ;

var dmanager, manager, dg, g;



$(f_initGrid);
function f_initGrid(){
	//部署表硌
	dg = dmanager = $("#deploymentmaingrid").ligerGrid({
    	title:'部署列表',
        columns: [
        { display: '部署ID', name: 'id', width: '15%', type: 'text', frozen: true },
        { display: '部署名称', name: 'name',type: 'text' , width: '25%'},
        { display: '部署时间', name: 'deploymentTime',type: 'text' , width: '15%'},
        { display: '操作',type: 'text' , width: '10%',render: function (rowdata, rowindex, value)
        {
        	var r = "<a href='javascript:deleteDeployment("+rowdata.id+",\""+rowdata.name+"\")'>删除</a>";
        	
            return r;
        }  }
        ],
        onSelectRow: function (rowdata, rowindex)
        {
            $("#txtrowindex").val(rowindex);
        },
        enabledEdit: true,clickToEdit:false, isScroll: true,
        rownumbers:true,
        data: dresultListData,
        record: resultListData.Total,
        usePager :false,
        width: '90%',height: '45%',
        onReload: function() {
        	search();
        },
       
        //isScroll: false, 
        
        toolbar: { items: [{ text: '增加', value:'add', click: itemclick, icon: 'add', img: '${base}/ligerUI/skins/icons/add.gif' }//,
                           //{ text: '修改', value:'edit', click: itemclick, icon: 'edit', img: '${base}/ligerUI/skins/icons/edit.gif' },
                           //{ text: '删除', value:'delete', click: itemclick, icon: 'delete', img: '${base}/ligerUI/skins/icons/delete.gif' } 
                           ] }

        
                                 
    });
    
    g = manager = $("#maingrid").ligerGrid({
    	title:'流程列表',
        columns: [
        { display: '流程ID', name: 'id', width: '15%', type: 'text', frozen: true },
        { display: '流程名称', name: 'name',type: 'text' , width: '25%'},
        { display: 'key', name: 'key',type: 'text' , width: '15%'},
        { display: '流程描述文件', name: 'fileName',type: 'text' , width: '10%'},
        { display: '流程描述图片', name: 'pngName',type: 'text' , width: '10%',render: function (rowdata, rowindex, value)
        {
        	var r = "<a target='_blank' href='${base}/admin/process/processAction!viewPng.${actionExt}?deploymentId=" + rowdata.deploymentId+"&pngName="+rowdata.pngName+"'>"+rowdata.pngName+"</a>";
        	
            return r;
        }  },
        { display: '版本version', name: 'version',type: 'text' , width: '10%'},
        { display: '部署ID', name: 'deploymentId',type: 'text' , width: '10%'}
        ],
        onSelectRow: function (rowdata, rowindex)
        {
            $("#txtrowindex").val(rowindex);
        },
        enabledEdit: true,clickToEdit:false, isScroll: true,
        rownumbers:true,
        data: resultListData,
        record: resultListData.Total,
        usePager :false,
        width: '90%',height: '50%',
        onReload: function() {
        	search();
        }//,
       
        //isScroll: false, 
        
                                 
    });

    
}
function search(){
	document.forms[0].submit();
}

function deleteDeployment(deploymentId, name){
	
	$.ligerDialog.confirm('确认删除部署 <font color="red">'+name+' 及其对应的流程</font>信息？', function (yes) {
            if(yes==true){
            //alert(deploymentId);
            	window.location="${base}/admin/process/processAction!delete.${actionExt}?id=" + deploymentId;
            }
        });
}

function itemclick(item)
{
	if(item.value=='add'){
		window.location="${base}/admin/process/processAction!add.${actionExt}";
	}
    
}


</script>
<style type="text/css">
        .l-case-title{font-weight:bold; margin-top:20px;margin-bottom:20px;}
     </style>
</head>
<body style="padding:6px; overflow:hidden;">
<form name="moduleForm" id="moduleForm" method="post" action="moduleAction.${actionExt}" >
<div id="searchbar">
   流程名称：<input id="module.modulename" type="text" name="module.modulename" value=""/>
    <input id="btnOK" type="submit" value="查询"/>
</div>
<div id="deploymentmaingrid" style="margin:0; padding:0"></div>
    <div id="maingrid" style="margin:0; padding:0"></div>
   

  <div style="display:none;">
</div>
 
</form>
</body>

</html>