/**
 * Created by Paul Chan on 2017/4/23.
 */

var track_list = {};

$(function(){
    track_list.page = $('#track-table').page(Util.tableSetting());
    $('#btn-show-add-track').on('click', function(){
        $('select[name=step]').val('');
        $('select[name=trackType]').val('');
        $('textarea[name=content]').val('');
        $('#modal-add-track').modal('show');
    })
    $('#btn-add-track').on('click', function(){
        track_list.validate.resetForm();
        track_list.validate.validate();
        var isValid = track_list.validate.isValid();
        if(isValid){
            dialog.confirm('录入跟踪', function(){
                track_list.add_track();
            }, '是否录入跟踪？')
        }
    });
    $('select[name=cusType]').val(customer_info.type);
    track_list.init_validate();
    $('input[name=nextTime]').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,
        language : 'zh-CN'
    });
})

track_list.add_track = function(){
    var formData = $('#form-add-track').serialize();
    track_service.add_track(formData, function(json){
        if(json.code == 1000){
            $('#modal-add-track').modal('hide');
            track_list.page.ajax.reload();
        }else{
            dialog.alert('保存失败！')
        }
    })
}

track_list.init_validate = function(){
    $('#form-add-track').bootstrapValidator({
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            step:{
                validators: {
                    notEmpty: {
                        message: '请选择跟踪步骤'
                    }
                }
            },
            trackType:{
                validators: {
                    notEmpty: {
                        message: '请选择跟踪类型'
                    }
                }
            }
        }
    });
    track_list.validate = $('#form-add-track').data('bootstrapValidator');
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
            searching:false,
            search:false,
            isAdvancedSearch:false,
            baseUrl : '${base}',
            ajax: {
                "url": base + "/oa/track/page",
                type:"post",
                "data": function (data) {
                    data.cusId = customer_info.customerId;
                }
            },
            "columns": [
                {data: "trackId",orderable:false, title:'跟踪ID'},
                {data: "cusName",orderable:false, title:'客户姓名'},
                {data: "cusType",orderable:false, title:'客户类型', render:function(data, type, row, meta){
                    return customer.type[data]==null?"":customer.type[data];
                }},
                {data: "createTime",orderable:false, title:'时间'},
                {data: "nextTime",orderable:false, title:'计划下次跟踪时间'},
                {data: "step",orderable:false, title:'跟踪步骤', render:function(data, type, row, meta){
                    return track.step[data]==null?'':track.step[data];
                }},
                {data: "trackType",orderable:false, title:'跟踪类型', render:function(data, type, row, meta){
                    return track.trackType[data]==null?'':track.trackType[data];
                }},
                {data: "content",orderable:false, title:'跟踪内容'},
            ]
        };
        return setting;
    }
};
