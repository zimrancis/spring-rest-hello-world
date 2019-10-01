package com.ciphersnippet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphersnippet.model.Book;

//Spring Data magic :)
public interface BookRepository extends JpaRepository<Book, Long> {
}
