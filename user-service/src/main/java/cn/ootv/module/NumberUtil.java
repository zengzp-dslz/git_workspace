package cn.ootv.module;

import java.math.BigDecimal;
import java.util.Random;
import org.apache.commons.lang.StringUtils;

/**
 * @author zengzp
 * @title NumberUtil
 * @description
 * @date 2017年3月10日 下午2:52:40
 * @version V1.0
 */
public class NumberUtil {


	/**
	 * @author zengzp
	 * @description 获取指定范围内的随机数
	 * @date 2017年3月10日 下午2:54:54
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInt(int min, int max) {
		return (int) Math.round(Math.random() * (max - min) + min);
	}

	
	public static int parseInt(String in) {
		return parseInt(in, 0);
	}

	public static int parseInt(String in, int defaultValue) {
		int re = defaultValue;
		if (!StringUtils.isEmpty(in)) {
			try {
				re = Integer.parseInt(in);
			} catch (Exception e) {
				re = defaultValue;
			}
		}
		return re;
	}

	public static long parseLong(String in) {
		return parseLong(in, 0L);
	}

	public static long parseLong(String in, long defaultValue) {
		long re = defaultValue;
		if (!StringUtils.isEmpty(in)) {
			try {
				re = Long.parseLong(in);
			} catch (Exception e) {
				re = defaultValue;
			}
		}
		return re;
	}

	public static double parseDouble(String in) {
		return parseDouble(in, 0.0D);
	}

	public static double parseDouble(String in, double defaultValue) {
		double re = defaultValue;
		if (!StringUtils.isEmpty(in)) {
			try {
				re = Double.parseDouble(in);
			} catch (Exception e) {
				re = defaultValue;
			}
		}
		return re;
	}

	/*
	 * public static int[] parseInt(String[] in) { int[] arr = new
	 * int[in.length]; int i = 0; String[] arrayOfString = in; int j =
	 * in.length; for (int i = 0; i < j; i++) { String s = arrayOfString[i];
	 * arr[i] = parseInt(s); i++; } return arr; }
	 */

	public static String getRandomNumberWithFixLength(int digCount) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(digCount);
		for (int i = 0; i < digCount; i++)
			sb.append((char) (48 + rnd.nextInt(10)));
		return sb.toString();
	}

	public static double divide(int scale, Long dividend, Long divisor) {
		Long zero = new Long(0L);
		if ((dividend == null) || (zero.equals(dividend)) || (divisor == null)
				|| (zero.equals(divisor))) {
			return 0.0D;
		}

		BigDecimal ret = null;
		try {
			ret = new BigDecimal(dividend.longValue()).divide(new BigDecimal(
					divisor.longValue()), scale, 4);
		} catch (Exception e) {
			return 0.0D;
		}
		return ret.doubleValue();
	}

	public static double divide(int scale, Double dividend, Long divisor) {
		Long zero = new Long(0L);
		if ((dividend == null) || (zero.equals(dividend)) || (divisor == null)
				|| (zero.equals(divisor))) {
			return 0.0D;
		}

		BigDecimal ret = null;
		try {
			ret = new BigDecimal(dividend.doubleValue()).divide(new BigDecimal(
					divisor.longValue()), scale, 4);
		} catch (Exception e) {
			return 0.0D;
		}
		return ret.doubleValue();
	}

	public static double divide(int scale, Double dividend, Double divisor) {
		Long zero = new Long(0L);
		if ((dividend == null) || (zero.equals(dividend)) || (divisor == null)
				|| (zero.equals(divisor))) {
			return 0.0D;
		}

		BigDecimal ret = null;
		try {
			ret = new BigDecimal(dividend.doubleValue()).divide(new BigDecimal(
					divisor.doubleValue()), scale, 4);
		} catch (Exception e) {
			return 0.0D;
		}
		return ret.doubleValue();
	}

	public static double divide(int scale, String dividend, String divisor) {
		Long zero = new Long(0L);
		if ((dividend == null) || (zero.equals(dividend)) || (divisor == null)
				|| (zero.equals(divisor))) {
			return 0.0D;
		}

		BigDecimal ret = null;
		try {
			ret = new BigDecimal(dividend).divide(new BigDecimal(divisor),
					scale, 4);
		} catch (Exception e) {
			return 0.0D;
		}
		return ret.doubleValue();
	}

	public static Long liConvertFeng(Long liValue) {
		if ((liValue == null) || (liValue.longValue() == 0L))
			return Long.valueOf(0L);

		BigDecimal ret = null;
		try {
			ret = new BigDecimal(liValue.longValue()).divide(
					new BigDecimal(10), 0, 4);
		} catch (Exception e) {
			return Long.valueOf(0L);
		}
		return Long.valueOf(ret.longValue());
	}
}