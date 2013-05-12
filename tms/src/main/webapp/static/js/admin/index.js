$(function(){
	initLeftMenu();
	bindTabEvent();
	bindTabMenuEvent();
	extentValidate();
	initDialog();
});

//初始化左侧
function initLeftMenu() {
	$('.easyui-accordion li a').click(function(){
		$('.easyui-accordion li div').removeClass('selected');
		$(this).parent().addClass('selected');
	}).hover(function(){
		$(this).parent().addClass('hover');
	},function(){
		$(this).parent().removeClass('hover');
	});
}

//添加一个新的tab,全部以iframe的形式来加载
function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon||'icon-dict'
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		//$('#mm-tabupdate').click();//刷新tab页
	}
	bindTabEvent();
}

function createFrame(url){
	var s;
	if(url&&url!='undefined'&&$.trim(url) != ""){
		s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99%;"></iframe>';
	}else{
		s="<div align=center style='margin-top:30px;color:red;font-size:16px;font-weight:bold;'>网页未实现</div>";
	}
	
	return s;
}

function bindTabEvent()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定tab右键菜单事件
function bindTabMenuEvent()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url){
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			});
		}
	});
	//关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		if($('#tabs').tabs('getTab',currtab_title).panel('options').closable){
			$('#tabs').tabs('close',currtab_title);
		}
	});
	//全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			if ($(this).parent().next().is('.tabs-close')) {
				var t = $(n).text();
				$('#tabs').tabs('close', t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i, n) {
			if ($(this).parent().next().is('.tabs-close')) {
				var t = $(n).text();
				if (t != currtab_title)
					$('#tabs').tabs('close', t);
			}
		});
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			if ($('a.tabs-close', $(n)).length > 0) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			}
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 1) {
			alert('前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			if ($('a.tabs-close', $(n)).length > 0) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			}
		});
		return false;
	});
}
/**    
 * 刷新tab
 * @cfg 
 *example: {tabTitle:'tabTitle',url:'refreshUrl'}
 *如果tabTitle为空，则默认刷新当前选中的tab
 *如果url为空，则默认以原来的url进行reload
 */
function refreshTab(cfg){
	var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');
	if(refresh_tab && refresh_tab.find('iframe').length > 0){
	var _refresh_ifram = refresh_tab.find('iframe')[0];
	var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
	_refresh_ifram.contentWindow.location.href=refresh_url;
	}
}

function extentValidate(){
	$.extend($.fn.validatebox.defaults.rules, {
	    equalTo: {
	        validator: function(value, param){
	        	 return value == $('#'+param[0]).val();
	        },
	        message: '两次输入的密码不一致'
	    }
	});
}

function initDialog(){
	$('#changePwdDiv').dialog({
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				submitPwdForm();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#changePwdDiv').dialog('close');
			}
		}]
	}).dialog('close');
	
}
function logout(){
	$.messager.confirm('提示','您确认要退出系统吗？',function(t){
		if(t){
			window.location.href=_ctx+'/logout';
		}
	});
}
function changePwd(){
	$('#oldPwd,#newPwd,#newPwdRepeat').val('');
	$('#changePwdDiv').dialog('open');
}
function submitPwdForm(){
	if(!$('#changePwdForm').form('validate'))return;
	var param = $('#changePwdForm').serialize();
	var url = _ctx+'/admin/user/changePwd';
	$.post(url,param,function(data){
		if('error' == data){
			$('#oldPwdWrong').show();
		}else if(data=='success'){
			$.messager.show({
				title:'提示',
				msg:'保存成功,请妥善保管您的密码！',
				timeout:5000,
				showType:'slide'
			});
			$('#changePwdDiv').dialog('close');
		}else{
			$.messager.alert('提示','保存失败');
		}
	});
}
function refreshCache(){
	$.post(_ctx+"/app/cache.action",function(data){
		alert(data);
	});
}
function editMyInfo(){
	addTab('我的资料',_ctx+ '/plas/plas-user!my.action');
}