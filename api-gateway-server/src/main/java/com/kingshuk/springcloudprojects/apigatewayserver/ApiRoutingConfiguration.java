package com.kingshuk.springcloudprojects.apigatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiRoutingConfiguration {

    @Bean
    public RouteLocator applicationRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/currency-convertor-feign/**")
                        .uri("http://currency-conversion-service/currency-convertor-v2/(?<remaining>.*)"))
                .build();
    }
}
