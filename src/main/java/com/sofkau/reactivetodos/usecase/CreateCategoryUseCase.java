package com.sofkau.reactivetodos.usecase;

import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.mapper.TodoMapper;
import com.sofkau.reactivetodos.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreateCategoryUseCase {
    private ICategoryRepository iCategoryRepository;

    private TodoMapper todoMapper;

    public Mono<CategoryDTO> apply(CategoryDTO categoryDTO) {
        return validateDTO(categoryDTO)
                .flatMap(categoryDTO1 -> iCategoryRepository.save(todoMapper.toEntity(categoryDTO1)))
                .map(todoMapper::toCategoryDTO);
    }


    private Mono<CategoryDTO> validateDTO(CategoryDTO categoryDTO) {
        return Mono.just(categoryDTO).filter(this::validateProperties)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Missing properties")));
    }

    private boolean validateProperties(CategoryDTO dto) {
        return dto.getMessage() != null && dto.getMessage() != null;
    }

}

