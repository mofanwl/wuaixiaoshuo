package com.wn.dao;

import com.wn.pojo.Admin;
import com.wn.pojo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//admin管理员表
@Mapper
public interface AdminDao {
	
	/*
	 * login
	 */
	public Admin selLogin(@Param("admin_name") String admin_name, @Param("admin_pwd") Integer admin_pwd);
	

	/*
	 * 查询全部
	 */
	public List<Admin> selectAll(Admin admin);

	/*
	 * 增
	 */
	public int insert(Admin admin);

	/*
	 * 删
	 */
	public int delete(Map<String, String[]> map);

	/*
	 * 改
	 */
	public int update(Admin admin);

	/*
	 * 模糊查询
	 */
	public List<Admin> selectLike(Admin admin);

	/*
	 * 查询总行数
	 */
	public int selectCount(Admin admin);



	/*
	YC
	 */
	/*
	 * 模糊查询
	 */
	public List<Admin> selectLikeC(Admin admin);
	/*
	 * 查询全部
	 */
	public List<Admin> selectAllC(Admin admin);
	/*
	 * 增
	 */
	public int insertC(Admin admin);
	/*
	 * 查询总行数
	 */
	public int selectCountC(Admin admin);
	/*
    分页查询+模糊分页
    */
	public List<Admin> selectLimitC(Page p);
	public int insertRc(Admin admin);
	/*
	YC
	 */


}
