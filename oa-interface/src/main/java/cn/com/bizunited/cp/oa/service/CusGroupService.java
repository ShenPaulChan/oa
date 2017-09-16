package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.CusGroup;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/23-19:15
 */
public interface CusGroupService extends BaseService<CusGroup> {


    List<CusGroup> listUserCusGroups(Integer userId);

    CusGroup addCusGroup(String groupName, Admin admin);

    void deleteCusGroup(Long cusGroupId);
}
