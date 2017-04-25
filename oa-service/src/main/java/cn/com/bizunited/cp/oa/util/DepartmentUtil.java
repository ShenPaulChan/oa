package cn.com.bizunited.cp.oa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.bizunited.cp.oa.domain.base.Department;
import cn.com.bizunited.cp.oa.utils.StringUtil;

public class DepartmentUtil {

	private static final Log logger = LogFactory.getLog(DepartmentUtil.class);
	
	public static List<Department> parseRemoteData(JSONObject ret){
		
		List<Department> deptDtos = new ArrayList<>();
		Map<String, String> treePathMap = new HashMap<>();
		JSONArray items = (JSONArray)ret.get("items");
		recursive(items, deptDtos, treePathMap);
		
		return deptDtos;
	}
	
	public static void recursive(JSONArray items, List<Department> deptDtos, Map<String, String> treePathMap){
		
		if(items != null){
			
			for (Object object : items) {
				
				Map map = (Map)object;
				Department dept = new Department();
				dept.setDepNo((String)map.get("id"));
				dept.setDepName((String)map.get("name"));
				dept.setParentNo((String)map.get("parentId"));
				/** treepath = patrent.getTreePath() + "," + dept.getDepNo()*/
				if(StringUtil.isEmpty(dept.getParentNo()) 
						|| StringUtils.equals(dept.getParentNo(), "##")){
					
					dept.setTreePath(","+dept.getDepNo()+",");
				}else{
					
					dept.setTreePath(treePathMap.get(dept.getParentNo())+dept.getDepNo()+",");
				}
				logger.info(dept.getTreePath());
				//保存treePath
				treePathMap.put(dept.getDepNo(), dept.getTreePath());
				dept.setLelve((Integer)map.get("level"));
				deptDtos.add(dept);
				JSONArray iitems = (JSONArray)map.get("childrens");
				if(iitems != null && iitems.size() == 0){
					
					return;
				}
				recursive(iitems, deptDtos, treePathMap);
			}
		}
	}
}
