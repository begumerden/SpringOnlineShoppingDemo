<div class="container">

    <div class="row">

        <!-- Sidebar -->
        <div class="col-md-3">
            <%@include file="./common/sidebar.jsp" %>
        </div>

        <div class="col-md-9">

            <!-- Breadcrump -->
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${allProductPageClicked}">
                        <ol class="breadcrumb">
                            <li><a href="${contextRoot}/home">Home</a></li>
                            <li class="active">All products</li>
                        </ol>
                    </c:if>

                    <c:if test="${categoryProductsClicked}">
                        <ol class="breadcrumb">
                            <li><a href="${contextRoot}/home">Home</a></li>
                            <li class="active">Category</li>
                            <li class="active">${category.name}</li>
                        </ol>
                    </c:if>
                </div>

            </div>

        </div>
    </div>
</div>