package com.ruyicai.mgr.util;

import java.util.ArrayList;
import java.util.List;

public class URLExcludeUtil {

	private static List<String> urls = new ArrayList<String>();
	
	static {
		urls.add("/frame");
		urls.add("/login");
		urls.add("/logout");
		urls.add("/changepassword");
		urls.add(".jpg");
		urls.add(".png");
		urls.add(".gif");
		urls.add(".js");
		urls.add(".css");
		urls.add(".htm");
		urls.add(".html");
		urls.add("/fckeditor");
		
	}
	
	public static List<String> getExcludeUrls() {
		return urls;
	}
}
