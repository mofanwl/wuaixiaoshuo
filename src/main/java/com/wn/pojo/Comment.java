package com.wn.pojo;

import lombok.Data;

/*
 * 留言表
 */
@Data
public class Comment {
	private Integer comment_id;// 编号
	private Integer comment_mid;// 模块id
	private Integer comment_rid;//文章的id
	private Integer comment_pid;
	private Integer user_id;// 用户id
	private String comment_name;// 用户名昵称
	private Integer comment_status;// 状态0未审核1已审核
	private Long comment_ip;// ip地址
	private Long comment_time;// 留言时间
	private String comment_content;// 留言内容
	private Integer comment_up;// 顶数
	private Integer comment_down;// 踩数
	private Integer comment_reply;// 回复数
	private Integer comment_report;// 举报

}
