package com.JPA.example.LMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentEmailUpdateResponselDto {  //we will get these three attributes as response
    private int id;

    private String name;

    private String email;
}
