/**
 * 表单工具
 */

var form_utils  = {};

$.fn.serializeObject = function()
{
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

form_utils.build_data_by_class = function(clazz){
    var inputs = $('.' + clazz);
    var paramsStr = '';
    for(var i=0;i<inputs.length;i++){
        var input = inputs[i];
        paramsStr += $(input).attr('name') + '=';
        var value = encodeURIComponent($(input).val());
        paramsStr += value;
        if(i < (inputs.length - 1)){
            paramsStr += '&';
        }
    }
    return paramsStr;
}