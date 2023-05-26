package com.fvilla.services.authservice.services;

import com.fvilla.services.authservice.dtos.UserCredentialDTO;
import com.fvilla.services.authservice.mappers.UserCredentialMapper;
import com.fvilla.services.authservice.models.UserCredential;
import com.fvilla.services.authservice.repositories.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;
    private final UserCredentialMapper userCredentialMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserCredential getByUsername(String username) {
        return userCredentialRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Failed to find UserCredentials with username " + username));
    }

    private UserDetails save(UserCredentialDTO userCredentialDTO) {
        UserCredential user = userCredentialMapper.toEntity(userCredentialDTO);
        user.setPassword(passwordEncoder.encode(userCredentialDTO.password()));
        log.info("Saving new user {} to the database", userCredentialDTO.email());
        return userCredentialRepository.save(user);
    }

    public String authenticate(UserCredentialDTO userCredential) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCredential.email(),
                        userCredential.password()
                )
        );
        var user = getByUsername(userCredential.email());
        return jwtService.generateToken(user);
    }

    public String register(UserCredentialDTO userCredential) {
        var user = save(userCredential);
        return jwtService.generateToken(user);
    }
}
