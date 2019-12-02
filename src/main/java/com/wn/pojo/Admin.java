package com.wn.pojo;

import lombok.Data;

//admin管理员表
@Data
public class Admin {
	private Integer admin_id;// 编号
	private String admin_name;// 账户
	private String admin_pwd;// 密码
	private String admin_random;// 随机
	private Integer admin_status;// 状态
	private String admin_auth;// 认证
	private Long admin_login_time;// 登录时间
	private Long admin_login_ip;// ip地址
	private Integer admin_login_num;// 登录次数
	private Long admin_last_login_time;// 最后登录的时间
	private Long admin_last_login_ip;// 最后登录的ip

}
