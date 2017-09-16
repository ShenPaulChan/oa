package cn.com.bizunited.cp.oa.service.impl;

import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.CusGroup;
import cn.com.bizunited.cp.oa.mapper.CusGroupBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import cn.com.bizunited.cp.oa.mapper.base.CusGroupMapper;
import cn.com.bizunited.cp.oa.service.CusGroupService;
import cn.com.bizunited.cp.oa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/23-19:16
 */
@Service
public class CusGroupServiceImpl extends BaseServiceImpl<CusGroup> implements CusGroupService {

    @Autowired
    private CusGroupBeanMapper cusGroupBeanMapper;
    @Autowired
    private CustomerService customerService;

    @Autowired
    public void setBaseMapper(CusGroupMapper baseMapper) {
        super.setBaseMapper(baseMapper);
    }

    @Override
    public List<CusGroup> listUserCusGroups(Integer userId) {
        return cusGroupBeanMapper.findUserCusGroups(userId);
    }

    @Override
    @Transactional
    public CusGroup addCusGroup(String groupName, Admin admin) {
        CusGroup group = new CusGroup();
        group.setUserId(admin.getId());
        group.setCreateBy(admin.getUsername());
        group.setName(groupName);
        insertEntity(group);
        return group;
    }

    @Override
    @Transactional
    public void deleteCusGroup(Long cusGroupId) {
        CusGroup cusGroup = selectEntityById(cusGroupId);
        if(cusGroup == null){
            return;
        }
        deleteEntityById(cusGroupId);
        customerService.removeCusOfGroup(cusGroup);
    }


}
