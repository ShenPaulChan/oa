package cn.com.bizunited.cp.oa.controller;

import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.domain.base.City;
import cn.com.bizunited.cp.oa.service.CityService;
import cn.com.bizunited.cp.oa.vo.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/21-23:05
 */
@Controller
public class CityController extends BaseController {

    @Autowired
    private CityService cityService;

    @ResponseBody
    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    public AjaxJson listCities(Integer pId){
        List<City> cities = cityService.listCityByPid(pId);
        AjaxJson json = new AjaxJson(AccessStatus.SERVER_SUCCESS);
        json.setData(cities);
        return json;
    }

}
