//分页

if (jQuery) {
	(function($) {
		$.fn.page = function(setting) {
			var _setting = {
				paging : true,
				pagingType : "simple_numbers",
				lengthChange : false,
				searching : false,
				ordering : true,
				info : true,
				colReorder : true,
				scrollX : "100%",
				scrollCollapse : true,
				autoWidth : false,
				iDisplayLength : 10,
				language : {
					paginate : {
						previous : "上一页",
						next : "下一页"
					},
					info : "当前显示 _START_ 到 _END_ 条,一共 _TOTAL_ 条",
					infoEmpty: "当前显示 _START_ 到 _END_ 条,一共 _TOTAL_ 条",
					search : "",
					searchPlaceholder: "数据搜索",
					zeroRecords: "无符合条件数据"
				},
				colReorder : {
					fixedColumnsLeft : 1
				},
				//processing : true, // 打开数据加载时的等待效果
				//serverSide : false,// 打开后台分页
			}
			
			var descOptions = $.extend({}, _setting, setting || {});
			if(setting.severSide != null && setting.severSide == true) {
				if(setting.columns == null || setting.columns.length <=0) {
					throw new error("columns is not defined ");
				}
				descOptions.columns = setting.columns;
			}
			return $(this).DataTable(descOptions);
		};
	})(jQuery);
}







