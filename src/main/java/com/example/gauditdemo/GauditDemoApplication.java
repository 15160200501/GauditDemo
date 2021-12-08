package com.example.gauditdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.gauditdemo.dao")//使用MapperScan批量扫描所有的Mapper接口；
@EnableScheduling
public class GauditDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GauditDemoApplication.class, args);
    }

}
