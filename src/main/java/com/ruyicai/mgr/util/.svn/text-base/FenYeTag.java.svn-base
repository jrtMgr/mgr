package com.ruyicai.mgr.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *  <my:pager pageSize="10" pageNo="1" recordCount="100"  url="index.jsp" />
 */
public class FenYeTag extends TagSupport {
	private int pageSize = 15; // 每页要显示的记录数 1.
	private int pageNo = 1; // 页号 1.
	private int recordCount; // 总记录数 1.
	private String url; // 目的地URL 1.

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int doStartTag() throws JspException {
		if (recordCount == 0) {
			return super.doStartTag();
		}

		// 总页数 1.
		int pageCount = recordCount % pageSize == 0 ? recordCount / pageSize : recordCount / pageSize + 1;
		// 页号越界处理 1.
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		StringBuilder sb = new StringBuilder();
		String actionurl = request.getContextPath()+"/"+url;
		sb.append(
				"<form name='pageController' id='pageController' action='"+actionurl+"' method='post'>\r\n")
				.append("<input type='hidden' id='pageIndex' name='pageIndex' value='' />\r\n")
				.append("<input type='hidden' id='maxResult' name='maxResult' value='" + pageSize + "' />\r\n");

		// ------------------------------------ 获取所有請求中的参数 1.
		
		Enumeration<String> enumeration = request.getParameterNames();
		String name = null;
		String value = null;
		// 把请求中的所有参数当作隐藏表单域在页面中写出) 1.
		while (enumeration.hasMoreElements()) {
			name = enumeration.nextElement();
			value = request.getParameter(name);
			if (name.equals("pageNo")) {
				if (null != value && !"".equals(value)) {
					pageNo = Integer.parseInt(value);
				}
				continue;
			}
			sb.append("<input type='hidden' name='").append(name)
					.append("' value='").append(value).append("'/>\r\n");
		}
		// ---------------------------------------------------- 1.

		sb.append(" 总共有" + pageCount + "页,").append("当前是第" + pageNo + "页 \r\n");

		if (pageNo == 1) {
			sb.append("首页");
			sb.append(" ");
			sb.append("上一页\r\n");
		} else {
			sb.append("<a href='#' onclick='turnOverPage(1)'>首页</a>\r\n");
			sb.append(" ");
			sb.append("<a href='#' onclick='turnOverPage(")
					.append((pageNo - 1)).append(")'>上一页</a>\r\n");
		}
		sb.append(" ");
		if (pageNo == pageCount) {
			sb.append("下一页");
			sb.append(" ");
			sb.append("尾页\r\n");
		} else {
			sb.append("<a href='#' onclick='turnOverPage(")
					.append((pageNo + 1)).append(")'>下一页</a>\r\n");
			sb.append(" ");
			sb.append("<a href='#' onclick='turnOverPage(").append(pageCount)
					.append(")'>尾页</a>\r\n");
		}

		sb.append(" 跳到<select onChange='turnOverPage(this.value)'>\r\n");
		for (int i = 1; i <= pageCount; i++) {
			if (i == pageNo) {
				sb.append("  <option value='").append(i)
						.append("' selected='selected'>第").append(i)
						.append("页</option>\r\n");
			} else {
				sb.append("  <option value='").append(i).append("'>第")
						.append(i).append("页</option>\r\n");
			}
		}
		sb.append("</select>\r\n");
		sb.append(" \r\n");
		sb.append("</form>\r\n");

		// 生成提交表单的JS 1.
		sb.append("<script language='javascript'>\r\n");
		sb.append("  //翻页函数\t\n");
		sb.append("  function turnOverPage(no){\r\n");
		sb.append("    alert(no-1);");
		sb.append("    var form = document.pageController;\r\n");
		sb.append("    //页号越界处理\r\n");
		sb.append("    if(no").append(">").append(pageCount).append(") {\r\n");
		sb.append("        no=").append(pageCount).append(";\r\n");
		sb.append("    }\r\n");
		sb.append("    if(no").append("< 1){\r\n");
		sb.append("        no=1;\r\n");
		sb.append("    }\r\n");
		sb.append("    form.").append("pageIndex").append(".value=no-1;\r\n");
		sb.append("    form.submit();\r\n");
		sb.append("  }\r\n");
		sb.append("</script>\r\n");

		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doStartTag();
	}
}
