package cn.com.bizunited.cp.oa.service.impl;

import cn.com.bizunited.cp.oa.domain.base.Department;
import cn.com.bizunited.cp.oa.mapper.DepartmentBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import cn.com.bizunited.cp.oa.mapper.base.DepartmentMapper;
import cn.com.bizunited.cp.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/25-20:19
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    @Autowired
    private DepartmentBeanMapper departmentBeanMapper;

    @Autowired
    public void setBaseMapper(DepartmentMapper baseMapper) {
        super.setBaseMapper(baseMapper);
    }

    @Override
    public List<Department> listSubDepartment(Department department) {
        return departmentBeanMapper.findDepartmentsByParentDepNo(department.getDepNo());
    }
}
