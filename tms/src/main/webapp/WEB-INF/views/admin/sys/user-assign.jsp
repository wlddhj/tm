<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户分配</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout">
<div region="west" style="width: 380px;" title="用户">
	<ul id="treeUser"></ul>
</div>
<div region="center" border="false">
	<div class="easyui-layout" fit="true">
		<div region="center" title="角色">
			<ul id="treeRole"  class="easyui-tree" data-options="checkbox:true"></ul>
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
	$("#treeUser").tree({
		checkbox:false,
		url:"${ctx}/admin/user/initTree",
		onSelect:function(node){
			if (node.attributes.nodeType =='user'){
				curRowId=node.id.substr(4);
				loadRole();
			}
		}
	});
});
function loadRole(){
	$.post("${ctx}/admin/user/role/"+curRowId,function(result) {
		$("#treeRole").tree('loadData',result);
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
	var ro = getCheckedIds('treeRole');
	$.post("${ctx}/admin/user/saveRoles/"+curRowId,{roleids:ro},function(result) {
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
	loadRole();
}
</script>
</body>
</html>
