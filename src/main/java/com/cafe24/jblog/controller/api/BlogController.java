package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.CategoryVO;

@Controller("blogAPIController")
@RequestMapping("/blog/api")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@RequestParam(value="no", required=true, defaultValue="0") Long no) {
		//카테고리 내 모든 게시물 먼저 삭제
		boolean flag = blogService.deletePost(no);
		if(flag) {
			flag = blogService.deleteCategory(no);
		}
		//JSON 형태로 변형하여 전송
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert(@RequestParam(value="subject", required=true, defaultValue="") String subject,
							 @RequestParam(value="description", required=true, defaultValue="") String description,
							 @RequestParam(value="userId", required=true, defaultValue="") String userId) {
		
		CategoryVO cvo = new CategoryVO();
		cvo.setSubject(subject);
		cvo.setDescription(description);
		cvo.setUserId(userId);
		
		boolean flag = blogService.insertCategory(cvo);
		
		//JSON 형태로 변형하여 전송
		JSONResult result = JSONResult.success(flag);
		return result;
	}
	
}