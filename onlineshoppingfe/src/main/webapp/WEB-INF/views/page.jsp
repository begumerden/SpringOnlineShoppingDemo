<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="author" content="Begum">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="/resources/css/myapp.css" rel="stylesheet"> <!-- Custom CSS -->
    <link href="/resources/css/bootstrap-theme.css" rel="stylesheet"> <!-- Bootstrap Readable Theme -->

    <title>Online Shopping Demo ${title}</title>

    <script>
        window.menu = '${title}';
        window.contextRoot = '${contextRoot}'
    </script>
</head>

<body>

<div class="se-pre-con"></div>
<div class="wrapper">
    <!--Navigation-->
    <%@include file="./common/navbar.jsp" %>

    <div class="content">

        <!-- Home content -->
        <c:if test="${userClickHome == true }">
            <%@include file="home.jsp" %>
        </c:if>

        <!-- About content-->
        <c:if test="${userClickAbout == true }">
            <%@include file="about.jsp" %>
        </c:if>

        <!-- Contact content-->
        <c:if test="${userClickContact == true }">
            <%@include file="contact.jsp" %>
        </c:if>
    </div>

    <!-- Footer -->
    <%@include file="./common/footer.jsp" %>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/resources/js/myapp.js"></script>

</div>
</body>
</html>