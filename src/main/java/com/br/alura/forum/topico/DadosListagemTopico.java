package com.br.alura.forum.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String autor,
		String curso) {
	
	public DadosListagemTopico(Topico topico) {
		this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getIdAutor().getNome(),
				topico.getIdCurso().getNome());
		
	}
}
