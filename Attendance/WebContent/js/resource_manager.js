$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'资源列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../system/resource_data.action', 
        remoteSort:false,  
        idField:'resourceId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'资源名称',field:'resourceName',width:'120',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "../system/resource_edit.action?resourceId="+ rowData.resourceId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'资源类型',field:'resourceType',width:'150',rowspan:2,align:'center'},
                  {title:'资源值',field:'resourceValue',width:'200',rowspan:2,align:'center'},
                  {title:'资源描述',field:'resourceDesc',width:'120',rowspan:2,align:'center'},
                  {title:'父资源ID',field:'resourceParentId',width:'120',rowspan:2,align:'center'},
                  {title:'是否为叶子',field:'resourceIsLeaf',width:'80',rowspan:2,align:'center'},
                  {title:'是否可用',field:'resourceEnabled',width:'80',rowspan:2,align:'center'}
                 ]],
        toolbar: [{ 
            text: '添加', 
            iconCls: 'icon-add', 
            handler: function() { 
            	var url = getAddUrl();
            	openWindow(url,"add",{title:"增加"});
            } 
        },  '-',{ 
            text: '删除', 
            iconCls: 'icon-remove', 
            handler: function(){
            	var url = getDelUrl();
            	var resourceId = getSelectedRowId();
            	$.get(url,{"resource.resourceId":resourceId},function(){
            		$.messager.show({
        				title:'提示',
        				msg:':-) 删除成功,5秒后自动关闭',
        				timeout:5000,
        				showType:'slide'
        			});
            		$('#list_data').datagrid('reload');
            		
            	});
            } 
        }], 
    }); 
	
});

function getSelectedRowId(){
		var selected = $('#list_data').datagrid('getSelected');
		if (selected){
			return selected.resourceId;
		}
		return null;
	}
