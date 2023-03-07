package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entity.Book;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class BookControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookRepository bookRepository;

   @InjectMocks
    private BookController bookController;

    Book RECORD_1 = new Book(1L, "Atomic Habits", "How to build better habits", 5);
    Book RECORD_2 = new Book(2L, "Think Fast and slow", "How to create models of thinking", 4);
    Book RECORD_3 = new Book(3L, "Grokking Algoritms", "Learn algoritms the fun way", 5);


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

    }

    @Test
    public void getAllRecords_success() throws Exception{

        List<Book> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));

        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/book")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)));
               // .andExpect(jsonPath("$[2].name", is("Grokking Algoritms")));
    }

}