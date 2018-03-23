package com.lad.admin.datasource;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/20
 */
@Configuration
public class MutliMongoProperties {

    @Bean(name="baseMongoProperties")
    @Primary
    @ConfigurationProperties(prefix="spring.data.mongodb.base")
    public MongoProperties statisMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name="inforMongoProperties")
    @ConfigurationProperties(prefix="spring.data.mongodb.infor")
    public MongoProperties listMongoProperties() {
        return new MongoProperties();
    }
}
