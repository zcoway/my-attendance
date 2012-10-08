$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'请假条列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../leave/auth_data.action', 
        idField:'leaveId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'请假者',field:'leaveUser',width:'90',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "../leave/leave_edit.action?leaveId="+ rowData.leaveId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'学生代理',field:'leaveDelegate',width:'90',rowspan:2,align:'center'},
                  {title:'受理教师',field:'leaveTeacher',width:'90',rowspan:2,align:'center'},
                  {title:'请假时间',field:'leaveInfo',width:'150',rowspan:2,align:'center'},
                  {title:'请假原因',field:'leaveReason',width:'300',rowspan:2,align:'center'},
                  {title:'操作日期',field:'leaveTime',width:'90',rowspan:2,align:'center'},
                  {title:'学生审核 ',field:'flagName',width:'90',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  if(value=="未审核")
                			  return "<span style="+"color:red;font-weight:600"+">"+value+"</span>";
                		  else
                			  return value;
                	  }
                  },
                  {title:'教师审核 ',field:'statusName',width:'90',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  if(value=="未审核")
                			  return "<span style="+"color:red;font-weight:600"+">"+value+"</span>";
                		  else
                			  return value;
                	  }
                  }
                 ]],
        toolbar: [{ 
            text: '删除', 
            iconCls: 'icon-remove', 
            handler: function(){
            	var url = getDelUrl();
            	var leaveId = getSelectedRowId();
            	$.get(url,{"leave.leaveId":leaveId},function(){
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
			return selected.leaveId;
		}
		return null;
	}
