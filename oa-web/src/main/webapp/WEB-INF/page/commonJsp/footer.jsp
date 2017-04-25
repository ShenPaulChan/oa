<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<script src="${base}/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<script src="${base}/components/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/components/plugins/datepicker/bootstrap-datepicker.js"></script>
<!--<script src="${base}/components/plugins/My97DatePicker/WdatePicker.js"></script>-->
<script src="${base}/components/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<!-- SlimScroll -->
<script src="${base}/components/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${base}/components/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${base}/components/static/js/app.min.js"></script>

<script src="${base}/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/components/static/js/demo.js"></script>
<script src="${base}/components/static/js/pagingUtils.js"></script>
<script type="text/javascript" src="${base}/components/static/js/bootstrapValidator.min.js"></script>
<script src="${base}/components/dist/js/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${base}/resources/js/ztreeCommon.js" ></script>

<script src="${base}/components/plugins/My97DatePicker/WdatePicker.js"></script>
<!-- DataTables -->
<script src="${base}/components/plugins/bootstrap-table/bootstrap-table.js" type="text/javascript"></script>
<script src="${base}/components/plugins/bootstrap-table/bootstrap-table-zh-CN.js" type="text/javascript"></script>
<script src="${base}/js/common/utils.js" type="text/javascript"></script>
<script src="${base}/js/common/request-utils.js" type="text/javascript"></script>
<script type="text/javascript">
    function  setYearMonthDay(inputId){
        $("#" + inputId).datepicker({dateFormat: "yy-mm-dd",language: 'zh-CN'});
    }
</script>