package cn.com.bizunited.cp.oa.controller;

import cn.com.bizunited.cp.common.utils.JsonUtils;
import cn.com.bizunited.cp.common.web.pagination.*;
import cn.com.bizunited.cp.common.web.utils.AdminLTEParseUtils;
import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.commons.enums.*;
import cn.com.bizunited.cp.oa.domain.base.*;
import cn.com.bizunited.cp.oa.domain.base.Position;
import cn.com.bizunited.cp.oa.exception.AjaxException;
import cn.com.bizunited.cp.oa.service.*;
import cn.com.bizunited.cp.oa.utils.DateUtils;
import cn.com.bizunited.cp.oa.vo.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/23-23:20
 */
@Controller
@RequestMapping("/oa/track")
public class TrackController extends BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;

    @RequestMapping("/list/view")
    public ModelAndView trackListView(Long customerId, Integer start){
        ModelAndView mav = new ModelAndView("track/list");
        Customer customer = customerService.selectEntityById(customerId);
        if(customer == null){
            throw new AjaxException(AccessStatus.NOT_FIND_CUSTOMER);
        }
        mav.addObject("customer_page_start", start);
        mav.addObject("customer", customer);
        mav.addObject("customerJson", JsonUtils.toJson(customer));
        return mav;
    }

    @RequestMapping("/count/list/view")
    public ModelAndView trackCountListView(){
        ModelAndView mav = new ModelAndView("track/track-count");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/page")
    public DataTablesView getTrackPage(Integer start, Integer length,Long cusId, Boolean isCount,HttpServletRequest request){
        Map<String,String> parseMap = new HashMap<String,String>();
        //排序
        CommonSort sort = AdminLTEParseUtils.getSortByRequest(request, parseMap);
        //搜索
        CommonSearch commonSearch = AdminLTEParseUtils.getSearchByRequest(request);
        //高级搜索
        Map<String,Object>  advancedMap = AdminLTEParseUtils.getAdvancedSearchByRequest(request,commonSearch,null);
        advancedMap.put("cusId", cusId);
        Pageable pageable = new Pageable(start, length,isCount,commonSearch,sort,advancedMap);
        Page<Track> page = trackService.getPage(pageable);
        return new DataTablesView(page);
    }

    @ResponseBody
    @RequestMapping("/count/page")
    public DataTablesView countTrack(Integer start, Integer length,Boolean isCount,HttpServletRequest request){
        Integer userId = getCurrentUserId();
        Admin admin = adminService.selectEntityById(userId);
        List<Department> departments = new ArrayList<>();
        Department department = departmentService.selectEntityById(admin.getDeptid());
        departments.add(department);
        Position position = positionService.getUserPosition(admin.getId());
        boolean isLeader = cn.com.bizunited.cp.oa.commons.enums.Position.isLeader(position.getPosCode());
        if(isLeader){
            List<Department> subDepts = departmentService.listSubDepartment(department);
            if(subDepts != null) departments.addAll(subDepts);
        }
        Map<String,String> parseMap = new HashMap<String,String>();
        //排序
        CommonSort sort = AdminLTEParseUtils.getSortByRequest(request, parseMap);
        //搜索
        CommonSearch commonSearch = AdminLTEParseUtils.getSearchByRequest(request);
        //高级搜索
        Map<String,Object>  advancedMap = AdminLTEParseUtils.getAdvancedSearchByRequest(request,commonSearch,null);
        advancedMap.put("rangeDate", DateUtils.getRangeDate((String) advancedMap.get("rangeDate")));
        advancedMap.put("isLeader", isLeader);
        advancedMap.put("depts", departments);
        Pageable pageable = new Pageable(start, length,isCount,commonSearch,sort,advancedMap);
        Page page = trackService.getCountTrackPage(pageable);
        return new DataTablesView(page);
    }

    @ResponseBody
    @RequestMapping("/add")
    public AjaxJson addTrack(Track track){
        Integer userId = getCurrentUserId();
        Admin admin = adminService.selectEntityById(userId);
        trackService.addTrack(track, admin);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        return json;
    }

}
