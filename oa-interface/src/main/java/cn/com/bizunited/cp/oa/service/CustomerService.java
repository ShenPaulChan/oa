package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.Customer;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/19-23:08
 */
public interface CustomerService extends BaseService<Customer> {

    public Page<Customer> getPage(Pageable pageable);

    void saveCus(Customer customer, Admin admin);

    void updateCusGroup(Long customerId, Long cusGroupId);
}
