package com.thiago.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{
	
}
