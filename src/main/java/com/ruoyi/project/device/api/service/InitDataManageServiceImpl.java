package com.ruoyi.project.device.api.service;

import com.ruoyi.common.constant.DevConstants;
import com.ruoyi.common.constant.WorkConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.TimeUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.api.form.ApiWorkForm;
import com.ruoyi.project.device.api.form.WorkDataForm;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.device.devDeviceCounts.domain.DevDataLog;
import com.ruoyi.project.device.devDeviceCounts.mapper.DevDataLogMapper;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.project.device.devList.mapper.DevListMapper;
import com.ruoyi.project.production.countPiece.domain.CountPiece;
import com.ruoyi.project.production.countPiece.mapper.CountPieceMapper;
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.mapper.DevWorkDataMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import com.ruoyi.project.production.singleWork.domain.SingleWork;
import com.ruoyi.project.production.singleWork.mapper.SingleWorkMapper;
import com.ruoyi.project.production.workstation.domain.Workstation;
import com.ruoyi.project.production.workstation.mapper.WorkstationMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class InitDataManageServiceImpl implements IInitDataManageService {

    @Autowired
    private DevListMapper devListMapper;

    @Autowired
    private ProductionLineMapper productionLineMapper;

    @Autowired
    private DevCompanyMapper devCompanyMapper;

    @Autowired
    private DevWorkOrderMapper devWorkOrderMapper;

    @Autowired
    private DevWorkDataMapper devWorkDataMapper;



    @Autowired
    private DevDataLogMapper devDataLogMapper;

    @Autowired
    private WorkstationMapper workstationMapper;

    @Autowired
    private SingleWorkMapper singleWorkMapper;

    @Autowired
    private CountPieceMapper countPieceMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 拉取工单信息
     *
     * @param code 硬件编码
     * @return 结果
     */
    @Override
    public Map<String, Object> workOrder(String code) {
        Map<String, Object> map = new HashMap<>(16);
        //判断对应的硬件编码是否存在
        try {
            DevList devList = devListMapper.selectDevListByCode(code);
            if (devList == null || devList.getCompanyId() == null) {
                //硬件编码不存在
                map.put("code", 3);
                map.put("msg","硬件编码不存在");
                map.put("data", null);
                return map;
            }
            if (devList.getDeviceStatus().equals(DevConstants.DEV_STATUS_NO)) {
                //硬件编码被禁用
                map.put("code", 5);
                map.put("msg","硬件编码被禁用");
                map.put("data", null);
                return map;
            }
            if (devList.getDevType() == null) {
                // 硬件未配置流水线工位或者车间单工位
                map.put("code", 2);
                map.put("msg","硬件未被配置");
                map.put("data", null);
                return map;
            }
            ApiWorkForm workForm = null;
            if (devList.getDevType().equals(DevConstants.DEV_TYPE_LINE)) {
                /**
                 * 流水线
                 */
                //查询对应硬件产线配置工位
                Workstation workstation = workstationMapper.selectInfoByDevice(devList.getId(), 0, 0);
                if (workstation != null && workstation.getLineId() != null) {
                    workForm = findLineAndWork(devList, workstation, null);
                }
            } else if (devList.getDevType().equals(DevConstants.DEV_TYPE_HOUSE)) {
                /**
                 * 车间
                 */
                // 查询对应硬件车间单工位信息
                SingleWork singleWork = singleWorkMapper.selectSingleWorkByCode(devList.getId(), 0, 0);
                if (singleWork != null) {
                    workForm = findLineAndWork(devList, null, singleWork);
                }
            }
            if (workForm == null) {
                // 未找到流水线或者车间信息
                map.put("code", 4);
                map.put("msg","未找到流水线或者车间");
                map.put("data", null);
                return map;
            }
            map.put("code", 1);
            map.put("msg","请求成功");
            map.put("data", workForm);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg","系统异常");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 计数器上传数据
     * @param data 上报数据
     * @return
     */
    @Override
    public Map<String, Object> workData(WorkDataForm data) {
        Map<String, Object> map = new HashMap<>();
        try {
            //判断对应的硬件编码是否存在
            DevList devList = devListMapper.selectDevListByCode(data.getCode());
            if (devList == null || devList.getCompanyId() == null) {
                map.put("code", 3);//硬件编码不存在
                map.put("msg","硬件编码不存在");
                map.put("status", 0);//没有正在进行的工单
                map.put("num", 0);//没有正在进行的工单
                map.put("tag", 0);//流水线标记
                map.put("workCode", null);//工单编号为空
                return map;
            }
            if (devList.getDeviceStatus().equals(DevConstants.DEV_STATUS_NO)) {
                //硬件编码被禁用
                map.put("code", 5);//硬件编码不存在
                map.put("msg","硬件编码被禁用");
                map.put("status", 0);//没有正在进行的工单
                map.put("num", 0);//没有正在进行的工单
                map.put("tag", 0);//流水线标记
                map.put("workCode", null);//工单编号为空
                return map;
            }
            // 硬件未配置车间或者生产线
            if (devList.getDevType() == null) {
                map.put("code", 4);
                map.put("msg","硬件未被配置");
                map.put("status", 0);
                map.put("num", 0);
                map.put("tag", 0);//流水线标记
                map.put("workCode", null);
                return map;
            }

            if (devList.getDevType().equals(DevConstants.DEV_TYPE_LINE)) {
                /**
                 * 生产线
                 */
                //查询对应硬件产线配置信息
                Workstation workstation = workstationMapper.selectInfoByDevice(devList.getId(), 0, 0);
                if (workstation == null || workstation.getLineId() == null) {
                    map.put("code", 4);//硬件未配置产线
                    map.put("msg","硬件未配置产线");
                    map.put("status", 0);//没有正在进行的工单
                    map.put("num", 0);//没有正在进行的工单
                    map.put("tag", 0);//流水线标记
                    map.put("workCode", null);//工单编号为空
                    return map;
                }
                //查询对应的产线是否存在
                ProductionLine line = productionLineMapper.selectProductionLineById(workstation.getLineId());
                if (line != null) {
                    DevDataLog devDataLog = new DevDataLog();
                    devDataLog.setCompanyId(devList.getCompanyId());
                    devDataLog.setDataTotal(data.getD1());
                    devDataLog.setDevId(devList.getId());
                    devDataLog.setDelData(0);
                    // 设置为流水线
                    devDataLog.setScType(WorkConstants.SING_LINE);
                    devDataLog.setLineId(workstation.getLineId());
                    //查询正在进行工单
                    DevWorkOrder workOrder = devWorkOrderMapper.selectWorkByCompandAndLine(devList.getCompanyId(), workstation.getLineId(), WorkConstants.SING_LINE);
                    // 存在工单并且需要计数
                    if (workOrder != null && workOrder.getPbSign().equals(WorkConstants.PB_SIGN_YES)) {
                        // 工单进行中
                        if (workOrder.getOperationStatus().equals(WorkConstants.OPERATION_STATUS_STARTING)) {
                            countNumData(data, devList, workstation, devDataLog, workOrder);

                        // 工单已暂停
                        } else {
                            workOrder.setPbSign(WorkConstants.PB_SIGN_NO);
                            countNumData(data, devList, workstation, devDataLog, workOrder);
                        }

                    } else if (workOrder == null) {
                        //查询最近完成工单信息
                        workOrder = devWorkOrderMapper.selectLatelyCompleteWork(devList.getCompanyId(), workstation.getLineId(), WorkConstants.SING_LINE);
                        if (workOrder != null && workOrder.getSign() == 1) {
                            devDataLog.setWorkId(workOrder.getId());
                            devDataLog.setIoId(workstation.getId());//设置工位id
                            DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devList.getCompanyId(), workstation.getLineId(), workOrder.getId(),
                                    devList.getId(), workstation.getId(), WorkConstants.SING_LINE);
                            if (workData != null) {
                                workData.setIoSign(workstation.getSign());
                                //记录累计产量
                                devWorkDataMapper.saveTotalWorkData(workData.getDataId(), data.getD1(), workData.getIoSign());
                                workOrder.setCumulativeNumber(data.getD1());
                                workOrder.setSign(0);
                                devWorkOrderMapper.updateDevWorkOrder(workOrder);
                            }
                            //查询对应日志上传数据数据
                            DevDataLog log = devDataLogMapper.selectLineWorkDevIo(workstation.getLineId(), workOrder.getId(), devList.getId(), workstation.getId(), WorkConstants.SING_LINE);
                            if (log != null) {
                                devDataLog.setDelData(devDataLog.getDataTotal() - log.getDataTotal());
                            }
                        }
                    }
                    devDataLog.setCreateDate(new Date());
                    devDataLog.setCreateTime(new Date());
                    devDataLogMapper.insertDevDataLog(devDataLog);
                }
                map.put("code", 1);//成功
                map.put("msg","无工单");
                map.put("status", 0);//没有正在进行的工单
                map.put("num", 0);//没有正在进行的工单
                map.put("tag", 0);//流水线标记
                map.put("workCode", null);//工单编号为空
                DevWorkOrder workOrder = devWorkOrderMapper.selectWorkByCompandAndLine(devList.getCompanyId(), workstation.getLineId(), WorkConstants.SING_LINE);
                if (workOrder != null) {
                    //查询对应工单的累计产量
                    DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(workstation.getCompanyId(), workstation.getLineId(),
                            workOrder.getId(), devList.getId(), workstation.getId(), WorkConstants.SING_LINE);
                    if (workData != null && workData.getCumulativeNum() != null) {
                        map.put("msg","进行中");
                        map.put("num", workData.getCumulativeNum());//没有正在进行的工单
                        map.put("status", workOrder.getOperationStatus());//工单正在进行
                        map.put("workCode", workOrder.getWorkorderNumber());//工单编号为空
                    }
                }

            } else if (devList.getDevType().equals(DevConstants.DEV_TYPE_HOUSE)) {
                /**
                 * 车间
                 */
                // 查询单工位
                SingleWork singleWork = singleWorkMapper.selectSingleWorkByCode(devList.getId(), 0, 0);
                if (StringUtils.isNull(singleWork) || singleWork.getParentId() == null) {
                    //硬件未被单工位配置
                    map.put("code", 4);
                    map.put("msg","硬件未被工位配置");
                    //没有正在进行的工单
                    map.put("status", 0);
                    //工单累计
                    map.put("num", 0);
                    map.put("perNum", 0);//个人计件
                    map.put("tag", 1);//车间标记
                    //工单编号为空
                    map.put("workCode", null);
                    return map;
                }
                // 查询车间是否存在
                SingleWork house = singleWorkMapper.selectSingleWorkById(singleWork.getParentId());
                if (house != null) {
                    DevDataLog devDataLog = new DevDataLog();
                    devDataLog.setCompanyId(devList.getCompanyId());
                    devDataLog.setDataTotal(data.getD1());
                    devDataLog.setDevId(devList.getId());
                    devDataLog.setDelData(0);
                    // 设置为流水线
                    devDataLog.setScType(WorkConstants.SING_SINGLE);
                    devDataLog.setLineId(house.getId());
                    // 查询正在车间生产对应单工位的工单信息
                    DevWorkOrder workOrder = devWorkOrderMapper.selectWorkInHouseBeInBySingId(devList.getCompanyId(), house.getId(), WorkConstants.SING_SINGLE,
                            singleWork.getId(), WorkConstants.WORK_STATUS_STARTING);
                    // 存在进行中的需要计数的工单信息
                    if (workOrder != null && workOrder.getPbSign() != null && workOrder.getPbSign().equals(WorkConstants.PB_SIGN_YES)) {

                        // 工单进行中
                        if (workOrder.getOperationStatus().equals(WorkConstants.OPERATION_STATUS_STARTING)) {
                            countSingWorkNum(data, devList, singleWork, house, devDataLog, workOrder);

                            // 工单暂停
                        } else {
                            workOrder.setPbSign(WorkConstants.PB_SIGN_NO);
                            //查询对应日志上传数据数据
                            countSingWorkNum(data, devList, singleWork, house, devDataLog, workOrder);
                        }

                        // 工单为空
                    } else if (workOrder == null) {
                        //查询车间对应最近完成工单信息
                        workOrder = devWorkOrderMapper.selectWorkInHouseLastByWorkStatus(devList.getCompanyId(), house.getId(), WorkConstants.SING_SINGLE,
                                singleWork.getId(), WorkConstants.WORK_STATUS_END);
                        if (workOrder != null && workOrder.getSign() == 1) {
                            devDataLog.setWorkId(workOrder.getId());
                            //设置单工位id
                            devDataLog.setIoId(singleWork.getId());
                            DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devList.getCompanyId(), house.getId(), workOrder.getId(),
                                    devList.getId(), singleWork.getId(), WorkConstants.SING_SINGLE);
                            if (workData != null) {
                                //记录累计产量
                                devWorkDataMapper.saveTotalWorkData(workData.getDataId(), data.getD1(), workData.getIoSign());
                                workOrder.setCumulativeNumber(data.getD1());
                                workOrder.setSign(0);
                                devWorkOrderMapper.updateDevWorkOrder(workOrder);
                            }
                            //查询对应日志上传数据数据
                            DevDataLog log = devDataLogMapper.selectLineWorkDevIo(house.getId(), workOrder.getId(), devList.getId(), singleWork.getId(), WorkConstants.SING_SINGLE);
                            if (log != null) {
                                devDataLog.setDelData(devDataLog.getDataTotal() - log.getDataTotal());

                                // 统计个人计件
                                CountPiece countPiece = getCountPiece(devList, singleWork, workOrder);
                                countPiece.setCpNumber(countPiece.getCpNumber() + (devDataLog.getDataTotal() - log.getDataTotal()));
                                countPiece.setTotalPrice(workOrder.getWorkPrice() * countPiece.getCpNumber());
                                countPieceMapper.updateCountPiece(countPiece);
                            }
                        }
                    }
                    devDataLog.setCreateDate(new Date());
                    devDataLog.setCreateTime(new Date());
                    devDataLogMapper.insertDevDataLog(devDataLog);
                }
                map.put("code", 1);//成功
                map.put("msg","无工单");
                map.put("status", 0);//没有正在进行的工单
                map.put("tag", 1);//车间标记
                map.put("perNum", 0);//个人计件
                map.put("num", 0);//没有正在进行的工单
                map.put("workCode", null);//工单编号为空
                DevWorkOrder workOrder = devWorkOrderMapper.selectWorkInHouseBeInBySingId(devList.getCompanyId(), singleWork.getParentId(), WorkConstants.SING_SINGLE,
                        singleWork.getId(), WorkConstants.WORK_STATUS_STARTING);
                if (workOrder != null) {
                    map.put("msg","进行中");
                    //查询对应工位的个人累计数量
                    CountPiece countPiece = countPieceMapper.selectPieceByWorkIdAndUid(workOrder.getId(), devList.getCompanyId(), singleWork.getLiableOne(), TimeUtil.getNYR());
                    if (countPiece != null) {
                        map.put("perNum", countPiece.getCpNumber());
                    }
                    DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devList.getCompanyId(), singleWork.getParentId(),
                            workOrder.getId(), devList.getId(), singleWork.getId(), WorkConstants.SING_SINGLE);
                    if (workData != null && workData.getCumulativeNum() != null) {
                        map.put("status", workOrder.getOperationStatus());
                        if(workOrder.getOperationStatus() == WorkConstants.OPERATION_STATUS_PAUSE){
                            map.put("msg","暂停中");
                        }
                        // 该单工位累计产量
                        map.put("num", workData.getCumulativeNum());
                        map.put("workCode", workOrder.getWorkorderNumber());
                    }
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);//异常错误
            map.put("msg","系统异常");
            map.put("status", 0);//没有正在进行的工单
            map.put("perNum", 0);//个人计件
            map.put("num", 0);//工单累计
            map.put("tag", 1);//车间标记
            map.put("workCode", null);//工单编号为空
        }
        return map;
    }

    /**
     * 单工位统计计数
     */
    private void countSingWorkNum(WorkDataForm data, DevList devList, SingleWork singleWork, SingleWork house, DevDataLog devDataLog, DevWorkOrder workOrder) {
        devDataLog.setWorkId(workOrder.getId());
        devDataLog.setIoId(singleWork.getId());
        //查询对应日志上传数据数据
        DevDataLog log = devDataLogMapper.selectLineWorkDevIo(house.getId(), workOrder.getId(), devList.getId(), singleWork.getId(), WorkConstants.SING_SINGLE);
        if (log != null) {
            devDataLog.setDelData(devDataLog.getDataTotal() - log.getDataTotal());
            if ((devDataLog.getDataTotal() - log.getDataTotal()) > 0) {
                // 个人计件统计
                CountPiece countPiece = getCountPiece(devList, singleWork, workOrder);
                countPiece.setCpNumber(countPiece.getCpNumber() + (devDataLog.getDataTotal() - log.getDataTotal()));
                countPiece.setTotalPrice(workOrder.getWorkPrice() * (countPiece.getCpNumber() - countPiece.getCpBadNumber()));
                countPieceMapper.updateCountPiece(countPiece);
            }
//            else if (data.getD1() > 0){
//                CountPiece countPiece = getCountPiece(devList, singleWork, workOrder);
//                devDataLog.setWorkId(workOrder.getId());
//                devDataLog.setIoId(singleWork.getId());
//                countPiece.setCpNumber(countPiece.getCpNumber() + data.getD1());
//                countPiece.setTotalPrice(workOrder.getWorkPrice() * (countPiece.getCpNumber() - countPiece.getCpBadNumber()));
//                countPieceMapper.updateCountPiece(countPiece);
//            }
            // 个人计件统计
        } else {
            devDataLog.setDelData(data.getD1());
            if (data.getD1() > 0) {
                // 个人计件统计
                CountPiece countPiece = getCountPiece(devList, singleWork, workOrder);
                devDataLog.setWorkId(workOrder.getId());
                devDataLog.setIoId(singleWork.getId());
                countPiece.setCpNumber(countPiece.getCpNumber() + data.getD1());
                countPiece.setTotalPrice(workOrder.getWorkPrice() * (countPiece.getCpNumber() - countPiece.getCpBadNumber()));
                countPieceMapper.updateCountPiece(countPiece);
            }
        }
        //对相关数据进行记录
        DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devList.getCompanyId(), house.getId(), workOrder.getId(),
                devList.getId(), singleWork.getId(), WorkConstants.SING_SINGLE);
        if (workData != null) {
            // 生产标记为车间生产
            workData.setScType(WorkConstants.SING_SINGLE);
            //记录累计产量
            devWorkDataMapper.saveTotalWorkData(workData.getDataId(), data.getD1(), workData.getIoSign());

            //查询对应车间已生产的总数
            DevWorkOrder workActNum = devWorkDataMapper.selectHouseWorkDataActualNum(devList.getCompanyId(), house.getId(), workOrder.getId(), WorkConstants.SING_SINGLE);
            if (workActNum != null && workActNum.getCumulativeNumber() != null) {
                // 设置工单累计产量
                workOrder.setCumulativeNumber(workActNum.getCumulativeNumber());
                devWorkOrderMapper.updateDevWorkOrder(workOrder);
            }
        } else {
            workData = new DevWorkData();
            // 生产标记为车间
            workData.setScType(WorkConstants.SING_SINGLE);
            workData.setCompanyId(devList.getCompanyId());
            workData.setLineId(house.getId());
            workData.setWorkId(workOrder.getId());
            workData.setDevId(devList.getId());
            workData.setDevName(devListMapper.selectDevListById(devList.getId()).getDeviceName());
            workData.setIoId(singleWork.getId());
            workData.setCumulativeNum(data.getD1());
            workData.setCreateTime(new Date());
            devWorkDataMapper.insertDevWorkData(workData);
        }
    }

    /**
     * 流水线工单计数
     */
    private void countNumData(WorkDataForm data, DevList devList, Workstation workstation, DevDataLog devDataLog, DevWorkOrder workOrder) {
        devDataLog.setWorkId(workOrder.getId());
        devDataLog.setIoId(workstation.getId());//设置工位id
        //查询对应日志上传数据数据
        DevDataLog log = devDataLogMapper.selectLineWorkDevIo(workstation.getLineId(), workOrder.getId(), devList.getId(), workstation.getId(), WorkConstants.SING_LINE);
        if (log != null) {
            devDataLog.setDelData(devDataLog.getDataTotal() - log.getDataTotal());
        } else {
            devDataLog.setDelData(data.getD1());
        }
        //对相关数据进行记录
        DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devList.getCompanyId(), workstation.getLineId(), workOrder.getId(),
                devList.getId(), workstation.getId(), WorkConstants.SING_LINE);
        if (workData != null) {
            // 生产标记为流水线生产
            workData.setScType(WorkConstants.SING_LINE);
            workData.setIoSign(workstation.getSign());//标记数据 为报表数据
            //记录累计产量
            devWorkDataMapper.saveTotalWorkData(workData.getDataId(), data.getD1(), workData.getIoSign());
            workOrder.setCumulativeNumber(data.getD1());
            devWorkOrderMapper.updateDevWorkOrder(workOrder);
        } else {
            workData = new DevWorkData();
            // 生产标记为流水线生产
            workData.setScType(WorkConstants.SING_LINE);
            workData.setIoSign(workstation.getSign());
            workData.setDevName(devListMapper.selectDevListById(devList.getId()).getDeviceName());
            workData.setCompanyId(workstation.getCompanyId());
            workData.setLineId(workstation.getLineId());
            workData.setWorkId(workOrder.getId());
            workData.setDevId(devList.getId());
            workData.setIoId(workstation.getId());
            workData.setCumulativeNum(data.getD1());
            workData.setCreateTime(new Date());
            devWorkDataMapper.insertDevWorkData(workData);
        }
    }


    /**
     * 异常上报
     *
     * @param code 硬件编码
     * @return
     */
    @Override
    public Map<String, Object> workEx(String code) {
        Map<String, Object> map = new HashMap<>(16);
        try {
            //判断对应的硬件编码是否存在
            DevList devList = devListMapper.selectDevListByCode(code);
            if (devList == null || devList.getCompanyId() == null) {
                map.put("status", 3);//硬件编码不存在
                map.put("msg","硬件编码不存在");
                map.put("code", 0);//上报失败
                return map;
            }
            if (devList.getDeviceStatus().equals(DevConstants.DEV_STATUS_NO)) {
                map.put("status", 5);
                map.put("msg","硬件编码被禁用");
                map.put("code", 0);//上报失败
                return map;
            }
            if (devList.getDevType() == null) {
                map.put("status", 4);//硬件未配置流水线或者车间
                map.put("msg","硬件未被配置");
                map.put("code", 0);//上报失败
                return map;
            }
            /**
             * 流水线
             */
            if (devList.getDevType().equals(WorkConstants.SING_LINE)) {
                //查询对应硬件是否配置产线
                Workstation workstation = workstationMapper.selectInfoByDevice(devList.getId(), 0, 0);
                if (workstation == null || workstation.getLineId() == null) {
                    map.put("status", 4);//硬件未配置产线
                    map.put("msg","硬件未被配置");
                    map.put("code", 0);//上报失败
                    return map;
                }
                ApiWorkForm workForm = findLineAndWork(devList, workstation, null);
                if (workForm == null || workForm.getWorkId() == null) {
                    map.put("status", 2);//不存在对应工单信息
                    map.put("msg","不存在对应工单信息");
                    map.put("code", 0);//上报失败
                    return map;
                }
//                //查询对应异常类型是否存在
//                WorkExceptionType exceptionType = exceptionTypeMapper.selectByCompanyAndTypeName(workForm.getCompanyId(), "异常上报");
//                if (exceptionType == null) {
//                    exceptionType = new WorkExceptionType();
//                    exceptionType.setCompanyId(workForm.getCompanyId());
//                    exceptionType.setTypeName("异常上报");
//                    exceptionType.setDefId(0);
//                    exceptionType.setCreateTime(new Date());
//                    exceptionTypeMapper.insertWorkExceptionType(exceptionType);
//                }
//                //添加异常信息
//                WorkExceptionList exceptionList = new WorkExceptionList();
//                exceptionList.setCompanyId(workForm.getCompanyId());
//                exceptionList.setExceStatut(0);
//                exceptionList.setExceType(exceptionType.getId());
//                exceptionList.setWorkId(workForm.getWorkId());
//                exceptionList.setRemark("工位:" + workstation.getwName() + ",设备ID：" + code + ",上报异常");
//                exceptionList.setCreateTime(new Date());
//                workExceptionListMapper.insertWorkExceptionList(exceptionList);
                map.put("status", 1);//创建成功
                map.put("msg","上报成功");
                map.put("code", 1);//上报成功


                /**
                 * 车间
                 */
            } else {
                // 查询对应硬件车间单工位信息
                SingleWork singleWork = singleWorkMapper.selectSingleWorkByCode(devList.getId(), 0, 0);
                if (singleWork == null) {
                    map.put("code", 4);//硬件未配置车间
                    map.put("msg","硬件未被配置");
                    map.put("status", 0);//上报失败
                    return map;
                }
                ApiWorkForm workForm = findLineAndWork(devList, null, singleWork);
                if (workForm == null || workForm.getWorkId() == null) {
                    map.put("code", 2);//不存在对应工单信息
                    map.put("msg","不存在对应工单信息");
                    map.put("status", 0);//上报失败
                    return map;
                }
//                //查询对应异常类型是否存在
//                WorkExceptionType exceptionType = exceptionTypeMapper.selectByCompanyAndTypeName(workForm.getCompanyId(), "异常上报");
//                if (exceptionType == null) {
//                    exceptionType = new WorkExceptionType();
//                    exceptionType.setCompanyId(workForm.getCompanyId());
//                    exceptionType.setTypeName("异常上报");
//                    exceptionType.setDefId(0);
//                    exceptionType.setCreateTime(new Date());
//                    exceptionTypeMapper.insertWorkExceptionType(exceptionType);
//                }
//                //添加异常信息
//                WorkExceptionList exceptionList = new WorkExceptionList();
//                exceptionList.setCompanyId(workForm.getCompanyId());
//                exceptionList.setExceStatut(0);
//                exceptionList.setExceType(exceptionType.getId());
//                exceptionList.setWorkId(workForm.getWorkId());
//                exceptionList.setRemark("单工位设备编号:" + singleWork.getImCode() + ",设备ID：" + code + ",上报异常");
//                exceptionList.setCreateTime(new Date());
//                workExceptionListMapper.insertWorkExceptionList(exceptionList);
                map.put("status", 1);//创建成功
                map.put("msg","上报成功");
                map.put("code", 1);//上报成功

            }

        } catch (Exception e) {
            // e.printStackTrace();
            map.put("status", 0);//异常错误
            map.put("msg","系统异常");
            map.put("code", 0);//上报失败
        }
        return map;
    }

    /**
     * 根据硬件查询对应的产线信息和正在进行的工单信息
     *
     * @param workstation
     * @return
     */
    private ApiWorkForm findLineAndWork(DevList devList, Workstation workstation, SingleWork singleWork) throws Exception {
        int companyId = 0;
        if (workstation != null) {
            companyId = workstation.getCompanyId();
        } else if (singleWork != null) {
            companyId = singleWork.getCompanyId();
        }
        ApiWorkForm workForm = new ApiWorkForm();
        //查询公司信息
        DevCompany devCompany = devCompanyMapper.selectDevCompanyById(companyId);
        if (devCompany == null) return null;
        workForm.setCompanyId(devCompany.getCompanyId());
        workForm.setCompanyLogo(devCompany.getComLogo());
        workForm.setCompanyName(devCompany.getComName());
        //查询对应产线正在进行的工单信息
        DevWorkOrder workOrder = null;
        if (workstation != null) {
            //查询对应的产线信息
            ProductionLine line = null;
            line = productionLineMapper.selectProductionLineById(workstation.getLineId());
            if (line == null) return null;
            workForm.setTag(WorkConstants.SING_LINE); // 流水线
            workForm.setLineName(line.getLineName() + "-" + workstation.getwName());
            workForm.setLineId(line.getId());
            workOrder = devWorkOrderMapper.selectWorkByCompandAndLine(devCompany.getCompanyId(), line.getId(), WorkConstants.SING_LINE);
            //设置工单默认状态
            workForm.setPerNum(0);
            workForm.setWorkorderStatus(0);
            workForm.setActualNum(0);
            if (workOrder != null) {
                setWorkFormData(workForm, workOrder);
                // 查询生产线对应工单累计生产产量信息
                DevWorkData workData = devWorkDataMapper.selectWorkDataByIosign(devCompany.getCompanyId(), workOrder.getId(), line.getId(), devList.getId(), WorkConstants.SING_LINE);
                if (workData != null) {
                    workForm.setActualNum(workData.getCumulativeNum());
                }
            }

            /**
             * 车间
             */
        } else if (singleWork != null) {
            //查询对应的车间信息
            SingleWork work = singleWorkMapper.selectSingleWorkById(singleWork.getParentId());
            if (work == null) return null;
            User user = userMapper.selectUserInfoById(singleWork.getLiableOne());
            if (user != null) {
                workForm.setUserId(user.getUserId().intValue());
                workForm.setUserName(user.getUserName());
            }
            workForm.setTag(WorkConstants.SING_SINGLE); // 车间
            workForm.setLineName(work.getWorkshopName() + "-" + singleWork.getImCode());
            workForm.setLineId(work.getId());
            workOrder = devWorkOrderMapper.selectWorkInHouseBeInBySingId(devList.getCompanyId(), singleWork.getParentId(), WorkConstants.SING_SINGLE,
                    singleWork.getId(), WorkConstants.WORK_STATUS_STARTING);
            //设置工单默认状态
            workForm.setWorkorderStatus(0);
            workForm.setPerNum(0);
            workForm.setActualNum(0);
            if (workOrder != null) {
                setWorkFormData(workForm, workOrder);
                //查询对应工位的个人累计数量
                CountPiece countPiece = countPieceMapper.selectPieceByWorkIdAndUid(workOrder.getId(), devList.getCompanyId(), singleWork.getLiableOne(), TimeUtil.getNYR());
                if (countPiece != null) {
                    workForm.setPerNum(countPiece.getCpNumber());
                }
                // 查询车间对应单工位累计产量信息
                DevWorkData workData = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(devCompany.getCompanyId(), work.getId(), workOrder.getId(),
                        devList.getId(), singleWork.getId(), WorkConstants.SING_SINGLE);
                if (workData != null) {
                    workForm.setActualNum(workData.getCumulativeNum());
                }

            }
        } else {
            return null;
        }
        return workForm;
    }

    /**
     * 设置拉取的相关信息
     *
     * @param workForm
     * @param workOrder
     */
    private void setWorkFormData(ApiWorkForm workForm, DevWorkOrder workOrder) {
        workForm.setWorkId(workOrder.getId());
        workForm.setWorkCode(workOrder.getWorkorderNumber());//工单号
        workForm.setProductCode(workOrder.getProductCode());//产品编码
        workForm.setProductName(workOrder.getProductName());//产品名称
        workForm.setWorkNumber(workOrder.getProductNumber());
        workForm.setWorkorderStatus(workOrder.getOperationStatus()); // 生产状态
        workForm.setOp(workOrder.getOperationStatus());
    }

    /**
     * 初始化个人计件数据
     * @param devList 硬件
     * @param singleWork 单工位
     * @param workOrder 工单
     * @return 个人计件
     */
    private CountPiece getCountPiece(DevList devList, SingleWork singleWork, DevWorkOrder workOrder) {
        // 查询对应工单个人计件信息
        CountPiece countPiece = countPieceMapper.selectPieceByWorkIdAndUid(workOrder.getId(), devList.getCompanyId(), singleWork.getLiableOne(), TimeUtil.getNYR());
        if (countPiece == null) {
            countPiece = new CountPiece();
            countPiece.setCompanyId(devList.getCompanyId());
            countPiece.setCpUserId(singleWork.getLiableOne());
            countPiece.setCpDate(new Date());
            countPiece.setWorkId(workOrder.getId());
            countPiece.setCpNumber(0);
            countPiece.setCpBadNumber(0);
            countPiece.setTotalPrice(0F);
            countPiece.setWorkPrice(workOrder.getWorkPrice());
            countPiece.setWorkNumber(workOrder.getWorkorderNumber());
            countPieceMapper.insertCountPiece(countPiece);
        }
        return countPiece;
    }

    /**
     * 根据机器设备上扫描上传工单，对工单进行绑定开始操作
     *
     * @param code      设备绑定的硬件编号
     * @param orderCode 工单号
     * @return
     */
    @Override
    public AjaxResult startWorkOrder(String code, String orderCode) {
        //查询对应硬件是否存在
        DevList devList = devListMapper.selectDevListByCode(code);
        if (devList == null) {
            return AjaxResult.api(1, "硬件编号不存在", null);
        }
        //查询对应的工单

        return AjaxResult.api(0, "操作成功", null);
    }
}
