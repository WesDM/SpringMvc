package com.wesdm.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity // determines adding the Spring MVC features based upon the
					// classpath
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	// configure web security by overriding WebSecurity-
	// ConfigurerAdapter’s three configure() methods and setting behavior on the
	// parameter passed in

	protected void configure(HttpSecurity http) throws Exception {
		// all HTTP requests
		// coming into the application be authenticated. It also configures
		// Spring Security to
		// support authentication via a form-based login (using a predefined
		// login page) as well
		// as HTTP Basic
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	}
	
	/**
	 * In Memory User Store config
	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// in memory user store. withUser adds user to store with password and
//		// roles
//		// good to testing and debugging
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
//				.password("password").roles("USER", "ADMIN");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//relational database user store. change default queries to match your db schema
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, true " + "from Spitter where username=?")
				.authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
				.passwordEncoder(new StandardPasswordEncoder("53cr3t"));	//encrypts password before trying to match encrypted pword in db
	}
}

//Default queries for RDBMS

//public static final String DEF_USERS_BY_USERNAME_QUERY =
//"select username,password,enabled " +
//"from users " +
//"where username = ?";
//public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
//"select username,authority " +
//"from authorities " +
//"where username = ?";
//public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
//"select g.id, g.group_name, ga.authority " +
//"from groups g, group_members gm, group_authorities ga " +
//"where gm.username = ? " +
//"and g.id = ga.group_id " +
//"and g.id = gm.group_id";