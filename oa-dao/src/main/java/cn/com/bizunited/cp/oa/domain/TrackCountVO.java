package cn.com.bizunited.cp.oa.domain;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/27-0:00
 */
public class TrackCountVO {

    private String date;
    private Integer trackCount;
    private Integer netTrackCount;
    private Integer telTrackCount;
    private Integer netTeachTrackCount;
    private Integer userId;
    private String username;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getNetTrackCount() {
        return netTrackCount;
    }

    public void setNetTrackCount(Integer netTrackCount) {
        this.netTrackCount = netTrackCount;
    }

    public Integer getTelTrackCount() {
        return telTrackCount;
    }

    public void setTelTrackCount(Integer telTrackCount) {
        this.telTrackCount = telTrackCount;
    }

    public Integer getNetTeachTrackCount() {
        return netTeachTrackCount;
    }

    public void setNetTeachTrackCount(Integer netTeachTrackCount) {
        this.netTeachTrackCount = netTeachTrackCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
