package com.wyqlib.java.utils.base.string;

public class StringUtils {
	// ------------------------------------------------------------------------------
	// null------------------------------------------------------------------------------
	public static boolean isNullOrEmpty(String value) {
		if (value != null) {
			return (value.toCharArray().length == 0);
		}
		return true;
	}

	public static boolean IsNullOrWhiteSpace(String value) {
		if (isNullOrEmpty(value))
			return true;
		else {
			return value.trim().toCharArray().length == 0;
		}
	}

	// trim------------------------------------------------------------------------------
	public static String trimEnd(String s, char[] tcs) {

		return trimHelper(s, tcs, 1);
	}

	public static String trimStart(String s, char[] tcs) {
		return trimHelper(s, tcs, 0);
	}

	public static String trim(String s, char[] tcs) {
		return trimHelper(s, tcs, 2);
	}

	public static String trimHelper(String s, char[] trimChars, int trimType) {
		char[] cs = s.toCharArray();
		int end = s.length() - 1;
		int start = 0;
		if (trimType != 1) {
			start = 0;
			while (start < cs.length) {
				int index = 0;
				char ch = cs[start];
				index = 0;
				while (index < trimChars.length) {
					if (trimChars[index] == ch) {
						break;
					}
					index++;
				}
				if (index == trimChars.length) {
					break;
				}
				start++;
			}
		}
		if (trimType != 0) {
			end = cs.length - 1;
			while (end >= start) {
				int num4 = 0;
				char ch2 = cs[end];
				num4 = 0;
				while (num4 < trimChars.length) {
					if (trimChars[num4] == ch2) {
						break;
					}
					num4++;
				}
				if (num4 == trimChars.length) {
					break;
				}
				end--;
			}
		}
		int length = (end - start) + 1;
		if (length == cs.length) {
			return s;
		}
		if (length == 0) {
			return "";
		}
		return s.substring(start, end + 1);
	}
	// ------------------------------------------------------------------------------
}
