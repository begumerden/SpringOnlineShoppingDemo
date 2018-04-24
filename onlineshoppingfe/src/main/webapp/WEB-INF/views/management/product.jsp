<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">

    <c:if test="${not empty message}">
        <div class="col-xs-12">
            <div class="alert alert-info alert-dismissable">
                <button type="button" class="close" data-dismiss="alert"></button>
                    ${message}
            </div>
        </div>
    </c:if>

    <div class="row">

        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Product Management</h4>
                </div>

                <div class="panel-body">
                    <!-- Form -->
                    <sf:form class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products"
                             method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="control-label col-md-4" for="name">Product Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="name" id="name" placeholder="Product Name"
                                          class="form-control"/>
                                <sf:errors path="name" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="brand">Brand Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="brand" id="brand" placeholder="Brand Name"
                                          class="form-control"/>
                                <sf:errors path="brand" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="brand">Description:</label>
                            <div class="col-md-8">
                                <sf:textarea path="description" id="description" rows="3"
                                             placeholder="Write a description"></sf:textarea>
                                <sf:errors path="brand" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="unitPrice">Enter Unit Price:</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price"
                                          class="form-control"/>
                                <sf:errors path="unitPrice" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="quantity">Quantity Available:</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="quantity" id="quantity" class="form-control"/>
                                <sf:errors path="quantity" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="file">Select an image:</label>
                            <div class="col-md-8">
                                <sf:input type="file" path="file" id="file" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="categoryId">Select Category:</label>
                            <div class="col-md-8">
                                <sf:select class="form-control" id="categoryId" path="categoryId"
                                           items="${categories}" itemLabel="name" itemValue="id"/>
                            </div>
                        </div>

                        <c:if test="${product.id == 0}">
                            <br/>
                            <div class="text-right">
                                <button type="button" data-toggle="modal" data-target="#categoryModel"
                                        class="btn btn-warning btn-xs">Add Category
                                </button>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
                            </div>
                        </div>

                        <sf:hidden path="id"/>
                        <sf:hidden path="code"/>
                        <sf:hidden path="supplierId"/>
                        <sf:hidden path="purchases"/>
                        <sf:hidden path="views"/>
                        <sf:hidden path="active"/>

                    </sf:form>

                </div>
            </div>
        </div>

    </div>

    <%@include file="./category.jsp" %>

    <hr/>
    <h3>Available Products</h3>
    <hr/>

    <div class="row">

        <div class="col-xs-12">

            <table id="adminProductTbl" class="table table-condensed table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>&#160;</th>
                    <th>Name</th>
                    <th>Brand</th>
                    <th>Quantity Available</th>
                    <th>Unit Price</th>
                    <th>Activate</th>
                    <th>Edit</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Id</th>
                    <th>&#160;</th>
                    <th>Name</th>
                    <th>Brand</th>
                    <th>Qty. Avail</th>
                    <th>Unit Price</th>
                    <th>Activate</th>
                    <th>Edit</th>
                </tr>
                </tfoot>

            </table>

        </div>

    </div>

</div>