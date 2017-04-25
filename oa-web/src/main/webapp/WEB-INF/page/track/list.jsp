<%--
  Created by IntelliJ IDEA.
  User: Paul Chan
  Date: 2017/4/19
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<script src="${base}/js/track/track-list.js"></script>
<script src="${base}/js/track/track.js"></script>
<script src="${base}/js/customer/customer.js"></script>
<script src="${base}/js/track/track-service.js"></script>
<script type="application/javascript">
    var customer_info = ${customerJson};
</script>
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

    <title>跟踪记录</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content-header">
    <h1>
        跟踪记录
    </h1>
    <ol class="breadcrumb">
        <li><a href="${base}/admin/masterDatas/productMaster.jhtml?type=productMasterInfo"><i
                class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">跟踪记录</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <button type="button" onclick="history.back();" class="btn btn-primary">
                                返回
                            </button>
                            <button type="button" id="btn-show-add-track" style="float: right" class="btn btn-primary">
                                录入跟踪
                            </button>
                        </div>
                    </div>
                    <!--高级搜索框-->
                    <div id="advancedSearch" hidden>

                    </div>
                    <table class="table table-bordered table-striped" id="track-table">
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="modal-add-track" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    录入跟踪
                </h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <form class="form-horizontal" id="form-add-track" role="form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        客户姓名：
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="cusId" value="${customer.customerId}" />
                                        <span id="span-cus-name">${customer.name}</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">
                                        客户类型：
                                    </label>
                                    <div class="col-sm-10">
                                        <select name="cusType" value="" class="form-control" id="inputPassword3" >
                                            <option value=""></option>
                                            <option value="1">A.准客户</option>
                                            <option value="2">B.意向客户</option>
                                            <option value="3">C.一般客户</option>
                                            <option value="4">D.未有意向客户</option>
                                            <option value="5">E.本地化客户</option>
                                            <option value="6">V.无效客户</option>
                                            <option value="7">N.成交客户</option>
                                            <option value="8">F.黑名单（同行）</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        计划下次跟踪时间：
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="text" name="nextTime" class="form-control"  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        跟踪步骤：
                                    </label>
                                    <div class="col-sm-10">
                                        <select name="step" value="" class="form-control" >
                                            <option value=""></option>
                                            <option value="1">首次沟通</option>
                                            <option value="2">早盘</option>
                                            <option value="3">基本信息</option>
                                            <option value="4">沟通介入</option>
                                            <option value="5">远程讲解</option>
                                            <option value="6">现场邀约</option>
                                            <option value="7">来过现场</option>
                                            <option value="8">提升</option>
                                            <option value="9">洽谈</option>
                                            <option value="10">其他</option>
                                            <option value="11">售前确认</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        跟踪类型：
                                    </label>
                                    <div class="col-sm-10">
                                        <select name="trackType" value="" class="form-control"  >
                                            <option value=""></option>
                                            <option value="1">电话跟踪</option>
                                            <option value="2">网络跟踪</option>
                                            <option value="3">远程讲解</option>
                                            <option value="4">现场讲解</option>
                                            <option value="5">成交总结</option>
                                            <option value="6">讲师指导</option>
                                            <option value="7">主管建议</option>
                                            <option value="8">售前回访</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        跟踪内容：
                                    </label>
                                    <div class="col-sm-10">
                                        <textarea class="form-control" rows="3" name="content"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
                <button type="button" id="btn-add-track" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>

    </div>

</div>

</body>
</html>
