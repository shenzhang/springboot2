package com.github.shenzhang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: shenzhang
 * Date: 3/27/14
 * Time: 2:13 PM
 */

@SpringBootApplication
@RestController
public class BackendApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendApi.class);

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(BackendApi.class);
        application.run(args);
    }

    private AtomicInteger count = new AtomicInteger();

    @GetMapping("/hello")
    public String hello() throws Exception {
        LOGGER.info(Integer.toString(count.incrementAndGet()));

        Thread.sleep(1000);
        return "hello world";
    }

    @GetMapping("/rest")
    public void rest() throws Exception {
        count.set(1);
    }
}
