package com.ruyicai.mgr.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.ruyicai.mgr.exception.RuyicaiException;

public class DES {

	private static Logger logger = Logger.getLogger(DES.class);

	private DES() {
	}

	public static String enc(String keyStr, String content) {
		try {
			SecretKeySpec key = new SecretKeySpec(getKeyByStr(keyStr), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.encode(cipher.doFinal(content
					.getBytes("UTF-8")));
		} catch (Exception e) {
			logger.error("des enc error", e);
			throw new RuyicaiException("des error", e);
		}
	}

	public static String dec(String keyStr, String content) {
		try {
			SecretKeySpec key = new SecretKeySpec(getKeyByStr(keyStr), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] b = Base64.decode(content);
			return new String(cipher.doFinal(b), "UTF-8");
		} catch (Exception e) {
			logger.error("des dec error", e);
			throw new RuyicaiException("des error", e);
		}
	}

	public static byte[] getKeyByStr(String str) {
		byte bRet[] = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i))
					+ getChrInt(str.charAt(2 * i + 1)));
			bRet[i] = itg.byteValue();
		}
		return bRet;
	}

	private static int getChrInt(char chr) {
		int iRet = 0;
		if (chr == "0".charAt(0))
			iRet = 0;
		if (chr == "1".charAt(0))
			iRet = 1;
		if (chr == "2".charAt(0))
			iRet = 2;
		if (chr == "3".charAt(0))
			iRet = 3;
		if (chr == "4".charAt(0))
			iRet = 4;
		if (chr == "5".charAt(0))
			iRet = 5;
		if (chr == "6".charAt(0))
			iRet = 6;
		if (chr == "7".charAt(0))
			iRet = 7;
		if (chr == "8".charAt(0))
			iRet = 8;
		if (chr == "9".charAt(0))
			iRet = 9;
		if (chr == "A".charAt(0))
			iRet = 10;
		if (chr == "B".charAt(0))
			iRet = 11;
		if (chr == "C".charAt(0))
			iRet = 12;
		if (chr == "D".charAt(0))
			iRet = 13;
		if (chr == "E".charAt(0))
			iRet = 14;
		if (chr == "F".charAt(0))
			iRet = 15;
		return iRet;
	}
}
