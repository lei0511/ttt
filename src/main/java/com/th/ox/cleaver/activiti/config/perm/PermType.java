package com.th.ox.cleaver.activiti.config.perm;

/*
 * 系统权限分类字典
 * 
 */
public interface PermType {

	/** 菜单权限 */
	int MENU = 1;

	/** 按钮权限 */
	int BUTTON = 2;

	/** 接口权限（自动通过注解拿到） */
	int API = 3;

	/** 数据权限 */
	int DATA = 4;

}
