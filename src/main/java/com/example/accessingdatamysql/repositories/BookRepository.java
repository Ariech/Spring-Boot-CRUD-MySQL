package com.example.accessingdatamysql.repositories;

import com.example.accessingdatamysql.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByUserId(Integer id);

    @Transactional
    void deleteByUserId(Integer id);
}
