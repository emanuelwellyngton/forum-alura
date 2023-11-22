package com.br.alura.forum;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.curso.Curso;
import com.br.alura.forum.curso.CursoRepository;
import com.br.alura.forum.topico.DadosAtualizacaoTopicos;
import com.br.alura.forum.topico.DadosCadastroTopico;
import com.br.alura.forum.topico.DadosListagemTopico;
import com.br.alura.forum.topico.StatusTopico;
import com.br.alura.forum.topico.Topico;
import com.br.alura.forum.topico.TopicoRepository;
import com.br.alura.forum.usuario.Usuario;
import com.br.alura.forum.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository repositoryTopico;
	@Autowired
	private CursoRepository repositoryCurso;
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
		Curso curso = repositoryCurso.getReferenceById(dados.idCurso());
		Usuario usuario = repositoryUsuario.getReferenceById(dados.idAutor());
		Topico topico = new Topico(dados, usuario, curso);
		repositoryTopico.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemTopico(topico));
	}
	
	@GetMapping
	public ResponseEntity<Page<Topico>> listar(Pageable paginacao) {
		Page<Topico> paginas = repositoryTopico.findAll(paginacao);
		return ResponseEntity.ok(paginas);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopicos dados) {
		Topico topico = repositoryTopico.getReferenceById(id);
		topico.atualizar(dados);
		return ResponseEntity.ok(new DadosListagemTopico(topico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity arquivar(@PathVariable Long id) {
		Topico topico = repositoryTopico.getReferenceById(id);
		topico.mudarStatus(StatusTopico.FECHADO);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		Topico topico = repositoryTopico.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemTopico(topico));
	}

}
