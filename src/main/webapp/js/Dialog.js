var CONTEXTPATH = "";
var scripts = document.getElementsByTagName("script");
for(var i=0;i<scripts.length;i++){
	if(/.*js\/Dialog\.js$/g.test(scripts[i].getAttribute("src"))){
		var jsPath = scripts[i].getAttribute("src").replace(/js\/Dialog\.js$/g,'');
		if(jsPath.indexOf("/")==0||jsPath.indexOf("://")>0){
			CONTEXTPATH = jsPath;
			break;
		}
		var arr1 = jsPath.split("/");
		var path = window.location.href;
		if(path.indexOf("?")!=-1){
			path = path.substring(0,path.indexOf("?"));
		}
		var arr2 = path.split("/");
		arr2.splice(arr2.length-1,1);
		for(var i=0;i<arr1.length;i++){
			if(arr1[i]==".."){
				arr2.splice(arr2.length-1,1);
			}
		}
		CONTEXTPATH = arr2.join('/')+'/';
		break;
	}
}
var Server = {};
Server.ContextPath = CONTEXTPATH;

var isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1;
var isIE8 = !!window.XDomainRequest&&!!document.documentMode;
var isIE7 = navigator.userAgent.toLowerCase().indexOf("msie 7.0") != -1 && !isIE8;
var isIE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1;
var isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;
var isOpera = navigator.userAgent.toLowerCase().indexOf("opera") != -1;
var isQuirks = document.compatMode == "BackCompat";
var isStrict = document.compatMode == "CSS1Compat";
var isBorderBox = isIE && isQuirks;

if(isGecko){
	var p = HTMLElement.prototype;
	p.__defineSetter__("innerText",function(txt){this.textContent = txt;});
	p.__defineGetter__("innerText",function(){return this.textContent;});
	p.insertAdjacentElement = function(where,parsedNode){
		switch(where){
		  case "beforeBegin":
		    this.parentNode.insertBefore(parsedNode,this);
		    break;
		  case "afterBegin":
		    this.insertBefore(parsedNode,this.firstChild);
		    break;
		  case "beforeEnd":
		    this.appendChild(parsedNode);
		    break;
		  case "afterEnd":
		    if(this.nextSibling)
		      this.parentNode.insertBefore(parsedNode,this.nextSibling);
		    else
		      this.parentNode.appendChild(parsedNode);
		    break;
		  }
	};
	p.insertAdjacentHTML = function(where,htmlStr){
		var r=this.ownerDocument.createRange();
		r.setStartBefore(this);
		var parsedHTML=r.createContextualFragment(htmlStr);
		this.insertAdjacentElement(where,parsedHTML);
	};
	p.attachEvent = function(evtName,func){
		evtName = evtName.substring(2);
		this.addEventListener(evtName,func,false);
	}
	p.detachEvent = function(evtName,func){
		evtName = evtName.substring(2);
		this.removeEventListener(evtName,func,false);
	}
	window.attachEvent = p.attachEvent;
	window.detachEvent = p.detachEvent;
	document.attachEvent = p.attachEvent;
	document.detachEvent = p.detachEvent;
	p.__defineGetter__("currentStyle", function(){
		return this.ownerDocument.defaultView.getComputedStyle(this,null);
  });
	p.__defineGetter__("children",function(){
    var tmp=[];
    for(var i=0;i<this.childNodes.length;i++){
    	var n=this.childNodes[i];
      if(n.nodeType==1){
      	tmp.push(n);
      }
    }
    return tmp;
  });
	p.__defineSetter__("outerHTML",function(sHTML){
    var r=this.ownerDocument.createRange();
    r.setStartBefore(this);
    var df=r.createContextualFragment(sHTML);
    this.parentNode.replaceChild(df,this);
    return sHTML;
  });
  p.__defineGetter__("outerHTML",function(){
    var attr;
		var attrs=this.attributes;
		var str="<"+this.tagName.toLowerCase();
		for(var i=0;i<attrs.length;i++){
		    attr=attrs[i];
		    if(attr.specified){
		        str+=" "+attr.name+'="'+attr.value+'"';
		    }
		}
		if(!this.hasChildNodes){
		    return str+">";
		}
		return str+">"+this.innerHTML+"</"+this.tagName.toLowerCase()+">";
  });
	p.__defineGetter__("canHaveChildren",function(){
	  switch(this.tagName.toLowerCase()){
			case "area":
			case "base":
			case "basefont":
			case "col":
			case "frame":
			case "hr":
			case "img":
			case "br":
			case "input":
			case "isindex":
			case "link":
			case "meta":
			case "param":
			return false;
    }
    return true;
  });
  Event.prototype.__defineGetter__("srcElement",function(){
    var node=this.target;
    while(node&&node.nodeType!=1)node=node.parentNode;
    return node;
  });
  p.__defineGetter__("parentElement",function(){
		if(this.parentNode==this.ownerDocument){
			return null;
		}
		return this.parentNode;
	});
}else{
	try {
		document.documentElement.addBehavior("#default#userdata");
		document.execCommand('BackgroundImageCache', false, true);
	} catch(e) {alert(e)}
}

var DragManager = {};

DragManager.DragProxy = null;

DragManager.onMouseOver = function (evt,ele) {
	if(DragManager.DragFlag){
		var dragOver = ele.getAttribute("dragOver");	
		if(dragOver){
			var func = eval(dragOver);
			func.apply(ele,arguments);
		}
	}
}

DragManager.onMouseOut = function (evt,ele) {
	if(DragManager.DragFlag){
		var dragOut = ele.getAttribute("dragOut");	
		if(dragOut){
			var func = eval(dragOut);
			func.apply(ele,arguments);
		}
	}
}

