package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.oa.domain.base.Position;
import org.apache.ibatis.annotations.Param;

public interface PositionBeanMapper {

    Position findPositionByUserId(@Param("userId") Integer userId);

}