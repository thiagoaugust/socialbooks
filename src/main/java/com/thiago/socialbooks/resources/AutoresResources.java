package com.thiago.socialbooks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiago.socialbooks.domain.Autor;
import com.thiago.socialbooks.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutoresResources {

	@Autowired
	private AutorService autorService;

	@GetMapping
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(autorService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Autor autor){
		autor = autorService.salvar(autor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
