

package com.ruoyi.project.device.api.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-25
 */
public class CompanyForm {
    @NotNull(message="用户ID不能为空")
    private Integer userId;

    @NotBlank(message="公司名称")
    private String comName;
    
    @NotBlank(message="公司负责人")
    private String comPeople;

    private String comTelephone;
    
    @NotBlank(message="负责人手机号")
    private String comPhoneNumber;
    
    private String comDescribe;
    
    private String comCode;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComPeople() {
		return comPeople;
	}

	public void setComPeople(String comPeople) {
		this.comPeople = comPeople;
	}

	public String getComTelephone() {
		return comTelephone;
	}

	public void setComTelephone(String comTelephone) {
		this.comTelephone = comTelephone;
	}

	public String getComPhoneNumber() {
		return comPhoneNumber;
	}

	public void setComPhoneNumber(String comPhoneNumber) {
		this.comPhoneNumber = comPhoneNumber;
	}

	public String getComDescribe() {
		return comDescribe;
	}

	public void setComDescribe(String comDescribe) {
		this.comDescribe = comDescribe;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
    
}
