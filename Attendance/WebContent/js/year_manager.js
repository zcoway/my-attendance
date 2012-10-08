$(document).ready(function(){
	var obj = $('#list_data');
	obj.datagrid({ 
        iconCls:'icon-edit',//图标 
        nowrap: false, 
        striped: true, 
        border: false, 
        url:'../year/year_data.action',
        fit: true,//自动大小 
        idField:'yearId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {title:'学年',field:'yearName',width:'150',align:'center',},
                  {title:'学期',field:'yearDetail',width:'100',align:'center'},
                  {title:'开学时间',field:'yearTime',width:'100',align:'center'},
                  {title:'学年描述',field:'yearDesc',width:'200',align:'center'}
                 ]]

    }); 
});

function addItem(){
	url = '../year/year_save.action';
	$('#myform').form('clear');
	$('#dlg').dialog('setTitle','新增学年').dialog('open');
}
function editItem(){
	var row = $('#list_data').datagrid('getSelected');
	if (row){
		//设置提交的url
		url = '../year/year_update.action';
		$('#myform').formid('loadit',row);
		$('#dlg').dialog('setTitle','修改学年').dialog('open');
	} else {
		$.messager.show({
			title:'提示',
			msg:'请先选择学年记录，再进行修改。'
		});
	}
}
function saveItem(){
	alert(url);
	var data = $('#myform').form('submit',{
		url:url,
	//	onSubmit:function(){return $(this).form('validate');},
		success:function(data){
			$('#dlg').dialog('close');
			$('#list_data').datagrid('reload');
			alert("data:"+data);
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
				$.post('../year/year_delete.action',{"yearId":row.yearId},function(data){
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