<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>资源管理</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout">
<div region="north" title="查询" icon="icon-search" class="search_div" border="false">
	<form id="searchForm" class="form-search" method="post">
		<table>
			<tr>
				<td style="width:60px;">url:</td>
				<td><input name="search_LIKES_url" type="text" value=""></input></td>
				<td style="width:60px;">是否有效:</td>
				<td><form:select path="search_EQB_enableFlg" items="${mapEnableFlg}" name="search_EQB_enableFlg"></form:select></td>
				
				<td>
				<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">查询</a></td>
			</tr>
		</table>
	</form> 
</div>
<div region="center" border="false">
	<table id="tt" fit="true"
			title="" iconCls="icon-edit" singleSelect="true"
			idField="id" url="${ctx}/admin/resource/list">
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
				{field:'url',title:'url',editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}, sortable:true, width:280,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'remark',title:'备注',editor:{type:'text'}, sortable:true, width:300,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'enableFlg',title:'是否有效',editor:{type:'checkbox',options:{on:'true',off:'false'}}, sortable:true, width:80, align:'center',
					formatter:function(value,row,index){
						if(value=='true')
							return '是';
						else 
							return '否';
					}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					$('#tt').datagrid('endEdit', lastIndex);
					var field={
							url:'',
							remark:'',
							enableFlg:'true'//默认值
						};
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
						var nextSelect=index;
						if (row.id==undefined){
							$('#tt').datagrid('deleteRow',index);
							nextSelect=index>0?index-1:0;
							$('#tt').datagrid('selectRow', nextSelect);
						}else{
							$.messager.confirm('提示','确认要删除该记录吗?',function(t){
								if(t){
									nextSelect=index>0?index-1:0;
									//TODO:如果该记录被引用,是否强制不允许删除?
									$.post("${ctx}/admin/resource/delete/"+row.id,function(result) {
										var rObj = eval(result);
										if(rObj.success){
											$('#tt').datagrid('reload');
											$('#tt').datagrid('selectRow', nextSelect);
										}else{
											alert(rObj.failure);
										}
									});
								}
							});
						}
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
			}
		});
	});
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
		$.post("${ctx}/admin/resource/saveBatch",Convert.ToSaveParam("tt") , function(result) {
			var rObj = eval(result);
			if(rObj.success){
				$('#tt').datagrid('reload');
			}else{
				alert(rObj.failure);
			}
		});
		}
	}
	//清空查询条件
	function cleanSearchCon(){
		$('#search_LIKE_url').val('');
	}
</script>
</body>
</html>
