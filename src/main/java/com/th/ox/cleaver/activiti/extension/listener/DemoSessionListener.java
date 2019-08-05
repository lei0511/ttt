package com.th.ox.cleaver.activiti.extension.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class DemoSessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public DemoSessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println(se.getSession().getId() + " session create");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(se.getSession().getId() + " session destroy");
	}

}
