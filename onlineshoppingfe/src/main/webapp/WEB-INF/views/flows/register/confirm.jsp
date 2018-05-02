<%@include file="../common/header.jsp" %>
<div class="container">

    <div class="row">

        <div class="col-sm-6">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Personal Details</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <p>
                            <a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-6">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Billing Address</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <p>
                            <a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="row">

        <div class="col-sm-4 col-sm-offset-4">
            <div class="text-center">
                <a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-lg btn-primary">Confirm</a>
            </div>
        </div>

    </div>

</div>
<%@include file="../common/footer.jsp" %>