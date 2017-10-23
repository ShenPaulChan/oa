/**
 * Created by Paul Chan on 2017/4/19.
 */
var customer_list = {};

$(function(){
    customer_list.page = $('#cus-table').page(Util.tableSetting());
    $('#table-checkbox-all').on('click', function(){
        console.info($(this).is(':checked'));
        if($(this).is(':checked')){
            $('.checkbox-customer').prop('checked', true);
        }else{
            $('.checkbox-customer').prop('checked', false);
        }
    });
    $('#btn-show-add-cus').on('click', function(){
        $('#modal-add-cus').modal({
            keyboard: false,
            backdrop:false
        });
        $('#form-add-cus')[0].reset();
        $('#modal-add-cus').modal('show');
    });
    $('#btn-add-cus').on('click', function(){
        customer_list.validate.resetForm();
        customer_list.validate.validate();
        var isValid = customer_list.validate.isValid();
        if(isValid){
            dialog.confirm('确认保存', function () {
                customer_list.save_cus();
            }, '确认保存吗');
        }
    });
    $('select[name=province]').on('change', function(){
        var province = $(this).val();
        customer_list.loadCities(province);
    });
    $('#btn-add-cus-group').on('click', function(){
        $('#form-add-group')[0].reset();
        $('#modal-add-cus-group').modal({
            keyboard: false,
            backdrop:false
        });
        $('#modal-add-cus-group').modal('show');
    })
    $('#btn-show-transfer-cus').on('click', function(){
        var cusInputs = $('.checkbox-customer:checked');
        if(cusInputs.length == 0){
            dialog.alert('请选择客户');
            return;
        }
        $('#modal-transfer-cus').modal({
            keyboard: false,
            backdrop:false
        });
        $('#modal-transfer-cus').modal('show');
        setTimeout(function(){
            if(customer_list.transfer_cus_page == null){
                customer_list.transfer_cus_page = $('#table-transfer-cus').page(Util.transfer_cus_tableSetting());
            }else{
                customer_list.transfer_cus_page.ajax.reload();
            }
        }, 500);
    })
    $('#btn-add-group').on('click', function(){
        var groupName = $('#form-add-group').find('input[name=groupName]').val();
        if(groupName == null && groupName.trim() == ''){
            dialog.alert('请输入分类名称');
            return;
        }
        customer_list.add_group(groupName);
    })
    $('#btn-del-group').on('click', function(){
        var cusGroupId = $('#form-cus-group').find('select[name=cusGroupId]').val();
        if(cusGroupId == null || cusGroupId == ''){
            dialog.alert('请选择自选分类');
            return;
        }
        dialog.confirm('删除自选分类', function(){
            customer_list.del_group(cusGroupId);
        }, '确认删除当前自选分类吗？');
    })
    $('#btn-save-cus-group').on('click', function(){
        customer_list.save_cus_group();
    })
    $('select[name=cus-source]').on('change', function(){
        customer_list.page.ajax.reload();
    })
    customer_list.init_validate();
});

customer_list.list_track = function(customerId){
	var pageInfo = customer_list.page.page.info();
    location.href = base + '/oa/track/list/view?customerId='+customerId+'&start='+pageInfo.start;
}

customer_list.get_checked_cusIds = function(){
    var cusIds = [];
    var cusInputs = $('.checkbox-customer:checked');
    $.each(cusInputs, function(index, input){
        cusIds.push($(input).attr('data'));
    });
    return cusIds;
}

customer_list.transfer_cus = function(userId){
    var params = {userId : userId};
    params.cusIds = customer_list.get_checked_cusIds();
    dialog.confirm('转让', function () {
        customer_service.transfer_cus(params, function(json){
            if(json.code == 1000){
                dialog.alert('转让成功', function(){
                    $('#modal-transfer-cus').modal('hide');
                    customer_list.page.ajax.reload();
                })
            }else{
                dialog.alert(json.message);
            }
        });
    },'确认要转让给该员工吗？');
}

customer_list.save_cus_group = function(){
    var formData = $('#form-cus-group').serialize();
    customer_service.save_cus_group(formData, function(json){
        if(json.code == 1000){
            $('#modal-cus-group').modal('hide');
            customer_list.page.ajax.reload(null, false);
        }else{
            dialog.alert('保存失败');
        }
    });
}

