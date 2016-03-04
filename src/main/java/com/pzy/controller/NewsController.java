package com.pzy.controller;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzy.entity.News;
import com.pzy.service.NewsService;
/***发布公告到首页
 * @author panchaoyang
 * qq:263608237
 */
@Controller
@RequestMapping("/admin/news")
public class NewsController {
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "admin/news/create";
	}
	@RequestMapping("index")
	public String index() {
		return "admin/news/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(News news,Model model) {
		news.setCreateDate(new Date());
		newsService.save(news);
		model.addAttribute("tip","发布成功");
		return "admin/news/create";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value = "sEcho", defaultValue = "1") int sEcho,
			@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", defaultValue = "10") int iDisplayLength, String username
			) throws ParseException {
		int pageNumber = (int) (iDisplayStart / iDisplayLength) + 1;
		int pageSize = iDisplayLength;
		Page<News> users = newsService.findAll(pageNumber, pageSize, username);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aaData", users.getContent());
		map.put("iTotalRecords", users.getTotalElements());
		map.put("iTotalDisplayRecords", users.getTotalElements());
		map.put("sEcho", sEcho);
		return map;
	}
	
}
