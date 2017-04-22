package com.wesdm.springmvc.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Used to replace web.xml Configures DispatchServlet.
 * AbstractAnnotationConfigDispatcherServletInitializer creates DispatchServlet
 * and ContextLoadListner. Servlet container must support Servlet 3.0 for this
 * to replace web.xml
 * 
 * @author Wesley
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; // map dispatch servlet to /
	}

	/**
	 * used to configure the application context created by
	 * ContextLoaderListener. expected to load the other beans in your
	 * application.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	/**
	 * define beans for DispatcherServlet’s application context. expected to
	 * load beans containing web components such as controllers, view resolvers,
	 * and handler mappings
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {

		// register a MultipartConfigElement, for file uploads
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp/spittr/uploads",
				2097152, 4194304, 0);

		registration.setMultipartConfig(multipartConfigElement);

	}

}
