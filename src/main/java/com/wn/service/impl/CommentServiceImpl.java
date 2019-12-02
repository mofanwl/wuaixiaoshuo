package com.wn.service.impl;

import com.wn.dao.CommentDao;
import com.wn.pojo.Comment;
import com.wn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao cdao;

	@Override
	public List<Comment> selectAll(Comment comment) {
		return cdao.selectAll(comment);
	}

	@Override
	public int insert(Comment comment) {
		return cdao.insert(comment);
	}

	@Override
	public int delete(Map<String, String[]> map) {
		return cdao.delete(map);
	}

	@Override
	public int update(Comment comment) {
		return cdao.update(comment);
	}

	@Override
	public List<Comment> selectLike(Comment comment) {
		return cdao.selectLike(comment);
	}

	@Override
	public int selectCount(Comment comment) {
		return cdao.selectCount(comment);
	}

}
