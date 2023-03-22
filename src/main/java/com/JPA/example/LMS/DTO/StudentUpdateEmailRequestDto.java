package com.JPA.example.LMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//made this dto class to take only few inputs so user cannot manipulate data using primary key as input and also we don't need to take
//every attribute as input for updation of email
public class StudentUpdateEmailRequestDto {
    private int id;

    private String email;
}
