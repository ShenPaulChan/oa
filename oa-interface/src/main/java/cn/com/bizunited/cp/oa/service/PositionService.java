package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.oa.domain.base.Position;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/25-20:07
 */
public interface PositionService extends BaseService<Position> {

    Position getUserPosition(Integer userId);

}
