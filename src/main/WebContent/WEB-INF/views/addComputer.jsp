<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/jqueryvalidate.js"></script>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="Dashboard"> <spring:message code="title"/> </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="Add"/></h1>
                    <form id="AddComputerForm" action="AddComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="Name"/></label>
                                <input type="text" class="form-control" id="name" placeholder="Computer name" name="name">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="intro"/></label>
                                <input type="date" class="form-control" id="intro" placeholder="Introduced date" name="intro">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="disc"/></label>
                                <input type="date" class="form-control" id="disc" placeholder="Discontinued date" name="disc">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company"/></label>
                                <select class="form-control" id="companyId" name="companyname">
                                   	<c:forEach items="${companies}" var="company">
                                    	<option value="${company.name}">${company.name}</option>
                                    </c:forEach>   
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="AddButton"/>" class="btn btn-primary">
                            <spring:message code="Or"/>
                            <a href="Dashboard" class="btn btn-default"><spring:message code="Cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>