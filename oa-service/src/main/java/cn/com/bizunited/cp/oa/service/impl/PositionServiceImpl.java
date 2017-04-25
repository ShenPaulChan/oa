package cn.com.bizunited.cp.oa.service.impl;

import cn.com.bizunited.cp.oa.domain.base.Position;
import cn.com.bizunited.cp.oa.mapper.PositionBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import cn.com.bizunited.cp.oa.mapper.base.PositionMapper;
import cn.com.bizunited.cp.oa.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/25-20:08
 */
@Service
public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService {

    @Autowired
    private PositionBeanMapper positionBeanMapper;

    @Autowired
    public void setBaseMapper(PositionMapper baseMapper) {
        super.setBaseMapper(baseMapper);
    }

    @Override
    public Position getUserPosition(Integer userId) {
        return positionBeanMapper.findPositionByUserId(userId);
    }
}
