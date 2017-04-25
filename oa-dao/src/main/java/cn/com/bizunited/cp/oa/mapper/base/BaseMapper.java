package cn.com.bizunited.cp.oa.mapper.base;

import java.io.Serializable;

/**
 * Created by Weston on 2016/6/24.
 */
public interface BaseMapper<T> {

    int deleteByPrimaryKey(Serializable id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Serializable id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
}
