var dom = (document.getElementsByTagName) ? true : false;
var ie5 = (document.getElementsByTagName && document.all) ? true : false;
var arrowUp, arrowDown;
var tableSortValue = 0;

if (ie5 || dom)
	initSortTable();

function initSortTable() {
	arrowUp = document.createElement("SPAN");
	var tn = document.createTextNode("5");
	arrowUp.appendChild(tn);
	arrowUp.className = "arrow";

	arrowDown = document.createElement("SPAN");
	var tn = document.createTextNode("6");
	arrowDown.appendChild(tn);
	arrowDown.className = "arrow";
}

function sortTable(tableNode, nCol, bDesc, sType) {
	var tBody = tableNode.tBodies[0];
	var trs = tBody.rows;
	var trl = trs.length;
	var a = [];

	for (var i = 0; i < trl; i++) {
		a.push(trs[i]);
	}

	if(tBody.sortIndex && tBody.sortIndex == nCol){
		a.reverse();
	}else{
		shellSort(a,nCol,bDesc,sType);
//		a.sort(compareByColumn(nCol, bDesc, sType));
	}

	for (var i = 0; i < trl; i++) {
		tBody.appendChild(a[i]);
	}
	tBody.sortIndex = nCol;
}

function shellSort(array,col,bDesc,sType){//希尔排序
	var length = array.length;
	var compare = compareByShell(col,bDesc,sType);
    for (var step = length >> 1; step > 0; step >>= 1){
        for (var i = 0; i < step; ++i){
            for (var j = i + step; j < length; j += step){
                var k = j, value = array[j];
                while (k >= step && compare(array[k - step].cells[col].innerText,value.cells[col].innerText)){
                    array[k] = array[k - step];
                    k -= step;
                }
                array[k] = value;
            }
        }
    }
}

function compareByShell(nCol, bDescending, sType) {
	var fTypeCast = String;

	if (sType == "Number"){
		fTypeCast = Number;
	}else if (sType == "Date"){
		fTypeCast = parseDate;
	}else if (sType == "CaseInsensitiveString"){
		fTypeCast = CaseInsensitiveString;
	}else if (sType == "Currency"){
		fTypeCast = parseCurrency;
	}else if (sType == "String") {
		return function(n1,n2) {
			return (bDescending ? n1.localeCompare(n2)
				: -n1.localeCompare(n2)) == 1?true:false;
		}
	}

	return function(n1, n2) {
		if (fTypeCast(n1) < fTypeCast(n2))
			return bDescending ? false : true;
		if (fTypeCast(n1) > fTypeCast(n2))
			return bDescending ? true : false;
		return false;
	};
}

function CaseInsensitiveString(s) {
	return String(s).toUpperCase();
}

function parseDate(s) {
	return Date.parse(s.replace(/\-/g, '/'));
}

function parseCurrency(s) {
	return Number(s.replace(/,/g, ""));
}

/*
 * alternative to number function This one is slower but can handle non
 * numerical characters in the string allow strings like the follow (as well as
 * a lot more) to be used: "1,000,000" "1 000 000" "100cm"
 */

function toNumber(s) {
	s = s.replace(',', "");
	return Number(s.replace(/[^0-9\.]/g, ""));
}

function compareByColumn(nCol, bDescending, sType) {
	var c = nCol;
	var d = bDescending;

	var fTypeCast = String;

	if (sType == "Number")
		fTypeCast = Number;
	else if (sType == "Date")
		fTypeCast = parseDate;
	else if (sType == "CaseInsensitiveString")
		fTypeCast = CaseInsensitiveString;
	else if (sType == "Currency")
		fTypeCast = parseCurrency;
	else if (sType == "String") {
		return function(n1, n2) {
			return d
					? getInnerText(n1.cells[c])
							.localeCompare(getInnerText(n2.cells[c]))
					: -getInnerText(n1.cells[c])
							.localeCompare(getInnerText(n2.cells[c]));
		}
	}

	return function(n1, n2) {
		if (fTypeCast(getInnerText(n1.cells[c])) < fTypeCast(getInnerText(n2.cells[c])))
			return d ? -1 : +1;
		if (fTypeCast(getInnerText(n1.cells[c])) > fTypeCast(getInnerText(n2.cells[c])))
			return d ? +1 : -1;
		return 0;
	};
}

