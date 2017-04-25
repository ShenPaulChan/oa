//类型
//树形初始化
if(jQuery) {
	(function($){
		//初始树
		$.fn.zTreeInit = function(setting_temp) {
			var $this = $(this);
			try{
				$.ajax({
					url:setting_temp.url+'/resources/admin/menus/roleses_menus.js',
					async:false,
					dataType:'json',
					success:function(data){
						$.fn.zTree.init($this, setting_temp, data);
						return $.fn.zTree.getZTreeObj($this[0].id);
					}
				});
			}catch(e){
			}
		},
		//根据自己发送的请求路径发送json请求
		$.fn.myzTreeInit = function(setting_temp,dataJson) {
			var $this = $(this);
			try{
				$.fn.zTree.init($this, setting_temp, dataJson);
				return $.fn.zTree.getZTreeObj($this[0].id);
			}catch(e){
			}
		},
		//保存权限
		$.fn.shiroPermissionSave = function(zTree,hideElement,callBack){
			//var $this = $(this);
			//$this.bind("click",function(){
				if(zTree != null) {
					var sNodes = zTree.getCheckedNodes(true);
					var authorities = [];
					for(var i=0;i<sNodes.length;i++) {
						var node = sNodes[i];
						var permission = "";
						var permissionName = node.permission;
						if(permissionName != null) {
							if(node.getParentNode() != null && node.getParentNode().permission == null) {
								id = node.id;
								permission +=permissionName+":";
								permission += getCountNoHiddenNode(node);
								authorities.push(permission);
							}
						}
					}
					$('#'+hideElement).val(authorities.toString());
				}
				if((callBack != 'undefined' && callBack != null) && typeof(callBack) == 'function') {
					callBack();
				}
			//});
		},
		
		$.fn.shiroRoleSave = function(zTree,hideElement,callBack){
				if(zTree != null) {
					var sNodes = zTree.getCheckedNodes(true);
					var roles = [];
					for(var i=0;i<sNodes.length;i++) {
						var node = sNodes[i];
						var roleId = node.id;
						console.debug(roleId);
						if(roleId != null) {
							roles.push(roleId);
						}
					}
					$('#'+hideElement).val(roles.toString());
				}
				if((callBack != 'undefined' && callBack != null) && typeof(callBack) == 'function') {
					callBack();
				}
		}
	})(jQuery);
}
//获取个数 除开隐藏的节点
function getCountNoHiddenNode(node){
	if(node.children == null) {
		return "*";
	}
	var noHidecount = 0;
	var checkedCount = 0;
	var nodeAll = [];
	nodeAll = getChildren(nodeAll,node);
	var checkActionPermission = "";
	if(nodeAll != null && nodeAll.length >0) {
		for(var i= 1;i<nodeAll.length;i++) {
			var child = nodeAll[i];
			if(!child.isHidden){
				noHidecount ++;
				if(child.checked) {
					checkedCount ++;
					checkActionPermission +=child.permission+'|';
				}
			}
		}
	}
	if(checkedCount != 0 && noHidecount == checkedCount) {
		checkActionPermission = "*";
	}
	return checkActionPermission;
}

function getChildren(nodes,treeNode){
	 nodes.push(treeNode);
	 if (treeNode.isParent){
			for(var obj in treeNode.children){
				getChildren(nodes,treeNode.children[obj]);
			}
	    }
	 return nodes;
}

/**
 * 加载Json数据
 */

