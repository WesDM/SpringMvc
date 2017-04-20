package com.wesdm.springmvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Used to replace web.xml Configures DispatchServlet. AbstractAnnotationConfigDispatcherServletInitializer creates DispatchServlet
 * and ContextLoadListner. Servlet container must support  Servlet 3.0 for this to replace web.xml
 * @author Wesley
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };		//map dispatch servlet to /
	}
	
	/**
	 * used to configure the application context created by ContextLoaderListener
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}
	
	/**
	 * define beans for DispatcherServlet’s application context
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

}
