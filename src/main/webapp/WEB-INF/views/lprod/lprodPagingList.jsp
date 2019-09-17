<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basicLib</title>
<%@include file="/WEB-INF/views/commonJsp/basicLib.jsp" %>

<script>
	$(document).ready(function(){
		$(".lprodTr").on("click", function(){
			//console.log($(this).children().eq(1).text());
			var dataValue = $(this).data("lprodgu");
			//$("#lprodId").val($(this).children().eq(1).text());
			$("#lprodId").val(dataValue);
			
			$("#frm").submit();
		});
	})
</script>

</head>

<body>
<form id="frm" action="${cp }/prodList" method="get">
	<input type="hidden" id="lprodId" name="lprodId"/>
</form>

<!-- header -->
<%@include file="/WEB-INF/views/commonJsp/header.jsp" %>
<div class="container-fluid">
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@include file="/WEB-INF/views/commonJsp/left.jsp" %>
</div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">제품리스트</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>제품번호</th>
					<th>제품코드</th>
					<th>제품명</th>
				</tr>
				
				<%-- for(User user : userList --%>
				<c:forEach items="${lprodList }" var="lprod">
					<tr class="lprodTr" data-lprodgu="${lprod.lprod_gu }">
						<td>${lprod.lprod_id }</td>
						<td>${lprod.lprod_gu }</td>
						<td>${lprod.lprod_nm }</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>

		<a class="btn btn-default pull-right">제품그룹 등록</a>

		<div class="text-center">
			<ul class="pagination">
				<%-- 이전페이지 가기  --%>
				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled">
							<span aria-hidden="true">&laquo;</span>
		      			</li>
					</c:when>
					<c:otherwise>
						<li>							
					      	<a href="${cp }/lprod/lprodPagingList?page=${pageVo.page-1 }&pagesize=5" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
		      				</a>
		      			</li>
					</c:otherwise>
				</c:choose>
				
				<%-- 페이지 표시 --%>
				<c:forEach begin="1" end="${paginationSize }" var="page">				
					<c:choose>
						<c:when test="${page == pageVo.page }">
							<li class="active"><span>${page }</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${cp }/lprod/lprodPagingList?page=${page }&pagesize=5">${page }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<%-- 다음페이지 가기 --%>
				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled">
							<span aria-hidden="true">&raquo;</span>
			      		</li>
					</c:when>
					<c:otherwise>
						<li>							
					      	<a href="${cp }/lprod/lprodPagingList?page=${pageVo.page+1 }&pagesize=5" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
		      				</a>
		      			</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>