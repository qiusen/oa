<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>当前流程图</title>
<%@ include file="/jsp/common/meta.jsp"%>
</head>
<body>
<img style="position:absolute;top:0px;left:0px;" alt="当前流程" src="${base}/admin/process/processAction!viewPng.${actionExt}?deploymentId=${requestScope.processDefinition.deploymentId }&pngName=${requestScope.processDefinition.diagramResourceName }" />
<div style="position:absolute;border:1px solid red;top:${requestScope.ai.y}px;left:${requestScope.ai.x}px;width:${requestScope.ai.width}px;height:${requestScope.ai.height}px;"></div>
</body>
</html>