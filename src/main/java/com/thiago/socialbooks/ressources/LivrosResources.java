package com.thiago.socialbooks.ressources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiago.socialbooks.domain.Livro;
import com.thiago.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosRepository livrosRepository;

	@GetMapping
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		livrosRepository.save(livro);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Optional<Livro> livro = livrosRepository.findById(id);
		
		if(livro.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(livro);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosRepository.save(livro);
		return ResponseEntity.noContent().build();
	}
}
