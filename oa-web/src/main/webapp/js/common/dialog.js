/**
 * 
 */

var dialog = {};

dialog.alert = function(msg, callback, title) {
	if(title == null || title == ''){
		title = '提示'
	}
	var alertDialog = $.alert({
		title : title,
		content : msg,
		confirm: function(){
			if(callback != null){
				callback();
			}
	    }
	});
/*	setTimeout(function(){
		$('.content-pane').css('height', 'auto');
		$('.content-pane').css('min-height', '50px');
	},1002);
*/}

dialog.confirm = function(title, confirmCallBack, content, cancelCallBack, sureBtn, cancelBtn){
	if(content == null){
		content = false;
	}
	if(sureBtn == null || sureBtn == ''){
		sureBtn = '确认';
	}
	if(cancelBtn == null || cancelBtn == ''){
		cancelBtn = '取消';
	}
	$.confirm({
        confirmButton:sureBtn,
        cancelButton:cancelBtn,
        confirmButtonClass:'btn-info',
        cancelButtonClass:'btn-default',
        title: title,
        content:content,
        dialogClass: "modal-dialog modal-lg",
        confirm: function(){
        	if(confirmCallBack != null){
        		confirmCallBack();
        	}
        },
        cancel:function(){
            $.confirm().close();
            if(cancelCallBack != null){
            	cancelCallBack();
            }
        }
    });
}