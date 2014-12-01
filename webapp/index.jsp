<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title><%=Property.SYSTEM_NAME %></title>
<%@ include file="/jsp/common/meta.jsp"%>
<link rel="stylesheet" href="${base }/css/custom.css"/>
<script>
function changeCheckCode(){
	var checkCodeImg = document.getElementById("checkCodeImg");
	checkCodeImg.src="${base }/getcheckimage?"+Math.random();
}
$(function (){
	$("#loginname").focus();
});

function onsubmit(){
	document.getElementById("loginform").submit();
}
</script>
    <!--[if IE 6]>
<script src="public/js/DD_belatedPNG_0.0.8a-min.js"></script>
<script type="text/javascript">
    DD_belatedPNG.fix('div');
</script>
<![endif]-->
</head>
<body>
    <div class="Box">
        <div class="BoxL">
            <div class="BoxLT">Welcome</div>
            <div class="BoxLA">
            </div>
        </div>
        
        <div class="BoxR">
            <div class="BoxRCon">
            <form id="loginform" action="${base }/login.${actionExt}" method="post">
                <div class="fishT"><%=Property.SYSTEM_NAME %>登录</div>
                <div class="line1 MT30">
                    <i class=""></i>
                    <div class="">用户名</div>
                    <span class=""><input type="text" name="username" id="username" /></span>
                </div>
                <div class="line1 MT10 mod">
                    <i class=""></i>
                    <div class="">密码</div>
                    <span class=""><input type="password" id="password" name="password" /></span>
                </div>

                <div class="line1 MT10 mod">
                    <i class="myhide"></i>
                    <div class="">验证码</div>
                    <b class="lala"><input type="text" value="1111" id='checkCode' name='checkCode' maxlength="4" /></b>
                    <a href="#" onclick="changeCheckCode()"><img alt="点击刷新" src="${base }/getcheckimage" id="checkCodeImg" border="0" style="vertical-align: middle;margin-left:5px;"/></a>
                </div>
                <div class="line1 MT10 mod" style="text-align: center;">
                <font color="red">${errorStr}</font>
                </div>
                <a href="javascript:onsubmit();" style="color:#FFF;"><div class="btn MT30" >登录</div></a>
                </form>
            </div>
        </div>
    </div>

    <!--遮罩-->
    <div class="mask myHide"></div>
    <div style="margin-left:550px;"><%@ include file="/jsp/common/bottom.jsp"%></div>
</body>
<script>
    $(function(){
        $(".Box").height($(window).height()-35);
    });
</script>
</html>