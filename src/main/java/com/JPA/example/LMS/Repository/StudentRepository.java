package com.JPA.example.LMS.Repository;

import com.JPA.example.LMS.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);    //custom search based on attribute....find letter of attribute in capitals like Email not email
}
