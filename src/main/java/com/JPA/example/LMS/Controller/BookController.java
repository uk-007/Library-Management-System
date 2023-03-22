package com.JPA.example.LMS.Controller;

import com.JPA.example.LMS.DTO.BookRequestDto;
import com.JPA.example.LMS.DTO.BookResponseDto;
import com.JPA.example.LMS.Entity.Book;
import com.JPA.example.LMS.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {

        return bookService.addBook(bookRequestDto);
    }


}
