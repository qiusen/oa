<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>请假单 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	var bd = document.getElementById("beginDate").value;
	var bh = document.getElementById("beginHour").value;
	var bm = document.getElementById("beginMinute").value;
	
	var ed = document.getElementById("endDate").value;
	var eh = document.getElementById("endHour").value;
	var em = document.getElementById("endMinute").value;
	
	
	if(bd=='' || ed==''){
		$.ligerDialog.error('日期必选');
		return false;	
	}
	
	var beginTime = bd + " " + bh + ":" + bm + ":00";
	var endTime = ed + " " + eh + ":" + em + ":00";
	
	//alert( beginTime +" " + endTime);
	//beginTime = ; 
	var b = new Date(Date.parse(beginTime.replace(/-/g, "/"))); 
	
	//endTime = ; 
	var e = new Date(Date.parse(endTime.replace(/-/g, "/"))); 
	
	//alert(b.getTime() + " " + e.getTime());
	if(b.getTime() >= e.getTime()){
		$.ligerDialog.error('结束时间应大于开始时间');
		return false;	
	}
	
	document.getElementById("begintime").value=beginTime;
	document.getElementById("endtime").value=endTime;
	return true;
}
function cancleClick(){
	self.location='leaveBillWorkflowAction.${actionExt}';   
}

