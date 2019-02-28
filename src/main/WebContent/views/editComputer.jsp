<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="jquery-xxxx.min.js"></script>
<script type="text/javascript" src="jquery.validate.min.js"></script>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="Dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${computer.id}"/>
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="EditComputer" method="POST">
                        <input type="hidden" value="<c:out value="${computer.id}"/>" id="id" name="id" />
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
								<input type="text" class="form-control" id="computerName" placeholder="Computer name" name="name" value="<c:out value="${computer.name}" />">			
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date" name="intro" value='${fn:substringBefore(computer.dateIntro," 00:00:00.0")}'>
                                
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="disc" value='${fn:substringBefore(computer.dateDiscontinuation," 00:00:00.0")}'>               
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyname">
                                <c:forEach items="${companies}" var="company">
                                	<c:choose>
									  <c:when test="${computer.company.id == company.id}">
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
                            <input type="submit" value="Edit" class="btn btn-primary"> <!-- POST Update computer -->
                            or
                            <a href="Dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>