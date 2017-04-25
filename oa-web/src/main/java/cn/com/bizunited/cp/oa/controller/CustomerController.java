package cn.com.bizunited.cp.oa.controller;

import cn.com.bizunited.cp.common.web.pagination.*;
import cn.com.bizunited.cp.common.web.utils.AdminLTEParseUtils;
import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.commons.enums.*;
import cn.com.bizunited.cp.oa.domain.base.*;
import cn.com.bizunited.cp.oa.domain.base.Position;
import cn.com.bizunited.cp.oa.exception.AjaxException;
import cn.com.bizunited.cp.oa.service.*;
import cn.com.bizunited.cp.oa.vo.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/19-21:20
 */
@Controller
@RequestMapping("/oa/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CusGroupService cusGroupService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson saveCus(Customer customer){
        Integer userId = getCurrentUserId();
        Admin admin = adminService.selectEntityById(userId);
        customerService.saveCus(customer, admin);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public AjaxJson getGroups(){
        Integer userId = getCurrentUserId();
        List<CusGroup> cusGroups = cusGroupService.listUserCusGroups(userId);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        json.setData(cusGroups);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/cusGroup/update", method = RequestMethod.POST)
    public AjaxJson updateCusGroup(Long customerId, Long cusGroupId){
        checkParameters(customerId);
        customerService.updateCusGroup(customerId, cusGroupId);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/groups/add", method = RequestMethod.POST)
    public AjaxJson addGroup(String groupName){
        checkParameters(groupName);
        Integer userId = getCurrentUserId();
        Admin admin = adminService.selectEntityById(userId);
        CusGroup cusGroup = cusGroupService.addCusGroup(groupName, admin);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        json.setData(cusGroup);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/groups/delete", method = RequestMethod.POST)
    public AjaxJson deleteGroup(Long cusGroupId){
        checkParameters(cusGroupId);
        cusGroupService.deleteEntityById(cusGroupId);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public AjaxJson transferCus(Integer userId, @RequestParam(defaultValue = "", value = "cusIds[]") List<Long> cusIds){
        checkParameters(userId);
        if(cusIds == null || cusIds.size() == 0){
            throw new AjaxException(AccessStatus.NOT_FIND_CUSTOMER);
        }
        Admin admin = adminService.selectEntityById(userId);
        customerService.transfer(admin, cusIds);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public AjaxJson getCusInfo(Long customerId){
        Customer customer = customerService.selectEntityById(customerId);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        json.setData(customer);
        return json;
    }

    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public ModelAndView customerListView(){
        Integer userId = getCurrentUserId();
        ModelAndView mav = new ModelAndView("costomer/list");
        List<City> cities = cityService.listCityByPid(0);
        List<CusGroup> cusGroups = cusGroupService.listUserCusGroups(userId);
        mav.addObject("cities", cities);
        mav.addObject("cusGroups", cusGroups);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public DataTablesView customerPage(Integer start, Integer length,
                                       @RequestParam(defaultValue = "1", required = false) Integer listType,
                                       Boolean isCount, HttpServletRequest request){
        Integer userId = getCurrentUserId();
        List<Department> departments = new ArrayList<>();
        Admin admin = adminService.selectEntityById(userId);
        Department department = departmentService.selectEntityById(admin.getDeptid());
        departments.add(department);
        Position position = positionService.getUserPosition(admin.getId());
        if(listType == 2 && cn.com.bizunited.cp.oa.commons.enums.Position.isLeader(position.getPosCode())){
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
        advancedMap.put("listType", listType);
        advancedMap.put("depts", departments);
        Pageable pageable = new Pageable(start, length,isCount,commonSearch,sort,advancedMap);
        Page<Customer> page = customerService.getPage(pageable);
        return new DataTablesView(page);
    }

}
