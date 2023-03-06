package com.jawbr.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jawbr.entity.SourceBook;

public interface SourceBookRepository extends JpaRepository<SourceBook, Integer> {

	public Optional<SourceBook> findBySourceName(String sourceName);

}
