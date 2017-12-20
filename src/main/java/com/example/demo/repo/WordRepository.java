package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long>{

	List<Word> findByValueIgnoreCase(String value);
}
