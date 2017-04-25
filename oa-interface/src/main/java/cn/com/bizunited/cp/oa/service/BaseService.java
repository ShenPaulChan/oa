package cn.com.bizunited.cp.oa.service;


import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Weston on 2016/6/24.
 */
public interface BaseService<T>{

    boolean insertEntity(T t);

    boolean deleteEntityById(Serializable id);

    boolean updateEntity(T t);

    boolean updateEntityKeySelective(T t);

    T selectEntityById(Serializable id);

}
