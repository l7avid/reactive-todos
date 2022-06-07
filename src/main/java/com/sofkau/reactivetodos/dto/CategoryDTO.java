package com.sofkau.reactivetodos.dto;

import com.sofkau.reactivetodos.collection.ToDo;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CategoryDTO {
    private String id;

    @NotBlank(message = "Title can't be blank")
    private String title;

    @NotBlank(message = "Message can't be blank")
    private String message;

    private List<ToDo> toDos;
}
