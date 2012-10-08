$(document).ready(function(){
	$('#list_data').datagrid({ 
        title:'站内信列表', 
        loadMsg:'数据加载中,请稍后...',
        iconCls:'icon-edit',//图标 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
        url:'../message/message_data.action', 
        idField:'messageId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[
                  {title:'发送者',field:'senderName',width:'150',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  var url = "../message/message_edit.action?messageId="+ rowData.messageId;
                		  return "<a href='#' onclick=edit("+"'"+url+"'"+")>"+value+"</a>";  
                	  }
                  },
                  {title:'接收者',field:'receiverName',width:'150',rowspan:2,align:'center'},
                  {title:'标题',field:'messageTitle',width:'300',rowspan:2,align:'center'},
                  {title:'日期',field:'messageTime',width:'150',rowspan:2,align:'center'},
                  {title:'状态',field:'messageRead',width:'150',rowspan:2,align:'center',
                	  formatter:function(value,rowData,rowIndex){
                		  if(value=="未读")
                			  return "<span style="+"color:red;font-weight:600"+">"+value+"</span>";
                		  else
                			  return value;
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
            	var messageId = getSelectedRowId();
            	$.get(url,{"message.messageId":messageId},function(){
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
			return selected.messageId;
		}
		return null;
	}
