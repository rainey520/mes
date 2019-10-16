package com.ruoyi.project.system.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.role.domain.Role;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**用户标记定义0为其他用户 */
    public static final String COMPANY_OTHER ="0";
    /**用户标记定义1代表公司管理员 */
    public static final String COMPANY_ADMIN ="1";

    /** 用户ID */
    //@Excel(name = "用户序号",type = Type.EXPORT)
    private Long userId;

    /** 部门ID */
    private Long deptId;

    /** 部门父ID */
    private Long parentId;

    /** 登录名称 */
    @Excel(name = "手机号")
    private String loginName;

    /** 用户名称 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱",type = Type.EXPORT)
    private String email;

    /** 手机号码 */
    //@Excel(name = "手机号码",type = Type.EXPORT)
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用",type = Type.EXPORT)
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆IP */
    private String loginIp;

    /** 最后登陆时间 */
    private Date loginDate;

    /** 部门对象 */
    private Dept dept;

    /** 角色集合 */
    private List<Role> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 用户标记 （1代表公司管理员 0代表其他用户） */
    private String tag;

    /**
     * 用户部门
     */
    private String userDepartment;
    /**
     * 用户职位
     */
    private String userPosition;

    /**
     * 用户登录标记 （1代表注册用户 0代表添加用户）
     */
    private String loginTag;

    /** 公司主键ID */
    private Integer companyId;

    /** 公司对象 */
    //@Excel(name = "公司名称", targetAttr = "comName", type = Type.EXPORT)
    private DevCompany devCompany;
    /** 公司名称 */
    private String comName;

    /** 所属行业 */
    private Integer industry;
    /** 设备类型 */
    private String devType;
    /** 设备号 */
    private String devCode;
    /** 用户等级 */
    private Integer grade;

    private int sign;//标记注册者
    /**
     * 语言版本
     */
    private Integer langVersion;

    public Integer getLangVersion() {
        return langVersion;
    }

    public void setLangVersion(Integer langVersion) {
        this.langVersion = langVersion;
    }

    public String getLoginTag() {
        return loginTag;
    }

    public void setLoginTag(String loginTag) {
        this.loginTag = loginTag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public DevCompany getDevCompany() {
        return devCompany;
    }

    public void setDevCompany(DevCompany devCompany) {
        this.devCompany = devCompany;
    }

    public Long getUserId()
    {
        return userId;
    }


    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {

        return false;
    }

    public static boolean isAdmin(User user)
    {
        return user != null && "3".equals(user.getTag());
    }

    public static boolean isJty(User user){
        return user != null && "2".equals(user.getTag());
    }

    public static boolean isSys(User user){
        return  user != null && "1".equals(user.getTag());
    }

    /**
     * 是否为公司创始人
     * @param user
     * @return
     */
    public static boolean isCompanyInit(User user){
        return user != null && user.getLoginName().equals(user.getCreateBy());
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    /**
     * 生成随机盐
     */
    public void randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        setSalt(hex);
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public Dept getDept()
    {
        if (dept == null)
        {
            dept = new Dept();
        }
        return dept;
    }

    public void setDept(Dept dept)
    {
        this.dept = dept;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", parentId=" + parentId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate=" + loginDate +
                ", dept=" + dept +
                ", roles=" + roles +
                ", roleIds=" + Arrays.toString(roleIds) +
                ", postIds=" + Arrays.toString(postIds) +
                ", tag='" + tag + '\'' +
                ", userDepartment='" + userDepartment + '\'' +
                ", userPosition='" + userPosition + '\'' +
                ", loginTag='" + loginTag + '\'' +
                ", companyId=" + companyId +
                ", devCompany=" + devCompany +
                ", comName='" + comName + '\'' +
                ", industry=" + industry +
                ", devType='" + devType + '\'' +
                ", devCode='" + devCode + '\'' +
                ", grade=" + grade +
                ", sign=" + sign +
                '}';
    }
}
