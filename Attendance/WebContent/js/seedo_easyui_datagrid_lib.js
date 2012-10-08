/**
 * 格式化超链接函数
 * @param value 当前字段的值
 * @param rowData 当前行json对象
 * @param rowIndex 当前行号
 * @param extendParams {link:"${link}",linkParams:${linkParams},field:"${column}",id:"${dataGridId}"};
 * @return 返回加上超链接的字串
 */
function formatterLink(value,rowData,rowIndex,extendParams)
{
	/*
	 * extendParams={link:"url",linkParams:{...},field:"columnName",id:"dataGridId"}
	 * {linkParams:{columns:"col1,col2,...",extParams:{param1:value1,param2:value2...}},windowParams:{window properties:...}}
	 */
	try
	{
		var columnLinkParamsJson="{";
		if(extendParams.linkParams && extendParams.linkParams.columns && extendParams.linkParams.columns.length>0)
		{
			var cols=extendParams.linkParams.columns.split(",");
			for ( var i=0;i<cols.length;i++)
			{
				var column=cols[i];
				if(i==cols.length-1)
				{
					columnLinkParamsJson+=column+":'"+rowData[column]+"'";
					break;
				}
				columnLinkParamsJson+=column+":'"+rowData[column]+"',";
			}
		}
		columnLinkParamsJson+="}";
		var linkParams="";
		eval("var colJson="+columnLinkParamsJson);
		if(!jQuery.isEmptyObject(colJson))
		{
			linkParams+=jQuery.param(colJson);
		}
		if(jQuery.isPlainObject(extendParams.linkParams) && jQuery.isPlainObject(extendParams.linkParams.extParams))
		{
			var splitchr="&";
			if(linkParams.length>0)
			{
				linkParams+=splitchr;
			}
			linkParams+=jQuery.param(extendParams.linkParams.extParams);
		}
		/*
		 * 如果datagrid中有id字段则自动加上该参数
		 */
		if(rowData["id"])
		{
			linkParams="id="+rowData["id"]+(linkParams.length>0?"&"+linkParams:"");
		}
		var id=extendParams.id;
		var url=extendParams.link+"?"+linkParams;
		if(typeof extendParams.windowParams=="string")
		{
			value="<a href='javascript:openWindow(\""+url+"\",\""+id+"\","+extendParams.windowParams.replace(/'/g,"\"")+");'>"+value+"</a>";
		}
		else
		{
			value="<a href='javascript:openWindow(\""+url+"\",\""+id+"\",null);'>"+value+"</a>";
		}
	}
	catch(e)
	{
		$.messager.alert('error','extendParams参数无效:'+extendParams); 
	}
	return value;
}

/**
 * 弹出窗口形式
 * @param url 该窗口显示的url
 * @param id 唯一的id
 * @return
 */
var windowId="_window_id_";
function openWindow(url,id,windowParams)
{
	var win_iframe_id_prefix="iframe_";
	var iframeId=win_iframe_id_prefix+id;
	var iframeContainerId="iframeContainer_"+id;
	var winDiv="<div id='"+iframeContainerId+" class='easyui-panel'><iframe id='"+iframeId+"' src='"+url+"' scrolling='auto' frameBorder='0' width='100%' height='100%'></iframe></div>";
	var body=document.body;
	var winObj=$(winDiv).appendTo(body);
	var h=document.documentElement.clientHeight*0.85;
	var w=document.documentElement.clientWidth*0.85;
	var winParam={
			width:w
			,height:h
			,shadow: true
			,resizable:true
			,modal:true
			,minimizable:false
			,collapsible:false
			,onClose:function(){
				winObj.window("destroy");
			}
			,onResize:function(w,h){
				$("#"+iframeId).height($("#"+iframeContainerId).height());
				$("#"+iframeId).width($("#"+iframeContainerId).width()-10);
			}
		};
	if(typeof windowParams=="object")
	{
		jQuery.extend(winParam,windowParams);
	}
	winObj.window(winParam);
	winObj.window("open");
	var ifameObject=document.getElementById(iframeId);
	ifameObject.winObject=winObj;
}

/**
 * 关闭当前弹出式窗口
 * @return
 */
function closeWindow()
{
	$.messager.defaults={ok:"确定",cancel:"取消"};   
	$.messager.confirm('提醒','确定退出？',function(r){
		  if(r){
		   var iframes=$(parent.document).find("iframe");
		   if(typeof iframes=="object")
		   {
		    for(var i=0;i<iframes.length;i++)
		    {
		     if(typeof iframes[i].winObject=="object")
		     {
		      var datagridId=iframes[i].id.replace("iframe_","");
		      var forms=parent.document.forms;
		      iframes[i].winObject.window("close");
		      forms[0].submit();
		      return;
		     }
		    }
		   }
		   //调用closeWindow无法返回的解决方法
		   iframes=$(document).find("iframe");
		   if(typeof iframes=="object")
		   {
		    for(var i=0;i<iframes.length;i++)
		    {
		     if(typeof iframes[i].winObject=="object")
		     {
		      var datagridId=iframes[i].id.replace("iframe_","");
		      var forms=parent.document.forms;
		      iframes[i].winObject.window("close");
		      forms[0].submit();
		      return;
		     }
		    }
		   }
		  }
		 });
}

/**
 * 判断datagrid是否被form包含
 * @param id datagrid id
 * @return 有返回true,否则返回false
 */
function getFormByDatagrid(id)
{
	/*
	 * 判断表格是否在form表单中
	 */
	var forms=$("form:has(#"+id+"_column_values)");
	if(forms.length!=1)
	{
		return null;
	}
	return forms[0];
}
/**
 * 点击工具条中的新增按钮
 * @param id datagrid的id
 * @return
 */
function toolbarButtonAddEvent(id)
{
	var form=getFormByDatagrid(id);
	if(form==null)
	{
		$.messager.alert('提醒','没有HTML表单标签或多个表单标签包含此表格无法提交数据！');
		return;
	}
	/*
	 * 调用开发人员必须实现的函数名称该函数名称必须是getAddUrl()返回的结果是
	 * 新增页面的url,如果没有实现函数则系统自动调用form表单中的action指定的路径
	 * 进行提交
	 */
	var url=form.action;
	var windowParams={title:"SeeDo",iconCls:"icon-edit"};
	if(typeof getAddUrl=="function")
	{
		var urlObj=getAddUrl();
		if(typeof urlObj=="string")
		{
			url=urlObj;
		}
		else if(typeof urlObj=="object")
		{
			url=urlObj.url;
			if(typeof url!="string")
			{
				$.messager.alert('error','url不正确！');
				return;
			}
			if(typeof urlObj.windowParams=="object")
			{
				windowParams=urlObj.windowParams;
			}
		}
	}
//	location.href=url;
	openWindow(url,id,windowParams);
}

/**
 * 点击删除按钮
 * @param id
 * @return
 */
function toolbarButtonRemoveEvent(id)
{
	var form=getFormByDatagrid(id);
	if(form==null)
	{
		$.messager.alert('提醒','没有HTML表单标签或多个表单标签包含此表格无法提交数据！');
		return;
	}
	var selectedRecords=$("#"+id).datagrid("getSelections");
	if(selectedRecords.length==0)
	{
		$.messager.defaults={ok:"确定"};
		$.messager.alert("提醒","请选择记录");
		return;
	}
	$.messager.confirm('提醒','确认删除记录？',function(r){
		if(r)
		{
			if(!r)
			{
				return false;
			}
			var ids="";
			for(var i=0;i<selectedRecords.length;i++)
			{
				if(i==selectedRecords.length-1)
				{
					ids+=selectedRecords[i]["id"];
					break;
				}
				ids+=selectedRecords[i]["id"]+",";
			}
			/*
			 * 调用开发人员必须实现的函数名称该函数名称必须是getAddUrl()返回的结果是
			 * 新增页面的url,如果没有实现函数则系统自动调用form表单中的action指定的路径
			 * 进行提交
			 */
			if(typeof getRemoveUrl=="function")
			{
				var url=getRemoveUrl();
				if(url.indexOf("?")>0)
				{
					url=url+"&selectedIds="+ids;
				}
				else
				{
					url=url+"?selectedIds="+ids;
				}
				form.action=url;
				form.submit();
				return;
			}
			$("<input type='hidden' name='selectedIds' value='"+ids+"'>").appendTo(form);
			form.submit(); 
		}
	});  	
}

/**
 * 点击打印按钮
 * @param id
 * @return
 */
function toolbarButtonPrintEvent(id)
{
}

/**
 * 点击导出按钮
 * @param id
 * @return
 */
function toolbarButtonExportExcelEvent(id)
{
}