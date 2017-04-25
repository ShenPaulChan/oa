/*
 * Copyright 2005-2013 www.biz-united.com.cn. All rights reserved.
 * Support: http://www.biz-united.com.cn
 * License: http://www.biz-united.com.cn/license
 * 
 * JavaScript - cascade
 * Version: 3.0
 */

$().ready( function() {

	//根据选择的促销活动级联显示促销方式
	$("#promotionModeIds").change(function () {
		$("#promotionWayIds option:gt(0)").remove();
		$.ajax({  
		    url: 'findpromotionways.jhtml',   
		    data: { promotionModeId: $(this).val()}, 
		    dataType: "json",  
		    type: "POST",   
		    success: function (data) {
		    	if(data!=null && data.length>0){
		    		$.each(data, function (i) {
		    			var option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                    	$("#promotionWayIds").append(option);
		    		});
		    	}
	    	}
		});
    });

});