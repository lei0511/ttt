package com.th.ox.cleaver.activiti.extension.annotation.log;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.th.ox.cleaver.activiti.util.ctx.RequestContextUtil;

/**
 * <p>
 * 为增删改添加监控
 */
@Aspect
// 切面注解，容器启动时自动扫描根包下的所有注解，为指定包中的方法进行切面增强
@Component
// 将此切面类交由容器进行管理（@Component等价于@Controllor等价于@Service等价于@Resposity）
@Slf4j
// lombok提供的作用域：@Retention(RetentionPolicy.SOURCE)；为类提供一个名为log的Log4j变量
public class LogAspect {

	@Pointcut("@annotation(com.th.ox.cleaver.activiti.extension.annotation.log.Log)")
	private void pointcut() {

	}

	@After("pointcut()")
	public void insertLogSuccess(JoinPoint jp) {
		addLog(jp, getDesc(jp));
	}

	/**
	 * 记录异常
	 */
	@AfterThrowing(value = "pointcut()", throwing = "e")
	public void afterException(JoinPoint joinPoint, Exception e) {
		System.out.print("-----------afterException:" + e.getMessage());
		addLog(joinPoint, getDesc(joinPoint) + e.getMessage());
	}

	/*
	 * 从注解中获取当前方法的注解描述信息（签名）
	 * -------------------------------------------------------------------
	 * 此示例比较重要，是从注解中获取注解信息，并根据注解信息分别进行处理
	 * -------------------------------------------------------------------
	 * 关于AOP的详细使用可以参考com.th.linyismart.extension.aop.DemoAspect或EasyDemoAspect;
	 */
	private String getDesc(JoinPoint joinPoint) {
		MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
		Method method = methodName.getMethod();
		return method.getAnnotation(Log.class).desc();
	}

	private void addLog(JoinPoint jp, String text) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String ip = RequestContextUtil.getIp(request);
			log.error(ip + ":" + text);
		}
	}
}
