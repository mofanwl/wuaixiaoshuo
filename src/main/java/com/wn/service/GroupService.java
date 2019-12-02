package com.wn.service;

import com.wn.pojo.Group;

import java.util.List;
import java.util.Map;

public interface GroupService {
	/*
	 * 查询全部
	 */
	public List<Group> selectAll(Group group);

	/*
	 * 增
	 */
	public int insert(Group group);

	/*
	 * 删
	 */
	public int delete(Map<String, String[]> map);

	/*
	 * 改
	 */
	public int update(Group group);

	/*
	 * 模糊查询
	 */
	public List<Group> selectLike(Group group);

	/*
	 * 查询总行数
	 */
	public int selectCount(Group group);
}
