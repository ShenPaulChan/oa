<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>出错了</title>
	</head>
	<style type="text/css">
		.e-icon{
			font-family: "微软雅黑";
		}
		@media screen and (min-width:300px) and (max-width: 1024px){
			.error-pc-div{
				display: none;
			}
			body,html{
				margin: 0px;
				padding: 0px;
				background-color: #FFFFFF;
			}
            .error-phone-div{
            	width: 100%;
            	display: block;
            }
            .error-title{
            	font-size: 14px;
				background: #fff;
				width: 100%;
				color: #fff;
				background-color: #1E323D;
				text-align: center;
				padding: 15px 0px;
            }
            .error-phone-con{
            	padding: 150px 20px 20px 20px;
        		text-align: center;
        		background-color: #FFFFFF;
            }
            .error-phone-con p{
        		font-size: 14px;
        		color: #333;
        	}
        	.error-phone-con a{
        		background-color: cornflowerblue;
        		color: #FFF;
        		border-radius: 3px;
        		padding:4px 8px;
        		margin: 0 5px;
        		font-size: 12px;
        		text-decoration: none;
        	}
        	.error-phone-div h2{
        		font-size: 24px;
        		color: red;
        	}
        }
        @media screen and (min-width: 1025px){
        	.error-phone-div{
        		display: none;
        	}
        	body{
				margin: 0px;
				padding: 0px;
				background-color: #EFEFEF;
			}
        	.error-pc-div{
        		display: block;
        		width: 50%;
        		font-family: "微软雅黑";
        		height: 200px;
        		margin: 100px auto;
        		padding: 20px;
        		background-color: #FFFFFF;
        	}
        	.error-pc-div h2{
        		font-size: 24px;
        		color: red;
        		font-family: initial;
        	}
        	.error-pc-div p{
        		font-size: 16px;
        		color: #333;
        	}
        }
	</style>
	<body>
		<div class="error-pc-div ">
			<h2><span class="e-icon">^_^ </span><span>${message}</span></h2>
			<%-- <p>${message}，<span class="er-ss"></span>s后自动跳转到上一步，您也可以点击<a href="javascript:history.go(-1)">返回</a>跳转。</p> --%>
		</div>
		<div class="error-phone-div">
			<div class="error-title">出错了</div>
			<div class="error-phone-con">
				<h2><span class="e-icon">^_^ </span><span>${message}</span></h2>
				<%-- <p>${message}，<span class="er-ss"></span>s后自动跳转到上一步</p>
				<p>您也可以点击<a href="javascript:history.go(-1)">返回</a>跳转</p> --%>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		/* var countdown=5; 
		var val = document.getElementsByClassName("er-ss");
		function settime(val) { 
			if (countdown == 0) { 
				//window.history.go(-1);
			} else { 
			    var i;
			    for (i = 0; i < val.length; i++) {
			        val[i].innerHTML=countdown;
			    }
				countdown--; 
			} 
			setTimeout(function() { 
				settime(val);
			},1000) 
		} 
		settime(val); */

	</script>
</html>