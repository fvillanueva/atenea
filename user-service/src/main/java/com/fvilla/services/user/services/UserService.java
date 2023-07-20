package com.fvilla.services.user.services;

import com.fvilla.services.user.clients.CourseClient;
import com.fvilla.services.user.dtos.CourseDTO;
import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.mappers.UserMapper;
import com.fvilla.services.user.models.User;
import com.fvilla.services.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final CourseClient courseClient;

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(userMapper::toDto).toList();
    }

    private User getById(long userId) {
        return repository.findById(userId).orElseThrow(() -> new EntityNotFoundException(String.format("Failed to find user with id %s", userId)));
    }

    public UserDTO getByIdWithCourses(long userId) {
        User user = getById(userId);
        return userMapper.toDto(user, getCourses(user.getCourses()));
    }

    private List<CourseDTO> getCourses(List<Long> coursesId) {
        return Optional.ofNullable(coursesId)
                .map(course -> course.stream()
                        .map(courseClient::find)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public void save(UserDTO userDTO) {
        repository.save(userMapper.toEntity(userDTO));
        log.info("Saving new user {} to the database", userDTO.email());
    }

    public void update(long userId, UserDTO userDTO) {
        User updateUser = getById(userId);
        updateUser.setFirstName(userDTO.firstName());
        updateUser.setLastName(userDTO.lastName());
        updateUser.setEmail(userDTO.email());
        updateUser.setDateOfBirth(userDTO.dateOfBirth());
    }

    public void addCourseToUser(long userId, long courseId) {
        User user = getById(userId);
        Optional.ofNullable(user.getCourses())
                .ifPresentOrElse(courses -> user.getCourses().add(courseId), () -> user.setCourses(List.of(courseId)));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
        log.info("Deleting user with id {}", id);
    }
}