customer_list.del_group = function(cusGroupId){
    customer_service.del_group(cusGroupId, function(json){
        if(json.code == 1000){
            $('#form-cus-group').find('select[name=cusGroupId]').find('option[value='+cusGroupId+']').remove();
            customer_list.page.ajax.reload(null, false);
        }else{
            dialog.alert('删除失败');
        }
    });
}

customer_list.add_group = function(groupName){
    customer_service.add_cus_group({groupName:groupName}, function(json){
        if(json.code == 1000){
            $('#modal-add-cus-group').modal('hide');
            $('#form-cus-group').find('select[name=cusGroupId]').append('<option value="'+json.data.cusGroupId+'" >'+json.data.name+'</option>');
        }else{
            dialog.alert('新增失败');
        }
    })
}


customer_list.editCus = function(customerId){
    customer_service.getCusById(customerId, function(json){
        if(json.code == 1000){
            customer_list.init_add_form(json.data);
            $('#modal-add-cus').modal({
                keyboard: false,
                backdrop:false
            });
            $('#modal-add-cus').modal('show');
        }else{
            dialog.alert('获取客户信息失败');
        }
    });
}

customer_list.init_cusgroup_from = function(cus){
    $('#form-cus-group').find('#span-cusName').html(cus.name);
    $('#form-cus-group').find('input[name=customerId]').val(cus.customerId);
    var grupSelect = $('#form-cus-group').find('select[name=cusGroupId]');
    grupSelect.html('<option value="" ></option>');
    customer_service.getUserGroup(function(json){
        if(json.code == 1000){
            $.each(json.data, function(index, group){
                grupSelect.append('<option value="'+group.cusGroupId+'" >'+group.name+'</option>');
            });
            grupSelect.val(cus.groupId);
        }
    });
}

customer_list.cusGroup = function(customerId){
    customer_service.getCusById(customerId, function(json){
        if(json.code == 1000){
            customer_list.init_cusgroup_from(json.data);
            $('#modal-cus-group').modal({
                keyboard: false,
                backdrop:false
            });
            $('#modal-cus-group').modal('show');
        }else{
            dialog.alert('获取客户信息失败');
        }
    });
}

customer_list.init_add_form = function(cus){
    var inputs = $('#form-add-cus').find('input');
    $.each(inputs, function(index, input){
        var type = $(input).attr('type');
        var name = $(input).attr('name');
        if(type == 'radio'){
            if($(input).val() == cus[name]){
                $(input).attr('checked', true);
            }
        }else{
            $(input).val(cus[name]);
        }
    });
    var selects = $('#form-add-cus').find('select');
    $.each(selects, function(index, select){
        var name = $(select).attr('name');
        $(select).val(cus[name]);
    });
    $('input[name=wxStatus][value='+cus.wxStatus+']').attr("checked",true);
    $('input[name=sex][value='+cus.sex+']').attr("checked",true);
    customer_list.loadCities(cus.province, cus.city);
}

customer_list.save_cus = function(){
    var formData = $('#form-add-cus').serialize();
    customer_service.save_cus(formData, function(json){
        if(json.code == 1000){
            dialog.alert('保存成功', function(){
                $('#form-add-cus')[0].reset();
                $('#modal-add-cus').modal('hide');
                customer_list.page.ajax.reload();
            })
        }else if(json.code == 1008){
            dialog.alert(json.message);
        }
    });
}

customer_list.loadCities = function(province, city){
    $('select[name=city]').html('<option value=""></option>');
    city_service.listProvinceCities({pId:province}, function(json){
        if(json.code == 1000){
            customer_list.initCity(json.data, city);
        }
    });
}

customer_list.initCity = function(cities, cityId){
    if(cityId == null){
        cityId = '';
    }
    if(cityId != ''){
        $('select[name=city]').val(cityId);
    }
    var select_city = $('select[name=city]');
    select_city.html('<option value=""></option>');
    if(cities != null){
        $.each(cities, function(index, city){
            var selected = '';
            if(cityId == city.id){
                selected = 'selected';
            }
            var html = '<option '+selected+' value="'+city.id+'">'+city.name+'</option>';
            select_city.append(html);
        })
    }
}

