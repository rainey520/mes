package com.ruoyi.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public AjaxResult handleRRException(RRException e){
		AjaxResult r = new AjaxResult();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public AjaxResult handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return AjaxResult.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public AjaxResult handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return AjaxResult.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public AjaxResult handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return AjaxResult.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public AjaxResult handleException(Exception e){
		logger.error(e.getMessage(), e);
		return AjaxResult.error();
	}
}
