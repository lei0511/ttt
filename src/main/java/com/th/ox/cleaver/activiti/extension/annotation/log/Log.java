package com.th.ox.cleaver.activiti.extension.annotation.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解:下面的例子是一个自定义的Log注解
 * -----------------------------------------------------------------
 * 注解本身不会有任何功能，注解只允许包含简单类型，描述简单的信息 注解想要起作用，必须使用AOP对注解所描述的方法或类进行增强，否则卵用没有
 * -----------------------------------------------------------------------------
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
@Inherited
public @interface Log {

	/*
	 * 这个是此注解的内部枚举
	 */
	public enum LOG_TYPE {
		ADD, UPDATE, DEL, SELECT, ATHOR
	}

	;

	/**
	 * 内容：这个事此注解的简单描述
	 */
	String desc();

	/**
	 * 类型：这个是此注解的简单描述
	 */
	LOG_TYPE type() default LOG_TYPE.ATHOR;
}
