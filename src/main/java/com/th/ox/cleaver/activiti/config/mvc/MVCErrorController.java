package com.th.ox.cleaver.activiti.config.mvc;

import com.alibaba.fastjson.JSON;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 发生全局未捕获异常后，springboot会捕获到并且转向到error页面，此处重写error页面的返回值。
 *
 * @author ZColin
 */
@RestController
public class MVCErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 401) {
            return JSON.toJSONString(new MVCResult("", false, MVCResult.HttpStatus.UNAUTHEN, "用户未登录", null));
        } else if (statusCode == 404) {
            return JSON.toJSONString(new MVCResult("", false, MVCResult.HttpStatus.NOT_FOUND, "地址不存在", null));
        } else if (statusCode == 403) {
            return JSON.toJSONString(new MVCResult("", false, MVCResult.HttpStatus.UNAUTHZ, "用户无权限", null));
        } else {
            return JSON.toJSONString(new MVCResult("", false, MVCResult.HttpStatus.SERVER_ERR, "服务端异常", null));
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}