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
import java.util.Set;
import java.util.function.LongFunction;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private static final LongFunction<String> FAILED_TO_FIND_ID = id ->  String.format("Failed to find user with id %s", id);

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final CourseClient courseClient;

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserDTO getById(long userId) {
        return userMapper.toDto(repository.findById(userId).orElseThrow(() -> new EntityNotFoundException(FAILED_TO_FIND_ID.apply(userId))), getUserCourses(userId));
    }

    private Set<CourseDTO> getUserCourses(long userId) {
        return repository.findById(userId).orElseThrow(() -> new EntityNotFoundException(FAILED_TO_FIND_ID.apply(userId))).getCourses().stream().map(courseClient::get).collect(Collectors.toSet());
    }

    public void save(UserDTO userDTO) {
        repository.save(userMapper.toEntity(userDTO));
        log.info("Saving new user {} to the database", userDTO.getEmail());
    }

    public void update(long id, UserDTO userDTO) {
        User updateUser = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(FAILED_TO_FIND_ID.apply(id)));
        updateUser.setFirstName(userDTO.getFirstName());
        updateUser.setLastName(userDTO.getLastName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setDateOfBirth(userDTO.getDateOfBirth());
    }

    public void addCourseToUser(long userId, long courseId) {
        Optional.ofNullable(courseClient.get(courseId)).ifPresent(course -> repository.findById(userId).orElseThrow(() -> new EntityNotFoundException(FAILED_TO_FIND_ID.apply(userId))).getCourses().add(courseId));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
        log.info("Deleting user with id {}", id);
    }
}
