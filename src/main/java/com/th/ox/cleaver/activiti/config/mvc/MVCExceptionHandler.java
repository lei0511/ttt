package com.th.ox.cleaver.activiti.config.mvc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.th.ox.cleaver.activiti.extension.exception.MyException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *  对所有@RestController进行增强 A：统一异常处理   B：数据绑定器定义
 */
@Slf4j
@RestControllerAdvice
public class MVCExceptionHandler {

	/**
	 * A：统一异常处理   B：数据绑定器定义
	 */
	private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";


	/*
	 * 处理自定义异常;首先查找类名一致的异常；如果找不到才会找Exception
	 */
	@ExceptionHandler(Exception.class)
	public MVCResult handleException(Exception e,HttpServletRequest request) {
		log.info("Exception=>"+e + "=>"+request.getMethod());
		return MVCResult.fail("", "服务器出错，请联系管理员！");
	}

	@ExceptionHandler(MyException.class)
	public MVCResult handleMyException(MyException e,HttpServletRequest request) {
		log.info("MyException=>"+e + "=>"+request.getMethod());
		return MVCResult.fail("", "服务器出错，请联系管理员！");
	}

}