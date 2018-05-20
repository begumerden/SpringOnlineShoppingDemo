<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="images" value="/resources/images" />

<!DOCTYPE html>
<html lang="en">

<%@include file="./common/head.jsp" %>

<body>

<div class="se-pre-con"></div>
<div class="wrapper">
    <!--Navigation-->
    <%@include file="./common/navbar.jsp" %>

    <div class="content">

        <!-- Home content -->
        <c:if test="${homePageClicked}">
            <%@include file="home.jsp" %>
        </c:if>

        <!-- About content-->
        <c:if test="${aboutPageClicked}">
            <%@include file="about.jsp" %>
        </c:if>

        <!-- Contact content-->
        <c:if test="${contactPageClicked}">
            <%@include file="contact.jsp" %>
        </c:if>

        <!-- List Products content-->
        <c:if test="${allProductPageClicked or categoryProductsClicked}">
            <%@include file="listProducts.jsp" %>
        </c:if>

        <!-- Product Detail content-->
        <c:if test="${showProductClicked}">
            <%@include file="productDetail.jsp" %>
        </c:if>

        <!-- Product Management content -->
        <c:if test="${manageProductsClicked}">
            <%@include file="./management/product.jsp" %>
        </c:if>

        <!-- Cart content -->
        <c:if test="${showCartClicked}">
            <%@include file="./cart.jsp" %>
        </c:if>

    </div>

    <!-- Footer -->
    <%@include file="./common/footer.jsp" %>


    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.16/cr-1.4.1/r-2.2.1/datatables.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
    <script src="/resources/js/myapp.js"></script>

</div>
</body>
</html>