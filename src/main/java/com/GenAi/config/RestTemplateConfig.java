/*
package com.GenAi.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() throws Exception {
        // Build SSLContext that trusts all certificates
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, new TrustAllStrategy())
                .build();

        // Build connection manager with custom SSLContext
        PoolingHttpClientConnectionManager connManager =
                PoolingHttpClientConnectionManagerBuilder.create()
                        .setSslContext(sslContext)
                        .build();

        // Build HttpClient with that connection manager
        HttpClient client = HttpClients.custom()
                .setConnectionManager(connManager)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }
}
*/
