package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	//블로그-메인페이지 이동(회원 아이디 필요 -> 각자의 블로그 정보 표시)
	@RequestMapping({"/{userId}","/{userId}/{pathNo1}","/{userId}/{pathNo1}/{pathNo2}"})
	public String blog(@PathVariable Optional<String> userId,
					   @PathVariable Optional<Long> pathNo1,
					   @PathVariable Optional<Long> pathNo2,
					   Model model) {
		
		//회원 아이디
		String id = "";
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		//blog-main에서 필요한 정보 = 블로그 타이틀, 카테고리 리스트, 카테고리 별 게시물 리스트, 기본 표시할 게시물 제목 및 내용 
		
		if(userId.isPresent()) {
			id = userId.get();
			//해당 카테고리 내 해당 게시물 표시
			if(pathNo2.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = pathNo2.get();
			}
			//해당 카테고리 내 max번 게시물 표시
			else if(pathNo1.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = 1L; //max로 변경
			}
			//max번 카테고리 내 max번 게시물
			else {
				categoryNo = 1L; //max로 변경
				postNo = 1L; //max로 변경
			}
		}
		
		List<CategoryVO> categoryList = blogService.getCategoryList(id);
		List<PostVO> postList = blogService.getPostList(categoryNo);
		
		//[게시물 하나 가져오기] getOnePost();
		
		//리스트 외 블로그 정보 추출
		BlogVO bvo = blogService.getInfo(id);
		
		System.out.println(bvo);
		
		//현재 접속한 블로그 회원 아이디
		model.addAttribute("userId", id);
		model.addAttribute("bvo", bvo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		
		//path로 입력된 회원 관련 블로그 정보 추출
		
		
		return "blog/blog-main";
	}
	
	//블로그-관리 페이지 이동
	@RequestMapping("/{userId}/admin/basic")
	public String basic(@PathVariable Optional<String> userId,
						   Model model) {
		
		String id = "";
		
		if(userId.isPresent()) {
			id=userId.get();
		}
		
		BlogVO bvo = blogService.getInfo(id);
		
		model.addAttribute("userId", id);
		model.addAttribute("bvo", bvo);
		
		return "blog/blog-admin-basic";
	}
	
	//블로그-관리 정보 변경
	@RequestMapping("/{userId}/admin/update")
	public String update(@PathVariable Optional<String> userId,
						 @RequestParam (value="title", required=true, defaultValue="") String title,
						 @RequestParam (value="logo") MultipartFile logo) {
		
		String id = "";
		
		if(userId.isPresent()) {
			id=userId.get();
		}
		
		String url = userService.restore(logo);
		
		BlogVO bvo = new BlogVO();
		bvo.setUserId(id);
		bvo.setTitle(title);
		bvo.setLogo(url);
		
		boolean flag = blogService.update(bvo);
		
		return "redirect:/blog/"+id;
	}
	
	//블로그-카테고리 페이지 이동
	@RequestMapping("/{userId}/admin/category")
	public String category(@PathVariable Optional<String> userId,
						   Model model) {
		
		String id = "";
		
		if(userId.isPresent()) {
			id=userId.get();
		}
		
		List<CategoryVO> categoryList = blogService.getCategoryList(id);

		model.addAttribute("userId", id);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-category";
	}
	
}
