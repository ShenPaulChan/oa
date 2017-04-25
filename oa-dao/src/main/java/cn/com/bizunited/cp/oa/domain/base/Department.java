package cn.com.bizunited.cp.oa.domain.base;

import java.util.Date;

public class Department {
    private Integer depId;

    private String depNo;

    private String depName;

    private String depCurator;

    private String depPhone;

    private String depAddress;

    private Integer depStatus;

    private Date createTime;

    private Integer lelve;

    private Integer isLifeHouse;

    private Integer erpFlag;

    private String parentNo;

    private String treePath;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo == null ? null : depNo.trim();
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public String getDepCurator() {
        return depCurator;
    }

    public void setDepCurator(String depCurator) {
        this.depCurator = depCurator == null ? null : depCurator.trim();
    }

    public String getDepPhone() {
        return depPhone;
    }

    public void setDepPhone(String depPhone) {
        this.depPhone = depPhone == null ? null : depPhone.trim();
    }

    public String getDepAddress() {
        return depAddress;
    }

    public void setDepAddress(String depAddress) {
        this.depAddress = depAddress == null ? null : depAddress.trim();
    }

    public Integer getDepStatus() {
        return depStatus;
    }

    public void setDepStatus(Integer depStatus) {
        this.depStatus = depStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLelve() {
        return lelve;
    }

    public void setLelve(Integer lelve) {
        this.lelve = lelve;
    }

    public Integer getIsLifeHouse() {
        return isLifeHouse;
    }

    public void setIsLifeHouse(Integer isLifeHouse) {
        this.isLifeHouse = isLifeHouse;
    }

    public Integer getErpFlag() {
        return erpFlag;
    }

    public void setErpFlag(Integer erpFlag) {
        this.erpFlag = erpFlag;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo == null ? null : parentNo.trim();
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath == null ? null : treePath.trim();
    }
}