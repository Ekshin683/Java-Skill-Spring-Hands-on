package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
// Every method return value is serialized directly to HTTP response body
@RestController
public class HelloController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(HelloController.class);

    // GET /hello
    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start");
        String response = "Hello World!!";
        LOGGER.debug("Response: {}", response);
        LOGGER.info("End");
        return response;
    }
}