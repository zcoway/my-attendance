package org.lch.attendance.common;

import org.lch.attendance.domain.User;

/**
 * 
 * 应用Context,包含当前用户相关的所有信息
 * 
 * 
 */
public final class AppContext {


	/**
	 * 当前线程中的用户信息
	 */
	private final static ThreadLocal<User> currentUser = new ThreadLocal<User>();

	/**
	 * 设置用户信息
	 * 
	 * @param userInfo
	 *            用户信息
	 */
	public static void setUserInfo(User userInfo) {
		currentUser.set(userInfo);
	}

	/**
	 * 返回当前用户信息
	 * 
	 * @return
	 */
	public static User getUserInfo() {
		if (currentUser.get() == null) {
			currentUser.set(new User());
		}

		return currentUser.get();
	}

	public static void cleanUser() {
		currentUser.set(null);
	}
}