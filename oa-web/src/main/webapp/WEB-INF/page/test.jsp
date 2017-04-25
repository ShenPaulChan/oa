<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
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
			<h2><span class="e-icon">^_^ </span><span>权限展示</span></h2>
			 <shiro:myHasPermission name="deshan:test:delete">
              <button type="button" class="btn btn-primary btn-flat" id="addUserBtn">删除按钮（有删除权限）</button>
        	</shiro:myHasPermission>
		</div>
	</body>
	
</html>