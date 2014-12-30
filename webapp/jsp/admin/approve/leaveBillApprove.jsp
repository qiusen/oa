<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>请假单办理</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
var resultListData = {${requestScope.json}} ;

var manager, g;



$(f_initGrid);
function f_initGrid(){
    
    g = manager = $("#maingrid").ligerGrid({
    	title:'批注',
        columns: [
        { display: 'ID', name: 'id', width: '15%', type: 'text', frozen: true },
        { display: '批注人', name: 'userId',type: 'text' , width: '20%'},
        { display: '批注时间', name: 'time',type: 'text' , width: '20%'},
        { display: '批注信息', name: 'message',type: 'text' , width: '30%'}
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
        width: '70%',height: '40%',
        onReload: function() {
        	search();
        }//,
       
        //isScroll: false, 
        
                                 
    });

    
}
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='workflowApproveAction.${actionExt}';    
}
function completeClick(cv){
	document.getElementById("cv").value=cv;
	document.getElementById("leaveBillForm").submit();
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
<form name="leaveBillForm" id="leaveBillForm" method="post" action="workflowApproveAction!approveComplete.${actionExt}" onsubmit="return checkForm();">
<input type="hidden" id="bid" name="bid" value="${requestScope.leaveBill.id}"/>
<input type="hidden" id="taskId" name="taskId" value="${requestScope.task.id}"/>
<input type="hidden" id="cv" name="cv" />
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
        <td align="right" class="l-table-edit-td"><b>假期申请</b></td>
        <td align="left" class="l-table-edit-td"></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">申请人EMAIL：</td>
        <td align="left" class="l-table-edit-td"><input type="text" ltype="text" value="${requestScope.leaveBill.email}" readonly="readonly"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input type="text" ltype="text" value="${requestScope.leaveBill.reason}" readonly="readonly"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">类型：</td>
        <td align="left" class="l-table-edit-td">
        <c:if test="${requestScope.leaveBill.type==1}"><input type="text" ltype="text" value="病假" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==2}"><input type="text" ltype="text" value="事假" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==3}"><input type="text" ltype="text" value="年假" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==4}"><input type="text" ltype="text" value="调休" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==5}"><input type="text" ltype="text" value="婚假" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==6}"><input type="text" ltype="text" value="产检假" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==7}"><input type="text" ltype="text" value="陪产假" readonly="readonly"/></c:if>
        <c:if test="${requestScope.leaveBill.type==8}"><input type="text" ltype="text" value="丧假" readonly="readonly"/></c:if>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">描述：</td>
        <td align="left" class="l-table-edit-td">
        <textarea cols="10" rows="3" style="width:420px;height:80px;" readonly="readonly">${requestScope.leaveBill.description}</textarea>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">开始时间：</td>
        <td align="left" class="l-table-edit-td"><input type="text" ltype="text" value="${requestScope.leaveBill.begintime}" readonly="readonly"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">结束时间：</td>
        <td align="left" class="l-table-edit-td"><input type="text" ltype="text" value="${requestScope.leaveBill.endtime}" readonly="readonly"/></td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<hr/>
    	</td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">批注：</td>
        <td align="left" class="l-table-edit-td">
        <textarea name="message" id="message" cols="10" rows="3" style="width:420px;height:80px;" ></textarea>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<input type="button" value="同意" class="l-button l-button-submit" onclick="javascript:completeClick('y');"/> 
    	<input type="button" value="驳回" class="l-button l-button-submit" onclick="javascript:completeClick('n');"/> 
	<input type="button" value="取消" class="l-button l-button-reset" onclick="javascript:cancleClick();"/>
    	</td>
    </tr>
    
</table>
    <div id="maingrid" style="margin:20px; padding:0"></div>
   

  <div style="display:none;">
</div>
</form>
</body>
</html>