package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDAO;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;

@Service
public class BlogService {

	@Autowired
	private BlogDAO blogDao;
	
	//blog-main
	public List<CategoryVO> getCategoryList(String id) {
		return blogDao.getCategoryList(id);
	}
	
	//blog-main
	public List<PostVO> getPostList(Long categoryNo) {
		return blogDao.getPostList(categoryNo);
	}
	
	//blog-main
	public BlogVO getInfo(String id) {
		return blogDao.getInfo(id);
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
	public boolean deletePost(Long categoryNo) {
		return blogDao.deletePost(categoryNo);
	}
	
	//blog-admin-category
	public boolean deleteCategory(Long no) {
		return blogDao.deleteCategory(no);
	}
	
}
