package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.BookRepository;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository repository;



}
