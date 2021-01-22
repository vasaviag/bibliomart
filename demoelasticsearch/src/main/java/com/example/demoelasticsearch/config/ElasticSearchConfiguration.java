
package com.example.demoelasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;



@Configuration
@EnableElasticsearchRepositories(value = "com.example.demoelasticsearch.repository")
public class ElasticSearchConfiguration {

}

