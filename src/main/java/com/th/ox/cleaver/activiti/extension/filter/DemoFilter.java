package com.th.ox.cleaver.activiti.extension.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//写一个过滤器。过滤实现在doFilter方法里面。chain.doFilter(request, response)  表示过滤通过，能够往下执行。
//所以过滤操作要写在chain.doFilter(request, response) 前面，作一些条件判断，如果不符合条件，则不执行chain.doFilter(request, response);
//别忘了让springboot启动类LinyismartApplication扫描servlet组件，否则是不生效的，扫描的注解是@ServletComponentScan。

@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class DemoFilter implements Filter {
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("MyFilter init............");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("MyFilter doFilter.........before");
		chain.doFilter(request, response);
		System.out.println("MyFilter doFilter.........after");
	}

	@Override
	public void destroy() {
		System.out.println("MyFilter destroy..........");
	}
}