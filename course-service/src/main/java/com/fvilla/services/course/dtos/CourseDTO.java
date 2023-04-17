package com.fvilla.services.course.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseDTO {

    @Size(min = 3, max = 30, message = "Name must be of 3 - 50 characters")
    private final String name;
    @Size(min = 3, max = 100, message = "Description must be of 3 - 100 characters")
    private final String description;

}