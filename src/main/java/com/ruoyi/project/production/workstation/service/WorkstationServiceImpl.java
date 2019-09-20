package com.ruoyi.project.production.workstation.service;

import com.ruoyi.common.constant.DevConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.project.device.devList.mapper.DevListMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import com.ruoyi.project.production.workstation.domain.Workstation;
import com.ruoyi.project.production.workstation.mapper.WorkstationMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 工位配置 服务层实现
 * 
 * @author sj
 * @date 2019-06-13
 */
@Service
public class WorkstationServiceImpl implements IWorkstationService 
{
	@Autowired
	private WorkstationMapper workstationMapper;

	@Autowired
    private DevListMapper devListMapper;

	@Autowired
	private ProductionLineMapper productionLineMapper;



	/**
     * 查询工位配置信息
     * 
     * @param id 工位配置ID
     * @return 工位配置信息
     */
    @Override
	public Workstation selectWorkstationById(Integer id)
	{
	    return workstationMapper.selectWorkstationById(id);
	}
	
	/**
     * 查询工位配置列表
     * 
     * @param workstation 工位配置信息
     * @return 工位配置集合
     */
	@Override
	public List<Workstation> selectWorkstationList(Workstation workstation)
	{
		User user = JwtUtil.getUser();
		if (user == null) {
		    return Collections.emptyList();
		}
		workstation.setCompanyId(user.getCompanyId());
	    return workstationMapper.selectWorkstationList(workstation);
	}

	@Override
	public List<Workstation> appSelectWorkstationList(Workstation workstation) {
		return null;
	}

