package com.JPA.example.LMS.Service;

import com.JPA.example.LMS.DTO.BookRequestDto;
import com.JPA.example.LMS.DTO.BookResponseDto;
import com.JPA.example.LMS.Entity.Author;
import com.JPA.example.LMS.Entity.Book;
import com.JPA.example.LMS.Repository.AuthorRepository;
import com.JPA.example.LMS.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;   //because we need details of author (we will find that by id) becuase we are adding book in that we need to set author also

    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {
        Author author;
        try {
            author = authorRepository.findById(bookRequestDto.getAuthorId()).get();  //may or may not contain author thats why exception may occur
        }catch(Exception e){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setTitle("NULL");
            bookResponseDto.setPrice(0);
            return bookResponseDto;
        }



        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        author.getBooks().add(book);   //adding the book in list of books written by author
        //we don't need to set the card attribute (think logically which attribute to set and which not)



        authorRepository.save(author);   //written this line because book is child of author so this save will also be commited in book table

        BookResponseDto bookResponseDto = new BookResponseDto();  //creating a response
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;

    }
}
