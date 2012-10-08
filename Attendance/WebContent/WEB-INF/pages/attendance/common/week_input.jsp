<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
　     <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.edatagrid.js"></script>
	<%-- <script type="text/javascript" src="${ctx}/js/week_input.js"></script> --%>
	
<script type="text/javascript">
$(document).ready(function(){
	
	eval("var colVal=[{'text':'是'},{'text':'否'}]");	
	var weekId = "${param.weekId}";
	var week = document.getElementById("week");
	week.value = weekId;
	var obj = $('#list_data');
	var h = document.documentElement.clientHeight*1;
	var w = document.documentElement.clientWidth*1;
 	obj.edatagrid({
		url: 'detail_data.action?weekId='+weekId,  
	    saveUrl: 'detail_save.action?weekId='+weekId,  
	    updateUrl: 'detail_update.action',  
	    destroyUrl: 'detail_destroy.action',
 	    idField:'detailId',
        loadMsg:'数据加载中,请稍后...',
        striped: true, 
        border: false, 
        height: 350,
        witdh: 'auto',
//        fit: true,//自动大小 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {title:'姓名',field:'userName',width:'120',rowspan:2,align:'center',
                	  editor:{type:'text',options:{required:true}}
                  },
                  {title:'迟到',field:'detailLate',width:'80',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'早退',field:'detailEarly',width:'80',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'旷课',field:'detailQuit',width:'80',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'事假',field:'detailAffair',width:'80',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'病假',field:'detailIll',width:'80',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'公假',field:'detailPub',width:'80',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'日期',field:'detailTime',width:'100',rowspan:2,align:'center',
                	  editor:{type:'datebox',options:{required:true}}
                  },
                  {title:'销假情况',field:'detailClear',width:'120',rowspan:2,align:'center',
                	formatter:function(value){
                		if("是" == value)
                			return "是";
                		if("否" == value)
                			return "否";
                		if("" == value || (value == null))
                			return "";
                	  },
                	  editor:{
                		  type:'combobox',
                		  options:{
                			    valueField:'text',  
                			    textField:'text',
								data:colVal
                		  }
                	  }
                  }
                 ]],	
      	 toolbar:[{
 					text:'增加',
 					iconCls:'icon-add',
 					handler:function(){
 						obj.edatagrid('addRow');
 					}
 				},'-',{
 					text:'删除',
 					iconCls:'icon-remove',
 					handler:function(){
 						obj.edatagrid('destroyRow');
 					}
 				},'-',{
 					text:'保存',
 					iconCls:'icon-save',
 					handler:function(){
 						obj.edatagrid('saveRow');
 					}
 				},'-',{
 					text:'取消',
 					iconCls:'icon-undo',
 					handler:function(){
 						obj.edatagrid('cancelRow');
 					}
 				},'-',{
 					text:'查找',
 					iconCls:'icon-search',
 					handler:function(){
 						$('#dlg3').dialog('setTitle','查找').dialog('open');
 					}
 				},'-',{
 					text:'单周汇总',
 					iconCls:'icon-edit',
 					handler:function(){
 						$.post("detail_calculate.action",{"weekId":weekId},function(data){
  							$('#tt').datagrid({
 						        loadMsg:'数据加载中,请稍后...',
 						        striped: true, 
 						        border: false, 
 						        width:750,
 						        height:250,
// 						        fit: true,//自动大小 
 						        singleSelect:true,//是否单选 
 						        rownumbers:true,//行号 
 						        columns:[[
 						                  {title:'姓名',field:'userName',width:'100',rowspan:2,align:'center'   },
 						                  {title:'迟到',field:'detailLate',width:'70',rowspan:2,align:'center'  },
 						                  {title:'早退',field:'detailEarly',width:'70',rowspan:2,align:'center' }, 
 						                  {title:'旷课',field:'detailQuit',width:'70',rowspan:2,align:'center'  }, 
 						                  {title:'事假',field:'detailAffair',width:'70',rowspan:2,align:'center'},
 						                  {title:'病假',field:'detailIll',width:'70',rowspan:2,align:'center'   },
 						                  {title:'公假',field:'detailPub',width:'70',rowspan:2,align:'center'   },
 						                  {title:'销假情况',field:'detailClear',width:'100',rowspan:2,align:'center'}
 						                 ]]
 							});
  							$('#tt').datagrid('loadData',data);
 							$('#dlg').dialog('setTitle','周汇总').dialog('open');
//							$('#tt').datagrid('reload');
// 							alert(data);
 						});
 						}
 				},'-',{
 					text:'导入数据',
 					iconCls:'icon-add',
 					handler:function(){
 						$('#dlg2').dialog('setTitle','导入Excel数据').dialog('open');
 					}
 				}]
 	
 	});
 	
 	//----------------------------------------

 	//----------------------------------------

});
function query() {
	var weekId = document.getElementById("week").value;
	var userName = document.getElementById("userName").value;
	$.post("detail_query.action",{"weekId":weekId,"userName":userName},
			function(data){
    			$('#list_data').edatagrid('loadData',data);
				$('#dlg3').dialog('close');
	});
}
function doQuery(event) {
	if(event.keyCode == 13){
		query();
	}
}
function importData() {
	var weekId = document.getElementById("week").value;
		$('#ff').form('submit',{
	        url:"detail_import.action?weekId="+weekId,
	        success:function(data){
	        	$('#dlg2').dialog('close');
	        	$.messager.show({
	    			title:'提示',
	    			msg:':-) 导入成功,5秒后自动关闭',
	    			timeout:5000,
	    			showType:'slide'
	    		});
	        	$('#list_data').edatagrid('reload');
	        }
		});	
		$('#dlg2').dialog('close');
    	$.messager.show({
			title:'提示',
			msg:':-) 导入成功,5秒后自动关闭',
			timeout:5000,
			showType:'slide'
		});
    	$('#list_data').edatagrid('reload');
}

