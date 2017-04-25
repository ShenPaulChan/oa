package cn.com.bizunited.cp.oa.controller;

import cn.com.bizunited.cp.common.web.pagination.*;
import cn.com.bizunited.cp.common.web.utils.AdminLTEParseUtils;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.Department;
import cn.com.bizunited.cp.oa.domain.base.Position;
import cn.com.bizunited.cp.oa.service.AdminService;
import cn.com.bizunited.cp.oa.service.DepartmentService;
import cn.com.bizunited.cp.oa.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/25-23:51
 */
@Controller
@RequestMapping("/oa/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;

    @ResponseBody
    @RequestMapping(value = "/user/colleague/page")
    public DataTablesView getUserColleaguePage(Integer start, Integer length, Boolean isCount, HttpServletRequest request){
        Integer userId = getCurrentUserId();
        List<Department> departments = new ArrayList<>();
        Admin admin = adminService.selectEntityById(userId);
        Department department = departmentService.selectEntityById(admin.getDeptid());
        departments.add(department);
        Position position = positionService.getUserPosition(admin.getId());
        if(cn.com.bizunited.cp.oa.commons.enums.Position.isLeader(position.getPosCode())){
            List<Department> subDepts = departmentService.listSubDepartment(department);
            if(subDepts != null) departments.addAll(subDepts);
        }
        Map<String,String> parseMap = new HashMap<String,String>();
        parseMap.put("customerId", "tc.CUSTOMER_ID");
        //排序
        CommonSort sort = AdminLTEParseUtils.getSortByRequest(request, parseMap);
        //搜索
        CommonSearch commonSearch = AdminLTEParseUtils.getSearchByRequest(request);
        //高级搜索
        Map<String,Object>  advancedMap = AdminLTEParseUtils.getAdvancedSearchByRequest(request,commonSearch,null);
        advancedMap.put("userId", userId);
        advancedMap.put("depts", departments);
        Pageable pageable = new Pageable(start, length,isCount,commonSearch,sort,advancedMap);
        Page<Admin> page = adminService.getUserColleaguePage(pageable);
        return new DataTablesView(page);
    }

}
