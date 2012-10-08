/**********************************************************************
 * FILE : EncryptUtils.java
 * CREATE DATE : 2008-07-01
 * DESCRIPTION :
 *		
 *		在IMES系统中，EncryptUtils提供对用户密码SHA-1算法签名，以及验证的函数。
 *		提供用户登录后Token的生成，以及Token的提交验证。
 *      
 * CHANGE HISTORY LOG
 *---------------------------------------------------------------------
 * NO.|    DATE    |     NAME     |     REASON     | DESCRIPTION
 *---------------------------------------------------------------------
 * 1  | 2008-07-01 |  Liu Yan Lu  |    创建草稿版本
 *---------------------------------------------------------------------              
 **********************************************************************
 */
package org.lch.attendance.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtil {

	// 系统生成令牌的过期时间
	public static final int TOKEN_EXPIRED_DATE = 3; // days

	public static final String TOKEN_SEPARATOR = ",";

	public static final String TOKEN_PREFIX = "#TOKEN";

	private static final String TOKEN_KEY = "itaf.rcp.tokenKey";

	private final static String FORMAT_DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将明文的密码以SHA-1算法加密
	 * 
	 * @param passwd
	 *            明文密码
	 * @return String SHA-1算法加密后的密码签名
	 */
	public static String encodePassword(String passwd) {
		MessageDigest md = null;
		byte[] bt = passwd.getBytes();
		String strDes = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}

		return strDes;
	}

	/**
	 * 验证用户输入的密码是否与数据库中SHA-1算法加密后的密码相同
	 * 
	 * @param passwd
	 *            明文密码
	 * @param sha1_passwd
	 *            SHA-1加密算法后的密码签名
	 * @return true 相同 false 不相同
	 */
	public static boolean validatePassword(String passwd, String sha1_passwd) {

		if (null == passwd || null == sha1_passwd)
			return false;

		if (sha1_passwd.equals(encodePassword(passwd))) {
			return true;
		}

		return false;
	}

	/**
	 *  用户登录验证成功后，系统自动为用户生成TOKEN : 通过可逆加密算法，将username + login
	 * timestamp转换为字符串，作为TOKEN返回给客户端。
	 * 
	 * @param username
	 *            用户登录名
	 */
	public static String generateToken(Long userId, String username) {
		String currentDate = getCurrentDateAsString(FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);

		String before_token_str = currentDate + TOKEN_SEPARATOR + userId
				+ TOKEN_SEPARATOR + username;

		return addTokenPrefix(encodeToken(before_token_str));
	}

	/**
	 *  验证用户的TOKEN是否正确 : 通过可逆加密算法，将Token拆分为username和login timestamp。
	 * 判断拆分出来的username是否与输入的用户名相同，login时间是否没有超过指定的有效期
	 * 
	 * @param username
	 *            用户登录用ID
	 * @param token
	 *            用户的Token
	 * 
	 * @return true 验证通过 false 验证失败
	 */
	public final static String[] validateToken(String username, String token)
			throws Exception {
		String err_str = "Invalid token!";
		Calendar current_time = Calendar.getInstance();
		String encryptToken = removeTokenPrefix(token);
		// remove prefix
		encryptToken = decodeToken(encryptToken);
		if (null == encryptToken || encryptToken.equals(""))
			throw new Exception(err_str);

		String[] sep_index = encryptToken.split(TOKEN_SEPARATOR);
		if (sep_index == null || sep_index.length <= 0)
			throw new Exception(err_str);

		String strtoken_time = sep_index[0];
		String strtoken_userid = sep_index[1];
		String strtoken_username = sep_index[2];
		try {
			Long.parseLong(strtoken_userid);
		} catch (Exception e) {
			throw new Exception(err_str);
		}

		if (!username.equalsIgnoreCase(strtoken_username))
			throw new Exception(err_str);

		Date token_date = parseDate(strtoken_time,
				FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);

		Calendar expiredDate = Calendar.getInstance();
		expiredDate.setTime(token_date);
		expiredDate.add(Calendar.DAY_OF_MONTH, TOKEN_EXPIRED_DATE);
		if (expiredDate.before(current_time))
			throw new Exception(err_str);
		return sep_index;
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	private static byte[] hex2Bytes(String srcStr) {
		int byte_len = srcStr.length() / 2;
		byte[] result = new byte[byte_len];
		for (int i = 0; i < byte_len; i++) {
			String bytestr = srcStr.substring(i * 2, i * 2 + 2);
			result[i] = Integer.valueOf(bytestr, 16).byteValue();
		}
		return result;
	}

	public static String encodeToken(String inputstr) {

		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密匙数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(TOKEN_KEY.getBytes());

			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

			SecretKey securekey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

			// 现在，获取数据并加密
			// 正式执行加密操作
			return bytes2Hex(cipher.doFinal(inputstr.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String decodeToken(String inputStr) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(TOKEN_KEY.getBytes());

			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

			// 现在，获取数据并解密
			// 正式执行解密操作
			return new String(cipher.doFinal(hex2Bytes(inputStr)));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private final static String getCurrentDateAsString(String formatPattern) {
		Date date = new Date();
		return new SimpleDateFormat(formatPattern).format(date);
	}

	private final static Date parseDate(String stringValue, String formatPattern) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		try {
			return format.parse(stringValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public final static boolean isEncryptToken(String str) {
		return str.startsWith(TOKEN_PREFIX);
	}

	private final static String removeTokenPrefix(String str) {
		if (str.startsWith(TOKEN_PREFIX)) {
			str = str.substring(TOKEN_PREFIX.length());
		}
		return str;
	}

	private final static String addTokenPrefix(String str) {
		return TOKEN_PREFIX + str;
	}
}
