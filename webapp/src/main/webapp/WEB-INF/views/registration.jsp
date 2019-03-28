<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="resources/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<span> <a class="navbar-brand" href="Dashboard"> <spring:message
						code="title" /></a> <a class="navbar-brand" href="/webapp/logout"
				style="float: right;">logout</a>
			</span>

		</div>
	</header>
	<div class="row">
		<div class="col-xs-8 col-xs-offset-2 box">
			<h1>Registration</h1>
			<form action="registration" method="POST" enctype="utf8">
				<div>
					<label>Login</label> <input type="text" class="form-control"
						id="username" placeholder="username" name="username" />
				</div>
				<div>
					<label>password</label> <input type="password" class="form-control"
						id="password" placeholder="password" name="password" />
				</div>
				<div>
					<label>confirm</label> <input type="password" class="form-control"
						id="confirm" placeholder="confirm" name=confirm />
				</div>
				<button type="submit">submit</button>
			</form>
			
				<a href="/webapp/login">login</a>
		</div>
	</div>
</body>
</html>