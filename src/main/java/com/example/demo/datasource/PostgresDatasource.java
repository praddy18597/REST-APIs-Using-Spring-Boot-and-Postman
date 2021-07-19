package com.example.demo.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration    //this class will serve as configuration
class Datasource {    //grabbing all the dependencies from the app and then data source

    @Bean    // we are instantiating HikariDatasource as a bean
    @ConfigurationProperties("app.datasource")  //we create configuration file which contain username,URL,password
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
