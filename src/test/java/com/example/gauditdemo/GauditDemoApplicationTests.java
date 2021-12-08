package com.example.gauditdemo;

import com.example.gauditdemo.service.OperationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class GauditDemoApplicationTests {

    @Autowired
    private OperationService operationService;

}
