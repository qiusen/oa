<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>请假单 列表</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function cancleClick(){
	self.location='leaveBillWorkflowAction.${actionExt}';    
}
var resultListData = {${requestScope.json}} ;

var manager, g;


$(f_initGrid);
function f_initGrid(){
    g = manager = $("#maingrid").ligerGrid({
    	title:'请假单列表',
        columns: [
        //{ display: '主键', name: 'id', width: 50, type: 'int', frozen: true },
		
		{ display: '批注人', name: 'userId',type: 'text' , width: '20%'},
		{ display: '批注时间', name: 'time',type: 'text' , width: '20%'},
		
		{ display: '批注信息', name: 'message',type: 'text' , width: '35%'}
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
        width: '70%',height: '30%'
    });
    
}


</script>
<style type="text/css">
.l-case-title{font-weight:bold; margin-top:20px;margin-bottom:20px;}
</style>
</head>
<body style="padding:6px; overflow:hidden;">
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
        <td align="left" class="l-table-edit-td"><input type="text" ltype="text" value="<fmt:formatDate value="${requestScope.leaveBill.begintime}" pattern="yyyy-MM-dd hh:mm"/>" readonly="readonly"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">结束时间：</td>
        <td align="left" class="l-table-edit-td"><input type="text" ltype="text" value="<fmt:formatDate value="${requestScope.leaveBill.endtime}" pattern="yyyy-MM-dd hh:mm"/>" readonly="readonly"/></td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<hr/>
    	</td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
	<input type="button" value="返回" class="l-button l-button-reset" onclick="javascript:cancleClick();"/>
    	</td>
    </tr>
    
</table>
<div id="maingrid" style="margin:20px; padding:0"></div>
   

  <div style="display:none;">
</div>
</body>
</html>