package com.wn.controller;

import com.google.gson.Gson;
import com.wn.pojo.*;
import com.wn.service.UserService;
import com.wn.utils.MD5;
import com.wn.utils.Shortt;
import com.wn.utils.Tools;
import com.wn.utils.UploadUtils;
import com.wn.vo.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.tools.Tool;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.junit.runners.Parameterized.Parameters;*/

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	//七牛
	@Value("${qiniu.url}")
	private String url;
	@Autowired
	private UploadUtils uploadUtils;

	/*
     用户的余额查询
	 */
	@RequestMapping("selMoney/{user_id}")
	@ResponseBody
	public Map<String,String> selMoney(@PathVariable("user_id")Integer user_id){
		User user = userService.selectUserById(user_id);
		Map<String,String> map=new HashMap<>();
		map.put("user_vip_time",Tools.getVipTime(user.getUser_vip_time()));
		map.put("user_total_mount",user.getUser_total_mount());
		return map;
	}

	/*
	vip充值
	 */
	@RequestMapping("/tovip/{user_id}/{date}")
	@ResponseBody
	public Map<String,String> tovip(@PathVariable("user_id")Integer user_id,@PathVariable("date") String date){
		Float mount=0f;
		Integer time=0;
        if("1个月".equals(date)){
			mount=15.00f;
			time=30;
		}else if("3个月".equals(date)){
			mount=40.00f;
			time=90;
		}else if("半年".equals(date)){
			mount=70.00f;
			time=180;
		}else if("一年".equals(date)){
			mount=120.00f;
			time=365;
		}
		Map<String,String> map=new HashMap<>();
		map.put("msg","no");
		//查询余额
		User user = userService.selectUserById(user_id);
		String user_total_mount = user.getUser_total_mount();
		if((Float.valueOf(user_total_mount)-mount)>=0){
			System.out.println("会员余额ok");
			User user1 = new User();
			user1.setUser_id(user_id);
			user1.setUser_total_mount(String.valueOf(Float.valueOf(user_total_mount)-mount));
			//查询用户是否为VIp
			String aLong = userService.selByVipTime(user_id);
			System.out.println(aLong);
			String vip = Tools.getVip(aLong);
			if(vip.equals("vip")){
				user1.setUser_vip_time(String.valueOf(Tools.toVip2(time,aLong)));
				int update = userService.update(user1);
				if(update==1){
					System.out.println("会员充值成功");
					map.put("msg","ok");
				}
			}else{
				//时间
				user1.setUser_vip_time(String.valueOf(Tools.toVip(3)));
				int update = userService.update(user1);
				if(update==1){
					System.out.println("会员充值成功");
					map.put("msg","ok");
				}
			}
		}else{
			map.put("msg","mountno");
		}
		return map;
	}
	/*
	用户头像上传
	 */
	@RequestMapping(value = "/touxiang")
	@ResponseBody
	public Port touxiang(@RequestParam("file") MultipartFile file,@RequestParam("idid")Integer id){
		System.out.println("这是接收到的id:"+id);
		System.out.println("touxiang");
		Port port = new Port();
		try {
			//上传
			String upload = uploadUtils.upload(file);//上传后返回文件名
			System.out.println(upload);
			User user = new User();
			user.setUser_id(id);
			user.setUser_portrait(url+upload);
			//修改
			int update = userService.update(user);
			if(update==1){
				System.out.println("ok");

				port.setMsg("ok");
				User user1 = userService.selectUserById(id);

				port.setUser_portrait(user1.getUser_portrait());
			}else{
				System.out.println("no");
				port.setMsg("no");
			}
			return port;
		}catch (Exception e){
			port.setMsg("no");
		}
		return port;
	}
	/*
	查询会员到期时间
	 */
	@RequestMapping("/selByVipTime")
	@ResponseBody
	public Map<String,String> selByVipTime() {
		Map<String,String> map=new HashMap<>();
       // String aLong = userService.selByVipTime(11);
       // String time = Tools.getTime(Long.valueOf(aLong));

        //加入用户到期时间
       /* String s = Tools.toVip(30);
        User user = new User();
        user.setUser_id(11);
        user.setUser_vip_time(s);
        userService.insertVipTime(user);*/

        //查询用户是否为VIp
        String aLong = userService.selByVipTime(15);
		System.out.println(aLong);
		String vip = Tools.getVip(aLong);
        System.out.println(vip);
		map.put("vip",vip);


        //查询用户到期时间
        String aLong1 = userService.selByVipTime(15);
         String time = Tools.getVipTime(aLong1);
		map.put("viptime",time);


        return map;
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
	@ResponseBody
	public User selectOne(@RequestParam("user_name") String user_name) {
		User user = userService.selectOne(user_name);
		/*
		 */
		return user;

	}

	/*
	 * 按手机号查询
	 */
	@RequestMapping("/selectPhone")
	@ResponseBody
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
	public Map<String, String> insert(@RequestBody User users,HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
		System.out.println(users+"-=-=-=-=-=-=-=-=-================-=-==-=--==-=-=");
		String user_name = users.getUser_name();
		String user_pwd = users.getUser_pwd();
		String user_phone = users.getUser_phone();
		String user_yzm = users.getUser_yzm();
        //判断手机号是否正确
        if(users.getUser_phone().length()==11){
            //查询是否有这个用户
            User uuser = userService.selectOne(user_name);

            if (uuser == null) {
                User user = new User();
                user.setUser_name(user_name);
                MD5 md5 = new MD5();
                user.setUser_pwd(md5.getMD5ofStr(user_pwd));
				user.setGroup_id(1);
				user.setUser_nick_name("魔鬼");
				user.setUser_reg_time(Tools.getTime());
				user.setUser_portrait("http://q1bjiy8xr.bkt.clouddn.com/default_user.0.2.png");
                user.setUser_phone(user_phone);
                // 短信验证
                HttpSession session = request.getSession();
                String phone_yzm = (String) session.getAttribute("phone_yzm");
                System.out.println("取到的验证码：" + phone_yzm);
                // 验证码正确
               // if (true) {
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
        }else{
            map.put("ins_cg", "zcno");
            map.put("tishi", "注册失败");
        }

		return map;
	}

	/*
	 * 短信验证
	 */
	@RequestMapping("short")
	@ResponseBody
	public Map<String, String> duanxin(@RequestBody User user, HttpServletRequest request) {
		System.out.println("手机号short：");
		String user_phone = user.getUser_phone();
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
	@RequestMapping("/show")
	@ResponseBody
	public List<User> show() {
		List<User> list = userService.selectAll(null);
		return list;
	}

	/*
	 * 用户名登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public cookie login(@RequestBody User loginuser) {
		cookie cookies = new cookie();
		cookies.setLogin("loginno");
		String user_name = loginuser.getUser_name();
		String user_pwd = loginuser.getUser_pwd();
		System.out.println(loginuser+"----------==================================");

		Map<String, String> map = new HashMap<>();
		MD5 md5 = new MD5();
		user_pwd = md5.getMD5ofStr(user_pwd);
		//查询是否有这个用户
		User uuser = userService.selectOne(user_name);
		if(uuser!=null){
			User usera = userService.selectDl(user_name, user_pwd);
			if(usera!=null){
				cookies.setTishi("登陆成功");
				cookies.setDlyz("ok");
				cookies.setLogin("loginyes");
				cookies.setUser_name(usera.getUser_name());
				cookies.setUser_id(usera.getUser_id());
				//会员判断
				String vip = Tools.getVip(uuser.getUser_vip_time());
				if("vip".equals(vip)){
					cookies.setUser_vip_time(Tools.getVipTime(usera.getUser_vip_time()));
				}else {
					cookies.setUser_vip_time("-- --");
				}

				cookies.setUser_portrait(usera.getUser_portrait());
				cookies.setUser_total_mount(usera.getUser_total_mount());
				String aLong = userService.selByVipTime(uuser.getUser_id());
				cookies.setVip( Tools.getVip(aLong));
			}else{
				cookies.setTishi("登陆失败");
				cookies.setDlyz("no");
				System.out.println("登陆失败");
			}
			//shiro
          /*  try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user_name, user_pwd);
                subject.login(usernamePasswordToken);
                //登陆成功
                if(subject.isAuthenticated()){
					cookies.setTishi("登陆成功");
					cookies.setDlyz("ok");
					cookies.setLogin("loginyes");
					String aLong = userService.selByVipTime(uuser.getUser_id());
					cookies.setVip( Tools.getVip(aLong));
                }
            }catch (Exception e){
				cookies.setTishi("登陆失败");
				cookies.setDlyz("no");
                System.out.println("登陆失败");
                System.out.println(e.getMessage());
            }*/
		}else{
			cookies.setTishi("此用户不存在");
			cookies.setDlyz("userno");
		}
		//密码验证

		return cookies;

	}

	/*
	 * 手机号登陆
	 */
	@RequestMapping("/logintel")
	@ResponseBody
	public cookie logintel(@RequestBody User users, HttpServletRequest request, HttpServletResponse response) {
		cookie cookies = new cookie();
		cookies.setLogin("loginno");
		String user_phone = users.getUser_phone();
		String user_yzm = users.getUser_yzm();
		System.out.println("手机号：" + user_phone);
		System.out.println("接收的验证码：" + user_yzm);
		Map<String, String> map = new HashMap<>();
		HttpSession session = request.getSession();
		String phone_yzm = (String) session.getAttribute("phone_yzm");
		System.out.println("取到的验证码：" + phone_yzm);
		// 判断手机号是否存在
		User user = userService.selectPhone(user_phone);
		if (user != null) {
			if (user_yzm.equals(phone_yzm)) {
			//if (true) {
				cookies.setTishi("登陆成功");
				cookies.setLogin("loginyes");
				String aLong = userService.selByVipTime(user.getUser_id());
				cookies.setVip( Tools.getVip(aLong));
				cookies.setDlyz("yzmnok");
				cookies.setTishi("验证码正确");
			} else {
				System.out.println("cuowu..................");
				map.put("fh_cg", "yzmno");
				map.put("tishi", "验证码错误");
				cookies.setDlyz("yzmno");
				cookies.setTishi("验证码错误");
			}
		} else {
			map.put("fh_cg", "nophone");
			map.put("tishi", "手机号不存在");
			cookies.setDlyz("nophone");
			cookies.setTishi("手机号不存在");
		}
		return cookies;
		// if (user != null) {
		// 验证码正确

		/*
		 */

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
	 * 找回密码 修改密码==》三
	 */
	@RequestMapping("/updatepwd")
	@ResponseBody
	public Map<String, String> zhaohpwd(@RequestBody User users,HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		//判断手机号是否存在
		User userphone = userService.selectPhone(users.getUser_phone());
		if(userphone!=null){
            //判断验证码是否正确
            HttpSession session = request.getSession();
            String phone_yzm = (String) session.getAttribute("phone_yzm");
            // 验证成功
            if (users.getUser_yzm().equals(phone_yzm)) {
            //if (true) {
                MD5 md5 = new MD5();
                String user_pwd = md5.getMD5ofStr(users.getUser_pwd());
                users.setUser_id(userphone.getUser_id());

                users.setUser_pwd(user_pwd);
                int res = userService.update(users);
                if (res == 1) {
                    map.put("fanui", "ok");
                    map.put("tishi", "修改成功");
                } else {
                    map.put("fanui", "no");
                    map.put("tishi", "修改失败");
                }
            } else {
                map.put("fanui", "yzmno");
                map.put("tishi", "验证码错误");
            }
		}else{
			map.put("fanui", "phoneno");
			map.put("tishi", "此手机号未注册");
		}
		return map;
	}

	/*
	 * 找回密码 手机号查询用户名,返回用户名
	 */
	@RequestMapping("/sel_phone")
	@ResponseBody
	public Map<String, String> sel_phone(@RequestBody User users, HttpServletRequest request) {
		String user_phone =users.getUser_phone();
		String user_yzm = users.getUser_yzm();
		HttpSession session = request.getSession();
		String phone_yzm = (String) session.getAttribute("phone_yzm");
		Map<String, String> map = new HashMap<>();
		//判断手机号是否存在
		User userphone = userService.selectPhone(user_phone);
		if(userphone!=null){
			// 验证成功
			if (user_yzm.equals(phone_yzm)) {
				User user = userService.selectPhone(user_phone);
				map.put("user_name", user.getUser_name());
				map.put("user_id", String.valueOf(user.getUser_id()));
				map.put("tishi", "yzmok");
				map.put("user_name",userphone.getUser_name());
			} else {
				map.put("tishi", "yzmno");
				map.put("fh_cg", "验证码错误");
			}
		}else{
			map.put("tishi", "phoneno");
			map.put("fh_cg", "此手机号未注册");
		}


		return map;
	}

	@RequestMapping("/ceshi")
	@ResponseBody
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
	/*
YC
 */
	/*
            删除,改变状态         */
	@RequiresPermissions(value = "user_delete")
	@RequestMapping("/deleteOneC")
	public String delete1(Integer id) {
		User user=new User();
		user.setUser_id(id);

		user.setUser_status(2);
		int update = userService.update(user);
		if(update!=0){
			return "redirect:/user/findLimitC";
		}else{
			return "error";
		}

	}

	/*
新增
 */
	@RequiresPermissions(value = "user_insert")
	@RequestMapping("/insert1C")
	public String insert1(User user)  {
		//查询是否有对应账户
		User user1=new User();
		user1.setUser_name(user.getUser_name());
		List<User> list = userService.selectAll(user1);

		if (list.size() == 0) {
			//没有，则新增数据
			int res = userService.insertC(user);

			return "redirect:/user/findLimitC";
		}else{
			return "error";
		}

	}
	@RequiresPermissions(value = "user_insert")
	@RequestMapping("/saveC")
	public String save() {
		return "user_save";
	}
	/*
    修改
     */
	@RequiresPermissions(value = "user_update")
	@RequestMapping("/update1C")
	public String update1(User user,Model model) {

		int res = userService.update(user);

		if(res!=0){
			return "redirect:/user/findLimitC";
		}else{
			return "error";
		}
	}
	@RequestMapping("/updateC")
	public String update(Integer id,Model model) {
		User user=new User();
//		user.setUser_id(id);
		List<User> users = userService.selectAll(user);
		for(User a:users){
			if(a.getUser_id()==id){
				user=a;
			}
		}
		model.addAttribute("user",user);
		return "user_update";
	}

	/*
    模糊查询！
     */
	@RequestMapping("/findLikeC")
	public String findLike(User user,Model model){
		Admin singleton = Single.getSingleton();

		model.addAttribute("username",singleton.getAdmin_name());

		List<User> users = userService.selectLikeC(user);
		model.addAttribute("users",users);
		return "user_index";

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
	public String findLimit(Model model){
		Admin singleton = Single.getSingleton();
		if(singleton.getAdmin_name()==null){
			return "redirect:/admin/login1C";
		}
		;
		model.addAttribute("username",singleton.getAdmin_name());

		//查询总数据
		User user=new User();
		int total = userService.selectCountC(user);
		p.setPage(a);
		p.setPageSize(p.getPageSize());
		p.setTotal(total);
		int b=(p.getPage()-1)*p.getPageSize();
		p.setPageStart(b);
		//总页数
		int c=(p.getTotal()/p.getPageSize())+1;
		p.setPageTotal(c);

		model.addAttribute("P",p);
		List<User> list = userService.selectLimitC(p);

		model.addAttribute("list",list);
		return "user_index";
	}


	/*
	YC
	 */

}
