package com.fvilla.services.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
public record UserDTO(
        @Size(min = 1, max = 100, message = "First name must be of 1 - 100 characters")
        String firstName,
        @Size(min = 1, max = 100, message = "Last name must be of 1 - 100 characters")
        String lastName,
        @Email
        String email,
        @Past
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth,
        List<CourseDTO> courses){}
