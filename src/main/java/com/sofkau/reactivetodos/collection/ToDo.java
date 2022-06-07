package com.sofkau.reactivetodos.collection;


import java.util.Objects;

public class ToDo {
    private String task;

    public ToDo(String task) {
        this.task = task;
    }

    public ToDo() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "task='" + task + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return task.equals(toDo.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
