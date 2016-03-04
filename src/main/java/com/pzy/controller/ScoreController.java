package com.pzy.controller;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzy.entity.Score;
import com.pzy.service.CategoryService;
import com.pzy.service.ScoreService;
import com.pzy.service.UserService;
/***
 * 成绩管理
 * @author panchaoyang
 *qq 263608237
 */
@Controller
@RequestMapping("/admin/score")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("index")
	public String index(Model model) {
			model.addAttribute("users", userService.findAll());
			model.addAttribute("categorys", categoryService.findAll());
      		return "admin/score/index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value = "sEcho", defaultValue = "1") int sEcho,
			@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", defaultValue = "10") int iDisplayLength, String scorename
			) throws ParseException {
		int pageNumber = (int) (iDisplayStart / iDisplayLength) + 1;
		int pageSize = iDisplayLength;
		Page<Score> scores = scoreService.findAll(pageNumber, pageSize, scorename);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aaData", scores.getContent());
		map.put("iTotalRecords", scores.getTotalElements());
		map.put("iTotalDisplayRecords", scores.getTotalElements());
		map.put("sEcho", sEcho);
		return map;
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Score score) {
		score.setCreateDate(new Date());
		scoreService.save(score);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("msg", "保存成功");
		return map;
	}
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, Object> update(Score score) {
		scoreService.save(score);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("msg", "保存成功");
		return map;
	}
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			scoreService.delete(id);
			map.put("state", "success");
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("state", "error");
			map.put("msg", "删除失败，外键约束");
		}
        return map;
	}

	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable Long id ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("object", scoreService.find(id));
		map.put("state", "success");
		map.put("msg", "成功");
		return map;
	}
}
