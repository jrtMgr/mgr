var $ = document.getElementById; 
var $$ = document.getElementsByName;

var _private_Login_Status = false;

var HeightMinus = 0;
var WindowHeight = window.screen.height;
var WindowWidth = window.screen.width;
if(WindowHeight==800 && WindowWidth==1280){
	/*分辨率是1280*800*/
	HeightMinus = 27;
}else if(WindowHeight==768 && WindowWidth==1024){
	/*分辨率是1024*768*/
	HeightMinus = 57;
}else if(WindowHeight==900 && WindowWidth==1440){
	/*分辨率是1440*900*/
	HeightMinus = 22;
}else{
	/*默认是按照1024*768的标准分辨率，保证所有的页面的按钮都可以看到最后一行的按钮*/
	HeightMinus = 57;
}

function checkPageNumber()   
	{   
		var vPageIndex = $('recordNum');
		if(vPageIndex.value!='' && vPageIndex.value!=null){
		if(!/^\d+$/.test(vPageIndex.value)){
			alert('查询的页数要输入大于0的数字!');
			vPageIndex.value='';
			vPageIndex.focus();
		}
	}
	}  
	function checkPageIsNull(totalPageNum)   
	{   
	var vPageIndex = $('pageIndex');
	if(vPageIndex.value==null || vPageIndex.value==''){
		alert('请输入要查询的页数!');
		vPageIndex.value='';
		vPageIndex.focus();
		return false;
    }else{
		if(vPageIndex.value>totalPageNum){
			alert('输入的查询页数超出最大记录页数,请重新输入!');
		    vPageIndex.value='';
		    vPageIndex.focus();
		    return false;
		}else{
			return true;
		}
    }
	}
/*控制文本框小数位数*/
/*_value 文本框ID，_num:小数位数*/
function numberic(_value,_num)
{	
	var str = Trim($(_value).value);
	if(str.length == 0)
	{
		$(_value).value = '';
		return;
	}
	var val = parseFloat(str);
	$(_value).value = val.toFixed(_num);
	/* 如果输入多个小数点那么返回空 */
	if($(_value).value == 'NaN')
	{
		$(_value).value = '';
	}
}
/* 控制文本框数值的最大值 */
/*_value 文本框ID，_num:最大值*/
function maxNum(_value,_num)
{
	var obj = $(_value);
	var str = Trim(obj.value);
	if(str.length == 0)
	{
		obj.value = '';
		return;
	}
	var val = parseFloat(str);
	if (val >= _num)
	{
		alert('数值要小于：' + _num);
		obj.value = '';
		obj.focus();
	}
}
/* 只能输入数值 */
function onlyNum()
{
	if(event.keyCode == 13){
		if(isNaN(Math.round(event.srcElement.value))){
			event.srcElement.value = 100;
		}else{
			event.srcElement.value = Math.round(event.srcElement.value);
		}
	}
	if(event.shiftKey&&event.keyCode==190)
	{
		event.returnValue=false; 
		return;
	}
	//window.alert(event.keyCode);
	if(event.keyCode==46||event.keyCode==8||event.keyCode==37||event.keyCode==39)
	{
		return;
	}
	
	if(event.keyCode==110||event.keyCode==190)
	{
		return;
	}
	
	if(! ((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))   ) 
	{
		//考虑小键盘上的数字键			
		event.returnValue=false; 
		return;
	}
} 
/* 只能输入数字和英文 */
function onlyNumAndEng()
{
	if(event.keyCode==46||event.keyCode==8||event.keyCode==37||event.keyCode==39)
	{
		return;
	}
	if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=97&&event.keyCode<=122)||(event.keyCode>=65&&event.keyCode<=90)) )
	{
		event.returnValue=false; 
		return;
	}   
}
/**//*    Trim:清除两边空格 
        @str        要处理的字符集
    */
function Trim (str) 
 {
     if (typeof str == 'string') return str.replace(/(^\s*)|(\s*$)/g, '');
 }

/*
*设置指定控件的属性值 
*/
function sethiddenvalue(elementId,elementValue)
{
	document.getElementById(elementId).value=elementValue;
}
/*
*提交 
*/
function formsubmit(formobj)
{
	formobj.submit();
}

/*
 * 隐藏窗体加载效果
 */ 
function hideLayer(_hidden)
{
	this._hidden = _hidden?_hidden:"hidden";

	// IE5+, NN6+
	if (document.getElementById)
	{
		oElement = document.getElementById("ie");
	// IE4
	}else if (document.all){
		oElement = document.all("ie");
	// NN4
	}else if (document.layers){
		oElement = document.layers["ns"];
	}

	if (oElement != null && document.layers)
	{
		oElement.visibility = this._hidden;
	}else if (oElement != null){
		oElement.style.visibility = this._hidden;
	}
}

/*
 * 重设高度
 * @params 控件名称
 * @params 操作工具栏高度
 */
