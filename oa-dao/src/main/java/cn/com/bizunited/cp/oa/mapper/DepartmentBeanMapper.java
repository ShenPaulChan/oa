package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.oa.domain.base.Department;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentBeanMapper {


    List<Department> findDepartmentsByParentDepNo(@Param("depNo") String depNo);
}