<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="firstUrl" value="/students?page=1" />
<c:url var="lastUrl" value="/students?page=${pages}" />
<c:url var="prevUrl" value="/students?page=${current - 1}" />
<c:url var="nextUrl" value="/students?page=${current + 1}" />
<html>
<head>
    <title>Students</title>
    <style>
        <%@include file='css/bootstrap.css' %>
    </style>
</head>
<body>
<center><h1>Студенти</h1></center>
<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>firstname</th>
        <th>lastname</th>
        <th >email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="students">
        <tr>
            <td>${students.id}</td>
            <td>${students.firstName}</td>
            <td>${students.lastName}</td>
            <td>${students.email}</td>
            <td><a href="/delete/${students.id}">Удалить</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<center>
<nav>
    <ul class="pagination" >
        <c:choose>
            <c:when test="${current == 1}">
                <li ><a href="#">&lt;&lt;</a></li>
                <li ><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach  var="i" begin="${beginIndex}" end="${endIndex}">
            <c:choose>
                <c:when test="${(i)==current}">
                    <li class="active"><a href="/students?page=${i}" style="color: #122b40">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li ><a href="/students?page=${i}" style="color: #122b40">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${current == pages}">
                <li ><a href="#">&gt;</a></li>
                <li ><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
            </ul>
    </nav>
    </center>
<center><h1>Додати студента</h1></center>
<center>
    <form:form method="post" action="add" commandName="student" role="form">
    <div class="form-group">
        <label for="firstName">FirstName</label>
        <input type="text" class="form-control" id="firstName" name="firstName" path="firstName" placeholder="firstname">
    </div>

        <div class="form-group">
            <label for="lastname">lastname</label>
            <input type="text" class="form-control" id="lastName" name="lastName" path="lastName" placeholder="lastname">
        </div>
        <div class="form-group">
            <label for="FirstName">FirstName</label>
            <input type="text" class="form-control" id="email"  name="email" path="email" placeholder="email">
        </div>

        <input type="submit" class="btn btn-success" value="Додати" /></td>


    </form:form>
</center>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>
