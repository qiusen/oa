<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>请假单 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script src="${base}/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
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
<form name="leaveBillForm" id="leaveBillForm" method="post" action="leaveBillWorkflowAction!addSave.${actionExt}" onsubmit="return checkForm();">
<input name="begintime" type="hidden" id="begintime" />
<input name="endtime" type="hidden" id="endtime" />
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
    	<td align="right" class="l-table-edit-td">原因：</td>
    	<td align="left" class="l-table-edit-td"><input name="leaveBill.reason" type="text" id="leaveBill.reason" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">类型：</td>
    	<td align="left" class="l-table-edit-td">
    	<select name="leaveBill.type" id="leaveBill.type">
    		<option value="1">病假</option>
    		<option value="2">事假</option>
    		<option value="3">年假</option>
    		<option value="4">调休</option>
    		<option value="5">婚假</option>
    		<option value="6">产检假</option>
    		<option value="7">陪产假</option>
    		<option value="8">丧假</option>
    	</select>
    </td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">描述：</td>
    	<td align="left" class="l-table-edit-td" colspan="2">
    	<textarea name="leaveBill.description" id="leaveBill.description" cols="10" rows="3" style="width:420px;height:80px;"></textarea></td>
    </tr>
    <tr>
    	<td align="left" class="l-table-edit-td" colspan="2">
    	<input name="beginDate" type="text" id="beginDate" style="float:left;"/>
    	</td>
    	<td align="left">
    	<select name="beginHour" id="beginHour" style="float:left;">
    	<!-- 早9晚6 -->
    		<!-- 
    		<option value="01">01</option>
    		<option value="02">02</option>
  		<option value="03">03</option>
    		<option value="04">04</option>
    		<option value="05">05</option>
    		<option value="06">06</option>
    		<option value="07">07</option>
    		<option value="08">08</option>
    		 -->
    		<option value="09">09</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="12">12</option>
  		<option value="13">13</option>
    		<option value="14">14</option>
    		<option value="15">15</option>
    		<option value="16">16</option>
    		<option value="17">17</option>
    		<option value="18">18</option>
    		<!--
    		<option value="19">19</option>
    		<option value="20">20</option>
    		<option value="21">21</option>
    		<option value="22">22</option>
    		<option value="23">23</option>
    		<option value="24">24</option>
    		 -->
    	</select>
    	<select name="beginMinute" id="beginMinute" style="float:left;">
    		<option value="00">00</option>
    		<option value="30">30</option>
    	</select>
    	</td>
    </tr>
    <tr>
    	<td align="left" class="l-table-edit-td" colspan="2">
    	<input name="endDate" type="text" id="endDate" ltype="text" />
    	</td>
    	<td align="left">
    	<select name="endHour" id="endHour" >
    		<!--
    		<option value="01">01</option>
    		<option value="02">02</option>
  		<option value="03">03</option>
    		<option value="04">04</option>
    		<option value="05">05</option>
    		<option value="06">06</option>
    		<option value="07">07</option>
    		<option value="08">08</option>
    		 -->
    		<option value="09">09</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="12">12</option>
  		<option value="13">13</option>
    		<option value="14">14</option>
    		<option value="15">15</option>
    		<option value="16">16</option>
    		<option value="17">17</option>
    		<option value="18">18</option>
    		<!--
    		<option value="19">19</option>
    		<option value="20">20</option>
    		<option value="21">21</option>
    		<option value="22">22</option>
    		<option value="23">23</option>
    		<option value="24">24</option>
    		 -->
    	</select>
    	<select name="endMinute" id="endMinute" >
    		<option value="00">00</option>
    		<option value="30">30</option>
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