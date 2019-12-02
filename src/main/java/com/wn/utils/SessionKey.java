package com.wn.utils;

public interface SessionKey {
	/*
	 * 验证码
	 */
	public final static String SYS_CODE="RANDOMVALIDATECODEKEY";
	/*
	 * 登陆用户信息
	 */
	public final static String SYS_USER="user";//登陆成功。记录用户信息  session.setAttribute(Sessionkey.SYS_USER,User)
	//session.getAttribute(SessionKey.SYS_USER);
}
