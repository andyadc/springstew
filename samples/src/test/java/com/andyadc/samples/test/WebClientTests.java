package com.andyadc.samples.test;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * https://reflectoring.io/spring-webclient/
 */
public class WebClientTests {

    @Test
    public void testGetRequest() {
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://www.baidu.com")
                .retrieve();
    }
}
