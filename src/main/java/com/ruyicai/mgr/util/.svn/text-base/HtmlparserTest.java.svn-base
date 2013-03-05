package com.ruyicai.mgr.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlparserTest {

	public static void main(String[] args) {
		String htmlText = null;
		try {
			htmlText = fetchContentByJDKConnection("http://www.okooo.com/zucai/13003?rad="+new Date().getTime());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (htmlText != null) {
			Parser parser = Parser.createParser(htmlText, "GBK");
			NodeFilter filter = new AndFilter(new TagNameFilter("table"), 
					new HasAttributeFilter("class", "jcmaintable"));
			NodeList nodes = null;
			try {
				nodes = parser.extractAllNodesThatMatch(filter);
			} catch (ParserException e) {
				e.printStackTrace();
			}
			if (nodes != null && nodes.size() > 0) {
				TableTag table = (TableTag) nodes.elementAt(0);
				//String childrenHTML = table.getChildrenHTML();
				//System.out.println(childrenHTML);
				TableRow[] rows = table.getRows();
				for(TableRow row : rows){
					/*StringTokenizer st = new StringTokenizer(row.toPlainTextString());
					while(st.hasMoreTokens()){
						System.out.print(st.nextToken()+"\t");
					}
					System.out.println();*/
					/*TableColumn[] columns = row.getColumns();
					for(TableColumn col : columns){
						String s  = col.toPlainTextString().trim();
						System.out.print(s+"\t");
						System.out.println("-------------------");
						System.out.println(col.getAttribute("span"));
					}*/
					TableColumn[] columns = row.getColumns();
					TableColumn t = columns[3];
					NodeList children = t.getChildren();
					//NodeList nodeList = children.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("span"),
							//new HasAttributeFilter("class", "homenameobj awayname")));
					
					NodeList nodeList = children.extractAllNodesThatMatch(new TagNameFilter("span"));
					Span span1 = (Span)nodeList.elementAt(0);
					System.out.println(span1.toPlainTextString());
					System.out.println(nodeList.elementAt(0).toPlainTextString());
					
					System.out.println(nodeList.toHtml());
					NodeList nodeList1 = children.extractAllNodesThatMatch(new TagNameFilter("em"));
					System.out.println(nodeList1.toHtml()+"111");
					System.out.println(nodeList1.elementAt(0).toPlainTextString());
					System.out.println(nodeList1.elementAt(1).toPlainTextString());
				/*	for (int i = 0; i < nodeList1.size(); i++) {
						System.out.println(nodeList1.elementAt(i).toPlainTextString());
					}*/
					
					//Span span2 = ()nodeList.elementAt(0);
					
					//System.out.println(span2.toPlainTextString());
					//System.out.println(t.getChildrenHTML());
					//System.out.println(t.toHtml());
					//System.out.println("---"+t.toPlainTextString().trim()+"+++");
					
				}
			} else {
				System.out.println("error");
			}
		}
	}

	public static String fetchContentByJDKConnection(String contentUrl)
			throws IOException {

		HttpURLConnection connection = (HttpURLConnection) new URL(contentUrl)
				.openConnection();
		connection.setReadTimeout(20 * 1000);
		try {
			connection.connect();
			InputStream input = null;
			try {
				input = connection.getInputStream();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			try {
				IOUtils.copy(input, output);
				output.flush();
				return new String(output.toByteArray(), "GBK");
			} finally {
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(output);
			}
		} finally {
			connection.disconnect();
		}
	}

}
