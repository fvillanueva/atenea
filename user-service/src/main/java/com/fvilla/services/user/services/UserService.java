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

import java.util.List;
import java.util.Optional;

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

    public UserDTO getById(long userId) {
        return userMapper.toDto(repository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Failed to find user with id " + userId)), getUserCourses(userId));
    }

    private List<CourseDTO> getUserCourses(long userId) {
        return repository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Failed to find user with id " + userId)).getCourses().stream().map(courseClient::get).toList();
    }

    public void save(UserDTO userDTO) {
        repository.save(userMapper.toEntity(userDTO));
        log.info("Saving new user {} to the database", userDTO.getEmail());
    }

    public void update(long id, UserDTO userDTO) {
        User updateUser = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Failed to find user with id " + id));
        updateUser.setFirstName(userDTO.getFirstName());
        updateUser.setLastName(userDTO.getLastName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setDateOfBirth(userDTO.getDateOfBirth());
    }

    public void addCourseToUser(long userId, long courseId) {
        Optional.ofNullable(courseClient.get(courseId)).ifPresent(course -> repository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Failed to find user with id " + userId)).getCourses().add(courseId));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
        log.info("Deleting user with id {}", id);
    }
}
