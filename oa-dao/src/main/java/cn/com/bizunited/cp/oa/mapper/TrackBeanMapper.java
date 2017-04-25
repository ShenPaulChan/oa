package cn.com.bizunited.cp.oa.mapper;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.oa.domain.base.Track;
import cn.com.bizunited.cp.oa.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrackBeanMapper {


    List<Track> getPage(Page<Track> page);
}