customer_list.init_validate = function(){
    $('#form-add-cus').bootstrapValidator({
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            name:{
                validators: {
                    notEmpty: {
                        message: '请输入客户姓名'
                    }
                }
            },
            mobile:{
                validators: {
                    notEmpty: {
                        message: '请输入手机号码'
                    },
                    regexp: {
                        regexp: regExp.mobile,
                        message: '请输入正确的手机号码'
                    }
                }
            },
            type:{
                validators: {
                    notEmpty: {
                        message: '请选择客户类型'
                    }
                }
            }
        }
    });
    customer_list.validate = $('#form-add-cus').data('bootstrapValidator');
}

var Util = {
    /** Table 设置*/
    tableSetting: function () {
        var setting = {
            scrollX: true,
            colReorder: false,
            processing: true, //打开数据加载时的等待效果
            serverSide: true,//打开后台分页
            ordering: true,
            pageLength: 30,
	        displayStart:page_start,
            searching:true,
            search:true,
            isAdvancedSearch:{
                searchHtml:'#advancedSearch'
            },
            baseUrl : '${base}',
            ajax: {
                "url": base + "/oa/customer/page",
                type:"post",
                "data": function (data) {
                    data.listType = $('select[name=cus-source]').val();
                    customer_list.page_start = data.start;
                    return data;
                }
            },
            "columns": [
                {data: "customerId",orderable:false, title:'<input id="table-checkbox-all" type="checkbox" />', render:function(data, type, row, meta){
                    return '<input class="checkbox-customer" data="' + data + '" type="checkbox" />';
                }},
                {data: "customerId",orderable:false, title:'客户ID'},
                {data: "name",orderable:false, title:'客户姓名'},
                {data: "sex",orderable:false, title:'性别', render:function(data, type, row, meta){
                    return customer.sex[data]==null?"":customer.sex[data];
                }},
                {data: "type",orderable:false, title:'客户类型', render:function(data, type, row, meta){
                    return customer.type[data]==null?"":customer.type[data];
                }},
                {data: "groupName",orderable:false, title:'自选分类'},
                {data: "mobile",orderable:false, title:'手机'},
                {data: "mobile2",orderable:false, title:'手机2'},
                {data: "qq",orderable:false, title:'QQ'},
                {data: "qqName",orderable:false, title:'QQ昵称'},
                {data: "city",orderable:false, title:'地区', render:function(data, type, row, meta){
                    return (row.provinceName==null?'':row.provinceName) + '-' + (row.cityName==null?'':row.cityName);
                }},
                {data: "qq2",orderable:false, title:'QQ2'},
                {data: "qqName2",orderable:false, title:'QQ昵称2'},
                {data: "wx",orderable:false, title:'微信号'},
                {data: "wxName",orderable:false, title:'微信昵称'},
                {data: "customerId",orderable:false, title:'操作', render:function(data, type, row, meta){
                    var html = '';
                    html += '<button type="button" onclick="customer_list.editCus('+data+')" class="btn btn-sm btn-primary btn-edit-cus">修改</button>';
                    html += '<button type="button" onclick="customer_list.cusGroup('+data+')" class="btn btn-sm btn-primary btn-edit-cus-group">自选</button>';
                    html += '<button type="button" onclick="customer_list.list_track('+data+')" class="btn btn-sm btn-primary btn-edit-cus-group">跟踪</button>';
                    return html;
                }},
            ]
        };
        return setting;
    },
    transfer_cus_tableSetting: function () {
        var setting = {
            scrollX: true,
            colReorder: false,
            processing: true, //打开数据加载时的等待效果
            serverSide: true,//打开后台分页
            ordering: true,
            searching:true,
            search:true,
            isAdvancedSearch:false,
            baseUrl : '${base}',
            ajax: {
                "url": base + "/oa/admin/user/colleague/page",
                type:"post",
                "data": function (data) {
                }
            },
            "columns": [
                {data: "id",orderable:false, title:'序号', render:function(data, type, row, meta){
                    console.info(meta);
                    return meta.row+1;
                }},
                {data: "username",orderable:false, title:'员工'},
                {data: "id",orderable:false, title:'操作', render:function(data, type, row, meta){
                    var html = '';
                    html += '<button type="button" onclick="customer_list.transfer_cus('+data+')" class="btn btn-primary btn-transfer-cus">转让</button>';
                    return html;
                }},
            ]
        };
        return setting;
    }
};
