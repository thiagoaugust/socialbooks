package com.thiago.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.socialbooks.domain.Autor;
import com.thiago.socialbooks.repository.AutorRepository;
import com.thiago.socialbooks.service.exceptions.AutorExistenteException;

@Service
public class AutorService {

	@Autowired
	private AutorRepository  autorRepository;
	
	public List<Autor> listar(){
		return autorRepository.findAll();
	}

	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor autorRetornado = autorRepository.getById(autor.getId());
			if(autorRetornado != null) {
				throw new AutorExistenteException("O autor já existe.");
			}
		}
		autor.setId(null);
		return autorRepository.save(autor);
	}
	
	
}
