package com.wn.pojo;

import lombok.Data;

//用户表
@Data
public class User {
	private Integer user_id;// 用户编号
	private Integer group_id;// 用户组编号
	private String user_name;// 登录名
	private String user_pwd;// 密码
	private String user_nick_name;// 昵称
	private String user_qq;// QQ
	private String user_email;// 邮箱
	private String user_phone;// 联系电话
	private Integer user_status;// 状态
	private String user_portrait;// 头像
	private String user_portrait_thumb;// 小头像
	private String user_openid_qq;// qq关联
	private String user_openid_weixin;// 微信关联
	private String user_question;// 用户提问
	private String user_answer;// 用户回答
	private Integer user_points;// 积分
	private Integer user_points_froze;// 积分冻结
	private Long user_reg_time;// 注册时间
	private Long user_reg_ip;// 注册ip
	private Long user_login_time;// 登录时间
	private Long user_login_ip;// 登录ip
	private Long user_last_login_time;// 上次登录时间
	private Long user_last_login_ip;// 上次登录ip
	private Integer user_login_num;// 登录次数
	private Integer user_extend;// 用户拓展
	private String user_random;// 用户随机数
	private Long user_end_time;// vip截止期限
	private Integer user_pid;// 用户PID
	private Integer user_pid_2;// PID2
	private Integer user_pid_3;// PID3

}
