package com.JPA.example.LMS.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    private String email;

    private int mobNo;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();   //first author coming then initializing empty book list and we can have another api to add books
                                            //itnitializing this book parameter with emptylist. no need to set again from backend or postman
}
