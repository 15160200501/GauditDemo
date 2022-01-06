package com.example.gauditdemo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @author Frederic.Hu
 * @Description
 * @date 2021/12/02 13:37
 */
@Component
public class ESConfigClient {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public RestHighLevelClient esClient(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

}

