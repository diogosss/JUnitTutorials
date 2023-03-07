package controller;

import entity.Book;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository repository;

    @GetMapping
    public List<Book> getAllBooksRecords(){
        return repository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Book getBookById(@PathVariable(value = "bookId") Long bookId){
        return repository.findById(bookId).get();
    }

    @PostMapping
    public Book createBookRecord(@RequestBody @Validated Book bookrecord){
        return repository.save(bookrecord);
    }

    @PutMapping
    public Book updateBookRecord(@RequestBody @Validated Book bookRecord) throws NotFoundException {
        if(bookRecord == null || bookRecord.getBookId() == null){
            throw new NotFoundException("BookRecord or ID must not be null");
        }
        Optional<Book> optionalBook = repository.findById(bookRecord.getBookId());
        if(!optionalBook.isPresent()){
            throw new NotFoundException("Book with ID: " + bookRecord.getBookId() + " does not exist");
        }

        Book existingBookRecord = optionalBook.get();
        existingBookRecord.setName(bookRecord.getName());
        existingBookRecord.setSummary(bookRecord.getSummary());
        existingBookRecord.setRating(bookRecord.getRating());

        return repository.save(existingBookRecord);
    }

    // TODO: Write /delete endpoint using TDD method


}
