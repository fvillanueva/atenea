package com.fvilla.services.user.controllers;

import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") long id) {
        return userService.getByIdWithCourses(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable("id") long id, @RequestBody @Valid UserDTO userDTO) {
        userService.update(id, userDTO);
    }

    @PutMapping("/{id}/courses")
    @Transactional
    public void addCourseToUser(@PathVariable("id") long userId, long courseId) {
        userService.addCourseToUser(userId, courseId);
    }

    @DeleteMapping
    public void deleteById(@PathVariable("id") long id) {
        userService.deleteById(id);
    }
}
