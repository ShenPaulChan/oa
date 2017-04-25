/**
 * @Description:
 * @ClassName: com.biz.omsadmin.dto.AdminDto
 * @author: Omar(OmarZhang)
 * @date: 2016年4月28日 下午4:05:06
 */
package cn.com.bizunited.cp.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户DTO
 * @Description:
 * @ClassName: com.biz.omsadmin.dto.AdminDto
 * @author: Omar(OmarZhang)
 * @date: 2016年4月28日 下午4:05:06
 *
 */
public class AdminDto implements Serializable{

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    /** 用户名*/
    private String username;
    /** 用户密码*/
    private String userpwd;
    /** 最后一次登录IP*/
    private String lastloginip;
    /** 最后一次登录时间*/
    private Date lastlogintime;
    /** 创建时间*/
    private Date createtime;
    /** 状态*/
    private Integer status;
    /** 部门id*/
    private Integer deptid;
    /** 部门名称*/
    private String deptname;
    /** 角色名称*/
    private String rolenames;
    /** 职位名称*/
    private String posnames;
    /** 电话号码*/
    private String phone;
    /** 操作平台*/
    private Integer os;
    /** 访问标识*/
    private String accesstoken;
    /** 手机端推送标志*/
    private String pushFlag;

    public String getRolenames() {
        return rolenames;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    public String getPosnames() {
        return posnames;
    }

    public void setPosnames(String posnames) {
        this.posnames = posnames;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 用户名*/
    public String getUsername() {
        return this.username;
    }

    /** 设置 用户名*/
    public void setUsername(String username) {
        this.username = username;
    }

    /** 获取 用户密码*/
    public String getUserpwd() {
        return this.userpwd;
    }

    /** 设置 用户密码*/
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /** 获取 最后一次登录IP*/
    public String getLastloginip() {
        return this.lastloginip;
    }

    /** 设置 最后一次登录IP*/
    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    /** 获取 最后一次登录时间*/
    public Date getLastlogintime() {
        return this.lastlogintime;
    }

    /** 设置 最后一次登录时间*/
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /** 获取 创建时间*/
    public Date getCreatetime() {
        return this.createtime;
    }

    /** 设置 创建时间*/
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(String pushFlag) {
        this.pushFlag = pushFlag;
    }
}
