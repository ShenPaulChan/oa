// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

//获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

//移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}


/*
MAP对象，实现MAP功能

接口：
size()     获取MAP元素个数
isEmpty()    判断MAP是否为空
clear()     删除MAP所有元素
put(key, value)   向MAP中增加元素（key, value) 
remove(key)    删除指定KEY的元素，成功返回True，失败返回False
get(key)    获取指定KEY的元素值VALUE，失败返回NULL
element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
containsKey(key)  判断MAP中是否含有指定KEY的元素
containsValue(value) 判断MAP中是否含有指定VALUE的元素
values()    获取MAP中所有VALUE的数组（ARRAY）
keys()     获取MAP中所有KEY的数组（ARRAY）

例子：
var map = new Map();
map.put("key", "value");
var val = map.get("key")
*/
function Map() {
this.elements = new Array();

//获取MAP元素个数
this.size = function() {
    return this.elements.length;
};

//判断MAP是否为空
this.isEmpty = function() {
    return (this.elements.length < 1);
};

//删除MAP所有元素
this.clear = function() {
    this.elements = new Array();
};

//向MAP中增加元素（key, value)
this.put = function(_key, _value) {
	 this.removeByKey(_key);
    this.elements.push( {
        key : _key,
        value : _value
    });
};

//删除指定KEY的元素，成功返回True，失败返回False
this.removeByKey = function(_key) {
    var bln = false;
    try {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].key == _key) {
                this.elements.splice(i, 1);
                return true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
};

//删除指定VALUE的元素，成功返回True，失败返回False
this.removeByValue = function(_value) {//removeByValueAndKey
    var bln = false;
    try {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].value == _value) {
                this.elements.splice(i, 1);
                return true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
};

//删除指定VALUE的元素，成功返回True，失败返回False
this.removeByValueAndKey = function(_key,_value) {
    var bln = false;
    try {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].value == _value && this.elements[i].key == _key) {
                this.elements.splice(i, 1);
                return true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
};

 //获取指定KEY的元素值VALUE，失败返回NULL
this.get = function(_key) {
    try {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].key == _key) {
                return this.elements[i].value;
            }
        }
    } catch (e) {
        return null;
    }
    return null;
};

//获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
this.element = function(_index) {
    if (_index < 0 || _index >= this.elements.length) {
        return null;
    }
    return this.elements[_index];
};

//判断MAP中是否含有指定KEY的元素
this.containsKey = function(_key) {
    var bln = false;
    try {
        for ( var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].key == _key) {
                bln = true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
};

//判断MAP中是否含有指定VALUE的元素
this.containsValue = function(_value) {
    var bln = false;
    try {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].value == _value) {
                bln = true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
};

//判断MAP中是否含有指定VALUE的元素
this.containsObj = function(_key,_value) {
    var bln = false;
    try {
        for (var i = 0; i < this.elements.length; i++) {
            if (this.elements[i].value == _value && this.elements[i].key == _key) {
                bln = true;
            }
        }
    } catch (e) {
        bln = false;
    }
    return bln;
};

//获取MAP中所有VALUE的数组（ARRAY）
this.values = function() {
    var arr = new Array();
    for (var i = 0; i < this.elements.length; i++) {
        arr.push(this.elements[i].value);
    }
    return arr;
};

//获取MAP中所有VALUE的数组（ARRAY）
this.valuesByKey = function(_key) {
    var arr = new Array();
    for (var i = 0; i < this.elements.length; i++) {
        if (this.elements[i].key == _key) {
            arr.push(this.elements[i].value);
        }
    }
    return arr;
};

 //获取MAP中所有KEY的数组（ARRAY）
this.keys = function() {
    var arr = new Array();
    for (var i = 0; i < this.elements.length; i++) {
        arr.push(this.elements[i].key);
    }
    return arr;
};

 //获取key通过value
this.keysByValue = function(_value) {
    var arr = new Array();
    for (var i = 0; i < this.elements.length; i++) {
        if(_value == this.elements[i].value){
            arr.push(this.elements[i].key);
        }
    }
    return arr;
};

//获取MAP中所有KEY的数组（ARRAY）
this.keysRemoveDuplicate = function() {
    var arr = new Array();
    for (var i = 0; i < this.elements.length; i++) {
        var flag = true;
        for(var j=0;j<arr.length;j++){
            if(arr[j] == this.elements[i].key){
                flag = false;
                break;
            } 
        }
        if(flag){
            arr.push(this.elements[i].key);
        }
    }
    return arr;
};
}

