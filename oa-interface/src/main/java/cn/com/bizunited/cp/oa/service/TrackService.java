package cn.com.bizunited.cp.oa.service;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.domain.base.Track;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/24-0:41
 */
public interface TrackService extends BaseService<Track> {

    void addTrack(Track track, Admin admin);

    Page<Track> getPage(Pageable pageable);

    Page getCountTrackPage(Pageable pageable);
}
