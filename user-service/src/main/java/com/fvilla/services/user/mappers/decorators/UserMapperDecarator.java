package com.fvilla.services.user.mappers.decorators;

import com.fvilla.services.user.dtos.CourseDTO;
import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.mappers.UserMapper;
import com.fvilla.services.user.models.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class UserMapperDecarator implements UserMapper {

    private final UserMapper userMapper;

    @Override
    public UserDTO toDto(User user, List<CourseDTO> courses) {
        return userMapper.toDto(user, courses).toBuilder()
                .courses(courses)
                .build();
    }
}
