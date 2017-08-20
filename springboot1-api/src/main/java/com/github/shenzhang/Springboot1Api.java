package com.github.shenzhang;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * User: shenzhang
 * Date: 3/27/14
 * Time: 2:13 PM
 */

@SpringBootApplication
@RestController
public class Springboot1Api {
    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Springboot1Api.class);
        application.run(args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setDefaultMaxPerRoute(100);
        connManager.setMaxTotal(100);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .build();

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        return restTemplate;
    }

    @GetMapping("/test")
    public String test() {
        return restTemplate.getForObject("http://localhost:9002/backend-api/hello", String.class);
    }
}