DragManager.onMouseDown = function (evt,ele) {
	var evt = getEvent(evt);
	if(!$o(evt.srcElement).getParentByAttr){
		return;
	}
	var dragFlag = $o(evt.srcElement).getParentByAttr("drag","false");
	if(dragFlag){
		return;	
	}
	DragManager.DragSource = ele;
	DragManager.StartFlag = true;
	if(isIE){
		//���㶪ʧ
		DragManager.DragSource.attachEvent("onlosecapture",DragManager.onDragExit);
		//������겶��
		//DragManager.DragSource.setCapture();
	}else{
		//���㶪ʧ
		window.attachEvent("onblur",DragManager.onDragExit);
		//��ֹĬ�϶���
		evt.preventDefault();
	};
}
function getEvent(evt){
	if(document.all) return window.event;
	if (evt) {
		if((evt.constructor  == Event || evt.constructor == MouseEvent) || (typeof(evt) == "object" && evt.preventDefault && evt.stopPropagation)) {
			return evt;
		}
	}
	func = getEvent.caller;
	while(func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if((arg0.constructor  == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func=func.caller;
	}
	return null;
}
function stopEvent(evt){//阻止一切事件执行,包括浏览器默认的事件
	evt = getEvent(evt);
	if(!evt){
		return;
	}
	if(isGecko){
		evt.preventDefault();
		evt.stopPropagation();
	}
	evt.cancelBubble = true
	evt.returnValue = false;
}

function cancelEvent(evt){//仅阻止用户定义的事件
	evt = getEvent(evt);
	evt.cancelBubble = true;
}

function getEventPosition(evt){//返回相对于最上级窗口的左上角原点的坐标
	evt = getEvent(evt);
	var pos = {x:evt.clientX, y:evt.clientY};
	var win;
	if(isGecko){
		win = evt.srcElement.ownerDocument.defaultView;
	}else{
		win = evt.srcElement.ownerDocument.parentWindow;
	}
	var sw,sh;
	while(win!=win.parent){
		if(win.frameElement&&win.parent.DataCollection){
			pos2 = $E.getPosition(win.frameElement);
			pos.x += pos2.x;
			pos.y += pos2.y;
		}
		sw = Math.max(win.document.body.scrollLeft, win.document.documentElement.scrollLeft);
		sh = Math.max(win.document.body.scrollTop, win.document.documentElement.scrollTop);
		pos.x -= sw;
		pos.y -= sh;
		if(!win.parent.DataCollection){
			break;
		}
		win = win.parent;
	}

	return pos;
}
function getEventPositionLocal(evt){//返回事件在当前页面上的坐标
	evt = getEvent(evt);
	var pos = {x:evt.clientX, y:evt.clientY};
	var win;
	if(isGecko){
		win = evt.srcElement.ownerDocument.defaultView;
	}else{
		win = evt.srcElement.ownerDocument.parentWindow;
	}
	pos.x += Math.max(win.document.body.scrollLeft, win.document.documentElement.scrollLeft);
	pos.y += Math.max(win.document.body.scrollTop, win.document.documentElement.scrollTop);
	return pos;
}  
DragManager.onMouseMove = function (evt) {
	evt = getEvent(evt);
	if(DragManager.StartFlag){
		DragManager.DragFlag = true;
		DragManager.StartFlag = false;
		var dragStart = DragManager.DragSource.getAttribute("dragStart");
		if(dragStart){
			var func = eval(dragStart);
			func.apply(DragManager.DragSource,arguments);
		}
	}else	if(DragManager.DragFlag){
		if(DragManager.DragProxy){
			if(!evt.srcElement){
				return;
			}
			//������Ӵ�����Ӧ�˸����ڷ�����¼�������Ҫ��Զ�λ.todo:��δ���Ƕ��iframeǶ�׵�����
			var pos = null;
			if(window==$E.getTopLevelWindow()){
				pos = getEventPosition(evt);
			}else{
				pos = getEventPositionLocal(evt);	
			}
			
			DragManager.DragProxy.style.left = (pos.x-DragManager.DragProxy.cx)+"px";
			if(DragManager.DragProxy==DragManager.DragSource){
				DragManager.DragProxy.style.top = (pos.y-DragManager.DragProxy.cy)+"px";
			}else{
				DragManager.DragProxy.style.top = (pos.y+5)+"px";
			}
		}

		//����draging
		var draging = DragManager.DragSource.getAttribute("draging");
		if(draging){
			var func = eval(draging);
			func.apply(DragManager.DragSource,arguments);
		}
		
		//end draging
	}else	if(DragManager.ChildDragFlag){//�п�����iframe�з������ק
		if(DragManager.DragProxy){
			var pos = getEventPosition(evt);
			DragManager.DragProxy.style.left = (pos.x-DragManager.DragProxy.cx)+"px";
			if(DragManager.DragProxy==DragManager.DragSource){
				DragManager.DragProxy.style.top = (pos.y-DragManager.DragProxy.cy)+"px";
			}else{
				DragManager.DragProxy.style.top = (pos.y+5)+"px";
			}
		}
	}else{//�����Ǹ������ڷ�����϶�,��Ի���
		var pw = $E.getTopLevelWindow();
		if(pw.DragManager!=null&&pw.DragManager.DragFlag){
			pw.DragManager.onMouseMove(evt);
		}
	}
}

DragManager.onMouseUp = function (evt,ele) {
	var evt = getEvent(evt);
	if(!$o(evt.srcElement).getParentByAttr){
		return;
	}
	var dragFlag = $o(evt.srcElement).getParentByAttr("drag","false");
	if(dragFlag){
		return;	
	}

	if(DragManager.DragFlag){
		DragManager.onMouseOut.apply(ele,arguments);

		var dragEnd = ele.getAttribute("dragEnd");
		var func = eval(dragEnd);
		func.apply(ele,arguments);		
		
		if(DragManager.DragProxy&&DragManager.DragSource!=DragManager.DragProxy){
			DragManager.DragProxy.outerHTML = "";
		}
	}
	Misc.unlockSelect();
	if(isIE&&DragManager.DragSource){
		DragManager.DragSource.detachEvent("onlosecapture", DragManager.onDragExit);
		//DragManager.DragSource.releaseCapture();
	}else{
		window.detachEvent("onblur",DragManager.onDragExit);
	};
	DragManager.DragProxy = null;
	DragManager.DragFlag = false;
	DragManager.StartFlag = false;
	DragManager.DragSource = null;
}

DragManager.onDragExit = function(){
	var pw = $E.getTopLevelWindow();
	DragManager.DragFlag = false;
	pw.DragManager.DragFlag = false;
	if(!DragManager.DragSource){
		return;
	}
	var dragExit = DragManager.DragSource.getAttribute("dragExit");
	if(dragExit){
		var func = eval(dragExit);
		func.apply(DragManager.DragSource,arguments);
	}
	var dragOut = DragManager.DragSource.getAttribute("dragOut");
	if(dragOut){
		var func = eval(dragOut);
		func.apply(DragManager.DragSource,arguments);
	}
	if(DragManager.DragProxy&&DragManager.DragSource!=DragManager.DragProxy){
		DragManager.DragProxy.outerHTML = "";//����Ҫ���,������ܻ�����ж��ͬID��Ԫ��,����DataGrid�е���ק
	}	
	Misc.unlockSelect();
	if(isIE&&DragManager.DragSource){
		DragManager.DragSource.detachEvent("onlosecapture", DragManager.onDragExit);
		//DragManager.DragSource.releaseCapture();
	}else{
		window.detachEvent("onblur",DragManager.onDragExit);
	};
	DragManager.DragProxy = null;
	DragManager.DragFlag = false;
	DragManager.StartFlag = false;
	DragManager.DragSource = null;
}

DragManager.doDrag = function(evt,proxy){
	var dragProxy;
	var pos1 = $E.getPosition(DragManager.DragSource);
	var dim = $E.getDimensions(DragManager.DragSource);
	evt = getEvent(evt);
	var pos2 = getEventPositionLocal(evt);

	if(!proxy){
		dragProxy = DragManager.DragSource;
		if(dragProxy.style.position != "absolute"){
			dragProxy.style.width = dim.width+"px";
			dragProxy.style.top = pos1.y+"px";
			dragProxy.style.position = "absolute";
		}
	}else{
		if(typeof(proxy)=="string"){
			//����ȥ��Proxy���¼�����Ϊ�����ƶ��Ĺ���������ܻ���Proxy�������¼�
			proxy = proxy.replace(/on\w*?=([\'\"]).*?\1/gi,"");
			proxy = proxy.replace(/drag\w*?=([\'\"]).*?\1/gi,"");
			var div = $o("_DragProxy");
			if(!div){
				div = document.createElement('div');
				div.id = "_DragProxy";
				div.style.position = "absolute";
				div.style.zIndex = 999;
				Misc.lockSelect(div);
				document.body.appendChild(div) ; 
			}
			div.innerHTML = proxy;
			div.style.display = "";
			div.style.left = (2*pos1.x-pos2.x)+"px";
			div.style.top = (2*pos1.y-pos2.y)+"px";
			dragProxy = div;
		}else{
			dragProxy = proxy;
			dragProxy.style.top = pos1.y+"px";
			dragProxy.style.position = "absolute";
			if(dragProxy!=DragManager.DragSource){
				dragProxy.style.zIndex = 999;
			}
		}
	}
	dragProxy.cx = pos2.x - pos1.x; 
	dragProxy.cy = pos2.y - pos1.y; 
	DragManager.DragProxy = dragProxy;
	Misc.lockSelect();
}
function $o(ele) {
  if (typeof(ele) == 'string'){
    ele = document.getElementById(ele)
    if(!ele){
  		return null;
    }
  }
  if(ele){
  	Core.attachMethod(ele);
	}
  return ele;
}

function $V(ele){
	var eleId = ele;
	ele = $o(ele);
	if(!ele){
		alert("表单元素不存在:"+eleId);
		return null;
	}
  switch (ele.type.toLowerCase()) {
    case 'submit':
    case 'hidden':
    case 'password':
    case 'textarea':
    case 'file':
    case 'image':
    case 'select-one':
    case 'text':
      return ele.value;
    case 'checkbox':
    case 'radio':
      if (ele.checked){
    		return ele.value;
    	}else{
    		return null;
    	}
    default:
    		return "";
  }
}

function $S(ele,v){
	var eleId = ele;
	ele = $o(ele);
	if(!v&&v!=0){
		v = "";
	}
	if(!ele){
		alert("表单元素不存在:"+eleId);
		return;
	}
  switch (ele.type.toLowerCase()) {
    case 'submit':
    case 'hidden':
    case 'password':
    case 'textarea':
    case 'button':
    case 'file':
    case 'image':
    case 'select-one':
    case 'text':
      ele.value = v;
      break;
    case 'checkbox':
    case 'radio':
      if(ele.value==""+v){
      	ele.checked = true;
      }else{
      	ele.checked=false;
      }
      break;
   }
}
function $T(tagName,ele){
	ele = $o(ele);
	ele = ele || document;
	var ts = ele.getElementsByTagName(tagName);//此处返回的不是数组
	var arr = [];
	var len = ts.length;
	for(var i=0;i<len;i++){
		arr.push($o(ts[i]));
	}
	return arr;
}

function $N(ele){
    if (typeof(ele) == 'string'){
      ele = document.getElementsByName(ele)
      if(!ele||ele.length==0){
    		return null;
      }
      var arr = [];
      for(var i=0;i<ele.length;i++){
      	var e = ele[i];
      	if(e.getAttribute("ztype")=="select"){
      	e = e.parentNode;
      	}
      	Core.attachMethod(e);
      	arr.push(e);
      }
      ele = arr;
    }
    return ele;
}

function $NV(ele){
	ele = $N(ele);
	if(!ele){
		return null;
	}
	var arr = [];
	for(var i=0;i<ele.length;i++){
		var v = $V(ele[i]);
		if(v!=null){
			arr.push(v);
		}
	}
	return arr.length==0? null:arr;
}

function $NS(ele,value){
	ele = $N(ele);
	if(!ele){
		return;
	}
	if(!ele[0]){
		return $S(ele,value);
	}
	if(ele[0].type=="checkbox"){
		if(value==null){
			value = new Array(4);
		}
		var arr = value;
		if(!isArray(value)){
			arr = value.split(",");
		}
		for(var i=0;i<ele.length;i++){
			for(var j=0;j<arr.length;j++){
				if(ele[i].value==arr[j]){
					$S(ele[i],arr[j]);
					break;
				}
				$S(ele[i],arr[j]);
			}
		}
		return;
	}
	for(var i=0;i<ele.length;i++){
		$S(ele[i],value);
	}
}

function $F(ele){
	if(!ele)
		return document.forms[0];
	else{
		ele = $o(ele);
		if(ele&&ele.tagName.toLowerCase()!="form")
			return null;
		return ele;
	}
}

//多选框全选
function selectAll(ele,eles){
	var flag = $V(ele);
	var arr = $N(eles);
	if(arr){
		for(var i=0;i< arr.length;i++){
			arr[i].checked = flag;
	  }
	}
}

var $E = {};

$E.$A = function(attr,ele) {
	ele = ele || this;
	ele = $o(ele);
	return ele.getAttribute?ele.getAttribute(attr):null;
}

$E.$T = function(tagName,ele){
	ele = ele || this;
	ele = window.$o(ele);
	return window.$T(tagName,ele);
}

$E.visible = function(ele) {
	ele = ele || this;
	ele = $o(ele);
	if(ele.style.display=="none"){
		return false;
	}
  return true;
}

$E.toggle = function(ele) {
	ele = ele || this;
	ele = $o(ele);
  $E[$E.visible(ele) ? 'hide' : 'show'](ele);
}

$E.toString = function(flag,index,ele) {//flag表示是否显示函数内容
	ele = ele || this;
	var arr = [];
	var i = 0;
	for(var prop in ele){
		if(!index||i>=index){
			var v = null;
			try{v = ele[prop];}catch(ex){}//gecko下可能会报错
			if(!flag){
				if(typeof(v)=="function"){
					v = "function()";
				}else if((""+v).length>100){
					v = (""+v).substring(0,100)+"...";
				}
			}
			arr.push(prop+":"+v);
		}
		i++;
	}
  return arr.join("\n");
}

$E.focusEx = function(ele) {
	ele = ele || this;
	ele = $o(ele);
	try{
  	ele.focus();
	}catch(ex){}
}

$E.getForm = function(ele) {
	ele = ele || this;
	ele = $o(ele);
	if(isIE){
		ele = ele.parentElement;
	}else if(isGecko){
		ele = ele.parentNode;
	}
	if(!ele){
		return null;
	}
	if(ele.tagName.toLowerCase()=="form"){
		return ele
	}else{
		return $E.GetForm(ele);
	}
}

$E.hide = function(ele) {
	if(!ele){
		ele = this;
	}
	ele = $o(ele);
	if(ele.tagName.toLowerCase()=="input"&&ele.type=="button"){
		if(ele.parentElement&&ele.parentElement.getAttribute("ztype")=="zInputBtnWrapper"){
			ele.parentElement.style.display = 'none';
		}
	}
  ele.style.display = 'none';
}

$E.show = function(ele) {
	if(!ele){
		ele = this;
	}
	ele = $o(ele);
	if(ele.tagName.toLowerCase()=="input"&&ele.type=="button"){
		if(ele.parentElement&&ele.parentElement.getAttribute("ztype")=="zInputBtnWrapper"){
			ele.parentElement.style.display = '';
		}
	}
  ele.style.display = '';
}

$E.disable = function(ele) {
	ele = ele || this;
	ele = $o(ele);
	if(ele.tagName.toLowerCase()=="form"){
		var elements = ele.elements;
		for (var i = 0; i < elements.length; i++) {
		  var element = elements[i];
		  ele.blur();
		  if(ele.hasClassName("zPushBtn")){
			  ele.addClassName("zPushBtnDisabled");
			  if(ele.onclick){
				 ele.onclickbak = ele.onclick;
			  }
			  ele.onclick=null;
		  }else{
			  ele.disabled = 'true';
		  }
		}
	}else{
		if(ele.$A("ztype")&&ele.$A("ztype").toLowerCase()=="select"){
			Selector.setDisabled(ele,true);
		}else if(ele.hasClassName("zPushBtn")){
			ele.addClassName("zPushBtnDisabled");
			if(ele.onclick){
				ele.onclickbak = ele.onclick;
			}
			ele.onclick=null;
		}else{
				ele.addClassName("disabled");
	    	ele.disabled = 'true';
		}
	}
}

$E.enable = function(ele) {
	ele = ele || this;
	ele = $o(ele);
	if(ele.tagName.toLowerCase()=="form"){
		var elements = ele.elements;
	    for (var i = 0; i < elements.length; i++) {
	      var element = elements[i];
			if(ele.hasClassName("zPushBtnDisabled")){
				ele.className="zPushBtn";
				if(ele.onclickbak){
					ele.onclick = ele.onclickbak;
				}
			}else{
		    	ele.disabled = '';
			}
	    }
	}else{
		if(ele.$A("ztype")&&ele.$A("ztype").toLowerCase()=="select"){
			Selector.setDisabled(ele,false);
		}else if(ele.hasClassName("zPushBtnDisabled")){
			ele.className="zPushBtn";
			if(ele.onclickbak){
				ele.onclick = ele.onclickbak;
			}
		}else{
			if(ele.hasClassName("disabled")){
				ele.removeClassName("disabled");
			}
	    ele.disabled = '';
		}
	}
}

$E.scrollTo = function(ele) {
	ele = ele || this;
	ele = $o(ele);
  var x = ele.x ? ele.x : ele.offsetLeft,
      y = ele.y ? ele.y : ele.offsetTop;
  window.scrollTo(x, y);
}

$E.getDimensions = function(ele){
  ele = ele || this;
  ele = $o(ele);
  var dim;
  if(ele.tagName.toLowerCase()=="script"){
  	dim = {width:0,height:0};
  }else if ($E.visible(ele)){
		if(isIE && ele.offsetWidth ==0 && ele.offsetHeight ==0){
			var curStyle=ele.currentStyle;
			if(isBorderBox){
				dim = {width: curStyle.pixelWidth, height: curStyle.pixelHeight};
			}else{
				dim = {width: +curStyle.pixelWidth+parseInt(curStyle.borderLeftWidth)+parseInt(curStyle.borderRightWidth)+parseInt(curStyle.paddingLeft)+parseInt(curStyle.paddingRight),
						height: +curStyle.pixelHeight+parseInt(curStyle.borderTopWidth)+parseInt(curStyle.borderBottomWidth)+parseInt(curStyle.paddingTop)+parseInt(curStyle.paddingBottom)
				};
			}
		}else{
			dim = {width: ele.offsetWidth, height: ele.offsetHeight};
		}
  }else{
	  var style = ele.style;
	  var vis = style.visibility;
	  var pos = style.position;
	  var dis = style.display;
	  style.visibility = 'hidden';
	  style.position = 'absolute';
	  style.display = 'block';
	  var w = ele.offsetWidth;
	  var h = ele.offsetHeight;
	  style.display = dis;
	  style.position = pos;
	  style.visibility = vis;
	  dim = {width: w, height: h};
	}
	return dim;
}
$E.getViewportDimensions = function (win) {
	var win = win || window,
		doc = win.document,
		viewportWidth,
		viewportHeight;
	if(isIE){
		viewportWidth=isQuirks?doc.body.clientWidth :doc.documentElement.clientWidth ;
		viewportHeight=isQuirks?doc.body.clientHeight :doc.documentElement.clientHeight;
	}else{
		viewportWidth=win.innerWidth;
		viewportHeight=win.innerHeight;
	}
	return {width:viewportWidth, height:viewportHeight};
}
$E.getPosition = function(ele){
	ele = ele || this;
	ele = $o(ele);
	var doc = ele.ownerDocument;
  if(ele.parentNode===null||ele.style.display=='none'){
    return false;
  }
  var parent = null;
  var pos = [];
  var box;
  if(ele.getBoundingClientRect){//IE,FF3
    box = ele.getBoundingClientRect();
    var scrollTop = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
    var scrollLeft = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
    var X = box.left + scrollLeft - doc.documentElement.clientLeft-doc.body.clientLeft;
    var Y = box.top + scrollTop - doc.documentElement.clientTop-doc.body.clientTop;
    return {x:X, y:Y};
  }else if(doc.getBoxObjectFor){ // FF2
    box = doc.getBoxObjectFor(ele);
    var borderLeft = (ele.style.borderLeftWidth)?parseInt(ele.style.borderLeftWidth):0;
    var borderTop = (ele.style.borderTopWidth)?parseInt(ele.style.borderTopWidth):0;
    pos = [box.x - borderLeft, box.y - borderTop];
  }
  if (ele.parentNode) {
    parent = ele.parentNode;
  }else {
    parent = null;
  }
  while (parent && parent.tagName != 'BODY' && parent.tagName != 'HTML'){
    pos[0] -= parent.scrollLeft;
    pos[1] -= parent.scrollTop;
    if (parent.parentNode){
      parent = parent.parentNode;
    }else{
      parent = null;
    }
  }
  return {x:pos[0],y:pos[1]}
}

$E.getPositionEx = function(ele){
	ele = ele || this;
	ele = $o(ele);
	var pos = $E.getPosition(ele);
	var win = window;
	var sw,sh;
	while(win!=win.parent){
		if(win.frameElement&&win.parent.DataCollection){
			pos2 = $E.getPosition(win.frameElement);
			pos.x += pos2.x;
			pos.y += pos2.y;
		}
		sw = Math.max(win.document.body.scrollLeft, win.document.documentElement.scrollLeft);
		sh = Math.max(win.document.body.scrollTop, win.document.documentElement.scrollTop);
		pos.x -= sw;
		pos.y -= sh;
		if(!win.parent.DataCollection){
			break;
		}
		win = win.parent;
	}
	return pos;
}

$E.getParent = function(tagName,ele){
	ele = ele || this;
	ele = $o(ele);
	while(ele){
		if(ele.tagName.toLowerCase()==tagName.toLowerCase()){
			return $o(ele);
		}
		ele = ele.parentElement;
	}
	return null;
}

$E.getParentByAttr = function(attrName,attrValue,ele){
	ele = ele || this;
	ele = $o(ele);
	while(ele){
		if(ele.getAttribute(attrName)==attrValue){
			return $o(ele);
		}
		ele = ele.parentElement;
	}
	return null;
}

$E.nextElement = function(ele){
	ele = ele || this;
	ele = $o(ele);
	var x = ele.nextSibling;
	while (x&&x.nodeType!=1){
		x = x.nextSibling;
	}
	return $o(x);
}

$E.previousElement = function(ele){
	ele = ele || this;
	ele = $o(ele);
	var x = ele.previousSibling;
	while (x&&x.nodeType!=1){
		x = x.previousSibling;
	}
	return $o(x);
}

$E.getTopLevelWindow = function(){
	var pw = window;
	while(pw!=pw.parent){
		if(!pw.parent.DataCollection){
			return pw;
		}
		pw = pw.parent;
	}
	return pw;
}

$E.hasClassName = function(className,ele){
	ele = ele || this;
	ele = $o(ele);
	return (new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i").test(ele.className));
}

$E.addClassName = function(className,ele,before){
	ele = ele || this;
	ele = $o(ele);
  var currentClass = ele.className;
  currentClass = currentClass?currentClass:"";
  if(!new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i").test(currentClass)){
		if(before){
			ele.className = className + ((currentClass.length > 0)? " " : "") + currentClass;
		}else{
			ele.className = currentClass + ((currentClass.length > 0)? " " : "") + className;
		}
  }
  return ele.className;
}

$E.removeClassName = function(className,ele){
	ele = ele || this;
	ele = $o(ele);
  var classToRemove = new RegExp(("(^|\\s)" + className + "(?=\\s|$)"), "i");
  ele.className = ele.className.replace(classToRemove, "").replace(/^\s+|\s+$/g, "");
  return ele.className;
}

/*
给定p1(x1/y1)和p2(x2/y2)，p1在p2的左上方(也可重合)，计算一个起始坐标，
使得元素ele(宽为w,高为h)能够全部在p1之上，或者p2之下，并且尽可能显示ele的全部
flag="all"表示ele能够显示在x1的两边或者x2的两边
flag="left"表示ele能够显示在x1的左边或者x2的左边
flag="right"表示ele能够显示在x1的右边或者x2的右边
右键菜单、日期控件、下拉框控件需要这个函数
*/
$E.computePosition = function(x1,y1,x2,y2,flag,w,h,win){
	var doc = win?win.document:document;
	var cw = isQuirks?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = isQuirks?doc.body.clientHeight:doc.documentElement.clientHeight;
	var sw = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
	if(!flag||flag.toLowerCase()=="all"){
		//先考虑p2
		if(y2-sh+h-ch<0){
			if(x2-sw+w-cw<0){//从P2往右展开可行
				return {x:x2,y:y2};
			}else{//往左展开
				return {x:x2-w,y:y2};
			}
		}
		//考虑p1
		if(x1-sw+w-cw<0){//从P1往右展开可行
			return {x:x1,y:y1-h};
		}else{//往左展开
			return {x:x1-w,y:y1-h};
		}
	}else	if(flag.toLowerCase()=="right"){
		//先考虑p2
		if(y2-sh+h-ch<0){
			if(x2-sw+w-cw<0){//从P2往右展开可行

				return {x:x2,y:y2};
			}
		}
		//考虑p1
		return {x:x1,y:y1-h};
	}else if(flag.toLowerCase()=="left"){
		//先考虑p2
		if(y2-sh+h-ch<0){
			if(x2-sw-w>0){//从P2往左展开可行
				return {x:x2,y:y2};
			}
		}
		//考虑p1
		return {x:x1-w,y:y1-h};
	}
}


var Core = {};
Core.attachMethod = function(ele){
	if(!ele||ele["$A"]){
		return;
	}
	if(ele.nodeType==9){
		return;
	}
	var win;
	try{
		if(isGecko){
			win = ele.ownerDocument.defaultView;
		}else{
			win = ele.ownerDocument.parentWindow;
		}
		for(var prop in win.$E){
			ele[prop] = win.$E[prop];
		}
	}catch(ex){
		//alert("Core.attachMethod:"+ele)//有些对象不能附加属性，如flash
	}
}

function Dialog(){
		var strID,w,h,title,url;
		if(arguments.length==1){//兼容旧写法
			strID = arguments[0];
		}else{
			title = arguments[0];
			url = arguments[1];
			w = arguments[2];
			h = arguments[3];
			if(!Dialog.MaxID){
				Dialog.MaxID = 0;
			}
			Dialog.MaxID++;
			strID = "Dialog"+Dialog.MaxID;
		}
		if(!strID){
			alert("错误的Dialog ID!");
			return;
		}
		this.ID = strID;
		this.isModal = true;
		this.coverSelect = false;
		this.Width = w|| 400;
		this.Height = h|| w/2 || 300;
		this.Title = title||"";
		this.URL = url||null;
		this.Top = 0;
		this.Left = 0;
		this.ParentWindow = null;
		this.onLoad = null;
		this.Window = null;

		this.DialogArguments = {};
		this.WindowFlag = false;
		this.Message = null;
		this.MessageTitle = null;
		this.ShowMessageRow = false;
		this.ShowButtonRow = true;
		this.Icon = null;
		this.bgdivID=null;
		this.Animator = true;
		this.resizeable=false;
		this.keydown=false;
}

Dialog._Array = [];

Dialog.prototype.showWindow = function(){
	if(isIE){
		this.ParentWindow.showModalessDialog( this.URL, this.DialogArguments, "dialogWidth:" + this.Width + ";dialogHeight:" + this.Height + ";help:no;scroll:no;status:no") ;
	}
	if(isGecko){
		var sOption  = "location=no,menubar=no,status=no;toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=no";
		this.Window = this.ParentWindow.open( '', this.URL, sOption, true ) ;
		var w = this.Window;
		if ( !w ){
			alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
			return ;
		}
		w.moveTo( this.Left, this.Top ) ;
		w.resizeTo( this.Width, this.Height+30 ) ;
		w.focus() ;
		w.location.href = this.URL ;
		w.Parent = this.ParentWindow;
		w.dialogArguments = this.DialogArguments;
	}
}

Dialog.prototype.show = function(){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var vp = $E.getViewportDimensions(pw);
	var cw = vp.width;
	var ch = vp.height;
	var sw = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
	var mw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var mh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	if(!this.ParentWindow){
	 	this.ParentWindow = window ;
	}
	this.DialogArguments._DialogInstance = this;
	this.DialogArguments.ID = this.ID;
	this.dialogDivWidth=this.Width+13+13;//Dialog容器div的宽
	this.dialogDivHeight=this.Height+33+13;//Dialog容器div的高
	if(this.ShowMessageRow)//如果有显示消息栏高度加50
		this.dialogDivHeight += 48;
	if(this.Left==0){
		this.Left = (cw - this.dialogDivWidth) / 2 +sw;
	}
	if(this.Top==0){
		this.Top = (ch - this.dialogDivHeight) / 2 + sh ;
	}
	if(this.WindowFlag){
		this.showWindow();
		return;
	}
	var arr = [];
	arr.push("<table style='-moz-user-select:none;' oncontextmenu='stopEvent(event);' onselectstart='stopEvent(event);' border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>");
	arr.push("  <tr style='cursor:move;'>");
	arr.push("    <td width='13' height='33' style=\"background-image:url("+Server.ContextPath+"images/dialog_lt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_lt.png', sizingMethod='crop');\"><div style='width:13px;'></div></td>");
	arr.push("    <td height='33' style=\"background-image:url("+Server.ContextPath+"images/dialog_ct.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_ct.png', sizingMethod='crop');\"><div style=\"float:left;font-weight:bold; color:#FFFFFF; padding:9px 0 0 4px;\"><img src=\""+Server.ContextPath+"images/icon_dialog.gif\" align=\"absmiddle\">&nbsp;<span id='_DialogTitle'>"+this.Title+"</span></div>");
	arr.push("      <div style=\"position: relative;cursor:pointer; float:right; margin:5px 0 0; _margin:4px 0 0;height:17px; width:28px; background-image:url("+Server.ContextPath+"images/dialog_closebtn.gif)\" onMouseOver=\"this.style.backgroundImage='url("+Server.ContextPath+"images/dialog_closebtn_over.gif)'\" onMouseOut=\"this.style.backgroundImage='url("+Server.ContextPath+"images/dialog_closebtn.gif)'\" drag='false' onClick=\"Dialog.getInstance('"+this.ID+"').close();\"></div></td>");
	arr.push("    <td width='13' height='33' style=\"background-image:url("+Server.ContextPath+"images/dialog_rt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_rt.png', sizingMethod='crop');\"><div style=\"width:13px;\"></div></td>");
	arr.push("  </tr>");
	arr.push("  <tr drag='false'><td width='13' style=\"background-image:url("+Server.ContextPath+"images/dialog_mlm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_mlm.png', sizingMethod='crop');\"></td>");
	arr.push("    <td align='center' valign='top'>");
	arr.push("    <table width='100%' height='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>");
	arr.push("        <tr id='_MessageRow_"+this.ID+"' style='display:none'>");
	arr.push("          <td height='50' valign='top'><table id='_MessageTable_"+this.ID+"' width='100%' border='0' cellspacing='0' cellpadding='8' style=\" background:#EAECE9 url("+Server.ContextPath+"images/dialog_bg.jpg) no-repeat right top;\">");
	arr.push("              <tr><td width='25' align='right'><img id='_MessageIcon_"+this.ID+"' src='"+Server.ContextPath+"images/window.gif' width='32' height='32'></td>");
	arr.push("                <td align='left' style='line-height:16px;'>");
	arr.push("                <h5 class='fb' id='_MessageTitle_"+this.ID+"'>&nbsp;</h5>");
	arr.push("                <div id='_Message_"+this.ID+"'>&nbsp;</div></td>");
	arr.push("              </tr></table></td></tr>");
	arr.push("        <tr><td align='center' valign='top'>");
	arr.push("          <iframe ");
	if(this.URL.indexOf(":")==-1){
		arr.push("src='"+Server.ContextPath+this.URL+"'");
	}else{
		arr.push("src='"+this.URL+"'");
	}
	arr.push(" id='_DialogFrame_"+this.ID+"' allowTransparency='true'  width='100%' height='"+this.Height+"' frameborder='0' style=\"background-color: #transparent; border:none;\"></iframe></td></tr>");
	arr.push("      </table><a href='#;' onfocus='$o(\"_DialogFrame_"+this.ID+"\").focus();'></a></td>");
	arr.push("    <td width='13' style=\"background-image:url("+Server.ContextPath+"images/dialog_mrm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_mrm.png', sizingMethod='crop');\"></td></tr>");
	arr.push("  <tr><td width='13' height='13' style=\"background-image:url("+Server.ContextPath+"images/dialog_lb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_lb.png', sizingMethod='crop');\"></td>");
	arr.push("    <td style=\"background-image:url("+Server.ContextPath+"images/dialog_cb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_cb.png', sizingMethod='crop');\"></td>");
	arr.push("    <td width='13' height='13' style=\"background-image:url("+Server.ContextPath+"images/dialog_rb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"images/dialog_rb.png', sizingMethod='crop');\"></td>");
	arr.push("  </tr></table>");
	this.TopWindow = pw;
	var bgdiv = pw.$o("_DialogBGDiv");
	if(!bgdiv){
		bgdiv = pw.document.createElement("div");
		bgdiv.id = "_DialogBGDiv";
		bgdiv.style.cssText = "background-color:#666;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:100%;height:" + mh + "px;z-index:960";
		$E.hide(bgdiv);
	 	pw.$T("body")[0].appendChild(bgdiv);
	}
	var div = pw.$o("_DialogDiv_"+this.ID);
	if(!div){
		div = pw.document.createElement("div");
		$E.hide(div);
		div.id = "_DialogDiv_"+this.ID;
		div.className = "dialogdiv";
		div.setAttribute("dragStart","Dialog.dragStart");
		div.setAttribute("dragExit","Dialog.dragExit");
	 	pw.$T("body")[0].appendChild(div);
	}
	this.DialogDiv = div;
	div.innerHTML = arr.join('\n');
	pw.$o("_DialogFrame_"+this.ID).DialogInstance = this;
	//显示标题图片
	if(this.ShowMessageRow){
		$E.show(pw.$o("_MessageRow_"+this.ID));
		if(this.MessageTitle){
			pw.$o("_MessageTitle_"+this.ID).innerHTML = this.MessageTitle;
		}
		if(this.Message){
			pw.$o("_Message_"+this.ID).innerHTML = this.Message;
		}
	}
	if(!this.AlertFlag){
		//$E.show(bgdiv);
		this.bgdivID = "_DialogBGDiv";
	}else{
		bgdiv = pw.$o("_AlertBGDiv");
		if(!bgdiv){
			bgdiv = pw.document.createElement("div");
			bgdiv.id = "_AlertBGDiv";
			bgdiv.style.cssText = "background-color:#666;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:100%;height:" + mh + "px;z-index:991";
			$E.hide(bgdiv);
		 	pw.$T("body")[0].appendChild(bgdiv);
		}
		//$E.show(bgdiv);
		this.bgdivID = "_AlertBGDiv";
	}
	if(bgdiv.style.display=="none"){
		if(this.coverSelect){
			bgdiv.innerHTML='<iframe src="about:blank" style="filter:alpha(opacity=0);" width="100%" height="100%"></iframe>';
		}
		if(this.Animator){
			$E.show(bgdiv);
			//pw.Effect.fade(bgdiv,0,10,isIE6?5:2);
		}else{
			pw.Effect.setAlpha(bgdiv,10);
			$E.show(bgdiv);
		}
	}
	this.DialogDiv.style.cssText = "position:absolute; display:block;z-index:"+(this.AlertFlag?992:990)+";left:"+Math.round(this.Left)+"px;top:"+Math.round(this.Top)+"px;width:"+this.dialogDivWidth+"px;height:"+this.dialogDivHeight+"px";
	//判断当前窗口是否是对话框，如果是，则将其置在bgdiv之后
	if(!this.AlertFlag){
		var win = window;
		var flag = false;
		while(win!=win.parent){//需要考虑父窗口是弹出窗口中的一个iframe的情况
			if(win._DialogInstance){
				win._DialogInstance.DialogDiv.style.zIndex = 959;
				flag = true;
				break;
			}
			win = win.parent;
		}
		if(!flag){
			//bgdiv.style.cssText = "background-color:#666;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:100%;height:" + mh + "px;z-index:960";
			//pw.Effect.setAlpha(bgdiv,3)
			$E.show(bgdiv);
		}
		this.ParentWindow.$D = this;
	}
	if(isIE){
		var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
		pwbody.style.overflow="hidden";//禁止出现滚动条
	}
	if(this.resizeable&&window.Resize){
		//new Resize(div);//可调整宽度
		var self=this;
		new Resize(div,{ 
					Max:		true, 
					mxContainer:{clientWidth:cw,clientHeight:ch},
					proxy:	true,
					endResize:function(size){//调整宽度实现
						selfDialogDivWidth=size.width;
						selfDialogDivHeight=size.height;
						selfWidth=selfDialogDivWidth-13-13;//Dialog中iframe的宽
						selfHeight=selfDialogDivHeight-33-13;//Dialog中iframe的高
						if(self.ShowButtonRow)//如果有显示按钮栏
							selfHeight -= 38;
						if(self.ShowMessageRow)//如果有显示消息栏
							selfHeight -= 48;
						self.resize(selfWidth,selfHeight,true);
					}
		});
	}
	div.attachEvent("onmousedown", function(evt){
		var w = $E.getTopLevelWindow();
		w.DragManager.onMouseDown(evt||w.event,div);//注意在ie下，通过attachEvent给dom元素事件注册方法时，this并不指向元素本身，所以在此处必须传递div
	})

	//放入队列中，以便于ESC时正确关闭
	pw.Dialog._Array.push(this.ID);
}

Dialog.hideAllFlash = function(win){//非IE浏览器在对话框弹出时必须手工隐藏flash
	if(!win||!win.$T){//有可能是Dialog.alert()
		return;
	}
	return;//
	var swfs = win.$T("embed");
	for(var i=0;i<swfs.length;i++){
		try{
			swfs[i].OldStyle = swfs[i].style.display;
			swfs[i].style.display = 'none';
		}catch(ex){}
	}
	var fs = win.$T("iframe");
	for(var i=0;fs&&i<fs.length;i++){
		Dialog.hideAllFlash(fs[i].contentWindow);
	}
}

Dialog.showAllFlash = function(win){
	if(!win||!win.$T){
		return;
	}
	return;//
	var swfs = win.$T("embed");
	for(var i=0;i<swfs.length;i++){
		try{
			swfs[i].style.display = swfs[i].OldStyle;
		}catch(ex){}
	}
	var fs = win.$T("iframe");
	for(var i=0;fs&&i<fs.length;i++){
		Dialog.hideAllFlash(fs[i].contentWindow);
	}
}

Dialog.prototype.addParam = function(paramName,paramValue){
		this.DialogArguments[paramName] = paramValue;
}

Dialog.prototype.close = function(){
	if(this.WindowFlag){
		this.ParentWindow.$D = null;
		this.ParentWindow.$DW = null;
		this.Window.opener = null;
		this.Window.close();
		this.Window = null;
	}else{
		//如果上级窗口是对话框，则将其置于bgdiv前
		var pw = $E.getTopLevelWindow();
		var doc=pw.document;
		var win = window;
		var flag = false;
		while(win!=win.parent){
			if(win._DialogInstance){
				flag = true;
				win._DialogInstance.DialogDiv.style.zIndex = 960;
				break;
			}
			win = win.parent;
		}
		if(this.AlertFlag){
			if(this.coverSelect){
				pw.$o("_AlertBGDiv").innerHTML='';
			}
			pw.eval('window._OpacityFunc = function(id){var w = $E.getTopLevelWindow();w.$E.hide(w.$o("_AlertBGDiv"));}');
			pw._OpacityFunc();
		}
		if(!flag&&!this.AlertFlag){//此处是为处理弹出窗口被关闭后iframe立即被重定向时背景层不消失的问题
			if(this.coverSelect){
				pw.$o("_DialogBGDiv").innerHTML='';
			}
			pw.eval('window._OpacityFunc = function(){var w = $E.getTopLevelWindow();w.$E.hide(w.$o("_DialogBGDiv"));}');
			pw._OpacityFunc();
		}
		this.DialogDiv.outerHTML = "";
		//delete this.DialogDiv
		if(isIE){
			var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
			pwbody.style.overflow="auto";//还原滚动条
		}
		pw.Dialog._Array.remove(this.ID);
	}
}

Dialog.prototype.addButton = function(id,txt,func){
	var html = "<input id='_Button_"+this.ID+"_"+id+"' type='button' value='"+txt+"'> ";
	var pw = $E.getTopLevelWindow();
	pw.$o("_DialogButtons_"+this.ID).$T("input")[0].getParent("a").insertAdjacentHTML("beforeBegin",html);
	Effect.initCtrlStyle(pw.$o("_Button_"+this.ID+"_"+id));
	pw.$o("_Button_"+this.ID+"_"+id).onclick = func;
}

Dialog.prototype.resize = function(w,h,static){
	this.Width = w;
	this.Height = h;
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var vp = $E.getViewportDimensions(pw);
	var cw = vp.width;
	var ch = vp.height;
	var sw = Math.max(document.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
	this.dialogDivWidth=this.Width+13+13;//Dialog容器div的宽
	this.dialogDivHeight=this.Height + 33 + 13;//Dialog容器div的高
	if(this.ShowButtonRow)//如果有显示按钮栏高度加38
		this.dialogDivHeight += 38;
	if(this.ShowMessageRow)//如果有显示消息栏高度加50
		this.dialogDivHeight += 48;
	if(!static){
		this.Left = (cw - this.dialogDivWidth) / 2 +sw;
		this.Top = (ch - this.dialogDivHeight) / 2 + sh ;
		this.DialogDiv.style.left = Math.round(this.Left)+"px";
		this.DialogDiv.style.top = Math.round(this.Top)+"px";
	}
	var frame = pw.$o("_DialogFrame_"+this.ID);
	frame.width = this.Width;
	frame.height = this.Height;
	this.DialogDiv.style.width = this.dialogDivWidth +"px";
	this.DialogDiv.style.height = this.dialogDivHeight +"px";
}

Dialog.close = function(evt){
	try{
		window.Args._DialogInstance.close();
	}catch(ex){}
}

Dialog.closeEx = Dialog.closeAlert = Dialog.endWait = function(){
	var pw = $E.getTopLevelWindow()
	var diag = pw.Dialog.getInstance("_DialogAlert"+(pw.Dialog.AlertNo-1));
	if(diag){
		diag.close();
	}
	pw.Dialog.AlertNo--;
	pw.clearTimeout(pw.Dialog.WaitID);
}

Dialog.getInstance = function(id){
	var pw = $E.getTopLevelWindow()
	var f = pw.$o("_DialogFrame_"+id);
	if(!f){
		return null;
	}
	return f.DialogInstance;
}

Dialog.AlertNo = 0;
var diag;
function diagclose(diag) {
	try {
	diag.close();
	} catch(e) {}
	diag = null;
}
Dialog.alert = function(msg,w,h){
	var pw = $E.getTopLevelWindow();
	if(diag != "undefined" && null != diag) {
		diagclose(diag);
	}
	diag = new Dialog("_DialogAlert"+pw.Dialog.AlertNo++);
	diag.ParentWindow = pw;
	diag.Width = w||300;
	diag.Height = h||120;
	diag.Title = "系统提示";
	diag.URL = "javascript:void(0);";
	diag.AlertFlag = true;
	diag.show();
	pw.$o("_AlertBGDiv").show();
	//$E.hide(pw.$o("_ButtonOK_"+diag.ID));
	var diagFrame=pw.$o("_DialogFrame_"+diag.ID);
	var win = diagFrame.contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<body oncontextmenu='return false;'></body>") ;
	var arr = [];
	arr.push("<table height='100' border='0' align='center' cellpadding='10' cellspacing='0'>");
	arr.push("<tr>");
	arr.push("<td align='center' id='Message' style='font-size:10pt'>"+msg+"</td></tr>");
	arr.push("<tr><td align='center'><input type='button' id='_closeButton' class='inputButton' value='确定'></td></tr>");
	arr.push("</table>");
	var div = doc.createElement("div");
	div.innerHTML = arr.join('');
	doc.body.appendChild(div);
	doc.close();
	doc.getElementById("_closeButton").onclick = function() {
		diag.close();
	};
	var h = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	var w = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	if(w>300){
		if(w>900){
			w=900;
		}
		win.frameElement.width = w;
	}
	if(h>120){
		if(h>400){
			h=400;
		}
		win.frameElement.height = h;
	}
	doc = pw.document;
	var vp = $E.getViewportDimensions(pw);
	var cw = vp.width;
	var ch = vp.height;
	var sw = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var top = (ch - h - 30) / 2 + sh - 8;
	var left = (cw - w - 12) / 2 +sw;
	diag.DialogDiv.style.cssText = "position:absolute; display:block;z-index:992;left:"+left+"px;top:"+top+"px;width:"+diag.dialogDivWidth+"px;height:"+diag.dialogDivHeight+"px";
	//diag.CancelButton.value = "确 定";
	try{
		diag.CancelButton.focus();
		pw.$o("_DialogButtons_"+diag.ID).style.textAlign = "center";
	}catch(ex){}//有可能不能focus;
}

Dialog.confirm = function(msg,func1,func2,w,h){
	var pw = $E.getTopLevelWindow();
	if(diag != "undefined" && null != diag) {
		diagclose(diag);
	}
	diag = new Dialog("_DialogAlert"+pw.Dialog.AlertNo++);
	diag.Width = w||300;
	diag.Height = h||120;
	diag.Title = "信息确认";
	diag.URL = "javascript:void(0);";
	diag.AlertFlag = true;
	diag.show();
	pw.$o("_AlertBGDiv").show();
	var diagFrame=pw.$o("_DialogFrame_"+diag.ID);
	var win = diagFrame.contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<body oncontextmenu='return false;'></body>") ;
	var arr = [];
	arr.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'><tr>");
	arr.push("<td align='left' id='Message' style='font-size:9pt' colspan='2'>"+msg+"</td></tr>");
	arr.push("<tr><td align='center'><input type='button' id='_confirmOKButton' value='确定' class='inputButton'></td>");
	arr.push("<td align='center'><input type='button' id='_confirmCButton' value='取消' class='inputButton'></td>");
	arr.push("</tr></table>");
	var div = doc.createElement("div");
	div.innerHTML = arr.join('');
	doc.body.appendChild(div);
	doc.getElementById("_confirmOKButton").onclick = function() {
		func1();
		diagclose(diag);
	}
	doc.getElementById("_confirmCButton").onclick = function() {
		func2();
		diagclose(diag);
	};
	doc.close();
}

Dialog.wait = function(msg){//某些地方不需要进度条但后台执行时间又比较长的可以使用此方法
	var pw = $E.getTopLevelWindow();
	var script = [];
	Dialog.alert(msg,null);
	pw.Dialog.WaitID = pw.setTimeout(pw.Dialog.waitAction,1000);
	var diag = pw.Dialog.getInstance("_DialogAlert"+(pw.Dialog.AlertNo-1));
	diag.CancelButton.disable();
	diag.CancelButton.onclick = function(){};
	pw.Dialog.WaitSecondCount = 0;
}

Dialog.waitAction = function(){
	var pw = $E.getTopLevelWindow();
	var diag = pw.Dialog.getInstance("_DialogAlert"+(pw.Dialog.AlertNo-1));
	if(!diag){
		return;	
	}
	pw.Dialog.WaitSecondCount++;
	diag.CancelButton.value = "请等待("+pw.Dialog.WaitSecondCount+")..."
	pw.Dialog.WaitID = pw.setTimeout(pw.Dialog.waitAction,1000);
}

var _DialogInstance = window.frameElement?window.frameElement.DialogInstance:null;

var Page = {};

Page.wait = function(){//通过在当前页面加入透明层的方式阻止页面继续响应事件，主要用于防止用户两次点击按钮
	var bgdiv = $o("_WaitBGDiv");
	if(!bgdiv){
		var bgdiv = document.createElement("div");
		bgdiv.id = "_WaitBGDiv";
		$E.hide(bgdiv);
	 	$T("body")[0].appendChild(bgdiv);
		bgdiv.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0.03;filter:alpha(opacity=3);width:100%;height:100%;z-index:991";
	}
	var mh = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
	bgdiv.style.height = mh+"px";
	$E.show(bgdiv);
}

Page.endWait = function(){
	var bgdiv = $o("_WaitBGDiv");
	if(bgdiv){
		$E.hide(bgdiv);
	}
}

Page.clickFunctions = [];
Page.click = function(event){
	for(var i=0;i<Page.clickFunctions.length;i++){
		Page.clickFunctions[i](event);
	}
	if(window!=window.parent&&window.parent.Page){
		window.parent.Page.click();
	}
}
Page.onClick = function(f){
	Page.clickFunctions.push(f);
}

Page._Sort = function(a1,a2){
	var i1 = a1[1];
	var i2 = a2[1];
	if(typeof(i1)=="number"){
		if(typeof(i2)=="number"){
			if(i1>i2){
				return 1;
			}else if(i1==i2){
				return 0;
			}else{
				return -1;
			}
		}
		return -1;
	}else{
		if(typeof(i2)=="number"){
			return 1;
		}else{
			return 0;
		}
	}
}

Page.loadFunctions = [];
Page.load = function(){
	/*
	if(!Page.isReadyExecuted){//要保证ready,load先后顺序
		Page.isLoadWaiting = true;
		return;
	}
	*/
	if(window._OnLoad){//Select控件会用到
		try{window._OnLoad();}catch(ex){alert(ex.message)}
	}
	if(window.frameElement&&window.frameElement._OnLoad){//Select控件会用到
		try{window.frameElement._OnLoad();}catch(ex){alert(ex.message)}
	}
	Page.loadFunctions.sort(Page._Sort);
	for(var i=0;i<Page.loadFunctions.length;i++){
		try{Page.loadFunctions[i][0]();}catch(ex){}
	}
}

Page.onLoad = function(f,index){
	Page.loadFunctions.push([f,index]);
}

Page.readyFunctions = [];
Page.ready = function(){
	if(window._OnReady){
		try{window._OnReady();}catch(ex){}
	}
	Page.readyFunctions.sort(Page._Sort);
	for(var i=0;i<Page.readyFunctions.length;i++){
		try{Page.readyFunctions[i][0]();}catch(ex){}
	}
	/*
	Page.isReadyExecuted = true;
	if(Page.isLoadWaiting){
		Page.load();
	}
	*/
};

Page.onReady= function(f,index){
	Page.readyFunctions.push([f,index]);
};

Page.mouseDownFunctions = [];
Page.mousedown = function(event){
	for(var i=0;i<Page.mouseDownFunctions.length;i++){
		Page.mouseDownFunctions[i](event);
	}
}

Page.onMouseDown = function(f){
	Page.mouseDownFunctions.push(f);
}

Page.mouseUpFunctions = [];
Page.mouseup = function(event){
	for(var i=0;i<Page.mouseUpFunctions.length;i++){
		Page.mouseUpFunctions[i](event);
	}
}

Page.onMouseUp = function(f){
	Page.mouseUpFunctions.push(f);
}

Page.mouseMoveFunctions = [];
Page.mousemove = function(event){
	for(var i=0;i<Page.mouseMoveFunctions.length;i++){
		Page.mouseMoveFunctions[i](event);
	}
}

Page.onMouseMove = function(f){
	Page.mouseMoveFunctions.push(f);
}

if(document.attachEvent){
	document.attachEvent('onclick',Page.click);
	document.attachEvent('onmousedown',Page.mousedown);
	window.attachEvent('onload',Page.load);
	document.attachEvent('onmouseup',Page.mouseup);
	document.attachEvent('onmousemove',Page.mousemove);
}else{
	document.addEventListener('click',Page.click,false);
	document.addEventListener('mousedown',Page.mousedown,false);
	window.addEventListener('load',Page.load,false);
	document.addEventListener('mouseup',Page.mouseup,false);
	document.addEventListener('mousemove',Page.mousemove,false);
}

Page.isReady=false;
Page.bindReady = function(evt){
	if(Page.isReady) return;
	Page.isReady=true;
	Page.ready.call(window);
	if(document.removeEventListener){
		document.removeEventListener("DOMContentLoaded", Page.bindReady, false);
	}else if(document.attachEvent){
		document.detachEvent("onreadystatechange", Page.bindReady);
		if(window == window.top){
			clearInterval(Page._Timer);
			Page._Timer = null;
		}
	}
};
if(document.addEventListener){
	document.addEventListener("DOMContentLoaded", Page.bindReady, false);
}else if(document.attachEvent){
	document.attachEvent("onreadystatechange", function(){
		if((/loaded|complete/).test(document.readyState))
			Page.bindReady();
	});
	if(window == window.top){
		Page._Timer = setInterval(function(){
			try{
				Page.isReady||document.documentElement.doScroll('left');//在IE下用能否执行doScroll判断dom是否加载完毕
			}catch(e){
				return;
			}
			Page.bindReady();
		},5);
	}
}

/**
onWindowResize(func)
在非ie6浏览器中使用此方法，等同于window.attachEvent('onresize',func)
在ie6下使用此方法注册的方法，在仅仅页面尺寸变化，而窗口尺寸并没有变化时，不会执行。
用于修正ie6下body尺寸改变时会激发onresize事件的问题，
**/
var onWindowResize=(function(){
	var viewportBackup=null;//通过闭包来避免使用全局变量
	var doViewportBackup=function(){
		viewportBackup=$E.getViewportDimensions();
	}
	window.attachEvent('onload',doViewportBackup);
	var onResize = function(fn){
		if(!fn||typeof(fn)!='function'){
			throw ' Main.js#onWindowResize: 参数错误，请确定参数类型为function';
			return;
		}
		var onWindowResizeFn=null;
		if(isIE6){
			onWindowResizeFn=function(){
				var viewport=$E.getViewportDimensions();
				if(viewportBackup){
					if(viewport.width!==viewportBackup.width||viewport.height!==viewportBackup.height){
						viewportBackup={width:viewport.width,height:viewport.height};
						try{
							fn()
						}catch(ex){
							throw location.pathname+' Main.js#onWindowResize: 传递给onWindowResize的方法执行错误'
						}
					}
				}else{
					try{
						fn()
					}catch(ex){}
				}
			}
		}else{
			onWindowResizeFn=fn;
		}
		window.attachEvent('onresize',onWindowResizeFn);
	};
	return onResize;
})();

Page.onDialogLoad = function(){
	if(_DialogInstance){
		if(_DialogInstance.Title){
			document.title = _DialogInstance.Title;
		}
		window.Args = _DialogInstance.DialogArguments;
		_DialogInstance.Window = window;
		window.Parent = _DialogInstance.ParentWindow;
	}
}
Page.onDialogLoad();

Page.onReady(function (){
	var d = _DialogInstance;
	if(d){
		try{
			d.ParentWindow.$DW = d.Window;
			var flag = false;
			if(!d.AlertFlag){
				var win = d.ParentWindow;
				while(win!=win.parent){
					if(win._DialogInstance){
						flag = true;
						break;
					}
					win = win.parent;
				}
				if(!flag){
					//if(d.Animator)
					//Effect.fade($E.getTopLevelWindow().$o("_DialogBGDiv"),0,3,0.2);//如果不是对话框里弹出的对话框，则显示渐变效果
				}
			}
			if(d.AlertFlag){
				$E.show($E.getTopLevelWindow().$o("_AlertBGDiv"));
			}
			if(d.onLoad){
				Page.onLoad(function(){
					d.onLoad();
				});
			}
		}catch(ex){alert("DialogOnLoad:"+ex.message+"\t("+ex.fileName+" "+ex.lineNumber+")");}
	}
},4);

Page.onMouseUp(DragManager.onDragExit);
Page.onMouseMove(DragManager.onMouseMove);

Dialog.onKeyDown = function(event){
	if(event.shiftKey&&event.keyCode==9){//屏蔽shift+tab键
		var pw = $E.getTopLevelWindow();
		if(pw.Dialog._Array.length>0){
			stopEvent(event);
			return false;
		}
	}
	if(event.keyCode==27){//按ESC键关闭Dialog
		var pw = $E.getTopLevelWindow();
		if(pw.Dialog._Array.length>0){
			Page.mousedown();
			Page.click();
			var diag = pw.Dialog.getInstance(pw.Dialog._Array[pw.Dialog._Array.length-1]);
			diag.CancelButton.onclick.apply(diag.CancelButton,[]);
		}
	}
};

Dialog.dragStart = function(evt){
	DragManager.doDrag(evt,this.getParent("div"));
}
Dialog.dragExit = function(evt){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var vp = $E.getViewportDimensions(pw);
	var cw = vp.width;
	var ch = vp.height;
	var sw = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
	var div = this.getParent("div");
	var left=parseInt(div.style.left);
	var top=parseInt(div.style.top);

	//if(left<0)
	//	div.style.left='0px';
	if(left+div.clientWidth-93<0)
		div.style.left=93-div.clientWidth+'px';
	if(left+div.clientWidth>cw)
		div.style.left=cw-div.clientWidth+'px';
	if(top<sh)
		div.style.top=sh+'px';
	if(top+44>sh+ch)
		div.style.top=sh+ch-44+'px';
}

Dialog.setPosition=function(){
	if(window.parent!=window)return;
	var pw = $E.getTopLevelWindow();
	var DialogArr=pw.Dialog._Array;
	if(DialogArr==null||DialogArr.length==0)return;

	for(i=0;i<DialogArr.length;i++)
	{
		pw.$o("_DialogFrame_"+DialogArr[i]).DialogInstance.setPosition();
	}
}
Dialog.prototype.setPosition=function(){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var vp = $E.getViewportDimensions(pw);
	var cw = vp.width;
	var ch = vp.height;
	var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);
	var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	sw=Math.max(sw,cw);
	sh=Math.max(sh,ch);
	this.Top = (ch - this.Height - 30) / 2 + st - 8;//有8像素的透明背景
	this.Left = (cw - this.Width - 12) / 2 +sl;
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	this.DialogDiv.style.top=Math.round(this.Top)+"px";
	this.DialogDiv.style.left=Math.round(this.Left)+"px";
	//pw.$o(this.bgdivID).style.width= sw + "px";
	pw.$o(this.bgdivID).style.height= sh + "px";
}

var Misc = {};
Misc.setButtonText = function(ele,text){//为z:button设置文本
	$o(ele).childNodes[1].innerHTML = text+"&nbsp;";
}

Misc.withinElement = function(event, ele) {//仅适用于Gecko,判断onmouseover,onmouseout是否是一次元素内部重复触发
	return false;
	var parent = event.relatedTarget;
	while(parent&&parent!=ele&&parent!=document.body){
		try{
			parent = parent.parentNode;
		}catch(ex){
			alert("Misc.withinElement:"+ex.message);
			return false;
		}
	}
	return parent == ele;
}

Misc.copyToClipboard = function(text){
	if(text==null){
		return;
	}
	if (window.clipboardData){
		window.clipboardData.setData("Text", text);
	}else if (window.netscape){
		try{
			netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
		}catch(ex){
			Dialog.alert("Firefox自动复制功能未启用！<br>请<a href='about:config' target='_blank'>点击此处</a> 将’signed.applets.codebase_principal_support’设置为’true’");
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		if(!clip){return;}
		var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		if(!trans){return;}
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		var copytext=text;
		str.data=copytext;
		trans.setTransferData("text/unicode",str,copytext.length*2);
		var clipid=Components.interfaces.nsIClipboard;
		if(!clip){return;}
		clip.setData(trans,null,clipid.kGlobalClipboard);
	}else{
		alert("该浏览器不支持自动复制功能！");
		return;
	}
	Dialog.alert("己复制文字：<br><br><font color='red'>"+text+"</font><br><br>到剪贴板",null,400,200);
}

Misc.lockSelect = function(ele){
	if(!ele){
		ele = document.body;
	}
	if(isGecko){
		ele.style.MozUserSelect = "none";
		ele.style.MozUserInput = "none";
	}else{
		document.selection.empty();
		ele.onselectstart = stopEvent;
	}
}

Misc.unlockSelect = function(ele){
	if(!ele){
		ele = document.body;
	}
	if(isGecko){
		ele.style.MozUserSelect = "";
		ele.style.MozUserInput = "";
	}else{
		ele.onselectstart = null;
	}
}

Misc.lockScroll = function(win){
	if(!win){
		win = window;
	}
	if(isIE){
		win.document.body.attachEvent("onmousewheel",win.stopEvent);
	}else{
		win.addEventListener("DOMMouseScroll", win.stopEvent, false);
	}
}

Misc.unlockScroll = function(win){
	if(!win){
		win = window;
	}
	if(isIE){
		win.document.body.detachEvent("onmousewheel",win.stopEvent);
		win.document.body.detachEvent("onmousewheel",win.stopEvent);//Select.js中可能会连续attch两次,所以必须detach两次
	}else{
		win.removeEventListener("DOMMouseScroll", win.stopEvent, false);
	}
}

function toArray(a,i,j) {//把可枚举的集合转换为数组 
	 if(isIE){
		 var res = [];
		 for (var x = 0,
		 len = a.length; x < len; x++) {
			 res.push(a[x])
		 }
		 return res.slice(i || 0, j || res.length)
	 }else{
		 return Array.prototype.slice.call(a, i || 0, j || a.length)
	 }
}

Afloat=function(id,pos){//漂浮元素使之固定在窗口底部或项部，pos值为字符串'top'或'bottom'，默认为'bottom'
	this.pos=pos||'bottom';
	this.dom=$o(id);
	if(this.dom.tagName=='TD'){
		var wrap=document.createElement('div');
		var children=this.dom.children;
		children=toArray(children);
		children.each(function(el){wrap.appendChild(el);})
		this.dom.appendChild(wrap);
		this.dom=$o(wrap);
	}
	this.init();
	var me=this;
	window.attachEvent("onscroll",function(){me.setPosition()})
	onWindowResize(function(){me.setPosition()})
}
Afloat.prototype={
	init:function(){
		this.dom.style.position="static";
		var domPosition=this.dom.getPosition();
		this.fixX=Math.round(domPosition.x);
		this.fixY=Math.round(domPosition.y);//当目标元素处于隐藏状态的该值可能为0
		this.fixWidth=this.dom.offsetWidth;//当目标元素处于隐藏状态的该值可能为0
		this._width=this.dom.style.width;
	    this.fixHeight=this.dom.offsetHeight;
		this.dom.style.left=this.fixX+'px';
	    this.viewportH=$E.getViewportDimensions().height;
	},
	setPosition:function(){
		 if(this.fixY==0||this.fixWidth==0){
			this.init();
		 }
		 var st = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
		 if((this.pos=='bottom'&&st+this.viewportH<this.fixY)
			||(this.pos=='top'&&st>this.fixY)
		 ){
			 if(isIE6){
				 this.dom.style.position="absolute";
				 this.dom.style.top = this.pos=='bottom'?
					st+this.viewportH-this.fixHeight+'px' : st+'px';
			 }else{
				 this.dom.style.position="fixed";
				 this.pos=='bottom'?this.dom.style.bottom='0':this.dom.style.top='0';
			 }
			this.dom.style.width=this.fixWidth+'px';
		 }else{
			this.dom.style.position="static";
			this.dom.style.width=this._width;
		 }
	 }
}

if(isIE){
	document.attachEvent("onkeydown",Dialog.onKeyDown);
	window.attachEvent('onresize',Dialog.setPosition);
}else{
	document.addEventListener("keydown",Dialog.onKeyDown,false);
	window.addEventListener('resize',Dialog.setPosition,false);
}

$E.computePositionEx = function(ele1,ele2){
	var pos = $E.getPositionEx(ele1);
	var dim = $E.getDimensions(ele1);
	var dim2 = $E.getDimensions(ele2);
	return $E.computePosition(pos.x+dim.width,pos.y,pos.x+dim.width,pos.y+dim.height,"all",dim2.width,dim2.height,$E.getTopLevelWindow());
}
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
}

String.prototype.leftPad = function(c,count){
	if(!isNaN(count)){
		var a = "";
		for(var i=this.length;i<count;i++){
			a = a.concat(c);
		}
		a = a.concat(this);
		return a;
	}
	return null;
}

String.prototype.rightPad = function(c,count){
	if(!isNaN(count)){
		var a = this;
		for(var i=this.length;i<count;i++){
			a = a.concat(c);
		}
		return a;
	}
	return null;
}
var Calendar = {
	monthNames : ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
	weekNames : ["日","一","二","三","四","五","六"]
}

var TimeSelector = {};

var DateTime = {};
DateTime.getCurrentDate = function(){
	return DateTime.toString(new Date());
}

DateTime.getCurrentTime = function(){
	var date = new Date();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	var arr = [];
	arr.push(h>9?h:"0"+h);
	arr.push(m>9?m:"0"+m);
	arr.push(s>9?s:"0"+s);
	return arr.join(":");
}

DateTime.toString = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var arr = [];
	arr.push(y);
	arr.push(m>9?m:"0"+m);
	arr.push(d>9?d:"0"+d);
	return arr.join("-");
}

DateTime.parseDate = function(str){//解析形如yyyy-MM-dd hh:mm:ss的日期字符串为Date对象
	var regex = /^(\d{4})-(\d{1,2})-(\d{1,2})(\s(\d{1,2}):(\d{1,2})(:(\d{1,2}))?)?$/;
	if(!regex.test(str)){
		//alert("DateTime.parseDate:错误的日期"+str);
	}
	regex.exec(str);
	var y = RegExp.$1;
	var M = RegExp.$2;
	var d = RegExp.$3;
	var h = RegExp.$5;
	var m = RegExp.$6;
	var s = RegExp.$8;
	var date = new Date();
	date.setYear(y);
	date.setMonth(M-1);
	date.setDate(d);
	if(!h){
		h = 0;
		m = 0;
	}		
	date.setHours(h);
	date.setMinutes(m);
	if(!s){
		s=0;
	}
	date.setSeconds(s);
	return date;	
}

DateTime.LastID = new Date().getTime();
DateTime.initCtrl = function(ele){
	if(ele.onmousedown == DateTime.onMouseDown){
		return;//已经初始化了
	}
	ele = $o(ele);
	var ztype = ele.$A("ztype");
	if(ztype){
		var str;
		var date = new Date();
		if(ztype.toLowerCase()=="date"){
			str =  "Calendar";
		}else if(ztype.toLowerCase()=="time"){
			str =  "TimeSelector";
		}else{
			return;	
		}
		if(ele.$A("init")=="true"){
			if(ztype.toLowerCase()=="date"){
				ele.value = DateTime.getCurrentDate();	
			}else{
				ele.value = DateTime.getCurrentTime();	
			}
		}
    var id = ele.id;
    if(id==null||id==""){
    	ele.id = id = DateTime.LastID++;
    }
    ele.insertAdjacentHTML("afterEnd","<img src='"+Server.ContextPath+"Framework/Images/"+str+".gif' align='absmiddle' vspace='1' style='position:relative; left:-20px; margin-right:-20px; cursor:pointer;' onmousedown=\"DateTime.onImageMouseDown(event,'"+str+"','"+id+"');\">");
    ele.attachEvent("onfocus",function(){
    	eval(str+".show('"+id+"')");
    });
    ele.onmousedown = DateTime.onMouseDown;
	}
}

DateTime.onImageMouseDown = function(evt,str,id){
	Calendar.close();
	TimeSelector.close();
	var pw = $E.getTopLevelWindow();
	if(pw.DateTime&&id==pw.DateTime.showingID){
		return;
	}
	eval(str+".show(id)");
	stopEvent(evt);
}

DateTime.onMouseDown = function(evt){
	var pw = $E.getTopLevelWindow();
	if(this.id==pw.DateTime.showingID&&pw.DateTimeWindow==window){
		cancelEvent(evt);
	}
}

Page.onMouseDown(function(){
		Calendar.close();
		TimeSelector.close();
});

function _LeftPad(str,c,count){
	str = ""+str;
	return str.leftPad(c,count);
}

TimeSelector.setTime = function(time){
	var h,m,s;
	if(time){
		if(!/\d{1,2}\:\d{1,2}(\:\d{1,2})?/.test(time)){
			Dialog.alert("错误的时间:"+time);
		}
		var arr = time.split(":");
		h = parseInt(arr[0]);
		m = parseInt(arr[1]);
		s = parseInt(arr[2]);
	}else{
		var d = new Date();
		h = d.getHours();
		m = 0;
		s = 0;
	}
	h = h>23?23:h;
	m = m>59?59:m;
	s = s>59?59:s;
	m = m>=10?m:"0"+m;
	s = s>=10?s:"0"+s;
	var win = $o("_TimeSelector_Frame").contentWindow;
	var arr = win.$o('divWrapper').getElementsByTagName('td');
	var len = arr.length;
	for(var i=0;i<len;i++){
		arr[i].className='';
	} 
	win.$o("selectorHour").innerHTML = h;
	win.$o("selectorMinute").innerHTML = m;
	win.$o("selectorSecond").innerHTML = s;
	win.$o("_TimeSelector_Tip").innerHTML = h+":"+m+":"+s;
	win.$o("divHour").getElementsByTagName("td")[parseInt(h)].className = "selected";
	win.$o("divMinute").getElementsByTagName("td")[parseInt(m)].className = "selected";
	win.$o("divSecond").getElementsByTagName("td")[parseInt(s)].className = "selected";
	TimeSelector.showType('Hour');//重置当前选择类别
	return true;
}

TimeSelector.setTip = function(){
	$o("_TimeSelector_Tip").innerText = [$V("_TimeSelector_Hour"),$V("_TimeSelector_Minute"),$V("_TimeSelector_Second")].join(":");
}

TimeSelector.setNow = function(){
	$S(Control,DateTime.getCurrentTime());
	var _evt = Control.getAttribute("onchange");
	if(_evt){
		eval(_evt);
	}
	TimeSelector.close();
}

TimeSelector.returnTime = function(flag){
	var win = $o("_TimeSelector_Frame").contentWindow;
	if(flag){
		$S(win.Control,DateTime.getCurrentTime());
	}else{
		var arr = [win.$o("selectorHour").innerHTML,win.$o("selectorMinute").innerHTML,win.$o("selectorSecond").innerHTML];
		$S(win.Control,arr.join(":"));
	}
	TimeSelector.close();
}

TimeSelector.showType = function(type){
	var win = $o("_TimeSelector_Frame").contentWindow;
	var arr = ["Hour","Minute","Second"];
	for(var i=0;i<arr.length;i++){
		win.$o("selector"+arr[i]).className = "selector";
		win.$o('div'+arr[i]).style.display = 'none';
	}
	win.$o('div'+type).style.display = '';
	win.$o('selector'+type).className = 'selector_current';
	TimeSelector.adjustSize();
}

TimeSelector.show = function(ctrl,time){
	var pw = $E.getTopLevelWindow();
	ctrl = $o(ctrl);
	try{ctrl.onfocus.apply(ctrl,[]);}catch(ex){}
	time = time?time:$V(ctrl);
	var ele;
	if(!pw.$o("_TimeSelector")){
		ele = pw.document.createElement('div');
		ele.id = "_TimeSelector";
		ele.style.position = "absolute";
		ele.style.zIndex = 999;
		ele.innerHTML = "<iframe id='_TimeSelector_Frame' frameborder=0 scrolling=no width=194 height=153></iframe>";
		ele.style.width = "194px";
		pw.document.body.appendChild(ele) ; 
		ele.style.display = '';

		var win = pw.$o("_TimeSelector_Frame").contentWindow;
		var doc = win.document;
		doc.open();
		var arr = [];
		arr.push("<style>");
		arr.push("body {margin: 0px;}");
		arr.push(".timetable {}");
		arr.push(".timetable {position: absolute;	border-top: 1px solid #777;	border-right: 1px solid #555;border-bottom: 1px solid #444;font-family: tahoma,verdana,sans-serif;");
		arr.push("border-left: 1px solid #666;font-size: 11px;cursor: default;background: #fff;}");
		arr.push(".timetable .buttonNow {text-align: center;	background-color:#def;	border-right: 1px solid #999;color:#000;font-size: 12px;}");
		arr.push(".timetable .buttonConfirm {text-align: center;	background-color:#def;	border-left: 1px solid #999;color:#000;font-size: 12px;}");
		arr.push(".timetable .buttonclose {color:#06c;text-align: center;	background-color:#def;border-left: 1px solid #999;font-size:9px;width:16px}");
		arr.push(".timetable td.selected {font-weight: bold;border: 1px solid #39f;	background: #c3e4FF;}");
		arr.push(".timetable td.now {font-weight: bold; color: #03f;}");
		arr.push(".timetable .tipnow {font-weight: bold;font-size:12px;color: #258;text-align: left;}");
		arr.push(".timetable td.over {border:1px solid #06c;background: #EDFBD2;}");
		arr.push(".selector {color:#258;padding:0 8px;border-right: 1px solid #999;border-left: 1px solid #999;background: #def;}");
		arr.push(".selector_current {color:#fff;padding:0 8px;border-right: 1px solid #999;border-left: 1px solid #999;background: #ff8800;}");
		arr.push(".wrapper {background-color:#fff;border-top: 1px solid #999;	border-bottom: 1px solid #999;text-align: center;}");
		arr.push(".wrapper td{border: 1px solid #fff;	font-size: 12px;text-align: center;	color: #06c;}");
		arr.push("</style>");
		arr.push("<body><div class='timetable' id='_TimeSelector_Table'>");
		arr.push("<table border='0' cellpadding='0' cellspacing='0' onselectstart='return false;' oncontextmenu='return false'>");
		arr.push("<tr><td><table width='100%' border='0' cellpadding='0' cellspacing='0'>");
		arr.push("<tr><td height='18' class='tipnow'>");
		arr.push("<table width='100' height='100%' border='0' cellpadding='0' cellspacing='0'>");
		arr.push("<tr class='tipnow'>");
		arr.push("<td valign='middle' class='selector_current' id='selectorHour' onclick=\"TopWindow.TimeSelector.showType('Hour')\">0</td>");
		arr.push("<td valign='middle' align='center'><span style='padding:3px;'>:</span></td>");
		arr.push("<td valign='middle' class='selector' id='selectorMinute' onclick=\"TopWindow.TimeSelector.showType('Minute')\">00</td>");
		arr.push("<td valign='middle' align='center'><span style='padding:3px;'>:</span></td>");
		arr.push("<td valign='middle' class='selector' id='selectorSecond' onclick=\"TopWindow.TimeSelector.showType('Second')\">00</td>");
		arr.push("</tr></table></td>");
		arr.push("<td width='16'><table height='100%' height='100%' border='0' cellpadding='0' cellspacing='0'><tr><td class='buttonclose' title='取消' valign='middle' onclick=\"TopWindow.TimeSelector.close();this.style.backgroundColor='#def'\" onmouseover=\"this.style.backgroundColor='#9cf'\" onmouseout=\"this.style.backgroundColor='#def'\">×</td></tr></table></td>");
		arr.push("</tr></table>");
		arr.push("<div class='wrapper' id='divWrapper'>");
		arr.push("<div id='divHour'>");
		arr.push("<table width='210' height='60' border='0' cellpadding='0' cellspacing='0' style='font-size:13px'>");
		for(var i=0;i<24;i++){
			if(i%8==0){
				arr.push("<tr>");		
			}
			if(i%12==0){
				arr.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)' style='color: #e70'>"+i+"</td>");
			}else{
				arr.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)'>"+i+"</td>");
			}
			if(i%8==7){
				arr.push("</tr>");
			}
		}
		arr.push("</table>");
		arr.push("</div>");
		arr.push("<div id='divMinute' style='display:none'>");
		var html = [];
		html.push("<table width='210' height='120' border='0' cellpadding='0' cellspacing='0'>");
		for(var i=0;i<60;i++){
			if(i%10==0){
				html.push("<tr>");
				html.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)' style='color: #e70'>"+(i>=10?i:"0"+i)+"</td>");
			}else{
				html.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)'>"+(i>=10?i:"0"+i)+"</td>");
			}
			if(i%10==9){
				html.push("</tr>");
			}
		}
		html.push("</table>");
		html.push("</div>");
		arr.push(html.join('\n'));
		arr.push("<div id='divSecond' style='display:none'>");
		arr.push(html.join('\n'));
		arr.push("</div>");
		arr.push("<table width='100%' border='0' align='left' cellpadding='0' cellspacing='0'>");
		arr.push("<tr>");
		arr.push("<td width='17%' height='20' class='buttonNow' onclick=\"TopWindow.TimeSelector.returnTime(true);this.style.backgroundColor='#def'\" onmouseover=\"this.style.backgroundColor='#9cf'\" onmouseout=\"this.style.backgroundColor='#def'\">现在</td>");
		arr.push("<td width='66%' style='font-size:11px;background-color:#fff6cc;font-weight:bold;color:#258;' id='_TimeSelector_Tip' align='center'>0:00:00</td>");
		arr.push("<td width='17%' height='20' class='buttonConfirm' onclick=\"TopWindow.TimeSelector.returnTime();this.style.backgroundColor='#def'\" onmouseover=\"this.style.backgroundColor='#9cf'\" onmouseout=\"this.style.backgroundColor='#def'\">确定</td>");
		arr.push("</tr>");
		arr.push("</table>");
		arr.push("</td>");
		arr.push("</tr>");
		arr.push("</table>");
		arr.push("</div></body>");
		arr.push("<script>function $o(ele){return document.getElementById(ele);};function setTime(){if(!TopWindow.TimeSelector.setTime(Control.value)){}TopWindow.TimeSelector.adjustSize();}</script>");
		doc.write(arr.join("\n"));
		doc.close();				
		win.Control = ctrl;
		win.TopWindow = pw;
		win.setTime();
	}else{
		ele = pw.$o("_TimeSelector");
		ele.show();
		var frame = pw.$o("_TimeSelector_Frame");
		frame.show();
		frame.contentWindow.Control = ctrl;
		frame.contentWindow.setTime();
	}	
	var pos1 = $E.computePositionEx(ctrl,ele);
	ele.style.top = pos1.y+"px";
	ele.style.left = pos1.x+"px";
	pw.DateTime.showingID = ctrl.id;
	pw.DateTimeWindow = window;
	Misc.lockScroll(window);
}

TimeSelector.onMouseOver = function(cell){
	var win = $o("_TimeSelector_Frame").contentWindow;
	var id = cell.parentNode.parentNode.parentNode.parentNode.id;
	var arr = [win.$o("selectorHour").innerHTML,win.$o("selectorMinute").innerHTML,win.$o("selectorSecond").innerHTML];
	if(id=="divHour"){
		arr[0] = cell.innerHTML;
	}else if(id=="divMinute"){
		arr[1] = cell.innerHTML;
	}else if(id=="divSecond"){
		arr[2] = cell.innerHTML;
	}
	win.$o("_TimeSelector_Tip").innerHTML = arr.join(":");
	$E.addClassName("over",true,cell);
}
	
TimeSelector.onMouseOut = function(cell){
	$E.removeClassName("over",cell);
}

TimeSelector.onClick = function(cell){
	var win = $o("_TimeSelector_Frame").contentWindow;
	$E.addClassName("selected",true,cell);
	var id = cell.parentNode.parentNode.parentNode.parentNode.id;
	if(id=="divHour"){
		win.$o("divHour").getElementsByTagName("td")[parseInt(win.$o("selectorHour").innerHTML)].className = "";
		win.$o("selectorHour").innerHTML = cell.innerHTML;		
		TimeSelector.showType('Minute');
	}else if(id=="divMinute"){
		win.$o("divMinute").getElementsByTagName("td")[parseInt(win.$o("selectorMinute").innerHTML)].className = "";
		win.$o("selectorMinute").innerHTML = cell.innerHTML;		
		TimeSelector.showType('Second');
	}else if(id=="divSecond"){
		win.$o("divSecond").getElementsByTagName("td")[parseInt(win.$o("selectorSecond").innerHTML)].className = "";
		win.$o("selectorSecond").innerHTML = cell.innerHTML;		
		TimeSelector.returnTime();
	}
	var pw = $E.getTopLevelWindow();
	Misc.unlockScroll(pw.DateTimeWindow);
}

TimeSelector.close = function(){
	var pw = $E.getTopLevelWindow();
	if(pw.DateTime&&pw.$o("_TimeSelector")&&pw.$o("_TimeSelector").visible()){
  	var frame = pw.$o("_TimeSelector_Frame");
		try{frame.contentWindow.Control.onblur.apply(frame.contentWindow.Control,[]);}catch(ex){}
		$E.hide(pw.$o("_TimeSelector"));
  	Misc.unlockScroll(pw.DateTimeWindow);			
		pw.DateTimeWindow = null;
  	pw.DateTime.showingID = false;
  }
}	

Calendar.showYearSelector = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var eleYear = win.$o("_Calendar_Year"),eleSelector = win.$o("_Calendar_YearSelector");
	eleYear.style.display = 'none';
	eleSelector.style.display = '';
	var year = eleYear.Year;
	for(var i=year>50?year-50:0;i<=50+parseInt(year);i++){
		eleSelector.options.add(new Option(i, i));
	}
	eleSelector.focus();
	eleSelector.selectedIndex = 50;
	Calendar.adjustSize();
}

Calendar.showMonthSelector = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var eleMonth = win.$o("_Calendar_Month"),eleSelector = win.$o("_Calendar_MonthSelector");
	eleMonth.style.display = 'none';
	eleSelector.style.display = '';
	eleSelector.focus();
	eleSelector.selectedIndex = eleMonth.Month;
	Calendar.adjustSize();
}

Calendar.hideYearSelector = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var eleYear = win.$o("_Calendar_Year"),eleSelector = win.$o("_Calendar_YearSelector");
	eleYear.style.display = '';
	eleSelector.style.display = 'none';
	for(var i=eleSelector.options.length; i>-1; i--) eleSelector.remove(i);
	Calendar.adjustSize();
}
	
Calendar.hideMonthSelector = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	win.$o("_Calendar_Month").style.display = '';
	win.$o("_Calendar_MonthSelector").style.display = 'none';
	Calendar.adjustSize();
}

Calendar.adjustSize = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var dim = $E.getDimensions(win.$o("_Calendar_Table"));
	win.frameElement.height = dim.height+1;
	win.frameElement.width = dim.width+3;	
}

