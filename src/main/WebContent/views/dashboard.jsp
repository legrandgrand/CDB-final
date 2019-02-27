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
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
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
              ${fn:length(computers)} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="GetComputer" method="GET" class="form-inline"> <!-- GET one computer -->

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
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
				  <c:forEach items="${computers}" var="computer">
				    <tr>
				        <td class="editMode">
                            <input type="checkbox" class="cb" value="${computer.id}">
                        </td>
				      <td> <a href="EditComputer?<c:out value="${computer.id}"/>" onclick=""><c:out value="${computer.name}" /></a></td>
				      <td><c:out value="${computer.dateIntro}" /></td>
				      <td><c:out value="${computer.dateDiscontinuation}" /></td>
				      <td><c:out value="${computer.company}" /></td>
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
                    <a href="Dashboard?0" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <li><a href="Dashboard?0">1</a></li>
              <li><a href="Dashboard?20">2</a></li>
              <li><a href="Dashboard?40">3</a></li>
              <li><a href="Dashboard?60">4</a></li>
              <li><a href="Dashboard?80">5</a></li>
              <li><a href="Dashboard?100">6</a></li>
              <li><a href="Dashboard?120">7</a></li>
              <li><a href="Dashboard?140">8</a></li>
              <li><a href="Dashboard?160">9</a></li>
              <li><a href="Dashboard?180">10</a></li>
              <li><a href="Dashboard?200">11</a></li>
              <li><a href="Dashboard?220">12</a></li>
              <li><a href="Dashboard?240">13</a></li>
              <li><a href="Dashboard?260">14</a></li>
              <li><a href="Dashboard?280">15</a></li>
              <li><a href="Dashboard?300">16</a></li>
              <li><a href="Dashboard?320">17</a></li>
              <li><a href="Dashboard?340">18</a></li>
              <li><a href="Dashboard?360">19</a></li>
              <li><a href="Dashboard?380">20</a></li>
              <li><a href="Dashboard?400">21</a></li>
              <li><a href="Dashboard?420">22</a></li>
              <li><a href="Dashboard?440">23</a></li>
              <li><a href="Dashboard?460">24</a></li>
              <li><a href="Dashboard?480">25</a></li>
              <li><a href="Dashboard?500">26</a></li>
              <li><a href="Dashboard?520">27</a></li>
              <li><a href="Dashboard?540">28</a></li>
              <li>
                <a href="Dashboard?540" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
		</div>
		
        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default">10</button>
            <button type="button" class="btn btn-default">50</button>
            <button type="button" class="btn btn-default">100</button>
        </div>

    </footer>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/dashboard.js"></script>

</body>
</html>