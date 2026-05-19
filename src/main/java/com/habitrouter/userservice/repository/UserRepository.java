package com.habitrouter.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.habitrouter.userservice.model.User;;;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ //work on user table, id type long

    Optional<User> findByEmail(String email);
}
