package com.wn.dao;

import com.wn.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//用户表
@Mapper
public interface UserDao {
	/*
	加入会员时间
	 */
	public int insertVipTime(User user);
	/*
	查询会员到期时间
	 */
	public String selByVipTime(Integer user_id);
	/*
	 * 手机号查询
	 */
	public User selectPhone(String user_phone);
	/*
	 * 用户名登陆
	 */
	public User selectDl(@Param("user_name") String user_name, @Param("user_pwd") String user_pwd);
	/*
	 * name查询一个用户
	 */
	public User selectOne(String user_name);
	/*
	 * id查询一个用户
	 */
	public User selectUserById(Integer user_id);
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
	public List<User> selectAllUser(Map<String, Integer> map);
	/*
	 * 删除一行数据
	 */
	public int del(int user_id);
}
