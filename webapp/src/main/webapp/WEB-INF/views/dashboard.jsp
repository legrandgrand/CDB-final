<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<span><a class="navbar-brand" href="Dashboard"> <spring:message
						code="title" /></a>
			</span> 

		</div>
	</header>
	
	<span style="float: right;"><spring:message code="lang" /> : <a href="?lang=en"><spring:message code="lang.en" /></a> | <a href="?lang=fr"><spring:message code="lang.fr" /></a></span>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				${maxId}
				<spring:message code="amount" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="GetComputer" method="GET"
						class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code="Search"/>" /> <input
							type="submit" id="searchsubmit"
							value="<spring:message code="Filter"/>" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="AddComputer"><spring:message
							code="Add" /></a> <a class="btn btn-default" id="editComputer"
						href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="Edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="DeleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="Dashboard"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="OrderBy?Order=${Order}&type=name&page=${page}&limit=${limit}"><spring:message
									code="Name" /></a></th>
						<th><a
							href="OrderBy?Order=${Order}&type=intro&page=${page}&limit=${limit}"><spring:message
									code="intro" /> </a></th>
						<!-- Table header for Discontinued Date -->
						<th><a
							href="OrderBy?Order=${Order}&type=discontinuation&page=${page}&limit=${limit}"><spring:message
									code="disc" /> </a></th>
						<!-- Table header for Company -->
						<th><a
							href="OrderBy?Order=${Order}&type=company&page=${page}&limit=${limit}"><spring:message
									code="company" /></a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" class="cb"
								value="${computer.idComputer}"></td>
							<td><a href="EditComputer?id=${computer.idComputer}"
								onclick=""><c:out value="${computer.name}" /></a></td>
							<td><c:out value="${computer.intro}" /></td>
							<td><c:out value="${computer.discontinuation}" /></td>
							<td><c:out value="${computer.companyName}" /></td>
						</tr>
					</c:forEach>
					<tr>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="Dashboard?page=0&limit=${limit}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="i" begin="0" end="10" step="1">
					<c:if test="${page - 5 + i >0 && page -5 +i <(maxId/20)}">
						<li><a href="Dashboard?page=${page -5 + i}&limit=${limit}">${page -5 +i}</a></li>
					</c:if>
				</c:forEach>
				<li><a
					href="Dashboard?page=${fn:substringBefore((maxId/20), '.')}&limit=${limit}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="Dashboard?page=${page}&limit=10"><button type="button"
						class="btn btn-default">10</button></a> <a
					href="Dashboard?page=${page}&limit=50"><button type="button"
						class="btn btn-default">50</button></a> <a
					href="Dashboard?page=${page}&limit=100"><button type="button"
						class="btn btn-default">100</button></a>
			</div>
		</div>

	</footer>

<!-- 	<script src="resources/js/international.js"></script> -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>