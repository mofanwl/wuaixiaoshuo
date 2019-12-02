package com.wn.controller;

import com.google.gson.Gson;
import com.wn.pojo.User;
import com.wn.service.UserService;
import com.wn.utils.MD5;
import com.wn.utils.Shortt;
import com.wn.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.junit.runners.Parameterized.Parameters;*/

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/show")
	@ResponseBody
	public List<User> show() {
		List<User> list = userService.selectAll(null);
		return list;
	}

	/*
	 * 跳转。。。。
	 */
	@RequestMapping("/user_list")
	public String type_list(Model model) {
		return "user_list";
	}

	/*
	 * 查询一个用户
	 */
	@RequestMapping("/selectOne")
	public User selectOne(@RequestParam("user_name") String user_name) {
		User user = userService.selectOne(user_name);
		/*
		 * System.out.println(user); Map<String,User> map=new HashMap<>();
		 * map.put("user", user);
		 */
		return user;

	}

	/*
	 * 按手机号查询
	 */
	@RequestMapping("/selectPhone")
	public User selectPhone(@RequestParam("user_phone") String user_phone) {
		System.out.println("接收到的手机号：" + user_phone);
		User user = userService.selectPhone(user_phone);
		return user;
	}

	/*
	 * 注册
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, String> insert(HttpServletRequest request, HttpServletResponse response) {
		String user_name = request.getParameter("user_name");
		String user_pwd = request.getParameter("user_pwd");
		String user_phone = request.getParameter("user_phone");
		String user_yzm = request.getParameter("user_yzm");
		User uuser = userService.selectOne(user_name);
		Map<String, String> map = new HashMap<>();
		if (uuser == null) {
			User user = new User();
			user.setUser_name(user_name);
			MD5 md5 = new MD5();
			user.setUser_pwd(md5.getMD5ofStr(user_pwd));
			user.setUser_phone(user_phone);
			// 短信验证
			HttpSession session = request.getSession();
			String phone_yzm = (String) session.getAttribute("phone_yzm");
			System.out.println("取到的验证码：" + phone_yzm);
			// 验证码正确
			if (user_yzm.equals(phone_yzm)) {
				System.out.println("验证码正确");
				User userp = userService.selectPhone(user_phone);
				if (userp == null) {
					int res = userService.insert(user);
					System.out.println("这是接收到的用户信息" + user);
					System.out.println("返回值" + res);
					// 注册成功
					if (res == 1) {
						map.put("ins_cg", "zcok");
						map.put("提示：", "注册成功");
						// 注册失败
					} else {
						map.put("ins_cg", "zcno");
						map.put("tishi", "注册失败");
					}
				} else {
					map.put("ins_cg", "phoneok");
					map.put("tishi", "手机号已存在");
				}
				return map;
				// 短信验证码错误
			} else {
				System.out.println("验证码错误");
				map.put("ins_cg", "yzmno");
				map.put("tishi", "验证码错误");

			}
			// 用户名重复
		} else {
			map.put("ins_cg", "userno");
			map.put("tishi", "用户名重复");

		}
		return map;
	}

	/*
	 * 短信验证
	 */
	@RequestMapping("short")
	public Map<String, String> duanxin(HttpServletRequest request) {
		String user_phone = request.getParameter("user_phone");
		PrintWriter pw;
		HttpSession session = request.getSession();
		// 发送
		Map<String, String> map = new HashMap<String, String>();
		Gson gson = new Gson();
		List<String> dxht = Shortt.getShort(user_phone);

		String result = dxht.get(0);// 成功回调
		String sjm = dxht.get(1);
		System.out.println(dxht.get(1));
		map = gson.fromJson(result, Map.class);
		if ("操作成功".equals(map.get("reason"))) {

			map.put("short_cg", "ok");
		} else {
			map.put("short_cg", "no");
		}
		session.removeAttribute("phone_yzm");
		session.setAttribute("phone_yzm", sjm);
		// System.out.println(map.get("reason"));

		return map;
	}

	@RequestMapping("/count")
	@ResponseBody
	public int count(User user) {
		int res = userService.selectCount(user);
		return res;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public int delete(String[] empnos) {
		Map<String, String[]> map = new HashMap<>();
		map.put("enos", empnos);
		int res = userService.delete(map);
		return res;
	}

	@RequestMapping("/update")
	@ResponseBody
	public int update(User user) {
		int res = userService.update(user);
		return res;
	}

	/*
	 * 用户名登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request, HttpServletResponse response) {
		String user_name = request.getParameter("user_name");
		String user_pwd = request.getParameter("user_pwd");

		// String nolog = request.getParameter("nolog");
		Map<String, String> map = new HashMap<>();
		MD5 md5 = new MD5();
		user_pwd = md5.getMD5ofStr(user_pwd);

		User user = userService.selectDl(user_name, user_pwd);
		if (user != null) {
			map.put("dlyz", "ok");
			map.put("tishi", "登陆成功");
		} else {
			map.put("dlyz", "no");
			map.put("tishi", "登陆失败");
		}
		return map;

	}

	/*
	 * 手机号登陆
	 */
	@RequestMapping("/logintel")
	@ResponseBody
	public Map<String, String> logintel(HttpServletRequest request, HttpServletResponse response) {
		String user_phone = request.getParameter("user_phone");
		String user_yzm = request.getParameter("user_yzm");
		System.out.println("手机号：" + user_phone);
		System.out.println("接收的验证码：" + user_yzm);
		Map<String, String> map = new HashMap<>();
		HttpSession session = request.getSession();
		String phone_yzm = (String) session.getAttribute("phone_yzm");
		System.out.println("取到的验证码：" + phone_yzm);
		// 判断手机号是否存在
		// if (user != null) {
		// 验证码正确
		if (user_yzm.equals(phone_yzm)) {
			System.out.println("号登陆");
			User user = userService.selectPhone(user_phone);
			System.out.println(user + "----");
			if (user != null) {
				map.put("fh_cg", "yzmnok");
				map.put("tishi", "验证码正确");
			} else {
				map.put("fh_cg", "nophone");
			}
			return map;
		} else {
			System.out.println("cuowu..................");
			map.put("fh_cg", "yzmno");
			map.put("tishi", "验证码错误");
			return map;
		}
		/*
		 * } else { map.put("fh_cg", "nophone"); map.put("tishi", "此手机号未注册"); }
		 */
		// return map;

		/*
		 * String nolog = request.getParameter("nolog"); User user = new User();
		 * user.setUser_phone(user_phone); user.setUser_pwd(user_pwd);
		 * List<User> list = userService.selectAll(user); if (list.size() != 0)
		 * { HttpSession session = request.getSession(); // 将用户信息存入cookie中
		 * Cookie ck = new Cookie(user_phone, user_pwd);// 创建Cookie if
		 * ("1".equals(nolog))// 1个月 ck.setMaxAge(30 * 24 * 60 * 60); else if
		 * ("3".equals(nolog)) ck.setMaxAge(30 * 24 * 60 * 60 * 3); else if
		 * ("6".equals(nolog)) ck.setMaxAge(30 * 24 * 60 * 60 * 6);
		 * response.addCookie(ck);// 添加cookie // 将用户信息记录在session中
		 * session.setAttribute("userinfo", user); return 1; } else if
		 * (list.size() == 0) { request.setAttribute("err", "用户信息有误，请重新填写！");
		 * request.setAttribute("user_name", user_phone); } return 0;
		 */
	}

	/*
	 * 找回密码 修改密码
	 */
	@RequestMapping("/zhaohpwd")
	public Map<String, String> zhaohpwd(HttpServletRequest request) {
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		Map<String, String> map = new HashMap<>();
		User user = new User();
		user.setUser_id(Integer.valueOf(user_id));
		user.setUser_name(user_name);
		MD5 md5 = new MD5();
		user_pwd = md5.getMD5ofStr(user_pwd);
		user.setUser_pwd(user_pwd);
		int res = userService.update(user);
		if (res == 1) {
			map.put("fanhui", "ok");
			map.put("tishi", "修改成功");
		} else {
			map.put("fanhui", "no");
			map.put("tishi", "修改失败");
		}
		return map;
	}

	/*
	 * 找回密码 手机号查询用户名,返回用户名
	 */
	@RequestMapping("/sel_phone")
	public Map<String, String> sel_phone(HttpServletRequest request) {
		String user_phone = request.getParameter("user_phone");
		String user_yzm = request.getParameter("user_yzm");
		HttpSession session = request.getSession();
		String phone_yzm = (String) session.getAttribute("phone_yzm");
		Map<String, String> map = new HashMap<>();
		// 验证成功
		if (user_yzm.equals(phone_yzm)) {
			User user = userService.selectPhone(user_phone);
			map.put("user_name", user.getUser_name());
			map.put("user_id", String.valueOf(user.getUser_id()));
			map.put("tishi", "yzmok");
		} else {
			map.put("tishi", "yzmno");
			map.put("fh_cg", "验证码错误");
		}
		return map;
	}

	@RequestMapping("/ceshi")
	public List<User> selectAll() {
		List<User> list = userService.selectAll(null);
		return list;
	}

	/*
	 * 后台接口
	 */

	@RequestMapping("/showUser")
	@ResponseBody
	public Msg<User> showUser(int page, int rows) {
		int count = userService.selectCount(null);
		List<User> user = userService.selectAllUser(page, rows);
		Msg<User> msg = new Msg<>();
		msg.setTotal(count);
		msg.setRows(user);
		return msg;
	}

	@RequestMapping("/all")
	public String test1(Model model) {
		return "user";
	}

	@RequestMapping("/javaupdate")
	@ResponseBody
	public String javaupdate(User user) {
		int res = userService.update(user);
		if (res == 1) {
			return "{\"success\":\"success\"}";
		} else {

			return "\"errorMsg\":\"修改失败\"";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(User user) {
		int res = userService.insert(user);
		System.out.println(res + "+++++++++++++++++++++++++++");
		if (res == 1) {
			return "{\"success\":\"ok\"}";
		} else {
			return "\"errorMsg\":\"添加失败\"";
		}
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public String del(String id) {
		int res = userService.del(Integer.valueOf(id));
		if (res == 1) {
			return "{\"success\":\"ok\"}";
		} else {
			return "{\"error\":\"no ok\",\"errorMsg\":\"删除失败\"}";
		}
	}

}
