package com.ruoyi.project.device.api.controller;

import com.ruoyi.common.utils.DataTurn;
import com.ruoyi.common.utils.Tools;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.api.form.WorkDataForm;
import com.ruoyi.project.device.api.service.IInitDataManageService;
import com.ruoyi.project.device.devDevice.domain.DevDevice;
import com.ruoyi.project.device.devDevice.service.IDevDeviceService;
import com.ruoyi.project.device.devDeviceCounts.service.IDevDeviceCountsService;
import com.ruoyi.project.device.devDeviceIo.service.IDevDeviceIoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Controller
@RequestMapping("/api/init")
public class InitDataManageController extends BaseController
{


	@Autowired
	private IDevDeviceService devDeviceService;
	
	@Autowired
	private IDevDeviceIoService devDeviceIoService;
	
	@Autowired
	private IDevDeviceCountsService devDeviceCountsService;

	@Autowired
	private IInitDataManageService iInitDataManageService;
	
	/**
	 * 初始化接口参数
	 */
	@RequestMapping("/restData")
	@ResponseBody
	public Object restData(@RequestBody String iemi)
	{		

        iemi = iemi.substring(8, 24);
        String result ="553A0A82"+iemi;
        try {
        	//这里解析设备码
            DevDevice devDevice = devDeviceService.selectDevDeviceByIemi(iemi);
            List<Map<String,Object>> list = devDeviceIoService.selectDeviceIoByDevId(devDevice.getId());
            String[] arr = {"00","00","00","00","00","00","00","00"};
            for(Map<String,Object> map : list){
            	arr[Integer.parseInt(map.get("ioname").toString())-1] = "0"+map.get("ioposition").toString();
            }
            for(int i=0;i<8;i++){
            	result = result + arr[i];
            }
            result = result + DataTurn.IntToHex(Integer.parseInt(devDevice.getUploadtime()),4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Tools.addCheck("553A0182"+iemi+"01");
		}
        
        return Tools.addCheck(result);
	}
	
	/**
	 * 上报数据接口
	 */
	@ResponseBody
	@GetMapping(value = "/uploadData",produces = "text/plain;charset=UTF-8")
	public Object uploadData(String order)
	{
		try {
			String id = order.substring(8, 16);
			Integer[] arr = new Integer[8];
			arr[0] = DataTurn.HexToInt(order.substring(16, 24));
			arr[1] = DataTurn.HexToInt(order.substring(24, 32));
			arr[2] = DataTurn.HexToInt(order.substring(32, 40));
			arr[3] = DataTurn.HexToInt(order.substring(40, 48));
			arr[4] = DataTurn.HexToInt(order.substring(48, 56));
			arr[5] = DataTurn.HexToInt(order.substring(56, 64));
			arr[6] = DataTurn.HexToInt(order.substring(64, 72));
			arr[7] = DataTurn.HexToInt(order.substring(72, 80));
			devDeviceCountsService.insertDevDeviceCounts(id,arr);
//        return Tools.addCheck("553A0181"+id+"00");
			return "ok";
		}catch (Exception e){
			e.printStackTrace();
		}
		return "fail";

	}

	/**
	 * 根据硬件编码查询对应的工单信息
	 * @param code 硬件编码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/w/{code}")
	public Map<String,Object> workOrder(@PathVariable("code")String code){
		return  iInitDataManageService.workOrder(code);
	}

	/**
	 * 数据上报接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/workData")
	public Map<String,Object> workData(@RequestBody WorkDataForm data){
		return iInitDataManageService.workData(data);
	}

	/**
	 * 异常上报
	 * @param code 硬件编码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/e/{code}")
	public Map<String,Object> workEx(@PathVariable("code")String code){
		return iInitDataManageService.workEx(code);
	}

//	/**
//	 * 获取各个用户的服务器请求地址
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/path/{code}")
//	public Map<String,Object> getPath(@PathVariable("code")String code){
//		Map<String,Object> map = new HashMap<>();
//		map.put("status",1);
//		map.put("path","http://39.108.187.126:8088");
//		return map;
//	}

	/**
	 * 车间工单通过在机器设备上扫描绑定开始操作
	 * @param code 设置绑定的硬件编号
	 * @param orderCode 工单号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/startWork/{code}/{orderCode}")
	public AjaxResult startWorkOrder(@PathVariable("code")String code,@PathVariable("orderCode")String orderCode){
		return iInitDataManageService.startWorkOrder(code,orderCode);
	}

}
