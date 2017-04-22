package com.wesdm.springmvc.config;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Intercepts requests for url mapping configured in {@link WebConfig}
 * @author Wesley
 *
 */
public class TimeBasedInterceptor extends HandlerInterceptorAdapter {
	private int openingTime;
	private int closingTime;

	public TimeBasedInterceptor(int openingTime, int closingTime) {
		super();
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public void setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
	}

	public void setClosingTime(int closingTime) {
		this.closingTime = closingTime;
	}
	
	/**
	 * Called before controller method
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		Calendar cal = Calendar.getInstance();
//		int hour = cal.get(Calendar.HOUR_OF_DAY);
//		if (openingTime <= hour && hour < closingTime) {
//			return true;
//		}
//		response.sendRedirect("/resources/error.html");
		return false;
	}
}
