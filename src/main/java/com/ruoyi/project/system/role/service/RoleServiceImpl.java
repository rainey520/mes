package com.ruoyi.project.system.role.service;

import java.util.*;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.domain.RoleDept;
import com.ruoyi.project.system.role.domain.RoleMenu;
import com.ruoyi.project.system.role.mapper.RoleDeptMapper;
import com.ruoyi.project.system.role.mapper.RoleMapper;
import com.ruoyi.project.system.role.mapper.RoleMenuMapper;
import com.ruoyi.project.system.user.mapper.UserRoleMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色 业务层处理
 *
 * @author ruoyi
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleDeptMapper roleDeptMapper;

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    @DataScope(tableAlias = "u")
    public List<Role> selectRoleList(Role role) {
        User sysUser = JwtUtil.getTokenUser(ServletUtils.getRequest());
        role.setCompanyId(sysUser.getCompanyId().longValue());
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        List<Role> userRoles = roleMapper.selectRolesByUserId(userId);
        List<Role> roles = null;
        if(User.isAdmin(user) || User.isJty(user)){
            roles = roleMapper.selectSysRoles(user.getCompanyId());
        }else{
            roles = roleMapper.selectRolesByCompany(user.getCompanyId());
        }
        for (Role role : roles) {
            for (Role userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 根据公司ID查询角色
     *
     * @param companyId 公司ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByCompanyId(Long companyId,HttpServletRequest request) {
        List<Role> userRoles = roleMapper.selectRolesByCompanyId(companyId);
        List<Role> roles = selectRoleAll();
        for (Role role : roles) {
            for (Role userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<Role> selectRoleAll() {
        return selectRoleList(new Role());
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public Role selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public boolean deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId) > 0 ? true : false;
    }

    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deleteRoleByIds(String ids) throws BusinessException {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            Role role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int insertRole(Role role,HttpServletRequest request) {
        User u = JwtUtil.getTokenUser(request);
        role.setCreateBy(u.getLoginName());
        // 由管理员创建的角色
        if (User.isAdmin(u)) {
            role.setCompanyId(u.getCompanyId().longValue());
        } else {
            // 非系统管理员创建的角色设置对应公司的id属性
            role.setCompanyId(u.getCompanyId().longValue());
        }
        // 新增角色信息
        roleMapper.insertRole(role);
        ShiroUtils.clearCachedAuthorizationInfo();
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(Role role,HttpServletRequest request) {
        User u = JwtUtil.getTokenUser(request);
        role.setUpdateBy(u.getLoginName());
        // 修改角色信息
        roleMapper.updateRole(role);
        ShiroUtils.clearCachedAuthorizationInfo();
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRule(Role role) {
        role.setUpdateBy(ShiroUtils.getLoginName());
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(Role role) {
        int rows = 1;
        // 新增用户与角色管理
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        for (Long menuId : role.getMenuIds()) {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(Role role) {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<RoleDept> list = new ArrayList<RoleDept>();
        for (Long deptId : role.getDeptIds()) {
            RoleDept rd = new RoleDept();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0) {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(Role role,HttpServletRequest request) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        Map<String, Object> mmap = new HashMap<>();
        mmap.put("roleName", role.getRoleName());
        User u = JwtUtil.getTokenUser(request);
        if (!User.isAdmin(u)) {
            mmap.put("companyId", u.getUserId());
        }
        Role info = roleMapper.checkRoleNameUnique(mmap);
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(Role role,HttpServletRequest request) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        Integer companyId = null;
        User u = JwtUtil.getTokenUser(request);
        if (!User.isAdmin(u)) { // 非系统用户
            companyId = u.getCompanyId();
        }
        Role info = roleMapper.checkRoleKeyUnique(role.getRoleKey(),companyId);
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 角色状态修改
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int changeStatus(Role role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 根据公司id查询该公司所有的角色
     * @param companyId
     * @return
     */
    @Override
    public List<Role> selectRoleAllByCompanyId(long companyId) {
        return roleMapper.selectRolesByCompanyId(companyId);
    }
    /***************************SOP独立版本修改点******************************/

    /**
     * 查询系统管理员的角色,不包括管理员
     * @return
     */
    @Override
    public List<Role> selectSysRoles() {
        User user = JwtUtil.getTokenUser(ServletUtils.getRequest());
        return roleMapper.selectSysRoles(user.getCompanyId());
    }
}
