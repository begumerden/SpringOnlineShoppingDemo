$(function () {
    switch (menu) {
        case 'About Us':
            $('#about').addClass('active');
            break;
        case 'Contact Us':
            $('#contact').addClass('active');
            break;
        case 'Products':
            $('#listProducts').addClass('active');
            break;
        case 'Product Management':
            $('#manageProducts').addClass('active');
            break;
        case 'User Cart':
            $('#userCart').addClass('active');
            break;
        default:
            if (menu == "Home")
                break;
            $('#listProducts').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }


    // datatables
    var $table = $('#listProductsTable');
    if ($table.length) {

        var jsonUrl = '';
        if (window.categoryId == '') {
            jsonUrl = window.contextRoot + '/datatable/all/products';
        } else {
            jsonUrl = window.contextRoot + '/datatable/category/' + window.categoryId + '/products';
        }

        $table.DataTable({
            lengthMenu: [[3, 5, 10, -1], ['3', '5', '10', 'ALL']],
            pageLength: 5,
            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot
                            + '/resources/images/' + data
                            + '.jpg" class="dataTableImg"/>';
                    }
                },
                {
                    data: 'name',
                }, {
                    data: 'brand',
                }, {
                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&euro;' + data;
                    }
                }, {
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color:red">Out of Stock!</span>';
                        }
                        return data;
                    }
                }, {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var s = '';
                        s += '<a href="'
                            + window.contextRoot + '/show/' + data
                            + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &nbsp;';

                        if (userRole == 'ADMIN') {
                            s += '<a href="'
                                + window.contextRoot + '/manage/' + data
                                + '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
                        }
                        else {
                            if (row.quantity < 1) {
                                s += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                            } else {
                                s += '<a href="'
                                    + window.contextRoot + '/cart/add/' + data
                                    + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                            }
                        }
                        return s;
                    }
                }
            ]
        })
    }
    ;


    var adminTbl = $('#adminProductTbl');

    if (adminTbl.length) {
        var jsonUrl = window.contextRoot + '/datatable/admin/all/products';

        adminTbl.DataTable({
            lengthMenu: [[10, 30, 50, -1], ['10', '30', '50', 'ALL']],
            pageLength: 5,
            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {data: 'id'},
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot
                            + '/resources/images/' + data
                            + '.jpg" class="dataTableImg"/>';
                    }
                },
                {
                    data: 'name',
                }, {
                    data: 'brand',
                }, {
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color:red">Out of Stock!</span>';
                        }
                        return data;
                    }
                }, {
                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&euro;' + data;
                    }
                }, {
                    data: 'active',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var s = '';
                        if (data) {
                            s += '<label class="switch"> <input type="checkbox" value="' + row.id + '" checked="checked">  <div class="slider round"> </div></label>';

                        } else {
                            s += '<label class="switch"> <input type="checkbox" value="' + row.id + '">  <div class="slider round"> </div></label>';
                        }
                        return s;
                    }
                }, {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var s = '';
                        s += '<a href="' + window.contextRoot + '/manage/' + data
                            + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

                        return s;
                    }
                }
            ],
            initComplete: function () {
                var api = this.api();
                api.$('.switch input[type="checkbox"]').on('change', function () {
                    var checkbox = $(this);
                    var checked = checkbox.prop('checked');
                    var msg = (checked) ? 'Do you want to Activate Product ? ' : 'Do you want to Deactivate Product ? ';
                    var value = checkbox.prop('value');

                    bootbox.confirm({
                        size: 'medium',
                        title: 'Product Activation',
                        message: msg,
                        callback: function (confirmed) {
                            if (confirmed) {

                                var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
                                $.post(activationUrl, function (data) {
                                    bootbox.alert({
                                        size: 'medium',
                                        title: ' Info',
                                        message: data
                                    })
                                });
                            } else {
                                checkbox.prop('checked', !checked);
                            }
                        }
                    })
                })

            }
        })

    }
    ;


    // after 5 sec alert msg will be removed
    var alert = $('.alert');
    if (alert.length) {
        setTimeout(function () {
            alert.fadeOut('slow');
        }, 5000);
    }
    ;


    $('.switch input[type="checkbox"]').on('change', function () {
        var checkbox = $(this);
        var checked = checkbox.prop('checked');
        var msg = checked ? 'Do you want to Activate Product ? ' : 'Deactivate Product';
        var val = checked.prop('value');

        bootbox.confirm({
            size: 'medium',
            title: 'Product Activation',
            message: msg,
            callback: function (confirmed) {
                if (confirmed) {
                    bootbox.alert({
                        size: 'medium',
                        title: 'Info',
                        message: 'Operation on product:'
                    });
                } else {
                    checkbox.prop('checked', !checked);
                }
            }
        })
    });

    var categoryForm = $('#categoryForm');

    if (categoryForm.length) {
        categoryForm.validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 3
                    },
                    description: {
                        required: true,
                        minlength: 3
                    }
                },
                messages: {
                    name: {
                        required: 'Please enter product name!',
                        minlength: 'Please enter at least three characters'
                    },
                    description: {
                        required: 'Please enter product name!',
                        minlength: 'Please enter at least three characters'
                    }
                },
                errorElement: "em",
                errorPlacement: function (error, element) {
                    error.addClass("help-block");

                    error.insertAfter(element);

                    element.parents(".validate").addClass("has-feedback");
                }
            }
        );
    }


    var loginForm = $('#loginForm');

    if (loginForm.length) {
        loginForm.validate({
                rules: {
                    username: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 3
                    }
                },
                messages: {
                    username: {
                        required: 'Please enter email!',
                    },
                    password: {
                        required: 'Please enter password!',
                        minlength: 'Please enter at least 3 characters'
                    }
                },
                errorElement: "em",
                errorPlacement: function (error, element) {
                    error.addClass("help-block");

                    error.insertAfter(element);

                    element.parents(".validate").addClass("has-feedback");
                }
            }
        );
    }
    ;

    $('button[name="refreshCart"]').click(function () {

        var cartLineId = $(this).attr('value');
        var countElement = $('#count_' + cartLineId);
        var originalCount = countElement.attr('value');
        var currentCount = countElement.val();

        if (currentCount !== originalCount) {

            if (currentCount < 1 || currentCount > 5) {
                countElement.val(originalCount);
                bootbox.alert({
                    size: 'medium',
                    title: 'Error',
                    message: 'Product count should be between 1 and 5'
                })
            } else {
                var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
                window.location.href = updateUrl;
            }

        }

    });

});