$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'角色列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../system/role_data.action', 
        idField:'roleId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'角色名称',field:'roleName',width:'200',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "../system/role_edit.action?roleId="+ rowData.roleId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'角色描述',field:'roleDesc',width:'200',rowspan:2,align:'center'},
                  {title:'是否可用',field:'roleEnabled',width:'150',rowspan:2,align:'center'}
                 ]],
        toolbar: [{ 
            text: '添加', 
            iconCls: 'icon-add', 
            handler: function() { 
            	var url = getAddUrl();
            	openWindow(url,"add",{title:"增加"});
            	$('#list_data').datagrid('reload');
            } 
        },  '-',{ 
            text: '删除', 
            iconCls: 'icon-remove', 
            handler: function(){
            	var url = getDelUrl();
            	var roleId = getSelectedRowId();
            	$.get(url,{"role.roleId":roleId},function(){
            		$.messager.alert('提醒','删除成功');
            		$('#list_data').datagrid('reload');
            		
            	});
            } 
        }], 
    }); 
});

function getSelectedRowId(){
		var selected = $('#list_data').datagrid('getSelected');
		if (selected){
			return selected.roleId;
		}
		return null;
	}
