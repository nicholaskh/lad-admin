package com.lad.admin.datasource;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2018
 * Version: 1.0
 * Time:2018/3/20
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.lad.admin.model", mongoTemplateRef = "baseMongo")
public class BaseMongoTemplate {

    @Autowired
    @Qualifier("baseMongoProperties")
    private MongoProperties mongoProperties;

    @Primary
    @Bean(name = "baseMongo")
    public MongoTemplate baseMongoTemplate() throws Exception {
        return new MongoTemplate(baseFactory(this.mongoProperties));
    }

    @Bean
    @Primary
    public MongoDbFactory baseFactory(MongoProperties mongoProperties) throws Exception {
        ServerAddress serverAdress = new ServerAddress(mongoProperties.getUri());
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList
                .add(MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword()));
        return new SimpleMongoDbFactory(new MongoClient(serverAdress, mongoCredentialList), mongoProperties.getDatabase());
    }
    
}
