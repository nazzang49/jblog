package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;

@Repository
public class BlogDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//블로그 자동 생성 from UserService
	public boolean insert(BlogVO bvo) {
		return sqlSession.insert("blog.makeBlog", bvo)==1;
	}
	
	//블로그 정보 추출
	public BlogVO getInfo(String id) {
		return sqlSession.selectOne("blog.getInfo", id);
	}
	
	//블로그 정보 추출(대표 게시물)
	public Long getOnePost(Long categoryNo) {
		return sqlSession.selectOne("blog.getOnePost", categoryNo);
	}
	
	//블로그 정보 추출(대표 카테고리)
	public Long getOneCategory(String id) {
		return sqlSession.selectOne("blog.getOneCategory", id);
	}
	
	//특정 카테고리 내 전체 게시물 수
	public int getCount(Long categoryNo) {
		return sqlSession.selectOne("blog.getCount", categoryNo);
	}
	
	//블로그 정보 추출(대표 카테고리)
	public PostVO getOne(Long categoryNo, Long postNo) {
		Map<String, Long> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne("blog.getOne", map);
	}
	
	//블로그 카테고리 추출
	public List<CategoryVO> getCategoryList(String id) {
		return sqlSession.selectList("blog.getCategoryList", id);
	}
	
	//카테고리 추가
	public boolean insertCategory(CategoryVO cvo) {
		return sqlSession.insert("blog.insertCategory", cvo)==1;
	}
	
	//카테고리 추가
	public boolean insertPost(PostVO pvo) {
		return sqlSession.insert("blog.insertPost",pvo)==1;
	}
	
	//카테고리 삭제 전 내부 게시물 먼저 삭제
	public boolean deletePost(Long categoryNo) {
		return sqlSession.delete("blog.deletePost", categoryNo)==1;
	}
	
	//카테고리 삭제
	public boolean deleteCategory(Long no) {
		return sqlSession.delete("blog.deleteCategory", no)==1;
	}
	
	//블로그 게시물 추출
	public List<PostVO> getPostList(Long categoryNo, int startRow, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("startRow", startRow);
		map.put("pageSize", pageSize);
		return sqlSession.selectList("blog.getPostList", map);
	}
	
	//블로그 정보 변경
	public boolean update(BlogVO bvo) {
		return sqlSession.insert("blog.updateBlog", bvo)==1;
	}
}
