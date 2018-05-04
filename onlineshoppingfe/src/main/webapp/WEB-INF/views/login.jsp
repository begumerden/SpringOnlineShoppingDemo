<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="images" value="/resources/images"/>

<!DOCTYPE html>
<html lang="en">

<%@include file="./common/head.jsp" %>

<body>

<div class="wrapper">
    <!--Navigation-->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextRoot}/home">Online Shopping Demo</a>
            </div>
        </div>
    </nav>


    <div class="content">

        <div class="container">

            <c:if test="${not empty message}">
                <div class="row">
                    <div class="col-md-offset-3 col-md-6">
                        <div class="alert alert-danger">
                            ${message}
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row">

                <div class="col-md-offset-3 col-md-6">

                    <div class="panel panel-primary">

                        <div class="panel-heading">
                            <h4>Login</h4>
                        </div>

                        <div class="panel-body">
                            <form action="${contextRoot}/login" method="POST" class="form-horizontal"
                                  id="loginForm">
                                <div class="form-group">
                                    <label for="username" class="col-md-4 control-label">Email: </label>
                                    <div class="col-md-8">
                                        <input type="text" name="username" id="username" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-md-4 control-label">Password: </label>
                                    <div class="col-md-8">
                                        <input type="password" name="password" id="password" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" value="Login" class="btn btn-primary"/>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <%@include file="./common/footer.jsp" %>


            <script type="text/javascript"
                    src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script type="text/javascript"
                    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script type="text/javascript"
                    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
            <script src="/resources/js/myapp.js"></script>

        </div>
    </div>
</body>
</html>