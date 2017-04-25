/**
 * Created by shenp on 2017/3/25.
 */

var search_utils = {};
/*
 adapters = {orderSource:{name:'订单来源',
 values:{1:'会议',2:'电销'}
 }
 }
 * */
search_utils.show_advance_condition_labels = function(data, adapters){
    if(adapters == null) return;
    if(data == null) return;
    var searchConditions = data.search.value;
    var conditionArray = searchConditions.split('&');
    var html = '';
    for(var i = 0; i < conditionArray.length; i++){
        var keyValue = conditionArray[i].split('=');
        if(keyValue.length == 0)  continue;
        var key = keyValue[0];
        var value = keyValue[1];
        value = decodeURI(value);
        if(key == 'sourceSearchInput') continue;
        if(value == null || value == '') continue;
        html += search_utils.get_condition_label({'key':key, 'value':value}, adapters);
    }
    $('#advanceConditionLabels').html(html);
}
search_utils.get_condition_label = function(condition, adapters){
    for(var adapterName in adapters){
        if(adapterName == condition.key){
            var adapter = adapters[adapterName];
            if(adapter == null) return;
            var htmlContent = adapter.name + ' : ';
            if(adapter.values == null) {
                htmlContent += condition.value;
            }else{
                htmlContent += search_utils.getValueName(condition.value, adapter.values);
            }
            var html = '<span class="label label-success">'+htmlContent+'</span>';
            return html;
        }
    }
    return '';
}

search_utils.getValueName = function(value, values){
    for(var val in values){
        if(val == value){
            return values[val];
        }
    }
}
