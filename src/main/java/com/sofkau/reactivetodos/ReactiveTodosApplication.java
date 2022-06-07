package com.sofkau.reactivetodos;

import com.sofkau.reactivetodos.mapper.TodoMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveTodosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveTodosApplication.class, args);
    }

    @Bean
    public TodoMapper TodoMapper() {
        return new TodoMapper();
    }
}
