<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><%=Property.SYSTEM_NAME %></title>
<%@ include file="/jsp/common/meta.jsp"%>
<script type="text/javascript">
<c:if test="${sessionScope.manager==null}">
window.location="${base}";
</c:if>
<c:forEach items="${menuList}" var="menu">
<c:set value="0" var="catalogIndex" /> 
	var indexdata${menu.id } = [
	<c:forEach items="${catalogList}" var="catalog">
		<c:if test="${catalog.menuId==menu.id}">
			<c:if test="${catalogIndex!=0}">,</c:if>{isexpand:"false",text:"${catalog.catalogname}",children:[ 
			<c:set value="0" var="moduleIndex" /> 
	       	<c:forEach items="${moduleList}" var="module" varStatus="i">
		       	<c:if test="${module.catalogId==catalog.id}">	
		       		<c:if test="${moduleIndex!=0}">,</c:if>{url:"${base}${module.moduleurl}/${module.moduleact}.${actionExt}",text:"${module.modulename}"}<c:set value="${moduleIndex+1}" var="moduleIndex" /> 
		       	</c:if>
	       	</c:forEach>  
	       	]}<c:set value="${catalogIndex+1}" var="catalogIndex" /> 
		</c:if>
	</c:forEach>              
           ];	//动态树
	var tree${menu.id } = null;
</c:forEach>
     
      var tab = null;
      var accordion = null;
      
	$(function () {
      	//布局
      	$("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });
          var height = $(".l-layout-center").height();
          //Tab
          $("#framecenter").ligerTab({ height: height });
          //面板
          $("#accordion1").ligerAccordion({ height: height - 24, speed: null });
          $(".l-link").hover(function (){
          	$(this).addClass("l-link-over");
          }, function (){
          	$(this).removeClass("l-link-over");
          });
          //树
          <c:forEach items="${menuList}" var="menu">
          $("#tree${menu.id }").ligerTree({	//动态树
          	data : indexdata${menu.id },
          	checkbox: false,
          	slide: true,
          	nodeWidth: 120,
          	btnClickToToggleOnly:false,
          	attribute: ['nodename', 'url'],
           	onSelect: function (node){
              	if (!node.data.url) return;
              	var tabid = $(node.target).attr("tabid");
              	if (!tabid) {
                  	tabid = new Date().getTime();
                  	$(node.target).attr("tabid", tabid)
               	} 
              	f_addTab(tabid, node.data.text, node.data.url);
           	}
          });
          </c:forEach>
          
          

          tab = $("#framecenter").ligerGetTabManager();
          accordion = $("#accordion1").ligerGetAccordionManager();
          <c:forEach items="${menuList}" var="menu">
          tree${menu.id } = $("#tree${menu.id }").ligerGetTreeManager();	//动态树
          </c:forEach>
          
          $("#pageloading").hide();
	});
      function f_heightChanged(options) {
      	if (tab)
          	tab.addHeight(options.diff);
      	if (accordion && options.middleHeight - 24 > 0)
          	accordion.setHeight(options.middleHeight - 24);
      }
      function f_addTab(tabid,text, url) { 
      	tab.addTabItem({ tabid : tabid,text: text, url: url });
      } 
           
          
</script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('${base}/image/loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} 
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
    .space{ color:#E7E7E7;}
    /* 顶部 */ 
    .l-topmenu{ margin:0; padding:0; height:31px; line-height:31px; background:url('${base}/image/top.jpg') repeat-x bottom;  position:relative; border-top:1px solid #1D438B;  }
    .l-topmenu-logo{ color:#E7E7E7; padding-left:35px; line-height:26px;background:url('${base}/image/topicon.gif') no-repeat 10px 5px;}
    .l-topmenu-welcome{  position:absolute; height:24px; line-height:24px;  right:30px; top:2px;color:#070A0C;}
    .l-topmenu-welcome a{ color:#E7E7E7; text-decoration:underline} 

 </style>

</head>
<body style="padding:0px;background:#EAEEF5;">  
<div id="pageloading"></div>  
<%@ include file="/jsp/common/top.jsp"%>
  <div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; "> 
        <div position="left"  title="主要菜单" id="accordion1"> 
                    <c:forEach items="${menuList}" var="menu">
                    <div title="${menu.menuname }" class="l-scroll">
                         <ul id="tree${menu.id }" style="margin-top:3px;"></ul>
                    </div> 
                    </c:forEach>
                     <div title="个人资料">
                    <div style=" height:7px;"></div>
                          <a class="l-link" href="javascript:f_addTab('changeinfo','个人信息修改','${base}/userInfo/userInfo.${actionExt}')">个人信息修改</a>
                          <a class="l-link" href="javascript:f_addTab('changepasswd','密码修改','${base}/userInfo/userInfo!editPassword.${actionExt}')">密码修改</a>  
                    </div> 
        </div>
        <div position="center" id="framecenter"> 
            <div tabid="home" title="我的主页" style="height:300px" >
                <iframe frameborder="0" name="home" id="home" src="${base}/jsp/welcome.jsp"></iframe>
            </div> 
        </div> 
        
    </div>
    <div style="height:32px; line-height:32px; text-align:center;">
            Copyright <%=Property.COMP_NAME %>
    </div>
</body>

</html>