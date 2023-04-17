package com.fvilla.services.user.controllers;

import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        return userService.getById(id);
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

    @DeleteMapping
    public void deleteById(@PathVariable("id") long id) {
        userService.deleteById(id);
    }
}
