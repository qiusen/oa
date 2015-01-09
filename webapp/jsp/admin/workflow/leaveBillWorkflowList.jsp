<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>请假单 列表</title>
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
    	title:'请假单列表',
        columns: [
        //{ display: '主键', name: 'id', width: 50, type: 'int', frozen: true },
		
		//{ display: '申请人EMAIL', name: 'email',type: 'text' , width: '15%'},
		{ display: '原因', name: 'reason',type: 'text' , width: '15%'},
		{ display: '类型', name: 'type',type: 'text' , width: '10%',render: function (rowdata, rowindex, value)
        {
        	var h = "-";
            if(rowdata.type==1){
                h = "病假";
            }
            if(rowdata.type==2){
                h = "事假";
            }
            if(rowdata.type==3){
                h = "年假";
            }
            if(rowdata.type==4){
                h = "调休";
            }
            if(rowdata.type==5){
                h = "产检假";
            }
            if(rowdata.type==6){
                h = "陪产假";
            }
            if(rowdata.type==7){
                h = "病假";
            }
            if(rowdata.type==8){
                h = "丧假";
            }
            return h;
        } },
		{ display: '描述', name: 'description',type: 'text' , width: '15%'},
		{ display: '状态', name: 'status',type: 'text' , width: '10%',render: function (rowdata, rowindex, value)
        {
        	var h = "-";
            if(rowdata.status==0){
                h = "未开始";
            }
            if(rowdata.status==1){
                h = "审批中";
            }
            if(rowdata.status==2){
                h = "已完成";
            }
            if(rowdata.status==3){
                h = "已取消";
            }
            
            return h;
        } },
		{ display: '开始时间', name: 'begintime',type: 'text' , width: '15%'},
		{ display: '结束时间', name: 'endtime',type: 'text' , width: '15%'},
		//{ display: '创建人', name: 'creator',type: 'text' , width: '15%'},
		//{ display: '创建时间', name: 'createtime',type: 'text' , width: '15%'},
		//{ display: '修改人', name: 'updator',type: 'text' , width: '15%'},
		//{ display: '修改时间', name: 'updatetime',type: 'text' , width: '15%'}
		{ display: '操作', type: 'text' , width: '15%',render: function (rowdata, rowindex, value)
        {
        		var h = "-";
            if(rowdata.status==0){
                h = "<a href='${base}/admin/workflow/leaveBillWorkflowAction!start.${actionExt}?id="+rowdata.id+"'>提交</a> <a href='${base}/admin/workflow/leaveBillWorkflowAction!edit.${actionExt}?id="+rowdata.id+"'>修改</a> <a href='javascript:deleteData("+rowdata.id+")'>删除</a>";
            }
            if(rowdata.status==1){
                h = "<a href='${base}/admin/workflow/leaveBillWorkflowAction!viewCurrentPng.${actionExt}?businessKey=leaveBill-"+rowdata.id+"' target='_blank'>查看流程图</a> <a href='${base}/admin/workflow/leaveBillWorkflowAction!cancel.${actionExt}?businessKey=leaveBill-"+rowdata.id+"'>取消流程</a>";
            }
            if(rowdata.status==2){
                h = "<a href='${base}/admin/workflow/leaveBillWorkflowAction!viewComments.${actionExt}?businessKey=leaveBill-"+rowdata.id+"' >查看审核记录</a>";
            }
            if(rowdata.status==3){
                h = "<a href='${base}/admin/workflow/leaveBillWorkflowAction!viewComments.${actionExt}?businessKey=leaveBill-"+rowdata.id+"' >查看审核记录</a>";
            }
            
            return h;
        } }
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
        
        toolbar: { items: [{ text: '申请', value:'add', click: itemclick, icon: 'add', img: '${base}/ligerUI/skins/icons/add.gif' }//,
                           //{ text: '修改', value:'edit', click: itemclick, icon: 'edit', img: '${base}/ligerUI/skins/icons/edit.gif' },
                           //{ text: '删除', value:'delete', click: itemclick, icon: 'delete', img: '${base}/ligerUI/skins/icons/delete.gif' }
                            ] }

        
                                 
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
function deleteData(id){
    $.ligerDialog.confirm('确认删除此请假单？', function (yes) {
            if(yes==true){
            	window.location="${base}/admin/workflow/leaveBillWorkflowAction!delete.${actionExt}?id=" + id;
            }
    });
}
function showData(id){
	var dialog=$.ligerDialog.open({ title:'查看', url: '${base}/admin//leaveBill/leaveBillAction!show.${actionExt}?id=' + id, height: 300, width: null, buttons: [
              { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
           ], isResize: true
          });                                                                 	
}

function itemclick(item){
	if(item.value=='add'){
		window.location="${base}/admin/workflow/leaveBillWorkflowAction!add.${actionExt}";
	}
	if(item.value=='edit'){
        var row = manager.getSelectedRow();
        if (!row) { $.ligerDialog.warn('请选择行'); return; }
            //alert(row.id);
		window.location="${base}/admin/workflow/leaveBillAction!edit.${actionExt}?id=" + row.id;
	}
	if(item.value=='delete'){
		var row = manager.getSelectedRow();
        if (!row) {$.ligerDialog.warn('请选择行'); return; }
		$.ligerDialog.confirm('确认删除模块 ' + row.email + ' 的信息？', function (yes) {
            if(yes==true){
            	window.location="${base}/admin/workflow/leaveBillAction!delete.${actionExt}?id=" + row.id;
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
<form name="leaveBillForm" id="leaveBillForm" method="post" action="leaveBillAction.${actionExt}" >
<input type="hidden" name="pageNo" id="pageNo" value="${requestScope.pageInfo.page}" />
<input type="hidden" name="pageSize" id="pageSize" value="${requestScope.pageInfo.pageSize}" />
    <div id="maingrid" style="margin:0; padding:0"></div>
   

  <div style="display:none;">
</div>
 
</form>
</body>
</html>