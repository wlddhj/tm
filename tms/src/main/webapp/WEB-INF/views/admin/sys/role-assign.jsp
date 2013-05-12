<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>角色分配</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout">
<div region="north" border="false">
</div>
<div region="west" border="false" style="width: 400px;">
<table id="tb" class="easyui-datagrid"
            data-options="fit:true,singleSelect:true,url:'${ctx}/admin/role/all'">  
        <thead>
            <tr>  
                <th data-options="field:'roleCd',width:180">角色代码</th>  
                <th data-options="field:'roleName',width:180">角色名称</th>  
            </tr>  
        </thead>
</table>
</div>
<div region="center" border="false">
	<div class="easyui-layout" fit="true">
		<div region="west" title="权限" style="width: 250px;">
			<ul id="tree"></ul>
		</div>
		<div region="center" title="用户">
			<ul id="treeUser"></ul>
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
			loadPerm();
			loadUsers();
		}
	});
	$("#tree").tree({
		checkbox:true,
		onSelect:function(node){
			
		},
		onLoadSuccess:function(node,data){
			
		}
	});
	$("#treeUser").tree({
		checkbox:true,
		onSelect:function(node){
			
		},
		onLoadSuccess:function(node,data){
			
		}
	});
});
function loadPerm(){
	$.post("${ctx}/admin/role/permission/"+curRowId,function(result) {
		$("#tree").tree('loadData',result);
	});
}
function loadUsers(){
	$.post("${ctx}/admin/role/user/"+curRowId,function(result) {
		$("#treeUser").tree('loadData',result);
	});
}
function getCheckedUserIds(treeId){
	var nodes = $('#'+treeId).tree('getChecked');
	var m = '';
	for(var i=0; i<nodes.length; i++){
		if (m != '') m += ',';
		var node=nodes[i];
		if (node.attributes.nodeType =='user'){
			m += node.id.substr(4);
		}
	}
	return m;
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
	var s = getCheckedIds('tree');
	var u = getCheckedUserIds('treeUser');
	$.post("${ctx}/admin/role/saveAssign/"+curRowId,{permids:s,userids:u},function(result) {
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
	loadPerm();
	loadUsers();
}
</script>
</body>
</html>
