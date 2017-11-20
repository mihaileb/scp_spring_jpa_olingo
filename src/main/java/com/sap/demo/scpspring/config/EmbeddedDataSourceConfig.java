package com.sap.demo.scpspring.config;

//@Configuration
//@EnableJpaRepositories("com.sap.demo.scpspring.*")
//@EnableTransactionManagement
public class EmbeddedDataSourceConfig {

//  @Bean
//  public DataSource dataSource() {
//    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//  }
//
//  @Bean
//  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//    return new JpaTransactionManager(emf);
//  }
//
//  @Bean
//  public JpaVendorAdapter jpaVendorAdapter() {
//    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//    jpaVendorAdapter.setDatabase(Database.H2);
//    jpaVendorAdapter.setGenerateDdl(true);
//    return jpaVendorAdapter;
//  }
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//    LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
//    lemfb.setDataSource(dataSource());
//    lemfb.setJpaVendorAdapter(jpaVendorAdapter());
//    lemfb.setPackagesToScan("com.sap.demo.scpspring.*");
//    return lemfb;
//  }
}
