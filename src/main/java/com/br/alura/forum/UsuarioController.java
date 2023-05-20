package com.br.alura.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.alura.forum.usuario.DadosCadastroUsuario;
import com.br.alura.forum.usuario.Usuario;
import com.br.alura.forum.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
		repository.save(new Usuario(dados));
	}

}
