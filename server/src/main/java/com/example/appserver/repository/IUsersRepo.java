package com.example.appserver.repository;

import com.example.appserver.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepo extends JpaRepository<Users, Integer> {
}
