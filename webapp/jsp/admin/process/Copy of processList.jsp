<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程 列表</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
var resultListData = {${requestScope.json}} ;

var manager, g;


var pageSize = ${requestScope.pageInfo.pageSize};
var totalPage=${requestScope.pageInfo.totalPage};
var itemCount = resultListData.Total;
var pageNo =${requestScope.pageInfo.page};


$(f_initGrid);
function f_initGrid(){
    g = manager = $("#maingrid").ligerGrid({
    	title:'流程列表',
        columns: [
        { display: '流程ID', name: 'id', width: '20%', type: 'text', frozen: true },
        { display: '流程名称', name: 'name',type: 'text' , width: '25%'},
        { display: 'key', name: 'key',type: 'text' , width: '20%'},
        { display: '版本version', name: 'version',type: 'text' , width: '10%'},
        { display: '部署ID', name: 'deploymentId',type: 'text' , width: '15%'}
        ],
        onSelectRow: function (rowdata, rowindex)
        {
            $("#txtrowindex").val(rowindex);
        },
        enabledEdit: true,clickToEdit:false, isScroll: true,
        rownumbers:true,
        data: resultListData,
        record: resultListData.Total,
        //usePager :false,
        width: '90%',height: '90%',
        pageSize:pageSize,
        pageSizeOptions:[5,10,15,20],
        onReload: function() {
        	search();
        },
        onToFirst: function() {
			if(pageNo != 1) {
				document.getElementById("pageNo").value = 1;
				search();
			}
			return false;
        },
        onToPrev: function() {
        	if(pageNo > 1) {
        		document.getElementById("pageNo").value = pageNo - 1;
        		search();
        	}
			return false;
        },
        onToNext: function() {
        	if(pageNo < totalPage) {
        		document.getElementById("pageNo").value = pageNo +1;
        		search();
        	}
			return false;
        },
        onToLast: function() {
        	if(pageNo != totalPage) {
				document.getElementById("pageNo").value = totalPage;
        		search();
        	}
			return false;
        } ,
        //isScroll: false, 
        
        toolbar: { items: [{ text: '增加', value:'add', click: itemclick, icon: 'add', img: '${base}/ligerUI/skins/icons/add.gif' },
                           //{ text: '修改', value:'edit', click: itemclick, icon: 'edit', img: '${base}/ligerUI/skins/icons/edit.gif' },
                           { text: '删除', value:'delete', click: itemclick, icon: 'delete', img: '${base}/ligerUI/skins/icons/delete.gif' } ] }

        
                                 
    });

    $(".pcontrol input").val(pageNo);
   	$(".pcontrol input").css("width", ((totalPage + "").length * 7) + "px");
   	$(".pcontrol input").attr("maxlength", (totalPage + "").length);
   	$(".pcontrol input").attr("readonly", "readonly");
       $(".pcontrol span").html(totalPage);
       var start = 0;
       var end = 0;
       if(pageNo>0){
    	   start = (pageSize * (pageNo - 1) + 1);
    	   end = start + resultListData.Rows.length - 1;
       }
       $(".l-bar-text").html("显示记录从" + start + "到" + end + "，总数 " + itemCount + " 条");
       if (!itemCount)
       {
           $(".l-bar-btnfirst span,.l-bar-btnprev span,.l-bar-btnnext span,.l-bar-btnlast span").addClass("l-disabled");
       }
       if (pageNo == 1)
       {
           $(".l-bar-btnfirst span").addClass("l-disabled");
           $(".l-bar-btnprev span").addClass("l-disabled");
       }
       else if (pageNo > 1 && totalPage > 0)
       {
           $(".l-bar-btnfirst span").removeClass("l-disabled");
           $(".l-bar-btnprev span").removeClass("l-disabled");
       }
       if (pageNo == totalPage)
       {
           $(".l-bar-btnlast span").addClass("l-disabled");
           $(".l-bar-btnnext span").addClass("l-disabled");
       }
       else if (pageNo < totalPage && totalPage > 0)
       {
           $(".l-bar-btnlast span").removeClass("l-disabled");
           $(".l-bar-btnnext span").removeClass("l-disabled");
       }
    
    $(".l-bar-selectpagesize select").change(function() {
   		document.getElementById("pageSize").value = $(".l-bar-selectpagesize select").val();
   		document.getElementById("pageNo").value = 1;
   		search();
       }); 
}
function search(){
	document.forms[0].submit();
}

function itemclick(item)
{
	if(item.value=='add'){
		window.location="${base}/admin/process/processAction!add.${actionExt}";
	}
	if(item.value=='edit'){
        var row = manager.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
            //alert(row.id);
		window.location="${base}/admin/process/processAction!edit.${actionExt}?id=" + row.id;
	}
	if(item.value=='delete'){
		var row = manager.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
		$.ligerDialog.confirm('确认删除模块 ' + row.name + ' 的信息？', function (yes) {
            if(yes==true){
            	window.location="${base}/admin/process/processAction!delete.${actionExt}?id=" + row.id;
            }
        });
	}
    
}


</script>
<style type="text/css">
        .l-case-title{font-weight:bold; margin-top:20px;margin-bottom:20px;}
     </style>
</head>
<body style="padding:6px; overflow:hidden;">
<form name="moduleForm" id="moduleForm" method="post" action="moduleAction.${actionExt}" >
<input type="hidden" name="pageNo" id="pageNo" value="${requestScope.pageInfo.page}" />
<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInfo.pageSize}" />
<div id="searchbar">
   流程名称：<input id="module.modulename" type="text" name="module.modulename" value=""/>
    <input id="btnOK" type="submit" value="查询"/>
</div>
    <div id="maingrid" style="margin:0; padding:0"></div>
   

  <div style="display:none;">
</div>
 
</form>
</body>

</html>