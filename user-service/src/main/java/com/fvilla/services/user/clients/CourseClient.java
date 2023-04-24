package com.fvilla.services.user.clients;

import com.fvilla.services.user.dtos.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "course-service/course-service")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    Optional<CourseDTO> get(@PathVariable("id") long id);

}
