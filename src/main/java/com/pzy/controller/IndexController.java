package com.pzy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pzy.entity.News;
import com.pzy.entity.User;
import com.pzy.service.NewsService;
import com.pzy.service.TeacherService;
import com.pzy.service.UserService;
/***
 * 后台首页，处理后台登录验证权限等操作
 * @author qq:263608237
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private TeacherService teacherService;
	@RequestMapping("center/index")
	public String center() {
		return "admin/center/index";
	}
	@RequestMapping("center/docenter")
	public String docenter(User user,Model model,HttpSession httpSession) {
		User newUser=userService.find(user.getId());
		newUser.setTel(user.getTel());
		newUser.setName(user.getName());
		userService.save(newUser);
		httpSession.setAttribute("adminuser", userService.find(user.getId()));
		model.addAttribute("tip","修改成功");
		return "admin/center/index";
	}
	
	
	@RequestMapping("center/docenterpassword")
	public String docenterpassword(String oldpassword,String newpassword,String newpasswordtwo,
			Model model,HttpSession httpSession) {
		User user=(User)httpSession.getAttribute("adminuser");
		if(!user.getPassword().equals(oldpassword)){
			model.addAttribute("tip","旧密码不正确");
			return "admin/center/index";
		}
		if(newpassword==null||newpasswordtwo==null){
			model.addAttribute("tip","新密码不能为空");
			return "admin/center/index";
		}
		if(!newpassword.equals(newpasswordtwo)){
			model.addAttribute("tip","两次输入密码不正确");
			return "admin/center/index";
		}
		user.setPassword(newpassword);
		userService.save(user);
		httpSession.setAttribute("adminuser", userService.find(user.getId()));
		model.addAttribute("tip","修改成功");
		return "admin/center/index";
	}
	
	
	@RequestMapping("index")
	public String index() {
		return "admin/login";
	}
	@RequestMapping("login")
	public String login() {
		return "admin/login";
	}
	@RequestMapping("loginout")
	public String loginout(HttpSession httpSession) {
		httpSession.setAttribute("adminuser", null);
		return "admin/login";
	}
	@RequestMapping("gologin")
	public String gologin(HttpSession httpSession,String userName,String password,Model model)  {
		User user=userService.login(userName, password);
		List<News> list=newsService.findAll();
		model.addAttribute("usernum",userService.findAll().size());
		model.addAttribute("num1",teacherService.findAll().size());
    	if("admin".equals(userName)&&"123456".equals(password)){
    		User admin=new User();  
    		admin.setUsername("admin");
    		admin.setPassword("123456");
    		admin.setName("超级管理员");
    		
    		
    		model.addAttribute("news",list.size()==0?new News():list.get(0));
    		httpSession.setAttribute("adminuser", admin);
    		return "admin/index";
    	}
    	if("work".equals(userName)&&"123456qwe".equals(password)){
    		User admin=new User();  
    		admin.setUsername("work");
    		admin.setPassword("123456");
    		admin.setName("企业账号");
    		
    		
    		model.addAttribute("news",list.size()==0?new News():list.get(0));
    		httpSession.setAttribute("adminuser", admin);
    		return "admin/index";
    	}
    	else if(user!=null){
    		httpSession.setAttribute("adminuser", user);
    		return "admin/index";
    	}
    	else{
    		httpSession.setAttribute("adminuser", null);
    		model.addAttribute("tip","登陆失败 不存在此用户名或密码!");
    		return "admin/login";
    	}
    	
	}
}

