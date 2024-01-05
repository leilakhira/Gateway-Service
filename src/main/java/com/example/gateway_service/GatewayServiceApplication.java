package com.example.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    public RouteLocator route(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/demandeurs/**").uri("lb://DEMANDE-SERVICE"))
                .route(r->r.path("/demandes/**").uri("lb://DEMANDE-SERVICE"))

                //.route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();
    }
    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoute(ReactiveDiscoveryClient rdc,
                                                              DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

}
