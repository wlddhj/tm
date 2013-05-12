<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>权限分配</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout">
<div region="north" border="false">
</div>
<div region="west" border="false" style="width: 380px;">
<table id="tb" class="easyui-datagrid"
            data-options="fit:true,singleSelect:true,url:'${ctx}/admin/permission/all'">  
        <thead>
            <tr>  
                <th data-options="field:'permCd',width:180">权限代码</th>  
                <th data-options="field:'permName',width:180">权限名称</th>  
            </tr>  
        </thead>
</table>
</div>
<div region="center" border="false">
	<div class="easyui-layout" fit="true">
		<div region="center"  border="false">
		<div class="easyui-layout" fit="true">
			<div data-options="region:'west',collapsible:false" title="菜单" style="width: 250px;">
			<ul id="treeMenu" class="easyui-tree" data-options="checkbox:true"></ul>
			</div>
			<div data-options="region:'center'" title="角色" >
			<ul id="treeRole"  class="easyui-tree" data-options="checkbox:true"></ul>
			</div>
			<div data-options="region:'east',collapsible:false" title="资源" style="width:250px;">
			<ul id="treeResource"  class="easyui-tree" data-options="checkbox:true"></ul>
			</div>
		</div>
		</div>
		<div region="south" style="height: 40px;padding:5px;">
			<div>  
	        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save();">Save</a> 
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="refresh();">Refresh</a>  
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var curRowId;
$(function(){
	$('#tb').datagrid({
		onClickRow:function(rowIndex, row){
			curRowId=row.id;
			loadMenu();
			loadRole();
			loadReource();
		}
	});
});
function loadMenu(){
	$.post("${ctx}/admin/permission/menu/"+curRowId,function(result) {
		$("#treeMenu").tree('loadData',result);
	});
}
function loadRole(){
	$.post("${ctx}/admin/permission/role/"+curRowId,function(result) {
		$("#treeRole").tree('loadData',result);
	});
}
function loadReource(){
	$.post("${ctx}/admin/permission/resource/"+curRowId,function(result) {
		$("#treeResource").tree('loadData',result);
	});
}
function getCheckedIds(treeId){
	var nodes = $('#'+treeId).tree('getChecked');
	var m = '';
	for(var i=0; i<nodes.length; i++){
		if (m != '') m += ',';
		m += nodes[i].id;
	}
	return m;
}
function save(){
	var m = getCheckedIds('treeMenu');
	var re = getCheckedIds('treeResource');
	var ro = getCheckedIds('treeRole');
	$.post("${ctx}/admin/permission/saveAssign/"+curRowId,{menuids:m,resourceids:re,roleids:ro},function(result) {
// 		$("#treeMenu").tree('loadData',result);
		if (result.success){
	        $.messager.show({
	            title:'Tip',
	            msg:result.success,
	            timeout:2000,
	            showType:'show'
	        });
		}else{
			 $.messager.alert('Tip','保存失败!','warning'); 
		}
	});
}
function refresh(){
	loadMenu();
	loadRole();
	loadReource();
}
</script>
</body>
</html>
