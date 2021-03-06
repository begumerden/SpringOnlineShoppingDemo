<div class="container">

    <div class="row">
        <div class="col-xs-12">

            <ol class="breadcrumb">
                <li><a href="${contextRoot}/home">Home</a>
                <li><a href="${contextRoot}/show/all/products">Products</a>
                <li class="active">${product.name}</li>
            </ol>

        </div>
    </div>


    <div class="row">
        <!-- Product image -->
        <div class="col-xs-12 col-sm-4">
            <div class="thumbnail">
                <img src="${images}/${product.code}.jpg" class="img img-responsive"/>
            </div>
        </div>

        <div class="col-xs-12 col-sm-8">
            <h3>${product.name}</h3>
            <hr/>

            <p>${product.description}</p>
            <hr/>

            <h4>Price: <strong> ${product.unitPrice} &euro; </strong></h4>
            <hr/>

            <h6>Quantity Available:
                ${product.quantity < 1 ? '<span style="color:red">Out of Stock </span>' : product.quantity}
            </h6>


            <security:authorize access="hasAuthority('USER')">
                <a href="${contextRoot}/cart/add/${product.id}/product"
                   class="btn btn-success ${product.quantity < 1 ? 'disabled ' : '' }">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    Add to Cart
                </a>
            </security:authorize>

            <security:authorize access="hasAuthority('ADMIN')">
                <a href="${contextRoot}/manage/${product.id}/product"
                   class="btn btn-warning">
                    <span class="glyphicon glyphicon-pencil"></span>
                    Edit
                </a>
            </security:authorize>

            <a href="${contextRoot}/list/all/products" class="btn btn-primary">
                <span class="glyphicon glyphicon-arrow-left"></span>
                Back
            </a>
        </div>

    </div>

</div>


</div>