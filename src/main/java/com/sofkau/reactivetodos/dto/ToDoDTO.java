package com.sofkau.reactivetodos.dto;

import java.util.Objects;

public class ToDoDTO {
    private String task;

    public ToDoDTO(String task) {
        this.task = task;
    }

    public ToDoDTO() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ToDoDTO{" +
                "task='" + task + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoDTO toDoDTO = (ToDoDTO) o;
        return task.equals(toDoDTO.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
