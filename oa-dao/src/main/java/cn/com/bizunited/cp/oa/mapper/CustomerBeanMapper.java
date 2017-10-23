package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.oa.domain.base.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/19-23:12
 */
public interface CustomerBeanMapper {

    List<Customer> findPage(Page page);

    Long checkMobile(@Param("customerId") Long customerId, @Param("mobile") String mobile);

    List<Customer> findByGroupId(@Param("cusGroupId") Long cusGroupId);

    String findAdminNameByMobile(@Param("mobile") String mobile);

}
