package com.example.accessingdatamysql.repositories;

import com.example.accessingdatamysql.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
