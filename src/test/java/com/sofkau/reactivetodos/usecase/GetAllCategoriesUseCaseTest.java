package com.sofkau.reactivetodos.usecase;

import com.sofkau.reactivetodos.collection.Category;
import com.sofkau.reactivetodos.collection.ToDo;
import com.sofkau.reactivetodos.dto.CategoryDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class GetAllCategoriesUseCaseTest {
    @MockBean
    private GetAllCategoriesUseCase getAllCategoriesUseCase;

    @Test
    @DisplayName("testGetAllCategories")
    void testGetAllCategories (){
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId("1");
        categoryDTO1.setTitle("Primera Categoria");
        categoryDTO1.setMessage("Hogar");
        List<ToDo> todos1 = new ArrayList<>();
        todos1.add(new ToDo("Planchar"));
        todos1.add(new ToDo("Lavar"));
        todos1.add(new ToDo("Cocinar"));
        categoryDTO1.setToDos(todos1);
        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId("2");
        categoryDTO2.setTitle("Segunda Categoria");
        categoryDTO2.setMessage("Universidad");
        List<ToDo> todos2 = new ArrayList<>();
        todos2.add(new ToDo("Estudiar"));
        todos2.add(new ToDo("Estudiar mas"));
        todos2.add(new ToDo("Seguir estudiando"));
        categoryDTO2.setToDos(todos2);

        StepVerifier
                .create(Flux.just(Mockito.when(getAllCategoriesUseCase.apply())
                        .thenReturn(Flux.just(categoryDTO1, categoryDTO2))))
                .verifyComplete();
    }
}