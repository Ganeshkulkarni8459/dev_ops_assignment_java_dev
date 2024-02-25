package org.dnyanyog.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
	//call back method
	@Bean
	public RouteLocator customeRouteLocator(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("directory_public_route", r -> r.path("/api/v1/public/auth/**").uri("http://localhost:8081"))
				.route("directory_route", r -> r.path("/api/v1/auth/**").uri("http://localhost:8081"))
				.route("customer_route", r -> r.path("/api/v1/customer/**").uri("http://localhost:8083")).build();
	}
}
