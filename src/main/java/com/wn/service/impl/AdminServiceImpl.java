package com.wn.service.impl;

import com.wn.dao.AdminDao;
import com.wn.pojo.Admin;
import com.wn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adao;

	@Override
	public List<Admin> selectAll(Admin admin) {
		return adao.selectAll(admin);
	}

	@Override
	public int insert(Admin admin) {
		return adao.insert(admin);
	}

	@Override
	public int delete(Map<String, String[]> map) {
		return adao.delete(map);
	}

	@Override
	public int update(Admin admin) {
		return adao.update(admin);
	}

	@Override
	public List<Admin> selectLike(Admin admin) {
		return adao.selectLike(admin);
	}

	@Override
	public int selectCount(Admin admin) {
		return adao.selectCount(admin);
	}

	@Override
	public Admin selLogin(String admin_name, Integer admin_pwd) {
		return adao.selLogin(admin_name, admin_pwd);
	}

}
