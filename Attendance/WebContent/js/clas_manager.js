$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'班级列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../system/clas_data.action', 
        idField:'clasId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'班级名称',field:'clasName',width:'200',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "../system/clas_edit.action?clasId="+ rowData.clasId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'所属年级',field:'clasGrade',width:'200',rowspan:2,align:'center'},
                  {title:'所属系部',field:'clasDept',width:'150',rowspan:2,align:'center'},
                  {title:'班级描述',field:'clasDesc',width:'150',rowspan:2,align:'center'}
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
            	var clasId = getSelectedRowId();
            	$.get(url,{"clas.clasId":clasId},function(){
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
			return selected.clasId;
		}
		return null;
	}
