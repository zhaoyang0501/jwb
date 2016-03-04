package com.pzy.controller.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pzy.entity.Category;
import com.pzy.entity.Expert;
import com.pzy.entity.Lab;
import com.pzy.entity.News;
import com.pzy.entity.Paper;
import com.pzy.entity.Patent;
import com.pzy.entity.Project;
import com.pzy.entity.Report;
import com.pzy.entity.User;
import com.pzy.service.AttenceService;
import com.pzy.service.CategoryService;
import com.pzy.service.ExpertService;
import com.pzy.service.GradeService;
import com.pzy.service.LabService;
import com.pzy.service.NewsService;
import com.pzy.service.PaperService;
import com.pzy.service.PatentService;
import com.pzy.service.ProjectService;
import com.pzy.service.ReportService;
import com.pzy.service.ScoreService;
import com.pzy.service.TeacherService;
import com.pzy.service.TimetableService;
import com.pzy.service.UserService;
import com.pzy.service.WorkService;
/***
 * 前台，首页各种连接登陆等
 * @author qq:263608237
 *
 */
@Controller
@RequestMapping("/")
public class FrontController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private TimetableService timetableService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private WorkService workService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private AttenceService attenceService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ExpertService expertService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private LabService labService;
	/***
	 * 跳转到首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		List<News> news= newsService.findAll();
		model.addAttribute("news", news.size()==0?null:news.get(0));
		return "index";
	}
	
	
	
	/***
	 * 注册连接
	 * @return
	 */
	@RequestMapping("register")
	public String register() {
		return "register";
	}
	/***
	 * 点击注册
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("doregister")
	public String register(User user,Model model) {
		user.setCreateDate(new Date());
		model.addAttribute("tip","注册成功请登录！");
		userService.save(user);
		return "login";
	}
	/***
	 * 登陆连接
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	/***
	 * 退出操作清空缓存等操作
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping("loginout")
	public String loginout(HttpSession httpSession,Model model) {
		httpSession.removeAttribute("user");
		List<News> news= newsService.findAll();
		model.addAttribute("news", news.size()==0?null:news.get(0));
		return "index";
	}
	
	/***
	 * 报名连接
	 * @param model
	 * @return
	 */
	@RequestMapping("report")
	public String report(Model model) {
		model.addAttribute("grades", gradeService.findAll());
		return "report";
	}
	/***
	 * 点击报名
	 * @param model
	 * @param report
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("doreport")
	public String doreport(Model model,Report report,HttpSession httpSession) {
		report.setCreateDate(new Date());
		report.setUser((User)httpSession.getAttribute("user"));
		reportService.save(report);
		model.addAttribute("tip","报名成功!");
		model.addAttribute("grades", gradeService.findAll());
		return "report";
	}
	/***
	 * 教师风采
	 * @param model
	 * @return
	 */
	@RequestMapping("teacher")
	public String teacher(Model model) {
		model.addAttribute("teachers",teacherService.findAll());
		return "teacher";
	}
	
	/***
	 * 执行登陆动作
	 * @param user
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping("dologin")
	public String dologin(User user,HttpSession httpSession,Model model) {
		User loginuser=userService.login(user.getUsername(), user.getPassword());
    	if(loginuser!=null){
    		List<News> news= newsService.findAll();
    		model.addAttribute("news", news.size()==0?null:news.get(0));
    		httpSession.setAttribute("user", loginuser);
            return "index"; 
    	}
    	else{
    		httpSession.removeAttribute("user");
    		model.addAttribute("tip","登陆失败 不存在此用户名或密码!");
    		return "login";
    	}
	}
	
	/***
	 *科研项目
	 * @param model
	 * @return
	 */
	@RequestMapping("project")
	public String project(Model model,String key,Long cid) {
		model.addAttribute("cagegorys", categoryService.findByType("20"));
		Category category=null;
		if(cid!=null)
			category=categoryService.find(cid);
		List<Project> list= projectService.findAll(1, 20, key,category).getContent();
		model.addAttribute("projects",list);
		return "project";
	}
	
	@RequestMapping("viewproject")
	public String viewproject(Long id,Model model) {
		model.addAttribute("bean",projectService.find(id));
		return "viewproject";
	}
	
	
	/***
	 *专家学者
	 * @param model
	 * @return
	 */
	@RequestMapping("expert")
	public String expert(Model model,String key,Long cid) {
		model.addAttribute("cagegorys", categoryService.findByType("10"));
		Category category=null;
		if(cid!=null)
			category=categoryService.find(cid);
		List<Expert> list= expertService.findAll(1, 20, key,category).getContent();
		model.addAttribute("experts",list);
		return "expert";
	}
	
	@RequestMapping("viewexpert")
	public String viewexpert(Long id,Model model) {
		model.addAttribute("bean",expertService.find(id));
		return "viewexpert";
	}
	
	/***
	 *文献资料
	 * @param model
	 * @return
	 */
	@RequestMapping("paper")
	public String paper(Model model,String key,Long cid) {
		model.addAttribute("cagegorys", categoryService.findByType("30"));
		Category category=null;
		if(cid!=null)
			category=categoryService.find(cid);
		List<Paper> list= paperService.findAll(1, 20, key,category).getContent();
		model.addAttribute("papers",list);
		return "paper";
	}
	
	@RequestMapping("viewpaper")
	public String viewpaper(Long id,Model model) {
		model.addAttribute("bean",paperService.find(id));
		return "viewpaper";
	}
	
	/***
	 *专利文献
	 * @param model
	 * @return
	 */
	@RequestMapping("patent")
	public String patent(Model model,String key,Long cid) {
		model.addAttribute("cagegorys", categoryService.findByType("40"));
		Category category=null;
		if(cid!=null)
			category=categoryService.find(cid);
		List<Patent> list= patentService.findAll(1, 20, key,category).getContent();
		model.addAttribute("patents",list);
		return "patent";
	}
	
	@RequestMapping("viewpatent")
	public String viewpatent(Long id,Model model) {
		model.addAttribute("bean",patentService.find(id));
		return "viewpatent";
	}
	
	
	@RequestMapping("lab")
	public String lab(Model model,String key,Long cid) {
		model.addAttribute("cagegorys", categoryService.findByType("50"));
		Category category=null;
		if(cid!=null)
			category=categoryService.find(cid);
		List<Lab> list= labService.findAll(1, 20, key,category).getContent();
		model.addAttribute("labs",list);
		return "lab";
	}
	
	@RequestMapping("viewlab")
	public String viewlab(Long id,Model model) {
		model.addAttribute("bean",labService.find(id));
		return "viewlab";
	}
}