TimeSelector.adjustSize = function(){
	var win = $o("_TimeSelector_Frame").contentWindow;
	var dim = $E.getDimensions(win.$o("_TimeSelector_Table"));
	win.frameElement.height = dim.height;
	win.frameElement.width = dim.width+3;
}

Calendar.onYearSelectorChange = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var eleYear = win.$o("_Calendar_Year"),eleSelector = win.$o("_Calendar_YearSelector");
	eleYear.Year = eleSelector.value;
	var date = eleYear.Year+"-"+_LeftPad(win.$o("_Calendar_Month").Month+1,"0",2)+"-01";
	Calendar.setDate(date);
	eleYear.style.display = '';
	eleSelector.style.display = 'none';
	Calendar.adjustSize();
}
	
Calendar.onMonthSelectorChange = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var eleMonth = win.$o("_Calendar_Month"),eleSelector = win.$o("_Calendar_MonthSelector");
	eleMonth.Month = parseInt(eleSelector.value);
	var date = win.$o("_Calendar_Year").Year+"-"+_LeftPad(eleMonth.Month+1,"0",2)+"-01";
	Calendar.setDate(date);
	eleMonth.style.display = '';
	eleSelector.style.display = 'none';
	Calendar.adjustSize();
}

Calendar.getDateString = function(cell){
	var win = $o("_Calendar_Frame").contentWindow;
	var Control = win.Control;
	var format = Control.$A("format");
	if(!format){
		format = "yyyy-MM-dd";
	}
	if(cell.Day){
		var day = cell.Day,month=win.$o("_Calendar_Month").Month,year=win.$o("_Calendar_Year").Year;
		if(day<0){
			day = -day;
			if(month==0){month = 11;year--;}else{month--;}
		}else if(day>32){
			day -= 40;
			if(month==11){month = 0;year++;}else{month++;}
		}
		format = format.replace("yyyy",year);
		format = format.replace("MM",_LeftPad(month+1,"0",2));
		format = format.replace("dd",_LeftPad(day,"0",2));
		return format;
	}else if(cell.id=="_Calendar_Today"){
		var d = new Date();
		format = format.replace("yyyy",isGecko?d.getYear()+1900:d.getYear());
		format = format.replace("MM",_LeftPad(d.getMonth()+1,"0",2));
		format = format.replace("dd",_LeftPad(d.getDate(),"0",2));
		return format;
	}else{
		return false;
	}
}
	
