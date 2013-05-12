<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>角色管理</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout">
<div region="north" title="查询" icon="icon-search" class="search_div" border="false">
	<form id="searchForm" class="form-search" method="post">
	<table>
		<tr>
			<td style="width:60px;">角色代码:</td>
			<td><input name="search_LIKES_roleCd" type="text" value=""></input></td>
			<td style="width:60px;">角色名称:</td>
			<td><input type="text" name="search_LIKES_roleName"></td>
			<td>
			<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">查询</a>
			</td>
		</tr>
	</table>
	</form> 
</div>
<div region="center" border="false">
	<table id="tt" fit="true"
			title="" iconCls="icon-edit" singleSelect="true"
			idField="id" url="${ctx}/admin/role/list">
	</table>
</div>
<script type="text/javascript">
	var lastIndex;
	$(function(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:10,
			rownumbers:true,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'roleCd',title:'角色代码',editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}, sortable:true, width:200},
				{field:'roleName',title:'角色名称',editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}, sortable:true, width:200},
				{field:'remark',title:'备注',editor:{type:'text'}, sortable:true, width:300}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					$('#tt').datagrid('endEdit', lastIndex);
					var field={
							roleCd:'',
							roleName:'',
							remark:''	
					}
					$('#tt').datagrid('appendRow',field);
					lastIndex = $('#tt').datagrid('getRows').length-1;
					$('#tt').datagrid('selectRow', lastIndex);
					$('#tt').datagrid('beginEdit', lastIndex);
					focusEditor(field,lastIndex);
				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					saveEdit();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var row = $('#tt').datagrid('getSelected');
					if(row){
						var index = $('#tt').datagrid('getRowIndex', row);
						if (row.id==undefined){
							$('#tt').datagrid('deleteRow',index);
						}else{
						$.messager.confirm('提示','确认要删除该记录吗?',function(t){
							if(t){
								//TODO:如果该记录被引用,是否强制不允许删除?
								$.post("${ctx}/admin/role/delete/"+row.id,function(result) {
									var rObj = eval(result);
									if(rObj.success){
										$('#tt').datagrid('reload');
									}else{
										alert(rObj.failure);
									}
								});
							}
						});
						}
						var nextSelect=index>0?index-1:0;
						$('#tt').datagrid('selectRow', nextSelect);
					}
				}
			},'-'],
			onDblClickCell:function(index,field){
				$('#tt').datagrid('endEdit', lastIndex);
				$('#tt').datagrid('beginEdit', index);
				lastIndex = index;
				focusEditor(field,lastIndex);
			},
			onClickCell:function(index,field){
				if (lastIndex != index){
					$('#tt').datagrid('endEdit', lastIndex);
					$('#tt').datagrid('beginEdit', index);
				}
				lastIndex = index;
				focusEditor(field,lastIndex);
			},
		});
	});
	function saveEdit(){
		var flag=true;
		var row = $('#tt').datagrid('getSelected');
		if (row){
			var index = $('#tt').datagrid('getRowIndex', row);
			$('#tt').datagrid('endEdit', index);
		}
		var changeRows=$('#tt').datagrid('getChanges','inserted','updated');
    	for(var i=0;i<changeRows.length;i++){
			var index = $('#tt').datagrid('getRowIndex', changeRows[i]);
			flag=$('#tt').datagrid('validateRow',index);
    		if (!flag){
    			$('#tt').datagrid('selectRow',index);
    			$('#tt').datagrid('beginEdit', index);
				break;
    		}else{
    			$('#tt').datagrid('endEdit', index);
    		}
		}
		if (flag){
		$.post("${ctx}/admin/role/saveBatch",Convert.ToSaveParam("tt") , function(result) {
			var rObj = eval(result);
			if(rObj.success){
				$('#tt').datagrid('reload');
			}else{
				alert(rObj.failure);
			}
		});
		}
	}
	function focusEditor(field,editIndex){
		var editor = $('#tt').datagrid('getEditor', {index:editIndex,field:field});
		if (editor){
			editor.target.focus();
		} else {
			var editors = $('#tt').datagrid('getEditors', editIndex);
			if (editors.length){
				editors[0].target.focus();
			}
		}
	}
</script>
</body>
</html>
