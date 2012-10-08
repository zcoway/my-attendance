$(document).ready(function(){
	var treeObj = $('#tt2');
	treeObj.tree({
		checkbox: false,
		url: '../system/user_tree_data.action',
		onClick:function(node){
			treeObj.tree('toggle', node.target);
			var node = treeObj.tree('getSelected');
			var isLeaf = treeObj.tree('isLeaf', node.target);
			if(isLeaf){
				showDatagrid(node.id);
			}
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			$('#tt2').tree('select', node.target);
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
	});

});

function getSelectedRowId(){
		var selected = $('#list_data').datagrid('getSelected');
		if (selected){
			return selected.userId;
		}
		return null;
}

function importData() {
		$('#ff').form('submit',{
	        url:"user_import.action",
	        success:function(data){
	        	$('#dlg2').dialog('close');
	        	$.messager.show({
	    			title:'提示',
	    			msg:':-) 导入成功,5秒后自动关闭',
	    			timeout:5000,
	    			showType:'slide'
	    		});
	        	$('#list_data').datagrid('reload');
	        }
		});	
}

function isLeaf(){
	var node = $('#tt2').tree('getSelected');
	var b = $('#tt2').tree('isLeaf', node.target);
	alert(b);
}
function getSelected(){
	var node = $('#tt2').tree('getSelected');
	alert(node.id);
}
function showDatagrid(id) {
	var obj = $('#list_data');
	obj.datagrid({ 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        nowrap: false, 
        striped: true, 
        border: false, 
        url:'../system/user_data.action?clasId='+id,
        fit: true,//自动大小 
        idField:'userId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {title:'姓名',field:'userName',width:'75',align:'center',},
                  {title:'学号',field:'userNum',width:'75',align:'center'},
                  {title:'班级',field:'clasName',width:'75',align:'center'},
                  {title:'性别',field:'genderText',width:'75',align:'center'},
                  {title:'生日',field:'birthdayText',width:'75',align:'center'},
                  {title:'邮箱地址',field:'userEmail',width:'100',align:'center'},
                  {title:'联系方式',field:'userTel',width:'100',align:'center'},
                  {title:'QQ号码',field:'userQq',width:'75',align:'center'},
                  {title:'用户简介',field:'userIntro',width:'150',align:'center'},
                  {title:'爱好',field:'userHobby',width:'150',align:'center'}
                 ]]
/*        toolbar: [{ 
            text: '添加', 
            iconCls: 'icon-add', 
            handler: function() { 
            	var url = getAddUrl();
            	openWindow(url,"add",{title:"增加"});
            	('#list_data').datagrid('reload');
            } 
        },  '-',{ 
            text: '删除', 
            iconCls: 'icon-remove', 
            handler: function(){
            	var url = getDelUrl();
            	var userId = getSelectedRowId();
            	$.get(url,{"userVo.userId":userId},function(){
            		$.messager.show({
        				title:'提示',
        				msg:':-) 删除成功,5秒后自动关闭',
        				timeout:5000,
        				showType:'slide'
        			});
            		obj.datagrid('reload');
            		
            	});
            }
        }, '-', {
        	text: '批量导入', 
            iconCls: 'icon-ok', 
            handler: function(){
            	$('#dlg').dialog('setTitle','导入Excel数据').dialog('open');
        	}
        }],*/ 
    }); 
}/*
function addItem() {
	var url = getAddUrl();
	openWindow(url,"add",{title:"增加"});
}
function editItem() {
	var userId = $('#list_data').datagrid('getSelected').userId;
	alert("userId:"+userId);
	var url = "../system/user_edit.action?userId="+userId;
	openWindow(url,"add",{title:"修改"});
}*/
function addItem(){
	url = '../system/user_save.action';
	$('input[id="clasName"]').combotree({
		url:"clas_tree_data.action"
		/*onClick:function(node){
			$(this).combotree('toggle', node.target);
		}*/
	});
	$('#myform').form('clear');
	//$("#dlg-buttons a:first-child").show();
	$('#dlg').dialog('setTitle','新增用户').dialog('open');
}
function editItem(){
	var row = $('#list_data').datagrid('getSelected');
	if (row){
		//编辑之前加载上级资源列表
		$('input[id="clasName"]').combotree({
			url:"clas_tree_data.action"
		});
		//设置提交的url
		url = '../system/user_update.action';
		//因为row中的.为特殊字符，需要转义才能让easyui自动赋值，该函数返回类型是字符串
		//row = JSON.stringify(row).replace(/\./g,"\\\\.");
		//$('#myform').form('load',eval('('+row+')'));
		$('#myform').formid('loadit',row);
		
		$('#dlg').dialog('setTitle','修改用户').dialog('open');
	} else {
		$.messager.show({
			title:'提示',
			msg:'请先选择用户记录，再进行修改。'
		});
	}
}
function saveItem(){
	
	var frm=document.getElementById("myform");
	frm.action=url;
	frm.submit();
	return;
	
	
	alert(url);
	var data = $('#myform').form('submit',{
		url:url,
	//	onSubmit:function(){return $(this).form('validate');},
		success:function(data){
			$('#dlg').dialog('close');
			$('#list_data').datagrid('reload');
			data=eval('('+data+')');
			if(data.success){
				$.messager.show(
					{
						title:'提示',
						msg:'操作成功！',
						showType:'slide'
					}
				);
				loadAllTrees();
			}
			if(data.error){
				$.messager.alert('提示','操作失败！','error');
			}
		}
	});
}
function removeItem(){
	var row = $('#list_data').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','确定要删除该记录?',function(r){
			if(r){
				$.post('../system/user_delete.action',{"userId":row.userId},function(data){
					if(data.success){
						$('#list_data').datagrid('reload');
						$.messager.show({
								title:'提示',
								msg:':-) 操作成功！',
								showType:'slide'
							});
					}else{
						$.messager.alert('提示','操作失败！','error');
					}
				});
			}
		});
	} else {
		$.messager.show({
			title:'提示',
			msg:'请先选择用户记录，再进行删除。'
		});
	}
}
function refresh(){
	$('#list_data').datagrid('reload');
}
function showImportDialog(){
	var dlg = $('#dlg-import');
	if (!dlg.length){
		dlg = $('<div id="dlg-import"></div>').appendTo('body');
		var formDiv =  $('<div sytle="text-align:center;padding-top:5px"></div>');
		var formElement =$('<form id="ff" name="ff" method="post" enctype="multipart/form-data" sytle="text-align:center"></form>');
		formElement.append($('<input type="file" name="file">'));	
		formDiv.append(formElement);
		
		formDiv.appendTo(dlg);
		
		dlg.dialog({
			title:'导入数据',
			width:350,
			height:100,
			closed:true,
			modal:true,
			buttons:[{
				text:'导入',
				iconCls:'icon-redo',
				handler:function(){
					$('#ff').form('submit',{
				        url:"user_import.action",
				        onSubmit:function(){return true;},
				        success:function(data){
				        	alert(data);
				        	if(data.success){
				        		dlg.dialog('close');
								$('#list_data').datagrid('reload');
								$.messager.show({
										title:'提示',
										msg:':-) 操作成功！',
										showType:'slide'
									});
							}else{
								$.messager.alert('提示','操作失败！','error');
							}
				        	dlg.remove();
				        }
					});
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					dlg.dialog('close');
					dlg.remove();
				}
			}]
		});
		
	}
	dlg.dialog('open');
}