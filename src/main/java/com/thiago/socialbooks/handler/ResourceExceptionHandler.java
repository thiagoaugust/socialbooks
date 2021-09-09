package com.thiago.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thiago.socialbooks.service.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<Void> handleLivroNaoEncontradoException(LivroNaoEncontradoException e,
			HttpServletRequest request) {
		return ResponseEntity.notFound().build();
	}
}