Calendar.onMouseOver = function(cell){
	var win = $o("_Calendar_Frame").contentWindow;
	cell.oldCssText = cell.style.cssText;
	var str = Calendar.getDateString(cell);
	if(str){win.$o("_Calendar_Tip").innerHTML = str;}
	if(cell.Day){
		cell.style.cssText = "border-top: 1px solid #06c;"
				  +"border-right: 1px solid #06c;"
				  +"border-bottom: 1px solid #06c;"
				  +"border-left: 1px solid #06c;"
				  +"padding: 2px 2px 0px 2px;"
				  +"background: #EDFBD2;";
	}else{
		cell.style.cssText = "background: #9cf;";
	}
	Calendar.isMouseOut = false;
}
	
Calendar.onMouseOut = function(cell){
	cell.style.cssText = cell.oldCssText;
}
	
Calendar.returnDate = function(cell){
	var win = $o("_Calendar_Frame").contentWindow;
	var Control = win.Control;
	$S(Control,Calendar.getDateString(cell));
	var _evt = Control.$A("onchange");
	if(_evt){
		eval(_evt);
	}
	cell.style.cssText = cell.oldCssText;
	Calendar.close();
}
	
Calendar.previousYear = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var date = (--win.$o("_Calendar_Year").Year)+"-"+_LeftPad(++win.$o("_Calendar_Month").Month,"0",2)+"-01";
	Calendar.setDate(date);
	Calendar.adjustSize();
}
	
