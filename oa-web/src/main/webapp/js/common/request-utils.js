

var request_util = {};
//发送get请求
request_util.get = function(url, params, callback, error){
	$.ajax({
		type : "GET",
		url : url,//后台映射的URL 	
		data : params,//往后台传的参数 	
		dataType : 'json',//返回的数据格式 
		async: false,
		success : function(data) {
			callback(data);
		},
		error : function(data) {
			if(data != null){
				error(data);
			}else{
				alert('网络错误！');
			}
		}
	});
};
//发送post请求
request_util.post = function(url, params, callback, error){
	$.ajax({
		type : "POST",
		url : url,//后台映射的URL 	
		data : params,//往后台传的参数 	
		dataType : 'json',//返回的数据格式
		async: false,
		success : function(data) {
			callback(data);
		},
		error : function(data) {
			if(data != null){
				error(data);
			}else{
				alert('网络错误！');
			}
		}
	});
};

//post请求页面
request_util.post_url = function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
}