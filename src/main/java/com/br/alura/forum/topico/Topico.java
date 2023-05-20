package com.br.alura.forum.topico;

import java.time.LocalDateTime;

import com.br.alura.forum.curso.Curso;
import com.br.alura.forum.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	@Enumerated
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_autor")
	private Usuario idAutor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_curso")
	private Curso idCurso;

	public Topico(DadosCadastroTopico dados, Usuario autor, Curso curso) {
		this.titulo = dados.titulo();
		this.mensagem = dados.mensagem();
		this.dataCriacao = LocalDateTime.now();
		this.idAutor = autor;
		this.idCurso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void atualizar(DadosAtualizacaoTopicos dados) {
		if(dados.titulo() != null) {
			this.titulo = dados.titulo();
		}
		if (dados.mensagem() != null) {
			this.mensagem = dados.mensagem();
		}
		
	}
	
	public void mudarStatus(StatusTopico status) {
		this.status = status;
	}

}
