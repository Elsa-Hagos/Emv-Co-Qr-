package com.lib.emv.co.Config;


import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
//import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() throws NoSuchAlgorithmException, SSLException {
        // Trust manager that trusts all certificates
        TrustManager trustAllCertificates = new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        };

        // Create a Netty SslContext with our trust manager that trusts all certificates
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(trustAllCertificates) // Use a single TrustManager here
                .build();

        // Create an HttpClient using the custom SslContext
        HttpClient httpClient = HttpClient.create()
                .secure(ssl -> ssl.sslContext(sslContext));

        // Build the WebClient with the custom HttpClient
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
