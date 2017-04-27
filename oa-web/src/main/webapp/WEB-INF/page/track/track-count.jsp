<%--
  Created by IntelliJ IDEA.
  User: Paul Chan
  Date: 2017/4/26
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<script src="${base}/js/track/track-count.js"></script>
<script src="${base}/js/track/track.js"></script>
<script src="${base}/js/customer/customer.js"></script>
<script src="${base}/js/track/track-service.js"></script>
<style>
    .control-label{
        word-break : keep-all;
    }
    #modal-add-cus .form-control{
        height: 30px !important;
        padding: 3px 12px !important;
    }
    #modal-add-cus .form-group{
        margin-bottom: 5px!important;
    }
</style>

<title>量化报表</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content-header">
    <h1>
        量化报表
    </h1>
    <ol class="breadcrumb">
        <li><a href="${base}/admin/masterDatas/productMaster.jhtml?type=productMasterInfo"><i
                class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">量化报表</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                        </div>
                    </div>
                    <!--高级搜索框-->
                    <div id="advancedSearch" hidden>
                        <div class="form-group">
                            <label  class="control-sidebar-subheading">跟踪时间</label>
                            <input id="rangeDate" name="rangeDate" class="form-control" />
                        </div>
                    </div>
                    <table class="table table-bordered table-striped" id="track-count-table">
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>