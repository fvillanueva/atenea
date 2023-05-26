package com.fvilla.services.authservice.dtos;

import com.fvilla.services.authservice.models.Role;

public record UserCredentialDTO(String email, String password, Role role) {
}
