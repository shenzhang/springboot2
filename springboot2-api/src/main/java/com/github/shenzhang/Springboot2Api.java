package com.github.shenzhang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: shenzhang
 * Date: 3/27/14
 * Time: 2:13 PM
 */

@SpringBootApplication
@RestController
public class Springboot2Api {
    private static final Logger LOGGER = LoggerFactory.getLogger(Springboot2Api.class);

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Springboot2Api.class);
        application.run(args);
    }

    @Autowired
    private WebClient webClient;
    private AtomicInteger count = new AtomicInteger();

    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.create("http://localhost:9002/backend-api");
        return webClient;
    }

    @GetMapping("/test")
    public Mono<String> test() {
        LOGGER.info(Integer.toString(count.incrementAndGet()));

//        serverResponse.headers().add("Content-Length", "11");
        return webClient.get()
                .uri("/hello")
                .exchange()
                .flatMap(response -> response.bodyToMono(String.class));
    }
}
