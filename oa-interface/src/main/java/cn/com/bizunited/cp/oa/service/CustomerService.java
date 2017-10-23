package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.CusGroup;
import cn.com.bizunited.cp.oa.domain.base.Customer;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/19-23:08
 */
public interface CustomerService extends BaseService<Customer> {

    Page<Customer> getPage(Pageable pageable);

    void saveCus(Customer customer, Admin admin);

    void updateCusGroup(Long customerId, Long cusGroupId);

    void transfer(Admin admin, List<Long> cusIds);

    boolean checkMobile(Long customerId, String mobile);

    void removeCusOfGroup(CusGroup cusGroup);

	String getAdminNameByMobile(String mobile);
}
