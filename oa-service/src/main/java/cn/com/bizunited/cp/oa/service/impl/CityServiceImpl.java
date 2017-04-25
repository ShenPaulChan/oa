package cn.com.bizunited.cp.oa.service.impl;

import cn.com.bizunited.cp.oa.domain.base.City;
import cn.com.bizunited.cp.oa.mapper.CityBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import cn.com.bizunited.cp.oa.mapper.base.CityMapper;
import cn.com.bizunited.cp.oa.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/21-22:37
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City> implements CityService {

    @Autowired
    private CityBeanMapper cityBeanMapper;

    @Autowired
    public void setBaseMapper(CityMapper baseMapper) {
        super.setBaseMapper(baseMapper);
    }

    @Override
    public List<City> listCityByPid(Integer pId) {
        return cityBeanMapper.findByPid(pId);
    }
}
