package com.example.accessingdatamysql.repositories;

import com.example.accessingdatamysql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
