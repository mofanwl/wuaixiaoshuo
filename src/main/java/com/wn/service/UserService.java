package com.wn.service;

import com.wn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
	/*
	 * 手机号查询
	 */
	public User selectPhone(String user_phone);
	/*
	 * 用户名登陆
	 */
	public User selectDl(@Param("user_name") String user_name, @Param("user_pwd") String user_pwd);
	
	/*
	 * 查询一个用户
	 */
	public User selectOne(String user_name);
	/*
	 * 查询全部
	 */
	public List<User> selectAll(User user);

	/*
	 * 增
	 */
	public int insert(User user);

	/*
	 * 删
	 */
	public int delete(Map<String, String[]> map);

	/*
	 * 改
	 */
	public int update(User user);

	/*
	 * 模糊查询
	 */
	public List<User> selectLike(User user);

	/*
	 * 查询总行数
	 */
	public int selectCount(User user);
	/*
	 * 后端分页查询
	 */
	public List<User> selectAllUser(int page, int rows);
	/*
	 * 删除一行数据
	 */
	public int del(int user_id);
}
