package cmp;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	
	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	
	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式为源md5比特
	public static byte[] hexStr2ByteArray(String hexStr) {
		hexStr = hexStr.toUpperCase();
		char[] data = hexStr.toCharArray();
		int len = data.length;

		byte[] out = new byte[len / 2];
		for (int i = 0; i < len; i = i + 2) {
			String strTmp = new String(data, i, 2);
			out[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return out;
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}
	
	public final static String MD5String(String s) {
		
		String resultString = null;
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			resultString = byteToString(mdInst.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return resultString;
		}
		return resultString;
	}
	
	public final static byte[] MD5Byte(String s) {
		byte[] byt = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byt = md.digest(s.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byt;
	}
	
	public String encode(byte[] byt) {
		String s = "";
		try {
			BASE64Encoder be = new BASE64Encoder();
			if (byt != null) {
				s = be.encode(byt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public String encode(String s) {
		String encode = "";
		try {
			BASE64Encoder be = new BASE64Encoder();
			if (s != null) {
				encode = be.encode(s.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encode;
	}

	public String decode(String s) {
		String decode = "";
		try {
			BASE64Decoder bd = new BASE64Decoder();
			if (s != null) {
				byte[] byt = bd.decodeBuffer(s);
				decode = byteToString(byt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decode;
	}

	public static void main(String[] args) {
		String s = Md5Util.MD5String("Y6KjCoBzdqcd");
		System.out.println(s);
	}
}
