package com.sofkau.reactivetodos.mapper;

import com.sofkau.reactivetodos.collection.Category;
import com.sofkau.reactivetodos.collection.ToDo;
import com.sofkau.reactivetodos.dto.CategoryDTO;
import com.sofkau.reactivetodos.dto.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
@RequiredArgsConstructor
public class TodoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public CategoryDTO toCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public static Category toEntity(CategoryDTO dto) {
        return modelMapper.map(dto, Category.class);
    }

    public ToDoDTO toToDoDTO(ToDo todo) {
        return modelMapper.map(todo, ToDoDTO.class);
    }

    public ToDo toEntity(ToDoDTO dto) {
        return modelMapper.map(dto, ToDo.class);
    }

}