if(jQuery){
	$(function(){
		//设置Token 防止重复提交
		$("form").submit(function() {
			var $this = $(this);
			if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && $this.find("input[name='token']").size() == 0) {
				var token = getCookie("token");
				if (token != null) {
					$this.append('<input type="hidden" name="token" value="' + token + '" \/>');
				}
			}
		});
		
	});
	/** 消息 如果成功 返回 true 失败返回 false*/
	$.message = function (messageData) {
		var isSuccess = false;
		var isInfo = true;
		if(isNotNull(messageData)) {
			try{
				if(typeof(messageData) == 'string') {
					messageData = JSON.parse(messageData);
				}
			}catch(e){
				return isSuccess;
			}
			var context = messageData.context;
			if(messageData.status == 'SUCCESS'){
				isSuccess = true;
			}else if(messageData.status == 'ERROR'){
				isSuccess = false;
			}
			isInfo = messageType(messageData.type);
			//是info
			if(isInfo) {
				$.info(context);
			}else{
				if(isSuccess) {
					$.alert("info", context);
				}else {
					$.alert("error", context);
				}
			}
		}
		return isSuccess;
	};
	
	/** 消息*/
	$.info = function(content){
		$.messager.show({
			title:'提示',
			msg:content,
			showType:'fade',
			timeout: 1000,
			style:{
				right:'',
				bottom:''
			}
		});
	};
	/** 错误信息提示 错误消息类型有:error、question、info、warning*/
	$.alert = function(type,content) {
		$.messager.alert('提示',content,type);
	};
	
	/** 确认提示框 */
	$.confirm = function(content,callBack){
		$.messager.confirm('提示', content, function(r){
			if (r){
				if(callBack != null && typeof(callBack) == 'function' ){
					callBack();
				}
			}
		});
	};
	
	
	(function($){
		
		/** 打开窗口,主要是对话框*/
		$.fn.openWin = function(title,width,height,url){
			if(!isNotNull(title)) {
				title = '弹出框';
			}
			if(!isNotNull(width)) {
				width = 300;
			}
			if(!isNotNull(height)) {
				height = 'auto';
			}
			if(!isNotNull(url)) {
				url = 'javascript:;';
			}
			var $this = $(this);
//			var $thisId = $this.attr('id');
			
			var _defaultWindowSetting = {
					title: title,
				    width: width,
				    height: height,
				    closed: false,
				    cache: false,
				    href: url,
				    autoOpen: false,
				    modal: true
				};
			$this.dialog(_defaultWindowSetting);
		};
		
		/** type: reflush*/
		$.fn.closeWin = function(type) {
			$(this).dialog('close');
			if(typeof(type) != 'undefined' && type == 'reflush') {
				window.location.reload();
			}
		};
		
		/** 打开自定义的窗口.主要是Window*/
		$.fn.openDefinedWin = function (title,width,height,url) {
			var $this = $(this);
			if(!isNotNull(title)) {
				title = '弹出框';
			}
			if(!isNotNull(width)) {
				width = 400;
			}
			if(!isNotNull(height)) {
				height = 'auto';
			}
			if(!isNotNull(url)) {
				url = 'javascript:;';
			}
			var _defaultWindowSetting = {
				title: title,
			    width: width,
			    height: height,
			    href: url,
			    shadow: true,
			    cache: false,
			    modal: true,
			    closed: true,
			    resizable:false,
			    minimizable: false,
			    maximizable: false,
			    collapsible: false	
			};
			$this.window(_defaultWindowSetting);
			$this.window('open');
		},
		/** 关闭自定义Window*/
		$.fn.closeDefinedWin = function(){
			$(this).window('close'); 
		},
		$.fn.browser = function(options,baseContext) {
				var settings = {
					type: "image",
					uploadPath : "/",
					bucketName : "DEFAULT",
					title: "图片上传",
					setValue : '',
					isUpload: true,
					browserUrl: baseContext + "/admin/image/browser.jhtml",
					uploadUrl:  baseContext + "/admin/image/upload.jhtml",
					callback: null
				};
				$.extend(settings, options);
				var token = getCookie("token");
				var cache = {};
				var browserFrameId = "browserFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
				var tempId = browserFrameId+"001";
				var $browser = $('<div class="xxBrowser" id="'+tempId+'"><\/div>');
				var $browserBar = $('<div class="browserBar"><\/div>').appendTo($browser);
				var $browserFrame = null;
				var $browserForm  = null;
				var $browserUploadButton  = null;
				var $browserUploadInput = null;
				var $browserParentButton  = null;
				var $browserLoadingIcon = null;
				var $browserList = null;
				if (settings.isUpload) {
					$browserFrame = $('<iframe id="' + browserFrameId + '" name="' + browserFrameId + '" style="display: none;"><\/iframe>').appendTo($browserBar);
					$browserForm = $('<form action="' + settings.uploadUrl + '" method="post" encType="multipart/form-data" target="' + browserFrameId + '"><input type="hidden" name="token" value="' + token + '" \/><input type="hidden" name="fileType" value="' + settings.type + '" \/><input type="hidden" name="bucketName" value="' + settings.bucketName + '" \/><input type="hidden" name="uploadPath" id="uploadPathOnly" value="'+settings.uploadPath+'" \/><\/form>').appendTo($browserBar);
					$browserUploadButton = $('<a href="javascript:;" class="browserUploadButton button easyui-linkbutton">上传<\/a>').appendTo($browserForm);
					$browserUploadInput = $('<input type="file" name="file" \/>').appendTo($browserUploadButton);
				}
				$browserParentButton = $('<a href="javascript:;" class="button">上一级<\/a>').appendTo($browserBar);
				$browserLoadingIcon = $('<span class="loadingIcon" style="display: none;">&nbsp;<\/span>').appendTo($browserBar);
				$browserList = $('<div class="browserList"><\/div>').appendTo($browser);
				
				$browser.dialog({
					title: "图片上传",
				    width: 500,
				    height: 400,
				    closed: false,
				    cache: false,
				    //content: $browser,
				    autoOpen: false,
				    modal: true
				});

				browserList(settings.uploadPath);
				
				function browserList(path) {
					var key = settings.type + "_" + path ;
					if (cache[key] == null) {
						$.ajax({
							url: settings.browserUrl,
							type: "GET",
							data: {bucketName: settings.bucketName, dirPath: path},
							dataType: "json",
							cache: false,
							beforeSend: function() {
								$browserLoadingIcon.show();
							},
							success: function(data) {
								createBrowserList(path, data);
								cache[key] = data;
							},
							complete: function() {
								$browserLoadingIcon.hide();
							}
						});
					} else {
						createBrowserList(path, cache[key]);
					}
				}
				
				function createBrowserList(path, data) {
					var browserListHtml = "";
					$.each(data, function(i, fileInfo) {
						var iconUrl;
						var title;
						if (fileInfo.hasDir) {
							iconUrl = baseContext + "/resources/admin/images/folder_icon.gif";
							title = fileInfo.name;
						} else if (new RegExp("^\\S.*\\.(jpg|jpeg|bmp|gif|png)$", "i").test(fileInfo.name)) {
							iconUrl = fileInfo.fullPath;
							title = iconUrl + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModifyDate).toLocaleString() + ")";
						} else {
							iconUrl = baseContext + "/resources/admin/images/file_icon.gif";
							title = baseContext + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModifyDate).toLocaleString() + ")";
						}
						browserListHtml += '<div class="browserItem"><img src="' + iconUrl + '" title="' + title + '" url="/' + fileInfo.dirPath + '" isDirectory="' + fileInfo.hasDir + '" \/><div title="'+fileInfo.name+'">' + fileInfo.name + '<\/div><\/div>';
					});
					//browserListHtml += '<div class="browserItem"><img src="' + path + '" url="' + path + '" isDirectory="false" \/><div><\/div><\/div>';
					$browserList.html(browserListHtml);
					
					$browserList.find("img").bind("click", function() {
						var $this = $(this);
						var isDirectory = $this.attr("isDirectory");
						if (isDirectory == "true") {
							var queryPath = $this.attr('url');
							if(queryPath == null || queryPath == '') {
								queryPath = settings.uploadPath;
							}
							$("#uploadPathOnly").val(queryPath);
							browserList(queryPath);
						} else {
							var url = $this.attr("src");
							if (settings.input != null) {
								settings.input.val(url);
							} else {
								$("#"+settings.setValue).textbox("setValue",url);
								//browserButton.prev().find("input[type='hidden']").val(url);
							}
							if (settings.callback != null && typeof settings.callback == "function") {
								settings.callback(url);
							}
							$browser.dialog("close");
						}
					});
					
					if (path == "" || path == "/" || path == settings.uploadPath) {
						$browserParentButton.addClass("disabled");
						$browserParentButton.unbind("click");
					} else {
						var parentPath = path.substr(0, path.replace(/\/$/, "").lastIndexOf("/") + 1);
						$browserParentButton.removeClass("disabled");
						$browserParentButton.unbind("click").bind("click", function() {
							browserList(parentPath);
						});
					}
				}
				
				$browserUploadInput.change(function() {
					var allowedUploadExtensions;
					if (settings.type == "flash") {
						allowedUploadExtensions = setting.uploadFlashExtension;
					} else if (settings.type == "media") {
						allowedUploadExtensions = setting.uploadMediaExtension;
					} else if (settings.type == "file") {
						allowedUploadExtensions = setting.uploadFileExtension;
					} else {
						allowedUploadExtensions = "jpg,jpeg,bmp,gif,png";
					}
					if ($.trim(allowedUploadExtensions) == "" || !new RegExp("^\\S.*\\.(" + allowedUploadExtensions.replace(/,/g, "|") + ")$", "i").test($browserUploadInput.val())) {
						$.info("类型不匹配!");
						return false;
					}
					$browserLoadingIcon.show();
					$browserForm.submit();
				});
				
				$browserFrame.load(function() {
					var text = "";
					var io = document.getElementById(browserFrameId);
					if(io.contentWindow) {
						text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
					} else if(io.contentDocument) {
						text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
					}
					if ($.trim(text) != "") {
						$browserLoadingIcon.hide();
						var vals = eval("("+text+")");
						var datas = $.parseJSON(vals);
						var isSuccess = false;
						if(datas != null && datas.length > 0) {
							var data = datas[0];
							if (data.hasSuccess) {
								if (settings.input != null) {
									settings.input.val(data.downUrl);
								} else {
	//								$browserButton.prev(":hidden").val(data.url);
									$("#"+settings.setValue).textbox('setValue',data.downUrl);
								}
								if (settings.callback != null && typeof settings.callback == "function") {
									settings.callback(data.downUrl);
								}
								cache = {};
								$browser.dialog("close");
							}else {
								isSuccess = false;
							}
						} else {
							isSuccess = false;
						}
						if(isSuccess) {
							$.info("上传失败!");
						}
					}
				});
		};
		
	})(jQuery);
	
};

