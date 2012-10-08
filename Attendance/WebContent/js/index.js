// JavaScript Document
$(function(){
	tabClose();
	tabCloseEven();
	setDialog_edit();
	closeDialog_edit();
	
	$.post("../message/message_check.action",function(data){
		if(data.flag){
			$.messager.show({
				title:'提示',
				msg:'您的站内信有新消息啦，赶紧看下吧~~',
				timeout:5000,
				showType:'slide'
			});
		}
	});
});
function addTab(title, href)
{
       var tt = $('#tabs');
       if (tt.tabs('exists', title)){
        tt.tabs('select', title);
       } else {
        if (href){
         var content = '<iframe scrolling="yes" frameborder="0" src="'+href+'" style="width:100%;height:100%;"></iframe>';
        } else {
         var content = '未实现';
        }
        tt.tabs('add',{title:title,closable:true, content:content });                      
       }
	   tabClose();
}
	  
function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
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
//绑定右键菜单事件
function tabCloseEven()
{
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var othertabs = $('.tabs-selected').siblings();
		if(othertabs.length>0){
			othertabs.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$('#tabs').tabs('close',t);
			});
			return false;
		}
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

/**--------------修改密码操作弹出框------------------**/
//设置弹出框的属性
function setDialog_edit(){
	$('#changePWD').dialog({
		title : '用户密码修改',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			changePWDReset();
		}
	});
}
//打开对话框
function openDialog_edit(){
	$('#changePWD').dialog('open');
}
//关闭对话框
function closeDialog_edit(){
	$('#changePWD').dialog('close');
}

function changePWDReset()
{
	$("#changePWD_message").html("");
	$("#changePWD_PWD").val("");
	$("#changePWD_NewPWD").val("");
	$("#changePWD_reNewPWD").val("");
}

function changePWD()
{
	
}

function changePWDCancel()
{
	closeDialog_edit();
}

