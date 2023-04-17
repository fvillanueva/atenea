package com.fvilla.services.course.services;

import com.fvilla.services.course.dtos.CourseDTO;
import com.fvilla.services.course.mappers.CourseMapper;
import com.fvilla.services.course.models.Course;
import com.fvilla.services.course.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> getAll() {
        return repository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    public CourseDTO getById(long id) {
        return courseMapper.toDTO(repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Failed to find course with id " + id)));
    }

    public void save(CourseDTO courseDTO) {
        repository.save(courseMapper.toEntity(courseDTO));
        log.info("Saving new course {} to the database", courseDTO.getName());
    }

    public void update(long id, CourseDTO courseDTO) {
        Course updateCourse = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Failed to find course with id " + id));
        updateCourse.setName(courseDTO.getName());
        updateCourse.setDescription(courseDTO.getDescription());
    }

    public void deleteById(long id) {
        repository.deleteById(id);
        log.info("Deleting course with id {}", id);
    }
}
