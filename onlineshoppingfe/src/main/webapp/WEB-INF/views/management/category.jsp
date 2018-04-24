<div class="modal fade" id="categoryModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">New Category</h4>
            </div>
            <div class="modal-body">

                <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST">

                    <div class="form-group">
                        <label class="control-label col-md-4">Name</label>
                        <div class="col-md-8 validate">
                            <sf:input type="text" path="name" class="form-control"
                                      placeholder="Category Name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4">Description</label>
                        <div class="col-md-8 validate">
                            <sf:textarea path="description" class="form-control"
                                         placeholder="Enter category description here!" />
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-4">
                            <input type="submit" name="submit" value="Save" class="btn btn-primary"/>
                        </div>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>
</div>
