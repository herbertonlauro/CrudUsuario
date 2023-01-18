package com.CrudJavaSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CrudJavaSpring.model.Usuario;
import com.CrudJavaSpring.repository.UsuarioRepository;



@RestController
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@GetMapping(path = "/apiusuario/listar")
	public List<Usuario> Listar() {
		return usuariorepository.findAll();
	}
	
	@PostMapping(path = "/apiusuario/salvar")
	public Usuario Salvar(@RequestBody Usuario usuario) {
		return usuariorepository.save(usuario);
		
	}
	
	@GetMapping(path = "/apiusuario/{codigo}")
	public ResponseEntity<Usuario> ConsultarId(@PathVariable("codigo") Integer id) {
		return usuariorepository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
		
	}
	
	
	@PutMapping(path = "/apiusuario/{id}")
	public ResponseEntity<Usuario> Update(@PathVariable ("id") Integer id, @RequestBody Usuario usuario){
		return usuariorepository.findById(id).map(record -> {
			record.setNome(usuario.getNome());
			record.setCpf(usuario.getCpf());
			record.setEmail(usuario.getEmail());
			Usuario updated = usuariorepository.save(record);
			return ResponseEntity.ok().body(updated);
			}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/apiusuario/{id}")
	public ResponseEntity<Object> Deletar(@PathVariable Integer id) {
	   return usuariorepository.findById(id).map(record -> {
		   usuariorepository.deleteById(id);
		   return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
}
