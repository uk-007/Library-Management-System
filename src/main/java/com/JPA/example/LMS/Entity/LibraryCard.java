package com.JPA.example.LMS.Entity;

import com.JPA.example.LMS.Enum.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

    @CreationTimestamp
    private Date creationDate;


    @UpdateTimestamp
    private Date updationDate;


    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "card" , cascade = CascadeType.ALL)
    List<Transaction> transaction = new ArrayList<>();

   @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    List<Book> booksIssued = new ArrayList<>();                //initially no books issued so we can initialize with empty list



}
