package com.learnreactivespring.router;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learnreactivespring.handler.SampleHandlerFunction;

@Component
public class RouterFunctionConfig {
    @Bean
    public RouterFunction<ServerResponse> route(
            SampleHandlerFunction handlerFunction) {
        return RouterFunctions.route(
                RequestPredicates.GET("/functional/flux")
                        .and(RequestPredicates
                                .accept(MediaType.APPLICATION_JSON)),
                handlerFunction::flux)
                .andRoute(RequestPredicates.GET("/functional/mono")
                        .and(RequestPredicates
                                .accept(MediaType.APPLICATION_JSON)),
                        handlerFunction::mono);

    }
}
