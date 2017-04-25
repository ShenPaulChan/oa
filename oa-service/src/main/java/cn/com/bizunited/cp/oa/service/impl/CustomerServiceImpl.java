package cn.com.bizunited.cp.oa.service.impl;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.City;
import cn.com.bizunited.cp.oa.domain.base.CusGroup;
import cn.com.bizunited.cp.oa.domain.base.Customer;
import cn.com.bizunited.cp.oa.exception.ServerException;
import cn.com.bizunited.cp.oa.mapper.CustomerBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.CustomerMapper;
import cn.com.bizunited.cp.oa.service.CityService;
import cn.com.bizunited.cp.oa.service.CusGroupService;
import cn.com.bizunited.cp.oa.service.CustomerService;
import cn.com.bizunited.cp.oa.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/19-23:10
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

    @Autowired
    private CustomerBeanMapper customerBeanMapper;
    @Autowired
    private CityService cityService;
    @Autowired
    private CusGroupService cusGroupService;

    @Autowired
    public void setBaseMapper(CustomerMapper baseMapper) {
        super.setBaseMapper(baseMapper);
    }

    @Override
    @Transactional
    public void saveCus(Customer customer, Admin admin) {
        setCusCity(customer);
        if(customer.getCustomerId() == null || customer.getCustomerId() == 0){
            customer.setUserId(admin.getId());
            customer.setCreateBy(admin.getUsername());
            insertEntity(customer);
        }else{
            Customer oldCustomer = selectEntityById(customer.getCustomerId());
            if(oldCustomer == null){
                throw new ServerException(AccessStatus.NOT_FIND_CUSTOMER);
            }
            oldCustomer.update(customer);
            updateEntity(oldCustomer);
        }
    }

    private void setCusCity(Customer customer){
        if(StringUtil.isNotEmpty(customer.getProvince())){
            City province = cityService.selectEntityById(Integer.parseInt(customer.getProvince()));
            if(province != null){
                customer.setProvinceName(province.getName());
            }
        }
        if(StringUtil.isNotEmpty(customer.getCity())){
            City city = cityService.selectEntityById(Integer.parseInt(customer.getCity()));
            if(city != null){
                customer.setCityName(city.getName());
            }
        }
    }

    @Override
    public Page<Customer> getPage(Pageable pageable) {
        Page page = new Page(pageable);
        List<Customer> customers = customerBeanMapper.findPage(page);
        page.setRows(customers);
        return page;
    }

    @Override
    @Transactional
    public void updateCusGroup(Long customerId, Long cusGroupId) {
        Customer customer = selectEntityById(customerId);
        if(customer == null){
            throw new ServerException(AccessStatus.NOT_FIND_CUSTOMER);
        }
        if(cusGroupId != null && cusGroupId > 0){
            CusGroup group = cusGroupService.selectEntityById(cusGroupId);
            if(group == null){
                throw new ServerException(AccessStatus.NOT_FIND_GROUP);
            }
            customer.setGroupId(group.getCusGroupId());
            customer.setGroupName(group.getName());
            updateEntityKeySelective(customer);
        }
    }
}
