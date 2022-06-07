package com.sofkau.reactivetodos.usecase;

import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.mapper.TodoMapper;
import com.sofkau.reactivetodos.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GetAllCategoriesUseCase {

    private ICategoryRepository iCategoryRepository;

    private TodoMapper todoMapper;

    public Flux<CategoryDTO> apply() {
/*
        return iCategoryRepository.findAll().map(category -> {
           return todoMapper.toCategoryDTO(category);
        });
*/
//        return iCategoryRepository.findAll().map(category -> todoMapper.toCategoryDTO(category));
        return iCategoryRepository.findAll().map(todoMapper::toCategoryDTO);
    }

}
