<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>需办理的任务列表</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
var resultListData = {${requestScope.json}} ;

var manager, g;



$(f_initGrid);
function f_initGrid(){
    
    g = manager = $("#maingrid").ligerGrid({
    	title:'需办理的任务',
        columns: [
        { display: '任务ID', name: 'id', width: '15%', type: 'text', frozen: true },
        { display: '任务名称', name: 'name',type: 'text' , width: '20%'},
        { display: '创建时间', name: 'createTime',type: 'text' , width: '20%'},
        { display: '办理人', name: 'assignee',type: 'text' , width: '20%'},
        { display: '操作', type: 'text' , width: '20%',render: function (rowdata, rowindex, value)
        {
        	var r = "<a href='javascript:approve("+rowdata.id+")'>办理</a>";
        		r += " <a href='javascript:viewCurrentPng("+rowdata.id+")'>查看流程图</a>";
            return r;
        }  }
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
        width: '90%',height: '90%',
        onReload: function() {
        	search();
        }//,
       
        //isScroll: false, 
        
                                 
    });

    
}
function search(){
	document.forms[0].submit();
}

function approve(taskId){
	window.location="${base}/admin/approve/workflowApproveAction!approve.${actionExt}?taskId=" + taskId;
}

function viewCurrentPng(taskId){
	window.open("${base}/admin/approve/workflowApproveAction!viewCurrentPng.${actionExt}?taskId=" + taskId);
}

</script>
<style type="text/css">
        .l-case-title{font-weight:bold; margin-top:20px;margin-bottom:20px;}
     </style>
</head>
<body style="padding:6px; overflow:hidden;">
<form name="moduleForm" id="moduleForm" method="post" action="moduleAction.${actionExt}" >

<div id="maingrid" style="margin:0; padding:0"></div>
   

  <div style="display:none;">
</div>
 
</form>
</body>

</html>