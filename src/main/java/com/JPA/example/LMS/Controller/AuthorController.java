package com.JPA.example.LMS.Controller;

import com.JPA.example.LMS.Entity.Author;
import com.JPA.example.LMS.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String aaAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return "Author Added Succefully";
    }

}
