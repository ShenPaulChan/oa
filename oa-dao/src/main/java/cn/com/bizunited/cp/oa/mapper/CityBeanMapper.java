package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.oa.domain.base.City;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityBeanMapper {

    List<City> findByPid(@Param("pId") Integer pId);

}