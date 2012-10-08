$(document).ready(function(){
	var obj = $('#list_data');
	/*	var lastIndex;
	obj.datagrid({ 
        title:'2008级网络工程2班 第 3 周考勤 ', 
        loadMsg:'数据加载中,请稍后...',
        nowrap: false, 
        striped: true, 
        border: false, 
        fit: true,//自动大小 
//        url:'detail_data.action', 
        saveUrl: 'detail_save.action',  
	    updateUrl: 'detail_update.action',  
	    destroyUrl: 'detail_destroy.action',
        idField:'detailId', 
        singleSelect:true,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        columns:[[
                  {title:'姓名',field:'userName',width:'100',rowspan:2,align:'center',
                	  editor:{type:'text',options:{required:true}}
                  },
                  {title:'迟到',field:'detailLate',width:'50',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'早退',field:'detailEarly',width:'50',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'旷课',field:'detailQuit',width:'50',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'事假',field:'detailAffair',width:'50',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'病假',field:'detailIll',width:'50',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'公假',field:'detailPub',width:'50',rowspan:2,align:'center',
                	  editor:{type:'numberbox'}
                  },
                  {title:'日期',field:'detailTime',width:'80',rowspan:2,align:'center',
                	  editor:{type:'datebox',options:{required:true}}
                  },
                  {title:'销假情况',field:'detailClear',width:'100',rowspan:2,align:'center',
                	  editor:{type:'text'}
                  }
                 ]],
 		toolbar:[{
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						obj.datagrid('endEdit', lastIndex);
						obj.datagrid('appendRow',{
							userName:'',
							detailLate:0,
							detailEarly:0,
							detailQuit:0,
							detailAffair:0,
							detailIll:0,
							detailPub:0,
							detailTime:'',
							detailClear:''
						});
						var lastIndex = obj.datagrid('getRows').length-1;
						obj.datagrid('selectRow', lastIndex);
						obj.datagrid('beginEdit', lastIndex);
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						var row = obj.datagrid('getSelected');
						if (row){
							var index = obj.datagrid('getRowIndex', row);
							obj.datagrid('deleteRow', index);
						}
					}
				},'-',{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						obj.datagrid('acceptChanges');
						var insertRows = obj.datagrid('getChanges','inserted');
						var updateRows = obj.datagrid('getChanges','updated');
						var deleteRows = obj.datagrid('getChanges','deleted');
						var changesRows = {
								inserted : [],
								updated : [],
								deleted : [],
								};
							if (insertRows.length>0) {
								for (var i=0;i<insertRows.length;i++) {
									changesRows.inserted.push(insertRows[i]);
								}
							}

							if (updateRows.length>0) {
								for (var k=0;k<updateRows.length;k++) {
									changesRows.updated.push(updateRows[k]);
								}
							}
							
							if (deleteRows.length>0) {
								for (var j=0;j<deleteRows.length;j++) {
									changesRows.deleted.push(deleteRows[j]);
								}
							}
					}
				},'-',{
					text:'取消',
					iconCls:'icon-undo',
					handler:function(){
						obj.datagrid('rejectChanges');
					}
				},'-',{
					text:'getChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = obj.datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}],
				
		onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						obj.datagrid('endEdit', lastIndex);
						obj.datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
		}*/
		
		/*		onAfterEdit:function(rowIndex, rowData, changes){
			alert("before saveRow");
			obj.datagrid('saveRow');
			alert("after saveRow");
		}
				onAdd:function(index,row){
			alert("onAdd");
		},
		onBeforeSave:function(index){
			alert("onBeforeSave");
		},
		onSave:function(index,row){
			alert("onSave");
		},
		onDestroy:function(index,row){
			alert("onDestroy");
		}
    }); */
    obj.edatagrid({  
    	url:'detail_data.action', 
        saveUrl: 'detail_save.action',  
	    updateUrl: 'detail_update.action',  
	    destroyUrl: 'detail_destroy.action',
    });  
	
});

function getSelectedRowId(){
		var selected = $('#list_data').datagrid('getSelected');
		if (selected){
			return selected.detailId;
		}
		return null;
}
