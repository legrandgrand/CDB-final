<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="<c:url value="/src/main/WebContent/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/src/main/WebContent/css/font-awesome.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/src/main/WebContent/css/main.css" />" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="Dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
              <c:out value="${maxId}"/> Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="GetComputer" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="AddComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
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

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="Dashboard" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
	                      <a href="OrderBy?Order=${Order}&type=name&page=${page}&limit=${limit}">Computer name</a>
                        </th>
                        <th>
	                        <a href="OrderBy?Order=${Order}&type=introduced&page=${page}&limit=${limit}">Introduced date </a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
	                      <a href="OrderBy?Order=${Order}&type=discontinued&page=${page}&limit=${limit}">Discontinued date </a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
	                      <a href="OrderBy?Order=${Order}&type=company_id&page=${page}&limit=${limit}">Company</a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
				  <c:forEach items="${computers}" var="computer">
				    <tr>
				        <td class="editMode">
                            <input type="checkbox" class="cb" value="${computer.idComputer}">
                        </td>
				      <td> <a href="EditComputer?<c:out value="${computer.idComputer}"/>" onclick=""><c:out value="${computer.name}" /></a></td>
				      <td><c:out value="${computer.dateIntro}" /></td>
				      <td><c:out value="${computer.dateDiscontinuation}" /></td>
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
              <li>
                <a href="Dashboard?page=0&limit=${limit}" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
	            <c:forEach var="i" begin="0" end="10" step="1">
	    		  <c:if test="${page - 5 + i >0 && page -5 +i <(maxId/20)-1}">
	    			<li><a href="Dashboard?page=${page -5 + i}&limit=${limit}">${page -5 +i}</a></li>
	    		  </c:if>
				</c:forEach>
              <li>
	            <a href="Dashboard?page=${fn:substringBefore((maxId/20)-1, '.')}&limit=${limit}" aria-label="Next">
	              <span aria-hidden="true">&raquo;</span>
	            </a>
              </li>
        	</ul>

	        <div class="btn-group btn-group-sm pull-right" role="group" >
	            <a href="Dashboard?page=${page}&limit=10"><button type="button" class="btn btn-default">10</button></a>
	            <a href="Dashboard?page=${page}&limit=50"><button type="button" class="btn btn-default">50</button></a>
	            <a href="Dashboard?page=${page}&limit=100"><button type="button" class="btn btn-default">100</button></a>
	        </div>
        </div>

    </footer>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/dashboard.js"></script>

</body>
</html>