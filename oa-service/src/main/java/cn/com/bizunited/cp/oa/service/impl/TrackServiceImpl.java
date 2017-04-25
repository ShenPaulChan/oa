package cn.com.bizunited.cp.oa.service.impl;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.Customer;
import cn.com.bizunited.cp.oa.domain.base.Track;
import cn.com.bizunited.cp.oa.exception.ServerException;
import cn.com.bizunited.cp.oa.mapper.TrackBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import cn.com.bizunited.cp.oa.mapper.base.TrackMapper;
import cn.com.bizunited.cp.oa.service.CustomerService;
import cn.com.bizunited.cp.oa.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/24-0:43
 */
@Service
public class TrackServiceImpl extends BaseServiceImpl<Track> implements TrackService {

    @Autowired
    private TrackBeanMapper trackBeanMapper;
    @Autowired
    private CustomerService customerService;

    @Autowired
    public void setBaseMapper(TrackMapper baseMapper) {
        super.setBaseMapper(baseMapper);
    }

    @Override
    @Transactional
    public void addTrack(Track track, Admin admin) {
        Customer customer = customerService.selectEntityById(track.getCusId());
        if(customer == null){
            throw new ServerException(AccessStatus.NOT_FIND_CUSTOMER);
        }
        track.setUserId(admin.getId());
        track.setCusName(customer.getName());
        track.setCreateBy(admin.getUsername());
        insertEntity(track);
    }

    @Override
    public Page<Track> getPage(Pageable pageable) {
        Page<Track> page = new Page<Track>(pageable);
        List<Track> tracks = trackBeanMapper.getPage(page);
        page.setRows(tracks);
        return page;
    }
}
