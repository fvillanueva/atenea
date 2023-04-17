package com.fvilla.services.user.mappers;

import com.fvilla.services.user.dtos.UserDTO;
import com.fvilla.services.user.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDto(User user);

}
