package com.br.alura.forum.topico;

import com.br.alura.forum.curso.Curso;
import com.br.alura.forum.usuario.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
		@NotBlank
		String titulo,
		@NotBlank
		String mensagem,
		@NotNull
		Long idAutor,
		@NotNull
		Long idCurso) {

}
