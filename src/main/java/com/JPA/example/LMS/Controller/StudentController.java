package com.JPA.example.LMS.Controller;

import com.JPA.example.LMS.DTO.StudentEmailUpdateResponselDto;
import com.JPA.example.LMS.DTO.StudentRequestDto;
import com.JPA.example.LMS.DTO.StudentUpdateEmailRequestDto;
import com.JPA.example.LMS.Entity.Student;
import com.JPA.example.LMS.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        studentService.addStudent(studentRequestDto);
        return "Student Added Successfully";

    }
    @GetMapping("/find_by_email")
    public String findStudentByEmail(@RequestParam("email") String email){
        return studentService.findStudentByEmail(email);
    }

    @PutMapping("/update_email")
    public StudentEmailUpdateResponselDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        return studentService.delete(id);
    }

}
