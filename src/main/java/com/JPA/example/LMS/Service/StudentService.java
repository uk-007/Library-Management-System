package com.JPA.example.LMS.Service;

import com.JPA.example.LMS.DTO.StudentEmailUpdateResponselDto;
import com.JPA.example.LMS.DTO.StudentRequestDto;
import com.JPA.example.LMS.DTO.StudentUpdateEmailRequestDto;
import com.JPA.example.LMS.Entity.LibraryCard;
import com.JPA.example.LMS.Entity.Student;
import com.JPA.example.LMS.Enum.CardStatus;
import com.JPA.example.LMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setEmail(studentRequestDto.getEmail());
        student.setDepartment(studentRequestDto.getDepartment());


        LibraryCard card = new LibraryCard();
        //setting the value of new card
        card.setStatus(CardStatus.ACTIVATED);

        card.setStudent(student);  //if we don't set student in card then foreign key value in card table will be null

        //set the card attribute in student
        student.setCard(card);


        studentRepository.save(student);  //will save both in student and card due to CascadeType.All

    }
    public String findStudentByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student.getName();

    }
    public StudentEmailUpdateResponselDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        //Dto class not contains some attributes so dto,s cannot be passed to rep layer because database only understands student class not studentRequestDto
        //therefore we need to convert dto into student class in service layer

        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        //update email
        Student updatedStudent = studentRepository.save(student);  //return type od save is object

        //convert updated student to response dto
        StudentEmailUpdateResponselDto studentEmailUpdateResponselDto = new StudentEmailUpdateResponselDto();
        studentEmailUpdateResponselDto.setId(updatedStudent.getId());
        studentEmailUpdateResponselDto.setEmail(updatedStudent.getEmail());
        studentEmailUpdateResponselDto.setName(updatedStudent.getName());

        return studentEmailUpdateResponselDto;
    }

    public String delete(int id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return "Student deleted";
    }

}
