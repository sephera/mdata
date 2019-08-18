package com.github.sephera.mdata.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


public class MDataSourceConfig {

    private String unitName;

    private String prefix;

    private Class<?>[] packages;

    @Autowired
    private Environment environment;

    @Autowired(required = false)
    PersistenceUnitManager persistenceUnitManager;

    @Bean
    public JpaProperties jpaProperties() {
        return Binder.get(environment)
                .bind(prefix, JpaProperties.class)
                .orElse(null);
    }

    @Bean
    public DataSourceProperties dataSourceProperties() {
        return Binder.get(environment)
                .bind(prefix, DataSourceProperties.class)
                .orElse(null);
    }

    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DataSource.class).build();
    }

    public LocalContainerEntityManagerFactoryBean entityManager() {
        final AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        final JpaProperties properties = this.jpaProperties();
        adapter.setShowSql(properties.isShowSql());
        adapter.setDatabase(properties.getDatabase());
        adapter.setDatabasePlatform(properties.getDatabasePlatform());
        adapter.setGenerateDdl(properties.isGenerateDdl());

        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(adapter,
                properties.getProperties(), persistenceUnitManager);

        final DataSource datasource = this.dataSource();
        return builder
                .dataSource(datasource)
                .packages(packages)
                .persistenceUnit(unitName)
                .build();
    }

    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManager) {
        return new JpaTransactionManager(entityManager.getObject());
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Class<?>[] getPackages() {
        return packages;
    }

    public void setPackages(Class<?>[] packages) {
        this.packages = packages;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public PersistenceUnitManager getPersistenceUnitManager() {
        return persistenceUnitManager;
    }

    public void setPersistenceUnitManager(PersistenceUnitManager persistenceUnitManager) {
        this.persistenceUnitManager = persistenceUnitManager;
    }
}
