<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="author" content="Begum">

    <title>Online Shopping Demo</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div class="se-pre-con"></div>
<div class="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li id="about">
                        <a href="#">About</a>
                    </li>

                    <li id="contact">
                        <a href="#">Contact</a>
                    </li>

                    <li id="listProducts">
                        <a href="#">View Products</a>
                    </li>
                    <li id="manageProduct">
                        <a href="#">Manage Product</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li id="signup">
                        <a href="#">Sign Up</a>
                    </li>
                    <li id="login">
                        <a href="#">Login</a>
                    </li>
                    <li class="dropdown">
                        <a class="btn btn-default dropdown-toggle" href="javascript:void(0)" id="dropdownMenu1"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li id="cart">
                                <a href="#">
                                    <span class="glyphicon glyphicon-shopping-cart"></span>&#160;<span
                                        class="badge"></span> - &#8377;
                                </a>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li id="logout">
                                <a href="#">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- navbar-collapse -->
        </div>
        <!-- container -->
    </nav>
</div>
</body>
</html>