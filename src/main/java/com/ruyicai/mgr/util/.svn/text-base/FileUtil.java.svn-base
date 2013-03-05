package com.ruyicai.mgr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ruyicai.mgr.exception.RuyicaiException;

public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileUtil.class);

	public static List<String> read(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String data = null;
			while(null != (data = in.readLine())) {
				list.add(data);
			}
		} catch(Exception e) {
			logger.error("读取文件出错, fileName:　" + file.getName() + ", error: " + e.getMessage());
			throw new RuyicaiException("读取文件出错, fileName:　" + file.getName() + ", error: " + e.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return list;
	}
	
	public static Properties loadProps(String resourceLocation) {
		Properties props = new Properties();
		try {
			props.load(FileUtil.class.getClassLoader().getResourceAsStream(resourceLocation));
		} catch (IOException e) {
			logger.error("load properties error", e);
		}
		return props;
	}
}
