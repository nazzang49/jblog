<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<!-- 제이쿼리 등록 -->
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
<!-- id 중복 체크 -->
<script type="text/javascript">
	$(function(){
		$('#blog-id').change(function(){
			$('#btn-id').show();
			$('#img-checkemail').hide();
		});
		
		$('#btn-id').on('click',function(){
			var id = $('#blog-id').val();
			if(id==''){
				alert("[입력 최소 길이 > 1]");
				$('#blog-id').focus();
				return false;
			}			
			$.ajax({
				url:"${pageContext.servletContext.contextPath }/user/api/checkid?id="+id,
				type:"get",
				dataType:"json",
				success:function(response){
					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data==true){
						alert("[중복] 다른 ID 입력");
						$('#blog-id').focus();
						$('#blog-id').val("");
						return;
					}
					$('#btn-id').hide();
					$('#img-checkemail').show();
				}
			});
		});
	});
</script>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		
		<c:import url="/WEB-INF/views/includes/menu.jsp"></c:import>
		
		<!-- MultiPart 객체로 전송 -->
		<form class="join-form" 
			id="join-form" 
			method="post" 
			action="${pageContext.request.contextPath}/user/join" 
			enctype="multipart/form-data">
			<label class="block-label">이름</label>
			<input id="name" name="name" type="text">
			
			<label class="block-label">아이디</label>
			<input id="blog-id" name="id" type="text">
			
			<!-- Ajax로 구현 --> 
			<input id="btn-id" type="button" value="중복검사">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label">패스워드</label>
			<input id="password" name="pw" type="password">
			
			<!-- 가입하는 순간 개인 블로그 자동 생성 -->
			<label class="block-label">블로그 타이틀</label>
			<input id="blog-title" name="title" type="text">
			
			<label class="block-label">블로그 로고</label>
			<input type="file" id="blog-logo" name="logo">

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">전체동의</label>
			</fieldset>
			<input type="submit" value="가입하기">
		</form>
	</div>
</body>
</html>