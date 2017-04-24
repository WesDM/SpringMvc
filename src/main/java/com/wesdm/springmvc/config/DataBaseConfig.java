package com.wesdm.springmvc.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages="com.wesdm.springmvc.spittr.db")
public class DataBaseConfig {

	// Data source injected at runtime based on profile active
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();

		lcemfb.setDataSource(dataSource);
		lcemfb.setPackagesToScan(new String[] { "com.wesdm.springmvc.domain" }); // only point to packages with @Enitity
		// lcemfb.setPersistenceUnitName("MyTestPU");

		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcemfb.setJpaVendorAdapter(va);

		Properties ps = new Properties();
		ps.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		ps.put("hibernate.hbm2ddl.auto", "create-drop"); // create tables based on ORM mappings
		// ps.put("hibernate.hbm2ddl.import_files", "sql/testData.sql"); // insert test data
		// ps.put("hibernate.dialect", env.getProperty("hibernate.dialect")); //gets property from Environment object

		lcemfb.setJpaProperties(ps);

		lcemfb.afterPropertiesSet();

		return lcemfb;

	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@Profile("prod")
	public JndiObjectFactoryBean dataSourceJndi() {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean(); // pooled
		jndiObjectFB.setJndiName("jdbc/SpittrDS");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFB;
	}

	@Bean
	@Profile("qa")
	public DataSource dataSourceC3P0() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("org.h2.Driver");
		ds.setJdbcUrl("jdbc:h2:tcp://localhost/~/spitter");
		ds.setUser("sa");
		ds.setPassword("");
		ds.setInitialPoolSize(5);
		ds.setMaxPoolSize(10);
		return ds;
	}

	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource(); // not pooled
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public DataSource dataSourceH2() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:schema.sql").addScript("classpath:test-data.sql")
				.build();

		// return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
}
