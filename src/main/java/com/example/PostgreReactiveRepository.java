package com.example;

import com.example.demo.BookRel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgreReactiveRepository extends ReactiveCrudRepository<BookRel, Integer> {
}
