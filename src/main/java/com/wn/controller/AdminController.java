package com.wn.controller;

import com.wn.pojo.Admin;
import com.wn.pojo.Page;
import com.wn.pojo.Single;
import com.wn.service.AdminService;
import com.wn.utils.MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


	/*
YC
 */
/*
    新增
     */
	@RequiresPermissions(value = "admin_insert")
	@RequestMapping("/insertC")
	public String insertC(Admin admin)  {
		//查询是否有对应账户
		Admin m = new Admin();
		m.setAdmin_name(admin.getAdmin_name());
		List<Admin> list = adminService.selectAllC(m);
		if (list.size() == 0) {
			//没有，则新增数据
			int res = adminService.insertC(admin);
			return "redirect:/admin/findLimitC";
		}else{
			return "error";
		}

	}
	@RequiresPermissions(value = "admin_insert")
	@RequestMapping("/saveC")
	public String save() {
		return "admin_save";
	}
	/*
        删除,改变状态         */
	@RequiresPermissions(value = "admin_delete")
	@RequestMapping("/deleteOneC")
	public String delete1C(Integer id) {
		Admin admin=new Admin();
		admin.setAdmin_id(id);
		admin.setAdmin_status(2);
		int update = adminService.update(admin);
		if(update!=0){
			return "redirect:/admin/findLimitC";
		}else{
			return "error";
		}

	}
	/*
    修改
     */
	@RequiresPermissions(value = "admin_update")
	@RequestMapping("/update1C")
	public String update1(Admin admin,Model model) {

		int res = adminService.update(admin);
		if(res!=0){
			return "redirect:/admin/findLimitC";
		}else{
			return "error";
		}
	}
	@RequiresPermissions(value = "admin_update")
	@RequestMapping("/updateC")
	public String update(Integer id,Model model) {
		Admin admin=new Admin();
		admin.setAdmin_id(id);
		List<Admin> admins = adminService.selectAllC(admin);

		for(Admin a:admins){
			if(a!=null){

				admin=a;
			}
		}
		model.addAttribute("admin",admin);
		return "admin_update";
	}
/*
模糊查询！
 */

	@RequestMapping("/findLikeC")
	public String findLike(Admin admin,Model model){
		Admin singleton = Single.getSingleton();
		model.addAttribute("username",singleton.getAdmin_name());
		List<Admin> admins = adminService.selectLikeC(admin);
		model.addAttribute("admins",admins);
		return "admin_index";

	}
	/*
    分页查询
     */
	private Page p=new Page();
	private  Integer a=p.getPage();
	@RequestMapping("/indexpageC")
	public String indexpage(Model model){//下一页
		a=a+1;
		if(a>p.getPageTotal())
		{
			a=a-1;
		}
		return "redirect:findLimitC";
	}
	@RequestMapping("/lastpageC")
	public String lastpage(Model model){//上一页
		a=a-1;
		if(a<1)
		{
			a=a+1;
		}
		return "redirect:findLimitC";
	}
	@RequestMapping("/startpageC")
	public String startpage(Model model){//首页
		a=1;
		return "redirect:findLimitC";
	}
	@RequestMapping("/endpageC")
	public String endpage(Model model){//尾页
		a=p.getPageTotal();
		return "redirect:findLimitC";
	}
	@RequestMapping("/findLimitC")
	public String findLimit( Model model){
//			System.out.println("------------admin");
		//单例设计，放置登录传过来的账号
		Admin singleton = Single.getSingleton();
		if(singleton.getAdmin_name()==null){
			return "redirect:login1C";
		}
		model.addAttribute("username",singleton.getAdmin_name());

		//查询总数据
		Admin admin=new Admin();
		int total = adminService.selectCountC(admin);
		p.setPage(a);
		p.setPageSize(p.getPageSize());
		p.setTotal(total);
		int b=(p.getPage()-1)*p.getPageSize();
		p.setPageStart(b);
		//总页数
		int c=(p.getTotal()/p.getPageSize())+1;
		p.setPageTotal(c);

		model.addAttribute("P",p);
		List<Admin> list = adminService.selectLimitC(p);

		model.addAttribute("list",list);
		return "admin_index";
	}

	/*
    退出
     */
	@RequestMapping("/outC")
	public String tiaoC(){

		Admin singleton = Single.getSingleton();
		singleton.setAdmin_name(null);
		return "redirect:/admin/login1C";
	}

	//登录
	@RequestMapping("/loginC")
	public String  login(String name,String pwd,Model model){
		//单例设计，放置登录传过来的账号
		Admin singleton = Single.getSingleton();
		if(name!=null&&name!=""){
			if(pwd!=null&&pwd!=""){
				MD5 md5 = new MD5();
//					Admin admin = adminService.selLogin(name, md5.getMD5ofStr(pwd));
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(name,md5.getMD5ofStr(pwd));
				subject.login(usernamePasswordToken);
				if(subject.isAuthenticated()){
					singleton.setAdmin_name(name);

					model.addAttribute("username",singleton.getAdmin_name());
					return "redirect:findLimitC";
				}else{
					singleton.setAdmin_name("认证失败");
					System.out.println(singleton.getAdmin_name()+"------------认证失败");
					return "redirect:login1C";
				}
			}else{
				singleton.setAdmin_name("密码为空");
				System.out.println(singleton.getAdmin_name()+"------------密码为空");
				return "redirect:login1C";
			}
		}else{
			singleton.setAdmin_name("账号为空");
			System.out.println(singleton.getAdmin_name()+"------------账号为空");
			return "redirect:login1C";
		}
	}
	//注册
	@RequestMapping("/registerC")
	public String  registerC(String name,String pwd,Model model){
		//单例设计，放置注册传过来的账号
		Admin singleton = Single.getSingleton();
		if(name!=null&&name!=""){
			if(pwd!=null&&pwd!=""){
				Admin admin=new Admin();
				admin.setAdmin_name(name);
				List<Admin> admins = adminService.selectLikeC(admin);
				System.out.println(admins+"--------------");
				if (admins.isEmpty()) {
					MD5 md5 = new MD5();
					admin.setAdmin_pwd(md5.getMD5ofStr(pwd));
					admin.setAdmin_auth("ceshi");
					int insert = adminService.insertRc(admin);
					if(insert==0){
						singleton.setAdmin_name("添加失败，网络有问题！");
						return "redirect:register1C";
					}else{
						Admin admin1=Single.getSingleton();
						admin1.setAdmin_name(null);
						return "redirect:login1C";
					}
				} else {

					singleton.setAdmin_name("账户已存在");
					return "redirect:/admin/register1C";
				}
			}else{
				singleton.setAdmin_name("密码为空");

				return "redirect:login1C";
			}
		}else{
			singleton.setAdmin_name("账号为空");

			return "redirect:login1C";
		}
	}
	/*
	login，register页面跳转
	*/
	@RequestMapping("/register1C")
	public String register1(Model model){
		Admin singleton = Single.getSingleton();


		model.addAttribute("errorname",singleton.getAdmin_name());
		return "register";
	}
	@RequestMapping("/login1C")
	public String loginC(Model model){
		Admin singleton = Single.getSingleton();

		model.addAttribute("errorname",singleton.getAdmin_name());
		return "login";
	}
	@RequestMapping("/pay")
	public String findlimit11(Model model){
		Admin singleton = Single.getSingleton();


		model.addAttribute("username",singleton.getAdmin_name());
		return "pay_index";
	}
	/*
	YC
	 */


}
