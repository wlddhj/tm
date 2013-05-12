<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>

<body class="easyui-layout">
	<div region="north" title="查询" icon="icon-search" class="search_div" border="false">
	<form id="searchForm" class="form-search" method="post">
		<table>
			<tr>
				<td style="width:80px;">字典类型CD:</td>
				<td><input name="search_LIKES_dictTypeCd" type="text" value=""></input></td>
				<td style="width:80px;">字典类型名称:</td>
				<td><input name="search_LIKES_dictTypeName" type="text" value=""></input></td>
				<td>
				<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">查询</a></td>
			</tr>
		</table>
	</form> 
	</div>
	<div region="center" border="false">
		<table id="tt" fit="true"
				title="" iconCls="icon-edit" singleSelect="true"
				url="${ctx}/admin/dict/list">
		</table>
	</div>
	<div id="w" class="easyui-window" style="width:720px;height:500px;"></div>
<script type="text/javascript">
	var lastIndex=0;
	$(function(){
		init();

	});
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'dictTypeCd',title:'字典类型CD',sortable:true,width:150,height:30},
				{field:'dictTypeName',title:'字典类型名称',width:150},
				{field:'dispOrderNo',title:'显示序号',width:60,align:'center'	},
				{field:'createdDate',title:'创建时间',width:110},
				{field:'updatedDate',title:'更新时间',width:110,align:'center'}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					editrow(0);
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-save',
				handler:function(){
					var row = $('#tt').datagrid('getSelected');  
					editrow(row.id);
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					deleteType();
				}
			},'-'],
			onDblClickRow:function(index,row){
				lastIndex = index;
				editrow(row.id);
			},
			onClickRow:function(index,row){
				lastIndex = index;
			},
			onLoadSuccess:function(data){
				var total =$('#tt').datagrid('getData').total;
				if (lastIndex >total-1){
					lastIndex=total-1;
				}
				$('#tt').datagrid('selectRow', lastIndex);
			}
		});
		$('#w').window({
			title: '字典管理',
			modal:true,
			closed: true,
			collapsible : false,
			minimizable : false,
			cache:false,
			iconCls:"icon-save",
			onClose:function(){
				$('#tt').datagrid('reload');
			}
		});
	}
	function deleteType(){
		
		var row = $('#tt').datagrid('getSelected');
		if(row){
			var index = $('#tt').datagrid('getRowIndex', row);
			var lastIndex=index;
			if (row.id==undefined){
				$('#tt').datagrid('deleteRow',index);
				$('#w').window("close");
				lastIndex=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
			}else{
				$.messager.confirm('提示','确认要删除该记录吗?',function(t){
					if(t){
						
						lastIndex=index>0?index-1:0;
						//TODO:如果该记录被引用,是否强制不允许删除?
						$.post("${ctx}/admin/dict/delete/"+row.id,function(result) {
							var rObj = eval(result);
							if(rObj.success){
								$('#tt').datagrid('reload');
								$('#w').window("close");
							}else{
								alert(rObj.failure);
							}
						});
					}
				});
			}
		}
	}
	function editrow(id){
		var url="${ctx}/admin/dict/detail/"+id;
		$('#w').window({href:url});
		$('#w').window("open");
	}
</script>
</body>
</html>