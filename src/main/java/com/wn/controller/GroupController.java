package com.wn.controller;

import com.wn.pojo.Group;
import com.wn.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;

	@RequestMapping("/show")
	@ResponseBody
	public List<Group> show(Group group) {
		List<Group> list = groupService.selectAll(group);
		return list;
	}

	@RequestMapping("/insert")
	@ResponseBody
	public int insert(Group group) {
		int res = groupService.insert(group);
		return res;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public int delete(String[] empnos) {
		Map<String, String[]> map = new HashMap<>();
		map.put("enos", empnos);
		int res = groupService.delete(map);
		return res;
	}

	@RequestMapping("/update")
	@ResponseBody
	public int update(Group group) {
		int res = groupService.update(group);
		return res;
	}
}
