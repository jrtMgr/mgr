<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:directive.page import="org.springframework.web.context.WebApplicationContext"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<%@ include file="/ext-base.jsp"%>
</head>
<script type="text/javascript">
<% 
String errormsg = (String)request.getAttribute("errormsg");
String result = (String)request.getAttribute("result");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%
}
%>
Ext.onReady(function() {
	var root = new Ext.tree.TreeNode( {
		text : "根节点",
		expanded : true,
		id : "0",
		allowDrop : false
	});
	<%
		Map<String,String> lotTypes = (Map)request.getAttribute("lotTypes");
		for(Entry lotType :lotTypes.entrySet()){
			String lotno = String.valueOf(lotType.getKey());
			String lotname = String.valueOf(lotType.getValue());
	%>
		var node_<%=lotno %> = new Ext.tree.AsyncTreeNode({
			text : '<%=lotname %>',
			id : '<%=lotno %>',
			draggable : false,
			loader : new Ext.tree.TreeLoader({
				baseAttrs : {leaf:true,iconCls:'userIcon'},
				dataUrl : '../caselot/selectCelebrity',
				requestMethod : 'GET'
			})
		});
		root.appendChild(node_<%=lotno %>);
	<%
		}
	%>
	
	var contextMenu = new Ext.menu.Menu({
		id : 'celebrityTreeContextMenu',
		items : [{
			id : 'removeCelebrity',
			text : '删除合买名人',
			iconCls : 'page_delIcon',
			handler : function() {
				delCelebrity();
			}
		}]
	});
	
	/* 删除合买名人 */
	function delCelebrity() {
		var selectModel = celebrityTree.getSelectionModel();
		var selectNode = selectModel.getSelectedNode();
		if (!selectNode) {
			Ext.Msg.alert('提示', "请选择一个合买名人");
			return false;
		}
		if (selectNode.attributes.leaf) {
			Ext.Msg.confirm('请确认', '确认删除吗？', function(btn, text) {
				if (btn == 'yes') {
					celebrityTree.getSelectionModel().selectPrevious();
					var parent = selectNode.parentNode;
					showWaitMsg("正在删除[" + selectNode.attributes.text + "]名人");
					Ext.Ajax.request({
			    		url :'../caselot/removeCelebrity',
			    		method :'POST',
			    		params : {
			    			userno : selectNode.attributes.userno,
			    			lotno  : parent.attributes.id
			    		},
			    		success : function(response) {
			    			hideWaitMsg();
			    			parent.removeChild(selectNode);
			    		},
			    		failure : function(response) {
			    			hideWaitMsg();
							Ext.Msg.alert('错误', "删除失败");
			    		}
			    	});
				}
			});
		} else {
			Ext.Msg.alert("提示", "不能删除");
		}
	}
	
	var celebrityTree = new Ext.tree.TreePanel({
		title : '',
		useArrows: true,
		autoHeight : true,
		autoWidth : true,
		autoScroll : false,
		animate : true,
		rootVisible : false,
		border : false,
		containerScroll : true,
		applyTo : 'treeDiv',
		root : root,
		ddGroup: "tgDD",
		enableDD : true,
		listeners: {  
	        // 监听beforenodedrop事件，主要就是在这里工作
	        beforenodedrop: function(dropEvent) {
	            var node = dropEvent.target;    // 目标结点
	            var data = dropEvent.data;      // 拖拽的数据
	            var point = dropEvent.point;    // 拖拽到目标结点的位置
	            // 禁止在叶子节点上append新节点
	            if(point == 'append'){
	            	if(node.attributes.leaf==true){
                		return false;
                	}
	            }
	            // 如果data.node为空，则不是tree本身的拖拽，而是从grid到tree的拖拽，需要处理
	            if(!data.node) {
	                switch(point) {
	                    case 'append':
	                        // 添加时，目标结点为node，子结点设为空
	                        Ext.Msg.confirm('请确认', '确认添加到'+node.text+'彩种吗？', function(btn, text) {
								if (btn == 'yes') {
			                        ddGrid2Tree(node, null, data.selections);
								}
	                        });
	                        break;
	                    case 'above':
	                        // 插入到node之上，目标结点为node的parentNode，子结点为node
	                        Ext.Msg.confirm('请确认', '确认添加到'+node.parentNode.text+'彩种吗？', function(btn, text) {
								if (btn == 'yes') {
	                        		ddGrid2Tree(node.parentNode, node, data.selections);
								}
	                        });
	                        break;
	                    case 'below':
	                        // 插入到node之下，目标结点为node的parentNode，子结点为node的nextSibling
	                        Ext.Msg.confirm('请确认', '确认添加到'+node.parentNode.text+'彩种吗？', function(btn, text) {
								if (btn == 'yes') {
	                        		ddGrid2Tree(node.parentNode, node.nextSibling, data.selections);
								}
	                        });
	                        break;
	                }
	            }else{
	            	var _userno = data.node.attributes.userno;
	            	var _lotno = data.node.parentNode.attributes.id;
	            	switch(point) {
	                    case 'append':
	                        // 添加时，目标结点为node，子结点设为空
	                        return ddTreeNode(node,_userno,_lotno);
	                        break;
	                    case 'above':
	                        // 插入到node之上，目标结点为node的parentNode，子结点为node
	                        return ddTreeNode(node.parentNode,_userno,_lotno);
	                        break;
	                    case 'below':
	                        // 插入到node之下，目标结点为node的parentNode，子结点为node的nextSibling
	                        return ddTreeNode(node.parentNode,_userno,_lotno);
	                        break;
	                }
	            }
	        }
	    }
	});
	celebrityTree.expandAll();
	
	/* 右击事件 */
	celebrityTree.on('contextmenu', function(node, e) {
		e.preventDefault();
		if(!node.attributes.leaf){
			contextMenu.items.get('removeCelebrity')['disable']();
		}else{
			contextMenu.items.get('removeCelebrity')['enable']();
		}
		node.select();
		contextMenu.showAt(e.getXY());
	});
	
	/* 刷新指定节点 */
	function refreshNode(nodeid) {
		var node = celebrityTree.getNodeById(nodeid);
		/* 异步加载树在没有展开节点之前是获取不到对应节点对象的 */
		if (Ext.isEmpty(node)) {
			celebrityTree.root.reload();
			return;
		}
		if (node.attributes.leaf) {
			node.parentNode.reload();
		} else {
			node.reload();
		}
	}
	
	//==================grid start============================//
	
	// 定义列模型
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
		header : '用户编号',
		dataIndex : 'userno'
	}, {
		header : '用户名',
		dataIndex : 'userName',
		sortable : true
	}, {
		header : '昵称',
		dataIndex : 'nickname',
		sortable : true
	}, {
		header : '电话',
		dataIndex : 'mobileid',
		sortable : true
	}, {
		header : '邮箱',
		dataIndex : 'email',
		sortable : true
	}]);

	/**
	 * 数据存储
	 */
	var store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			method: 'GET',
			url : '../tuserinfoes/page'
		}),
		remoteSort : true,
		reader : new Ext.data.JsonReader({
			root: 'list',
			totalProperty : 'totalResult',
			root : 'list',
			id : 'userno'
		}, [{
			name : 'userno'
		}, {
			name : 'nickname'
		}, {
			name : 'mobileid'
		}, {
			name : 'email'
		}, {
			name : 'userName'
		}])
	});
	
	// 翻页排序时带上查询条件
	store.on('beforeload', function() {
		this.baseParams = {
			queryParam : Ext.getCmp('queryParam').getValue()?escape(Ext.getCmp('queryParam').getValue()):Ext.getCmp('queryParam').getValue()
		};
	});
	
	// 分页工具栏
	var bbar = new Ext.PagingToolbar({
		pageSize : 20,
		store : store,
		displayInfo : true,
		displayMsg : '显示{0}条到{1}条,共{2}条',
		plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
		emptyMsg : "没有符合条件的记录"
	});
	
	/**
	 * 重置查询框
	 */
	function reset() {
		Ext.getCmp("queryParam").reset();
		queryTuserinfo();
	}
	
	// 表格实例
	var grid = new Ext.grid.GridPanel({
		// 表格面板标题,默认为粗体，我不喜欢粗体，这里设置样式将其格式为正常字体
		title : '<img src="../images/ext/group.png" align="top" class="IEPNG">用户列表',
		height : 500,
		autoScroll : true,
		frame : true,
		region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
		store : store, // 数据存储
		stripeRows : true, // 斑马线
		enableDragDrop: true,
	    ddGroup: "tgDD",
		cm : cm, // 列模型
		sm : new Ext.grid.RowSelectionModel({singleSelect:true}),
		bbar : bbar,// 分页工具栏
		tbar : [new Ext.form.TextField({
					id : 'queryParam',
					name : 'queryParam',
					emptyText : '用户编号或用户名',
					enableKeyEvents : true,
					listeners : {
						specialkey : function(field, e) {
							if (e.getKey() == Ext.EventObject.ENTER) {
								queryTuserinfo();
							}
						}
					},
					width : 130
				}), {
					text : '查询',
					iconCls : 'page_findIcon',
					handler : function() {
						queryTuserinfo();
					}
				}, {
					text : "重置",
					iconCls : 'arrow_refreshIcon',
					handler : function() {
						reset();
					}
				}],
		viewConfig : {
			forceFit : true
		},
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		}
	});
	
	/**
	 * 根据条件查询
	 */
	function queryTuserinfo() {
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				queryParam : Ext.getCmp('queryParam').getValue()
			}
		});
	}
	//初始化时查询
	queryTuserinfo();
	
	// 拖拽时需要用到的函数，用来处理添加树结点  
	/* 
	 * TreeNode     node        :   tree上的目标结点 
	 * TreeNode     refNode     :   目标结点的子结点，用于定位新添加的结点 
	 * Record Array selections  :   从grid中选取的记录数组    
	 */  
	var ddGrid2Tree = function(node, refNode, selections) {
	    for(var i = 0; i < selections.length; i ++) {
	    	var record = selections[i];
	    	var recordUserno = record.get('userno');
	    	if (node && node.childNodes.length > 0) {
				for (var i = 0; i < node.childNodes.length; i++) {
					var child = node.childNodes[i];
					if(child.attributes.userno == recordUserno){
						Ext.Msg.alert('错误', "该名人已存在");
						return false;
					}
				}
			}
	    	Ext.Ajax.request({
	    		url :'../caselot/createCaseLotCelebrity',
	    		method :'POST',
	    		params : {
	    			lotno : node.attributes.id,
	    			userno: recordUserno
	    		},
	    		success : function(response) {
	    			hideWaitMsg();
					node.insertBefore(new Ext.tree.TreeNode({
			            text: record.get('nickname'),
			            id: record.get('userno'),
			            userno : record.get('userno'),
			            iconCls:'userIcon',
			            leaf: true
			        }), refNode);
					saveSort(node);
	    		},
	    		failure : function(response) {
	    			hideWaitMsg();
					Ext.Msg.alert('错误', "拖拽用户操作失败");
	    		}
	    	});
	    }
	}
	
	var ddTreeNode = function(newNode, userno, oldLotno){
		if(newNode.attributes.id != oldLotno){
			if (newNode && newNode.childNodes.length > 0) {
				for (var i = 0; i < newNode.childNodes.length; i++) {
					var child = newNode.childNodes[i];
					if(child.attributes.userno == userno){
						Ext.Msg.alert('错误', "该名人已存在");
						return false;
					}
				}
			}
		}
		Ext.Ajax.request({
	   		url :'../caselot/updateCaseLotCelebrity',
	   		method :'POST',
	   		params : {
	   			newlotno : newNode.attributes.id,
	   			userno: userno,
	   			oldlotno:oldLotno
	   		},
	   		success : function(response) {
				saveSort(newNode);
	   		},
	   		failure : function(response) {
				Ext.Msg.alert('错误', "拖拽节点操作失败");
	   		}
	   	});
	}
	
	/* 保存顺序 */
	function saveSort(node) {
		var ch = [];
		appendNode(node, ch);
		Ext.Ajax.request({
    		url :'../caselot/sortCelebrity',
    		method :'POST',
    		params : {
    			data : Ext.encode(ch)
    		},
    		success : function(response) {
    		},
    		failure : function(response) {
				Ext.Msg.alert('错误', "排序失败");
    		}
    	});
	}
	// 递归拼装需要排序的节点。本次实际只有一层数据，不需要递归。
	function appendNode(node, array) {
		if (!node || node.childNodes.length < 1) {
			return;
		}
		for (var i = 0; i < node.childNodes.length; i++) {
			var child = node.childNodes[i];
			array.push({
				userno	:	child.attributes.userno,
				lotno	:	node.attributes.id
			});
			appendNode(child, array);
		}
	}
	
	//==================grid end============================//
	
	/**
	 * 布局
	 */
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [{
			title : '<span>合买名人</span>',
			iconCls : 'layout_contentIcon',
			tools : [{
				id : 'refresh',
				qtip : '刷新节点',
				handler : function() {
					var selectModel = celebrityTree.getSelectionModel();
					var selectNode = selectModel.getSelectedNode();
					if (selectNode) {
						refreshNode(selectNode.attributes.id);
					}
				}
			}],
			collapsible : false,
			width : 300,
			minSize : 200,
			maxSize : 400,
			split : true,
			region : 'west',
			autoScroll : true,
			border : false,
			items : [celebrityTree]
		}, {
			region : 'center',
			layout : 'fit',
			border : false,
			items : [grid]
		}]
	});
	
});
</script>
<body>
	<div id="treeDiv" style="width: 100%; height: 100%;"></div>
</body>
</html>