/** 如果type为 info 返回 true 否则返回 false */
function messageType(type) {
	if(type == 'INFO') {
		return true; 
	}
	return false;
} 
/** 是否不为Null*/
function isNotNull(obj) {
	if(obj != null && obj != '' && obj != 'undefined' && typeof(obj) != 'undefined') {
		return true;
	}
	return false;
}


//错误消息打印
function erroMsg(msg) {
	$.info(msg);
	window.setTimeout(function(){
		window.history.back();
	}, 2000);
}

var cacheMap = new Map();
/** 缓存工具类*/
var cacheUtil = {
	/** 设置缓存值 如果存在则覆盖 isOverride 是否覆盖*/
	put : function (key,value,isOverride){
		if(!isNotNull(isOverride)) {
			isOverride = false;
		}
		if(isOverride) {
			cacheMap.put(key, value);
		}else{
			if(!this.isExists(key)) {
				cacheMap.put(key, value);
			}
		}
	},
	/** 根据Key获取Value 如果key 不存在 则返回Null*/
	get : function (key) {
		if(this.isExists(key)){
			return cacheMap.get(key);
		}
		return null;
	},
	/** 判断key是否存在*/
	isExists : function(key) {
		if(cacheMap.get(key) == null) {
			return false;
		}
		return true;
	},
	/** 清空缓存 */
	clearCache : function (key) {
		cacheMap.removeByKey(key);
	},
	/** 获取所有缓存数据值*/
	getValues : function () {
		return cacheMap.values();
	},
	/** 获取所有的缓存的键值*/
	getKeys : function() {
		return cacheMap.keys();
	},
	/** 清除所有的缓存*/
	clearCacheAll : function () {
		cacheMap = new Map();
	}
};

