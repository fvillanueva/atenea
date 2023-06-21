package com.fvilla.services.user.mappers.decorators;

import com.fvilla.services.user.dtos.CourseDTO;
import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.mappers.UserMapper;
import com.fvilla.services.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

public abstract class UserMapperDecarator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper userMapper;

    @Override
    public UserDTO toDto(User user, Set<CourseDTO> courses) {
        return userMapper.toDto(user, courses).toBuilder()
                .courses(courses)
                .build();
    }
}
