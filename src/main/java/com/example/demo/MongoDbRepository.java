package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDbRepository
        extends MongoRepository<Book, Integer> { // (1)
    Iterable<Book> findByAuthorsOrderByPublishingYearDesc(
                                                           String... authors
    );
    @Query("{ 'authors.1': { $exists: true } }") // (3)
    Iterable<Book> booksWithFewAuthors();
}