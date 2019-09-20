package com.ruoyi.project.system.role.service;

import java.util.List;
import java.util.Set;
import com.ruoyi.project.system.role.domain.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色业务层
 * 
 * @author ruoyi
 */
public interface IRoleService
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<Role> selectRolesByUserId(Long userId);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    public List<Role> selectRoleAll();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public boolean deleteRoleById(Long roleId);

    /**
     * 批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteRoleByIds(String ids) throws Exception;

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(Role role,HttpServletRequest request);

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(Role role,HttpServletRequest request);

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRule(Role role);

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleNameUnique(Role role,HttpServletRequest request);

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleKeyUnique(Role role,HttpServletRequest request);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 角色状态修改
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int changeStatus(Role role);

    /**
     * 根据公司ID查询角色
     *
     * @param companyId 公司ID
     * @return 角色列表
     */
    public List<Role> selectRolesByCompanyId(Long companyId,HttpServletRequest request);

    /**
     * 通过公司id查询该公司所有角色
     * @param companyId
     * @return
     */
    public List<Role> selectRoleAllByCompanyId(long companyId);

    /**
     * 查询系统管理员的角色,不包括管理员
     * @return
     */
    List<Role> selectSysRoles();
}
