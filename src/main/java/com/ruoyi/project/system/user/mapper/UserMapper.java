package com.ruoyi.project.system.user.mapper;

import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserApp;
import com.ruoyi.project.system.user.domain.UserQrCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
public interface UserMapper
{
    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> selectUserList(User user);

    /**
     * 根据条件分页查询用户对象
     * 通过公司ID
     *
     * @param map 用户和公司所属公司id
     * @return 用户信息集合信息
     */
    public List<User> selectUserListByCompanyId(Map map);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    public User selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public User selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 通过登录手机号修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserByLoginName(User user);

    /**
     * 修改用户删除状态
     * @param id
     * @return
     */
    int updateUserDelFlag(@Param("id")int id,@Param("companyId")int companyId);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public User checkEmailUnique(String email);

    /**
     * 查询对应公司所有的员工信息
     * @return
     */
    List<User> selectComAllUser(@Param("comid")int comid);

    /**
     * 根据用户编号查询用户基本信息
     * @param user_id
     * @return
     */
    User selectUserInfoById(@Param("user_id")int user_id);

    /**
     * 查询用户二维码信息
     * @param user 用户信息
     * @return
     */
    List<UserQrCode> selectUserQrCode(User user);

    /**
     * app交互查询用户列表
     * @param companyId 公司id
     * @return 结果
     */
    List<UserApp> appSelectUserList(@Param("companyId") Integer companyId);

    /**
     * 更新用户语言版本
     * @param user 用户
     * @return 结果
     */
    int updateUserLangVersion(User user);
}
