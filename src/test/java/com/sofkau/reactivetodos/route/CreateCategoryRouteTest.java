package com.sofkau.reactivetodos.route;

import com.sofkau.reactivetodos.collection.ToDo;
import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.mapper.TodoMapper;
import com.sofkau.reactivetodos.repository.ICategoryRepository;
import com.sofkau.reactivetodos.usecase.CreateCategoryUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CreateCategoryRoute.class, CreateCategoryUseCase.class, TodoMapper.class})
class CreateCategoryRouteTest {
    @MockBean
    private ICategoryRepository iCategoryRepository;

    @MockBean
    private TodoMapper todoMapper;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId("1");
        categoryDTO.setTitle("Primera Categoria");
        categoryDTO.setMessage("Hogar");
        List<ToDo> todos1 = new ArrayList<>();
        categoryDTO.setToDos(todos1);

        Mockito.when(iCategoryRepository.save(todoMapper.toEntity(categoryDTO))
                .thenReturn(Mono.just(categoryDTO)));

        webTestClient.post()
                .uri("/v1/api/recipe/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(categoryDTO), CategoryDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CategoryDTO.class)
                .value(categoryDTO1 -> {
                    Assertions.assertThat(categoryDTO1.getId()).isEqualTo(categoryDTO.getId());
                });

    }
}