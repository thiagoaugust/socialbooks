package com.thiago.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.socialbooks.domain.Livro;
import com.thiago.socialbooks.repository.LivrosRepository;
import com.thiago.socialbooks.service.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		Livro livro = livrosRepository.getById(id);
		if(livro == null) {
			throw new LivroNaoEncontradoException("O livro não foi encontrado");
		}
		return livro;
	}
}
