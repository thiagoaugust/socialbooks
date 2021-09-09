package com.thiago.socialbooks.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thiago.socialbooks.domain.Comentario;
import com.thiago.socialbooks.domain.Livro;
import com.thiago.socialbooks.repository.ComentarioRepository;
import com.thiago.socialbooks.repository.LivrosRepository;
import com.thiago.socialbooks.service.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	public List<Livro> listar() {
		return livrosRepository.findAll();
	}

	public Optional<Livro> buscar(Long id) {
		Optional<Livro> livro = livrosRepository.findById(id);
		if (!livro.isPresent()) {
			throw new LivroNaoEncontradoException("O livro não foi encontrado");
		}
		return livro;
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}

	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}

	public void atualizar(Livro livro) {
		verificaSeLivroExiste(livro);
		livrosRepository.save(livro);
	}

	private void verificaSeLivroExiste(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Optional<Livro> livro = buscar(livroId);
		comentario.setLivro(livro.get());
		comentario.setData(new Date());
		return comentarioRepository.save(comentario);
	}

	public List<Comentario> listarComentariosDoLivro(Long livroId) {
		Livro livro = buscar(livroId).get();
		return livro.getComentarios();		
	}

}