function resizeHeight(oElementByName,oToolHeight)
{
	var clientHeight = document.body.clientHeight;
	this.oElementByName = oElementByName?oElementByName:"eachitem";
	this.oToolHeight = oToolHeight?oToolHeight:30;

	if (document.body.clientHeight > this.oToolHeight)
	{
		try {
			document.getElementById(this.oElementByName).height = parseInt(clientHeight - this.oToolHeight);
		}catch(e){;}
	}
}

/*
 * 该功能类似 ASP 里的 Request,获取并分析URL的附带参数
 * @params 需要获取参数名称
 * @params 需要获取参数的地址
 */
function request(QueryString,strHref)
{
	var strParameter;
	strHref = strHref?strHref:window.location.href;

	if(strHref.search(/\?/)!=-1)
	{
		strHref=strHref.substr(strHref.search(/\?/)+1);
		strHref=strHref.split(/&/);
		for(var i = 0; i<strHref.length; i++)
		{
			if(strHref[i].search("^"+QueryString+"=")!=-1)
			{
				strParameter=strHref[i].substr(QueryString.length+1)
			}
		}
		
		return(strParameter);
	}
}

/*
 * 附选框全选控件(BUG)
 * 函数使用：表单名称，不被选择对象
 */
function checkBoxALL(oForm,object,oSelect,oviewType)
{
	var displayValue;
	var num = 0;
	this.oviewType = oviewType?oviewType:"normal";
	
	if (object.name == oSelect)
	{
		for (var i=0;i<oForm.elements.length;i++)
		{
			var e = oForm.elements[i];
			if (e.type == "checkbox")
			{
				if ((e.name != oSelect) && (e.name != "status"))
				{
					if (object.checked)
					{
						e.checked = true;
						displayValue = "block";
					}else{
						e.checked = false;
						displayValue = "none";
					}

					if (this.oviewType == "view")
					{
						eval(oSelect+"_inner").style.display = "block";
					}else if (this.oviewType == "noview"){
						eval(oSelect+"_inner").style.display = "none";
					}else{
						eval(oSelect+"_inner").style.display = displayValue;
					}
				}
			}
		}
	}else{
		if (object.checked)
		{
			object.checked = true;
			eval(oSelect+"_inner").style.display = "block";
		}else{

			object.checked = false;

			for (var i=0;i<oForm.elements.length;i++)
			{
				var e = oForm.elements[i];
				if (e.type == "checkbox")
				{
					if ((e.name != oSelect) && (e.name != "status") && (e.checked))
					{
						num += 1;
					}
				}
			}
			
			if (num == 0)
			{
				eval(oSelect+"_inner").style.display = "none";
			}
		}
	}
	num = 0;
}

/**
 * Object.HTMLEncode("1 = 1")
 * @Params 输入 字符串
 * @Return 字符串
 */
function HTMLEncode(text)
{
	if ( typeof( text ) != "string" )
		text = text.toString() ;

	text = text.replace(/&/g, "&amp;") ;
	text = text.replace(/"/g, "&quot;") ;
	text = text.replace(/</g, "&lt;") ;
	text = text.replace(/>/g, "&gt;") ;
	text = text.replace(/'/g, "&#39;") ;
	text = text.replace(/\\n/g, "\n") ;
	text = text.replace(/\\r/g, "\r") ;

	return text ;
}

/**
 * Object.IIf("1 = 1","on","off")
 * @Params 必选项。字符串匹配条件 
 * @Params 必选项。条件为真返回
 * @Params 必选项。条件为假返回
 * @Return 字符串
 */
function IIf(strParameter,blTrue,blFalse)
{
	this.blTrue = blTrue?blTrue:true;
	this.blFalse = blFalse?blFalse:false;
	
	if (strParameter)
	{
		return (blTrue);
	}else{
		return (blFalse);
	}
}

/*
 * 格式化货币样式
 * @params 类似VBSCRIPT里的FormatCurrency
 * @params 输入货币值
 */
function formatCurrency(num)
{
	num = num.toString().replace(/\$|\,/g,'');

	if(isNaN(num))

	num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();

	if(cents<10)
	{
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		{
			num = num.substring(0,num.length-(4*i+3))+','+
			num.substring(num.length-(4*i+3));
		}
	
	}

	return (((sign)?'':'-') + num);
}

function formatUrlForReload(_url,_params)
{
	if (_url.toString().indexOf("?") == -1)
	{
		return (_url + "?" + _params);
	}else{
		return (_url + "&" + _params);
	}
}

/*
 * 通过XMLHTTP判断当前用户是否登录
 * @params 返回为0将自动注销
 * @remark
 * 
 */
function IsLogin()
{
	if (!_private_Login_Status)
	{
		var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")

		xmlhttp.onreadystatechange = getreadystatechange;
		xmlhttp.open("GET","servlet/modules/member/process.asp?action=isign",false)
		xmlhttp.send()

		function getreadystatechange()
		{
			if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200))
			{
				if (xmlhttp.responseText == 0)
				{
					_private_Login_Status = false;
					top.location = "servlet/modules/member/process.asp?action=signout";
				}
			}else{
				_private_Login_Status = true;
			}
		}

		delete(xmlhttp);
	}

	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
	xmlhttp.onreadystatechange = getreadystatechangebyversion;
	xmlhttp.open("GET","servlet/modules/configuration/version.asp",false)
	xmlhttp.send()

	function getreadystatechangebyversion()
	{
		if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200))
		{
			var _param = xmlhttp.responseText.toString().split("|");
			top.frames["main"].frames["frmtoolbar"].CurrentVersion.innerHTML = _param[0]?_param[0]:"未知";
			top.frames["main"].frames["frmtoolbar"].CurrentVersionLastRelease.innerHTML = _param[1]?_param[1]:"未知";
		}
	}
}

