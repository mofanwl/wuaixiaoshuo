package com.wn.controller;

import com.wn.pojo.Admin;
import com.wn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/show")
	@ResponseBody
	public List<Admin> show() {
		List<Admin> list = adminService.selectAll(null);
		return list;
	}

	@RequestMapping("/insert")
	@ResponseBody
	public int insert(Admin admin) {
		Admin m = new Admin();
		m.setAdmin_name(admin.getAdmin_name());
		List<Admin> list = adminService.selectAll(m);
		if (list.size() == 0) {
			int res = adminService.insert(admin);
			return res;
		}
		return 0;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public int delete(String[] empnos) {
		Map<String, String[]> map = new HashMap<>();
		map.put("enos", empnos);
		int res = adminService.delete(map);
		return res;
	}

	@RequestMapping("/update")
	@ResponseBody
	public int update(Admin admin) {
		int res = adminService.update(admin);
		return res;
	}
	@RequestMapping("/admin")
	public String tiao(){
		return "admin";
	}
	@RequestMapping("/adminlogin")
	//@ResponseBody
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		String admin_name = request.getParameter("admin_name");
		if(admin_name==null){
			admin_name="1";
		}else if(admin_name==" "){
			admin_name="1";
		}
		String admin_pwd = request.getParameter("admin_pwd");
		if(admin_pwd==null){
			admin_name="1";
		}else if(admin_name==""){
			admin_name="1";
		}
		Integer pwd=Integer.valueOf(admin_pwd);

		Admin admin = adminService.selLogin(admin_name, pwd);
		System.out.println(admin);
		ModelAndView mv = new ModelAndView();
		if (admin != null) {
			mv.setViewName("main");
			//return "ok";
			
			//return "redirece:tiao";
		} else {
		//	ModelAndView mv = new ModelAndView("admin");
			mv.setViewName("admin");
			//return "admin";
			//mv.addObject("cuowu");
			//return "no";
			//request.getRequestDispatcher("admin.html").forward(request,response);
			// request.setAttribute("fanhui", "错误");
		}

		return mv;

		/*
		 * Admin admin = new Admin(); admin.setAdmin_name(admin_name);
		 * admin.setAdmin_pwd(admin_pwd); List<Admin> list =
		 * adminService.selectAll(admin); if (list.size() != 0) { // 成功跳转到管理界面
		 * // user地址在根目录里；admin在根目录里；admin.jsp在admin里；
		 * request.getRequestDispatcher("").forward(request, response); return
		 * 1; }
		 */
		//后台页面的跳转------------------------------------------------------------------------------

	}
	@RequestMapping("/login1")
	public String login(){
		return "login";
	}
}
