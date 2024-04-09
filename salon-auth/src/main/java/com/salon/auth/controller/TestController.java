package com.salon.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test")
    public String get() {
        logger.info("test..................");
        return "test success!!";
    }

    @RequestMapping("/test2")
    public String get2() {
        logger.info("test222..................");
        return "test success!!";
    }

}
