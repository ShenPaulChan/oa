package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.oa.domain.base.City;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/21-22:35
 */
public interface CityService extends BaseService<City> {

    List<City> listCityByPid(Integer pId);

}
