if (!Ext.grid.GridView.prototype.templates) {
	Ext.grid.GridView.prototype.templates = {};
}
Ext.grid.GridView.prototype.templates.cell = new Ext.Template(
		'<td class="x-grid3-col x-grid3-cell x-grid3-td-{id} x-selectable {css}" style="{style}" tabIndex="0" {cellAttr}>',
		'<div class="x-grid3-cell-inner x-grid3-col-{id}" {attr}>{value}</div>',
		'</td>');
Ext.onReady(function() {

	var lotnoComboBox = new Ext.form.ComboBox({
		hiddenId : 'EQG_sendState',
		hiddenName : 'EQG_sendState',
		fieldLabel : '发送状态',
		triggerAction : 'all',
		emptyText : '请选择',
		store : new Ext.data.SimpleStore({
			fields : [ 'value', 'name' ],
			data : [ [ '0', '待发送' ], [ '1', '发送成功' ], [ '2', '发送失败' ],
					[ '3', '正在发送' ] ]
		}),
		displayField : 'name',
		valueField : 'value',
		mode : 'local',
		forceSelection : false, // 选中内容必须为下拉列表的子项
		editable : false,
		typeAhead : true,
		anchor : '100%'
	});

	var qForm = new Ext.form.FormPanel({
		id : 'qForm',
		region : 'north',
		title : '<span style="font-weight:normal">查询条件<span>',
		collapsible : true,
		border : true,
		labelWidth : 50, // 标签宽度
		frame : true, // 是否渲染表单面板背景色
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
		buttonAlign : 'center',
		height : 130,
		items : [ {
			layout : 'column',
			border : false,
			items : [ {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ lotnoComboBox ]
			}, {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ {
					fieldLabel : '手机号', // 标签
					id : 'EQS_mobileid',
					name : 'EQS_mobileid', // name:后台根据此name属性取值
					allowBlank : true, // 是否允许为空
					maxLength : 50, // 可输入的最大文本长度,不区分中英文字符
					anchor : '100%'
				} ]
			}, {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ {
					fieldLabel : '短信内容', // 标签
					id : 'LIKES_content',
					name : 'LIKES_content', // name:后台根据此name属性取值
					allowBlank : true, // 是否允许为空
					maxLength : 50, // 可输入的最大文本长度,不区分中英文字符
					anchor : '100%'
				} ]
			} ]
		}, {
			layout : 'column',
			border : false,
			items : [ {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ {
					xtype : 'datefield',
					format : 'Y-m-d',
					editable : false,
					fieldLabel : '创建时间从', // 标签
					id : 'GED_createTime',
					name : 'GED_createTime', // name:后台根据此name属性取值
					anchor : '100%'
				} ]
			}, {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ {
					xtype : 'datefield',
					format : 'Y-m-d',
					editable : false,
					fieldLabel : '到', // 标签
					id : 'LED_createTime',
					name : 'LED_createTime', // name:后台根据此name属性取值
					anchor : '100%'
				} ]
			}, {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ {
					xtype : 'datefield',
					format : 'Y-m-d',
					editable : false,
					fieldLabel : '发送时间从', // 标签
					id : 'GED_sendTime',
					name : 'GED_sendTime', // name:后台根据此name属性取值
					anchor : '100%'
				} ]
			}, {
				columnWidth : .25,
				layout : 'form',
				labelWidth : 80, // 标签宽度
				defaultType : 'textfield',
				border : false,
				items : [ {
					xtype : 'datefield',
					format : 'Y-m-d',
					editable : false,
					fieldLabel : '到', // 标签
					id : 'LED_sendTime',
					name : 'LED_sendTime', // name:后台根据此name属性取值
					anchor : '100%'
				} ]
			} ]
		} ],
		buttons : [ {
			text : '查询',
			iconCls : 'previewIcon',
			handler : function() {
				queryDatas();
			}
		}, {
			text : '重置',
			iconCls : 'tbar_synchronizeIcon',
			handler : function() {
				qForm.getForm().reset();
			}
		} ]
	});

	// 定义列模型
	var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
			{
				header : '手机号',
				dataIndex : 'mobileid',
				width : 80,
				sortable : true
			},
			{
				header : '短信内容',
				dataIndex : 'content',
				width : 160,
				sortable : true
			},
			{
				header : '短信渠道',
				dataIndex : 'channelName',
				width : 80,
				sortable : true
			},
			{
				header : '发送状态',
				dataIndex : 'sendState',
				width : 80,
				sortable : true,
				renderer : function(value) {
					if (value == '0') {
						return '待发送';
					} else if (value == '1') {
						return '发送成功';
					} else if (value == '2') {
						return '发送失败';
					} else if (value == '3') {
						return value;
					} else {
						return value;
					}
				}
			},
			{
				header : '创建时间',
				dataIndex : 'createTime',
				width : 120,
				sortable : true,
				renderer : function(value) {
					if (value == null || value == 0) {
						return '';
					} else {
						return Ext.util.Format.date(new Date(parseInt(value)),
								'Y-m-d H:i:s');
					}
				}
			},
			{
				header : '发送时间',
				dataIndex : 'sendTime',
				width : 120,
				sortable : true,
				renderer : function(value) {
					if (value == null || value == 0) {
						return '';
					} else {
						return Ext.util.Format.date(new Date(parseInt(value)),
								'Y-m-d H:i:s');
					}
				}
			} ]);

	/**
	 * 数据存储
	 */
	var store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : _ctx + '/msgcenter/listWaitforSendSMS'
		}),
		remoteSort : true,
		reader : new Ext.data.JsonReader({
			root : 'list',
			totalProperty : 'totalResult'
		}, [ {
			name : 'id',
			mapping : 'id'
		}, {
			name : 'mobileid',
			mapping : 'mobileid'
		}, {
			name : 'content',
			mapping : 'content'
		}, {
			name : 'channelName',
			mapping : 'channelName'
		}, {
			name : 'sendState',
			mapping : 'sendState'
		}, {
			name : 'createTime',
			mapping : 'createTime'
		}, {
			name : 'sendTime',
			mapping : 'sendTime'
		}, {
			name : 'sendSuccTime',
			mapping : 'sendSuccTime'
		}, {
			name : 'hashCode',
			mapping : 'hashCode'
		} ])
	});

	// 翻页排序时带上查询条件
	store.on('beforeload', function() {
		var _params = qForm.getForm().getValues(true);
		this.baseParams = {
			condition : _params
		};
	});
	// 每页显示条数下拉选择框
	var pagesize_combo = new Ext.form.ComboBox({
		name : 'pagesize',
		triggerAction : 'all',
		mode : 'local',
		store : new Ext.data.ArrayStore({
			fields : [ 'value', 'text' ],
			data : [ [ 10, '10条/页' ], [ 20, '20条/页' ], [ 50, '50条/页' ],
					[ 100, '100条/页' ] ]
		}),
		valueField : 'value',
		displayField : 'text',
		value : '20',
		editable : false,
		width : 85
	});

	var number = parseInt(pagesize_combo.getValue());
	// 改变每页显示条数reload数据
	pagesize_combo.on("select", function(comboBox) {
		bbar.pageSize = parseInt(comboBox.getValue());
		number = parseInt(comboBox.getValue());
		store.reload({
			params : {
				start : 0,
				limit : bbar.pageSize
			}
		});
	});

	// 分页工具栏
	var bbar = new Ext.PagingToolbar({
		pageSize : number,
		store : store,
		displayInfo : true,
		displayMsg : '显示{0}条到{1}条,共{2}条',
		plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
		emptyMsg : "没有符合条件的记录",
		items : [ '-', '&nbsp;&nbsp;', pagesize_combo ]
	});

	// 表格实例
	var grid = new Ext.grid.GridPanel({
		// 表格面板标题,默认为粗体，我不喜欢粗体，这里设置样式将其格式为正常字体
		title : '<span style="font-weight:normal">待发送短信列表</span>',
		iconCls : 'folder_tableIcon',
		autoScroll : true,
		frame : true,
		region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
		store : store, // 数据存储
		stripeRows : true, // 斑马线
		cm : cm, // 列模型
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),
		bbar : bbar,// 分页工具栏
		viewConfig : {
			forceFit : true
		},
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		}
	});

	// 布局
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [ qForm, grid ]
	});

	/**
	 * 根据条件查询
	 */
	function queryDatas() {
		var condition = qForm.getForm().getValues(true);
		var params = {
			condition : condition,
			start : 0,
			limit : bbar.pageSize
		};
		store.load({
			params : params
		});
	}
	// 初始化时查询
	// queryDatas();
});