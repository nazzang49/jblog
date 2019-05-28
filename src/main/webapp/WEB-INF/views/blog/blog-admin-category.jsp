<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<!-- 카테고리 추가 및 삭제 Ajax로 구현 -->
<script type="text/javascript">

	function deleteCategory(no){
		var userId = $('#authuser-id').val();
		//삭제
		var flag = confirm("[해당 카테고리 삭제] 예 / 아니오");
		if(flag){
			$.ajax({
				url:"${pageContext.servletContext.contextPath }/blog/api/delete?no="+no+"&userId="+userId,
				type:"get",
				dataType:"json",
				success:function(response){
					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data==true){
						alert("[삭제완료]");
						return;
					}
				}
			});
		}
	}

	$(function(){
		var userId = $('#authuser-id').val();
		//추가
		$('#category-insert').on('click',function() {
			var subject = $('#category-subject').val();
			var description = $('#category-description').val();
			$.ajax({
				url:"${pageContext.servletContext.contextPath }/blog/api/insert?subject="+subject+"&description="+description+"&userId="+userId,
				type:"get",
				dataType:"json",
				success:function(response){
					if(response.result!="success"){
						console.error(response.message);
						return;
					}
					if(response.data==true){
						alert("[추가완료]");
						return;
					}
				}
			});
		});
	});
</script>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp">
					<c:param name="menu" value="category"/>
				</c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>
		      		</tr>
		      		<c:forEach items='${categoryList }' var='cvo'>
					<tr>
						<td>${cvo.no }</td>
						<td>${cvo.subject }</td>
						<td>${cvo.postCount }</td>
						<td>${cvo.description }</td>
						<!-- 카테고리 삭제 -->
						<td>
						<button id="category-delete" onclick="deleteCategory(${cvo.no})">삭제</button>
						</td>
					</tr>  
					</c:forEach>
				</table>
      	
      			<input type="hidden" name="authuser-id" value="${authUser.id }" id="authuser-id">
      	
      			<h4 class="n-c">카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="subject" id="category-subject"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description" id="category-description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="category-insert" type="button" value="추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>