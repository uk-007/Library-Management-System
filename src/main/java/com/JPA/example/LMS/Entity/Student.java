package com.JPA.example.LMS.Entity;

import com.JPA.example.LMS.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor        //Annotation for no argument constructor
@AllArgsConstructor
@Getter                  //Annotation for getters and setters
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    private String email;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)   //in inverted comma we need to write variable which helps to connect child
            //class to parent class. child class me kis variable se map hai (in this case it is student)
            //CascadeType.All---> all the crud operations that is performed in parent class, will be applied in child class also
            //mappedBy is always written in parents class
            //we want library card is generated as soon as student registers in college
    LibraryCard card;

}
