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
<script src="${base}/js/customer/customer-list.js"></script>
<script src="${base}/js/customer/customer.js"></script>
<script src="${base}/js/customer/customer-service.js"></script>
<script src="${base}/js/city/city-service.js"></script>
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

    <title>客户管理</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content-header">
    <h1>
        客户管理
    </h1>
    <ol class="breadcrumb">
        <li><a href="${base}/admin/masterDatas/productMaster.jhtml?type=productMasterInfo"><i
                class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">客户列表</li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group add-inputSelect-style col-md-1">
                                <select name="cus-source" class="form-control">
                                    <option value="1">本人</option>
                                    <option value="2">团组</option>
                                </select>
                            </div>
                            <button type="button" id="btn-show-add-cus" style="float: right" class="btn btn-primary">
                                录入客户
                            </button>
                        </div>
                    </div>
                    <!--高级搜索框-->
                    <div id="advancedSearch" hidden>
                        <div class="form-group add-inputSelect-style">
                            <label  class="control-sidebar-subheading">客户类型</label>
                            <select name="type" class="form-control">
                                <option value="">所有</option>
                                <option value="1">准客户</option>
                                <option value="2">意向客户</option>
                                <option value="3">一般客户</option>
                                <option value="4">未有意向客户</option>
                                <option value="5">本地化客户</option>
                                <option value="6">无效客户</option>
                                <option value="7">成交客户</option>
                                <option value="8">黑名单（同行）</option>
                            </select>
                        </div>
                        <div class="form-group add-inputSelect-style">
                            <label  class="control-sidebar-subheading">客户姓名</label>
                            <input  class="form-control" id="query_cusName" name="name" placeholder=""/>
                        </div>
                        <div class="form-group add-inputSelect-style">
                            <label  class="control-sidebar-subheading">自选分类</label>
                            <select name="groupId" class="form-control" >
                                <option value="">所有</option>
                                <c:forEach var="cusGroup" items="${cusGroups}">
                                    <option value="${cusGroup.cusGroupId}">${cusGroup.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <table class="table table-bordered table-striped" id="cus-table">
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="modal-add-cus" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    录入客户
                </h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <form class="form-horizontal" id="form-add-cus" role="form">
                            <input type="hidden" name="customerId" value="" class="form-control" id="customerId" />
                            <%--左边--%>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="username" class="col-sm-3 control-label">
                                        姓名:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="name" class="form-control" id="username" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="mobile" class="col-sm-3 control-label">
                                        手机:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="mobile" class="form-control" id="mobile" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="qq" class="col-sm-3 control-label">
                                        QQ号:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="qq" class="form-control" id="qq" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="qq2" class="col-sm-3 control-label">
                                        QQ号2:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="qq2" class="form-control" id="qq2" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="wx" class="col-sm-3 control-label">
                                        微信号:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="wx" class="form-control" id="wx" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="wxStatus" class="col-sm-2 control-label">
                                        微信号:
                                    </label>
                                    <div class="col-sm-10" style="padding-top: 0px" id="wxStatus">
                                        <input type="radio" style="display: none" name="wxStatus" value=""  />
                                        <input type="radio" name="wxStatus" value="0"  />未录入
                                        <input type="radio" name="wxStatus" value="1"  />已录入
                                        <input type="radio" name="wxStatus" value="2"  />不存在
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="province" class="col-sm-3 control-label">
                                        省份:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="province" class="form-control" id="province" >
                                            <option value=""></option>
                                            <c:forEach var="city" items="${cities}">
                                                <option value="${city.id}">${city.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="quality" class="col-sm-3 control-label">
                                        客户质量:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="quality" class="form-control" id="quality" >
                                            <option value=""></option>
                                            <option value="1">普通</option>
                                            <option value="2">优质</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="recentEarning" class="col-sm-3 control-label">
                                        近期收益:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="recentEarning" class="form-control" id="recentEarning" >
                                            <option value=""></option>
                                            <option value="1">大亏</option>
                                            <option value="2">小亏</option>
                                            <option value="3">持平</option>
                                            <option value="4">小赚</option>
                                            <option value="5">大赚</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="money" class="col-sm-3 control-label">
                                        资金量:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="money" class="form-control" id="money" >
                                            <option value=""></option>
                                            <option value="1">5万以下</option>
                                            <option value="2">5万-10万</option>
                                            <option value="3">10万-20万</option>
                                            <option value="4">20万-50万</option>
                                            <option value="5">50万以上</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="problem" class="col-sm-3 control-label">
                                        投资问题:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="problem" class="form-control" id="problem" >
                                            <option value=""></option>
                                            <option value="1">不会判断大盘</option>
                                            <option value="2">不会选股</option>
                                            <option value="3">买卖点把握不好</option>
                                            <option value="4">仓位控制不好</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="attitude" class="col-sm-3 control-label">
                                        态度:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="attitude" class="form-control" id="attitude" >
                                            <option value=""></option>
                                            <option value="1">友好</option>
                                            <option value="2">正常</option>
                                            <option value="3">恶劣</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="intention" class="col-sm-3 control-label">
                                        软件意向:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="intention" class="form-control" id="intention" >
                                            <option value=""></option>
                                            <option value="1">主动了解</option>
                                            <option value="2">愿意了解</option>
                                            <option value="3">不排斥</option>
                                            <option value="4">排斥</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <%--右边--%>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="type" class="col-sm-3 control-label">
                                        客户类型:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="type" value="3" class="form-control" id="type" >
                                            <option value=""></option>
                                            <option value="1">A.准客户</option>
                                            <option value="2">B.意向客户</option>
                                            <option value="3" selected>C.一般客户</option>
                                            <option value="4">D.未有意向客户</option>
                                            <option value="5">E.本地化客户</option>
                                            <option value="6">V.无效客户</option>
                                            <option value="7">N.成交客户</option>
                                            <option value="8">F.黑名单（同行）</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="mobile2" class="col-sm-3 control-label">
                                        手机2:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="mobile2" class="form-control" id="mobile2" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="qqName" class="col-sm-3 control-label">
                                        QQ昵称:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="qqName" class="form-control" id="qqName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="qqName2" class="col-sm-3 control-label">
                                        QQ昵称2:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="qqName2" class="form-control" id="qqName2" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="wxName" class="col-sm-3 control-label">
                                        微信昵称:
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="wxName" class="form-control" id="wxName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="sex" style="padding-top: 0px" class="col-sm-3 control-label">
                                        性别:
                                    </label>
                                    <div class="col-sm-8" id="sex">
                                        <input type="radio" style="display: none" name="sex" value=""  />
                                        <input type="radio" name="sex" value="1"  />男
                                        <input type="radio" name="sex" value="2"  />女
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="city" class="col-sm-3 control-label">
                                        城市:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="city" value="3" class="form-control" id="city" >
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="stockAge" class="col-sm-3 control-label">
                                        股龄:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="stockAge" value="3" class="form-control" id="stockAge" >
                                            <option value=""></option>
                                            <option value="1">1年</option>
                                            <option value="2">2年</option>
                                            <option value="3">3年</option>
                                            <option value="4">4年</option>
                                            <option value="5">5年</option>
                                            <option value="6">6年</option>
                                            <option value="7">7年</option>
                                            <option value="8">8年</option>
                                            <option value="9">9年</option>
                                            <option value="10">10年</option>
                                            <option value="11">10年以上</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="risk" class="col-sm-3 control-label">
                                        投资风格:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="risk" value="3" class="form-control" id="risk" >
                                            <option value=""></option>
                                            <option value="1">短线</option>
                                            <option value="2">中线</option>
                                            <option value="3">长线</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="energy" class="col-sm-3 control-label">
                                        看盘精力:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="energy" value="3" class="form-control" id="energy" >
                                            <option value=""></option>
                                            <option value="1">经常看盘</option>
                                            <option value="2">偶尔看盘</option>
                                            <option value="3">不怎么看盘</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="model" class="col-sm-3 control-label">
                                        盈利模式:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="model" value="3" class="form-control" id="model" >
                                            <option value=""></option>
                                            <option value="1">无</option>
                                            <option value="2">有但不理想</option>
                                            <option value="3">有</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="profession" class="col-sm-3 control-label">
                                        职业:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="profession" value="3" class="form-control" id="profession" >
                                            <option value=""></option>
                                            <option value="1">退休人员</option>
                                            <option value="2">公务员</option>
                                            <option value="3">医生</option>
                                            <option value="4">教师</option>
                                            <option value="5">个体老板</option>
                                            <option value="6">学生</option>
                                            <option value="7">职业股民</option>
                                            <option value="8">公司职员</option>
                                            <option value="9">警察</option>
                                            <option value="10">国企职工</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="resource" class="col-sm-3 control-label">
                                        职业:
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="resource" value="3" class="form-control" id="resource" >
                                            <option value=""></option>
                                            <option value="1">东财</option>
                                            <option value="2">今日头条</option>
                                            <option value="3">企鹅、QQ公众平台</option>
                                            <option value="4">一点资讯</option>
                                            <option value="5">搜狐</option>
                                            <option value="6">新浪</option>
                                            <option value="7">微博</option>
                                            <option value="8">百度</option>
                                            <option value="9">同花顺</option>
                                            <option value="10">和讯</option>
                                            <option value="11">其他</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="remark" class="col-sm-2 control-label">
                                            备注:
                                        </label>
                                        <div class="col-sm-9" style="padding-left: 0px">
                                            <textarea id="remark" name="remark" rows="1" style="height: auto!important;" cols="50" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
                <button type="button" id="btn-add-cus" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>

    </div>

</div>

<div class="modal fade" id="modal-cus-group" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title">
                    自选分类
                </h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <form class="form-horizontal" id="form-cus-group" role="form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        客户姓名：
                                    </label>
                                    <div class="col-sm-10" >
                                        <input type="hidden" name="customerId" />
                                        <span id="span-cusName">陈加平</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">
                                        分类：
                                    </label>
                                    <div class="col-sm-8">
                                        <select name="cusGroupId" class="form-control" id="inputPassword3" >
                                            <option value="" ></option>
                                        </select>
                                        <div class="col-sm-8" style="margin-top: 10px;">
                                            <button type="button" id="btn-add-cus-group" class="btn btn-primary">新增自选</button>
                                            <button type="button" id="btn-del-group" class="btn btn-primary">删除当前分类</button>
                                        </div>
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
                <button type="button" id="btn-save-cus-group" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>

    </div>

</div>

<div class="modal fade" id="modal-add-cus-group" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title">
                    新增自选分类
                </h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <form class="form-horizontal" id="form-add-group" role="form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        自选分类：
                                    </label>
                                    <div class="col-sm-10" >
                                        <input type="text" name="groupName" class="form-control" id="inputEmail3" />
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
                <button type="button" id="btn-add-group" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>

    </div>

</div>

</body>
</html>
