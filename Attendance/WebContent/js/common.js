function openWindow(url,type,winParam){
	var iframeId = "iframe_"+type;
	var iframeDivId = "iframe_div_"+type;
	var winDiv="<div id='"+iframeDivId+"' class='easyui-panel' title='"+winParam.title+"'><iframe id='"+iframeId+"' src='"+url+"' scrolling='auto' frameBorder='0' width='100%' height='100%'></iframe></div>";
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
				$("#"+iframeId).height($("#"+iframeDivId).height());
				$("#"+iframeId).width($("#"+iframeDivId).width()-10);
			}
		};
	winObj.window(winParam);
	if(typeof windowParams=="object")
	{
		jQuery.extend(winParam,windowParams);
	}
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
		      iframes[i].winObject.window("close");
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
		      iframes[i].winObject.window("close");
		      return;
		     }
		    }
		   }
		  }
		 });
}

function closeWin(){
	var iframes = $(parent.document).find("iframe");
	if (typeof iframes == "object") {
		for ( var i = 0; i < iframes.length; i++) {
			if (typeof iframes[i].winObject == "object") {
				iframes[i].winObject.window("close");
				return;
			}
		}
	}
	// 调用closeWindow无法返回的解决方法
	iframes = $(document).find("iframe");
	if (typeof iframes == "object") {
		for ( var i = 0; i < iframes.length; i++) {
			if (typeof iframes[i].winObject == "object") {
				iframes[i].winObject.window("close");
				return;
			}
		}
	}
}
/*弹出修改页面*/
function edit(url) {
	openWindow(url, "edit", {title:"修改"});
}
/*弹出录入数据页面*/
function inputData(url) {
	openWindow(url, "inputData", {title:"录入考勤数据"});
}