package cn.com.bizunited.cp.oa.domain.base;

import java.lang.reflect.Field;
import java.util.Date;

public class Customer {
    private Long customerId;

    private String name;

    private Integer type;

    private String mobile;

    private String mobile2;

    private String qq;

    private String qqName;

    private String qq2;

    private String qqName2;

    private String wx;

    private String wxName;

    private Integer wxStatus;

    private Integer sex;

    private String province;

    private String provinceName;

    private String city;

    private String cityName;

    private Integer quality;

    private Integer stockAge;

    private Integer recentEarning;

    private Integer risk;

    private Integer money;

    private Integer energy;

    private Integer problem;

    private Integer model;

    private Integer attitude;

    private Integer profession;

    private Integer intention;

    private Integer resource;

    private String remark;

    private Long groupId;

    private String groupName;

    private Integer userId;

    private Date createTime;

    private String createBy;


    public static void main(String[] args) {
        Field[] fields = Customer.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            System.out.println("this."+fieldName + " = customer.get"+fieldName+"();" );
        }
    }

    public void update(Customer customer){
        this.name = customer.getName();
        this.type = customer.getType();
        this.mobile = customer.getMobile();
        this.mobile2 = customer.getMobile2();
        this.qq = customer.getQq();
        this.qqName = customer.getQqName();
        this.qq2 = customer.getQq2();
        this.qqName2 = customer.getQqName2();
        this.wx = customer.getWx();
        this.wxName = customer.getWxName();
        this.wxStatus = customer.getWxStatus();
        this.sex = customer.getSex();
        this.province = customer.getProvince();
        this.provinceName = customer.getProvinceName();
        this.city = customer.getCity();
        this.cityName = customer.getCityName();
        this.quality = customer.getQuality();
        this.stockAge = customer.getStockAge();
        this.recentEarning = customer.getRecentEarning();
        this.risk = customer.getRisk();
        this.money = customer.getMoney();
        this.energy = customer.getEnergy();
        this.problem = customer.getProblem();
        this.model = customer.getModel();
        this.attitude = customer.getAttitude();
        this.profession = customer.getProfession();
        this.intention = customer.getIntention();
        this.resource = customer.getResource();
        this.remark = customer.getRemark();
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2 == null ? null : mobile2.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getQqName() {
        return qqName;
    }

    public void setQqName(String qqName) {
        this.qqName = qqName == null ? null : qqName.trim();
    }

    public String getQq2() {
        return qq2;
    }

    public void setQq2(String qq2) {
        this.qq2 = qq2 == null ? null : qq2.trim();
    }

    public String getQqName2() {
        return qqName2;
    }

    public void setQqName2(String qqName2) {
        this.qqName2 = qqName2 == null ? null : qqName2.trim();
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    public Integer getWxStatus() {
        return wxStatus;
    }

    public void setWxStatus(Integer wxStatus) {
        this.wxStatus = wxStatus;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getStockAge() {
        return stockAge;
    }

    public void setStockAge(Integer stockAge) {
        this.stockAge = stockAge;
    }

    public Integer getRecentEarning() {
        return recentEarning;
    }

    public void setRecentEarning(Integer recentEarning) {
        this.recentEarning = recentEarning;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Integer getProfession() {
        return profession;
    }

    public void setProfession(Integer profession) {
        this.profession = profession;
    }

    public Integer getIntention() {
        return intention;
    }

    public void setIntention(Integer intention) {
        this.intention = intention;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

}