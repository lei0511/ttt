package com.th.ox.cleaver.activiti.config.mybatisplus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

@Configuration
public class MyBatisPlusConfig {

	/*
	 * 设置Mybatis Mapper拦截器，拦截Mapper数据库操作，对Mapper进行增强
	 * --------------------------------------------------------------
	 * 具体配置详见application-mybatisplus.yml
	 * --------------------------------------------------------------
	 * 如需分页时，通过形参向Mapper接口注入分页对象Pagination，根据方言调整SQL语句进行分页 ；
	 * 分页操作可以参考示例DemoService
	 * --------------------------------------------------------------
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("postgresql");
		return page;
	}
}
