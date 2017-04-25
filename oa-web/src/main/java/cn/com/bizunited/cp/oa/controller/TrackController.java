package cn.com.bizunited.cp.oa.controller;

import cn.com.bizunited.cp.common.utils.JsonUtils;
import cn.com.bizunited.cp.common.web.pagination.*;
import cn.com.bizunited.cp.common.web.utils.AdminLTEParseUtils;
import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.Customer;
import cn.com.bizunited.cp.oa.domain.base.Track;
import cn.com.bizunited.cp.oa.exception.AjaxException;
import cn.com.bizunited.cp.oa.service.AdminService;
import cn.com.bizunited.cp.oa.service.CustomerService;
import cn.com.bizunited.cp.oa.service.TrackService;
import cn.com.bizunited.cp.oa.vo.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @RequestMapping("/list/view")
    public ModelAndView trackListView(Long customerId){
        ModelAndView mav = new ModelAndView("track/list");
        Customer customer = customerService.selectEntityById(customerId);
        if(customer == null){
            throw new AjaxException(AccessStatus.NOT_FIND_CUSTOMER);
        }
        mav.addObject("customer", customer);
        mav.addObject("customerJson", JsonUtils.toJson(customer));
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
    @RequestMapping("/add")
    public AjaxJson addTrack(Track track){
        Integer userId = getCurrentUserId();
        Admin admin = adminService.selectEntityById(userId);
        trackService.addTrack(track, admin);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        return json;
    }

}
