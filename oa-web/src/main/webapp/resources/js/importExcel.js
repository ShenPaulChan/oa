/*$(function(){
	appendSrcJs(Type.CSS,"../../resources/admin/css/importExcel.css");
});
//类型
var Type={JS:0,CSS:1};
function appendSrcJs (type,srcVal) {
	var bodyElement = document.getElementsByTagName("body")[0];
	var createElments = null;
	switch (type) {
		case 0:
			var typeElement = "text/javascript";
			createElments = createElment("script");
			setElmentAttribute(createElments,"type",typeElement);
			setElmentAttribute(createElments,"src",srcVal);
			break;
		default:
			var typeElement = "text/css";
			createElments = createElment("link");
			setElmentAttribute(createElments,"type",typeElement);
			setElmentAttribute(createElments,"href",srcVal);
			setElmentAttribute(createElments,"rel","stylesheet");
			break;
	}
	bodyElement.appendChild(createElments);
}

//创建节点
var createElment = function(name){
	return document.createElement(name);
};
//设置属性
var setElmentAttribute = function(element,name,val) {
	element.setAttribute(name, val);
};

if(jQuery) {
	(function($){
		$.fn.importUtil = function(options) {
			var _default = {
				importUrl : '../../admin/commonImportExcel/commomImportExcel.jhtml',
				definedInterface : '',
				definedFunction : '',
				startImportRow : 0,//从第几行开始进行导入
				endImportCol : 'B',//第几行结束
				sheetName : '', //excel 表的第几个sheet名称 进行导入
				type : 'all', //默认all  all:全部写入 (如果某一条数据不匹配:忽略该条数据 继续导入)  直接写表(数据库表)
				//once :一次性 (如果某一条数据不匹配:不允许导入) 一次性 : 临时表完成
				validColumn :[
				  {	index:1,        //参数
					className : 'com.bozhi.con.Test', // 完全报名
				    columns:
					[
					 {	fieldName : '', //与实体的的字段名称对应
						 fieldType : String,      // 对应的类型
						 isRequired: true//default:false:是否认必填 默认不必填
					},
					{
						isTransient : true//defalt:false (是否临时,有些Excel 里面的字段在 表中不存在,临时占位) 
					},
					{
						fieldName :'',
						fieldType :'Date',
						format : [
							'yyyy-MM-dd',
							'yyyy-MM-dd hh:mm:ss',
							'yyyy/MM/dd hh:mm:ss'
			            ]
					}
					]
					}
				]
			};
			if(options != null && validFiled(options.definedFunction) &&  validFiled(options.definedInterface) && validFiled(options.validColumn) && options.validColumn.length > 0 ) {
				var descOptions = $.extend({}, _default, options || {});
				if(descOptions != null && descOptions.validColumn.length > 0) {
					var validColumns = descOptions.validColumn;
					for(var i = 0 ;i<validColumns.length;i++) {
						var setValidColumn = validColumns[i].columns;
						for(var j=0 ; j<setValidColumn.length ; j ++) {
							var column = setValidColumn[j];
							if(column.fieldType!=null&&column.fieldType.toLowerCase() == 'date' && (!validFiled (column.formart ) || column.formart.length==0)) {
								column.formart = _default.validColumn[0].columns[1].formart;
							}
						}
					}
				}
				$(this).on('click',function(){
					openWindow(descOptions);
				});
			}
		};
	})(jQuery);
}

var validFiled = function (vals) {
	if(vals != null && vals != '' && vals != 'undefined') {
		return true;
	}
	return false;
};

var openWindow = function (descOptions) {
	$.dialog({
		modal: true,
		content: '<form id="exportForm" method="post" enctype="multipart/form-data" target="ExcelUpload" style="width:100%;float:left;">'+
				'		<table width="500px;" class="input" border="0" id="top" align="center" cellpadding="4"                                           '+
				'			cellspacing="4">                                                                                                 '+
				'			<tr>                                                                                                             '+
				'				<td class="width1" align="right"><span style="color: red;">*</span>Excel文件:</td>                               '+
				'				<td class="width2"><input type="file" id="excelFiles" name="excelFiles" accept=".xls,.xlsx"/></td>                                '+
				'			    <td align="left">                                                                                            '+
				'					<input id="jsData" name="jsData" type="hidden">                                                                         '+
				'					<input id="save" type="button" class="button" value="导入" />                                                                 '+
				'				</td>                                                                                                          '+
				'			</tr>                                                                                                            '+
				'		</table>                                                                                                           '+
				'	</form>                                                                                                              '+
				'	<div id="div_run" style="font-family:"宋体";font-size:12px;float:left;">                                                                                                  '+
				'		<div id="div_reading">&nbsp;文件上传解析中,请稍候...</div>                                                                         '+
				'		<div id="div_reading_success"></div>                                                                               '+
				'		<div id="div_writeData">&nbsp;&nbsp;当前正在导入第[<span id="currentPosition"></span>]行记录</div>                           '+
				'	</div>                                                                                                               '+
				'		<div id="err_msg" style="overflow: auto; height: 320px;width: 600px;">&nbsp;</div>                                                                                     '+
				'	<div>                                                                                                                '+
				'		<div id="div_finishData">                                                                                          '+
				'			<div><span id="finishData"></span></div>                                                                         '+
				'		</div>                                                                                                             '+
				'	</div>                                                                                                               '+
				'	<iframe id="importExcel" name="importExcel" src="../../resources/admin/returnMessage.jsp" >                                               '+
				'	</iframe>                                                                                   ',
		width: 600,
		height: "auto",
		title:"Excel导入",
		onCancel: null,
		onClose: null,
		onShow:function(){
			$(".dialogContent").css("height","450px");
			$(".xxDialog").css("margin-top","-110px");
			$("#excelFiles").bind('change',function(){
				var thisFileVal = $(this).val();
				var puxSubIndex = thisFileVal.toString().lastIndexOf(".");
				var puxName = thisFileVal.substring(puxSubIndex + 1);
				if (!/^(xls|xlsx)/.test(puxName)) {
					$("#excelFiles")[0].outerHTML = $("#excelFiles")[0].outerHTML;
					$.message("warn","请选择正确的Excel文件!");
				}
			});
			$("#save").on('click',function(){
				if ($("#excelFiles").val() == null
						|| $("#excelFiles").val() == '') {
					$.message("warn","请选择要导入的Excel文件!");
					return;
				}
				$(this).attr("disabled","true");
				$("#err_msg").html('');
//				$("#div_run div").html('');
				$("#div_run div").hide();
				$("#div_finishData").hide();
				//文件上传中,等待状态
				$("#div_reading").show();
				
				$("#jsData").val(JSON.stringify(descOptions));
				var $importForm  = $("#exportForm");
				$importForm.attr("action",descOptions.importUrl);
				$("#exportForm").submit();
			});
		},
		ok: null,
		cancel: '取消',
		onOk: function() {
		}
	});
	
};

function writeRowPosition(position){
	var $writeData = $("#div_writeData");
	if($writeData.is(":hidden")){
		$("#div_writeData").show();
	}
	$("#currentPosition").html(position);
}
//解析成功
function totalRows(totalRows,realtotalRows) {
	$("#div_reading_success").show();
	$("#div_reading_success").html("&nbsp;&nbsp;&nbsp;文件解析成功,本次应导入[<span style='color:red'>"+totalRows+"</span>]行记录,实导入[<span style='color:red'>"+realtotalRows+"</span>]行记录");
}
//导入成功
function successFinish(realtotalRows) {
	$("#div_finishData").show();
	$("#finishData").html("&nbsp;&nbsp;&nbsp;本次导入成功,已导入[<span style='color:red'>"+realtotalRows+"</span>]行数据!");
	$("#save").attr("disabled",false);
}
//导入失败
function erroRead(msg) {
	$("#div_reading_success").show();
	$("#div_reading_success").html(msg);
	$("#save").attr("disabled",false);
}
//导入失败
function erroFinish(realtotalRows) {
	$("#div_finishData").show();
	$("#finishData").html("&nbsp;&nbsp;&nbsp;<span style='color:red'>本次导入失败,已导入[0]行数据,请根据错误提示信息,修改之后,重新导入!</span>");
	$("#save").attr("disabled",false);
}


function error(msg,rowCount,colCount){
//	if(rowCount !=0 && colCount != 0) {
		$("#err_msg").append("<div>&nbsp;&nbsp;&nbsp;第[<font color='red'>"+rowCount+"</font>]行 第[<font color='red'>"+colCount+"</font>]列  出现错误  错误信息:"+ msg + "</div>");
//	}else {
//		$("#err_msg").append("<div>" + msg + "</div>");
//	}
}

function writeRowErro(msg,rowCount){
	$("#err_msg").append("<div>&nbsp;&nbsp;&nbsp;第[<font color='red'>"+rowCount+"</font>]行 出现错误  错误信息:"+ msg + "</div>");
}
// 导入结束
function end(){
	
}*/