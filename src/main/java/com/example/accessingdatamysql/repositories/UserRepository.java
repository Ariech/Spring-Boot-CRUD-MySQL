package com.example.accessingdatamysql.repositories;

import com.example.accessingdatamysql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
