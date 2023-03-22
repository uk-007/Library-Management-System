package com.JPA.example.LMS.DTO;

import com.JPA.example.LMS.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestDto {
    private String title;

    private int price;

    private Genre genre;

    private int authorId;
}
