package com.ruoyi.project.device.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.validator.ValidatorUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.api.form.CompanyForm;
import com.ruoyi.project.device.api.form.JoinCompanyForm;
import com.ruoyi.project.device.api.form.LoginForm;
import com.ruoyi.project.device.api.form.MdfPasswordForm;
import com.ruoyi.project.device.api.form.RegisterForm;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.device.devUser.domain.DevUser;
import com.ruoyi.project.device.devUser.service.IDevUserService;
import com.ruoyi.project.device.devUserCompany.domain.DevUserCompany;
import com.ruoyi.project.device.devUserCompany.service.IDevUserCompanyService;

/**
 * 用户 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Controller
@RequestMapping("/api/reg")
public class UserManageController extends BaseController
{
	
	@Autowired
	private IDevUserService devUserService;
	
	@Autowired
	private IDevCompanyService devCompanyService;
	
	@Autowired
	private IDevUserCompanyService devUserCompanyService;
	
	/**
	 * 新增保存用户
	 */
	@PostMapping("/userReg")
	@ResponseBody
	public AjaxResult userReg(RegisterForm form)
	{		
		//表单校验
        ValidatorUtils.validateEntity(form);
        if(!form.comparePassword()){
        	return error(500, "输入的密码不一致");
        }
        DevUser devUser = new DevUser();
        devUser.setPhoneNumber(form.getPhoneNumber());
        devUser.setPassword(new Md5Hash(form.getPhoneNumber() + form.getPassword()).toHex().toString() );
        devUser.setUserName(form.getUserName());
        devUser.setCreateTime(new Date());
        devUserService.insertDevUser(devUser);
		return toAjax(1);
	}
	
	@PostMapping("/login")
	@ResponseBody
	public AjaxResult login(LoginForm form,HttpServletRequest request)
	{		
		//表单校验
        ValidatorUtils.validateEntity(form);
        Map<String,Object> paramMap = MapDataUtil.convertDataMap(request);
        paramMap.put("password", new Md5Hash(form.getPhoneNumber() + form.getPassword()).toHex().toString() );
        DevUser devUser = devUserService.selectLogin(paramMap);
        if(devUser!=null){
        	Map<String,Object> data = new HashMap<String,Object>();
        	data.put("token", UUID.randomUUID().toString());
        	data.put("userId", devUser.getId());
        	return success("登录成功",data);
        }
		return error("账号密码错误");
	}
	
	@PostMapping("/createCompany")
	@ResponseBody
	public AjaxResult createCompany(CompanyForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{		
		//表单校验
        ValidatorUtils.validateEntity(form);
        
        DevCompany devCompany = new DevCompany();
        PropertyUtils.copyProperties(devCompany, form);
        //devCompany.setComCode(String.valueOf((int)((Math.random()*9+1)*100000)));
        devCompany.setCreateTime(new Date());
        devCompanyService.insertDevCompany(devCompany);
		return toAjax(1);
	}
	
	@PostMapping("/joinCompany")
	@ResponseBody
	public AjaxResult joinCompany(JoinCompanyForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{		
		//表单校验
        ValidatorUtils.validateEntity(form);
        DevCompany devCompany = devCompanyService.selectDevCompanyByComCode(form.getUniqueCode());
        if(devCompany==null){
        	return error("公司不存在");
        }
        DevUserCompany devUserCompany = new DevUserCompany();
        devUserCompany.setUserId(form.getUserId());
        devUserCompany.setCompanyId(devCompany.getCompanyId());
        devUserCompanyService.insertDevUserCompany(devUserCompany);
		return toAjax(1);
	}
	
	@PostMapping("/mdfPassword")
	@ResponseBody
	public AjaxResult mdfPassword(MdfPasswordForm form,HttpServletRequest request)
	{		
		//表单校验
        ValidatorUtils.validateEntity(form);
        if(!form.comparePassword()){
        	return error(500, "输入的密码不一致");
        }
        Map<String,Object> paramMap = MapDataUtil.convertDataMap(request);
        DevUser devUser = devUserService.selectByMobile(paramMap);
        if(devUser==null){
        	return error(500, "账号不存在");
        }
        devUser.setPassword(new Md5Hash(form.getPhoneNumber() + form.getPasswords()).toHex().toString() );
        devUserService.updateDevUser(devUser);
		return toAjax(1);
	}
	
	
}