function sortColumnWithHold(e) {
	// find table element
	var el = ie5 ? e.srcElement : e.target;
	var table = getParent(el, "TABLE");

	// backup old cursor and onclick
	var oldCursor = table.style.cursor;
	var oldClick = table.onclick;

	// change cursor and onclick
	table.style.cursor = "wait";
	table.onclick = null;

	// the event object is destroyed after this thread but we only need
	// the srcElement and/or the target
	var fakeEvent = {
		srcElement : e.srcElement,
		target : e.target
	};

	// call sortColumn in a new thread to allow the ui thread to be updated
	// with the cursor/onclick
	window.setTimeout(function() {
				sortColumn(fakeEvent);
				// once done resore cursor and onclick
				table.style.cursor = oldCursor;
				table.onclick = oldClick;
			}, 100);
}
/* noSortColsSN:Don't need sort columns number(0,1,2,3,4) */
/* opeType:operate type 0-sort 1-unite cols 2-sort and unite cols */
var preTDElement;
function sortColumn(e, noSortColsSN, opeType) {
	var tmp = document.activeElement?document.activeElement:e.target;
	if(tmp.nodeName == 'TD' && tmp.parentNode.parentNode.nodeName == 'TBODY'){
		if(preTDElement){
			preTDElement.style.backgroundColor = currTDColor;
		}
		currTDColor = tmp.parentNode.style.backgroundColor;
		preTDElement = tmp.parentNode;
		tmp.parentNode.style.backgroundColor= '#87CEFA';
	}
	var tHeadParent = getParent(tmp, "THEAD");
	var el = getParent(tmp, "TD");
	if (el && tHeadParent) {
		var p = el.parentNode;
		var i;
		// typecast to Boolean
		el._descending = !Boolean(el._descending);
		if (tHeadParent.arrow) {
			if (tHeadParent.arrow.parentNode != el) {
				tHeadParent.arrow.parentNode._descending = null;
			}
			tHeadParent.arrow.parentNode.removeChild(tHeadParent.arrow);
		}

		if (el._descending){
			tHeadParent.arrow = arrowUp.cloneNode(true);
		}else{
			tHeadParent.arrow = arrowDown.cloneNode(true);
		}

		/* jn edit start */
		var noSortCols = [];
		if (noSortColsSN) {
			noSortCols = noSortColsSN.split(",");
		}
		if(el.getAttribute("nosort") == 'true') {
			return;
		}
		// get the index of the td
		var RowLine = null;
		if(tHeadParent.rows[tHeadParent.rows.length-1].cells.length == 1) {
				RowLine = tHeadParent.rows[tHeadParent.rows.length-1].cloneNode(true);
				tHeadParent.deleteRow(tHeadParent.rows.length-1);//去掉thead中的黑线
			}
		if (tHeadParent.rows.length > 1) {//多表头
			if (tableSortValue == 0) {
				var children = allChild(first(tHeadParent.parentNode));
				for (var i = 0; i < children.length; i++) {
					children[i].setAttribute("current", 0);
				}
				var temp = allChild(children[0]);
				for (var j = 0; j < temp.length; j++) {
					var rowspan = getAttr(temp[j], 'rowspan');
					var colspan = getAttr(temp[j], 'colspan');
					findLast(temp[j], colspan, rowspan, children.length);
					increaseDom(temp[j]);
				}
				i = Number(el.getAttribute("indexValue")) - 1;
			} else {
				i = Number(el.getAttribute("indexValue")) - 1;
			}
			if(i == -1) {
				return;
			}
		} else {//单表头
			var cells = p.cells;
			var l = cells.length;
			for (i = 0; i < l; i++) {
				if (cells[i] == el) {
					break;
				}
			}
		}
		if(RowLine) {
			tHeadParent.appendChild(RowLine);
		}
		//不排序的列
		for (j = 0; j < noSortCols.length; j++) {
			if (i == noSortCols[j]) {
				return;
			}
		}

		/* jn edit end */
		/* set column arrow picture */
		if (opeType == '0' || opeType == '2') {
			el.appendChild(tHeadParent.arrow);
		}

		var table = getParent(el, "TABLE");
		// can't fail
		if (opeType == '0' || opeType == '2') {
			sortTable(table, i, el._descending, el.getAttribute("type"));
		}
		if (opeType == '1' || opeType == '2') {
			var groupID = el.getAttribute("groupID");
			if (groupID == null) {
			} else {
				if (table.tBodies[0].rows.length > 0) {
					groupColHideShow(table, i, el.getAttribute("groupID"));
				}
			}
		}
	}
}
function findLast(dom, end, current, depth) {
	if (current == depth) {
		tableSortValue++;
		dom.setAttribute('indexValue', tableSortValue);
		return;
	} else {
		var len = getAttr(dom, 'rowspan');
		var myNext = dom.parentNode;
		for (var i = 0; i < len; i++) {
			myNext = next(myNext)
		}
		var children = allChild(myNext);
		var max = parseInt(getAttr(myNext, 'current'));
		if (window.ActiveXObject) {
			max = max - 1;
		}
		for (var i = max; i < max + end; i++) {
			if (children[i]) {
				var rowspan = getAttr(children[i], 'rowspan');
				var colspan = getAttr(children[i], 'colspan');
				var mm = current + rowspan;
				findLast(children[i], colspan, mm, depth);
				increaseDom(children[i]);
			}
		}
	}
}

