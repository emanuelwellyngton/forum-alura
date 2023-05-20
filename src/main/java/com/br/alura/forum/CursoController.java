package com.br.alura.forum;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.curso.Curso;
import com.br.alura.forum.curso.CursoRepository;
import com.br.alura.forum.curso.DadosCadastroCurso;
import com.br.alura.forum.curso.DadosDetalheCurso;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cursos")
public class CursoController {
	
	@Autowired
	public CursoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder) {
		Curso curso = new Curso(dados);
		repository.save(curso);
		URI uri = uriBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalheCurso(curso));
	}

}
