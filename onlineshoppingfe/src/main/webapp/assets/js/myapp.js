$(function () {
    switch (menu) {
        case 'About Us':
            $('#about').addClass('active');
            break;
        case 'Contact Us':
            $('#contact').addClass('active');
            break;
        case 'All Products':
            $('#listProducts').addClass('active');
            break;
        case 'Product Management':
            $('#manageProduct').addClass('active');
            break;
        default:
            if (menu == "Home")
                break;
            $('#listProducts').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }


    // datatable

    var $table = $('#listProductsTable');

    if ($table.length) {

        var jsonUrl = '';
        if (window.categoryId == '') {
            jsonUrl = window.contextRoot + '/json/data/all/products';
        } else {
            jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
        }

        $table.DataTable({
            lengthMenu: [[3, 5, 10, -1], ['3', '5', '10', 'ALL']],
            pageLenght: 5,
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
                }, {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var s = '';
                        s += '<a href="'
                            + window.contextRoot + '/show/' + data
                            + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &nbsp;';

                        s += '<a href="' +
                            window.contextRoot + '/card/add' + data
                            + '/product " class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a> &nbsp;';
                        return s;
                    }
                }
            ]
        })
    }

});