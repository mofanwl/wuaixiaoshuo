package com.wn.controller;

import com.wn.pojo.Admin;
import com.wn.pojo.Group;
import com.wn.pojo.Single;
import com.wn.pojo.UserPermission;
import com.wn.service.AdminPermissionService;
import com.wn.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired
	private AdminPermissionService adminPermissionService;

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

	/*
	YC
	 */
			/*
			角色-权限
			*/
	@RequestMapping("/perssion_gC")
	public String perssion_g(){
		return "redirect:findperssion_gC";
	}
	/*
    根据角色-查询权限
    */
	@RequestMapping("/findperssion_gC")
	public String findperssion_g( Model model){
		Admin singleton = Single.getSingleton();

		model.addAttribute("username",singleton.getAdmin_name());

		//		查询权限
		List<UserPermission> adminPermissions = adminPermissionService.selectByRoler(singleton.getAdmin_name());
		model.addAttribute("aper",adminPermissions);

		//查询角色
		List<Group> groups = adminPermissionService.selectByGroup(singleton.getAdmin_name());
		Group g1=new Group();
		for(Group g:groups){
			g1=g;
		}
		model.addAttribute("groups",g1);
		return "perssion_g";
	}

	/*
     角色-用户
     */
	@RequestMapping("/admin_gC")
	public String admin_g(){

		return "redirect:findadmin_gC";
	}
	@RequestMapping("/findadmin_gC")
	public String findadmin_g( Model model){
		Admin singleton = Single.getSingleton();

		model.addAttribute("username",singleton.getAdmin_name());

		List<Group> groups = adminPermissionService.selectByGroup(singleton.getAdmin_name());

		Group g1=new Group();
		for(Group g:groups){
			g1=g;
		}
		model.addAttribute("groups",g1);
		return "admin_g";
	}
	/*
	YC
	 */
}
