package com.fvilla.services.authservice.mappers;

import com.fvilla.services.authservice.dtos.UserCredentialDTO;
import com.fvilla.services.authservice.models.UserCredential;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCredentialMapper {

    UserCredential toEntity(UserCredentialDTO userCredentialDTO);

    UserCredentialDTO toDTO(UserCredential userCredential);

}
