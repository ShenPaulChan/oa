<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
    .bar {
        height: 18px;
        background: green;
    }

    .progress {
        width: 100px;
        margin-top:3px;
        margin-left:5px;
        border: 1px solid #c7c4c4;
        float: left;
        height: 18px;
    }

    .progress-detail {
        margin-top: 3px;
        width: 35px;
        float: left;
        height: 18px;
        line-height: 18px;
    }
</style>
<script type="text/javascript">
    var setProgressValue = function (currentValue, bipId, msg) {
        if (currentValue == null || currentValue == '' || currentValue == 'null') {
            progressError(bipId, msg);
        }
        $('#prod' + bipId).html(currentValue + '%');
        $('#bar' + bipId).css('width', currentValue + '%');
    }
</script>