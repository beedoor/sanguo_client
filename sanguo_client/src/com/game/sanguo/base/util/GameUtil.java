package com.game.sanguo.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");

	public static String convertStr(InputStream input)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		StringBuffer strb = new StringBuffer();
		String s1 = null;
		try {
			while ((s1 = br.readLine()) != null) {
				strb.append(s1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strb.toString();
	}
	public static String parseUnicode(InputStream input) {
		return parseUnicode(convertStr(input));
	}
	public static String parseUnicode(String line) {
		if (line == null) {
			return "";
		}
		int len = line.length();
		char[] out = new char[len];// 保存解析以后的结果
		int outLen = 0;
		for (int i = 0; i < len; i++) {
			char aChar = line.charAt(i);
			if (aChar == '\\') {
				aChar = line.charAt(++i);
				if (aChar == 'u') {
					int value = 0;
					for (int j = 0; j < 4; j++) {
						aChar = line.charAt(++i);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	public static String ingoreNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public static String parseDate(Date date) {
		if (null == date) {
			return "";
		}
		return sdf.format(date);
	}

	// 格式为 new Date(12341122121)
	public static Date parseDate(String dateStr) {
		int sIdx = dateStr.indexOf('(');
		int eIdx = dateStr.indexOf(')');
		if (sIdx != -1 && eIdx != -1) {
			return new Date(Long.parseLong(dateStr.substring(sIdx + 1, eIdx)));
		}
		return null;
	}

	public static void main(String args[]) {
		System.out.println(parseDate("new Date(1386000000000)"));
	}
}
