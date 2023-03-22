package com.JPA.example.LMS.Service;

import com.JPA.example.LMS.Entity.Author;
import com.JPA.example.LMS.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public void addAuthor(Author author){
        authorRepository.save(author);
    }
}
