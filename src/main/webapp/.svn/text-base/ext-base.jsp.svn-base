<%@ page language="java" pageEncoding="utf-8"%>
<!-- EXTJS CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/ext_icon.css" />
<!-- 默认皮肤 -->
<!-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/extjs/resources/css/ext-all.css" /> -->
<!-- 更换EXTJS皮肤 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/extjs/theme/gray/resources/css/ext-all.css" />
<!-- EXT UX CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/extjs/ux/css/ux-all.css"/>

<!-- EXTJS JS -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/extjs/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/extjs/ux/ux-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/extjs/ext-basex.js"></script>
<!-- EXTJS CONFIG -->
<script type="text/javascript">
	var _ctx = '<%=request.getContextPath()%>';
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';
	Ext.BLANK_IMAGE_URL = '<%=request.getContextPath()%>/js/extjs/theme/gray/resources/images/default/s.gif';
	// 修复ExtJS3.3中自动关闭下来树的Bug
	Ext.override(Ext.form.ComboBox, {
		onViewClick : function(doFocus) {
			var index = this.view.getSelectedIndexes()[0], s = this.store, r = s
					.getAt(index);
			if (r) {
				this.onSelect(r, index);
			} else if (s.getCount() === 0) {
				this.collapse();
			}
			if (doFocus !== false) {
				this.el.focus();
			}
		}
	});
	// 重新RadioGroup的getValue和setValue
	Ext.override(Ext.form.RadioGroup, {
	    getValue : function() {
	        var v;
	        this.items.each(function(item) {
	            if (item.getValue()) {
	                v = item.getRawValue();
	                return false;
				}
			});
			return v;
		},
		setValue : function(v) {
	        if (this.rendered) {
	            this.items.each(function(item) {
	                item.setValue(item.getRawValue() == v);
				});
	        } else {
	            for (k in this.items) {
	                this.items[k].checked = this.items[k].inputValue == v;
	            }
	        }
		}
	});
	// 重新CheckboxGroup的getValue和setValue
	Ext.override(Ext.form.CheckboxGroup, {
		getValue : function() {
			var v = [];
			this.items.each(function(item) {
				if (item.getValue()) {
					v.push(item.getRawValue());
				} else {
					v.push('');
				}
			});
			return v;
		},
		setValue : function(vals) {
			var a = [];
			if (Ext.isArray(vals)) {
				a = vals;
			} else {
				a = [vals];
			}
			this.items.each(function(item) {
				item.setValue(false); // reset value
				for (var i = 0; i < a.length; i++) {
					var val = a[i];
					if (val == item.getRawValue()) {
						item.setValue(true);
					}
				};
			});
		}
	});
	
	/**
	 * 显示提示框
	 * 
	 * @param {}
	 *            title
	 * @param {}
	 *            msg
	 */
	function showOkTipMsg(title, msg) {
		Ext.MessageBox.alert(title, msg);
	}
	/**
	 * 显示请求等待进度条窗口
	 * 
	 * @param {}
	 *            msg
	 */
	function showWaitMsg(msg) {
		Ext.MessageBox.show({
			title : '系统提示',
			msg : msg == null ? '正在处理数据,请稍候...' : msg,
			progressText : 'processing now,please wait...',
			width : 300,
			wait : true,
			waitConfig : {
				interval : 150
			}
		});
	}

	/**
	 * 隐藏请求等待进度条窗口
	 */
	function hideWaitMsg() {
		Ext.MessageBox.hide();
	}
</script>