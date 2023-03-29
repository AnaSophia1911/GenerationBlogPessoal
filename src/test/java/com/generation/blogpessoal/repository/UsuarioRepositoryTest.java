package com.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "João Gomes", "jao@email.com", "12345678", ""));
		
		usuarioRepository.save(new Usuario(0L, "Manoel Gomes", "canetaazul@email.com", "sheeeesh", ""));
		
		usuarioRepository.save(new Usuario(0L, "Junior Gomes", "juninhoc@email.com", "eojunior", ""));
		
		usuarioRepository.save(new Usuario(0L, "Marina Jacinto", "marininha@email.com", "aaaaaaaa", ""));
				
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("jao@email.com");
		assertTrue(usuario.get().getUsuario().equals("jao@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List <Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("gomes");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João Gomes"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manoel Gomes"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Junior Gomes"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}

}
 