	/**
     * 新增工位配置
     * 
     * @param workstation 工位配置信息
     * @return 结果
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertWorkstation(Workstation workstation) throws Exception
	{
		// 查询产线名称
		String lineName = "";
		ProductionLine line = productionLineMapper.selectProductionLineById(workstation.getLineId());
		if (StringUtils.isNotNull(line)) {
			lineName += line.getLineName() + "--";
		}
        //查询看板器硬件
        if(workstation.getcId() != null && workstation.getcId() > 0){
            DevList devList = devListMapper.selectDevListById(workstation.getcId());
            if(devList != null && devList.getDefId() == 0 && devList.getDeviceStatus() == 1 && devList.getSign() == 0){
                workstation.setcCode(devList.getDeviceId());
                devList.setDeviceName(lineName + workstation.getwName());
				devList.setSign(1);
				devList.setDevType(DevConstants.DEV_TYPE_LINE);
				devListMapper.updateDevSign(devList);
            }else {
                throw new Exception("看板硬件编码配置错误");
            }
        }
        workstation.setcTime(new Date());
        return workstationMapper.insertWorkstation(workstation);
	}

	/**
     * 修改工位配置
     * 
     * @param workstation 工位配置信息
     * @return 结果
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateWorkstation(Workstation workstation) throws Exception
	{
		Workstation work = workstationMapper.selectWorkstationById(workstation.getId());
		if(work == null){
			throw new Exception("工位被删除...");
		}
		String wName = "";
		if (StringUtils.isNotEmpty(workstation.getwName())) {
			wName = workstation.getwName();
		}
		String lineName = "";
		ProductionLine line = productionLineMapper.selectProductionLineById(work.getLineId());
		if (StringUtils.isNotNull(line)) {
			lineName += line.getLineName() + "--";
		}
		//操作硬件
		DevList devList =null;
		//看板
		if(work.getcId() != workstation.getcId()){
			workstation.seteCode(null);
			if(work.getcId() > 0){
				devList = devListMapper.selectDevListById(work.getcId());
				if(devList != null){
					devList.setSign(0);
					devList.setDeviceName("");
					devList.setDevType(null);
					devListMapper.updateDevSign(devList);
				}
			}
			if(workstation.getcId() >0){
				devList = devListMapper.selectDevListById(workstation.getcId());
				if(devList != null){
					devList.setSign(1);
					devList.setDeviceName(lineName + wName);
					devList.setDevType(DevConstants.DEV_TYPE_LINE);
					devListMapper.updateDevSign(devList);
					workstation.setcCode(devList.getDeviceId());
				}
			}
		}else{
			devList = devListMapper.selectDevListById(work.getcId());
			if(devList != null){
				devList.setSign(1);
				devList.setDeviceName(lineName + wName);
				devList.setDevType(DevConstants.DEV_TYPE_LINE);
				devListMapper.updateDevSign(devList);
			}
			workstation.setcCode(work.getcCode());
		}
		return workstationMapper.updateWorkstation(workstation);
	}

	/**
	 * app 端工位配置看板硬件
	 * @param workstation 工位信息
	 * @return 结果
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int appLineCnfStation(Workstation workstation) {
		User user = JwtUtil.getUser();
		if (user == null) {
		    throw new BusinessException(UserConstants.NOT_LOGIN);
		}
		Workstation work = workstationMapper.selectWorkstationById(workstation.getId());
		if (work == null) {
		    throw new BusinessException("工位不存在或者被删除");
		}
		ProductionLine line = productionLineMapper.selectProductionLineById(work.getLineId());
		if (StringUtils.isNull(line)) {
		    throw new BusinessException("该工位关联的产线不存在或被删除");
		}
		if (StringUtils.isEmpty(workstation.getcCode())) {
		    throw new BusinessException("看板硬件不能为空");
		}
		// 查询扫码的硬件是否存在
		DevList devList = devListMapper.selectDevListByComCode(user.getCompanyId(), workstation.getcCode());
		if (devList == null) {
			throw new BusinessException("硬件不存在或被删除");
		}
		if (devList.getSign().equals(DevConstants.DEV_SIGN_USED)) {
			throw new BusinessException("该硬件已被配置过");
		}
		if (work.getcId() != 0) {
			// 相同情况
			if (workstation.getcCode().equals(work.getcCode())) {
				work.setcId(devList.getId());
			    work.setcCode(devList.getDeviceId());
			    // 不同情况
			} else {
				// 还原之前硬件配置
				devListMapper.updateDevSignAndType1(user.getCompanyId(),work.getcId(),DevConstants.DEV_SIGN_NOT_USE,null,"");
				// 新增配置
				work.setcId(devList.getId());
				work.setcCode(devList.getDeviceId());
				devListMapper.updateDevSignAndType1(user.getCompanyId(),devList.getId(),DevConstants.DEV_SIGN_USED,DevConstants.DEV_TYPE_LINE,(line.getLineName() + "--" +work.getwName()));
			}
		} else {
			// 新增配置
			work.setcId(devList.getId());
			work.setcCode(devList.getDeviceId());
			// 修改硬件为配置过
			devListMapper.updateDevSignAndType1(user.getCompanyId(),devList.getId(),DevConstants.DEV_SIGN_USED,DevConstants.DEV_TYPE_LINE,(line.getLineName() + "--" +work.getwName()));
		}

		return workstationMapper.updateWorkstation(work);
	}

	/**
     * 删除工位配置对象
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteWorkstationById(Integer id)
	{
		User user = JwtUtil.getUser();
		Workstation work = workstationMapper.selectWorkstationById(id);
		int row = workstationMapper.deleteWorkstationById(id);
		if(work.getSign() == 1){
			workstationMapper.editFirstWorkstionSign(work.getLineId(),work.getCompanyId());
		}
		/**
		 * 更新生产线手动或者自动
		 */
		updateLineManual(work);
		/**
		 * 删除工位还原已该工位已配置的所有硬件信息
		 */
		if (work.getDevId() > 0) {
			devListMapper.updateDevSignAndType(user.getCompanyId(),work.getDevId(),DevConstants.DEV_SIGN_NOT_USE,null);
		}
		if (work.getcId() > 0) {
			devListMapper.updateDevSignAndType(user.getCompanyId(),work.getcId(),DevConstants.DEV_SIGN_NOT_USE,null);
		}
		if (work.geteId() > 0) {
		    devListMapper.updateDevSignAndType(user.getCompanyId(),work.geteId(),DevConstants.DEV_SIGN_NOT_USE,null);
		}
		return row;
	}

	/**
	 * 根据产线查询所以工位信息
	 * @param lineId 产线id
	 * @return
	 */
	@Override
	public List<Workstation> selectAllByLineId(Integer lineId) {
		return workstationMapper.selectAllByLineId(lineId);
	}

	/**
	 * 判断流水线是手动还是自动
	 * @param workstation 工位信息
	 */
	private void updateLineManual(Workstation workstation) {
		Workstation w = workstationMapper.selectWorkstationSignByLineId(workstation.getLineId(), workstation.getCompanyId());
		if (w != null && w.getDevId() != null && w.getDevId() > 0) {
			//将产线修改为自动
			ProductionLine line = productionLineMapper.selectProductionLineById(workstation.getLineId());
			if (line != null) {
				line.setManual(0);
				productionLineMapper.updateProductionLine(line);
			}
		} else {
			ProductionLine line = productionLineMapper.selectProductionLineById(workstation.getLineId());
			if (line != null) {
				line.setManual(1);
				productionLineMapper.updateProductionLine(line);
			}
		}
	}
}
