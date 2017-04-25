package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.oa.domain.base.Customer;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/19-23:12
 */
public interface CustomerBeanMapper {

    List<Customer> findPage(Page page);

}
