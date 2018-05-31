package com.lingyun.demos.shiro.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = {
        "com.lingyun.demos.**.controller",
        "com.lingyun.demos.**.config",
        "com.lingyun.demos.**.service",
        "com.lingyun.demos.**.repository"})
@EnableJpaRepositories(basePackages = {"com.lingyun.demos.**.repository"})
@EnableAutoConfiguration
@EntityScan(basePackages ={"com.lingyun.demos.**.entity"})
@ServletComponentScan("com.lingyun.demos.**.filter")
public class Application implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
    @Bean
    public DataSource dataSourceH2() {
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/shiro;MVCC=TRUE");
        return ds;
    }
}