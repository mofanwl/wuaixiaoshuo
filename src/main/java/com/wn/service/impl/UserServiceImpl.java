package com.wn.service.impl;

import com.wn.dao.UserDao;
import com.wn.pojo.User;
import com.wn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao udao;

	@Override
	public List<User> selectAll(User user) {
		return udao.selectAll(user);
	}

	@Override
	public int insert(User user) {
		return udao.insert(user);
	}

	@Override
	public int delete(Map<String, String[]> map) {
		return udao.delete(map);
	}

	@Override
	public int update(User user) {
		return udao.update(user);
	}

	@Override
	public List<User> selectLike(User user) {
		return udao.selectLike(user);
	}

	@Override
	public int selectCount(User user) {
		return udao.selectCount(user);
	}

	@Override
	public User selectOne(String user_name) {
		return udao.selectOne(user_name);
	}

	@Override
	public User selectDl(String user_name, String user_pwd) {
		return udao.selectDl(user_name, user_pwd);
	}

	@Override
	public User selectUserById(Integer user_id) {
		return udao.selectUserById(user_id);
	}

	@Override
	public int insertVipTime(User user) {
		return udao.insertVipTime(user);
	}

	@Override
	public String selByVipTime(Integer user_id) {
		return udao.selByVipTime(user_id);
	}

	@Override
	public User selectPhone(String user_phone) {
		return udao.selectPhone(user_phone);
	}

	/*
	 * 后端分页查询
	 */

	@Override
	public List<User> selectAllUser(int page, int rows) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", (page - 1) * rows);
		map.put("end", rows);
		return udao.selectAllUser(map);

	}

	@Override
	public int del(int user_id) {
		return udao.del(user_id);
	}

}
