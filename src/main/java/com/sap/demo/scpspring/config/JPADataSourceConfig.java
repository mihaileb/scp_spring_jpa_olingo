package com.sap.demo.scpspring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPADataSourceConfig {

	@Bean
	public JpaVendorAdapter jpaVendorAdapterHana() {
		EclipseLinkJpaVendorAdapter va = new EclipseLinkJpaVendorAdapter();
		va.setDatabase(Database.DEFAULT);
		va.setDatabasePlatform("org.eclipse.persistence.platform.database.HANAPlatform");
		va.setShowSql(false);
		va.setGenerateDdl(false);

		return va;
	}

	@Bean
	public DataSource getDataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("jdbc/DefaultDB");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(getDataSource());
		emfb.setJpaVendorAdapter(jpaVendorAdapter);

		Properties jpaProperties = new Properties();

		jpaProperties.put("eclipselink.weaving", "static");
		jpaProperties.put("eclipselink.weaving", "false");
		jpaProperties.put("eclipselink.ddl-generation.output-mode", "both");
		jpaProperties.put("eclipselink.ddl-generation", "none");
		jpaProperties.put("eclipselink.logging.level.sql", "FINEST");
		emfb.setJpaProperties(jpaProperties);
		emfb.setPackagesToScan("com.sap.demo.scpspring.*");
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
