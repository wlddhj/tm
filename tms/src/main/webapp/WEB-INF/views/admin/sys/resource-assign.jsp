<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>资源分配</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout">
<div region="north" border="false">
</div>
<div region="west" border="false" style="width: 400px;">
<table id="tb" class="easyui-datagrid"
            data-options="fit:true,singleSelect:true,url:'${ctx}/admin/resource/allEable'">  
        <thead>
            <tr>  
                <th data-options="field:'url',width:180">url</th>  
                <th data-options="field:'remark',width:180">备注</th>  
            </tr>  
        </thead>
</table>
</div>
<div region="center" border="false">
	<div class="easyui-layout" fit="true">
		<div region="center" title="权限" >
			<ul id="tree"></ul>
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
		}
	});
	$("#tree").tree({
		checkbox:true,
		onSelect:function(node){
			
		},
		onLoadSuccess:function(node,data){
			
		}
	});
});
function loadPerm(){
	$.post("${ctx}/admin/resource/permission/"+curRowId,function(result) {
		$("#tree").tree('loadData',result);
	});
}
function save(){
	var nodes = $('#tree').tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
		s += nodes[i].id;
	}
	$.post("${ctx}/admin/resource/savePerm/"+curRowId,{permids:s},function(result) {
		$("#tree").tree('loadData',result);
            $.messager.show({
                title:'Tip',
                msg:'保存成功',
                timeout:2000,
                showType:'show'
            });
	});
}
function refresh(){
	loadPerm();
}
</script>
</body>
</html>
