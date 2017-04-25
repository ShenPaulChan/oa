var Util = {
	enterQuery : function(isFlag, callBack) {
		if (isFlag) {
			var textObj = $("body").find('form input[type="text"]');
			var bodyObj = $("body");
			var numberObj = $("body").find('input.Number[type="text"]');
			var divObj = $("div").find('form input[type="text"]');
			var attr = [ textObj,divObj,numberObj];
			for (var i = 0; i < attr.length; i++) {
				$(attr[i])
						.each(
								function() {
									$(this)[0].onkeydown = function(e) {
										var code = null;
										var elementObj = null;
										if (!e) {
											e = window.event;
										}
										if (window.event) {
											elementObj = e.srcElement;
											code = e.keyCode;
										} else if (e.which) {
											code = e.which;
											elementObj = e.target;
										}
										if($(elementObj).attr("class") != null) {
											if($(elementObj).attr("class").toString().indexOf("Number") != -1) {
												if(!Util.judgeCode(code)) {
													return false;
												}
												else if(callBack !=null) {
													callBack(elementObj);
												} 
											}
										}
/*										//回车事件
										if (code == 13
												&& event.srcElement.type != "textarea"
												&& event.srcElement.type != "password") {
											if(callBack != null) {
												callBack();
											}
										}
*/									};
								});
			}
		}
	},
	trimForm : function(formId) {
		if (formId != 'undefined' && formId != '') {
			var formObj = document.getElementById(formId);
			if(formObj == null || formObj == 'undefined' || formObj == '') {
				formObj = $(formId);
			}
			$(formObj).find("input[type='text']").each(function() {
				$(this).val($.trim($(this).val()));
			});
		}
	},judgeCode : function(code) {
		var keychar = String.fromCharCode(code);
		var numcheck = /\d/;
		if(code != 8 && code != 39 && code != 37 && code != 9 &&code != 13 &&!numcheck.test(keychar)) {
			return false;
		}
		return true;
	} 
};

//字符转Utf-8
function utf16to8(str) {  
    var out, i, len, c;  
    out = "";  
    len = str.length;  
    for(i = 0; i < len; i++) {  
    c = str.charCodeAt(i);  
    if ((c >= 0x0001) && (c <= 0x007F)) {  
        out += str.charAt(i);  
    } else if (c > 0x07FF) {  
        out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
        out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));  
        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
    } else {  
        out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));  
        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
    	}  
    }
    return out;  
} 