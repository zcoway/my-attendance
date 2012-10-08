$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'周考勤表列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'week_data.action', 
        idField:'weekId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'创建者姓名',field:'userName',width:'150',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "week_edit.action?weekId="+ rowData.weekId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'创建时间',field:'weekTime',width:'150',rowspan:2,align:'center'},
                  {title:'周次',field:'weekNum',width:'70',rowspan:2,align:'center'},
                  {title:'所在系',field:'deptName',width:'150',rowspan:2,align:'center'},
                  {title:'所在年级',field:'clasGrade',width:'150',rowspan:2,align:'center'},
                  {title:'所在班级',field:'clasName',width:'150',rowspan:2,align:'center'},
                  {title:'操作',field:'operation',width:'150',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "week_input.action?weekId="+rowData.weekId;
                		  return "<a href=javascript:inputData("+"'"+url+"'"+")  >"+"录入考勤信息"+"</a>"; 
                	  }
                  }
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
            	var weekId = getSelectedRowId();
            	$.get(url,{"week.weekId":weekId},function(){
            		$.messager.show({
        				title:'提示',
        				msg:':-) 删除成功,5秒后自动关闭',
        				timeout:5000,
        				showType:'slide'
        			});
            		$('#list_data').datagrid('reload');
            		
            	});
            } 
        },  '-',{ 
            text: '设置第一周', 
            iconCls: 'icon-edit', 
            handler: function(){
            	$('#dlg').dialog('setTitle','设置第一周').dialog('open');
            } 
        }], 
    }); 
	
	$('#dd').datebox({  
        required:true  
    });
});

function getSelectedRowId(){
		var selected = $('#list_data').datagrid('getSelected');
		if (selected){
			return selected.weekId;
		}
		return null;
	}
