package com.JPA.example.LMS.Repository;

import com.JPA.example.LMS.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {
}
