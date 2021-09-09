package com.thiago.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.socialbooks.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
