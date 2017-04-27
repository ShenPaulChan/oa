/**
 * Created by Paul Chan on 2017/4/27.
 */
var track_count = {};


$(function(){
    setTimeout(function () {
        $('#rangeDate').daterangepicker({
            format : 'YYYY-MM-DD',
            locale : daterangepick_zh_ch
        }, function(start, end, label) {
        });
    }, 1000);
    track_count.page = $('#track-count-table').page(Util.tableSetting());
})


var Util = {
    /** Table 设置*/
    tableSetting: function () {
        var setting = {
            scrollX: true,
            colReorder: false,
            processing: true, //打开数据加载时的等待效果
            serverSide: true,//打开后台分页
            ordering: true,
            searching:true,
            search:true,
            isAdvancedSearch:{
                searchHtml:'#advancedSearch'
            },
            baseUrl : '${base}',
            ajax: {
                "url": base + "/oa/track/count/page",
                type:"post",
                "data": function (data) {

                }
            },
            "columns": [
                {data: "userId",orderable:false, title:'序号', render:function(data, type, row, meta){
                    return meta.row + 1;
                }},
                {data: "username",orderable:false, title:'员工'},
                {data: "date",orderable:false, title:'日期'},
                {data: "telTrackCount",orderable:false, title:'电话跟踪', render:function(data, type, row, meta){
                    return data == null?0:data;
                }},
                {data: "netTrackCount",orderable:false, title:'网络跟踪', render:function(data, type, row, meta){
                    return data == null?0:data;
                }},
                {data: "netTeachTrackCount",orderable:false, title:'远程讲解', render:function(data, type, row, meta){
                    return data == null?0:data;
                }},
                {data: "trackCount",orderable:false, title:'总跟踪次数', render:function(data, type, row, meta){
                    return data == null?0:data;
                }},
            ]
        };
        return setting;
    }
};
