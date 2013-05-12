<%@page import="com.hhz.tms.admin.ShiroUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>后台管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script type="text/javascript">	_ctx='${ctx}';	</script>
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/jquery-validation/1.10.0/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-easyui/themes/gray/easyui.css" rel="stylesheet" type="text/css" >
<link href="${ctx}/static/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" >
<link href="${ctx}/static/css/default.css" rel="stylesheet" type="text/css" >
<script src="${ctx}/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.10.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.10.0/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/ConvertUtil.js" type="text/javascript"></script>

<script src="${ctx}/static/js/admin/index.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no" >
	<noscript>
        <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
            <img src="${ctx}/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
        </div>
    </noscript>
	
	<div region="north" split="false" border="false">
        <div class="top">
            <span style="float:right; padding-right:20px;" class="head">
            	当前用户：<b><shiro:principal/></b>
				&nbsp;
				[ <a href="javascript: changePwd();">修改密码</a> 
				| <a  href="javascript:logout();">退出登录</a> ]
            </span>
            <span style="font-size: 16px; ">
            	<div style="float:left;margin: 5px 0 0 10px;"><img src="${ctx}/images/pd_logo.png" width="76" height="25" align="absmiddle" />&nbsp;</div>
            	<div style="float:left;margin: 5px 0 0 10px;font-size:20px;color:white;font-weight: bold;vertical-align: middle;">欢迎使用XX认证授权平台</div>
            </span>
        </div>
	</div>
	<div region="south" style="height:30px;padding:5px;background:#efefef;">
		<div align="center" style="font-weight: bold;color:#1B5978">诚信  恭谦 创新 敬业</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:180px;">
		<div class="easyui-accordion" fit="true"  border="false">
				<div title="系统管理" iconCls="icon-dict" style="overflow:auto;">
					<ul>
						<li>
							<div>
							<a href="javascript:addTab('资源管理','${ctx}/admin/resource')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">资源管理</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('资源分配','${ctx}/admin/resource/assign')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">资源分配</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('菜单管理','${ctx}/admin/menu')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">菜单管理</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('菜单分配','${ctx}/admin/menu/assign')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">菜单分配</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('权限管理','${ctx}/admin/permission')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">权限管理</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('权限分配','${ctx}/admin/permission/assign')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">权限分配</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('角色管理','${ctx}/admin/role')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">角色管理</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('角色分配','${ctx}/admin/role/assign')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">角色分配</span>
							</a>
							</div>
						</li>
					</ul>
				</div>
				<div title="组织管理" iconCls="icon-dict" style="overflow:auto;">
					<ul>
						<li>
							<div>
							<a href="javascript:addTab('机构管理','${ctx}/admin/dept')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">机构管理</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('用户管理','${ctx}/admin/user')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">用户管理</span>
							</a>
							</div>
						</li>
						<li>
							<div>
							<a href="javascript:addTab('用户分配','${ctx}/admin/user/assign')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">用户分配</span>
							</a>
							</div>
						</li>
					</ul>
				</div>
				<div title="基础管理" iconCls="icon-dict" style="overflow:auto;">
					<ul>
						<li>
							<div>
							<a href="javascript:addTab('数据字典','${ctx}/admin/dict')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">数据字典</span>
							</a>
							</div>
						</li>
					</ul>
				</div>
		</div>
	</div>
	<div region="center" style="overflow:hidden;">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" style="min-width:860px;width:100%;overflow: hidden;"> 
				<h1 style="font-size:24px;">欢迎使用XX系统认证授权平台</h1>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">关闭全部</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">关闭后面</div>
		<div id="mm-tabcloseleft">关闭前面</div>
	</div>

	<div id="changePwdDiv" title="密码修改" style="width:300px;">
		<form id="changePwdForm" method="post">
			<input type="hidden" name="id"  value="<%=ShiroUtil.getCurrentUserId()  %>"/>
			<table style="width:100%;">
				<tr>
					<td width="60">原密码</td>
					<td><input type="password" id="oldPwd" class="easyui-validatebox"  required="true" name="oldPwd" onchange="$('#oldPwdWrong').hide();"/>
						<span style="color:red;display: none;" id="oldPwdWrong">原密码错误</span>
					</td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input type="password" id="newPwd" class="easyui-validatebox"  required="true" name="newPwd"/></td>
				</tr>
				<tr>
					<td>再输一次</td>
					<td><input type="password" class="easyui-validatebox" validType="equalTo['newPwd']"  required="true" id="newPwdRepeat"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>