Calendar.nextYear = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var date = (++win.$o("_Calendar_Year").Year)+"-"+_LeftPad(++win.$o("_Calendar_Month").Month,"0",2)+"-01";
	Calendar.setDate(date);
	Calendar.adjustSize();
}
Calendar.previousMonth = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var month = win.$o("_Calendar_Month").Month,year = win.$o("_Calendar_Year").Year;
	if(month==0){month=11;year--;}else{month--;}
	var date = ""+year+"-"+_LeftPad(month+1,"0",2)+"-01";
	Calendar.setDate(date);
	Calendar.adjustSize();
}
	
Calendar.nextMonth = function(){
	var win = $o("_Calendar_Frame").contentWindow;
	var month = win.$o("_Calendar_Month").Month,year = win.$o("_Calendar_Year").Year;
	if(month==11){month=0;year++;}else{month++;}
	var date = ""+year+"-"+_LeftPad(month+1,"0",2)+"-01";
	Calendar.setDate(date);
	Calendar.adjustSize();
}
	
Calendar.setDate = function(date){//日期算法在这里
	var win = $o("_Calendar_Frame").contentWindow;
	var Control = win.Control;
	var array;
	if(!date){
		var d = new Date();
		array = [isGecko?d.getYear()+1900:d.getYear(),d.getMonth()+1,d.getDate()];
	}else{
		var format = Control.$A("format");
		if(!format){
			format = "yyyy-MM-dd";
		}
		array = new Array(3);
		var yIndex = format.indexOf("yyyy");
		if(yIndex>=0){
			array[0] = date.substr(yIndex,4);
		}else{
			alert("日期格式错误，没有年!");	
		}
		var MIndex = format.indexOf("MM");
		if(MIndex>=0){
			array[1] = date.substr(MIndex,2);
		}else{
			alert("日期格式错误，没有月!");	
		}
		var dIndex = format.indexOf("dd");
		if(dIndex>=0){
			array[2] = date.substr(dIndex,2);
		}else{
			alert("日期格式错误，没有日!");	
		}
	}
	var year = array[0];
	var isRightDate = true;
	if(isNaN(year)){
		year = '2000';
		isRightDate = false;
	}
	win.$o("_Calendar_Year").innerHTML = year;
	win.$o("_Calendar_Year").Year = year;
	var month =	array.length>1?array[1]-1:0;
	if(array.length==1)isRightDate = false;
	if(isNaN(month)||month<0||month>11){month = 0;isRightDate = false;}
	win.$o("_Calendar_Month").innerHTML = Calendar.monthNames[month];
	win.$o("_Calendar_Month").Month = month;
	var day = array.length>2?array[2]:0;
	var d2 = new Date();
	d2.setYear(year);
	d2.setMonth(month);
	d2.setDate(1);
	var week = d2.getDay();
	//计算上个月最后几天
	if(month==0){
		d2.setYear(year-1);
		d2.setMonth(11);
	}else{
		d2.setYear(year);
		d2.setMonth(month-1);
	}
	var days = [],i,j;
	for(i=28;i<33;i++){
		d2.setDate(i);
		if(d2.getMonth()==month){//等于表明是前一月的日期
			for(j=i-week;j<i;j++){
				days.push([0,j]);
			}
			break;
		}
	}
	//计算本月的所有天数
	d2.setYear(year);
	d2.setMonth(month);
	for(i=1;i<32;i++){
		if(i>=28){
			d2.setDate(i);
			if(d2.getMonth()!=month){
				break;//到了该月最后一天了
			}
		}
		if((week+i)%7==0||(week+i)%7==1){
			days.push([1,i]);
		}else{
			days.push([2,i]);
		}
	}
	//计算下月的开头几天
	for(j=0;j<7-((i-1+week)%7==0?7:(i-1+week)%7);j++){
		days.push([3,j+1]);
	}
	var html = [],rows = win.$o("_Calendar_Table").rows;
	for(i=0;i<days.length;i++){
		var flag = days[i][0];
		var cell = rows[Math.floor(2+i/7)].cells[i%7];
		cell.innerHTML = days[i][1];
		if(flag==0){cell.className = "day disabled";cell.Day = -days[i][1];}
		if(flag==3){cell.className = "day disabled";cell.Day = 40+days[i][1];}
		if(flag==1){cell.className = "day weekend";cell.Day = days[i][1];}
		if(flag==2){cell.className = "day";cell.Day = days[i][1];}
	}
	for(j=4;j<6;j++){
		if(j>days.length/7-1){
			win.$o("_Calendar_DayRow"+j).style.display = 'none';
		}else{
			win.$o("_Calendar_DayRow"+j).style.display = '';					
		}
	}
	if(array.length==2)isRightDate = false;
	if(isNaN(day)||day<1||day>31){day = 1;isRightDate = false;}
	win.$o("_Calendar_Day"+(day-1+week)).className += " selected";
	win.$o("_Calendar_Tip").innerHTML = year+"-"+_LeftPad(month+1,"0",2)+"-"+_LeftPad(day,"0",2)
	d2 = new Date();
	if((isGecko?d2.getYear()+1900:d2.getYear())==year&&d2.getMonth()==month){
		win.$o("_Calendar_Day"+(d2.getDate()-1+week)).className += " today";//当前日期样式
	}
	return isRightDate;
}
	
