<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>比特能专家系统 - 后台管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- bootstrap -->
<link rel="stylesheet"
	href="/robot/resources/css/bootstrap/bootstrap.css" />
<link rel="stylesheet"
	href="/robot/resources/css/bootstrap/bootstrap-responsive.css" />
<link rel="stylesheet"
	href="/robot/resources/css/bootstrap/bootstrap-overrides.css" />
<!-- libraries -->
<link
	href="/robot/resources/css//backend/lib/jquery-ui-1.10.2.custom.css"
	rel="stylesheet" type="text/css" />
<link href="/robot/resources/font/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" />
<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="/robot/resources/css/backend/layout.css" />
<link rel="stylesheet" type="text/css"
	href="/robot/resources/css/backend/elements.css" />
<link rel="stylesheet" type="text/css"
	href="/robot/resources/css/backend/icons.css" />
<!-- this page specific styles -->
<link rel="stylesheet"
	href="/robot/resources/css/backend/patent-list.css"
	type="text/css" media="screen" />
	</head>
<body>
	<!-- navbar -->
	<jsp:include page="../common/navbar.jsp"></jsp:include>
	<!-- sidebar -->
	<jsp:include page="../common/sidebar.jsp"></jsp:include>
	<!-- main container -->
	<div class="content">
		<c:if test="${not empty message}">
					<div class="alert alert-info">
						<i class="fa fa-exclamation-circle"></i>
						${message}
					</div>
		</c:if>
		<div class="container-fluid">
			<div id="pad-wrapper" class="users-list">
				<div class="row-fluid header">
					<h3>专利列表</h3>
					<div class="span10 pull-right">
						<input type="text" class="span5 search"
							placeholder="按条件搜索专利..." />

						<!-- custom popup filter -->
						<!-- styles are located in css/elements.css -->
						<!-- script that enables this dropdown is located in js/theme.js -->
						<div class="ui-dropdown">
							<div class="head" data-toggle="tooltip" title="Click me!">
								搜索 <i class="arrow-down"></i>
							</div>
							<div class="dialog">
								<div class="pointer">
									<div class="arrow"></div>
									<div class="arrow_border"></div>
								</div>
								<div class="body">
									<p class="title">选择查找条件:</p>
									<div class="form">
										<select>
											<option>姓名</option>
											<option>性别</option>
											<option>组织</option>
											<option>Signed up</option>
											<option>Last seen</option>
										</select> <select>
											<option>is equal to</option>
											<option>is not equal to</option>
											<option>is greater than</option>
											<option>starts with</option>
											<option>contains</option>
										</select> <input type="text" /> <a class="btn-flat small">Add
											filter</a>
									</div>
								</div>
							</div>
						</div>

						<a href="/robot/backend/patent/add" class="btn-flat success pull-right"> <span>&#43;</span>
							添加专利
						</a>
					</div>
				</div>
			
				<h5>系统共有${pages.totalCount}篇专利</h5>
				<!-- Users table -->
				<div class="row-fluid table">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="span1 sortable">id</th>
								<th class="span3 sortable">标题</th>
								<th class="span2 sortable"><span class="line"></span>申请人</th>
								<th class="span2 sortable"><span class="line"></span>申请时间</th>
								<th class="span2 sortable"><span class="line"></span>申请单位</th>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pages.list}" var="patent">
							<!-- row -->
							 <tr class="first">
							 <td>${patent.patentId}</td>
								<td> <a href="/robot/backend/patent/${patent.patentId}" class="name">${patent.title}</a> </td>
								<td>${patent.applicant}</td>
								<td>${patent.date}</td>
								<td>${patent.orgnization.name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination pull-right">
				<ul>
				<c:choose>
				<c:when test="${not pages.havePrePage}">
					<li class="disabled"><a href="#">&#8249;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/robot/backend/patent/patents?page=${pages.currentPage-1}&pageSize=${pages.pageSize}">&#8249;</a></li>
				</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${pages.totalPage lt 5}">
						<c:forEach var="item" varStatus="status" begin="1" end="${pages.totalPage}">
						  	<li ><a <c:if test="${pages.currentPage eq status.index}">class="active"</c:if> href="/robot/backend/patent/patents?page=${status.index}&pageSize=${pages.pageSize}">${status.index}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" varStatus="status" begin="${pages.currentPage-2}" end="${pages.currentPage+2}">
						  	<li ><a <c:if test="${pages.currentPage eq status.index}">class="active"</c:if> href="/robot/backend/patent/patents?page=${status.index}&pageSize=${pages.pageSize}">${status.index}</a></li>
						</c:forEach>						
					</c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${not pages.haveNextPage}">
					<li class="disabled"><a href="#">&#8250;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/robot/backend/patent/patents?page=${pages.currentPage+1}&pageSize=${pages.pageSize}">&#8250;</a></li>
				</c:otherwise>
				</c:choose>
				</ul>
				</div>
				<!-- end users table -->
			</div>
		</div>
	</div>
		<!-- end main container -->
	<!-- scripts -->
	<script src="/robot/resources/js/jquery/jquery-2.0.0.min.js"></script>
	<script src="/robot/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/robot/resources/js/jquery/jquery-ui-1.10.2.custom.min.js"></script>
	<script src="/robot/resources/js/backend/common.js"></script>
	<!-- this page specific scripts -->
	<script src="/robot/resources/js/backend/patent-list.js"></script>
</body>
</html>
</body>
</html>