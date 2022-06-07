package com.sofkau.reactivetodos.route;

import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.usecase.CreateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateCategoryRoute {
    @Bean
    public RouterFunction<ServerResponse> createCategory(CreateCategoryUseCase post) {
        return route(POST("/v1/api/category")
                .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CategoryDTO.class)
                        .flatMap(post::apply)
                        .flatMap(categoryDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(categoryDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}
