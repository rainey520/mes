
package com.ruoyi.project.device.api.form;

import javax.validation.constraints.NotBlank;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-25
 */
public class RegisterForm {
    @NotBlank(message="手机号不能为空")
    private String phoneNumber;

    @NotBlank(message="密码不能为空")
    private String password;
    
    @NotBlank(message="确认密码不能为空")
    private String passwords;
    
    @NotBlank(message="验证码不能为空")
    private String verificationCode;
    
    @NotBlank(message="用户名不能为空")
    private String userName;
    
    public boolean comparePassword(){
    	if(this.password.equals(passwords)){
    		return true;
    	}
    	return false;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    
}