function increaseDom(dom) {
	var len = getAttr(dom.parentNode, 'current');
	dom.parentNode.setAttribute('current', ++len);
}

function getAttr(dom, attr) {
	if (dom.getAttribute(attr)) {
		return parseInt(dom.getAttribute(attr));
	} else {
		return 1;
	}
}

function onTestClick(e) {
	e = e ? e : window.event;
	var target = e.target ? e.target : e.srcElement;
	if (target.getAttribute("indexValue")) {
		alert("当前" + target.getAttribute("indexValue") + "个" + value);
	}
}

function allChild(elem) {
	var children = elem.childNodes;
	var arr = [];
	for (var i = 0; i < children.length; i++) {
		if (children[i] && children[i].nodeType == 1) {
			arr.push(children[i]);
		}
	}
	return arr;
}

function next(elem) {
	do {
		elem = elem.nextSibling;
	} while (elem && elem.nodeType != 1);
	return elem;
}

function first(elem) {
	elem = elem.firstChild;
	return elem && elem.nodeType != 1 ? next(elem) : elem;
}
function groupColHideShow(tableNode, nCol, groupID) {
	var tBody = tableNode.tBodies[0];
	/* rows obj */
	var trs = tBody.rows;
	/* row number */
	var trl = trs.length;
	/* column number */
	var tdl = tBody.rows.item(0).cells.length;
	for (i = 0; i < trl; i++) {
		var td = tBody.rows[i].cells[nCol];
		for (j = nCol + 1; j < tdl; j++) {
			with (tBody.rows[i].cells[j]) {
				tdTmp = tableNode.rows[0].cells[j];
				if (typeof(tdTmp.groupID) == "string") {
					if (tdTmp.groupID == groupID) {
						if (style.display == '') {
							tdTmp.style.display = 'none';
							style.display = 'none';
						} else {
							tdTmp.style.display = '';
							style.display = '';
						}
					}
				}
			}
		}
	}
}

function getInnerText(el) {
	return el.innerText; // Not needed but it is faster

//	var str = "";
//
//	var cs = el.childNodes;
//	var l = cs.length;
//	for (var i = 0; i < l; i++) {
//		switch (cs[i].nodeType) {
//			case 1 : // ELEMENT_NODE
//				str += getInnerText(cs[i]);
//				break;
//			case 3 : // TEXT_NODE
//				str += cs[i].nodeValue;
//				break;
//		}
//
//	}
//
//	return str;
}

function getParent(el, pTagName) {
	if (el){
		if(el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase()){
			return el;
		}else{
			return getParent(el.parentNode, pTagName);
		}
	}
}