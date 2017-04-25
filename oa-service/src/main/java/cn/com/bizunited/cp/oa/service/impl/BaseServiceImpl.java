package cn.com.bizunited.cp.oa.service.impl;


import org.springframework.transaction.annotation.Transactional;

import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import cn.com.bizunited.cp.oa.service.BaseService;

import java.io.Serializable;

/**
 * Created by Weston on 2016/6/24.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseMapper<T> baseMapper;

    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    @Transactional
    public boolean insertEntity(T t) {
        return baseMapper.insertSelective(t)!=0;
    }

    @Override
    @Transactional
    public boolean deleteEntityById(Serializable id) {
        return baseMapper.deleteByPrimaryKey(id)!=0;
    }

    @Override
    @Transactional
    public boolean updateEntity(T t) {
      return  baseMapper.updateByPrimaryKey(t)!=0;

    }

    @Override
    @Transactional
    public boolean updateEntityKeySelective(T t) {
        return  baseMapper.updateByPrimaryKeySelective(t)!=0;

    }

    @Override
    public T selectEntityById(Serializable id) {
        return baseMapper.selectByPrimaryKey(id);
    }

}
