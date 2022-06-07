package com.sofkau.reactivetodos.repository;

import com.sofkau.reactivetodos.collection.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ICategoryRepository extends ReactiveMongoRepository<Category, String> {
}
