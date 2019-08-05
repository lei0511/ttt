package com.th.ox.cleaver.activiti.extension.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DemoContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(sce.getServletContext().getServletContextName() + " init");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(sce.getServletContext().getServletContextName() + " destroy");

	}
}

/*
 * 可以实现的监听器的种类 javax.servlet.AsyncListener -
 * 如果在添加了侦听器的ServletRequest上启动的异步操作已完成，超时或导致错误，将会通知侦听器。
 * javax.servlet.ServletContextListener - 用于接收关于ServletContext生命周期更改的通知事件的接口。
 * javax.servlet.ServletContextAttributeListener -
 * 接收关于ServletContext属性更改的通知事件的接口。javax.servlet.ServletRequestListener -
 * 用于接收关于进入和超出Web应用程序范围的请求的通知事件的接口。javax.servlet.ServletRequestAttributeListener
 * - 接收关于ServletRequest属性更改的通知事件的接口。javax.servlet.http.HttpSessionListener -
 * 接收关于HttpSession生命周期更改的通知事件的接口。javax.servlet.http.HttpSessionBindingListener -
 * 使对象从会话绑定到绑定或从其绑定时被通知。javax.servlet.http.HttpSessionAttributeListener -
 * 用于接收关于HttpSession属性更改的通知事件的接口。
 * javax.servlet.http.HttpSessionActivationListener -
 * 绑定到会话的对象可能会侦听容器事件，通知他们会话将被钝化，该会话将被激活。
 * 需要在VM或持久化会话之间迁移会话的容器来通知绑定到实现HttpSessionActivationListener的会话的所有属性。
 */
