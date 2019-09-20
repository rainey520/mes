package com.ruoyi.project.system.menu.mapper;

import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.domain.MenuApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单表 数据层
 * 
 * @author ruoyi
 */
public interface MenuMapper
{
    /**
     * 查询系统所有菜单（含按钮）
     * 
     * @return 菜单列表
     */
    public List<Menu> selectMenuAll();

    /**
     * 通过角色id查询系统所有菜单（含按钮）
     *
     * @return 菜单列表
     */
    public List<Menu> selectMenuAllByUserId(Long userId);
    
    /**
     * 查询系统正常显示菜单（不含按钮）
     * 
     * @return 菜单列表
     */
    public List<Menu> selectMenuNormalAll();
    
    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectMenuTree(Long roleId);

    /**
     * 查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<Menu> selectMenuList(Menu menu);

    /**
     * 根据用户角色查询菜单列表
     *
     * @param map 菜单信息和用户角色id
     * @return 菜单列表
     */
    public List<Menu> selectMenuListByUserId(Map map);

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public Menu selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Long parentId);

    /**
     * 新增菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(Menu menu);

    /**
     * 修改菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(Menu menu);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public Menu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);

    /**
     * 根据公司ID查询菜单
     *
     * @param companyId 用户ID
     * @return 菜单列表
     */
    public List<Menu> selectMenusByCompanyId(Long companyId);

    /**
     * api接口查询菜单
     * @param userId 用户id
     * @param parentId 父id
     * @return 结果
     */
    List<MenuApi> selectMenuListByParentId(@Param("userId") Integer userId,@Param("parentId") Integer parentId);

    List<Menu> selectMenuListByParentIdAndUserId(@Param("userId") Integer userId,@Param("parentId") Integer parentId);
}
