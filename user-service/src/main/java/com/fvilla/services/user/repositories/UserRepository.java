package com.fvilla.services.user.repositories;

import com.fvilla.services.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