</script>
<title></title>
</head>
<div style="font-size: 14px;color: red;"><s:actionmessage/></div>
<!-- <body>
    <table id="list_data" title="2008级 网络工程2班 第  3 周 考勤"  style="padding:0px;text-align:center"  
            toolbar="#toolbar" pagination="true" striped: "true" border="false"
            rownumbers="true" fitColumns="true" singleSelect="true">  
        <thead>  
            <tr>  
                <th field="userName" width="100" editor="{type:'text',options:{required:true}}" align="center">姓名</th>  
                <th field="detailLate" width="50" editor="numberbox" align="center">迟到</th>  
                <th field="detailEarly" width="50" editor="numberbox" align="center">早退</th>  
                <th field="detailQuit" width="50" editor="numberbox" align="center">旷课</th>  
                <th field="detailAffair" width="50" editor="numberbox" align="center">事假</th>  
                <th field="detailIll" width="50" editor="numberbox" align="center">病假</th>  
                <th field="detailPub" width="50" editor="numberbox" align="center">公假</th>  
                <th field="detailTime" width="80" editor="{type:'datebox',options:{required:true}}" align="center">日期</th>  
                <th field="detailClear" width="100" editor="{type:'text'}" align="center">销假情况</th>  
            </tr>  
        </thead>  
    </table>  
    <div id="toolbar">  
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#list_data').edatagrid('addRow')">增加</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#list_data').edatagrid('destroyRow')">删除</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#list_data').edatagrid('saveRow')">保存</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#list_data').edatagrid('cancelRow')">取消</a>  
    </div>  
</body> -->
<!-- <body class="easyui-layout">
	<div region="west" border="false" split="false" fit="auto" style="padding:2px;">
		<table id="list_data"></table>
	</div>
	<div region="center" style="display:none">
	</div>
</body> -->
<body>
	<div style="padding: 0; margin: 0">
		<table id="list_data"></table>
	</div>
	<div id="dlg" class="easyui-dialog" style="width:800px;height:300px;"
					closed="true" modal="false" buttons="#dlg-buttons">
				<div>
<!-- 					<table id="tt" style="padding:0px;text-align:center"  
				            striped="true" border="true"
				            rownumbers="true" fitColumns="true" singleSelect="true">  
				        <thead>  
				            <tr>  
				                <th field="userName" width="100" editor="{type:'text',options:{required:true}}" align="center">姓名</th>  
				                <th field="detailLate" width="50" editor="numberbox" align="center">迟到</th>  
				                <th field="detailEarly" width="50" editor="numberbox" align="center">早退</th>  
				                <th field="detailQuit" width="50" editor="numberbox" align="center">旷课</th>  
				                <th field="detailAffair" width="50" editor="numberbox" align="center">事假</th>  
				                <th field="detailIll" width="50" editor="numberbox" align="center">病假</th>  
				                <th field="detailPub" width="50" editor="numberbox" align="center">公假</th>  
				                <th field="detailClear" width="100" editor="{type:'text'}" align="center">销假情况</th>  
				            </tr>  
				        </thead>  
				    </table>   -->
				    <form name="saveForm" action="week_calculate.action" method="post">
				    	<table id="tt"></table>
				    	<input type="hidden" id="week" name="weekId"/>
				    </form>
				</div>
				<div id="dlg-buttons" style="text-align:center">
					<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:document.saveForm.submit()">保存为Excel</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
				</div>
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width:350px;height:100px;"
			closed="true" modal="true" buttons="#dlg-buttons">
		<div style="text-align:center;">
			<form id="ff" name="ff" method="post" enctype="multipart/form-data">
				<input type="file" name="file" id="fileInput">
			</form>
		</div>
		<div id="dlg-buttons" style="text-align:center">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:importData();">导入</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')">关闭</a>
		</div>
	</div>
	<div id="dlg3" class="easyui-dialog" style="width:350px;height:100px;"
			closed="true" modal="true" buttons="#dlg-buttons">
		<div style="text-align:center;">
			<!-- <form id="queryForm" method="post"> -->
				姓名<input type="text" id="userName" name="userName" onkeydown="doQuery(event)">
			<!-- </form> -->
		</div>
		<div id="dlg-buttons" style="text-align:center">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:query()">查找</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg3').dialog('close')">关闭</a>
		</div>
	</div>
</body>
</html>