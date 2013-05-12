<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;" border="false">
		<form id="inputForm" method="post" class="form-horizontal">
			<input type="hidden" name="id" id="dictTypeId" value="${dictType.id}" />
			<div class="control-group">
			<label for="dictTypeCd"  class="control-label">字典代码:</label>
			<input name="dictTypeCd" id="dictTypeCd" class="input-large required" required="true" type="text" value="${dictType.dictTypeCd}"></input>
			</div>
			<div class="control-group">
			<label for="dictTypeName"  class="control-label">字典名称:</label>
			<input name="dictTypeName" id="dictTypeName" class="input-large required" required="true" type="text" value="${dictType.dictTypeName}"></input>
			</div>
			<div class="control-group">
			<label for="dispOrderNo"  class="control-label">显示序号:</label>
			<input name="dispOrderNo" id="dispOrderNo" type="text"  class="input-large required" required="true" number="true" max="10000" value="${dictType.dispOrderNo}"></input>
			</div>
			<div class="control-group">
			<label for="remark" class="control-label">备注:</label>
			<textarea name="remark" id="remark" class="input-large">${dictType.remark}</textarea>
			</div>
		</form>
		<div>
			<table id="tt2" url="${ctx}/admin/dict/listData/${dictType.id}"
				title="字典数据" singleSelect="true" rownumbers="true">
			</table>
		</div>
		
	</div>
	<div region="south" border="false" style="height:30px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="$('#inputForm').submit();">保存</a>
			<c:if test="${dictType.id!=0 && dictType.id!=null }">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${dictType.id}');">刷新</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteType();">删除</a>
			</c:if>
			<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editrow('0');">新增</a>
	</div>
</div>


<script type="text/javascript">
	var lastIndex2=-1;
	var isExists=false;
	$(function(){
		initAppDictInput();
		$("#inputForm").validate({
			rules: {
				dictTypeCd: {
					remote: "${ctx}/admin/dict/isTypeExists?oldDictTypeCd=" + encodeURIComponent('${dictType.dictTypeCd}')
				}
			},
			messages: {
				dictTypeCd: {
					remote: "已经存在！"
				}
			},
			submitHandler: function(form)
			   {
				saveDict();
			   }
		});
	});
	function saveDict(){
		var options={
		        url:'${ctx}/admin/dict/save',
		        onSubmit: function(){
		        	var flag=true;
	        		var changeRows=$('#tt2').datagrid('getChanges','inserted','updated');
		        	for(var i=0;i<changeRows.length;i++){
		        		var rowIndex=$('#tt2').datagrid('getRowIndex',changeRows[i]);
		        		flag=$('#tt2').datagrid('validateRow',rowIndex);
		        		if (!flag){
		        			$('#tt2').datagrid('selectRow',rowIndex);
		        			$('#tt2').datagrid('beginEdit', rowIndex);
							break;
		        		}else{
		        			$('#tt2').datagrid('endEdit', rowIndex);
		        		}
		        	}
		        	if(flag){
		        		$('#tt2').datagrid('endEdit', lastIndex2);
				        Convert.setChildren2Form("inputForm","tt2");
		        	}
		        	return flag;
		        },
		        success:function(data){
		        	if(data.indexOf('success')!=-1){
		        		 $.messager.show({
		                     title:'Tip',
		                     msg:'保存成功',
		                     timeout:2000,
		                     showType:'show'
		                 });
						var id =$("#dictTypeId").val();
						if (id == '0'){
				        	$('#w').window("close");
						}else{
				    		$('#tt2').datagrid('reload');
						}
		        	}else{
		        		$.messager.alert('','保存失败');
		        	}
		        }
			};
		$('#inputForm').form('submit', options);
	}
	function initAppDictInput(){
		$('#tt2').datagrid({
			columns:[[
				{field:'dictCd',title:'代码',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'dictName',title:'名称',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'i18n',title:'i18n',width:100,editor:{type:'validatebox',options:{validType:'length[1,50]'}}},
				{field:'dispOrderNo',title:'序号',align:'right',width:50,editor:{type:'numberbox',options:{max:'10'}}},
				{field:'remark',title:'备注',width:100,editor:{type:'validatebox',options:{validType:'length[0,200]'}},
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updatedDate',title:'更新时间',sortable:true,width:110,
					formatter:function(value,row,index){
						if(value){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}else{
							return '';
						}
					}
				},
				{field:'action',title:'操作',width:60,align:'center',
					formatter:function(value,row,index){
						var id =row.id;
						var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow2("'+id+'") >删除</a>';
						return d;
					}
				}
				]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					var rowSel=$('#tt2').datagrid('getSelected', lastIndex2);
					$('#tt2').datagrid('endEdit', lastIndex2);
					var index = $('#tt2').datagrid('getData').total;
					if(!index)index = $('#tt2').datagrid('getRows').length;
					var field={
							id:'0',
							dictCd:'',
							dictName:'',
							i18n:'',
							remark:'',
							dispOrderNo:index+1
						}
					$('#tt2').datagrid('appendRow',field);
					lastIndex2 = $('#tt2').datagrid('getRows').length-1;
					$('#tt2').datagrid('selectRow', lastIndex2);
					$('#tt2').datagrid('beginEdit', lastIndex2);
					
					focusEditor(field,lastIndex2);
				}
			}],
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onClickCell:function(index,field){
				if (lastIndex2 != index){
					$('#tt2').datagrid('endEdit', lastIndex);
					$('#tt2').datagrid('beginEdit', index);
				}
				lastIndex2 = index;
				focusEditor(field,lastIndex2);
			}
		});
	}
	function focusEditor(field,editIndex){
		var editor = $('#tt2').datagrid('getEditor', {index:editIndex,field:field});
		if (editor){
			editor.target.focus();
		} else {
			var editors = $('#tt2').datagrid('getEditors', editIndex);
			if (editors.length){
				editors[0].target.focus();
			}
		}
	}
	function deleterow2(id){
		$('#tt2').datagrid('selectRecord',id);
		var row = $('#tt2').datagrid('getSelected');
		var index = $('#tt2').datagrid('getRowIndex',row);
		if (id=='0'){
			$('#tt2').datagrid('deleteRow', index);
			var nextSelect=index>0?index-1:0;
			$('#tt2').datagrid('selectRow', nextSelect);
		}else{
			$.messager.confirm('Confirm','确认删除字典数据?',function(r){
				if (r){
// 					$('#tt2').datagrid('deleteRow', index);
// 					var nextSelect=index>0?index-1:0;
// 					$('#tt2').datagrid('selectRow', nextSelect);
					if(id){
						$.post("${ctx}/admin/dict/deleteData/"+id, function(result) {
							$('#tt2').datagrid('reload');
						});
					}
				}
			});
		}
	}
</script>