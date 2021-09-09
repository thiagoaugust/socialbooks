package com.thiago.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.socialbooks.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
