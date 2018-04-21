<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<%@include file="./common/head.jsp" %>

<body>

<div class="wrapper">

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextRoot}/home">Home</a>
            </div>
        </div>
    </nav>


    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="jumbotron">

                        <h1>${errorTitle}</h1>
                        <hr/>

                        <c:if test="${errorDesc != null}">
                            <blockquote style="word-wrap:break-word">
                                    ${errorDesc}
                            </blockquote>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="./common/footer.jsp" %>

</div>

</body>

</html>