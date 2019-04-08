<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/jqueryvalidate.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<span> <a class="navbar-brand" href="Dashboard"> <spring:message
						code="title" /></a> <a class="navbar-brand" href="/computer-database/logout"
				style="float: right;">logout</a>
			</span>
		</div>
	</header>

	<span style="float: right;"><spring:message
			code="lang" /> : <a href="?lang=en"><spring:message
				code="lang.en" /></a> | <a href="?lang=fr"><spring:message
				code="lang.fr" /></a></span>

	<section id="main">
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				<c:out value="${error}" />
			</div>
		</c:if>

		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">
						id:
						<c:out value="${computer.idComputer}" />
					</div>
					<h1>
						<spring:message code="EditTitle" />
					</h1>

					<form action="EditComputer" method="POST">
						<input type="hidden"
							value="<c:out value="${computer.idComputer}"/>" id="id" name="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="Name" /></label>
								<input type="text" class="form-control" id="computerName"
									placeholder="Computer name" name="name"
									value="<c:out value="${computer.name}" />">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="intro" /></label>
								<input type="date" class="form-control" id="introduced"
									placeholder="Introduced date" name="intro"
									value='${fn:substringBefore(computer.intro," 00:00:00.0")}'>

							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="disc" /></label>
								<input type="date" class="form-control" id="discontinued"
									placeholder="Discontinued date" name="disc"
									value='${fn:substringBefore(computer.discontinuation," 00:00:00.0")}'>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="company" /></label>
								<select class="form-control" id="companyId" name="companyname">
									<c:forEach items="${companies}" var="company">
										<c:choose>
											<c:when test="${computer.idCompany == company.id}">
												<option selected="selected" value="${company.name}">${company.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${company.name}">${company.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="Edit"/>"
								class="btn btn-primary">
							<!-- POST Update computer -->
							<spring:message code="Or" />
							<a href="Dashboard" class="btn btn-default"><spring:message
									code="Cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>