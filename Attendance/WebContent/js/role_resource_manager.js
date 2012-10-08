$(document).ready(function(){
	var resourceObj = $('#list_data2');
//	total=1;
	$.post("../system/resource_data.action",function(data){
		resourceObj.datagrid('loadData',data);
//		total = data.total;
	});
	$('#list_data').datagrid({ 
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
        columns:[[
                  {field:'roleDesc',title:'角色名称',width:495}
                 ]],
        onClickRow:function(rowIndex, rowData){
        	var roleId = document.getElementById("roleId");
        	roleId.value = rowData.roleId; //保存roleId值
        	$.get('../system/role_resource_relation.action',{"role.roleId":rowData.roleId},function(data){
        		resourceObj.datagrid('unselectAll'); //清除上一次记录
        		var idValue = data.resourceIds;
        		if(typeof idValue != "undefined"){
            		var inputValue = document.getElementById("resourceIds");
            		inputValue.value = idValue;  //保存resourceIds值
            		if( idValue.match("\,")){
            			idValue = idValue.split(",");
            			for(var i=0;i<idValue.length;i++){
            				resourceObj.datagrid('selectRecord',idValue[i]);
                		}
            		}else{
            			resourceObj.datagrid('selectRecord',idValue);
            		}
        		}
        	});
        }

        
    }); 
	
    //=========================================================================
	page = 1;
	$('#list_data2').datagrid({ 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        border: false, 
        fit: true,//自动大小 
//        url:'../system/resource_data.action', 
        idField:'resourceId', 
        singleSelect:false,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {field:'resourceName',title:'角色拥有的资源',width:470},
                  {field:'ck',checkbox:true}
                 ]],
    }); 
    var p = resourceObj.datagrid('getPager'); 
    
    $(p).pagination({
    	onSelectPage:function(pageNumber, pageSize){
    		$.post("../system/resource_data.action",{"page":pageNumber,"rows":pageSize},function(data){
//    			page = pageNumber; //保存当前页数
    			resourceObj.datagrid('loadData',data);
//    			jQuery.extend(resourceObj, {pageNumber: pageNumber});
//    			resourceObj.datagrid({
//    				pageNumber:pageNumber
//    			});
    			resourceObj.datagrid('unselectAll'); //清除上一次记录
    			var idValue = document.getElementById("resourceIds").value;
        		if( idValue.match("\,")){
        			idValue = idValue.split(",");
        			for(var i=0;i<idValue.length;i++){
        				resourceObj.datagrid('selectRecord',idValue[i]);
            		}
        		}else{
        			resourceObj.datagrid('selectRecord',idValue);
        		}
    		});
    	},
    	onChangePageSize:function(pageSize){
    		$.post("../system/resource_data.action",{"page":1,"rows":pageSize},function(data){
    			resourceObj.datagrid('loadData',data);
//    			alert(page);
//    			resourceObj.datagrid({
//    				pageNumber:page
//    			});
    			resourceObj.datagrid('unselectAll'); //清除上一次记录
    			var idValue = document.getElementById("resourceIds").value;
        		if( idValue.match("\,")){
        			idValue = idValue.split(",");
        			for(var i=0;i<idValue.length;i++){
        				resourceObj.datagrid('selectRecord',idValue[i]);
            		}
        		}else{
        			resourceObj.datagrid('selectRecord',idValue);
        		}
    		});
    	},
//    	onBeforeRefresh:function(){
//	        $(this).pagination('loading');
////	        alert('before refresh');
//	        $(this).pagination('loaded');
//    	},
//    	alert(page)
    });
});

function resetEdit() {
	var inputValue = document.getElementById("resourceIds").value;
	var resourceObj = $('#list_data2');
	resourceObj.datagrid('unselectAll'); //清除上一次记录
	if( inputValue.match("\,")){
		inputValue = inputValue.split(",");
		for(var i=0;i<inputValue.length;i++){
			resourceObj.datagrid('selectRecord',inputValue[i]);
		}
	}else{
		resourceObj.datagrid('selectRecord',inputValue);
	}
}
function saveEdit() {
	var roleId = document.getElementById("roleId").value;
	var selectRows = $('#list_data2').datagrid('getSelections');
	var ids = new String;
	for(var i=0; i<selectRows.length; i++){
		ids = ids + selectRows[i].resourceId+",";
	}
	ids = ids.substring(0, ids.length-1);
	$.post("../system/role_resource_change.action",{"resourceIds":ids,"roleId":roleId},function(){
		$.messager.show({
			title:'提示',
			msg:':-) 修改成功,5秒后自动关闭',
			timeout:5000,
			showType:'slide'
		});
	});
}