package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDAO;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;

@Service
public class BlogService {

	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private BlogDAO blogDao;
	
	//post 페이징 처리
	public Map<String, Object> getPostList(Long categoryNo, int pageNum){
		
		///현재 페이지
		int currentPage = pageNum;
		
		//한 페이지에 10개의 게시물 표시
		int pageSize = 5;
		int pageBlock = 3;
		
		//한 페이지의 시작행, 끝행
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
				//게시물 리스트 반환할 배열
		List<PostVO> list = null;

		int count = 0;
		//카테고리 내 총 게시물 수
		count = blogDao.getCount(categoryNo);
		int pageCount = count/pageSize+(count%pageSize==0? 0:1);
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		
		System.out.println(pageCount);
		System.out.println(endPage);
		
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		

		List<PostVO> postList = blogDao.getPostList(categoryNo, startRow-1, pageSize);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postList", postList);
		map.put("currentPage", currentPage);
		map.put("startPage", startPage);
		map.put("pageBlock", pageBlock);
		map.put("pageCount", pageCount);
		map.put("endPage", endPage);
		map.put("count", count);
		
		return map;
	}
	
	//blog-main
	public List<CategoryVO> getCategoryList(String id) {
		return blogDao.getCategoryList(id);
	}
	
	//blog-main
	public BlogVO getInfo(String id) {
		return blogDao.getInfo(id);
	}
	
	//blog-main
	public Long getOnePost(Long categoryNo) {
		return blogDao.getOnePost(categoryNo);
	}
	
	//blog-main
	public PostVO getOne(Long categoryNo, Long postNo) {
		return blogDao.getOne(categoryNo, postNo);
	}
	
	//blog-main
	public Long getOneCategory(String id) {
		return blogDao.getOneCategory(id);
	}
	
	//blog-main
	public boolean update(BlogVO bvo) {
		return blogDao.update(bvo);
	}
	
	//blog-admin-category
	public boolean insertCategory(CategoryVO cvo) {
		return blogDao.insertCategory(cvo);
	}
	
	//blog-admin-category
	public boolean insertPost(PostVO pvo) {
		return blogDao.insertPost(pvo);
	}
	
	//blog-admin-category
	public boolean deletePost(Long categoryNo) {
		return blogDao.deletePost(categoryNo);
	}
	
	//blog-admin-category
	public boolean deleteCategory(Long no) {
		return blogDao.deleteCategory(no);
	}
	
}
