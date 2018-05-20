<div class="container">

    <div class="row"></div>

    <div class="col-md-3">
        <%@include file="./common/sidebar.jsp" %>
    </div>

    <div class="col-md-8">
        <h4>Most Viewed Products</h4>
        <div id="products" class="row list-group">
            <c:forEach items="${productsByViewCount}" var="productByViewCount">
                <div class="item col-xs-4 col-lg-4">
                    <div class="thumbnail">
                        <img href="${contextRoot}/show/${productByViewCount.id}/product" class="group list-group-image"
                             src="${contextRoot}/resources/images/${productByViewCount.code}.jpg"
                             alt="${productByViewCount.name}"/>
                        <div class="caption">
                            <h4 class="group inner list-group-item-heading">
                                    ${productByViewCount.name}</h4>
                            <p class="group inner list-group-item-text">
                                    ${productByViewCount.description}</p>
                            <div class="row">
                                <div class="col-xs-12 col-md-6">
                                    <p class="lead">
                                            ${productByViewCount.unitPrice}</p>
                                </div>
                                <div class="col-xs-12 col-md-6">
                                    <a class="btn btn-success"
                                       href="${contextRoot}/cart/add/${productByViewCount.id}/product">Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>



</div>