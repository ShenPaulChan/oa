package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.oa.domain.base.CusGroup;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CusGroupBeanMapper {

    List<CusGroup> findUserCusGroups(@Param("userId") Integer userId);


}