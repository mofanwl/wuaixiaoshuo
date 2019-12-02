package com.wn.pojo;

import lombok.Data;

//角色表--游客-默认会员--VIP会员
@Data
public class Group {
	private Integer group_id;// 编号
	private String group_name;// 身份名（游客，默认会员，vip）
	private Integer group_status;// 状态
	private String group_type;// 类型
	//private String group_popedom;// 权限
	private Integer group_points_day;// 天
	private Integer group_points_week;// 周
	private Integer group_points_month;// 月
	private Integer group_points_year;// 年
	private Integer group_points_free;// 免费
}
