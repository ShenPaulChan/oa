<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<link rel="stylesheet" href="${base}/resources/css/reset.css">
<script src="${base}/resources/js/refreshAction.js"></script>
<script src="${base}/plugins/jQuery-fileupload/jquery.ui.widget.js"></script>
<script src="${base}/plugins/jQuery-fileupload/jquery.iframe-transport.js"></script>
<script src="${base}/plugins/jQuery-fileupload/jquery.fileupload.js"></script>
<script type="text/javascript">
    var setFileUploadEvent = function (fileInputId, sucessSpan, aspan, reUploadBtn,uploadUrl,uploadFileName) {
        var url = '${base}' + '/upload/uploadFile.jhtml?random=' + Math.random();
        $("#" + fileInputId).fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                $("#" + uploadFileName).val(data.files[0].name);
                $("#" + fileInputId).hide();
                reUploadBtn.show();
                //给传给后台的input赋值
                $("#" + uploadUrl).val(data.result.data);
                reUploadBtn.on("click",function(){
                    $("#" + fileInputId).show();
                    reUploadBtn.hide();
                    aspan.html("");
                })
                sucessSpan.html('upload success!');
                aspan.attr('href',data.result.data);
//                alert(data.result.data);
                aspan.html("  点此下载 or  ");
                loadingCircleDisappear();
            },
            fail: function (e, data) {
                sucessSpan.html('upload failed!');
                loadingCircleDisappear();
            }
        }).on('fileuploadadd', function (e, data) {

            loadingCircle();
        });
    };

</script>