Calendar.show = function(ctrl,date){
	var pw = $E.getTopLevelWindow();
	ctrl = $o(ctrl);	
	try{ctrl.onfocus.apply(ctrl,[]);}catch(ex){}
	date = date?date:$V(ctrl);
	date = date+"";
	if(date.indexOf(" ")>0){
		date = date.substring(0,date.indexOf(" "));//去掉时间
	}
	var ele;
	if(!pw.$o("_Calendar")){
		ele = pw.document.createElement('div');
		ele.id = "_Calendar";
		ele.style.position = "absolute";
		ele.style.zIndex = 999;
		ele.innerHTML = "<iframe id='_Calendar_Frame' frameborder=0 scrolling=no width=194 height=153></iframe>";
		ele.style.width = "194px";
		pw.document.body.appendChild(ele) ; 
		ele.style.display = '';

		var win = pw.$o("_Calendar_Frame").contentWindow;
		var doc = win.document;
		doc.open();
		var arr = [];
		arr.push("<style>");
		arr.push(".nostyle{}");
		arr.push(".calendar {position: absolute; border-top: 1px solid #777; border-right: 1px solid #555; border-bottom: 0px solid #444; border-left: 1px solid #666; font-size: 11px; cursor: default; background: #ddd;}");
		arr.push(".calendar table { font-size: 11px; color: #06c; cursor: default; background: #def; font-family: tahoma,verdana,sans-serif;}");
		arr.push(".daynames{color:555;}");
		arr.push(".calendar .button {text-align: center;padding: 1px;border-top: 1px solid #fff; border-right: 1px solid #999; border-bottom: 1px solid #999; border-left: 1px solid #fff;}");
		arr.push(".calendar .buttontoday {text-align: center; padding: 1px; border-top: 1px solid #999; border-right: 1px solid #999; border-bottom: 1px solid #666; color:#000;}");
		arr.push(".calendar .buttonclose {text-align: center; padding: 1px; border-top: 1px solid #fff; border-right: 0px solid #999; border-bottom: 1px solid #999; border-left: 1px solid #fff;}");
		arr.push(".calendar thead .title {font-weight: bold; border-right: 1px solid #999; border-bottom: 1px solid #999; background: #B3D4FF; color: #258; text-align: center;}");
		arr.push(".calendar thead .name {border-bottom: 1px solid #ccc; padding: 2px; text-align: right; background: #E8EEF4;}");
		arr.push(".calendar .weekend {color: #e70;}");
		arr.push(".calendar tbody .day {width: 2em; text-align: right; padding: 2px 4px 2px 2px; background: #fff;}");
		arr.push(".calendar tbody td.selected {font-weight: bold; border-top: 1px solid #06c; border-right: 1px solid #06c; border-bottom: 1px solid #06c; border-left: 1px solid #06c; padding: 2px 2px 0px 2px; background: #B3D4FF;}");
		arr.push(".calendar tbody td.weekend {color: #e70;}");
		arr.push(".calendar tbody td.today {font-weight: bold;color: #03f;}");
		arr.push(".calendar tbody .disabled { color: #999; }");
		arr.push(".calendar tfoot .tiptoday {padding: 2px; border-top: 1px solid #999; border-right: 0px solid #999; border-bottom: 1px solid #666; border-left: 0px solid #999; background: #fff6cc; font-weight: bold; color: #258; text-align: center;}");
		arr.push("body {margin: 0px; }");
		arr.push("</style>");
		arr.push("<div class='calendar'>");
		arr.push("<TABLE oncontextmenu='return false' onselectstart='return false;' id=_Calendar_Table cellSpacing=0 cellPadding=0 width=190>");
		arr.push("  <THEAD>");
		arr.push("    <TR><TD colSpan=7>");
		arr.push("      <TABLE class=nostyle cellSpacing=0 cellPadding=0 width='100%'>");
		arr.push("        <TBODY>");
		arr.push("          <TR height=20>");
		arr.push("            <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.previousYear(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8249;</TD>");
		arr.push("            <TD class=title><DIV id=_Calendar_Year style='WIDTH: 63px' onclick=TopWindow.Calendar.showYearSelector();>2006</DIV>");
		arr.push("              <SELECT id=_Calendar_YearSelector onBlur='TopWindow.Calendar.hideYearSelector()' style='DISPLAY: none; FONT-SIZE: 11px; WIDTH: 63px' onChange='TopWindow.Calendar.onYearSelectorChange()'>");
		arr.push("              </SELECT></TD>");
		arr.push("            <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.nextYear(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8250;</TD>");
		arr.push("            <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.previousMonth(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8249;</TD>");
		arr.push("            <TD class=title><DIV id=_Calendar_Month style='WIDTH: 63px' onclick=TopWindow.Calendar.showMonthSelector();>12月</DIV>");
		arr.push("              <SELECT id=_Calendar_MonthSelector onblur=TopWindow.Calendar.hideMonthSelector() style='DISPLAY: none; FONT-SIZE: 11px; WIDTH: 63px' onchange=TopWindow.Calendar.onMonthSelectorChange()>");
		arr.push("                <OPTION value=0 selected>1月</OPTION>");
		for(var i=1;i<12;i++){
			arr.push("                <OPTION value="+i+">"+(i+1)+"月</OPTION>");
		}
		arr.push("              </SELECT></TD>");
		arr.push("        <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.nextMonth(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8250;</TD>");
		arr.push("          <TD class=buttonclose onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.close(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=16>×</TD>");
		arr.push("        </TR>");
		arr.push("        </TBODY>");
		arr.push("      </TABLE>");
		arr.push("      </TD>");
		arr.push("    </TR>");
		arr.push("    <TR class=daynames>");
		arr.push("      <TD class='name weekend'>日</TD>");
		arr.push("      <TD class=name>一</TD>");
		arr.push("      <TD class=name>二</TD>");
		arr.push("      <TD class=name>三</TD>");
		arr.push("      <TD class=name>四</TD>");
		arr.push("      <TD class=name>五</TD>");
		arr.push("      <TD class='name weekend'>六</TD>");
		arr.push("    </TR>");
		arr.push("  </THEAD>");
		arr.push("  <TBODY id=_Calendar_Body>");
		for(var i=0;i<6;i++){
			arr.push("    <TR class=daysrow id=_Calendar_DayRow"+i+">");
			for(var j=0;j<7;j++){
				arr.push("      <TD class=day id=_Calendar_Day"+(i*7+j)+" onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.returnDate(this); onmouseout=TopWindow.Calendar.onMouseOut(this);>&nbsp;</TD>");
			}
			arr.push("    </TR>");
		}
		arr.push("  </TBODY>");
		arr.push("  <TFOOT>");
		arr.push("    <TR class=footrow>");
		arr.push("      <TD class=buttontoday id=_Calendar_Today onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.returnDate(this); onmouseout=TopWindow.Calendar.onMouseOut(this); colSpan=2>今日</TD>");
		arr.push("      <TD class=tiptoday id=_Calendar_Tip align=middle colSpan=5>&nbsp;</TD>");
		arr.push("    </TR>");
		arr.push("  </TFOOT>");
		arr.push("</TABLE>");
		arr.push("</div>");
		arr.push("<script>function $o(ele){return document.getElementById(ele);};function setDate(){if(!TopWindow.Calendar.setDate(Control.value)){}TopWindow.Calendar.adjustSize();}</script>");
		doc.write(arr.join("\n"));
		doc.close();
		win.Control = ctrl;
		win.TopWindow = pw;
		win.setDate();
	}else{
		ele = pw.$o("_Calendar");
		ele.show();
		var frame = pw.$o("_Calendar_Frame");
		frame.show();
		frame.contentWindow.Control = ctrl;
		frame.contentWindow.setDate();
	}	

	var pos1 = $E.computePositionEx(ctrl,ele);
	ele.style.top = pos1.y+"px";
	ele.style.left = pos1.x - ctrl.style.width.substring(0,ctrl.style.width.length - 2) - 6 +"px";
	pw.DateTime.showingID = ctrl.id;
	pw.DateTimeWindow = window;
	Misc.lockScroll(window);
}
	
Calendar.close = function(){
	var pw = $E.getTopLevelWindow();
	if(pw.DateTime&&pw.$o("_Calendar")&&pw.$o("_Calendar").visible()){
		var frame = pw.$o("_Calendar_Frame");
		try{frame.contentWindow.Control.onblur.apply(frame.contentWindow.Control,[]);}catch(ex){}
  	$E.hide(pw.$o("_Calendar"));
  	Misc.unlockScroll(pw.DateTimeWindow);  	
		pw.DateTimeWindow = null;
  	pw.DateTime.showingID = false;
  }
}