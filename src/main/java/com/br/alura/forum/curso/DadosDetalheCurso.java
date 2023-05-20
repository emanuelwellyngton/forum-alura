package com.br.alura.forum.curso;

public record DadosDetalheCurso(Long id, String nome, String categoria) {
	
	public DadosDetalheCurso(Curso curso) {
		this(curso.getId(), curso.getNome(), curso.getCategoria());
	}
	
}
