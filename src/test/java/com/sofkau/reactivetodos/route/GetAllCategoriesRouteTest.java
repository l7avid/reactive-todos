package com.sofkau.reactivetodos.route;

import com.sofkau.reactivetodos.collection.Category;
import com.sofkau.reactivetodos.collection.ToDo;
import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.mapper.TodoMapper;
import com.sofkau.reactivetodos.repository.ICategoryRepository;
import com.sofkau.reactivetodos.usecase.GetAllCategoriesUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;



@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GetAllCategoriesRoute.class, GetAllCategoriesUseCase.class, TodoMapper.class})
public class GetAllCategoriesRouteTest {
    @MockBean
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetAllCategories() {
        Category category1 = new Category();
        category1.setId("1");
        category1.setTitle("Primera Categoria");
        category1.setMessage("Hogar");
        List<ToDo> todos1 = new ArrayList<>();
        todos1.add(new ToDo("Planchar"));
        todos1.add(new ToDo("Lavar"));
        todos1.add(new ToDo("Cocinar"));
        category1.setToDos(todos1);
        Category category2 = new Category();
        category2.setId("2");
        category2.setTitle("Segunda Categoria");
        category2.setMessage("Universidad");
        List<ToDo> todos2 = new ArrayList<>();
        todos2.add(new ToDo("Estudiar"));
        todos2.add(new ToDo("Estudiar mas"));
        todos2.add(new ToDo("Seguir estudiando"));
        category2.setToDos(todos2);

        Mockito.when(iCategoryRepository.findAll())
                .thenReturn(Flux.just(category1, category2));

        webTestClient.get()
                .uri("/v1/api/categories")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CategoryDTO.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(category1.getId());
                    Assertions.assertThat(userResponse.get(0).getMessage()).isEqualTo(category1.getMessage());
                    Assertions.assertThat(userResponse.get(0).getTitle()).isEqualTo(category1.getTitle());
                    Assertions.assertThat(userResponse.get(0).getToDos()).isEqualTo(category1.getToDos());
                    Assertions.assertThat(userResponse.get(1).getId()).isEqualTo(category2.getId());
                    Assertions.assertThat(userResponse.get(1).getTitle()).isEqualTo(category2.getTitle());
                    Assertions.assertThat(userResponse.get(1).getMessage()).isEqualTo(category2.getMessage());
                    Assertions.assertThat(userResponse.get(1).getToDos()).isEqualTo(category2.getToDos());
                });
    }
}