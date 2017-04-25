<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld" %>

<%
    String root = request.getContextPath();
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	request.getSession().setAttribute("base", base);
%>
<script type="text/javascript">
	var base = '${base}';
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${base}/components/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${base}/components/bootstrap/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${base}/components/bootstrap/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${base}/components/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${base}/components/plugins/datatables/dataTables.colReorder.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${base}/components/static/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${base}/components/static/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${base}/resources/js/jquery-ui/jquery-ui.min.css">
    <link rel="stylesheet" href="${base}/resources/js/jquery-ui/jquery-ui.structure.min.css">
    <link rel="stylesheet" href="${base}/resources/js/jquery-ui/jquery-ui.theme.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="<c:url value="/plugins/iCheck/square/blue.css"/>">
<link rel="stylesheet"
	href="<c:url value="/plugins/daterangepicker/daterangepicker-bs3.css"/>">
<link rel="stylesheet"
	href="<c:url value="/plugins/datepicker/datepicker3.css"/>">
<link rel="stylesheet"
	href="<c:url value="/plugins/bootstrap-table/bootstrap-table.css"/>">
<link rel="stylesheet"
	href="<c:url value="/plugins/jquery-confirm/jquery-confirm.css"/>">
<link rel="stylesheet"
  href="<c:url value="/components/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"/>">


<!-- jQuery 2.2.0 -->
<script src="${base}/components/dist/js/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${base}/components/bootstrap/js/bootstrap.min.js"></script>
	
<script src="<c:url value="/plugins/daterangepicker/moment.min.js" />" type="text/javascript" ></script>
<script src="<c:url value="/plugins/daterangepicker/daterangepicker.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/daterangepicker-zh-cn.js" />" type="text/javascript" ></script>
<script src="<c:url value="/plugins/datepicker/bootstrap-datepicker.js" />" type="text/javascript"></script>
<script src="<c:url value="/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js" />" type="text/javascript"></script>
<script src="<c:url value="/plugins/bootstrap-table/bootstrap-table.js" />" type="text/javascript" ></script>
<script src="<c:url value="/plugins/bootstrap-table/bootstrap-table-zh-CN.js" />" type="text/javascript" ></script>
<script src="<c:url value="/plugins/jquery-confirm/jquery-confirm.js"/>" type="text/javascript"></script>
<script src="<c:url value="/components/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/components/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"/>" type="text/javascript"></script>

<!-- DataTables -->
<script src="${base}/components/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script src="${base}/components/plugins/datatables/dataTables.bootstrap.js"></script>
<script src="${base}/components/plugins/datatables/dataTables.colReorder.js"></script>
<!-- SlimScroll -->
<script src="${base}/components/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${base}/components/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${base}/components/static/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/components/static/js/demo.js"></script>

<script src="${base}/components/static/js/pagingUtils.js"></script>
<script src="${base}/components/static/js/tabFillData.js"></script>

<script type="text/javascript" src="${base}/components/static/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery-ui/jquery-ui.min.js"></script>
<script src="<c:url value="/plugins/My97DatePicker/WdatePicker.js" />"></script>

<script src="<c:url value="/js/common/number-utils.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/request-utils.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/date-utils.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/daterangepicker-zh-cn.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/form-utils.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/dialog.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/search-utils.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/autocomplete-utils.js" />" type="text/javascript" ></script>
<script src="<c:url value="/js/common/regExp-utils.js" />" type="text/javascript" ></script>
<script type="text/javascript">
    function  setYearMonthDay(inputId){
        $("#" + inputId).datepicker({dateFormat: "yy-mm-dd",language: 'zh-CN'});
    }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
