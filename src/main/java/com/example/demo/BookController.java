package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class BookController {

    @Autowired
    private MongoDbReactiveRepository mongoDbReactiveRepository;

    @PostMapping(value = "/addBook")
    @ResponseBody Mono<Book> addBook(@RequestBody Book book){
        return mongoDbReactiveRepository.save(book);
    }


    @GetMapping(value = "/getBooksReactive", produces = "text/event-stream")
    @ResponseBody Flux<Book> getBooksReactive() {
        System.out.println("BOOKS REACTIVE");
        return mongoDbReactiveRepository.findAll();
    }
}
