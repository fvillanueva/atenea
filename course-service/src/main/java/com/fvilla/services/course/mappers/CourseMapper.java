package com.fvilla.services.course.mappers;

import com.fvilla.services.course.dtos.CourseDTO;
import com.fvilla.services.course.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    Course toEntity(CourseDTO courseDTO);

    CourseDTO toDTO(Course course);

}
