package com.JPA.example.LMS.Repository;

import com.JPA.example.LMS.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AuthorRepository extends JpaRepository<Author,Integer> {
}
