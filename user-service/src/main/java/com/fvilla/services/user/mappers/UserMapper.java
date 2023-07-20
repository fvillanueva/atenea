package com.fvilla.services.user.mappers;

import com.fvilla.services.user.dtos.CourseDTO;
import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.mappers.decorators.UserMapperDecarator;
import com.fvilla.services.user.models.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(UserMapperDecarator.class)
public interface UserMapper {

    @Mapping(target = "courses", ignore = true)
    User toEntity(UserDTO userDTO);

    @Mapping(target = "courses", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "courses", ignore = true)
    UserDTO toDto(User user, List<CourseDTO> courses);

}
