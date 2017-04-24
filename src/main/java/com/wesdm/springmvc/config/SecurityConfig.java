package com.wesdm.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/*
 * Cross Site Request Forgery protection comes automatically via token synchronization between client and server.
 * Logout handled by default
 */
@Configuration
@EnableWebSecurity // determines adding the Spring MVC features based upon the
					// classpath
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	// configure web security by overriding WebSecurity-
	// ConfigurerAdapter’s three configure() methods and setting behavior on the
	// parameter passed in

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// all HTTP requests
		// coming into the application be authenticated. It also configures
		// Spring Security to
		// support authentication via a form-based login (using a predefined
		// login page) as well
		// as HTTP Basic
		
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

		// authenticate users for first two, otherwise allow access to all paths
		
		// http.authorizeRequests().antMatchers("/spitters/me").authenticated().antMatchers(HttpMethod.POST,
		// "/spittles")
		// .authenticated().anyRequest().permitAll();

		// login page, adds role security requirement, remember me functionality
		// enabled
		http.formLogin().loginPage("/login").and().rememberMe().tokenValiditySeconds(2419200).key("spittrKey").and()
				.authorizeRequests().antMatchers("/spitter/me").hasRole("SPITTER")
				.antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER").anyRequest().permitAll().and()
				.requiresChannel().antMatchers("/spitter/form") // require https
				.requiresSecure().and().logout().logoutSuccessUrl("/");  //specify where to go upon successful logout

		// ****use SpEL to express more security requirements. limited in
		// previous examples above.
		// http.authorizeRequests().antMatchers("/spitter/me")
		// .access("hasRole('ROLE_SPITTER') and hasIpAddress('192.168.1.2')");
	}

	/**
	 * In Memory User Store config
	 */
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// // in memory user store. withUser adds user to store with password and
	// // roles
	// // good to testing and debugging
	// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
	// .password("password").roles("USER", "ADMIN");
	// }

	/**
	 * RDBMS User store config
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// relational database user store. change default queries to match your
		// db schema
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, true " + "from Spitter where username=?")
				.authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
				.passwordEncoder(new StandardPasswordEncoder("53cr3t")); // encrypts
																			// password
																			// before
																			// trying
																			// to
																			// match
																			// encrypted
																			// pword
																			// in
																			// db
	}

	/**
	 * LDap user store configuration
	 */
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})").groupSearchBase("ou=groups")
	// .groupSearchFilter("member={0}").passwordCompare();
	// }

	/*
	 * If you need to authenticate against NOSQL database then implement a
	 * custom UserDetailsService
	 * org.springframework.security.core.userdetails.UserDetailsService
	 */
}

// Default queries for RDBMS

// public static final String DEF_USERS_BY_USERNAME_QUERY =
// "select username,password,enabled " +
// "from users " +
// "where username = ?";
// public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
// "select username,authority " +
// "from authorities " +
// "where username = ?";
// public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
// "select g.id, g.group_name, ga.authority " +
// "from groups g, group_members gm, group_authorities ga " +
// "where gm.username = ? " +
// "and g.id = ga.group_id " +
// "and g.id = gm.group_id";