$(function (){
 
	$("#beginDate").ligerDateEditor({ 
		showTime: false, 
		label: '开始时间', 
		labelWidth: 100, labelAlign: 'left' 
	});
	
	$("#endDate").ligerDateEditor({ 
		showTime: false, 
		label: '结束时间', 
		labelWidth: 100, labelAlign: 'left'
	});
});
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
<form name="leaveBillForm" id="leaveBillForm" method="post" action="leaveBillWorkflowAction!editSave.${actionExt}" onsubmit="return checkForm();">
<input name="begintime" type="hidden" id="begintime" />
<input name="endtime" type="hidden" id="endtime" />
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="leaveBill.id" name="leaveBill.id" value="${requestScope.leaveBill.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">原因：</td>
        <td align="left" class="l-table-edit-td"><input name="leaveBill.reason" type="text" id="leaveBill.reason" ltype="text" value="${requestScope.leaveBill.reason}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">类型：</td>
        <td align="left" class="l-table-edit-td">
        <select name="leaveBill.type" id="leaveBill.type">
	    		<option value="1" <c:if test="${requestScope.leaveBill.type==1}"></c:if>>病假</option>
	    		<option value="2" <c:if test="${requestScope.leaveBill.type==2}"></c:if>>事假</option>
	    		<option value="3" <c:if test="${requestScope.leaveBill.type==3}"></c:if>>年假</option>
	    		<option value="4" <c:if test="${requestScope.leaveBill.type==4}"></c:if>>调休</option>
	    		<option value="5" <c:if test="${requestScope.leaveBill.type==5}"></c:if>>婚假</option>
	    		<option value="6" <c:if test="${requestScope.leaveBill.type==6}"></c:if>>产检假</option>
	    		<option value="7" <c:if test="${requestScope.leaveBill.type==7}"></c:if>>陪产假</option>
	    		<option value="8" <c:if test="${requestScope.leaveBill.type==8}"></c:if>>丧假</option>
	    	</select>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">描述：</td>
        <td align="left" class="l-table-edit-td" colspan="2">
        <textarea name="leaveBill.description" id="leaveBill.description" cols="10" rows="3" style="width:420px;height:80px;">${requestScope.leaveBill.description}</textarea>
        </td>
    </tr>
    <tr>
	    	<td align="left" class="l-table-edit-td" colspan="2">
	    	<input name="beginDate" type="text" id="beginDate" style="float:left;" value="${requestScope.beginDate }"/>
	    	</td>
	    	<td align="left">
	    	<select name="beginHour" id="beginHour" style="float:left;">
	    		<option value="01" <c:if test="${requestScope.beginHour=='01' }">selected="selected"</c:if>>01</option>
	    		<option value="02" <c:if test="${requestScope.beginHour=='02' }">selected="selected"</c:if>>02</option>
	  		<option value="03" <c:if test="${requestScope.beginHour=='03' }">selected="selected"</c:if>>03</option>
	    		<option value="04" <c:if test="${requestScope.beginHour=='04' }">selected="selected"</c:if>>04</option>
	    		<option value="05" <c:if test="${requestScope.beginHour=='05' }">selected="selected"</c:if>>05</option>
	    		<option value="06" <c:if test="${requestScope.beginHour=='06' }">selected="selected"</c:if>>06</option>
	    		<option value="07" <c:if test="${requestScope.beginHour=='07' }">selected="selected"</c:if>>07</option>
	    		<option value="08" <c:if test="${requestScope.beginHour=='08' }">selected="selected"</c:if>>08</option>
	    		<option value="09" <c:if test="${requestScope.beginHour=='09' }">selected="selected"</c:if>>09</option>
	    		<option value="10" <c:if test="${requestScope.beginHour=='10' }">selected="selected"</c:if>>10</option>
	    		<option value="11" <c:if test="${requestScope.beginHour=='11' }">selected="selected"</c:if>>11</option>
	    		<option value="12" <c:if test="${requestScope.beginHour=='12' }">selected="selected"</c:if>>12</option>
	  		<option value="13" <c:if test="${requestScope.beginHour=='13' }">selected="selected"</c:if>>13</option>
	    		<option value="14" <c:if test="${requestScope.beginHour=='14' }">selected="selected"</c:if>>14</option>
	    		<option value="15" <c:if test="${requestScope.beginHour=='15' }">selected="selected"</c:if>>15</option>
	    		<option value="16" <c:if test="${requestScope.beginHour=='16' }">selected="selected"</c:if>>16</option>
	    		<option value="17" <c:if test="${requestScope.beginHour=='17' }">selected="selected"</c:if>>17</option>
	    		<option value="18" <c:if test="${requestScope.beginHour=='18' }">selected="selected"</c:if>>18</option>
	    		<option value="19" <c:if test="${requestScope.beginHour=='19' }">selected="selected"</c:if>>19</option>
	    		<option value="20" <c:if test="${requestScope.beginHour=='20' }">selected="selected"</c:if>>20</option>
	    		<option value="21" <c:if test="${requestScope.beginHour=='21' }">selected="selected"</c:if>>21</option>
	    		<option value="22" <c:if test="${requestScope.beginHour=='22' }">selected="selected"</c:if>>22</option>
	    		<option value="23" <c:if test="${requestScope.beginHour=='23' }">selected="selected"</c:if>>23</option>
	    		<option value="24" <c:if test="${requestScope.beginHour=='24' }">selected="selected"</c:if>>24</option>
	    	</select>
	    	<select name="beginMinute" id="beginMinute" style="float:left;">
	    		<option value="00" <c:if test="${requestScope.beginMinute=='00' }">selected="selected"</c:if>>00</option>
	    		<option value="30" <c:if test="${requestScope.beginMinute=='30' }">selected="selected"</c:if>>30</option>
	    	</select>
	    	</td>
    </tr>
    <tr>
	    	<td align="left" class="l-table-edit-td" colspan="2">
	    	<input name="endDate" type="text" id="endDate" ltype="text" value="${requestScope.endDate }"/>
	    	</td>
	    	<td align="left">
	    	<select name="endHour" id="endHour" >
	    		<option value="01" <c:if test="${requestScope.endHour=='01' }">selected="selected"</c:if>>01</option>
	    		<option value="02" <c:if test="${requestScope.endHour=='02' }">selected="selected"</c:if>>02</option>
	  		<option value="03" <c:if test="${requestScope.endHour=='03' }">selected="selected"</c:if>>03</option>
	    		<option value="04" <c:if test="${requestScope.endHour=='04' }">selected="selected"</c:if>>04</option>
	    		<option value="05" <c:if test="${requestScope.endHour=='05' }">selected="selected"</c:if>>05</option>
	    		<option value="06" <c:if test="${requestScope.endHour=='06' }">selected="selected"</c:if>>06</option>
	    		<option value="07" <c:if test="${requestScope.endHour=='07' }">selected="selected"</c:if>>07</option>
	    		<option value="08" <c:if test="${requestScope.endHour=='08' }">selected="selected"</c:if>>08</option>
	    		<option value="09" <c:if test="${requestScope.endHour=='09' }">selected="selected"</c:if>>09</option>
	    		<option value="10" <c:if test="${requestScope.endHour=='10' }">selected="selected"</c:if>>10</option>
	    		<option value="11" <c:if test="${requestScope.endHour=='11' }">selected="selected"</c:if>>11</option>
	    		<option value="12" <c:if test="${requestScope.endHour=='12' }">selected="selected"</c:if>>12</option>
	  		<option value="13" <c:if test="${requestScope.endHour=='13' }">selected="selected"</c:if>>13</option>
	    		<option value="14" <c:if test="${requestScope.endHour=='14' }">selected="selected"</c:if>>14</option>
	    		<option value="15" <c:if test="${requestScope.endHour=='15' }">selected="selected"</c:if>>15</option>
	    		<option value="16" <c:if test="${requestScope.endHour=='16' }">selected="selected"</c:if>>16</option>
	    		<option value="17" <c:if test="${requestScope.endHour=='17' }">selected="selected"</c:if>>17</option>
	    		<option value="18" <c:if test="${requestScope.endHour=='18' }">selected="selected"</c:if>>18</option>
	    		<option value="19" <c:if test="${requestScope.endHour=='19' }">selected="selected"</c:if>>19</option>
	    		<option value="20" <c:if test="${requestScope.endHour=='20' }">selected="selected"</c:if>>20</option>
	    		<option value="21" <c:if test="${requestScope.endHour=='21' }">selected="selected"</c:if>>21</option>
	    		<option value="22" <c:if test="${requestScope.endHour=='22' }">selected="selected"</c:if>>22</option>
	    		<option value="23" <c:if test="${requestScope.endHour=='23' }">selected="selected"</c:if>>23</option>
	    		<option value="24" <c:if test="${requestScope.endHour=='24' }">selected="selected"</c:if>>24</option>
	    	</select>
	    	<select name="endMinute" id="endMinute" >
	    		<option value="00" <c:if test="${requestScope.endMinute=='00' }">selected="selected"</c:if>>00</option>
	    		<option value="30" <c:if test="${requestScope.endMinute=='30' }">selected="selected"</c:if>>30</option>
	    	</select>
	    	</td>
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