package com.sofkau.reactivetodos.route;

import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.usecase.GetAllCategoriesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetAllCategoriesRoute {
    @Bean
    public RouterFunction<ServerResponse> allCategories(GetAllCategoriesUseCase get) {
        return route(GET("/v1/api/categories"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(get.apply(), CategoryDTO.class))
        );
    }
}
