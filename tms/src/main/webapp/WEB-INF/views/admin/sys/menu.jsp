<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>菜单管理</title>
<%@ include file="/WEB-INF/layouts/admin/header.jsp"%>
</head>
<body class="easyui-layout" >

	<div data-options="region:'west'" title="菜单" style="width:380px;">
	  	<ul id="tt">
	  	</ul>
	</div>
	<div data-options="region:'center'" title="详细信息"  style="">
		<div class="easyui-layout" style="width:100%;height:100%;">
	       	<div region="center" border="false" id="divRightContent">
	       	
	       	</div>
	    </div>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">  
        <div onclick="append()" data-options="iconCls:'icon-add'">新建</div>  
        <div id="mm_remove" onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>  
        <div class="menu-sep"></div>  
        <div onclick="expand()">Expand</div>  
        <div onclick="collapse()">Collapse</div>  
    </div>
<script type="text/javascript">
var lastSelectNodeId;
$(function(){
	$("#tt").tree({
		checkbox:false,
		animate:true,dnd:true,
		url:"${ctx}/admin/menu/initTree",
		onSelect:function(node){
			lastSelectNodeId=node.id;
			var isLeaf=$(this).tree('isLeaf',node.target);
			var item = $('#mm').menu('findItem', '删除');
			if (!isLeaf){
				$('#mm').menu('disableItem', item.target);
			}else{
				$('#mm').menu('enableItem', item.target);
			}
			if (node.id !='0'){
				loadEntity(node.id);
			}
		},
		onLoadSuccess:function(node,data){
			if (data!=''){
				if(typeof (lastSelectNodeId) != "undefined" && lastSelectNodeId!=''){
					var lastNode=$("#tt").tree('find',lastSelectNodeId);
					if (lastNode!=undefined){
						var parentNode=$("#tt").tree('getParent',lastNode.target)
						$("#tt").tree('expand',parentNode.target);
					}
					$("#tt").tree('select',lastNode.target);
				}
			}
		},
		onDrop:function(target, source, point){
			var parentNode=$("#tt").tree('getNode',target);
		  	$.post('${ctx}/admin/menu/drag',{id:source.id,'parentId':parentNode.id},function(result){
			  $('#tt').tree('reload');
			});
		},
		onContextMenu: function(e,node){  
            e.preventDefault();  
            $(this).tree('select',node.target);  
            $('#mm').menu('show',{  
                left: e.pageX,  
                top: e.pageY  
            });  
        }
	});
});
function loadEntity(id){
	$.get('${ctx}/admin/menu/detail/'+id,function(result){
		$("#divRightContent").html(result);
	});
}
function append(){
    var t = $('#tt');
    var node = t.tree('getSelected');
//     t.tree('append', {
//         parent: (node?node.target:null),  
//         data: [{
//             text: '新菜单'
//         }]  
//     });
    $.get('${ctx}/admin/menu/create/'+node.id,function(result){
		$("#divRightContent").html(result);
	});
}  
function remove(){  
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('remove', node.target);
    lastSelectNodeId='';
    $.get('${ctx}/admin/menu/delete/'+node.id,function(result){
    	$("#divRightContent").html('');
	});
}
function collapse(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('collapse',node.target);
}
function expand(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('expand',node.target);
}
function save(){
	$("#inputForm").form('submit',{
		url:'${ctx}/admin/menu/save',
		success:function(result){
			if(result.indexOf('success')!=-1){
				var id=result.substr(result.indexOf(":")+1);
				lastSelectNodeId=id;
				$("#tt").tree('reload');
			}else{
				alert(result);
			}
		}
	});
}
</script>
</body>
</html>
