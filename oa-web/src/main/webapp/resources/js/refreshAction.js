/**
 * Created by think on 2016/7/18.
 */
//等待响应的转圈按钮
var loadingCircle = function(){
    $("body").append('<div id="loadingCircleBox" style="position: fixed;z-index: 100;left: 0;top: 0;width: 100%;height: 100%;background-color: rgba(0, 0, 0, 0.40);">'+
    '<div style="position: absolute;left: 50%;top: 50%;width: 90px;height: 90px;margin-left: -45px;margin-top: -45px;border-radius: 5px;background-color: rgba(0, 0, 0, 0.60)">'+
    '<span class="loading-circle" style="margin-top: 30px;"></span></div></div>');
};
var loadingCircleDisappear = function(){
    if($("#loadingCircleBox").length>0){
        $("#loadingCircleBox").remove()
    }
};
