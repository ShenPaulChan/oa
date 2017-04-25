package cn.com.bizunited.cp.oa.domain.base;

import java.util.Date;

public class CusGroup {
    private Long cusGroupId;

    private String name;

    private Date createTime;

    private String createBy;

    private Integer userId;

    public Long getCusGroupId() {
        return cusGroupId;
    }

    public void setCusGroupId(Long cusGroupId) {
        this.cusGroupId = cusGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}