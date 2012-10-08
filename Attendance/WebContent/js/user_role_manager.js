$(document).ready(function(){
	$('#list_data').datagrid({ 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../system/user_data.action', 
        idField:'userId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {field:'userName',title:'用户名称',width:510}
                 ]],
        onClickRow:function(rowIndex, rowData){
        	var userId = document.getElementById("userId");
        	userId.value = rowData.userId; //保存userId值
        	$.post('../system/user_role_relation.action',{"user.userId":rowData.userId},function(data){
        		var roleObj = $('#list_data2');
        		roleObj.datagrid('unselectAll'); //清除上一次记录
        		var idValue = data.roleIds;
        		var inputValue = document.getElementById("roleIds");
        		inputValue.value = idValue;  //保存roleIds值
        		if( idValue.match("\,")){
        			idValue = idValue.split(",");
        			for(var i=0;i<idValue.length;i++){
        				roleObj.datagrid('selectRecord',idValue[i]);
            		}
        		}else{
        			roleObj.datagrid('selectRecord',idValue);
        		}
        	});
        }

        
    }); 
	
    //=========================================================================
    $('#list_data2').datagrid({ 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        border: false, 
        fit: true,//自动大小 
        url:'../system/role_data.action', 
        idField:'roleId', 
        singleSelect:false,//是否单选 
        pagination:false,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {field:'roleDesc',title:'用户所属角色',width:450},
                  {field:'ck',checkbox:true}
                 ]],
    }); 
    
});

function getSelectedRowId(){
	/*	var rows = $('#list_data').datagrid('getSelections');
		var ids = new Array();
		for(var i=0; i<rows.length; i++){
			ids[i] = rows[i].userId;
		}
		
		if(ids!=null){
			return ids;
		}
		return null;*/
		var selected = $('#list_data').datagrid('getSelected');
		if (selected){
			return selected.resourceId;
		}
		return null;
}
function resetEdit() {
	var inputValue = document.getElementById("roleIds").value;
	var roleObj = $('#list_data2');
	roleObj.datagrid('unselectAll'); //清除上一次记录
	if( inputValue.match("\,")){
		inputValue = inputValue.split(",");
		for(var i=0;i<inputValue.length;i++){
			roleObj.datagrid('selectRecord',inputValue[i]);
		}
	}else{
		roleObj.datagrid('selectRecord',inputValue);
	}
}
function saveEdit() {
	var userId = document.getElementById("userId").value;
	var selectRows = $('#list_data2').datagrid('getSelections');
	var ids = new String;
	for(var i=0; i<selectRows.length; i++){
		ids = ids + selectRows[i].roleId+",";
	}
	ids = ids.substring(0, ids.length-1);
	$.post("../system/user_role_change.action",{"roleIds":ids,"userId":userId},function(){
		$.messager.show({
			title:'提示',
			msg:':-) 修改成功,5秒后自动关闭',
			timeout:5000,
			showType:'slide'
		});
	});
}