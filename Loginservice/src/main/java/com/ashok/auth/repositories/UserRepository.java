package com.ashok.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ashok.auth.model.User;

public interface UserRepository extends JpaRepository<User, String> {


	User findByUserIdAndPassword(String userId, String password);
}
