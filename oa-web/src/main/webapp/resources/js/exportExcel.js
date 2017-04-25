window.onload = function() {
	goldExportInit();
}

function goldExportInit(){
		if(typeof(gold_export_util) != 'undefined'){
			if(gold_export_util.downClickEvent != '' && gold_export_util.downClickEvent !=null && $('#'+gold_export_util.downClickEvent) != null) {
				//导出Excel按钮事件
				$('#'+gold_export_util.downClickEvent).click(function(){
					exportWindow(gold_export_util.exportExcel.excel.filename);
				});
			}
			if(gold_export_util.exportExcel != '' && gold_export_util.exportExcel !=null) {
				
			}
		};
}

var beforeCheck = '../../admin/commomExportExcel/beforeCheck.jhtml';
var excelExportUrl = '../../admin/commomExportExcel/exportExcel.jhtml';
function exportWindow(filename) {
	$.dialog({
		modal: true,
		content: '<div style="height:70px;padding:10px;">'
				+'<table width="100%"><tr><td width="60">文件类型:</td><td> <table><tbody><tr><td><input style="border: none;" checked type="checkbox" value="1" class="downCheckedVersion"></td> <td>.xls</td><td>&nbsp;</td><td><input style="border: none;" type="checkbox" value="2" class="downCheckedVersion"></td><td>.xlsx</td></tr></tbody></table></td></tr>'
				+'<tr><td>文件名称:</td><td><input type="text" id="_filename" value="" style="width: 100%"></td></tr></table></div>',
		width: 320,
		height: "auto",
		title:"Excel导出",
		onCancel: null,
		onClose: null,
		onShow:function(){
			$(".downCheckedVersion").singleCheckbox();
			if(filename == null || filename == '') {
				 var date = new Date();
	             var format = date.getFullYear() + '年' + (date.getMonth()+1) + '月' + date.getDate() + '日';
		            format += date.getHours() + '时' + date.getMinutes() + '分';
		            $('#_filename').val('数据导出_'+format);
			}else{
				$("#_filename").val(filename);
			};
			var args = gold_export_util.exportExcel.service.method.args;
			if(args != null && typeof(args) != 'undefined') {
				for(var i=0;i<args.length;i++) {
					var elementid = args[i].elementID;
					if(elementid != null && elementid != '') {
						args[i].value=$("#"+elementid).val();
					}
				}
			}
		},
		ok: '下载',
		cancel: '取消',
		onOk: function() {
			var objToString = JSON.stringify(gold_export_util);
			$.ajax({
				url:beforeCheck,
				dataType:'json',
				type:'post',
				async:false,
				data:{'excelStr':objToString},
				success:function(data){     
					if(data) {
						if(data.status == 1) {
							if(data.resultListSize > 0) {
							    excelExportUrl += '?version=' + $('.downCheckedVersion:input[type="checkbox"]:checked').val();
					            var filename = $('#_filename').val();
					            if(filename != '') {
					            	excelExportUrl += '&filename=' + encodeURI(encodeURI(filename));
					            }
					            window.location.href=excelExportUrl;
							}else{
								alert("当前无数据可导出!");
							}
						}else {
							alert("Excel导出失败!");
						}
					}    
				  }
			});
		}
	});
};


if(jQuery) {
	(function($){
		$.fn.singleCheckbox = function(c){
			var self = this;
			var clickFn = function(s) {
				$(self).each(function(){
					$(this).attr("checked",false);
				});
				$(s).attr("checked",true);
				if(typeof(c) == 'function') {
					c(s);
				}
			}
			$(this).each(function(){
				var ele = this;
				$(this).click(function(){
					clickFn(ele);
				});
			});
		};
	})(jQuery);
}

