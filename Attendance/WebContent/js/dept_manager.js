$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'系部列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../system/dept_data.action', 
        idField:'deptId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'系部名称',field:'deptName',width:'200',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "../system/dept_edit.action?deptId="+ rowData.deptId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'系部描述',field:'deptDesc',width:'200',rowspan:2,align:'center'}
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
            	var deptId = getSelectedRowId();
            	$.get(url,{"dept.deptId":deptId},function(){
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
			return selected.deptId;
		}
		return null;
	}