/*
 * 获取系统所运行的根目录参数类
 * @params 根据系统条件分析: scripting,servlet 三个特殊地址
 * @params 获取系统跟目录地址路径;
 * @remark
 * 
 */
function object_root()
{
	this.parameter;
	this.path;

	// <summary>
	// 获取系统所运行的根目录地址
	// </summary>
	this.InitializeRootVirtualPath = function()
	{
		var thisUrl = window.location.href.toString();
		var arrUrlParams = this.parameter.split(",");

		for (var i = 0;i <= arrUrlParams.length ; i++ )
		{
			if (thisUrl.indexOf(arrUrlParams[i]) > 0)
			{
				thisUrl = thisUrl.substring(0,thisUrl.indexOf(arrUrlParams[i]));
				break;
			}

			if (i == arrUrlParams.length)
			{
				thisUrl = thisUrl.substring(0,thisUrl.lastIndexOf("/")+1);

				if (thisUrl.length = 0)
				{
					thisUrl = thisUrl.substring(0,thisUrl.lastIndexOf("\\")+1);
				}
				break;
			}
		}
		
		this.path = thisUrl;
	}
}

	//o  目标id page 页数 	取得该id下所有的input和select的值,用于条件返回
function getElements(o,page){
	var tar = [];
	var inputs = document.getElementById(o).getElementsByTagName('input');
	for(var i=0;i<inputs.length;i++){
		inputs[i].type != 'button' && 
			(inputs[i].type == 'checkbox' && tar.push('<input name='+inputs[i].name+' value='+(inputs[i].checked?'on':'')+'>')
			|| tar.push('<input name='+inputs[i].name+' value='+inputs[i].value+'>'));
	}
	var select = document.getElementById(o).getElementsByTagName('select');
	for(var i=0;i<select.length;i++){
		tar.push('<input name='+select[i].name+' value='+select[i].value+'>');
	}
	tar.push('<input name=pageNum value='+page+'>');
	$('recordNum') && tar.push('<input name=recordNum value='+$('recordNum').value+'>');
	return tar.join('');
}

//返回到主页面 o为主页面地址,param为getElements获取到的条件
function returnToMain(o,param){
	var tar = document.createElement("form");
	tar.action = o;
	tar.method = 'post';
	tar.name = 'tarform';
	tar.style.display = 'none';
	tar.innerHTML = param;
	document.body.appendChild(tar);
	tar.submit();
}


/* 打开、关闭机构树、产品树 */
function opentree(elementId,b)
{
	var DivRef = document.getElementById(elementId);
	var IfrRef = document.getElementById('DivShim');//ifram
	DivRef.style.display = "block";
	IfrRef.style.width = DivRef.offsetWidth;
	IfrRef.style.height = DivRef.offsetHeight;
	IfrRef.style.top = DivRef.style.top;
	IfrRef.style.left = DivRef.style.left;
	IfrRef.style.zIndex = DivRef.style.zIndex - 1;
	IfrRef.style.display = "block";
	
	document.getElementById(elementId).style.display = b;
	document.getElementById('DivShim').style.display = b;
}

//转到第n页totalpage总页数 totalRec总记录数
function gotoPage(totalPage,totalRec){
	if(event.keyCode == 13){
		if($('recordNum') && $('pageIndex') && $('pageNum') && checkPageIsNull(totalPage)){
			if((totalRec/$('recordNum').value)+1>$('pageIndex').value){
				$('pageNum').value = $('pageIndex').value;
			}
			($('recordNum').onkeydown)();
		}
	}
}

function resetSize(param){
	if(param){
		$('divData').style.height=document.body.offsetHeight-$('tblCondition').clientHeight-HeightMinus+Number(param);
	}else{
		$('divData').style.height=document.body.offsetHeight-$('tblCondition').clientHeight-HeightMinus;
	}
}

function initContentSize(param){
	try{
		resetSize(param);
	}catch(e){
		$('tblCondition').attachEvent('onresize',function(){resetSize(param);});
	}
}

var root = new object_root();
root.parameter = "frame.html,redirect.html,cache,images,scripting,servlet,stylesheet";
root.InitializeRootVirtualPath()