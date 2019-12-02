package com.wn.service;

import com.wn.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
	/*
	 * 查询全部
	 */
	public List<Comment> selectAll(Comment comment);

	/*
	 * 增
	 */
	public int insert(Comment comment);

	/*
	 * 删
	 */
	public int delete(Map<String, String[]> map);

	/*
	 * 改
	 */
	public int update(Comment comment);

	/*
	 * 模糊查询
	 */
	public List<Comment> selectLike(Comment comment);

	/*
	 * 查询总行数
	 */
	public int selectCount(Comment comment);
}
