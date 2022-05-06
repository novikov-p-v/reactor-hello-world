package com.example.demo;

import com.example.PostgreReactiveRepository;
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
    @Autowired
    private PostgreReactiveRepository postgreReactiveRepository;

    @PostMapping(value = "/addBook")
    @ResponseBody Mono<Book> addBook(@RequestBody Book book){
        return mongoDbReactiveRepository.save(book);
    }


    @GetMapping(value = "/getBooksReactive", produces = "text/event-stream")
    @ResponseBody Flux<Book> getBooksReactive() {
        System.out.println("BOOKS REACTIVE");
        return mongoDbReactiveRepository.findAll();
    }

    @GetMapping(value = "/getBooksReactivePostgres", produces = "text/event-stream")
    @ResponseBody Flux<BookRel> getBooksReactivePostgres() {
        System.out.println("BOOKS REACTIVE Postgres");
        return postgreReactiveRepository.findAll();
    }
}
