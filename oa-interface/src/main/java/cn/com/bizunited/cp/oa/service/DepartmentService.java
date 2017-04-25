package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.oa.domain.base.Department;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/25-20:19
 */
public interface DepartmentService extends BaseService<Department> {

    List<Department> listSubDepartment(Department department);

}
