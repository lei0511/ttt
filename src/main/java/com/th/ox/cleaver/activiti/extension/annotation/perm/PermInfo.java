package com.th.ox.cleaver.activiti.extension.annotation.perm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 此注解为权限注解，在前端页面中，有个权限分配界面，可以通过此注解获取到当前系统中所有的权限名称及权限标识
 * -------------------------------------------------------------
 * 这种做法的优点是，省去了人为在权限管理里面的权限标签硬编码指定，造成的错误
 * -------------------------------------------------------------
 * 方法位于Url："/auth/meta/api"          SysPermController.listApiPermMetadata()
 * -------------------------------------------------------------
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermInfo {

	/**
	 * 权限值
	 * 
	 * @return
	 */
	String pval() default "";

	/**
	 * 权限名称 pname的别名
	 * 
	 * @return
	 */
	String value() default "";

}
