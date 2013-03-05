package com.ruyicai.mgr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class FTPUtil {

	private static Logger logger = Logger.getLogger(FTPUtil.class);

	public static boolean ftpdownload(String hostname, int port, String ftpdir,
			String localdir, String filename, String ftpmode, String charset,
			String user, String pwd) {
		FTPClient ftp = new FTPClient();
		FileOutputStream fos = null;
		makedir(localdir);
		File filepath = new File(localdir, filename);
		try {
			ftp.connect(hostname, port);
			ftp.login(user, pwd);
			if (ftpmode.equals("PASV")) {
				ftp.enterLocalPassiveMode();
			} else {
				ftp.enterLocalActiveMode();
			}
			ftp.changeWorkingDirectory(ftpdir);
			fos = new FileOutputStream(filepath);
			ftp.setBufferSize(1024);
			ftp.setControlEncoding(charset);
			// 设置文件类型（二进制）
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			boolean isSuccess = ftp.retrieveFile(filename, fos);
			return isSuccess;
		} catch (IOException e) {
			logger.error("ftp下载失败, filename:" + filename, e);
			return false;
		} finally {
			try {
				fos.close();
				ftp.disconnect();
			} catch (IOException e) {
			}
		}
	}

	private static void makedir(String dir) {
		File fdir = new File(dir);
		if (!fdir.exists()) {
			System.out.println(fdir.mkdirs());
		}
	}
}
