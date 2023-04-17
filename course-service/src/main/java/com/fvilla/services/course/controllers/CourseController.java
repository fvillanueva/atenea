package com.fvilla.services.course.controllers;

import com.fvilla.services.course.dtos.CourseDTO;
import com.fvilla.services.course.services.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable("id") long id) {
        return courseService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid CourseDTO courseDTO) {
        courseService.save(courseDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable("id") long id, @RequestBody @Valid CourseDTO courseDTO) {
        courseService.update(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        courseService.deleteById(id);
    }

}