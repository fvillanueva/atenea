package com.fvilla.services.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

    @Size(min = 1, max = 100, message = "First name must be of 1 - 100 characters")
    private final String firstName;
    @Size(min = 1, max = 100, message = "Last name must be of 1 - 100 characters")
    private final String lastName;
    @Email
    private final String email;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate dateOfBirth;
    private final List<CourseDTO> courses;
}
