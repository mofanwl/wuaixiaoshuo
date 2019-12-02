package com.wn.service.impl;

import com.wn.dao.GroupDao;
import com.wn.pojo.Group;
import com.wn.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao gdao;

	@Override
	public List<Group> selectAll(Group group) {
		return gdao.selectAll(group);
	}

	@Override
	public int insert(Group group) {
		return gdao.insert(group);
	}

	@Override
	public int delete(Map<String, String[]> map) {
		return gdao.delete(map);
	}

	@Override
	public int update(Group group) {
		return gdao.update(group);
	}

	@Override
	public List<Group> selectLike(Group group) {
		return gdao.selectLike(group);
	}

	@Override
	public int selectCount(Group group) {
		return gdao.selectCount(group);
	}

}
